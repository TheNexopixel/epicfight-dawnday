package net.epicfight_dd;
import net.minecraftforge.common.ForgeConfigSpec;
public class DawnDayServerConfig {

    public static final ForgeConfigSpec.Builder BUILDER =
            new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_SPECTATOR_RESPAWN =
            BUILDER
                    .comment("Set Gamemode to Spectator when a player respawns (Default: false)")
                    .define("death_spectator", false);

    public static final ForgeConfigSpec.BooleanValue REMOVE_WOM_TIME_TRAVEL_REQUIREMENT = BUILDER
            .comment("Removes the Shadow Step requirement from Weapons of Miracles Time Travel skill. (Default: false)")
            .define("remove_wom_time_travel_requirement", false);

    public static final ForgeConfigSpec.BooleanValue REMOVE_WOM_DFB_REQUIREMENT = BUILDER
            .comment("Removes the skill requirement from Weapons of Miracles Dreadful Buster skill. (Default: false)")
            .define("remove_wom_dfb_requirement", false);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
