package net.epicfight_dd.skill.passive;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID)
public final class DivinePunishmentSkill extends PassiveSkill {
    private static final float DAMAGE_DEALT_MULTIPLIER = 1.10F; // +10 %
    private static final float DAMAGE_TAKEN_MULTIPLIER = 0.80F; // -20 %
    private static final float BASE_CHANCE = 0.02F;
    private static final float CHANCE_PER_SWEEPING_LEVEL = 0.02F;
    private static final int MAX_SWEEPING_LEVELS = 4;
    private static final float PROC_DAMAGE_MULTIPLIER = 1.70F; // +70 %

    private static final Set<UUID> ACTIVE = ConcurrentHashMap.newKeySet();

    public DivinePunishmentSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            ACTIVE.add(player.getUUID());
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            ACTIVE.remove(player.getUUID());
        }
        super.onRemoved(container);
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity victim = event.getEntity();
        Entity attacker = event.getSource().getEntity();

        if (attacker instanceof ServerPlayer player
                && ACTIVE.contains(player.getUUID())
                && victim.getMobType() == MobType.UNDEAD) {

            float damage = event.getAmount() * DAMAGE_DEALT_MULTIPLIER;
            spawnSparkles(victim);

            if (player.getRandom().nextFloat() < procChance(player)) {
                damage *= PROC_DAMAGE_MULTIPLIER;
                spawnProcEffects(victim);
            }

            event.setAmount(damage);
        }

        if (victim instanceof ServerPlayer player
                && ACTIVE.contains(player.getUUID())
                && attacker instanceof LivingEntity living
                && living.getMobType() == MobType.UNDEAD) {

            event.setAmount(event.getAmount() * DAMAGE_TAKEN_MULTIPLIER);
        }
    }

    private static float procChance(ServerPlayer player) {
        int level = EnchantmentHelper.getItemEnchantmentLevel(
                Enchantments.SWEEPING_EDGE, player.getMainHandItem());
        return BASE_CHANCE + CHANCE_PER_SWEEPING_LEVEL * Math.min(level, MAX_SWEEPING_LEVELS);
    }

    private static void spawnProcEffects(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel level)) return;

        double x = target.getX();
        double y = target.getY() + target.getBbHeight() * 0.5D;
        double z = target.getZ();
        double w = target.getBbWidth() * 0.5D;
        double h = target.getBbHeight() * 0.4D;

        level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z, 50, w, h, w, 1.22D);
        level.playSound(null, x, y, z, SoundEvents.FIREWORK_ROCKET_BLAST, SoundSource.PLAYERS, 2.0F, 1.2F);
    }

    private static void spawnSparkles(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel level)) return;

        double x = target.getX();
        double y = target.getY() + target.getBbHeight() * 0.5D;
        double z = target.getZ();
        double w = target.getBbWidth() * 0.4D;
        double h = target.getBbHeight() * 0.3D;

        level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z, 14, w, h, w, 0.04D);
    }

}