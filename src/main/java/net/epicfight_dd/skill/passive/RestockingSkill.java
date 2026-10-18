
package net.epicfight_dd.skill.passive;

import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Objects;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import reascer.wom.gameasset.WOMSkills;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.network.EntityPairingPacketTypes;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPEntityPairingPacket;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;


public class RestockingSkill extends PassiveSkill {
    private static final UUID EVENT_UUID =
            UUID.fromString("a8161186-b902-11ed-afa1-0242ac120002");

    private static final int DEFAULT_COOLDOWN_TICKS = 300;

    private final Map<Player, Integer> cooldowns = new WeakHashMap<>();

    public RestockingSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
        this.consumption = 15.0F;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        PlayerPatch<?> playerPatch = container.getExecutor();
        Player player = playerPatch.getOriginal();

        cooldowns.putIfAbsent(player, 0);

        playerPatch.getEventListener().addEventListener(
                EventType.PLAYER_KILLED_EVENT,
                EVENT_UUID,
                event -> {
                    PlayerPatch<?> patch = container.getExecutor();
                    Player killer = patch.getOriginal();

                    if (cooldowns.getOrDefault(killer, 0) > 0) {
                        return;
                    }

                    var capability = patch.getAdvancedHoldingItemCapability(
                            InteractionHand.MAIN_HAND
                    );

                    var heldItem = patch.getValidItemInHand(
                            InteractionHand.MAIN_HAND
                    );

                    Skill innateSkill =
                            capability.getInnateSkill(patch, heldItem);

                    if (innateSkill == null) {
                        return;
                    }

                    SkillContainer innateContainer =
                            patch.getSkillCapability()
                                    .getSkillContainer(innateSkill);

                    if (innateContainer == null) {
                        return;
                    }

                    boolean restored = false;

                    if (isWomResourceSkill(innateSkill)) {

                        float currentResource = innateContainer.getResource();
                        float maxResource = innateContainer.getMaxResource();

                        if (currentResource < maxResource) {
                            float amountToRestore =
                                    innateSkill.getConsumption();

                            float newResource = Math.min(
                                    currentResource + amountToRestore,
                                    maxResource
                            );

                            if (newResource > currentResource) {
                                innateContainer.setResource(newResource);
                                restored = true;
                            }
                        }
                    } else {

                        if (innateContainer.isFull()) {
                            return;
                        }

                        int maxStacks =
                                innateContainer.getSkill().getMaxStack();

                        int stacksToAdd;

                        if (event.getKilledEntity() instanceof Player) {
                            stacksToAdd =
                                    maxStacks - innateContainer.getStack();
                        } else {
                            stacksToAdd = 1;
                        }

                        if (stacksToAdd <= 0) {
                            return;
                        }

                        int newStack = Math.min(
                                innateContainer.getStack() + stacksToAdd,
                                maxStacks
                        );

                        Skill.setSkillStackSynchronize(
                                innateContainer,
                                newStack
                        );

                        restored = true;
                    }


                    if (!restored) {
                        return;
                    }

                    cooldowns.put(killer, DEFAULT_COOLDOWN_TICKS);

                    killer.level().playSound(
                            null,
                            killer.getX(),
                            killer.getY(),
                            killer.getZ(),
                            SoundEvents.RESPAWN_ANCHOR_SET_SPAWN,
                            killer.getSoundSource(),
                            3.5F,
                            2.0F
                    );

                    SPEntityPairingPacket pairingPacket =
                            new SPEntityPairingPacket(
                                    killer.getId(),
                                    EntityPairingPacketTypes.FLASH_WHITE
                            );

                    pairingPacket.getBuffer().writeInt(9);
                    pairingPacket.getBuffer().writeInt(15);
                    pairingPacket.getBuffer().writeInt(1);
                    pairingPacket.getBuffer().writeBoolean(true);

                    EpicFightNetworkManager.sendToAllPlayerTrackingThisEntityWithSelf(
                            pairingPacket,
                            container.getServerExecutor().getOriginal()
                    );
                }
        );
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        Player player = container.getExecutor().getOriginal();

        container.getExecutor().getEventListener().removeListener(
                EventType.PLAYER_KILLED_EVENT,
                EVENT_UUID
        );

        cooldowns.remove(player);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);

        Player player = container.getExecutor().getOriginal();
        int remaining = cooldowns.getOrDefault(player, 0);

        if (remaining > 0) {
            cooldowns.put(player, remaining - 1);
        }
    }

    @Override
    public boolean shouldDraw(SkillContainer container) {
        Player player = container.getExecutor().getOriginal();
        return cooldowns.getOrDefault(player, 0) > 0;
    }

    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);

        if (parameters.contains("consumption")) {
            this.consumption = parameters.getFloat("consumption");
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(
            BattleModeGui gui,
            SkillContainer container,
            GuiGraphics guiGraphics,
            float x,
            float y,
            float partialTick) {

        guiGraphics.blit(
                this.getSkillTexture(),
                (int) x,
                (int) y,
                24, 24,
                0, 0,
                1, 1,
                1, 1
        );

        Player player = container.getExecutor().getOriginal();
        int remaining = cooldowns.getOrDefault(player, 0);

        if (remaining > 0) {
            int seconds = (int) Math.ceil(remaining / 20.0);
            String text = String.valueOf(seconds);

            int textWidth = gui.getFont().width(text);

            guiGraphics.drawString(
                    gui.getFont(),
                    text,
                    (int) x + (24 - textWidth) / 2,
                    (int) y + 15,
                    16777215,
                    true
            );
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public List<Object> getTooltipArgsOfScreen(List<Object> list) {
        list.add(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(
                this.consumption
        ));

        return list;
    }
    private boolean isWomResourceSkill(Skill skill) {
        if (skill == null) {
            return false;
        }

        try {
            Class<?> womSkills = Class.forName(
                    "reascer.wom.gameasset.WOMSkills"
            );

            Object trueWrath = womSkills.getField("TRUE_BERSERK").get(null);
            Object demonicAscension =
                    womSkills.getField("DEMONIC_ASCENSION").get(null);

            return skill == trueWrath || skill == demonicAscension;

        } catch (ClassNotFoundException
                 | NoSuchFieldException
                 | IllegalAccessException
                 | LinkageError e) {
            return false;
        }
    }
}
