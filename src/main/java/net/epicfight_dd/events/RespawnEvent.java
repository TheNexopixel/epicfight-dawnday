package net.epicfight_dd.events;

import net.epicfight_dd.config.DawnDayConfigs;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RespawnEvent {
    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {

        if (DawnDayConfigs.Server.ENABLE_SPECTATOR_RESPAWN.get().equals(true)) {

            if (!(event.getEntity() instanceof ServerPlayer player)) {
                return;
            }

            player.setGameMode(GameType.SPECTATOR);
        }
    }
}
