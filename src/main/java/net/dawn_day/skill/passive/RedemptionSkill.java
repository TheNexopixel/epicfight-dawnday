package net.dawn_day.skill.passive;


import net.dawn_day.registry.entries.DawnDaySounds;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.joml.Vector3f;

import yesman.epicfight.api.event.EntityEventListener;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.registry.entries.EpicFightMobEffects;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;


import java.util.ArrayList;
import java.util.List;

public class RedemptionSkill extends PassiveSkill {


    public RedemptionSkill(SkillBuilder<?> builder) {
        super(builder);
    }


    @Override
    public float getCooldownRegenPerSecond(PlayerPatch<?> playerpatch) {
        return 1.0F;
    }

    @Override
    public void onInitiate(SkillContainer container, EntityEventListener eventListener) {
        super.onInitiate(container, eventListener);



        eventListener.registerEvent(
                EpicFightEventHooks.Entity.TAKE_DAMAGE_POST,
                event -> {

                    PlayerPatch<?> playerPatch = container.getExecutor();
                    Player player = playerPatch.getOriginal();

                    if (container.getStack() <= 0) {
                        return;
                    }
                    float currentHealth = player.getHealth();

                    if (currentHealth > 6) {
                        return;
                    }

                    activateRedemption(container, player, event.getDamageSource());
                },this
        );

    }

    private void activateRedemption(
            SkillContainer container,
            Player player,
            DamageSource source
    ) {
        Skill.setSkillStackSynchronize(container, 0);



        List<MobEffectInstance> harmfulEffects = new ArrayList<>();

        for (MobEffectInstance effect : player.getActiveEffects()) {

            if (effect.getEffect().value().getCategory()
                    == MobEffectCategory.HARMFUL) {

                harmfulEffects.add(effect);
            }
        }

        boolean transferredEffect = false;

        if (source.getEntity() instanceof LivingEntity attacker) {

            for (MobEffectInstance effect : harmfulEffects) {

                attacker.addEffect(
                        new MobEffectInstance(
                                effect.getEffect(),
                                effect.getDuration(),
                                effect.getAmplifier()
                        )
                );

                transferredEffect = true;
            }

            if (transferredEffect) {

                attacker.level().playSound(
                        null,
                        attacker.getX(),
                        attacker.getY(),
                        attacker.getZ(),
                        DawnDaySounds.CURSED1.get(),
                        attacker.getSoundSource(),
                        1.0F,
                        0.75F
                );

                if (!attacker.level().isClientSide()) {

                    ServerLevel level = (ServerLevel) attacker.level();

                    for (int i = 0; i < 40; i++) {

                        float gray = 0.05F + level.random.nextFloat() * 0.15F;
                        float scale = 0.7F + level.random.nextFloat() * 1.3F;

                        level.sendParticles(
                                new DustParticleOptions(
                                        new Vector3f(gray, gray, gray),
                                        scale
                                ),
                                attacker.getX(),
                                attacker.getY() + 1.0D,
                                attacker.getZ(),
                                1,
                                0.5D,
                                0.7D,
                                0.5D,
                                -0.08D
                        );
                    }
                }
            }
        }
        for (MobEffectInstance effect : harmfulEffects) {
            player.removeEffect(effect.getEffect());
        }
        player.addEffect(
                new MobEffectInstance(
                        MobEffects.ABSORPTION,
                        400,
                        2
                )
        );
        PlayerPatch<?> playerPatch =
                EpicFightCapabilities.getEntityPatch(player, PlayerPatch.class);
        assert playerPatch != null;
        float stamina = playerPatch.getStamina();
        float maxstamina = playerPatch.getMaxStamina();

        playerPatch.setStamina(stamina + maxstamina /2);



        player.addEffect(
                new MobEffectInstance(
                        MobEffects.REGENERATION,
                        150,
                        1
                )
        );
        player.addEffect(
                new MobEffectInstance(
                        MobEffects.DAMAGE_RESISTANCE,
                        350,
                        1
                )
        );

        player.addEffect(
                new MobEffectInstance(
                        EpicFightMobEffects.STUN_IMMUNITY,
                        100,
                        3
                )
        );
        player.level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.RESPAWN_ANCHOR_CHARGE,
                player.getSoundSource(),
                0.8F,
                1.2F
        );

        player.level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.TOTEM_USE,
                player.getSoundSource(),
                1.0F,
                0.65F
        );
        player.level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                DawnDaySounds.CURSED2.get(), //TODO: NEXO CHANGE THE SOUND BACK TO REDEMTPION
                player.getSoundSource(),
                1.5F,
                1.0F
        );

        if (!player.level().isClientSide()) {

            ServerLevel level = (ServerLevel) player.level();



            spawnTotemSphere(level, player, 3.0D, 60);
            spawnTotemSphere(level, player, 4.5D, 100);
            spawnTotemSphere(level, player, 7.0D, 350);


            for (int i = 0; i < 50; i++) {

                float scale = 0.8F + level.random.nextFloat();

                level.sendParticles(
                        new DustParticleOptions(
                                new Vector3f(0.85F, 0.0F, 0.0F),
                                scale
                        ),
                        player.getX(),
                        player.getY() + 1.0D,
                        player.getZ(),
                        1,
                        0.8D,
                        1.2D,
                        0.8D,
                        0.02D
                );
            }
        }
    }


    @Override
    public boolean shouldDraw(SkillContainer container) {
        return container.getStack() <= 0;
    }


    @Override
    public void loadDatapackParameters(CompoundTag parameters) {
        super.loadDatapackParameters(parameters);
        this.consumption = parameters.getFloat("consumption");
    }

    @Override
    public void drawOnGui(
            BattleModeGui gui,
            SkillContainer container,
            GuiGraphics guiGraphics,
            float x,
            float y,
            float partialTick
    ) {
        guiGraphics.blit(
                this.getSkillTexture(),
                (int)x,
                (int)y,
                24,
                24,
                0,
                0,
                1,
                1,
                1,
                1
        );

        float resource = container.getResource(partialTick);

        int seconds =
                (int)Math.ceil(
                        (1.0F - resource) * consumption
                );

        guiGraphics.drawString(
                gui.getFont(),
                String.valueOf(seconds),
                x + 18,
                y + 14,
                16777215,
                true
        );
    }
    private static void spawnTotemSphere(
            ServerLevel level,
            LivingEntity player,
            double radius,
            int particles
    ) {
        for (int i = 0; i < particles; i++) {

            double theta = level.random.nextDouble() * Math.PI * 2.0D;
            double phi = Math.acos(2.0D * level.random.nextDouble() - 1.0D);

            double x = player.getX() + radius * Math.sin(phi) * Math.cos(theta);
            double y = player.getY() + 1.0D + radius * Math.cos(phi);
            double z = player.getZ() + radius * Math.sin(phi) * Math.sin(theta);

            double dx = player.getX() - x;
            double dy = (player.getY() + 1.0D) - y;
            double dz = player.getZ() - z;

            double length = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (length > 0.0D) {
                dx /= length;
                dy /= length;
                dz /= length;
            }

            double speed = 1.1D;

            level.sendParticles(
                    ParticleTypes.SOUL,
                    x,
                    y,
                    z,
                    0,
                    dx * speed,
                    dy * speed,
                    dz * speed,
                    1.0D
            );
        }
    }
}