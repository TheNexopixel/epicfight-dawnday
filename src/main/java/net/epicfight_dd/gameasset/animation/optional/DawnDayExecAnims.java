package net.epicfight_dd.gameasset.animation.optional;

import java.util.Set;

import net.epicfight_dd.api.animation.AnimUtils;
import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.gameasset.DawnDaySounds;
import net.epicfight_dd.gameasset.animation.DawnDayCollider;
import net.epicfight_dd.gameasset.animation.optional.type.SelectiveExecutionAttackProxy;
import net.epicfight_dd.gameasset.animation.optional.type.SelectiveExecutionHitAnimation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionAttackAnimation;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionAttackAnimation.ExecutionPhase;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionHitAnimation;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiCollider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.collider.OBBCollider;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;

import static net.epicfight_dd.api.animation.AnimUtils.playSoundOnFrame;

public class DawnDayExecAnims {


    public static AnimationAccessor<ExecutionAttackAnimation> EXECUTION_KNEE_STOMP;
    public static AnimationAccessor<ExecutionHitAnimation> EXECUTED_FIST_FULL;
    public static AnimationAccessor<ExecutionAttackAnimation> NAOYA_EXEC;
    public static AnimationAccessor<ExecutionHitAnimation> NAOYA_EXEC_HIT;

    public static AnimationAccessor<ExecutionAttackAnimation> MILADY_EXECUTION_DUAL;
    public static AnimationAccessor<ExecutionHitAnimation> MILADY_EXECUTION_HIT_SPECIAL;

    public static AnimationAccessor<ExecutionAttackAnimation> BATTLESTAFF_EXECUTE;
    public static AnimationAccessor<ExecutionHitAnimation> BATTLESTAFF_EXECUTED;

    public static AnimationAccessor<ExecutionAttackAnimation> IRON_FIST_EXECUTE;
    public static AnimationAccessor<ExecutionHitAnimation> IRON_FIST_EXECUTED;

    public static AnimationAccessor<ExecutionAttackAnimation> POLEBLADE_EXECUTE;
    public static AnimationAccessor<ExecutionHitAnimation> POLEBLADE_EXECUTED;

    public static AnimationAccessor<ExecutionAttackAnimation> KNIFE_EXECUTE;
    public static AnimationAccessor<ExecutionHitAnimation> KNIFE_EXECUTED;

    public static AnimationAccessor<ExecutionAttackAnimation> RITUS_DAGGER_DUAL_EXECUTE;
    public static AnimationAccessor<ExecutionHitAnimation> RITUS_DAGGER_DUAL_EXECUTED;

    public static AnimationAccessor<ExecutionAttackAnimation> RITUS_DAGGER_EXECUTE;
    public static AnimationAccessor<ExecutionHitAnimation> RITUS_DAGGER_EXECUTED;

    public static AnimationAccessor<ExecutionAttackAnimation> LG_SW_EXECUTE;
    public static AnimationAccessor<ExecutionHitAnimation> LG_SW_EXECUTED;

    public static AnimationAccessor<ExecutionAttackAnimation> LIGHT_GREATSWORD_EXECUTE;
    public static AnimationAccessor<ExecutionHitAnimation> LIGHT_GREATSWORD_EXECUTED;

    public static AnimationAccessor<SelectiveExecutionAttackProxy> MILADY_EXECUTION_SEL;
    public static AnimationAccessor<SelectiveExecutionHitAnimation> MILADY_EXECUTION_SEL_HIT;


    private static final ExtraDamageInstance.ExtraDamage TARGET_MAX_HEALTH = new ExtraDamageInstance.ExtraDamage(
            (attacker, itemstack, target, baseDamage, params) -> params[0] + target.getMaxHealth() * params[1],
            (itemstack, tooltips, baseDamage, params) -> {});

    private static final MultiCollider<OBBCollider> COLLIDER =
            new MultiOBBCollider(3, 1.25F, 1.5F, 1.5F, 0.0F, 1.5F, -1.5F);
    private static final MultiCollider<OBBCollider> COLLIDER_BIG =
            new MultiOBBCollider(3, 4.25F, 1.5F, 3.5F, 0.0F, 1.5F, -2.5F);
    private static final MultiCollider<OBBCollider> COLLIDER_FRONT =
            new MultiOBBCollider(3, 1.25F, 1.5F, 1.5F, 0.0F, 1.5F, 1.5F);

    private static final AnimationProperty.PlaybackSpeedModifier SPEED_NORMAL = (self, patch, speed, prev, elapsed) -> 1.0F;
    private static final AnimationProperty.PlaybackSpeedModifier SPEED_SLOWED = (self, patch, speed, prev, elapsed) -> 0.8333333F;

    private static final int[] NAOYA_PUNCH_FRAMES = {13, 16, 20, 26, 38, 43, 70, 83, 94, 101, 106, 118, 124,
            145, 154, 163, 168, 173, 178, 185, 193, 199, 203, 209, 218, 220};
    private static final int NAOYA_HEAVY_FRAME = 118;

