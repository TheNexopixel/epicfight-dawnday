package net.dawn_day.skill.skill_compats;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.registry.entries.DawnDayItems;
import net.dawn_day.world.capabilities.item.DawnDayWeaponCategories;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import yesman.epicfight.api.client.event.types.registry.RegisterWeaponCategoryIconEvent;
import yesman.epicfight.api.event.types.registry.SkillBuilderModificationEvent;
import yesman.epicfight.registry.entries.EpicFightSkills;
import yesman.epicfight.skill.passive.SwordmasterSkill;

// @EventBusSubscriber(modid = EpicFightDawnDay.MOD_ID)
public class EpicFightSkillCompat {

    // Compiler says no :(
    /*
    @SubscribeEvent
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
    @SubscribeEvent
    public static void onWeaponCategoryIconCreation(RegisterWeaponCategoryIconEvent icon) {
        icon.registerCategory(DawnDayWeaponCategories.RITUS_DAGGER, new ItemStack(DawnDayItems.BLOOD_RITUS_DAGGER.get()));
        icon.registerCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD, new ItemStack(DawnDayItems.IRON_LIGHT_GREATSWORD.get()));
        icon.registerCategory(DawnDayWeaponCategories.FLORETT, new ItemStack(DawnDayItems.IRON_FLORETT.get()));
    }

     */

}
