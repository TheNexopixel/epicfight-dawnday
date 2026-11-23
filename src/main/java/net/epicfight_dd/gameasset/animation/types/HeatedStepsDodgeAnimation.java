package net.epicfight_dd.gameasset.animation.types;

import net.epicfight_dd.skill.dodge.HeatedStepsSkill;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.types.DodgeAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public final class HeatedStepsDodgeAnimation extends DodgeAnimation {
    public HeatedStepsDodgeAnimation(float transition, AnimationAccessor<? extends DodgeAnimation> accessor,
                                     float width, float height) {
        super(transition, accessor, width, height, Armatures.BIPED);
    }

    public HeatedStepsDodgeAnimation(float transition, float delay,
                                     AnimationAccessor<? extends DodgeAnimation> accessor, float width, float height) {
        super(transition, delay, accessor, width, height, Armatures.BIPED);
    }

    @Override
    protected Vec3 getCoordVector(LivingEntityPatch<?> patch, AssetAccessor<? extends DynamicAnimation> animation) {
        Vec3 movement = super.getCoordVector(patch, animation);
        float scale = HeatedStepsSkill.distanceScale(patch);
        return scale == 1.0F ? movement : movement.multiply(scale, 1.0D, scale);
    }
}
