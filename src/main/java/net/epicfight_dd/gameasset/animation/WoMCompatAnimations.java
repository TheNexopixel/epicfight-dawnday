package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.gameasset.DawnDaySounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import reascer.wom.animation.attacks.BasicMultipleAttackAnimation;
import reascer.wom.world.damagesources.WOMDamageType;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;
import yesman.epicfight.world.damagesource.StunType;

import javax.swing.plaf.basic.BasicArrowButton;
import java.util.Set;

public class WoMCompatAnimations {

    // HALBERD

    public static AnimationManager.AnimationAccessor<StaticAnimation> HALBERD_DFB_WINDUP;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> HALBERD_DFB_RELEASE;

    // HOOKCLAWS
    public static AnimationManager.AnimationAccessor<StaticAnimation> HOOKCLAWS_DFB_WINDUP;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> HOOKCLAWS_DFB_RELEASE;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> HOOKCLAWS_COUNTER;

    // IRON FIST
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> IRON_FIST_GUARD_COUNTER;

    // NIGHTINGALE

    public static AnimationManager.AnimationAccessor<StaticAnimation> NIGHTINGALE_DFB_WINDUP;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> NIGHTINGALE_DFB_RELEASE;



    // POLEBLADE
    public static AnimationManager.AnimationAccessor<StaticAnimation> POLEBLADE_BUSTER_WINDUP;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> POLEBLADE_BUSTER_RELEASE;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> POLEBLADE_COUNTER;

    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;

        HOOKCLAWS_DFB_WINDUP = builder.nextAccessor("biped/skill/hookclaws_dfb_windup", ac ->
                new StaticAnimation(0.3F, false, ac, biped)
                        .newConditionalTimePair((entitypatch) -> entitypatch.getOriginal().isUsingItem() ? 0 : 1, 0.0F, Float.MAX_VALUE)
                        .addConditionalState(0, EntityState.UPDATE_LIVING_MOTION, false)
                        .addConditionalState(1, EntityState.UPDATE_LIVING_MOTION, true)
                        .newTimePair(0.0F, Float.MAX_VALUE).addStateRemoveOld(EntityState.CAN_SWITCH_HAND_ITEM, false)
                        .addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
        );

