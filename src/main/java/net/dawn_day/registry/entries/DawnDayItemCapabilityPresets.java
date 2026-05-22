package net.dawn_day.registry.entries;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.gameasset.DawnDaySounds;
import net.dawn_day.gameasset.animation.DawnDayCollider;
import net.dawn_day.world.capabilities.item.DawnDayWeaponCategories;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.registry.deferred.ItemPresetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredWeapon;
import yesman.epicfight.registry.entries.EpicFightParticles;
import yesman.epicfight.registry.entries.EpicFightProviderConditionals;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import static net.dawn_day.registry.entries.DawnDayConditionals.*;
import static net.dawn_day.registry.entries.DawnDayMovesets.*;
import static net.dawn_day.registry.entries.DawnDayMovesets.Milady.*;

public final class DawnDayItemCapabilityPresets
{
    private DawnDayItemCapabilityPresets() {}
    public static final ItemPresetRegister REGISTRY = ItemPresetRegister.create(EpicFightDawnDay.MOD_ID);

    public static final DeferredWeapon MILADY = REGISTRY.registerWeapon("milady",
            () -> WeaponCapability.builder()
                    .category(DawnDayWeaponCategories.LIGHT_GREATSWORD)
                    .hitSound(EpicFightSounds.BLADE_HIT)
                    .swingSound(DawnDaySounds.MILADY_LIGHT_SLASH)
                    .collider(DawnDayCollider.LIGHT_GREATSWORD)
                    .canBePlacedOffhand(true)
                    // Priority matters here: It will check for Wingstance first, then Dual Wield, then fallback to 1H
                    .addConditionals(WINGSTANCE_ACTIVE, DUAL_LIGHT_GREATSWORD, EpicFightProviderConditionals.DEFAULT_1H_WIELD_STYLE)
                    .addMoveset(CapabilityItem.Styles.OCHS, MILADY_OCHS)
                    .addMoveset(CapabilityItem.Styles.TWO_HAND, MILADY_2H)
                    .addMoveset(CapabilityItem.Styles.ONE_HAND, MILADY_1H)
    );

