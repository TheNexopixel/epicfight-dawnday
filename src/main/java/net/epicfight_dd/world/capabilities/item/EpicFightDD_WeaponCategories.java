package net.epicfight_dd.world.capabilities.item;

import yesman.epicfight.world.capabilities.item.WeaponCategory;

public enum EpicFightDD_WeaponCategories implements WeaponCategory{
    LIGHT_GREATSWORD;
        final int id;

    EpicFightDD_WeaponCategories() {
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
    }



    @Override
    public int universalOrdinal() {
        return this.id;
    }


}




