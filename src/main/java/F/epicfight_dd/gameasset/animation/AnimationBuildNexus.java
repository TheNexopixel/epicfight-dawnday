package F.epicfight_dd.gameasset.animation;

import yesman.epicfight.api.animation.AnimationManager;


public class AnimationBuildNexus {
// we register like this to manage animations more easily

    public static void buildAnimations(AnimationManager.AnimationBuilder builder) {
       MiladyMoveset.build(builder); // use this builder from this and put it there
    }



}
