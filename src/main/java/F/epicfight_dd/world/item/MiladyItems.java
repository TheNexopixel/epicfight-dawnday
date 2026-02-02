package F.epicfight_dd.world.item;

import F.epicfight_dd.Epicfight_dd;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class MiladyItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Epicfight_dd.MODID);

    public static final RegistryObject<Item> milady =
            ITEMS.register("milady",() -> new miladyItem(Tiers.IRON));


}
