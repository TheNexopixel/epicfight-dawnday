package net.dawn_day.registry.entries;

import net.dawn_day.EpicFightDawnDay;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class DawnDayPotions {
    private DawnDayPotions() {}
    public static final DeferredRegister<Potion> REGISTRY =
            DeferredRegister.create(BuiltInRegistries.POTION, EpicFightDawnDay.MOD_ID);

    public static final DeferredHolder<Potion, Potion> STUNARMOR =
            REGISTRY.register("stun_armor", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.UNFLINCHING, 4000, 0)));

    public static final DeferredHolder<Potion, Potion> STUNARMOR_LONG =
            REGISTRY.register("stun_armor_long", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.UNFLINCHING, 10400, 0)));

    public static final DeferredHolder<Potion, Potion> STUNARMOR_STRONG =
            REGISTRY.register("stun_armor_strong", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.UNFLINCHING, 2100, 1)));

    public static final DeferredHolder<Potion, Potion> FORTIFIED =
            REGISTRY.register("fortified", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.FORTIFIED, 3600, 0)));

    public static final DeferredHolder<Potion, Potion> FORTIFIED_LONG =
            REGISTRY.register("fortified_long", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.FORTIFIED, 9600, 0)));

    public static final DeferredHolder<Potion, Potion> FORTIFIED_STRONG =
            REGISTRY.register("fortified_strong", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.FORTIFIED, 1800, 1)));

    public static final DeferredHolder<Potion, Potion> STAMINA_STIMULANT =
            REGISTRY.register("staminaregen_potion", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.RECOVERY, 3600, 0)));

    public static final DeferredHolder<Potion, Potion> STRONG_STAMINA_STIMULANT =
            REGISTRY.register("staminaregen_potion_strong", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.RECOVERY, 1700, 1)));

    public static final DeferredHolder<Potion, Potion> LONG_STAMINA_STIMULANT =
            REGISTRY.register("staminaregen_potion_long", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.RECOVERY, 9600, 0)));

    public static final DeferredHolder<Potion, Potion> LONG_STAMINA_POTION =
            REGISTRY.register("stamina_potion_long", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.ENDURANCE, 9600, 0)));

    public static final DeferredHolder<Potion, Potion> STRONG_STAMINA_POTION =
            REGISTRY.register("stamina_potion_strong", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.ENDURANCE, 1700, 1)));

    public static final DeferredHolder<Potion, Potion> STAMINA_POTION =
            REGISTRY.register("stamina_potion", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.ENDURANCE, 3600, 0)));

    public static final DeferredHolder<Potion, Potion> POWER_POTION =
            REGISTRY.register("potion_of_power", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.EMPOWERED, 1200, 0)));

    public static final DeferredHolder<Potion, Potion> SWEEPING =
            REGISTRY.register("sweeping", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.SWEEPING, 1300, 0)));

    public static final DeferredHolder<Potion, Potion> impactpotion_strong =
            REGISTRY.register("potion_of_stronger_impact", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.CRUSHING, 1700, 1)));

    public static final DeferredHolder<Potion, Potion> impactpotion_long =
            REGISTRY.register("potion_of_longer_impact", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.CRUSHING, 9600, 0)));

    public static final DeferredHolder<Potion, Potion> impactpotion =
            REGISTRY.register("potion_of_impact", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.CRUSHING, 3600, 0)));

    public static final DeferredHolder<Potion, Potion> CRUMBLING =
            REGISTRY.register("crumbling", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.CRUMBLING, 3600, 0)));

    public static final DeferredHolder<Potion, Potion> CRUMBLING_LONG =
            REGISTRY.register("crumbling_long", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.CRUMBLING, 6666, 0)));

    public static final DeferredHolder<Potion, Potion> CRUMBLING_STRONG =
            REGISTRY.register("crumbling_strong", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.CRUMBLING, 2100, 1)));

    public static final DeferredHolder<Potion, Potion> CURSED =
            REGISTRY.register("cursed", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.CURSED, 2960, 0)));

    public static final DeferredHolder<Potion, Potion> CURSED_LONG =
            REGISTRY.register("cursed_long", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.CURSED, 6666, 0)));

    public static final DeferredHolder<Potion, Potion> CURSED_STRONG =
            REGISTRY.register("cursed_strong", () ->
                    new Potion(new MobEffectInstance(DawnDayEffects.CURSED, 1929, 1)));


}
