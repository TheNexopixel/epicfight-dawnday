package net.epicfight_dd.skill;

import net.epicfight_dd.EpicFightDawnDay;
import net.epicfight_dd.skill.passive.CortisolSkill;
import net.epicfight_dd.skill.passive.RapidRetreatSkill;
import net.epicfight_dd.skill.passive.RestockingSkill;
import net.epicfight_dd.skill.passive.Riposte;
import net.epicfight_dd.skill.passive.unstoppable.UnstoppableSkill;
import net.epicfight_dd.skill.stances.WingStanceSkill;
import net.epicfight_dd.skill.dodge.HeatedStepsSkill;
import net.epicfight_dd.skill.weapon_innate.AnnullationSkill;
import net.epicfight_dd.skill.weapon_passive.CrescendoSkill;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.api.utils.PacketBufferCodec;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;

public class SkillDataKeyZ {

    private SkillDataKeyZ() {}

    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(ResourceLocation.fromNamespaceAndPath(EpicFightMod.MODID, "skill_data_keys"), EpicFightDawnDay.MODID);

    private static <T> RegistryObject<SkillDataKey<T>> register(String name, PacketBufferCodec<T> codec, T defaultValue, boolean syncToTrackingPlayers, Class<?>... skillClasses) {
        return DATA_KEYS.register(name, () -> SkillDataKey.createSkillDataKey(codec, defaultValue, syncToTrackingPlayers, skillClasses));
    }

    private static <T> RegistryObject<SkillDataKey<T>> register(String name, PacketBufferCodec<T> codec, T defaultValue, Class<?>... skillClasses) {
        return register(name, codec, defaultValue, false, skillClasses);
    }

    // Heated Steps
    public static final RegistryObject<SkillDataKey<Integer>> HEATED_STEPS_TIER = register("heated_steps_tier", PacketBufferCodec.INTEGER, 0, true, HeatedStepsSkill.class);
    public static final RegistryObject<SkillDataKey<Integer>> HEATED_STEPS_RESULT = register("heated_steps_result", PacketBufferCodec.INTEGER, -1, HeatedStepsSkill.class);
    public static final RegistryObject<SkillDataKey<Integer>> HEATED_STEPS_RESULT_UNTIL = register("heated_steps_result_until", PacketBufferCodec.INTEGER, 0, HeatedStepsSkill.class);

    // Unstoppable
    public static final RegistryObject<SkillDataKey<Integer>> UNSTOPPABLE_STACKS = register("unstoppable_stacks", PacketBufferCodec.INTEGER, 0, UnstoppableSkill.class);
    public static final RegistryObject<SkillDataKey<Integer>> UNSTOPPABLE_CAP = register("unstoppable_cap", PacketBufferCodec.INTEGER, 3, UnstoppableSkill.class);
    public static final RegistryObject<SkillDataKey<Integer>> UNSTOPPABLE_COOLDOWN = register("unstoppable_cooldown", PacketBufferCodec.INTEGER, 0, UnstoppableSkill.class);
    public static final RegistryObject<SkillDataKey<Double>> UNSTOPPABLE_REGENERATION = register("unstoppable_regeneration", PacketBufferCodec.DOUBLE, 0D, UnstoppableSkill.class);
    public static final RegistryObject<SkillDataKey<Integer>> UNSTOPPABLE_AMBIENT_DELAY = register("unstoppable_ambient_delay", PacketBufferCodec.INTEGER, 0, UnstoppableSkill.class);

    // Wing Stance
    public static final RegistryObject<SkillDataKey<Boolean>> SPECIAL_STANCE_ACTIVATE = register("special_stance_active", PacketBufferCodec.BOOLEAN, false, true, WingStanceSkill.class);

    // Cortisol
    public static final RegistryObject<SkillDataKey<Integer>> CORTISOL_STRESS = register("cortisol_stress", PacketBufferCodec.INTEGER, 0, CortisolSkill.class);
    public static final RegistryObject<SkillDataKey<Integer>> CORTISOL_IDLE = register("cortisol_idle", PacketBufferCodec.INTEGER, 0, CortisolSkill.class);

    // Iron Will (Riposte)
    public static final RegistryObject<SkillDataKey<Integer>> IRON_WILL_STACKS = register("iron_will_stacks", PacketBufferCodec.INTEGER, 0, Riposte.class);
    public static final RegistryObject<SkillDataKey<Float>> IRON_WILL_DAMAGE = register("iron_will_damage", PacketBufferCodec.FLOAT, 0.0F, Riposte.class);

    // Restocking
    public static final RegistryObject<SkillDataKey<Integer>> RESTOCKING_COOLDOWN = register("restocking_cooldown", PacketBufferCodec.INTEGER, 0, RestockingSkill.class);

    // Crescendo
    public static final RegistryObject<SkillDataKey<Integer>> CRESCENDO_DAMAGE = register("crescendo_damage", PacketBufferCodec.INTEGER, 0, CrescendoSkill.class);

    // Annullation
    public static final RegistryObject<SkillDataKey<Integer>> ANNULLATION_TICKS = register("annullation_ticks", PacketBufferCodec.INTEGER, 0, AnnullationSkill.class);
    public static final RegistryObject<SkillDataKey<Integer>> ANNULLATION_COOLDOWN = register("annullation_cooldown", PacketBufferCodec.INTEGER, 0, AnnullationSkill.class);

    // Rapid Retreat
    public static final RegistryObject<SkillDataKey<Boolean>> RAPID_RETREAT_ACTIVE = register("rapid_retreat_active", PacketBufferCodec.BOOLEAN, false, RapidRetreatSkill.class);
    public static final RegistryObject<SkillDataKey<Integer>> RAPID_RETREAT_COOLDOWN = register("rapid_retreat_cooldown", PacketBufferCodec.INTEGER, 0, RapidRetreatSkill.class);
    public static final RegistryObject<SkillDataKey<Integer>> RAPID_RETREAT_DURATION = register("rapid_retreat_duration", PacketBufferCodec.INTEGER, 0, RapidRetreatSkill.class);
}