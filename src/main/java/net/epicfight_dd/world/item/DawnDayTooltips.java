package net.epicfight_dd.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class DawnDayTooltips extends SwordItem {

    private final String tooltipKey;
    private final String colorKey;

    public DawnDayTooltips(Tier tier, int damage, float speed,
                           Properties properties, String tooltipKey,  String colorKey) {
        super(tier, damage, speed, properties);
        this.tooltipKey = tooltipKey;
        this.colorKey = colorKey;
    }
/// style: bold, obfuscated, italic, strikethrough, underline
    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable(tooltipKey)
                .withStyle(Objects.requireNonNull(ChatFormatting.getByName(colorKey)))
        );
        super.appendHoverText(stack, level, tooltip, flag);
    }

}