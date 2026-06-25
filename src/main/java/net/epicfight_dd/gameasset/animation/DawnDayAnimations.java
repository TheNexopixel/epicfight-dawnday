package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.gameasset.dawnDaySounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
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
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.effect.EpicFightMobEffects;

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
    public static AnimationAccessor<StaticAnimation> LIGHTGREATSWORD_SWORD_IDLE;
    public static AnimationAccessor<StaticAnimation> LIGHTGREATSWORD_SWORD_WALK;
    public static AnimationAccessor<StaticAnimation> LIGHTGREATSWORD_SWORD_RUN;

    public static AnimationAccessor<BasicAttackAnimation> LIGHTGREATSWORD_SWORD_ATT1;
    public static AnimationAccessor<BasicAttackAnimation> LIGHTGREATSWORD_SWORD_ATT2;
    public static AnimationAccessor<BasicAttackAnimation> LIGHTGREATSWORD_SWORD_ATT3;
    public static AnimationAccessor<BasicAttackAnimation> LIGHTGREATSWORD_SWORD_ATT4;
    public static AnimationAccessor<AirSlashAnimation> LIGHTGREATSWORD_SWORD_AIRSLASH;

    public static AnimationAccessor<StaticAnimation> MILADY_ONEHANDED_RUN;
    public static AnimationAccessor<BasicAttackAnimation> TRAIL_TEST;

    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO4;
    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO5;
    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO6;
    public static AnimationAccessor<DashAttackAnimation> SABER_DASH;
    public static AnimationAccessor<BasicAttackAnimation> SABER_AIRSLASH;

    public static AnimationAccessor<BasicAttackAnimation> BAT_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> BAT_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> BAT_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> BAT_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> BAT_DASH;
    public static AnimationAccessor<AirSlashAnimation> BAT_AIRSLASH;

    public static AnimationAccessor<BasicAttackAnimation> BATTLESTAFF_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> BATTLESTAFF_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> BATTLESTAFF_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> BATTLESTAFF_AUTO4;
    public static AnimationAccessor<BasicAttackAnimation> BATTLESTAFF_AUTO5;
    public static AnimationAccessor<DashAttackAnimation> BATTLESTAFF_DASH;
    public static AnimationAccessor<AirSlashAnimation> BATTLESTAFF_AIRSLASH;

    public static AnimationAccessor<StaticAnimation> IRON_FIST_IDLE;
    public static AnimationAccessor<StaticAnimation> IRON_FIST_WALK;
    public static AnimationAccessor<LongHitAnimation> IRON_FIST_GUARD_BREAK;
    public static AnimationAccessor<StaticAnimation> IRON_FIST_GUARD;
    public static AnimationAccessor<GuardAnimation> IRON_FIST_GUARD_HIT;
    public static AnimationAccessor<AttackAnimation> IRON_FIST_GUARD_COUNTER;
    public static AnimationAccessor<BasicAttackAnimation> IRON_FIST_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> IRON_FIST_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> IRON_FIST_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> IRON_FIST_AUTO4;
    public static AnimationAccessor<BasicAttackAnimation> IRON_FIST_AUTO5;
    public static AnimationAccessor<DashAttackAnimation> IRON_FIST_DASH;
    public static AnimationAccessor<BasicAttackAnimation> IRON_FIST_AIRSLASH;
    public static AnimationAccessor<AttackAnimation> POWERFUL_KICK;

    public static AnimationAccessor<BasicAttackAnimation> SICKLE_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> SICKLE_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> SICKLE_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> SICKLE_AUTO4;
    public static AnimationAccessor<BasicAttackAnimation> SICKLE_AIRSLASH;
    public static AnimationAccessor<DashAttackAnimation> SICKLE_DASH;
    public static AnimationAccessor<BasicAttackAnimation> SICKLE_DUAL_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> SICKLE_DUAL_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> SICKLE_DUAL_AUTO3;

    public static AnimationAccessor<BasicAttackAnimation> STEELAXE_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> STEELAXE_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> STEELAXE_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> STEELAXE_AUTO4;

    public static AnimationAccessor<BasicAttackAnimation> KNIFE_ONEHANDED_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> KNIFE_ONEHANDED_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> KNIFE_ONEHANDED_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> KNIFE_ONEHANDED_AUTO4;
    public static AnimationAccessor<BasicAttackAnimation> KNIFE_DUAL_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> KNIFE_DUAL_AUTO2;

    public static AnimationAccessor<DashAttackAnimation> KNIFE_DUAL_DASH;
    public static AnimationAccessor<AirSlashAnimation> KNIFE_DUAL_AIRSLASH;


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
    public static AnimationAccessor<AttackAnimation> SKULL_RUPTURE;
    public static AnimationAccessor<AttackAnimation> QUICK_RUSH;
    public static AnimationAccessor<AttackAnimation> WHIRLWIND;
    public static AnimationAccessor<AttackAnimation> IMPAILING_THRUST;
    public static AnimationAccessor<AttackAnimation> SPINNING_SHADOW;
    public static AnimationAccessor<AttackAnimation> BRUTAL_DASH;
    public static AnimationAccessor<AttackAnimation> GROUNDSLAM;
    public static AnimationAccessor<AttackAnimation> WILD_STRIKES;
    public static AnimationAccessor<AttackAnimation> WILD_STRIKES_DUAL;

    public static AnimationAccessor<BasicAttackAnimation> MILADY_TWOHANDED_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_TWOHANDED_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_TWOHANDED_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> MILADY_TWOHANDED_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> MILADY_TWOHANDED_DASH;
    public static AnimationAccessor<DashAttackAnimation> MILADY_DASH_NEW;
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

    public static AnimationAccessor<BasicAttackAnimation> MESSER_DUAL_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> MESSER_DUAL_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> MESSER_DUAL_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> MESSER_ONEHANDED_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> MESSER_ONEHANDED_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> MESSER_ONEHANDED_AUTO3;

    public static AnimationAccessor<StaticAnimation> ECLIPSE_IDLE;
    public static AnimationAccessor<BasicAttackAnimation> ECLIPSE_ATT1;
    public static AnimationAccessor<BasicAttackAnimation> ECLIPSE_ATT2;
    public static AnimationAccessor<BasicAttackAnimation> ECLIPSE_ATT3;

    public static AnimationAccessor<StaticAnimation> WARSICKLE_IDLE;
    public static AnimationAccessor<BasicAttackAnimation> WARSICKLE_ATT1;
    public static AnimationAccessor<BasicAttackAnimation> WARSICKLE_ATT2;
    public static AnimationAccessor<BasicAttackAnimation> WARSICKLE_ATT3;

    public static AnimationAccessor<StaticAnimation> RITUS_DAGGER_GUARD;
    public static AnimationAccessor<StaticAnimation> RITUS_DAGGER_DUAL_WALK;
    public static AnimationAccessor<LongHitAnimation> RITUS_DAGGER_NEUTRALIZED;
    public static AnimationAccessor<GuardAnimation> RITUS_DAGGER_GUARD_HIT;
    public static AnimationAccessor<ActionAnimation> RITUS_DAGGER_PARRY1;
    public static AnimationAccessor<ActionAnimation> RITUS_DAGGER_PARRY2;

    public static AnimationAccessor<StaticAnimation> NIGHT_RITUS_DAGGER_IDLE;
    public static AnimationAccessor<BasicAttackAnimation> NIGHT_RITUS_DAGGER_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> NIGHT_RITUS_DAGGER_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> NIGHT_RITUS_DAGGER_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> NIGHT_RITUS_DAGGER_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> NIGHT_RITUS_DAGGER_DASH;
    public static AnimationAccessor<LongHitAnimation> SEPUKKU;

    public static AnimationAccessor<StaticAnimation> BLOOD_RITUS_DAGGER_IDLE;
    public static AnimationAccessor<StaticAnimation> BLOOD_RITUS_DAGGER_WALK;
    public static AnimationAccessor<BasicAttackAnimation> BLOOD_RITUS_DAGGER_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> BLOOD_RITUS_DAGGER_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> BLOOD_RITUS_DAGGER_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> BLOOD_RITUS_DAGGER_AUTO4;

    public static AnimationAccessor<StaticAnimation> RITUS_DAGGER_DUAL_IDLE;
    public static AnimationAccessor<BasicAttackAnimation> RITUS_DAGGER_DUAL_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> RITUS_DAGGER_DUAL_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> RITUS_DAGGER_DUAL_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> RITUS_DAGGER_DUAL_AIRSLASH;
    public static AnimationAccessor<DashAttackAnimation> RITUS_DAGGER_DUAL_DASH;

    public static AnimationAccessor<StaticAnimation> FLORETT_IDLE;
    public static AnimationAccessor<BasicAttackAnimation> FLORETT_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> FLORETT_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> FLORETT_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> FLORETT_AUTO4;

    public static AnimationAccessor<StaticAnimation> FLORETT_DUAL_IDLE;
    public static AnimationAccessor<StaticAnimation> FLORETT_DUAL_WALK;
    public static AnimationAccessor<StaticAnimation> FLORETT_DUAL_GUARD;
    public static AnimationAccessor<GuardAnimation> FLORETT_DUAL_GUARD_HIT;
    public static AnimationAccessor<ActionAnimation> FLORETT_DUAL_PARRY1;
    public static AnimationAccessor<ActionAnimation> FLORETT_DUAL_PARRY2;
    public static AnimationAccessor<LongHitAnimation> FLORETT_DUAL_NEUTRALIZED;
    public static AnimationAccessor<BasicAttackAnimation> FLORETT_DUAL_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> FLORETT_DUAL_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> FLORETT_DUAL_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> FLORETT_DUAL_AIRSLASH;
    public static AnimationAccessor<DashAttackAnimation> FLORETT_DUAL_DASH;
    public static AnimationAccessor<AttackAnimation> PIERCING_STRIKE;

    public static AnimationAccessor<BasicAttackAnimation> SAW_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> SAW_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> SAW_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> SAW_AUTO4;


    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO4;
    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO5;
    public static AnimationAccessor<BasicAttackAnimation> WAR_SICKLE_AUTO6;

    public static AnimationAccessor<BasicAttackAnimation> BACKHAND_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> BACKHAND_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> BACKHAND_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> BACKHAND_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> BACKHAND_DASH;
    public static AnimationAccessor<AirSlashAnimation> BACKHAND_AIRSLASH;

    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> HALBERD_DASH;

    public static AnimationAccessor<StaticAnimation> VITR_IDLE;
    public static AnimationAccessor<StaticAnimation> VITR_WALK;
    public static AnimationAccessor<AttackAnimation> INCISURA_VITREA;
    public static AnimationAccessor<BasicAttackAnimation> VITR_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> VITR_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> VITR_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> VITR_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> VITR_DASH;

    public static AnimationAccessor<ActionAnimation> TCH_I_MISSED;
    public static AnimationAccessor<LongHitAnimation> PLS_NOOOO_DONT_KEBAB_MEEE;
    public static AnimationAccessor<GrapplingAttackAnimation> GET_KEBABed_MuAHAHAHA;
    public static AnimationAccessor<GrapplingTryAnimation> IM_GONNA_KEBAB_YOUUUU;

    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;

        MESSER_DUAL_IDLE = builder.nextAccessor("biped/living/messer_dual_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        LIGHTGREATSWORD_SWORD_IDLE = builder.nextAccessor("biped/living/lg_sw_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        LIGHTGREATSWORD_SWORD_WALK = builder.nextAccessor("biped/living/lg_sw_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        LIGHTGREATSWORD_SWORD_RUN = builder.nextAccessor("biped/living/lg_sw_run", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        VITR_WALK = builder.nextAccessor("biped/living/vitreus_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        ECLIPSE_IDLE = builder.nextAccessor("biped/living/eclipse_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        VITR_IDLE = builder.nextAccessor("biped/living/vit_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        WARSICKLE_IDLE = builder.nextAccessor("biped/living/warsickle_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        IRON_FIST_IDLE = builder.nextAccessor("biped/living/iron_fist_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        IRON_FIST_WALK = builder.nextAccessor("biped/living/iron_fist_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        FLORETT_DUAL_WALK = builder.nextAccessor("biped/living/florett_dual_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        FLORETT_DUAL_GUARD = builder.nextAccessor("biped/living/florett_dual_guard", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        FLORETT_DUAL_GUARD_HIT = builder.nextAccessor("biped/living/florett_dual_guard_hit", ac ->
                new GuardAnimation(0.12F, ac, biped));

        FLORETT_DUAL_PARRY1 = builder.nextAccessor("biped/living/florett_dual_parry1", ac ->
                new ActionAnimation(0.12F, ac, biped));

        FLORETT_DUAL_PARRY2 = builder.nextAccessor("biped/living/florett_dual_parry2", ac ->
                new ActionAnimation(0.12F, ac, biped));

        IRON_FIST_GUARD = builder.nextAccessor("biped/living/iron_fist_guard", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        IRON_FIST_GUARD_HIT = builder.nextAccessor("biped/living/iron_fist_guard_hit", ac ->
                new GuardAnimation(0.12F, ac, biped));

        NIGHT_RITUS_DAGGER_IDLE = builder.nextAccessor("biped/living/night_ritus_dagger_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        RITUS_DAGGER_GUARD = builder.nextAccessor("biped/living/ritus_dagger_guard", ac ->
                new StaticAnimation(0.25F, true, ac, biped));

        RITUS_DAGGER_GUARD_HIT = builder.nextAccessor("biped/living/ritus_dagger_guard_hit", ac ->
                new GuardAnimation(0.12F, ac, biped));

        RITUS_DAGGER_PARRY1 = builder.nextAccessor("biped/living/ritus_dagger_parry1", ac ->
                new ActionAnimation(0.12F, ac, biped));

        FLORETT_DUAL_NEUTRALIZED = builder.nextAccessor("biped/living/florett_dual_neutralized", ac ->
                new LongHitAnimation(0.12F, ac, biped)
                        .addEvents(AnimationProperty.StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().playSound(dawnDaySounds.guard_break.get(),100,1), AnimationEvent.Side.CLIENT
                        ))
        );

        RITUS_DAGGER_NEUTRALIZED = builder.nextAccessor("biped/living/ritus_dagger_neutralized", ac ->
                new LongHitAnimation(0.12F, ac, biped)
                        .addEvents(AnimationProperty.StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().playSound(dawnDaySounds.guard_break.get(),100,1), AnimationEvent.Side.CLIENT
                        ))
        );

        IRON_FIST_GUARD_BREAK = builder.nextAccessor("biped/living/iron_fist_guard_break", ac ->
                new LongHitAnimation(0.12F, ac, biped)
                        .addEvents(AnimationProperty.StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().playSound(dawnDaySounds.guard_break.get(),100,1), AnimationEvent.Side.CLIENT
                        ))
        );

        RITUS_DAGGER_PARRY2 = builder.nextAccessor("biped/living/ritus_dagger_parry2", ac ->
                new ActionAnimation(0.12F, ac, biped));

        BLOOD_RITUS_DAGGER_IDLE = builder.nextAccessor("biped/living/blood_ritus_dagger_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        BLOOD_RITUS_DAGGER_WALK = builder.nextAccessor("biped/living/blood_ritus_dagger_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        RITUS_DAGGER_DUAL_WALK = builder.nextAccessor("biped/living/ritus_dagger_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        RITUS_DAGGER_DUAL_IDLE = builder.nextAccessor("biped/living/ritus_dagger_dual_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        FLORETT_IDLE = builder.nextAccessor("biped/living/florett_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        FLORETT_DUAL_IDLE = builder.nextAccessor("biped/living/florett_dual_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        MESSER_IDLE = builder.nextAccessor("biped/living/messer_onehanded_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        TRAIL_TEST = builder.nextAccessor("biped/living/trail_test", (accessor) ->
                new BasicAttackAnimation(0f, 0.05F, 0.25F, 0.3F, 1.43F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 50F));

        GREATAXE_DUAL_IDLE = builder.nextAccessor("biped/living/greataxe_dual_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        LIGHTGREATSWORD_SWORD_ATT1 = builder.nextAccessor("biped/combat/lg_sw_att1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.43f, 0.62f, 1.0f, 0.62f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.1f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        ,

                        new AttackAnimation.Phase(0.82f, 0.7f, 0.82f, 0.98f, 1f, 5.8f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(15))
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.7f))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );
        LIGHTGREATSWORD_SWORD_ATT2 = builder.nextAccessor("biped/combat/lg_sw_att2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.43f, 0.62f, 1.0f, 0.62f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        ,

                        new AttackAnimation.Phase(0.76f, 0.7f, 0.76f, 0.89f, 1f, 5.8f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(15))
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.7f))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );
        LIGHTGREATSWORD_SWORD_ATT3 = builder.nextAccessor("biped/combat/lg_sw_att3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,

                        new AttackAnimation.Phase(0.0f, 0.20f, 0.2f, 0.4f, 1.3f, 0.4f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(15))
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.7f))
                        ,
                        new AttackAnimation.Phase(0.4f, 0.40f, 0.43f, 0.62f, 1.3f, 0.62f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(15))
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.7f))
                        ,

                        new AttackAnimation.Phase(0.96f, 0.7f, 0.96f, 1.19f, 1.3f, 5.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.2f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)

                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        LIGHTGREATSWORD_SWORD_ATT4 = builder.nextAccessor("biped/combat/lg_sw_att4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,

                        new AttackAnimation.Phase(0.0f, 0.20f, 0.5f, 0.8f, 2.1f, 0.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.1f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        ,
                        new AttackAnimation.Phase(1.0f, 0.50f, 1.3f, 1.56f, 2.1f, 5.62f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(15))
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.9f))

                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        LIGHTGREATSWORD_SWORD_AIRSLASH = builder.nextAccessor("biped/combat/lg_sw_airslash", ac ->
                new AirSlashAnimation(0.1f,0.45f, 0.67f, 1.0f, null, biped.get().toolR, ac, biped)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0f, 0.70f))

        );

        PIERCING_STRIKE = builder.nextAccessor("biped/skill/piercing_strike", ac ->
                new AttackAnimation(0.1f, 0.4f, 0.45f, 0.67f, 1.0f, InteractionHand.MAIN_HAND, null, biped.get().toolR, ac, biped)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 20, 60)), AnimationEvent.Side.SERVER
                        ))
        );

        FLORETT_AUTO1 = builder.nextAccessor("biped/combat/florett_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.35F, 0.55F, 0.7F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        FLORETT_AUTO2 = builder.nextAccessor("biped/combat/florett_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.41F, 0.6F, 0.8F, 0.9F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, false)
                   //     .addProperty(AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.2f,0.7f))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        FLORETT_AUTO3 = builder.nextAccessor("biped/combat/florett_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.23f, 0.42f, 1.0f, 0.42f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        ,

                        new AttackAnimation.Phase(0.42f, 0.4f, 0.47f, 0.6f, 1f, 0.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)),

                        new AttackAnimation.Phase(0.98f, 0.47f, 1.05f, 1.20f, 1.0f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.88F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );
        FLORETT_AUTO4 = builder.nextAccessor("biped/combat/florett_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.41F, 0.45F, 0.8F, 1.35F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        FLORETT_DUAL_AUTO1 = builder.nextAccessor("biped/combat/florett_dual_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.33f, 0.52f, 1.0f, 0.52f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                        ,

                        new AttackAnimation.Phase(0.62f, 0.6f, 0.67f, 0.8f, 1f, 5.8f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        FLORETT_DUAL_AUTO2 = builder.nextAccessor("biped/combat/florett_dual_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.6f, 0.8f, 1.3f, 0.8f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        ,

                        new AttackAnimation.Phase(0.9f, 0.9f, 0.9f, 1.1f, 1.3f, 3.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.2f,0.7f))
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        FLORETT_DUAL_AUTO3 = builder.nextAccessor("biped/combat/florett_dual_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.13f, 0.28f, 1.0f, 0.3f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                        ,

                        new AttackAnimation.Phase(0.3f, 0.4f, 0.47f, 0.6f, 1f, 0.6f, InteractionHand.MAIN_HAND, biped.get().toolR, null),

                        new AttackAnimation.Phase(0.6f, 0.6f, 0.61f, 0.75f, 1f, 5.8f, InteractionHand.OFF_HAND, biped.get().toolL, null)

                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        FLORETT_DUAL_DASH = builder.nextAccessor("biped/combat/florett_dual_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.25f, 0.31f, 1.0f, 0.31f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                        ,

                        new AttackAnimation.Phase(0.35f, 0.36f, 0.4f, 0.65f, 1.0f, 5.8f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );
        FLORETT_DUAL_AIRSLASH = builder.nextAccessor("biped/combat/florett_dual_airslash", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.15f, 0.21f, 0.9f, 0.21f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                        ,

                        new AttackAnimation.Phase(0.25f, 0.26f, 0.3f, 0.6f, 0.9f, 5.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.EXTRA_COLLIDERS,1)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        RITUS_DAGGER_DUAL_AUTO1 = builder.nextAccessor("biped/combat/ritus_dagger_dual_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.15f, 0.23f, 0.6f, 0.25f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        ,

                        new AttackAnimation.Phase(0.26f, 0.27f, 0.37f, 0.45f, 0.68f, 0.46f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.KNIFE_BIGGER)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)),

                        new AttackAnimation.Phase(0.46f, 0.47f, 0.47f, 0.65f, 0.68f, 3.51f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.88F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        RITUS_DAGGER_DUAL_AUTO2 = builder.nextAccessor("biped/combat/ritus_dagger_dual_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.25f, 0.35f, 1.5f, 0.35f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F))
                        ,

                        new AttackAnimation.Phase(0.351f, 0.352f, 0.48f, 0.6f, 1.5f, 0.6f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.KNIFE_BIGGER)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.62f, 0.63f, 0.63f, 0.75f, 1.5f, 0.8f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F)),

                        new AttackAnimation.Phase(0.81f, 0.82f, 0.93f, 1.1f, 1.5f, 1.18f, InteractionHand.OFF_HAND, biped.get().toolL, DawnDayCollider.KNIFE_BIGGER)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(1.2f, 1.23f, 1.2f, 1.4f, 1.5f, 5.18f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.MILADY_DASH)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.6F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.84F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        RITUS_DAGGER_DUAL_DASH = builder.nextAccessor("biped/combat/ritus_dagger_dual_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.25f, 0.33f, 0.8f, 0.33f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F))
                        ,

                        new AttackAnimation.Phase(0.33f, 0.331f, 0.332f, 0.5f, 0.8f, 0.5f, InteractionHand.OFF_HAND, biped.get().toolL, DawnDayCollider.KNIFE_BIGGER)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.51f, 0.51f, 0.54f, 0.68f, 0.8f, 5.18f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.78F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.RESET_PLAYER_COMBO_COUNTER, false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        RITUS_DAGGER_DUAL_AUTO3 = builder.nextAccessor("biped/combat/ritus_dagger_dual_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.1f, 0.2f, 1.5f, 0.2f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.13F))
                        ,

                        new AttackAnimation.Phase(0.21f, 0.22f, 0.4f, 0.5f, 1.5f, 0.5f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG),

                        new AttackAnimation.Phase(0.5f, 0.56f, 0.5f, 0.68f, 1.5f, 0.7f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.53F)),

                        new AttackAnimation.Phase(0.7f, 0.72f, 0.8f, 0.9f, 1.5f, 0.9f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.23F)),

                        new AttackAnimation.Phase(0.9f, 0.9f, 0.91f, 1.1f, 1.2f, 5.18f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.84F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );
        RITUS_DAGGER_DUAL_AIRSLASH = builder.nextAccessor("biped/combat/ritus_dagger_dual_airslash", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.35f, 0.49f, 1.2f, 0.5f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F))
                        ,
                        new AttackAnimation.Phase(0.5f, 0.10f, 0.53f, 0.6f, 1.2f, 0.60f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2F))
                        ,
                        new AttackAnimation.Phase(0.6f, 0.60f, 0.7f, 0.8f, 1.2f, 0.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F))
                        ,
                        new AttackAnimation.Phase(0.75f, 0.76f, 0.85f, 0.96f, 1.2f, 0.96f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2F))
                        ,
                        new AttackAnimation.Phase(0.96f, 0.99f, 1.06f, 1.15f, 1.28f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.88F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(AnimationProperty.AttackAnimationProperty.RESET_PLAYER_COMBO_COUNTER, false)
                    //    .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.15F, 0.7F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        BLOOD_RITUS_DAGGER_AUTO1 = builder.nextAccessor("biped/combat/blood_ritus_dagger_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.05F, 0.25F, 0.34F, 0.43F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BLOOD_RITUS_DAGGER_AUTO2 = builder.nextAccessor("biped/combat/blood_ritus_dagger_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
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
                new BasicAttackAnimation(0.12F, accessor, biped,
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
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.25f, 0.4f, 0.8f, 0.45f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1F)),

                        new AttackAnimation.Phase(0.5f, 0.56f, 0.71f, 0.75f, 0.8f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.KNIFE_BIGGER)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.78F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        NIGHT_RITUS_DAGGER_AUTO1 = builder.nextAccessor("biped/combat/night_ritus_dagger_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.05F, 0.25F, 0.3F, 0.43F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        NIGHT_RITUS_DAGGER_AUTO2 = builder.nextAccessor("biped/combat/night_ritus_dagger_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
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
                new BasicAttackAnimation(0.12F, accessor, biped,
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
                new BasicAttackAnimation(0.12F, accessor, biped,
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

        ECLIPSE_ATT1 = builder.nextAccessor("biped/combat/eclipse_att1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.41F, 0.53F, 0.75F, 1.03F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        ECLIPSE_ATT2 = builder.nextAccessor("biped/combat/eclipse_att2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.41F, 0.57F, 0.75F, 1.03F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.25F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        0.6F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -2.5F), Armatures.BIPED.get().rootJoint,0.5D, 1.0F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        ECLIPSE_ATT3 = builder.nextAccessor("biped/combat/eclipse_att3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.41F, 0.5F, 0.75F, 1.03F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WARSICKLE_ATT1 = builder.nextAccessor("biped/combat/warsickle_att1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.41F, 0.43F, 0.6F, 0.83F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WARSICKLE_ATT2 = builder.nextAccessor("biped/combat/warsickle_att2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.33F, 0.45F, 0.83F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WARSICKLE_ATT3 = builder.nextAccessor("biped/combat/warsickle_att3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.41F, 0.67F, 0.89F, 0.83F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        0.75F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.0F, -2.0F), Armatures.BIPED.get().rootJoint,0.5D, 1.0F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        IRON_FIST_AUTO1 = builder.nextAccessor("biped/combat/iron_fist_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.22f, 0.27f, 0.55f, 0.36f, InteractionHand.MAIN_HAND, biped.get().toolR, null),

                        new AttackAnimation.Phase(0.37f, 0.374f, 0.45f, 0.50f, 0.55f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.62F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        IRON_FIST_AUTO2 = builder.nextAccessor("biped/combat/iron_fist_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.18f, 0.30f, 1.3f, 0.36f, InteractionHand.MAIN_HAND, biped.get().elbowR, null),

                        new AttackAnimation.Phase(0.37f, 0.374f, 0.4f, 0.58f, 1.3f, 0.81f, InteractionHand.MAIN_HAND, biped.get().kneeL, null),

                        new AttackAnimation.Phase(0.81f, 0.5f, 0.9f, 1.2f, 1.3f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolL, null)
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        IRON_FIST_AUTO3 = builder.nextAccessor("biped/combat/iron_fist_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.15f, 0.2f, 1.1f, 0.21f, InteractionHand.MAIN_HAND, biped.get().kneeR, DawnDayCollider.IRON_FIST_KICK),

                        new AttackAnimation.Phase(0.22f, 0.23f, 0.3f, 0.4f, 1.1f, 0.4f, InteractionHand.MAIN_HAND, biped.get().kneeR, DawnDayCollider.IRON_FIST_KICK),

                        new AttackAnimation.Phase(0.41f, 0.5f, 0.58f, 0.65f, 1.1f, 0.7f, InteractionHand.MAIN_HAND, biped.get().kneeR, DawnDayCollider.IRON_FIST_KICK),

                        new AttackAnimation.Phase(0.81f, 0.8f, 0.95f, 1.05f, 1.1f, 3.51f, InteractionHand.MAIN_HAND, biped.get().kneeL, DawnDayCollider.IRON_FIST_REACH)
                                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER,ValueModifier.adder(6))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.2f))
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get())
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        IRON_FIST_AUTO4 = builder.nextAccessor("biped/combat/iron_fist_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.13f, 0.26f, 0.8f, 0.36f, InteractionHand.MAIN_HAND, biped.get().toolL, DawnDayCollider.IRON_FIST_REACH),

                        new AttackAnimation.Phase(0.61f, 0.5f, 0.65f, 0.8f, 0.8f, 3.51f, InteractionHand.MAIN_HAND, biped.get().kneeR, DawnDayCollider.IRON_FIST_KICK)
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        IRON_FIST_AUTO5 = builder.nextAccessor("biped/combat/iron_fist_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.41F, 0.73F, 0.8F, 1.33F, DawnDayCollider.IRON_FIST_REACH, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.4f))
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.3F, 0.75F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        IRON_FIST_AIRSLASH = builder.nextAccessor("biped/combat/iron_fist_airslash", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.22f, 0.28f, 1.4f, 0.36f, InteractionHand.MAIN_HAND, biped.get().kneeL, null),

                        new AttackAnimation.Phase(0.37f, 0.374f, 0.4f, 0.58f, 1.4f, 0.81f, InteractionHand.MAIN_HAND, biped.get().kneeR, null),

                        new AttackAnimation.Phase(0.81f, 0.5f, 0.95f, 1.05f, 1.4f, 3.51f, InteractionHand.MAIN_HAND, biped.get().kneeL, DawnDayCollider.IRON_FIST_REACH)
                                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER,ValueModifier.adder(6))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.2f))
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get())
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.82F)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,true)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 1.25F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        IRON_FIST_DASH = builder.nextAccessor("biped/combat/iron_fist_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.11F, 0.33F, 0.4F, 0.53F, DawnDayCollider.IRON_FIST_KICK, biped.get().kneeR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.1f))
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.9F)
                        .addProperty(AttackAnimationProperty.RESET_PLAYER_COMBO_COUNTER,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        IRON_FIST_GUARD_COUNTER = builder.nextAccessor("biped/skill/iron_fist_guard_counter", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.3f, 0.4f, 1.2f, 0.50f, InteractionHand.MAIN_HAND, biped.get().kneeR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get()),

                        new AttackAnimation.Phase(0.51f, 0.52f, 0.60f, 0.75f, 1.2f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

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

                                            if (entitypatch.getOriginal().hasEffect(EffectRegistry.DRAINDED.get()) ||
                                                    entitypatch.getOriginal().hasEffect(EffectRegistry.SEPUKKU.get())) {


                                                damage = 3 + maxHealth * 0.6F;
                                            }



                                            entitypatch.getOriginal().hurt(
                                                    entitypatch.getOriginal()
                                                            .damageSources()
                                                            .genericKill(),
                                                    damage
                                            );
                                        }, AnimationEvent.Side.SERVER
                                ),
                                AnimationEvent.InTimeEvent.create(1.4f, (e, s, p) ->{
                                    e.getOriginal().level().playSound(
                                            null,
                                            e.getOriginal().blockPosition(),
                                            dawnDaySounds.CRITICAL_HIT.get(),
                                            SoundSource.PLAYERS,
                                            1.0F,
                                            1.3F
                                    );

                                }, AnimationEvent.Side.SERVER),

                                AnimationEvent.InTimeEvent.create(2.9f, (e, s, p) ->{
                                    e.getOriginal().level().playSound(
                                            null,
                                            e.getOriginal().blockPosition(),
                                            dawnDaySounds.PULL_OUT.get(),
                                            SoundSource.PLAYERS,
                                            1.0F,
                                            1.3F
                                    );

                                }, AnimationEvent.Side.SERVER),


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
                                                            EffectRegistry.SEPUKKU.get(),
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
                new BasicAttackAnimation(0.12F, 0.21F, 0.2F, 0.30F, 0.53F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AUTO2 = builder.nextAccessor("biped/combat/saber_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.31F, 0.4F, 0.60F, 0.63F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        SABER_AUTO3 = builder.nextAccessor("biped/combat/saber_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.2F, 0.30F, 0.53F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(33F))
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AUTO4 = builder.nextAccessor("biped/combat/saber_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.3f, 0.4f, 1.2f, 0.50f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F)),

                        new AttackAnimation.Phase(0.51f, 0.52f, 0.90f, 1.12f, 1.2f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AUTO5 = builder.nextAccessor("biped/combat/saber_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.4F, 0.50F, 0.7F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AUTO6 = builder.nextAccessor("biped/combat/saber_auto6", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.5F, 0.60F, 0.8F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AIRSLASH = builder.nextAccessor("biped/combat/saber_airslash", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
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
                        new BasicAttackAnimation(0.12F, accessor, biped,
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
                new BasicAttackAnimation(0.12F, 0.21F, 0.40F, 0.58F, 0.87F, DawnDayCollider.KNIFE_DASH, biped.get().head, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25F))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MESSER_DUAL_AUTO3 = builder.nextAccessor("biped/combat/messer_dual_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.8f, 0.92f, 1.3f, 0.92f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                        ,

                        new AttackAnimation.Phase(1.0f, 0.93f, 1.00f, 1.1f, 1.33f, 3.51f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.6F))
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MESSER_ONEHANDED_AUTO1 = builder.nextAccessor("biped/combat/messer_onehanded_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.40F, 0.58F, 0.87F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MESSER_ONEHANDED_AUTO2 = builder.nextAccessor("biped/combat/messer_onehanded_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.40F, 0.58F, 0.87F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MESSER_ONEHANDED_AUTO3 = builder.nextAccessor("biped/combat/messer_onehanded_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.40F, 0.58F, 1.27F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(15F))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AUTO1 = builder.nextAccessor("biped/combat/nailbat_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.49F, 0.76F, 0.87F, DawnDayCollider.BAT_LONGER, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AUTO2 = builder.nextAccessor("biped/combat/nailbat_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.42F, 0.72F, 0.83F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AUTO3 = builder.nextAccessor("biped/combat/nailbat_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
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
                new BasicAttackAnimation(0.12F, 0.26F, 0.55F, 0.83F, 2.39F, DawnDayCollider.BAT_LONGER, biped.get().toolR, accessor, biped)
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
                new BasicAttackAnimation(0.12F, accessor, biped,
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
                new BasicAttackAnimation(0.12F, 0.16F, 0.3F, 0.55F, 0.80F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SAW_AUTO3 = builder.nextAccessor("biped/combat/saw_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.16F, 0.17F, 0.33F, 0.50F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SAW_AUTO4 = builder.nextAccessor("biped/combat/saw_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
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
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.18f, 0.30f, 0.70f, 0.35f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.battlestaff_swing.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.55F)),

                        new AttackAnimation.Phase(0.35f, 0.3f, 0.55f, 0.75f, 0.78f, 1.82f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_ROD.get())
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        BATTLESTAFF_AUTO2 = builder.nextAccessor("biped/combat/battlestaff_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.2f, 0.5f, 1.04f, 0.62f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.15F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get()),

                        new AttackAnimation.Phase(0.61f, 0.3f, 0.75f, 1.0f, 1.04f, 1.98f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BATTLESTAFF_AUTO3 = builder.nextAccessor("biped/combat/battlestaff_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.31F, 0.5F, 0.70F, 0.73F,null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BATTLESTAFF_AUTO4 = builder.nextAccessor("biped/combat/battlestaff_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.08f, 0.15f, 0.9f, 0.20f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.battlestaff_swing.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.21f, 0.21f, 0.23f, 0.25f, 0.9f, 0.3f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2F)),

                        new AttackAnimation.Phase(0.31f, 0.32f, 0.33f, 0.35f, 0.9f, 0.41f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.battlestaff_swing.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F)),

                        new AttackAnimation.Phase(0.41f, 0.45f, 0.5f, 0.9f, 0.9f, 1.9f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F))


                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.7F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BATTLESTAFF_AUTO5 = builder.nextAccessor("biped/combat/battlestaff_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.01f, 0.05f, 0.25f, 1.2f, 0.32f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get()),

                        new AttackAnimation.Phase(0.32f, 0.3f, 0.45f, 0.5f, 1.25f, 1.98f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(5))
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BATTLESTAFF_DASH = builder.nextAccessor("biped/combat/battlestaff_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,

                        new AttackAnimation.Phase(0.0f, 0.1f, 0.1f, 0.18f, 0.9f, 0.3f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_ROD.get()),

                        new AttackAnimation.Phase(0.31f, 0.35f, 0.3f, 0.6f, 0.9f, 2f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
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
                new BasicAttackAnimation(0.12F, 0.3F, 0.25F, 0.52F, 0.75F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.milady_light_sweep.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        MILADY_ONE_HANDED_AUTO_2 = builder.nextAccessor("biped/combat/milady_onehanded_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.25F, 0.5F, 0.78F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_ONE_HANDED_AUTO_3 = builder.nextAccessor("biped/combat/milady_onehanded_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.1F, 0.2F, 0.48F, 0.54F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_ONE_HANDED_AUTO_4 = builder.nextAccessor("biped/combat/milady_onehanded_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.1F, 0.2F, 0.41F, 0.5F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        //the method changes if there has to be more than one phases
        MILADY_ONE_HANDED_AUTO_5 = builder.nextAccessor("biped/combat/milady_onehanded_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
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
                        .addProperty(AttackPhaseProperty.HIT_SOUND, dawnDaySounds.poise_break.get())
                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true) // remove if fault
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 2, 60)), AnimationEvent.Side.SERVER
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
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 25, 60)), AnimationEvent.Side.SERVER
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
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 2, 60)), AnimationEvent.Side.SERVER
                        ))
        );
        SPEARING_STRIKE = builder.nextAccessor("biped/skill/spearing_strike", ac ->
                new AttackAnimation(0.1f, 0.2f, 0.2f, 0.95f, 20f, InteractionHand.MAIN_HAND, DawnDayCollider.HALBERD_INNATE, biped.get().toolR, ac, biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
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
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 2, 60)), AnimationEvent.Side.SERVER
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
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 25, 60)), AnimationEvent.Side.SERVER
                        ))
        );

        POWERFUL_KICK = builder.nextAccessor("biped/skill/powerful_kick", ac ->
                new AttackAnimation(0.1f, 1.0f, 1.0f, 1.16f, 1.1f, InteractionHand.MAIN_HAND, DawnDayCollider.BACKHAND_INNATE, biped.get().rootJoint, ac, biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.3F, 0.85F))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
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

        WILD_STRIKES_DUAL = builder.nextAccessor("biped/skill/wild_strikes_dual", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.4f, 2.9f, 0.41f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1f)),

                        new AttackAnimation.Phase(0.41f, 0.4f, 0.45f, 0.6f, 2.9f, 0.65f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.95f)),


                        new AttackAnimation.Phase(0.85f, 0.85f, 0.9f, 1.1f, 2.9f, 1.15f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BACKHAND_INNATE)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2f)),

                        new AttackAnimation.Phase(1.2f, 1.2f, 1.2f, 1.3f, 1.8f, 20f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BACKHAND_INNATE)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(15f))
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.FINISHER))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.BYPASS_DODGE))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.EXECUTION))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.4f))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
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
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 20, 60)), AnimationEvent.Side.SERVER))
        );

        FURIOUS_CUT = builder.nextAccessor("biped/skill/furious_cut", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.7f, 0.9f, 0.9f, 0.91f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get()),

                        new AttackAnimation.Phase(1.02f, 1.03f, 1.05f, 1.28f, 1.18f, 1.29f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
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
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.battlestaff_swing.get()),

                        new AttackAnimation.Phase(0.22f, 0.35f, 0.4f, 0.45f, 2.9f, 0.36f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FULL)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),


                        new AttackAnimation.Phase(0.37f, 0.4f, 0.5f, 0.55f, 2.9f, 0.6f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FULL)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.battlestaff_swing.get()),

                        new AttackAnimation.Phase(0.6f, 0.62f, 0.65f, 0.7f, 2.9f, 0.8f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FULL)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get()),

                        new AttackAnimation.Phase(0.81f, 0.82f, 0.85f, 0.93f, 2.18f, 0.95f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FULL)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.battlestaff_swing.get())
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
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE),

                        new AttackAnimation.Phase(0.20f, 0.22f, 0.20f, 0.28f, 1.98f, 0.39f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BACKHAND_INNATE)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.25f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG),
                        new AttackAnimation.Phase(0.3f, 0.35f, 0.4f, 0.55f, 1.9f, 5f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BACKHAND_INNATE)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
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
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
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
                new BasicAttackAnimation(0.12F, 0.3F, 0.52F, 0.7F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.milady_light_sweep.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO2 = builder.nextAccessor("biped/combat/milady_twohanded_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.5F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO3 = builder.nextAccessor("biped/combat/milady_twohanded_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.5F, 0.64F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO4 = builder.nextAccessor("biped/combat/milady_twohanded_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.47F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
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
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.4f, 1.29f, 0.5f, InteractionHand.OFF_HAND, biped.get().toolL, null),

                        new AttackAnimation.Phase(0.51f, 0.6f, 0.7f, 1.2f, 1.2f, 2.2f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO2 = builder.nextAccessor("biped/combat/milady_dual_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.58f, 0.7f, 0.8f, 0.8f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.MILADY_DASH),

                        new AttackAnimation.Phase(0.481f, 0.2f, 0.4f, 0.6f, 0.8f, 1.91f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO3 = builder.nextAccessor("biped/combat/milady_dual_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.4f, 1.0f, 0.41f, InteractionHand.OFF_HAND, biped.get().toolL, null),

                        new AttackAnimation.Phase(0.42f, 0.5f, 0.55f, 0.8f, 1.0f, 2.2f, InteractionHand.MAIN_HAND, biped.get().toolR, null))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO4 = builder.nextAccessor("biped/combat/milady_dual_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.55f, 0.8f, 1.3f, 0.8f, InteractionHand.OFF_HAND, biped.get().toolL, null),

                        new AttackAnimation.Phase(0.81f, 0.82f, 0.83f, 1.15f, 1.3f, 2.6f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO5 = builder.nextAccessor("biped/combat/milady_dual_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.2F, 0.3F, 0.4F, 0.82F, DawnDayCollider.MILADY_DASH, biped.get().rootJoint, accessor, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 1.15))
                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
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
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.46f, 0.45f, 0.46f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.47f, 0.48f, 0.5f, 0.85f, 0.8f, 1.9f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO2 = builder.nextAccessor("biped/combat/milady_special_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.6f, 0.6f, 0.6f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.61f, 0.5f, 0.5f, 0.82f, 0.8f, 1.3f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO3 = builder.nextAccessor("biped/combat/milady_special_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.12f, 0.4f, 0.4f, 0.41f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.42f, 0.43f, 0.45f, 0.8f, 0.9f, 2.21f, InteractionHand.MAIN_HAND, biped.get().toolR, null))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO4 = builder.nextAccessor("biped/combat/milady_special_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.26f, 0.5f, 0.5f, 0.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.52f, 0.53f, 0.7f, 1.0f, 1.0f, 1.01f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F)),

                        new AttackAnimation.Phase(1.02f, 1.03f, 1.04f, 1.25f, 1.4f, 2.55f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25))
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO5 = builder.nextAccessor("biped/combat/milady_special_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.5f, 0.5f, 0.51f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get()),

                        new AttackAnimation.Phase(0.52f, 0.53f, 0.55f, 0.9158f, 1.0f, 2.21f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
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
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO2 = builder.nextAccessor("biped/combat/war_sickle_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO3 = builder.nextAccessor("biped/combat/war_sickle_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO4 = builder.nextAccessor("biped/combat/war_sickle_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO5 = builder.nextAccessor("biped/combat/war_sickle_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        WAR_SICKLE_AUTO6 = builder.nextAccessor("biped/combat/war_sickle_auto6", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO1 = builder.nextAccessor("biped/combat/backhand_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO2 = builder.nextAccessor("biped/combat/backhand_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO3 = builder.nextAccessor("biped/combat/backhand_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.30F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1f))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO4 = builder.nextAccessor("biped/combat/backhand_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.30F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.3f))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20))
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO1 = builder.nextAccessor("biped/combat/halbeard_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO2 = builder.nextAccessor("biped/combat/halbeard_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO3 = builder.nextAccessor("biped/combat/halbeard_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_AUTO4 = builder.nextAccessor("biped/combat/halbeard_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
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
                new BasicAttackAnimation(0.12F, 0.3F, 0.3F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_AUTO2 = builder.nextAccessor("biped/combat/sickle_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.3F, 0.62F, 0.75F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8f))
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_AUTO3 = builder.nextAccessor("biped/combat/sickle_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.3F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_AUTO4 = builder.nextAccessor("biped/combat/sickle_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.2F, 0.3F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_DASH = builder.nextAccessor("biped/combat/sickle_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.2F, 0.35F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_AIRSLASH = builder.nextAccessor("biped/combat/sickle_airslash", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.2F, 0.25F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5f))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_DUAL_AUTO1 = builder.nextAccessor("biped/combat/sickle_dual_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.32f, 0.55f, 0.55f, 0.55f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)),

                        new AttackAnimation.Phase(0.56f, 0.6f, 0.75f, 0.85f, 0.88f, 1.9f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_DUAL_AUTO2 = builder.nextAccessor("biped/combat/sickle_dual_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.38F, 0.52F, 0.75F, ColliderPreset.DUAL_SWORD_AIR_SLASH, biped.get().chest, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1f))
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_DUAL_AUTO3 = builder.nextAccessor("biped/combat/sickle_dual_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.38F, 0.52F, 0.75F, ColliderPreset.DUAL_SWORD_AIR_SLASH, biped.get().chest, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4f))
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO1 = builder.nextAccessor("biped/combat/steelaxe_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.3F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.42F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, 0.25F, -2.0F), Armatures.BIPED.get().toolR, 1.1D, 0.55F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO2 = builder.nextAccessor("biped/combat/steelaxe_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.3F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO3 = builder.nextAccessor("biped/combat/steelaxe_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.4F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO4 = builder.nextAccessor("biped/combat/steelaxe_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
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
                new BasicAttackAnimation(0.12F, 0.3F, 0.3F, 0.52F, 0.52F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_ONEHANDED_AUTO2 = builder.nextAccessor("biped/combat/knife_onehanded_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.01f, 0.05f, 0.3f, 0.55f, 0.45f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.46f, 0.47f, 0.45f, 0.65f, 0.85f, 2.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_ONEHANDED_AUTO3 = builder.nextAccessor("biped/combat/knife_onehanded_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.1f, 0.35f, 0.7f, 0.45f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F)),

                        new AttackAnimation.Phase(0.46f, 0.50f, 0.55f, 0.65f, 0.7f, 2.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_ONEHANDED_AUTO4 = builder.nextAccessor("biped/combat/knife_onehanded_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.5F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_AUTO1 = builder.nextAccessor("biped/combat/knife_dual_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.25f, 0.8f, 0.38f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8f)),

                        new AttackAnimation.Phase(0.381f, 0.4f, 0.45f, 0.6f, 0.65f, 2.5f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 1.13)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_AUTO2 = builder.nextAccessor("biped/combat/knife_dual_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.55f, 0.8f, 0.60f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7f)),

                        new AttackAnimation.Phase(0.61f, 0.6f, 0.65f, 0.8f, 0.65f, 1.5f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 1.13)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_DASH = builder.nextAccessor("biped/combat/knife_dual_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.10f, 0.35f, 0.8f, 0.48f, InteractionHand.MAIN_HAND, biped.get().head, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8f)),

                        new AttackAnimation.Phase(0.481f, 0.4f, 0.65f, 0.85f, 0.75f, 1.5f, InteractionHand.OFF_HAND, biped.get().head, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 1.23)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_AIRSLASH = builder.nextAccessor("biped/combat/knife_dual_airslash", (accessor) ->
                new AirSlashAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.10f, 0.35f, 0.5f, 0.48f, InteractionHand.MAIN_HAND, biped.get().head, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AIRSLASH = builder.nextAccessor("biped/combat/backhand_airslash", (accessor) ->
                new AirSlashAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.10f, 0.35f, 0.8f, 0.48f, InteractionHand.MAIN_HAND, biped.get().head, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get()))

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
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3f)),

                        new AttackAnimation.Phase(0.581f, 0.5f, 0.65f, 0.9f, 0.95f, 2.5f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 0.73)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        VITR_AUTO1 = builder.nextAccessor("biped/combat/vit_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.40F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        VITR_AUTO2 = builder.nextAccessor("biped/combat/vit_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.30f, 0.55f, 1.6f, 0.58f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.3f)),

                        new AttackAnimation.Phase(0.8f, 0.8f, 0.85f, 1.15f, 1.6f, 1.2f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                ,

                        new AttackAnimation.Phase(1.2f, 1.2f, 1.45f, 1.75f, 1.6f, 3.5f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 1.23)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,true)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AttackAnimationProperty.NO_GRAVITY_TIME,TimePairList.create(0.2f,1.6f))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false));

        VITR_AUTO3 = builder.nextAccessor("biped/combat/vit_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.4F, 0.7F, 0.88F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        VITR_AUTO4 = builder.nextAccessor("biped/combat/vit_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.39f, 0.56f, 1.6f, 0.7f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8f)),

                        new AttackAnimation.Phase(0.73f, 0.8f, 0.76f, 1.0f, 1.6f, 3.5f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier( 1.4f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        INCISURA_VITREA = builder.nextAccessor("biped/skill/incisura_vitrea", ac ->
                new AttackAnimation(0.1f, 0.2f, 1.1f, 1.4f, 1.6f, InteractionHand.MAIN_HAND, DawnDayCollider.WILDSTRIKES, biped.get().toolR, ac, biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL, false)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 60, 60)), AnimationEvent.Side.SERVER
                        ))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
        );

        VITR_DASH = builder.nextAccessor("biped/combat/vit_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.3F, 0.4F, 0.7F, 0.88F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

    }


}

