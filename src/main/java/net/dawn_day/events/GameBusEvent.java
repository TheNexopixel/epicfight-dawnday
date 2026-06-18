package net.dawn_day.events;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.registry.entries.DawnDayEffects;
import net.dawn_day.gameasset.animation.QoLMiscAnimations;
import net.dawn_day.registry.entries.DawnDaySkills;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.main.EpicFightSharedConstants;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.damagesource.StunType;

import java.util.Objects;

@EventBusSubscriber(modid = EpicFightDawnDay.MOD_ID)
public class GameBusEvent {


    @SubscribeEvent
    public static void resistance(LivingDamageEvent.Pre event) {
        var effectInstance = event.getEntity().getEffect(DawnDayEffects.IMPREGNABILITY);

        if (effectInstance != null) {
            float originalAmount = event.getNewDamage();
            float reductionFactor = 1.0f - ((effectInstance.getAmplifier() + 1) * 0.10f);
            reductionFactor = Math.max(0.0f, reductionFactor);
            event.setNewDamage(originalAmount * reductionFactor);
        }
    }

    public static void onStun(FMLCommonSetupEvent evt) {
        EpicFightEventHooks.Entity.APPLY_STUN.registerEvent((event)-> {

        if(event.getEntityPatch() instanceof ServerPlayerPatch serverPlayerPatch){
            if(event.getStunType().equals(StunType.KNOCKDOWN)){
                serverPlayerPatch.playAnimationSynchronized(QoLMiscAnimations.DAWNDAY_KNOCKDOWN, 0.0f);
            }
        }
        LivingEntity entity = event.getEntityPatch().getOriginal();

        StunType stunType = event.getStunType();

        if(entity.hasEffect(DawnDayEffects.IMPREGNABILITY) && stunType != StunType.NEUTRALIZE){
            event.cancel();
        }

        });
    }

    @SubscribeEvent
    public static void onKB(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntity();
        if ( entity.hasEffect(DawnDayEffects.IMPREGNABILITY) ) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event){
        if(event.getSlot().equals(EquipmentSlot.MAINHAND) && event.getEntity().hasEffect(DawnDayEffects.IMPREGNABILITY)){
            LivingEntity target = event.getEntity();
            if(target instanceof ServerPlayer player){
                ServerPlayerPatch playerPatch = EpicFightCapabilities.getServerPlayerPatch(player);
                if (playerPatch != null) {
                    if (!playerPatch.getAdvancedHoldingItemCapability(InteractionHand.MAIN_HAND).isEmpty()
                            && !Objects.deepEquals(playerPatch.getAdvancedHoldingItemCapability(InteractionHand.MAIN_HAND)
                            .getInnateSkill(playerPatch, playerPatch.getValidItemInHand(InteractionHand.MAIN_HAND)), DawnDaySkills.RAAAHHH)

                    ) {
                        target.removeEffect(DawnDayEffects.IMPREGNABILITY);
                    }
                }
            }
        }
        if(event.getSlot().equals(EquipmentSlot.MAINHAND) && event.getEntity().hasEffect(DawnDayEffects.SEPUKKU.getDelegate())){
            LivingEntity target = event.getEntity();
            if (EpicFightSharedConstants.IS_DEV_ENV) {
                target.sendSystemMessage(Component.literal("YOU HAVE CHANGED ITEMS WITH SEPPUKU!"));
            }
            if(target instanceof ServerPlayer player){
                ServerPlayerPatch playerPatch = EpicFightCapabilities.getServerPlayerPatch(player);
                if (playerPatch != null) {
                    CapabilityItem capability = playerPatch.getAdvancedHoldingItemCapability(InteractionHand.MAIN_HAND);

                    if (!capability.isEmpty()) {
                        Skill innate = capability.getInnateSkill(playerPatch,playerPatch.getValidItemInHand(InteractionHand.MAIN_HAND));
                        if (innate != null && !innate.equals(DawnDaySkills.SEPPUKU.value()) && capability.getWeaponCategory() != CapabilityItem.WeaponCategories.NOT_WEAPON) {
                            if (EpicFightSharedConstants.IS_DEV_ENV) {
                                target.sendSystemMessage(Component.literal(
                                        "Is Capability empty?: " + capability.isEmpty()
                                ));
                                target.sendSystemMessage(Component.literal(
                                        "Innate: " + innate
                                ));
                                target.sendSystemMessage(Component.literal(
                                        "Trying to remove seppuku!"
                                ));
                            }
                            target.removeEffect(DawnDayEffects.SEPUKKU);
                            target.addEffect(new MobEffectInstance(DawnDayEffects.DRAINED, 350, 0));
                        }
                    }
                }
            }
        }
    }


}
