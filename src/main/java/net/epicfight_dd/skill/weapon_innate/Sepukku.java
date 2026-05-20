package net.epicfight_dd.skill.weapon_innate;

import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffectInstance;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

public class Sepukku extends WeaponInnateSkill {


    public Sepukku(SkillBuilder<? extends WeaponInnateSkill> builder) {
        super(builder);
    }


    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {
        super.executeOnServer(container, args);
        container.getExecutor().playAnimationSynchronized(DawnDayAnimations.SEPUKKU,0.1f);
    }
}
