package net.dawn_day.gameasset.animation;

import net.dawn_day.registry.entries.DawnDayEffects;
import net.dawn_day.registry.entries.DawnDaySounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.*;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.types.grappling.GrapplingAttackAnimation;
import yesman.epicfight.api.animation.types.grappling.GrapplingTryAnimation;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.registry.entries.EpicFightMobEffects;
import yesman.epicfight.registry.entries.EpicFightParticles;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;
import yesman.epicfight.world.damagesource.StunType;

import java.util.Set;

public class DawnDayAnimations {

    public static AnimationAccessor<StaticAnimation> MILADY_WALK;
    public static AnimationAccessor<StaticAnimation> TPOSE;
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
    public static AnimationAccessor<StaticAnimation> BATTLESTAFF_IDLE;
    public static AnimationAccessor<StaticAnimation> SICKLE_IDLE;
    public static AnimationAccessor<StaticAnimation> SICKLE_DUAL_IDLE;
    public static AnimationAccessor<StaticAnimation> STEELAXE_IDLE;
    public static AnimationAccessor<StaticAnimation> KNIFE_ONEHANDED_IDLE;
    public static AnimationAccessor<StaticAnimation> KNIFE_DUAL_IDLE;
    public static AnimationAccessor<StaticAnimation> SABER_IDLE;
    public static AnimationAccessor<StaticAnimation> BAT_IDLE;
    public static AnimationAccessor<StaticAnimation> SAW_IDLE;
    public static AnimationAccessor<StaticAnimation> MESSER_DUAL_IDLE;
    public static AnimationAccessor<StaticAnimation> MESSER_IDLE;
    public static AnimationAccessor<StaticAnimation> GREATAXE_DUAL_IDLE;

    public static AnimationAccessor<StaticAnimation> MILADY_ONEHANDED_RUN;
    public static AnimationAccessor<ComboAttackAnimation> TRAIL_TEST;

    public static AnimationAccessor<ComboAttackAnimation> SABER_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> SABER_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> SABER_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> SABER_AUTO4;
    public static AnimationAccessor<ComboAttackAnimation> SABER_AUTO5;
    public static AnimationAccessor<ComboAttackAnimation> SABER_AUTO6;
    public static AnimationAccessor<DashAttackAnimation> SABER_DASH;
    public static AnimationAccessor<ComboAttackAnimation> SABER_AIRSLASH;

    public static AnimationAccessor<ComboAttackAnimation> BAT_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> BAT_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> BAT_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> BAT_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> BAT_DASH;
    public static AnimationAccessor<AirSlashAnimation> BAT_AIRSLASH;

    public static AnimationAccessor<ComboAttackAnimation> BATTLESTAFF_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> BATTLESTAFF_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> BATTLESTAFF_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> BATTLESTAFF_AUTO4;
    public static AnimationAccessor<ComboAttackAnimation> BATTLESTAFF_AUTO5;
    public static AnimationAccessor<DashAttackAnimation> BATTLESTAFF_DASH;
    public static AnimationAccessor<AirSlashAnimation> BATTLESTAFF_AIRSLASH;

    public static AnimationAccessor<ComboAttackAnimation> SICKLE_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> SICKLE_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> SICKLE_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> SICKLE_AUTO4;
    public static AnimationAccessor<ComboAttackAnimation> SICKLE_AIRSLASH;
    public static AnimationAccessor<DashAttackAnimation> SICKLE_DASH;
    public static AnimationAccessor<ComboAttackAnimation> SICKLE_DUAL_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> SICKLE_DUAL_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> SICKLE_DUAL_AUTO3;

    public static AnimationAccessor<ComboAttackAnimation> STEELAXE_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> STEELAXE_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> STEELAXE_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> STEELAXE_AUTO4;

    public static AnimationAccessor<ComboAttackAnimation> KNIFE_ONEHANDED_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> KNIFE_ONEHANDED_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> KNIFE_ONEHANDED_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> KNIFE_ONEHANDED_AUTO4;
    public static AnimationAccessor<ComboAttackAnimation> KNIFE_DUAL_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> KNIFE_DUAL_AUTO2;

    public static AnimationAccessor<DashAttackAnimation> KNIFE_DUAL_DASH;
    public static AnimationAccessor<AirSlashAnimation> KNIFE_DUAL_AIRSLASH;


    public static AnimationAccessor<ComboAttackAnimation> MILADY_ONE_HANDED_AUTO_1;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_ONE_HANDED_AUTO_2;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_ONE_HANDED_AUTO_3;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_ONE_HANDED_AUTO_4;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_ONE_HANDED_AUTO_5;
    public static AnimationAccessor<DashAttackAnimation> milady_onehanded_dash;
    public static AnimationAccessor<AirSlashAnimation> MILADY_AIR_SLASH;

    public static AnimationAccessor<AttackAnimation> MILADY_KNUCKLE_INNATE;
    public static AnimationAccessor<AttackAnimation> PIERCING_FANG;
    public static AnimationAccessor<AttackAnimation> SPEARING_STRIKE;
    public static AnimationAccessor<AttackAnimation> FURIOUS_CUT;
    public static AnimationAccessor<AttackAnimation> SKULL_RUPTURE;
    public static AnimationAccessor<AttackAnimation> QUICK_RUSH;
    public static AnimationAccessor<AttackAnimation> WHIRLWIND;
    public static AnimationAccessor<AttackAnimation> IMPAILING_THRUST;
    public static AnimationAccessor<AttackAnimation> SPINNING_SHADOW;
    public static AnimationAccessor<AttackAnimation> BRUTAL_DASH;
    public static AnimationAccessor<AttackAnimation> GROUNDSLAM;
    public static AnimationAccessor<AttackAnimation> WILD_STRIKES;

    public static AnimationAccessor<ComboAttackAnimation> MILADY_TWOHANDED_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_TWOHANDED_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_TWOHANDED_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_TWOHANDED_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> MILADY_TWOHANDED_DASH;
    public static AnimationAccessor<DashAttackAnimation> MILADY_DASH_NEW;
    public static AnimationAccessor<AirSlashAnimation> MILADY_TWOHANDED_AIRSLASH;

    public static AnimationAccessor<ComboAttackAnimation> MILADY_DUAL_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_DUAL_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_DUAL_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_DUAL_AUTO4;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_DUAL_AUTO5;
    public static AnimationAccessor<DashAttackAnimation> MILADY_DUAL_DASH;
    public static AnimationAccessor<AirSlashAnimation> MILADY_DUAL_AIRSLASH;

    public static AnimationAccessor<ComboAttackAnimation> MILADY_SPECIAL_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_SPECIAL_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_SPECIAL_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_SPECIAL_AUTO4;
    public static AnimationAccessor<ComboAttackAnimation> MILADY_SPECIAL_AUTO5;
    public static AnimationAccessor<DashAttackAnimation> MILADY_SPECIAL_DASH;

    public static AnimationAccessor<ComboAttackAnimation> MESSER_DUAL_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> MESSER_DUAL_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> MESSER_DUAL_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> MESSER_ONEHANDED_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> MESSER_ONEHANDED_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> MESSER_ONEHANDED_AUTO3;

    public static AnimationAccessor<StaticAnimation> RITUS_DAGGER_GUARD;
    public static AnimationAccessor<LongHitAnimation> RITUS_DAGGER_NEUTRALIZED;
    public static AnimationAccessor<GuardAnimation> RITUS_DAGGER_GUARD_HIT;
    public static AnimationAccessor<ActionAnimation> RITUS_DAGGER_PARRY1;
    public static AnimationAccessor<ActionAnimation> RITUS_DAGGER_PARRY2;

