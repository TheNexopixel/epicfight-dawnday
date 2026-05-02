package net.epicfight_dd.skill.weapon_innate;

import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffectInstance;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

import java.util.UUID;

public class RAHHHHH extends WeaponInnateSkill {

    private static final UUID SkillUUID = UUID.fromString("3680990b-9be1-4650-aea7-a998c1362bcf");

    int Duration;
    int Amplifier;

    public RAHHHHH(SkillBuilder<? extends WeaponInnateSkill> builder) {
        super(builder);
    }

    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
        if(parameters.contains("buff_duration") && parameters.contains("amplifier")){
            Duration = parameters.getInt("buff_duration");
            Amplifier = parameters.getInt("amplifier");
        }
    }

    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {
        super.executeOnServer(container, args);
        container.getExecutor().playAnimationSynchronized(DawnDayAnimations.RAAAHHHHH,0.1f);
        container.getExecutor().getOriginal().addEffect(new MobEffectInstance(EffectRegistry.IMPREGNABILITY.get(), Duration, Amplifier));
    }


}
