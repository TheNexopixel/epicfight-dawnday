package net.epicfight_dd.world;

import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
public class DawnDayLootModifiers {

    @Mod.EventBusSubscriber
    public class ModLootTableModifiers {

        @SubscribeEvent
        public static void onLootLoad(LootTableLoadEvent event) {

            if (event.getName().equals(
                    new ResourceLocation(
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
                        .add(LootItem.lootTableItem(DawnDayItems.ruby.get()))
                        .name("night_ritus_dagger")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/woodland_mansion"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.35f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.BLOOD_RITUS_DAGGER.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.ruby.get()))
                        .name("blood_ritus_dagger")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/village/village_toolsmith"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.35f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.nail.get()

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
                    new ResourceLocation(
                            "minecraft",
                            "chests/bastion_bridge"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.15f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                                DawnDayItems.netherite_halberd.get()

                                        )
                        )
                        .name("netherite_halberd")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/bastion_treasure"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.1f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.milady.get()

                                )
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.diamond_light_greatsword.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.diamond_messer.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.diamond_halberd.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.bonecutting_saw.get()))
                        .name("milady")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/bastion_hoglin_stable"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.253f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.netherite_battlestaff.get()

                                )
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.golden_messer.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.golden_battlestaff.get()))
                        .name("netherite_battlestaff")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/bastion_other"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.2f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.netherite_messer.get()

                                )

                        )
                        .add(LootItem.lootTableItem(DawnDayItems.golden_battlestaff.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.golden_knife.get()))
                        .name("netherite_messer")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/simple_dungeon"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.35f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.herb_sickle.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_FIST.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.iron_battlestaff.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.iron_light_greatsword.get()))
                        .name("herb_sickle")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/shipwreck_treasure"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.152f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.saber.get()

                                )
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.ruby.get()))
                        .name("saber")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/ruined_portal"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.2f))
                        .add(LootItem.lootTableItem(DawnDayItems.golden_messer.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.golden_battlestaff.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.golden_knife.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.golden_light_greatsword.get()))
                        .name("golden_messer")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/abandoned_mineshaft"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.3f))
                        .add(LootItem.lootTableItem(DawnDayItems.backhand_blade.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_FIST.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.ruby.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.iron_knife.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.iron_halberd.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.iron_messer.get()))
                        .name("backhand_blade")
                        .build();

                event.getTable().addPool(pool);
            }
        }
    }
}


