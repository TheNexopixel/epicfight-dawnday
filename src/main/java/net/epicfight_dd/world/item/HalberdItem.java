package net.epicfight_dd.world.item;

import net.minecraft.world.item.Tier;
import yesman.epicfight.world.item.WeaponItem;

public class HalberdItem extends WeaponItem {

    public HalberdItem(Tier materialIn) {
        super(materialIn, 4, -2.7F, new Properties().stacksTo(1));
    }


}