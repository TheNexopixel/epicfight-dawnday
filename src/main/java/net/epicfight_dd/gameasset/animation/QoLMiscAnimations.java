package net.epicfight_dd.gameasset.animation;

import com.tacz.guns.GunMod;
import com.tacz.guns.init.ModDamageTypes;
import net.epicfight_dd.client.particle.DeathParticles;
import net.epicfight_dd.gameasset.DawnDaySounds;
import net.epicfight_dd.gameasset.animation.types.SelectiveAnimationProxy;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import org.joml.Vector3f;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.EpicFightDamageTypes;

public class QoLMiscAnimations {

    public static AnimationAccessor<ActionAnimation> GENERIC_DEATH_1, GENERIC_DEATH_2, GENERIC_DEATH_3, GENERIC_DEATH_4,
            GENERIC_DEATH_5, GENERIC_DEATH_6, GENERIC_DEATH_7, GENERIC_DEATH_8;
    public static AnimationAccessor<ActionAnimation> DEATH_SOUL1, DEATH_MAGIC, WITHERING_DEMISE, SAD_DEATH,
            SHOT_DEAD, FALL_DEATH, DEATH_SONICBOOM, EXPLOSION_DEATH, SEPUKKU_DEATH;
    public static AnimationAccessor<ActionAnimation> DEATH_MOB_GENERIC1, DEATH_MOB_GENERIC2, DEATH_MOB_GENERIC3,
            DEATH_MOB_GENERIC4, DEATH_MOB_GENERIC5, DEATH_MOB_GENERIC6, DEATH_MOB_GENERIC7, DEATH_MOB_GENERIC8,
            DEATH_MOB_GENERIC9, DEATH_MOB_GENERIC10, DEATH_MOB_GENERIC11, DEATH_MOB_GENERIC12,
            DEATH_MOB_GENERIC13, DEATH_MOB_GENERIC14;
    public static AnimationAccessor<ActionAnimation> FALL_DEATH_MOB, SHOT_DEAD_MOB, DEATH_MOB_MAGIC, DEATH_MOB_WITHER;
    public static AnimationAccessor<SelectiveAnimationProxy> EXPRESSIVE_DEATH;
    public static AnimationAccessor<DodgeAnimation> HEATED_STEP_BACKWARD;

    public static AnimationAccessor<LongHitAnimation> BATTLE$TAFF_PARRIED;
    public static AnimationAccessor<KnockdownAnimation> DAWNDAY_KNOCKDOWN, BREAKDOWN;
    public static AnimationAccessor<ActionAnimation> WIPE_MOUTH;
    public static AnimationAccessor<StaticAnimation> AIR_DASH;

    private static final String DEATH_PATH = "biped/deathanims/";
    private static final DustParticleOptions WHITE_DUST = new DustParticleOptions(new Vector3f(1.0F, 1.0F, 1.0F), 0.8F);

    private static final int D_DEFAULT = 0, D_WITHER = 2, D_SHOT = 3, D_FALL = 5, D_EXPLOSION = 6, D_SONIC = 7,
            D_MAGIC = 13, D_SAD = 14, D_SEPUKKU = 16, D_FALL_MOB = 17, D_SHOT_MOB = 18, D_MAGIC_MOB = 27, D_WITHER_MOB = 28;
    private static final int[] PLAYER_POOL = {1, 4, 8, 9, 10, 11, 12, 15, 33,34,35};
    private static final int[] MOB_POOL = {19, 20, 11, 12, 10, 21, 22, 23, 24, 25, 26, 29, 30, 31, 32, 34, 35};

    public static void animBuild(AnimationManager.AnimationBuilder builder) {
        buildDeathSelector(builder);
        buildDeathAnimations(builder);
        buildHitAnimations(builder);
        buildMovement(builder);
    }


