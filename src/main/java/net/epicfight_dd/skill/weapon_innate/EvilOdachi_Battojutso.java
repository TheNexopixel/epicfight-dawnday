package net.epicfight_dd.skill.weapon_innate;

import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.epicfight_dd.gameasset.animation.WingStanceAnims;
import net.epicfight_dd.network.DDNetworkHandler;
import net.epicfight_dd.network.ServerBoundSkillResourceConsumePacket;
import net.epicfight_dd.skill.SkillDataKeyZ;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.*;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;

public class EvilOdachi_Battojutso extends SimpleWeaponInnateSkill {
    private static final UUID EVENT_UUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    public EvilOdachi_Battojutso(SkillBuilder<? extends SimpleWeaponInnateSkill> builder) {
        super((Builder) builder);
    }
    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        var listener = container.getExecutor().getEventListener();

        var data_manager = container.getDataManager();
        listener.addEventListener(PlayerEventListener.EventType.SKILL_CAST_EVENT, EVENT_UUID, (event) -> {

            boolean sprintSpecial = (event.getPlayerPatch().getOriginal().isSprinting());
            boolean innate = event.getSkillContainer().getSkill().getCategory() == SkillCategories.WEAPON_INNATE;


            int innateStackCount = container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).getStack();
            Skill innateSkill = container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).getSkill();
            boolean canActivate = innateStackCount >= 1 || container.getExecutor().getOriginal().isCreative();

            if (data_manager != null && data_manager.hasData(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get())) {
            }

            if (!(innate && canActivate)) return;

            if (sprintSpecial) {
                if (container.sendCastRequest((LocalPlayerPatch) container.getExecutor(),
                        ClientEngine.getInstance().controlEngine).isExecutable()) {

                    event.getPlayerPatch().playAnimationSynchronized(DawnDayAnimations.EVIL_ODACHI_BATTOJUTSO, 0.0f);

                    if (!event.getPlayerPatch().getOriginal().isCreative()) {
                        DDNetworkHandler.INSTANCE.sendToServer(new ServerBoundSkillResourceConsumePacket(1));
                    }

                    event.setCanceled(true);
                    return;
                }
            }
            if (container.sendCastRequest((LocalPlayerPatch) container.getExecutor(),
                    ClientEngine.getInstance().controlEngine).isExecutable()) {

                event.getPlayerPatch().playAnimationSynchronized(DawnDayAnimations.EVIL_ODACHI_BEAAAMMMM, 0.0f);

                if (!event.getPlayerPatch().getOriginal().isCreative()) {
                    DDNetworkHandler.INSTANCE.sendToServer(new ServerBoundSkillResourceConsumePacket(1));
                }

                event.setCanceled(true);
            }

        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
    }

}
