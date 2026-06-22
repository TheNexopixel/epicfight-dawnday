package net.epicfight_dd;
import net.minecraftforge.common.ForgeConfigSpec;
public class DawnDayConfig {

    public static final ForgeConfigSpec.Builder BUILDER =
            new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_DEATH_SOUND =
            BUILDER
                    .comment("Enable Death Sound Effect (Default: true)")
                    .define("deathsound", true);

    public static final ForgeConfigSpec.BooleanValue ENABLE_SPECTATOR_RESPAWN =
            BUILDER
                    .comment("Set Gamemode to Spectator when a player respawns (Default: false)")
                    .define("death_spectator", false);

    public static final ForgeConfigSpec.BooleanValue ENABLE_DEATH_PARTICLES =
            BUILDER
                    .comment("Enables the Particles on DawnDays Custom Death Animations (Default: true)")
                    .define("death_particles", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
