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
        int moonPhase = player.level().getMoonPhase();
        float bonus;
        Level level = player.level();

        if (level.isNight()) {
        switch (moonPhase) {

            case 0 -> bonus = 5.0f; // = full moon
            case 1, 7 -> bonus = 3.5f;
            case 2, 6 -> bonus = 2.5f;
            case 3, 5 -> bonus = 1.0f;
            default -> bonus = 0.0f;
        }
        event.setAmount(event.getAmount() + bonus);
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

        LivingEntity entity = event.getEntity();


        if (player.getMainHandItem().getItem()
                == DawnDayItems.BLOOD_RITUS_DAGGER.get() || player.getMainHandItem().getItem()
                == DawnDayItems.POLEBLADE.get()) {

            if (entity != null) {
                double max_health_bonus = entity.getMaxHealth() * 0.05;



            if (event.getEntity() instanceof Player) {
                player.heal ((float) (max_health_bonus + 10.0F));
            }
            if (event.getEntity() instanceof Player && (player.hasEffect(EffectRegistry.SEPUKKU.get()) )) {
                player.heal ((float) (max_health_bonus + 20.0F));
            }

            if(player.hasEffect(EffectRegistry.SEPUKKU.get())) {
                player.heal((float) (max_health_bonus * 2));
            }

            if(player.hasEffect(EffectRegistry.DRAINDED.get())) {
                player.heal((float) (max_health_bonus / 2));
            }
            else {
                player.heal((float) max_health_bonus);
            }
            if (player.hasEffect(EffectRegistry.DRAINDED.get())) {
                if (!player.level().isClientSide) {

                    ServerLevel level = (ServerLevel) player.level();

                    level.sendParticles(
                            new DustParticleOptions(
                                    new Vector3f(1.0f, 1.0f, 1.0f),
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
                player.removeEffect(EffectRegistry.DRAINDED.get());
            }
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

            float stamina = playerPatch.getStamina();
            float maxstamina = playerPatch.getMaxStamina();

            if (event.getEntity() instanceof Player) {
                playerPatch.setStamina(stamina + maxstamina / 2);
            }
            if (event.getEntity() instanceof Player && (player.hasEffect(EffectRegistry.SEPUKKU.get()) )) {
                playerPatch.setStamina(stamina + maxstamina);
            }


            if (player.level().isNight()) {
                playerPatch.setStamina(stamina + 3.0f);
            }
            if (player.hasEffect(EffectRegistry.SEPUKKU.get())
                    && player.level().isNight()) {
                playerPatch.setStamina(stamina + 6.0f);
            } else {
                playerPatch.setStamina(stamina + 1.5f);
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

