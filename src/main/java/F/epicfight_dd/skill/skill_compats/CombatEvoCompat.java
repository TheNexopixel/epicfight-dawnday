package F.epicfight_dd.skill.skill_compats;

import F.epicfight_dd.gameasset.animation.optional.DawnDayExecution_TYPES;
import F.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.shelmarow.combat_evolution.api.event.RegisterCustomExecutionEvent;
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
                      return DawnDayExecution_TYPES.KNEE_STOMP;
                  } else return null;
              });

      event.registerExecutionByCategory(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD, CapabilityItem.Styles.ONE_HAND,
              DawnDayExecution_TYPES.MILADY);

      event.registerExecutionByCategory(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD, CapabilityItem.Styles.TWO_HAND,
              DawnDayExecution_TYPES.MILADY_DUAL);


      //System.out.println("EXECUTION TYPES SUCCESSFULLY REGISTERED");

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
