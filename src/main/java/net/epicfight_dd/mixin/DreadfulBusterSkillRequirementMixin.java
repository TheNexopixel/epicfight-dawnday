package net.epicfight_dd.mixin;

import net.epicfight_dd.DawnDayServerConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import reascer.wom.skill.guard.DreadFullBusterSkill;
import yesman.epicfight.skill.Skill;

@Mixin(DreadFullBusterSkill.class)
public class DreadfulBusterSkillRequirementMixin {

    @Inject(
            method = "getPriorSkill",
            at = @At("HEAD"),
            cancellable = true
    )
    private void epicfight_dd_removePriorSkill(
            CallbackInfoReturnable<Skill> cir) {

        if (DawnDayServerConfig.REMOVE_WOM_DFB_REQUIREMENT.get()) {
            cir.setReturnValue(null);
        }
    }
}