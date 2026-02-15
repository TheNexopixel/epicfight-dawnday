package F.epicfight_dd.gameasset.animation;

import F.epicfight_dd.gameasset.animation.types.SelectiveAnimationProxy;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
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



    public static void animBuild(AnimationManager.AnimationBuilder builder){

        EXPRESSIVE_DEATH = builder.nextAccessor(
                "biped/living/death_sel",
                ac -> new SelectiveAnimationProxy(patch -> {
                    LivingEntity entity = patch.getOriginal();
                    DamageSource source = entity.getLastDamageSource();

                    if (source == null) return 0;

                    if (source.is(DamageTypes.GENERIC ) || source.is(DamageTypes.IN_FIRE) || source.is(DamageTypes.ON_FIRE)) return 4;
                    if (source.is(DamageTypes.WITHER)) return 2;
                    if (source.is(DamageTypes.FALL) || source.is(DamageTypes.FALLING_ANVIL)) return 5;
                    if (source.is(DamageTypes.DRY_OUT) || source.is(DamageTypes.MAGIC) || source.is(DamageTypes.LIGHTNING_BOLT) || source.is(DamageTypes.DRAGON_BREATH)) return 8;
                    if (source.is(DamageTypes.ARROW) || source.is(DamageTypes.MOB_PROJECTILE)) return 3;
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
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        EXPLOSION_DEATH = builder.nextAccessor("biped/living/death_explosion", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        SHOT_DEAD = builder.nextAccessor("biped/living/death_arrow", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        FALL_DEATH = builder.nextAccessor("biped/living/death_fall", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));


    }
}
