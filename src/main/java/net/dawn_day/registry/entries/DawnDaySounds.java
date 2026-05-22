package net.dawn_day.registry.entries;

import net.dawn_day.EpicFightDawnDay;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class DawnDaySounds {

    private DawnDaySounds() {}
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, EpicFightDawnDay.MOD_ID);
    public static final DeferredHolder<SoundEvent, SoundEvent> MILADY_LIGHT_SLASH = registerSound("sfx.milady_slash");
    public static final DeferredHolder<SoundEvent, SoundEvent> MILADY_HEAVY_SLASH = registerSound("sfx.milady_heavy_slash");
    public static final DeferredHolder<SoundEvent, SoundEvent> MILADY_DUAL_SLASH = registerSound("sfx.milady_dual_slash");
    public static final DeferredHolder<SoundEvent, SoundEvent> PIERCING_FANG = registerSound("sfx.piercing_fang");
    public static final DeferredHolder<SoundEvent, SoundEvent> MILADY_LIGHT_SWEEP = registerSound("sfx.milady_extra_light");
    public static final DeferredHolder<SoundEvent, SoundEvent> BATTLE_STAFF_SWING = registerSound("sfx.battlestaff_swing");
    public static final DeferredHolder<SoundEvent, SoundEvent> GUARD_BREAK = registerSound("sfx.guard_break");
    public static final DeferredHolder<SoundEvent, SoundEvent> POISE_BREAK = registerSound("sfx.poise_break");
    public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_BANGING = registerSound("sfx.skeleton_banging");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOFT_WIPE = registerSound("sfx.soft_wipe");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRITICAL_HIT = registerSound("sfx.critical_hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> PULL_OUT = registerSound("sfx.pull_out");
    public static final DeferredHolder<SoundEvent, SoundEvent> KILLED1 = registerSound("sfx.killed1");
    public static final DeferredHolder<SoundEvent, SoundEvent> KILLED2 = registerSound("sfx.killed2");
    public static final DeferredHolder<SoundEvent, SoundEvent> KILLED3 = registerSound("sfx.killed3");
    public static final DeferredHolder<SoundEvent, SoundEvent> DRAINED = registerSound("sfx.drained");
    public static final DeferredHolder<SoundEvent, SoundEvent> CURSED1 = registerSound("sfx.cursed_1");
    public static final DeferredHolder<SoundEvent, SoundEvent> CURSED2 = registerSound("sfx.cursed_2");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        ResourceLocation res = ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(res));
    }
}
