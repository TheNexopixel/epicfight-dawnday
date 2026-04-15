package net.epicfight_dd.skill.skill_compats;

import net.epicfight_dd.gameasset.animation.optional.DawnDayExecution_TYPES;
import net.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.epicfight_dd.world.item.DawnDayItems;
import net.epicfight_dd.skill.skill_compats.WoMCompat;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.shelmarow.combat_evolution.api.event.RegisterCustomExecutionEvent;
import reascer.wom.main.WeaponsOfMinecraft;
import reascer.wom.world.item.WOMItems;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.item.GloveItem;

public class  CombatEvoCompat implements ICompatModule {

  public static void registerExecution(RegisterCustomExecutionEvent event){

      event.registerExecutionByItem(EpicFightMod.identifier("glove"), CapabilityItem.Styles.COMMON,
              (item, livingentitypatch) -> {
                  Item item1 = livingentitypatch.getAdvancedHoldingItemStack(InteractionHand.OFF_HAND).getItem();
                    return DawnDayExecution_TYPES.KNEE_STOMP;
//                  if (item1 instanceof GloveItem) {

//                  } else return null;
              });

      event.registerExecutionByCategory(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD, CapabilityItem.Styles.ONE_HAND,
              DawnDayExecution_TYPES.MILADY);

      event.registerExecutionByCategory(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD, CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.MILADY_DUAL);

      event.registerExecutionByItem(DawnDayItems.diamond_battlestaff.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.golden_battlestaff.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.iron_battlestaff.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.netherite_battlestaff.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.BATTLESTAFF);

      event.registerExecutionByItem(DawnDayItems.wooden_battlestaff.getId(), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.BATTLESTAFF);

      event.registerExecutionByItem(WeaponsOfMinecraft.identifier("evil_tachi"), CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.EVIL_TACHI);



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