        NIGHTINGALE_DFB_WINDUP = builder.nextAccessor("biped/skill/nightingale_dfb_windup", ac ->
                new StaticAnimation(0.8F, false, ac, biped)
                        .newConditionalTimePair((entitypatch) -> entitypatch.getOriginal().isUsingItem() ? 0 : 1, 0.0F, Float.MAX_VALUE)
                        .addConditionalState(0, EntityState.UPDATE_LIVING_MOTION, false)
                        .addConditionalState(1, EntityState.UPDATE_LIVING_MOTION, true)
                        .newTimePair(0.0F, Float.MAX_VALUE).addStateRemoveOld(EntityState.CAN_SWITCH_HAND_ITEM, false)
                        .addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
        );
        NIGHTINGALE_DFB_RELEASE = builder.nextAccessor("biped/skill/nightingale_dfb_release", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, 0.05F, 0.1F, 0.3F, 0.50F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE, WOMDamageType.BLACKOUT))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addState(EntityState.TURNING_LOCKED, true)
                        .addState(EntityState.LOCKON_ROTATE, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));

        HALBERD_DFB_WINDUP = builder.nextAccessor("biped/skill/halberd_dfb_windup", ac ->
                new StaticAnimation(0.8F, false, ac, biped)
                        .newConditionalTimePair((entitypatch) -> entitypatch.getOriginal().isUsingItem() ? 0 : 1, 0.0F, Float.MAX_VALUE)
                        .addConditionalState(0, EntityState.UPDATE_LIVING_MOTION, false)
                        .addConditionalState(1, EntityState.UPDATE_LIVING_MOTION, true)
                        .newTimePair(0.0F, Float.MAX_VALUE).addStateRemoveOld(EntityState.CAN_SWITCH_HAND_ITEM, false)
                        .addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
        );

        HALBERD_DFB_RELEASE = builder.nextAccessor("biped/skill/halberd_dfb_release", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, 0.05F, 0.23F, 0.42F, 0.60F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE, WOMDamageType.BLACKOUT))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addState(EntityState.TURNING_LOCKED, true)
                        .addState(EntityState.LOCKON_ROTATE, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));

        HOOKCLAWS_DFB_RELEASE = builder.nextAccessor("biped/skill/hookclaws_dfb_release", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, 0.05F, 0.23F, 0.42F, 0.60F, DawnDayCollider.HOOKCLAWS_SPECIAL, biped.get().rootJoint, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.6F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(50F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE, WOMDamageType.BLACKOUT))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addState(EntityState.TURNING_LOCKED, true)
                        .addState(EntityState.LOCKON_ROTATE, true)
                        // .addProperty(AnimationProperty.AttackAnimationProperty.RESET_PLAYER_COMBO_COUNTER,false)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));

        HOOKCLAWS_COUNTER = builder.nextAccessor("biped/skill/hookclaws_counter", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, 0.21F, 0.38F, 0.5F, 0.83F, null, biped.get().toolL, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1.0F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.NONE)
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));

        IRON_FIST_GUARD_COUNTER = builder.nextAccessor("biped/skill/iron_fist_guard_counter", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, accessor, biped,
                        new AttackAnimation.Phase(0.0f, 0.10f, 0.3f, 0.4f, 1.2f, 0.50f, InteractionHand.MAIN_HAND, biped.get().kneeR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT),

                        new AttackAnimation.Phase(0.51f, 0.52f, 0.60f, 0.75f, 1.2f, 3.51f, InteractionHand.MAIN_HAND, biped.get().toolR, null)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE))

                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, false)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));


        POLEBLADE_BUSTER_WINDUP = builder.nextAccessor("biped/skill/poleblade_buster_windup", ac ->
                new StaticAnimation(0.12F, false, ac, biped)
                        .newConditionalTimePair((entitypatch) -> entitypatch.getOriginal().isUsingItem() ? 0 : 1, 0.0F, Float.MAX_VALUE)
                        .addConditionalState(0, EntityState.UPDATE_LIVING_MOTION, false)
                        .addConditionalState(1, EntityState.UPDATE_LIVING_MOTION, true)
                        .newTimePair(0.0F, Float.MAX_VALUE).addStateRemoveOld(EntityState.CAN_SWITCH_HAND_ITEM, false)
                        .addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(

                                AnimationEvent.InTimeEvent.create(0.15f, (e, s, p) ->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        DawnDaySounds.battlestaff_swing.get(),
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        0.7F
                                                )

                                        , AnimationEvent.Side.SERVER))
        );


        POLEBLADE_BUSTER_RELEASE = builder.nextAccessor("biped/skill/poleblade_buster_release", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, 0.05F, 0.1F, 0.3F, 0.60F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.6F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(50F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER, EpicFightDamageTypeTags.IS_MAGIC, DamageTypeTags.BYPASSES_RESISTANCE, WOMDamageType.BLACKOUT))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addState(EntityState.TURNING_LOCKED, true)
                        .addState(EntityState.LOCKON_ROTATE, true)
                       // .addProperty(AnimationProperty.AttackAnimationProperty.RESET_PLAYER_COMBO_COUNTER,false)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));

        POLEBLADE_COUNTER = builder.nextAccessor("biped/skill/poleblade_counter", (accessor) ->
                new BasicMultipleAttackAnimation(0.12F, 0.21F, 0.38f, 0.52F, 0.80F, null, biped.get().toolR, accessor, biped)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.NONE)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND,EpicFightSounds.WHOOSH_SHARP.get())
                        .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.7F)
                        .addProperty(AnimationProperty.AttackAnimationProperty.RESET_PLAYER_COMBO_COUNTER,false)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, true));



    }
}
