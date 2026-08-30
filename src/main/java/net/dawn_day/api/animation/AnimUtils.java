package net.dawn_day.api.animation;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.damagesource.StunType;

import java.util.List;

public class AnimUtils {



    public static AnimationEvent.InTimeEvent<AnimationEvent.Event<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>>
    LaunchEnemyAirSlash(
            float stunTime,
            int levitationLevel,
            int levitationTicks,
            float startTime) {

        return AnimationEvent.InTimeEvent.create(
                startTime,
                (livingEntityPatch, assetAccessor, animationParameters) -> {

                    if (!livingEntityPatch.isLastAttackSuccess()) {
                        return;
                    }

                    List<LivingEntity> targets =
                            livingEntityPatch.getCurrentlyActuallyHitEntities();

                    if (targets == null || targets.isEmpty()) {
                        return;
                    }

                    for (LivingEntity entity : targets) {

                        if (entity == null || !entity.isAlive()) {
                            continue;
                        }

                        LivingEntityPatch<?> targetPatch =
                                EpicFightCapabilities.getEntityPatch(
                                        entity,
                                        LivingEntityPatch.class
                                );

                        if (targetPatch == null) {
                            continue;
                        }

                        float kbRes =
                                (float) entity.getAttributeValue(
                                        Attributes.KNOCKBACK_RESISTANCE);

                        targetPatch.applyStun(
                                StunType.HOLD,
                                stunTime * (1.0F - kbRes)
                        );

                        if (!entity.level().isClientSide) {

                            entity.removeEffect(MobEffects.LEVITATION);
                            entity.removeEffect(MobEffects.SLOW_FALLING);


                            entity.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.LEVITATION,
                                            levitationTicks,
                                            levitationLevel,
                                            true,
                                            false,
                                            false
                                    )
                            );

                        }
                    }

                },
                AnimationEvent.Side.SERVER
        );
    }
}
