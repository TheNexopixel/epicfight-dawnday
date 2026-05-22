package net.dawn_day.skill.stances;

import net.dawn_day.gameasset.animation.WingStanceAnims;
import net.dawn_day.network.DDNetworkHandler;
import net.dawn_day.network.CPSkillConsume;
import net.dawn_day.skill.SkillDataKeys;
import net.neoforged.neoforge.network.PacketDistributor;
import yesman.epicfight.api.event.EntityEventListener;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.client.events.engine.ControlEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.*;


public class WingStanceSkill extends Skill {

    public WingStanceSkill(SkillBuilder<?> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container, EntityEventListener listener) {
        super.onInitiate(container, listener);


        var data_manager = container.getDataManager();

        listener.registerEvent(EpicFightEventHooks.Player.CAST_SKILL, event -> {
            boolean sprintSpecial = (event.getPlayerPatch().getOriginal().isSprinting());
            boolean crouchspecial = (event.getPlayerPatch().getOriginal().isCrouching());
            boolean innate = event.getSkillContainer().getSkill().getCategory() == SkillCategories.WEAPON_INNATE;


            int innateStackCount = container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).getStack();
            Skill innateSkill = container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).getSkill();
            boolean canActivate = innateStackCount >= 1 || container.getExecutor().getOriginal().isCreative();
            boolean isInWingStance = false;

            if (data_manager != null && data_manager.hasData(SkillDataKeys.SPECIAL_STANCE_ACTIVATE)) {
                isInWingStance = data_manager.getDataValue(SkillDataKeys.SPECIAL_STANCE_ACTIVATE);
            }

            if (!isInWingStance) return;
            if (!(innate && canActivate)) return;

            if (crouchspecial) {
                if (container.sendCastRequest((LocalPlayerPatch) container.getExecutor(),
                        ControlEngine.getInstance()).isExecutable()) {

                    event.getPlayerPatch().playAnimationSynchronized(WingStanceAnims.WINGSTANCE_SKILL2, 0.0f);

                    if (!event.getPlayerPatch().getOriginal().isCreative()) {
                        DDNetworkHandler.sendToServer(new CPSkillConsume(1));
                    }

                    event.cancel();
                    return;
                }
            }
            if (sprintSpecial) {
                if (container.sendCastRequest((LocalPlayerPatch) container.getExecutor(), ControlEngine.getInstance()).isExecutable()) {

                    event.getPlayerPatch().playAnimationSynchronized(WingStanceAnims.WINGSTANCE_CROUCH_ATTACK, 0.0f);

                    if (!event.getPlayerPatch().getOriginal().isCreative()) {
                        PacketDistributor.sendToServer(new CPSkillConsume(1));
                    }

                    event.cancel();
                    return;
                }
            }
            if (container.sendCastRequest((LocalPlayerPatch) container.getExecutor(),
                    ControlEngine.getInstance()).isExecutable()) {

                event.getPlayerPatch().playAnimationSynchronized(WingStanceAnims.WINGSTANCE_SKILL1, 0.0f);

                if (!event.getPlayerPatch().getOriginal().isCreative()) {
                    DDNetworkHandler.sendToServer(new CPSkillConsume(1));
                }

                event.cancel();
            }
        }, this);
    }
}
