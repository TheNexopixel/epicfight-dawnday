package net.dawn_day.mixin;


import net.dawn_day.gameasset.animation.DawnDayCollider;
import net.dawn_day.gameasset.animation.optional.WoMCompatAnimations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import reascer.wom.gameasset.WOMAnimations;
import reascer.wom.skill.guard.DreadFullBusterSkill;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

@Mixin(value = DreadFullBusterSkill.class, remap = false)
public class DreadFullBusterMixin{

    @Inject(method = "getWindupAnimation", at = @At("HEAD"), cancellable = true)
    private void BusterWindup(CapabilityItem capabilityItem, PlayerPatch<?> playerPatch, CallbackInfoReturnable<AnimationManager.AnimationAccessor<?>> cir) {
        if (capabilityItem.getWeaponCollider() == DawnDayCollider.POLEBLADE) {
            cir.setReturnValue(WoMCompatAnimations.POLEBLADE_BUSTER_WINDUP);
        }
        if (capabilityItem.getWeaponCollider() == DawnDayCollider.IRON_FIST) {
            cir.setReturnValue(WoMCompatAnimations.HOOKCLAWS_DFB_WINDUP);
        }
        if (capabilityItem.getWeaponCollider() == DawnDayCollider.BATTLESTAFF_FULL) {
            cir.setReturnValue(WOMAnimations.STAFF_BUSTER_WINDUP);
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Inject(method = "getReleaseAnimataion", at = @At("HEAD"), cancellable = true)
    private void BusterRelease(CapabilityItem capabilityItem, PlayerPatch<?> playerPatch, CallbackInfoReturnable<AnimationManager.AnimationAccessor<? extends StaticAnimation>> cir){
        if(capabilityItem.getWeaponCollider() == DawnDayCollider.POLEBLADE){
            cir.setReturnValue(WoMCompatAnimations.POLEBLADE_BUSTER_RELEASE);
        }
        if (capabilityItem.getWeaponCollider() == DawnDayCollider.IRON_FIST) {
            cir.setReturnValue(WoMCompatAnimations.HOOKCLAWS_DFB_RELEASE);
        }
        if (capabilityItem.getWeaponCollider() == DawnDayCollider.BATTLESTAFF_FULL) {
            cir.setReturnValue(WOMAnimations.STAFF_BUSTER_RELEASE);
        }
    }
}
