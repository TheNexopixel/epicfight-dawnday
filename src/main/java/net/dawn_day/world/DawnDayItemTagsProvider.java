package net.dawn_day.world;

import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.registry.entries.DawnDayItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DawnDayItemTagsProvider extends ItemTagsProvider {

    public DawnDayItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, EpicFightDawnDay.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(ItemTags.SWORDS)
                .add(
                        DawnDayItems.BAT.get(),
                        DawnDayItems.BACKHAND_BLADE.get(),
                        DawnDayItems.BLOOD_RITUS_DAGGER.get(),
                        DawnDayItems.DIAMOND_MESSER.get(),
                        DawnDayItems.GOLDEN_MESSER.get(),
                        DawnDayItems.NETHERITE_MESSER.get(),
                        DawnDayItems.IRON_MESSER.get(),
                        DawnDayItems.HERB_SICKLE.get(),
                        DawnDayItems.SABER.get(),
                        DawnDayItems.STEEL_AXE.get(),
                        DawnDayItems.NIGHT_RITUS_DAGGER.get(),
                        DawnDayItems.IRON_KNIFE.get(),
                        DawnDayItems.DIAMOND_KNIFE.get(),
                        DawnDayItems.NETHERITE_KNIFE.get(),
                        DawnDayItems.GOLDEN_KNIFE.get(),
                        DawnDayItems.WOODEN_BATTLESTAFF.get(),
                        DawnDayItems.IRON_BATTLESTAFF.get(),
                        DawnDayItems.GOLDEN_BATTLESTAFF.get(),
                        DawnDayItems.DIAMOND_BATTLESTAFF.get(),
                        DawnDayItems.NETHERITE_BATTLESTAFF.get(),
                        DawnDayItems.NAIL_BAT.get(),
                        DawnDayItems.MILADY.get(),
                        DawnDayItems.BONECUTTING_SAW.get(),
                        DawnDayItems.NETHERITE_LIGHT_GREATSWORD.get(),
                        DawnDayItems.DIAMOND_LIGHT_GREATSWORD.get(),
                        DawnDayItems.GOLDEN_LIGHT_GREATSWORD.get(),
                        DawnDayItems.IRON_LIGHT_GREATSWORD.get(),
                        DawnDayItems.WAR_SICKLE.get(),
                        DawnDayItems.DIAMOND_HALBERD.get(),
                        DawnDayItems.GOLDEN_HALBERD.get(),
                        DawnDayItems.IRON_HALBERD.get(),
                        DawnDayItems.NETHERITE_HALBERD.get()
                );
    }
}
