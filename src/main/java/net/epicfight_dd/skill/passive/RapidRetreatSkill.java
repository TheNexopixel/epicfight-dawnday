
package net.epicfight_dd.skill.passive;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.epicfight_dd.gameasset.DawnDaySounds;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import org.joml.Vector4f;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.EntityDecorations;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

public class RapidRetreatSkill extends PassiveSkill {
    // THIS CODE WAS MADE MOSTLY BY AI
    // gez. Nexo

    private static final UUID EVENT_UUID =
            UUID.fromString("6c7e22f1-a92b-47df-8b19-0e7a5c31d604");

    private static final UUID DAMAGE_EVENT_UUID =
            UUID.fromString("b31d4e20-9f61-4a6c-a8b7-1e93d4f60c12");

    private static final UUID SPEED_MODIFIER_UUID =
            UUID.fromString("91c22d12-8a1f-4c93-82f0-2b19ab2c8f41");

    private static final ResourceLocation OVERLAY_ID =
            ResourceLocation.fromNamespaceAndPath("epicfight_dd", "rapid_retreat_overlay");

    private static final Set<UUID> ACTIVE_PLAYERS =
            ConcurrentHashMap.newKeySet();

    private float healthThreshold = 0.30F;
    private double speedBonus = 0.20D;
    private float damageMultiplier = 0.40F;

    private int cooldownTicks = 60 * 20;
    private int equipCooldownTicks = 5 * 20;
    private int activeDurationTicks = 30 * 20;

    private static final String ACTIVE_KEY = "rapid_retreat_active";

