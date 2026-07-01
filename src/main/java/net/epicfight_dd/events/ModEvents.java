package net.epicfight_dd.events;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameType;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.event.entity.player.PlayerEvent;
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

            // ENDURANCE
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(awkwardPotion),
                    Ingredient.of(DawnDayItems.PERIDOT.get()),
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

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.staminaregen.get())),
                    Ingredient.of(Items.WITHER_ROSE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.EXHAUSTED.get()));

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.staminaregen.get())),
                    Ingredient.of(Items.WITHER_ROSE),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.EXHAUSTED.get()));

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), DawnDayItems.staminaregen.get())),
                    Ingredient.of(Items.WITHER_ROSE),
                    PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION),DawnDayItems.EXHAUSTED.get()));

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.EXHAUSTED.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.EXHAUSTED_LONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.EXHAUSTED.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.EXHAUSTED_LONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.EXHAUSTED.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.EXHAUSTED_STRONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.EXHAUSTED.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.EXHAUSTED_STRONG.get()));

            // Stamina Potion
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
                    Ingredient.of(DawnDayItems.MALACHITE.get()),
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
                            PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
                    Ingredient.of(DawnDayItems.ruby.get()),
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
                    Ingredient.of(DawnDayItems.JADE.get()),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.SWEEPING.get()));
            // IMPACT
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
                    Ingredient.of(Items.IRON_INGOT),
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
                    Ingredient.of(DawnDayItems.RUSTED_METAL_PLATE.get()),
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

            // STUNARMOR
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(thickpotion),
                    Ingredient.of(DawnDayItems.METAL_PLATE.get()),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.STUNARMOR.get()));

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.STUNARMOR.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.STUNARMOR_LONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.STUNARMOR.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.STUNARMOR_LONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.STUNARMOR.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.STUNARMOR_STRONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.STUNARMOR.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.STUNARMOR_STRONG.get()));


            // FORTIFIED
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(thickpotion),
                    Ingredient.of(DawnDayItems.TANZANITE.get()),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.FORTIFIED.get()));

            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.FORTIFIED.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.FORTIFIED_LONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.FORTIFIED.get())),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.FORTIFIED_LONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.FORTIFIED.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.POTION),DawnDayItems.FORTIFIED_STRONG.get()));
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(
                            PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.FORTIFIED.get())),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.FORTIFIED_STRONG.get()));
        });
    }
}
