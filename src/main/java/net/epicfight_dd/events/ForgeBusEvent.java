package net.epicfight_dd.events;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.EntityStunEvent;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.damagesource.StunType;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeBusEvent {

    @SubscribeEvent
    public static void overrideKnockDownStun(EntityStunEvent event){

       if(event.getStunnedEntityPatch() instanceof ServerPlayerPatch serverPlayerPatch){
            if(event.getStunType().equals(StunType.KNOCKDOWN)){
                serverPlayerPatch.playAnimationSynchronized(QoLMiscAnimations.DAWNDAY_KNOCKDOWN, 0.0f);
            }
        }

    }

}
