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
                        .add(LootItem.lootTableItem(DawnDayItems.BLOOD_RITUS_DAGGER.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.ruby.get()))
                        .name("blood_ritus_dagger")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/desert_pyramid"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.25f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_FLORETT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.ruby.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.WAR_SICKLE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_HALBERD.get()))
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
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/bastion_bridge"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.15f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                                DawnDayItems.NETHERITE_HALBERD.get()

                                        )
                        )
                        .name("netherite_halberd")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/bastion_treasure"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.3f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.MILADY.get()

                                )
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.DIAMOND_LIGHT_GREATSWORD.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.DIAMOND_MESSER.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.DIAMOND_HALBERD.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.BONECUTTING_SAW.get()))
                        .name("milady")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/bastion_hoglin_stable"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.253f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.NETHERITE_BATTLESTAFF.get()

                                )
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_MESSER.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_BATTLESTAFF.get()))
                        .name("netherite_battlestaff")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/bastion_other"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.2f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.NETHERITE_MESSER.get()

                                )

                        )
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_BATTLESTAFF.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_KNIFE.get()))
                        .name("netherite_messer")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/simple_dungeon"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.35f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.HERB_SICKLE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_FIST.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_BATTLESTAFF.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_LIGHT_GREATSWORD.get()))
                        .name("herb_sickle")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/shipwreck_treasure"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.152f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.SABER.get()

                                )
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.ruby.get()))
                        .name("saber")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/ruined_portal"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.2f))
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_MESSER.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_BATTLESTAFF.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_KNIFE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_LIGHT_GREATSWORD.get()))
                        .name("golden_messer")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/abandoned_mineshaft"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.3f))
                        .add(LootItem.lootTableItem(DawnDayItems.BACKHAND_BLADE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_KNIFE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_FIST.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_HALBERD.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_MESSER.get()))
                        .name("backhand_blade")
                        .build();

                event.getTable().addPool(pool);
            }
        }
    }
}


