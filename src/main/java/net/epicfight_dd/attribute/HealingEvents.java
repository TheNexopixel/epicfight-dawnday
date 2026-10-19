package net.epicfight_dd.attribute;

import net.epicfight_dd.Epicfight_dd;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID)
public final class HealingEvents {
    private HealingEvents() {
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void heal(LivingHealEvent event) {
        if (event.getEntity().level().isClientSide || !(event.getAmount() > 0)) {
            return;
        }
        var attribute = event.getEntity().getAttribute(DawnDayAttributes.HEALING.get());
        if (attribute == null) {
            return;
        }
        double amount = event.getAmount() * attribute.getValue();
        event.setAmount((float) Math.min(Float.MAX_VALUE, Math.max(0, amount)));
    }
}
