package net.dawn_day.skill.weapon_innate;

import net.dawn_day.gameasset.animation.DawnDayAnimations;
import net.minecraft.nbt.CompoundTag;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

public class Seppuku extends WeaponInnateSkill {


    public Seppuku(Builder<?> builder) {
        super(builder);
    }


    @Override
    public void executeOnServer(SkillContainer container, CompoundTag args) {
        super.executeOnServer(container, args);
        container.getExecutor().playAnimationSynchronized(DawnDayAnimations.SEPUKKU,0.1f);
    }
}
