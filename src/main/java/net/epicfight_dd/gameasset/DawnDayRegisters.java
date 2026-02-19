package net.epicfight_dd.gameasset;

import net.epicfight_dd.skill.SkillDataKeyZ;
import net.epicfight_dd.world.item.DawnDayCreativeTab;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

public class DawnDayRegisters {

   public static List<DeferredRegister<?>> REGISTERS =
            List.of(
                    dawnDaySounds.SOUNDS,
                    DawnDayItems.ITEMS,
                    DawnDayCreativeTab.CREATIVE_MODE_TABS,
                    SkillDataKeyZ.DATA_KEYS
            );

}
