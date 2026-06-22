package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.DawnDayConfig;
import net.epicfight_dd.gameasset.animation.types.SelectiveAnimationProxy;
import com.tacz.guns.GunMod;
import com.tacz.guns.init.ModDamageTypes;
import net.epicfight_dd.gameasset.dawnDaySounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.api.animation.types.KnockdownAnimation;
import yesman.epicfight.api.animation.types.LongHitAnimation;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.EpicFightDamageTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.epicfight_dd.api.animation.JointTrack.getJointWithTranslation;

public class QoLMiscAnimations {

    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_2;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_1;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_3;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_4;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_5;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_6;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_7;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_8;
    public static AnimationManager.AnimationAccessor<ActionAnimation> DEATH_MAGIC;
    public static AnimationManager.AnimationAccessor<ActionAnimation> WITHERING_DEMISE;
    public static AnimationManager.AnimationAccessor<ActionAnimation> SAD_DEATH;
    public static AnimationManager.AnimationAccessor<ActionAnimation> SHOT_DEAD;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FALL_DEATH;
    public static AnimationManager.AnimationAccessor<ActionAnimation> DEATH_SONICBOOM;
    public static AnimationManager.AnimationAccessor<ActionAnimation> EXPLOSION_DEATH;
    public static AnimationManager.AnimationAccessor<ActionAnimation> SEPUKKU_DEATH;
    public static AnimationManager.AnimationAccessor<SelectiveAnimationProxy> EXPRESSIVE_DEATH;

    public static AnimationManager.AnimationAccessor<LongHitAnimation> BATTLE$TAFF_PARRIED;
    public static AnimationManager.AnimationAccessor<KnockdownAnimation> DAWNDAY_KNOCKDOWN;
    public static AnimationManager.AnimationAccessor<KnockdownAnimation> BREAKDOWN;


    public static AnimationManager.AnimationAccessor<ActionAnimation> WIPE_MOUTH;



