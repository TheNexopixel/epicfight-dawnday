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
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;

import java.util.Set;

import static net.epicfight_dd.api.animation.AnimUtils.playSoundOnFrame;

@SuppressWarnings("SameParameterValue")
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

    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> IRON_FIST_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> IRON_FIST_EXECUTED;

    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> GREATAXE_DUAL_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> GREATAXE_DUAL_EXECUTED;

    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> RITUS_DAGGER_DUAL_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> RITUS_DAGGER_DUAL_EXECUTED;

    public static AnimationManager.AnimationAccessor<SelectiveExecutionAttackProxy> MILADY_EXECUTION_SEL;
    public static AnimationManager.AnimationAccessor<SelectiveExecutionHitAnimation> MILADY_EXECUTION_SEL_HIT;


    public static void build(AnimationManager.AnimationBuilder builder) {

        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;
        Joint Left_hand = biped.get().handL;
        Joint Right_hand = biped.get().handR;

        MultiCollider<OBBCollider> executionCollider = new MultiOBBCollider(3, 1.25F, 1.5F, 1.5F, 0.0F, 1.5F, -1.5F);
        MultiCollider<OBBCollider> executionColliderBIG = new MultiOBBCollider(3, 4.25F, 1.5F, 3.5F, 0.0F, 1.5F, -2.5F);
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

        IRON_FIST_EXECUTED = builder.nextAccessor("biped/execution/iron_fist_executed", (accessor) ->
                (new ExecutionHitAnimation(0.1f, accessor, Armatures.BIPED))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTED)

        );
        GREATAXE_DUAL_EXECUTED = builder.nextAccessor("biped/execution/greataxe_dual_executed", (accessor) ->
                (new ExecutionHitAnimation(0.1f, accessor, Armatures.BIPED))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTED)

        );

        IRON_FIST_EXECUTE = builder.nextAccessor("biped/execution/iron_fist_execute", (accessor) ->
                IRON_FIST_EXECUTE(accessor, executionColliderBIG, CONSTANT_EXECUTION, 0.42f, 0.43f, 1.70f, 1.80f,2.0f,2.1f,3.1f,3.2f,5.0f,5.1f));

        NAOYA_EXEC = builder.nextAccessor("biped/execution/unarmed/naoya_aurafarm", (ac) ->
                new ExecutionAttackAnimation(0.1f, 0.0f,
                        AnimUtils.getAnimTimeFromFrame(220),
                        AnimUtils.getAnimTimeFromFrame(229),
                        190.93f,
                        executionCollider,
                        Armatures.BIPED.get().rootJoint,
                        ac,
                        Armatures.BIPED
                )
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.HIT_BLUNT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, dawnDaySounds.soft_wipe.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                        .addEvents(
                                playSoundOnFrame(13, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(16, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(20, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(26, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(38, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(43, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(70, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(83, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(94, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(101, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(106, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(118, EpicFightSounds.BLUNT_HIT.get()),
                                playSoundOnFrame(124, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(145, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(154, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(163, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(168, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(173, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(178, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(185, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(193, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(199, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(203, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(209, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(218, dawnDaySounds.Light_Punch.get()),
                                playSoundOnFrame(220, dawnDaySounds.Light_Punch.get())

                        )


                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION)

        );

        NAOYA_EXEC_HIT = builder.nextAccessor("biped/execution/unarmed/get_aurafarmed", (accessor) ->
                new ExecutionHitAnimation(0.12f, accessor, Armatures.BIPED)
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

        GREATAXE_DUAL_EXECUTE = builder.nextAccessor("biped/execution/greataxe_dual_execute", (accessor) ->
                greataxe_dual(accessor, executionCollider, CONSTANT_EXECUTION, 1.3f, 1.4f, 2.42f, 2.5f,3.85f,3.9f));

        RITUS_DAGGER_DUAL_EXECUTE = builder.nextAccessor("biped/execution/ritus_dagger_dual_execute", (accessor) ->
                ritusdaggerdual(accessor, executionCollider, CONSTANT_EXECUTION, 0.4f, 0.5f, 1.0f, 1.1f,2.65f,2.7f,3.4f,3.5f,4.8f,4.9f));
        RITUS_DAGGER_DUAL_EXECUTED = builder.nextAccessor("biped/execution/ritus_dagger_dual_executed", (accessor) ->
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

    private static ExecutionAttackAnimation IRON_FIST_EXECUTE(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider,
                                                                 AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION,
                                                                 float preDelay1,
                                                                 float contact1,
                                                                 float preDelay2,
                                                                 float contact2,
                                                              float preDelay3,
                                                                  float contact3,
                                                                 float preDelay4,
                                                                 float contact4,
                                                                float preDelay5,
                                                                 float contact5

    ) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, preDelay1, contact1, 12.73F, 0.5F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F)),

                (new ExecutionAttackAnimation.ExecutionPhase(false, 1.7F, 0.0F, preDelay2, contact2, 12.73F, 1.9F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.8F)),

                (new ExecutionAttackAnimation.ExecutionPhase(false, 2.1F, 0.0F, preDelay3, contact3, 12.73F, 2.4F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.45F)),

                (new ExecutionAttackAnimation.ExecutionPhase(false, 3.0F, 0.0F, preDelay4, contact4, 12.73F, 3.83F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(6.2F)),

                (new ExecutionAttackAnimation.ExecutionPhase(true, 4.63F, 0.0F, preDelay5, contact5, 18.0F, 20.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.5F))

        }))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION);

    }
    // Thats for Battlestaff
    private static ExecutionAttackAnimation get2PhaseExecAtkAnim(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider,
                                                                 AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION,
                                                                 float preDelay1,
                                                                 float contact1,
                                                                 float preDelay2,
                                                                 float contact2

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
    private static ExecutionAttackAnimation ritusdaggerdual(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider,
                                                          AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION,
                                                          float preDelay1,
                                                          float contact1,
                                                          float preDelay2,
                                                          float contact2,
                                                          float preDelay3,
                                                          float contact3,
                                                            float preDelay4,
                                                            float contact4,
                                                            float preDelay5,
                                                            float contact5

    ) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, preDelay1, contact1, 12.73F, 0.93F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.2F))
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),

                (new ExecutionAttackAnimation.ExecutionPhase(false, 0.83F, 0.0F, preDelay2, contact2, 18.0F, 1.5F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.3F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),

                (new ExecutionAttackAnimation.ExecutionPhase(false, 1.6F, 0.0F, preDelay3, contact3, 18.0F, 2.75F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.3F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),

                (new ExecutionAttackAnimation.ExecutionPhase(false, 3.6F, 0.0F, preDelay4, contact4, 18.0F, 4.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(4.3F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get()),

                (new ExecutionAttackAnimation.ExecutionPhase(true, 4.0F, 0.0F, preDelay5, contact5, 18.0F, 20.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.1F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100F))
        }))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION);

    }
    private static ExecutionAttackAnimation greataxe_dual(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider,
                                                                 AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION,
                                                                 float preDelay1,
                                                                 float contact1,
                                                                 float preDelay2,
                                                                 float contact2,
                                                                 float preDelay3,
                                                                 float contact3

    ) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, preDelay1, contact1, 12.73F, 1.53F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.2F))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),

                (new ExecutionAttackAnimation.ExecutionPhase(false, 1.93F, 0.0F, preDelay2, contact2, 18.0F, 2.5F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.3F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                (new ExecutionAttackAnimation.ExecutionPhase(true, 3.0F, 0.0F, preDelay3, contact3, 18.0F, 20.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,EpicFightSounds.EVISCERATE.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20F))
        }))
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
