package net.epicfight_dd.gameasset;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class dawnDaySounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "t0001");


    public static final RegistryObject<SoundEvent> Milady_light_slash = registerSound("sfx.milady_slash");
    public static final RegistryObject<SoundEvent> Milady_heavy_slash = registerSound("sfx.milady_heavy_slash");
    public static final RegistryObject<SoundEvent> Milady_dual_slash = registerSound("sfx.milady_dual_slash");
    public static final RegistryObject<SoundEvent> piercing_fang = registerSound("sfx.piercing_fang");
    public static final RegistryObject<SoundEvent> milady_light_sweep = registerSound("sfx.milady_extra_light");
    public static final RegistryObject<SoundEvent> battlestaff_swing = registerSound("sfx.battlestaff_swing");




    private static RegistryObject<SoundEvent> registerSound(String name) {
        ResourceLocation res = ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(res));
    }
}
