
package net.epicfight_dd.network;
import java.util.function.Supplier;
import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.mover.AirDashSkill;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class AirDashPacket {

    // THIS CODE WAS MADE MOSTLY BY AI
    // gez. Nexo

    public AirDashPacket() {
    }

    public AirDashPacket(FriendlyByteBuf buffer) {
    }

    public void encode(FriendlyByteBuf buffer) {
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();

        ServerPlayer sender = context.getSender();

        if (sender != null) {
            context.enqueueWork(() -> {
                ServerPlayerPatch playerPatch =
                        EpicFightCapabilities.getEntityPatch(
                                sender,
                                ServerPlayerPatch.class
                        );

                if (playerPatch == null) {
                    return;
                }

                SkillContainer container =
                        playerPatch.getSkill(DawnDaySkills.AIR_DASH);

                if (container == null) {
                    return;
                }

                if (!(container.getSkill() instanceof AirDashSkill skill)) {
                    return;
                }

                skill.performServerDash(playerPatch, container);

                skill.performServerDash(playerPatch, container);
            });
        }

        context.setPacketHandled(true);
    }
}
