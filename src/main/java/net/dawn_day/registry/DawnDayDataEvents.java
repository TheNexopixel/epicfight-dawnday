package net.dawn_day.registry;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.world.DawnDayBlockTagProvider;
import net.dawn_day.world.DawnDayItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = EpicFightDawnDay.MOD_ID)
public class DawnDayDataEvents {

    private DawnDayDataEvents(){}

@SubscribeEvent
    public static void DawnDay$gatherData(GatherDataEvent event){
    DataGenerator gen = event.getGenerator();
    PackOutput packOutput = gen.getPackOutput();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    DawnDayBlockTagProvider blockTagsProvider = new DawnDayBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
    gen.addProvider(event.includeServer(), new DawnDayItemTagsProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

    }

}
