package net.epicfight_dd;
import net.minecraftforge.common.ForgeConfigSpec;
public class DawnDayServerConfig {

    public static final ForgeConfigSpec.Builder BUILDER =
            new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_SPECTATOR_RESPAWN =
            BUILDER
                    .comment("Set Gamemode to Spectator when a player respawns (Default: false)")
                    .define("death_spectator", false);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
