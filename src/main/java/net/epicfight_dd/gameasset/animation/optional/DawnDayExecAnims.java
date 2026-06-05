package net.epicfight_dd.gameasset.animation.optional;


import net.epicfight_dd.api.animation.AnimUtils;
import net.epicfight_dd.gameasset.animation.optional.type.SelectiveExecutionAttackProxy;
import net.epicfight_dd.gameasset.animation.optional.type.SelectiveExecutionHitAnimation;
import net.epicfight_dd.gameasset.dawnDaySounds;
import net.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionAttackAnimation;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionHitAnimation;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.collider.MultiCollider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.collider.OBBCollider;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;

import java.util.Set;

import static net.epicfight_dd.api.animation.AnimUtils.playSoundOnFrame;

public class DawnDayExecAnims {

    private static final ExtraDamageInstance.ExtraDamage TARGET_MAX_HEALTH = new ExtraDamageInstance.ExtraDamage(
            (attacker, itemstack, target, baseDamage, params) -> params[0] + target.getMaxHealth() * params[1], (itemstack, tooltips, baseDamage, params) -> {
    });



    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> EXECUTION_KNEE_STOMP;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> EXECUTED_FIST_FULL;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> NAOYA_EXEC;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> NAOYA_EXEC_HIT;

    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> MILADY_EXECUTION_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> MILADY_EXECUTION_HIT_SPECIAL;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> MILADY_EXECUTION;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> MILADY_EXECUTION_DUAL;

    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> BATTLESTAFF_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> BATTLESTAFF_EXECUTED;

    public static AnimationManager.AnimationAccessor<SelectiveExecutionAttackProxy> MILADY_EXECUTION_SEL;
    public static AnimationManager.AnimationAccessor<SelectiveExecutionHitAnimation> MILADY_EXECUTION_SEL_HIT;


    public static void build(AnimationManager.AnimationBuilder builder) {

        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;
        Joint Left_hand = biped.get().handL;
        Joint Right_hand = biped.get().handR;

        MultiCollider<OBBCollider> executionCollider = new MultiOBBCollider(3, 1.25F, 1.5F, 1.5F, 0.0F, 1.5F, -1.5F);
        MultiCollider<OBBCollider> executionCollider2 = new MultiOBBCollider(3, 1.25F, 1.5F, 1.5F, 0.0F, 1.5F, 1.5F);
        MultiCollider<OBBCollider> evil_collider = new MultiOBBCollider(1, 2.0F, 7.5F, 9.5F, 0.0F, 3.5F, 1.5F);

        AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 1.0F;
        AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTED = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 0.8333333F;

        EXECUTION_KNEE_STOMP = builder.nextAccessor("biped/execution/unarmed/stompee", (accessor) ->
                (getBluntExecutionAttackAnimation(accessor, executionCollider2, CONSTANT_EXECUTION)));

        EXECUTED_FIST_FULL = builder.nextAccessor("biped/execution/unarmed/stomped", (accessor) ->
                new ExecutionHitAnimation(0.067f, accessor, Armatures.BIPED)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION)

        );

