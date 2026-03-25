package net.epicfight_dd.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

import java.util.function.Supplier;

/// NOTE: in future maybe EXPAND THIS to cover other resource types
public class ServerBoundSkillResourceConsumePacket {

    private final int toconsume;

    public ServerBoundSkillResourceConsumePacket(int consume) {
        this.toconsume = consume;
    }

    public ServerBoundSkillResourceConsumePacket(FriendlyByteBuf buf) {
        this.toconsume = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(toconsume);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            ServerPlayerPatch serverPlayerPatch = EpicFightCapabilities.getServerPlayerPatch(player);
            if(serverPlayerPatch == null)return;
            Skill innateSkill = serverPlayerPatch.getSkill(SkillSlots.WEAPON_INNATE).getSkill();
            serverPlayerPatch.consumeForSkill(innateSkill, Skill.Resource.WEAPON_CHARGE,this.toconsume);
        });
        ctx.get().setPacketHandled(true);
    }



}