    public static AnimationAccessor<StaticAnimation> NIGHT_RITUS_DAGGER_IDLE;
    public static AnimationAccessor<ComboAttackAnimation> NIGHT_RITUS_DAGGER_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> NIGHT_RITUS_DAGGER_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> NIGHT_RITUS_DAGGER_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> NIGHT_RITUS_DAGGER_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> NIGHT_RITUS_DAGGER_DASH;
    public static AnimationAccessor<LongHitAnimation> SEPUKKU;

    public static AnimationAccessor<StaticAnimation> BLOOD_RITUS_DAGGER_IDLE;
    public static AnimationAccessor<ComboAttackAnimation> BLOOD_RITUS_DAGGER_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> BLOOD_RITUS_DAGGER_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> BLOOD_RITUS_DAGGER_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> BLOOD_RITUS_DAGGER_AUTO4;

    public static AnimationAccessor<StaticAnimation> RITUS_DAGGER_DUAL_IDLE;
    public static AnimationAccessor<ComboAttackAnimation> RITUS_DAGGER_DUAL_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> RITUS_DAGGER_DUAL_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> RITUS_DAGGER_DUAL_AUTO3;
    public static AnimationAccessor<DashAttackAnimation> RITUS_DAGGER_DUAL_DASH;


    public static AnimationAccessor<ComboAttackAnimation> SAW_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> SAW_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> SAW_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> SAW_AUTO4;

    public static AnimationAccessor<ComboAttackAnimation> GREATAXE_DUAL_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> GREATAXE_DUAL_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> GREATAXE_DUAL_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> GREATAXE_DUAL_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> GREATAXE_DUAL_DASH;
    public static AnimationAccessor<AirSlashAnimation> GREATAXE_AIRSLASH;
    public static AnimationAccessor<AttackAnimation> ANNIHILATE;

    public static AnimationAccessor<ComboAttackAnimation> WAR_SICKLE_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> WAR_SICKLE_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> WAR_SICKLE_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> WAR_SICKLE_AUTO4;
    public static AnimationAccessor<ComboAttackAnimation> WAR_SICKLE_AUTO5;
    public static AnimationAccessor<ComboAttackAnimation> WAR_SICKLE_AUTO6;

    public static AnimationAccessor<ComboAttackAnimation> BACKHAND_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> BACKHAND_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> BACKHAND_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> BACKHAND_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> BACKHAND_DASH;
    public static AnimationAccessor<AirSlashAnimation> BACKHAND_AIRSLASH;

    public static AnimationAccessor<ComboAttackAnimation> HALBERD_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> HALBERD_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> HALBERD_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> HALBERD_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> HALBERD_DASH;

    public static AnimationAccessor<StaticAnimation> HOLLOW_OCHS_IDLE;
    public static AnimationAccessor<StaticAnimation> HOLLOW_OCHS_WALK;
    public static AnimationAccessor<StaticAnimation> HOLLOW_OCHS_RUN;
    public static AnimationAccessor<ComboAttackAnimation> HOLLOW_OCHS_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> HOLLOW_OCHS_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> HOLLOW_OCHS_AUTO3;
    public static AnimationAccessor<ComboAttackAnimation> HOLLOW_OCHS_AUTO4;
    public static AnimationAccessor<StaticAnimation> HOLLOW_ONEHANDED_IDLE;
    public static AnimationAccessor<ComboAttackAnimation> HOLLOW_ONEHANDED_AUTO1;
    public static AnimationAccessor<ComboAttackAnimation> HOLLOW_ONEHANDED_AUTO2;
    public static AnimationAccessor<ComboAttackAnimation> HOLLOW_ONEHANDED_AUTO3;
    public static AnimationAccessor<LongHitAnimation> RAAAHHHHH;

    public static AnimationAccessor<ActionAnimation> TCH_I_MISSED;
    public static AnimationAccessor<LongHitAnimation> PLS_NOOOO_DONT_KEBAB_MEEE;
    public static AnimationAccessor<GrapplingAttackAnimation> GET_KEBABed_MuAHAHAHA;
    public static AnimationAccessor<GrapplingTryAnimation> IM_GONNA_KEBAB_YOUUUU;

    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;

        MESSER_DUAL_IDLE = builder.nextAccessor("biped/living/messer_dual_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        NIGHT_RITUS_DAGGER_IDLE = builder.nextAccessor("biped/living/night_ritus_dagger_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        RITUS_DAGGER_GUARD = builder.nextAccessor("biped/living/ritus_dagger_guard", ac ->
                new StaticAnimation(0.25F, true, ac, biped));

        RITUS_DAGGER_GUARD_HIT = builder.nextAccessor("biped/living/ritus_dagger_guard_hit", ac ->
                new GuardAnimation(0.12F, ac, biped));

        RITUS_DAGGER_PARRY1 = builder.nextAccessor("biped/living/ritus_dagger_parry1", ac ->
                new ActionAnimation(0.12F, ac, biped));

        RITUS_DAGGER_NEUTRALIZED = builder.nextAccessor("biped/living/ritus_dagger_neutralized", ac ->
                new LongHitAnimation(0.12F, ac, biped)
                        .addEvents(AnimationProperty.StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().playSound(DawnDaySounds.GUARD_BREAK.get(),100,1), AnimationEvent.Side.CLIENT
                        ))
        );

        RITUS_DAGGER_PARRY2 = builder.nextAccessor("biped/living/ritus_dagger_parry2", ac ->
                new ActionAnimation(0.12F, ac, biped));

