package F.epicfight_dd.gameasset.animation.optional;


import net.minecraft.world.phys.Vec3;
import  net.shelmarow.combat_evolution.execution.ExecutionTypeManager.Type;

public class DawnDayExecution_TYPES {





    // FLIP X AND Z if attacker / victim goes in opposite directions than supposed to be
    public static final Type KNEE_STOMP = new Type(
            DawnDayExecAnims.EXECUTION_KNEE_STOMP,
            DawnDayExecAnims.EXECUTED_FIST_FULL,
            new Vec3(0.201, 0.0, 1.90), -90.1f, 80
    );

    public static final Type MILADY = new Type(
            DawnDayExecAnims.MILADY_EXECUTION,
            DawnDayExecAnims.MILADY_EXECUTION_HIT,
            new Vec3(1.35, 0.0F, 0.0F), -30.0F, 100
    );




}
