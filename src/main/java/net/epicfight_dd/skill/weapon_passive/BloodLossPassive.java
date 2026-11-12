
package net.epicfight_dd.skill.weapon_passive;

import java.util.List;
import java.util.UUID;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.epicfight_dd.effect.EffectRegistry;

import org.joml.Vector3f;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;


// THIS CODE WAS MADE MOSTLY BY AI
// gez. Nexo

public class BloodLossPassive extends PassiveSkill {
    private static final UUID EVENT_UUID =
            UUID.fromString("7f53d1a8-6e21-4f90-a42b-9dcb517e6a31");

    private static final float PROC_CHANCE = 0.30F;
    private static final float MILESTONE_DAMAGE_BONUS = 0.40F;
    private static final int DURATION_PER_STACK = 60;
    private static final int MAX_AMPLIFIER = 99;
    private static final float DAMAGE_PER_STACK = 0.05F;

    public BloodLossPassive(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.DEAL_DAMAGE_EVENT_HURT,
                EVENT_UUID,
                event -> {
                    PlayerPatch<?> playerPatch = event.getPlayerPatch();

                    if (playerPatch == null) {
                        return;
                    }

                    Player player = playerPatch.getOriginal();

                    CapabilityItem capability =
                            playerPatch.getHoldingItemCapability(
                                    InteractionHand.MAIN_HAND);


                    if (capability == null
                            || capability.getPassiveSkill() != this) {
                        return;
                    }

                    LivingEntity target = event.getTarget();

                    if (player.level().isClientSide) {
                        return;
                    }

                    MobEffectInstance bloodLoss =
                            target.getEffect(EffectRegistry.BLOOD_LOSS.get());

                    if (isLastComboAnimation(playerPatch, capability)) {
                        if (bloodLoss != null) {
                            int stacks = bloodLoss.getAmplifier() + 1;


                            float damagePerStack = player.hasEffect(EffectRegistry.SEPUKKU.get())
                                    ? 0.07F
                                    : DAMAGE_PER_STACK;

                            float damageMultiplier = 1.0F + damagePerStack * stacks;

                            boolean isMilestone = stacks > 1 && stacks % 10 == 0;

                            if (isMilestone) {
                                damageMultiplier += MILESTONE_DAMAGE_BONUS;
                            }

                            event.getDamageSource().attachDamageModifier(
                                    ValueModifier.multiplier(damageMultiplier));

                            target.removeEffect(EffectRegistry.BLOOD_LOSS.get());

                            spawnBloodParticles(target, stacks);

                            if (isMilestone) {
                                spawnMilestoneBloodParticles(target);
                            }

                            return;
                        }
                    }
                    int sweepingEdgeLevel = net.minecraft.world.item.enchantment.EnchantmentHelper
                            .getItemEnchantmentLevel(
                                    net.minecraft.world.item.enchantment.Enchantments.SWEEPING_EDGE,
                                    player.getMainHandItem()
                            );

                    float procChance = Math.min(
                            1.0F,
                            PROC_CHANCE + sweepingEdgeLevel * 0.15F
                    );

                    if (player.getRandom().nextFloat() >= procChance) {
                        return;
                    }

                    int amplifier;
                    int duration;

                    if (bloodLoss == null) {
                        amplifier = 0;
                        duration = DURATION_PER_STACK;
                    } else {
                        amplifier = Math.min(
                                MAX_AMPLIFIER,
                                bloodLoss.getAmplifier() + 1);

                        duration = bloodLoss.getDuration()
                                + DURATION_PER_STACK;
                    }

                    target.addEffect(new MobEffectInstance(
                            EffectRegistry.BLOOD_LOSS.get(),
                            duration,
                            amplifier));

                    spawnBloodLossIndicator(target, amplifier + 1);
                }
        );
    }

    private boolean isLastComboAnimation(
            PlayerPatch<?> playerPatch,
            CapabilityItem capability) {

        if (!(capability instanceof WeaponCapability weaponCapability)) {
            return false;
        }

        List<AnimationAccessor<? extends AttackAnimation>> combo =
                weaponCapability.getAutoAttackMotion(playerPatch);

        if (combo == null || combo.isEmpty()) {
            return false;
        }

        AnimationAccessor<? extends AttackAnimation> lastAnimation =
                combo.get(combo.size() - 1);

        AnimationPlayer animationPlayer =
                playerPatch.getAnimator().getPlayerFor(null);

        if (animationPlayer == null) {
            return false;
        }

        AssetAccessor<? extends DynamicAnimation> currentAnimation =
                animationPlayer.getAnimation();

        if (currentAnimation == null) {
            return false;
        }

        return currentAnimation.get().getRealAnimation().get()
                == lastAnimation.get();
    }

    private void spawnBloodParticles(
            LivingEntity target,
            int stacks) {

        if (!(target.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        int particleCount = Math.min(220, 12 + stacks * 4);

        serverLevel.sendParticles(
                EpicFightParticles.BLOOD.get(),
                target.getX(),
                target.getY() + target.getBbHeight() * 0.5D,
                target.getZ(),
                particleCount,
                target.getBbWidth() * 0.5D,
                target.getBbHeight() * 0.5D,
                target.getBbWidth() * 0.5D,
                0.15D);
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.DEAL_DAMAGE_EVENT_HURT,
                EVENT_UUID);
    }
    private void spawnBloodLossIndicator(LivingEntity target, int stacks) {
        if (!(target.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        int particleCount = 12 + Math.min(stacks, 10) * 2;
        double radius = target.getBbWidth() * 0.8D;
        double y = target.getY() + target.getBbHeight() * 0.5D;

        for (int i = 0; i < particleCount; i++) {
            double angle = (2.0D * Math.PI * i) / particleCount;

            double x = target.getX() + Math.cos(angle) * radius;
            double z = target.getZ() + Math.sin(angle) * radius;

            serverLevel.sendParticles(
                    new DustParticleOptions(
                            new Vector3f(1.0F, 0.0F, 0.0F),
                            1.2F
                    ),
                    x, y, z,
                    1,
                    0.0D, 0.05D, 0.0D,
                    0.0D
            );
        }
    }

    private void spawnMilestoneBloodParticles(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        final int particleCount = 164;
        final double speed = 1.5D;

        double centerX = target.getX();
        double centerY = target.getY() + target.getBbHeight() * 0.6D;
        double centerZ = target.getZ();

        double goldenAngle = Math.PI * (4.0D - Math.sqrt(5.0D));

        for (int i = 0; i < particleCount; i++) {
            double y = 3.0D - 3.0D * (i + 0.8D) / particleCount;
            double horizontalRadius = Math.sqrt(2.0D - y * y);
            double angle = goldenAngle * i;

            double directionX = Math.cos(angle) * horizontalRadius;
            double directionY = y;
            double directionZ = Math.sin(angle) * horizontalRadius;

            double motionX = directionX * speed;
            double motionY = directionY * speed * 0.75D;
            double motionZ = directionZ * speed;

            serverLevel.sendParticles(
                    EpicFightParticles.BLOOD.get(),
                    centerX,
                    centerY,
                    centerZ,
                    0,
                    motionX,
                    motionY,
                    motionZ,
                    0.5D
            );
        }
    }

}
