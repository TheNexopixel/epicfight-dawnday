package net.dawn_day.registry.entries;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.world.item.HalberdItem;
import net.dawn_day.world.item.LightGreatswordItem;
import net.dawn_day.world.item.SpecialDrinkItem;
import net.dawn_day.world.item.WarSickleItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public final class DawnDayItems {
    private DawnDayItems() {}
    public static final DeferredRegister.Items REGISTRY =
            DeferredRegister.createItems(EpicFightDawnDay.MOD_ID);

    public static final DeferredItem<Item> BACKHAND_BLADE =
            REGISTRY.register("backhand_blade", () -> new SwordItem(Tiers.IRON,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 3, -2.4f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2561)));

    public static final DeferredItem<Item> DIAMOND_MESSER =
            REGISTRY.register("diamond_messer", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 2, -2.1f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(1561)));

    public static final DeferredItem<Item> GOLDEN_MESSER =
            REGISTRY.register("golden_messer", () -> new SwordItem(Tiers.GOLD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.GOLD, 3, -2.0f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(530)));

    public static final DeferredItem<Item> NETHERITE_MESSER =
            REGISTRY.register("netherite_messer", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -2.1f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(2561)));

    public static final DeferredItem<Item> IRON_MESSER =
            REGISTRY.register("iron_messer", () -> new SwordItem(Tiers.IRON,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 2, -2.1f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(860)));

    public static final DeferredItem<Item> HERB_SICKLE =
            REGISTRY.register("herb_sickle", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 3, -2.2f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2500)));

    public static final DeferredItem<Item> BAT =
            REGISTRY.register("bat", () -> new SwordItem(Tiers.WOOD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.WOOD, 4, -2.5f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(840)));

    public static final DeferredItem<Item> SABER =
            REGISTRY.register("saber", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 2, -2.0f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2500)));

    public static final DeferredItem<Item> STEEL_AXE =
            REGISTRY.register("steelaxe", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 4, -2.7f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2500)));

    public static final DeferredItem<Item> NIGHT_RITUS_DAGGER =
            REGISTRY.register("night_ritus_dagger", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 1, -2.0f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2800)));

    public static final DeferredItem<Item> BLOOD_RITUS_DAGGER =
            REGISTRY.register("blood_ritus_dagger", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 2, -2.1f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2100)));

    public static final DeferredItem<Item> IRON_KNIFE =
            REGISTRY.register("iron_knife", () -> new SwordItem(Tiers.IRON,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 1, -2.2f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(860)));

    public static final DeferredItem<Item> DIAMOND_KNIFE =
            REGISTRY.register("diamond_knife", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 1, -2.0f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(1360)));

    public static final DeferredItem<Item> NETHERITE_KNIFE =
            REGISTRY.register("netherite_knife", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -1.9f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(2360)));

    public static final DeferredItem<Item> GOLDEN_KNIFE =
            REGISTRY.register("golden_knife", () -> new SwordItem(Tiers.GOLD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.GOLD, 2, -1.8f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(360)));

    public static final DeferredItem<Item> WOODEN_BATTLESTAFF =
            REGISTRY.register("wooden_battlestaff", () -> new SwordItem(Tiers.WOOD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.WOOD, 5, -2.2f)).stacksTo(1)
                            .durability(1450)));

    public static final DeferredItem<Item> IRON_BATTLESTAFF =
            REGISTRY.register("iron_battlestaff", () -> new SwordItem(Tiers.IRON,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 3, -2.5f)).stacksTo(1)
                            .durability(980)));

    public static final DeferredItem<Item> GOLDEN_BATTLESTAFF =
            REGISTRY.register("golden_battlestaff", () -> new SwordItem(Tiers.GOLD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.GOLD, 3, -2.2f)).stacksTo(1)
                            .durability(680)));

    public static final DeferredItem<Item> DIAMOND_BATTLESTAFF =
            REGISTRY.register("diamond_battlestaff", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 3, -2.5f)).stacksTo(1)
                            .durability(1680)));

    public static final DeferredItem<Item> NETHERITE_BATTLESTAFF =
            REGISTRY.register("netherite_battlestaff", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 3, -2.5f)).stacksTo(1)
                            .durability(2680)));

    public static final DeferredItem<Item> NAIL_BAT =
            REGISTRY.register("nailbat", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 4, -2.6f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2080)));

    public static final DeferredItem<Item> NAIL =
            REGISTRY.register("nail", () -> new Item(
                    new Item.Properties().stacksTo(64)
                            .rarity(Rarity.COMMON)));

    public static final DeferredItem<Item> MILADY =
            REGISTRY.register("milady", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 1, -2.4f)).stacksTo(1)
                            .rarity(Rarity.EPIC)
                            .durability(3680)));

    public static final DeferredItem<Item> BONECUTTING_SAW =
            REGISTRY.register("bonecutting_saw", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -2.8f)).stacksTo(1)
                            .rarity(Rarity.EPIC)
                            .durability(3680)));
    public static final DeferredItem<Item> NETHERITE_LIGHT_GREATSWORD =
            REGISTRY.register("netherite_light_greatsword", () -> new LightGreatswordItem(Tiers.NETHERITE, 2442));

    public static final DeferredItem<Item> DIAMOND_LIGHT_GREATSWORD =
            REGISTRY.register("diamond_light_greatsword", () -> new LightGreatswordItem(Tiers.DIAMOND, 1942));

    public static final DeferredItem<Item> GOLDEN_LIGHT_GREATSWORD =
            REGISTRY.register("golden_light_greatsword", () -> new LightGreatswordItem(Tiers.GOLD, 600));

    public static final DeferredItem<Item> IRON_LIGHT_GREATSWORD =
            REGISTRY.register("iron_light_greatsword", () -> new LightGreatswordItem(Tiers.IRON, 961));

    public static final DeferredItem<Item> WAR_SICKLE =
            REGISTRY.register("war_sickle", () -> new WarSickleItem(Tiers.NETHERITE));

    public static final DeferredItem<Item> DIAMOND_HALBERD =
            REGISTRY.register("diamond_halberd", () -> new HalberdItem(Tiers.DIAMOND));

    public static final DeferredItem<Item> GOLDEN_HALBERD =
            REGISTRY.register("golden_halberd", () -> new HalberdItem(Tiers.GOLD));

    public static final DeferredItem<Item> IRON_HALBERD =
            REGISTRY.register("iron_halberd", () -> new HalberdItem(Tiers.IRON));

    public static final DeferredItem<Item> NETHERITE_HALBERD =
            REGISTRY.register("netherite_halberd", () -> new HalberdItem(Tiers.NETHERITE));

    public static final DeferredItem<Item> ENERGY_DRINK = REGISTRY.register(
            "energydrink",
            () -> new SpecialDrinkItem(
                    new Item.Properties()
                            .stacksTo(4).craftRemainder(Items.IRON_NUGGET),

                    new MobEffectInstance(DawnDayEffects.RECOVERY,
                            1200,
                            2
                    ),

                    new MobEffectInstance(DawnDayEffects.ENDURANCE,
                            1000,
                            0
                    ),

                    new MobEffectInstance(
                            MobEffects.MOVEMENT_SPEED,
                            1100,
                            1
                    ),
                    new MobEffectInstance(
                            MobEffects.DIG_SPEED,
                            900,
                            0
                    )
            )
    );

}
