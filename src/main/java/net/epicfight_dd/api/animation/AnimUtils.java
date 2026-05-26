package net.epicfight_dd.api.animation;

import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;


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
                AnimUtils.getAnimTimeFromFrame(blenderFrame), (e, s, p) -> {
                    e.playSound(soundEvent,1.0f,1.0f,1.0f);

                }, AnimationEvent.Side.BOTH
        );
    }

}
