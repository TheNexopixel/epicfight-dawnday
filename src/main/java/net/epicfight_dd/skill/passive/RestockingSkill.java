package net.epicfight_dd.skill.passive;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
    private static final UUID EVENT_UUID = UUID.fromString("a8161186-b902-11ed-afa1-0242ac120002");

    private static final Field[] WOM_RESOURCE_FIELDS = resolveWomFields();

    public RestockingSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
        this.consumption = 15.0F;
    }

    private static Field[] resolveWomFields() {
        try {
            Class<?> womSkills = Class.forName("reascer.wom.gameasset.WOMSkills");
            return new Field[] {
                    womSkills.getField("TRUE_BERSERK"),
                    womSkills.getField("DEMONIC_ASCENSION")
            };
        } catch (ReflectiveOperationException | LinkageError e) {
            return new Field[0];
        }
    }

    private static boolean isWomResourceSkill(Skill skill) {
        for (Field field : WOM_RESOURCE_FIELDS) {
            try {
                if (field.get(null) == skill) return true;
            } catch (IllegalAccessException ignored) {
            }
        }
        return false;
    }

    private static int cooldown(SkillContainer container) {
        return container.getDataManager().getDataValue(SkillDataKeyZ.RESTOCKING_COOLDOWN.get());
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(
                EventType.PLAYER_KILLED_EVENT,
                EVENT_UUID,
                event -> {
                    if (container.getExecutor().isLogicalClient() || cooldown(container) > 0) return;

                    PlayerPatch<?> patch = container.getExecutor();
                    var capability = patch.getAdvancedHoldingItemCapability(InteractionHand.MAIN_HAND);
                    var heldItem = patch.getValidItemInHand(InteractionHand.MAIN_HAND);

                    Skill innateSkill = capability.getInnateSkill(patch, heldItem);
                    if (innateSkill == null) return;

                    SkillContainer innate = patch.getSkillCapability().getSkillContainer(innateSkill);
                    if (innate == null) return;

                    if (isWomResourceSkill(innateSkill)) {
                        float current = innate.getResource();
                        float max = innate.getMaxResource();
                        if (current >= max) return;
                        innate.setResource(Math.min(current + innateSkill.getConsumption(), max));
                    } else {
                        if (innate.isFull()) return;
                        int max = innate.getSkill().getMaxStack();
                        int target = event.getKilledEntity() instanceof Player
                                ? max
                                : Math.min(innate.getStack() + 1, max);
                        Skill.setSkillStackSynchronize(innate, target);
                    }

                    ServerPlayer killer = container.getServerExecutor().getOriginal();

                    container.getDataManager().setDataSync(
                            SkillDataKeyZ.RESTOCKING_COOLDOWN.get(),
                            Math.round(this.consumption * 20.0F)
                    );

                    killer.level().playSound(null, killer.getX(), killer.getY(), killer.getZ(),
                            SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, killer.getSoundSource(), 3.5F, 2.0F);

                    SPEntityPairingPacket packet =
                            new SPEntityPairingPacket(killer.getId(), EntityPairingPacketTypes.FLASH_WHITE);
                    packet.getBuffer().writeInt(9);
                    packet.getBuffer().writeInt(15);
                    packet.getBuffer().writeInt(1);
                    packet.getBuffer().writeBoolean(true);

                    EpicFightNetworkManager.sendToAllPlayerTrackingThisEntityWithSelf(packet, killer);
                }
        );
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecutor().getEventListener().removeListener(EventType.PLAYER_KILLED_EVENT, EVENT_UUID);
        container.getDataManager().setData(SkillDataKeyZ.RESTOCKING_COOLDOWN.get(), 0);
        super.onRemoved(container);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
        int remaining = cooldown(container);
        if (remaining > 0) {
            container.getDataManager().setData(SkillDataKeyZ.RESTOCKING_COOLDOWN.get(), remaining - 1);
        }
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
    public boolean shouldDraw(SkillContainer container) {
        return cooldown(container) > 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics graphics,
                          float x, float y, float partialTick) {
        graphics.blit(this.getSkillTexture(), (int) x, (int) y, 24, 24, 0, 0, 1, 1, 1, 1);

        int remaining = cooldown(container);
        if (remaining > 0) {
            String text = String.valueOf((remaining + 19) / 20);
            graphics.drawString(gui.getFont(), text,
                    (int) x + (24 - gui.getFont().width(text)) / 2, (int) y + 15, 0xFFFFFF, true);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public List<Object> getTooltipArgsOfScreen(List<Object> list) {
        list.add(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(this.consumption));
        return list;
    }
}