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

    public static final RegistryObject<Item> light_great_sword =
            ITEMS.register("diamond_light_greatsword",() -> new light_greatsword_Item(Tiers.DIAMOND));

    public static final RegistryObject<Item> pole_axe =
            ITEMS.register("pole_axe",() -> new pole_axe_item(Tiers.NETHERITE));

    public static final RegistryObject<Item> war_sickle =
            ITEMS.register("war_sickle",() -> new war_sickle_item(Tiers.NETHERITE));


}
