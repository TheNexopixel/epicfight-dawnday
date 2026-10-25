package net.dawn_day.skill.skill_compats;

import net.dawn_day.gameasset.animation.optional.DawnDayExecutionTypes;
import net.dawn_day.registry.entries.DawnDayItems;
import net.dawn_day.world.capabilities.item.DawnDayWeaponCategories;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.shelmarow.combat_evolution.api.event.RegisterCustomExecutionEvent;
import yesman.epicfight.EpicFight;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class  CombatEvoCompat implements ICompatModule {

  public static void registerExecution(RegisterCustomExecutionEvent event){

      event.registerExecutionByItem(EpicFight.identifier("glove"), CapabilityItem.Styles.COMMON,
              (item, livingentitypatch) -> {
                  Item item1 = livingentitypatch.getAdvancedHoldingItemStack(InteractionHand.OFF_HAND).getItem();
                    return DawnDayExecutionTypes.KNEE_STOMP;
//                  if (item1 instanceof GloveItem) {

//                  } else return null;
              });

      event.registerExecutionByCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD, CapabilityItem.Styles.ONE_HAND,
              DawnDayExecutionTypes.MILADY);

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


  }


    @Override
    public void onModEventBus(IEventBus eventBus) {
        eventBus.addListener(CombatEvoCompat::registerExecution);
    }

    @Override
    public void onGameEventBus(IEventBus eventBus) {

    }

    @Override
    public void onModEventBusClient(IEventBus eventBus) {

    }

    @Override
    public void onGameEventBusClient(IEventBus eventBus) {

    }
}