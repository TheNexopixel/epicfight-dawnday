package net.epicfight_dd.events;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {


            ItemStack awkwardPotion = PotionUtils.setPotion(
                    new ItemStack(Items.POTION),
                    Potions.AWKWARD);

            ItemStack thickpotion = PotionUtils.setPotion(
                    new ItemStack(Items.POTION),
                    Potions.THICK);

            BrewingRecipeRegistry.addRecipe(
                    Ingredient.of(awkwardPotion),
                    Ingredient.of(Items.GOLDEN_APPLE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.staminaregen.get()));

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.staminaregen.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.staminaregen_long.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.staminaregen.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.staminaregen_long.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.staminaregen.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.staminaregen_strong.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.staminaregen.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.staminaregen_strong.get()));

            // Stamina Potion
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
                    Ingredient.of(Items.FEATHER),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.maxstamina.get()));

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.maxstamina.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.maxstamina_long.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.maxstamina.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.maxstamina_long.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.maxstamina.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.maxstamina_strong.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.maxstamina.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.maxstamina_strong.get()));

            // POWER
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_STRENGTH)),
                    Ingredient.of(Items.ECHO_SHARD),
                    PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.powerpotion.get())
            );
            // MORTAL CURSE
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HEALING)),
                    Ingredient.of(Items.WITHER_ROSE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.CURSED.get()));

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.CURSED.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.CURSED_LONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.CURSED.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.CURSED_LONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.CURSED.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.CURSED_STRONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.CURSED.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.CURSED_STRONG.get()));
            // SWEEPING
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
                    Ingredient.of(Items.PRISMARINE_SHARD),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.SWEEPING.get()));
            // IMPACT
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
                    Ingredient.of(Items.PRISMARINE_CRYSTALS),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.impactpotion.get()));

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.impactpotion.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.impactpotion_long.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.impactpotion.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.impactpotion_long.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.impactpotion.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.impactpotion_strong.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.impactpotion.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.impactpotion_strong.get()));
            // CRUMBLING
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(thickpotion),
                    Ingredient.of(Items.POPPED_CHORUS_FRUIT),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.CRUMBLING.get()));

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.CRUMBLING.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.CRUMBLING_LONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.CRUMBLING.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.CRUMBLING_LONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.CRUMBLING.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.CRUMBLING_STRONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.CRUMBLING.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.CRUMBLING_STRONG.get()));


        });
    }
}
