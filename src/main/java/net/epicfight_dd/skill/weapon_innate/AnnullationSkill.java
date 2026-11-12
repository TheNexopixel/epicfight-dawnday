package net.epicfight_dd.skill.weapon_innate;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.network.EntityPairingPacketTypes;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPEntityPairingPacket;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

// CODE WRITTEN BY AI (gez. Nexo)

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID)
public class AnnullationSkill extends WeaponInnateSkill {
    private static final String COOLDOWN_UNTIL = "epicfight_dd.annullation.cooldown_until";

    private static final Map<UUID, SkillContainer> CONTAINERS = new ConcurrentHashMap<>();
    private static final UUID CONSUME_EVENT_UUID = UUID.fromString("b8e41f27-3c6a-4d95-a0e2-7f19c5d84a36");
    private static final int PARTICLE_COUNT = 120;
    private static final double MIN_RADIUS = 1.5D;
    private static final double MAX_RADIUS = 3.5D;

    private static final int TEXT_OFFSET_X = 12;
    private static final int TEXT_OFFSET_Y = 15;

    private static volatile int durationTicks = 300;
    private static volatile int cooldownTicks = 2400;

    public AnnullationSkill(SkillBuilder<? extends WeaponInnateSkill> builder) {
        super(builder);
    }

    private static int remaining(SkillContainer container) {
        return container.getDataManager().getDataValue(SkillDataKeyZ.ANNULLATION_TICKS.get());
    }

    private static int cooldown(SkillContainer container) {
        return container.getDataManager().getDataValue(SkillDataKeyZ.ANNULLATION_COOLDOWN.get());
    }


    private static void storeTimer(SkillContainer container, int value, boolean sync) {
        if (sync) {
            container.getDataManager().setDataSync(SkillDataKeyZ.ANNULLATION_TICKS.get(), value);
        } else {
            container.getDataManager().setData(SkillDataKeyZ.ANNULLATION_TICKS.get(), value);
        }
    }

    private static void storeCooldown(SkillContainer container, int value, boolean sync) {
        if (sync) {
            container.getDataManager().setDataSync(SkillDataKeyZ.ANNULLATION_COOLDOWN.get(), value);
        } else {
            container.getDataManager().setData(SkillDataKeyZ.ANNULLATION_COOLDOWN.get(), value);
        }
    }

    public static boolean isAnnulled(ServerPlayer player) {
        SkillContainer container = CONTAINERS.get(player.getUUID());
        return container != null && remaining(container) > 0;
    }

    private static int remainingCooldown(ServerPlayer player) {
        long left = player.getPersistentData().getLong(COOLDOWN_UNTIL) - player.level().getGameTime();
        return (int) Math.max(0L, left);
    }

    private static void writeCooldown(ServerPlayer player) {
        player.getPersistentData().putLong(COOLDOWN_UNTIL, player.level().getGameTime() + cooldownTicks);
    }


