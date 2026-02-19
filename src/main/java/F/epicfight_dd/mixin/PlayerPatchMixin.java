package F.epicfight_dd.mixin;


import F.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = PlayerPatch.class, remap = false, priority = 2000)
public abstract class PlayerPatchMixin {


    @Inject(method = "initAnimator", at = @At("TAIL"), remap = false)
    private void ed$replaceDeathAnimation(Animator animator, CallbackInfo ci) {
        animator.addLivingAnimation(
                LivingMotions.DEATH,
                QoLMiscAnimations.EXPRESSIVE_DEATH
        );
    }

    @Inject(method = "initAnimator", at = @At("RETURN"), remap = false)
    private void re$replaceDeathAnimation(Animator animator, CallbackInfo ci) {
        animator.addLivingAnimation(
                LivingMotions.DEATH,
                QoLMiscAnimations.EXPRESSIVE_DEATH
        );
    }

}
