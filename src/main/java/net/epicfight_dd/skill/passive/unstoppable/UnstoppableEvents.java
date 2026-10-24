package net.epicfight_dd.skill.passive.unstoppable;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.network.UnstoppableVisualPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID)
public final class UnstoppableEvents {
    private UnstoppableEvents() {
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void killed(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer killer && event.getEntity() != killer) {
            var container = container(killer);
            if (container != null && killer.isAlive()) {
                UnstoppableSkill.refresh(container);
                int cap = container.getDataManager().getDataValue(SkillDataKeyZ.UNSTOPPABLE_CAP.get());
                UnstoppableSkill.setStacks(container, UnstoppableRules.kill(UnstoppableSkill.stacks(container), cap,
                        event.getEntity() instanceof Player));
            }
        }
        if (event.getEntity() instanceof ServerPlayer victim) {
            var container = container(victim);
            if (container != null) {
                UnstoppableSkill.setStacks(container, 0);
            }
            UnstoppableSkill.clearEffects(victim);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void barrier(LivingHurtEvent event) {
        if (event.isCanceled() || !(event.getEntity() instanceof ServerPlayer player) || !player.isAlive()
                || !(event.getAmount() > 0) || event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return;
        }
        var container = container(player);
        if (container != null) {
            UnstoppableSkill.refresh(container);
            if (UnstoppableRules.barrierReady(UnstoppableSkill.stacks(container), UnstoppableSkill.cooldown(container))) {
                event.setCanceled(true);
                event.setAmount(0);
                UnstoppableSkill.startBarrierCooldown(container);
                UnstoppableSkill.refresh(container);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void hurt(LivingHurtEvent event) {
        if (event.isCanceled() || !(event.getEntity() instanceof ServerPlayer player) || !(event.getAmount() > 0)) {
            return;
        }
        var container = container(player);
        if (container != null) {
            applyHit(event, container);
        }
    }

    private static void applyHit(LivingHurtEvent event, SkillContainer container) {
        UnstoppableSkill.refresh(container);
        int current = UnstoppableSkill.stacks(container);
        var hit = UnstoppableRules.hit(current, UnstoppableSkill.cooldown(container),
                event.getAmount(), event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY));
        if (hit.absorbed()) {
            UnstoppableSkill.startBarrierCooldown(container);
            event.setCanceled(true);
            event.setAmount(0);
            UnstoppableSkill.refresh(container);
            return;
        }
        event.setAmount(hit.damage());
        if (hit.uninterruptedStamina()) {
            var patch = container.getServerExecutor();
            if (patch.getStaminaRegenAwaitTicks() > 0 && patch.getStamina() < patch.getMaxStamina()) {
                patch.setStaminaRegenAwaitTicks(0);
            }
        }
        int remaining = hit.stacks();
        if (remaining < current && container.getServerExecutor().getSkillContainerFor(EpicFightSkills.ADAPTIVE_SKIN)
                .filter(value -> !value.isDisabled()).isPresent()
                && container.getServerExecutor().getOriginal().getRandom().nextInt(3) == 0) {
            remaining = current;
        }
        UnstoppableSkill.setStacks(container, remaining);
    }

    @SubscribeEvent
    public static void logout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            UnstoppableSkill.clearEffects(player);
        }
    }

    @SubscribeEvent
    public static void login(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && container(player) == null) {
            UnstoppableSkill.clearEffects(player);
        }
    }

    @SubscribeEvent
    public static void tracking(PlayerEvent.StartTracking event) {
        if (event.getEntity() instanceof ServerPlayer observer && event.getTarget() instanceof ServerPlayer player
                && player.hasEffect(EffectRegistry.UNSTOPPABLE_BARRIER.get()))
            UnstoppableVisualPacket.barrierFor(observer, player);
    }

    @SubscribeEvent
    public static void dimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && player.hasEffect(EffectRegistry.UNSTOPPABLE_BARRIER.get()))
            UnstoppableVisualPacket.barrierFor(player, player);
    }

    private static SkillContainer container(ServerPlayer player) {
        if (DawnDaySkills.UNSTOPPABLE == null) {
            return null;
        }
        var patch = EpicFightCapabilities.getEntityPatch(player, ServerPlayerPatch.class);
        return patch == null ? null : patch.getSkillContainerFor(DawnDaySkills.UNSTOPPABLE)
                .filter(value -> !value.isDisabled() && value.getDataManager().hasData(SkillDataKeyZ.UNSTOPPABLE_STACKS.get()))
                .orElse(null);
    }
}
