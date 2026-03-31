package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.gameasset.dawnDaySounds;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.damagesource.StunType;

public class WingStanceAnims {


    public static AnimationManager.AnimationAccessor<StaticAnimation> WING_STANCE_IDLE;

    public static AnimationManager.AnimationAccessor<MovementAnimation> WINGSTANCE_WALK;
    public static AnimationManager.AnimationAccessor<MovementAnimation> WINGSTANCE_RUN;

    public static AnimationManager.AnimationAccessor<StaticAnimation> WINGSTANCE_TRANSITION;

    public static AnimationManager.AnimationAccessor<BasicAttackAnimation> WINGSTANCE_ATTACK_1;
    public static AnimationManager.AnimationAccessor<BasicAttackAnimation> WINGSTANCE_ATTACK_2;
    public static AnimationManager.AnimationAccessor<BasicAttackAnimation> WINGSTANCE_ATTACK_3;
    public static AnimationManager.AnimationAccessor<BasicAttackAnimation> WINGSTANCE_CROUCH_ATTACK;
    public static AnimationManager.AnimationAccessor<BasicAttackAnimation> WINGSTANCE_ATTACK_5;
    public static AnimationManager.AnimationAccessor<DashAttackAnimation> WINGSTANCE_DASH;
    public static AnimationManager.AnimationAccessor<BasicAttackAnimation> WINGSTANCE_AIRSLASH;
    public static AnimationManager.AnimationAccessor<AttackAnimation> WINGSTANCE_SKILL1;
    public static AnimationManager.AnimationAccessor<AttackAnimation> WINGSTANCE_SKILL2;



    public static void build(AnimationManager.AnimationBuilder builder){

        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;


        WING_STANCE_IDLE = builder.nextAccessor("biped/skill/wingstance/wingstance_idle",
                accessor -> new StaticAnimation(
                        0.12f,true,accessor, Armatures.BIPED
                ));


        WINGSTANCE_WALK = builder.nextAccessor("biped/skill/wingstance/wingstance_walk", ac ->
                new MovementAnimation(0.12F,true,ac, biped));

        WINGSTANCE_RUN = builder.nextAccessor("biped/skill/wingstance/wingstance_run", ac ->
                new MovementAnimation(0.12F,true,ac, biped));

        WINGSTANCE_SKILL1 = builder.nextAccessor("biped/skill/wingstance/wingstance_skill1", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.55f, 0.8f, 2.0f, 0.81f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.18f)),

                        new AttackAnimation.Phase(0.81f, 0.87f, 1.10f, 1.4f, 2.0f,1.41f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(15.55f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.57f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                        new AttackAnimation.Phase(1.61f, 1.6f, 1.70f, 1.95f, 2.2f, 20f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.25f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                )
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));

        WINGSTANCE_SKILL2 = builder.nextAccessor("biped/skill/wingstance/wingstance_skill2", (accessor) ->
                new AttackAnimation(0.12F, 0.6F, 0.65F, 1.12F, 1.8F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, dawnDaySounds.piercing_fang.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(33.33f))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.6f))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())


                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));


        WINGSTANCE_TRANSITION = builder.nextAccessor("biped/skill/wingstance/wingstance_transition", accessor ->
                new StaticAnimation(false,accessor,biped)
                        .addProperty(AnimationProperty.StaticAnimationProperty.RESET_LIVING_MOTION, LivingMotions.ALL)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER,(s,r,p,f,k)-> 0.69f)


        );

        WINGSTANCE_ATTACK_1 = builder.nextAccessor("biped/skill/wingstance/wingstance_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.0f, 0.7f, 0.9f, 1.0F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.1))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        WINGSTANCE_ATTACK_2 = builder.nextAccessor("biped/skill/wingstance/wingstance_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.0f, 0.6f, 0.9f, 1.0F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        WINGSTANCE_ATTACK_3 = builder.nextAccessor("biped/skill/wingstance/wingstance_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.0f, 0.6f, 0.9f, 1.0F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier( 1.30f))
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.adder( 5))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(33.33f))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        WINGSTANCE_CROUCH_ATTACK = builder.nextAccessor("biped/skill/wingstance/wingstance_crouch_attack", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.05f, 0.18f, 0.30f, 1.4f, 0.38f, InteractionHand.MAIN_HAND, biped.get().legR,MiladyCollider.EVIL_TACHI_SPECIAL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.HOLD)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.adder( 5))
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLUNT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.1f)),

                        new AttackAnimation.Phase(0.481f, 0.9f, 1.18f, 1.45f, 1.4f,1.5f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(40.0f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_light_slash.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.83)))

                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addState(EntityState.TURNING_LOCKED,true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        WINGSTANCE_ATTACK_5 = builder.nextAccessor("biped/skill/wingstance/wingstance_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.3f, 0.4f, 0.55f, 1.4f, 0.68f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.8f)),

                        new AttackAnimation.Phase(0.681f, 0.9f, 1.00f, 1.28f, 1.4f,1.5f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_light_slash.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.EVISCERATE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.13)))

                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        WINGSTANCE_DASH = builder.nextAccessor("biped/skill/wingstance/wingstance_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.05f, 0.18f, 0.30f, 0.8f, 0.38f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.adder( 5))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.55f)),

                        new AttackAnimation.Phase(0.481f, 0.5f, 0.50f, 0.55f, 0.8f,1.5f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(33.33f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_light_slash.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.93)))

                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        WINGSTANCE_AIRSLASH = builder.nextAccessor("biped/skill/wingstance/wingstance_airslash", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.2f, 0.23f, 0.58f, 0.8F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier( 1.30f))
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.adder( 5))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));








    }


}
