package net.epicfight_dd.world.capabilities.item;

import net.minecraft.world.item.Item;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

public enum DawnDayWeaponCategories implements WeaponCategory {
    LIGHT_GREATSWORD,
    SICKLE,
    CLAWS,
    BAT,
    FLORETT,
    RITUS_DAGGER,
    IRON_FIST;

        final int id;

    DawnDayWeaponCategories() {
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
    }



    @Override
    public int universalOrdinal() {
        return this.id;
    }
    public CapabilityItem.Builder apply(Item item) {
        return WeaponCategoryMapper.apply(item,this);
    }

}




