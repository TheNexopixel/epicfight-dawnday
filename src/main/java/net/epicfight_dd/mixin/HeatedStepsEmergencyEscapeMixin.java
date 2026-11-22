package net.epicfight_dd.mixin;

import net.epicfight_dd.skill.dodge.HeatedStepsSkill;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.EmergencyEscapeSkill;
import yesman.epicfight.world.entity.eventlistener.SkillConsumeEvent;

@Mixin(value = EmergencyEscapeSkill.class, remap = false)
public abstract class HeatedStepsEmergencyEscapeMixin {
    @Inject(method = "lambda$onInitiate$1", at = @At("HEAD"), cancellable = true)
    private void epicfight_dd_heatedStepsActualDodge(SkillContainer container, SkillConsumeEvent event, CallbackInfo callback) {
        if (event.getSkill() instanceof HeatedStepsSkill && !HeatedStepsSkill.isEmergencyRequest(event.getArguments())) {
            callback.cancel();
        }
    }
}
