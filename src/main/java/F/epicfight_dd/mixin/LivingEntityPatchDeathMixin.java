package F.epicfight_dd.mixin;


import F.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = LivingEntityPatch.class, remap = false)
public abstract class LivingEntityPatchDeathMixin {

    @Shadow
    public abstract Animator getAnimator();

    @Shadow
    public LivingMotion currentLivingMotion;

    @Inject(method = "onDeath",
            at = @At(value = "INVOKE",
                    target = "Lyesman/epicfight/api/animation/Animator;playDeathAnimation()V"),
            cancellable = true,
            remap = false)
    private void replaceDeathAnimIfPlayer(LivingDeathEvent event, CallbackInfo ci) {
        Object self = this;

        if (self instanceof PlayerPatch) {
            System.out.println("=== PLAYER DEATH - Playing custom death animation here!~ ===");

            this.getAnimator().addLivingAnimation(LivingMotions.DEATH, QoLMiscAnimations.EXPRESSIVE_DEATH);
            this.currentLivingMotion = LivingMotions.DEATH;

            ci.cancel();
        }
    }
}
