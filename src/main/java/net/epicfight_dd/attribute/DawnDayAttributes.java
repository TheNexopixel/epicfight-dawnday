package net.epicfight_dd.attribute;

import net.epicfight_dd.EpicFightDawnDay;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = EpicFightDawnDay.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DawnDayAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, EpicFightDawnDay.MODID);
    private static final List<Attribute> REGISTERED_ATTRIBUTES = new ArrayList<>();

    public static final RegistryObject<Attribute> HEALING = register("healing", new RangedAttribute("attribute.name.epicfight_dd.healing", 1, 0, 1024).setSyncable(true));

    private DawnDayAttributes() {}

    private static <T extends Attribute> RegistryObject<Attribute> register(String name, T attribute) {
        REGISTERED_ATTRIBUTES.add(attribute);
        return ATTRIBUTES.register(name, () -> attribute);
    }

    @SubscribeEvent
    public static void onAttributeModify(EntityAttributeModificationEvent event) {
        for (var type : event.getTypes()) {
            for (Attribute attribute : REGISTERED_ATTRIBUTES) {
                if (!event.has(type, attribute)) {
                    event.add(type, attribute);
                }
            }
        }
    }
}