    @Override
    public void setParams(CompoundTag p) {
        super.setParams(p);
        durationTicks = p.contains("duration_ticks") ? p.getInt("duration_ticks") : 300;
        cooldownTicks = p.contains("cooldown_ticks") ? p.getInt("cooldown_ticks") : 2400;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.SKILL_CONSUME_EVENT,
                CONSUME_EVENT_UUID,
                event -> {
                    if (event.getSkill() == this) {
                        event.setResourceType(Skill.Resource.NONE);
                    }
                });

        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            CONTAINERS.put(player.getUUID(), container);
            storeTimer(container, 0, true);
            storeCooldown(container, remainingCooldown(player), true);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.SKILL_CONSUME_EVENT, CONSUME_EVENT_UUID);

        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            if (remaining(container) > 0) {
                writeCooldown(player);
            }
            CONTAINERS.remove(player.getUUID());
            storeTimer(container, 0, true);
        }
        super.onRemoved(container);
    }

    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && isAnnulled(player)) {
            writeCooldown(player);
        }
        CONTAINERS.remove(event.getEntity().getUUID());
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        SkillContainer container = CONTAINERS.get(player.getUUID());
        if (container != null && remaining(container) > 0) {
            endEffect(container, player, false);
        }
    }

    @Override
    public boolean checkExecuteCondition(SkillContainer container) {
        return remaining(container) <= 0
                && cooldown(container) <= 0
                && super.checkExecuteCondition(container);
    }

    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {
        ServerPlayer player = container.getServerExecutor().getOriginal();

        if (remainingCooldown(player) > 0 || remaining(container) > 0) return;

        storeTimer(container, durationTicks, true);

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, SoundSource.PLAYERS, 1.9F, 1.3F);
        spawnSuckIn(player);

        SPEntityPairingPacket packet =
                new SPEntityPairingPacket(player.getId(), EntityPairingPacketTypes.FLASH_WHITE);
        packet.getBuffer().writeInt(9);
        packet.getBuffer().writeInt(15);
        packet.getBuffer().writeInt(1);
        packet.getBuffer().writeBoolean(true);

        EpicFightNetworkManager.sendToAllPlayerTrackingThisEntityWithSelf(packet, player);

        super.executeOnServer(container, args);
    }

    private static void spawnSuckIn(ServerPlayer player) {
        if (!(player.level() instanceof ServerLevel level)) return;

        RandomSource random = player.getRandom();
        double cx = player.getX();
        double cy = player.getY() + player.getBbHeight() * 0.5D;
        double cz = player.getZ();

        for (int i = 0; i < PARTICLE_COUNT; i++) {
            double theta = random.nextDouble() * Math.PI * 2.0D;
            double cosPhi = 2.0D * random.nextDouble() - 1.0D;
            double sinPhi = Math.sqrt(1.0D - cosPhi * cosPhi);

            double ux = sinPhi * Math.cos(theta);
            double uy = cosPhi;
            double uz = sinPhi * Math.sin(theta);

            double radius = MIN_RADIUS + random.nextDouble() * (MAX_RADIUS - MIN_RADIUS);
            double speed = radius * 0.035D;

            level.sendParticles(ParticleTypes.CLOUD,
                    cx + ux * radius, cy + uy * radius, cz + uz * radius,
                    0, -ux, -uy, -uz, speed);
        }
    }

    private static void endEffect(SkillContainer container, ServerPlayer player, boolean playSound) {
        storeTimer(container, 0, true);
        writeCooldown(player);
        storeCooldown(container, remainingCooldown(player), true);

        if (playSound) {
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), SoundSource.PLAYERS, 1.2F, 0.7F);
        }
    }


    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);

        if (container.getExecutor().isLogicalClient()) {
            int left = remaining(container);
            if (left > 0) storeTimer(container, left - 1, false);
            int cd = cooldown(container);
            if (cd > 0) storeCooldown(container, cd - 1, false);
            return;
        }

        ServerPlayer player = container.getServerExecutor().getOriginal();

        if (container.getStack() < this.getMaxStack()) {
            Skill.setSkillStackSynchronize(container, this.getMaxStack());
        }

        int left = remaining(container);
        if (left > 0) {
            if (!player.isAlive()) {
                endEffect(container, player, false);
            } else {
                left--;
                if (left <= 0) {
                    endEffect(container, player, true);
                } else {
                    storeTimer(container, left, player.tickCount % 20 == 0);
                }
            }
        }
        int cd = remainingCooldown(player);
        int shown = cooldown(container);
        boolean changed = (cd == 0) != (shown == 0);
        storeCooldown(container, cd, changed || player.tickCount % 20 == 0);
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !isAnnulled(player)) return;
        if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;

        event.setCanceled(true);
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return remaining(container) > 0 || cooldown(container) > 0 || super.shouldDraw(container);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics graphics,
                          float x, float y, float partialTick) {
        int left = remaining(container);
        int cd = cooldown(container);

        if (left <= 0 && cd <= 0) {
            super.drawOnGui(gui, container, graphics, x, y, partialTick);
            return;
        }

        if (left > 0) {
            RenderSystem.setShaderColor(0.6F, 0.9F, 1.0F, 1.0F);
        } else {
            RenderSystem.setShaderColor(0.45F, 0.45F, 0.45F, 1.0F);
        }
        super.drawOnGui(gui, container, graphics, x, y, partialTick);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int ticks = left > 0 ? left : cd;
        String text = String.valueOf((ticks + 19) / 20);
        graphics.drawString(gui.getFont(), text,
                (int) x + TEXT_OFFSET_X - gui.getFont().width(text) / 2, (int) y + TEXT_OFFSET_Y, 0xFFFFFF, true);
    }
}