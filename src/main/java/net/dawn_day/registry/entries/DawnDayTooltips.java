package net.dawn_day.registry.entries;

import net.dawn_day.world.item.TooltipLine;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import java.util.List;

public class DawnDayTooltips  extends SwordItem {

    private final TooltipLine[] tooltips;

    public DawnDayTooltips(Tier tier, int damage, float speed,
                          Properties properties,
                          TooltipLine... tooltips) {
        super(tier, properties);
        this.tooltips = tooltips;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        for (TooltipLine line : tooltips) {
            tooltipComponents.add(
                    Component.translatable(line.key())
                            .withStyle(line.formatting())
            );
        }


        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }


}
