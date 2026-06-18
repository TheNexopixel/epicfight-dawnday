package net.dawn_day.world.capabilities.item;

import net.minecraft.world.item.Item;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

public enum DawnDayWeaponCategories implements WeaponCategory {
    LIGHT_GREATSWORD,
    EVIL_TACHI,
    FLORETT,
    IRON_FIST,
    RITUS_DAGGER;

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




