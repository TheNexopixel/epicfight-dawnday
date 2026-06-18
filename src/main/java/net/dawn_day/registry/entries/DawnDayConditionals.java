package net.dawn_day.registry.entries;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.world.capabilities.item.DawnDayWeaponCategories;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.ex_cap.provider.ProviderConditional;
import yesman.epicfight.registry.deferred.ProviderConditionalRegister;
import yesman.epicfight.registry.deferred.holders.DeferredConditional;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

@SuppressWarnings("UnstableApiUsage")
public final class DawnDayConditionals
{
    private DawnDayConditionals() {}

    public static ProviderConditionalRegister REGISTRY = ProviderConditionalRegister.create(EpicFightDawnDay.MOD_ID);

    public static final DeferredConditional DUAL_LIGHT_GREATSWORD = REGISTRY.registerConditional(
            "dual_light_greatsword",
            () -> ProviderConditional.createWeaponCategory(CapabilityItem.Styles.TWO_HAND, DawnDayWeaponCategories.LIGHT_GREATSWORD, InteractionHand.OFF_HAND, true)
    );

    public static final DeferredConditional DUAL_LONGSWORDS = REGISTRY.registerConditional(
            "dual_longswords",
            () -> ProviderConditional.createWeaponCategory(CapabilityItem.Styles.TWO_HAND, CapabilityItem.WeaponCategories.LONGSWORD, InteractionHand.OFF_HAND, true)
    );

    public static final DeferredConditional WINGSTANCE_ACTIVE = REGISTRY.registerConditional(
            "wingstance_active",
            () -> ProviderConditional.createSkillDataKey(CapabilityItem.Styles.OCHS, DawnDaySkills.WINGSTANCE, SkillSlots.WEAPON_PASSIVE, DawnDaySkillDataKeys.SPECIAL_STANCE_ACTIVATE, false)
    );

    public static final DeferredConditional DUAL_AXES = REGISTRY.registerConditional("dual_axes", () ->
            ProviderConditional.createWeaponCategory(CapabilityItem.Styles.TWO_HAND, CapabilityItem.WeaponCategories.AXE, InteractionHand.OFF_HAND, true)
    );

    public static final DeferredConditional DUAL_GREATSWORDS = REGISTRY.registerConditional("dual_greatswords", () ->
            ProviderConditional.createWeaponCategory(CapabilityItem.Styles.TWO_HAND, CapabilityItem.WeaponCategories.GREATSWORD, InteractionHand.OFF_HAND, true)
    );

    public static final DeferredConditional DUAL_RITUS_DAGGERS = REGISTRY.registerConditional("dual_ritus_daggers", () ->
            ProviderConditional.createWeaponCategory(CapabilityItem.Styles.TWO_HAND, DawnDayWeaponCategories.RITUS_DAGGER, InteractionHand.OFF_HAND, true)
    );
    public static final DeferredConditional DUAL_FLORETT = REGISTRY.registerConditional("dual_florett", () ->
            ProviderConditional.createWeaponCategory(CapabilityItem.Styles.TWO_HAND, DawnDayWeaponCategories.FLORETT, InteractionHand.OFF_HAND, true)
    );
    public static final DeferredConditional DUAL_IRON_FIST = REGISTRY.registerConditional("dual_iron_fist", () ->
            ProviderConditional.createWeaponCategory(CapabilityItem.Styles.TWO_HAND, DawnDayWeaponCategories.IRON_FIST, InteractionHand.OFF_HAND, true)
    );

    // Note: Hollow Longsword uses TWO_HAND style when a SHIELD is equipped in the offhand
    public static final DeferredConditional SHIELD_OFFHAND_FORCE_2H = REGISTRY.registerConditional("shield_offhand_force_2h", () ->
            ProviderConditional.createWeaponCategory(CapabilityItem.Styles.TWO_HAND, CapabilityItem.WeaponCategories.SHIELD, InteractionHand.OFF_HAND, true)
    );
}
