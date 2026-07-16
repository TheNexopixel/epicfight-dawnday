package net.epicfight_dd.effect;

import net.epicfight_dd.DawnDayConfig;
import net.epicfight_dd.DawnDayServerConfig;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.UUID;

public class SepukkuEffect extends MobEffect {

    private static final UUID ATTACK_DAMAGE_UUID =
            UUID.fromString("9b2e3a11-1c3d-4f58-bd22-1a9c7e5f3b44");

    private static final UUID IMPACT_UUID =
            UUID.fromString("9b2e3a11-9a3f-4c12-8f6a-1b2c3d4e5f60");

    protected SepukkuEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity entity,
                                      @NotNull AttributeMap map,
                                      int amplifier) {

        super.addAttributeModifiers(entity, map, amplifier);

        if (!(entity instanceof Player)) {
            return;
        }

        AttributeInstance attack = map.getInstance(Attributes.ATTACK_DAMAGE);

        if (attack != null) {
            attack.removeModifier(ATTACK_DAMAGE_UUID);

            attack.addPermanentModifier(
                    new AttributeModifier(
                            ATTACK_DAMAGE_UUID,
                            "Sepukku Attack Damage",
                            DawnDayConfig.SEPUKKU_ATTACK_DAMAGE.get(),
                            AttributeModifier.Operation.MULTIPLY_BASE
                    )
            );
        }

        AttributeInstance impact = map.getInstance(yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes.IMPACT.get());

        if (impact != null) {
            impact.removeModifier(IMPACT_UUID);

            impact.addPermanentModifier(
                    new AttributeModifier(
                            IMPACT_UUID,
                            "Sepukku Impact",
                            DawnDayConfig.SEPUKKU_IMPACT.get(),
                            AttributeModifier.Operation.ADDITION
                    )
            );
        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity entity,
                                         @NotNull AttributeMap map,
                                         int amplifier) {

        super.removeAttributeModifiers(entity, map, amplifier);

        AttributeInstance attack = map.getInstance(Attributes.ATTACK_DAMAGE);

        if (attack != null) {
            attack.removeModifier(ATTACK_DAMAGE_UUID);
        }

        AttributeInstance impact = map.getInstance(EpicFightAttributes.IMPACT.get());

        if (impact != null) {
            impact.removeModifier(IMPACT_UUID);
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            entity.getServer().execute(() ->
                    entity.addEffect(new MobEffectInstance(
                            EffectRegistry.DRAINDED.get(),
                            350,
                            0
                    ))
            );
        }
    }
}