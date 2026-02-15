package F.epicfight_dd.gameasset.animation;

import F.epicfight_dd.gameasset.dawnDaySounds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty.*;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.effect.EpicFightMobEffects;


public class MiladyMoveset {

    public static AnimationAccessor<StaticAnimation> MILADY_WALK;
    public static AnimationAccessor<StaticAnimation> MILADY_IS_IDLE;
    public static AnimationAccessor<StaticAnimation> MILADY_DUAL_IS_IDLE;
    public static AnimationAccessor<StaticAnimation> MILADY_TWOHANDED_IS_IDLE;
    public static AnimationAccessor<StaticAnimation> MILADY_TWOHANDED_WALK;
    public static AnimationAccessor<StaticAnimation> MILADY_SPECIAL_WALK;
    public static AnimationAccessor<StaticAnimation> POLE_AXE_IDLE;
    public static AnimationAccessor<StaticAnimation> WAR_SICKLE_IDLE;
    public static AnimationAccessor<StaticAnimation> HEAVY_AXE_IDLE;
    public static AnimationAccessor<StaticAnimation> MILADY_SPECIAL_IDLE;
    public static AnimationAccessor<StaticAnimation> BACKHAND_IDLE;
    public static AnimationAccessor<StaticAnimation> HALBEARD_IDLE;

    public static AnimationAccessor<StaticAnimation> MILADY_ONEHANDED_RUN;

    public static AnimationAccessor<StaticAnimation> MILADY_TWOHANDED_GUARD;
    public static AnimationAccessor<StaticAnimation> MILADY_DUAL_GUARD;


    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_1;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_2;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_3;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_4;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_ONE_HANDED_AUTO_5;
    public static AnimationAccessor<DashAttackAnimation> milady_onehanded_dash;
    public static AnimationAccessor<AirSlashAnimation> MILADY_AIR_SLASH;

    public static AnimationAccessor<AttackAnimation> MILADY_KNUCKLE_INNATE;
    public static AnimationAccessor<AttackAnimation> PIERCING_FANG;
    public static AnimationAccessor<AttackAnimation> SPEARING_STRIKE;
    public static AnimationAccessor<AttackAnimation> FURIOUS_CUT;

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

    public static AnimationAccessor<BasicAttackAnimation> MILADY_SPECIAL_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_SPECIAL_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_SPECIAL_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_SPECIAL_AUTO4;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_SPECIAL_AUTO5;
    public static AnimationAccessor<DashAttackAnimation> MILADY_SPECIAL_DASH;

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

    public static AnimationAccessor<BasicAttackAnimation> BACKHAND_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> BACKHAND_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> BACKHAND_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> BACKHAND_AUTO4;

    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> HALBEARD_DASH;

    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;

