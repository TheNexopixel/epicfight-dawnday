package net.dawn_day.network;

import net.dawn_day.EpicFightDawnDay;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface ManagedCustomPayloadType extends CustomPacketPayload
{
    ConcurrentMap<Class<? extends CustomPacketPayload>, Type<?>> PAYLOAD_TYPES = new ConcurrentHashMap<>();

    CustomPacketPayload.Type<CPLivingModifierPacket> SERVER_BOUND_LIVING_MODIFIER = registerPayloadType(CPLivingModifierPacket.class, "server_bound_living_modifier");
    CustomPacketPayload.Type<CPSkillConsume> SERVER_BOUND_SKILL_CONSUME = registerPayloadType(CPSkillConsume.class, "server_bound_skill_consume");

    static <T extends ManagedCustomPayloadType> CustomPacketPayload.Type<T> registerPayloadType(Class<T> type, String payloadId) {
        CustomPacketPayload.Type<T> packet = new CustomPacketPayload.Type<T> (EpicFightDawnDay.identifier(payloadId));
        PAYLOAD_TYPES.put(type, packet);

        return packet;
    }

    @Override
    default CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
        if (!PAYLOAD_TYPES.containsKey(this.getClass())) {
            throw new NoSuchElementException("Unregistered packet: " + this.getClass());
        }

        return PAYLOAD_TYPES.get(this.getClass());
    }
}
