package net.epicfight_dd.gameasset.animation.optional;



import net.minecraft.world.phys.Vec3;
import  net.shelmarow.combat_evolution.execution.ExecutionTypeManager.Type;

public class DawnDayExecution_TYPES {



    // FLIP X AND Z if attacker / victim goes in opposite directions and too forward than supposed to be
    // and possibly flip rot
    public static final Type KNEE_STOMP = new Type(
            DawnDayExecAnims.EXECUTION_KNEE_STOMP,
            DawnDayExecAnims.EXECUTED_FIST_FULL,
            new Vec3(0.201, 0.0, 1.90), -90.1f, 87
    );

    public static final Type NAOYA = new Type(
            DawnDayExecAnims.NAOYA_EXEC,
            DawnDayExecAnims.NAOYA_EXEC_HIT,
            new Vec3(0.201d,0.0d,0.0d), 180.0f, 90
    );

    public static final Type MILADY = new Type(
            DawnDayExecAnims.MILADY_EXECUTION,
            DawnDayExecAnims.MILADY_EXECUTION_HIT,
            new Vec3(1.35, 0.0F, 0.0F), 0.0F, 128
    );

    public static final Type BATTLESTAFF = new Type(
            DawnDayExecAnims.BATTLESTAFF_EXECUTE,
            DawnDayExecAnims.BATTLESTAFF_EXECUTED,
            new Vec3(1.35, 0.0F, 0.0F), 0.0F, 80
    );

    public static final Type EVIL_TACHI = new Type(
            AdditionalExecutions.EVIL_TACHI_EXECUTE,
            AdditionalExecutions.EVIL_TACHI_EXECUTED,
            new Vec3(1.35, 0.0F, 0.0F), 0.0F, 128
    );

    public static final Type MILADY_DUAL = new Type(
            DawnDayExecAnims.MILADY_EXECUTION_DUAL,
            DawnDayExecAnims.MILADY_EXECUTION_HIT_SPECIAL,
            new Vec3(1.35, 0.0F, 0.0F), 0.0F, 50
    );
    public static final Type IRON_FIST = new Type(
            DawnDayExecAnims.IRON_FIST_EXECUTE,
            DawnDayExecAnims.IRON_FIST_EXECUTED,
            new Vec3(0.4, 0.0F, 0.0F), 0.0F, 115
    );
    public static final Type GREATAXE_DUAL = new Type(
            DawnDayExecAnims.GREATAXE_DUAL_EXECUTE,
            DawnDayExecAnims.GREATAXE_DUAL_EXECUTED,
            new Vec3(1.65, 0.0F, 0.0F), 0.0F, 112
    );
    public static final Type DUAL_RITUAL_DAGGER = new Type(
            DawnDayExecAnims.RITUS_DAGGER_DUAL_EXECUTE,
            DawnDayExecAnims.RITUS_DAGGER_DUAL_EXECUTED,
            new Vec3(1.50, 0.0F, 0.0F), 0F, 112
    );

    public static final Type ONEHANDED_RITUAL_DAGGER = new Type(
            DawnDayExecAnims.RITUS_DAGGER_EXECUTE,
            DawnDayExecAnims.RITUS_DAGGER_EXECUTED,
            new Vec3(0.6, 0.0F, 0.0F), 0.0F, 112
    );
    public static final Type LIGHTGREATSWORD_SWORD = new Type(
            DawnDayExecAnims.LG_SW_EXECUTE,
            DawnDayExecAnims.LG_SW_EXECUTED,
            new Vec3(2.55, 0.0F, 0.0F), 0.0F, 112
    );





}
