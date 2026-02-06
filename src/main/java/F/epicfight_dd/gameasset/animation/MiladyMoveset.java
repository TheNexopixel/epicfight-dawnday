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
    public static AnimationAccessor<StaticAnimation> MILADY_DUAL_IS_IDLE;
    public static AnimationAccessor<StaticAnimation> MILADY_TWOHANDED_IS_IDLE;
    public static AnimationAccessor<StaticAnimation> MILADY_TWOHANDED_WALK;
    public static AnimationAccessor<StaticAnimation> POLE_AXE_IDLE;
    public static AnimationAccessor<StaticAnimation> WAR_SICKLE_IDLE;
    public static AnimationAccessor<StaticAnimation> HEAVY_AXE_IDLE;

    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_1;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_2;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_3;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_4;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_5;
    public static AnimationAccessor<DashAttackAnimation> milady_onehanded_dash;
    public static AnimationAccessor<AirSlashAnimation> MILADY_AIR_SLASH;

    public static AnimationAccessor<BasicAttackAnimation> MILADY_TWOHANDED_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_TWOHANDED_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_TWOHANDED_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_TWOHANDED_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> MILADY_TWOHANDED_DASH;
    public static AnimationAccessor<AirSlashAnimation> MILADY_TWOHANDED_AIRSLASH;

    public static AnimationAccessor<BasicAttackAnimation> MILADY_DUAL_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_DUAL_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_DUAL_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_DUAL_AUTO4;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_DUAL_AUTO5;
    public static AnimationAccessor<DashAttackAnimation> MILADY_DUAL_DASH;
    public static AnimationAccessor<AirSlashAnimation> MILADY_DUAL_AIRSLASH;

    public static AnimationAccessor<BasicAttackAnimation> POLE_AXE_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> POLE_AXE_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> POLE_AXE_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> POLE_AXE_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> POLE_AXE_DASH;
    public static AnimationAccessor<AirSlashAnimation> POLE_AXE_AIRSLASH;

    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO4;
    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO5;
    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO6;

    public static AnimationAccessor<BasicAttackAnimation> HEAVY_AXE_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> HEAVY_AXE_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> HEAVY_AXE_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> HEAVY_AXE_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> HEAVY_AXE_DASH;

    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;

        MILADY_WALK = builder.nextAccessor("biped/living/milady_onehanded_walk", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_TWOHANDED_WALK = builder.nextAccessor("biped/living/milady_twohanded_walk", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_IS_IDLE = builder.nextAccessor("biped/living/milady_onehanded_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_DUAL_IS_IDLE = builder.nextAccessor("biped/living/milady_dual_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_TWOHANDED_IS_IDLE = builder.nextAccessor("biped/living/milady_twohanded_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        POLE_AXE_IDLE = builder.nextAccessor("biped/living/pole_axe_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        WAR_SICKLE_IDLE = builder.nextAccessor("biped/living/war_sickle_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        HEAVY_AXE_IDLE = builder.nextAccessor("biped/living/heavy_axe_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));



            MILADY_ONE_HANDED_AUTO_1 = builder.nextAccessor("biped/combat/milady_onehanded_auto1", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            MILADY_ONE_HANDED_AUTO_2 = builder.nextAccessor("biped/combat/milady_onehanded_auto2", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.3F, 0.31F, 0.7F, 0.88F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            MILADY_ONE_HANDED_AUTO_3 = builder.nextAccessor("biped/combat/milady_onehanded_auto3", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.1F, 0.2F, 0.48F, 0.74F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            MILADY_ONE_HANDED_AUTO_4 = builder.nextAccessor("biped/combat/milady_onehanded_auto4", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.1F, 0.2F, 0.41F, 0.7F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            //the method changes if there has to be more than one phases
            MILADY_ONE_HANDED_AUTO_5 = builder.nextAccessor("biped/combat/milady_onehanded_auto5", (accessor) ->
                    new BasicAttackAnimation(0.12F, accessor, biped,
                            new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.4f, 0.5f, 0.48f, InteractionHand.MAIN_HAND, biped.get().toolR,null),

                            new AttackAnimation.Phase(0.481f, 0.5f, 0.6f, 1.0f, 1.3f,1.5f, InteractionHand.MAIN_HAND, biped.get().toolR,null))

                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            milady_onehanded_dash = builder.nextAccessor("biped/combat/milady_onehanded_dash", (accessor) ->
                    new DashAttackAnimation(0.12F, 0.0f, 0.1f, 0.3f, 0.8F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            MILADY_AIR_SLASH = builder.nextAccessor("biped/combat/milady_onehanded_airslash" , ac ->
                    new AirSlashAnimation(  0.12F, 0.1f ,0.3f, 0.8f,null, biped.get().toolR, ac , biped));

        MILADY_TWOHANDED_AUTO1 = builder.nextAccessor("biped/combat/milady_twohanded_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO2 = builder.nextAccessor("biped/combat/milady_twohanded_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO3 = builder.nextAccessor("biped/combat/milady_twohanded_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO4 = builder.nextAccessor("biped/combat/milady_twohanded_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_DASH = builder.nextAccessor("biped/combat/milady_twohanded_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.01F, 0.02F, 0.3F, 0.62F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AIRSLASH = builder.nextAccessor("biped/combat/milady_twohanded_airslash", (ac) ->
                new AirSlashAnimation(  0.12F, 0.1f ,0.3f, 0.8f,null, biped.get().toolR, ac , biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO1 = builder.nextAccessor("biped/combat/milady_dual_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.4f, 0.49f, 0.5f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.51f, 0.6f, 0.7f, 1.2f, 1.1f,1.71f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
                        .addStateRemoveOld(EntityState.CAN_BASIC_ATTACK, false));

        MILADY_DUAL_AUTO2 = builder.nextAccessor("biped/combat/milady_dual_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.4f, 0.6f, 0.7f, 0.8f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.481f, 0.2f, 0.4f, 0.6f, 0.9f,0.91f, InteractionHand.MAIN_HAND, biped.get().toolR,null))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO3 = builder.nextAccessor("biped/combat/milady_dual_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.4f, 0.4f, 0.41f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.42f, 0.5f, 0.55f, 0.8f, 1.1f,1.2f, InteractionHand.MAIN_HAND, biped.get().toolR,null))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO4 = builder.nextAccessor("biped/combat/milady_dual_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.55f, 0.8f, 0.8f, 0.8f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.81f, 0.82f, 0.83f, 1.15f, 1.4f,1.6f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO5 = builder.nextAccessor("biped/combat/milady_dual_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.2F, 0.3F, 0.4F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_DASH = builder.nextAccessor("biped/combat/milady_dual_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.3F, 0.20F, 0.32F, 0.52F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AIRSLASH = builder.nextAccessor("biped/combat/milady_dual_airslash" , ac ->
                new AirSlashAnimation(  0.12F, 0.2f ,0.4f, 0.6f,null, biped.get().toolR, ac , biped));

        POLE_AXE_AUTO1 = builder.nextAccessor("biped/combat/pole_axe_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        POLE_AXE_AUTO2 = builder.nextAccessor("biped/combat/pole_axe_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        POLE_AXE_AUTO3 = builder.nextAccessor("biped/combat/pole_axe_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        POLE_AXE_AUTO4 = builder.nextAccessor("biped/combat/pole_axe_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.4f, 0.5f, 0.48f, InteractionHand.MAIN_HAND, biped.get().toolR,null),

                        new AttackAnimation.Phase(0.481f, 0.5f, 0.6f, 1.0f, 1.3f,1.5f, InteractionHand.MAIN_HAND, biped.get().toolR,null))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        POLE_AXE_AIRSLASH = builder.nextAccessor("biped/combat/pole_axe_airslash" , ac ->
                new AirSlashAnimation(  0.12F, 0.2f ,0.4f, 0.6f,null, biped.get().toolR, ac , biped));

        POLE_AXE_DASH = builder.nextAccessor("biped/combat/pole_axe_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.2F, 0.20F, 0.42F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        WAR_SICKLE_AUTO1 = builder.nextAccessor("biped/combat/war_sickle_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO2 = builder.nextAccessor("biped/combat/war_sickle_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO3 = builder.nextAccessor("biped/combat/war_sickle_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO4 = builder.nextAccessor("biped/combat/war_sickle_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO5 = builder.nextAccessor("biped/combat/war_sickle_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO6 = builder.nextAccessor("biped/combat/war_sickle_auto6", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HEAVY_AXE_AUTO1 = builder.nextAccessor("biped/combat/heavy_axe_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HEAVY_AXE_AUTO2 = builder.nextAccessor("biped/combat/heavy_axe_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HEAVY_AXE_AUTO3 = builder.nextAccessor("biped/combat/heavy_axe_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HEAVY_AXE_AUTO4 = builder.nextAccessor("biped/combat/heavy_axe_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HEAVY_AXE_DASH = builder.nextAccessor("biped/combat/heavy_axe_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.2F, 0.20F, 0.42F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        }
    }

