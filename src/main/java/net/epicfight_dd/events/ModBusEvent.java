package net.epicfight_dd.events;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.AnimationBuildNexus;
import net.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import net.epicfight_dd.gameasset.animation.types.SelectiveAnimationProxy;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.EntityStunEvent;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.damagesource.StunType;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvent {

    @SubscribeEvent //register animation on the mod bus to make it official
    public static void registerAnimation(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder(Epicfight_dd.MODID, AnimationBuildNexus::buildAnimations);
    }

    @SubscribeEvent
    public static void overrideKnockDownStun(EntityStunEvent event){

        if(event.getStunnedEntityPatch() instanceof ServerPlayerPatch serverPlayerPatch){
            if(event.getStunType().equals(StunType.KNOCKDOWN)){
              serverPlayerPatch.playAnimationSynchronized(QoLMiscAnimations.DAWNDAY_KNOCKDOWN,0.01f);
            }
        }

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void overrideDeathAnim(LivingDeathEvent event) {
        if (ModList.get().isLoaded("epicfightx")) {
            if (event.getResult().equals(Event.Result.ALLOW) && event.getEntity() instanceof ServerPlayer serverPlayer) {
                ServerPlayerPatch serverPlayerPatch = EpicFightCapabilities.getServerPlayerPatch(serverPlayer);
                if (serverPlayerPatch != null) {
                    if (!serverPlayerPatch.getAnimator().getLivingAnimations().get(LivingMotions.DEATH).checkType(SelectiveAnimationProxy.class)
                            && serverPlayerPatch.getAnimator().getLivingAnimations().get(LivingMotions.DEATH).equals(QoLMiscAnimations.EXPRESSIVE_DEATH)
                    ) {
                        serverPlayerPatch.getAnimator().getLivingAnimations().replace(LivingMotions.DEATH, QoLMiscAnimations.EXPRESSIVE_DEATH);
                    }
                }
            }
        }

    }








}
