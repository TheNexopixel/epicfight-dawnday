package net.epicfight_dd.events;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import net.epicfight_dd.gameasset.animation.types.SelectiveAnimationProxy;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.EntityStunEvent;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.damagesource.StunType;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeBusEvent {

    @SubscribeEvent
    public static void overrideKnockDownStun(EntityStunEvent event){

       event.getStunnedEntityPatch().getOriginal().sendSystemMessage(Component.literal("Stunned, Stuntype: " + event.getStunType().toString()));

       if(event.getStunnedEntityPatch().getOriginal() instanceof ServerPlayer serverPlayer){

            if(event.getStunType().equals(StunType.KNOCKDOWN)){

                ServerPlayerPatch serverPlayerPatch = EpicFightCapabilities.getServerPlayerPatch(serverPlayer);

                if (serverPlayerPatch != null) {
                    serverPlayerPatch.playAnimationSynchronized(QoLMiscAnimations.DAWNDAY_KNOCKDOWN, 0.0f);
                }

            }
        }

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void overrideDeathAnim(LivingDeathEvent event) {
        if (ModList.get().isLoaded("epicfightx")) {
            if (!event.isCanceled() && event.getEntity() instanceof ServerPlayer serverPlayer) {
                ServerPlayerPatch serverPlayerPatch = EpicFightCapabilities.getServerPlayerPatch(serverPlayer);
                if (serverPlayerPatch != null) {
                    if (!serverPlayerPatch.getAnimator().getLivingAnimations().get(LivingMotions.DEATH).checkType(SelectiveAnimationProxy.class)
                            && !serverPlayerPatch.getAnimator().getLivingAnimations().get(LivingMotions.DEATH).equals(QoLMiscAnimations.EXPRESSIVE_DEATH)
                    ) {
                        serverPlayerPatch.getAnimator().getLivingAnimations().replace(LivingMotions.DEATH, QoLMiscAnimations.EXPRESSIVE_DEATH);
                    }
                }
            }
        }
    }

}
