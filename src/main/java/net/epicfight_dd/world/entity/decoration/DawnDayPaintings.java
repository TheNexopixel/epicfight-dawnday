package net.epicfight_dd.world.entity.decoration;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DawnDayPaintings {

    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(Registries.PAINTING_VARIANT, Epicfight_dd.MODID);

    public static final RegistryObject<PaintingVariant> CULTIST =
            PAINTING_VARIANTS.register("cultist",
                    () -> new PaintingVariant(16, 32));

    public static final RegistryObject<PaintingVariant> WILTED_FLOWER =
            PAINTING_VARIANTS.register("wilted_flower",
                    () -> new PaintingVariant(16, 32));

    public static final RegistryObject<PaintingVariant> THUNDERSTORM =
            PAINTING_VARIANTS.register("thunderstorm",
                    () -> new PaintingVariant(32, 32));

    public static final RegistryObject<PaintingVariant> MOUNTAIN =
            PAINTING_VARIANTS.register("mountain",
                    () -> new PaintingVariant(16, 16));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}