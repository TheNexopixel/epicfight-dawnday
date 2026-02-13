package F.epicfight_dd.world.item;

import net.minecraft.world.item.Tier;
import yesman.epicfight.world.item.WeaponItem;

public class halbeardItem extends WeaponItem {

    public halbeardItem(Tier materialIn) {
        super(materialIn, 4, -2.0F, new Properties().stacksTo(1));
    }


}