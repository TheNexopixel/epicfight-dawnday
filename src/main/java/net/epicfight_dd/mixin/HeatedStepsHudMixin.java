package net.epicfight_dd.mixin;

import net.epicfight_dd.skill.dodge.HeatedStepsSkill;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.gui.BattleModeGui;

@Mixin(value = BattleModeGui.class, remap = false)
public abstract class HeatedStepsHudMixin {
    @Inject(method = "renderCharingBar", at = @At("HEAD"), cancellable = true, remap = false)
    private void epicfight_dd_heatedStepsMeter(CallbackInfo callback) {
        var patch = ClientEngine.getInstance().getPlayerPatch();
        if (patch != null && patch.getHoldingSkill() instanceof HeatedStepsSkill) callback.cancel();
    }
}
