package F.epicfight_dd.skill.skill_compats;

import F.epicfight_dd.gameasset.animation.optional.DawnDayExecution_TYPES;
import net.minecraftforge.eventbus.api.IEventBus;
import net.shelmarow.combat_evolution.api.event.RegisterCustomExecutionEvent;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.main.EpicFightMod;

public class CombatEvoCompat implements ICompatModule {

  public static void registerExecution(RegisterCustomExecutionEvent event){

      event.RegisterExecutionByItem(EpicFightMod.identifier("glove"),
              DawnDayExecution_TYPES.KNEE_STOMP);

      //System.out.println("EXECUTION TYPES SUCESSFULLY REGISTERED");

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
