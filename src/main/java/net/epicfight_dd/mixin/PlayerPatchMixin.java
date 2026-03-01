package net.epicfight_dd.mixin;


import net.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.damagesource.StunType;

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

    @Inject(method = "getHitAnimation(Lyesman/epicfight/world/damagesource/StunType;)Lyesman/epicfight/api/animation/AnimationManager$AnimationAccessor;"
    ,at = @At("HEAD"),remap = false, cancellable = true)
    private void ed$replaceKnockDownAnimation(StunType stunType, CallbackInfoReturnable<AnimationManager.AnimationAccessor<? extends StaticAnimation>> cir){
        if (stunType == StunType.KNOCKDOWN) {
            cir.setReturnValue(QoLMiscAnimations.DAWNDAY_KNOCKDOWN);
        }
    }

}
