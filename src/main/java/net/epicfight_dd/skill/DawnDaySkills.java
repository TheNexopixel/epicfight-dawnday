package net.epicfight_dd.skill;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.MiladyMoveset;

import java.util.Set;

import net.epicfight_dd.gameasset.dawnDaySounds;
import net.epicfight_dd.skill.stances.WingStanceSkill;
import net.epicfight_dd.skill.weapon_innate.SkullRuptureSkill;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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


    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent build){
        SkillBuildEvent.ModRegistryWorker modRegistry = build.createRegistryWorker(Epicfight_dd.MODID);

       WeaponInnateSkill gentlenudge = modRegistry.build("gentle_nudge",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(MiladyMoveset.MILADY_KNUCKLE_INNATE)
                .setCategory(SkillCategories.WEAPON_INNATE));
               gentlenudge.newProperty()
                       .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                       .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                       .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.2F))
                       .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                       .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
                       .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                       .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                       .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
               GENTLE_NUDGE = gentlenudge;


        WeaponInnateSkill skullrupture = modRegistry.build("skull_rupture",
                SkullRuptureSkill::new, SkullRuptureSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(MiladyMoveset.SKULL_RUPTURE)
                .setCategory(SkillCategories.WEAPON_INNATE));
        skullrupture.newProperty()
                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.6F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
        SKULL_RUPTURE = skullrupture;

        WeaponInnateSkill piercingfang = modRegistry.build("piercing_fang",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(MiladyMoveset.PIERCING_FANG)
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

        WeaponInnateSkill spearingstrike = modRegistry.build("spearing_strike",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(MiladyMoveset.SPEARING_STRIKE)
                .setCategory(SkillCategories.WEAPON_INNATE));
        spearingstrike.newProperty()
                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                        .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
        SPEARING_STRIKE = spearingstrike;

        WeaponInnateSkill furiousCut = modRegistry.build("furious_cut",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(MiladyMoveset.FURIOUS_CUT)
                .setCategory(SkillCategories.WEAPON_INNATE));
        furiousCut.newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG);
        FURIOUS_CUT = furiousCut;

        WeaponInnateSkill quickrush = modRegistry.build("quick_rush",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(MiladyMoveset.QUICK_RUSH)
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

    }


}
