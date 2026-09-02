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
            .addComboAttacks(DawnDayAnimations.WARSICKLE_ATT1, DawnDayAnimations.WARSICKLE_ATT2, DawnDayAnimations.WARSICKLE_ATT3, DawnDayAnimations.IUDEX_HALBERD_DASH, DawnDayAnimations.MILADY_AIR_SLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.WARSICKLE_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_TWOHANDED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> EpicFightSkills.STEEL_WHIRLWIND.get())
    );

    public static final DeferredMoveset HALBERD_2H = REGISTRY.registerMoveset("halberd_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.HALBERD_AUTO1, DawnDayAnimations.HALBERD_AUTO2, DawnDayAnimations.HALBERD_AUTO3, DawnDayAnimations.HALBERD_AUTO4, DawnDayAnimations.IUDEX_HALBERD_DASH, DawnDayAnimations.IUDEX_HALBERD_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.HALBERD_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.IUDEX_HALBERD_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, DawnDayAnimations.IUDEX_HALBERD_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.SPEARING_STRIKE.get())
    );

    public static final DeferredMoveset BATTLE_STAFF_2H = REGISTRY.registerMoveset("battle_staff_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.BATTLESTAFF_AUTO1, DawnDayAnimations.BATTLESTAFF_AUTO2, DawnDayAnimations.BATTLESTAFF_AUTO3, DawnDayAnimations.BATTLESTAFF_AUTO4,DawnDayAnimations.POLEBLADE_DASH, DawnDayAnimations.POLEBLADE_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.BATTLESTAFF_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.SKULL_RUPTURE.get())
    );

    public static final DeferredMoveset BATTLE_STAFF_WOOD_2H = REGISTRY.registerMoveset("battle_staff_wood_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.BATTLESTAFF_AUTO1, DawnDayAnimations.BATTLESTAFF_AUTO2, DawnDayAnimations.BATTLESTAFF_AUTO3, DawnDayAnimations.BATTLESTAFF_AUTO4, DawnDayAnimations.POLEBLADE_DASH, DawnDayAnimations.POLEBLADE_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.BATTLESTAFF_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.GROUNDSLAM.get())
    );

    // ==================== AXES & SICKLES ====================
    public static final DeferredMoveset HERB_SICKLE_1H = REGISTRY.registerMoveset("herb_sickle_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.SICKLE_ONEHAND_AUTO1, DawnDayAnimations.SICKLE_ONEHAND_AUTO2, DawnDayAnimations.SICKLE_ONEHAND_AUTO3, DawnDayAnimations.SICKLE_ONEHAND_AUTO3, DawnDayAnimations.SICKLE_ONEHAND_DASH, DawnDayAnimations.SICKLE_ONEHAND_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.SICKLE_ONEHAND_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.QUICK_RUSH.get())
    );

    public static final DeferredMoveset HERB_SICKLE_2H = REGISTRY.registerMoveset("herb_sickle_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.SICKLE_DUAL_AUTO1, DawnDayAnimations.SICKLE_DUAL_AUTO2, DawnDayAnimations.SICKLE_DUAL_AUTO3, DawnDayAnimations.MILADY_DUAL_DASH, DawnDayAnimations.SICKLE_ONEHAND_AIRSLASH)
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
            .addComboAttacks(DawnDayAnimations.BACKHAND_BLADE_AUTO1, DawnDayAnimations.BACKHAND_BLADE_AUTO2, DawnDayAnimations.BACKHAND_BLADE_AUTO3, DawnDayAnimations.BACKHAND_AUTO4, DawnDayAnimations.BACKHAND_DASH, DawnDayAnimations.KNIFE_DUAL_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.BACKHAND_BLADE_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.SPINNING_SHADOW.get())
    );

    public static final DeferredMoveset KNIFE_1H = REGISTRY.registerMoveset("knife_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.KNIFE_ONEHAND_AUTO1, DawnDayAnimations.KNIFE_ONEHAND_AUTO2, DawnDayAnimations.KNIFE_ONEHAND_AUTO3, DawnDayAnimations.KNIFE_ONEHAND_AUTO4, DawnDayAnimations.SICKLE_ONEHAND_DASH, DawnDayAnimations.SICKLE_ONEHAND_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.KNIFE_ONEHAND_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.IMPAILING_THRUST.get())
    );

    public static final DeferredMoveset KNIFE_2H = REGISTRY.registerMoveset("knife_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.SICKLE_DUAL_AUTO1, DawnDayAnimations.SICKLE_DUAL_AUTO2, DawnDayAnimations.SICKLE_DUAL_AUTO3, DawnDayAnimations.SICKLE_DUAL_DASH, DawnDayAnimations.KNIFE_DUAL_AIRSLASH)
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
            .addComboAttacks(DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO1, DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO2, DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO3, DawnDayAnimations.RITUS_DAGGER_DUAL_DASH, DawnDayAnimations.RITUS_DAGGER_DUAL_AIRSLASH)
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
            .addComboAttacks(DawnDayAnimations.MESSER_ONEHANDED_AUTO1, DawnDayAnimations.MESSER_ONEHANDED_AUTO2, DawnDayAnimations.MESSER_ONEHANDED_AUTO3, DawnDayAnimations.SICKLE_ONEHAND_DASH, DawnDayAnimations.SABER_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.MESSER_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.WILD_STRIKES.get())
    );

    public static final DeferredMoveset FLORETT_1H = REGISTRY.registerMoveset("florett_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.FLORETT_AUTO1, DawnDayAnimations.FLORETT_AUTO2, DawnDayAnimations.FLORETT_AUTO3,DawnDayAnimations.FLORETT_AUTO4, DawnDayAnimations.VITR_DASH, DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO1)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.FLORETT_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, DawnDayAnimations.FLORETT_DUAL_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.PIERCING_STRIKE.get())
    );
    public static final DeferredMoveset FLORETT_2H = REGISTRY.registerMoveset("florett_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.FLORETT_DUAL_AUTO1, DawnDayAnimations.FLORETT_DUAL_AUTO2, DawnDayAnimations.FLORETT_DUAL_AUTO3, DawnDayAnimations.FLORETT_DUAL_DASH, DawnDayAnimations.FLORETT_DUAL_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.FLORETT_DUAL_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.FLORETT_DUAL_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, DawnDayAnimations.FLORETT_DUAL_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.WILD_STRIKES_DUAL.get())
    );
    public static final DeferredMoveset IRON_FIST_1H = REGISTRY.registerMoveset("iron_fist_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.IRON_FIST_AUTO1, DawnDayAnimations.IRON_FIST_AUTO2, DawnDayAnimations.IRON_FIST_AUTO3,DawnDayAnimations.IRON_FIST_AUTO4,DawnDayAnimations.IRON_FIST_AUTO5, DawnDayAnimations.IRON_FIST_DASH, DawnDayAnimations.IRON_FIST_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.IRON_FIST_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.IRON_FIST_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, DawnDayAnimations.IRON_FIST_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.POWERFUL_KICK.get()));

    public static final DeferredMoveset IRON_FIST_2H = REGISTRY.registerMoveset("iron_fist_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.IRON_FIST_AUTO1, DawnDayAnimations.IRON_FIST_AUTO2, DawnDayAnimations.IRON_FIST_AUTO3,DawnDayAnimations.IRON_FIST_AUTO4,DawnDayAnimations.IRON_FIST_AUTO5, DawnDayAnimations.IRON_FIST_DASH, DawnDayAnimations.IRON_FIST_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.IRON_FIST_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.IRON_FIST_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, DawnDayAnimations.IRON_FIST_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.POWERFUL_KICK.get()));

    public static final DeferredMoveset VITREUS_2H = REGISTRY.registerMoveset("vitreus_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.VITR_AUTO1, DawnDayAnimations.VITR_AUTO2, DawnDayAnimations.VITR_AUTO3,DawnDayAnimations.VITR_AUTO4, DawnDayAnimations.VITR_DASH, DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO1)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.VITR_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.INCISURA_VITREA.get()));

    public static final DeferredMoveset ECLIPSE_2H = REGISTRY.registerMoveset("eclipse_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.ECLIPSE_ATTACK1, DawnDayAnimations.ECLIPSE_ATTACK2, DawnDayAnimations.IUDEX_HALBERD_AUTO3,DawnDayAnimations.IUDEX_HALBERD_AUTO4,DawnDayAnimations.IUDEX_HALBERD_AUTO2, DawnDayAnimations.IUDEX_HALBERD_AUTO1)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.IUDEX_HALBERD_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.IUDEX_HALBERD_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, DawnDayAnimations.IUDEX_HALBERD_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.GENTLE_NUDGE.get()));

    public static final DeferredMoveset IUDEX_HALBERD_2H = REGISTRY.registerMoveset("iudex_halberd_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.IUDEX_HALBERD_AUTO1, DawnDayAnimations.IUDEX_HALBERD_AUTO2, DawnDayAnimations.IUDEX_HALBERD_AUTO3,DawnDayAnimations.IUDEX_HALBERD_AUTO4, DawnDayAnimations.IUDEX_HALBERD_DASH, DawnDayAnimations.IUDEX_HALBERD_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.IUDEX_HALBERD_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.IUDEX_HALBERD_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, DawnDayAnimations.IUDEX_HALBERD_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.CHAMPIONS_MIGHT.get()));

    public static final DeferredMoveset POLEBLADE_2H = REGISTRY.registerMoveset("poleblade_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.POLEBLADE_AUTO1, DawnDayAnimations.POLEBLADE_AUTO2, DawnDayAnimations.POLEBLADE_AUTO3,DawnDayAnimations.POLEBLADE_AUTO4, DawnDayAnimations.POLEBLADE_DASH, DawnDayAnimations.POLEBLADE_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.POLEBLADE_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.POLEBLADE_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
            .addLivingMotionModifier(LivingMotions.BLOCK, DawnDayAnimations.POLEBLADE_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.BLOOD_DANCE.get()));

    public static final DeferredMoveset HOOKCLAWS_2H = REGISTRY.registerMoveset("hookclaws_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.HOOKCLAWS_AUTO1, DawnDayAnimations.HOOKCLAWS_AUTO2, DawnDayAnimations.HOOKCLAWS_AUTO3,DawnDayAnimations.HOOKCLAWS_AUTO4, DawnDayAnimations.HOOKCLAWS_DASH, DawnDayAnimations.HOOKCLAWS_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.HOOKCLAWS_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.HOOKCLAWS_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, DawnDayAnimations.HOOKCLAWS_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.BEAST_EYE.get()));

    public static final DeferredMoveset HOOKCLAWS_1H = REGISTRY.registerMoveset("hookclaws_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.HOOKCLAWS_ONEHAND_AUTO1, DawnDayAnimations.HOOKCLAWS_ONEHAND_AUTO2,DawnDayAnimations.HOOKCLAWS_ONEHAND_DASH, DawnDayAnimations.HOOKCLAWS_ONEHAND_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.HOOKCLAWS_ONEHAND_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.HOOKCLAWS_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, DawnDayAnimations.HOOKCLAWS_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.QUICK_STEP.get()));

    public static final DeferredMoveset MESSER_2H = REGISTRY.registerMoveset("messer_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.MESSER_DUAL_AUTO1, DawnDayAnimations.MESSER_DUAL_AUTO2, DawnDayAnimations.MESSER_DUAL_AUTO3, DawnDayAnimations.KNIFE_DUAL_DASH, DawnDayAnimations.KNIFE_DUAL_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.MESSER_DUAL_IDLE)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.WHIRLWIND.get())
    );

    public static final DeferredMoveset MESSER_OCHS = REGISTRY.registerMoveset("messer_ochs", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.MILADY_ONE_HANDED_AUTO_1, DawnDayAnimations.MILADY_ONE_HANDED_AUTO_2, DawnDayAnimations.KNIFE_DUAL_DASH, DawnDayAnimations.KNIFE_DUAL_AIRSLASH)
    );



    // ==================== HEAVY BLUNT & GREATSWORDS ====================
    public static final DeferredMoveset BAT_1H = REGISTRY.registerMoveset("bat_1h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.BAT_AUTO1, DawnDayAnimations.BAT_AUTO2, DawnDayAnimations.BAT_AUTO3, DawnDayAnimations.BAT_AUTO4, DawnDayAnimations.BAT_DASH, DawnDayAnimations.BAT_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.BAT_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.BRUTAL_DASH.get())
    );
    public static final DeferredMoveset BAT_DUAL = REGISTRY.registerMoveset("bat_dual", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.NAILBAT_DUAL_AUTO1, DawnDayAnimations.NAILBAT_DUAL_AUTO2, DawnDayAnimations.NAILBAT_DUAL_AUTO3,DawnDayAnimations.NAILBAT_DUAL_DASH, DawnDayAnimations.NAILBAT_DUAL_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.NAILBAT_DUAL_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, DawnDayAnimations.NAILBAT_DUAL_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, DawnDayAnimations.NAILBAT_DUAL_RUN)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.HEAD_KNOCKER.get())
    );

    public static final DeferredMoveset BONECUTTING_SAW_2H = REGISTRY.registerMoveset("bonecutting_saw_2h", () -> Moveset.builder()
            .addComboAttacks(DawnDayAnimations.SAW_AUTO1, DawnDayAnimations.SAW_AUTO2, DawnDayAnimations.SAW_AUTO3, DawnDayAnimations.SAW_AUTO4, DawnDayAnimations.BAT_DASH, DawnDayAnimations.BAT_AIRSLASH)
            .addLivingMotionModifier(LivingMotions.IDLE, DawnDayAnimations.SAW_IDLE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
            .addInnateSkill((itemStack, playerPatch) -> DawnDaySkills.BRUTAL_DASH.get())
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
