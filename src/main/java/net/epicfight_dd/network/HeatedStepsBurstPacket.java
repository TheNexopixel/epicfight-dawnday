package net.epicfight_dd.network;

import java.util.function.Supplier;
import net.epicfight_dd.client.particle.HeatedStepsParticles;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

public record HeatedStepsBurstPacket(ResourceLocation dimension, Vec3 position) {
    public HeatedStepsBurstPacket(FriendlyByteBuf buffer) {
        this(buffer.readResourceLocation(), new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble()));
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(dimension);
        buffer.writeDouble(position.x);
        buffer.writeDouble(position.y);
        buffer.writeDouble(position.z);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> HeatedStepsParticles.accept(this));
        context.get().setPacketHandled(true);
    }

    public static void send(ServerPlayer player, Vec3 position) {
        DDNetworkHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                new HeatedStepsBurstPacket(player.level().dimension().location(), position));
    }
}
