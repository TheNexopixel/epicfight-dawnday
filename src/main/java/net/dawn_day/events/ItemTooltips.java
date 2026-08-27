package net.dawn_day.events;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.registry.entries.DawnDayItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = EpicFightDawnDay.MOD_ID)
public class ItemTooltips {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {

        ItemStack stack = event.getItemStack();

        if (stack.is(DawnDayItems.POLEBLADE_BLADE_PART.get())) {
            event.getToolTip().add(
                    Component.translatable("tooltip.epicfight_dd.poleblade_blade_part")
                            .withStyle(ChatFormatting.GRAY)
            );

        }
        if (stack.is(DawnDayItems.NAIL.get())) {
            event.getToolTip().add(
                    Component.translatable("tooltip.epicfight_dd.nail")
                            .withStyle(ChatFormatting.GRAY)
            );

        }
        if (stack.is(DawnDayItems.PERIDOT.get())) {
            event.getToolTip().add(
                    Component.translatable("tooltip.epicfight_dd.peridot")
                            .withStyle(ChatFormatting.GRAY)
            );

        }
        if (stack.is(DawnDayItems.ruby.get())) {
            event.getToolTip().add(
                    Component.translatable("tooltip.epicfight_dd.ruby")
                            .withStyle(ChatFormatting.GRAY)
            );

        }
        if (stack.is(DawnDayItems.AMBER.get())) {
            event.getToolTip().add(
                    Component.translatable("tooltip.epicfight_dd.amber")
                            .withStyle(ChatFormatting.GRAY)
            );

        }
        if (stack.is(DawnDayItems.MALACHITE.get())) {
            event.getToolTip().add(
                    Component.translatable("tooltip.epicfight_dd.malachite")
                            .withStyle(ChatFormatting.GRAY)
            );

        }
        if (stack.is(DawnDayItems.METAL_PLATE.get())) {
            event.getToolTip().add(
                    Component.translatable("tooltip.epicfight_dd.metal_plate")
                            .withStyle(ChatFormatting.GRAY)
            );

        }
        if (stack.is(DawnDayItems.RUSTED_METAL_PLATE.get())) {
            event.getToolTip().add(
                    Component.translatable("tooltip.epicfight_dd.rusted_metal_plate")
                            .withStyle(ChatFormatting.GRAY)
            );

        }
    }
}
