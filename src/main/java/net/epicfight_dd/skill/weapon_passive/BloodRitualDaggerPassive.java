package net.epicfight_dd.skill.weapon_passive;

import java.util.UUID;

import net.epicfight_dd.effect.EffectRegistry;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;

import org.joml.Vector3f;

import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

public class BloodRitualDaggerPassive extends PassiveSkill {
    private static final UUID DAMAGE_EVENT_UUID =
            UUID.fromString("a7a3d80b-6a48-4f21-8c12-43b9e7d51a20");

    private static final UUID KILL_EVENT_UUID =
            UUID.fromString("c5f7d4a1-8e32-4b69-a125-97d3f0c64b81");

    public BloodRitualDaggerPassive(
            SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    private boolean hasThisPassive(PlayerPatch<?> playerPatch) {
        CapabilityItem capability = playerPatch.getHoldingItemCapability(
                InteractionHand.MAIN_HAND);

        return capability != null && capability.getPassiveSkill() == this;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT,
                DAMAGE_EVENT_UUID,
                event -> {
                    PlayerPatch<?> playerPatch = event.getPlayerPatch();

                    if (playerPatch == null || !hasThisPassive(playerPatch)) {
                        return;
                    }

                    Player player = playerPatch.getOriginal();

                    if (player.hasEffect(EffectRegistry.SEPUKKU.get())) {
                        event.attachValueModifier(ValueModifier.adder(2.0F));
                    }
                }
        );

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.PLAYER_KILLED_EVENT,
                KILL_EVENT_UUID,
                event -> {
                    PlayerPatch<?> playerPatch = event.getPlayerPatch();

                    if (playerPatch == null || !hasThisPassive(playerPatch)) {
                        return;
                    }

                    Player player = playerPatch.getOriginal();

                    if (!(player.level() instanceof ServerLevel level)) {
                        return;
                    }

                    LivingEntity victim = event.getKilledEntity();
                    if (victim == null) {
                        return;
                    }

                    double maxHealthBonus = victim.getMaxHealth() * 0.05D;
                    boolean sepukku = player.hasEffect(EffectRegistry.SEPUKKU.get());
                    boolean drained = player.hasEffect(EffectRegistry.DRAINDED.get());

                    float healing = 0.0F;

                    if (victim instanceof Player) {
                        healing += (float) (maxHealthBonus + (sepukku ? 20.0D : 10.0D));
                    }

                    if (sepukku) {
                        healing += (float) (maxHealthBonus * 2.0D);
                    }

                    healing += (float) (drained
                            ? maxHealthBonus / 2.0D
                            : maxHealthBonus);

                    player.heal(healing);

                    if (drained) {
                        level.sendParticles(
                                new DustParticleOptions(
                                        new Vector3f(1.0F, 1.0F, 1.0F),
                                        1.2F
                                ),
                                player.getX(),
                                player.getY() + 1.0D,
                                player.getZ(),
                                15,
                                0.3D, 0.5D, 0.3D,
                                0.01D
                        );

                        player.removeEffect(EffectRegistry.DRAINDED.get());
                    }

                    level.sendParticles(
                            new DustParticleOptions(
                                    new Vector3f(1.0F, 0.0F, 0.0F),
                                    1.2F
                            ),
                            player.getX(),
                            player.getY() + 1.0D,
                            player.getZ(),
                            15,
                            0.3D, 0.5D, 0.3D,
                            0.01D
                    );
                }
        );
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT,
                DAMAGE_EVENT_UUID);

        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.PLAYER_KILLED_EVENT,
                KILL_EVENT_UUID);
    }
}