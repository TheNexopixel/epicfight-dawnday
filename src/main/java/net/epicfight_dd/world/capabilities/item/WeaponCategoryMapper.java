package net.epicfight_dd.world.capabilities.item;

import net.minecraft.world.item.Item;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class WeaponCategoryMapper {
    private static final Map<EpicFightDD_WeaponCategories, WeaponCategory> categoryMap = new HashMap<>();

    static {
        categoryMap.put(EpicFightDD_WeaponCategories.EVIL_TACHI, CapabilityItem.WeaponCategories.TACHI);
        categoryMap.put(EpicFightDD_WeaponCategories.IRON_FIST, CapabilityItem.WeaponCategories.FIST);
        categoryMap.put(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD, CapabilityItem.WeaponCategories.SWORD);
        categoryMap.put(EpicFightDD_WeaponCategories.RITUS_DAGGER, CapabilityItem.WeaponCategories.DAGGER);
    }
    public static CapabilityItem.Builder apply(Item item, EpicFightDD_WeaponCategories category) {
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