    public static void animBuild(AnimationManager.AnimationBuilder builder){


        EXPRESSIVE_DEATH = builder.nextAccessor(
                "biped/living/death_sel",
                ac -> new SelectiveAnimationProxy(patch -> {
                    LivingEntity entity = patch.getOriginal();
                    DamageSource source = entity.getLastDamageSource();

                    if (source == null) return 0; //  fallback




                    boolean deathByTacZBullet = ModList.get().isLoaded(GunMod.MOD_ID) &&
                            (       source.is(ModDamageTypes.BULLET) ||
                                    source.is(ModDamageTypes.BULLET_VOID) ||
                                    source.is(ModDamageTypes.BULLETS_TAG) ||
                                    source.is(ModDamageTypes.BULLET_IGNORE_ARMOR) ||
                                    source.is(ModDamageTypes.BULLET_VOID_IGNORE_ARMOR)
                            );
                    Random randomgeneric = new Random();

                    if (source.is(DamageTypes.WITHER)) return 2;

                    if (source.is(DamageTypes.FALL) || source.is(DamageTypes.FALLING_ANVIL) || source.is(DamageTypes.FALLING_BLOCK)|| source.is(DamageTypes.FALLING_STALACTITE) || source.is(DamageTypes.STALAGMITE)) return 5;

                    if (source.is(DamageTypes.ARROW) || source.is(DamageTypes.MOB_PROJECTILE) || deathByTacZBullet) return 3;

                    if (source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.PLAYER_EXPLOSION)) return 6;

                    if (source.is(DamageTypes.SONIC_BOOM) || source.is(EpicFightDamageTypes.WITHER_BEAM)) return 7;

                    if (source.is(DamageTypes.MAGIC) || source.is(DamageTypes.INDIRECT_MAGIC)) return 13;

                    if (source.is(DamageTypes.GENERIC_KILL)) return 16;

                    if(source.is(DamageTypes.STARVE) || source.is(DamageTypes.DRY_OUT) || source.is(DamageTypes.FREEZE)) return 14;


                    int[] genericDeaths = {1, 4, 8, 9, 10,11,12,15};
                    return genericDeaths[randomgeneric.nextInt(genericDeaths.length)];
                }, ac,
                        Animations.BIPED_DEATH,  // 0 index
                        GENERIC_DEATH_1,         // 1
                        WITHERING_DEMISE,        // 2
                        SHOT_DEAD,               // 3
                        GENERIC_DEATH_2,         // 4
                        FALL_DEATH,              // 5
                        EXPLOSION_DEATH,         // 6
                        DEATH_SONICBOOM,         // 7
                        GENERIC_DEATH_3,         // 8
                        GENERIC_DEATH_4,         // 9
                        GENERIC_DEATH_5,         // 10
                        GENERIC_DEATH_6,         // 11
                        GENERIC_DEATH_7,         // 12
                        DEATH_MAGIC,             // 13
                        SAD_DEATH,               // 14
                        GENERIC_DEATH_8,         // 15
                        SEPUKKU_DEATH            // 16

                )
                        .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true)
                        .addEvents(AnimationProperty.StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                                (e, s, p) ->{
                                    if (DawnDayConfig.ENABLE_DEATH_PARTICLES.get().equals(false)){
                                        return;
                                    }



                                                for (int i = 0; i < 80; i++) {


                                                    LivingEntity entity = e.getOriginal();

                                                    RandomSource random = entity.getRandom();

                                                    if (!entity.level().isClientSide()) {
                                                        return;
                                                    }

                                                    double x =
                                                            entity.getX()
                                                                    + (random.nextDouble() - 0.5D) * entity.getBbWidth();

                                                    double y =
                                                            entity.getY()
                                                                    + random.nextDouble() * entity.getBbHeight();

                                                    double z =
                                                            entity.getZ()
                                                                    + (random.nextDouble() - 0.5D) * entity.getBbWidth();

                                                    double vx =
                                                            random.nextGaussian() * 0.20D;

                                                    double vy =
                                                            random.nextDouble() * 0.18D;

                                                    double vz =
                                                            random.nextGaussian() * 0.20D;

                                                    entity.level().addParticle(
                                                            ParticleTypes.ASH,
                                                            x,
                                                            y,
                                                            z,
                                                            vx,
                                                            vy,
                                                            vz
                                                    );

                                            }

                                }, AnimationEvent.Side.CLIENT
                                        )
                        )
                        .addEvents(
                                AnimationProperty.StaticAnimationProperty.TICK_EVENTS,
                                AnimationEvent.SimpleEvent.create(
                                        (patch, anim, params) -> {
                                            if (DawnDayConfig.ENABLE_DEATH_PARTICLES.get().equals(false)){
                                                return;
                                            }

                                            LivingEntity entity = patch.getOriginal();

                                            if (entity == null || !entity.level().isClientSide()) {
                                                return;
                                            }

                                            RandomSource random = entity.getRandom();

                                            int numParticles = 3;

                                            for (int i = 0; i < numParticles; i++) {

                                                float L = -0.05F;
                                                float R = 0.05F;

                                                List<Vec3> positions = new ArrayList<>();

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(L, 0F, 0.6F),
                                                        Armatures.BIPED.get().head
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(R, 0F, 0.6F),
                                                        Armatures.BIPED.get().head
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(L, 0.06F, 0.1F),
                                                        Armatures.BIPED.get().chest
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(R, 0.06F, 0.1F),
                                                        Armatures.BIPED.get().chest
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(L, 0.00F, 0.0F),
                                                        Armatures.BIPED.get().torso
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(L, 0.06F, 0.1F),
                                                        Armatures.BIPED.get().thighL
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(R, 0.06F, 0.1F),
                                                        Armatures.BIPED.get().thighR
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(0F, 0.6F, 0F),
                                                        Armatures.BIPED.get().handL
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(0F, 0.6F, 0F),
                                                        Armatures.BIPED.get().handR
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(0F, 0.2F, 0.2F),
                                                        Armatures.BIPED.get().kneeL
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(0F, 0.2F, 0.2F),
                                                        Armatures.BIPED.get().kneeR
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(0F, 0.2F, 0.2F),
                                                        Armatures.BIPED.get().legL
                                                ));

                                                positions.add(getJointWithTranslation(
                                                        Minecraft.getInstance().player,
                                                        entity,
                                                        new Vec3f(0F, 0.2F, 0.2F),
                                                        Armatures.BIPED.get().legR
                                                ));

                                                Vec3 movement = entity.getDeltaMovement();

                                                for (Vec3 pos : positions) {

                                                    if (pos == null) {
                                                        continue;
                                                    }


                                                    double x =
                                                            pos.x + random.nextGaussian() * 0.08D;

                                                    double y =
                                                            pos.y + random.nextGaussian() * 0.12D;

                                                    double z =
                                                            pos.z + random.nextGaussian() * 0.08D;


                                                    double windStrength =
                                                            0.05D + random.nextDouble() * 0.15D;

                                                    double windAngle =
                                                            random.nextDouble() * Math.PI * 2.0D;

                                                    double windX =
                                                            Math.cos(windAngle) * windStrength;

                                                    double windZ =
                                                            Math.sin(windAngle) * windStrength;


                                                    double swirlAngle =
                                                            random.nextDouble() * Math.PI * 2.0D;

                                                    double swirlStrength =
                                                            0.05D + random.nextDouble() * 0.12D;

                                                    double swirlX =
                                                            Math.cos(swirlAngle) * swirlStrength;

                                                    double swirlZ =
                                                            Math.sin(swirlAngle) * swirlStrength;


                                                    double vx =
                                                            movement.x * 0.5D
                                                                    + windX
                                                                    + swirlX
                                                                    + random.nextGaussian() * 0.05D;

                                                    double vy =
                                                            movement.y * 0.3D
                                                                    + random.nextDouble() * 0.12D;

                                                    double vz =
                                                            movement.z * 0.5D
                                                                    + windZ
                                                                    + swirlZ
                                                                    + random.nextGaussian() * 0.05D;


                                                    if (random.nextFloat() < 0.15F) {

                                                        vx *= 2.5D;
                                                        vy *= 1.5D;
                                                        vz *= 2.5D;
                                                    }

                                                    Particle particle =
                                                            Minecraft.getInstance()
                                                                    .particleEngine
                                                                    .createParticle(
                                                                            ParticleTypes.WHITE_ASH,
                                                                            x,
                                                                            y,
                                                                            z,
                                                                            vx,
                                                                            vy,
                                                                            vz
                                                                    );

                                                    if (particle != null) {
                                                        particle.setLifetime(
                                                                20 + random.nextInt(20)
                                                        );
                                                    }


                                                    if (random.nextFloat() < 0.35F) {

                                                        entity.level().addParticle(
                                                                ParticleTypes.ASH,
                                                                x,
                                                                y,
                                                                z,
                                                                (random.nextDouble() - 0.5D) * 0.08D,
                                                                0.05D + random.nextDouble() * 0.04D,
                                                                (random.nextDouble() - 0.5D) * 0.08D
                                                        );
                                                    }
                                                }
                                            }

                                        },
                                        AnimationEvent.Side.CLIENT
                                )
                        )


                        .addEvents(
                                AnimationProperty.StaticAnimationProperty.TICK_EVENTS,
                                AnimationEvent.SimpleEvent.create(
                                        (patch, anim, params) -> {
                                            if (DawnDayConfig.ENABLE_DEATH_PARTICLES.get().equals(false)){
                                                return;
                                            }

                                            LivingEntity entity = patch.getOriginal();

                                            if (!entity.level().isClientSide()) {
                                                return;
                                            }

                                            RandomSource random = entity.getRandom();


                                            if (entity.tickCount % 2 != 0) {
                                                return;
                                            }

                                            double windX = 0.02D;
                                            double windZ = -0.015D;


                                            for (int i = 0; i < 12; i++) {

                                                double x =
                                                        entity.getX()
                                                                + (random.nextDouble() - 0.5D) * entity.getBbWidth();

                                                double y =
                                                        entity.getY()
                                                                + random.nextDouble() * entity.getBbHeight();

                                                double z =
                                                        entity.getZ()
                                                                + (random.nextDouble() - 0.5D) * entity.getBbWidth();

                                                double angle =
                                                        random.nextDouble() * Math.PI * 2.0D;

                                                double swirlX =
                                                        Math.cos(angle) * 0.04D;

                                                double swirlZ =
                                                        Math.sin(angle) * 0.04D;

                                                double vx =
                                                        swirlX
                                                                + random.nextGaussian() * 0.03D
                                                                + windX;

                                                double vy =
                                                        random.nextDouble() * 0.04D;

                                                double vz =
                                                        swirlZ
                                                                + random.nextGaussian() * 0.03D
                                                                + windZ;

                                                entity.level().addParticle(
                                                        ParticleTypes.WHITE_ASH,
                                                        x,
                                                        y,
                                                        z,
                                                        vx,
                                                        vy,
                                                        vz
                                                );
                                            }


                                            for (int i = 0; i < 8; i++) {

                                                double x =
                                                        entity.getX()
                                                                + (random.nextDouble() - 0.5D) * 0.8D;

                                                double y =
                                                        entity.getY()
                                                                + random.nextDouble() * entity.getBbHeight();

                                                double z =
                                                        entity.getZ()
                                                                + (random.nextDouble() - 0.5D) * 0.8D;

                                                entity.level().addParticle(
                                                        ParticleTypes.ASH,
                                                        x,
                                                        y,
                                                        z,
                                                        (random.nextDouble() - 0.5D) * 0.02D,
                                                        0.05D + random.nextDouble() * 0.03D,
                                                        (random.nextDouble() - 0.5D) * 0.02D
                                                );
                                            }


                                            for (int i = 0; i < 8; i++) {

                                                double x =
                                                        entity.getX()
                                                                + (random.nextDouble() - 0.5D) * entity.getBbWidth();

                                                double z =
                                                        entity.getZ()
                                                                + (random.nextDouble() - 0.5D) * entity.getBbWidth();

                                                entity.level().addParticle(
                                                        ParticleTypes.ASH,
                                                        x,
                                                        entity.getY() + 0.05D,
                                                        z,
                                                        (random.nextDouble() - 0.5D) * 0.02D,
                                                        0.01D,
                                                        (random.nextDouble() - 0.5D) * 0.02D
                                                );
                                            }
                                        },
                                        AnimationEvent.Side.CLIENT
                                )
                        )




        );




        GENERIC_DEATH_1 = builder.nextAccessor("biped/deathanims/death_generic1", ac -> new ActionAnimation(0.0f,20.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true)
        );

        SEPUKKU_DEATH = builder.nextAccessor("biped/deathanims/sepukku_death", ac -> new ActionAnimation(0.0f,20.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true)
                .addEvents(

                        AnimationEvent.InPeriodEvent.create(

                                0.1f,
                                2.4f,

                                (entitypatch, animation, params) -> {

                                    LivingEntity entity =
                                            entitypatch.getOriginal();

                                    float yaw = entity.getYRot();

                                    double rad = Math.toRadians(yaw);

                                    double forwardX = -Math.sin(rad);
                                    double forwardZ = Math.cos(rad);

                                    for (int i = 0; i < 4; i++) {

                                        double x =
                                                entity.getX()
                                                        + (entity.getRandom().nextDouble() - 0.5D)
                                                        * 0.25D;

                                        double y =
                                                entity.getY()
                                                        + entity.getBbHeight() * 0.65D;

                                        double z =
                                                entity.getZ()
                                                        + (entity.getRandom().nextDouble() - 0.5D)
                                                        * 0.25D;

                                        double motionX =
                                                forwardX * 0.08D
                                                        + (entity.getRandom().nextDouble() - 0.5D)
                                                        * 0.02D;

                                        double motionY =
                                                -0.16D;

                                        double motionZ =
                                                forwardZ * 0.08D
                                                        + (entity.getRandom().nextDouble() - 0.5D)
                                                        * 0.02D;

                                        entity.level().addParticle(
                                                EpicFightParticles.BLOOD.get(),

                                                x,
                                                y,
                                                z,

                                                motionX,
                                                motionY,
                                                motionZ
                                        );
                                    }
                                }, AnimationEvent.Side.CLIENT
                        )
                )
                );

        DEATH_SONICBOOM = builder.nextAccessor("biped/deathanims/death_sonicboom", ac -> new ActionAnimation(0.0f,4.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_2 = builder.nextAccessor("biped/deathanims/death_generic2", ac -> new ActionAnimation(0.0f,5.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_3 = builder.nextAccessor("biped/deathanims/death_generic3", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_4 = builder.nextAccessor("biped/deathanims/death_generic4", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_5 = builder.nextAccessor("biped/deathanims/death_generic5", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_6 = builder.nextAccessor("biped/deathanims/death_generic6", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_7 = builder.nextAccessor("biped/deathanims/death_generic7", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_8 = builder.nextAccessor("biped/deathanims/death_generic8", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        DEATH_MAGIC = builder.nextAccessor("biped/deathanims/death_magic", ac -> new ActionAnimation(0.0f,7.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true)
                .addProperty(AnimationProperty.AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(4f, 6.60f))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL,true));

        SAD_DEATH = builder.nextAccessor("biped/deathanims/death_starving", ac -> new ActionAnimation(0.0f,2.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        WITHERING_DEMISE = builder.nextAccessor("biped/deathanims/death_wither", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL,true)
                .addProperty(AnimationProperty.AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0f, 5.60f))
                .addEvents(AnimationProperty.StaticAnimationProperty.TICK_EVENTS, AnimationEvent.SimpleEvent.create(
                        (e,s,p)->{
                            LivingEntity entity = e.getOriginal();
                            if (entity.tickCount % 5 != 0) return; //return if not every 5 tick

                            if (!entity.level().isClientSide) return;

                            var random = entity.getRandom();

                            int particleCount = 10 + random.nextInt(16); // 10–25
                            double sphereRadius = 0.66;

                            for (int i = 0; i < particleCount; i++) {
                                double theta = random.nextDouble() * 2 * Math.PI;
                                double phi = Math.acos(2 * random.nextDouble() - 1);

                                double xOffset = sphereRadius * Math.sin(phi) * Math.cos(theta);
                                double yOffset = sphereRadius * Math.sin(phi) * Math.sin(theta);
                                double zOffset = sphereRadius * Math.cos(phi);

                                double vxOffset = xOffset * 0.2;
                                double vyOffset = yOffset * 0.2;
                                double vzOffset = zOffset * 0.2;

                                Particle particle = Minecraft.getInstance().particleEngine.createParticle(
                                        ParticleTypes.LARGE_SMOKE,
                                        entity.getX() + xOffset,
                                        entity.getY() + yOffset + 0.6,
                                        entity.getZ() + zOffset,
                                        vxOffset, vyOffset, vzOffset
                                );

                                if (particle != null) {
                                    particle.scale(1.1F);
                                    particle.setLifetime(15);
                                }
                            }

                        }, AnimationEvent.Side.CLIENT
                ))

        );

        EXPLOSION_DEATH = builder.nextAccessor("biped/deathanims/death_explosion", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        SHOT_DEAD = builder.nextAccessor("biped/deathanims/death_arrow", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true)
                .addEvents(AnimationProperty.StaticAnimationProperty.ON_BEGIN_EVENTS, AnimationEvent.SimpleEvent.create(
                        (e,s,p)->{

                            LivingEntity entity = e.getOriginal();

                            // Random count between 10–25
                            int particleCount = 15 + entity.getRandom().nextInt(19);

                            for (int i = 0; i < particleCount; i++) {
                                double offsetX = (entity.getRandom().nextDouble() - 0.5D) * entity.getBbWidth();
                                double offsetY = entity.getRandom().nextDouble() * entity.getBbHeight();
                                double offsetZ = (entity.getRandom().nextDouble() - 0.5D) * entity.getBbWidth();

                                entity.level().addParticle(
                                        EpicFightParticles.BLOOD.get(),
                                        entity.getX() + offsetX,
                                        entity.getY() + offsetY,
                                        entity.getZ() + offsetZ,
                                        0.0D, 0.02D, 0.0D
                                );
                            }

                        }, AnimationEvent.Side.CLIENT
                ))


        );

        FALL_DEATH = builder.nextAccessor("biped/deathanims/death_fall", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true)
        );


        BATTLE$TAFF_PARRIED = builder.nextAccessor("biped/living/battlestaff_parried", ac->
                new LongHitAnimation( 0.1f, ac, Armatures.BIPED)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE,false)
        );

        DAWNDAY_KNOCKDOWN = builder.nextAccessor("biped/living/knockdown", ac->
                new KnockdownAnimation( 0.1f, ac, Armatures.BIPED)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE,false)
        );

        BREAKDOWN = builder.nextAccessor("biped/living/breakdown", ac->
                new KnockdownAnimation( 0.1f, ac, Armatures.BIPED)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE,false)
        );
        // Just Animations I have done some time ago. I wanted to see how they look like ingame
        WIPE_MOUTH = builder.nextAccessor("biped/living/wipemouth", ac -> new ActionAnimation(0.02f,0.2f,ac,Armatures.BIPED)
                .addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION,true)
                .addEvents(AnimationEvent.InTimeEvent.create(0.15f,Animations.ReusableSources.PLAY_SOUND, AnimationEvent.Side.CLIENT).params(dawnDaySounds.soft_wipe.get()))
        );


    }
}
