package net.epicfight_dd.mixin;

import net.epicfight_dd.skill.identity.GorgedBulwarkSkill;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class GorgedBulwarkMixin {
    @Inject(method = "canEat", at = @At("HEAD"), cancellable = true)
    private void epicfight_dd$eatAnytime(boolean alwaysEdible, CallbackInfoReturnable<Boolean> cir) {
        if (GorgedBulwarkSkill.isActiveFor((Player) (Object) this)) {
            cir.setReturnValue(true);
        }
    }
}
