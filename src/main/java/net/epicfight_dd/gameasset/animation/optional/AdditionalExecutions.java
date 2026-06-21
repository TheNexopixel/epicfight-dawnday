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
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static net.epicfight_dd.api.animation.JointTrack.getJointWithTranslation;

public class AdditionalExecutions {

    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> EVIL_TACHI_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> EVIL_TACHI_EXECUTED;


    public static void build(AnimationManager.AnimationBuilder builder) {
       MultiCollider<OBBCollider> evil_collider = new MultiOBBCollider(1, 2.0F, 7.5F, 9.5F, 0.0F, 3.5F, 1.5F);

        AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 1.0F;
        AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTED = (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 0.8333333F;


        EVIL_TACHI_EXECUTED = builder.nextAccessor("biped/execution/evil_odachi_executed", (accessor) ->
                (new ExecutionHitAnimation(0.0f, accessor, Armatures.BIPED))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTED)

        );

        EVIL_TACHI_EXECUTE = builder.nextAccessor("biped/execution/evil_odachi_execute", (accessor)->
                evil_execution(accessor,evil_collider,CONSTANT_EXECUTION,0.4f,0.41f,3.02f,3.05f)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL,true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_HEAD_ROTATION, true)
                        .addState(EntityState.TURNING_LOCKED,true)
                        .addState(EntityState.LOCKON_ROTATE,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.SYNC_CAMERA,true)
                        .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(1,6.1F))
                        .addEvents(
                                AnimationEvent.InPeriodEvent.create(0.27f,6.1f, (e,s,p)->{
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




                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.2f, (e,s,p)->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        SoundEvents.WITHER_AMBIENT,
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        1.0F
                                                )


                                        , AnimationEvent.Side.SERVER),
                                AnimationEvent.InTimeEvent.create(6.2f, (e,s,p)->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        SoundEvents.WITHER_SHOOT,
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        1.0F
                                                )


                                        , AnimationEvent.Side.SERVER)
                        )
                        .addEvents(


                                //BUZZ
                                AnimationEvent.InTimeEvent.create(2.25f, (e,s,p)->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        EpicFightSounds.BUZZ.get(),
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        0.7F
                                                )


                                        , AnimationEvent.Side.SERVER),

                                AnimationEvent.InTimeEvent.create(3.01f, (e, s, p) ->
                                                e.getOriginal().level().playSound(
                                                        null,
                                                        e.getOriginal().blockPosition(),
                                                        EpicFightSounds.LASER_BLAST.get(),
                                                        SoundSource.PLAYERS,
                                                        1.0F,
                                                        0.8F
                                                )


                                        , AnimationEvent.Side.SERVER),


                                //BEAM + LASER SOUND
                                AnimationEvent.InTimeEvent.create(3.01F, (entitypatch, self, params) -> {
                                    LivingEntity entity = entitypatch.getOriginal();

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

                                    float beamRange = 4.0F;
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
                                            WOMParticles.BLACK_RAY.get(), worldX, worldY, worldZ,
                                            worldX + boneForwardX * beamRange,
                                            worldY + boneForwardY * beamRange,
                                            worldZ + boneForwardZ * beamRange
                                    );

                                }, AnimationEvent.Side.CLIENT))
        );



    }

    @SuppressWarnings("RedundantArrayCreation")
    private static ExecutionAttackAnimation evil_execution(AnimationManager.AnimationAccessor<ExecutionAttackAnimation> accessor, MultiCollider<OBBCollider> executionCollider,
                                                           AnimationProperty.PlaybackSpeedModifier CONSTANT_EXECUTION,
                                                           float preDelay1,
                                                           float contact1 ,
                                                           float preDelay2,
                                                           float contact2

// Thats for Evil Tachi
    )
    {
        return (new ExecutionAttackAnimation(0.01F, accessor,

                Armatures.BIPED, new ExecutionAttackAnimation.ExecutionPhase[]{(new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, preDelay1, contact1, 12.73F, 1.23F, Armatures.BIPED.get().rootJoint, executionCollider))
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,WOMParticles.ANTITHEUS_PUNCH_HIT)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,SoundEvents.WITHER_HURT)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.075F)),

                (new ExecutionAttackAnimation.ExecutionPhase(true, 1.23F, 0.0F, preDelay2, contact2, 18.0F, 20.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(8.325F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE,WOMParticles.ANTITHEUS_PUNCH_HIT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND,SoundEvents.WITHER_BREAK_BLOCK)

        }))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_EXECUTION);

    }

}
