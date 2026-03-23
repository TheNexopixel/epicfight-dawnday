package net.epicfight_dd.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import net.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = PlayerPatch.class, remap = false, priority = 1500)
public class EpicFightExtraMixin {

    @TargetHandler(
            mixin="com.asanginxst.epicfightx.mixin.PlayerPatchMixin",
            name = "epicfight_animation$InitAnimator"
    )
    @Inject(
            method = "@MixinSquared:Handler",
            at = @At(value = "TAIL")
    )
    private void InjectCustomDeath(Animator animator, CallbackInfo ci){
         animator.addLivingAnimation(LivingMotions.DEATH, QoLMiscAnimations.EXPRESSIVE_DEATH);
    }


}
