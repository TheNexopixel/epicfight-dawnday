package F.epicfight_dd.skill.stances;

import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
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

       listener.addEventListener(PlayerEventListener.EventType.SKILL_CAST_EVENT, EVENT_UUID,(event)->{

           boolean move_1 = (event.getPlayerPatch().getOriginal().isCrouching());


       });

    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        container.getExecutor().getEventListener().removeListener(PlayerEventListener.EventType.SKILL_CAST_EVENT,EVENT_UUID);

    }


}
