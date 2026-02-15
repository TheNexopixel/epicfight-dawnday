package F.epicfight_dd.gameasset.animation;

import F.epicfight_dd.gameasset.dawnDaySounds;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.MovementAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
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
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.7f, 0.9f, 0.9f, 0.91f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLADE),

                        new AttackAnimation.Phase(0.91f, 0.92f, 1.05f, 1.18f, 1.18f,1.19f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                        new AttackAnimation.Phase(1.19f, 1.55f, 1.65f, 1.9f, 2.9f, 20f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(5))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLADE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(5))
                )
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.0F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));

        WINGSTANCE_SKILL2 = builder.nextAccessor("biped/skill/wingstance/wingstance_skill2", (accessor) ->
                new AttackAnimation(0.12F, 0.6F, 0.6F, 0.9F, 6.2F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));






    }


}
