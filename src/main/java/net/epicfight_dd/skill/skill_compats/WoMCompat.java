package net.epicfight_dd.skill.skill_compats;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import com.mojang.logging.LogUtils;
import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.epicfight_dd.gameasset.animation.WoMCompatAnimations;
import net.epicfight_dd.world.capabilities.item.DawnDayWeaponCategories;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;
import reascer.wom.gameasset.WOMSkills;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

@SuppressWarnings({"unchecked", "rawtypes"})
public class WoMCompat implements ICompatModule {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static boolean registered = false;

    @Override
    public void onModEventBus(IEventBus bus) {}

    @Override
    public void onForgeEventBus(IEventBus bus) {}

    @Override
    public void onModEventBusClient(IEventBus bus) {}

    @Override
    public void onForgeEventBusClient(IEventBus bus) {}

    public static void buildSkillEvent(RegisterEvent event) {
        if (registered) return;
        if (EpicFightSkills.GUARD == null) return;
        if (WOMSkills.COUNTER_ATTACK == null
                || WOMSkills.VENGEFUL_PARRY == null
                || WOMSkills.PERFECT_BULWARK == null) return;

        try {
            regGuard();
            LOGGER.info("[epicfight_dd] WoM guard motions registered");
        } catch (ReflectiveOperationException | RuntimeException e) {
            LOGGER.error("[epicfight_dd] Failed to register WoM guard motions", e);
        }
        registered = true;
    }

    private static void regGuard() throws ReflectiveOperationException {
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guard = new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardBreak = new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> advanced = new HashMap<>();

        entry(guard, guardBreak, advanced, DawnDayWeaponCategories.RITUS_DAGGER,
                () -> DawnDayAnimations.RITUS_DAGGER_GUARD_HIT,
                () -> DawnDayAnimations.RITUS_DAGGER_NEUTRALIZED,
                () -> DawnDayAnimations.RITUS_DAGGER_DUAL_DASH);

        entry(guard, guardBreak, advanced, DawnDayWeaponCategories.HORUS_FANG,
                () -> DawnDayAnimations.HORUS_GUARD_HIT,
                () -> DawnDayAnimations.POLEBLADE_NEUTRALIZE,
                () -> DawnDayAnimations.NIGHTINGALE_AUTO1);

        entry(guard, guardBreak, advanced, DawnDayWeaponCategories.POLEBLADE,
                () -> DawnDayAnimations.POLEBLADE_GUARD_HIT,
                () -> DawnDayAnimations.POLEBLADE_NEUTRALIZE,
                () -> WoMCompatAnimations.TWINBLADE_COUNTER);

        entry(guard, guardBreak, advanced, DawnDayWeaponCategories.IRON_FIST,
                () -> DawnDayAnimations.IRON_FIST_GUARD_HIT,
                () -> DawnDayAnimations.IRON_FIST_GUARD_BREAK,
                () -> WoMCompatAnimations.IRON_FIST_GUARD_COUNTER);

        entry(guard, guardBreak, advanced, DawnDayWeaponCategories.CLAWS,
                () -> DawnDayAnimations.HOOKCLAWS_GUARD_HIT,
                () -> DawnDayAnimations.HOOKCLAWS_NEUTRALIZE,
                () -> WoMCompatAnimations.HOOKCLAWS_COUNTER);

        entry(guard, guardBreak, advanced, DawnDayWeaponCategories.SICKLE,
                () -> Animations.SWORD_GUARD_HIT,
                () -> Animations.BIPED_COMMON_NEUTRALIZED,
                () -> DawnDayAnimations.SICKLE_ONEHAND_AUTO1);

        putInto("guardMotions", guard,
                WOMSkills.COUNTER_ATTACK, WOMSkills.VENGEFUL_PARRY, WOMSkills.PERFECT_BULWARK);
        putInto("guardBreakMotions", guardBreak,
                WOMSkills.COUNTER_ATTACK, WOMSkills.VENGEFUL_PARRY, WOMSkills.PERFECT_BULWARK);
        putInto("advancedGuardMotions", advanced,
                WOMSkills.COUNTER_ATTACK);
    }

    private static void entry(
            Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guard,
            Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardBreak,
            Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> advanced,
            WeaponCategory category,
            Supplier<?> guardHit,
            Supplier<?> breakMotion,
            Supplier<?> advancedMotion) {
        guard.put(category, (item, player) -> guardHit.get());
        guardBreak.put(category, (item, player) -> breakMotion.get());
        advanced.put(category, (item, player) -> advancedMotion.get());
    }

    private static void putInto(String fieldName,
                                Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> source,
                                Object... skills) throws ReflectiveOperationException {
        Field field = GuardSkill.class.getDeclaredField(fieldName);
        field.setAccessible(true);

        for (Object skill : skills) {
            Map target = (Map) field.get(skill);
            if (target == null) {
                throw new IllegalStateException(fieldName + " is null for " + skill);
            }
            target.putAll(source);
        }
    }
}