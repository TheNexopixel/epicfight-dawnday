package net.epicfight_dd;

import java.nio.file.Path;
import java.util.Arrays;

import net.epicfight_dd.compat.DawnDayCompatibilities;
import net.epicfight_dd.config.DawnDayConfigs;
import net.epicfight_dd.gameasset.DawnDayRegisters;
import net.epicfight_dd.network.DDNetworkHandler;
import net.epicfight_dd.skill.passive.CortisolSkill;
import net.epicfight_dd.skill.passive.unstoppable.UnstoppableEvents;
import net.epicfight_dd.skill.passive.unstoppable.UnstoppableSkill;
import net.epicfight_dd.world.capabilities.item.DawnDayWeaponCategories;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.minecraftforge.resource.PathPackResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

@Mod(EpicFightDawnDay.MODID)
public class EpicFightDawnDay {
    public static final String MODID = "epicfight_dd";
    public static final Logger LOGGER = LogManager.getLogger("EpicFightDawnDay");

    public EpicFightDawnDay(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();

        DawnDayConfigs.register(context);
        DawnDayRegisters.registerAll(bus);

        WeaponCategory.ENUM_MANAGER.registerEnumCls(MODID, DawnDayWeaponCategories.class);

        bus.addListener(this::addPackFindersEvent);
        bus.addListener(this::commonSetup);

        this.loadCompatibilityModules(context);
    }

    private void loadCompatibilityModules(FMLJavaModLoadingContext context) {
        for (Class<? extends ICompatModule> compatCls : this.getDawnDawCompatModules()) {
            ICompatModule.loadCompatModule(context, compatCls);
        }
    }

    private Class<? extends ICompatModule>[] getDawnDawCompatModules() {
        return Arrays.stream(DawnDayCompatibilities.values())
                .filter(compatModule -> ModList.get().isLoaded(compatModule.getModId()))
                .map(DawnDayCompatibilities::getModuleClass)
                .<Class<? extends ICompatModule>>toArray(Class[]::new);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(DDNetworkHandler::register);
    }

    private void addPackFindersEvent(AddPackFindersEvent event) {
        if (event.getPackType() != PackType.CLIENT_RESOURCES) return;

        addBuiltInPack(event, "Slam High Pitch", Pack.Position.BOTTOM);
        addBuiltInPack(event, "16xTrails", Pack.Position.BOTTOM);
        addBuiltInPack(event, "DawnDay3D Weapon Pack", Pack.Position.TOP);
    }

    private static void addBuiltInPack(AddPackFindersEvent event, String name, Pack.Position position) {
        ModFile modFile = (ModFile) ModList.get().getModFileById(MODID).getFile();
        Path resourcePath = modFile.findResource("packs/" + name);

        PathPackResources pack = new PathPackResources(modFile.getFileName() + ":" + resourcePath, false, resourcePath);
        Pack.ResourcesSupplier supplier = string -> pack;
        Pack.Info info = Pack.readPackInfo(name, supplier);
        if (info == null) return;

        event.addRepositorySource(consumer -> consumer.accept(Pack.create(
                name,
                Component.translatable("pack." + name + ".title"),
                false,
                supplier,
                info,
                PackType.CLIENT_RESOURCES,
                position,
                false,
                PackSource.BUILT_IN)));
    }
}