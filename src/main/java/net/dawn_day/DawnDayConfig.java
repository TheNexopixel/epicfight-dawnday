package net.dawn_day;

import net.neoforged.neoforge.common.ModConfigSpec;

public class DawnDayConfig {

    public static final ModConfigSpec.Builder BUILDER =
            new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_DEATH_SOUND =
            BUILDER
                    .comment("Enable Death Sound Effect (Default: true)")
                    .define("deathsound", true);

    public static final ModConfigSpec.BooleanValue ENABLE_SPECTATOR_RESPAWN =
            BUILDER
                    .comment("Set Gamemode to Spectator when a player respawns (Default: false)")
                    .define("death_spectator", false);

    public static final ModConfigSpec SPEC = BUILDER.build();

}
