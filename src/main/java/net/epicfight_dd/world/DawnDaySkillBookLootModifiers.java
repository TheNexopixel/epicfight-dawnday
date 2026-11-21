package net.epicfight_dd.world;

import java.util.List;

import net.epicfight_dd.Epicfight_dd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.data.loot.function.SetSkillFunction;
import yesman.epicfight.world.item.EpicFightItems;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID)
public final class DawnDaySkillBookLootModifiers {
    private DawnDaySkillBookLootModifiers() {}

    private static final String[] COMMON = {
            Epicfight_dd.MODID + ":riposte",
            Epicfight_dd.MODID + ":outlasting",
            Epicfight_dd.MODID + ":rapid_retreat",
            Epicfight_dd.MODID + ":cortisol",
            Epicfight_dd.MODID + ":restocking",
            Epicfight_dd.MODID + ":air_dash"
    };

    private static final String[] HOGLIN = {
            Epicfight_dd.MODID + ":gorged_bulwark",
            Epicfight_dd.MODID + ":final_strength"
    };
    private static final String[] RIPOSTE = {
            Epicfight_dd.MODID + ":riposte"
    };
    private static final String[] ENDER_DRAGON = {
            Epicfight_dd.MODID + ":redemption"
    };
    private static final String[] RAVAGER = {
            Epicfight_dd.MODID + ":unstoppable"
    };
    private static final String[] DIVINE_PUNISHMENT = {
            Epicfight_dd.MODID + ":divine_punishment"
    };
    private static final String[] HEATED_STEP = {
            Epicfight_dd.MODID + ":heated_step"
    };

    private record ChestEntry(ResourceLocation table, float minRolls, float maxRolls, float chance, String[] skills) {}

    private record MobEntry(ResourceLocation table, float chance, boolean playerKillOnly, String[] skills) {}

    private static final List<ChestEntry> CHESTS = List.of(
            new ChestEntry(BuiltInLootTables.SIMPLE_DUNGEON, 1, 2, 0.15F, COMMON),
            new ChestEntry(BuiltInLootTables.ABANDONED_MINESHAFT, 1, 2, 0.15F, COMMON),
            new ChestEntry(BuiltInLootTables.DESERT_PYRAMID, 1, 2, 0.30F, COMMON),
            new ChestEntry(BuiltInLootTables.JUNGLE_TEMPLE, 1, 2, 0.30F, COMMON),
            new ChestEntry(BuiltInLootTables.VILLAGE_TEMPLE, 1, 1, 0.70F, DIVINE_PUNISHMENT),
            new ChestEntry(BuiltInLootTables.CLERIC_GIFT, 1, 1, 0.70F, DIVINE_PUNISHMENT),
            new ChestEntry(BuiltInLootTables.PILLAGER_OUTPOST, 1, 2, 0.25F, COMMON),
            new ChestEntry(BuiltInLootTables.SHIPWRECK_MAP, 1, 1, 0.15F, COMMON),
            new ChestEntry(BuiltInLootTables.UNDERWATER_RUIN_BIG, 1, 1, 0.20F, COMMON),
            new ChestEntry(BuiltInLootTables.STRONGHOLD_LIBRARY, 1, 3, 0.40F, COMMON),
            new ChestEntry(BuiltInLootTables.WOODLAND_MANSION, 1, 3, 0.40F, COMMON),
            new ChestEntry(BuiltInLootTables.BASTION_TREASURE, 0, 1, 0.10F, HEATED_STEP),
            new ChestEntry(BuiltInLootTables.BASTION_OTHER, 1, 2, 0.35F, COMMON),
            new ChestEntry(BuiltInLootTables.ANCIENT_CITY, 1, 2, 0.35F, COMMON),
            new ChestEntry(BuiltInLootTables.END_CITY_TREASURE, 1, 2, 0.40F, COMMON)
    );

    private static final List<MobEntry> MOBS = List.of(
            new MobEntry(mob("skeleton"), 0.006F, true, RIPOSTE),
            new MobEntry(mob("zombie"), 0.005F, true, RIPOSTE),
            new MobEntry(mob("stray"), 0.007F, true, COMMON),
            new MobEntry(mob("hoglin"), 0.05F, true, HOGLIN),
            new MobEntry(mob("ravager"), 0.09F, true, RAVAGER),
            new MobEntry(mob("strider"), 0.05F, true, HEATED_STEP),
            new MobEntry(mob("ender_dragon"), 1.0F, true, ENDER_DRAGON),
            new MobEntry(mob("warden"), 1.0F, true, COMMON),
            new MobEntry(mob("zoglin"), 0.7F, true, HOGLIN),
            new MobEntry(mob("elder_guardian"), 0.5F, true, COMMON)
    );

    private static ResourceLocation mob(String name) {
        return ResourceLocation.fromNamespaceAndPath("minecraft", "entities/" + name);
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();

        for (ChestEntry entry : CHESTS) {
            if (entry.table().equals(name)) {
                event.getTable().addPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(entry.minRolls(), entry.maxRolls()))
                        .when(LootItemRandomChanceCondition.randomChance(entry.chance()))
                        .add(LootItem.lootTableItem(EpicFightItems.SKILLBOOK.get())
                                .apply(SetSkillFunction.builder(entry.skills())))
                        .build());
            }
        }

        for (MobEntry entry : MOBS) {
            if (entry.table().equals(name)) {
                LootPool.Builder pool = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(entry.chance()))
                        .add(LootItem.lootTableItem(EpicFightItems.SKILLBOOK.get())
                                .apply(SetSkillFunction.builder(entry.skills())));

                if (entry.playerKillOnly()) {
                    pool.when(LootItemKilledByPlayerCondition.killedByPlayer());
                }
                event.getTable().addPool(pool.build());
            }
        }
    }
}