package net.epicfight_dd.world.item;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
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
            ITEMS.register("milady",() -> new miladyItem(Tiers.IRON));

    public static final RegistryObject<Item> backhand_blade =
            ITEMS.register("backhand_blade",() -> new SwordItem(Tiers.IRON,3,-1.5f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(1261))
                    );
    public static final RegistryObject<Item> herb_sickle =
            ITEMS.register("herb_sickle",() -> new SwordItem(Tiers.DIAMOND,3,-1.5f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(2500)));

    public static final RegistryObject<Item> iron_battlestaff =
            ITEMS.register("iron_battlestaff",() -> new SwordItem(Tiers.IRON,3,-2.5f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(680)));

    public static final RegistryObject<Item> golden_battlestaff =
            ITEMS.register("golden_battlestaff",() -> new SwordItem(Tiers.GOLD,3,-2.4f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(380)));

    public static final RegistryObject<Item> diamond_battlestaff =
            ITEMS.register("diamond_battlestaff",() -> new SwordItem(Tiers.DIAMOND,3,-2.5f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(1680)));

    public static final RegistryObject<Item> netherite_battlestaff =
            ITEMS.register("netherite_battlestaff",() -> new SwordItem(Tiers.NETHERITE,3,-2.5f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(2680)));

    public static final RegistryObject<Item> netherite_light_greatsword =
            ITEMS.register("netherite_light_greatsword",() -> new light_greatsword_Item(Tiers.NETHERITE, 4,-1.5f,2442));

    public static final RegistryObject<Item> diamond_light_greatsword =
            ITEMS.register("diamond_light_greatsword",() -> new light_greatsword_Item(Tiers.DIAMOND, 4,-1.5f,1942));

    public static final RegistryObject<Item> golden_light_greatsword =
            ITEMS.register("golden_light_greatsword",() -> new light_greatsword_Item(Tiers.GOLD, 4,-1.5f,600));

    public static final RegistryObject<Item> iron_light_greatsword =
            ITEMS.register("iron_light_greatsword",() -> new light_greatsword_Item(Tiers.IRON, 3, -2.0f,961));

    public static final RegistryObject<Item> war_sickle =
            ITEMS.register("war_sickle",() -> new war_sickle_item(Tiers.NETHERITE));

    public static final RegistryObject<Item> diamond_halberd =
            ITEMS.register("diamond_halberd",() -> new halbeardItem(Tiers.DIAMOND));

    public static final RegistryObject<Item> golden_halberd =
            ITEMS.register("golden_halberd",() -> new halbeardItem(Tiers.GOLD));

    public static final RegistryObject<Item> iron_halberd =
            ITEMS.register("iron_halberd",() -> new halbeardItem(Tiers.IRON));

    public static final RegistryObject<Item> netherite_halberd =
            ITEMS.register("netherite_halberd",() -> new halbeardItem(Tiers.NETHERITE));


}
