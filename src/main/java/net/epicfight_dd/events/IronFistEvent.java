package net.epicfight_dd.events;

import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class IronFistEvent {
    @SubscribeEvent
    public static void onLiving(LivingHurtEvent event) {

        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }

        Item mainHand = player.getMainHandItem().getItem();
        Item offHand = player.getOffhandItem().getItem();

        if (mainHand != DawnDayItems.IRON_FIST.get()) {
            return;
        }

        float damage = event.getAmount();

        if (offHand != DawnDayItems.IRON_FIST.get()) {
            event.setAmount(damage - 2.0f);
        }
    }
}