    private static void buildDeathSelector(AnimationManager.AnimationBuilder builder) {
        EXPRESSIVE_DEATH = builder.nextAccessor("biped/living/death_sel", ac -> new SelectiveAnimationProxy(
                patch -> selectDeath(patch.getOriginal()), ac,
                Animations.BIPED_DEATH,  // 0
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
                SEPUKKU_DEATH,           // 16
                FALL_DEATH_MOB,          // 17
                SHOT_DEAD_MOB,           // 18
                DEATH_MOB_GENERIC1,      // 19
                DEATH_MOB_GENERIC2,      // 20
                DEATH_MOB_GENERIC3,      // 21
                DEATH_MOB_GENERIC4,      // 22
                DEATH_MOB_GENERIC5,      // 23
                DEATH_MOB_GENERIC6,      // 24
                DEATH_MOB_GENERIC7,      // 25
                DEATH_MOB_GENERIC8,      // 26
                DEATH_MOB_MAGIC,         // 27
                DEATH_MOB_WITHER,        // 28
                DEATH_MOB_GENERIC9,      // 29
                DEATH_MOB_GENERIC10,     // 30
                DEATH_MOB_GENERIC11,     // 31
                DEATH_MOB_GENERIC12,     // 32
                DEATH_SOUL1,             // 33
                DEATH_MOB_GENERIC13,     // 34
                DEATH_MOB_GENERIC14      // 35
        )
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION, true)
                .addEvents(AnimationProperty.StaticAnimationProperty.TICK_EVENTS,
                        AnimationEvent.SimpleEvent.create(
                                (patch, anim, params) -> DeathParticles.tick(patch.getOriginal()),
                                AnimationEvent.Side.CLIENT)));
    }

    private static int selectDeath(LivingEntity entity) {
        DamageSource source = entity.getLastDamageSource();
        if (source == null) return D_DEFAULT;

        boolean mob = entity instanceof Mob;

        if (source.is(DamageTypes.WITHER)) return mob ? D_WITHER_MOB : D_WITHER;
        if (isFall(source)) return mob ? D_FALL_MOB : D_FALL;
        if (source.is(DamageTypes.ARROW) || source.is(DamageTypes.MOB_PROJECTILE) || isTacZBullet(source)) {
            return mob ? D_SHOT_MOB : D_SHOT;
        }
        if (source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.PLAYER_EXPLOSION)) return D_EXPLOSION;
        if (source.is(DamageTypes.SONIC_BOOM) || source.is(EpicFightDamageTypes.WITHER_BEAM)) return D_SONIC;
        if (source.is(DamageTypes.MAGIC) || source.is(DamageTypes.INDIRECT_MAGIC)) return mob ? D_MAGIC_MOB : D_MAGIC;
        if (source.is(DamageTypes.GENERIC_KILL)) return mob ? D_DEFAULT : D_SEPUKKU;
        if (source.is(DamageTypes.STARVE) || source.is(DamageTypes.DRY_OUT) || source.is(DamageTypes.FREEZE)) return D_SAD;

        int[] pool = mob ? MOB_POOL : PLAYER_POOL;
        return pool[entity.getRandom().nextInt(pool.length)];
    }

    private static boolean isFall(DamageSource source) {
        return source.is(DamageTypes.FALL) || source.is(DamageTypes.FALLING_ANVIL)
                || source.is(DamageTypes.FALLING_BLOCK) || source.is(DamageTypes.FALLING_STALACTITE)
                || source.is(DamageTypes.STALAGMITE);
    }

    private static boolean isTacZBullet(DamageSource source) {
        return ModList.get().isLoaded(GunMod.MOD_ID) && isTacZBulletUnchecked(source);
    }

    private static boolean isTacZBulletUnchecked(DamageSource source) {
        return source.is(ModDamageTypes.BULLET)
                || source.is(ModDamageTypes.BULLET_VOID)
                || source.is(ModDamageTypes.BULLETS_TAG)
                || source.is(ModDamageTypes.BULLET_IGNORE_ARMOR)
                || source.is(ModDamageTypes.BULLET_VOID_IGNORE_ARMOR);
    }

    private static AnimationAccessor<ActionAnimation> death(AnimationManager.AnimationBuilder builder,
                                                            String name, float postDelay) {
        return builder.nextAccessor(DEATH_PATH + name, ac -> new ActionAnimation(0.0F, postDelay, ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION, true));
    }

    private static AnimationAccessor<ActionAnimation> witherDeath(AnimationManager.AnimationBuilder builder, String name) {
        return builder.nextAccessor(DEATH_PATH + name, ac -> new ActionAnimation(0.0F, 0.5F, ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0F, 5.60F))
                .addEvents(AnimationProperty.StaticAnimationProperty.TICK_EVENTS,
                        AnimationEvent.SimpleEvent.create(
                                (e, s, p) -> DeathParticles.witherSwirl(e.getOriginal()),
                                AnimationEvent.Side.CLIENT)));
    }

    private static AnimationAccessor<ActionAnimation> shotDeath(AnimationManager.AnimationBuilder builder, String name) {
        return builder.nextAccessor(DEATH_PATH + name, ac -> new ActionAnimation(0.0F, 0.5F, ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION, true)
                .addEvents(AnimationProperty.StaticAnimationProperty.ON_BEGIN_EVENTS,
                        AnimationEvent.SimpleEvent.create(
                                (e, s, p) -> bloodBurst(e.getOriginal()),
                                AnimationEvent.Side.CLIENT)));
    }

    private static void buildDeathAnimations(AnimationManager.AnimationBuilder builder) {
        GENERIC_DEATH_1 = death(builder, "death_generic1", 20.5F);

        SEPUKKU_DEATH = builder.nextAccessor(DEATH_PATH + "sepukku_death", ac -> new ActionAnimation(0.0F, 20.5F, ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION, true)
                .addEvents(AnimationEvent.InPeriodEvent.create(0.1F, 2.4F,
                        (patch, animation, params) -> sepukkuBlood(patch.getOriginal()),
                        AnimationEvent.Side.CLIENT)));

        DEATH_SONICBOOM = death(builder, "death_sonicboom", 4.5F);
        GENERIC_DEATH_2 = death(builder, "death_generic2", 5.5F);
        GENERIC_DEATH_3 = death(builder, "death_generic3", 0.5F);
        GENERIC_DEATH_4 = death(builder, "death_generic4", 0.5F);
        GENERIC_DEATH_5 = death(builder, "death_generic5", 0.5F);
        GENERIC_DEATH_6 = death(builder, "death_generic6", 0.5F);
        GENERIC_DEATH_7 = death(builder, "death_generic7", 0.5F);
        GENERIC_DEATH_8 = death(builder, "death_generic8", 0.5F);
        DEATH_SOUL1 = death(builder, "death_soul1", 0.5F);

        DEATH_MOB_GENERIC1 = death(builder, "death_mob_generic1", 0.5F);
        DEATH_MOB_GENERIC2 = death(builder, "death_mob_generic2", 0.5F);
        DEATH_MOB_GENERIC3 = death(builder, "death_mob_generic3", 0.5F);
        DEATH_MOB_GENERIC4 = death(builder, "death_mob_generic4", 0.5F);
        DEATH_MOB_GENERIC5 = death(builder, "death_mob_generic5", 0.5F);
        DEATH_MOB_GENERIC6 = death(builder, "death_mob_generic6", 0.5F);
        DEATH_MOB_GENERIC7 = death(builder, "death_mob_generic7", 0.5F);
        DEATH_MOB_GENERIC8 = death(builder, "death_mob_generic8", 0.5F);
        DEATH_MOB_GENERIC9 = death(builder, "death_mob_generic9", 0.5F);
        DEATH_MOB_GENERIC10 = death(builder, "death_mob_generic10", 0.5F);
        DEATH_MOB_GENERIC11 = death(builder, "death_mob_generic11", 0.5F);
        DEATH_MOB_GENERIC12 = death(builder, "death_mob_generic12", 0.5F);
        DEATH_MOB_GENERIC13 = death(builder, "death_mob_generic13", 0.5F);
        DEATH_MOB_GENERIC14 = death(builder, "death_mob_generic14", 0.5F);

        DEATH_MAGIC = builder.nextAccessor(DEATH_PATH + "death_magic", ac -> new ActionAnimation(0.0F, 7.5F, ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(4F, 6.60F))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true));

        DEATH_MOB_MAGIC = builder.nextAccessor(DEATH_PATH + "death_mob_magic", ac -> new ActionAnimation(0.0F, 0.5F, ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0F, 8.60F)));

        SAD_DEATH = death(builder, "death_starving", 2.5F);
        WITHERING_DEMISE = witherDeath(builder, "death_wither");
        DEATH_MOB_WITHER = witherDeath(builder, "death_mob_wither");
        EXPLOSION_DEATH = death(builder, "death_explosion", 0.5F);
        SHOT_DEAD = shotDeath(builder, "death_arrow");
        SHOT_DEAD_MOB = shotDeath(builder, "death_arrow_mob");
        FALL_DEATH = death(builder, "death_fall", 0.5F);
        FALL_DEATH_MOB = death(builder, "death_fall_mob", 0.5F);

        HEATED_STEP_BACKWARD = builder.nextAccessor("biped/skill/heatstep_backward", ac ->
                new DodgeAnimation(0.12F, 0.9f,ac,0.9f,1.0f, Armatures.BIPED)
                        .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(AnimationProperty.AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.1F, 0.7F))
        );
    }

    private static void bloodBurst(LivingEntity entity) {
        RandomSource random = entity.getRandom();
        int count = 15 + random.nextInt(19);
        for (int i = 0; i < count; i++) {
            entity.level().addParticle(EpicFightParticles.BLOOD.get(),
                    entity.getX() + (random.nextDouble() - 0.5D) * entity.getBbWidth(),
                    entity.getY() + random.nextDouble() * entity.getBbHeight(),
                    entity.getZ() + (random.nextDouble() - 0.5D) * entity.getBbWidth(),
                    0.0D, 0.02D, 0.0D);
        }
    }

    private static void sepukkuBlood(LivingEntity entity) {
        RandomSource random = entity.getRandom();
        double rad = Math.toRadians(entity.getYRot());
        double forwardX = -Math.sin(rad);
        double forwardZ = Math.cos(rad);

        for (int i = 0; i < 4; i++) {
            entity.level().addParticle(EpicFightParticles.BLOOD.get(),
                    entity.getX() + (random.nextDouble() - 0.5D) * 0.25D,
                    entity.getY() + entity.getBbHeight() * 0.65D,
                    entity.getZ() + (random.nextDouble() - 0.5D) * 0.25D,
                    forwardX * 0.08D + (random.nextDouble() - 0.5D) * 0.02D,
                    -0.16D,
                    forwardZ * 0.08D + (random.nextDouble() - 0.5D) * 0.02D);
        }
    }


    private static AnimationAccessor<KnockdownAnimation> knockdown(AnimationManager.AnimationBuilder builder, String name) {
        return builder.nextAccessor(name, ac -> new KnockdownAnimation(0.1F, ac, Armatures.BIPED)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));
    }

    private static void buildHitAnimations(AnimationManager.AnimationBuilder builder) {
        BATTLE$TAFF_PARRIED = builder.nextAccessor("biped/living/battlestaff_parried", ac ->
                new LongHitAnimation(0.1F, ac, Armatures.BIPED)
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false));

        DAWNDAY_KNOCKDOWN = knockdown(builder, "biped/living/knockdown");
        BREAKDOWN = knockdown(builder, "biped/living/breakdown");
    }

    private static void buildMovement(AnimationManager.AnimationBuilder builder) {
        WIPE_MOUTH = builder.nextAccessor("biped/living/wipemouth", ac -> new ActionAnimation(0.12F, 0.2F, ac, Armatures.BIPED)
                .addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
                .addEvents(AnimationEvent.InTimeEvent.create(0.15F, Animations.ReusableSources.PLAY_SOUND,
                        AnimationEvent.Side.CLIENT).params(DawnDaySounds.soft_wipe.get())));

        AIR_DASH = builder.nextAccessor("biped/skill/air_dash", ac -> new StaticAnimation(0.02F, false, ac, Armatures.BIPED)
                .addProperty(AnimationProperty.AttackAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0F, 1F))
                .addProperty(AnimationProperty.AttackAnimationProperty.CANCELABLE_MOVE, false)
                .addEvents(
                        AnimationEvent.InTimeEvent.create(0.04F, (patch, animation, params) -> {
                            LivingEntity entity = patch.getOriginal();
                            entity.level().addParticle(EpicFightParticles.WHITE_AFTERIMAGE.get(),
                                    entity.getX(), entity.getY(), entity.getZ(),
                                    Double.longBitsToDouble(entity.getId()), 0.0D, 0.0D);
                        }, AnimationEvent.Side.CLIENT),
                        AnimationEvent.InTimeEvent.create(0.05F,
                                (patch, animation, params) -> spawnRing(patch.getOriginal(), 0.9D, 64),
                                AnimationEvent.Side.CLIENT),
                        AnimationEvent.InTimeEvent.create(0.08F, (patch, animation, params) -> {
                            LivingEntity entity = patch.getOriginal();
                            entity.level().playSound(null, entity.blockPosition(),
                                    EpicFightSounds.EMERGENCY_ESCAPE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                        }, AnimationEvent.Side.SERVER),
                        AnimationEvent.InTimeEvent.create(0.17F,
                                (patch, animation, params) -> spawnRing(patch.getOriginal(), 1.6D, 32),
                                AnimationEvent.Side.CLIENT)));
    }
    private static void spawnRing(LivingEntity entity, double radius, int count) {
        Vec3 look = entity.getLookAngle();
        Vec3 forward = new Vec3(look.x, 0.0D, look.z);
        forward = forward.lengthSqr() < 1.0E-6D ? new Vec3(0.0D, 0.0D, 1.0D) : forward.normalize();

        double centerY = entity.getY() + 1.0D;
        for (int i = 0; i < count; i++) {
            double angle = (Math.PI * 2.0D * i) / count;
            double horizontal = Math.cos(angle) * radius;
            double vertical = Math.sin(angle) * radius;

            entity.level().addParticle(WHITE_DUST,
                    entity.getX() - forward.z * horizontal,
                    centerY + vertical,
                    entity.getZ() + forward.x * horizontal,
                    0.0D, 0.0D, 0.0D);
        }
    }
}