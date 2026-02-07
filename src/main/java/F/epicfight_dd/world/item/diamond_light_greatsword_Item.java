package F.epicfight_dd.world.item;

import net.minecraft.world.item.Tier;
import yesman.epicfight.world.item.WeaponItem;

public class diamond_light_greatsword_Item extends WeaponItem
{
    public diamond_light_greatsword_Item(Tier materialIn) {
        super(materialIn, 3, -2.0F, new Properties().stacksTo(1));

    }
}
