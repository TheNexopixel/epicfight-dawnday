package net.dawn_day.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record CPLivingModifierPacket(boolean oldAnimations) implements ManagedCustomPayloadType{
    public static final StreamCodec<ByteBuf, CPLivingModifierPacket> CODEC =
            StreamCodec.composite(ByteBufCodecs.BOOL, CPLivingModifierPacket::oldAnimations, CPLivingModifierPacket::new);
}
