package F.epicfight_dd.world.item;

import net.minecraft.world.item.Tier;
import yesman.epicfight.world.item.WeaponItem;

public class pole_axe_item extends WeaponItem
{
    public pole_axe_item(Tier materialIn) {
        super(materialIn, 3, -2.0F, new Properties().stacksTo(1));

    }
}
