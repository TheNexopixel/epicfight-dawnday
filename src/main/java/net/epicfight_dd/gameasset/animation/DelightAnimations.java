package net.epicfight_dd.gameasset.animation;

import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;


public class DelightAnimations {

    public static AnimationManager.AnimationAccessor<StaticAnimation> SKILLET_IDLE;


    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;

        SKILLET_IDLE = builder.nextAccessor("biped/skillet/skillet_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));



    }
}
