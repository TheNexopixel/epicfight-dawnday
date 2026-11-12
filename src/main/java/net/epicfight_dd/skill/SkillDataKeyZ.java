package net.epicfight_dd.skill;

import net.epicfight_dd.Epicfight_dd;
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

    private SkillDataKeyZ(){}


    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(ResourceLocation.fromNamespaceAndPath(EpicFightMod.MODID,"skill_data_keys"), Epicfight_dd.MODID);

    public static final RegistryObject<SkillDataKey<Integer>> HEATED_STEPS_TIER =
            DATA_KEYS.register("heated_steps_tier", () -> SkillDataKey.createSkillDataKey(
                    PacketBufferCodec.INTEGER, 0, true, HeatedStepsSkill.class));
    public static final RegistryObject<SkillDataKey<Integer>> HEATED_STEPS_RESULT =
            DATA_KEYS.register("heated_steps_result", () -> SkillDataKey.createSkillDataKey(
                    PacketBufferCodec.INTEGER, -1, HeatedStepsSkill.class));
    public static final RegistryObject<SkillDataKey<Integer>> HEATED_STEPS_RESULT_UNTIL =
            DATA_KEYS.register("heated_steps_result_until", () -> SkillDataKey.createSkillDataKey(
                    PacketBufferCodec.INTEGER, 0, HeatedStepsSkill.class));

    public static final RegistryObject<SkillDataKey<Integer>> UNSTOPPABLE_STACKS =
            DATA_KEYS.register("unstoppable_stacks", () -> SkillDataKey.createSkillDataKey(
                    PacketBufferCodec.INTEGER, 0, UnstoppableSkill.class));
    public static final RegistryObject<SkillDataKey<Integer>> UNSTOPPABLE_CAP =
            DATA_KEYS.register("unstoppable_cap", () -> SkillDataKey.createSkillDataKey(
                    PacketBufferCodec.INTEGER, 3, UnstoppableSkill.class));
    public static final RegistryObject<SkillDataKey<Integer>> UNSTOPPABLE_COOLDOWN =
            DATA_KEYS.register("unstoppable_cooldown", () -> SkillDataKey.createSkillDataKey(
                    PacketBufferCodec.INTEGER, 0, UnstoppableSkill.class));
    public static final RegistryObject<SkillDataKey<Integer>> UNSTOPPABLE_REGENERATION =
            DATA_KEYS.register("unstoppable_regeneration", () -> SkillDataKey.createSkillDataKey(
                    PacketBufferCodec.INTEGER, 0, UnstoppableSkill.class));

    public static final RegistryObject<SkillDataKey<Boolean>> SPECIAL_STANCE_ACTIVATE = DATA_KEYS.register("special_stance_active", () -> SkillDataKey.createSkillDataKey(PacketBufferCodec.BOOLEAN, false,true, WingStanceSkill.class));

    public static final RegistryObject<SkillDataKey<Integer>> IRON_WILL_STACKS =
            DATA_KEYS.register( "iron_will_stacks", ()
                    -> SkillDataKey.createSkillDataKey(
                    PacketBufferCodec.INTEGER,
                    0, Riposte.class )
            );
    public static final RegistryObject<SkillDataKey<Float>> IRON_WILL_DAMAGE =
            DATA_KEYS.register("iron_will_damage", () ->
                    SkillDataKey.createSkillDataKey(
                            PacketBufferCodec.FLOAT,
                            0.0F,
                            Riposte.class
                    )
            );
    public static final RegistryObject<SkillDataKey<Integer>> RESTOCKING_COOLDOWN =
            DATA_KEYS.register("restocking_cooldown", () ->
                    SkillDataKey.createSkillDataKey(
                            PacketBufferCodec.INTEGER,
                            0,
                            RestockingSkill.class
                    )
            );

    public static final RegistryObject<SkillDataKey<Integer>> CRESCENDO_DAMAGE =
            DATA_KEYS.register("crescendo_damage", () ->
                    SkillDataKey.createSkillDataKey(
                            PacketBufferCodec.INTEGER,
                            0,
                            CrescendoSkill.class
                    )
            );
    public static final RegistryObject<SkillDataKey<Integer>> ANNULLATION_TICKS =
            DATA_KEYS.register("annullation_ticks", () ->
                    SkillDataKey.createSkillDataKey(
                            PacketBufferCodec.INTEGER,
                            0,
                            AnnullationSkill.class
                    )
            );
    public static final RegistryObject<SkillDataKey<Integer>> ANNULLATION_COOLDOWN =
            DATA_KEYS.register("annullation_cooldown", () ->
                    SkillDataKey.createSkillDataKey(
                            PacketBufferCodec.INTEGER,
                            0,
                            AnnullationSkill.class
                    )
            );

    public static final RegistryObject<SkillDataKey<Boolean>> RAPID_RETREAT_ACTIVE =
            DATA_KEYS.register("rapid_retreat_active", () ->
                    SkillDataKey.createSkillDataKey(
                            PacketBufferCodec.BOOLEAN,
                            false,
                            RapidRetreatSkill.class
                    )
            );
    public static final RegistryObject<SkillDataKey<Integer>> RAPID_RETREAT_COOLDOWN =
            DATA_KEYS.register("rapid_retreat_cooldown", () ->
                    SkillDataKey.createSkillDataKey(
                            PacketBufferCodec.INTEGER,
                            0,
                            RapidRetreatSkill.class
                    )
            );
    public static final RegistryObject<SkillDataKey<Integer>> RAPID_RETREAT_DURATION =
            DATA_KEYS.register("rapid_retreat_duration", () ->
                    SkillDataKey.createSkillDataKey(
                            PacketBufferCodec.INTEGER,
                            0,
                            RapidRetreatSkill.class
                    )
            );



}
