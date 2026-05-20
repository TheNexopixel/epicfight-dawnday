package net.epicfight_dd.events;

import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.gameasset.dawnDaySounds;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
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

        if (event.getEffectInstance().getEffect()
                != EffectRegistry.DRAINDED.get()) {
            return;
        }

        entity.level().playSound(
                null,
                entity.getX(),
                entity.getY(),
                entity.getZ(),
                dawnDaySounds.DRAINED.get(),
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

}
