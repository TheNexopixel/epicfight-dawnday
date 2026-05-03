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

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Epicfight_dd.MODID);

    public static final RegistryObject<MobEffect> IMPREGNABILITY =
            EFFECTS.register("impregnability",()-> new FortifiedEffect(MobEffectCategory.BENEFICIAL,0xFFC0C0C0)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, "df227985-3f3f-46d9-b600-cc0b9139ffb9", 0.2d, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(EpicFightAttributes.IMPACT.get(), "df227985-3f3f-46d9-b600-cc0b9139ffb9", 2.5, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(EpicFightAttributes.STAMINA_REGEN.get(), "df227985-3f3f-46d9-b600-cc0b9139ffb9", 1.0, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(EpicFightAttributes.OFFHAND_IMPACT.get(), "df227985-3f3f-46d9-b600-cc0b9139ffb9", 10.5, AttributeModifier.Operation.ADDITION)
            );


}
