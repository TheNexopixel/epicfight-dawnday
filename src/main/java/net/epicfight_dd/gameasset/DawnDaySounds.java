package net.epicfight_dd.gameasset;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DawnDaySounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "epicfight_dd");


    public static final RegistryObject<SoundEvent> Milady_light_slash = registerSound("sfx.milady_slash");
    public static final RegistryObject<SoundEvent> Milady_heavy_slash = registerSound("sfx.milady_heavy_slash");
    public static final RegistryObject<SoundEvent> Milady_dual_slash = registerSound("sfx.milady_dual_slash");
    public static final RegistryObject<SoundEvent> piercing_fang = registerSound("sfx.piercing_fang");
    public static final RegistryObject<SoundEvent> milady_light_sweep = registerSound("sfx.milady_extra_light");
    public static final RegistryObject<SoundEvent> battlestaff_swing = registerSound("sfx.battlestaff_swing");
    public static final RegistryObject<SoundEvent> guard_break = registerSound("sfx.guard_break");
    public static final RegistryObject<SoundEvent> poise_break = registerSound("sfx.poise_break");
    public static final RegistryObject<SoundEvent> GUARD_COUNTER = registerSound("sfx.guard_counter");
    public static final RegistryObject<SoundEvent> REDEMPTION = registerSound("sfx.redemption");
    public static final RegistryObject<SoundEvent> soft_wipe = registerSound("sfx.soft_wipe");
    public static final RegistryObject<SoundEvent> Light_Punch = registerSound("sfx.light_punch");
    public static final RegistryObject<SoundEvent> CRITICAL_HIT = registerSound("sfx.critical_hit");
    public static final RegistryObject<SoundEvent> PULL_OUT = registerSound("sfx.pull_out");
    public static final RegistryObject<SoundEvent> KILLED1 = registerSound("sfx.killed1");
    public static final RegistryObject<SoundEvent> KILLED2 = registerSound("sfx.killed2");
    public static final RegistryObject<SoundEvent> KILLED3 = registerSound("sfx.killed3");
    public static final RegistryObject<SoundEvent> DRAINED = registerSound("sfx.drained");
    public static final RegistryObject<SoundEvent> CURSED1 = registerSound("sfx.cursed_1");
    public static final RegistryObject<SoundEvent> CURSED2 = registerSound("sfx.cursed_2");





    private static RegistryObject<SoundEvent> registerSound(String name) {
        ResourceLocation res = ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(res));
    }
}
