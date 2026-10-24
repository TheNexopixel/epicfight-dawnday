package net.epicfight_dd.network;

import java.util.UUID;
import java.util.function.Supplier;
import net.epicfight_dd.client.particle.UnstoppableParticles;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

public record UnstoppableVisualPacket(int entityId, UUID playerId, int kind, int stacks) {
    public static final int GAIN = 0;
    public static final int LOSS = 1;
    public static final int BARRIER_START = 2;
    public static final int BARRIER_STOP = 3;

    public UnstoppableVisualPacket(FriendlyByteBuf buffer) {
        this(buffer.readVarInt(), buffer.readUUID(), buffer.readUnsignedByte(), buffer.readUnsignedByte());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeVarInt(entityId);
        buffer.writeUUID(playerId);
        buffer.writeByte(kind);
        buffer.writeByte(stacks);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> UnstoppableParticles.accept(this));
        context.get().setPacketHandled(true);
    }

    public static void send(ServerPlayer player, int kind, int stacks) {
        DDNetworkHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                new UnstoppableVisualPacket(player.getId(), player.getUUID(), kind, stacks));
    }

    public static void barrierFor(ServerPlayer observer, ServerPlayer player) {
        DDNetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> observer),
                new UnstoppableVisualPacket(player.getId(), player.getUUID(), BARRIER_START, 13));
    }
}