    public static final DeferredWeapon LIGHT_GREATSWORD = REGISTRY.registerWeapon("light_greatsword",
            () -> WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.LONGSWORD)
                    .hitSound(EpicFightSounds.BLADE_HIT)
                    .swingSound(DawnDaySounds.MILADY_LIGHT_SLASH)
                    .collider(DawnDayCollider.LIGHT_GREATSWORD)
                    .canBePlacedOffhand(true)
                    // Checks for dual longswords, otherwise falls back to 1H default
                    .addConditionals(DUAL_LONGSWORDS, EpicFightProviderConditionals.DEFAULT_1H_WIELD_STYLE)
                    .addMoveset(CapabilityItem.Styles.TWO_HAND, LIGHT_GREATSWORD_2H)
                    .addMoveset(CapabilityItem.Styles.ONE_HAND, LIGHT_GREATSWORD_1H)
    );

    public static final DeferredWeapon WAR_SICKLE = REGISTRY.registerWeapon("war_sickle", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.SPEAR)
            .collider(DawnDayCollider.WAR_SICKLE)
            .swingSound(DawnDaySounds.MILADY_HEAVY_SLASH)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(false)
            .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, WAR_SICKLE_2H)
    );

    public static final DeferredWeapon BACKHAND_BLADE = REGISTRY.registerWeapon("backhand_blade", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.DAGGER)
            .collider(DawnDayCollider.BACKHAND_BLADE)
            .swingSound(DawnDaySounds.MILADY_LIGHT_SLASH)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(false)
            .addConditionals(EpicFightProviderConditionals.DEFAULT_1H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.ONE_HAND, BACKHAND_BLADE_1H)
    );

    public static final DeferredWeapon HALBERD = REGISTRY.registerWeapon("halberd", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.SPEAR)
            .collider(DawnDayCollider.HALBERD)
            .swingSound(DawnDaySounds.MILADY_HEAVY_SLASH)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(false)
            .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, HALBERD_2H)
    );

    public static final DeferredWeapon BATTLE_STAFF = REGISTRY.registerWeapon("battle_staff", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.SPEAR)
            .collider(DawnDayCollider.BATTLESTAFF_FRONT)
            .swingSound(EpicFightSounds.WHOOSH)
            .hitParticle(EpicFightParticles.HIT_BLUNT)
            .hitSound(EpicFightSounds.BLUNT_HIT)
            .canBePlacedOffhand(false)
            .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, BATTLE_STAFF_2H)
    );

    public static final DeferredWeapon BATTLE_STAFF_WOOD = REGISTRY.registerWeapon("battle_staff_wood", () -> WeaponCapability.builder()
            .parent(BATTLE_STAFF)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, BATTLE_STAFF_WOOD_2H)
    );

    public static final DeferredWeapon HERB_SICKLE = REGISTRY.registerWeapon("herb_sickle", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.AXE)
            .collider(ColliderPreset.SWORD)
            .swingSound(EpicFightSounds.WHOOSH)
            .hitParticle(EpicFightParticles.HIT_BLADE)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(true)
            .addConditionals(DUAL_AXES, EpicFightProviderConditionals.DEFAULT_1H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, HERB_SICKLE_2H)
            .addMoveset(CapabilityItem.Styles.ONE_HAND, HERB_SICKLE_1H)
    );

    public static final DeferredWeapon STEEL_AXE = REGISTRY.registerWeapon("steel_axe", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.AXE)
            .collider(ColliderPreset.LONGSWORD)
            .swingSound(EpicFightSounds.WHOOSH)
            .hitParticle(EpicFightParticles.HIT_BLADE)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(false)
            .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, STEEL_AXE_2H)
    );

    public static final DeferredWeapon KNIFE = REGISTRY.registerWeapon("knife", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.DAGGER)
            .collider(DawnDayCollider.KNIFE)
            .swingSound(DawnDaySounds.MILADY_LIGHT_SWEEP)
            .hitParticle(EpicFightParticles.HIT_BLADE)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(true)
            .addConditionals(EpicFightProviderConditionals.DUAL_DAGGERS, EpicFightProviderConditionals.DEFAULT_1H_WIELD_STYLE) // Assuming EF has DUAL_DAGGERS, else use custom
            .addMoveset(CapabilityItem.Styles.TWO_HAND, KNIFE_2H)
            .addMoveset(CapabilityItem.Styles.ONE_HAND, KNIFE_1H)
    );

    public static final DeferredWeapon SABER = REGISTRY.registerWeapon("saber", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.TACHI)
            .collider(DawnDayCollider.LIGHT_GREATSWORD)
            .swingSound(EpicFightSounds.WHOOSH)
            .hitParticle(EpicFightParticles.HIT_BLADE)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(false)
            .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, SABER_2H)
    );

    public static final DeferredWeapon MESSER = REGISTRY.registerWeapon("messer", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.AXE)
            .collider(DawnDayCollider.KNIFE)
            .swingSound(EpicFightSounds.WHOOSH)
            .hitParticle(EpicFightParticles.HIT_BLADE)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(true)
            .addConditionals(DUAL_AXES, EpicFightProviderConditionals.DEFAULT_1H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.OCHS, MESSER_OCHS)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, MESSER_2H)
            .addMoveset(CapabilityItem.Styles.ONE_HAND, MESSER_1H)
    );

