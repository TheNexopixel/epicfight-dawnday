package net.epicfight_dd.effect;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DawnDayPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, Epicfight_dd.MODID);

    public static final RegistryObject<Potion> IMPREGNABILITY_POTION =
            POTIONS.register("impregnability", () ->
                    new Potion(
                            new MobEffectInstance(
                                    EffectRegistry.IMPREGNABILITY.get(),
                                    3600,
                                    0
                            )
                    )
            );
}