    public static void build(AnimationManager.AnimationBuilder builder) {

        EXECUTION_KNEE_STOMP = builder.nextAccessor("biped/execution/unarmed/stompee", ac ->
                execution(ac,
                        phase(false, 0.0F, 0.76F, 0.93F, 0.93F, 0.93F, COLLIDER_FRONT, root())
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get()),
                        phase(true, 0.93F, 1.36F, 1.56F, 5.0F, 5.0F, COLLIDER_FRONT, Armatures.BIPED.get().legR)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get()))
                        .addEvents(AnimationEvent.InTimeEvent.create(0.6F,
                                (patch, accessor, params) -> patch.getOriginal().addEffect(
                                        new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 9, false, false)),
                                AnimationEvent.Side.BOTH)));

        EXECUTED_FIST_FULL = executedAt(builder, "biped/execution/unarmed/stomped", 0.067F, SPEED_NORMAL);
        IRON_FIST_EXECUTED = executedAt(builder, "biped/execution/iron_fist_executed", 0.1F, SPEED_SLOWED);
        KNIFE_EXECUTED = executedAt(builder, "biped/execution/knife_dual_executed", 0.0F, SPEED_NORMAL);

        KNIFE_EXECUTE = builder.nextAccessor("biped/execution/knife_dual_execute", ac ->
                execution(ac,
                        hit(COLLIDER_BIG, 0.0F, 0.58F, 0.76F, 1.0F)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F)),
                        hit(COLLIDER_BIG, 1.2F, 7.30F, 7.40F, 8.0F)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.9F)),
                        hit(COLLIDER_BIG, 8.15F, 8.15F, 8.2F, 8.2F)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(70F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.6F)),
                        finisher(DawnDayCollider.TORNADO, 8.21F, 11.6F, 11.7F)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.01F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, SoundEvents.EMPTY)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.EMPTY))
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true));

        IRON_FIST_EXECUTE = builder.nextAccessor("biped/execution/iron_fist_execute", ac ->
                execution(ac,
                        hit(COLLIDER_BIG, 0.0F, 0.64F, 0.67F, 1.2F)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F)),
                        hit(COLLIDER_BIG, 1.7F, 1.70F, 1.80F, 1.9F)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.8F)),
                        hit(COLLIDER_BIG, 2.1F, 2.0F, 2.1F, 2.4F)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.45F)),
                        hit(COLLIDER_BIG, 3.0F, 3.1F, 3.2F, 3.83F)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(6.2F)),
                        finisher(COLLIDER_BIG, 4.63F, 5.0F, 5.1F)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.5F)))
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true));

        POLEBLADE_EXECUTE = builder.nextAccessor("biped/execution/poleblade_execute", ac ->
                execution(ac,
                        hit(COLLIDER_BIG, 0.0F, 0.44F, 0.5F, 1.2F)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F)),
                        finisher(COLLIDER_BIG, 1.63F, 3.50F, 3.60F)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.5F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get()))
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true));

        POLEBLADE_EXECUTED = executed(builder, "biped/execution/poleblade_executed", 0.1F);

        NAOYA_EXEC = builder.nextAccessor("biped/execution/unarmed/naoya_aurafarm", ac -> {
            ExecutionAttackAnimation animation = new ExecutionAttackAnimation(0.1F, 0.0F,
                    AnimUtils.getAnimTimeFromFrame(220),
                    AnimUtils.getAnimTimeFromFrame(229),
                    190.93F,
                    COLLIDER,
                    root(),
                    ac,
                    Armatures.BIPED);

            animation
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                    .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                    .addProperty(AttackPhaseProperty.HIT_SOUND, DawnDaySounds.soft_wipe.get())
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                    .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, SPEED_NORMAL);

            for (int frame : NAOYA_PUNCH_FRAMES) {
                SoundEvent sound = frame == NAOYA_HEAVY_FRAME
                        ? EpicFightSounds.BLUNT_HIT.get()
                        : DawnDaySounds.Light_Punch.get();
                animation.addEvents(playSoundOnFrame(frame, sound));
            }
            return animation;
        });

        NAOYA_EXEC_HIT = executedAt(builder, "biped/execution/unarmed/get_aurafarmed", 0.12F, SPEED_NORMAL);

        MILADY_EXECUTION_DUAL = builder.nextAccessor("biped/execution/milady/milady_special_execution", ac ->
                execution(ac,
                        phase(true, 0.0F, 0.82F, 0.93F, 0.93F, 0.93F, COLLIDER, root())
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(5.3F)),
                        phase(true, 0.93F, 10.16F, 10.36F, 5.0F, 5.0F, COLLIDER, root())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.3F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())));

        MILADY_EXECUTION_HIT_SPECIAL = executedAt(builder, "biped/execution/milady/milady_special_execution_hit", 0.3F, SPEED_SLOWED);

        BATTLESTAFF_EXECUTE = builder.nextAccessor("biped/execution/battlestaff_execute", ac ->
                execution(ac,
                        hit(COLLIDER, 0.0F, 0.8F, 0.85F, 1.23F)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2F)),
                        finisher(COLLIDER, 1.23F, 1.42F, 1.45F)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.6F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())));

        BATTLESTAFF_EXECUTED = executed(builder, "biped/execution/battlestaff_executed", 0.1F);

        RITUS_DAGGER_DUAL_EXECUTE = builder.nextAccessor("biped/execution/ritus_dagger_dual_execute", ac ->
                execution(ac,
                        hit(COLLIDER, 0.0F, 0.4F, 0.5F, 0.93F)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.2F))
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                        phase(false, 0.83F, 1.0F, 1.1F, 18.0F, 1.5F, COLLIDER, root())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.3F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                        phase(false, 1.6F, 2.65F, 2.7F, 18.0F, 2.75F, COLLIDER, root())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.3F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get()),
                        phase(false, 3.6F, 3.4F, 3.5F, 18.0F, 4.0F, COLLIDER, root())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(4.3F))
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get()),
                        phase(true, 4.0F, 4.8F, 4.9F, 18.0F, 20.0F, COLLIDER, root())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.1F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100F)))
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true));

        RITUS_DAGGER_DUAL_EXECUTED = executed(builder, "biped/execution/ritus_dagger_dual_executed", 0.1F);

        RITUS_DAGGER_EXECUTE = builder.nextAccessor("biped/execution/ritus_dagger_execute", ac ->
                execution(ac,
                        hit(COLLIDER, 0.0F, 0.7F, 0.75F, 1.23F)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.6F)),
                        finisher(COLLIDER, 1.23F, 2.32F, 2.35F)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL))
                        .addEvents(AnimationEvent.InTimeEvent.create(2.35F, (e, s, p) -> {
                            LivingEntity entity = e.getOriginal();
                            entity.addEffect(new MobEffectInstance(EffectRegistry.SEPUKKU.get(), 300, 0, false, false, true));
                        }, AnimationEvent.Side.SERVER)));

        RITUS_DAGGER_EXECUTED = executed(builder, "biped/execution/ritus_dagger_executed", 0.1F);

        LG_SW_EXECUTED = executed(builder, "biped/execution/lg_sw_executed", 0.0F);

        LG_SW_EXECUTE = builder.nextAccessor("biped/execution/lg_sw_execute", ac ->
                execution(ac,
                        hit(COLLIDER, 0.0F, 1.1F, 1.2F, 1.5F)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.0F)),
                        hit(COLLIDER, 1.7F, 3.4F, 3.5F, 3.9F)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.4F)),
                        hit(COLLIDER, 4.2F, 4.3F, 4.4F, 4.9F)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.52F)),
                        finisher(COLLIDER, 4.93F, 5.4F, 5.5F)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.1F)))
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true));

        LIGHT_GREATSWORD_EXECUTED = executedAt(builder, "biped/execution/light_greatsword_executed", 0.1F, SPEED_NORMAL);

        LIGHT_GREATSWORD_EXECUTE = builder.nextAccessor("biped/execution/light_greatsword_execute", ac ->
                execution(ac,
                        hit(COLLIDER, 0.0F, 0.73F, 0.8F, 1.5F)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.77F)),
                        hit(COLLIDER, 1.5F, 3.41F, 3.5F, 3.6F)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.99F)),
                        finisher(DawnDayCollider.TORNADO, 3.7F, 4.5F, 4.6F)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.1F)))
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true));
    }

    private static Joint root() {
        return Armatures.BIPED.get().rootJoint;
    }
    private static ExecutionAttackAnimation execution(AnimationAccessor<ExecutionAttackAnimation> accessor,
                                                      ExecutionPhase... phases) {
        return new ExecutionAttackAnimation(0.01F, accessor, Armatures.BIPED, phases)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, SPEED_NORMAL);
    }
    private static ExecutionPhase phase(boolean finisher, float start, float preDelay, float contact,
                                        float recovery, float end, Collider collider, Joint joint) {
        return new ExecutionPhase(finisher, start, 0.0F, preDelay, contact, recovery, end, joint, collider);
    }
    private static ExecutionPhase hit(Collider collider, float start, float preDelay, float contact, float end) {
        return phase(false, start, preDelay, contact, 12.73F, end, collider, root());
    }
    private static ExecutionPhase finisher(Collider collider, float start, float preDelay, float contact) {
        return phase(true, start, preDelay, contact, 18.0F, 20.0F, collider, root());
    }
    private static AnimationAccessor<ExecutionHitAnimation> executed(AnimationManager.AnimationBuilder builder,
                                                                     String name, float transitionTime) {
        return builder.nextAccessor(name, ac -> new ExecutionHitAnimation(transitionTime, ac, Armatures.BIPED));
    }
    private static AnimationAccessor<ExecutionHitAnimation> executedAt(AnimationManager.AnimationBuilder builder,
                                                                       String name, float blendTime,
                                                                       AnimationProperty.PlaybackSpeedModifier speed) {
        return builder.nextAccessor(name, ac -> new ExecutionHitAnimation(blendTime, ac, Armatures.BIPED)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, speed));
    }
    public static InteractionHand getHandJointBasedInteractionHand(Joint hand) {
        return hand == Armatures.BIPED.get().handL ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
    }
}