        MILADY_DUAL_GUARD = builder.nextAccessor("biped/living/milady_dual_guard", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_TWOHANDED_GUARD = builder.nextAccessor("biped/living/milady_twohanded_guard", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_ONEHANDED_RUN = builder.nextAccessor("biped/living/milady_onehanded_run", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_WALK = builder.nextAccessor("biped/living/milady_onehanded_walk", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        BACKHAND_IDLE = builder.nextAccessor("biped/living/backhand_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_SPECIAL_WALK = builder.nextAccessor("biped/living/milady_special_walk", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_TWOHANDED_WALK = builder.nextAccessor("biped/living/milady_twohanded_walk", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_IS_IDLE = builder.nextAccessor("biped/living/milady_onehanded_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_DUAL_IS_IDLE = builder.nextAccessor("biped/living/milady_dual_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_SPECIAL_IDLE = builder.nextAccessor("biped/living/milady_special_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        MILADY_TWOHANDED_IS_IDLE = builder.nextAccessor("biped/living/milady_twohanded_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        POLE_AXE_IDLE = builder.nextAccessor("biped/living/pole_axe_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        WAR_SICKLE_IDLE = builder.nextAccessor("biped/living/war_sickle_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        HEAVY_AXE_IDLE = builder.nextAccessor("biped/living/heavy_axe_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        HALBEARD_IDLE = builder.nextAccessor("biped/living/halbeard_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));



            MILADY_ONE_HANDED_AUTO_1 = builder.nextAccessor("biped/combat/milady_onehanded_auto1", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.milady_light_sweep.get())
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

                            new AttackAnimation.Phase(0.481f, 0.5f, 0.6f, 1.0f, 1.3f,1.5f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                    .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.23)))

                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            milady_onehanded_dash = builder.nextAccessor("biped/combat/milady_onehanded_dash", (accessor) ->
                    new DashAttackAnimation(0.12F, 0.0f, 0.1f, 0.3f, 1.2F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            MILADY_AIR_SLASH = builder.nextAccessor("biped/combat/milady_onehanded_airslash" , ac ->
                    new AirSlashAnimation(  0.12F, 0.1f ,0.3f, 0.8f,null, biped.get().toolR, ac , biped));

            MILADY_KNUCKLE_INNATE = builder.nextAccessor("biped/skill/milady/milady_special_knuckle_strike", ac->
                   new AttackAnimation(0.1f,0.2f,0.5f,0.6f,20f,InteractionHand.MAIN_HAND, ColliderPreset.HEAD,biped.get().elbowR,ac,biped)
                           .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                           .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_BIG.get())
                           .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true) // remove if fault
                           .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                           .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                           .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                   (e,s,p)->
                                           e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(),2,60)), AnimationEvent.Side.SERVER
                           ))
            );
        PIERCING_FANG = builder.nextAccessor("biped/skill/piercing_fang", ac->
                new AttackAnimation(0.1f,0.2f,0.5f,1.1f,20f,InteractionHand.MAIN_HAND, MiladyCollider.LIGHT_GREATSWORD,biped.get().toolR,ac,biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true) // remove if fault
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e,s,p)->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(),2,60)), AnimationEvent.Side.SERVER
                                ))
        );
        SPEARING_STRIKE = builder.nextAccessor("biped/skill/spearing_strike", ac->
                new AttackAnimation(0.1f,0.2f,0.2f,0.95f,20f,InteractionHand.MAIN_HAND, MiladyCollider.HALBERD_INNATE,biped.get().toolR,ac,biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true) // remove if fault
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.36F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(2.0F, 0.25F, -5.0F), Armatures.BIPED.get().toolR, 1.1D, 0.55F))
                        .addEvents(AnimationEvent.InTimeEvent.create(0.85F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(-0.0F, 0.25F, -1.0F), Armatures.BIPED.get().toolR, 1.5D, 1.3F))
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e,s,p)->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(),2,60)), AnimationEvent.Side.SERVER
                        ))
        );
        FURIOUS_CUT = builder.nextAccessor("biped/skill/furious_cut", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.7f, 0.9f, 0.9f, 0.91f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLADE),

                        new AttackAnimation.Phase(0.91f, 0.92f, 1.05f, 1.18f, 1.18f,1.19f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                        new AttackAnimation.Phase(1.19f, 1.55f, 1.65f, 1.9f, 2.9f, 20f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(5))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLADE)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(5))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.0F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false));

        MILADY_TWOHANDED_AUTO1 = builder.nextAccessor("biped/combat/milady_twohanded_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.milady_light_sweep.get())
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
                new DashAttackAnimation(0.12F, 0.01F, 0.02F, 0.3F, 1.2F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AIRSLASH = builder.nextAccessor("biped/combat/milady_twohanded_airslash", (ac) ->
                new AirSlashAnimation(  0.12F, 0.1f ,0.3f, 0.8f,null, biped.get().toolR, ac , biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO1 = builder.nextAccessor("biped/combat/milady_dual_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.4f, 0.49f, 0.5f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.51f, 0.6f, 0.7f, 1.2f, 1.2f,1.2f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO2 = builder.nextAccessor("biped/combat/milady_dual_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.4f, 0.6f, 0.7f, 0.8f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.481f, 0.2f, 0.4f, 0.6f, 0.9f,0.91f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())

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
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.15))
                        .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_DASH = builder.nextAccessor("biped/combat/milady_dual_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.3F, 0.20F, 0.32F, 1.2F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AIRSLASH = builder.nextAccessor("biped/combat/milady_dual_airslash" , ac ->
                new AirSlashAnimation(  0.12F, 0.2f ,0.4f, 0.6f,null, biped.get().toolR, ac , biped));

        MILADY_SPECIAL_AUTO1 = builder.nextAccessor("biped/combat/milady_special_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.46f, 0.45f, 0.46f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.47f, 0.48f, 0.5f, 0.85f, 0.8f,0.9f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO2 = builder.nextAccessor("biped/combat/milady_special_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.6f, 0.6f, 0.6f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.61f, 0.5f, 0.5f, 0.82f, 0.8f,1.3f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO3 = builder.nextAccessor("biped/combat/milady_special_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.12f, 0.4f, 0.4f, 0.41f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.42f, 0.43f, 0.45f, 0.8f, 0.9f,1.21f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO4 = builder.nextAccessor("biped/combat/milady_special_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.26f, 0.5f, 0.4f, 0.51f, InteractionHand.MAIN_HAND, biped.get().toolR,null),

                        new AttackAnimation.Phase(0.52f, 0.53f, 0.7f, 1.0f, 0.9f,1.01f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(1.02f, 1.03f, 1.04f, 1.25f, 1.1f, 1.25f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get()))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO5 = builder.nextAccessor("biped/combat/milady_special_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.5f, 0.5f, 0.51f, InteractionHand.OFF_HAND, biped.get().toolL,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get()),

                        new AttackAnimation.Phase(0.52f, 0.53f, 0.55f, 0.9158f, 1.0f,1.21f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(5))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_DASH = builder.nextAccessor("biped/combat/milady_special_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.1F, 0.20F, 0.42F, 1.2F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F));


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

        BACKHAND_AUTO1 = builder.nextAccessor("biped/combat/backhand_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO2 = builder.nextAccessor("biped/combat/backhand_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO3 = builder.nextAccessor("biped/combat/backhand_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO4 = builder.nextAccessor("biped/combat/backhand_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO1 = builder.nextAccessor("biped/combat/halbeard_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO2 = builder.nextAccessor("biped/combat/halbeard_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO3 = builder.nextAccessor("biped/combat/halbeard_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO4 = builder.nextAccessor("biped/combat/halbeard_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLADE)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.2F))
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.multiplier(1.6F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.6F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(-0.0F, 0.25F, -1.0F), Armatures.BIPED.get().toolR, 1.1D, 0.55F))

                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBEARD_DASH = builder.nextAccessor("biped/combat/halbeard_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.0f, 0.1f, 0.3f, 1.2F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        }
    }

