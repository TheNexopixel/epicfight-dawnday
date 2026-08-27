package net.dawn_day.world.item;

import net.minecraft.ChatFormatting;


public record TooltipLine(String key, ChatFormatting... formatting) {}