        BLOOD_RITUS_DAGGER_IDLE = builder.nextAccessor("biped/living/blood_ritus_dagger_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        RITUS_DAGGER_DUAL_IDLE = builder.nextAccessor("biped/living/ritus_dagger_dual_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        HOLLOW_ONEHANDED_IDLE = builder.nextAccessor("biped/living/hollow_onehanded_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        MESSER_IDLE = builder.nextAccessor("biped/living/messer_onehanded_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        TRAIL_TEST = builder.nextAccessor("biped/living/trail_test", (accessor) ->
                new ComboAttackAnimation(0f, 0.05F, 0.25F, 0.3F, 1.43F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 50F));

        HOLLOW_OCHS_IDLE = builder.nextAccessor("biped/living/hollow_ochs_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        HOLLOW_OCHS_WALK = builder.nextAccessor("biped/living/hollow_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        HOLLOW_OCHS_RUN = builder.nextAccessor("biped/living/hollow_run", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        GREATAXE_DUAL_IDLE = builder.nextAccessor("biped/living/greataxe_dual_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        RITUS_DAGGER_DUAL_AUTO1 = builder.nextAccessor("biped/combat/ritus_dagger_dual_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.15f, 0.23f, 0.6f, 0.25f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        ,

                        new AttackAnimation.Phase(0.26f, 0.27f, 0.37f, 0.45f, 0.68f, 0.46f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)),

                        new AttackAnimation.Phase(0.46f, 0.47f, 0.47f, 0.65f, 0.68f, 3.51f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.88F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        RITUS_DAGGER_DUAL_AUTO2 = builder.nextAccessor("biped/combat/ritus_dagger_dual_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.25f, 0.35f, 1.5f, 0.35f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F))
                        ,

                        new AttackAnimation.Phase(0.351f, 0.352f, 0.48f, 0.6f, 1.5f, 0.6f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.62f, 0.63f, 0.63f, 0.75f, 1.5f, 0.8f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F)),

                        new AttackAnimation.Phase(0.81f, 0.82f, 0.93f, 1.1f, 1.5f, 1.18f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(1.2f, 1.23f, 1.2f, 1.4f, 1.5f, 5.18f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.MILADY_DASH)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.6F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.84F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        RITUS_DAGGER_DUAL_DASH = builder.nextAccessor("biped/combat/ritus_dagger_dual_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.25f, 0.33f, 0.8f, 0.33f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F))
                        ,

                        new AttackAnimation.Phase(0.33f, 0.331f, 0.332f, 0.5f, 0.8f, 0.5f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.51f, 0.51f, 0.54f, 0.68f, 0.8f, 5.18f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.78F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );
        RITUS_DAGGER_DUAL_AUTO3 = builder.nextAccessor("biped/combat/ritus_dagger_dual_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.25f, 0.33f, 0.6f, 0.35f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        ,

                        new AttackAnimation.Phase(0.36f, 0.37f, 0.39f, 0.50f, 0.68f, 0.56f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)),

                        new AttackAnimation.Phase(0.56f, 0.47f, 0.77f, 0.85f, 0.68f, 3.51f, InteractionHand.MAIN_HAND, biped.get().head, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.88F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.15F, 0.8F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        BLOOD_RITUS_DAGGER_AUTO1 = builder.nextAccessor("biped/combat/blood_ritus_dagger_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.05F, 0.25F, 0.34F, 0.43F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BLOOD_RITUS_DAGGER_AUTO2 = builder.nextAccessor("biped/combat/blood_ritus_dagger_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.18f, 0.30f, 0.8f, 0.36f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1F)),

                        new AttackAnimation.Phase(0.37f, 0.374f, 0.4f, 0.58f, 0.8f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        BLOOD_RITUS_DAGGER_AUTO3 = builder.nextAccessor("biped/combat/blood_ritus_dagger_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.30f, 0.4f, 0.8f, 0.4f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F)),

                        new AttackAnimation.Phase(0.4f, 0.45f, 0.535f, 0.65f, 0.8f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.72F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        BLOOD_RITUS_DAGGER_AUTO4 = builder.nextAccessor("biped/combat/blood_ritus_dagger_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.25f, 0.4f, 0.8f, 0.45f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1F)),

                        new AttackAnimation.Phase(0.5f, 0.56f, 0.71f, 0.75f, 0.8f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.78F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        NIGHT_RITUS_DAGGER_AUTO1 = builder.nextAccessor("biped/combat/night_ritus_dagger_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.05F, 0.25F, 0.3F, 0.43F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        NIGHT_RITUS_DAGGER_AUTO2 = builder.nextAccessor("biped/combat/night_ritus_dagger_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.30f, 0.35f, 0.8f, 0.36f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1F)),

                        new AttackAnimation.Phase(0.37f, 0.374f, 0.375f, 0.45f, 0.8f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        NIGHT_RITUS_DAGGER_AUTO3 = builder.nextAccessor("biped/combat/night_ritus_dagger_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.13f, 0.30f, 0.8f, 0.32f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(5F))

                        ,

                        new AttackAnimation.Phase(0.37f, 0.4f, 0.40f, 0.62f, 0.8f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        NIGHT_RITUS_DAGGER_AUTO4 = builder.nextAccessor("biped/combat/night_ritus_dagger_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.13f, 0.28f, 0.8f, 0.28f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                        ,

                        new AttackAnimation.Phase(0.28f, 0.289f, 0.32f, 0.45f, 0.8f, 0.45f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1F)),

                        new AttackAnimation.Phase(0.45f, 0.56f, 0.63f, 1.02f, 1.4f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(15F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.96F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );
        NIGHT_RITUS_DAGGER_DASH = builder.nextAccessor("biped/combat/night_ritus_dagger_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.15f, 0.25f, 0.3f, 0.96f, 0.31f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1F)),

                        new AttackAnimation.Phase(0.32f, 0.33f, 0.35f, 0.45f, 0.96f, 0.46f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5F)),

                        new AttackAnimation.Phase(0.47f, 0.48f, 0.45f, 0.55f, 0.96f, 0.56f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5F)),

                        new AttackAnimation.Phase(0.57f, 0.58f, 0.59f, 0.71f, 0.96f, 2.56f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5F))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.96F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        HOLLOW_ONEHANDED_AUTO1 = builder.nextAccessor("biped/combat/hollow_onehanded_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.53F, 0.65F, 0.83F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HOLLOW_ONEHANDED_AUTO2 = builder.nextAccessor("biped/combat/hollow_onehanded_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.38F, 0.62F, 0.83F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HOLLOW_ONEHANDED_AUTO3 = builder.nextAccessor("biped/combat/hollow_onehanded_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.43F, 0.62F, 1.3F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(15F))
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HOLLOW_OCHS_AUTO1 = builder.nextAccessor("biped/combat/hollow_ochs_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.57F, 0.7F, 0.83F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.3F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HOLLOW_OCHS_AUTO2 = builder.nextAccessor("biped/combat/hollow_ochs_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.51F, 0.4F, 0.5F, 0.63F, DawnDayCollider.SHIELD, biped.get().toolL, accessor, biped)
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(5F))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HOLLOW_OCHS_AUTO3 = builder.nextAccessor("biped/combat/hollow_ochs_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.37F, 0.45F, 0.83F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.15F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HOLLOW_OCHS_AUTO4 = builder.nextAccessor("biped/combat/hollow_ochs_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.35f, 0.55f, 1.3f, 0.56f, InteractionHand.MAIN_HAND, biped.get().toolL, DawnDayCollider.SHIELD)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(5F))

                        ,

                        new AttackAnimation.Phase(0.67f, 0.68f, 0.70f, 0.82f, 1.3f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.8F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.15F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );
        RAAAHHHHH = builder.nextAccessor("biped/skill/raaahhh", ac -> new LongHitAnimation(0.0f, ac, Armatures.BIPED)
                .addEvents(
                        AnimationEvent.InTimeEvent.create(
                                2.5f,

                                (e, s, p) -> {

                                    LivingEntity entity = e.getOriginal();

                                    entity.addEffect(
                                            new MobEffectInstance(
                                                    DawnDayEffects.IMPREGNABILITY,
                                                    400, 0, false, false, true
                                            )
                                    );
                                }, AnimationEvent.Side.SERVER
                        )
                )
                .addEvents(
                        // should play when the last buff is applied
                        AnimationEvent.InTimeEvent.create(1.0f, (e, s, p) ->
                                e.getOriginal().level().playSound(
                                        (Player) e.getOriginal(),
                                        e.getOriginal().getX(),
                                        e.getOriginal().getY(),
                                        e.getOriginal().getZ(),
                                        DawnDaySounds.SKELETON_BANGING.get(),
                                        SoundSource.PLAYERS,
                                        1f,
                                        1.0f
                                )

                                , AnimationEvent.Side.CLIENT),
                        AnimationEvent.InTimeEvent.create(2.5f, (e, s, p) ->
                                        e.getOriginal().level().playSound(
                                                (Player) e.getOriginal(),
                                                e.getOriginal(),
                                                SoundEvents.BELL_RESONATE,
                                                SoundSource.PLAYERS,
                                                100, 1.2F
                                        )
                                , AnimationEvent.Side.CLIENT),
                        AnimationEvent.InTimeEvent.create(2.4f, (e, s, p) ->
                                        e.getOriginal().level().playSound(
                                                (Player) e.getOriginal(),
                                                e.getOriginal(),
                                                SoundEvents.RAVAGER_ROAR,
                                                SoundSource.PLAYERS,
                                                100, 1.5F
                                        )
                                , AnimationEvent.Side.CLIENT)
                )
                .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                        (e, s, p) ->
                                e.getOriginal().addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 220, 4)), AnimationEvent.Side.SERVER
                ))
                .addProperty(ActionAnimationProperty.CANCELABLE_MOVE,false)
                .addProperty(ActionAnimationProperty.STOP_MOVEMENT,true)
                .addState(EntityState.MOVEMENT_LOCKED,true)

        );

        SEPUKKU = builder.nextAccessor(
                "biped/skill/sepukku",
                ac -> new LongHitAnimation(0.0f, ac, Armatures.BIPED)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        1.5f,

                                        (entitypatch, s, p) -> {

                                            float maxHealth =
                                                    entitypatch.getOriginal().getMaxHealth();

                                            float damage =
                                                    1.5f + maxHealth * 0.3F;

                                            if (entitypatch.getOriginal().hasEffect(DawnDayEffects.DRAINED) ||
                                                    entitypatch.getOriginal().hasEffect(DawnDayEffects.SEPUKKU)) {


                                                damage = 3 + maxHealth * 0.6F;
                                            }



                                            entitypatch.getOriginal().hurt(
                                                    entitypatch.getOriginal()
                                                            .damageSources()
                                                            .mobAttack(entitypatch.getOriginal()),
                                                    damage
                                            );
                                        }, AnimationEvent.Side.SERVER
                                ),
                                AnimationEvent.InTimeEvent.create(1.4f, (e, s, p) ->{
                                    e.getOriginal().level().playSound(
                                            (Player) e.getOriginal(),
                                            e.getOriginal(),
                                            DawnDaySounds.CRITICAL_HIT.get(),
                                            SoundSource.PLAYERS,
                                            100, 1.3F
                                    );

                                }, AnimationEvent.Side.CLIENT),

                                AnimationEvent.InTimeEvent.create(2.9f, (e, s, p) ->{
                                    e.getOriginal().level().playSound(
                                            (Player) e.getOriginal(),
                                            e.getOriginal(),
                                            DawnDaySounds.PULL_OUT.get(),
                                            SoundSource.PLAYERS,
                                            100, 1.2F
                                    );

                                }, AnimationEvent.Side.CLIENT),


                                AnimationEvent.InTimeEvent.create(
                                        1.5f,
                                        (e, s, p) -> {

                                            LivingEntity entity = e.getOriginal();

                                            int particleCount = 220 + entity.getRandom().nextInt(60);

                                            for (int i = 0; i < particleCount; i++) {

                                                double offsetX =
                                                        (entity.getRandom().nextDouble() - 0.5D)
                                                                * entity.getBbWidth();

                                                double offsetY =
                                                        entity.getRandom().nextDouble()
                                                                * entity.getBbHeight();

                                                double offsetZ =
                                                        (entity.getRandom().nextDouble() - 0.5D)
                                                                * entity.getBbWidth();


                                                double motionX =
                                                        (entity.getRandom().nextDouble() - 0.5D)
                                                                * 1.2D;

                                                double motionY =
                                                        entity.getRandom().nextDouble()
                                                                * 0.8D;

                                                double motionZ =
                                                        (entity.getRandom().nextDouble() - 0.5D)
                                                                * 1.2D;

                                                entity.level().addParticle(
                                                        EpicFightParticles.BLOOD.get(),

                                                        entity.getX() + offsetX,
                                                        entity.getY() + offsetY,
                                                        entity.getZ() + offsetZ,

                                                        motionX,
                                                        motionY,
                                                        motionZ
                                                );
                                            }

                                        },
                                        AnimationEvent.Side.CLIENT
                                )
                        )
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        3.15f,

                                        (e, s, p) -> {

                                            LivingEntity entity = e.getOriginal();

                                            entity.addEffect(
                                                    new MobEffectInstance(
                                                            DawnDayEffects.SEPUKKU,
                                                            600, 0, false, false, true
                                                    )
                                            );
                                        }, AnimationEvent.Side.SERVER
                                )
                        )



        );



        MILADY_ONEHANDED_RUN = builder.nextAccessor("biped/living/milady_onehanded_run", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        SAW_IDLE = builder.nextAccessor("biped/living/saw_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        BAT_IDLE = builder.nextAccessor("biped/living/nailbat_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        TPOSE = builder.nextAccessor("biped/living/rest_pose", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        SABER_IDLE = builder.nextAccessor("biped/living/saber_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        KNIFE_ONEHANDED_IDLE = builder.nextAccessor("biped/living/knife_onehanded_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        KNIFE_DUAL_IDLE = builder.nextAccessor("biped/living/knife_dual_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        STEELAXE_IDLE = builder.nextAccessor("biped/living/steelaxe_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        SICKLE_DUAL_IDLE = builder.nextAccessor("biped/living/sickle_dual_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        SICKLE_IDLE = builder.nextAccessor("biped/living/sickle_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        MILADY_WALK = builder.nextAccessor("biped/living/milady_onehanded_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        BACKHAND_IDLE = builder.nextAccessor("biped/living/backhand_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        MILADY_SPECIAL_WALK = builder.nextAccessor("biped/living/milady_special_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        MILADY_TWOHANDED_WALK = builder.nextAccessor("biped/living/milady_twohanded_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        MILADY_IS_IDLE = builder.nextAccessor("biped/living/milady_onehanded_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        MILADY_DUAL_IS_IDLE = builder.nextAccessor("biped/living/milady_dual_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        MILADY_SPECIAL_IDLE = builder.nextAccessor("biped/living/milady_special_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        MILADY_TWOHANDED_IS_IDLE = builder.nextAccessor("biped/living/milady_twohanded_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        POLE_AXE_IDLE = builder.nextAccessor("biped/living/pole_axe_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        WAR_SICKLE_IDLE = builder.nextAccessor("biped/living/war_sickle_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        HEAVY_AXE_IDLE = builder.nextAccessor("biped/living/heavy_axe_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        HALBEARD_IDLE = builder.nextAccessor("biped/living/halbeard_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        BATTLESTAFF_IDLE = builder.nextAccessor("biped/living/battlestaff_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        SABER_AUTO1 = builder.nextAccessor("biped/combat/saber_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.2F, 0.30F, 0.53F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AUTO2 = builder.nextAccessor("biped/combat/saber_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.31F, 0.4F, 0.60F, 0.63F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        SABER_AUTO3 = builder.nextAccessor("biped/combat/saber_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.2F, 0.30F, 0.53F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(33F))
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AUTO4 = builder.nextAccessor("biped/combat/saber_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.3f, 0.4f, 1.2f, 0.50f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F)),

                        new AttackAnimation.Phase(0.51f, 0.52f, 0.90f, 1.12f, 1.2f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AUTO5 = builder.nextAccessor("biped/combat/saber_auto5", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.4F, 0.50F, 0.7F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AUTO6 = builder.nextAccessor("biped/combat/saber_auto6", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.5F, 0.60F, 0.8F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AIRSLASH = builder.nextAccessor("biped/combat/saber_airslash", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.3f, 0.4f, 1.2f, 0.50f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F)),

                        new AttackAnimation.Phase(0.51f, 0.52f, 0.90f, 1.12f, 1.2f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_DASH = builder.nextAccessor("biped/combat/saber_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.31F, 0.4F, 0.60F, 0.63F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MESSER_DUAL_AUTO1 = builder.nextAccessor("biped/combat/messer_dual_auto1", (accessor) ->
                        new ComboAttackAnimation(0.12F, accessor, biped,
                                new AttackAnimation.Phase(0.0f, 0.10f, 0.5f, 0.72f, 1.1f, 0.76f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                                ,

                                new AttackAnimation.Phase(0.77f, 0.78f, 0.87f, 1.02f, 1.1f, 1.81f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                        )

                                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                                .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MESSER_DUAL_AUTO2 = builder.nextAccessor("biped/combat/messer_dual_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.40F, 0.58F, 0.87F, DawnDayCollider.KNIFE_DASH, biped.get().head, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25F))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MESSER_DUAL_AUTO3 = builder.nextAccessor("biped/combat/messer_dual_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.8f, 0.92f, 1.3f, 0.92f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                        ,

                        new AttackAnimation.Phase(0.93f, 0.93f, 1.00f, 1.1f, 1.33f, 3.51f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.6F))
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MESSER_ONEHANDED_AUTO1 = builder.nextAccessor("biped/combat/messer_onehanded_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.40F, 0.58F, 0.87F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MESSER_ONEHANDED_AUTO2 = builder.nextAccessor("biped/combat/messer_onehanded_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.40F, 0.58F, 0.87F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MESSER_ONEHANDED_AUTO3 = builder.nextAccessor("biped/combat/messer_onehanded_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.40F, 0.58F, 0.87F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(15F))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        GREATAXE_DUAL_AUTO1 = builder.nextAccessor("biped/combat/greataxe_dual_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.4f, 0.6f, 1.2f, 0.7f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                        ,

                        new AttackAnimation.Phase(0.8f, 0.9f, 1.00f, 1.3f, 1.23f, 3.51f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.90F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        GREATAXE_DUAL_AUTO2 = builder.nextAccessor("biped/combat/greataxe_dual_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.35f, 0.5f, 1.7f, 0.55f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F))
                        ,

                        new AttackAnimation.Phase(0.6f, 0.6f, 0.80f, 1.0f, 1.73f, 1.05f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(0F))
                ,
                        new AttackAnimation.Phase(1.1f, 1.2f, 1.35f, 1.55f, 1.73f, 3.51f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BONE_SAW_AUTO4)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.05F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.HOLD)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(4F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        1.45F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -2.0F), Armatures.BIPED.get().rootJoint,0.5D, 1.0F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        GREATAXE_DUAL_AUTO3 = builder.nextAccessor("biped/combat/greataxe_dual_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.50f, 0.67f, 0.72f, 1.1f, 0.73f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F))
                        ,

                        new AttackAnimation.Phase(0.74f, 0.75f, 0.76f, 0.85f, 1.13f, 0.86f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.87f, 0.87f, 0.88f, 1.03f, 1.23f, 3.51f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BONE_SAW_AUTO4)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        0.75F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -4.0F), Armatures.BIPED.get().rootJoint, 2.0D, 2.0F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        GREATAXE_DUAL_AUTO4 = builder.nextAccessor("biped/combat/greataxe_dual_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.71F, 0.91F, 1.13F, 1.87F, DawnDayCollider.BONE_SAW_AUTO4, biped.get().rootJoint, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.7F))
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(6F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        0.95F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -3.0F), Armatures.BIPED.get().rootJoint, 3.0D, 4.0F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        GREATAXE_DUAL_DASH = builder.nextAccessor("biped/combat/greataxe_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.50f, 0.47f, 0.52f, 1.3f, 0.53f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                        ,

                        new AttackAnimation.Phase(0.54f, 0.55f, 0.56f, 0.75f, 1.3f, 2.86f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        GREATAXE_AIRSLASH = builder.nextAccessor("biped/combat/greataxe_scissors", (accessor) ->
                new AirSlashAnimation(0.12F, 0.65F, 0.7F, 1.27F, DawnDayCollider.BACKHAND_INNATE, biped.get().rootJoint, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 0.55F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        BAT_AUTO1 = builder.nextAccessor("biped/combat/nailbat_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.49F, 0.76F, 0.87F, DawnDayCollider.BAT_LONGER, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AUTO2 = builder.nextAccessor("biped/combat/nailbat_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.21F, 0.42F, 0.72F, 0.83F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AUTO3 = builder.nextAccessor("biped/combat/nailbat_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.4f, 0.6f, 1.9f, 0.70f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),


                        new AttackAnimation.Phase(0.71f, 0.8f, 1.0f, 1.25f, 1.9f, 1.26f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)),

                        new AttackAnimation.Phase(1.27f, 1.3f, 1.57f, 1.82f, 2.2f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BAT_LONGER)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AUTO4 = builder.nextAccessor("biped/combat/nailbat_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.26F, 0.55F, 0.83F, 2.39F, DawnDayCollider.BAT_LONGER, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.8F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(15f))
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(2))
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AIRSLASH = builder.nextAccessor("biped/combat/nailbat_airslash", ac ->
                new AirSlashAnimation(0.12F, 0.25f, 0.52f, 0.9f, DawnDayCollider.BAT_LONGER, biped.get().toolR, ac, biped)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, false)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );
        BAT_DASH = builder.nextAccessor("biped/combat/nailbat_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.21F, 0.33F, 0.50F, 0.63F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        0.34F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -1.0F), Armatures.BIPED.get().rootJoint, 0.5D, 2.5F))

        );

        SAW_AUTO1 = builder.nextAccessor("biped/combat/saw_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.58f, 0.90f, 0.59f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_ROD.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.15F)),

                        new AttackAnimation.Phase(0.4f, 0.4f, 0.9f, 1.20f, 0.98f, 2.82f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.EMPTY)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(25))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SAW_AUTO2 = builder.nextAccessor("biped/combat/saw_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.16F, 0.3F, 0.55F, 0.80F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SAW_AUTO3 = builder.nextAccessor("biped/combat/saw_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.16F, 0.17F, 0.33F, 0.50F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SAW_AUTO4 = builder.nextAccessor("biped/combat/saw_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.27f, 0.37f, 1.70f, 0.39f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_ROD.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.85F)),

                        new AttackAnimation.Phase(0.41f, 0.42f, 0.65f, 0.75f, 1.78f, 2.82f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BONE_SAW_AUTO4)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.6F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(25))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.EMPTY)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.2F, 0.4F))
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        0.65F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -3.0F), Armatures.BIPED.get().rootJoint, 2.5D, 3.5F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        BATTLESTAFF_AUTO1 = builder.nextAccessor("biped/combat/battlestaff_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.18f, 0.30f, 0.70f, 0.35f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FRONT)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.BATTLE_STAFF_SWING.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.55F)),

                        new AttackAnimation.Phase(0.35f, 0.3f, 0.55f, 0.75f, 0.78f, 1.82f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FRONT)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_ROD.get())
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        BATTLESTAFF_AUTO2 = builder.nextAccessor("biped/combat/battlestaff_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.2f, 0.5f, 1.04f, 0.62f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FRONT)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.15F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get()),

                        new AttackAnimation.Phase(0.61f, 0.3f, 0.75f, 1.0f, 1.04f, 1.98f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FRONT)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BATTLESTAFF_AUTO3 = builder.nextAccessor("biped/combat/battlestaff_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.31F, 0.5F, 0.70F, 0.73F, DawnDayCollider.BATTLESTAFF_BACK, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BATTLESTAFF_AUTO4 = builder.nextAccessor("biped/combat/battlestaff_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.08f, 0.15f, 0.9f, 0.20f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_BACK)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.BATTLE_STAFF_SWING.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.21f, 0.21f, 0.23f, 0.25f, 0.9f, 0.3f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2F)),

                        new AttackAnimation.Phase(0.31f, 0.32f, 0.33f, 0.35f, 0.9f, 0.41f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.BATTLE_STAFF_SWING.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F)),

                        new AttackAnimation.Phase(0.41f, 0.45f, 0.5f, 0.9f, 0.9f, 1.9f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F))


                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.7F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BATTLESTAFF_AUTO5 = builder.nextAccessor("biped/combat/battlestaff_auto5", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.01f, 0.05f, 0.25f, 1.2f, 0.32f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FRONT)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get()),

                        new AttackAnimation.Phase(0.32f, 0.3f, 0.45f, 0.5f, 1.25f, 1.98f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_BACK)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(5))
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BATTLESTAFF_DASH = builder.nextAccessor("biped/combat/battlestaff_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,

                        new AttackAnimation.Phase(0.0f, 0.1f, 0.1f, 0.18f, 0.9f, 0.3f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_BACK)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_ROD.get()),

                        new AttackAnimation.Phase(0.31f, 0.35f, 0.3f, 0.6f, 0.9f, 2f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_BACK))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4f))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        BATTLESTAFF_AIRSLASH = builder.nextAccessor("biped/combat/battlestaff_airslash", ac ->
                new AirSlashAnimation(0.12F, 0.25f, 0.46f, 0.6f, DawnDayCollider.BAT_LONGER, biped.get().toolR, ac, biped)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, false)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        MILADY_ONE_HANDED_AUTO_1 = builder.nextAccessor("biped/combat/milady_onehanded_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.25F, 0.52F, 0.75F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_LIGHT_SWEEP.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        MILADY_ONE_HANDED_AUTO_2 = builder.nextAccessor("biped/combat/milady_onehanded_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.25F, 0.5F, 0.78F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_ONE_HANDED_AUTO_3 = builder.nextAccessor("biped/combat/milady_onehanded_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.1F, 0.2F, 0.48F, 0.54F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_ONE_HANDED_AUTO_4 = builder.nextAccessor("biped/combat/milady_onehanded_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.1F, 0.2F, 0.41F, 0.5F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        //the method changes if there has to be more than one phases
        MILADY_ONE_HANDED_AUTO_5 = builder.nextAccessor("biped/combat/milady_onehanded_auto5", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.4f, 1.5f, 0.48f, InteractionHand.MAIN_HAND, biped.get().toolR, null),

                        new AttackAnimation.Phase(0.481f, 0.5f, 0.6f, 1.0f, 1.3f, 2.5f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 1.23)))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        milady_onehanded_dash = builder.nextAccessor("biped/combat/milady_onehanded_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.0f, 0.1f, 0.3f, 0.8F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_AIR_SLASH = builder.nextAccessor("biped/combat/milady_onehanded_airslash", ac ->
                new AirSlashAnimation(0.12F, 0.1f, 0.3f, 0.8f, null, biped.get().toolR, ac, biped));


        SKULL_RUPTURE = builder.nextAccessor("biped/skill/skull_rupture", ac ->
                new AttackAnimation(0.1f, 0.5f, 0.7f, 1.1f, 4f, InteractionHand.MAIN_HAND, DawnDayCollider.BATTLESTAFF_FRONT, biped.get().toolR, ac, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, DawnDaySounds.POISE_BREAK.get())
                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true) // remove if fault
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY, 2, 60)), AnimationEvent.Side.SERVER
                        ))
        );

        MILADY_KNUCKLE_INNATE = builder.nextAccessor("biped/skill/milady/milady_special_knuckle_strike", ac ->
                new AttackAnimation(0.1f, 0.2f, 0.5f, 0.6f, 1.0f, InteractionHand.MAIN_HAND, ColliderPreset.HEAD, biped.get().elbowR, ac, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false) // remove if fault
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY, 25, 60)), AnimationEvent.Side.SERVER
                        ))
        );
        PIERCING_FANG = builder.nextAccessor("biped/skill/piercing_fang", ac ->
                new AttackAnimation(0.1f, 0.2f, 0.5f, 1.1f, 20f, InteractionHand.MAIN_HAND, DawnDayCollider.LIGHT_GREATSWORD, biped.get().toolR, ac, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true) // remove if fault
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY, 2, 60)), AnimationEvent.Side.SERVER
                        ))
        );
        SPEARING_STRIKE = builder.nextAccessor("biped/skill/spearing_strike", ac ->
                new AttackAnimation(0.1f, 0.2f, 0.2f, 0.95f, 20f, InteractionHand.MAIN_HAND, DawnDayCollider.HALBERD_INNATE, biped.get().toolR, ac, biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true) // remove if fault
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.36F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(2.0F, 0.25F, -5.0F), Armatures.BIPED.get().toolR, 1.5D, 0.55F))
                        .addEvents(AnimationEvent.InTimeEvent.create(0.85F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(-0.0F, 0.25F, -1.0F), Armatures.BIPED.get().toolR, 2.5D, 1.3F))
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY, 2, 60)), AnimationEvent.Side.SERVER
                        ))
        );

        ANNIHILATE = builder.nextAccessor("biped/skill/annihilate", ac ->
                new AttackAnimation(0.1f, 1.1f, 1.3f, 1.5f, 20f, InteractionHand.MAIN_HAND, DawnDayCollider.BONE_SAW_AUTO4, biped.get().rootJoint, ac, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.5F, 1.2F))
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        1.4F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(2.0F, 0.25F, 5.0F), Armatures.BIPED.get().rootJoint, 3.5D, 3.5F))
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY, 15, 60 )), AnimationEvent.Side.SERVER
                        ))
        );

        QUICK_RUSH = builder.nextAccessor("biped/skill/quick_rush", ac ->
                new AttackAnimation(0.1f, 0.2f, 0.25f, 0.6f, 0.8f, InteractionHand.MAIN_HAND, null, biped.get().toolR, ac, biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true) // remove if fault
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY, 25, 60)), AnimationEvent.Side.SERVER
                        ))
        );

        WILD_STRIKES = builder.nextAccessor("biped/skill/wild_strikes", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.3f, 2.9f, 0.31f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1f)),

                        new AttackAnimation.Phase(0.35f, 0.4f, 0.6f, 0.7f, 2.9f, 0.75f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.95f)),


                        new AttackAnimation.Phase(0.77f, 0.8f, 1.15f, 1.25f, 2.9f, 1.45f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2f)),

                        new AttackAnimation.Phase(1.5f, 1.5f, 1.56f, 1.78f, 1.8f, 20f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.WILDSTRIKES)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(15f))
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.FINISHER))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.BYPASS_DODGE))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.EXECUTION))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.4f))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
        );

        BRUTAL_DASH = builder.nextAccessor("biped/skill/brutal_dash", ac ->
                new AttackAnimation(0.1f, 0.1f, 0.25f, 0.35f, 0.5f, InteractionHand.MAIN_HAND, DawnDayCollider.BAT_BACK, biped.get().toolR, ac, biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_ROD.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY, 20, 60)), AnimationEvent.Side.SERVER))
        );

        FURIOUS_CUT = builder.nextAccessor("biped/skill/furious_cut", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.7f, 0.9f, 0.9f, 0.91f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_LIGHT_SLASH.get()),

                        new AttackAnimation.Phase(1.02f, 1.03f, 1.05f, 1.28f, 1.18f, 1.29f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_HEAVY_SLASH.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                        new AttackAnimation.Phase(1.5f, 1.55f, 1.65f, 1.9f, 2.5f, 20f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.9f))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.0F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false));

        GROUNDSLAM = builder.nextAccessor("biped/skill/groundslam", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.06f, 0.1f, 0.2f, 2.9f, 0.21f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FULL)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.BATTLE_STAFF_SWING.get()),

                        new AttackAnimation.Phase(0.22f, 0.35f, 0.4f, 0.45f, 2.9f, 0.36f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FULL)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),


                        new AttackAnimation.Phase(0.37f, 0.4f, 0.5f, 0.55f, 2.9f, 0.6f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FULL)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.BATTLE_STAFF_SWING.get()),

                        new AttackAnimation.Phase(0.6f, 0.62f, 0.65f, 0.7f, 2.9f, 0.8f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FULL)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),

                        new AttackAnimation.Phase(0.81f, 0.82f, 0.85f, 0.93f, 2.18f, 0.95f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FULL)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.BATTLE_STAFF_SWING.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG),

                        new AttackAnimation.Phase(0.95f, 1.1f, 1.22f, 1.3f, 1.8f, 20f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.GROUNDSLAM)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.FINISHER))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.BYPASS_DODGE))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.EXECUTION))
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(9.6F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.9f))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.9F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        1.24F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -1.0F), Armatures.BIPED.get().rootJoint, 5.5D, 11.3F))
        );

        SPINNING_SHADOW = builder.nextAccessor("biped/skill/spinning_shadow", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.0f, 0.05f, 0.2f, 1.9f, 0.20f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BACKHAND_INNATE)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.15f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE),

                        new AttackAnimation.Phase(0.20f, 0.22f, 0.20f, 0.28f, 1.98f, 0.39f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BACKHAND_INNATE)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.25f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG),
                        new AttackAnimation.Phase(0.3f, 0.35f, 0.4f, 0.55f, 1.9f, 5f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BACKHAND_INNATE)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5f))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.0F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false));

        IMPAILING_THRUST = builder.nextAccessor("biped/skill/impailing_thrust", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.12f, 0.4f, 1.9f, 0.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE),

                        new AttackAnimation.Phase(0.52f, 0.52f, 0.8f, 1.18f, 1.88f, 3.89f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.0F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false));

        WHIRLWIND = builder.nextAccessor("biped/skill/whirlwind", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.2f, 0.3f, 5.31f, 0.32f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.1f)),
                        new AttackAnimation.Phase(0.33f, 0.34f, 0.35f, 0.36f, 5.38f, 0.39f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.16f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.40f, 0.44f, 0.45f, 0.46f, 5.48f, 0.49f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.50f, 0.54f, 0.55f, 0.56f, 5.58f, 0.59f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.60f, 0.64f, 0.65f, 0.66f, 5.68f, 0.69f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.70f, 0.74f, 0.75f, 0.76f, 5.78f, 0.79f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.80f, 0.84f, 0.85f, 0.86f, 5.88f, 0.89f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.90f, 0.94f, 0.95f, 0.96f, 5.98f, 0.99f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.00f, 1.04f, 1.05f, 1.06f, 5.08f, 1.09f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.10f, 1.14f, 1.15f, 1.16f, 5.18f, 1.19f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.20f, 1.24f, 1.25f, 1.26f, 5.28f, 1.29f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.30f, 1.34f, 1.35f, 1.36f, 5.38f, 1.39f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.40f, 1.44f, 1.45f, 1.46f, 5.48f, 1.49f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.50f, 1.54f, 1.55f, 1.56f, 5.58f, 1.59f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.HOLD)
                                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER,ValueModifier.adder(10))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),


                        new AttackAnimation.Phase(1.89f, 1.95f, 2.15f, 2.2f, 5.9f, 20f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND2)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.5f))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(33F))
                                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER,ValueModifier.adder(12))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false));

        MILADY_TWOHANDED_AUTO1 = builder.nextAccessor("biped/combat/milady_twohanded_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.42F, 0.8F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_LIGHT_SWEEP.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO2 = builder.nextAccessor("biped/combat/milady_twohanded_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO3 = builder.nextAccessor("biped/combat/milady_twohanded_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO4 = builder.nextAccessor("biped/combat/milady_twohanded_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.47F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2f))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_DASH = builder.nextAccessor("biped/combat/milady_twohanded_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.01F, 0.02F, 0.3F, 0.6F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AIRSLASH = builder.nextAccessor("biped/combat/milady_twohanded_airslash", (ac) ->
                new AirSlashAnimation(0.12F, 0.1f, 0.3f, 0.8f, null, biped.get().toolR, ac, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO1 = builder.nextAccessor("biped/combat/milady_dual_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.4f, 0.49f, 0.5f, InteractionHand.OFF_HAND, biped.get().toolL, null),

                        new AttackAnimation.Phase(0.51f, 0.6f, 0.7f, 1.2f, 1.2f, 2.2f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO2 = builder.nextAccessor("biped/combat/milady_dual_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.58f, 0.7f, 0.7f, 0.8f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.MILADY_DASH),

                        new AttackAnimation.Phase(0.481f, 0.2f, 0.4f, 0.6f, 0.7f, 1.91f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO3 = builder.nextAccessor("biped/combat/milady_dual_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.4f, 1.0f, 0.41f, InteractionHand.OFF_HAND, biped.get().toolL, null),

                        new AttackAnimation.Phase(0.42f, 0.5f, 0.55f, 0.8f, 1.0f, 2.2f, InteractionHand.MAIN_HAND, biped.get().toolR, null))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO4 = builder.nextAccessor("biped/combat/milady_dual_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.55f, 0.8f, 1.2f, 0.8f, InteractionHand.OFF_HAND, biped.get().toolL, null),

                        new AttackAnimation.Phase(0.81f, 0.82f, 0.83f, 1.15f, 1.2f, 2.6f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO5 = builder.nextAccessor("biped/combat/milady_dual_auto5", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.2F, 0.3F, 0.4F, 0.82F, DawnDayCollider.MILADY_DASH, biped.get().rootJoint, accessor, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 1.15))
                        .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_DASH = builder.nextAccessor("biped/combat/milady_dual_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.3F, 0.14F, 0.27F, 0.7F, DawnDayCollider.MILADY_DASH, biped.get().rootJoint, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AIRSLASH = builder.nextAccessor("biped/combat/milady_dual_airslash", ac ->
                new AirSlashAnimation(0.12F, 0.2f, 0.4f, 0.6f, null, biped.get().toolR, ac, biped));

        MILADY_SPECIAL_AUTO1 = builder.nextAccessor("biped/combat/milady_special_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.46f, 0.45f, 0.46f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.47f, 0.48f, 0.5f, 0.85f, 0.8f, 1.9f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO2 = builder.nextAccessor("biped/combat/milady_special_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.6f, 0.6f, 0.6f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.61f, 0.5f, 0.5f, 0.82f, 0.8f, 1.3f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO3 = builder.nextAccessor("biped/combat/milady_special_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.12f, 0.4f, 0.4f, 0.41f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.42f, 0.43f, 0.45f, 0.8f, 0.9f, 2.21f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO4 = builder.nextAccessor("biped/combat/milady_special_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.26f, 0.5f, 0.5f, 0.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.52f, 0.53f, 0.7f, 1.0f, 1.0f, 1.01f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F)),

                        new AttackAnimation.Phase(1.02f, 1.03f, 1.04f, 1.25f, 1.4f, 2.55f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25))
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO5 = builder.nextAccessor("biped/combat/milady_special_auto5", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.5f, 0.5f, 0.51f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_LIGHT_SLASH.get()),

                        new AttackAnimation.Phase(0.52f, 0.53f, 0.55f, 0.9158f, 1.0f, 2.21f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_HEAVY_SLASH.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(2))
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_DASH = builder.nextAccessor("biped/combat/milady_special_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.1F, 0.20F, 0.42F, 0.6F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F));

        WAR_SICKLE_AUTO1 = builder.nextAccessor("biped/combat/war_sickle_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO2 = builder.nextAccessor("biped/combat/war_sickle_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO3 = builder.nextAccessor("biped/combat/war_sickle_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO4 = builder.nextAccessor("biped/combat/war_sickle_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO5 = builder.nextAccessor("biped/combat/war_sickle_auto5", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO6 = builder.nextAccessor("biped/combat/war_sickle_auto6", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO1 = builder.nextAccessor("biped/combat/backhand_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO2 = builder.nextAccessor("biped/combat/backhand_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO3 = builder.nextAccessor("biped/combat/backhand_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.30F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1f))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO4 = builder.nextAccessor("biped/combat/backhand_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.30F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.3f))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20))
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO1 = builder.nextAccessor("biped/combat/halbeard_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO2 = builder.nextAccessor("biped/combat/halbeard_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO3 = builder.nextAccessor("biped/combat/halbeard_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO4 = builder.nextAccessor("biped/combat/halbeard_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLADE)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.6F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(-0.0F, 0.25F, -1.0F), Armatures.BIPED.get().toolR, 1.1D, 0.55F))

                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_DASH = builder.nextAccessor("biped/combat/halbeard_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.0f, 0.1f, 0.3f, 1.2F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        GET_KEBABed_MuAHAHAHA = builder.nextAccessor("biped/skill/grab/grab_execute", ac -> new GrapplingAttackAnimation(
                        0.52f, 0.85f, ac, biped)
                        .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.EXECUTION, DamageTypeTags.BYPASSES_ARMOR))
                //  .addProperty(ActionAnimationProperty.COORD_UPDATE_TIME, TimePairList.create(0.0F, 0.5F))
        );

        PLS_NOOOO_DONT_KEBAB_MEEE = builder.nextAccessor("biped/skill/grab/grab_hit", ac ->
                new LongHitAnimation(0.01f, ac, biped)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, true));

        TCH_I_MISSED = builder.nextAccessor("biped/skill/grab/grab_fail", accessor ->
                new ActionAnimation(0.0f, 0.85f, accessor, biped));

        IM_GONNA_KEBAB_YOUUUU = builder.nextAccessor("biped/skill/grab/grab_try", ac ->
                        new GrapplingTryAnimation(0.05f,
                                0.1f,
                                0.18f,
                                0.28f,
                                0.3667f,
                                InteractionHand.MAIN_HAND,
                                ColliderPreset.HEADBUTT_RAVAGER, biped.get().leftHandJoint(),
                                ac,
                                DawnDayAnimations.PLS_NOOOO_DONT_KEBAB_MEEE,
                                DawnDayAnimations.GET_KEBABed_MuAHAHAHA,
                                DawnDayAnimations.TCH_I_MISSED,
                                biped)
                // .addProperty(ActionAnimationProperty.COORD_START_KEYFRAME_INDEX, 1)
                // .addProperty(ActionAnimationProperty.DEST_LOCATION_PROVIDER, MoveCoordFunctions.SYNCHED_TARGET_ENTITY_LOCATION_VARIABLE)
        );

        SICKLE_AUTO1 = builder.nextAccessor("biped/combat/sickle_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.3F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_AUTO2 = builder.nextAccessor("biped/combat/sickle_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.3F, 0.62F, 0.75F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8f))
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_AUTO3 = builder.nextAccessor("biped/combat/sickle_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.3F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_AUTO4 = builder.nextAccessor("biped/combat/sickle_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.2F, 0.3F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_DASH = builder.nextAccessor("biped/combat/sickle_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.2F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_AIRSLASH = builder.nextAccessor("biped/combat/sickle_airslash", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.2F, 0.25F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5f))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_DUAL_AUTO1 = builder.nextAccessor("biped/combat/sickle_dual_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.32f, 0.55f, 0.55f, 0.55f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)),

                        new AttackAnimation.Phase(0.56f, 0.6f, 0.75f, 0.85f, 0.88f, 1.9f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_DUAL_AUTO2 = builder.nextAccessor("biped/combat/sickle_dual_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.38F, 0.52F, 0.75F, ColliderPreset.DUAL_SWORD_AIR_SLASH, biped.get().chest, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1f))
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_DUAL_AUTO3 = builder.nextAccessor("biped/combat/sickle_dual_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.38F, 0.52F, 0.75F, ColliderPreset.DUAL_SWORD_AIR_SLASH, biped.get().chest, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4f))
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO1 = builder.nextAccessor("biped/combat/steelaxe_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.3F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.42F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, 0.25F, -2.0F), Armatures.BIPED.get().toolR, 1.1D, 0.55F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO2 = builder.nextAccessor("biped/combat/steelaxe_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.3F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO3 = builder.nextAccessor("biped/combat/steelaxe_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.4F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO4 = builder.nextAccessor("biped/combat/steelaxe_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.1f, 0.3f, 0.42f, 0.55f, 0.55f, 0.55f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)),

                        new AttackAnimation.Phase(0.56f, 0.6f, 1.05f, 1.15f, 1.28f, 2.5f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.6F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addEvents(AnimationEvent.InTimeEvent.create(1.1F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, 0.25F, -1.0F), Armatures.BIPED.get().toolR, 1.1D, 0.65F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_ONEHANDED_AUTO1 = builder.nextAccessor("biped/combat/knife_onehanded_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.3F, 0.52F, 0.52F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_ONEHANDED_AUTO2 = builder.nextAccessor("biped/combat/knife_onehanded_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.01f, 0.05f, 0.3f, 0.55f, 0.45f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.46f, 0.47f, 0.45f, 0.65f, 0.85f, 2.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_ONEHANDED_AUTO3 = builder.nextAccessor("biped/combat/knife_onehanded_auto3", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.1f, 0.35f, 0.7f, 0.45f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F)),

                        new AttackAnimation.Phase(0.46f, 0.50f, 0.55f, 0.65f, 0.7f, 2.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_ONEHANDED_AUTO4 = builder.nextAccessor("biped/combat/knife_onehanded_auto4", (accessor) ->
                new ComboAttackAnimation(0.12F, 0.3F, 0.5F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_LIGHT_SLASH.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_AUTO1 = builder.nextAccessor("biped/combat/knife_dual_auto1", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.25f, 0.8f, 0.38f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8f)),

                        new AttackAnimation.Phase(0.381f, 0.4f, 0.45f, 0.6f, 0.65f, 2.5f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_LIGHT_SLASH.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 1.13)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_AUTO2 = builder.nextAccessor("biped/combat/knife_dual_auto2", (accessor) ->
                new ComboAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.55f, 0.8f, 0.60f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7f)),

                        new AttackAnimation.Phase(0.61f, 0.6f, 0.65f, 0.8f, 0.65f, 1.5f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 1.13)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_DASH = builder.nextAccessor("biped/combat/knife_dual_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.10f, 0.35f, 0.8f, 0.48f, InteractionHand.MAIN_HAND, biped.get().head, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8f)),

                        new AttackAnimation.Phase(0.481f, 0.4f, 0.65f, 0.85f, 0.75f, 1.5f, InteractionHand.OFF_HAND, biped.get().head, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_HEAVY_SLASH.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 1.23)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_AIRSLASH = builder.nextAccessor("biped/combat/knife_dual_airslash", (accessor) ->
                new AirSlashAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.10f, 0.35f, 0.5f, 0.48f, InteractionHand.MAIN_HAND, biped.get().head, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AIRSLASH = builder.nextAccessor("biped/combat/backhand_airslash", (accessor) ->
                new AirSlashAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.10f, 0.35f, 0.8f, 0.48f, InteractionHand.MAIN_HAND, biped.get().head, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_DASH = builder.nextAccessor("biped/combat/backhand_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.0f, 0.25f, 0.4f, 0.6F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 0.90))
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DASH_NEW = builder.nextAccessor("biped/combat/milady_dash_new", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.30f, 0.55f, 0.8f, 0.58f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_DUAL_SLASH.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3f)),

                        new AttackAnimation.Phase(0.581f, 0.5f, 0.65f, 0.9f, 0.95f, 2.5f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.MILADY_HEAVY_SLASH.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 0.73)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

    }


}

