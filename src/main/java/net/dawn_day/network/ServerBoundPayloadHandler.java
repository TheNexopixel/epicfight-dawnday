package net.dawn_day.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public interface ServerBoundPayloadHandler
{
    static void handleLivingModifier(CPLivingModifierPacket packet, final IPayloadContext context)
    {
        Player player = context.player();
        if (player instanceof ServerPlayer serverPlayer) {
            ServerPlayerPatch serverPlayerPatch = EpicFightCapabilities.getServerPlayerPatch(serverPlayer);
            if(serverPlayerPatch == null) return;
            serverPlayerPatch.modifyLivingMotionByCurrentItem(packet.oldAnimations());
        }
    }

    static void handleCPSkillConsume(CPSkillConsume packet, final IPayloadContext context)
    {
        Player player = context.player();
        if (player instanceof ServerPlayer serverPlayer) {
            ServerPlayerPatch serverPlayerPatch = EpicFightCapabilities.getServerPlayerPatch(serverPlayer);
            if(serverPlayerPatch == null) return;
            Skill innateSkill = serverPlayerPatch.getSkill(SkillSlots.WEAPON_INNATE).getSkill();
            serverPlayerPatch.consumeForSkill(innateSkill, Skill.Resource.WEAPON_CHARGE, packet.toConsume());
        }
    }
}
