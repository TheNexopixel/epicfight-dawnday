package net.epicfight_dd.network;

import net.epicfight_dd.Epicfight_dd;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;



public class DDNetworkHandler {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        INSTANCE.messageBuilder(ChangeLivingModifierPacket.class, id())
                .encoder(ChangeLivingModifierPacket::encode)
                .decoder(ChangeLivingModifierPacket::new)
                .consumerMainThread(ChangeLivingModifierPacket::handle)
                .add();
    }
}
