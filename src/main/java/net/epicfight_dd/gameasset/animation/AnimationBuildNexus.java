package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.gameasset.animation.optional.DawnDayExecAnims;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.CombatEvolution;
import yesman.epicfight.api.animation.AnimationManager;


public class AnimationBuildNexus {
// we register like this to manage animations more easily

    public static void buildAnimations(AnimationManager.AnimationBuilder builder) {

        MiladyMoveset.build(builder); // use this builder from this and put it there
        QoLMiscAnimations.animBuild(builder);
        WingStanceAnims.build(builder);

        if(ModList.get().isLoaded(CombatEvolution.MOD_ID)){
            DawnDayExecAnims.build(builder);
        }


    }


}
