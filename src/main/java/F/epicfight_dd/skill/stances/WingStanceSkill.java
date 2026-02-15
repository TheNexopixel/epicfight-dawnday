package F.epicfight_dd.skill.stances;

import F.epicfight_dd.gameasset.animation.WingStanceAnims;
import F.epicfight_dd.skill.SkillDataKeyZ;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
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

            boolean move_1 = (event.getPlayerPatch().isInAir());
            boolean innate = event.getSkillContainer().getSkill().getCategory() == SkillCategories.WEAPON_INNATE;

            if (!(data_manager.getDataValue(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get()))) {
                return;
            }

            if (move_1 && innate) {
                if (container.sendCastRequest((LocalPlayerPatch) container.getExecutor(), // check if player tries to activate skill
                        ClientEngine.getInstance().controlEngine).isExecutable()) {

                    event.getPlayerPatch().playAnimationSynchronized(WingStanceAnims.WINGSTANCE_SKILL2, 0.0f);

                    event.setCanceled(true);

                }
            } else if (innate) {
                if (container.sendCastRequest((LocalPlayerPatch) container.getExecutor(), // check if player tries to activate skill
                        ClientEngine.getInstance().controlEngine).isExecutable()) {

                    event.getPlayerPatch().playAnimationSynchronized(WingStanceAnims.WINGSTANCE_SKILL1, 0.0f);

                    event.setCanceled(true);

                }
            }


        });

    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        container.getExecutor().getEventListener().removeListener(PlayerEventListener.EventType.SKILL_CAST_EVENT,EVENT_UUID);

    }


}
