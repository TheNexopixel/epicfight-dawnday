package net.epicfight_dd.events;

import net.epicfight_dd.effect.EffectRegistry;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Vector3f;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mod.EventBusSubscriber
public class RitusEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {


        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }


        if (player.getMainHandItem().getItem() != DawnDayItems.NIGHT_RITUS_DAGGER.get()) {
            return;
        }

        Level level = player.level();


        if (level.isNight()) {


            float damage = event.getAmount();

            event.setAmount(damage + 3.0f);
        }
    }
    @SubscribeEvent
    public static void onSeppuku(LivingHurtEvent event) {


        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }


        if (player.getMainHandItem().getItem() != DawnDayItems.BLOOD_RITUS_DAGGER.get()) {
            return;
        }

        if (player.hasEffect(EffectRegistry.SEPUKKU.get())) {


            float damage = event.getAmount();

            event.setAmount(damage + 2.0f);
        }
    }
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {

        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }


        // BLOOD DAGGER


        if (player.getMainHandItem().getItem()
                == DawnDayItems.BLOOD_RITUS_DAGGER.get()) {

            if(player.hasEffect(EffectRegistry.SEPUKKU.get())) {
                player.heal(2.0f);
            }
            if(player.hasEffect(EffectRegistry.DRAINDED.get())) {
                player.heal(0.5f);
            }
            else {
                player.heal(1.0f);
            }



            if (!player.level().isClientSide) {

                ServerLevel level = (ServerLevel) player.level();

                level.sendParticles(
                        new DustParticleOptions(
                                new Vector3f(1.0f, 0.0f, 0.0f),
                                1.2f
                        ),
                        player.getX(),
                        player.getY() + 1.0,
                        player.getZ(),
                        15,
                        0.3,
                        0.5,
                        0.3,
                        0.01
                );
            }
        }


        // NIGHT DAGGER


        if (player.getMainHandItem().getItem()
                == DawnDayItems.NIGHT_RITUS_DAGGER.get()) {

            PlayerPatch<?> playerPatch =
                    EpicFightCapabilities.getEntityPatch(player, PlayerPatch.class);

            if (playerPatch != null) {

                float stamina = playerPatch.getStamina();

                if (player.level().isNight()) {
                    playerPatch.setStamina(stamina + 1.0f);
                }
                if (player.hasEffect(EffectRegistry.SEPUKKU.get())
                && player.level().isNight()) {
                    playerPatch.setStamina(stamina + 4.0f);
                }
                else {
                    playerPatch.setStamina(stamina + 0.5f);
                }
            }

            if (!player.level().isClientSide) {

                ServerLevel level = (ServerLevel) player.level();

                level.sendParticles(
                        new DustParticleOptions(
                                new Vector3f(0.2f, 0.4f, 1.0f),
                                1.2f
                        ),
                        player.getX(),
                        player.getY() + 1.0,
                        player.getZ(),
                        15,
                        0.3,
                        0.5,
                        0.3,
                        0.01
                );
            }
        }
    }
}
