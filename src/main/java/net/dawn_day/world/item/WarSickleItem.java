package net.dawn_day.world.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import yesman.epicfight.world.item.TieredWeaponItem;
import yesman.epicfight.world.item.WeaponItem;

public class WarSickleItem extends TieredWeaponItem
{
    public WarSickleItem(Tier materialIn) {
        super(materialIn, new Properties().stacksTo(1).attributes(createAttributes(materialIn)).rarity(Rarity.RARE));
    }

    public static ItemAttributeModifiers createAttributes(Tier tier)
    {
        return TieredWeaponItem.createAttributes(tier, 3.0F, -2.7F, 0.0F);
    }


}
