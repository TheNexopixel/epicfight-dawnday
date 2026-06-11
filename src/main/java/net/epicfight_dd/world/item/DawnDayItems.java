package net.epicfight_dd.world.item;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.effect.EffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.world.item.WeaponItem;

@SuppressWarnings("unused")
public class DawnDayItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Epicfight_dd.MODID);




    public static final RegistryObject<Item> backhand_blade =
            ITEMS.register("backhand_blade",() -> new SwordItem(Tiers.IRON,3,-2.4f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(2561)));

    public static final RegistryObject<Item> VITREUS =
            ITEMS.register("vitreus",() -> new SwordItem(Tiers.DIAMOND,3,-2.2f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(4561)));

    public static final RegistryObject<Item> IRON_FLORETT =
            ITEMS.register("iron_florett",() -> new SwordItem(Tiers.IRON,2,-2.05f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(861)));

    public static final RegistryObject<Item> GOLDEN_FLORETT =
            ITEMS.register("golden_florett",() -> new SwordItem(Tiers.GOLD,2,-1.9f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(861)));

    public static final RegistryObject<Item> DIAMOND_FLORETT =
            ITEMS.register("diamond_florett",() -> new SwordItem(Tiers.DIAMOND,2,-2.1f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(1861)));

    public static final RegistryObject<Item> NETHERITE_FLORETT =
            ITEMS.register("netherite_florett",() -> new SwordItem(Tiers.NETHERITE,2,-2.15f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(1861)));

    public static final RegistryObject<Item> ECLIPSE =
            ITEMS.register("eclipse",() -> new SwordItem(Tiers.NETHERITE,4,-2.7f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(5561)));

    public static final RegistryObject<Item> IRON_FIST =
            ITEMS.register("iron_fist",() -> new SwordItem(Tiers.IRON,1,-1.7f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(5561)));

    public static final RegistryObject<Item> diamond_messer =
            ITEMS.register("diamond_messer",() -> new SwordItem(Tiers.DIAMOND,2,-2.1f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(1561)));
    public static final RegistryObject<Item> golden_messer =
            ITEMS.register("golden_messer",() -> new SwordItem(Tiers.GOLD,3,-2.0f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(530)));
    public static final RegistryObject<Item> netherite_messer =
            ITEMS.register("netherite_messer",() -> new SwordItem(Tiers.NETHERITE,2,-2.1f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(2561)));

    public static final RegistryObject<Item> iron_messer =
            ITEMS.register("iron_messer",() -> new SwordItem(Tiers.IRON,2,-2.1f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(860)));

    public static final RegistryObject<Item> herb_sickle =
            ITEMS.register("herb_sickle",() -> new SwordItem(Tiers.DIAMOND,3,-2.2f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(2500)));

    public static final RegistryObject<Item> bat =
            ITEMS.register("bat",() -> new SwordItem(Tiers.WOOD,4,-2.5f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(840)));

    public static final RegistryObject<Item> saber =
            ITEMS.register("saber",() -> new SwordItem(Tiers.DIAMOND,2,-2.0f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(2500)));

    public static final RegistryObject<Item> steelaxe =
            ITEMS.register("steelaxe",() -> new WeaponItem(Tiers.NETHERITE,4,-2.7f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(2500)){});

    public static final RegistryObject<Item> NIGHT_RITUS_DAGGER =
            ITEMS.register("night_ritus_dagger",() -> new SwordItem(Tiers.DIAMOND,1,-2.0f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(2800)));

    public static final RegistryObject<Item> BLOOD_RITUS_DAGGER =
            ITEMS.register("blood_ritus_dagger",() ->  new SwordItem(Tiers.DIAMOND,2,-2.1f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(2100)));

    public static final RegistryObject<Item> iron_knife =
            ITEMS.register("iron_knife",() -> new SwordItem(Tiers.IRON,1,-2.2f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(860)));

    public static final RegistryObject<Item> diamond_knife =
            ITEMS.register("diamond_knife",() -> new SwordItem(Tiers.DIAMOND,1,-2.0f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(1360)));

    public static final RegistryObject<Item> netherite_knife =
            ITEMS.register("netherite_knife",() -> new SwordItem(Tiers.NETHERITE,2,-1.9f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(2360)));

    public static final RegistryObject<Item> golden_knife =
            ITEMS.register("golden_knife",() -> new SwordItem(Tiers.GOLD,2,-1.8f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.COMMON)
                            .defaultDurability(360)));

    public static final RegistryObject<Item> wooden_battlestaff =
            ITEMS.register("wooden_battlestaff",() -> new SwordItem(Tiers.WOOD,5,-2.4f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(650)));

    public static final RegistryObject<Item> iron_battlestaff =
            ITEMS.register("iron_battlestaff",() -> new SwordItem(Tiers.IRON,3,-2.5f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(980)));

    public static final RegistryObject<Item> golden_battlestaff =
            ITEMS.register("golden_battlestaff",() -> new WeaponItem(Tiers.GOLD,3,-2.2f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(680)){});

    public static final RegistryObject<Item> diamond_battlestaff =
            ITEMS.register("diamond_battlestaff",() -> new WeaponItem(Tiers.DIAMOND, 3, -2.5f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(1680)) {});

    public static final RegistryObject<Item> netherite_battlestaff =
            ITEMS.register("netherite_battlestaff",() -> new SwordItem(Tiers.NETHERITE,3,-2.5f,
                    new Item.Properties().stacksTo(1)
                            .defaultDurability(2680)));

    public static final RegistryObject<Item> spiked_bat =
            ITEMS.register("nailbat",() -> new SwordItem(Tiers.DIAMOND,4,-2.6f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)
                            .defaultDurability(2080)));

    public static final RegistryObject<Item> nail =
            ITEMS.register("nail",() -> new Item(
                    new Item.Properties().stacksTo(64)
                            .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> ruby =
            ITEMS.register("ruby",() -> new Item(
                    new Item.Properties().stacksTo(16)
                            .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> milady =
            ITEMS.register("milady",() -> new SwordItem(Tiers.NETHERITE,1,-2.4f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.EPIC)
                            .defaultDurability(3680)));

    public static final RegistryObject<Item> bonecutting_saw =
            ITEMS.register("bonecutting_saw",() -> new SwordItem(Tiers.NETHERITE,1,-2.8f,
                    new Item.Properties().stacksTo(1)
                            .rarity(Rarity.EPIC)
                            .defaultDurability(3680)));

    public static final RegistryObject<Item> netherite_light_greatsword =
            ITEMS.register("netherite_light_greatsword",() -> new light_greatsword_Item(Tiers.NETHERITE, 4,-2.0f,2442));

    public static final RegistryObject<Item> diamond_light_greatsword =
            ITEMS.register("diamond_light_greatsword",() -> new light_greatsword_Item(Tiers.DIAMOND, 4,-1.5f,1942));

    public static final RegistryObject<Item> golden_light_greatsword =
            ITEMS.register("golden_light_greatsword",() -> new light_greatsword_Item(Tiers.GOLD, 4,-1.5f,600));

    public static final RegistryObject<Item> iron_light_greatsword =
            ITEMS.register("iron_light_greatsword",() -> new light_greatsword_Item(Tiers.IRON, 3, -1.5f,961));

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

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, Epicfight_dd.MODID);

    public static final RegistryObject<Potion> EXHAUSTED =
            POTIONS.register("exhausted", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.EXHAUSTED.get(), 3600, 0)));

    public static final RegistryObject<Potion> EXHAUSTED_LONG =
            POTIONS.register("exhausted_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.EXHAUSTED.get(), 9600, 0)));

    public static final RegistryObject<Potion> EXHAUSTED_STRONG =
            POTIONS.register("exhausted_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.EXHAUSTED.get(), 2400, 1)));

    public static final RegistryObject<Potion> STUNARMOR =
            POTIONS.register("stun_armor", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.STUNARMOR.get(), 4000, 0)));

    public static final RegistryObject<Potion> STUNARMOR_LONG =
            POTIONS.register("stun_armor_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.STUNARMOR.get(), 10400, 0)));

    public static final RegistryObject<Potion> STUNARMOR_STRONG =
            POTIONS.register("stun_armor_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.STUNARMOR.get(), 2100, 1)));

    public static final RegistryObject<Potion> FORTIFIED =
            POTIONS.register("fortified", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.FORTIFIED.get(), 3600, 0)));

    public static final RegistryObject<Potion> FORTIFIED_LONG =
            POTIONS.register("fortified_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.FORTIFIED.get(), 9600, 0)));

    public static final RegistryObject<Potion> FORTIFIED_STRONG =
            POTIONS.register("fortified_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.FORTIFIED.get(), 1800, 1)));

    public static final RegistryObject<Potion> staminaregen =
            POTIONS.register("staminaregen_potion", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.ENDURANCE.get(), 3600, 0)));

    public static final RegistryObject<Potion> staminaregen_strong =
            POTIONS.register("staminaregen_potion_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.ENDURANCE.get(), 1700, 1)));

    public static final RegistryObject<Potion> staminaregen_long =
            POTIONS.register("staminaregen_potion_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.ENDURANCE.get(), 9600, 0)));

    public static final RegistryObject<Potion> maxstamina_long =
            POTIONS.register("stamina_potion_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.STAMNIA.get(), 9600, 0)));

    public static final RegistryObject<Potion> maxstamina_strong =
            POTIONS.register("stamina_potion_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.STAMNIA.get(), 1700, 1)));

    public static final RegistryObject<Potion> maxstamina =
            POTIONS.register("stamina_potion", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.STAMNIA.get(), 3600, 0)));

    public static final RegistryObject<Potion> powerpotion =
            POTIONS.register("potion_of_power", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.POTION_OF_POWER.get(), 1200, 0)));

    public static final RegistryObject<Potion> SWEEPING =
            POTIONS.register("sweeping", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.SWEEPING.get(), 1300, 0)));

    public static final RegistryObject<Potion> impactpotion_strong =
            POTIONS.register("potion_of_stronger_impact", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.IMPACT.get(), 1700, 1)));

    public static final RegistryObject<Potion> impactpotion_long =
            POTIONS.register("potion_of_longer_impact", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.IMPACT.get(), 9600, 0)));

    public static final RegistryObject<Potion> impactpotion =
            POTIONS.register("potion_of_impact", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.IMPACT.get(), 3600, 0)));

    public static final RegistryObject<Potion> CRUMBLING =
            POTIONS.register("crumbling", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CRUMBLING.get(), 3600, 0)));

    public static final RegistryObject<Potion> CRUMBLING_LONG =
            POTIONS.register("crumbling_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CRUMBLING.get(), 6666, 0)));

    public static final RegistryObject<Potion> CRUMBLING_STRONG =
            POTIONS.register("crumbling_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CRUMBLING.get(), 2100, 1)));

    public static final RegistryObject<Potion> CURSED =
            POTIONS.register("cursed", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CURSED.get(), 2960, 0)));

    public static final RegistryObject<Potion> CURSED_LONG =
            POTIONS.register("cursed_long", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CURSED.get(), 6666, 0)));

    public static final RegistryObject<Potion> CURSED_STRONG =
            POTIONS.register("cursed_strong", () ->
                    new Potion(new MobEffectInstance(EffectRegistry.CURSED.get(), 1929, 1)));

    public static final RegistryObject<Item> ENERGY_DRINK = ITEMS.register(
            "energydrink",
            () -> new SpecialDrinkItem(
                    new Item.Properties()
                            .stacksTo(4).craftRemainder(Items.IRON_NUGGET),

                    new MobEffectInstance(EffectRegistry.ENDURANCE.get(),
                            1200,
                            2
                    ),

                    new MobEffectInstance(EffectRegistry.STAMNIA.get(),
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
