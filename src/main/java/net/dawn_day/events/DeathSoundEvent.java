package net.dawn_day.events;

import net.dawn_day.DawnDayConfig;
import net.dawn_day.registry.entries.DawnDaySounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class DeathSoundEvent {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {

        LivingEntity entity = event.getEntity();

        if (!entity.level().isClientSide) {

            if (DawnDayConfig.ENABLE_DEATH_SOUND.get().equals(true)) {

                int random = entity.level().random.nextInt(3);

                SoundEvent sound;

                switch (random) {
                    case 0 -> sound = DawnDaySounds.KILLED2.get();
                    case 1 -> sound = DawnDaySounds.KILLED3.get();
                    default -> sound = DawnDaySounds.KILLED1.get();
                }

                entity.level().playSound(
                        null,
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        sound,
                        SoundSource.PLAYERS,
                        1.0f,
                        1.0f
                );
            }
        }
    }
}

