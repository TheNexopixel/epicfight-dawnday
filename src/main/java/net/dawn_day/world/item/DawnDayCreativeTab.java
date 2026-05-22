package net.dawn_day.world.item;

import net.dawn_day.EpicFightDawnDay;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.stream.Stream;

public class DawnDayCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EpicFightDawnDay.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DAWN_DAY_TAB = CREATIVE_MODE_TABS.register("epicfight_dd_tab", () -> CreativeModeTab.builder().icon(() ->
                    new ItemStack(DawnDayItems.MILADY.get()))
            .title(Component.translatable("creativetab.dawn_day_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                DawnDayItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));

                Stream.of(
                        DawnDayItems.FORTIFIED, DawnDayItems.FORTIFIED_STRONG, DawnDayItems.FORTIFIED_LONG,
                        DawnDayItems.STUNARMOR, DawnDayItems.STUNARMOR_STRONG, DawnDayItems.STUNARMOR_LONG,
                        DawnDayItems.STAMINA_STIMULANT, DawnDayItems.STRONG_STAMINA_STIMULANT, DawnDayItems.LONG_STAMINA_STIMULANT,
                        DawnDayItems.STAMINA_POTION, DawnDayItems.STRONG_STAMINA_POTION, DawnDayItems.LONG_STAMINA_POTION,
                        DawnDayItems.POWER_POTION, DawnDayItems.impactpotion, DawnDayItems.impactpotion_strong, DawnDayItems.impactpotion_long,
                        DawnDayItems.SWEEPING
                ).forEach(potion -> output.accept(PotionContents.createItemStack(Items.POTION, potion)));

                Stream.of(
                        DawnDayItems.CRUMBLING, DawnDayItems.CRUMBLING_STRONG, DawnDayItems.CRUMBLING_LONG,
                        DawnDayItems.CURSED, DawnDayItems.CURSED_STRONG, DawnDayItems.CURSED_LONG
                ).forEach(potion -> output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, potion)));
            })
            .build()
    );
}
