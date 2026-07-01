package net.epicfight_dd.skill.weapon_innate;

import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.minecraft.network.FriendlyByteBuf;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

public class Quickstep extends WeaponInnateSkill {

    private static final float STAMINA_COST = 2.0F;


    public Quickstep(SkillBuilder<? extends WeaponInnateSkill> builder) {
        super(builder);
    }

    @Override
    public boolean canExecute(SkillContainer container) {
        PlayerPatch<?> player = container.getExecutor();

        return player.getStamina() >= STAMINA_COST;
        }



    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {
        super.executeOnServer(container, args);
        PlayerPatch<?> player = container.getServerExecutor();


        player.setStamina(player.getStamina() - STAMINA_COST);
        container.getExecutor().playAnimationSynchronized(DawnDayAnimations.QUICKSTEP_FORWARD,0.0f);
    }
}
