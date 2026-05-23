package net.dawn_day.events;

import net.dawn_day.DawnDayConfig;
import net.dawn_day.EpicFightDawnDay;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = EpicFightDawnDay.MOD_ID)
public class RespawnEvent {
    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (DawnDayConfig.ENABLE_SPECTATOR_RESPAWN.get().equals(true)) {
            if (!(event.getEntity() instanceof ServerPlayer player)) {
                return;
            }
            player.setGameMode(GameType.SPECTATOR);
        }
    }
}
