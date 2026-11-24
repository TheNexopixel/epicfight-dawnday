package net.epicfight_dd.world.entity.decoration;

import net.epicfight_dd.EpicFightDawnDay;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DawnDayPaintings {

    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(Registries.PAINTING_VARIANT, EpicFightDawnDay.MODID);

    private static RegistryObject<PaintingVariant> register(String name, int width, int height) {
        return PAINTING_VARIANTS.register(name, () -> new PaintingVariant(width, height));
    }

    public static final RegistryObject<PaintingVariant> CULTIST = register("cultist", 16, 32);

    public static final RegistryObject<PaintingVariant> WILTED_FLOWER = register("wilted_flower", 16, 32);

    public static final RegistryObject<PaintingVariant> THUNDERSTORM = register("thunderstorm", 32, 32);

    public static final RegistryObject<PaintingVariant> RISING_CITY = register("rising_city", 64, 64);

    public static final RegistryObject<PaintingVariant> MOUNTAIN = register("mountain", 16, 16);

    public static final RegistryObject<PaintingVariant> VEILED_SMOKE = register("veiled_smoke", 32, 16);

}