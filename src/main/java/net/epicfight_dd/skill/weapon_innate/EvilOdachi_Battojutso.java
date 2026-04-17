package net.epicfight_dd.skill.weapon_innate;

import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.minecraft.network.FriendlyByteBuf;
import yesman.epicfight.skill.*;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;

import java.util.UUID;

public class EvilOdachi_Battojutso extends SimpleWeaponInnateSkill {
    private static final UUID EVENT_UUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    public EvilOdachi_Battojutso(SkillBuilder<? extends SimpleWeaponInnateSkill> builder) {
        super((Builder) builder);
    }

    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {
        boolean is_sprint = container.getServerExecutor().getOriginal().isSprinting();
        if (is_sprint) {
            container.getExecutor().playAnimationSynchronized(DawnDayAnimations.EVIL_ODACHI_BATTOJUTSO, 0);
        } else {
            super.executeOnServer(container, args);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
    }

}
