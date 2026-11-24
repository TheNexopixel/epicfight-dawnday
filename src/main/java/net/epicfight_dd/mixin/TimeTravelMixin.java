package net.epicfight_dd.mixin;

import net.epicfight_dd.config.DawnDayConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import reascer.wom.skill.dodges.TimeTravelSkill;
import yesman.epicfight.skill.Skill;

@Mixin(TimeTravelSkill.class)
public class TimeTravelMixin {

    @Inject(
            method = "getPriorSkill",
            at = @At("HEAD"),
            cancellable = true
    )
    private void epicfight_dd_removePriorSkill(
            CallbackInfoReturnable<Skill> cir) {

        if (DawnDayConfigs.Server.REMOVE_WOM_TIME_TRAVEL_REQUIREMENT.get()) {
            cir.setReturnValue(null);
        }
    }
}