package net.epicfight_dd.skill;

import net.epicfight_dd.DawnDayConfig;
import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.AdditionalAnimations;
import net.epicfight_dd.gameasset.animation.DawnDayAnimations;

import java.util.Set;

import net.epicfight_dd.gameasset.dawnDaySounds;
import net.epicfight_dd.skill.stances.WingStanceSkill;
import net.epicfight_dd.skill.weapon_innate.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.StunType;

@EventBusSubscriber(
        modid = Epicfight_dd.MODID,
        bus = Bus.MOD
)
public class DawnDaySkills {

    public static Skill GENTLE_NUDGE;
    public static Skill PIERCING_FANG;
    public static Skill SPEARING_STRIKE;
    public static Skill FURIOUS_CUT;
    public static Skill WINGSTANCE;
    public static Skill SKULL_RUPTURE;
    public static Skill QUICK_RUSH;
    public static Skill WHIRLWIND;
    public static Skill IMPAILING_THRUST;
    public static Skill SPINNING_SHADOW;
    public static Skill EVIL_BEAAAAMMMM;
    public static Skill BRUTAL_DASH;
    public static Skill GROUNDSLAM;
    public static Skill INCISURA_VITREA;
    public static Skill WILD_STRIKES;
    public static Skill WILD_STRIKES_DUAL;
    public static Skill RAAAHHH;
    public static Skill ANNIHILATE;
    public static Skill SEPUKKU;
    public static Skill PIERCING_STRIKE;
    public static Skill POWERFUL_KICK;


    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent build){
        SkillBuildEvent.ModRegistryWorker modRegistry = build.createRegistryWorker(Epicfight_dd.MODID);


            WeaponInnateSkill gentlenudge = modRegistry.build("gentle_nudge", SimpleWeaponInnateSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                    .setAnimations(DawnDayAnimations.MILADY_KNUCKLE_INNATE)
                    .setCategory(SkillCategories.WEAPON_INNATE));
            gentlenudge.newProperty()
                    .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2F))
                    .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                    .addProperty(AttackPhaseProperty.HIT_SOUND, dawnDaySounds.poise_break.get())
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
                    .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                            .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
            GENTLE_NUDGE = gentlenudge;


        WeaponInnateSkill incivi = modRegistry.build("incisura_vitrea",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.INCISURA_VITREA)
                .setCategory(SkillCategories.WEAPON_INNATE));
        incivi.newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(15.0F))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(4.2F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.8F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
        INCISURA_VITREA = incivi;

        WeaponInnateSkill piercingstrike = modRegistry.build("piercing_strike",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.PIERCING_STRIKE)
                .setCategory(SkillCategories.WEAPON_INNATE));
        piercingstrike.newProperty()
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.8F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(60.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.5F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
        PIERCING_STRIKE = piercingstrike;


        if (ModList.get().isLoaded("wom")) {
            WeaponInnateSkill annihilate = modRegistry.build("annihilate", SimpleWeaponInnateSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                    .setAnimations(AdditionalAnimations.ANNIHILATE)
                    .setCategory(SkillCategories.WEAPON_INNATE));
            annihilate.newProperty()
                    .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.2F))
                    .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(7.5F))
                    .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                            .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
            ANNIHILATE = annihilate;
        }

        WeaponInnateSkill groundslam = modRegistry.build("groundslam",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.GROUNDSLAM)
                .setCategory(SkillCategories.WEAPON_INNATE));
        groundslam.newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(10.0F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(40.0F))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
        GROUNDSLAM = groundslam;


        WeaponInnateSkill skullrupture = modRegistry.build("skull_rupture",
                SkullRuptureSkill::new, SkullRuptureSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.SKULL_RUPTURE)
                .setCategory(SkillCategories.WEAPON_INNATE));
        skullrupture.newProperty()
                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(20.5F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
        SKULL_RUPTURE = skullrupture;

        WeaponInnateSkill piercingfang = modRegistry.build("piercing_fang",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.PIERCING_FANG)
                .setCategory(SkillCategories.WEAPON_INNATE));
        piercingfang.newProperty()
                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                .addProperty(AttackPhaseProperty.SWING_SOUND, dawnDaySounds.piercing_fang.get())
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
        PIERCING_FANG = piercingfang;

        WeaponInnateSkill impailingthrust = modRegistry.build("impailing_thrust",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.IMPAILING_THRUST)
                .setCategory(SkillCategories.WEAPON_INNATE));
        impailingthrust.newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(5.0F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(30.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
        IMPAILING_THRUST = impailingthrust;

        WeaponInnateSkill spearingstrike = modRegistry.build("spearing_strike",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.SPEARING_STRIKE)
                .setCategory(SkillCategories.WEAPON_INNATE));
        spearingstrike.newProperty()
                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER,ValueModifier.multiplier(3.0f))
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
        SPEARING_STRIKE = spearingstrike;

        WeaponInnateSkill furiousCut = modRegistry.build("furious_cut", FuriousCutSkill::new, FuriousCutSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.FURIOUS_CUT)
                .setCategory(SkillCategories.WEAPON_INNATE));
        furiousCut.newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(30.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F));
        FURIOUS_CUT = furiousCut;

        WeaponInnateSkill quickrush = modRegistry.build("quick_rush",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.QUICK_RUSH)
                .setCategory(SkillCategories.WEAPON_INNATE));
        quickrush.newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.8F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
        QUICK_RUSH = quickrush;

        WINGSTANCE = modRegistry.build("wingstance",
                WingStanceSkill::new, WingStanceSkill.createBuilder()
                        .setCategory(SkillCategories.WEAPON_PASSIVE)
                );

        WeaponInnateSkill whirlwind = modRegistry.build("whirlwind",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.WHIRLWIND)
                .setCategory(SkillCategories.WEAPON_INNATE));
        whirlwind.newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(5.0F))
                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_HIT.get())
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(33.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.4F))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));

        WHIRLWIND = whirlwind;

        WeaponInnateSkill spinshadow = modRegistry.build("spinning_shadow",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.SPINNING_SHADOW)
                .setCategory(SkillCategories.WEAPON_INNATE));
        spinshadow.newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(5.0F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(5.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.5F))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));

        SPINNING_SHADOW = spinshadow;

        if (ModList.get().isLoaded("wom")) {
            WeaponInnateSkill evilbeam = modRegistry.build("evil_beam", EvilOdachi_Battojutso::new, EvilOdachi_Battojutso.createSimpleWeaponInnateBuilder()
                    .setAnimations(AdditionalAnimations.EVIL_ODACHI_BEAAAMMMM)
                    .setCategory(SkillCategories.WEAPON_INNATE));
            evilbeam.newProperty()
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.7F))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.FINISHER))
                    .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25.0F))
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(15.5F))
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                            .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));

            EVIL_BEAAAAMMMM = evilbeam;
        }

        WeaponInnateSkill brutaldash = modRegistry.build("brutal_dash",SkullRuptureSkill::new,SkullRuptureSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.BRUTAL_DASH)
                .setCategory(SkillCategories.WEAPON_INNATE));
        brutaldash.newProperty()
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.05F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE))
                .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.FINISHER))
                .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.BYPASS_DODGE))
                .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.EXECUTION))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(5.5F))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));

        BRUTAL_DASH = brutaldash;

        WeaponInnateSkill powerfulkick = modRegistry.build("powerful_kick",SkullRuptureSkill::new,SkullRuptureSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.POWERFUL_KICK)
                .setCategory(SkillCategories.WEAPON_INNATE));
        powerfulkick.newProperty()
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.25F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.GUARD_PUNCTURE))
                .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.FINISHER))
                .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.BYPASS_DODGE))
                .addProperty(AttackPhaseProperty.SOURCE_TAG,Set.of(EpicFightDamageTypeTags.EXECUTION))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(33.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(5.5F))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));

        POWERFUL_KICK = powerfulkick;

        WeaponInnateSkill wildstrikes = modRegistry.build("wild_strikes",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.WILD_STRIKES)
                .setCategory(SkillCategories.WEAPON_INNATE));
        wildstrikes.newProperty()
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.5F))
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER,ValueModifier.adder(25.0F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(25F))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));

        WILD_STRIKES = wildstrikes;

        WeaponInnateSkill wildstrikes_dual = modRegistry.build("wild_strikes_dual",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(DawnDayAnimations.WILD_STRIKES_DUAL)
                .setCategory(SkillCategories.WEAPON_INNATE));
        wildstrikes_dual.newProperty()
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.5F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(50F))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));

        WILD_STRIKES_DUAL = wildstrikes_dual;

        /// RAAAHHHHHHHHHH BANG BANG BANG
        RAAAHHH = modRegistry.build("rahhh", RAHHHHH::new,
                RAHHHHH.createWeaponInnateBuilder()
                        .setCategory(SkillCategories.WEAPON_INNATE)
        );

        SEPUKKU = modRegistry.build("sepukku", Sepukku::new,
                Sepukku.createWeaponInnateBuilder()
                        .setCategory(SkillCategories.WEAPON_INNATE)
        );

    }


}
