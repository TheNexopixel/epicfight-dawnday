package net.dawn_day.gameasset.animation;

import net.dawn_day.gameasset.animation.optional.AdditionalExecutions;
import net.dawn_day.gameasset.animation.optional.DawnDayExecAnims;
import net.neoforged.fml.ModList;
import net.shelmarow.combat_evolution.CombatEvolution;
import yesman.epicfight.api.animation.AnimationManager;


public class AnimationBuildNexus {
// we register like this to manage animations more easily

    public static void buildAnimations(AnimationManager.AnimationBuilder builder) {

        DawnDayAnimations.build(builder); // use this builder from this and put it there
        QoLMiscAnimations.animBuild(builder);
        WingStanceAnims.build(builder);

        if(ModList.get().isLoaded(CombatEvolution.MOD_ID)) {
            DawnDayExecAnims.build(builder);
        }
        if(ModList.get().isLoaded("wom")) {
            AdditionalAnimations.build(builder);
        }
        if (ModList.get().isLoaded("wom")
                && ModList.get().isLoaded(CombatEvolution.MOD_ID)) {

            AdditionalExecutions.build(builder);
        }
    }


}
