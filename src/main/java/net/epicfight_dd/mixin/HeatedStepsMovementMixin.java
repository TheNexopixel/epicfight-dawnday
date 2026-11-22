package net.epicfight_dd.mixin;

import net.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import net.epicfight_dd.skill.dodge.HeatedStepsSkill;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = ActionAnimation.class, remap = false)
public abstract class HeatedStepsMovementMixin {
    @Inject(method = "begin", at = @At("HEAD"))
    private void epicfight_dd_heatedStepsBeginCharge(LivingEntityPatch<?> patch, CallbackInfo ci) {
        if ((Object)this == Animations.BIPED_DEMOLITION_LEAP_CHARGING.get()
                && patch instanceof PlayerPatch<?> player && player.getHoldingSkill() instanceof HeatedStepsSkill) {
            HeatedStepsSkill.keepHoldingOnItemCancel(player);
        }
    }

    @Inject(method = "begin", at = @At(value = "INVOKE", target =
            "Lyesman/epicfight/api/animation/types/MainFrameAnimation;begin(Lyesman/epicfight/world/capabilities/entitypatch/LivingEntityPatch;)V"))
    private void epicfight_dd_heatedStepsAfterItemCancel(LivingEntityPatch<?> patch, CallbackInfo ci) {
        HeatedStepsSkill.consumeKeepHoldingOnItemCancel(patch);
    }

    @Inject(method = "getCoordVector", at = @At("RETURN"), cancellable = true, remap = false)
    private void epicfight_dd_heatedStepsDistance(LivingEntityPatch<?> patch,
            AssetAccessor<? extends DynamicAnimation> animation, CallbackInfoReturnable<Vec3> result) {
        Object self = this;
        if (self != QoLMiscAnimations.HEATED_STEP_FORWARD.get()
                && self != net.epicfight_dd.gameasset.animation.QoLMiscAnimations.HEATED_STEP_BACKWARD.get()) return;
        float scale = HeatedStepsSkill.distanceScale(patch);
        if (scale == 1) return;
        Vec3 move = result.getReturnValue();
        result.setReturnValue(new Vec3(move.x * scale, move.y, move.z * scale));
    }
}
