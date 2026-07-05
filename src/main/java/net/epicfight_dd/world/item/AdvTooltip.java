package net.epicfight_dd.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class AdvTooltip extends SwordItem {

    public AdvTooltip(Tier tier, int damage, float speed,
                           Item.Properties properties, String tooltipKey, String colorKey) {
        super(tier, damage, speed, properties);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, TooltipFlag flag) {

        tooltip.add(Component.translatable("tooltip.dawnday.poleblade.title")
                .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD));

        tooltip.add(Component.translatable("tooltip.dawnday.poleblade.desc")
                .withStyle(ChatFormatting.WHITE));

        tooltip.add(Component.translatable("tooltip.dawnday.poleblade.info")
                .withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, level, tooltip, flag);
    }
}
