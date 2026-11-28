package net.epicfight_dd.world;

import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.resources.ResourceLocation;
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
                        .add(LootItem.lootTableItem(DawnDayItems.RUBY.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.AMETHYST_BATTLESTAFF.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.NIGHTINGALE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.POLEBLADE_BLADE_PART.get()))
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
                        .add(LootItem.lootTableItem(DawnDayItems.BLOOD_KNIFE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.SANGUINEUS.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.WAR_SICKLE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.BONECUTTING_SAW.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GRIMM.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.POLEBLADE_BLADE_PART.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.RUBY.get()))
                        .name("blood_ritus_dagger")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/desert_pyramid"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.35f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_FLORETT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_FLORETT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GRIMM.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.HORUS_FANG.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.PERIDOT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.RUBY.get()))
                        .name("blood_ritus_dagger")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/underwater_ruin_small"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.1f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.GLORIOUS_CHARM.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.AMBER.get()))
                        .name("ocean_modifierf")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/underwater_ruin_big"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.2f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.GLORIOUS_CHARM.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.AMBER.get()))
                        .name("ocean_modifiera")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/buried_treasure"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.4f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.JADE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.TANZANITE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.AMBER.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IUDEX_HALBERD.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.PERIDOT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.RUBY.get()))
                        .name("blood_ritus_dagger")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/village/village_weaponsmith"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.35f)
                        )
                        .add(

                                LootItem.lootTableItem(DawnDayItems.NAILS.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f,5f))))
                        .add(LootItem.lootTableItem(DawnDayItems.METAL_PLATE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f,2f))))
                        .add(LootItem.lootTableItem(DawnDayItems.WAR_SICKLE.get()))
                        .name("nail")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/village/village_armorer"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.5f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.PERIDOT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.TANZANITE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.MALACHITE.get()))
                        .name("gem_addition")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/village/village_cartographer"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.2f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.PERIDOT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.CITRINE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.AMBER.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.JADE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.TANZANITE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.MALACHITE.get()))
                        .name("gem_addition")
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
                                                DawnDayItems.NETHERITE_HALBERD.get()

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
                                LootItemRandomChanceCondition.randomChance(0.3f)
                        )
                        .add(

                                LootItem.lootTableItem(
                                        DawnDayItems.MILADY.get()

                                )
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.DIAMOND_LIGHT_GREATSWORD.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.DIAMOND_MESSER.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GRIMM.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IUDEX_HALBERD.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.DIAMOND_HALBERD.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.BONECUTTING_SAW.get()))
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
                    new ResourceLocation(
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
                        .add(LootItem.lootTableItem(DawnDayItems.RUSTY_LUMBERJACK_AXE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.BLOOD_KNIFE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GOLDEN_KNIFE.get()))
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
                                LootItemRandomChanceCondition.randomChance(0.45f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_SICKLE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_FIST.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.RUSTY_LUMBERJACK_AXE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.REVENANT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.SPIKED_BAT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GRIMM.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_MESSER.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_KNIFE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.METAL_PLATE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f,3f))))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_FLORETT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.HOOKCLAWS.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_BATTLESTAFF.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_LIGHT_GREATSWORD.get()))
                        .name("iron_sickle")
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
                                        DawnDayItems.SABER.get()

                                )
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.RUBY.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.AMETHYST_BATTLESTAFF.get()))
                        .name("saber")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/end_city_treasure"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.2352f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.NIGHTINGALE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.PERIDOT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.TANZANITE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.CITRINE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.AMETHYST_BATTLESTAFF.get()))
                        .name("end_city_additon")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    new ResourceLocation(
                            "minecraft",
                            "chests/stronghold_corridor"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.1352f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.CRESCENDO.get()))
                        .name("stronghold_corridor_add")
                        .build();

                event.getTable().addPool(pool);
            }
            if (event.getName().equals(
                    ResourceLocation.fromNamespaceAndPath(
                            "minecraft",
                            "chests/stronghold_libary"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.1352f)
                        )
                        .add(LootItem.lootTableItem(DawnDayItems.CRESCENDO.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.TANZANITE.get()))
                        .name("stronghold_libary_add")
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
                            "chests/jungle_temple"))) {

                LootPool pool = LootPool.lootPool()
                        .when(
                                LootItemRandomChanceCondition.randomChance(0.2f))
                        .add(LootItem.lootTableItem(DawnDayItems.JADE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.BLOOD_KNIFE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.TANZANITE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.REVENANT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.MALACHITE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.PERIDOT.get()))
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
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_FIST.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.REVENANT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.TANZANITE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.MALACHITE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.PERIDOT.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.GRIMM.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.AMETHYST_BATTLESTAFF.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.JADE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_KNIFE.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.METAL_PLATE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f,3f))))
                        .add(LootItem.lootTableItem(DawnDayItems.RUSTED_METAL_PLATE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f,2f))))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_HALBERD.get()))
                        .add(LootItem.lootTableItem(DawnDayItems.IRON_MESSER.get()))
                        .name("backhand_blade")
                        .build();

                event.getTable().addPool(pool);
            }
        }
    }
}


