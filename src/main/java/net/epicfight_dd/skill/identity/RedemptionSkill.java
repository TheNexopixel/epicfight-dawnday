package net.epicfight_dd.skill.identity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.epicfight_dd.gameasset.DawnDaySounds;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.effect.EpicFightMobEffects;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

// THIS CODE WAS MADE MOSTLY BY AI
// gez. Nexo

public class RedemptionSkill extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("8b6b6af0-d76d-11ee-a506-0242ac120002");

    private static final int SPHERE_SMALL = 60;
    private static final int SPHERE_MEDIUM = 100;
    private static final int SPHERE_LARGE = 350;

    private float triggerHealth = 6.0F;
    private float staminaFraction = 0.5F;

    public RedemptionSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    @Override
    public float getCooldownRegenPerSecond(PlayerPatch<?> playerpatch) {
        return 1.0F;
    }

    // ---------- Lifecycle ----------

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(
                EventType.TAKE_DAMAGE_EVENT_HURT,
                EVENT_UUID,
                event -> {
                    if (!(container.getExecutor().getOriginal() instanceof ServerPlayer player)) return;
                    if (container.getStack() <= 0) return;
                    if (player.getHealth() > this.triggerHealth) return;

                    activateRedemption(container, container.getExecutor(), player, event.getDamageSource());
                }
        );
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
        container.getExecutor().getEventListener().removeListener(EventType.TAKE_DAMAGE_EVENT_HURT, EVENT_UUID);
    }

    // ---------- Parameter ----------

    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
        this.consumption = parameters.getFloat("consumption");
        this.triggerHealth = parameters.contains("trigger_health") ? parameters.getFloat("trigger_health") : 6.0F;
        this.staminaFraction = parameters.contains("stamina_fraction") ? parameters.getFloat("stamina_fraction") : 0.5F;
    }

    // ---------- Effekt ----------

    private void activateRedemption(SkillContainer container, PlayerPatch<?> patch,
                                    ServerPlayer player, DamageSource source) {
        Skill.setSkillStackSynchronize(container, 0);

        ServerLevel level = player.serverLevel();

        List<MobEffectInstance> harmful = new ArrayList<>();
        for (MobEffectInstance effect : player.getActiveEffects()) {
            if (effect.getEffect().getCategory() == MobEffectCategory.HARMFUL) {
                harmful.add(effect);
            }
        }

        if (source.getEntity() instanceof LivingEntity attacker && !harmful.isEmpty()) {
            for (MobEffectInstance effect : harmful) {
                attacker.addEffect(new MobEffectInstance(effect), player);
            }
            attacker.level().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                    DawnDaySounds.CURSED1.get(), attacker.getSoundSource(), 1.0F, 0.75F);
            spawnCurseDust((ServerLevel) attacker.level(), attacker);
        }

        for (MobEffectInstance effect : harmful) {
            player.removeEffect(effect.getEffect());

        }

        // Buffs
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 400, 2));
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 150, 1));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 350, 1));
        player.addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 100, 3));

        float max = patch.getMaxStamina();
        patch.setStamina(Math.min(max, patch.getStamina() + max * this.staminaFraction));

        double x = player.getX(), y = player.getY(), z = player.getZ();
        level.playSound(null, x, y, z, SoundEvents.RESPAWN_ANCHOR_CHARGE, player.getSoundSource(), 0.8F, 1.2F);
        level.playSound(null, x, y, z, SoundEvents.TOTEM_USE, player.getSoundSource(), 1.0F, 0.65F);
        level.playSound(null, x, y, z, DawnDaySounds.REDEMPTION.get(), player.getSoundSource(), 1.5F, 1.0F);

        spawnTotemSphere(level, player, 3.0D, SPHERE_SMALL);
        spawnTotemSphere(level, player, 4.5D, SPHERE_MEDIUM);
        spawnTotemSphere(level, player, 7.0D, SPHERE_LARGE);
        spawnRedDust(level, player);
    }

    private static void spawnCurseDust(ServerLevel level, LivingEntity attacker) {
        RandomSource random = level.random;
        for (int i = 0; i < 4; i++) {
            float gray = 0.05F + random.nextFloat() * 0.15F;
            float scale = 0.7F + random.nextFloat() * 1.3F;
            level.sendParticles(new DustParticleOptions(new Vector3f(gray, gray, gray), scale),
                    attacker.getX(), attacker.getY() + 1.0D, attacker.getZ(),
                    10, 0.5D, 0.7D, 0.5D, -0.08D);
        }
    }


    private static void spawnRedDust(ServerLevel level, LivingEntity player) {
        RandomSource random = level.random;
        for (int i = 0; i < 5; i++) {
            float scale = 0.8F + random.nextFloat();
            level.sendParticles(new DustParticleOptions(new Vector3f(0.85F, 0.0F, 0.0F), scale),
                    player.getX(), player.getY() + 1.0D, player.getZ(),
                    10, 0.8D, 1.2D, 0.8D, 0.02D);
        }
    }

    private static void spawnTotemSphere(ServerLevel level, LivingEntity player, double radius, int particles) {
        RandomSource random = level.random;
        double cx = player.getX();
        double cy = player.getY() + 1.0D;
        double cz = player.getZ();
        double speed = 1.1D;

        for (int i = 0; i < particles; i++) {
            double theta = random.nextDouble() * Math.PI * 2.0D;
            double cosPhi = 2.0D * random.nextDouble() - 1.0D;
            double sinPhi = Math.sqrt(1.0D - cosPhi * cosPhi);

            double ux = sinPhi * Math.cos(theta);
            double uy = cosPhi;
            double uz = sinPhi * Math.sin(theta);

            level.sendParticles(ParticleTypes.SOUL,
                    cx + radius * ux, cy + radius * uy, cz + radius * uz,
                    0, -ux * speed, -uy * speed, -uz * speed, 1.0D);
        }
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return container.getStack() <= 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics graphics,
                          float x, float y, float partialTick) {
        graphics.blit(this.getSkillTexture(), (int) x, (int) y, 24, 24, 0, 0, 1, 1, 1, 1);

        int seconds = (int) Math.ceil((1.0F - container.getResource(partialTick)) * this.consumption);
        String text = String.valueOf(seconds);
        graphics.drawString(gui.getFont(), text,
                (int) x + (24 - gui.getFont().width(text)) / 2, (int) y + 15, 0xFFFFFF, true);
    }
}