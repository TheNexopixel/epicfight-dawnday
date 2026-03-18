package net.epicfight_dd.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

import java.util.function.Supplier;

public class ChangeLivingModifierPacket {

    private final boolean checkOldAnims;

    public ChangeLivingModifierPacket(boolean checkOldAnimations) {
        this.checkOldAnims = checkOldAnimations;
    }

    public ChangeLivingModifierPacket(FriendlyByteBuf buf) {
        this.checkOldAnims = buf.readBoolean();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBoolean(checkOldAnims);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            ServerPlayerPatch serverPlayerPatch = EpicFightCapabilities.getServerPlayerPatch(player);
            if(serverPlayerPatch == null)return;
            serverPlayerPatch.modifyLivingMotionByCurrentItem(checkOldAnims);
        });
        ctx.get().setPacketHandled(true);
    }

}
