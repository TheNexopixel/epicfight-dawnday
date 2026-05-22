package net.dawn_day.effect;

import net.dawn_day.EpicFightDawnDay;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import yesman.epicfight.registry.entries.EpicFightAttributes;

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, EpicFightDawnDay.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> IMPREGNABILITY =
            EFFECTS.register("impregnability", () -> new FortifiedEffect(MobEffectCategory.BENEFICIAL, 0xFFC0C0C0)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, EpicFightDawnDay.identifier("impregnability_attack_speed"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, potency -> potency * 0.15)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, EpicFightDawnDay.identifier("impregnability_attack_damage"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, potency -> potency * 0.15)
                    .addAttributeModifier(Attributes.ARMOR, EpicFightDawnDay.identifier("impregnability_armor"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * 8.0)
                    .addAttributeModifier(EpicFightAttributes.IMPACT, EpicFightDawnDay.identifier("impregnability_impact"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * 2.5)
                    .addAttributeModifier(EpicFightAttributes.STAMINA_REGEN, EpicFightDawnDay.identifier("impregnability_stamina_regen"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * 0.5)
            );

    public static final DeferredHolder<MobEffect, MobEffect> RECOVERY =
            EFFECTS.register("recovery", () -> new FortifiedEffect(MobEffectCategory.BENEFICIAL, 0xffe100)
                    .addAttributeModifier(EpicFightAttributes.STAMINA_REGEN, EpicFightDawnDay.identifier("recovery_stamina_regen"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * 0.33)
            );

    public static final DeferredHolder<MobEffect, MobEffect> ENDURANCE =
            EFFECTS.register("endurance",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0x00ff77)
                    .addAttributeModifier(EpicFightAttributes.MAX_STAMINA, EpicFightDawnDay.identifier("endurance"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * 5)
            );

    public static final DeferredHolder<MobEffect, MobEffect> UNFLINCHING =
            EFFECTS.register("unflinching",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xf56d33)
                    .addAttributeModifier(EpicFightAttributes.STUN_ARMOR, EpicFightDawnDay.identifier("unflinching"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * 10)
            );

    public static final DeferredHolder<MobEffect, MobEffect> CRUSHING =
            EFFECTS.register("crushing",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xbf7c41)
                    .addAttributeModifier(EpicFightAttributes.IMPACT, EpicFightDawnDay.identifier("crushing"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * 3)
            );

    public static final DeferredHolder<MobEffect, MobEffect> SWEEPING =
            EFFECTS.register("sweeping",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xbde880)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, EpicFightDawnDay.identifier("sweeping"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, potency -> potency * 0.15)
            );

    public static final DeferredHolder<MobEffect, MobEffect> EMPOWERED =
            EFFECTS.register("empowered",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0x750000)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, EpicFightDawnDay.identifier("empowered"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, potency -> potency * 0.25)
            );

    public static final DeferredHolder<MobEffect, MobEffect> FORTIFIED =
            EFFECTS.register("fortified", () -> new FortifiedEffect(MobEffectCategory.BENEFICIAL, 0xded6ca)
                    .addAttributeModifier(Attributes.ARMOR, EpicFightDawnDay.identifier("fortified"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * 6.0)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, EpicFightDawnDay.identifier("fortified"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * 2.5)
            );

    public static final DeferredHolder<MobEffect, MobEffect> SEPUKKU =
            EFFECTS.register("sepukku", () -> new SepukkuEffect(MobEffectCategory.BENEFICIAL, 0xa11106)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, EpicFightDawnDay.identifier("sepukku"), AttributeModifier.Operation.ADD_MULTIPLIED_BASE, potency -> potency * 0.3)
                    .addAttributeModifier(EpicFightAttributes.IMPACT, EpicFightDawnDay.identifier("sepukku"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * 1.0)
            );

    public static final DeferredHolder<MobEffect, MobEffect> DRAINED =
            EFFECTS.register("drained", () -> new FortifiedEffect(MobEffectCategory.HARMFUL, 0xa11106)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, EpicFightDawnDay.identifier("drained"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, potency -> potency * -0.4)
                    .addAttributeModifier(EpicFightAttributes.IMPACT, EpicFightDawnDay.identifier("drained"), AttributeModifier.Operation.ADD_VALUE, potency -> potency * -0.5)
            );

    public static final DeferredHolder<MobEffect, MobEffect> CRUMBLING =
            EFFECTS.register("crumbling", () -> new FortifiedEffect(MobEffectCategory.HARMFUL, 0xa18074)
                    .addAttributeModifier(Attributes.ARMOR, EpicFightDawnDay.identifier("crumbling"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, potency -> potency * -0.25)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, EpicFightDawnDay.identifier("crumbling"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, potency -> potency * -0.5)
            );

    public static final DeferredHolder<MobEffect, MobEffect> CURSED =
            EFFECTS.register("cursed", () -> new FortifiedEffect(MobEffectCategory.HARMFUL, 0xa0303)
                    .addAttributeModifier(Attributes.MAX_HEALTH, EpicFightDawnDay.identifier("cursed"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, potency -> potency * -0.3)
            );



}
