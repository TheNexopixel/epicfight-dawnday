package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.api.animation.AnimUtils;
import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.gameasset.dawnDaySounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import org.joml.Vector3f;
import reascer.wom.animation.WomAnimationProperty;
import reascer.wom.animation.attacks.BasicMultipleAttackAnimation;
import reascer.wom.particle.WOMParticles;
import reascer.wom.world.damagesources.WOMDamageType;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.effect.EpicFightMobEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static net.epicfight_dd.api.animation.JointTrack.getJointWithTranslation;


public class AdditionalAnimations {

    public static AnimationManager.AnimationAccessor<StaticAnimation> EVIL_ODACHI_WALK;
    public static AnimationManager.AnimationAccessor<StaticAnimation> EVIL_ODACHI_IDLE;
    public static AnimationManager.AnimationAccessor<StaticAnimation> EVIL_ODACHI_RUN;
    public static AnimationManager.AnimationAccessor<StaticAnimation> EVIL_ODACHI_JUMP;
    public static AnimationManager.AnimationAccessor<StaticAnimation> EVIL_ODACHI_KNEEL;
    public static AnimationManager.AnimationAccessor<StaticAnimation> EVIL_ODACHI_SNEAK;
    public static AnimationManager.AnimationAccessor<StaticAnimation> EVIL_ODACHI_GUARD;
    public static AnimationManager.AnimationAccessor<GuardAnimation> EVIL_ODACHI_PARRY1;
    public static AnimationManager.AnimationAccessor<GuardAnimation> EVIL_ODACHI_PARRY2;
    public static AnimationManager.AnimationAccessor<GuardAnimation> EVIL_ODACHI_GUARD_HIT;
    public static AnimationManager.AnimationAccessor<ActionAnimation> EVIL_ODACHI_NEUTRALIZED;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> EVIL_ODACHI_AUTO1;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> EVIL_ODACHI_AUTO2;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> EVIL_ODACHI_AUTO3;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> EVIL_ODACHI_AUTO4;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> EVIL_ODACHI_AUTO5;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> EVIL_ODACHI_DASH;
    public static AnimationManager.AnimationAccessor<AirSlashAnimation> EVIL_ODACHI_AIRSLASH;
    public static AnimationManager.AnimationAccessor<AttackAnimation> EVIL_ODACHI_BEAAAMMMM;
    public static AnimationManager.AnimationAccessor<AttackAnimation> EVIL_ODACHI_COUNTER;
    public static AnimationManager.AnimationAccessor<AttackAnimation> EVIL_ODACHI_BATTOJUTSO;
    public static AnimationManager.AnimationAccessor<ActionAnimation> EVIL_ODACHI_OVERHEADSLASH_CHARGE;
    public static AnimationManager.AnimationAccessor<AttackAnimation> EVIL_ODACHI_OVERHEADSLASH_RELEASE;

    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> GREATAXE_DUAL_AUTO1;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> GREATAXE_DUAL_AUTO2;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> GREATAXE_DUAL_AUTO3;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> GREATAXE_DUAL_AUTO4;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> GREATAXE_DUAL_DASH;
    public static AnimationManager.AnimationAccessor<AirSlashAnimation> GREATAXE_AIRSLASH;
    public static AnimationManager.AnimationAccessor<AttackAnimation> ANNIHILATE;

    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;

