package net.epicfight_dd.skill.weapon_innate;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerPlayer;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;


public class SkullRuptureSkill extends SimpleWeaponInnateSkill {

    private static final UUID EVENT_UUID = UUID.fromString("14f18523-752f-46ca-9f40-40aba3ad25f0");
    private static final UUID PP_UUID = UUID.fromString("24a1145e-53da-42fa-bdc8-16da30e34217");

    public SkullRuptureSkill(Builder builder) {
        super(builder);
    }


    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(PlayerEventListener.EventType.DEAL_DAMAGE_EVENT_ATTACK, EVENT_UUID, (event) -> {

            if (event.getTarget() instanceof ServerPlayer targetPlayer) {

                ServerPlayerPatch targetPatch = EpicFightCapabilities.getServerPlayerPatch(targetPlayer);

                ServerPlayerPatch serverPlayerPatch = container.getServerExecutor();

                if (targetPatch != null) {
                    targetPatch.getOriginal().sendSystemMessage(Component.literal("RuptureImminent"));
                    serverPlayerPatch.getOriginal().sendSystemMessage(Component.literal("RuptureImminent"));
                    try {
                        targetPatch.getEventListener().addEventListener(PlayerEventListener.EventType.TAKE_DAMAGE_EVENT_ATTACK, PP_UUID, (evt) -> {
                            if (evt.isParried()) {
                                serverPlayerPatch.getOriginal().sendSystemMessage(Component.literal("Rupture parried").withStyle(ChatFormatting.DARK_GRAY), true);
                                evt.getPlayerPatch().getOriginal().sendSystemMessage(Component.literal("Rupture parried").withStyle(ChatFormatting.DARK_GRAY), true);
                                serverPlayerPatch.applyStun(StunType.NEUTRALIZE, 1.5f);
                                MinecraftServer server = evt.getPlayerPatch().getOriginal().server;
                                int curr_tick_cnt = server.getTickCount();
                                server.tell(new TickTask(curr_tick_cnt + 20, () ->
                                        targetPatch.getEventListener().removeListener(PlayerEventListener.EventType.TAKE_DAMAGE_EVENT_ATTACK, PP_UUID)));
                            }

                        });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }


            }


        });

    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
        container.getExecutor().getEventListener().removeListener(PlayerEventListener.EventType.DEAL_DAMAGE_EVENT_ATTACK, EVENT_UUID);

    }
}
