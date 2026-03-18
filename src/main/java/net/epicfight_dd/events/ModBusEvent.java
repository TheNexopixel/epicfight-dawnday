package net.epicfight_dd.events;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.AnimationBuildNexus;
import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvent {

    @SubscribeEvent //register animation on the mod bus to make it official
    public static void registerAnimation(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder(Epicfight_dd.MODID, AnimationBuildNexus::buildAnimations);
    }








}
