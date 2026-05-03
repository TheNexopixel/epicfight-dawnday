package net.epicfight_dd.effect;

import com.hm.efn.mobeffects.AttackSpeedIncreaseEffect;
import net.epicfight_dd.Epicfight_dd;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.UUID;

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Epicfight_dd.MODID);

    public static final RegistryObject<MobEffect> IMPREGNABILITY =
            EFFECTS.register("impregnability",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xFFC0C0C0)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, "df227945-3f3f-46d9-b600-cc0b9139ffb9", 0.15d, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(Attributes.ARMOR, "df227985-3f8f-46d9-b600-cc0b9139ffb9", 8.0d, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(EpicFightAttributes.IMPACT.get(), "df227935-3f3f-46d9-b600-cc0b9139ffb9", 2.5, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(EpicFightAttributes.STAMINA_REGEN.get(), "df227985-3f0f-46d9-b600-cc0b9139ffb9", 0.5, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> ENDURANCE =
            EFFECTS.register("endurance",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xffe100)
                    .addAttributeModifier(EpicFightAttributes.STAMINA_REGEN.get(), "3f7c9c7e-8c4d-4c4a-a2d7-5c6b9f2a1e8d", 0.33, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> STAMNIA =
            EFFECTS.register("stamina",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0x00ff77)
                    .addAttributeModifier(EpicFightAttributes.MAX_STAMINA.get(), "3f2504e0-4f89-11d3-9a0c-0305e82c3301", 5.0, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> IMPACT =
            EFFECTS.register("impact",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xbf7c41)
                    .addAttributeModifier(EpicFightAttributes.IMPACT.get(), "e7b8c1d2-9a3f-4c12-8f6a-1b2c3d4e5f60", 1.0, AttributeModifier.Operation.ADDITION)
            );

    public static final RegistryObject<MobEffect> POTION_OF_POWER =
            EFFECTS.register("potion_of_power",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0x750000)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, "9b2e6a11-0c3d-4f88-bd22-1a9c7e5f3b44", 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL)
            );


}
