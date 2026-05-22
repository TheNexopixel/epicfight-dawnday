package net.dawn_day.skill.weapon_innate;

import net.dawn_day.gameasset.animation.QoLMiscAnimations;
import net.dawn_day.gameasset.DawnDaySounds;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.event.EntityEventListener;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.api.event.IdentifierProvider;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;


public class SkullRuptureSkill extends SimpleWeaponInnateSkill {

    public SkullRuptureSkill(Builder builder) {
        super(builder);
    }


    @Override
    public void onInitiate(SkillContainer container, EntityEventListener listener) {
        super.onInitiate(container, listener);
        listener.registerEvent(EpicFightEventHooks.Entity.DELIVER_DAMAGE_INCOME, event -> {
            if (event.getTarget() instanceof ServerPlayer targetPlayer) {

                ServerPlayerPatch targetPatch = EpicFightCapabilities.getServerPlayerPatch(targetPlayer);

                ServerPlayerPatch serverPlayerPatch = container.getServerExecutor();

                AnimationPlayer animationPlayer = serverPlayerPatch.getServerAnimator().animationPlayer;
                boolean isInExecution = animationPlayer.getRealAnimation().equals(this.attackAnimation);

                if (isInExecution) {
                    if (targetPatch != null) {
                        try {
                            listener.registerEvent(EpicFightEventHooks.Entity.TAKE_DAMAGE_INCOME, (evt) -> {
                                if (evt.isParried()) {
                                    serverPlayerPatch.playAnimationSynchronized(QoLMiscAnimations.BATTLE$TAFF_PARRIED,0.1f);

                                    serverPlayerPatch.getOriginal().level().playSound(
                                            null,
                                            serverPlayerPatch.getOriginal().getOnPos(),
                                            DawnDaySounds.GUARD_BREAK.get()
                                            , SoundSource.PLAYERS,
                                            100f,
                                            1f
                                    );

                                }

                                MinecraftServer server = evt.getEntityPatch().getOriginal().getServer();
                                if (server == null) return;
                                int curr_tick_cnt = server.getTickCount();
                                server.tell(new TickTask(curr_tick_cnt + 20, () ->
                                        listener.removeListenersBelongTo(IdentifierProvider.constant("lol"))));

                            }, IdentifierProvider.constant("lol"));

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }


            }
        }, this);

    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

    }
}
