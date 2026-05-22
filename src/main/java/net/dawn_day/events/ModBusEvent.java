package net.dawn_day.events;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.gameasset.animation.AnimationBuildNexus;
import yesman.epicfight.api.animation.AnimationManager;

public class ModBusEvent {

    public static void registerAnimation(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder(EpicFightDawnDay.MOD_ID, AnimationBuildNexus::buildAnimations);
    }


}
