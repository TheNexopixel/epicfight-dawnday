package net.epicfight_dd.api.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.main.EpicFightSharedConstants;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.damagesource.StunType;

import java.util.ArrayList;
import java.util.List;


public class AnimUtils {

    public static float getAnimTimeFromTickTime(int ticks) {
        return (float) ticks / 20;
    }

    public static float getAnimTimeFromFrame(int frame) {
        return (float) frame / 60;
    }

    public static void sendDevDebugmsg(String msg){
        if(EpicFightSharedConstants.IS_DEV_ENV){
            assert Minecraft.getInstance().player != null;
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(msg));
        }
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

    public static AnimationEvent.InTimeEvent<AnimationEvent.Event<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>>
    LaunchEnemyAirSlash(float stunTime, int levitationLevel, int levitationTicks, float startTime) {

        return AnimationEvent.InTimeEvent.create(
                startTime,
                (patch, accessor, params) -> {
                    if (!patch.isLastAttackSuccess()) return;

                    List<LivingEntity> hit = patch.getCurrentlyActuallyHitEntities();
                    if (hit == null || hit.isEmpty()) return;

                    LivingEntity self = patch.getOriginal();

                    for (LivingEntity target : new ArrayList<>(hit)) {
                        if (target == null || target == self || !target.isAlive()) continue;

                        double kbRes = target.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
                        if (kbRes >= 1.0D) continue;
                        float scale = (float) (1.0D - kbRes);

                        LivingEntityPatch<?> targetPatch =
                                EpicFightCapabilities.getEntityPatch(target, LivingEntityPatch.class);
                        if (targetPatch != null) {
                            targetPatch.applyStun(StunType.HOLD, stunTime * scale);
                        }

                        int ticks = Math.max(1, Math.round(levitationTicks * scale));
                        target.removeEffect(MobEffects.LEVITATION);
                        target.addEffect(
                                new MobEffectInstance(MobEffects.LEVITATION, ticks, levitationLevel, true, false, false),
                                self);
                    }
                },
                AnimationEvent.Side.SERVER
        );
    }

}
