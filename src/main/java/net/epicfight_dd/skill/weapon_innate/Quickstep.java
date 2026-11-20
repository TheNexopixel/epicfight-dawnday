package net.epicfight_dd.skill.weapon_innate;

import java.util.UUID;

import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

public class Quickstep extends WeaponInnateSkill {
    private static final UUID CONSUME_EVENT_UUID = UUID.fromString("d4a7e913-6b20-4c58-9f1e-3a85c2b70e64");
    private static final float STAMINA_COST = 2.0F;

    public Quickstep(SkillBuilder<? extends WeaponInnateSkill> builder) {
        super(builder);
    }

    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
        this.consumption = 0.0F;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.SKILL_CONSUME_EVENT,
                CONSUME_EVENT_UUID,
                event -> {
                    if (event.getSkill() == this) {
                        event.setResourceType(Skill.Resource.NONE);
                    }
                });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.SKILL_CONSUME_EVENT, CONSUME_EVENT_UUID);
        super.onRemoved(container);
    }

    @Override
    public boolean canExecute(SkillContainer container) {
        return hasStamina(container);
    }

    @Override
    public boolean checkExecuteCondition(SkillContainer container) {
        return hasStamina(container);
    }

    private static boolean hasStamina(SkillContainer container) {
        PlayerPatch<?> player = container.getExecutor();
        return player.getOriginal().isCreative() || player.getStamina() >= STAMINA_COST;
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);

        if (!container.getExecutor().isLogicalClient()
                && container.getStack() < this.getMaxStack()) {
            Skill.setSkillStackSynchronize(container, this.getMaxStack());
        }
    }

    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {
        super.executeOnServer(container, args);
        PlayerPatch<?> player = container.getServerExecutor();

        if (!player.getOriginal().isCreative()) {
            player.setStamina(player.getStamina() - STAMINA_COST);
        }
        player.playAnimationSynchronized(DawnDayAnimations.QUICKSTEP_FORWARD, 0.0F);
    }
}