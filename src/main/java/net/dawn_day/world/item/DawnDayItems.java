package net.dawn_day.world.item;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.effect.EffectRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public class DawnDayItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(EpicFightDawnDay.MOD_ID);

    public static final DeferredItem<Item> BACKHAND_BLADE =
            ITEMS.register("backhand_blade", () -> new SwordItem(Tiers.IRON,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 3, -2.4f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2561)));

    public static final DeferredItem<Item> DIAMOND_MESSER =
            ITEMS.register("diamond_messer", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 2, -2.1f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(1561)));

    public static final DeferredItem<Item> GOLDEN_MESSER =
            ITEMS.register("golden_messer", () -> new SwordItem(Tiers.GOLD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.GOLD, 3, -2.0f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(530)));

    public static final DeferredItem<Item> NETHERITE_MESSER =
            ITEMS.register("netherite_messer", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -2.1f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(2561)));

    public static final DeferredItem<Item> IRON_MESSER =
            ITEMS.register("iron_messer", () -> new SwordItem(Tiers.IRON,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 2, -2.1f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(860)));

    public static final DeferredItem<Item> HERB_SICKLE =
            ITEMS.register("herb_sickle", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 3, -2.2f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2500)));

    public static final DeferredItem<Item> BAT =
            ITEMS.register("bat", () -> new SwordItem(Tiers.WOOD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.WOOD, 4, -2.5f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(840)));

    public static final DeferredItem<Item> SABER =
            ITEMS.register("saber", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 2, -2.0f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2500)));

    public static final DeferredItem<Item> STEEL_AXE =
            ITEMS.register("steelaxe", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 4, -2.7f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2500)));

    public static final DeferredItem<Item> NIGHT_RITUS_DAGGER =
            ITEMS.register("night_ritus_dagger", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 1, -2.0f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2800)));

    public static final DeferredItem<Item> BLOOD_RITUS_DAGGER =
            ITEMS.register("blood_ritus_dagger", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 2, -2.1f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2100)));

    public static final DeferredItem<Item> IRON_KNIFE =
            ITEMS.register("iron_knife", () -> new SwordItem(Tiers.IRON,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 1, -2.2f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(860)));

    public static final DeferredItem<Item> DIAMOND_KNIFE =
            ITEMS.register("diamond_knife", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 1, -2.0f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(1360)));

    public static final DeferredItem<Item> NETHERITE_KNIFE =
            ITEMS.register("netherite_knife", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -1.9f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(2360)));

    public static final DeferredItem<Item> GOLDEN_KNIFE =
            ITEMS.register("golden_knife", () -> new SwordItem(Tiers.GOLD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.GOLD, 2, -1.8f)).stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .durability(360)));

    public static final DeferredItem<Item> WOODEN_BATTLESTAFF =
            ITEMS.register("wooden_battlestaff", () -> new SwordItem(Tiers.WOOD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.WOOD, 5, -2.2f)).stacksTo(1)
                            .durability(1450)));

    public static final DeferredItem<Item> IRON_BATTLESTAFF =
            ITEMS.register("iron_battlestaff", () -> new SwordItem(Tiers.IRON,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 3, -2.5f)).stacksTo(1)
                            .durability(980)));

    public static final DeferredItem<Item> GOLDEN_BATTLESTAFF =
            ITEMS.register("golden_battlestaff", () -> new SwordItem(Tiers.GOLD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.GOLD, 3, -2.2f)).stacksTo(1)
                            .durability(680)));

    public static final DeferredItem<Item> DIAMOND_BATTLESTAFF =
            ITEMS.register("diamond_battlestaff", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 3, -2.5f)).stacksTo(1)
                            .durability(1680)));

    public static final DeferredItem<Item> NETHERITE_BATTLESTAFF =
            ITEMS.register("netherite_battlestaff", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 3, -2.5f)).stacksTo(1)
                            .durability(2680)));

    public static final DeferredItem<Item> NAIL_BAT =
            ITEMS.register("nailbat", () -> new SwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 4, -2.6f)).stacksTo(1)
                            .rarity(Rarity.RARE)
                            .durability(2080)));

    public static final DeferredItem<Item> NAIL =
            ITEMS.register("nail", () -> new Item(
                    new Item.Properties().stacksTo(64)
                            .rarity(Rarity.COMMON)));

    public static final DeferredItem<Item> MILADY =
            ITEMS.register("milady", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 1, -2.4f)).stacksTo(1)
                            .rarity(Rarity.EPIC)
                            .durability(3680)));

    public static final DeferredItem<Item> BONECUTTING_SAW =
            ITEMS.register("bonecutting_saw", () -> new SwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -2.8f)).stacksTo(1)
                            .rarity(Rarity.EPIC)
                            .durability(3680)));
    public static final DeferredItem<Item> NETHERITE_LIGHT_GREATSWORD =
            ITEMS.register("netherite_light_greatsword", () -> new LightGreatswordItem(Tiers.NETHERITE, 2442));

    public static final DeferredItem<Item> DIAMOND_LIGHT_GREATSWORD =
            ITEMS.register("diamond_light_greatsword", () -> new LightGreatswordItem(Tiers.DIAMOND, 1942));

    public static final DeferredItem<Item> GOLDEN_LIGHT_GREATSWORD =
            ITEMS.register("golden_light_greatsword", () -> new LightGreatswordItem(Tiers.GOLD, 600));

    public static final DeferredItem<Item> IRON_LIGHT_GREATSWORD =
            ITEMS.register("iron_light_greatsword", () -> new LightGreatswordItem(Tiers.IRON, 961));

    public static final DeferredItem<Item> WAR_SICKLE =
            ITEMS.register("war_sickle", () -> new WarSickleItem(Tiers.NETHERITE));

    public static final DeferredItem<Item> DIAMOND_HALBERD =
            ITEMS.register("diamond_halberd", () -> new HalberdItem(Tiers.DIAMOND));

    public static final DeferredItem<Item> GOLDEN_HALBERD =
            ITEMS.register("golden_halberd", () -> new HalberdItem(Tiers.GOLD));

    public static final DeferredItem<Item> IRON_HALBERD =
            ITEMS.register("iron_halberd", () -> new HalberdItem(Tiers.IRON));

    public static final DeferredItem<Item> NETHERITE_HALBERD =
            ITEMS.register("netherite_halberd", () -> new HalberdItem(Tiers.NETHERITE));

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, EpicFightDawnDay.MOD_ID);

    public static final DeferredHolder<Potion, Potion> STUNARMOR =
            POTIONS.register("stun_armor", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.UNFLINCHING, 4000, 0)));

    public static final DeferredHolder<Potion, Potion> STUNARMOR_LONG =
            POTIONS.register("stun_armor_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.UNFLINCHING, 10400, 0)));

    public static final DeferredHolder<Potion, Potion> STUNARMOR_STRONG =
            POTIONS.register("stun_armor_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.UNFLINCHING, 2100, 1)));

    public static final DeferredHolder<Potion, Potion> FORTIFIED =
            POTIONS.register("fortified", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.FORTIFIED, 3600, 0)));

    public static final DeferredHolder<Potion, Potion> FORTIFIED_LONG =
            POTIONS.register("fortified_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.FORTIFIED, 9600, 0)));

    public static final DeferredHolder<Potion, Potion> FORTIFIED_STRONG =
            POTIONS.register("fortified_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.FORTIFIED, 1800, 1)));

    public static final DeferredHolder<Potion, Potion> STAMINA_STIMULANT =
            POTIONS.register("staminaregen_potion", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.RECOVERY, 3600, 0)));

    public static final DeferredHolder<Potion, Potion> STRONG_STAMINA_STIMULANT =
            POTIONS.register("staminaregen_potion_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.RECOVERY, 1700, 1)));

    public static final DeferredHolder<Potion, Potion> LONG_STAMINA_STIMULANT =
            POTIONS.register("staminaregen_potion_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.RECOVERY, 9600, 0)));

    public static final DeferredHolder<Potion, Potion> LONG_STAMINA_POTION =
            POTIONS.register("stamina_potion_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.ENDURANCE, 9600, 0)));

    public static final DeferredHolder<Potion, Potion> STRONG_STAMINA_POTION =
            POTIONS.register("stamina_potion_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.ENDURANCE, 1700, 1)));

    public static final DeferredHolder<Potion, Potion> STAMINA_POTION =
            POTIONS.register("stamina_potion", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.ENDURANCE, 3600, 0)));

    public static final DeferredHolder<Potion, Potion> POWER_POTION =
            POTIONS.register("potion_of_power", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.EMPOWERED, 1200, 0)));

    public static final DeferredHolder<Potion, Potion> SWEEPING =
            POTIONS.register("sweeping", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.SWEEPING, 1300, 0)));

    public static final DeferredHolder<Potion, Potion> impactpotion_strong =
            POTIONS.register("potion_of_stronger_impact", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CRUSHING, 1700, 1)));

    public static final DeferredHolder<Potion, Potion> impactpotion_long =
            POTIONS.register("potion_of_longer_impact", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CRUSHING, 9600, 0)));

    public static final DeferredHolder<Potion, Potion> impactpotion =
            POTIONS.register("potion_of_impact", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CRUSHING, 3600, 0)));

    public static final DeferredHolder<Potion, Potion> CRUMBLING =
            POTIONS.register("crumbling", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CRUMBLING, 3600, 0)));

    public static final DeferredHolder<Potion, Potion> CRUMBLING_LONG =
            POTIONS.register("crumbling_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CRUMBLING, 6666, 0)));

    public static final DeferredHolder<Potion, Potion> CRUMBLING_STRONG =
            POTIONS.register("crumbling_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CRUMBLING, 2100, 1)));

    public static final DeferredHolder<Potion, Potion> CURSED =
            POTIONS.register("cursed", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CURSED, 2960, 0)));

    public static final DeferredHolder<Potion, Potion> CURSED_LONG =
            POTIONS.register("cursed_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CURSED, 6666, 0)));

    public static final DeferredHolder<Potion, Potion> CURSED_STRONG =
            POTIONS.register("cursed_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CURSED, 1929, 1)));

    public static final DeferredItem<Item> ENERGY_DRINK = ITEMS.register(
            "energydrink",
            () -> new SpecialDrinkItem(
                    new Item.Properties()
                            .stacksTo(4).craftRemainder(Items.IRON_NUGGET),

                    new MobEffectInstance(EffectRegistry.RECOVERY,
                            1200,
                            2
                    ),

                    new MobEffectInstance(EffectRegistry.ENDURANCE,
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
