package net.epicfight_dd.world.item;

import net.epicfight_dd.EpicFightDawnDay;
import net.epicfight_dd.effect.EffectRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.world.item.WeaponItem;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class DawnDayItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EpicFightDawnDay.MODID);

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, EpicFightDawnDay.MODID);

    private static final ChatFormatting WHITE = ChatFormatting.WHITE;
    private static final ChatFormatting GRAY = ChatFormatting.GRAY;
    private static final ChatFormatting BOLD = ChatFormatting.BOLD;

    private static TooltipLine tip(String key, ChatFormatting... formats) {
        return new TooltipLine("tooltip.epicfight_dd." + key, formats);
    }

    private static Item.Properties gear(Rarity rarity, int durability) {
        return new Item.Properties().stacksTo(1).rarity(rarity).defaultDurability(durability);
    }

    private static RegistryObject<Item> register(String name, Supplier<? extends Item> item) {
        return ITEMS.register(name, item);
    }

    private static RegistryObject<Item> registerSword(String name, Tier tier, int damage, float speed, Rarity rarity, int durability) {
        return register(name, () -> new SwordItem(tier, damage, speed, gear(rarity, durability)));
    }

    private static RegistryObject<Item> registerWeapon(String name, Tier tier, int damage, float speed, Rarity rarity, int durability) {
        return register(name, () -> new WeaponItem(tier, damage, speed, gear(rarity, durability)) {});
    }

    private static RegistryObject<Item> registerTooltip(String name, Tier tier, int damage, float speed, Supplier<Item.Properties> properties, TooltipLine... lines) {
        return register(name, () -> new DawnDayTooltips(tier, damage, speed, properties.get(), lines));
    }

    private static RegistryObject<Item> registerHalberd(String name, Tier tier) {
        return register(name, () -> new HalberdItem(tier));
    }

    private static RegistryObject<Item> registerBasic(String name, int stackSize) {
        return register(name, () -> new Item(new Item.Properties().stacksTo(stackSize).rarity(Rarity.COMMON)));
    }

    private static RegistryObject<Potion> registerPotion(String name, Supplier<? extends MobEffect> effect, int duration, int amplifier) {
        return POTIONS.register(name, () -> new Potion(new MobEffectInstance(effect.get(), duration, amplifier)));
    }

    // ------------------------------------------------------------ tooltip weapons

    public static final RegistryObject<Item> POLEBLADE = registerTooltip("poleblade", Tiers.DIAMOND, 0, -2.0F,
            () -> new Item.Properties().rarity(Rarity.EPIC),
            tip("blood_ritus_dagger_headline", WHITE, BOLD),
            tip("blood_ritus_dagger_passive", WHITE),
            tip("poleblade_text1", GRAY),
            tip("poleblade_text2", GRAY));

    public static final RegistryObject<Item> RUSTY_LUMBERJACK_AXE = registerTooltip("rusty_axe", Tiers.IRON, 2, -2.3F,
            () -> new Item.Properties().rarity(Rarity.COMMON),
            tip("rusty_axe", GRAY));

    public static final RegistryObject<Item> CRESCENDO = registerTooltip("crescendo", Tiers.NETHERITE, -4, -2.3F,
            () -> new Item.Properties().rarity(Rarity.EPIC),
            tip("weapon_passive", WHITE, BOLD),
            tip("crescendo_passive", WHITE),
            tip("crescendo", GRAY));

    public static final RegistryObject<Item> HORUS_FANG = registerTooltip("horus_fang", Tiers.IRON, 3, -2.2F,
            () -> gear(Rarity.COMMON, 1205),
            tip("horus_fang", GRAY));

    public static final RegistryObject<Item> SANGUINEUS = registerTooltip("sanguineus", Tiers.DIAMOND, 2, -1.4F,
            () -> gear(Rarity.COMMON, 1205),
            tip("weapon_passive", WHITE, BOLD),
            tip("blood_loss_passive", WHITE),
            tip("sanguineus", GRAY));

    public static final RegistryObject<Item> TWINBLADE = registerTooltip("twinblade", Tiers.IRON, 2, -1.4F,
            () -> gear(Rarity.COMMON, 1205),
            tip("weapon_passive", WHITE, BOLD),
            tip("blood_loss_passive", WHITE),
            tip("twinblade", GRAY));

    public static final RegistryObject<Item> REVENANT = registerTooltip("revenant", Tiers.STONE, 8, -2.8F,
            () -> gear(Rarity.COMMON, 1505),
            tip("revenant", GRAY));

    public static final RegistryObject<Item> GLORIA = registerTooltip("gloria", Tiers.NETHERITE, 0, -2.0F,
            () -> gear(Rarity.RARE, 5205),
            tip("gloria", GRAY));

    public static final RegistryObject<Item> HOOKCLAWS = registerTooltip("hookclaws", Tiers.IRON, 2, -1.6F,
            () -> gear(Rarity.COMMON, 1205),
            tip("weapon_passive", WHITE, BOLD),
            tip("blood_loss_passive", WHITE),
            tip("hookclaws", GRAY));

    public static final RegistryObject<Item> GRIMM = registerTooltip("grimm", Tiers.NETHERITE, 2, -2.5F,
            () -> gear(Rarity.COMMON, 4205),
            tip("grimm", GRAY));

    public static final RegistryObject<Item> SCYTHE = registerTooltip("scythe", Tiers.NETHERITE, 2, -2.5F,
            () -> gear(Rarity.RARE, 1805),
            tip("scythe", GRAY));

    public static final RegistryObject<Item> NIGHTINGALE = registerTooltip("nightingale", Tiers.DIAMOND, 4, -2.3F,
            () -> gear(Rarity.RARE, 3242),
            tip("nightingale", GRAY),
            tip("weapon_passive", WHITE, BOLD),
            tip("night_ritus_dagger_passive", WHITE));

    public static final RegistryObject<Item> IUDEX_HALBERD = registerTooltip("iudex_gundyr_halberd", Tiers.NETHERITE, 5, -2.4F,
            () -> new Item.Properties().rarity(Rarity.RARE),
            tip("iudex_gundyr_halberd", GRAY));

    public static final RegistryObject<Item> VITREUS = registerTooltip("vitreus", Tiers.DIAMOND, 3, -2.2f,
            () -> gear(Rarity.RARE, 4561),
            tip("vitreus", GRAY));

    public static final RegistryObject<Item> IRON_FIST = registerTooltip("iron_fist", Tiers.IRON, 1, -1.7f,
            () -> gear(Rarity.RARE, 5561),
            tip("iron_fist", GRAY));

    public static final RegistryObject<Item> SABER = registerTooltip("saber", Tiers.DIAMOND, 2, -2.0f,
            () -> gear(Rarity.RARE, 2500),
            tip("saber", GRAY));

    public static final RegistryObject<Item> NIGHT_RITUS_DAGGER = registerTooltip("night_ritus_dagger", Tiers.DIAMOND, 1, -2.0f,
            () -> gear(Rarity.RARE, 2800),
            tip("night_ritus_dagger_headline", WHITE, BOLD),
            tip("night_ritus_dagger_passive", WHITE),
            tip("night_ritus_dagger_lore", GRAY));

    public static final RegistryObject<Item> BLOOD_RITUS_DAGGER = registerTooltip("blood_ritus_dagger", Tiers.DIAMOND, 2, -2.1f,
            () -> gear(Rarity.RARE, 2100),
            tip("blood_ritus_dagger_headline", WHITE, BOLD),
            tip("blood_ritus_dagger_passive", WHITE),
            tip("blood_ritus_dagger_lore", GRAY));

    public static final RegistryObject<Item> BLOOD_KNIFE = registerTooltip("blood_knife", Tiers.NETHERITE, 2, -1.85f,
            () -> gear(Rarity.RARE, 2000),
            tip("blood_ritus_dagger_headline", WHITE, BOLD),
            tip("blood_loss_passive", WHITE),
            tip("blood_knife", GRAY));

    public static final RegistryObject<Item> SPIKED_BAT = registerTooltip("nailbat", Tiers.DIAMOND, 4, -2.6f,
            () -> gear(Rarity.RARE, 2080),
            tip("nailbat", GRAY));

    public static final RegistryObject<Item> MILADY = registerTooltip("milady", Tiers.NETHERITE, 1, -2.4f,
            () -> gear(Rarity.EPIC, 3680),
            tip("milady_passive", WHITE),
            tip("milady_lore", GRAY));

    public static final RegistryObject<Item> BONECUTTING_SAW = registerTooltip("bonecutting_saw", Tiers.NETHERITE, 1, -2.8f,
            () -> gear(Rarity.EPIC, 3680),
            tip("bonecutting_saw_text1", WHITE, ChatFormatting.ITALIC),
            tip("bonecutting_saw_text2", GRAY));

    // ------------------------------------------------------------ plain swords

    public static final RegistryObject<Item> NETHERITE_SICKLE = registerSword("netherite_sickle", Tiers.NETHERITE, 2, -1.9f, Rarity.COMMON, 2162);
    public static final RegistryObject<Item> DIAMOND_SICKLE = registerSword("diamond_sickle", Tiers.DIAMOND, 2, -1.85f, Rarity.COMMON, 1262);
    public static final RegistryObject<Item> IRON_SICKLE = registerSword("iron_sickle", Tiers.IRON, 2, -1.8f, Rarity.COMMON, 800);
    public static final RegistryObject<Item> STONE_SICKLE = registerSword("stone_sickle", Tiers.STONE, 2, -1.85f, Rarity.COMMON, 150);
    public static final RegistryObject<Item> GOLDEN_SICKLE = registerSword("golden_sickle", Tiers.GOLD, 2, -1.75f, Rarity.COMMON, 800);
    public static final RegistryObject<Item> WAR_SICKLE = registerSword("war_sickle", Tiers.NETHERITE, 3, -2.6f, Rarity.COMMON, 2061);

    public static final RegistryObject<Item> BACKHAND_BLADE = registerSword("backhand_blade", Tiers.IRON, 3, -2.4f, Rarity.RARE, 2561);

    public static final RegistryObject<Item> IRON_LIGHT_GREATSWORD = registerSword("iron_light_greatsword", Tiers.IRON, 4, -2.45f, Rarity.COMMON, 861);
    public static final RegistryObject<Item> STONE_LIGHT_GREATSWORD = registerSword("stone_light_greatsword", Tiers.STONE, 4, -2.5f, Rarity.COMMON, 250);
    public static final RegistryObject<Item> GOLDEN_LIGHT_GREATSWORD = registerSword("golden_light_greatsword", Tiers.GOLD, 4, -2.35f, Rarity.COMMON, 461);
    public static final RegistryObject<Item> DIAMOND_LIGHT_GREATSWORD = registerSword("diamond_light_greatsword", Tiers.DIAMOND, 4, -2.5f, Rarity.COMMON, 1361);
    public static final RegistryObject<Item> NETHERITE_LIGHT_GREATSWORD = registerSword("netherite_light_greatsword", Tiers.NETHERITE, 4, -2.55f, Rarity.COMMON, 2061);

    public static final RegistryObject<Item> IRON_FLORETT = registerSword("iron_florett", Tiers.IRON, 2, -2.05f, Rarity.COMMON, 861);
    public static final RegistryObject<Item> STONE_FLORETT = registerSword("stone_florett", Tiers.STONE, 2, -2.1f, Rarity.COMMON, 130);
    public static final RegistryObject<Item> GOLDEN_FLORETT = registerSword("golden_florett", Tiers.GOLD, 2, -1.9f, Rarity.COMMON, 861);
    public static final RegistryObject<Item> DIAMOND_FLORETT = registerSword("diamond_florett", Tiers.DIAMOND, 2, -2.1f, Rarity.COMMON, 1861);
    public static final RegistryObject<Item> NETHERITE_FLORETT = registerSword("netherite_florett", Tiers.NETHERITE, 2, -2.15f, Rarity.COMMON, 1861);

    public static final RegistryObject<Item> ECLIPSE = registerSword("eclipse", Tiers.NETHERITE, 4, -2.7f, Rarity.RARE, 5561);

    public static final RegistryObject<Item> DIAMOND_MESSER = registerSword("diamond_messer", Tiers.DIAMOND, 2, -2.1f, Rarity.COMMON, 1561);
    public static final RegistryObject<Item> GOLDEN_MESSER = registerSword("golden_messer", Tiers.GOLD, 3, -2.0f, Rarity.COMMON, 530);
    public static final RegistryObject<Item> NETHERITE_MESSER = registerSword("netherite_messer", Tiers.NETHERITE, 2, -2.1f, Rarity.COMMON, 2561);
    public static final RegistryObject<Item> IRON_MESSER = registerSword("iron_messer", Tiers.IRON, 2, -2.1f, Rarity.COMMON, 860);
    public static final RegistryObject<Item> STONE_MESSER = registerSword("stone_messer", Tiers.STONE, 2, -2.15f, Rarity.COMMON, 140);

    public static final RegistryObject<Item> BAT = registerSword("bat", Tiers.WOOD, 4, -2.5f, Rarity.COMMON, 840);

    public static final RegistryObject<Item> IRON_KNIFE = registerSword("iron_knife", Tiers.IRON, 1, -2.2f, Rarity.COMMON, 860);
    public static final RegistryObject<Item> STONE_KNIFE = registerSword("stone_knife", Tiers.STONE, 1, -2.25f, Rarity.COMMON, 125);
    public static final RegistryObject<Item> DIAMOND_KNIFE = registerSword("diamond_knife", Tiers.DIAMOND, 1, -2.0f, Rarity.COMMON, 1360);
    public static final RegistryObject<Item> NETHERITE_KNIFE = registerSword("netherite_knife", Tiers.NETHERITE, 1, -1.9f, Rarity.COMMON, 2360);
    public static final RegistryObject<Item> GOLDEN_KNIFE = registerSword("golden_knife", Tiers.GOLD, 2, -1.8f, Rarity.COMMON, 360);

    // ------------------------------------------------------------ battlestaves

    public static final RegistryObject<Item> WOODEN_BATTLESTAFF = registerSword("wooden_battlestaff", Tiers.WOOD, 3, -2.4f, Rarity.COMMON, 131);
    public static final RegistryObject<Item> STONE_BATTLESTAFF = registerSword("stone_battlestaff", Tiers.STONE, 3, -2.45f, Rarity.COMMON, 250);
    public static final RegistryObject<Item> IRON_BATTLESTAFF = registerSword("iron_battlestaff", Tiers.IRON, 3, -2.5f, Rarity.COMMON, 980);
    public static final RegistryObject<Item> NETHERITE_BATTLESTAFF = registerSword("netherite_battlestaff", Tiers.NETHERITE, 3, -2.5f, Rarity.COMMON, 2680);

    public static final RegistryObject<Item> GOLDEN_BATTLESTAFF = registerWeapon("golden_battlestaff", Tiers.GOLD, 3, -2.2f, Rarity.COMMON, 680);
    public static final RegistryObject<Item> AMETHYST_BATTLESTAFF = registerWeapon("amethyst_battlestaff", Tiers.DIAMOND, 4, -2.45f, Rarity.RARE, 1250);
    public static final RegistryObject<Item> DIAMOND_BATTLESTAFF = registerWeapon("diamond_battlestaff", Tiers.DIAMOND, 3, -2.5f, Rarity.COMMON, 1680);

    // ------------------------------------------------------------ materials

    public static final RegistryObject<Item> NAILS = registerBasic("nail", 64);
    public static final RegistryObject<Item> GLORIOUS_CHARM = registerBasic("glorious_charm", 64);
    public static final RegistryObject<Item> RUBY = registerBasic("ruby", 16);
    public static final RegistryObject<Item> TANZANITE = registerBasic("tanzanite", 16);
    public static final RegistryObject<Item> PERIDOT = registerBasic("peridot", 16);
    public static final RegistryObject<Item> MALACHITE = registerBasic("malachite", 16);
    public static final RegistryObject<Item> JADE = registerBasic("jade", 16);
    public static final RegistryObject<Item> AMBER = registerBasic("amber", 16);
    public static final RegistryObject<Item> CITRINE = registerBasic("citrine", 16);
    public static final RegistryObject<Item> METAL_PLATE = registerBasic("metal_plate", 64);
    public static final RegistryObject<Item> RUSTED_METAL_PLATE = registerBasic("rusted_metal_plate", 64);
    public static final RegistryObject<Item> POLEBLADE_BLADE_PART = registerBasic("poleblade_blade_part", 2);

    // ------------------------------------------------------------ halberds

    public static final RegistryObject<Item> DIAMOND_HALBERD = registerHalberd("diamond_halberd", Tiers.DIAMOND);
    public static final RegistryObject<Item> GOLDEN_HALBERD = registerHalberd("golden_halberd", Tiers.GOLD);
    public static final RegistryObject<Item> STONE_HALBERD = registerHalberd("stone_halberd", Tiers.STONE);
    public static final RegistryObject<Item> IRON_HALBERD = registerHalberd("iron_halberd", Tiers.IRON);
    public static final RegistryObject<Item> NETHERITE_HALBERD = registerHalberd("netherite_halberd", Tiers.NETHERITE);

    // ------------------------------------------------------------ potions

    public static final RegistryObject<Potion> BRUISING = registerPotion("bruising", EffectRegistry.BRUISING, 1600, 0);
    public static final RegistryObject<Potion> BRUISING_LONG = registerPotion("bruising_long", EffectRegistry.BRUISING, 3000, 0);
    public static final RegistryObject<Potion> BRUISING_STRONG = registerPotion("bruising_strong", EffectRegistry.BRUISING, 800, 1);
    public static final RegistryObject<Potion> BRUISING_STRONGER = registerPotion("bruising_stronger", EffectRegistry.BRUISING, 500, 2);

    public static final RegistryObject<Potion> HEART_STOP = registerPotion("heartstop", EffectRegistry.HEART_STOP, 3600, 0);
    public static final RegistryObject<Potion> HEART_STOP_LONG = registerPotion("heartstop_long", EffectRegistry.HEART_STOP, 9600, 0);
    public static final RegistryObject<Potion> HEART_STOP_STRONG = registerPotion("heartstop_strong", EffectRegistry.HEART_STOP, 2400, 1);

    public static final RegistryObject<Potion> EXHAUSTED = registerPotion("exhausted", EffectRegistry.EXHAUSTED, 3600, 0);
    public static final RegistryObject<Potion> EXHAUSTED_LONG = registerPotion("exhausted_long", EffectRegistry.EXHAUSTED, 9600, 0);
    public static final RegistryObject<Potion> EXHAUSTED_STRONG = registerPotion("exhausted_strong", EffectRegistry.EXHAUSTED, 2400, 1);

    public static final RegistryObject<Potion> STUNARMOR = registerPotion("stun_armor", EffectRegistry.STUNARMOR, 4000, 0);
    public static final RegistryObject<Potion> STUNARMOR_LONG = registerPotion("stun_armor_long", EffectRegistry.STUNARMOR, 10400, 0);
    public static final RegistryObject<Potion> STUNARMOR_STRONG = registerPotion("stun_armor_strong", EffectRegistry.STUNARMOR, 2100, 1);

    public static final RegistryObject<Potion> FORTIFIED = registerPotion("fortified", EffectRegistry.FORTIFIED, 3600, 0);
    public static final RegistryObject<Potion> FORTIFIED_LONG = registerPotion("fortified_long", EffectRegistry.FORTIFIED, 9600, 0);
    public static final RegistryObject<Potion> FORTIFIED_STRONG = registerPotion("fortified_strong", EffectRegistry.FORTIFIED, 1800, 1);

    public static final RegistryObject<Potion> STAMINAREGEN = registerPotion("staminaregen_potion", EffectRegistry.ENDURANCE, 3600, 0);
    public static final RegistryObject<Potion> STAMINAREGEN_STRONG = registerPotion("staminaregen_potion_strong", EffectRegistry.ENDURANCE, 1700, 1);
    public static final RegistryObject<Potion> STAMINAREGEN_LONG = registerPotion("staminaregen_potion_long", EffectRegistry.ENDURANCE, 9600, 0);

    public static final RegistryObject<Potion> MAXSTAMINA_LONG = registerPotion("stamina_potion_long", EffectRegistry.STAMNIA, 9600, 0);
    public static final RegistryObject<Potion> MAXSTAMINA_STRONG = registerPotion("stamina_potion_strong", EffectRegistry.STAMNIA, 1700, 1);
    public static final RegistryObject<Potion> MAXSTAMINA = registerPotion("stamina_potion", EffectRegistry.STAMNIA, 3600, 0);

    public static final RegistryObject<Potion> POWERPOTION = registerPotion("might", EffectRegistry.MIGHT, 1200, 0);
    public static final RegistryObject<Potion> SWEEPING = registerPotion("acceleration", EffectRegistry.ACCELERATION, 1300, 0);

    public static final RegistryObject<Potion> IMPACTPOTION_STRONG = registerPotion("potion_of_stronger_impact", EffectRegistry.IMPACT, 1700, 1);
    public static final RegistryObject<Potion> IMPACTPOTION_LONG = registerPotion("potion_of_longer_impact", EffectRegistry.IMPACT, 9600, 0);
    public static final RegistryObject<Potion> IMPACTPOTION = registerPotion("potion_of_impact", EffectRegistry.IMPACT, 3600, 0);

    public static final RegistryObject<Potion> CRUMBLING = registerPotion("crumbling", EffectRegistry.CRUMBLING, 3600, 0);
    public static final RegistryObject<Potion> CRUMBLING_LONG = registerPotion("crumbling_long", EffectRegistry.CRUMBLING, 6666, 0);
    public static final RegistryObject<Potion> CRUMBLING_STRONG = registerPotion("crumbling_strong", EffectRegistry.CRUMBLING, 2100, 1);

    public static final RegistryObject<Potion> CURSED = registerPotion("cursed", EffectRegistry.CURSED, 2960, 0);
    public static final RegistryObject<Potion> CURSED_LONG = registerPotion("cursed_long", EffectRegistry.CURSED, 6666, 0);
    public static final RegistryObject<Potion> CURSED_STRONG = registerPotion("cursed_strong", EffectRegistry.CURSED, 1929, 1);

    // ------------------------------------------------------------ misc

    public static final RegistryObject<Item> ENERGY_DRINK = register("energydrink", () -> new SpecialDrinkItem(
            new Item.Properties()
                    .stacksTo(4)
                    .craftRemainder(Items.IRON_NUGGET),
            new MobEffectInstance(EffectRegistry.ENDURANCE.get(), 1200, 1),
            new MobEffectInstance(EffectRegistry.STAMNIA.get(), 1200, 1),
            new MobEffectInstance(EffectRegistry.IMPACT.get(), 1200, 0)));
}