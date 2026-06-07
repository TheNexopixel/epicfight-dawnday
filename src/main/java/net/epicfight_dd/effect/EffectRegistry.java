package net.epicfight_dd.effect;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Epicfight_dd.MODID);

    public static final RegistryObject<MobEffect> IMPREGNABILITY =
            EFFECTS.register("impregnability",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xFFC0C0C0)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, "af227945-3f3f-46d9-b600-cc0b9139ffb9", 0.15d, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, "df227945-3b3f-46d9-b600-cc0b9139ffb9", 0.15d, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(Attributes.ARMOR, "df227985-3f8f-46d9-b600-cc0b9139ffb9", 8.0d, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(EpicFightAttributes.IMPACT.get(), "df227935-3f3f-46d9-b600-cc0b9139ffb9", 2.5, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(EpicFightAttributes.STAMINA_REGEN.get(), "df227985-3f0f-46d9-b600-cc0b9139ffb9", 0.5, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> ENDURANCE =
            EFFECTS.register("endurance",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xffe100)
                    .addAttributeModifier(EpicFightAttributes.STAMINA_REGEN.get(), "3f7c9c7e-8c4d-4c4a-a2d7-5c6b9f2a1e8d", 0.33, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> EXHAUSTED =
            EFFECTS.register("exhausted",()-> new FortifiedEffect(MobEffectCategory.HARMFUL,0x222e00)
                    .addAttributeModifier(EpicFightAttributes.STAMINA_REGEN.get(), "3f7c9c7e-8c4d-4c1a-a2d7-5c6b9f2a1e2d", -0.40, AttributeModifier.Operation.MULTIPLY_TOTAL)
            );

    public static final RegistryObject<MobEffect> STAMNIA =
            EFFECTS.register("stamina",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0x00ff77)
                    .addAttributeModifier(EpicFightAttributes.MAX_STAMINA.get(), "3f2504e0-4f89-11d3-9a0c-0305e82c3301", 5.0, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> STUNARMOR =
            EFFECTS.register("stunarmor",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xf56d33)
                    .addAttributeModifier(EpicFightAttributes.STUN_ARMOR.get(), "3f2504e0-4f29-11d3-9a0c-0305e82c3301", 10.0, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> IMPACT =
            EFFECTS.register("impact",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xbf7c41)
                    .addAttributeModifier(EpicFightAttributes.IMPACT.get(), "e7b8c1d2-9a3f-4c12-8f6a-1b2c3d4e5f60", 3.0, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> POTION_OF_POWER =
            EFFECTS.register("potion_of_power",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0x750000)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, "9b2e6a11-0c3d-4f88-bd22-1a9c7e5f3b44", 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL)
            );

    public static final RegistryObject<MobEffect> SWEEPING =
            EFFECTS.register("sweeping",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xbde880)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, "9b2e3a11-0c3d-4f88-bd22-1a9c7e5f3b44", 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)
            );

    public static final RegistryObject<MobEffect> FORTIFIED =
            EFFECTS.register("fortified",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xded6ca)
                    .addAttributeModifier(Attributes.ARMOR, "9b2e3a11-1c3d-4f88-bd22-1a9c7e5f3b44", 6.0, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "9b2e3a13-1c3d-4f88-bd22-1a9c7e5f3b44", 2.5, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> SEPUKKU =
            EFFECTS.register("sepukku",()-> new SepukkuEffect(MobEffectCategory.BENEFICIAL,0xa11106)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, "9b2e3a11-1c3d-4f58-bd22-1a9c7e5f3b44", 0.3, AttributeModifier.Operation.MULTIPLY_BASE)
                    .addAttributeModifier(EpicFightAttributes.IMPACT.get(), "9b2e3a11-9a3f-4c12-8f6a-1b2c3d4e5f60", 0.6, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> DRAINDED =
            EFFECTS.register("drained",()-> new FortifiedEffect(MobEffectCategory.HARMFUL,0xa11106)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "9b2e3a11-1c1d-4f58-bd22-1a9c7e5f3b44", -0.4, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(EpicFightAttributes.IMPACT.get(), "9b2e3a11-9a3f-4c12-8f6a-1b2c3d3e5f60", -0.435, AttributeModifier.Operation.MULTIPLY_TOTAL)
            );

    public static final RegistryObject<MobEffect> CRUMBLING =
            EFFECTS.register("crumbling",()-> new FortifiedEffect(MobEffectCategory.HARMFUL,0xa18074)
                    .addAttributeModifier(Attributes.ARMOR, "9b2e3a10-0c3d-4f88-bd22-1a9c7e5f3b44", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "9b2e3a10-0c3d-4f88-bd22-5a9c7e5f3b44", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL)
            );
    public static final RegistryObject<MobEffect> CURSED =
            EFFECTS.register("cursed",()-> new FortifiedEffect(MobEffectCategory.HARMFUL,0xa0303)
                    .addAttributeModifier(Attributes.MAX_HEALTH, "9b2e3a15-0c3d-4f88-bd22-1a9c7e5f3b44", -0.3, AttributeModifier.Operation.MULTIPLY_TOTAL)
            );



}
