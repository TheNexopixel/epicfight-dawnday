
package net.epicfight_dd.skill.mover;

import net.epicfight_dd.Epicfight_dd;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = Epicfight_dd.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class AirDashServerEvents {

    // THIS CODE WAS MADE MOSTLY BY AI gez. Nexo

    private static final String DASH_USED =
            "epicfight_dd_air_dash_used";

    @SubscribeEvent
    public static void onPlayerTick(
            TickEvent.PlayerTickEvent event
    ) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        if (event.player.level().isClientSide) {
            return;
        }

        if (event.player.onGround()) {
            event.player.getPersistentData().putBoolean(
                    DASH_USED,
                    false
            );
        }
    }
}
