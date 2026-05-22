package net.dawn_day.registry;

import com.google.common.collect.ImmutableList;
import net.dawn_day.effect.EffectRegistry;
import net.dawn_day.gameasset.DawnDaySounds;
import net.dawn_day.registry.entries.DawnDayConditionals;
import net.dawn_day.registry.entries.DawnDayItemCapabilityPresets;
import net.dawn_day.registry.entries.DawnDayMovesets;
import net.dawn_day.skill.DawnDaySkills;
import net.dawn_day.skill.SkillDataKeys;
import net.dawn_day.world.item.DawnDayCreativeTab;
import net.dawn_day.world.item.DawnDayItems;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class DawnDayRegistry {

   public static List<DeferredRegister<?>> REGISTERS =
           List.of(
                    DawnDaySounds.SOUNDS,
                    DawnDayItems.ITEMS,
                    DawnDayCreativeTab.CREATIVE_MODE_TABS,
                    SkillDataKeys.DATA_KEYS,
                    EffectRegistry.EFFECTS,
                    DawnDayItems.POTIONS,
                    DawnDayConditionals.REGISTRY,
                    DawnDayMovesets.REGISTRY,
                    DawnDayItemCapabilityPresets.REGISTRY,
                    DawnDaySkills.REGISTRY
            );

}
