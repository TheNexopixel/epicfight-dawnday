package net.epicfight_dd.world.item;

import net.minecraft.ChatFormatting;


public record TooltipLine(String key, ChatFormatting... formatting) {}

