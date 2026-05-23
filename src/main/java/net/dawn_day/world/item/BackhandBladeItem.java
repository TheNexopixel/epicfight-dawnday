package net.dawn_day.world.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import yesman.epicfight.world.item.TieredWeaponItem;
import yesman.epicfight.world.item.WeaponItem;

public class BackhandBladeItem extends TieredWeaponItem
{
    public BackhandBladeItem(Tier materialIn) {
        super(materialIn, new Properties().stacksTo(1).attributes(createBackhandBladeAttributes(materialIn)));

    }

    public static ItemAttributeModifiers createBackhandBladeAttributes(Tier iter) {
        return TieredWeaponItem.createAttributes(iter, 3.0F, -1.5F, 0.0F);
    }


}
