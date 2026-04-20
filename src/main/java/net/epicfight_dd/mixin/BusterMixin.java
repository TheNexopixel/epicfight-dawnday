package net.epicfight_dd.mixin;

import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.epicfight_dd.gameasset.animation.DawnDayCollider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import reascer.wom.skill.guard.DreadFullBusterSkill;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;


@Mixin(value = DreadFullBusterSkill.class, remap = false)
public class BusterMixin {

    @Inject(method = "getWindupAnimation", at = @At("HEAD"), cancellable = true)
    private void evilTachiWindup(CapabilityItem capabilityItem, PlayerPatch<?> playerPatch, CallbackInfoReturnable<AnimationManager.AnimationAccessor<?>> cir) {
        if (capabilityItem.getWeaponCollider() == DawnDayCollider.EVIL_TACHI) {
            cir.setReturnValue(DawnDayAnimations.EVIL_ODACHI_OVERHEADSLASH_CHARGE);
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Inject(method = "getReleaseAnimataion", at = @At("HEAD"), cancellable = true)
    private void evilTachiRelease(CapabilityItem capabilityItem, PlayerPatch<?> playerPatch, CallbackInfoReturnable<AnimationManager.AnimationAccessor<? extends StaticAnimation>> cir){
        if(capabilityItem.getWeaponCollider() == DawnDayCollider.EVIL_TACHI){
            cir.setReturnValue(DawnDayAnimations.EVIL_ODACHI_OVERHEADSLASH_RELEASE);
        }
    }

}
