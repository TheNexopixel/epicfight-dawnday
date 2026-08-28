package net.dawn_day.skill.skill_compats;

import net.dawn_day.registry.entries.DawnDayItems;
import net.dawn_day.world.capabilities.item.DawnDayWeaponCategories;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import yesman.epicfight.api.client.event.EpicFightClientEventHooks;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.api.event.types.registry.SkillBuilderModificationEvent;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.registry.entries.EpicFightSkills;
import yesman.epicfight.skill.passive.SwordmasterSkill;

public class EpicFightSkillCompat implements ICompatModule {

    // Compiler says no :(
    // It will say yes now :) - sid

    public static void onSwordMasterSkillCreation(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(EpicFightSkills.SWORD_MASTER.getId())) {
            if (event.getSkillBuilder() instanceof SwordmasterSkill.Builder builder) {
                builder.addAvailableWeaponCategory(DawnDayWeaponCategories.EVIL_TACHI)
                        .addAvailableWeaponCategory(DawnDayWeaponCategories.RITUS_DAGGER)
                        .addAvailableWeaponCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD)
                        .addAvailableWeaponCategory(DawnDayWeaponCategories.FLORETT);
            }
        }
    }


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
