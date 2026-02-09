package F.epicfight_dd.world.item;

import F.epicfight_dd.Epicfight_dd;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class DawnDayItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Epicfight_dd.MODID);

    public static final RegistryObject<Item> milady =
            ITEMS.register("milady",() -> new miladyItem(Tiers.NETHERITE));

    public static final RegistryObject<Item> backhand_blade =
            ITEMS.register("backhand_blade",() -> new SwordItem(Tiers.IRON,3,-1.5f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(1261)));

    public static final RegistryObject<Item> diamond_light_greatsword =
            ITEMS.register("diamond_light_greatsword",() -> new light_greatsword_Item(Tiers.DIAMOND, 4,-1.5f,1942));

    public static final RegistryObject<Item> iron_light_greatsword =
            ITEMS.register("iron_light_greatsword",() -> new light_greatsword_Item(Tiers.IRON, 3, -2.0f,961));

    public static final RegistryObject<Item> war_sickle =
            ITEMS.register("war_sickle",() -> new war_sickle_item(Tiers.NETHERITE));


}
