package F.epicfight_dd.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.shelmarow.combat_evolution.execution.ExecutionHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.item.EpicFightItems;

@Mixin(value = ExecutionHandler.class,remap = false)
public abstract class ExecutionHandlerMixin {
    @Inject(method = "isHoldingWeapon", at = @At("HEAD"), cancellable = true)
    private static void allowExecuteByFist(Player player, CallbackInfoReturnable<Boolean> cir) {
        CapabilityItem capabilityItem = EpicFightCapabilities.getItemStackCapability(player.getItemInHand(InteractionHand.MAIN_HAND));
        cir.setReturnValue(((capabilityItem).getWeaponCategory() != CapabilityItem.WeaponCategories.NOT_WEAPON
                && (capabilityItem.getWeaponCategory() != CapabilityItem.WeaponCategories.FIST))
                || player.getItemInHand(InteractionHand.MAIN_HAND).getItem().equals(EpicFightItems.GLOVE.get()));
    }

}