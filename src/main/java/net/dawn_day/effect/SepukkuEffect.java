package net.dawn_day.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class SepukkuEffect extends MobEffect {

    public SepukkuEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayer)) {
            return false;
        }
        return super.applyEffectTick(entity, amplifier);
    }


}