        EVIL_ODACHI_WALK = builder.nextAccessor("biped/living/evil_odachi_walk", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        EVIL_ODACHI_IDLE = builder.nextAccessor("biped/living/evil_odachi_idle", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        EVIL_ODACHI_RUN = builder.nextAccessor("biped/living/evil_odachi_run", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        EVIL_ODACHI_GUARD = builder.nextAccessor("biped/living/evil_odachi_guard", ac ->
                new StaticAnimation(0.25F, true, ac, biped));

        EVIL_ODACHI_JUMP = builder.nextAccessor("biped/living/evil_odachi_jump", ac ->
                new StaticAnimation(0.12F, false, ac, biped));

        EVIL_ODACHI_KNEEL = builder.nextAccessor("biped/living/evil_odachi_kneel", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        EVIL_ODACHI_SNEAK = builder.nextAccessor("biped/living/evil_odachi_sneak", ac ->
                new StaticAnimation(0.12F, true, ac, biped));

        EVIL_ODACHI_GUARD_HIT = builder.nextAccessor("biped/living/evil_odachi_guard_hit", ac ->
                new GuardAnimation(0.12F, ac, biped));

        EVIL_ODACHI_PARRY1 = builder.nextAccessor("biped/living/evil_odachi_parry1", ac ->
                new GuardAnimation(0.12F, ac, biped));

        EVIL_ODACHI_PARRY2 = builder.nextAccessor("biped/living/evil_odachi_parry2", ac ->
                new GuardAnimation(0.12F, ac, biped));

        EVIL_ODACHI_NEUTRALIZED = builder.nextAccessor("biped/living/evil_odachi_neutralize", ac ->
                new ActionAnimation(0.12F, ac, biped)
                        .addEvents(AnimationProperty.StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().playSound(dawnDaySounds.guard_break.get(),100,1), AnimationEvent.Side.CLIENT
                        ))
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(1.3f, (e, s, p) ->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        SoundEvents.WITHER_SHOOT,
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        1.0F
                                                )


                                        , AnimationEvent.Side.SERVER))
                        .addEvents(
                                AnimationEvent.InPeriodEvent.create(0.07f, 0.8f, (e, s, p) -> {
                                            var entity = e.getOriginal();
                                            int numParticles = 10;
                                            for (int i = 0; i < numParticles; i++) {
                                                if (entity == null) return;
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
                                                            0.0, 0.0, -0.1);
                                                }

                                            }
                                        }

                                        , AnimationEvent.Side.CLIENT))


        );
        EVIL_ODACHI_AUTO1 = builder.nextAccessor("biped/combat/evil_odachi_auto1", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.3f, 0.45f, 1.6f, 0.65f,
                                InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.4f)),

                        new AttackAnimation.Phase(0.67f, 0.6f, 0.68f, 0.9f, 1.6f, 1.0f,
                                InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.90f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(40))

                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.4f))

                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL),

                        new AttackAnimation.Phase(1.02f, 1.2f, 1.3f, 1.50f, 1.6f, 2.7f,
                                InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6f))

                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                )
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true)
        );


        EVIL_ODACHI_AUTO3 = builder.nextAccessor("biped/combat/evil_odachi_auto3", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, 0.0f, 0.58f, 0.8f, 1.0F,
                        DawnDayCollider.EVIL_TACHI_BACK, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.70f))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_ROD.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)

                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1.2f))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true)
        );


        EVIL_ODACHI_AUTO4 = builder.nextAccessor("biped/combat/evil_odachi_auto4", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.42f, 0.6f, 1.2f, 0.62f,
                                InteractionHand.MAIN_HAND, biped.get().legR, DawnDayCollider.EVIL_TACHI_SPECIAL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(0.8f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.05f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get()),

                        new AttackAnimation.Phase(0.6f, 0.65f, 0.70f, 0.9f, 1.2f, 0.9f,
                                InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.85f))

                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.5f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE),

                        new AttackAnimation.Phase(1.02f, 1.0f, 1.02f, 1.55f, 1.2f, 5.48f,
                                InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.60f))

                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(4))
                )
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.1F, 1.7F))
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        EVIL_ODACHI_AUTO5 = builder.nextAccessor("biped/combat/evil_odachi_auto5", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.1f, 0.32f, 0.53f, 1.5f, 0.62f,
                                InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT),

                        new AttackAnimation.Phase(0.9f, 1.15f, 0.95f, 1.2f, 1.8f, 1.7f,
                                InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())

                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.9f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE),

                        new AttackAnimation.Phase(1.92f, 1.9f, 1.72f, 2.1f, 2.1f, 5.48f,
                                InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.20f))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(4))
                )
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 2.3F))
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true)
        );

        EVIL_ODACHI_AUTO2 = builder.nextAccessor("biped/combat/evil_odachi_auto2", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, 0.0f, 0.25f, 0.4f, 0.6F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 0.90))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.5f))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));


        EVIL_ODACHI_DASH = builder.nextAccessor("biped/combat/evil_odachi_dash", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, 0.0f, 0.25f, 0.4f, 0.6F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier((float) 0.70))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.6f))
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.RESET_PLAYER_COMBO_COUNTER, false)
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(WomAnimationProperty.CAN_SPAM,false)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));


        EVIL_ODACHI_AIRSLASH = builder.nextAccessor("biped/combat/evil_odachi_airslash", (accessor) ->
                new AirSlashAnimation(0.12F, 0.05f, 0.4f, 0.9F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.3F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0f, 0.60f))
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true)
                        .addEvents(
                                AnimUtils.LaunchEnemyAirSlash(0.15f)
                        )
        );

        EVIL_ODACHI_OVERHEADSLASH_CHARGE = builder.nextAccessor("biped/skill/evil_odachi_overheadslash_charge", ac ->
                new ActionAnimation(0.1f, ac, Armatures.BIPED)
                        .newConditionalTimePair((entitypatch) -> entitypatch.getOriginal().isUsingItem() ? 0 : 1, 0.0F, Float.MAX_VALUE)
                        .addConditionalState(0, EntityState.UPDATE_LIVING_MOTION, false)
                        .addConditionalState(1, EntityState.UPDATE_LIVING_MOTION, true)
                        .newTimePair(0.0F, Float.MAX_VALUE).addStateRemoveOld(EntityState.CAN_SWITCH_HAND_ITEM, false)
                        .addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(

                                // SFX
                                AnimationEvent.InTimeEvent.create(0.15f, (e, s, p) ->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        SoundEvents.WITHER_AMBIENT,
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        1.0F
                                                )

                                        , AnimationEvent.Side.SERVER))
        );

        EVIL_ODACHI_OVERHEADSLASH_RELEASE = builder.nextAccessor("biped/skill/evil_odachi_overheadslash_release", ac ->
                new AttackAnimation(0.2f, 0.058f, 0.2f, 0.28f, 0.5f, InteractionHand.MAIN_HAND, DawnDayCollider.WHIRLWIND2, biped.get().rootJoint, ac, biped)

                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundEvents.WITHER_HURT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(7))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, WOMParticles.ANTITHEUS_PUNCH_HIT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.FALL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE, WOMDamageType.BLACKOUT))
                        .addState(EntityState.TURNING_LOCKED, true)
                        .addState(EntityState.LOCKON_ROTATE, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL, false)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(

                                //BUZZ
                                AnimationEvent.InTimeEvent.create(0.21f, (e, s, p) ->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        SoundEvents.WITHER_BREAK_BLOCK,
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        1.1F
                                                )

                                        , AnimationEvent.Side.SERVER)
                        )
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        0.21F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -1.0F), Armatures.BIPED.get().rootJoint, 5.5D, 11.3F))
        );

        EVIL_ODACHI_COUNTER = builder.nextAccessor("biped/skill/evil_odachi_counter", ac ->
                new AttackAnimation(0.1f, 0.058f, 0.60f, 0.61f, 0.9f, InteractionHand.MAIN_HAND, DawnDayCollider.EVIL_TACHI_COUNTER, biped.get().rootJoint, ac, biped)

                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_AMBIENT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundEvents.WITHER_HURT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(8))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, WOMParticles.ANTITHEUS_PUNCH_HIT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE))
                        .addState(EntityState.TURNING_LOCKED, true)
                        .addState(EntityState.LOCKON_ROTATE, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL, false)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(


                                //BUZZ
                                AnimationEvent.InTimeEvent.create(0.15f, (e, s, p) ->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        EpicFightSounds.BUZZ.get(),
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        1.1F
                                                )

                                        , AnimationEvent.Side.SERVER))
        );

        EVIL_ODACHI_BATTOJUTSO = builder.nextAccessor("biped/skill/evil_odachi_battojutso", ac ->
                new AttackAnimation(0.1f, 0.2f, 0.20f, 0.71f, 0.8f, InteractionHand.MAIN_HAND, DawnDayCollider.BACKHAND_INNATE, biped.get().rootJoint, ac, biped)

                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundEvents.FIREWORK_ROCKET_BLAST)
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(8))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20))
                        .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(15))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.2f))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, WOMParticles.ANTITHEUS_PUNCH_HIT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE))
                        .addState(EntityState.TURNING_LOCKED, true)
                        .addState(EntityState.LOCKON_ROTATE, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.15f, (e, s, p) -> {
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
                                AnimationEvent.InTimeEvent.create(0.14f, (e, s, p) -> {
                                            var entity = e.getOriginal();
                                            e.getOriginal().level().addParticle(
                                                    ParticleTypes.EXPLOSION,
                                                    entity.getX(),
                                                    entity.getY() + 1.0,
                                                    entity.getZ(),
                                                    0.0, 0.0, 0.0
                                            );
                                        }
                                        , AnimationEvent.Side.CLIENT)
                        )
                        .addEvents(
                                AnimationEvent.InPeriodEvent.create(0.12f, 0.8f, (e, s, p) -> {
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
                                                            0.0, 0.0, -0.1);
                                                }

                                            }
                                        }

                                        , AnimationEvent.Side.CLIENT))

        );


        EVIL_ODACHI_BEAAAMMMM = builder.nextAccessor("biped/skill/evil_beam", ac ->
                new AttackAnimation(0.1f, 0.658f, 0.728f, 0.9f, 1.2f, InteractionHand.MAIN_HAND, DawnDayCollider.EVIL_TACHI_RAY, biped.get().rootJoint, ac, biped)

                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundEvents.WITHER_BREAK_BLOCK)
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, WOMParticles.ANTITHEUS_PUNCH_HIT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.BYPASS_DODGE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.UNBLOCKALBE, DamageTypeTags.BYPASSES_RESISTANCE))
                        .addState(EntityState.TURNING_LOCKED, true)
                        .addState(EntityState.LOCKON_ROTATE, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true) // remove if fault
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL, false)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(
                                //BUZZ
                                AnimationEvent.InTimeEvent.create(0.15f, (e, s, p) ->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        EpicFightSounds.BUZZ.get(),
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        0.9F
                                                )


                                        , AnimationEvent.Side.SERVER),

                                AnimationEvent.InTimeEvent.create(0.66f, (e, s, p) ->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        EpicFightSounds.LASER_BLAST.get(),
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        0.9F
                                                )


                                        , AnimationEvent.Side.SERVER),

                                AnimationEvent.InTimeEvent.create(0.66F, (entitypatch, self, params) -> {
                                    LivingEntity entity = entitypatch.getOriginal();

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
                                    float boneForwardY = 0.0F;
                                    float boneForwardZ = (float) Math.cos(yawRad);


                                    float rightX = (float) Math.cos(yawRad);
                                    float rightY = 0.0F;
                                    float rightZ = (float) Math.sin(yawRad);

                                    float upX = 0.0F;
                                    float upY = 1.0F;
                                    float upZ = 0.0F;

                                    float beamRange = 20.0F;
                                    int particleCount = 80;
                                    Random rand = new Random();

                                    for (int i = 0; i < particleCount; i++) {
                                        double theta = Math.PI * 2 * rand.nextDouble();

                                        float radialX = (float) (rightX * Math.cos(theta) + upX * Math.sin(theta));
                                        float radialY = (float) (rightY * Math.cos(theta) + upY * Math.sin(theta));
                                        float radialZ = (float) (rightZ * Math.cos(theta) + upZ * Math.sin(theta));

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

                                    if (ModList.get().isLoaded("wom")) {
                                        Particle particle = Minecraft.getInstance().particleEngine.createParticle(
                                                WOMParticles.BLACK_LASER.get(), worldX, worldY, worldZ,
                                                worldX + boneForwardX * beamRange,
                                                worldY + boneForwardY * beamRange,
                                                worldZ + boneForwardZ * beamRange
                                        );
                                    }


//                                    entity.level().addParticle(
//                                            EpicFightParticles.LASER.get(),
//                                            worldX, worldY, worldZ,
//                                            worldX + boneForwardX * beamRange,
//                                            worldY + boneForwardY * beamRange,
//                                            worldZ + boneForwardZ * beamRange
//                                    );

                                }, AnimationEvent.Side.CLIENT))

                        .addEvents(
                                AnimationProperty.StaticAnimationProperty.ON_BEGIN_EVENTS,
                                AnimationEvent.SimpleEvent.create(
                                        (e, s, p) ->
                                                e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 4, 2)), AnimationEvent.Side.SERVER
                                )));


        GREATAXE_DUAL_AUTO1 = builder.nextAccessor("biped/combat/greataxe_dual_auto1", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.4f, 0.6f, 1.2f, 0.7f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.SHORT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.9F))
                        ,

                        new AttackAnimation.Phase(0.8f, 0.9f, 1.00f, 1.3f, 1.23f, 3.51f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.80F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1.3F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                )

                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        GREATAXE_DUAL_AUTO2 = builder.nextAccessor("biped/combat/greataxe_dual_auto2", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.20f, 0.35f, 0.5f, 1.7f, 0.55f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.9F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.85F))
                        ,

                        new AttackAnimation.Phase(0.6f, 0.6f, 0.80f, 1.0f, 1.73f, 1.05f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.9F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.SHORT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(0F))
                        ,
                        new AttackAnimation.Phase(1.1f, 1.2f, 1.35f, 1.55f, 1.73f, 3.51f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BONE_SAW_AUTO4)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.92F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                )

                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        1.45F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -2.0F), Armatures.BIPED.get().rootJoint,0.5D, 1.0F))
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        GREATAXE_DUAL_AUTO3 = builder.nextAccessor("biped/combat/greataxe_dual_auto3", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.50f, 0.67f, 0.72f, 1.1f, 0.73f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.2F))
                        ,

                        new AttackAnimation.Phase(0.74f, 0.75f, 0.76f, 0.85f, 1.13f, 0.86f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.2F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F)),

                        new AttackAnimation.Phase(0.87f, 0.87f, 0.88f, 1.03f, 1.23f, 3.51f, InteractionHand.MAIN_HAND, biped.get().rootJoint, DawnDayCollider.BONE_SAW_AUTO4)
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.LONG)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(6F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                )

                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        0.75F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -4.0F), Armatures.BIPED.get().rootJoint, 2.0D, 2.0F))
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        GREATAXE_DUAL_AUTO4 = builder.nextAccessor("biped/combat/greataxe_dual_auto4", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, 0.71F, 0.91F, 1.13F, 1.87F, DawnDayCollider.BONE_SAW_AUTO4, biped.get().rootJoint, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.NONE)
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(6F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10F))
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        0.95F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(-0.0F, 0.25F, -3.0F), Armatures.BIPED.get().rootJoint, 3.0D, 4.0F))
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        GREATAXE_DUAL_DASH = builder.nextAccessor("biped/combat/greataxe_dash", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.50f, 0.47f, 0.52f, 1.3f, 0.53f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.SHORT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.8F))
                        ,

                        new AttackAnimation.Phase(0.54f, 0.55f, 0.56f, 0.75f, 1.3f, 2.86f, InteractionHand.OFF_HAND, biped.get().toolL, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.SHORT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.7F))
                )

                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        GREATAXE_AIRSLASH = builder.nextAccessor("biped/combat/greataxe_scissors", (accessor) ->
                new AirSlashAnimation(0.12F, 0.65F, 0.7F, 1.27F, DawnDayCollider.BACKHAND_INNATE, biped.get().rootJoint, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 0.55F))
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        ANNIHILATE = builder.nextAccessor("biped/skill/annihilate", ac ->
                new AttackAnimation(0.1f, 1.1f, 1.3f, 1.5f, 20f, InteractionHand.MAIN_HAND, DawnDayCollider.BONE_SAW_AUTO4, biped.get().rootJoint, ac, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.AttackAnimationProperty.CANCELABLE_MOVE, false)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.5F, 1.2F))
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(
                                        1.4F,
                                        Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                        AnimationEvent.Side.CLIENT
                                ).params(new Vec3f(2.0F, 0.25F, 5.0F), Armatures.BIPED.get().rootJoint, 3.5D, 3.5F))
                        .addEvents(AnimationProperty.StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->
                                        e.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 15, 60 )), AnimationEvent.Side.SERVER
                        ))
        );



    }
}
