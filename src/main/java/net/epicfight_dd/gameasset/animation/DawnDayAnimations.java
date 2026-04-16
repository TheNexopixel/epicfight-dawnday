package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.gameasset.dawnDaySounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import reascer.wom.particle.WOMParticles;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.*;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.types.grappling.GrapplingAttackAnimation;
import yesman.epicfight.api.animation.types.grappling.GrapplingTryAnimation;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.effect.EpicFightMobEffects;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static net.epicfight_dd.api.animation.JointTrack.getJointWithTranslation;

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
    public static AnimationAccessor<StaticAnimation> EVIL_ODACHI_IDLE;
    public static AnimationAccessor<StaticAnimation> BAT_IDLE;

    public static AnimationAccessor<StaticAnimation> MILADY_ONEHANDED_RUN;

    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO2;

    public static AnimationAccessor<BasicAttackAnimation> BAT_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> BAT_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> BAT_AUTO3;
    public static AnimationAccessor<DashAttackAnimation> BAT_DASH;
    public static AnimationAccessor<AirSlashAnimation> BAT_AIRSLASH;

    public static AnimationAccessor<BasicAttackAnimation> BATTLESTAFF_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> BATTLESTAFF_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> BATTLESTAFF_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> BATTLESTAFF_AUTO4;
    public static AnimationAccessor<BasicAttackAnimation> BATTLESTAFF_AUTO5;
    public static AnimationAccessor<DashAttackAnimation> BATTLESTAFF_DASH;
    public static AnimationAccessor<AirSlashAnimation> BATTLESTAFF_AIRSLASH;

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
    public static AnimationAccessor<AttackAnimation> EVIL_ODACHI_BEAAAMMMM;
    public static AnimationAccessor<AttackAnimation> BRUTAL_DASH;
    public static AnimationAccessor<AttackAnimation> GROUNDSLAM;

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
    public static AnimationAccessor<DashAttackAnimation> BACKHAND_DASH;
    public static AnimationAccessor<AirSlashAnimation> BACKHAND_AIRSLASH;

    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> HALBERD_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> HALBERD_DASH;

    public static AnimationAccessor<StaticAnimation> EVIL_ODACHI_WALK;
    public static AnimationAccessor<StaticAnimation> EVIL_ODACHI_RUN;
    public static AnimationAccessor<StaticAnimation> EVIL_ODACHI_JUMP;
    public static AnimationAccessor<StaticAnimation> EVIL_ODACHI_GUARD;
    public static AnimationAccessor<GuardAnimation> EVIL_ODACHI_PARRY1;
    public static AnimationAccessor<GuardAnimation> EVIL_ODACHI_PARRY2;
    public static AnimationAccessor<GuardAnimation> EVIL_ODACHI_GUARD_HIT;
    public static AnimationAccessor<LongHitAnimation> EVIL_ODACHI_NEUTRALIZED;
    public static AnimationAccessor<BasicAttackAnimation> EVIL_ODACHI_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> EVIL_ODACHI_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> EVIL_ODACHI_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> EVIL_ODACHI_AUTO4;
    public static AnimationAccessor<DashAttackAnimation> EVIL_ODACHI_DASH;
    public static AnimationAccessor<AirSlashAnimation> EVIL_ODACHI_AIRSLASH;
    public static AnimationAccessor<AttackAnimation> EVIL_ODACHI_COUNTER;
    public static AnimationAccessor<AttackAnimation> EVIL_ODACHI_BATTOJUTSO;
    public static AnimationAccessor<ActionAnimation> EVIL_ODACHI_OVERHEADSLASH_CHARGE;
    public static AnimationAccessor<AttackAnimation> EVIL_ODACHI_OVERHEADSLASH_RELEASE;

    public static AnimationAccessor<ActionAnimation> TCH_I_MISSED;
    public static AnimationAccessor<LongHitAnimation> PLS_NOOOO_DONT_KEBAB_MEEE;
    public static AnimationAccessor<GrapplingAttackAnimation> GET_KEBABed_MuAHAHAHA;
    public static AnimationAccessor<GrapplingTryAnimation> IM_GONNA_KEBAB_YOUUUU;

    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;


        MILADY_ONEHANDED_RUN = builder.nextAccessor("biped/living/milady_onehanded_run", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        EVIL_ODACHI_JUMP = builder.nextAccessor("biped/living/evil_odachi_jump", ac ->
                new StaticAnimation(0.12F,false,ac, biped));

        EVIL_ODACHI_GUARD_HIT = builder.nextAccessor("biped/living/evil_odachi_guard_hit", ac ->
                new GuardAnimation(0.12F,ac, biped));

        EVIL_ODACHI_PARRY1 = builder.nextAccessor("biped/living/evil_odachi_parry1", ac ->
                new GuardAnimation(0.12F,ac, biped));

        EVIL_ODACHI_PARRY2 = builder.nextAccessor("biped/living/evil_odachi_parry2", ac ->
                new GuardAnimation(0.12F,ac, biped));

        EVIL_ODACHI_NEUTRALIZED = builder.nextAccessor("biped/living/evil_odachi_neutralize", ac ->
                new LongHitAnimation(0.12F,ac, biped)
                        .addEvents(


                                //BUZZ
                                AnimationEvent.InTimeEvent.create(0.01f, (e,s,p)->
                                                e.getOriginal().level().playSound(
                                                        (Player) e.getOriginal(),
                                                        e.getOriginal(),
                                                        dawnDaySounds.guard_break.get(),
                                                        SoundSource.PLAYERS,
                                                        100, 1.0F
                                                )

                                        , AnimationEvent.Side.CLIENT))
                        .addEvents(
                                AnimationEvent.InPeriodEvent.create(0.07f,0.1f, (e,s,p)->{
                                            var entity = e.getOriginal();
                                            int numParticles = 5;
                                            for (int i = 0; i < numParticles; i++) {
                                                if (entity == null) return;
                                                float L = -0.1F;
                                                float R = 0.1F;
                                                double xOffset = 0;
                                                double yOffset = 0;
                                                double zOffset = 0;
                                                Vec3 basePos = getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(0F, -1F, -0.3F), Armatures.BIPED.get().handR);
                                                List<Vec3> positions = new ArrayList<>();
                                                positions.add(getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(0F, 0.6F, 0F), Armatures.BIPED.get().handR));
                                                for (Vec3 pos : positions) {
                                                    if (pos != null) {
                                                        Vec3 ovalPos = pos.add(xOffset, yOffset, zOffset);
                                                        Particle particle = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.SMOKE, ovalPos.x, ovalPos.y, ovalPos.z, entity.getDeltaMovement().x, 0.052F, entity.getDeltaMovement().z);
                                                        if (particle != null) {
                                                            particle.setLifetime(7);
                                                        }
                                                    }
                                                    if (basePos != null) {
                                                        Particle particle1 = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.SMOKE, basePos.x, basePos.y, basePos.z, entity.getDeltaMovement().x, 0.02F, entity.getDeltaMovement().z);
                                                        Particle particle2 = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.SMOKE, basePos.x, basePos.y + 0.26F, basePos.z, entity.getDeltaMovement().x, 0.012F, entity.getDeltaMovement().z);
                                                        if (particle1 != null) {
                                                            particle1.scale(0.92F);
                                                            particle1.setLifetime(13);
                                                        }
                                                        if (particle2 != null) {
                                                            particle2.scale(0.96F);
                                                            particle2.setLifetime(3);
                                                        }
                                                    }

                                                    e.getOriginal().level().addParticle(
                                                            ParticleTypes.SMOKE,
                                                            entity.getX(),
                                                            entity.getY(),
                                                            entity.getZ(),
                                                            0.0, 0.0, -0.1);}

                                            }
                                        }

                                        , AnimationEvent.Side.CLIENT))


        );

        BAT_IDLE = builder.nextAccessor("biped/living/nailbat_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        EVIL_ODACHI_RUN = builder.nextAccessor("biped/living/evil_odachi_run", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        EVIL_ODACHI_IDLE = builder.nextAccessor("biped/living/evil_odachi_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        TPOSE = builder.nextAccessor("biped/living/rest_pose", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        SABER_IDLE = builder.nextAccessor("biped/living/saber_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        KNIFE_ONEHANDED_IDLE = builder.nextAccessor("biped/living/knife_onehanded_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        KNIFE_DUAL_IDLE = builder.nextAccessor("biped/living/knife_dual_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        STEELAXE_IDLE = builder.nextAccessor("biped/living/steelaxe_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        SICKLE_DUAL_IDLE = builder.nextAccessor("biped/living/sickle_dual_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        SICKLE_IDLE = builder.nextAccessor("biped/living/sickle_idle", ac ->
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

        BATTLESTAFF_IDLE = builder.nextAccessor("biped/living/battlestaff_idle", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        EVIL_ODACHI_WALK = builder.nextAccessor("biped/living/evil_odachi_walk", ac ->
                new StaticAnimation(0.12F,true,ac, biped));

        EVIL_ODACHI_GUARD = builder.nextAccessor("biped/living/evil_odachi_guard", ac ->
                new StaticAnimation(0.25F,true,ac, biped));

        SABER_AUTO1 = builder.nextAccessor("biped/combat/saber_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.2F, 0.30F, 0.53F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.1F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SABER_AUTO2 = builder.nextAccessor("biped/combat/saber_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.31F, 0.4F, 0.60F, 0.63F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.0F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AUTO1 = builder.nextAccessor("biped/combat/nailbat_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.49F, 0.76F, 0.87F, DawnDayCollider.BAT_LONGER, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.0F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AUTO2 = builder.nextAccessor("biped/combat/nailbat_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.21F, 0.42F, 0.72F, 0.83F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.0F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AUTO3 = builder.nextAccessor("biped/combat/nailbat_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.4f, 0.6f, 1.9f, 0.70f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.4F)),


                        new AttackAnimation.Phase(0.71f, 0.8f, 1.0f, 1.25f, 1.9f,1.26f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.0F)),

                        new AttackAnimation.Phase(1.27f, 1.3f, 1.57f, 1.82f, 2.2f,3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BAT_LONGER)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(20f))
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.6F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BAT_AIRSLASH = builder.nextAccessor("biped/combat/nailbat_airslash" , ac ->
                new AirSlashAnimation(  0.12F, 0.25f ,0.52f, 0.9f, DawnDayCollider.BAT_LONGER, biped.get().toolR, ac , biped)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );
        BAT_DASH = builder.nextAccessor("biped/combat/nailbat_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.21F, 0.22F, 0.42F, 0.63F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.2F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        BATTLESTAFF_AUTO1 = builder.nextAccessor("biped/combat/battlestaff_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.18f, 0.30f, 0.70f, 0.35f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FRONT)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.battlestaff_swing.get())
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.55F)),

                new AttackAnimation.Phase(0.35f, 0.3f, 0.55f, 0.75f, 0.78f, 1.82f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FRONT)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.6F))
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_ROD.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE,true));



        BATTLESTAFF_AUTO2 = builder.nextAccessor("biped/combat/battlestaff_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.2f, 0.5f, 1.04f, 0.62f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FRONT)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.15F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get()),

                        new AttackAnimation.Phase(0.61f, 0.3f, 0.75f, 1.0f, 1.04f, 1.98f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FRONT)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE,true));

        BATTLESTAFF_AUTO3 = builder.nextAccessor("biped/combat/battlestaff_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.31F, 0.5F, 0.70F, 0.73F, DawnDayCollider.BATTLESTAFF_BACK, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT.get())
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.2F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BATTLESTAFF_AUTO4 = builder.nextAccessor("biped/combat/battlestaff_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.08f, 0.15f, 0.9f, 0.20f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_BACK)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.battlestaff_swing.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.21f, 0.21f, 0.23f, 0.25f, 0.9f,0.3f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.2F)),

                        new AttackAnimation.Phase(0.31f, 0.32f, 0.33f, 0.35f, 0.9f,0.41f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.battlestaff_swing.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.4F)),

                        new AttackAnimation.Phase(0.41f, 0.45f, 0.5f, 0.9f, 0.9f,1.9f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.5F))



                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.7F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BATTLESTAFF_AUTO5 = builder.nextAccessor("biped/combat/battlestaff_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.01f, 0.05f, 0.25f, 1.2f, 0.32f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FRONT)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.9F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.SHORT)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT.get()),

                        new AttackAnimation.Phase(0.32f, 0.3f, 0.45f, 0.5f, 1.25f, 1.98f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_BACK)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.4F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.adder(5))
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.AIR_BURST)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE,true));

        BATTLESTAFF_DASH = builder.nextAccessor("biped/combat/battlestaff_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,

                new AttackAnimation.Phase(0.0f, 0.1f, 0.1f, 0.18f, 0.9f,0.3f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_BACK)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.4f))
                        .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_ROD.get()),

                new AttackAnimation.Phase(0.31f, 0.35f, 0.3f, 0.6f, 0.9f,2f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_BACK))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.4f))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        BATTLESTAFF_AIRSLASH = builder.nextAccessor("biped/combat/battlestaff_airslash" , ac ->
                        new AirSlashAnimation(  0.12F, 0.25f ,0.46f, 0.6f, DawnDayCollider.BAT_LONGER, biped.get().toolR, ac , biped)
                                .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                                .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));



            MILADY_ONE_HANDED_AUTO_1 = builder.nextAccessor("biped/combat/milady_onehanded_auto1", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.3F, 0.25F, 0.52F, 0.75F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.milady_light_sweep.get())
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
                            new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.4f, 1.5f, 0.48f, InteractionHand.MAIN_HAND, biped.get().toolR,null),

                            new AttackAnimation.Phase(0.481f, 0.5f, 0.6f, 1.0f, 1.3f,2.5f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                    .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.23)))
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            milady_onehanded_dash = builder.nextAccessor("biped/combat/milady_onehanded_dash", (accessor) ->
                    new DashAttackAnimation(0.12F, 0.0f, 0.1f, 0.3f, 0.8F, null, biped.get().toolR, accessor, biped)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

            MILADY_AIR_SLASH = builder.nextAccessor("biped/combat/milady_onehanded_airslash" , ac ->
                    new AirSlashAnimation(  0.12F, 0.1f ,0.3f, 0.8f,null, biped.get().toolR, ac , biped));


        SKULL_RUPTURE = builder.nextAccessor("biped/skill/skull_rupture", ac->
                new AttackAnimation(0.1f,0.5f,0.7f,1.1f,4f,InteractionHand.MAIN_HAND, DawnDayCollider.BATTLESTAFF_FRONT,biped.get().toolR,ac,biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, dawnDaySounds.poise_break.get())
                        .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true) // remove if fault
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e,s,p)->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(),2,60)), AnimationEvent.Side.SERVER
                        ))
        );

            MILADY_KNUCKLE_INNATE = builder.nextAccessor("biped/skill/milady/milady_special_knuckle_strike", ac->
                   new AttackAnimation(0.1f,0.2f,0.5f,0.6f,20f,InteractionHand.MAIN_HAND, ColliderPreset.HEAD,biped.get().elbowR,ac,biped)
                           .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                           .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_BIG.get())
                           .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false) // remove if fault
                           .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                           .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                           .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                   (e,s,p)->
                                           e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(),2,60)), AnimationEvent.Side.SERVER
                           ))
            );
        PIERCING_FANG = builder.nextAccessor("biped/skill/piercing_fang", ac->
                new AttackAnimation(0.1f,0.2f,0.5f,1.1f,20f,InteractionHand.MAIN_HAND, DawnDayCollider.LIGHT_GREATSWORD,biped.get().toolR,ac,biped)
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
                new AttackAnimation(0.1f,0.2f,0.2f,0.95f,20f,InteractionHand.MAIN_HAND, DawnDayCollider.HALBERD_INNATE,biped.get().toolR,ac,biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true) // remove if fault
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.36F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(2.0F, 0.25F, -5.0F), Armatures.BIPED.get().toolR, 1.5D, 0.55F))
                        .addEvents(AnimationEvent.InTimeEvent.create(0.85F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(-0.0F, 0.25F, -1.0F), Armatures.BIPED.get().toolR, 2.5D, 1.3F))
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e,s,p)->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(),2,60)), AnimationEvent.Side.SERVER
                        ))
        );

        QUICK_RUSH = builder.nextAccessor("biped/skill/quick_rush", ac->
                new AttackAnimation(0.1f,0.2f,0.25f,0.6f,0.8f,InteractionHand.MAIN_HAND, ColliderPreset.SWORD,biped.get().toolR,ac,biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true) // remove if fault
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e,s,p)->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(),25,60)), AnimationEvent.Side.SERVER
                        ))
        );

        BRUTAL_DASH = builder.nextAccessor("biped/skill/brutal_dash", ac->
                new AttackAnimation(0.1f,0.1f,0.25f,0.35f,0.5f,InteractionHand.MAIN_HAND, DawnDayCollider.BAT_BACK,biped.get().toolR,ac,biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_ROD.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.AIR_BURST)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                        .addEvents(StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e,s,p)->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(),20,60)), AnimationEvent.Side.SERVER))
        );

        FURIOUS_CUT = builder.nextAccessor("biped/skill/furious_cut", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.7f, 0.9f, 0.9f, 0.91f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLADE),

                        new AttackAnimation.Phase(0.91f, 0.92f, 1.05f, 1.28f, 1.18f,1.29f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                        new AttackAnimation.Phase(1.29f, 1.55f, 1.65f, 1.9f, 2.9f, 20f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLADE)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.9f))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.0F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
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

                        new AttackAnimation.Phase(0.81f, 0.82f, 0.85f, 0.93f, 2.18f,0.95f, InteractionHand.MAIN_HAND, biped.get().toolR, DawnDayCollider.BATTLESTAFF_FULL)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.battlestaff_swing.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG),

                        new AttackAnimation.Phase(0.95f, 1.1f, 1.22f, 1.3f, 1.8f, 20f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.GROUNDSLAM)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.FINISHER))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.BYPASS_DODGE))
                                .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.EXECUTION))
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(9.6F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.9f))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.9F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
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
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.EVISCERATE),

                        new AttackAnimation.Phase(0.20f, 0.22f, 0.20f, 0.28f, 1.98f,0.39f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BACKHAND_INNATE)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.25f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG),
                        new AttackAnimation.Phase(0.3f, 0.35f, 0.4f, 0.55f, 1.9f, 5f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BACKHAND_INNATE)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5f))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.0F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false));

        IMPAILING_THRUST = builder.nextAccessor("biped/skill/impailing_thrust", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.12f, 0.4f, 1.9f, 0.51f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.EVISCERATE),

                        new AttackAnimation.Phase(0.52f, 0.52f, 0.8f, 1.18f, 1.88f,3.89f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5f))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.0F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false));

        WHIRLWIND = builder.nextAccessor("biped/skill/whirlwind", (accessor) ->
                new AttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.2f, 0.3f, 5.31f, 0.32f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.HOLD)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.1f)),
                        new AttackAnimation.Phase(0.33f, 0.34f, 0.35f, 0.36f, 5.38f,0.39f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.16f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.40f, 0.44f, 0.45f, 0.46f, 5.48f,0.49f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.50f, 0.54f, 0.55f, 0.56f, 5.58f,0.59f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.2f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.60f, 0.64f, 0.65f, 0.66f, 5.68f,0.69f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.70f, 0.74f, 0.75f, 0.76f, 5.78f,0.79f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.80f, 0.84f, 0.85f, 0.86f, 5.88f,0.89f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(0.90f, 0.94f, 0.95f, 0.96f, 5.98f,0.99f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.2f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.00f, 1.04f, 1.05f, 1.06f, 5.08f,1.09f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.10f, 1.14f, 1.15f, 1.16f, 5.18f,1.19f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.2f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.20f, 1.24f, 1.25f, 1.26f, 5.28f,1.29f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.35f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.30f, 1.34f, 1.35f, 1.36f, 5.38f,1.39f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.1f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.40f, 1.44f, 1.45f, 1.46f, 5.48f,1.49f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.3f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),
                        new AttackAnimation.Phase(1.50f, 1.54f, 1.55f, 1.56f, 5.58f,1.59f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND1)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.4f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH.get()),


                        new AttackAnimation.Phase(1.89f, 1.95f, 2.15f, 2.2f, 5.9f, 20f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.WHIRLWIND2)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(2.5f))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLADE)
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false));

        MILADY_TWOHANDED_AUTO1 = builder.nextAccessor("biped/combat/milady_twohanded_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.milady_light_sweep.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO2 = builder.nextAccessor("biped/combat/milady_twohanded_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO3 = builder.nextAccessor("biped/combat/milady_twohanded_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AUTO4 = builder.nextAccessor("biped/combat/milady_twohanded_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.47F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.2f))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_DASH = builder.nextAccessor("biped/combat/milady_twohanded_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.01F, 0.02F, 0.3F, 0.6F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_TWOHANDED_AIRSLASH = builder.nextAccessor("biped/combat/milady_twohanded_airslash", (ac) ->
                new AirSlashAnimation(  0.12F, 0.1f ,0.3f, 0.8f,null, biped.get().toolR, ac , biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO1 = builder.nextAccessor("biped/combat/milady_dual_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.4f, 0.49f, 0.5f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.51f, 0.6f, 0.7f, 1.2f, 1.2f,2.2f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO2 = builder.nextAccessor("biped/combat/milady_dual_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.2f, 0.58f, 0.7f, 0.7f, 0.8f, InteractionHand.OFF_HAND, biped.get().rootJoint, DawnDayCollider.MILADY_DASH),

                        new AttackAnimation.Phase(0.481f, 0.2f, 0.4f, 0.6f, 0.7f,1.91f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO3 = builder.nextAccessor("biped/combat/milady_dual_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.4f, 1.0f, 0.41f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.42f, 0.5f, 0.55f, 0.8f, 1.0f,2.2f, InteractionHand.MAIN_HAND, biped.get().toolR,null))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO4 = builder.nextAccessor("biped/combat/milady_dual_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.55f, 0.8f, 1.2f, 0.8f, InteractionHand.OFF_HAND, biped.get().toolL,null),

                        new AttackAnimation.Phase(0.81f, 0.82f, 0.83f, 1.15f, 1.2f,2.6f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AUTO5 = builder.nextAccessor("biped/combat/milady_dual_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.2F, 0.3F, 0.4F, 0.82F, DawnDayCollider.MILADY_DASH, biped.get().rootJoint, accessor, biped)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.15))
                        .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_DASH = builder.nextAccessor("biped/combat/milady_dual_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.3F, 0.14F, 0.27F, 0.7F, DawnDayCollider.MILADY_DASH, biped.get().rootJoint, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DUAL_AIRSLASH = builder.nextAccessor("biped/combat/milady_dual_airslash" , ac ->
                new AirSlashAnimation(  0.12F, 0.2f ,0.4f, 0.6f,null, biped.get().toolR, ac , biped));

        MILADY_SPECIAL_AUTO1 = builder.nextAccessor("biped/combat/milady_special_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.46f, 0.45f, 0.46f, InteractionHand.OFF_HAND, biped.get().toolL,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.47f, 0.48f, 0.5f, 0.85f, 0.8f,1.9f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO2 = builder.nextAccessor("biped/combat/milady_special_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.6f, 0.6f, 0.6f, InteractionHand.OFF_HAND, biped.get().toolL,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.61f, 0.5f, 0.5f, 0.82f, 0.8f,1.3f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO3 = builder.nextAccessor("biped/combat/milady_special_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.12f, 0.4f, 0.4f, 0.41f, InteractionHand.OFF_HAND, biped.get().toolL,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.42f, 0.43f, 0.45f, 0.8f, 0.9f,2.21f, InteractionHand.MAIN_HAND, biped.get().toolR,null))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO4 = builder.nextAccessor("biped/combat/milady_special_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.26f, 0.5f, 0.5f, 0.51f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.5F)),

                        new AttackAnimation.Phase(0.52f, 0.53f, 0.7f, 1.0f, 1.0f,1.01f, InteractionHand.OFF_HAND, biped.get().toolL,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.4F)),

                        new AttackAnimation.Phase(1.02f, 1.03f, 1.04f, 1.25f, 1.4f, 2.55f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(25))
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.6F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get()))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_AUTO5 = builder.nextAccessor("biped/combat/milady_special_auto5", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.5f, 0.5f, 0.51f, InteractionHand.OFF_HAND, biped.get().toolL,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_light_slash.get()),

                        new AttackAnimation.Phase(0.52f, 0.53f, 0.55f, 0.9158f, 1.0f,2.21f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.Milady_heavy_slash.get())
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(2))
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_SPECIAL_DASH = builder.nextAccessor("biped/combat/milady_special_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.1F, 0.20F, 0.42F, 0.6F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.5F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F));


        POLE_AXE_AUTO1 = builder.nextAccessor("biped/combat/pole_axe_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        POLE_AXE_AUTO2 = builder.nextAccessor("biped/combat/pole_axe_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        POLE_AXE_AUTO3 = builder.nextAccessor("biped/combat/pole_axe_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        POLE_AXE_AUTO4 = builder.nextAccessor("biped/combat/pole_axe_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.4f, 0.5f, 0.48f, InteractionHand.MAIN_HAND, biped.get().toolR,null),

                        new AttackAnimation.Phase(0.481f, 0.5f, 0.6f, 1.0f, 1.3f,1.5f, InteractionHand.MAIN_HAND, biped.get().toolR,null))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        POLE_AXE_AIRSLASH = builder.nextAccessor("biped/combat/pole_axe_airslash" , ac ->
                new AirSlashAnimation(  0.12F, 0.2f ,0.4f, 0.6f,null, biped.get().toolR, ac , biped));

        POLE_AXE_DASH = builder.nextAccessor("biped/combat/war_sickle_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.2F, 0.20F, 0.42F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


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

        HEAVY_AXE_AUTO1 = builder.nextAccessor("biped/combat/heavy_axe_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HEAVY_AXE_AUTO2 = builder.nextAccessor("biped/combat/heavy_axe_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HEAVY_AXE_AUTO3 = builder.nextAccessor("biped/combat/heavy_axe_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HEAVY_AXE_AUTO4 = builder.nextAccessor("biped/combat/heavy_axe_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.35F, 0.72F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HEAVY_AXE_DASH = builder.nextAccessor("biped/combat/heavy_axe_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.2F, 0.20F, 0.42F, 0.82F, null, biped.get().toolR, accessor, biped)
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
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.1f))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(5))
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AUTO4 = builder.nextAccessor("biped/combat/backhand_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.30F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.EVISCERATE)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.3f))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder(20))
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())

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
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLADE)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.2F))
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.multiplier(1.6F))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.6F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(-0.0F, 0.25F, -1.0F), Armatures.BIPED.get().toolR, 1.1D, 0.55F))

                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        HALBERD_DASH = builder.nextAccessor("biped/combat/halbeard_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.0f, 0.1f, 0.3f, 1.2F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        GET_KEBABed_MuAHAHAHA = builder.nextAccessor("biped/skill/grab/grab_execute",ac-> new GrapplingAttackAnimation(
                0.52f, 0.85f,ac,biped)
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.EXECUTION, DamageTypeTags.BYPASSES_ARMOR))
              //  .addProperty(ActionAnimationProperty.COORD_UPDATE_TIME, TimePairList.create(0.0F, 0.5F))
        );

        PLS_NOOOO_DONT_KEBAB_MEEE = builder.nextAccessor("biped/skill/grab/grab_hit", ac->
                new LongHitAnimation(0.01f,ac,biped)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,true));

       TCH_I_MISSED = builder.nextAccessor("biped/skill/grab/grab_fail", accessor->
               new ActionAnimation(0.0f,0.85f,accessor,biped));

       IM_GONNA_KEBAB_YOUUUU = builder.nextAccessor("biped/skill/grab/grab_try",ac->
                new GrapplingTryAnimation(0.05f,
                        0.1f,
                        0.18f,
                        0.28f,
                        0.3667f,
                        InteractionHand.MAIN_HAND,
                        ColliderPreset.HEADBUTT_RAVAGER,biped.get().leftHandJoint(),
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
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.8f))
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.SHORT)
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
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.5f))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_DUAL_AUTO1 = builder.nextAccessor("biped/combat/sickle_dual_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.32f, 0.55f, 0.55f, 0.55f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.8F)),

                        new AttackAnimation.Phase(0.56f, 0.6f, 0.75f, 0.85f, 0.88f, 1.9f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.8F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE,true));

        SICKLE_DUAL_AUTO2 = builder.nextAccessor("biped/combat/sickle_dual_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.38F, 0.52F, 0.75F, ColliderPreset.DUAL_SWORD_AIR_SLASH, biped.get().chest, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.1f))
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.SHORT)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        SICKLE_DUAL_AUTO3 = builder.nextAccessor("biped/combat/sickle_dual_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.38F, 0.52F, 0.75F, ColliderPreset.DUAL_SWORD_AIR_SLASH, biped.get().chest, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.4f))
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO1 = builder.nextAccessor("biped/combat/steelaxe_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.3F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.42F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, 0.25F, -2.0F), Armatures.BIPED.get().toolR, 1.1D, 0.55F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO2 = builder.nextAccessor("biped/combat/steelaxe_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.3F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO3 = builder.nextAccessor("biped/combat/steelaxe_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.4F, 0.52F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        STEELAXE_AUTO4 = builder.nextAccessor("biped/combat/steelaxe_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.1f, 0.3f, 0.42f, 0.55f, 0.55f, 0.55f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.8F)),

                        new AttackAnimation.Phase(0.56f, 0.6f, 1.05f, 1.15f, 1.28f, 2.5f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(1.6F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,false)
                        .addEvents(AnimationEvent.InTimeEvent.create(1.1F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, 0.25F, -1.0F), Armatures.BIPED.get().toolR, 1.1D, 0.65F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE,true));

        KNIFE_ONEHANDED_AUTO1 = builder.nextAccessor("biped/combat/knife_onehanded_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.3F, 0.52F, 0.52F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_ONEHANDED_AUTO2 = builder.nextAccessor("biped/combat/knife_onehanded_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.01f, 0.05f, 0.3f, 0.55f, 0.45f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.46f, 0.47f, 0.45f, 0.65f, 0.85f, 2.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.6F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_ONEHANDED_AUTO3 = builder.nextAccessor("biped/combat/knife_onehanded_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.1f, 0.35f, 0.7f, 0.45f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.6F)),

                        new AttackAnimation.Phase(0.46f, 0.50f, 0.55f, 0.65f, 0.7f, 2.8f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.8F)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_ONEHANDED_AUTO4 = builder.nextAccessor("biped/combat/knife_onehanded_auto4", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.5F, 0.62F, 0.82F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_light_slash.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_AUTO1 = builder.nextAccessor("biped/combat/knife_dual_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.2f, 0.25f, 0.8f, 0.38f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.8f)),

                        new AttackAnimation.Phase(0.381f, 0.4f, 0.45f, 0.6f, 0.65f,2.5f, InteractionHand.OFF_HAND, biped.get().toolL,null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_light_slash.get())
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.13)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_AUTO2 = builder.nextAccessor("biped/combat/knife_dual_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.55f, 0.8f, 0.60f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.7f)),

                        new AttackAnimation.Phase(0.61f, 0.6f, 0.65f, 0.8f, 0.65f,1.5f, InteractionHand.OFF_HAND, biped.get().toolL,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.13)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_DASH = builder.nextAccessor("biped/combat/knife_dual_dash", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.10f, 0.35f, 0.8f, 0.48f, InteractionHand.MAIN_HAND, biped.get().head, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.8f)),

                        new AttackAnimation.Phase(0.481f, 0.4f, 0.65f, 0.85f, 0.75f,1.5f, InteractionHand.OFF_HAND, biped.get().head, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_heavy_slash.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.23)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        KNIFE_DUAL_AIRSLASH = builder.nextAccessor("biped/combat/knife_dual_airslash", (accessor) ->
                new AirSlashAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.10f, 0.35f, 0.5f, 0.48f, InteractionHand.MAIN_HAND, biped.get().head, DawnDayCollider.KNIFE_DASH)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_AIRSLASH = builder.nextAccessor("biped/combat/backhand_airslash", (accessor) ->
                new AirSlashAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.10f, 0.35f, 0.8f, 0.48f, InteractionHand.MAIN_HAND, biped.get().head,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get()))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        BACKHAND_DASH = builder.nextAccessor("biped/combat/backhand_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.0f, 0.25f, 0.4f, 0.6F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.90))
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        MILADY_DASH_NEW = builder.nextAccessor("biped/combat/milady_dash_new", (accessor) ->
                new DashAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.30f, 0.55f, 0.8f, 0.58f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_dual_slash.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(0.3f)),

                        new AttackAnimation.Phase(0.581f, 0.5f, 0.65f, 0.9f, 0.95f,2.5f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.SWING_SOUND,dawnDaySounds.Milady_heavy_slash.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.73)))

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        EVIL_ODACHI_AUTO1 = builder.nextAccessor("biped/combat/evil_odachi_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.45f, 1.6f, 0.46f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.6))
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLADE),

                        new AttackAnimation.Phase(0.59f, 0.6f, 0.68f, 0.9f, 1.6f,1.0f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.90))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,ValueModifier.adder( 40))
                                .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        ,
                        new AttackAnimation.Phase(1.02f, 1.2f, 1.3f, 1.50f, 1.6f,2.7f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.6))
                                .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                )

                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        EVIL_ODACHI_AUTO2 = builder.nextAccessor("biped/combat/evil_odachi_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.0f, 0.25f, 0.4f, 0.6F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.90))
                        .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        EVIL_ODACHI_AUTO3 = builder.nextAccessor("biped/combat/evil_odachi_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.0f, 0.58f, 0.8f, 1.0F, DawnDayCollider.EVIL_TACHI_BACK, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.70))
                        .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_ROD.get())
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.AIR_BURST)
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.adder( 10))
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.HOLD)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        EVIL_ODACHI_AUTO4 = builder.nextAccessor("biped/combat/evil_odachi_auto4", (accessor) ->
                        new BasicAttackAnimation(0.12F, accessor, biped,

                                new AttackAnimation.Phase(0.0f, 0.1f, 0.42f, 0.6f, 1.5f, 0.62f, InteractionHand.MAIN_HAND, biped.get().legR, DawnDayCollider.EVIL_TACHI_SPECIAL)
                                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.HOLD)
                                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.adder(4))
                                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLUNT)
                                        .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_BIG.get())
                                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.05))
                                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get()),

                                new AttackAnimation.Phase(0.6f, 0.65f, 0.70f, 0.9f, 1.5f, 0.9f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.85))
                                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_HIT.get()),

                                new AttackAnimation.Phase(1.02f, 1.0f, 1.02f, 1.55f, 1.7f, 5.48f, InteractionHand.MAIN_HAND, biped.get().toolR,null)
                                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 1.60))
                                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                                        .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.EVISCERATE.get())

                        )
                                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                                .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                                .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        EVIL_ODACHI_DASH = builder.nextAccessor("biped/combat/evil_odachi_dash", (accessor) ->
                new DashAttackAnimation(0.12F, 0.0f, 0.25f, 0.4f, 0.6F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier((float) 0.90))
                        .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.EVISCERATE)
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));


        EVIL_ODACHI_AIRSLASH = builder.nextAccessor("biped/combat/evil_odachi_airslash", (accessor) ->
                new AirSlashAnimation(0.12F, 0.05f, 0.4f, 0.9F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0f,0.50f))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));

        EVIL_ODACHI_OVERHEADSLASH_CHARGE = builder.nextAccessor("biped/skill/evil_odachi_overheadslash_charge", ac->
                new ActionAnimation( 0.1f, ac, Armatures.BIPED)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE,false)
                        .addEvents(

                                // SFX
                                AnimationEvent.InTimeEvent.create(0.15f, (e,s,p)->
                                                e.getOriginal().level().playSound(
                                                        (Player) e.getOriginal(),
                                                        e.getOriginal(),
                                                        SoundEvents.WITHER_AMBIENT,
                                                        SoundSource.PLAYERS,
                                                        100, 1.0F
                                                )

                                        , AnimationEvent.Side.CLIENT))
        );
        EVIL_ODACHI_OVERHEADSLASH_RELEASE = builder.nextAccessor("biped/skill/evil_odachi_overheadslash_release", ac->
                new AttackAnimation(0.2f,0.058f,0.2f,0.28f,0.5f,InteractionHand.MAIN_HAND, DawnDayCollider.WHIRLWIND2,biped.get().rootJoint,ac,biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, SoundEvents.WITHER_HURT)
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.adder( 7))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier( 1.30f))
                        .addProperty(AttackPhaseProperty.PARTICLE,WOMParticles.ANTITHEUS_PUNCH_HIT)
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                        .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE))
                        .addState(EntityState.TURNING_LOCKED,true)
                        .addState(EntityState.LOCKON_ROTATE,true)
                        .addProperty(AttackAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                        .addEvents(

                                //BUZZ
                                AnimationEvent.InTimeEvent.create(0.21f, (e,s,p)->
                                                e.getOriginal().level().playSound(
                                                        (Player) e.getOriginal(),
                                                        e.getOriginal(),
                                                        SoundEvents.WITHER_BREAK_BLOCK,
                                                        SoundSource.PLAYERS,
                                                        100, 1.1F
                                                )

                                        , AnimationEvent.Side.CLIENT)
                        )
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        0.21F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -1.0F), Armatures.BIPED.get().rootJoint, 5.5D, 11.3F))
        );

        EVIL_ODACHI_COUNTER = builder.nextAccessor("biped/skill/evil_odachi_counter", ac->
                new AttackAnimation(0.1f,0.058f,0.60f,0.61f,0.9f,InteractionHand.MAIN_HAND, DawnDayCollider.EVIL_TACHI_COUNTER,biped.get().rootJoint,ac,biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_AMBIENT)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, SoundEvents.WITHER_HURT)
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.adder( 8))
                        .addProperty(AttackPhaseProperty.PARTICLE,WOMParticles.ANTITHEUS_PUNCH_HIT)
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                        .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE))
                        .addState(EntityState.TURNING_LOCKED,true)
                        .addState(EntityState.LOCKON_ROTATE,true)
                        .addProperty(AttackAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                        .addEvents(


                                //BUZZ
                                AnimationEvent.InTimeEvent.create(0.15f, (e,s,p)->
                                                e.getOriginal().level().playSound(
                                                        (Player) e.getOriginal(),
                                                        e.getOriginal(),
                                                        EpicFightSounds.BUZZ.get(),
                                                        SoundSource.PLAYERS,
                                                        100, 1.1F
                                                )

                                        , AnimationEvent.Side.CLIENT))
        );

        EVIL_ODACHI_BATTOJUTSO = builder.nextAccessor("biped/skill/evil_odachi_battojutso", ac->
                        new AttackAnimation(0.1f,0.2f,0.34f,0.81f,0.9f,InteractionHand.MAIN_HAND, DawnDayCollider.BACKHAND_INNATE,biped.get().rootJoint,ac,biped)

                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, SoundEvents.FIREWORK_ROCKET_BLAST)
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.adder( 8))
                                .addProperty(AttackPhaseProperty.PARTICLE,WOMParticles.ANTITHEUS_PUNCH_HIT)
                                .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE))
                                .addState(EntityState.TURNING_LOCKED,true)
                                .addState(EntityState.LOCKON_ROTATE,true)
                                .addProperty(AttackAnimationProperty.FIXED_HEAD_ROTATION, true)
                                .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                                .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                                .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                                .addEvents(
                                        AnimationEvent.InTimeEvent.create(0.3f, (e,s,p)->{
                                                var entity = e.getOriginal();
                                                        e.getOriginal().level().addParticle(
                                                                        ParticleTypes.FLASH,
                                                                        entity.getX(),
                                                                        entity.getY() + 1.0,
                                                                        entity.getZ(),
                                                                        0.0, 0.0, 0.0
                                                                );
                                                }



                                                , AnimationEvent.Side.CLIENT)
                                )
                                .addEvents(
                                        AnimationEvent.InPeriodEvent.create(0.27f,0.9f, (e,s,p)->{
                                                    var entity = e.getOriginal();
                                                    int numParticles = 3;
                                            for (int i = 0; i < numParticles; i++) {
                                                if (entity == null) return;

                                                RandomSource random = RandomSource.create();
                                                float L = -0.1F;
                                                float R = 0.1F;
                                                double xOffset = (random.nextDouble() - 0.3) * 0.3;
                                                double yOffset = (random.nextDouble() - random.nextDouble()) * 0.3D;
                                                double zOffset = (random.nextDouble() - 0.3) * 0.3;
                                                Vec3 basePos = getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(0F, -1F, -0.3F), Armatures.BIPED.get().rootJoint);
                                                List<Vec3> positions = new ArrayList<>();
                                                positions.add(getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(L, 0F, 0.6F), Armatures.BIPED.get().head));
                                                positions.add(getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(R, 0F, 0.6F), Armatures.BIPED.get().head));
                                                positions.add(getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(L, 0.06F, 0.1F), Armatures.BIPED.get().chest));
                                                positions.add(getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(R, 0.06F, 0.1F), Armatures.BIPED.get().chest));
                                                positions.add(getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(0F, 0.6F, 0F), Armatures.BIPED.get().handL));
                                                positions.add(getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(0F, 0.6F, 0F), Armatures.BIPED.get().handR));
                                                positions.add(getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(0F, 0.2F, 0.2F), Armatures.BIPED.get().legL));
                                                positions.add(getJointWithTranslation(Minecraft.getInstance().player, entity, new Vec3f(0F, 0.2F, 0.2F), Armatures.BIPED.get().legR));
                                                for (Vec3 pos : positions) {
                                                    if (pos != null) {
                                                        Vec3 ovalPos = pos.add(xOffset, yOffset, zOffset);
                                                        Particle particle = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.SMOKE, ovalPos.x, ovalPos.y, ovalPos.z, entity.getDeltaMovement().x, 0.052F, entity.getDeltaMovement().z);
                                                        if (particle != null) {
                                                            particle.setLifetime(7);
                                                        }
                                                    }
                                                    if (basePos != null) {
                                                    Particle particle1 = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.SMOKE, basePos.x, basePos.y, basePos.z, entity.getDeltaMovement().x, 0.02F, entity.getDeltaMovement().z);
                                                    Particle particle2 = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.SMOKE, basePos.x, basePos.y + 0.26F, basePos.z, entity.getDeltaMovement().x, 0.012F, entity.getDeltaMovement().z);
                                                        if (particle1 != null) {
                                                            particle1.scale(0.92F);
                                                            particle1.setLifetime(13);
                                                        }
                                                        if (particle2 != null) {
                                                            particle2.scale(0.96F);
                                                            particle2.setLifetime(3);
                                                        }
                                                    }

                                                    e.getOriginal().level().addParticle(
                                                            ParticleTypes.SMOKE,
                                                            entity.getX(),
                                                            entity.getY(),
                                                            entity.getZ(),
                                                            0.0, 0.0, -0.1);}

                                                }
                                        }

                                        , AnimationEvent.Side.CLIENT))

        );


        EVIL_ODACHI_BEAAAMMMM = builder.nextAccessor("biped/skill/evil_beam", ac->
                new AttackAnimation(0.1f,0.658f,0.728f,0.9f,3.2f,InteractionHand.MAIN_HAND, DawnDayCollider.EVIL_TACHI_RAY,biped.get().rootJoint,ac,biped)

                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                        .addProperty(AttackPhaseProperty.PARTICLE,EpicFightParticles.AIR_BURST)
                        .addProperty(AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN)
                        .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE))
                        .addState(EntityState.TURNING_LOCKED,true)
                        .addState(EntityState.LOCKON_ROTATE,true)
                        .addProperty(AttackAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE,true) // remove if fault
                        .addProperty(AttackAnimationProperty.MOVE_VERTICAL,false)
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AttackAnimationProperty.CANCELABLE_MOVE,false)
                        .addEvents(
                                //BUZZ
                                AnimationEvent.InTimeEvent.create(0.15f, (e,s,p)->
                                        e.getOriginal().level().playSound(
                                                (Player) e.getOriginal(),
                                                e.getOriginal(),
                                                EpicFightSounds.BUZZ.get(),
                                                SoundSource.PLAYERS,
                                                100, 0.9F
                                        )


                                        , AnimationEvent.Side.CLIENT),


                                //BEAM + LASER SOUND
                                AnimationEvent.InTimeEvent.create(0.66F, (entitypatch, self, params) -> {
                                    LivingEntity entity = entitypatch.getOriginal();

                                    if (entitypatch instanceof PlayerPatch) {
                                        entity.level().playSound(
                                                (Player) entity,
                                                entity,
                                                EpicFightSounds.LASER_BLAST.get(),
                                                SoundSource.PLAYERS,
                                                100, 0.9F
                                        );
                                    }

                                    // Bone matrix only for world spawn position of particle
                                    OpenMatrix4f originMatrix = entitypatch.getArmature().getBoundTransformFor(
                                            entitypatch.getAnimator().getPose(-0.5F),
                                            Armatures.BIPED.get().toolR
                                    );

                                    originMatrix.translate(new Vec3f(0.0F, 0.0F, 0.3F));

                                    OpenMatrix4f yawCorrection = new OpenMatrix4f().rotate(
                                            (float) -Math.toRadians(entitypatch.getYRot() + 180.0F),
                                            new Vec3f(0.0F, 1.0F, 0.0F)
                                    );
                                    OpenMatrix4f.mul(yawCorrection, originMatrix, originMatrix);

                                    double worldX = originMatrix.m30 + entity.getX();
                                    double worldY = originMatrix.m31 + entity.getY();
                                    double worldZ = originMatrix.m32 + entity.getZ();

                                    //get direction from yBodyRot
                                    float yawRad = (float) Math.toRadians(entity.yBodyRot);
                                    float boneForwardX = (float) -Math.sin(yawRad);
                                    float boneForwardY =  0.0F;
                                    float boneForwardZ = (float) Math.cos(yawRad);


                                    float rightX = (float) Math.cos(yawRad);
                                    float rightY =  0.0F;
                                    float rightZ = (float) Math.sin(yawRad);

                                    float upX = 0.0F;
                                    float upY = 1.0F;
                                    float upZ = 0.0F;

                                    float beamRange = 20.0F;
                                    int particleCount = 80;
                                    Random rand = new Random();

                                    for (int i = 0; i < particleCount; i++) {
                                        double theta = Math.PI * 2 * rand.nextDouble();

                                        float radialX = (float)(rightX * Math.cos(theta) + upX * Math.sin(theta));
                                        float radialY = (float)(rightY * Math.cos(theta) + upY * Math.sin(theta));
                                        float radialZ = (float)(rightZ * Math.cos(theta) + upZ * Math.sin(theta));

                                        float speed = 0.80F;
                                        float forwardDrift = 0.09F;

                                        entity.level().addParticle(
                                                new DustParticleOptions(new Vector3f(0.0F, 0.0F, 0.0F), 1.5F),
                                                worldX, worldY, worldZ,
                                                radialX * speed + boneForwardX * forwardDrift,
                                                radialY * speed + boneForwardY * forwardDrift,
                                                radialZ * speed + boneForwardZ * forwardDrift
                                        );

                                    }

                                  Particle particle = Minecraft.getInstance().particleEngine.createParticle(
                                          WOMParticles.BLACK_LASER.get(), worldX, worldY, worldZ,
                                          worldX + boneForwardX * beamRange,
                                          worldY + boneForwardY * beamRange,
                                          worldZ + boneForwardZ * beamRange
                                  );



//                                    entity.level().addParticle(
//                                            EpicFightParticles.LASER.get(),
//                                            worldX, worldY, worldZ,
//                                            worldX + boneForwardX * beamRange,
//                                            worldY + boneForwardY * beamRange,
//                                            worldZ + boneForwardZ * beamRange
//                                    );

                                }, AnimationEvent.Side.CLIENT))

                        .addEvents(
                                StaticAnimationProperty.ON_BEGIN_EVENTS,
                                AnimationEvent.SimpleEvent.create(
                                (e,s,p)->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(),4,2)), AnimationEvent.Side.SERVER
                                )));
    }

}

