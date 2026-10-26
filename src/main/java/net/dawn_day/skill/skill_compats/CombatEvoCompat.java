package net.dawn_day.skill.skill_compats;

import net.dawn_day.gameasset.animation.optional.DawnDayExecutionTypes;
import net.dawn_day.registry.entries.DawnDayItems;
import net.dawn_day.world.capabilities.item.DawnDayWeaponCategories;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.shelmarow.combat_evolution.api.event.RegisterCustomExecutionEvent;
import net.shelmarow.combat_evolution.execution.ExecutionTypeManager;
import yesman.epicfight.EpicFight;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.item.GloveItem;

public class  CombatEvoCompat implements ICompatModule {

  public static void registerExecution(RegisterCustomExecutionEvent event){



      event.registerExecutionByItem(EpicFight.identifier("glove"), CapabilityItem.Styles.COMMON,
              (item, livingentitypatch) -> {
                  Item item1 = livingentitypatch.getAdvancedHoldingItemStack(InteractionHand.OFF_HAND).getItem();

                  if (item1 instanceof GloveItem) {
                      return DawnDayExecutionTypes.NAOYA;
                  }
                  return DawnDayExecutionTypes.KNEE_STOMP;
              });


      event.registerExecutionByCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD, CapabilityItem.Styles.ONE_HAND,
              ExecutionTypeManager.TACHI_TYPE);

      event.registerExecutionByCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD, CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.MILADY_DUAL);

      event.registerExecutionByItem(DawnDayItems.DIAMOND_BATTLESTAFF.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.GOLDEN_BATTLESTAFF.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.IRON_BATTLESTAFF.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.NETHERITE_BATTLESTAFF.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.WOODEN_BATTLESTAFF.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.HOOKCLAWS.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.IRON_FIST);

      event.registerExecutionByItem(DawnDayItems.POLEBLADE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.POLEBLADE);

//      event.registerExecutionByItem(DawnDayItems.NIGHTINGALE.getId(), CapabilityItem.Styles.TWO_HAND,
//              DawnDayExecutionTypes.POLEBLADE);

      event.registerExecutionByItem(DawnDayItems.IRON_KNIFE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.DUAL_RITUAL_DAGGER);

      event.registerExecutionByItem(DawnDayItems.GOLDEN_KNIFE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.DUAL_RITUAL_DAGGER);

      event.registerExecutionByItem(DawnDayItems.DIAMOND_KNIFE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.DUAL_RITUAL_DAGGER);

      event.registerExecutionByItem(DawnDayItems.NETHERITE_KNIFE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.DUAL_RITUAL_DAGGER);

      event.registerExecutionByItem(DawnDayItems.IRON_FIST.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.IRON_FIST);

      event.registerExecutionByItem(DawnDayItems.IRON_FLORETT.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.MILADY_DUAL);

      event.registerExecutionByItem(DawnDayItems.GOLDEN_FLORETT.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.MILADY_DUAL);

      event.registerExecutionByItem(DawnDayItems.DIAMOND_FLORETT.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.MILADY_DUAL);

      event.registerExecutionByItem(DawnDayItems.NETHERITE_FLORETT.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.MILADY_DUAL);

      event.registerExecutionByItem(DawnDayItems.IRON_FIST.getId(), CapabilityItem.Styles.ONE_HAND,
              DawnDayExecutionTypes.IRON_FIST);

      event.registerExecutionByItem(DawnDayItems.BLOOD_RITUS_DAGGER.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.DUAL_RITUAL_DAGGER);

      event.registerExecutionByItem(DawnDayItems.NIGHT_RITUS_DAGGER.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.DUAL_RITUAL_DAGGER);

      event.registerExecutionByItem(DawnDayItems.BLOOD_RITUS_DAGGER.getId(), CapabilityItem.Styles.ONE_HAND,
              DawnDayExecutionTypes.ONEHANDED_RITUAL_DAGGER);

      event.registerExecutionByItem(DawnDayItems.NIGHT_RITUS_DAGGER.getId(), CapabilityItem.Styles.ONE_HAND,
              DawnDayExecutionTypes.ONEHANDED_RITUAL_DAGGER);

//      event.registerExecutionByItem(DawnDayItems.GLORIA.getId(), CapabilityItem.Styles.TWO_HAND,
//              DawnDayExecution_TYPES.IRON_FIST);

      event.registerExecutionByItem(DawnDayItems.IRON_LIGHT_GREATSWORD.getId(), CapabilityItem.Styles.OCHS,
              DawnDayExecutionTypes.LIGHTGREATSWORD_SWORD);

      event.registerExecutionByItem(DawnDayItems.GOLDEN_LIGHT_GREATSWORD.getId(), CapabilityItem.Styles.OCHS,
              DawnDayExecutionTypes.LIGHTGREATSWORD_SWORD);

      event.registerExecutionByItem(DawnDayItems.DIAMOND_LIGHT_GREATSWORD.getId(), CapabilityItem.Styles.OCHS,
              DawnDayExecutionTypes.LIGHTGREATSWORD_SWORD);

      event.registerExecutionByItem(DawnDayItems.NETHERITE_LIGHT_GREATSWORD.getId(), CapabilityItem.Styles.OCHS,
              DawnDayExecutionTypes.LIGHTGREATSWORD_SWORD);

      event.registerExecutionByItem(DawnDayItems.IRON_SICKLE.getId(), CapabilityItem.Styles.ONE_HAND,
              ExecutionTypeManager.DAGGER_TYPE);

      event.registerExecutionByItem(DawnDayItems.GOLDEN_SICKLE.getId(), CapabilityItem.Styles.ONE_HAND,
              ExecutionTypeManager.DAGGER_TYPE);

      event.registerExecutionByItem(DawnDayItems.DIAMOND_SICKLE.getId(), CapabilityItem.Styles.ONE_HAND,
              ExecutionTypeManager.DAGGER_TYPE);

      event.registerExecutionByItem(DawnDayItems.NETHERITE_SICKLE.getId(), CapabilityItem.Styles.ONE_HAND,
              ExecutionTypeManager.DAGGER_TYPE);

      event.registerExecutionByItem(DawnDayItems.IRON_SICKLE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.LIGHTGREATSWORD_SWORD);

      event.registerExecutionByItem(DawnDayItems.GOLDEN_SICKLE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.LIGHTGREATSWORD_SWORD);

      event.registerExecutionByItem(DawnDayItems.DIAMOND_SICKLE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.LIGHTGREATSWORD_SWORD);

      event.registerExecutionByItem(DawnDayItems.NETHERITE_SICKLE.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecutionTypes.LIGHTGREATSWORD_SWORD);

  }


    @Override
    public void onModEventBus(IEventBus eventBus) {
        eventBus.addListener(CombatEvoCompat::registerExecution);
    }

    @Override
    public void onGameEventBus(IEventBus eventBus) {}

    @Override
    public void onModEventBusClient(IEventBus eventBus) {}

    @Override
    public void onGameEventBusClient(IEventBus eventBus) {}


}