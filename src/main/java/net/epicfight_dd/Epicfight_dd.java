package net.epicfight_dd;

import com.hm.efn.EFN;
import net.epicfight_dd.gameasset.DawnDayRegisters;
import net.epicfight_dd.network.DDNetworkHandler;
import net.epicfight_dd.skill.skill_compats.CombatEvoCompat;
import net.epicfight_dd.skill.skill_compats.NightfallCompat;
import net.epicfight_dd.skill.skill_compats.WoMCompat;
import net.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.resource.PathPackResources;
import net.shelmarow.combat_evolution.CombatEvolution;
import reascer.wom.main.WeaponsOfMinecraft;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.nio.file.Path;


@Mod(Epicfight_dd.MODID)
public class Epicfight_dd {

    public static final String MODID = "epicfight_dd";



    public Epicfight_dd(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();

        //register every deferred register in the list with the mod eventbus
        DawnDayRegisters.REGISTERS.forEach(deferredRegister -> deferredRegister.register(bus));
        bus.addListener(this::addPackFindersEvent);
        WeaponCategory.ENUM_MANAGER.registerEnumCls(Epicfight_dd.MODID, EpicFightDD_WeaponCategories.class);




        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                DawnDayConfig.SPEC
        );

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us



        if(ModList.get().isLoaded(CombatEvolution.MOD_ID)){
            ICompatModule.loadCompatModule(context, CombatEvoCompat.class);
        }
        if (ModList.get().isLoaded(WeaponsOfMinecraft.MODID)) {
            ICompatModule.loadCompatModule(context, WoMCompat.class);
            bus.addListener(WoMCompat::buildSkillEvent);
        }

        if(ModList.get().isLoaded(EFN.MODID)){
            ICompatModule.loadCompatModule(context, NightfallCompat.class);
        }

        bus.addListener(this::commonSetup);


    }
    public void addPackFindersEvent(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(Epicfight_dd.MODID).getFile().findResource("packs/Slam High Pitch");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(Epicfight_dd.MODID).getFile().getFileName() + ":" + resourcePath, false, resourcePath);
            Pack.ResourcesSupplier resourcesSupplier = (string) -> pack;
            Pack.Info info = Pack.readPackInfo("Slam High Pitch", resourcesSupplier);

            if (info != null) {
                event.addRepositorySource((source) ->
                        source.accept(Pack.create("Slam High Pitch", Component.translatable("pack.Slam High Pitch.title"), false, resourcesSupplier, info, PackType.CLIENT_RESOURCES, Pack.Position.TOP, false, PackSource.BUILT_IN)));
            }
        }
    }




    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(DDNetworkHandler::register);
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {


        }
    }


}
