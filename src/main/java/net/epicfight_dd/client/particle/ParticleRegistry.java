package net.epicfight_dd.client.particle;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ParticleRegistry {
    private ParticleRegistry() {}

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Epicfight_dd.MODID);

    public static final RegistryObject<SimpleParticleType> HUNGER_INDICATOR =
            PARTICLES.register("hunger_indicator", () -> new SimpleParticleType(false));
}