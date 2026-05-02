package net.epicfight_dd.events;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import net.epicfight_dd.skill.DawnDaySkills;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.EntityStunEvent;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.damagesource.StunType;

import java.util.Objects;

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

    @SubscribeEvent
    public static void resistance(LivingDamageEvent event) {
        var effectInstance = event.getEntity().getEffect(EffectRegistry.IMPREGNABILITY.get());

        if (effectInstance != null && !event.isCanceled()) {
            float originalAmount = event.getAmount();

            float reductionFactor = 1.0f - ((effectInstance.getAmplifier() + 1) * 0.10f);

            reductionFactor = Math.max(0.0f, reductionFactor);

            event.setAmount(originalAmount * reductionFactor);
        }
    }

    @SubscribeEvent
    public static void onStun(EntityStunEvent event) {
        LivingEntity entity = event.getStunnedEntityPatch().getOriginal();
        StunType stunType = event.getStunType();
        if(entity.hasEffect(EffectRegistry.IMPREGNABILITY.get()) && stunType != StunType.NEUTRALIZE){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onKB(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntity();
        if ( entity.hasEffect(EffectRegistry.IMPREGNABILITY.get()) ) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEquipMentChange(LivingEquipmentChangeEvent event){
        if(event.getSlot().equals(EquipmentSlot.MAINHAND) && event.getEntity().hasEffect(EffectRegistry.IMPREGNABILITY.get())){
            LivingEntity target = event.getEntity();
            if(target instanceof ServerPlayer player){
                ServerPlayerPatch playerPatch = EpicFightCapabilities.getServerPlayerPatch(player);
                if (playerPatch != null) {
                    if (!playerPatch.getAdvancedHoldingItemCapability(InteractionHand.MAIN_HAND).isEmpty()
                            && !Objects.equals(playerPatch.getAdvancedHoldingItemCapability(InteractionHand.MAIN_HAND)
                            .getInnateSkill(playerPatch, playerPatch.getValidItemInHand(InteractionHand.MAIN_HAND)), DawnDaySkills.RAAAHHH)

                    ) {
                        target.removeEffect(EffectRegistry.IMPREGNABILITY.get());
                    }
                }
            }
        }
    }


}
