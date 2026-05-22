package net.dawn_day.registry.entries;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.gameasset.animation.DawnDayAnimations;
import net.dawn_day.gameasset.animation.QoLMiscAnimations;
import net.dawn_day.gameasset.animation.WingStanceAnims;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.ex_cap.data.Moveset;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.registry.deferred.MovesetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredMoveset;
import yesman.epicfight.registry.entries.EpicFightSkills;

public final class DawnDayMovesets
{
    private DawnDayMovesets() {}
    public static final MovesetRegister REGISTRY = MovesetRegister.create(EpicFightDawnDay.MOD_ID);

    // ==================== POLEARMS & STAVES ====================
    public static final DeferredMoveset WAR_SICKLE_2H = REGISTRY.registerMoveset("war_sickle_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.WAR_SICKLE_AUTO1, DawnDayAnimations.WAR_SICKLE_AUTO2, DawnDayAnimations.WAR_SICKLE_AUTO3, DawnDayAnimations.WAR_SICKLE_AUTO4, DawnDayAnimations.WAR_SICKLE_AUTO5, DawnDayAnimations.WAR_SICKLE_AUTO6, DawnDayAnimations.SICKLE_DASH, DawnDayAnimations.MILADY_AIR_SLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.WAR_SICKLE_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_TWOHANDED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> EpicFightSkills.STEEL_WHIRLWIND.get())
    );

    public static final DeferredMoveset HALBERD_2H = REGISTRY.registerMoveset("halberd_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.HALBERD_AUTO1, DawnDayAnimations.HALBERD_AUTO2, DawnDayAnimations.HALBERD_AUTO3, DawnDayAnimations.HALBERD_AUTO4, DawnDayAnimations.HALBERD_DASH, DawnDayAnimations.MILADY_AIR_SLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.HALBEARD_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.SPEARING_STRIKE.get())
    );

    public static final DeferredMoveset BATTLE_STAFF_2H = REGISTRY.registerMoveset("battle_staff_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.BATTLESTAFF_AUTO1, DawnDayAnimations.BATTLESTAFF_AUTO2, DawnDayAnimations.BATTLESTAFF_AUTO3, DawnDayAnimations.BATTLESTAFF_AUTO4, DawnDayAnimations.BATTLESTAFF_AUTO5, DawnDayAnimations.BATTLESTAFF_DASH, DawnDayAnimations.BATTLESTAFF_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.BATTLESTAFF_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.SKULL_RUPTURE.get())
    );

    public static final DeferredMoveset BATTLE_STAFF_WOOD_2H = REGISTRY.registerMoveset("battle_staff_wood_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.BATTLESTAFF_AUTO1, DawnDayAnimations.BATTLESTAFF_AUTO2, DawnDayAnimations.BATTLESTAFF_AUTO3, DawnDayAnimations.BATTLESTAFF_AUTO4, DawnDayAnimations.BATTLESTAFF_AUTO5, DawnDayAnimations.BATTLESTAFF_DASH, DawnDayAnimations.BATTLESTAFF_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.BATTLESTAFF_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.GROUNDSLAM.get())
    );

    // ==================== AXES & SICKLES ====================
    public static final DeferredMoveset HERB_SICKLE_1H = REGISTRY.registerMoveset("herb_sickle_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.SICKLE_AUTO1, DawnDayAnimations.SICKLE_AUTO2, DawnDayAnimations.SICKLE_AUTO3, DawnDayAnimations.SICKLE_AUTO4, DawnDayAnimations.SICKLE_DASH, DawnDayAnimations.SICKLE_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.SICKLE_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.QUICK_RUSH.get())
    );

    public static final DeferredMoveset HERB_SICKLE_2H = REGISTRY.registerMoveset("herb_sickle_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.SICKLE_DUAL_AUTO1, DawnDayAnimations.SICKLE_DUAL_AUTO2, DawnDayAnimations.SICKLE_DUAL_AUTO3, DawnDayAnimations.MILADY_DUAL_DASH, DawnDayAnimations.SICKLE_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.SICKLE_DUAL_IDLE)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.WHIRLWIND.get())
    );

