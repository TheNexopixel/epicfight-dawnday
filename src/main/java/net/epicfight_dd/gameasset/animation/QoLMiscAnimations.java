package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.gameasset.animation.types.SelectiveAnimationProxy;
import com.tacz.guns.GunMod;
import com.tacz.guns.init.ModDamageTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.api.animation.types.KnockdownAnimation;
import yesman.epicfight.api.animation.types.LongHitAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.EpicFightDamageTypes;

public class QoLMiscAnimations {

    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_2;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_1;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_3;
    public static AnimationManager.AnimationAccessor<ActionAnimation> WITHERING_DEMISE;
    public static AnimationManager.AnimationAccessor<ActionAnimation> SHOT_DEAD;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FALL_DEATH;
    public static AnimationManager.AnimationAccessor<ActionAnimation> DEATH_SONICBOOM;
    public static AnimationManager.AnimationAccessor<ActionAnimation> EXPLOSION_DEATH;
    public static AnimationManager.AnimationAccessor<SelectiveAnimationProxy> EXPRESSIVE_DEATH;

    public static AnimationManager.AnimationAccessor<LongHitAnimation> BATTLE$TAFF_PARRIED;
    public static AnimationManager.AnimationAccessor<KnockdownAnimation> DAWNDAY_KNOCKDOWN;
    public static AnimationManager.AnimationAccessor<KnockdownAnimation> BREAKDOWN;



    public static void animBuild(AnimationManager.AnimationBuilder builder){

        EXPRESSIVE_DEATH = builder.nextAccessor(
                "biped/living/death_sel",
                ac -> new SelectiveAnimationProxy(patch -> {
                    LivingEntity entity = patch.getOriginal();
                    DamageSource source = entity.getLastDamageSource();

                    if (source == null) return 0;

                    boolean deathByTacZBullet = ModList.get().isLoaded(GunMod.MOD_ID) &&
                            (       source.is(ModDamageTypes.BULLET) ||
                                    source.is(ModDamageTypes.BULLET_VOID) ||
                                    source.is(ModDamageTypes.BULLETS_TAG) ||
                                    source.is(ModDamageTypes.BULLET_IGNORE_ARMOR) ||
                                    source.is(ModDamageTypes.BULLET_VOID_IGNORE_ARMOR)
                            );


                    if (source.is(DamageTypes.GENERIC ) || source.is(DamageTypes.IN_FIRE) || source.is(DamageTypes.ON_FIRE)) return 4;
                    if (source.is(DamageTypes.WITHER)) return 2;
                    if (source.is(DamageTypes.FALL) || source.is(DamageTypes.FALLING_ANVIL) || source.is(DamageTypes.FALLING_STALACTITE)) return 5;
                    if (source.is(DamageTypes.DRY_OUT) || source.is(DamageTypes.MAGIC) || source.is(DamageTypes.LIGHTNING_BOLT) || source.is(DamageTypes.DRAGON_BREATH) || source.is(DamageTypes.HOT_FLOOR)) return 8;
                    if (source.is(DamageTypes.ARROW) || source.is(DamageTypes.MOB_PROJECTILE) || deathByTacZBullet) return 3;
                    if (source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.PLAYER_EXPLOSION)) return 6;
                    if (source.is(DamageTypes.PLAYER_ATTACK) || source.is(DamageTypes.MOB_ATTACK)) return 1;
                    if (source.is(DamageTypes.SONIC_BOOM) || source.is(EpicFightDamageTypes.WITHER_BEAM)) return 7;

                    return 0; //  fallback
                }, ac,
                        Animations.BIPED_DEATH,  // 0 index
                        GENERIC_DEATH_1,         // 1
                        WITHERING_DEMISE,        // 2
                        SHOT_DEAD,               // 3
                        GENERIC_DEATH_2,         // 4
                        FALL_DEATH,              // 5
                        EXPLOSION_DEATH,         // 6
                        DEATH_SONICBOOM,         // 7
                        GENERIC_DEATH_3          // 8
                )
                        .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true)
        );




        GENERIC_DEATH_1 = builder.nextAccessor("biped/living/death_generic1", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        DEATH_SONICBOOM = builder.nextAccessor("biped/living/death_sonicboom", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_2 = builder.nextAccessor("biped/living/death_generic2", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_3 = builder.nextAccessor("biped/living/death_generic3", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        WITHERING_DEMISE = builder.nextAccessor("biped/living/death_wither", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true)
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

        EXPLOSION_DEATH = builder.nextAccessor("biped/living/death_explosion", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        SHOT_DEAD = builder.nextAccessor("biped/living/death_arrow", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
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

        FALL_DEATH = builder.nextAccessor("biped/living/death_fall", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));


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


    }
}
