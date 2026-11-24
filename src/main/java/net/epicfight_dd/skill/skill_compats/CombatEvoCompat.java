package net.epicfight_dd.skill.skill_compats;

import net.epicfight_dd.config.DawnDayConfigs;
import net.epicfight_dd.gameasset.animation.optional.DawnDayExecution_TYPES;
import net.epicfight_dd.world.capabilities.item.DawnDayWeaponCategories;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.shelmarow.combat_evolution.api.event.RegisterCustomExecutionEvent;
import net.shelmarow.combat_evolution.execution.ExecutionTypeManager;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.item.GloveItem;

public class CombatEvoCompat implements ICompatModule {

  public static void registerExecution(RegisterCustomExecutionEvent event){

      event.registerExecutionByItem(EpicFightMod.identifier("glove"), CapabilityItem.Styles.COMMON,
              (item, livingentitypatch) -> {
                  Item item1 = livingentitypatch.getAdvancedHoldingItemStack(InteractionHand.OFF_HAND).getItem();

                  if (item1 instanceof GloveItem) {
                   return DawnDayExecution_TYPES.NAOYA;
                  }
                  return DawnDayExecution_TYPES.KNEE_STOMP;
              });


      event.registerExecutionByCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD, CapabilityItem.Styles.ONE_HAND,
              DawnDayExecution_TYPES.LIGHT_GREATSWORD);


