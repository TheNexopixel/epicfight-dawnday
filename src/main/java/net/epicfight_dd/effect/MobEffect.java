package net.epicfight_dd.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class MobEffect extends net.minecraft.world.effect.MobEffect {

    public MobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
    }


    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
}