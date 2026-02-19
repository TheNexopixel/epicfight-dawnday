package net.epicfight_dd;

import net.epicfight_dd.gameasset.DawnDayRegisters;
import net.epicfight_dd.skill.skill_compats.CombatEvoCompat;
import net.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.shelmarow.combat_evolution.CombatEvolution;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.world.capabilities.item.WeaponCategory;


@Mod(Epicfight_dd.MODID)
public class Epicfight_dd {

    public static final String MODID = "epicfight_dd";



    public Epicfight_dd(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();


        //register every deferred register in the list with the mod eventbus
        DawnDayRegisters.REGISTERS.forEach(deferredRegister -> deferredRegister.register(bus));

        WeaponCategory.ENUM_MANAGER.registerEnumCls(Epicfight_dd.MODID, EpicFightDD_WeaponCategories.class);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us



        if(ModList.get().isLoaded(CombatEvolution.MOD_ID)){
            ICompatModule.loadCompatModule(context, CombatEvoCompat.class);
        }


    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {


        }
    }


}
