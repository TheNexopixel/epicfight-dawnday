package net.epicfight_dd.events;

import net.epicfight_dd.EpicFightDawnDay;
import net.epicfight_dd.client.particle.HungerIndicatorParticle;
import net.epicfight_dd.client.particle.ParticleRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EpicFightDawnDay.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBusEvents {


    public static final class ClientParticles {
        @SubscribeEvent
        public static void register(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ParticleRegistry.HUNGER_INDICATOR.get(), HungerIndicatorParticle.Provider::new);
        }
    }
}
