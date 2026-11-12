package net.epicfight_dd;

import java.nio.file.Path;

import com.hm.efn.EFN;
import net.epicfight_dd.gameasset.DawnDayRegisters;
import net.epicfight_dd.network.DDNetworkHandler;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.epicfight_dd.skill.skill_compats.CombatEvoCompat;
import net.epicfight_dd.skill.skill_compats.NightfallCompat;
import net.epicfight_dd.skill.skill_compats.WoMCompat;
import net.epicfight_dd.world.capabilities.item.DawnDayWeaponCategories;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.minecraftforge.resource.PathPackResources;
import net.shelmarow.combat_evolution.CombatEvolution;
import reascer.wom.main.WeaponsOfMinecraft;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

@Mod(Epicfight_dd.MODID)
public class Epicfight_dd {
    public static final String MODID = "epicfight_dd";

    public Epicfight_dd(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();

        DawnDayRegisters.REGISTERS.forEach(register -> register.register(bus));
        SkillDataKeyZ.DATA_KEYS.register(bus);

        WeaponCategory.ENUM_MANAGER.registerEnumCls(MODID, DawnDayWeaponCategories.class);

        bus.addListener(this::addPackFindersEvent);
        bus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DawnDayConfig.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, DawnDayServerConfig.SPEC);

        if (ModList.get().isLoaded(CombatEvolution.MOD_ID)) {
            ICompatModule.loadCompatModule(context, CombatEvoCompat.class);
        }
        if (ModList.get().isLoaded(WeaponsOfMinecraft.MODID)) {
            ICompatModule.loadCompatModule(context, WoMCompat.class);
            bus.addListener(WoMCompat::buildSkillEvent);
        }
        if (ModList.get().isLoaded(EFN.MODID)) {
            ICompatModule.loadCompatModule(context, NightfallCompat.class);
        }
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