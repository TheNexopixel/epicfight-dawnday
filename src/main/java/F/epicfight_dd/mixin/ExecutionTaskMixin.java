package F.epicfight_dd.mixin;


import net.minecraft.world.entity.LivingEntity;
import net.shelmarow.combat_evolution.execution.ExecutionTask;
import net.shelmarow.combat_evolution.execution.ExecutionTypeManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

@Mixin(value = ExecutionTask.class, remap = false)
public class ExecutionTaskMixin {

    @Shadow
    @Final
    private LivingEntity executor;

    @Shadow
    @Final
    private LivingEntity target;

    @Shadow
    @Final
    private ExecutionTypeManager.Type executionType;

    @Inject(method = "onStart", at = @At("HEAD"))
    private void dd$adddualwieldpredicate(CallbackInfo ci){
        boolean isDualWielding = EpicFightCapabilities.getItemStackCapability(executor.getOffhandItem()).getWeaponCategory() == EpicFightCapabilities.getItemStackCapability(executor.getMainHandItem()).getWeaponCategory();

        if(isDualWielding){
              target.addTag("dualWieldExecAnim");
        }

    }

    @Inject(method = "onFinish", at = @At("HEAD"))
    private void dd$removedualwieldpredicate(CallbackInfo ci){

            target.removeTag("dualWieldExecAnim");


    }




}
