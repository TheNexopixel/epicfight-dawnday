package net.dawn_day;

import com.mojang.logging.LogUtils;
import net.dawn_day.client.input.DawnDayKeyMappings;
import net.dawn_day.events.ModBusEvent;
import net.dawn_day.registry.DawnDayRegistry;
import net.dawn_day.world.capabilities.item.DawnDayWeaponCategories;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import yesman.epicfight.world.capabilities.item.WeaponCategory;


@Mod(EpicFightDawnDay.MOD_ID)
public class EpicFightDawnDay {

    public static final String MOD_ID = "epicfight_dd";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ResourceLocation identifier(String name)
    {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
    public EpicFightDawnDay(IEventBus bus, ModContainer modContainer) {
        bus.addListener(ModBusEvent::registerAnimation);
   //nexo pls import the required import and build
    if (EpicFightSharedConstants.isPhysicalClient()) {
         bus.addListener(DawnDayKeyMappings::registerKeys);
    }
        
        DawnDayRegistry.REGISTERS.forEach(deferredRegister -> deferredRegister.register(bus));
        WeaponCategory.ENUM_MANAGER.registerEnumCls(EpicFightDawnDay.MOD_ID, DawnDayWeaponCategories.class);
        modContainer.registerConfig(
                ModConfig.Type.COMMON,
                DawnDayConfig.SPEC
        );
        bus.addListener(this::commonSetup);
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}