    public static final DeferredMoveset STEEL_AXE_2H = REGISTRY.registerMoveset("steel_axe_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.STEELAXE_AUTO1, DawnDayAnimations.STEELAXE_AUTO2, DawnDayAnimations.STEELAXE_AUTO3, DawnDayAnimations.STEELAXE_AUTO4, DawnDayAnimations.HALBERD_DASH, DawnDayAnimations.BAT_DASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.STEELAXE_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.SPEARING_STRIKE.get())
    );

    // ==================== DAGGERS & KNIVES ====================
    public static final DeferredMoveset BACKHAND_BLADE_1H = REGISTRY.registerMoveset("backhand_blade_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.BACKHAND_AUTO1, DawnDayAnimations.BACKHAND_AUTO2, DawnDayAnimations.BACKHAND_AUTO3, DawnDayAnimations.BACKHAND_AUTO4, DawnDayAnimations.BACKHAND_DASH, DawnDayAnimations.KNIFE_DUAL_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.BACKHAND_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.SPINNING_SHADOW.get())
    );

    public static final DeferredMoveset KNIFE_1H = REGISTRY.registerMoveset("knife_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.KNIFE_ONEHANDED_AUTO1, DawnDayAnimations.KNIFE_ONEHANDED_AUTO2, DawnDayAnimations.KNIFE_ONEHANDED_AUTO3, DawnDayAnimations.KNIFE_ONEHANDED_AUTO4, DawnDayAnimations.SICKLE_DASH, DawnDayAnimations.SICKLE_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.KNIFE_ONEHANDED_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.IMPAILING_THRUST.get())
    );

    public static final DeferredMoveset KNIFE_2H = REGISTRY.registerMoveset("knife_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.KNIFE_DUAL_AUTO1, DawnDayAnimations.KNIFE_DUAL_AUTO2, DawnDayAnimations.SICKLE_DUAL_AUTO3, DawnDayAnimations.KNIFE_DUAL_DASH, DawnDayAnimations.KNIFE_DUAL_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.KNIFE_DUAL_IDLE)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.WHIRLWIND.get())
    );

    public static final DeferredMoveset BLOOD_RITUS_1H = REGISTRY.registerMoveset("blood_ritus_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.BLOOD_RITUS_DAGGER_AUTO1, DawnDayAnimations.BLOOD_RITUS_DAGGER_AUTO2, DawnDayAnimations.BLOOD_RITUS_DAGGER_AUTO3, DawnDayAnimations.BLOOD_RITUS_DAGGER_AUTO4, DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO2, DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO1)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.BLOOD_RITUS_DAGGER_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.SEPPUKU.get())
    );

    public static final DeferredMoveset NIGHT_RITUS_1H = REGISTRY.registerMoveset("night_ritus_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO1, DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO2, DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO3, DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO4, DawnDayAnimations.NIGHT_RITUS_DAGGER_DASH, DawnDayAnimations.BLOOD_RITUS_DAGGER_AUTO3)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.NIGHT_RITUS_DAGGER_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, DawnDayAnimations.RITUS_DAGGER_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.SEPPUKU.get())
    );

    public static final DeferredMoveset RITUS_2H = REGISTRY.registerMoveset("ritus_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO1, DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO2, DawnDayAnimations.MESSER_DUAL_AUTO3, DawnDayAnimations.RITUS_DAGGER_DUAL_DASH, DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO3)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.RITUS_DAGGER_DUAL_IDLE)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.WHIRLWIND.get())
    );

