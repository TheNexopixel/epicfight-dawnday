package net.dawn_day.skill.skill_compats;

import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;
import reascer.wom.gameasset.WOMSkills;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.registry.entries.EpicFightSkills;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class WoMCompat implements ICompatModule {


    public static void registerGuard(Event event) {
    }


    public static boolean regGuarded = false;

    public static void buildSkillEvent(RegisterEvent event) {
        if (EpicFightSkills.GUARD == null) {
            return;
        }
        if (regGuarded) {
            return;
        }
        try {
            regGuard();
        } catch (Exception e) {
            e.printStackTrace();
        }
        regGuarded = true;
    }

    public static void regGuard() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardMotions = new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardBreakMotions = new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> advancedGuardMotions = new HashMap<>();

        //Normal
//        guardMotions.put(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) ->
//                AdditionalAnimations.EVIL_ODACHI_GUARD_HIT);
//        guardBreakMotions.put(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) ->
//                AdditionalAnimations.EVIL_ODACHI_NEUTRALIZED);
//        advancedGuardMotions.put(EpicFightDD_WeaponCategories.EVIL_TACHI, (itemCap, playerpatch) ->
//                AdditionalAnimations.EVIL_ODACHI_COUNTER);

        Field temp;
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> target;
        temp = GuardSkill.class.getDeclaredField("guardMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(WOMSkills.COUNTER_ATTACK);
        for (WeaponCategory weaponCapability : guardMotions.keySet()) {
            target.put(weaponCapability, guardMotions.get(weaponCapability));
        }
        target = (Map) temp.get(WOMSkills.VENGEFUL_PARRY);
        for (WeaponCategory weaponCapability : guardMotions.keySet()) {
            target.put(weaponCapability, guardMotions.get(weaponCapability));
        }

        temp = GuardSkill.class.getDeclaredField("guardBreakMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(WOMSkills.COUNTER_ATTACK);
        for (WeaponCategory weaponCapability : guardBreakMotions.keySet()) {
            target.put(weaponCapability, guardBreakMotions.get(weaponCapability));
        }
        target = (Map) temp.get(WOMSkills.VENGEFUL_PARRY);
        for (WeaponCategory weaponCapability : guardBreakMotions.keySet()) {
            target.put(weaponCapability, guardBreakMotions.get(weaponCapability));
        }

        temp = GuardSkill.class.getDeclaredField("advancedGuardMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(WOMSkills.COUNTER_ATTACK);
        for (WeaponCategory weaponCapability : advancedGuardMotions.keySet()) {
            target.put(weaponCapability, advancedGuardMotions.get(weaponCapability));
        }

    }

    @Override
    public void onModEventBus(IEventBus iEventBus) {

    }

    @Override
    public void onGameEventBus(IEventBus iEventBus) {

    }

    @Override
    public void onModEventBusClient(IEventBus iEventBus) {

    }

    @Override
    public void onGameEventBusClient(IEventBus iEventBus) {

    }
}