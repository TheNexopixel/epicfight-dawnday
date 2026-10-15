package net.epicfight_dd.skill.mover;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import net.epicfight_dd.network.AirDashPacket;
import net.epicfight_dd.network.DDNetworkHandler;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import yesman.epicfight.api.client.input.InputManager;
import yesman.epicfight.api.client.input.action.MinecraftInputAction;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

// THIS CODE WAS MADE MOSTLY BY AI gez. Nexo

public class AirDashSkill extends Skill {
    private static final UUID EVENT_UUID =
            UUID.fromString("c78e7b20-2f5b-4d5d-a6a3-83bfc11e9041");

    private static final String DASH_USED =
            "epicfight_dd_air_dash_used";

    private double dashPower = 0.65D;
    private float staminaCost = 4.0F;

    private static class DashState {
        private boolean jumpPressedLastTick = false;
    }


    private final Map<SkillContainer, DashState> states =
            new WeakHashMap<>();

    private DashState getState(SkillContainer container) {
        return states.computeIfAbsent(
                container,
                key -> new DashState()
        );
    }

    public AirDashSkill(SkillBuilder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);

        if (parameters.contains("dash_power")) {
            this.dashPower = parameters.getDouble("dash_power");
        }

        if (parameters.contains("consumption")) {
            this.staminaCost = parameters.getFloat("consumption");
        }
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        getState(container);

        PlayerEventListener listener =
                container.getExecutor().getEventListener();


        listener.addEventListener(
                EventType.MOVEMENT_INPUT_EVENT,
                EVENT_UUID,
                event -> {
                    var playerPatch = event.getPlayerPatch();
                    var player = playerPatch.getOriginal();

                    if (!player.level().isClientSide) {
                        return;
                    }

                    if(player.isCreative()) {
                        return;
                    }

                    DashState state = getState(container);

                    boolean jumpPressed = isJumpPressed();
                    boolean justPressed =
                            jumpPressed && !state.jumpPressedLastTick;

                    state.jumpPressedLastTick = jumpPressed;

                    if (!justPressed) {
                        return;
                    }

                    if (player.onGround()
                            || player.getVehicle() != null
                            || player.getAbilities().flying
                            || playerPatch.getEntityState().inaction()
                            || playerPatch.isHoldingAny()) {
                        return;
                    }

                    DDNetworkHandler.INSTANCE.sendToServer(
                            new AirDashPacket()
                    );
                }
        );

        listener.addEventListener(
                EventType.FALL_EVENT,
                EVENT_UUID,
                event -> {
                    DashState state = getState(container);
                    state.jumpPressedLastTick = false;
                }
        );
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        states.remove(container);

        PlayerEventListener listener =
                container.getExecutor().getEventListener();

        listener.removeListener(
                EventType.MOVEMENT_INPUT_EVENT,
                EVENT_UUID
        );

        listener.removeListener(
                EventType.FALL_EVENT,
                EVENT_UUID
        );
    }

    @Override
    public boolean canExecute(SkillContainer container) {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    private static boolean isJumpPressed() {
        return InputManager.isActionActive(
                MinecraftInputAction.JUMP
        );
    }

    public void performServerDash(
            ServerPlayerPatch playerPatch,
            SkillContainer container
    ) {
        ServerPlayer player =
                (ServerPlayer) playerPatch.getOriginal();

        if (player.level().isClientSide) {
            return;
        }

        if (player.onGround()
                || player.getVehicle() != null
                || player.getAbilities().flying
                || player.isUnderWater()
                || player.isSpectator()
                || playerPatch.getEntityState().inaction()
                || playerPatch.isHoldingAny()) {
            return;
        }

        if (player.getPersistentData().getBoolean(DASH_USED)) {
            return;
        }

        if (!playerPatch.hasStamina(staminaCost)) {
            return;
        }

        Vec3 look = player.getLookAngle();
        Vec3 horizontal = new Vec3(look.x, 0.0D, look.z);

        if (horizontal.lengthSqr() < 1.0E-6D) {
            return;
        }

        horizontal = horizontal.normalize().scale(dashPower);

        playerPatch.setStamina(
                playerPatch.getStamina() - staminaCost
        );

        if (player.hasEffect(EffectRegistry.ENDURANCE.get())){
            playerPatch.setStaminaRegenAwaitTicks(15);
        } else{
            playerPatch.setStaminaRegenAwaitTicks(40);
        }
        player.addEffect(
                new MobEffectInstance(
                        MobEffects.MOVEMENT_SPEED,
                        10,
                        2,
                        false,
                        false,
                        false
                )
        );

        Vec3 velocity = player.getDeltaMovement();

        player.setDeltaMovement(
                velocity.x + horizontal.x,
                velocity.y,
                velocity.z + horizontal.z
        );

        player.hurtMarked = true;

        playerPatch.playAnimationSynchronized(
                QoLMiscAnimations.AIR_DASH,
                0.1F
        );
        player.getPersistentData().putBoolean(DASH_USED, true);
    }
}