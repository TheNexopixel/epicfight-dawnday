package F.epicfight_dd;

import F.epicfight_dd.gameasset.DawnDayRegisters;
import F.epicfight_dd.skill.skill_compats.CombatEvoCompat;
import F.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.shelmarow.combat_evolution.CombatEvolution;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import static com.mojang.text2speech.Narrator.LOGGER;


@Mod(Epicfight_dd.MODID)
public class Epicfight_dd {

    public static final String MODID = "epicfight_dd";



    public Epicfight_dd(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();
        bus.addListener(this::commonSetup);

        //register every deferred register in the list with the mod eventbus
        DawnDayRegisters.REGISTERS.forEach(deferredRegister -> deferredRegister.register(bus));

        WeaponCategory.ENUM_MANAGER.registerEnumCls(Epicfight_dd.MODID, EpicFightDD_WeaponCategories.class);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


//        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);


        if(ModList.get().isLoaded(CombatEvolution.MOD_ID)){
            ICompatModule.loadCompatModule(context, CombatEvoCompat.class);
        }


    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        if (Config.logDirtBlock) LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
//    private void addCreative(BuildCreativeModeTabContentsEvent event) {
//        event.getTabKey()
//    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {


        }
    }
}
