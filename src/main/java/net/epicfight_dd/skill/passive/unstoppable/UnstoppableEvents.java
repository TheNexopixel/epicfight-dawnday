package net.epicfight_dd.skill.passive.unstoppable;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
    public static void barrier(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !player.isAlive()
                || player.isInvulnerableTo(event.getSource()) || player.getAbilities().invulnerable
                || event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)
                || (event.getSource().is(DamageTypeTags.IS_FIRE) && player.hasEffect(MobEffects.FIRE_RESISTANCE))) {
            return;
        }
        float damage = event.getSource().scalesWithDifficulty()
                ? UnstoppableRules.difficultyDamage(event.getAmount(), player.level().getDifficulty().getId())
                : event.getAmount();
        if (!UnstoppableRules.attackCanHurt(damage, player.invulnerableTime, player.lastHurt,
                event.getSource().is(DamageTypeTags.BYPASSES_COOLDOWN))) {
            return;
        }
        var container = container(player);
        if (container != null) {
            UnstoppableSkill.refresh(container);
            if (UnstoppableRules.barrierReady(UnstoppableSkill.stacks(container), UnstoppableSkill.cooldown(container))) {
                event.setCanceled(true);
                UnstoppableSkill.startBarrierCooldown(container);
                UnstoppableSkill.refresh(container);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void hurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !(event.getAmount() > 0)) {
            return;
        }
        var container = container(player);
        if (container != null) {
            applyHit(event, container);
        }
    }

    private static void applyHit(LivingHurtEvent event, SkillContainer container) {
        UnstoppableSkill.refresh(container);
        var hit = UnstoppableRules.hit(UnstoppableSkill.stacks(container), UnstoppableSkill.cooldown(container),
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
        UnstoppableSkill.setStacks(container, hit.stacks());
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
