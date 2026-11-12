package net.epicfight_dd.skill.passive;

import java.util.UUID;

import net.epicfight_dd.gameasset.DawnDaySounds;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector4f;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataKey;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.EntityDecorations;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

// THIS CODE WAS MADE MOSTLY BY AI
// gez. Nexo

public class RapidRetreatSkill extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("6c7e22f1-a92b-47df-8b19-0e7a5c31d604");
    private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("91c22d12-8a1f-4c93-82f0-2b19ab2c8f41");
    private static final ResourceLocation OVERLAY_ID =
            ResourceLocation.fromNamespaceAndPath("epicfight_dd", "rapid_retreat_overlay");

    private float healthThreshold = 0.30F;
    private double speedBonus = 0.20D;
    private float damageMultiplier = 0.40F;
    private int cooldownTicks = 60 * 20;
    private int equipCooldownTicks = 5 * 20;
    private int activeDurationTicks = 30 * 20;

    public RapidRetreatSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    private static boolean isActive(SkillContainer container) {
        return container.getDataManager().getDataValue(SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get());
    }

    private static int cooldown(SkillContainer container) {
        return container.getDataManager().getDataValue(SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get());
    }

    private static int duration(SkillContainer container) {
        return container.getDataManager().getDataValue(SkillDataKeyZ.RAPID_RETREAT_DURATION.get());
    }

    private static <T> void store(SkillContainer container, SkillDataKey<T> key, T value, boolean sync) {
        if (sync) {
            container.getDataManager().setDataSync(key, value);
        } else {
            container.getDataManager().setData(key, value);
        }
    }

    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
        if (parameters.contains("health_threshold")) this.healthThreshold = parameters.getFloat("health_threshold");
        if (parameters.contains("speed_bonus")) this.speedBonus = parameters.getDouble("speed_bonus");
        if (parameters.contains("damage_multiplier")) this.damageMultiplier = parameters.getFloat("damage_multiplier");
        if (parameters.contains("cooldown")) this.cooldownTicks = parameters.getInt("cooldown");
        if (parameters.contains("equip_cooldown")) this.equipCooldownTicks = parameters.getInt("equip_cooldown");
        if (parameters.contains("active_duration")) this.activeDurationTicks = parameters.getInt("active_duration");
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
                    if (isActive(container)) {
                        event.attachValueModifier(ValueModifier.multiplier(this.damageMultiplier));
                    }
                }
        );

        if (!container.getExecutor().isLogicalClient()) {
            store(container, SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get(), this.equipCooldownTicks, false);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT, EVENT_UUID);

        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            removeSpeed(player);
            store(container, SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get(), false, player.connection != null);
        }
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);

        if (container.getExecutor().isLogicalClient()) {
            tickClient(container);
            return;
        }

        ServerPlayer player = container.getServerExecutor().getOriginal();
        boolean secondMark = player.tickCount % 20 == 0;

        if (isActive(container)) {
            int remaining = duration(container) - 1;
            if (remaining <= 0) {
                end(container, player);
            } else {
                applySpeed(player);
                store(container, SkillDataKeyZ.RAPID_RETREAT_DURATION.get(), remaining, secondMark);
            }
            return;
        }

        int cooldown = cooldown(container);
        if (cooldown > 0) {
            cooldown--;
            store(container, SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get(), cooldown, cooldown == 0 || secondMark);
            return;
        }

        if (belowThreshold(player)) {
            start(container, player);
        }
    }

    private void tickClient(SkillContainer container) {
        if (isActive(container)) {
            int remaining = duration(container);
            if (remaining > 0) {
                store(container, SkillDataKeyZ.RAPID_RETREAT_DURATION.get(), remaining - 1, false);
            }
        } else {
            int remaining = cooldown(container);
            if (remaining > 0) {
                store(container, SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get(), remaining - 1, false);
            }
        }
    }

    private boolean belowThreshold(Player player) {
        return player.isAlive()
                && player.getMaxHealth() > 0.0F
                && player.getHealth() / player.getMaxHealth() < this.healthThreshold;
    }

    private void start(SkillContainer container, ServerPlayer player) {
        store(container, SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get(), true, true);
        store(container, SkillDataKeyZ.RAPID_RETREAT_DURATION.get(), this.activeDurationTicks, true);

        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1, true, false, false));
        applySpeed(player);

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                DawnDaySounds.GUARD_COUNTER.get(), SoundSource.PLAYERS, 1.4F, 0.7F);
    }

    private void end(SkillContainer container, ServerPlayer player) {
        store(container, SkillDataKeyZ.RAPID_RETREAT_ACTIVE.get(), false, true);
        store(container, SkillDataKeyZ.RAPID_RETREAT_DURATION.get(), 0, true);
        store(container, SkillDataKeyZ.RAPID_RETREAT_COOLDOWN.get(), this.cooldownTicks, true);

        removeSpeed(player);

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS, 1.0F, 1.1F);
    }

    private void applySpeed(Player player) {
        AttributeInstance movement = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movement != null && movement.getModifier(SPEED_MODIFIER_UUID) == null) {
            movement.addTransientModifier(new AttributeModifier(SPEED_MODIFIER_UUID,
                    "Rapid Retreat speed bonus", this.speedBonus, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
    }

    private static void removeSpeed(Player player) {
        AttributeInstance movement = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movement != null) movement.removeModifier(SPEED_MODIFIER_UUID);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return isActive(container) || cooldown(container) > 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics graphics,
                          float x, float y, float partialTick) {
        graphics.blit(this.getSkillTexture(), (int) x, (int) y, 24, 24, 0, 0, 1, 1, 1, 1);

        int remaining = isActive(container) ? duration(container) : cooldown(container);
        if (remaining > 0) {
            String text = String.valueOf((remaining + 19) / 20);
            graphics.drawString(gui.getFont(), text,
                    (int) x + (24 - gui.getFont().width(text)) / 2, (int) y + 15, 0xFFFFFF, true);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void onInitiateClient(SkillContainer container) {
        super.onInitiateClient(container);

        Player player = container.getExecutor().getOriginal();
        Vector4f color = new Vector4f(0.3F, 0.7F, 0.4F, 0.0F);

        container.getExecutor().getEntityDecorations()
                .addDecorationOverlay(OVERLAY_ID, new EntityDecorations.DecorationOverlay() {
                    @Override
                    public Vector4f color(float partialTick) {
                        if (!isActive(container) || player.getMaxHealth() <= 0.0F) {
                            return color.set(0.3F, 0.7F, 0.4F, 0.0F);
                        }
                        float healthRatio = player.getHealth() / player.getMaxHealth();
                        float alpha = Mth.clampedLerp(0.0F, 0.25F, 1.0F - healthRatio);
                        return color.set(0.3F, 0.7F, 0.4F, alpha);
                    }

                    @Override
                    public boolean shouldRemove() {
                        return container.getExecutor().getSkill(RapidRetreatSkill.this) == null;
                    }
                });
    }
}