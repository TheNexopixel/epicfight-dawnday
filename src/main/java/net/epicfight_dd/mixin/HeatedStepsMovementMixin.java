package net.epicfight_dd.mixin;

import net.epicfight_dd.skill.dodge.HeatedStepsSkill;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = ActionAnimation.class, remap = false)
public abstract class HeatedStepsMovementMixin {
    @Redirect(method = "begin", at = @At(value = "INVOKE", target =
            "Lyesman/epicfight/world/capabilities/entitypatch/LivingEntityPatch;cancelItemUse()V"), remap = false)
    private void epicfight_dd_heatedStepsKeepCharge(LivingEntityPatch<?> patch) {
        if ((Object)this == Animations.BIPED_DEMOLITION_LEAP_CHARGING.get()
                && patch instanceof PlayerPatch<?> player && player.getHoldingSkill() instanceof HeatedStepsSkill) {
            var entity = patch.getOriginal();
            if (entity.isUsingItem()) {
                entity.stopUsingItem();
                ForgeEventFactory.onUseItemStop(entity, entity.getUseItem(), entity.getUseItemRemainingTicks());
            }
            return;
        }
        patch.cancelItemUse();
    }

    @Inject(method = "getCoordVector", at = @At("RETURN"), cancellable = true, remap = false)
    private void epicfight_dd_heatedStepsDistance(LivingEntityPatch<?> patch,
            AssetAccessor<? extends DynamicAnimation> animation, CallbackInfoReturnable<Vec3> result) {
        Object self = this;
        if (self != Animations.BIPED_ROLL_FORWARD.get() && self != Animations.BIPED_ROLL_BACKWARD.get()) return;
        float scale = HeatedStepsSkill.distanceScale(patch);
        if (scale == 1) return;
        Vec3 move = result.getReturnValue();
        result.setReturnValue(new Vec3(move.x * scale, move.y, move.z * scale));
    }
}
