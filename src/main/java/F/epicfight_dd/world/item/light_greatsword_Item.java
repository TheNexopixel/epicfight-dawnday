package F.epicfight_dd.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import yesman.epicfight.world.item.WeaponItem;

public class light_greatsword_Item extends WeaponItem {
    public light_greatsword_Item(Tier materialIn) {
        super(materialIn, 3, -2.0F, new Item.Properties().stacksTo(1));

    }
}