      event.registerExecutionByCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD, CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.MILADY_DUAL);

        if (DawnDayConfigs.Common.OVERRIDE_DEFAULT_EXECUTIONS.get().equals(true)){
      event.registerExecutionByCategory(CapabilityItem.WeaponCategories.SWORD, CapabilityItem.Styles.ONE_HAND,
              DawnDayExecution_TYPES.LIGHT_GREATSWORD);
      event.registerExecutionByCategory(CapabilityItem.WeaponCategories.DAGGER, CapabilityItem.Styles.TWO_HAND,
                    DawnDayExecution_TYPES.KNIFE_DUAL);

      }
      event.registerExecutionByCategory(DawnDayWeaponCategories.CLAWS, CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.HOOKCLAWS);

      event.registerExecutionByCategory(DawnDayWeaponCategories.POLEBLADE, CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.POLEBLADE);

      event.registerExecutionByItem(DawnDayItems.DIAMOND_BATTLESTAFF.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.GOLDEN_BATTLESTAFF.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.IRON_BATTLESTAFF.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.NETHERITE_BATTLESTAFF.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.WOODEN_BATTLESTAFF.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.BATTLESTAFF);


      event.registerExecutionByItem(DawnDayItems.NIGHTINGALE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.POLEBLADE);

      event.registerExecutionByItem(DawnDayItems.CRESCENDO.getId(), CapabilityItem.Styles.ONE_HAND,
              DawnDayExecution_TYPES.LIGHT_GREATSWORD);

      event.registerExecutionByItem(DawnDayItems.IRON_FIST.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.IRON_FIST);

      event.registerExecutionByItem(DawnDayItems.BLOOD_KNIFE.getId(), CapabilityItem.Styles.ONE_HAND,
              DawnDayExecution_TYPES.ONEHANDED_RITUAL_DAGGER);

      event.registerExecutionByItem(DawnDayItems.BLOOD_KNIFE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.KNIFE_DUAL);

      event.registerExecutionByItem(DawnDayItems.STONE_KNIFE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.KNIFE_DUAL);

      event.registerExecutionByItem(DawnDayItems.IRON_KNIFE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.KNIFE_DUAL);

      event.registerExecutionByItem(DawnDayItems.GOLDEN_KNIFE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.KNIFE_DUAL);

      event.registerExecutionByItem(DawnDayItems.DIAMOND_KNIFE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.KNIFE_DUAL);
      event.registerExecutionByItem(DawnDayItems.NETHERITE_KNIFE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.KNIFE_DUAL);




      event.registerExecutionByItem(DawnDayItems.IRON_FLORETT.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.MILADY_DUAL);
      event.registerExecutionByItem(DawnDayItems.GOLDEN_FLORETT.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.MILADY_DUAL);
      event.registerExecutionByItem(DawnDayItems.DIAMOND_FLORETT.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.MILADY_DUAL);
      event.registerExecutionByItem(DawnDayItems.NETHERITE_FLORETT.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.MILADY_DUAL);

      event.registerExecutionByItem(DawnDayItems.IRON_FIST.getId(), CapabilityItem.Styles.ONE_HAND,
              DawnDayExecution_TYPES.IRON_FIST);

      event.registerExecutionByItem(DawnDayItems.BLOOD_RITUS_DAGGER.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.DUAL_RITUAL_DAGGER);
      event.registerExecutionByItem(DawnDayItems.NIGHT_RITUS_DAGGER.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.DUAL_RITUAL_DAGGER);

      event.registerExecutionByItem(DawnDayItems.BLOOD_RITUS_DAGGER.getId(), CapabilityItem.Styles.ONE_HAND,
              DawnDayExecution_TYPES.ONEHANDED_RITUAL_DAGGER);
      event.registerExecutionByItem(DawnDayItems.NIGHT_RITUS_DAGGER.getId(), CapabilityItem.Styles.ONE_HAND,
              DawnDayExecution_TYPES.ONEHANDED_RITUAL_DAGGER);

      event.registerExecutionByItem(DawnDayItems.GLORIA.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.IRON_FIST);

      event.registerExecutionByItem(DawnDayItems.IRON_LIGHT_GREATSWORD.getId(), CapabilityItem.Styles.OCHS,
              DawnDayExecution_TYPES.LIGHTGREATSWORD_SWORD);
      event.registerExecutionByItem(DawnDayItems.GOLDEN_LIGHT_GREATSWORD.getId(), CapabilityItem.Styles.OCHS,
              DawnDayExecution_TYPES.LIGHTGREATSWORD_SWORD);
      event.registerExecutionByItem(DawnDayItems.DIAMOND_LIGHT_GREATSWORD.getId(), CapabilityItem.Styles.OCHS,
              DawnDayExecution_TYPES.LIGHTGREATSWORD_SWORD);
      event.registerExecutionByItem(DawnDayItems.NETHERITE_LIGHT_GREATSWORD.getId(), CapabilityItem.Styles.OCHS,
              DawnDayExecution_TYPES.LIGHTGREATSWORD_SWORD);

      event.registerExecutionByItem(DawnDayItems.IRON_SICKLE.getId(), CapabilityItem.Styles.ONE_HAND,
              ExecutionTypeManager.DAGGER_TYPE);
      event.registerExecutionByItem(DawnDayItems.GOLDEN_SICKLE.getId(), CapabilityItem.Styles.ONE_HAND,
              ExecutionTypeManager.DAGGER_TYPE);
      event.registerExecutionByItem(DawnDayItems.DIAMOND_SICKLE.getId(), CapabilityItem.Styles.ONE_HAND,
              ExecutionTypeManager.DAGGER_TYPE);
      event.registerExecutionByItem(DawnDayItems.NETHERITE_SICKLE.getId(), CapabilityItem.Styles.ONE_HAND,
              ExecutionTypeManager.DAGGER_TYPE);

      event.registerExecutionByItem(DawnDayItems.IRON_SICKLE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.LIGHTGREATSWORD_SWORD);
      event.registerExecutionByItem(DawnDayItems.GOLDEN_SICKLE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.LIGHTGREATSWORD_SWORD);
      event.registerExecutionByItem(DawnDayItems.DIAMOND_SICKLE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.LIGHTGREATSWORD_SWORD);
      event.registerExecutionByItem(DawnDayItems.NETHERITE_SICKLE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.LIGHTGREATSWORD_SWORD);

  }


    @Override
    public void onModEventBus(IEventBus iEventBus) {
    iEventBus.addListener(CombatEvoCompat::registerExecution);
    }


    @Override
    public void onForgeEventBus(IEventBus iEventBus) {

    }

    @Override
    public void onModEventBusClient(IEventBus iEventBus) {

    }

    @Override
    public void onForgeEventBusClient(IEventBus iEventBus) {
    }


}
