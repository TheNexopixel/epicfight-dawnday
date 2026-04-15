package net.epicfight_dd.gameasset.animation.optional;


import net.epicfight_dd.gameasset.animation.optional.type.SelectiveExecutionAttackProxy;
import net.epicfight_dd.gameasset.animation.optional.type.SelectiveExecutionHitAnimation;
import net.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionAttackAnimation;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionHitAnimation;
import org.joml.Vector3f;
import reascer.wom.particle.WOMParticles;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.collider.MultiCollider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.collider.OBBCollider;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;

import java.util.Random;
import java.util.Set;

public class DawnDayExecAnims {

    private static final ExtraDamageInstance.ExtraDamage TARGET_MAX_HEALTH = new ExtraDamageInstance.ExtraDamage(
            (attacker, itemstack, target, baseDamage, params) -> params[0] + target.getMaxHealth() * params[1], (itemstack, tooltips, baseDamage, params) -> {
    });


    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> EXECUTION_KNEE_STOMP;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> EXECUTED_FIST_FULL;

    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> MILADY_EXECUTION_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> MILADY_EXECUTION_HIT_SPECIAL;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> MILADY_EXECUTION;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> MILADY_EXECUTION_DUAL;

    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> BATTLESTAFF_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> BATTLESTAFF_EXECUTED;

    public static AnimationManager.AnimationAccessor<SelectiveExecutionAttackProxy> MILADY_EXECUTION_SEL;
    public static AnimationManager.AnimationAccessor<SelectiveExecutionHitAnimation> MILADY_EXECUTION_SEL_HIT;

    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> EVIL_TACHI_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> EVIL_TACHI_EXECUTED;

    public static void build(AnimationManager.AnimationBuilder builder) {
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


        MILADY_EXECUTION = builder.nextAccessor("biped/execution/milady/milady_execution", (accessor)->
                milady(accessor,executionCollider,CONSTANT_EXECUTION,0.1f,0.2f,1.57f,1.58f));

        MILADY_EXECUTION_DUAL = builder.nextAccessor("biped/execution/milady/milady_special_execution", (accessor) ->
                getExecutionAttackAnimation(accessor, executionCollider, CONSTANT_EXECUTION));

        MILADY_EXECUTION_HIT = builder.nextAccessor("biped/execution/milady/milady_execution_hit", (accessor) ->
                (new ExecutionHitAnimation(-0.3f, accessor, Armatures.BIPED))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTED)

        );
        EVIL_TACHI_EXECUTED = builder.nextAccessor("biped/execution/evil_odachi_executed", (accessor) ->
                (new ExecutionHitAnimation(0.0f, accessor, Armatures.BIPED))
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

        BATTLESTAFF_EXECUTE = builder.nextAccessor("biped/execution/battlestaff_execute", (accessor)->
        get2PhaseExecAtkAnim(accessor,executionCollider,CONSTANT_EXECUTION,0.8f,0.85f,1.42f,1.45f));
        /*
        Todolist for Evil Execution
        - add Witherlike Particles to Hitted Enemies
        - add Particles to first hit
        - try wing particles for big ray
         */

        EVIL_TACHI_EXECUTE = builder.nextAccessor("biped/execution/evil_odachi_execute", (accessor)->
                evil_execution(accessor,evil_collider,CONSTANT_EXECUTION,0.4f,0.41f,3.02f,3.05f)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL,true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addState(EntityState.TURNING_LOCKED,true)
                        .addState(EntityState.LOCKON_ROTATE,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(1,8))
                        .addEvents(AnimationProperty.StaticAnimationProperty.TICK_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e,s,p)->{
                                    LivingEntity entity = e.getOriginal();
                                    if (entity.tickCount % 3 != 0) return; //return if not every 5 tick

                                    if (!entity.level().isClientSide) return;

                                    var random = entity.getRandom();

                                    int particleCount = 18 + random.nextInt(20);
                                    double sphereRadius = 0.06;

                                    for (int i = 0; i < particleCount; i++) {
                                        double theta = random.nextDouble() * 2 * Math.PI;
                                        double phi = Math.acos(2 * random.nextDouble() - 1);

                                        double xOffset = sphereRadius * Math.sin(phi) * Math.cos(theta);
                                        double yOffset = sphereRadius * Math.sin(phi) * Math.sin(theta);
                                        double zOffset = sphereRadius * Math.cos(phi);

                                        double vxOffset = xOffset * -0.125;
                                        double vyOffset = yOffset * -0.3;
                                        double vzOffset = zOffset * -0.125;

                                        Particle particle = Minecraft.getInstance().particleEngine.createParticle(
                                                ParticleTypes.SMOKE,
                                                entity.getX() + xOffset,
                                                entity.getY() + yOffset,
                                                entity.getZ() + zOffset,
                                                vxOffset, vyOffset, vzOffset
                                        );
                                        if (particle != null) {
                                            particle.scale(1.0F);
                                            particle.setLifetime(28);
                                        }

                                    }

                                }, AnimationEvent.Side.CLIENT


                        ))
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.2f, (e,s,p)->
                                                        e.getOriginal().level().playSound(
                                                                (Player) e.getOriginal(),
                                                                e.getOriginal(),
                                                                SoundEvents.WITHER_AMBIENT,
                                                                SoundSource.PLAYERS,
                                                                100, 1.0F
                                                        )


