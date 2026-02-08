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




}