        NAOYA_EXEC = builder.nextAccessor("biped/execution/unarmed/naoya_aurafarm", (ac) ->
                new ExecutionAttackAnimation(0.1f, ac, biped,

                        getLightPunchExecPhase(Left_hand, 13,false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 13, false, executionCollider2),
                        getLightPunchExecPhase(Left_hand, 16, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 20, false, executionCollider2),
                        getLightPunchExecPhase(Left_hand, 26, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 38, false, executionCollider2),
                        getLightPunchExecPhase(Left_hand, 43, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 70, false, executionCollider2),
                        getLightPunchExecPhase(Left_hand, 83, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 94, false, executionCollider2),
                        getLightPunchExecPhase(Left_hand, 101, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 106, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 118, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 124, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 145, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 154, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 163, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 168, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 173, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 178, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 185, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 193, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 199, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 203, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 209, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 218, false, executionCollider2),
                        getLightPunchExecPhase(Right_hand, 220, true, executionCollider2)
                )

                        .addEvents(
                                playSoundOnFrame(118, EpicFightSounds.BLUNT_HIT.get())
                        )


                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION)

        );

        NAOYA_EXEC_HIT = builder.nextAccessor("biped/execution/unarmed/get_aurafarmed", (accessor) ->
                new ExecutionHitAnimation(0.12f, accessor, Armatures.BIPED)
                        .addEvents(AnimationProperty.StaticAnimationProperty.ON_END_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e,s,p)->
                                        e.playAnimationSynchronized(Animations.BIPED_ROLL_BACKWARD,0.01f)
                                        , AnimationEvent.Side.SERVER
                        ))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION)

        );


        MILADY_EXECUTION = builder.nextAccessor("biped/execution/milady/milady_execution", (accessor) ->
                milady(accessor, executionCollider, CONSTANT_EXECUTION, 0.22f, 0.23f, 1.77f, 1.78f));

        MILADY_EXECUTION_DUAL = builder.nextAccessor("biped/execution/milady/milady_special_execution", (accessor) ->
                getExecutionAttackAnimation(accessor, executionCollider, CONSTANT_EXECUTION));

        MILADY_EXECUTION_HIT = builder.nextAccessor("biped/execution/milady/milady_execution_hit", (accessor) ->
                (new ExecutionHitAnimation(-0.3f, accessor, Armatures.BIPED))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTED)

        );

        MILADY_EXECUTION_HIT_SPECIAL = builder.nextAccessor("biped/execution/milady/milady_special_execution_hit", (accessor) ->
                new ExecutionHitAnimation(0.3f, accessor, Armatures.BIPED)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTED)


        );


        ///unused after Combat evolution author added style based execution.
        /// kept here for future reference if anyone decides to use this animation type
        MILADY_EXECUTION_SEL = builder.nextAccessor("biped/execproxy/ems", ac -> new SelectiveExecutionAttackProxy(patch -> {
            boolean dualWieldingMilady = EpicFightCapabilities.getItemStackCapability(patch.getOriginal().getOffhandItem()).getWeaponCategory() == EpicFightDD_WeaponCategories.LIGHT_GREATSWORD;

            LivingEntityPatch<?> target = EpicFightCapabilities.getEntityPatch(patch.getTarget(), LivingEntityPatch.class);

            return dualWieldingMilady ? 1 : 0;
        }, ac,
                MILADY_EXECUTION,
                MILADY_EXECUTION_DUAL
        ));

        /// Deprecated, new combat evolution update makes this redundant
        MILADY_EXECUTION_SEL_HIT = builder.nextAccessor("biped/execproxy/ems_h", ac -> new SelectiveExecutionHitAnimation(patch -> {

                    boolean oppsdualwielding = patch.getOriginal().getTags().contains("dualWieldExecAnim");

                    return oppsdualwielding ? 1 : 0;

                }, 0.2f, ac, Armatures.BIPED,
                        MILADY_EXECUTION_HIT,
                        MILADY_EXECUTION_HIT_SPECIAL
                )

        );


        BATTLESTAFF_EXECUTE = builder.nextAccessor("biped/execution/battlestaff_execute", (accessor) ->
                get2PhaseExecAtkAnim(accessor, executionCollider, CONSTANT_EXECUTION, 0.8f, 0.85f, 1.42f, 1.45f));


        BATTLESTAFF_EXECUTED = builder.nextAccessor("biped/execution/battlestaff_executed", (accessor) ->
                new ExecutionHitAnimation(0.1f, accessor, Armatures.BIPED));


    }

    private static ExecutionAttackAnimation.@NotNull ExecutionPhase getLightPunchExecPhase(Joint hand, int ContactFrame ,boolean isFinished,MultiCollider<OBBCollider> executionCollider) {
        return new ExecutionAttackAnimation.ExecutionPhase(
                isFinished,
                AnimUtils.getAnimTimeFromFrame(Math.max(0, ContactFrame - 2)),
                0.0f,
                AnimUtils.getAnimTimeFromFrame(ContactFrame - 1),
                AnimUtils.getAnimTimeFromFrame(ContactFrame),
                Float.MAX_VALUE,
                AnimUtils.getAnimTimeFromFrame(ContactFrame + 1),
                getHandJointBasedInteractionHand(hand),
                hand,
                executionCollider
        )
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,dawnDaySounds.Light_Punch.get());

    }

    /// Only for Biped armatures
    public static InteractionHand getHandJointBasedInteractionHand(Joint hand){
        if(hand == Armatures.BIPED.get().handL){
            return InteractionHand.OFF_HAND;
        }
        else return InteractionHand.MAIN_HAND;
    }


    private static ExecutionAttackAnimation getBluntExecutionAttackAnimation(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider, AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, 0.76F, 0.93F, 0.93F, 0.93F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get()),
                (new ExecutionAttackAnimation.ExecutionPhase(true, 0.93F, 0.0F, 1.36F, 1.56F, 5.0F, 5.0F, Armatures.BIPED.get().legR, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())}))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION)
                .addEvents(new AnimationEvent[]{AnimationEvent.InTimeEvent.create(0.6F,
                        (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 9, false, false)), AnimationEvent.Side.BOTH)});

    }

    private static ExecutionAttackAnimation getExecutionAttackAnimation(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider, AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(true, 0.0F, 0.0F, 0.82F, 0.93F, 0.93F, 0.93F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(5.3F)),
                (new ExecutionAttackAnimation.ExecutionPhase(true, 0.93F, 0.0F, 10.16F, 10.36F, 5.0F, 5.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.3F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())}))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION);


    }

    private static ExecutionAttackAnimation get2PhaseExecAtkAnim(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider,
                                                                 AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION,
                                                                 float preDelay1,
                                                                 float contact1,
                                                                 float preDelay2,
                                                                 float contact2

// Thats for Battlestaff
    ) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, preDelay1, contact1, 12.73F, 1.23F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2F)),

                (new ExecutionAttackAnimation.ExecutionPhase(true, 1.23F, 0.0F, preDelay2, contact2, 18.0F, 20.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.6F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())}))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION);

    }

    private static ExecutionAttackAnimation milady(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider,
                                                   AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION,
                                                   float preDelay1,
                                                   float contact1,
                                                   float preDelay2,
                                                   float contact2

// Thats for Milady
    ) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, preDelay1, contact1, 12.73F, 1.23F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_HIT.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLADE)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F)),

                (new ExecutionAttackAnimation.ExecutionPhase(true, 1.23F, 0.0F, preDelay2, contact2, 18.0F, 20.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.9F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_HIT.get())

        }))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION);

    }


}
