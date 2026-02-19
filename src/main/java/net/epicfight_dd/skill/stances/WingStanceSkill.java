package net.epicfight_dd.skill.stances;

import net.epicfight_dd.gameasset.animation.WingStanceAnims;
import net.epicfight_dd.skill.SkillDataKeyZ;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.*;

import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;


public class WingStanceSkill extends Skill {

    public WingStanceSkill(SkillBuilder<? extends Skill> builder) {
        super(builder);
    }

    private static final UUID EVENT_UUID = UUID.fromString("fa4819dd-fb7a-435c-968f-fffca42a09b8");

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        var listener = container.getExecutor().getEventListener();

        var data_manager = container.getDataManager();


        listener.addEventListener(PlayerEventListener.EventType.SKILL_CAST_EVENT, EVENT_UUID, (event) -> {

            boolean sprintSpecial = (event.getPlayerPatch().getOriginal().isSprinting());
            boolean innate = event.getSkillContainer().getSkill().getCategory() == SkillCategories.WEAPON_INNATE;


            int innateStackCount = (int) container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).getResource();
            Skill innateSkill = container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).getSkill();
            boolean canActivate = innateStackCount >= 1 || container.getExecutor().getOriginal().isCreative();
            boolean isInWingStance= false;

            if (data_manager != null && data_manager.hasData(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get())) {
                isInWingStance = data_manager.getDataValue(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get());
            }

            if (!isInWingStance) {return;}

            if (sprintSpecial && innate && canActivate) {
                if (container.sendCastRequest((LocalPlayerPatch) container.getExecutor(), // check if player tries to activate skill
                        ClientEngine.getInstance().controlEngine).isExecutable()) {

                    event.getPlayerPatch().playAnimationSynchronized(WingStanceAnims.WINGSTANCE_SKILL2, 0.0f);


                    container.getExecutor().consumeForSkill(innateSkill ,Resource.WEAPON_CHARGE, innateStackCount -1);
                    event.setCanceled(true);

                }
            } else if (innate && canActivate) {
                if (container.sendCastRequest((LocalPlayerPatch) container.getExecutor(), // check if player tries to activate skill
                        ClientEngine.getInstance().controlEngine).isExecutable()) {

                    event.getPlayerPatch().playAnimationSynchronized(WingStanceAnims.WINGSTANCE_SKILL1, 0.0f);

                    container.getExecutor().consumeForSkill(innateSkill ,Resource.WEAPON_CHARGE, innateStackCount -1);
                    event.setCanceled(true);

                }
            }


        });

    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        container.getExecutor().getEventListener().removeListener(PlayerEventListener.EventType.SKILL_CAST_EVENT, EVENT_UUID);

    }


}
