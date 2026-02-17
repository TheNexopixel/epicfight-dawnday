package F.epicfight_dd.gameasset.animation;

import F.epicfight_dd.gameasset.dawnDaySounds;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.StunType;

public class WingStanceAnims {


    public static AnimationManager.AnimationAccessor<StaticAnimation> WING_STANCE_IDLE;

    public static AnimationManager.AnimationAccessor<MovementAnimation> WINGSTANCE_WALK;

    public static AnimationManager.AnimationAccessor<ActionAnimation> WINGSTANCE_TRANSITION;

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

        WINGSTANCE_SKILL1 = builder.nextAccessor("biped/skill/wingstance/wingstance_skill1", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.35f, 0.6f, 0.6f, 0.61f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLADE),

                        new AttackAnimation.Phase(0.61f, 0.67f, 0.68f, 1.0f, 1.0f,1.01f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLADE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                        new AttackAnimation.Phase(1.01f, 1.1f, 1.15f, 1.35f, 2.9f, 20f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(5))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLADE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(5))
                )
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.0F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL,true)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));

        WINGSTANCE_SKILL2 = builder.nextAccessor("biped/skill/wingstance/wingstance_skill2", (accessor) ->
                new AttackAnimation(0.12F, 0.6F, 0.65F, 1.4F, 6.2F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, dawnDaySounds.piercing_fang.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(15))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())


                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL,true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));


        WINGSTANCE_TRANSITION = builder.nextAccessor("biped/skill/wingstance/wingstance_transition", accessor ->
                new ActionAnimation(0.2f,accessor,biped)
                        .addState(EntityState.TURNING_LOCKED,false)
                        .addState(EntityState.MOVEMENT_LOCKED,false)
                        .addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT,false)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));







    }


}
