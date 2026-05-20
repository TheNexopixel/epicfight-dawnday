package net.epicfight_dd;
import net.minecraftforge.common.ForgeConfigSpec;
public class DawnDayConfig {

    public static final ForgeConfigSpec.Builder BUILDER =
            new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_DEATH_SOUND =
            BUILDER
                    .comment("Enable Death Sound Effect")
                    .define("deathsound", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
