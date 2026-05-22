package net.dawn_day.skill.weapon_innate;

import net.dawn_day.gameasset.animation.DawnDayAnimations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

import java.util.UUID;

public class RAHHHHH extends WeaponInnateSkill {

    int Duration;
    int Amplifier;

    public RAHHHHH(Builder<?> builder) {
        super(builder);
    }


    @Override
    public void loadDatapackParameters(CompoundTag parameters) {
        super.loadDatapackParameters(parameters);
        if(parameters.contains("buff_duration") && parameters.contains("amplifier")){
            Duration = parameters.getInt("buff_duration");
            Amplifier = parameters.getInt("amplifier");
        }
    }

    @Override
    public void executeOnServer(SkillContainer container, CompoundTag args) {
        super.executeOnServer(container, args);
        container.getExecutor().playAnimationSynchronized(DawnDayAnimations.RAAAHHHHH,0.1f);
    }


}
