package net.epicfight_dd.events;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = Epicfight_dd.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
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
        if (stack.is(DawnDayItems.nail.get())) {
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
    }
}
