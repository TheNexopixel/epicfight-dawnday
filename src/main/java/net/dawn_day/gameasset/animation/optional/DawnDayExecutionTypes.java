
package net.dawn_day.gameasset.animation.optional;

import net.dawn_day.EpicFightDawnDay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.shelmarow.combat_evolution.execution.ExecutionTypeManager;
import net.shelmarow.combat_evolution.execution.ExecutionTypeManager.Type;

public class DawnDayExecutionTypes {

    // FLIP X AND Z if attacker / victim goes in opposite directions
    // and too forward than supposed to be; possibly flip rotation too.
    public static final Type KNEE_STOMP = ExecutionTypeManager.creatExecutionType(
            ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID, "knee_stomp"),
            DawnDayExecAnims.EXECUTION_KNEE_STOMP,
            DawnDayExecAnims.EXECUTED_FIST_FULL,
            new Vec3(0.201, 0.0, 1.90),
            -90.1f,
            87
    );

    public static final Type NAOYA = ExecutionTypeManager.creatExecutionType(
            ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID, "naoya"),
            DawnDayExecAnims.NAOYA_EXEC,
            DawnDayExecAnims.NAOYA_EXEC_HIT,
            new Vec3(0.201, 0.0, 0.0),
            180.0f,
            90
    );

    public static final Type MILADY_DUAL = ExecutionTypeManager.creatExecutionType(
            ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID, "milady_dual"),
            DawnDayExecAnims.MILADY_EXECUTION_DUAL,
            DawnDayExecAnims.MILADY_EXECUTION_HIT_SPECIAL,
            new Vec3(1.35, 0.0, 0.0),
            0.0f,
            50
    );

    public static final Type BATTLESTAFF = ExecutionTypeManager.creatExecutionType(
            ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID, "battlestaff"),
            DawnDayExecAnims.BATTLESTAFF_EXECUTE,
            DawnDayExecAnims.BATTLESTAFF_EXECUTED,
            new Vec3(1.35, 0.0, 0.0),
            0.0f,
            128
    );

    public static final Type IRON_FIST = ExecutionTypeManager.creatExecutionType(
            ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID, "iron_fist"),
            DawnDayExecAnims.IRON_FIST_EXECUTE,
            DawnDayExecAnims.IRON_FIST_EXECUTED,
            new Vec3(0.4, 0.0, 0.0),
            0.0f,
            115
    );

    public static final Type DUAL_RITUAL_DAGGER = ExecutionTypeManager.creatExecutionType(
            ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID, "dual_ritual_dagger"),
            DawnDayExecAnims.RITUS_DAGGER_DUAL_EXECUTE,
            DawnDayExecAnims.RITUS_DAGGER_DUAL_EXECUTED,
            new Vec3(1.50, 0.0, 0.0),
            0.0f,
            112
    );

    public static final Type ONEHANDED_RITUAL_DAGGER = ExecutionTypeManager.creatExecutionType(
            ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID, "onehanded_ritual_dagger"),
            DawnDayExecAnims.RITUS_DAGGER_EXECUTE,
            DawnDayExecAnims.RITUS_DAGGER_EXECUTED,
            new Vec3(0.6, 0.0, 0.0),
            0.0f,
            112
    );

    public static final Type LIGHTGREATSWORD_SWORD = ExecutionTypeManager.creatExecutionType(
            ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID, "lightgreatsword_sword"),
            DawnDayExecAnims.LG_SW_EXECUTE,
            DawnDayExecAnims.LG_SW_EXECUTED,
            new Vec3(2.55, 0.0, 0.0),
            0.0f,
            112
    );

    public static final Type POLEBLADE = ExecutionTypeManager.creatExecutionType(
            ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID, "poleblade"),
            DawnDayExecAnims.POLEBLADE_EXECUTE,
            DawnDayExecAnims.POLEBLADE_EXECUTED,
            new Vec3(2.55, 0.0, 0.0),
            0.0f,
            130
    );


}
