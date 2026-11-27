package net.epicfight_dd.skill.passive.unstoppable;

import java.util.UUID;
import net.epicfight_dd.attribute.DawnDayAttributes;
import net.epicfight_dd.effect.DawnDayEffects;
import net.epicfight_dd.gameasset.SkillSoundEffects;
import net.epicfight_dd.network.UnstoppableVisualPacket;
import net.epicfight_dd.skill.DawnDaySkillDataKeys;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataKey;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.effect.EpicFightMobEffects;

public final class UnstoppableSkill extends PassiveSkill {
    private static final UUID HEALING_MODIFIER = UUID.fromString("c81c493c-5864-4279-b8fa-6486b904b2da");
    private static final String BARRIER_UNTIL = "epicfight_dd.unstoppable.barrier_until";
    private static final int DEFAULT_BARRIER_COOLDOWN = 200;
    private static final int DEFAULT_REGENERATION_INTERVAL = 1200;
    private int barrierCooldownTicks = DEFAULT_BARRIER_COOLDOWN;
    private int regenerationIntervalTicks = DEFAULT_REGENERATION_INTERVAL;

    public UnstoppableSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
        barrierCooldownTicks = positiveTicks(parameters, "barrier_cooldown_ticks", DEFAULT_BARRIER_COOLDOWN);
        regenerationIntervalTicks = positiveTicks(parameters, "regeneration_interval_ticks", DEFAULT_REGENERATION_INTERVAL);
    }

    private static int positiveTicks(CompoundTag parameters, String key, int fallback) {
        if (!parameters.contains(key, Tag.TAG_ANY_NUMERIC)) return fallback;
        double ticks = parameters.getDouble(key);
        return ticks >= 1 && ticks <= Integer.MAX_VALUE ? (int) ticks : fallback;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getDataManager().setData(DawnDaySkillDataKeys.UNSTOPPABLE_AMBIENT_DELAY.get(), 0);
        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            sync(container, DawnDaySkillDataKeys.UNSTOPPABLE_STACKS.get(), 0);
            container.getDataManager().setData(DawnDaySkillDataKeys.UNSTOPPABLE_REGENERATION.get(), 0D);
            sync(container, DawnDaySkillDataKeys.UNSTOPPABLE_COOLDOWN.get(), remainingCooldown(player));
            refresh(container);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getDataManager().setData(DawnDaySkillDataKeys.UNSTOPPABLE_AMBIENT_DELAY.get(), 0);
        if (!container.getExecutor().getOriginal().level().isClientSide) {
            container.getDataManager().setData(DawnDaySkillDataKeys.UNSTOPPABLE_REGENERATION.get(), 0D);
            clearEffects(container.getExecutor().getOriginal());
        }
        super.onRemoved(container);
    }

    @Override
    public void onReset(SkillContainer container) {
        container.getDataManager().setData(DawnDaySkillDataKeys.UNSTOPPABLE_AMBIENT_DELAY.get(), 0);
        if (container.getExecutor().getOriginal() instanceof ServerPlayer) {
            container.getDataManager().setData(DawnDaySkillDataKeys.UNSTOPPABLE_REGENERATION.get(), 0D);
            setStacks(container, 0);
        }
        super.onReset(container);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
        int cooldown = cooldown(container);
        if (container.getExecutor().isLogicalClient()) {
            if (cooldown > 0) {
                container.getDataManager().setData(DawnDaySkillDataKeys.UNSTOPPABLE_COOLDOWN.get(), cooldown - 1);
            }
            return;
        }
        ServerPlayer player = container.getServerExecutor().getOriginal();
        int remaining = remainingCooldown(player);
        container.getDataManager().setData(DawnDaySkillDataKeys.UNSTOPPABLE_COOLDOWN.get(), remaining);
        if (remaining != cooldown && remaining % 20 == 0) {
            container.getDataManager().setDataSync(DawnDaySkillDataKeys.UNSTOPPABLE_COOLDOWN.get(), remaining);
        }
        if (container.isDisabled() || !player.isAlive()) {
            container.getDataManager().setData(DawnDaySkillDataKeys.UNSTOPPABLE_REGENERATION.get(), 0D);
            setStacks(container, 0);
        } else {

            refresh(container);
            regenerate(container);
        }
        barrierAmbient(container, player);
    }

    private static void barrierAmbient(SkillContainer container, ServerPlayer player) {
        int delay = container.getDataManager().getDataValue(DawnDaySkillDataKeys.UNSTOPPABLE_AMBIENT_DELAY.get());
        if (container.isDisabled() || !player.isAlive() || !player.hasEffect(DawnDayEffects.UNSTOPPABLE_BARRIER.get())) {
            delay = 0;
        } else if (delay <= 0) {
            delay = SkillSoundEffects.barrierAmbientDelay();
        } else if (delay == 1) {
            SkillSoundEffects.unstoppableBarrierAmbient(player);
            delay = SkillSoundEffects.barrierAmbientDelay();
        } else {
            delay--;
        }
        container.getDataManager().setData(DawnDaySkillDataKeys.UNSTOPPABLE_AMBIENT_DELAY.get(), delay);
    }

    private void regenerate(SkillContainer container) {
        int current = stacks(container);
        int cap = container.getDataManager().getDataValue(DawnDaySkillDataKeys.UNSTOPPABLE_CAP.get());
        var data = container.getDataManager();
        var regeneration = DawnDaySkillDataKeys.UNSTOPPABLE_REGENERATION.get();
        if (current >= cap) {
            data.setData(regeneration, 0D);
            return;
        }
        double healing = container.getExecutor().getOriginal().getAttributeValue(DawnDayAttributes.HEALING.get());
        double progress = data.getDataValue(regeneration) + UnstoppableRules.regenerationRate(healing);
        if (progress >= regenerationIntervalTicks) {
            data.setData(regeneration, 0D);
            setStacks(container, current + 1);
        } else {
            data.setData(regeneration, progress);
        }
    }

    public static int stacks(SkillContainer container) {
        return container.getDataManager().getDataValue(DawnDaySkillDataKeys.UNSTOPPABLE_STACKS.get());
    }

    public static int cooldown(SkillContainer container) {
        return container.getDataManager().getDataValue(DawnDaySkillDataKeys.UNSTOPPABLE_COOLDOWN.get());
    }

    static int cappedStacks(SkillContainer container) {
        return UnstoppableRules.clamp(stacks(container),
                UnstoppableRules.cap(container.getExecutor().getOriginal().getAttributeValue(Attributes.ARMOR)));
    }

    public static void setStacks(SkillContainer container, int stacks) {
        Player player = container.getExecutor().getOriginal();
        int cap = UnstoppableRules.cap(player.getAttributeValue(Attributes.ARMOR));
        sync(container, DawnDaySkillDataKeys.UNSTOPPABLE_STACKS.get(), UnstoppableRules.clamp(stacks, cap));
        refresh(container);
    }

    void startBarrierCooldown(SkillContainer container) {
        Player player = container.getExecutor().getOriginal();
        player.getPersistentData().putLong(BARRIER_UNTIL, player.level().getGameTime() + barrierCooldownTicks);
        sync(container, DawnDaySkillDataKeys.UNSTOPPABLE_COOLDOWN.get(), barrierCooldownTicks);
        refresh(container);
    }

    public static void refresh(SkillContainer container) {
        Player player = container.getExecutor().getOriginal();
        int cap = UnstoppableRules.cap(player.getAttributeValue(Attributes.ARMOR));
        int stacks = UnstoppableRules.clamp(stacks(container), cap);
        sync(container, DawnDaySkillDataKeys.UNSTOPPABLE_CAP.get(), cap);
        sync(container, DawnDaySkillDataKeys.UNSTOPPABLE_STACKS.get(), stacks);
        if (!container.isDisabled() && player.isAlive() && UnstoppableRules.stunImmune(stacks)) {
            var immunity = player.getEffect(EpicFightMobEffects.STUN_IMMUNITY.get());
            if (immunity == null || (!immunity.isInfiniteDuration() && immunity.getDuration() <= 1)) {
                player.addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 2, 0, false, false, false));
            }
        }
        var healing = player.getAttribute(DawnDayAttributes.HEALING.get());
        if (healing != null) {
            var current = healing.getModifier(HEALING_MODIFIER);
            double penalty = UnstoppableRules.healingPenalty(stacks);
            if (current != null && (stacks == 0 || current.getAmount() != penalty)) {
                healing.removeModifier(HEALING_MODIFIER);
                current = null;
            }
            if (stacks > 0 && current == null) {
                healing.addTransientModifier(new AttributeModifier(HEALING_MODIFIER, "Unstoppable healing",
                        penalty, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }
        boolean ready = !container.isDisabled() && player.isAlive()
                && UnstoppableRules.barrierReady(stacks, cooldown(container));
        if (ready && !player.hasEffect(DawnDayEffects.UNSTOPPABLE_BARRIER.get())) {
            player.addEffect(new MobEffectInstance(DawnDayEffects.UNSTOPPABLE_BARRIER.get(), -1, 0, false, false, true));
            if (player instanceof ServerPlayer serverPlayer)
                UnstoppableVisualPacket.send(serverPlayer, UnstoppableVisualPacket.BARRIER_START, stacks);

            player.level().playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.RESPAWN_ANCHOR_SET_SPAWN,
                    player.getSoundSource(),
                    2.5F,
                    1.00F
            );
        } else if (!ready && player.hasEffect(DawnDayEffects.UNSTOPPABLE_BARRIER.get())) {
            player.removeEffect(DawnDayEffects.UNSTOPPABLE_BARRIER.get());
            if (player instanceof ServerPlayer serverPlayer) {
                UnstoppableVisualPacket.send(serverPlayer, UnstoppableVisualPacket.BARRIER_STOP, stacks);
                SkillSoundEffects.unstoppableBarrierStop(serverPlayer);
            }
        }
    }

    public static void clearEffects(Player player) {
        var healing = player.getAttribute(DawnDayAttributes.HEALING.get());
        if (healing != null) {
            healing.removeModifier(HEALING_MODIFIER);
        }
        if (player.hasEffect(DawnDayEffects.UNSTOPPABLE_BARRIER.get())) {
            player.removeEffect(DawnDayEffects.UNSTOPPABLE_BARRIER.get());
            if (player instanceof ServerPlayer serverPlayer) {
                UnstoppableVisualPacket.send(serverPlayer, UnstoppableVisualPacket.BARRIER_STOP, 0);
                SkillSoundEffects.unstoppableBarrierStop(serverPlayer);
            }
        }
    }

    static int remainingCooldown(ServerPlayer player) {
        long remaining = player.getPersistentData().getLong(BARRIER_UNTIL) - player.level().getGameTime();
        return (int) Math.max(0, Math.min(Integer.MAX_VALUE, remaining));
    }

    private static void sync(SkillContainer container, SkillDataKey<Integer> key, int value) {
        int previous = container.getDataManager().getDataValue(key);
        if (previous != value) {
            container.getDataManager().setDataSync(key, value);
            if (key == DawnDaySkillDataKeys.UNSTOPPABLE_STACKS.get() && !container.isDisabled()
                    && container.getExecutor().getOriginal() instanceof ServerPlayer player && player.isAlive()) {
                UnstoppableVisualPacket.send(player, value > previous ? UnstoppableVisualPacket.GAIN : UnstoppableVisualPacket.LOSS,
                        Math.max(previous, value));
                SkillSoundEffects.unstoppableStack(player, value > previous);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return !container.isDisabled();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics graphics,
                          float x, float y, float partialTick) {
        var pose = graphics.pose();
        pose.pushPose();
        pose.translate(0, gui.getSlidingProgression(), 0);
        graphics.blit(getSkillTexture(), (int) x, (int) y, 24, 24, 0, 0, 1, 1, 1, 1);
        int stacks = stacks(container);
        int cap = container.getDataManager().getDataValue(DawnDaySkillDataKeys.UNSTOPPABLE_CAP.get());
        String text = stacks + "/" + cap;
        int color = UnstoppableRules.barrierReady(stacks, cooldown(container)) ? 0xFFD86A : 0xFFFFFF;
        graphics.drawString(gui.getFont(), text, (int) x + (24 - gui.getFont().width(text)) / 2, (int) y + 15, color, true);
        if (cooldown(container) > 0) {
            String seconds = Long.toString((cooldown(container) + 19L) / 20);
            graphics.drawString(gui.getFont(), seconds, (int) x + (24 - gui.getFont().width(seconds)) / 2,
                    (int) y + 3, 0xD0D0D0, true);
        }
        pose.popPose();
    }
}
