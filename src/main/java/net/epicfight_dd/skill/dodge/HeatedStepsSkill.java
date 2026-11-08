package net.epicfight_dd.skill.dodge;

import java.util.UUID;
import net.epicfight_dd.gameasset.SkillSoundEffects;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.KeyMapping;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.client.input.InputManager;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.events.engine.ControlEngine;
import yesman.epicfight.client.input.EpicFightKeyMappings;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.network.client.CPSkillRequest;
import yesman.epicfight.network.server.SPSkillExecutionFeedback;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.dodge.DodgeSkill;
import yesman.epicfight.skill.modules.ChargeableSkill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

public final class HeatedStepsSkill extends DodgeSkill implements ChargeableSkill {
    private static final UUID INPUT = UUID.fromString("fa0f0692-9c54-41f5-b987-24e89f8c72c7");
    public record TrailParameters(int duration, double radius, float damage, int interval, int burnTicks, float burnChance) { }
    private TrailParameters trail = new TrailParameters(60, .75, 2, 10, 140, .25F);

    public HeatedStepsSkill(DodgeSkill.Builder builder) { super(held(builder)); }
    private static DodgeSkill.Builder held(DodgeSkill.Builder builder) {
        builder.setActivateType(ActivateType.HELD);
        return builder;
    }

    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
        double radius = parameters.contains("trail_radius") ? parameters.getDouble("trail_radius") : .75;
        float damage = parameters.contains("trail_damage") ? parameters.getFloat("trail_damage") : 2;
        float chance = parameters.contains("burn_chance") ? parameters.getFloat("burn_chance") : .25F;
        trail = new TrailParameters(60, Double.isFinite(radius) && radius > 0 ? radius : .75,
                Float.isFinite(damage) && damage >= 0 ? damage : 2, 10, HeatedStepsRules.BURN_TICKS,
                Float.isFinite(chance) ? Math.max(0, Math.min(1, chance)) : .25F);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecutor().getEventListener().addEventListener(EventType.MOVEMENT_INPUT_EVENT, INPUT, event -> {
            var patch = event.getPlayerPatch();
            if (patch.isHoldingSkill(this) && patch.getSkillChargingTicks() >= HeatedStepsRules.TAP_TICKS)
                InputManager.setInputState(event.getInputState().withLeftImpulse(0).withForwardImpulse(0)
                        .withUp(false).withDown(false).withLeft(false).withRight(false).withJumping(false));
        });
    }

    @Override @OnlyIn(Dist.CLIENT)
    public KeyMapping getKeyMapping() { return EpicFightKeyMappings.DODGE; }

    @Override @OnlyIn(Dist.CLIENT)
    public void gatherHoldArguments(SkillContainer container, ControlEngine engine, FriendlyByteBuf buffer) {
        engine.setHoldingKey(container.getSlot(), getKeyMapping());
        container.getExecutor().startSkillHolding(this);
    }

    @Override
    public void startHolding(SkillContainer container) {
        container.getDataManager().setData(SkillDataKeyZ.HEATED_STEPS_RESULT.get(), -1);
        container.getDataManager().setData(SkillDataKeyZ.HEATED_STEPS_RESULT_UNTIL.get(), 0);
    }

    @Override
    public void holdTick(SkillContainer container) {
        ChargeableSkill.super.holdTick(container);
        var patch = container.getExecutor();
        if (patch.isLogicalClient()) {
            if (patch.getSkillChargingTicks() == HeatedStepsRules.TAP_TICKS)
                SkillSoundEffects.heatedMeter(patch.getOriginal());
            return;
        }
        int ticks = patch.getSkillChargingTicks();
        if (!validPlayer(patch) || !patch.isEpicFightMode()
                || ticks <= HeatedStepsRules.TAP_TICKS && !patch.getEntityState().canUseSkill()
                || ticks > HeatedStepsRules.TAP_TICKS && !charging(patch)) {
            cancelOnServer(container, null);
            return;
        }
        if (ticks == HeatedStepsRules.TAP_TICKS)
            patch.playAnimationSynchronized(Animations.BIPED_DEMOLITION_LEAP_CHARGING, 0);
        if (ticks >= HeatedStepsRules.TAP_TICKS) {
            Vec3 velocity = patch.getOriginal().getDeltaMovement();
            patch.getOriginal().setDeltaMovement(0, velocity.y, 0);
        }
    }

    @Override @OnlyIn(Dist.CLIENT)
    public Object getExecutionPacket(SkillContainer container, FriendlyByteBuf arguments) {
        int ticks = Math.max(0, container.getExecutor().getSkillChargingTicks());
        freeze(container, ticks);
        CPSkillRequest packet = (CPSkillRequest)super.getExecutionPacket(container, arguments);
        packet.getBuffer().writeInt(ticks);
        return packet;
    }

    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf buffer) {
        var patch = container.getServerExecutor();
        if (buffer.readableBytes() < 12 || !patch.isHoldingSkill(this)) return;
        int animation = buffer.getInt(buffer.readerIndex());
        float yaw = buffer.getFloat(buffer.readerIndex() + 4);
        int requested = buffer.getInt(buffer.readerIndex() + 8);
        int elapsed = patch.getSkillChargingTicks();
        if (animation < 0 || animation >= animations.length || !Float.isFinite(yaw) || requested < 0
                || !validPlayer(patch) || !patch.isEpicFightMode()
                || elapsed < HeatedStepsRules.TAP_TICKS && !patch.getEntityState().canUseSkill()
                || elapsed > HeatedStepsRules.TAP_TICKS && !charging(patch)) {
            cancelOnServer(container, null);
            return;
        }
        int ticks = HeatedStepsRules.validatedTicks(requested, elapsed);
        container.getDataManager().setData(SkillDataKeyZ.HEATED_STEPS_RESULT.get(), ticks);
        int tier = HeatedStepsRules.tier(ticks);
        container.getDataManager().setDataSync(SkillDataKeyZ.HEATED_STEPS_TIER.get(), tier);
        super.executeOnServer(container, buffer);
        TrailParameters active = tier >= 3
                ? new TrailParameters(90, trail.radius() * 1.5, trail.damage(), trail.interval(), trail.burnTicks(), trail.burnChance()) : trail;
        HeatedStepsTrail.begin(container, active, tier);
        SkillSoundEffects.heatedStep(patch.getOriginal(), tier);
    }

    @Override
    public void onStopHolding(SkillContainer container, SPSkillExecutionFeedback feedback) {
        feedback.getBuffer().writeInt(container.getDataManager().getDataValue(SkillDataKeyZ.HEATED_STEPS_RESULT.get()));
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void executeOnClient(SkillContainer container, FriendlyByteBuf buffer) {
        if (buffer.readableBytes() < 8) return;
        buffer.readInt();
        freeze(container, buffer.readInt());
    }

    private static void freeze(SkillContainer container, int ticks) {
        container.getDataManager().setData(SkillDataKeyZ.HEATED_STEPS_RESULT.get(), ticks);
        container.getDataManager().setData(SkillDataKeyZ.HEATED_STEPS_RESULT_UNTIL.get(),
                container.getExecutor().getOriginal().tickCount + HeatedStepsRules.RESULT_TICKS);
    }

    @Override
    public void cancelOnServer(SkillContainer container, FriendlyByteBuf buffer) {
        var patch = container.getExecutor();
        if (!patch.isHoldingSkill(this)) return;
        boolean idle = charging(patch);
        patch.resetHolding();
        super.cancelOnServer(container, buffer);
        if (idle) patch.playAnimationSynchronized(Animations.BIPED_IDLE, 0);
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void cancelOnClient(SkillContainer container, FriendlyByteBuf buffer) {
        super.cancelOnClient(container, buffer);
        if (container.getExecutor().isHoldingSkill(this)) {
            container.getExecutor().resetHolding();
            ClientEngine.getInstance().controlEngine.releaseAllServedKeys();
        }
        container.getDataManager().setData(SkillDataKeyZ.HEATED_STEPS_RESULT_UNTIL.get(), 0);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
        if (!container.getExecutor().isLogicalClient()) {
            HeatedStepsTrail.follow(container);
            if (!rolling(container.getExecutor()) && container.getDataManager().getDataValue(SkillDataKeyZ.HEATED_STEPS_TIER.get()) != 0)
                container.getDataManager().setDataSync(SkillDataKeyZ.HEATED_STEPS_TIER.get(), 0);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecutor().getEventListener().removeListener(EventType.MOVEMENT_INPUT_EVENT, INPUT);
        clear(container);
        super.onRemoved(container);
    }

    @Override
    public void onReset(SkillContainer container) {
        clear(container);
        super.onReset(container);
    }

    private void clear(SkillContainer container) {
        var patch = container.getExecutor();
        if (patch.isHoldingSkill(this)) {
            boolean idle = charging(patch);
            patch.resetHolding();
            if (idle && !patch.isLogicalClient() && patch.getOriginal().isAlive())
                patch.playAnimationSynchronized(Animations.BIPED_IDLE, 0);
        }
        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) HeatedStepsTrail.clear(player);
    }

    private static boolean validPlayer(PlayerPatch<?> patch) {
        var player = patch.getOriginal();
        return player.isAlive() && !player.isRemoved() && !player.isSpectator() && player.onGround()
                && !player.isInWaterOrBubble() && !player.onClimbable() && player.getVehicle() == null;
    }

    private static boolean charging(LivingEntityPatch<?> patch) {
        return patch.getAnimator().getPlayerFor(null).getAnimation().get().getRealAnimation()
                == Animations.BIPED_DEMOLITION_LEAP_CHARGING;
    }

    public static boolean rolling(LivingEntityPatch<?> patch) {
        var animation = patch.getAnimator().getPlayerFor(null).getAnimation().get().getRealAnimation();
        return animation == Animations.BIPED_ROLL_FORWARD || animation == Animations.BIPED_ROLL_BACKWARD;
    }

    public static float distanceScale(LivingEntityPatch<?> patch) {
        if (!(patch instanceof PlayerPatch<?> player)) return 1;
        var container = player.getSkill(SkillSlots.DODGE);
        if (container == null || !(container.getSkill() instanceof HeatedStepsSkill)) return 1;
        return HeatedStepsRules.distance(container.getDataManager().getDataValue(SkillDataKeyZ.HEATED_STEPS_TIER.get()));
    }

    @Override public int getMinChargingTicks() { return -1; }
    @Override public int getMaxChargingTicks() { return Integer.MAX_VALUE; }
    @Override public int getAllowedMaxChargingTicks() { return Integer.MAX_VALUE - 1; }
    @Override @OnlyIn(Dist.CLIENT)
    public ResourceLocation getSkillTexture() { return EpicFightSkills.ROLL.getSkillTexture(); }
}
