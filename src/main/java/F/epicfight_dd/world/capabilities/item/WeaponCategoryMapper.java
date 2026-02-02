package F.epicfight_dd.world.capabilities.item;

import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.HashMap;
import java.util.Map;

public class WeaponCategoryMapper {
    private static final Map<MiladyWeaponCategories, WeaponCategory> categoryMap = new HashMap<>();
    static {
        categoryMap.put(MiladyWeaponCategories.milady, CapabilityItem.WeaponCategories.SWORD);
    }
}
