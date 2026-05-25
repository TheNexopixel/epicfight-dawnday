package net.dawn_day.events;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.registry.entries.DawnDayPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = EpicFightDawnDay.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent event) {
        var builder = event.getBuilder();

        // ==================== STAMINA REGEN ====================
        builder.addMix(Potions.AWKWARD, Items.GOLDEN_APPLE, DawnDayPotions.STAMINA_STIMULANT);
        builder.addMix(DawnDayPotions.STAMINA_STIMULANT, Items.REDSTONE, DawnDayPotions.LONG_STAMINA_STIMULANT);
        builder.addMix(DawnDayPotions.STAMINA_STIMULANT, Items.GLOWSTONE_DUST, DawnDayPotions.STRONG_STAMINA_STIMULANT);

        // ==================== MAX STAMINA ====================
        builder.addMix(Potions.AWKWARD, Items.FEATHER, DawnDayPotions.STAMINA_POTION);
        builder.addMix(DawnDayPotions.STAMINA_POTION, Items.REDSTONE, DawnDayPotions.LONG_STAMINA_POTION);
        builder.addMix(DawnDayPotions.STAMINA_POTION, Items.GLOWSTONE_DUST, DawnDayPotions.STRONG_STAMINA_POTION);

        // ==================== POWER ====================
        builder.addMix(Potions.STRONG_STRENGTH, Items.ECHO_SHARD, DawnDayPotions.POWER_POTION);

        // ==================== MORTAL CURSE ====================
        builder.addMix(Potions.STRONG_HEALING, Items.WITHER_ROSE, DawnDayPotions.CURSED);
        builder.addMix(DawnDayPotions.CURSED, Items.REDSTONE, DawnDayPotions.CURSED_LONG);
        builder.addMix(DawnDayPotions.CURSED, Items.GLOWSTONE_DUST, DawnDayPotions.CURSED_STRONG);

        // ==================== SWEEPING ====================
        builder.addMix(Potions.MUNDANE, Items.PRISMARINE_SHARD, DawnDayPotions.SWEEPING);

        // ==================== IMPACT ====================
        builder.addMix(Potions.AWKWARD, Items.PRISMARINE_CRYSTALS, DawnDayPotions.impactpotion);
        builder.addMix(DawnDayPotions.impactpotion, Items.REDSTONE, DawnDayPotions.impactpotion_long);
        builder.addMix(DawnDayPotions.impactpotion, Items.GLOWSTONE_DUST, DawnDayPotions.impactpotion_strong);

        // ==================== CRUMBLING ====================
        builder.addMix(Potions.THICK, Items.POPPED_CHORUS_FRUIT, DawnDayPotions.CRUMBLING);
        builder.addMix(DawnDayPotions.CRUMBLING, Items.REDSTONE, DawnDayPotions.CRUMBLING_LONG);
        builder.addMix(DawnDayPotions.CRUMBLING, Items.GLOWSTONE_DUST, DawnDayPotions.CRUMBLING_STRONG);

        // ==================== STUN ARMOR ====================
        builder.addMix(Potions.THICK, Items.IRON_INGOT, DawnDayPotions.STUNARMOR);
        builder.addMix(DawnDayPotions.STUNARMOR, Items.REDSTONE, DawnDayPotions.STUNARMOR_LONG);
        builder.addMix(DawnDayPotions.STUNARMOR, Items.GLOWSTONE_DUST, DawnDayPotions.STUNARMOR_STRONG);

        // ==================== FORTIFIED ====================
        builder.addMix(Potions.THICK, Items.DIAMOND, DawnDayPotions.FORTIFIED);
        builder.addMix(DawnDayPotions.FORTIFIED, Items.REDSTONE, DawnDayPotions.FORTIFIED_LONG);
        builder.addMix(DawnDayPotions.FORTIFIED, Items.GLOWSTONE_DUST, DawnDayPotions.FORTIFIED_STRONG);
    }
}