    // ==================== SWORDS & TACHIS ====================
    public static final DeferredMoveset SABER_2H = REGISTRY.registerMoveset("saber_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.SABER_AUTO1, DawnDayAnimations.SABER_AUTO2, DawnDayAnimations.SABER_AUTO3, DawnDayAnimations.SABER_AUTO4, DawnDayAnimations.SABER_AUTO5, DawnDayAnimations.SABER_AUTO6, DawnDayAnimations.SABER_DASH, DawnDayAnimations.SABER_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.SABER_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.WILD_STRIKES.get())
    );

    public static final DeferredMoveset MESSER_1H = REGISTRY.registerMoveset("messer_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.MESSER_ONEHANDED_AUTO1, DawnDayAnimations.MESSER_ONEHANDED_AUTO2, DawnDayAnimations.MESSER_ONEHANDED_AUTO3, DawnDayAnimations.SICKLE_DASH, DawnDayAnimations.SABER_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.MESSER_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.WILD_STRIKES.get())
    );

    public static final DeferredMoveset MESSER_2H = REGISTRY.registerMoveset("messer_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.MESSER_DUAL_AUTO1, DawnDayAnimations.MESSER_DUAL_AUTO2, DawnDayAnimations.MESSER_DUAL_AUTO3, DawnDayAnimations.KNIFE_DUAL_DASH, DawnDayAnimations.KNIFE_DUAL_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.MESSER_DUAL_IDLE)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.WHIRLWIND.get())
    );

    public static final DeferredMoveset MESSER_OCHS = REGISTRY.registerMoveset("messer_ochs", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.MILADY_ONE_HANDED_AUTO_1, DawnDayAnimations.MILADY_ONE_HANDED_AUTO_2, DawnDayAnimations.KNIFE_DUAL_DASH, DawnDayAnimations.KNIFE_DUAL_AIRSLASH)
    );

