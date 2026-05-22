package net.dawn_day.skill;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.skill.stances.WingStanceSkill;
import net.minecraft.network.codec.ByteBufCodecs;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import yesman.epicfight.registry.EpicFightRegistries;
import yesman.epicfight.skill.SkillDataKey;


public class SkillDataKeys {

    private SkillDataKeys(){}

 /*
ヽ(｀Д´)ﾉ
ヽ(｀Д´)ﾉ
ヽ(｀Д´)ﾉ
ヽ(｀Д´)ﾉ
  */

    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(EpicFightRegistries.SKILL_DATA_KEY, EpicFightDawnDay.MOD_ID);

    public static final DeferredHolder<SkillDataKey<?>,SkillDataKey<Boolean>> SPECIAL_STANCE_ACTIVATE = DATA_KEYS.register("special_stance_active", () -> SkillDataKey.createSkillDataKey(ByteBufCodecs.BOOL, false,true, WingStanceSkill.class));




}
