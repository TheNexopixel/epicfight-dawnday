package net.epicfight_dd.attribute;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DawnDayAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Epicfight_dd.MODID);
    public static final RegistryObject<Attribute> HEALING = ATTRIBUTES.register("healing", () ->
            new RangedAttribute("attribute.name.epicfight_dd.healing", 1, 0, 1024).setSyncable(true));

    private DawnDayAttributes() {
    }

    @SubscribeEvent
    public static void attributes(EntityAttributeModificationEvent event) {
        for (var type : event.getTypes()) {
            if (!event.has(type, HEALING.get())) {
                event.add(type, HEALING.get());
            }
        }
    }
}
