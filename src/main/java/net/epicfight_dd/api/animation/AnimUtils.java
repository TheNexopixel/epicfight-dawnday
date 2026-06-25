package net.epicfight_dd.api.animation;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.damagesource.StunType;

import java.util.List;


public class AnimUtils {

    /// useful for in game testing acquired data
    public static float getAnimTimeFromTickTime(int ticks) {
        return (float) ticks / 20;
    }

    /// assumes animation to be at 60 frames per second
    public static float getAnimTimeFromFrame(int frame) {
        return (float) frame / 60;
    }

    public static final AnimationProperty.PlaybackSpeedModifier ONE50PERCENT = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 1.5F;
    public static final AnimationProperty.PlaybackSpeedModifier ONE25PERCENT = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 1.25F;
    public static final AnimationProperty.PlaybackSpeedModifier DOUBLE = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 2F;
    public static final AnimationProperty.PlaybackSpeedModifier EIGHT5 = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 0.85F;
    public static final AnimationProperty.PlaybackSpeedModifier HALF = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 0.5F;

    public static AnimationEvent.@NotNull InTimeEvent<AnimationEvent.Event<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>> playSoundOnFrame(int blenderFrame, SoundEvent soundEvent) {
        return AnimationEvent.InTimeEvent.create(
                AnimUtils.getAnimTimeFromFrame(blenderFrame), (e, s, p) ->
                        e.playSound(soundEvent, 0.4f, 1.1f, 1.25f), AnimationEvent.Side.CLIENT
        );
    }

    public static AnimationEvent.SimpleEvent<AnimationEvent.Event<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>> LaunchEnemyAirSlash(float height) {

        return AnimationEvent.SimpleEvent.create(
                (livingEntityPatch, assetAccessor, animationParameters) -> {

                    if (!livingEntityPatch.isLastAttackSuccess()) {
                        return;
                    }

                    List<LivingEntity> targets =
                            livingEntityPatch.getCurrentlyActuallyHitEntities();

                    if (targets == null || targets.isEmpty()) {
                        return;
                    }

                    targets.forEach(entity -> {

                        if (entity == null || !entity.isAlive()) {
                            return;
                        }

                        LivingEntityPatch<?> targetPatch =
                                EpicFightCapabilities.getEntityPatch(
                                        entity,
                                        LivingEntityPatch.class
                                );

                        if (targetPatch != null
                             //   && !targetPatch.isStunned()
                        ) {

                            if (!entity.level().isClientSide) {
                                float stunTime = height * (
                                        1.0F - (float) entity.getAttributeValue(
                                                Attributes.KNOCKBACK_RESISTANCE
                                        )
                                );

                                targetPatch.applyStun(
                                        StunType.HOLD,
                                        stunTime
                                );


                                entity.addEffect(
                                        new MobEffectInstance(
                                                MobEffects.SLOW_FALLING,
                                                14,
                                                5,
                                                true,
                                                false,
                                                false
                                        )
                                );
                            }
                          //  targetPatch.setAirborneState(true);
                            entity.hasImpulse = true;
                            entity.hurtMarked = true;
                            entity.setDeltaMovement(entity.getDeltaMovement().x,entity.getDeltaMovement().y() + height, entity.getDeltaMovement().z);
                        }
                    });

                },
                AnimationEvent.Side.BOTH
        );
    }

}
