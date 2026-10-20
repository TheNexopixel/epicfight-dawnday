package net.epicfight_dd.skill.passive.unstoppable;

import java.util.UUID;
import net.epicfight_dd.attribute.DawnDayAttributes;
import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.server.level.ServerPlayer;
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

    public UnstoppableSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            sync(container, SkillDataKeyZ.UNSTOPPABLE_STACKS.get(), 0);
            sync(container, SkillDataKeyZ.UNSTOPPABLE_COOLDOWN.get(), remainingCooldown(player));
            refresh(container);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        if (!container.getExecutor().getOriginal().level().isClientSide) {
            clearEffects(container.getExecutor().getOriginal());
        }
        super.onRemoved(container);
    }

    @Override
    public void onReset(SkillContainer container) {
        if (container.getExecutor().getOriginal() instanceof ServerPlayer) {
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
                container.getDataManager().setData(SkillDataKeyZ.UNSTOPPABLE_COOLDOWN.get(), cooldown - 1);
            }
            return;
        }
        ServerPlayer player = container.getServerExecutor().getOriginal();
        int remaining = remainingCooldown(player);
        container.getDataManager().setData(SkillDataKeyZ.UNSTOPPABLE_COOLDOWN.get(), remaining);
        if (remaining != cooldown && remaining % 20 == 0) {
            container.getDataManager().setDataSync(SkillDataKeyZ.UNSTOPPABLE_COOLDOWN.get(), remaining);
        }
        if (container.isDisabled() || !player.isAlive()) {
            setStacks(container, 0);
        } else {
            refresh(container);
        }
    }

    public static int stacks(SkillContainer container) {
        return container.getDataManager().getDataValue(SkillDataKeyZ.UNSTOPPABLE_STACKS.get());
    }

    public static int cooldown(SkillContainer container) {
        return container.getDataManager().getDataValue(SkillDataKeyZ.UNSTOPPABLE_COOLDOWN.get());
    }

    public static void setStacks(SkillContainer container, int stacks) {
        Player player = container.getExecutor().getOriginal();
        int cap = UnstoppableRules.cap(player.getAttributeValue(Attributes.ARMOR));
        sync(container, SkillDataKeyZ.UNSTOPPABLE_STACKS.get(), UnstoppableRules.clamp(stacks, cap));
        refresh(container);
    }

    public static void startBarrierCooldown(SkillContainer container) {
        Player player = container.getExecutor().getOriginal();
        player.getPersistentData().putLong(BARRIER_UNTIL, player.level().getGameTime() + UnstoppableRules.BARRIER_COOLDOWN);
        sync(container, SkillDataKeyZ.UNSTOPPABLE_COOLDOWN.get(), UnstoppableRules.BARRIER_COOLDOWN);
    }

    public static void refresh(SkillContainer container) {
        Player player = container.getExecutor().getOriginal();
        int cap = UnstoppableRules.cap(player.getAttributeValue(Attributes.ARMOR));
        int stacks = UnstoppableRules.clamp(stacks(container), cap);
        sync(container, SkillDataKeyZ.UNSTOPPABLE_CAP.get(), cap);
        sync(container, SkillDataKeyZ.UNSTOPPABLE_STACKS.get(), stacks);
        if (!container.isDisabled() && player.isAlive() && UnstoppableRules.stunImmune(stacks)) {
            var immunity = player.getEffect(EpicFightMobEffects.STUN_IMMUNITY.get());
            if (immunity == null || (!immunity.isInfiniteDuration() && immunity.getDuration() <= 1)) {
                player.addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 2, 0, false, false, true));
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
        if (ready && !player.hasEffect(EffectRegistry.UNSTOPPABLE_BARRIER.get())) {
            player.addEffect(new MobEffectInstance(EffectRegistry.UNSTOPPABLE_BARRIER.get(), -1, 0, false, false, true));
        } else if (!ready && player.hasEffect(EffectRegistry.UNSTOPPABLE_BARRIER.get())) {
            player.removeEffect(EffectRegistry.UNSTOPPABLE_BARRIER.get());
        }
    }

    public static void clearEffects(Player player) {
        var healing = player.getAttribute(DawnDayAttributes.HEALING.get());
        if (healing != null) {
            healing.removeModifier(HEALING_MODIFIER);
        }
        player.removeEffect(EffectRegistry.UNSTOPPABLE_BARRIER.get());
    }

    private static int remainingCooldown(ServerPlayer player) {
        long remaining = player.getPersistentData().getLong(BARRIER_UNTIL) - player.level().getGameTime();
        return (int) Math.max(0, Math.min(UnstoppableRules.BARRIER_COOLDOWN, remaining));
    }

    private static void sync(SkillContainer container, SkillDataKey<Integer> key, int value) {
        if (container.getDataManager().getDataValue(key) != value) {
            container.getDataManager().setDataSync(key, value);
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
        int cap = container.getDataManager().getDataValue(SkillDataKeyZ.UNSTOPPABLE_CAP.get());
        String text = stacks + "/" + cap;
        int color = UnstoppableRules.barrierReady(stacks, cooldown(container)) ? 0xFFD86A : 0xFFFFFF;
        graphics.drawString(gui.getFont(), text, (int) x + (24 - gui.getFont().width(text)) / 2, (int) y + 15, color, true);
        if (cooldown(container) > 0) {
            String seconds = Integer.toString((cooldown(container) + 19) / 20);
            graphics.drawString(gui.getFont(), seconds, (int) x + (24 - gui.getFont().width(seconds)) / 2,
                    (int) y + 3, 0xD0D0D0, true);
        }
        pose.popPose();
    }
}
