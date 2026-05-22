package net.dawn_day.skill.weapon_innate;

import net.dawn_day.gameasset.animation.AdditionalAnimations;
import net.minecraft.nbt.CompoundTag;
import yesman.epicfight.skill.*;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;

public class OdachiIaijutsu extends SimpleWeaponInnateSkill {

    public OdachiIaijutsu(Builder builder) {
        super(builder);
    }

    @Override
    public void executeOnServer(SkillContainer container, CompoundTag args) {
        boolean is_sprint = container.getServerExecutor().getOriginal().isSprinting();
        if (is_sprint) {
            container.getExecutor().playAnimationSynchronized(AdditionalAnimations.EVIL_ODACHI_BATTOJUTSO, 0);
        } else {
            super.executeOnServer(container, args);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
    }

}
