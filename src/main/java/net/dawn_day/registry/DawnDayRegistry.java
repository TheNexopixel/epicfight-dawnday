package net.dawn_day.registry;

import net.dawn_day.registry.entries.*;
import net.dawn_day.registry.entries.DawnDaySounds;
import net.dawn_day.registry.entries.DawnDaySkills;
import net.dawn_day.registry.entries.DawnDaySkillDataKeys;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class DawnDayRegistry {

   public static List<DeferredRegister<?>> REGISTERS =
           List.of(
                    DawnDaySounds.SOUNDS,
                    DawnDayItems.REGISTRY,
                    DawnDayCreativeTabs.REGISTRY,
                    DawnDaySkillDataKeys.DATA_KEYS,
                    DawnDayEffects.REGISTRY,
                    DawnDayPotions.REGISTRY,
                    DawnDayConditionals.REGISTRY,
                    DawnDayMovesets.REGISTRY,
                    DawnDayItemCapabilityPresets.REGISTRY,
                    DawnDaySkills.REGISTRY
            );

}
