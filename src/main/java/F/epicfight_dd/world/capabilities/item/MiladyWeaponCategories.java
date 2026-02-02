package F.epicfight_dd.world.capabilities.item;

import yesman.epicfight.world.capabilities.item.WeaponCategory;

public enum MiladyWeaponCategories implements WeaponCategory{
        milady;

        final int id;

    MiladyWeaponCategories() {
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
    }



    @Override
    public int universalOrdinal() {
        return 0;
    }
}




