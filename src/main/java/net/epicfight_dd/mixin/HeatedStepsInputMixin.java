package net.epicfight_dd.mixin;

import net.epicfight_dd.skill.dodge.HeatedStepsSkill;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.events.engine.ControlEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.SkillSlots;

@Mixin(value = ControlEngine.class, remap = false)
public abstract class HeatedStepsInputMixin {
    @Shadow private LocalPlayerPatch playerPatch;

    @Inject(method = "maybeDodge", at = @At(value = "INVOKE", target =
            "Lyesman/epicfight/skill/SkillContainer;sendCastRequest(Lyesman/epicfight/client/world/capabilites/entitypatch/player/LocalPlayerPatch;Lyesman/epicfight/client/events/engine/ControlEngine;)Lyesman/epicfight/world/entity/eventlistener/SkillCastEvent;"),
            cancellable = true)
    private void epicfight_dd_heatedStepsEmergencyPress(CallbackInfo callback) {
        if (playerPatch.getEntityState().knockDown()) return;
        var container = playerPatch.getSkill(SkillSlots.DODGE);
        if (!(container.getSkill() instanceof HeatedStepsSkill skill)) return;
        if (playerPatch.isHoldingSkill(skill)) return;
        if (skill.sendEmergencyDodge(container, (ControlEngine)(Object)this)) callback.cancel();
    }
}
