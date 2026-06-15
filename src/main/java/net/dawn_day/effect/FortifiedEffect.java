package net.dawn_day.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class FortifiedEffect extends MobEffect {

    public FortifiedEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (!(entity instanceof Player)) {
            return false;
        }
        return super.applyEffectTick(entity, amplifier);
    }


    @Override
    public void addAttributeModifiers(@NotNull AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(attributeMap, amplifier);
    }


}
