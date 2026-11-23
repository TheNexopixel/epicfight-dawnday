package net.epicfight_dd.gameasset.animation.types;

import net.epicfight_dd.skill.dodge.HeatedStepsSkill;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.property.AnimationProperty.ActionAnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.StaticAnimationProperty;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

public final class HeatedStepsChargeAnimation extends ActionAnimation {
    private static final ThreadLocal<PlayerPatch<?>> KEEP_HOLDING = new ThreadLocal<>();

    public HeatedStepsChargeAnimation(AnimationAccessor<? extends ActionAnimation> accessor) {
        super(0.15F, accessor, Armatures.BIPED);
        setResourceLocation("epicfight", "biped/skill/demolition_leap_charge");
        addProperty(ActionAnimationProperty.STOP_MOVEMENT, true);
        addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CHARGING);
        addProperty(StaticAnimationProperty.POSE_MODIFIER, (animation, pose, patch, time, partialTick) ->
                Animations.BIPED_DEMOLITION_LEAP_CHARGING.get().getProperty(StaticAnimationProperty.POSE_MODIFIER)
                        .ifPresent(modifier -> modifier.modify(animation, pose, patch, time, partialTick)));
        addProperty(StaticAnimationProperty.RESET_LIVING_MOTION, LivingMotions.IDLE);
        newTimePair(0.0F, Float.MAX_VALUE)
                .addStateRemoveOld(EntityState.CAN_BASIC_ATTACK, true)
                .addStateRemoveOld(EntityState.CAN_SKILL_EXECUTION, true);
    }

    @Override
    public void begin(LivingEntityPatch<?> patch) {
        if (!(patch instanceof PlayerPatch<?> player) || !(player.getHoldingSkill() instanceof HeatedStepsSkill)) {
            super.begin(patch);
            return;
        }
        KEEP_HOLDING.set(player);
        try {
            super.begin(patch);
        } finally {
            KEEP_HOLDING.remove();
        }
    }

    public static boolean consumeKeepHolding(PlayerPatch<?> player) {
        if (KEEP_HOLDING.get() != player) return false;
        KEEP_HOLDING.remove();
        return true;
    }
}