//    public static final DeferredMoveset EVIL_TACHI_2H = REGISTRY.registerMoveset("evil_tachi_2h", () -> Moveset.builder()
//            .addComboAttacks(AdditionalAnimations.EVIL_ODACHI_AUTO1, AdditionalAnimations.EVIL_ODACHI_AUTO2, AdditionalAnimations.EVIL_ODACHI_AUTO3, AdditionalAnimations.EVIL_ODACHI_AUTO4, AdditionalAnimations.EVIL_ODACHI_DASH, AdditionalAnimations.EVIL_ODACHI_AIRSLASH)
//            .addLivingMotionModifier(LivingMotions.IDLE, AdditionalAnimations.EVIL_ODACHI_IDLE)
//            .addLivingMotionModifier(LivingMotions.WALK, AdditionalAnimations.EVIL_ODACHI_WALK)
//            .addLivingMotionModifier(LivingMotions.KNEEL, AdditionalAnimations.EVIL_ODACHI_KNEEL)
//            .addLivingMotionModifier(LivingMotions.SNEAK, AdditionalAnimations.EVIL_ODACHI_SNEAK)
//            .addLivingMotionModifier(LivingMotions.RUN, AdditionalAnimations.EVIL_ODACHI_RUN)
//            .addLivingMotionModifier(LivingMotions.DEATH, QoLMiscAnimations.WITHERING_DEMISE)
//            .addLivingMotionModifier(LivingMotions.JUMP, AdditionalAnimations.EVIL_ODACHI_JUMP)
//            .addLivingMotionModifier(LivingMotions.BLOCK, AdditionalAnimations.EVIL_ODACHI_GUARD)
//            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.EVIL_BEAAAAMMMM.get())
//            .setPassiveSkill(WOMSkills.EVIL_TACHI_PASSIVE)
//    );

    public static final DeferredMoveset HOLLOW_LONGSWORD_1H = REGISTRY.registerMoveset("hollow_longsword_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.HOLLOW_ONEHANDED_AUTO1, DawnDayAnimations.HOLLOW_ONEHANDED_AUTO2, DawnDayAnimations.HOLLOW_ONEHANDED_AUTO3, DawnDayAnimations.SICKLE_DASH, DawnDayAnimations.BAT_DASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.HOLLOW_ONEHANDED_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> EpicFightSkills.SHARP_STAB.get())
    );

    public static final DeferredMoveset HOLLOW_LONGSWORD_2H = REGISTRY.registerMoveset("hollow_longsword_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.HOLLOW_OCHS_AUTO1, DawnDayAnimations.HOLLOW_OCHS_AUTO2, DawnDayAnimations.HOLLOW_OCHS_AUTO3, DawnDayAnimations.HOLLOW_OCHS_AUTO4, DawnDayAnimations.SABER_AUTO2, DawnDayAnimations.SABER_AUTO4)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.HOLLOW_OCHS_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.HOLLOW_OCHS_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, DawnDayAnimations.HOLLOW_OCHS_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK_SHIELD, Animations.BIPED_BLOCK)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.RAAAHHH.get())
    );

    // ==================== HEAVY BLUNT & GREATSWORDS ====================
    public static final DeferredMoveset BAT_2H = REGISTRY.registerMoveset("bat_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.BAT_AUTO1, DawnDayAnimations.BAT_AUTO2, DawnDayAnimations.BAT_AUTO3, DawnDayAnimations.BAT_AUTO4, DawnDayAnimations.BAT_DASH, DawnDayAnimations.BAT_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.BAT_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.BRUTAL_DASH.get())
    );

    public static final DeferredMoveset BONECUTTING_SAW_2H = REGISTRY.registerMoveset("bonecutting_saw_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.SAW_AUTO1, DawnDayAnimations.SAW_AUTO2, DawnDayAnimations.SAW_AUTO3, DawnDayAnimations.SAW_AUTO4, DawnDayAnimations.BAT_DASH, DawnDayAnimations.BAT_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.SAW_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.BRUTAL_DASH.get())
    );

    public static final DeferredMoveset GREATAXE_1H = REGISTRY.registerMoveset("greataxe_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.SAW_AUTO1, DawnDayAnimations.SAW_AUTO2, DawnDayAnimations.SAW_AUTO3, DawnDayAnimations.SAW_AUTO4, DawnDayAnimations.BAT_DASH, DawnDayAnimations.BAT_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.SAW_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.SPEARING_STRIKE.get())
    );

    public static final DeferredMoveset GREATAXE_2H = REGISTRY.registerMoveset("greataxe_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.GREATAXE_DUAL_AUTO1, DawnDayAnimations.GREATAXE_DUAL_AUTO2, DawnDayAnimations.GREATAXE_DUAL_AUTO3, DawnDayAnimations.GREATAXE_DUAL_AUTO4, DawnDayAnimations.GREATAXE_DUAL_DASH, DawnDayAnimations.GREATAXE_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.GREATAXE_DUAL_IDLE)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.ANNIHILATE.get())
    );

    public static class Milady
    {
        // ================= MILADY MOVESETS =================
        public static final DeferredMoveset MILADY_1H = REGISTRY.registerMoveset("milady_1h",
                () -> Moveset.builder()
                        .addComboAttacks(
                                DawnDayAnimations.MILADY_ONE_HANDED_AUTO_1, DawnDayAnimations.MILADY_ONE_HANDED_AUTO_2,
                                DawnDayAnimations.MILADY_ONE_HANDED_AUTO_3, DawnDayAnimations.MILADY_ONE_HANDED_AUTO_4,
                                DawnDayAnimations.MILADY_ONE_HANDED_AUTO_5, DawnDayAnimations.MILADY_DASH_NEW,
                                DawnDayAnimations.MILADY_AIR_SLASH)
                        .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.MILADY_IS_IDLE)
                        .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                        .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
                        .addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
                        .addLivingMotionModifier(LivingMotions.DEATH, QoLMiscAnimations.EXPRESSIVE_DEATH)
                        .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.PIERCING_FANG.get())
                        .setPassiveSkill(DawnDaySkills.WINGSTANCE)
        );

        public static final DeferredMoveset MILADY_2H = REGISTRY.registerMoveset("milady_2h",
                () -> Moveset.builder()
                        .addComboAttacks(
                                DawnDayAnimations.MILADY_SPECIAL_AUTO1, DawnDayAnimations.MILADY_SPECIAL_AUTO2,
                                DawnDayAnimations.MILADY_SPECIAL_AUTO3, DawnDayAnimations.MILADY_SPECIAL_AUTO4,
                                DawnDayAnimations.MILADY_SPECIAL_AUTO5, DawnDayAnimations.MILADY_SPECIAL_DASH,
                                DawnDayAnimations.MILADY_DUAL_AIRSLASH)
                        .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.MILADY_SPECIAL_IDLE)
                        .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_SPECIAL_WALK)
                        .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
                        .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
                        .addLivingMotionModifier(LivingMotions.DEATH, QoLMiscAnimations.EXPRESSIVE_DEATH)
                        .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.GENTLE_NUDGE.get())
                        .setPassiveSkill(DawnDaySkills.WINGSTANCE)
        );

        public static final DeferredMoveset MILADY_OCHS = REGISTRY.registerMoveset("milady_ochs",
                () -> Moveset.builder()
                        .addComboAttacks(
                                WingStanceAnims.WINGSTANCE_ATTACK_1, WingStanceAnims.WINGSTANCE_ATTACK_2,
                                WingStanceAnims.WINGSTANCE_ATTACK_3, WingStanceAnims.WINGSTANCE_ATTACK_5,
                                WingStanceAnims.WINGSTANCE_DASH, WingStanceAnims.WINGSTANCE_AIRSLASH)
                        .addLivingMotionModifier(LivingMotions.IDLE, WingStanceAnims.WING_STANCE_IDLE)
                        .addLivingMotionModifier(LivingMotions.WALK, WingStanceAnims.WINGSTANCE_WALK)
                        .addLivingMotionModifier(LivingMotions.RUN, WingStanceAnims.WINGSTANCE_RUN)
                        .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
                        .addLivingMotionModifier(LivingMotions.DEATH, QoLMiscAnimations.EXPRESSIVE_DEATH)
                        .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.FURIOUS_CUT.get())
                        .setPassiveSkill(DawnDaySkills.WINGSTANCE)
        );

        // ================= LIGHT GREATSWORD MOVESETS =================
        public static final DeferredMoveset LIGHT_GREATSWORD_1H = REGISTRY.registerMoveset("light_greatsword_1h",
                () -> Moveset.builder()
                        .addComboAttacks(
                                DawnDayAnimations.MILADY_TWOHANDED_AUTO1, DawnDayAnimations.MILADY_TWOHANDED_AUTO2,
                                DawnDayAnimations.MILADY_TWOHANDED_AUTO3, DawnDayAnimations.MILADY_TWOHANDED_AUTO4,
                                DawnDayAnimations.MILADY_DASH_NEW, DawnDayAnimations.MILADY_AIR_SLASH)
                        .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.MILADY_TWOHANDED_IS_IDLE)
                        .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_TWOHANDED_WALK)
                        .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
                        .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.QUICK_RUSH.get())
        );

        public static final DeferredMoveset LIGHT_GREATSWORD_2H = REGISTRY.registerMoveset("light_greatsword_2h",
                () -> Moveset.builder()
                        .addComboAttacks(
                                DawnDayAnimations.MILADY_DUAL_AUTO1, DawnDayAnimations.MILADY_DUAL_AUTO2,
                                DawnDayAnimations.MILADY_DUAL_AUTO3, DawnDayAnimations.MILADY_DUAL_AUTO4,
                                DawnDayAnimations.MILADY_DUAL_AUTO5, DawnDayAnimations.MILADY_DUAL_DASH,
                                DawnDayAnimations.MILADY_DUAL_AIRSLASH)
                        .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.MILADY_DUAL_IS_IDLE)
                        .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                        .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
                        .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
                        .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.GENTLE_NUDGE.get())
        );
    }

}
