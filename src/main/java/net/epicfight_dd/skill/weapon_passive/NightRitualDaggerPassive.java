package net.epicfight_dd.skill.weapon_passive;

import java.util.List;
import java.util.Locale;
import java.util.UUID;


import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.DustParticleOptions;
import org.joml.Vector3f;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;

import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

public class NightRitualDaggerPassive extends PassiveSkill {
    private static final UUID EVENT_UUID =
            UUID.fromString("bc19f0d2-1b77-4f52-9c12-7a6d3e5f8104");

    public NightRitualDaggerPassive(
            SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    private static float getNightBonus(Level level) {
        if (!level.isNight()) {
            return 0.0F;
        }

        return switch (level.getMoonPhase()) {
            case 0 -> 5.0F;
            case 1, 7 -> 3.5F;
            case 2, 6 -> 2.5F;
            case 3, 5 -> 1.0F;
            default -> 0.0F;
        };
    }

    private static boolean isNightDagger(Player player) {
        return player.getMainHandItem().is(DawnDayItems.NIGHT_RITUS_DAGGER.get())
                || player.getMainHandItem().is(DawnDayItems.NIGHTINGALE.get());
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT,
                EVENT_UUID,
                event -> {
                    PlayerPatch<?> playerPatch = event.getPlayerPatch();

                    if (playerPatch == null) {
                        return;
                    }

                    Player player = playerPatch.getOriginal();
                    CapabilityItem capability =
                            playerPatch.getHoldingItemCapability(
                                    net.minecraft.world.InteractionHand.MAIN_HAND);

                    if (capability == null
                            || capability.getPassiveSkill() != this
                            || !isNightDagger(player)) {
                        return;
                    }

                    float bonus = getNightBonus(player.level());

                    if (bonus > 0.0F) {
                        event.attachValueModifier(ValueModifier.adder(bonus));
                    }
                }
        );

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.PLAYER_KILLED_EVENT,
                EVENT_UUID,
                event -> {
                    PlayerPatch<?> playerPatch = event.getPlayerPatch();

                    if (playerPatch == null) {
                        return;
                    }

                    Player player = playerPatch.getOriginal();

                    if (!isNightDagger(player)) {
                        return;
                    }

                    if (!(player.level() instanceof ServerLevel level)) {
                        return;
                    }

                    float maxStamina = playerPatch.getMaxStamina();
                    float staminaBonus = 0.0F;

                    if (event.getKilledEntity() instanceof Player) {
                        staminaBonus += player.hasEffect(EffectRegistry.SEPUKKU.get())
                                ? maxStamina
                                : maxStamina / 2.0F;
                    }

                    if (level.isNight()) {
                        staminaBonus += player.hasEffect(EffectRegistry.SEPUKKU.get())
                                ? 6.0F
                                : 3.0F;
                    } else {
                        staminaBonus += player.hasEffect(EffectRegistry.SEPUKKU.get())
                                ? 0.0F
                                : 1.5F;
                    }

                    playerPatch.setStamina(Math.min(
                            maxStamina,
                            playerPatch.getStamina() + staminaBonus
                    ));

                    level.sendParticles(
                            new DustParticleOptions(
                                    new Vector3f(0.2F, 0.4F, 1.0F),
                                    1.2F
                            ),
                            player.getX(),
                            player.getY() + 1.0,
                            player.getZ(),
                            15,
                            0.3,
                            0.5,
                            0.3,
                            0.01
                    );
                }
        );
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT,
                EVENT_UUID);

        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.PLAYER_KILLED_EVENT,
                EVENT_UUID);
    }

    @Override
    public List<Component> getTooltipOnItem(
            ItemStack itemStack,
            CapabilityItem itemCap,
            PlayerPatch<?> playerPatch) {

        List<Component> tooltip =
                super.getTooltipOnItem(itemStack, itemCap, playerPatch);

        float bonus = 0.0F;

        if (playerPatch != null) {
            bonus = getNightBonus(playerPatch.getOriginal().level());
        }

        tooltip.add(Component.translatable(
                "skill.epicfight_dd.ritus_passive.description",
                String.format(Locale.ROOT, "%.1f", bonus)
        ));

        return tooltip;
    }
}