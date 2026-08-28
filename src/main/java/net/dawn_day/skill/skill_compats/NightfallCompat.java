package net.dawn_day.skill.skill_compats;

import com.hm.efn.gameasset.animations.EFNSkillAnimations;
import net.dawn_day.gameasset.animation.DawnDayAnimations;
import net.dawn_day.world.capabilities.item.DawnDayWeaponCategories;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.api.event.types.registry.SkillBuilderModificationEvent;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;

import java.util.List;

public class NightfallCompat implements ICompatModule {


    public static void onEnhancedParry(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("efn", "efn_parry"))) {
            if (event.getSkillBuilder() instanceof GuardSkill.Builder builder) {
                builder

                        .addGuardMotion(
                                DawnDayWeaponCategories.LIGHT_GREATSWORD,
                                (i, p) -> Animations.LONGSWORD_GUARD_HIT
                        ).addGuardBreakMotion(DawnDayWeaponCategories.LIGHT_GREATSWORD,
                                (i, p) -> Animations.GREATSWORD_GUARD_BREAK)


                        .addGuardMotion(
                                DawnDayWeaponCategories.RITUS_DAGGER,
                                (i, p) -> DawnDayAnimations.RITUS_DAGGER_GUARD_HIT
                        ).addGuardBreakMotion(DawnDayWeaponCategories.RITUS_DAGGER,
                                (i, p) -> DawnDayAnimations.RITUS_DAGGER_NEUTRALIZED)

                        .addAdvancedGuardMotion(DawnDayWeaponCategories.RITUS_DAGGER, ((capabilityItem, pp) ->
                                List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))

                        .addAdvancedGuardMotion(DawnDayWeaponCategories.EVIL_TACHI, ((capabilityItem, pp) ->
                                List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))


                        .addAdvancedGuardMotion(DawnDayWeaponCategories.LIGHT_GREATSWORD, ((capabilityItem, pp) ->
                                List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))


                ;

            }
        }
    }


    @Override
    public void onModEventBus(IEventBus eventBus) {
        EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(NightfallCompat::onEnhancedParry, 3);
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
