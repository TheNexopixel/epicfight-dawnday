package F.epicfight_dd.gameasset;

import F.epicfight_dd.world.item.MiladyItems;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

public class DawnDayRegisters {

   public static List<DeferredRegister<?>> REGISTERS =
            List.of(
                    dawnDaySounds.SOUNDS,
                    MiladyItems.ITEMS
            );

}
