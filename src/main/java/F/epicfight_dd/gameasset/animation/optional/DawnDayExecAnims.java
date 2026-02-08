package F.epicfight_dd.gameasset.animation.optional;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionAttackAnimation;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionHitAnimation;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.collider.MultiCollider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.collider.OBBCollider;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;

import java.util.Set;

public class DawnDayExecAnims {

    private static final ExtraDamageInstance.ExtraDamage TARGET_MAX_HEALTH = new ExtraDamageInstance.ExtraDamage(
            (attacker, itemstack, target, baseDamage, params) -> params[0] + target.getMaxHealth() * params[1], (itemstack, tooltips, baseDamage, params) -> {
    });


    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> EXECUTION_KNEE_STOMP;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> EXECUTED_FIST_FULL;

    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> MILADY_EXECUTION_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> MILADY_EXECUTION;

    @SuppressWarnings("RedundantCast")
    public static void build(AnimationManager.AnimationBuilder builder) {
        MultiCollider<OBBCollider> executionCollider = new MultiOBBCollider(3, 1.25F, 1.5F, 1.5F, 0.0F, 1.5F, -1.5F);
        AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 1.0F;
        AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTED = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 0.8333333F;

        EXECUTION_KNEE_STOMP = builder.nextAccessor("biped/execution/unarmed/stompee", (accessor) ->
                (getBluntExecutionAttackAnimation(accessor, executionCollider, CONSTANT_EXECUTION)));

        EXECUTED_FIST_FULL = builder.nextAccessor("biped/execution/unarmed/stomped", (accessor) ->
                (ExecutionHitAnimation) (new ExecutionHitAnimation(0.25f, accessor, Armatures.BIPED))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTED)

        );

        MILADY_EXECUTION = builder.nextAccessor("biped/execution/milady/milady_execution", (accessor) ->
                getExecutionAttackAnimation(accessor,executionCollider,CONSTANT_EXECUTION));

        MILADY_EXECUTION_HIT = builder.nextAccessor("biped/execution/milady/milady_execution", (accessor) ->
                (ExecutionHitAnimation) (new ExecutionHitAnimation(0.0f, accessor, Armatures.BIPED))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTED)

        );


    }

    @SuppressWarnings("RedundantArrayCreation")
    private static ExecutionAttackAnimation getBluntExecutionAttackAnimation(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider, AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, 0.76F, 0.93F, 0.93F, 0.93F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get()),
                (new ExecutionAttackAnimation.ExecutionPhase(true, 0.93F, 0.0F, 3.16F, 3.36F, 5.0F, 5.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(new float[]{15.0F, 0.08F})))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())}))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION)
                .addEvents(new AnimationEvent[]{AnimationEvent.InTimeEvent.create(0.6F,
                        (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 9, false, false)), AnimationEvent.Side.BOTH)});

    }

    @SuppressWarnings("RedundantArrayCreation")
    private static ExecutionAttackAnimation getExecutionAttackAnimation(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider, AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, 0.76F, 0.93F, 0.93F, 0.93F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                (new ExecutionAttackAnimation.ExecutionPhase(true, 0.93F, 0.0F, 3.16F, 3.36F, 5.0F, 5.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(new float[]{15.0F, 0.08F})))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())}))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION)
                .addEvents(new AnimationEvent[]{AnimationEvent.InTimeEvent.create(0.6F,
                        (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 9, false, false)), AnimationEvent.Side.BOTH)});

    }



}
