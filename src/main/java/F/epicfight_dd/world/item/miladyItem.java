package F.epicfight_dd.world.item;
import net.minecraft.world.item.Tier;
import yesman.epicfight.world.item.WeaponItem;

public class miladyItem extends WeaponItem {

    public miladyItem(Tier materialIn) {
        super(materialIn, 3, -1.6F, new Properties().stacksTo(1));
    }

}