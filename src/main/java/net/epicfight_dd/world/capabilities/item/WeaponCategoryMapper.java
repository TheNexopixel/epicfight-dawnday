package net.epicfight_dd.world.capabilities.item;

import net.minecraft.world.item.Item;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class WeaponCategoryMapper {
    private static final Map<DawnDayWeaponCategories, WeaponCategory> categoryMap = new HashMap<>();

    static {
        categoryMap.put(DawnDayWeaponCategories.IRON_FIST, CapabilityItem.WeaponCategories.FIST);
        categoryMap.put(DawnDayWeaponCategories.SICKLE, CapabilityItem.WeaponCategories.SWORD);
        categoryMap.put(DawnDayWeaponCategories.POLEBLADE, CapabilityItem.WeaponCategories.SWORD);
        categoryMap.put(DawnDayWeaponCategories.CLAWS, CapabilityItem.WeaponCategories.FIST);
        categoryMap.put(DawnDayWeaponCategories.BAT, CapabilityItem.WeaponCategories.GREATSWORD);
        categoryMap.put(DawnDayWeaponCategories.LIGHT_GREATSWORD, CapabilityItem.WeaponCategories.SWORD);
        categoryMap.put(DawnDayWeaponCategories.RITUS_DAGGER, CapabilityItem.WeaponCategories.DAGGER);
    }
    public static CapabilityItem.Builder apply(Item item, DawnDayWeaponCategories category) {
        WeaponCategory mappedCategory = categoryMap.getOrDefault(category, category);
        try {
            Method applyMethod = mappedCategory.getClass().getMethod("apply", Item.class);
            return (CapabilityItem.Builder) applyMethod.invoke(mappedCategory, item);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


