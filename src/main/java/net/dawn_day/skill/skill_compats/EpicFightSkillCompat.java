package net.dawn_day.skill.skill_compats;

import net.dawn_day.gameasset.animation.DawnDayAnimations;
import net.dawn_day.registry.entries.DawnDayItems;
import net.dawn_day.world.capabilities.item.DawnDayWeaponCategories;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import yesman.epicfight.api.client.event.EpicFightClientEventHooks;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.api.event.types.registry.SkillBuilderModificationEvent;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.registry.entries.EpicFightSkills;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.passive.SwordmasterSkill;

public class EpicFightSkillCompat implements ICompatModule {

    // Compiler says no :(
    // It will say yes now :) - sid

    public static void onSwordMasterSkillCreation(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(EpicFightSkills.SWORD_MASTER.getId())) {
            if (event.getSkillBuilder() instanceof SwordmasterSkill.Builder builder) {
                builder
                        .addAvailableWeaponCategory(DawnDayWeaponCategories.RITUS_DAGGER)
                        .addAvailableWeaponCategory(DawnDayWeaponCategories.CLAWS)
                        .addAvailableWeaponCategory(DawnDayWeaponCategories.SICKLE)
                        .addAvailableWeaponCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD)
                        .addAvailableWeaponCategory(DawnDayWeaponCategories.FLORETT);
            }
        }
    }
/*
    @SubscribeEvent
    public static void onGuardSkillcreate(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(EpicFightSkills.GUARD.getId())) {
            if (event.getSkillBuilder() instanceof GuardSkill.Builder builder) {

            builder.addGuardMotion(
                    DawnDayWeaponCategories.LIGHT_GREATSWORD,
                    (i, p) -> Animations.LONGSWORD_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.LIGHT_GREATSWORD,
                    (i, p) -> Animations.GREATSWORD_GUARD_BREAK);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.CLAWS,
                    (i, p) -> DawnDayAnimations.HOOKCLAWS_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.CLAWS,
                    (i, p) -> DawnDayAnimations.HOOKCLAWS_NEUTRALIZE);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.POLEBLADE,
                    (i, p) -> DawnDayAnimations.POLEBLADE_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.POLEBLADE,
                    (i, p) -> DawnDayAnimations.POLEBLADE_NEUTRALIZE);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.SICKLE,
                    (i, p) -> Animations.SWORD_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.SICKLE,
                    (i, p) -> Animations.BIPED_COMMON_NEUTRALIZED);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.BAT,
                    (i, p) -> Animations.GREATSWORD_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.BAT,
                    (i, p) -> Animations.GREATSWORD_GUARD_BREAK);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_BREAK);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.RITUS_DAGGER,
                    (i, p) -> DawnDayAnimations.RITUS_DAGGER_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.RITUS_DAGGER,
                    (i, p) -> DawnDayAnimations.RITUS_DAGGER_NEUTRALIZED);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.FLORETT,
                    (i, p) -> DawnDayAnimations.FLORETT_DUAL_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.FLORETT,
                    (i, p) -> DawnDayAnimations.FLORETT_DUAL_NEUTRALIZED);


        }
            }

    }

 */




    @Override
    public void onModEventBus(IEventBus iEventBus) {

    }

    @Override
    public void onGameEventBus(IEventBus iEventBus) {
        EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(EpicFightSkillCompat::onSwordMasterSkillCreation);

    }

    @Override
    public void onModEventBusClient(IEventBus iEventBus) {

    }

    @Override
    public void onGameEventBusClient(IEventBus iEventBus) {
        EpicFightClientEventHooks.Registry.WEAPON_CATEGORY_ICON.registerEvent(
                icon -> {
                    icon.registerCategory(DawnDayWeaponCategories.RITUS_DAGGER, new ItemStack(DawnDayItems.BLOOD_RITUS_DAGGER.get()));
                    icon.registerCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD, new ItemStack(DawnDayItems.IRON_LIGHT_GREATSWORD.get()));
                    icon.registerCategory(DawnDayWeaponCategories.FLORETT, new ItemStack(DawnDayItems.IRON_FLORETT.get()));
                }
        );

    }
}
