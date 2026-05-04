package net.epicfight_dd.world.item;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DawnDayCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Epicfight_dd.MODID);

    public static final RegistryObject<CreativeModeTab> DAWN_DAY_TAB = CREATIVE_MODE_TABS.register("epicfight_dd_tab", () -> CreativeModeTab.builder().icon(() ->
                    new ItemStack(DawnDayItems.milady.get()))
            .title(Component.translatable("creativetab.dawn_day_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(DawnDayItems.milady.get());
                output.accept(DawnDayItems.bonecutting_saw.get());
                output.accept(DawnDayItems.herb_sickle.get());
                output.accept(DawnDayItems.saber.get());
                output.accept(DawnDayItems.backhand_blade.get());
                output.accept(DawnDayItems.steelaxe.get());
                output.accept(DawnDayItems.war_sickle.get());
                output.accept(DawnDayItems.spiked_bat.get());
                output.accept(DawnDayItems.bat.get());
                output.accept(DawnDayItems.netherite_messer.get());
                output.accept(DawnDayItems.diamond_messer.get());
                output.accept(DawnDayItems.iron_messer.get());
                output.accept(DawnDayItems.golden_messer.get());
                output.accept(DawnDayItems.netherite_light_greatsword.get());
                output.accept(DawnDayItems.diamond_light_greatsword.get());
                output.accept(DawnDayItems.iron_light_greatsword.get());
                output.accept(DawnDayItems.golden_light_greatsword.get());
                output.accept(DawnDayItems.netherite_halberd.get());
                output.accept(DawnDayItems.diamond_halberd.get());
                output.accept(DawnDayItems.iron_halberd.get());
                output.accept(DawnDayItems.golden_halberd.get());
                output.accept(DawnDayItems.netherite_knife.get());
                output.accept(DawnDayItems.diamond_knife.get());
                output.accept(DawnDayItems.iron_knife.get());
                output.accept(DawnDayItems.golden_knife.get());
                output.accept(DawnDayItems.netherite_battlestaff.get());
                output.accept(DawnDayItems.diamond_battlestaff.get());
                output.accept(DawnDayItems.iron_battlestaff.get());
                output.accept(DawnDayItems.golden_battlestaff.get());
                output.accept(DawnDayItems.wooden_battlestaff.get());
                output.accept(DawnDayItems.ENERGY_DRINK.get());

                // Potions

                // NORMAL
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.impregpot.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.staminaregen.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.staminaregen_strong.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.staminaregen_long.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.maxstamina.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.maxstamina_strong.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.maxstamina_long.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.powerpotion.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.impactpotion.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.impactpotion_strong.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.impactpotion_long.get()));


                // SPLASHPOTIONS
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.impregpot.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.powerpotion.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.staminaregen.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.staminaregen_strong.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.staminaregen_long.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.maxstamina.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.maxstamina_strong.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),DawnDayItems.maxstamina_long.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.impactpotion.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.impactpotion_strong.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.impactpotion_long.get()));



                // LINGERING
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), DawnDayItems.powerpotion.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION),DawnDayItems.impregpot.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION),DawnDayItems.staminaregen.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION),DawnDayItems.staminaregen_strong.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION),DawnDayItems.staminaregen_long.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION),DawnDayItems.maxstamina.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION),DawnDayItems.maxstamina_strong.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION),DawnDayItems.maxstamina_long.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), DawnDayItems.impactpotion.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), DawnDayItems.impactpotion_strong.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), DawnDayItems.impactpotion_long.get()));
            })
            .build()
    );


}
