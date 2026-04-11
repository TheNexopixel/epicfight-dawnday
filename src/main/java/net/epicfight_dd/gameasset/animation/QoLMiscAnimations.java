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

import java.util.Random;

public class QoLMiscAnimations {

    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_2;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_1;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_3;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_4;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_5;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_6;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_7;
    public static AnimationManager.AnimationAccessor<ActionAnimation> DEATH_MAGIC;
    public static AnimationManager.AnimationAccessor<ActionAnimation> WITHERING_DEMISE;
    public static AnimationManager.AnimationAccessor<ActionAnimation> SAD_DEATH;
    public static AnimationManager.AnimationAccessor<ActionAnimation> SHOT_DEAD;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FALL_DEATH;
    public static AnimationManager.AnimationAccessor<ActionAnimation> DEATH_SONICBOOM;
    public static AnimationManager.AnimationAccessor<ActionAnimation> EXPLOSION_DEATH;
    public static AnimationManager.AnimationAccessor<SelectiveAnimationProxy> EXPRESSIVE_DEATH;

    public static AnimationManager.AnimationAccessor<LongHitAnimation> BATTLE$TAFF_PARRIED;
    public static AnimationManager.AnimationAccessor<KnockdownAnimation> DAWNDAY_KNOCKDOWN;
    public static AnimationManager.AnimationAccessor<KnockdownAnimation> BREAKDOWN;

    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_ULTIMATE;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_WAVE;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_THROW_HIT;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_TROW;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_PUSH_STANDUP2;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_PUSH_STANDUP1;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_PUSH_HIT;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_PUSH;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_PULL_HIT;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_PULL;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_EXECUTED;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_EXECUTE;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_CHOKE_HIT;
    public static AnimationManager.AnimationAccessor<ActionAnimation> FORCE_CHOKE;



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
                    Random random = new Random();

                    if (source.is(DamageTypes.WITHER)) return 2;

                    if (source.is(DamageTypes.FALL) || source.is(DamageTypes.FALLING_ANVIL) || source.is(DamageTypes.FALLING_BLOCK)|| source.is(DamageTypes.FALLING_STALACTITE) || source.is(DamageTypes.STALAGMITE)) return 5;

                    if (source.is(DamageTypes.ARROW) || source.is(DamageTypes.MOB_PROJECTILE) || deathByTacZBullet) return 3;

                    if (source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.PLAYER_EXPLOSION)) return 6;

                    if (source.is(DamageTypes.SONIC_BOOM) || source.is(EpicFightDamageTypes.WITHER_BEAM)) return 7;

                    if (source.is(DamageTypes.MAGIC) || source.is(DamageTypes.INDIRECT_MAGIC)) return 13;

                    if(source.is(DamageTypes.STARVE) || source.is(DamageTypes.DRY_OUT) || source.is(DamageTypes.FREEZE)) return 14;


                    int[] genericDeaths = {1, 4, 8, 9, 10,11,12};
                    return genericDeaths[random.nextInt(genericDeaths.length)];
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
                        SAD_DEATH                // 14
                )
                        .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true)
        );




        GENERIC_DEATH_1 = builder.nextAccessor("biped/living/death_generic1", ac -> new ActionAnimation(0.0f,20.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        DEATH_SONICBOOM = builder.nextAccessor("biped/living/death_sonicboom", ac -> new ActionAnimation(0.0f,4.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_2 = builder.nextAccessor("biped/living/death_generic2", ac -> new ActionAnimation(0.0f,5.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_3 = builder.nextAccessor("biped/living/death_generic3", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_4 = builder.nextAccessor("biped/living/death_generic4", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_5 = builder.nextAccessor("biped/living/death_generic5", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_6 = builder.nextAccessor("biped/living/death_generic6", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_7 = builder.nextAccessor("biped/living/death_generic7", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        DEATH_MAGIC = builder.nextAccessor("biped/living/death_magic", ac -> new ActionAnimation(0.0f,7.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        SAD_DEATH = builder.nextAccessor("biped/living/death_starving", ac -> new ActionAnimation(0.0f,2.5f,ac, Armatures.BIPED)
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

        FORCE_CHOKE = builder.nextAccessor("biped/force/force_choke", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_ULTIMATE = builder.nextAccessor("biped/force/force_ultimate", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_EXECUTE = builder.nextAccessor("biped/force/force_execute", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_PUSH_STANDUP2 = builder.nextAccessor("biped/force/force_push_standup2", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_PULL = builder.nextAccessor("biped/force/force_pull", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_PUSH = builder.nextAccessor("biped/force/force_push", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_EXECUTED = builder.nextAccessor("biped/force/force_executed", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_CHOKE_HIT = builder.nextAccessor("biped/force/force_choke_hit", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_PULL_HIT = builder.nextAccessor("biped/force/force_pull_hit", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_PUSH_HIT = builder.nextAccessor("biped/force/force_push_hit", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_PUSH_STANDUP1 = builder.nextAccessor("biped/force/force_push_standup1", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_TROW = builder.nextAccessor("biped/force/force_throw", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_THROW_HIT = builder.nextAccessor("biped/force/force_throw_hit", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));
        FORCE_WAVE = builder.nextAccessor("biped/force/force_wave", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED));



    }
}