                                                , AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(7.9f, (e,s,p)->
                                                e.getOriginal().level().playSound(
                                                        (Player) e.getOriginal(),
                                                        e.getOriginal(),
                                                        SoundEvents.WITHER_SHOOT,
                                                        SoundSource.PLAYERS,
                                                        100, 1.0F
                                                )


                                        , AnimationEvent.Side.CLIENT)
                        )
                        .addEvents(


                                //BUZZ
                                AnimationEvent.InTimeEvent.create(2.25f, (e,s,p)->
                                                e.getOriginal().level().playSound(
                                                        (Player) e.getOriginal(),
                                                        e.getOriginal(),
                                                        EpicFightSounds.BUZZ.get(),
                                                        SoundSource.PLAYERS,
                                                        100, 0.7F
                                                )


                                        , AnimationEvent.Side.CLIENT),


                                //BEAM + LASER SOUND
                                AnimationEvent.InTimeEvent.create(3.01F, (entitypatch, self, params) -> {
                                    LivingEntity entity = entitypatch.getOriginal();

                                    if (entitypatch instanceof PlayerPatch) {
                                        entity.level().playSound(
                                                (Player) entity,
                                                entity,
                                                EpicFightSounds.LASER_BLAST.get(),
                                                SoundSource.PLAYERS,
                                                100, 0.8F
                                        );
                                    }

                                    // Bone matrix only for world spawn position of particle
                                    OpenMatrix4f originMatrix = entitypatch.getArmature().getBoundTransformFor(
                                            entitypatch.getAnimator().getPose(-0.5F),
                                            Armatures.BIPED.get().chest
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
                                    float boneForwardY =  -0.7F;
                                    float boneForwardZ = (float) Math.cos(yawRad);


                                    float rightX = (float) Math.cos(yawRad);
                                    float rightY =  0.0F;
                                    float rightZ = (float) Math.sin(yawRad);

                                    float upX = 0.0F;
                                    float upY = 1.0F;
                                    float upZ = 0.0F;

                                    float beamRange = 8.0F;
                                    int particleCount = 70;
                                    Random rand = new Random();

                                    for (int i = 0; i < particleCount; i++) {
                                        double theta = Math.PI * 2 * rand.nextDouble();

                                        float radialX = (float)(rightX * Math.cos(theta) + upX * Math.sin(theta));
                                        float radialY = (float)(rightY * Math.cos(theta) + upY * Math.sin(theta));
                                        float radialZ = (float)(rightZ * Math.cos(theta) + upZ * Math.sin(theta));

                                        float speed = 0.4F;
                                        float forwardDrift = 0.08F;

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

                                }, AnimationEvent.Side.CLIENT))
        );


        BATTLESTAFF_EXECUTED = builder.nextAccessor("biped/execution/battlestaff_executed", (accessor)->
                new ExecutionHitAnimation(0.1f, accessor, Armatures.BIPED));


    }






    @SuppressWarnings("RedundantArrayCreation")
    private static ExecutionAttackAnimation getBluntExecutionAttackAnimation(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider, AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, 0.76F, 0.93F, 0.93F, 0.93F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get()),
                (new ExecutionAttackAnimation.ExecutionPhase(true, 0.93F, 0.0F, 1.36F, 1.56F, 5.0F, 5.0F, Armatures.BIPED.get().legR, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(new float[]{15.0F, 0.08F})))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())}))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION)
                .addEvents(new AnimationEvent[]{AnimationEvent.InTimeEvent.create(0.6F,
                        (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 9, false, false)), AnimationEvent.Side.BOTH)});

    }

    @SuppressWarnings("RedundantArrayCreation")
    private static ExecutionAttackAnimation getExecutionAttackAnimation(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider, AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(true, 0.0F, 0.0F, 0.82F, 0.93F, 0.93F, 0.93F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.5F)),
                (new ExecutionAttackAnimation.ExecutionPhase(true, 0.93F, 0.0F, 10.16F, 10.36F, 5.0F, 5.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.3F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())}))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION);


    }
    @SuppressWarnings("RedundantArrayCreation")
    private static ExecutionAttackAnimation get2PhaseExecAtkAnim(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider,
                                                                 AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION,
                                                                 float preDelay1,
                                                                 float contact1 ,
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
    @SuppressWarnings("RedundantArrayCreation")
    private static ExecutionAttackAnimation evil_execution(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider,
                                                                 AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION,
                                                                 float preDelay1,
                                                                 float contact1 ,
                                                                 float preDelay2,
                                                                 float contact2

// Thats for Evil Tachi
    ) {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, preDelay1, contact1, 12.73F, 1.23F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,WOMParticles.ANTITHEUS_PUNCH_HIT)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,SoundEvents.WITHER_HURT)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.9F)),

                (new ExecutionAttackAnimation.ExecutionPhase(true, 1.23F, 0.0F, preDelay2, contact2, 18.0F, 20.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(4.0F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,WOMParticles.ANTITHEUS_PUNCH_HIT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,SoundEvents.WITHER_HURT)

        }))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION);

    }
    private static ExecutionAttackAnimation milady(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider,
                                                           AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION,
                                                           float preDelay1,
                                                           float contact1 ,
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
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,EpicFightSounds.BLADE_HIT.get())

        }))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION);

    }


}
