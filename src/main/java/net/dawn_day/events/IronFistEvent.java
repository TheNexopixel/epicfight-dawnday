package net.dawn_day.events;


import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.registry.entries.DawnDayItems;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = EpicFightDawnDay.MOD_ID)
public class IronFistEvent {
    @SubscribeEvent
    public static void onLiving(LivingDamageEvent.Pre event) {

        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }

        if (!player.getMainHandItem().is(DawnDayItems.IRON_FIST.get())) {
            return;
        }

        float damage = event.getNewDamage();

        if (!player.getOffhandItem().is(DawnDayItems.IRON_FIST.get())) {
            event.setNewDamage(damage - 1.0F);
        } else {
            event.setNewDamage(damage + 1.0F);
        }
    }
}
