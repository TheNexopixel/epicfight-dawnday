package net.dawn_day;

import com.hm.efn.EFN;
import com.mojang.logging.LogUtils;
import net.dawn_day.client.input.DawnDayKeyMappings;
import net.dawn_day.events.ModBusEvent;
import net.dawn_day.registry.DawnDayRegistry;
import net.dawn_day.skill.skill_compats.NightfallCompat;
import net.dawn_day.world.capabilities.item.DawnDayWeaponCategories;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import org.slf4j.Logger;
import reascer.wom.main.WeaponsOfMinecraft;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.main.EpicFightSharedConstants;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.nio.file.Path;
import java.util.Optional;


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

        if(ModList.get().isLoaded(EFN.MODID)){
            ICompatModule.loadCompatModule(bus, NightfallCompat.class);
        }

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
    /*
    public void addPackFindersEvent(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {

            Path resourcePath = ModList.get().getModFileById(EpicFightDawnDay.MOD_ID).getFile().findResource("packs/Slam High Pitch");

            PackLocationInfo packLocation = new PackLocationInfo("Slam High Pitch", Component.translatable("pack.Slam High Pitch.title"), PackSource.BUILT_IN, Optional.empty());
            addPack(event, resourcePath, packLocation);

            Path WEAPON3D = ModList.get().getModFileById(EpicFightDawnDay.MOD_ID).getFile().findResource("packs/DawnDay3D Weapon Pack");
            PackLocationInfo dd3d = new PackLocationInfo("DawnDay3D Weapon Pack", Component.translatable("pack.DawnDay3D Weapon Pack.title"), PackSource.BUILT_IN, Optional.empty());
            addPack(event, WEAPON3D, dd3d);


        }

    }
    private void addPack(AddPackFindersEvent event, Path WEAPON3D, PackLocationInfo dd3d) {
        Pack.ResourcesSupplier dd3ds = new PathPackResources.PathResourcesSupplier(WEAPON3D);
        Pack DawnDay3DWeaponPack = Pack.readMetaAndCreate(dd3d, dd3ds, PackType.CLIENT_RESOURCES, new PackSelectionConfig(false, Pack.Position.TOP, false));

        if (DawnDay3DWeaponPack != null) {
            event.addRepositorySource(source -> source.accept(DawnDay3DWeaponPack));
        }


    }

     */
}
