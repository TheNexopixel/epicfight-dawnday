package net.dawn_day.effect;

import net.dawn_day.DawnDayConfig;
import net.dawn_day.EpicFightDawnDay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import yesman.epicfight.registry.entries.EpicFightAttributes;

public class SepukkuEffect extends MobEffect {

    private static final ResourceLocation ATTACK_DAMAGE_ID =
            ResourceLocation.fromNamespaceAndPath(
                    EpicFightDawnDay.MOD_ID,
                    "9b2e3a11-1c3d-4f58-bd22-1a9c7e5f3b44"
            );

    private static final ResourceLocation IMPACT_ID =
            ResourceLocation.fromNamespaceAndPath(
                    EpicFightDawnDay.MOD_ID,
                    "9b2e3a11-9a3f-4c12-8f6a-1b2c3d4e5f60"
            );

    public SepukkuEffect(MobEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }

    @Override
    public void addAttributeModifiers(
            AttributeMap attributes,
            int amplifier
    ) {
        super.addAttributeModifiers(attributes, amplifier);

        AttributeInstance attack =
                attributes.getInstance(Attributes.ATTACK_DAMAGE);

        if (attack != null) {
            attack.removeModifier(ATTACK_DAMAGE_ID);

            attack.addPermanentModifier(
                    new AttributeModifier(
                            ATTACK_DAMAGE_ID,
                            DawnDayConfig.SEPUKKU_ATTACK_DAMAGE.get(),
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    )
            );
        }

        AttributeInstance impact =
                attributes.getInstance(EpicFightAttributes.IMPACT);

        if (impact != null) {
            impact.removeModifier(IMPACT_ID);

            impact.addPermanentModifier(
                    new AttributeModifier(
                            IMPACT_ID,
                            DawnDayConfig.SEPUKKU_IMPACT.get(),
                            AttributeModifier.Operation.ADD_VALUE
                    )
            );
        }
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributes) {
        super.removeAttributeModifiers(attributes);

        AttributeInstance attack =
                attributes.getInstance(Attributes.ATTACK_DAMAGE);

        if (attack != null) {
            attack.removeModifier(ATTACK_DAMAGE_ID);
        }

        AttributeInstance impact =
                attributes.getInstance(EpicFightAttributes.IMPACT);

        if (impact != null) {
            impact.removeModifier(IMPACT_ID);
        }
    }
}

