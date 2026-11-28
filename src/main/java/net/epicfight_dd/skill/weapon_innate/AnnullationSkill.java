package net.epicfight_dd.skill.weapon_innate;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import net.epicfight_dd.EpicFightDawnDay;
import net.epicfight_dd.skill.DawnDaySkillDataKeys;
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

@Mod.EventBusSubscriber(modid = EpicFightDawnDay.MODID)
public class AnnullationSkill extends WeaponInnateSkill {
    private static final String COOLDOWN_UNTIL = "epicfight_dd.annullation.cooldown_until";

    private static final Map<UUID, SkillContainer> CONTAINERS = new ConcurrentHashMap<>();
    private static final Map<UUID, Long> LAST_REACTION = new ConcurrentHashMap<>();

    private static final UUID CONSUME_EVENT_UUID = UUID.fromString("b8e41f27-3c6a-4d95-a0e2-7f19c5d84a36");
    private static final UUID ATTACK_EVENT_UUID = UUID.fromString("6a2d9c41-8e17-4b53-a0f6-3c5e7d1b9a82");

    private static final int PARTICLE_COUNT = 120;
    private static final double MIN_RADIUS = 1.5D;
    private static final double MAX_RADIUS = 3.5D;

    private static final double SHIELD_RADIUS = 1.2D;
    private static final int SHIELD_INTERVAL_TICKS = 3;
    private static final int SHIELD_PARTICLES = 5;
    private static final int HIT_PARTICLES = 24;
    private static final double HIT_SPEED = 0.25D;
    private static final int REACTION_COOLDOWN_TICKS = 5;

    private static final int TEXT_OFFSET_X = 12;
    private static final int TEXT_OFFSET_Y = 15;

    private static volatile int durationTicks = 300;
    private static volatile int cooldownTicks = 2400;

    public AnnullationSkill(SkillBuilder<? extends WeaponInnateSkill> builder) {
        super(builder);
    }

    private static int remaining(SkillContainer container) {
        Integer value = container.getDataManager().getDataValue(DawnDaySkillDataKeys.ANNULLATION_TICKS.get());
        return value == null ? 0 : value;
    }

    private static int cooldown(SkillContainer container) {
        Integer value = container.getDataManager().getDataValue(DawnDaySkillDataKeys.ANNULLATION_COOLDOWN.get());
        return value == null ? 0 : value;
    }

    private static void storeTimer(SkillContainer container, int value, boolean sync) {
        if (sync) {
            container.getDataManager().setDataSync(DawnDaySkillDataKeys.ANNULLATION_TICKS.get(), value);
        } else {
            container.getDataManager().setData(DawnDaySkillDataKeys.ANNULLATION_TICKS.get(), value);
        }
    }

    private static void storeCooldown(SkillContainer container, int value, boolean sync) {
        if (sync) {
            container.getDataManager().setDataSync(DawnDaySkillDataKeys.ANNULLATION_COOLDOWN.get(), value);
        } else {
            container.getDataManager().setData(DawnDaySkillDataKeys.ANNULLATION_COOLDOWN.get(), value);
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

        PlayerEventListener listener = container.getExecutor().getEventListener();

        listener.addEventListener(
                PlayerEventListener.EventType.SKILL_CONSUME_EVENT,
                CONSUME_EVENT_UUID,
                event -> {
                    if (event.getSkill() == this) {
                        event.setResourceType(Skill.Resource.NONE);
                    }
                });

        listener.addEventListener(
                PlayerEventListener.EventType.TAKE_DAMAGE_EVENT_ATTACK,
                ATTACK_EVENT_UUID,
                event -> {
                    if (remaining(container) <= 0) return;
                    if (event.getDamageSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;

                    event.setCanceled(true);
                    if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
                        react(player);
                    }
                });

        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            CONTAINERS.put(player.getUUID(), container);
            storeTimer(container, 0, false);
            storeCooldown(container, remainingCooldown(player), false);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        PlayerEventListener listener = container.getExecutor().getEventListener();
        listener.removeListener(PlayerEventListener.EventType.SKILL_CONSUME_EVENT, CONSUME_EVENT_UUID);
        listener.removeListener(PlayerEventListener.EventType.TAKE_DAMAGE_EVENT_ATTACK, ATTACK_EVENT_UUID);

        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            if (remaining(container) > 0) {
                writeCooldown(player);
            }
            CONTAINERS.remove(player.getUUID());
            LAST_REACTION.remove(player.getUUID());
            storeTimer(container, 0, player.connection != null);
        }
        super.onRemoved(container);
    }

    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && isAnnulled(player)) {
            writeCooldown(player);
        }
        CONTAINERS.remove(event.getEntity().getUUID());
        LAST_REACTION.remove(event.getEntity().getUUID());
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

    private static void spawnShield(ServerPlayer player, int count, double outwardSpeed) {
        if (!(player.level() instanceof ServerLevel level)) return;

        RandomSource random = player.getRandom();
        double cx = player.getX();
        double cy = player.getY() + player.getBbHeight() * 0.5D;
        double cz = player.getZ();

        for (int i = 0; i < count; i++) {
            double theta = random.nextDouble() * Math.PI * 2.0D;
            double cosPhi = 2.0D * random.nextDouble() - 1.0D;
            double sinPhi = Math.sqrt(1.0D - cosPhi * cosPhi);

            double ux = sinPhi * Math.cos(theta);
            double uy = cosPhi;
            double uz = sinPhi * Math.sin(theta);

            level.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                    cx + ux * SHIELD_RADIUS, cy + uy * SHIELD_RADIUS * 0.9D, cz + uz * SHIELD_RADIUS,
                    0, ux, uy, uz, outwardSpeed);
        }
    }

    private static void react(ServerPlayer player) {
        long now = player.level().getGameTime();
        Long last = LAST_REACTION.get(player.getUUID());
        if (last != null && now - last < REACTION_COOLDOWN_TICKS) return;
        LAST_REACTION.put(player.getUUID(), now);

        spawnShield(player, HIT_PARTICLES, HIT_SPEED);

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1.2F, 1.6F);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 0.25F, 2.0F);
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
                if (player.tickCount % SHIELD_INTERVAL_TICKS == 0) {
                    spawnShield(player, SHIELD_PARTICLES, 0.0D);
                }

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
        react(player);
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