//    public static final DeferredWeapon EVIL_TACHI = REGISTRY.registerWeapon("evil_tachi", () -> WeaponCapability.builder()
//            .category(EpicFightDD_WeaponCategories.EVIL_TACHI)
//            .collider(DawnDayCollider.EVIL_TACHI)
//            .swingSound(EpicFightSounds.WHOOSH)
//            .hitParticle(randomSlashHitParticleTYPE()) // Your static custom method evaluates here
//            .hitSound(EpicFightSounds.BLADE_HIT)
//            .canBePlacedOffhand(false)
//            .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE)
//            .addMoveset(CapabilityItem.Styles.TWO_HAND, EVIL_TACHI_2H)
//    );

    public static final DeferredWeapon BAT = REGISTRY.registerWeapon("bat", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.GREATSWORD)
            .collider(DawnDayCollider.BAT)
            .swingSound(EpicFightSounds.WHOOSH_BIG)
            .hitParticle(EpicFightParticles.HIT_BLUNT)
            .hitSound(EpicFightSounds.BLUNT_HIT)
            .canBePlacedOffhand(false)
            .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, BAT_2H)
    );

    public static final DeferredWeapon BONECUTTING_SAW = REGISTRY.registerWeapon("bonecutting_saw", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.GREATSWORD)
            .collider(DawnDayCollider.BAT_LONGER)
            .swingSound(EpicFightSounds.WHOOSH_BIG)
            .hitParticle(EpicFightParticles.HIT_BLADE)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(false)
            .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, BONECUTTING_SAW_2H)
    );

    public static final DeferredWeapon HOLLOW_LONGSWORD = REGISTRY.registerWeapon("hollow_longsword", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.LONGSWORD)
            .collider(DawnDayCollider.BAT)
            .swingSound(EpicFightSounds.WHOOSH)
            .hitParticle(EpicFightParticles.HIT_BLADE)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(false)
            .addConditionals(SHIELD_OFFHAND_FORCE_2H, EpicFightProviderConditionals.DEFAULT_1H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, HOLLOW_LONGSWORD_2H)
            .addMoveset(CapabilityItem.Styles.ONE_HAND, HOLLOW_LONGSWORD_1H)
    );

    public static final DeferredWeapon GREATAXE = REGISTRY.registerWeapon("greataxe", () -> WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.GREATSWORD)
            .collider(ColliderPreset.GREATSWORD)
            .swingSound(EpicFightSounds.WHOOSH_BIG)
            .hitParticle(EpicFightParticles.HIT_BLADE)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(true)
            .addConditionals(DUAL_GREATSWORDS, EpicFightProviderConditionals.DEFAULT_1H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, GREATAXE_2H)
            .addMoveset(CapabilityItem.Styles.ONE_HAND, GREATAXE_1H)
    );

    public static final DeferredWeapon BLOOD_RITUS_DAGGER = REGISTRY.registerWeapon("blood_ritus_dagger", () -> WeaponCapability.builder()
            .category(DawnDayWeaponCategories.RITUS_DAGGER)
            .collider(DawnDayCollider.KNIFE)
            .swingSound(EpicFightSounds.WHOOSH)
            .hitParticle(EpicFightParticles.HIT_BLADE)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(true)
            .addConditionals(DUAL_RITUS_DAGGERS, EpicFightProviderConditionals.DEFAULT_1H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, RITUS_2H)
            .addMoveset(CapabilityItem.Styles.ONE_HAND, BLOOD_RITUS_1H)
    );

    public static final DeferredWeapon NIGHT_RITUS_DAGGER = REGISTRY.registerWeapon("night_ritus_dagger", () -> WeaponCapability.builder()
            .category(DawnDayWeaponCategories.RITUS_DAGGER)
            .collider(DawnDayCollider.KNIFE)
            .swingSound(EpicFightSounds.WHOOSH)
            .hitParticle(EpicFightParticles.HIT_BLADE)
            .hitSound(EpicFightSounds.BLADE_HIT)
            .canBePlacedOffhand(true)
            .addConditionals(DUAL_RITUS_DAGGERS, EpicFightProviderConditionals.DEFAULT_1H_WIELD_STYLE)
            .addMoveset(CapabilityItem.Styles.TWO_HAND, RITUS_2H)
            .addMoveset(CapabilityItem.Styles.ONE_HAND, NIGHT_RITUS_1H)
    );

}
