package net.epicfight_dd.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class DawnDayConfigs {
    public static void register(ModLoadingContext context) {
        context.registerConfig(ModConfig.Type.COMMON, Common.SPEC);
        context.registerConfig(ModConfig.Type.SERVER, Server.SPEC);
    }

    public static class Common {

        public static final ForgeConfigSpec.Builder BUILDER =
                new ForgeConfigSpec.Builder();

        public static final ForgeConfigSpec.BooleanValue ENABLE_DEATH_SOUND =
                BUILDER
                        .comment("Enable Death Sound Effect (Default: true)")
                        .define("deathsound", true);

        public static final ForgeConfigSpec.BooleanValue ENABLE_DEATH_PARTICLES =
                BUILDER
                        .comment("Enables the Particles on DawnDays Custom Death Animations (Default: false)")
                        .define("death_particles", false);

        public static final ForgeConfigSpec.BooleanValue ENABLE_CUSTOM_MOB_DEATH_ANIM =
                BUILDER
                        .comment("Enable custom death animations on humanoid mob (Default: true) <<Only affects mobs with vanilla ef death animation>>")
                        .define("mob_death_anim", true);

        public static final ForgeConfigSpec.BooleanValue ENABLE_PLAYER_DEATH_ANIMATIONS =
                BUILDER
                        .comment("Enable custom death animations on Players (Default: true)")
                        .define("custom_player_death_anim", true);

        public static final ForgeConfigSpec.DoubleValue SEPUKKU_ATTACK_DAMAGE =
                BUILDER
                        .comment("Sepukku attack damage multiplier (Default: 0.3)(0.3 = 30%)")
                        .defineInRange("sepukku_attack_damage", 0.3D, -10D, 10D);

        public static final ForgeConfigSpec.DoubleValue SEPUKKU_IMPACT =
                BUILDER
                        .comment("Sepukku impact bonus(Default: 0.6)")
                        .defineInRange("sepukku_impact", 0.6D, -100D, 100D);

        public static final ForgeConfigSpec.BooleanValue OVERRIDE_DEFAULT_EXECUTIONS =
                BUILDER
                        .comment("This Option overrides the Executions for Dual Dagger and Onehand Sword (Default: true)")
                        .define("override_default_executions", true);

        public static final ForgeConfigSpec SPEC = BUILDER.build();

    }

    public static class Server {

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
}