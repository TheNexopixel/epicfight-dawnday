package net.epicfight_dd.skill.skill_compats;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;

import java.util.List;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DefaultEpicFightSkillCompat {


    @SubscribeEvent
    public static void onGuardSkillcreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<GuardSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","guard"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();

            builder.addGuardMotion(
                    EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                    (i, p) -> Animations.LONGSWORD_GUARD_HIT
            ).addGuardBreakMotion(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                    (i, p) -> Animations.GREATSWORD_GUARD_BREAK);


        }

    }

    @SubscribeEvent
    public static void onParrySkillcreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<GuardSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","parrying"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();

            builder.addGuardMotion(
                            EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> Animations.LONGSWORD_GUARD_HIT
                    ).addGuardBreakMotion(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> Animations.GREATSWORD_GUARD_BREAK)
                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> List.of(
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT1,
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            ));
        }

    }

    //TODO:ADD ICON CREATION COMP


}
