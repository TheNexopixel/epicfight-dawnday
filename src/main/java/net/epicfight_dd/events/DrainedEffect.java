package net.epicfight_dd.events;

import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.gameasset.DawnDaySounds;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Vector3f;

@Mod.EventBusSubscriber
public class DrainedEffect {
    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {

        LivingEntity entity = event.getEntity();
        if (!(entity instanceof ServerPlayer)) {
            return;
        }

        if (event.getEffectInstance().getEffect()
                == EffectRegistry.DRAINDED.get()) {



        entity.level().playSound(
                null,
                entity.getX(),
                entity.getY(),
                entity.getZ(),
                DawnDaySounds.DRAINED.get(),
                SoundSource.PLAYERS,
                1.5f,
                1.2f
        );
        if (!entity.level().isClientSide) {

            ServerLevel level = (ServerLevel) entity.level();

            for (int i = 0; i < 35; i++) {

                float gray = 0.1f + level.random.nextFloat() * 0.25f;
                float scale = 0.7f + level.random.nextFloat() * 1.2f;

                level.sendParticles(
                        new DustParticleOptions(
                                new Vector3f(gray, gray, gray),
                                scale
                        ),
                        entity.getX(),
                        entity.getY() + 1.0,
                        entity.getZ(),
                        1,
                        0.4,
                        0.5,
                        0.4,
                        0.02
                );
            }
        }
    }
        if (event.getEffectInstance().getEffect()
                == EffectRegistry.CURSED.get()) {

            int random = entity.level().random.nextInt(2);
            SoundEvent  sound;

            if (random == 0) {
                sound = DawnDaySounds.CURSED1.get();
            } else {
                sound = DawnDaySounds.CURSED2.get();
            }

            entity.level().playSound(
                    null,
                    entity.getX(),
                    entity.getY(),
                    entity.getZ(),
                    sound,
                    SoundSource.PLAYERS,
                    0.7f,
                    0.9f
            );
            if (!entity.level().isClientSide) {

                ServerLevel level = (ServerLevel) entity.level();

                for (int i = 0; i < 35; i++) {

                    float gray = 0.1f + level.random.nextFloat() * 0.25f;
                    float scale = 0.7f + level.random.nextFloat() * 1.2f;

                    level.sendParticles(
                            new DustParticleOptions(
                                    new Vector3f(gray, gray, gray),
                                    scale
                            ),
                            entity.getX(),
                            entity.getY() + 1.0,
                            entity.getZ(),
                            1,
                            0.4,
                            0.5,
                            0.4,
                            -0.12
                    );
                }
            }
        }

    }

}
