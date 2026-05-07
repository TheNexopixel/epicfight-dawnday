package net.epicfight_dd.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class FortifiedEffect extends MobEffect {

    protected FortifiedEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (!(entity instanceof Player)) {
            return;
        }

        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return super.isDurationEffectTick(duration, amplifier);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap map, int amplifier) {
        if (entity instanceof Player) {
            super.addAttributeModifiers(entity, map, amplifier);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap map, int amplifier) {
        if (entity instanceof Player) {
            super.removeAttributeModifiers(entity, map, amplifier);
        }
    }



}
