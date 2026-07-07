package net.epicfight_dd.world.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class DawnDayTooltips extends SwordItem {

    private final TooltipLine[] tooltips;

    public DawnDayTooltips(Tier tier, int damage, float speed,
                           Properties properties,
                           TooltipLine... tooltips) {
        super(tier, damage, speed, properties);
        this.tooltips = tooltips;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level,
                                @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {

        for (TooltipLine line : tooltips) {
            tooltip.add(
                    Component.translatable(line.key())
                            .withStyle(line.formatting())
            );
        }

        super.appendHoverText(stack, level, tooltip, flag);
    }
}