package F.epicfight_dd.mixin;


import F.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = PlayerPatch.class)
public abstract class PlayerPatchMixin {


//    @ModifyArgs(method = "initAnimator",
//    at = @At(value = "INVOKE", target = "Lyesman/epicfight/api/animation/Animator;addLivingAnimation(Lyesman/epicfight/api/animation/LivingMotion;Lyesman/epicfight/api/asset/AssetAccessor;)V")
//    ,remap = false)
//    public void redirectDeathMotion(Args args){
//     LivingMotion motion = args.get(0);
//
//     if(motion == LivingMotions.DEATH){
//         args.set(1,  (AssetAccessor<? extends StaticAnimation>) QoLMiscAnimations.EXPRESSIVE_DEATH);
//     }
//
//    }

    @Inject(method = "initAnimator", at = @At("TAIL"), remap = false)
    private void replaceDeathAnimation(Animator animator, CallbackInfo ci) {
        animator.addLivingAnimation(
                LivingMotions.DEATH,
                (AssetAccessor<? extends StaticAnimation>) QoLMiscAnimations.EXPRESSIVE_DEATH
        );
    }

}
