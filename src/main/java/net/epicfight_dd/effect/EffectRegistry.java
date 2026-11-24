package net.epicfight_dd.effect;

import net.epicfight_dd.EpicFightDawnDay;
import net.epicfight_dd.attribute.DawnDayAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.function.Supplier;

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EpicFightDawnDay.MODID);

    // helpers
    @FunctionalInterface
    private interface EffectFactory {
        MobEffect create(MobEffectCategory category, int color);
    }

    private record Mod(Supplier<? extends Attribute> attribute, String uuid, double amount, AttributeModifier.Operation operation) {}

    private static Mod mod(Supplier<? extends Attribute> attribute, String uuid, double amount, AttributeModifier.Operation operation) {
        return new Mod(attribute, uuid, amount, operation);
    }

    private static Mod mod(Attribute attribute, String uuid, double amount, AttributeModifier.Operation operation) {
        return new Mod(() -> attribute, uuid, amount, operation);
    }

    private static RegistryObject<MobEffect> register(String name, EffectFactory factory, MobEffectCategory category, int color, Mod... mods) {
        return EFFECTS.register(name, () -> {
            MobEffect effect = factory.create(category, color);
            for (Mod m : mods) {
                effect.addAttributeModifier(m.attribute().get(), m.uuid(), m.amount(), m.operation());
            }
            return effect;
        });
    }

    private static RegistryObject<MobEffect> registerEffect(String name, MobEffectCategory category, int color, Mod... mods) {
        return register(name, net.epicfight_dd.effect.MobEffect::new, category, color, mods);
    }

    // ---------------------------------------------------------------- effects

    public static final RegistryObject<MobEffect> UNSTOPPABLE_BARRIER =
            register("unstoppable_barrier", PlayerOnlyEffect::new, MobEffectCategory.BENEFICIAL, 0xD8B85A);

    public static final RegistryObject<MobEffect> ENDURANCE =
            registerEffect("endurance", MobEffectCategory.BENEFICIAL, 0xffe100,
                    mod(EpicFightAttributes.STAMINA_REGEN, "3f7c9c7e-8c4d-4c4a-a2d7-5c6b9f2a1e8d", 0.33, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> HEART_STOP =
            registerEffect("heartstop", MobEffectCategory.HARMFUL, 0x9c0034,
                    mod(DawnDayAttributes.HEALING, "3f7c9c3e-8c4d-4c4a-a2d7-5c6b9f2a1e8d", -0.5, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> BLOOD_LOSS =
            registerEffect("blood_loss", MobEffectCategory.HARMFUL, 0x9c0034,
                    mod(DawnDayAttributes.HEALING, "3f7c9c3e-8c4d-4c4a-a2d1-5c6b9f2a1e8d", -0.01, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> BRUISING =
            registerEffect("bruising", MobEffectCategory.HARMFUL, 0x9c0034,
                    mod(Attributes.ATTACK_SPEED, "3f7c9c3e-8c1d-4c4a-a2d7-5c6b9f2a1e8d", -0.15, AttributeModifier.Operation.ADDITION),
                    mod(Attributes.MOVEMENT_SPEED, "3f7c9c3e-8c1d-4c4a-a2d5-5c6b9f2a1e8d", -0.015, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> EXHAUSTED =
            registerEffect("exhausted", MobEffectCategory.HARMFUL, 0x222e00,
                    mod(EpicFightAttributes.STAMINA_REGEN, "3f7c9c7e-8c4d-4c1a-a2d7-5c6b9f2a1e2d", -0.40, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> STAMNIA =
            registerEffect("stamina", MobEffectCategory.BENEFICIAL, 0x00ff77,
                    mod(EpicFightAttributes.MAX_STAMINA, "3f2504e0-4f89-11d3-9a0c-0305e82c3301", 10.0, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> STUNARMOR =
            registerEffect("stunarmor", MobEffectCategory.BENEFICIAL, 0xf56d33,
                    mod(EpicFightAttributes.STUN_ARMOR, "3f2504e0-4f29-11d3-9a0c-0305e82c3301", 10.0, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> IMPACT =
            registerEffect("impact", MobEffectCategory.BENEFICIAL, 0xbf7c41,
                    mod(EpicFightAttributes.IMPACT, "e7b8c1d2-9a3f-4c12-8f6a-1b2c3d4e5f60", 3.0, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> MIGHT =
            registerEffect("might", MobEffectCategory.BENEFICIAL, 0x750000,
                    mod(Attributes.ATTACK_DAMAGE, "9b2e6a11-0c3d-4f88-bd22-1a9c7e5f3b44", 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> ACCELERATION =
            registerEffect("acceleration", MobEffectCategory.BENEFICIAL, 0xbde880,
                    mod(Attributes.ATTACK_SPEED, "9b2e3a11-0c3d-4f88-bd22-1a9c7e5f3b44", 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> FORTIFIED =
            registerEffect("fortified", MobEffectCategory.BENEFICIAL, 0xded6ca,
                    mod(Attributes.ARMOR, "9b2e3a11-1c3d-4f88-bd22-1a9c7e5f3b44", 6.0, AttributeModifier.Operation.ADDITION),
                    mod(Attributes.ARMOR_TOUGHNESS, "9b2e3a13-1c3d-4f88-bd22-1a9c7e5f3b44", 2.5, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> SEPUKKU =
            register("sepukku", SepukkuEffect::new, MobEffectCategory.BENEFICIAL, 0xa11106);

    public static final RegistryObject<MobEffect> DRAINDED =
            registerEffect("drained", MobEffectCategory.HARMFUL, 0xa11106,
                    mod(DawnDayAttributes.HEALING, "9b2e3a11-9a3f-4c12-8f6a-1b2c3d3e5f60", -0.5, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> CRUMBLING =
            registerEffect("crumbling", MobEffectCategory.HARMFUL, 0xa18074,
                    mod(Attributes.ARMOR, "9b2e3a10-0c3d-4f88-bd22-1a9c7e5f3b44", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL),
                    mod(Attributes.ARMOR_TOUGHNESS, "9b2e3a10-0c3d-4f88-bd22-5a9c7e5f3b44", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> CURSED =
            register("cursed", PlayerOnlyEffect::new, MobEffectCategory.HARMFUL, 0xa0303,
                    mod(Attributes.MAX_HEALTH, "9b2e3a15-0c3d-4f88-bd22-1a9c7e5f3b44", -0.3, AttributeModifier.Operation.MULTIPLY_TOTAL));
}