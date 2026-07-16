package net.epicfight_dd.events;

import net.epicfight_dd.DawnDayConfig;
import net.epicfight_dd.gameasset.DawnDaySounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
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

