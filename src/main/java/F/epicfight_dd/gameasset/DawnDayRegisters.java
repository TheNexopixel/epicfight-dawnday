package F.epicfight_dd.gameasset;

import F.epicfight_dd.world.item.DawnDayCreativeTab;
import F.epicfight_dd.world.item.DawnDayItems;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

public class DawnDayRegisters {

   public static List<DeferredRegister<?>> REGISTERS =
            List.of(
                    dawnDaySounds.SOUNDS,
                    DawnDayItems.ITEMS,
                    DawnDayCreativeTab.CREATIVE_MODE_TABS
            );

}
