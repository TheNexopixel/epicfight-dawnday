package net.dawn_day.registry.entries;

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

public final class DawnDayCreativeTabs {

    private DawnDayCreativeTabs() {}
    public static final DeferredRegister<CreativeModeTab> REGISTRY =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EpicFightDawnDay.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DAWN_DAY_TAB = REGISTRY.register("epicfight_dd_tab", () -> CreativeModeTab.builder().icon(() ->
                    new ItemStack(DawnDayItems.MILADY.get()))
            .title(Component.translatable("creativetab.dawn_day_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                DawnDayItems.REGISTRY.getEntries().forEach(item -> output.accept(item.get()));

                Stream.of(
                        DawnDayPotions.FORTIFIED, DawnDayPotions.FORTIFIED_STRONG, DawnDayPotions.FORTIFIED_LONG,
                        DawnDayPotions.STUNARMOR, DawnDayPotions.STUNARMOR_STRONG, DawnDayPotions.STUNARMOR_LONG,
                        DawnDayPotions.STAMINA_STIMULANT, DawnDayPotions.STRONG_STAMINA_STIMULANT, DawnDayPotions.LONG_STAMINA_STIMULANT,
                        DawnDayPotions.STAMINA_POTION, DawnDayPotions.STRONG_STAMINA_POTION, DawnDayPotions.LONG_STAMINA_POTION,
                        DawnDayPotions.POWER_POTION, DawnDayPotions.impactpotion, DawnDayPotions.impactpotion_strong, DawnDayPotions.impactpotion_long,
                        DawnDayPotions.SWEEPING
                ).forEach(potion -> output.accept(PotionContents.createItemStack(Items.POTION, potion)));

                Stream.of(
                        DawnDayPotions.CRUMBLING, DawnDayPotions.CRUMBLING_STRONG, DawnDayPotions.CRUMBLING_LONG,
                        DawnDayPotions.CURSED, DawnDayPotions.CURSED_STRONG, DawnDayPotions.CURSED_LONG
                ).forEach(potion -> output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, potion)));
            })
            .build()
    );
}
