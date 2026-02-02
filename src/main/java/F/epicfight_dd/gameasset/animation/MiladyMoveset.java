package F.epicfight_dd.gameasset.animation;

import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.property.AnimationProperty.*;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;


public class MiladyMoveset {

    public static AnimationAccessor<StaticAnimation> MILADY_WALK;
    public static AnimationAccessor<StaticAnimation> MILADY_IS_IDLE;

    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_1;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_2;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_3;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_4;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_5;
    public static AnimationAccessor<DashAttackAnimation> milady_onehanded_dash;
    public static AnimationAccessor<AirSlashAnimation> MILADY_AIR_SLASH;

    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;

        MILADY_WALK = builder.nextAccessor("biped/living/milady_onehanded_walk", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_IS_IDLE = builder.nextAccessor("biped/living/milady_onehanded_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));



            MILADY_ONE_HANDED_AUTO_1 = builder.nextAccessor("biped/combat/milady_onehanded_auto1", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            MILADY_ONE_HANDED_AUTO_2 = builder.nextAccessor("biped/combat/milady_onehanded_auto2", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.3F, 0.31F, 0.7F, 0.84F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            MILADY_ONE_HANDED_AUTO_3 = builder.nextAccessor("biped/combat/milady_onehanded_auto3", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.1F, 0.2F, 0.48F, 0.54F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            MILADY_ONE_HANDED_AUTO_4 = builder.nextAccessor("biped/combat/milady_onehanded_auto4", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.1F, 0.2F, 0.41F, 0.5F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            //the method changes if there has to be more than one phase
            MILADY_ONE_HANDED_AUTO_5 = builder.nextAccessor("biped/combat/milady_onehanded_auto5", (accessor) ->
                    new BasicAttackAnimation(0.12F, accessor, biped,
                            new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.4f, 0.5f, 0.48f, InteractionHand.MAIN_HAND, biped.get().toolR,null),

                            new AttackAnimation.Phase(0.481f, 0.5f, 0.6f, 1.0f, 1.2f,1.5f, InteractionHand.MAIN_HAND, biped.get().toolR,null))

                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            milady_onehanded_dash = builder.nextAccessor("biped/combat/milady_onehanded_dash", (accessor) ->
                    new DashAttackAnimation(0.12F, 0.1f, 0.3f, 0.4f, 0.54F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            MILADY_AIR_SLASH = builder.nextAccessor("biped/combat/milady_onehanded_airslash" , ac ->
                    new AirSlashAnimation(  0.12F, 0.1f ,0.3f, 0.4f,null, biped.get().toolR, ac , biped));


        }
    }

