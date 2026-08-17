package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.gameasset.DawnDaySounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
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

import java.util.Set;

public class WoMCompatAnimations {

    // HOOKCLAWS
    public static AnimationManager.AnimationAccessor<StaticAnimation> HOOKCLAWS_DFB_WINDUP;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> HOOKCLAWS_DFB_RELEASE;

    // POLEBLADE
    public static AnimationManager.AnimationAccessor<StaticAnimation> POLEBLADE_BUSTER_WINDUP;
    public static AnimationManager.AnimationAccessor<BasicMultipleAttackAnimation> POLEBLADE_BUSTER_RELEASE;

    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;

        HOOKCLAWS_DFB_WINDUP = builder.nextAccessor("biped/skill/hookclaws_dfb_windup", ac ->
                new StaticAnimation(0.12F, false, ac, biped)
                        .newConditionalTimePair((entitypatch) -> entitypatch.getOriginal().isUsingItem() ? 0 : 1, 0.0F, Float.MAX_VALUE)
                        .addConditionalState(0, EntityState.UPDATE_LIVING_MOTION, false)
                        .addConditionalState(1, EntityState.UPDATE_LIVING_MOTION, true)
                        .newTimePair(0.0F, Float.MAX_VALUE).addStateRemoveOld(EntityState.CAN_SWITCH_HAND_ITEM, false)
                        .addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
        );

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



    }
}
