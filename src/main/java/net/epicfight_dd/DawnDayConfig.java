package net.epicfight_dd;
import net.minecraftforge.common.ForgeConfigSpec;
public class DawnDayConfig {

    public static final ForgeConfigSpec.Builder BUILDER =
            new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_DEATH_SOUND =
            BUILDER
                    .comment("Enable Death Sound Effect (Default: true)")
                    .define("deathsound", true);

    public static final ForgeConfigSpec.BooleanValue ENABLE_DEATH_PARTICLES =
            BUILDER
                    .comment("Enables the Particles on DawnDays Custom Death Animations (Default: true)")
                    .define("death_particles", true);

    public static final ForgeConfigSpec.BooleanValue ENABLE_CUSTOM_MOB_DEATH_ANIM =
            BUILDER
                    .comment("Enable custom death animations on humanoid mob (Default: true) <<Only affects mobs with vanilla ef death animation>>")
                    .define("mob_death_anim", true);

    public static final ForgeConfigSpec.DoubleValue SEPUKKU_ATTACK_DAMAGE =
            BUILDER
                    .comment("Sepukku attack damage multiplier (Default: 0.3)(0.3 = 30%)")
                    .defineInRange("sepukku_attack_damage", 0.3D, -10D, 10D);

    public static final ForgeConfigSpec.DoubleValue SEPUKKU_IMPACT =
            BUILDER
                    .comment("Sepukku impact bonus(Default: 0.6)")
                    .defineInRange("sepukku_impact", 0.6D, -100D, 100D);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
