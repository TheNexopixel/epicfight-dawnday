package net.dawn_day.events;

import net.dawn_day.registry.entries.DawnDayEffects;
import net.dawn_day.registry.entries.DawnDaySounds;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.joml.Vector3f;

@EventBusSubscriber
public class DrainedEffect {
    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {

        LivingEntity entity = event.getEntity();

        if (event.getEffectInstance().getEffect()
                != DawnDayEffects.DRAINED.get()) {
            return;
        }

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

    @SubscribeEvent
    public static void onEffectExpire(MobEffectEvent.Expired event) {
        if (event.getEffectInstance().is(DawnDayEffects.SEPUKKU))
        {
            event.getEntity().addEffect(new MobEffectInstance(DawnDayEffects.DRAINED, 350, 0));
        }
    }

}
