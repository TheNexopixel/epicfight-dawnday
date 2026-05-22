package net.dawn_day.events;

import net.dawn_day.world.item.DawnDayItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

public class ModEvents {

    @SubscribeEvent
    public static void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent event) {
        var builder = event.getBuilder();

        // ==================== STAMINA REGEN ====================
        builder.addMix(Potions.AWKWARD, Items.GOLDEN_APPLE, DawnDayItems.STAMINA_STIMULANT);
        builder.addMix(DawnDayItems.STAMINA_STIMULANT, Items.REDSTONE, DawnDayItems.LONG_STAMINA_STIMULANT);
        builder.addMix(DawnDayItems.STAMINA_STIMULANT, Items.GLOWSTONE_DUST, DawnDayItems.STRONG_STAMINA_STIMULANT);

        // ==================== MAX STAMINA ====================
        builder.addMix(Potions.AWKWARD, Items.FEATHER, DawnDayItems.STAMINA_POTION);
        builder.addMix(DawnDayItems.STAMINA_POTION, Items.REDSTONE, DawnDayItems.LONG_STAMINA_POTION);
        builder.addMix(DawnDayItems.STAMINA_POTION, Items.GLOWSTONE_DUST, DawnDayItems.STRONG_STAMINA_POTION);

        // ==================== POWER ====================
        builder.addMix(Potions.STRONG_STRENGTH, Items.ECHO_SHARD, DawnDayItems.POWER_POTION);

        // ==================== MORTAL CURSE ====================
        builder.addMix(Potions.STRONG_HEALING, Items.WITHER_ROSE, DawnDayItems.CURSED);
        builder.addMix(DawnDayItems.CURSED, Items.REDSTONE, DawnDayItems.CURSED_LONG);
        builder.addMix(DawnDayItems.CURSED, Items.GLOWSTONE_DUST, DawnDayItems.CURSED_STRONG);

        // ==================== SWEEPING ====================
        builder.addMix(Potions.MUNDANE, Items.PRISMARINE_SHARD, DawnDayItems.SWEEPING);

        // ==================== IMPACT ====================
        builder.addMix(Potions.AWKWARD, Items.PRISMARINE_CRYSTALS, DawnDayItems.impactpotion);
        builder.addMix(DawnDayItems.impactpotion, Items.REDSTONE, DawnDayItems.impactpotion_long);
        builder.addMix(DawnDayItems.impactpotion, Items.GLOWSTONE_DUST, DawnDayItems.impactpotion_strong);

        // ==================== CRUMBLING ====================
        builder.addMix(Potions.THICK, Items.POPPED_CHORUS_FRUIT, DawnDayItems.CRUMBLING);
        builder.addMix(DawnDayItems.CRUMBLING, Items.REDSTONE, DawnDayItems.CRUMBLING_LONG);
        builder.addMix(DawnDayItems.CRUMBLING, Items.GLOWSTONE_DUST, DawnDayItems.CRUMBLING_STRONG);

        // ==================== STUN ARMOR ====================
        builder.addMix(Potions.THICK, Items.IRON_INGOT, DawnDayItems.STUNARMOR);
        builder.addMix(DawnDayItems.STUNARMOR, Items.REDSTONE, DawnDayItems.STUNARMOR_LONG);
        builder.addMix(DawnDayItems.STUNARMOR, Items.GLOWSTONE_DUST, DawnDayItems.STUNARMOR_STRONG);

        // ==================== FORTIFIED ====================
        builder.addMix(Potions.THICK, Items.DIAMOND, DawnDayItems.FORTIFIED);
        builder.addMix(DawnDayItems.FORTIFIED, Items.REDSTONE, DawnDayItems.FORTIFIED_LONG);
        builder.addMix(DawnDayItems.FORTIFIED, Items.GLOWSTONE_DUST, DawnDayItems.FORTIFIED_STRONG);
    }
}
