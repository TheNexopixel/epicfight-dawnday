package net.dawn_day.world.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import yesman.epicfight.world.item.TieredWeaponItem;
import yesman.epicfight.world.item.WeaponItem;

public class HalberdItem extends WeaponItem {

    public HalberdItem(Tier materialIn) {
        super(new Properties().attributes(createHalberdAttributes(materialIn)).stacksTo(1));
    }

    public static ItemAttributeModifiers createHalberdAttributes(Tier iter) {
        return TieredWeaponItem.createAttributes(iter, 4.0F, -2.8F, 0.0F);
    }
}