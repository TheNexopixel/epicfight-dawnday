package net.dawn_day.world;

import net.dawn_day.registry.entries.DawnDayItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;

public class DawnDayLootModifiers {

    @EventBusSubscriber
    public class ModLootTableModifiers {

        @SubscribeEvent
        public static void onLootLoad(LootTableLoadEvent event) {

            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/ancient_city"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.15f)
                        )
                        .add(
                                LootItem.lootTableItem(
                                        DawnDayItems.NIGHT_RITUS_DAGGER.get()
                                )
                        )
                        .name("night_ritus_dagger")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/woodland_mansion"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.25f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.BLOOD_RITUS_DAGGER.get()

                                )
                        )
                        .name("blood_ritus_dagger")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/village/village_toolsmith"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.35f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.NAIL.get()

                                )
                                        .apply(
                                                SetItemCountFunction.setCount(UniformGenerator.between(1f,5f))
                                        )
                        )
                        .name("nail")
                        .build();

                event.getTable().addPool(pool);
            }
        }
    }
}


