package net.dawn_day.registry.entries;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.gameasset.animation.AdditionalAnimations;
import net.dawn_day.gameasset.animation.DawnDayAnimations;

import java.util.Set;

import net.dawn_day.skill.stances.WingStanceSkill;
import net.dawn_day.skill.weapon_innate.*;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.registry.EpicFightRegistries;
import yesman.epicfight.registry.entries.EpicFightParticles;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.StunType;

public final class DawnDaySkills {

    private DawnDaySkills() {}

    public static final DeferredRegister<Skill> REGISTRY = DeferredRegister.create(EpicFightRegistries.Keys.SKILL, EpicFightDawnDay.MOD_ID);

    public static final DeferredHolder<Skill, WeaponInnateSkill> GENTLE_NUDGE = REGISTRY.register("gentle_nudge", key ->
        SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
                .setAnimations(DawnDayAnimations.MILADY_KNUCKLE_INNATE)
                .setCategory(SkillCategories.WEAPON_INNATE)
                .newProperty()
                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AttackPhaseProperty.HIT_SOUND, DawnDaySounds.POISE_BREAK.get())
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
                .build(key)
    );

    public static final DeferredHolder<Skill, WeaponInnateSkill> ANNIHILATE = REGISTRY.register("annihilate", key ->
        SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
                .setAnimations(DawnDayAnimations.ANNIHILATE)
                .setCategory(SkillCategories.WEAPON_INNATE)
                .newProperty()
                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.2F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(7.5F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
                .build(key)
    );
    public static final DeferredHolder<Skill, WeaponInnateSkill> PIERCING_STRIKE = REGISTRY.register("piercing_strike", key ->
            SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
                    .setAnimations(DawnDayAnimations.PIERCING_STRIKE)
                    .setCategory(SkillCategories.WEAPON_INNATE)
                .newProperty()
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.8F))
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(60.0F))
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.5F))
            .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
            .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
                    .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> INCISURA_VITREA = REGISTRY.register("incisura_vitrea", key ->
            SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
                    .setAnimations(DawnDayAnimations.INCISURA_VITREA)
                    .setCategory(SkillCategories.WEAPON_INNATE)
                    .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(15.0F))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(4.2F))
                    .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.8F))
                    .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                            .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
                            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> POWERFUL_KICK = REGISTRY.register("powerful_kick", key ->
            SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
                    .setAnimations(DawnDayAnimations.POWERFUL_KICK)
                    .setCategory(SkillCategories.WEAPON_INNATE)
                    .newProperty()
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.25F))
                    .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.FINISHER))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.BYPASS_DODGE))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.EXECUTION))
                    .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(33.0F))
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(5.5F))
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                            .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> WILD_STRIKES_DUAL = REGISTRY.register("wild_strikes_dual", key ->
            SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
                    .setAnimations(DawnDayAnimations.WILD_STRIKES_DUAL)
                    .setCategory(SkillCategories.WEAPON_INNATE)
                    .newProperty()
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.5F))
                    .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(50F))
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                            .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
                    .build(key));


    public static final DeferredHolder<Skill, WeaponInnateSkill> GROUNDSLAM = REGISTRY.register("groundslam", key -> SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
            .setAnimations(DawnDayAnimations.GROUNDSLAM)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(10.0F))
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(40.0F))
            .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> SKULL_RUPTURE = REGISTRY.register("skull_rupture", key -> SkullRuptureSkill.createSimpleWeaponInnateBuilder(SkullRuptureSkill::new)
            .setAnimations(DawnDayAnimations.SKULL_RUPTURE)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
            .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
            .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(20.5F))
            .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
            .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> PIERCING_FANG = REGISTRY.register("piercing_fang", key -> SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
            .setAnimations(DawnDayAnimations.PIERCING_FANG)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
            .addProperty(AttackPhaseProperty.SWING_SOUND, DawnDaySounds.PIERCING_FANG.get())
            .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
            .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
            .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
            .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> IMPAILING_THRUST = REGISTRY.register("impailing_thrust", key -> SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
            .setAnimations(DawnDayAnimations.IMPAILING_THRUST)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(5.0F))
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(30.0F))
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
            .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> SPEARING_STRIKE = REGISTRY.register("spearing_strike", key -> SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
            .setAnimations(DawnDayAnimations.SPEARING_STRIKE)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
            .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.0f))
            .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
            .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
            .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> FURIOUS_CUT = REGISTRY.register("furious_cut", key -> FuriousCutSkill.createSimpleWeaponInnateBuilder(FuriousCutSkill::new)
            .setAnimations(DawnDayAnimations.FURIOUS_CUT)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(30.0F))
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> QUICK_RUSH = REGISTRY.register("quick_rush", key -> SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
            .setAnimations(DawnDayAnimations.QUICK_RUSH)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
            .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
            .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.8F))
            .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
            .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, WingStanceSkill> WINGSTANCE = REGISTRY.register("wingstance", key ->
            WingStanceSkill.createBuilder(WingStanceSkill::new)
                    .setCategory(SkillCategories.WEAPON_PASSIVE)
                    .build(key)
    );

    public static final DeferredHolder<Skill, WeaponInnateSkill> WHIRLWIND = REGISTRY.register("whirlwind", key -> SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
            .setAnimations(DawnDayAnimations.WHIRLWIND)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(5.0F))
            .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_HIT.get())
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(33.0F))
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
            .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> SPINNING_SHADOW = REGISTRY.register("spinning_shadow", key -> SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
            .setAnimations(DawnDayAnimations.SPINNING_SHADOW)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(5.0F))
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5.0F))
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.5F))
            .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> BRUTAL_DASH = REGISTRY.register("brutal_dash", key -> SkullRuptureSkill.createSimpleWeaponInnateBuilder(SkullRuptureSkill::new)
            .setAnimations(DawnDayAnimations.BRUTAL_DASH)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.05F))
            .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.FINISHER))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.BYPASS_DODGE))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.EXECUTION))
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100.0F))
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(5.5F))
            .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, WeaponInnateSkill> WILD_STRIKES = REGISTRY.register("wild_strikes", key -> SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder(SimpleWeaponInnateSkill::new)
            .setAnimations(DawnDayAnimations.WILD_STRIKES)
            .setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
            .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.5F))
            .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(25.0F))
            .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25F))
            .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
            .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
            .build(key));

    public static final DeferredHolder<Skill, RAHHHHH> RAAAHHH = REGISTRY.register("rahhh", key ->
            WeaponInnateSkill.createWeaponInnateBuilder(RAHHHHH::new)
                    .setCategory(SkillCategories.WEAPON_INNATE)
                    .build(key)
    );

    public static final DeferredHolder<Skill, Seppuku> SEPPUKU = REGISTRY.register("sepukku", key ->
            WeaponInnateSkill.createWeaponInnateBuilder(Seppuku::new)
                    .setCategory(SkillCategories.WEAPON_INNATE)
                    .build(key)
    );

    // ==========================================
    // Conditionally Loaded Skills
    // ==========================================
    public static final DeferredHolder<Skill, WeaponInnateSkill> EVIL_BEAAAAMMMM = ModList.get().isLoaded("wom") ?
            REGISTRY.register("evil_beam", key -> OdachiIaijutsu.createSimpleWeaponInnateBuilder(OdachiIaijutsu::new)
                    .setAnimations(AdditionalAnimations.EVIL_ODACHI_BEAAAMMMM)
                    .setCategory(SkillCategories.WEAPON_INNATE)
                    .newProperty()
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.7F))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.FINISHER))
                    .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25.0F))
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(15.5F))
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE))
                    .build(key)) : null;


}
