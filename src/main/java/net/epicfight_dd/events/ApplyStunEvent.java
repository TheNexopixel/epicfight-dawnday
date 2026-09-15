package net.epicfight_dd.events;

import com.hm.efn.gameasset.animations.EFNStunAnimations;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.forgeevent.EntityStunEvent;
import yesman.epicfight.world.damagesource.StunType;

@Mod.EventBusSubscriber
public class ApplyStunEvent {

@SubscribeEvent
    public static void onStunEx(EntityStunEvent event) {

        LivingEntity entity = event.getEntityPatch().getOriginal();
        StunType stunType = event.getStunType();

        if (event.getDamageSource().getAnimation() instanceof AttackAnimation) {
            event.getEntityPatch().playAnimationSynchronized(EFNStunAnimations.BIPED_HIT_AIR_L0, 0.0F);
        }
    }

}
