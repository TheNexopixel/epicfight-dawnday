package net.epicfight_dd.client.particle;

import net.epicfight_dd.EpicFightDawnDay;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ParticleRegistry {
    private ParticleRegistry() {}

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EpicFightDawnDay.MODID);

    private static RegistryObject<ParticleType<?>> register(String name, ParticleType<?> type) {
        return PARTICLES.register(name, () -> type);
    }

    private static RegistryObject<SimpleParticleType> registerSimple(String name, SimpleParticleType type) {
        return PARTICLES.register(name, () -> type);
    }

    public static final RegistryObject<SimpleParticleType> HUNGER_INDICATOR = registerSimple("hunger_indicator", new SimpleParticleType(false));
}