    public RapidRetreatSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }


    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);

        if (parameters.contains("health_threshold")) {
            this.healthThreshold = parameters.getFloat("health_threshold");
        }

        if (parameters.contains("speed_bonus")) {
            this.speedBonus = parameters.getDouble("speed_bonus");
        }

        if (parameters.contains("damage_multiplier")) {
            this.damageMultiplier = parameters.getFloat("damage_multiplier");
        }

        if (parameters.contains("cooldown")) {
            this.cooldownTicks = parameters.getInt("cooldown");
        }

        if (parameters.contains("equip_cooldown")) {
            this.equipCooldownTicks = parameters.getInt("equip_cooldown");
        }

        if (parameters.contains("active_duration")) {
            this.activeDurationTicks = parameters.getInt("active_duration");
        }
    }


    @Override
    public float getCooldownRegenPerSecond(PlayerPatch<?> playerpatch) {
        return 1.0F;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);


        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT,
                EVENT_UUID,
                event -> {
                    boolean active = container.getDataManager().getDataValue(
                            SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get()
                    );

                    if (!active) {
                        return;
                    }

                    event.attachValueModifier(
                            ValueModifier.multiplier(damageMultiplier)
                    );
                }

        );
        container.getDataManager().setData(
                SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get(),
                equipCooldownTicks
        );
    }


    private boolean isActive(Player player) {
        return player.isAlive()
                && player.getMaxHealth() > 0.0F
                && player.getHealth() / player.getMaxHealth()
                < healthThreshold;
    }



    private void activate(Player player) {
        var movement = player.getAttribute(Attributes.MOVEMENT_SPEED);

        ACTIVE_PLAYERS.add(player.getUUID());

        player.addEffect(
                new MobEffectInstance(
                        MobEffects.REGENERATION,
                        300,
                        0,
                        true,
                        false,
                        false
                )
        );
        player.addEffect(
                new MobEffectInstance(
                        MobEffects.DAMAGE_RESISTANCE,
                        600,
                        1,
                        true,
                        false,
                        false
                )
        );

        player.level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                DawnDaySounds.GUARD_COUNTER.get(),
                net.minecraft.sounds.SoundSource.PLAYERS,
                1.4F,
                0.7F
        );

        if (movement != null
                && movement.getModifier(SPEED_MODIFIER_UUID) == null) {
            movement.addTransientModifier(
                    new AttributeModifier(
                            SPEED_MODIFIER_UUID,
                            "Rapid Retreat speed bonus",
                            speedBonus,
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    )
            );
        }
    }

    private void deactivate(Player player) {
        var movement = player.getAttribute(Attributes.MOVEMENT_SPEED);
        ACTIVE_PLAYERS.remove(player.getUUID());

        if (movement != null) {
            movement.removeModifier(SPEED_MODIFIER_UUID);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        MinecraftForge.EVENT_BUS.unregister(this);

        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT,
                EVENT_UUID
        );

        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            if (!player.level().isClientSide) {
                deactivate(player);

                container.getDataManager().setDataSync(
                        SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get(),
                        false
                );
            }
        }
    }
    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);

        if (!(container.getExecutor().getOriginal() instanceof ServerPlayer player)) {
            return;
        }

        if (player.level().isClientSide) {
            return;
        }

        boolean active = container.getDataManager().getDataValue(
                SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get()
        );

        int cooldown = container.getDataManager().getDataValue(
                SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get()
        );

        int duration = container.getDataManager().getDataValue(
                SkillDataKeyZ.RAPID_RETREAT_DURATION.get()
        );

        if (cooldown > 0) {
            cooldown--;

            container.getDataManager().setData(
                    SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get(),
                    cooldown
            );

            if (player.tickCount % 20 == 0 || cooldown == 0) {
                container.getDataManager().setDataSync(
                        SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get(),
                        cooldown
                );
            }
        }

        if (active) {

            if (duration > 0) {
                duration--;

                container.getDataManager().setData(
                        SkillDataKeyZ.RAPID_RETREAT_DURATION.get(),
                        duration
                );
            }

            if (duration <= 0) {
                container.getDataManager().setDataSync(
                        SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get(),
                        false
                );

                container.getDataManager().setDataSync(
                        SkillDataKeyZ.RAPID_RETREAT_DURATION.get(),
                        0
                );

                deactivate(player);

                player.level().playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.BEACON_DEACTIVATE,
                        net.minecraft.sounds.SoundSource.PLAYERS,
                        1.0F,
                        1.1F
                );


                container.getDataManager().setDataSync(
                        SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get(),
                        cooldownTicks
                );
            } else if (player.tickCount % 20 == 0) {
                container.getDataManager().setDataSync(
                        SkillDataKeyZ.RAPID_RETREAT_DURATION.get(),
                        duration
                );
            }

            return;
        }

        if (cooldown > 0) {
            return;
        }


        if (isActive(player)) {
            container.getDataManager().setDataSync(
                    SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get(),
                    true
            );

            container.getDataManager().setDataSync(
                    SkillDataKeyZ.RAPID_RETREAT_DURATION.get(),
                    activeDurationTicks
            );

            activate(player);
        }
    }

    @Override
    public void drawOnGui(
            BattleModeGui gui,
            SkillContainer container,
            GuiGraphics guiGraphics,
            float x,
            float y,
            float partialTick
    ) {
        guiGraphics.blit(
                this.getSkillTexture(),
                (int) x,
                (int) y,
                24,
                24,
                0, 0,
                1, 1,
                1, 1
        );

        boolean active = container.getDataManager().getDataValue(
                SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get()
        );

        int remainingTicks;

        if (active) {

            remainingTicks = container.getDataManager().getDataValue(
                    SkillDataKeyZ.RAPID_RETREAT_DURATION.get()
            );
        } else {

            remainingTicks = container.getDataManager().getDataValue(
                    SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get()
            );
        }

        if (remainingTicks > 0) {
            int seconds = (int) Math.ceil(remainingTicks / 20.0F);

            String text = String.valueOf(seconds);

            int textWidth = gui.getFont().width(text);

            guiGraphics.drawString(
                    gui.getFont(),
                    text,
                    (int) x + (24 - textWidth) / 2,
                    (int) y + 15,
                    16777215,
                    true
            );
        }
    }

    @Override
    public boolean shouldDraw(SkillContainer container) {
        boolean active = container.getDataManager().getDataValue(
                SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get()
        );

        int cooldown = container.getDataManager().getDataValue(
                SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get()
        );

        return active || cooldown > 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void onInitiateClient(SkillContainer container) {
        super.onInitiateClient(container);

        Player player = container.getExecutor().getOriginal();

        container.getExecutor().getEntityDecorations()
                .addDecorationOverlay(OVERLAY_ID, new EntityDecorations.DecorationOverlay() {

                    @Override
                    public Vector4f color(float partialTick) {
                        boolean active = container.getDataManager().getDataValue(
                                SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get()
                        );

                        if (!active || player.getMaxHealth() <= 0.0F) {
                            return new Vector4f(0.66F, 0.06F, 0.07F, 0.0F);
                        }

                        float healthRatio = player.getHealth() / player.getMaxHealth();

                        float alpha = Mth.clampedLerp(
                                0.0F,
                                0.25F,
                                1.0F - healthRatio
                        );

                        return new Vector4f(0.3F, 0.7F, 0.4F, alpha);
                    }

                    @Override
                    public boolean shouldRemove() {
                        return container.getExecutor().getSkill(
                                RapidRetreatSkill.this
                        ) == null;
                    }
                });
    }


}
