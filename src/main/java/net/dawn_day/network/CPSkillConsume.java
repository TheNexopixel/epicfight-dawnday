package net.dawn_day.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

/// NOTE: in future maybe EXPAND THIS to cover other resource types
public record CPSkillConsume(int toConsume) implements ManagedCustomPayloadType {
    public static final StreamCodec<ByteBuf, CPSkillConsume> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, CPSkillConsume::toConsume, CPSkillConsume::new
    );


}
