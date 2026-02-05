package F.epicfight_dd.gameasset.animation;

import F.epicfight_dd.gameasset.animation.types.SelectiveDeathAnimation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;

public class QoLMiscAnimations {

    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_2;
    public static AnimationManager.AnimationAccessor<ActionAnimation> GENERIC_DEATH_1;
    public static AnimationManager.AnimationAccessor<ActionAnimation> WITHERING_DEMISE;
    public static AnimationManager.AnimationAccessor<ActionAnimation> SHOT_DEAD;

    public static AnimationManager.AnimationAccessor<SelectiveDeathAnimation> EXPRESSIVE_DEATH;



    public static void animBuild(AnimationManager.AnimationBuilder builder){

        EXPRESSIVE_DEATH = builder.nextAccessor(
                "biped/living/death_sel",
                ac -> new SelectiveDeathAnimation(patch -> {
                    LivingEntity entity = patch.getOriginal();
                    DamageSource source = entity.getLastDamageSource();

                    if (source == null) return 0;

                    if (source.is(DamageTypes.GENERIC)) return 4;
                    if (source.is(DamageTypes.WITHER)) return 2;
                    if (source.is(DamageTypes.ARROW)) return 3;
                    if (source.is(DamageTypes.PLAYER_ATTACK ) || source.is(DamageTypes.PLAYER_EXPLOSION)) return 1;

                    return 0; //  fallback
                }, ac,
                        Animations.BIPED_DEATH,  // 0 index
                        GENERIC_DEATH_2,         // 1
                        WITHERING_DEMISE,        // 2
                        SHOT_DEAD,               // 3
                        GENERIC_DEATH_1          // 4
                )
        );




        GENERIC_DEATH_1 = builder.nextAccessor("biped/living/death_generic1", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        GENERIC_DEATH_2 = builder.nextAccessor("biped/living/death_generic2", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        WITHERING_DEMISE = builder.nextAccessor("biped/living/death_wither", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));

        SHOT_DEAD = builder.nextAccessor("biped/living/death_arrow", ac -> new ActionAnimation(0.0f,0.5f,ac, Armatures.BIPED)
                .addProperty(AnimationProperty.ActionAnimationProperty.IS_DEATH_ANIMATION,true));




    }
}
