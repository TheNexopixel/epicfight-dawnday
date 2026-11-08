package net.epicfight_dd.mixin;

import net.epicfight_dd.skill.dodge.HeatedStepsSkill;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.input.EpicFightKeyMappings;
import yesman.epicfight.client.events.engine.ControlEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.SkillSlot;

@Mixin(value = ControlEngine.class, remap = false)
public abstract class HeatedStepsInputMixin {
    @Shadow private LocalPlayerPatch playerPatch;
    @Shadow private void reserveKey(SkillSlot slot, KeyMapping key) { throw new AssertionError(); }

    @Inject(method = "maybeDodge", at = @At("HEAD"), cancellable = true, remap = false)
    private void epicfight_dd_heatedStepsPress(CallbackInfo callback) {
        if (playerPatch == null || !playerPatch.isEpicFightMode() || playerPatch.getEntityState().knockDown()) return;
        var container = playerPatch.getSkill(SkillSlots.DODGE);
        if (!(container.getSkill() instanceof HeatedStepsSkill skill)) return;
        callback.cancel();
        if (playerPatch.isHoldingSkill(skill)) return;
        ControlEngine engine = (ControlEngine)(Object)this;
        if (container.sendCastRequest(playerPatch, engine).shouldReserveKey())
            reserveKey(SkillSlots.DODGE, EpicFightKeyMappings.DODGE);
    }
}
