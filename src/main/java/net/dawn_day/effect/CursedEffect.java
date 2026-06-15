package net.dawn_day.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CursedEffect extends FortifiedEffect{

    public CursedEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        return entity instanceof ServerPlayer;
    }

    @Override
    public void onEffectAdded(@NotNull LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof ServerPlayer) {
            super.onEffectAdded(livingEntity, amplifier);
        }
    }

//    @Override
//    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, @NotNull LivingEntity livingEntity, int amplifier, double health) {
//        if (livingEntity instanceof ServerPlayer) {
//            super.applyInstantenousEffect(source, indirectSource, livingEntity, amplifier, health);
//        }
//    }
}
