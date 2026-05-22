package net.dawn_day.network;

import net.dawn_day.EpicFightDawnDay;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = EpicFightDawnDay.MOD_ID)
public class DDNetworkHandler {
    private static final String PROTOCOL_VERSION = "1";

    @SubscribeEvent
    public static void registerPackets(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(PROTOCOL_VERSION);

        registrar.playToServer(
                ManagedCustomPayloadType.SERVER_BOUND_LIVING_MODIFIER,
                CPLivingModifierPacket.CODEC,
                ServerBoundPayloadHandler::handleLivingModifier
        ).playToServer(
                ManagedCustomPayloadType.SERVER_BOUND_SKILL_CONSUME,
                CPSkillConsume.CODEC,
                ServerBoundPayloadHandler::handleCPSkillConsume
        );
    }

    public static void sendToServer(ManagedCustomPayloadType packet)
    {
        PacketDistributor.sendToServer(packet);
    }
}
