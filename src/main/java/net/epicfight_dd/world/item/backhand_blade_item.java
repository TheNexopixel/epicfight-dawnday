package net.epicfight_dd.world.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.world.item.WeaponItem;

import javax.annotation.Nullable;
import java.util.List;

public class backhand_blade_item extends WeaponItem
{
    public backhand_blade_item(Tier materialIn) {
        super(materialIn, 3, -1.5F, new Properties().stacksTo(1));

    }
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.epicfight_dd.poleblade.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }


}
