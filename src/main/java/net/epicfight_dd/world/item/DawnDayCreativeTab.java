package net.epicfight_dd.world.item;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
                output.accept(DawnDayItems.herb_sickle.get());
                output.accept(DawnDayItems.netherite_light_greatsword.get());
                output.accept(DawnDayItems.diamond_light_greatsword.get());
                output.accept(DawnDayItems.iron_light_greatsword.get());
                output.accept(DawnDayItems.golden_light_greatsword.get());
                output.accept(DawnDayItems.netherite_halberd.get());
                output.accept(DawnDayItems.diamond_halberd.get());
                output.accept(DawnDayItems.iron_halberd.get());
                output.accept(DawnDayItems.golden_halberd.get());
                output.accept(DawnDayItems.netherite_battlestaff.get());
                output.accept(DawnDayItems.diamond_battlestaff.get());
                output.accept(DawnDayItems.iron_battlestaff.get());
                output.accept(DawnDayItems.golden_battlestaff.get());
                output.accept(DawnDayItems.war_sickle.get());
                output.accept(DawnDayItems.backhand_blade.get());

            })
            .build()
    );


}
