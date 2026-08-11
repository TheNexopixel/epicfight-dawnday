package net.epicfight_dd.skill.skill_compats;

import com.hm.efn.gameasset.animations.EFNSkillAnimations;
import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.epicfight_dd.world.capabilities.item.DawnDayWeaponCategories;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;

import java.util.List;

public class NightfallCompat implements ICompatModule {


    public static void onEnhancedParry(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<GuardSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("efn", "efn_parry"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();
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

                    .addGuardMotion(
                            DawnDayWeaponCategories.FLORETT,
                            (i, p) -> DawnDayAnimations.FLORETT_DUAL_GUARD_HIT
                    ).addGuardBreakMotion(DawnDayWeaponCategories.FLORETT,
                            (i, p) -> DawnDayAnimations.FLORETT_DUAL_NEUTRALIZED)

                    .addGuardMotion(
                            DawnDayWeaponCategories.CLAWS,
                            (i, p) -> DawnDayAnimations.HOOKCLAWS_GUARD_HIT
                    ).addGuardBreakMotion(DawnDayWeaponCategories.CLAWS,
                            (i, p) -> DawnDayAnimations.HOOKCLAWS_NEUTRALIZE)
                    .addGuardMotion(
                            DawnDayWeaponCategories.SICKLE,
                            (i, p) -> Animations.SWORD_GUARD_HIT
                    ).addGuardBreakMotion(DawnDayWeaponCategories.SICKLE,
                            (i, p) -> Animations.BIPED_COMMON_NEUTRALIZED)

                    .addAdvancedGuardMotion(DawnDayWeaponCategories.SICKLE, ((capabilityItem, pp) ->
                            List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))


                    .addAdvancedGuardMotion(DawnDayWeaponCategories.FLORETT, ((capabilityItem, pp) ->
                            List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))

                    .addAdvancedGuardMotion(DawnDayWeaponCategories.CLAWS, ((capabilityItem, pp) ->
                            List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))

                    .addAdvancedGuardMotion(DawnDayWeaponCategories.RITUS_DAGGER, ((capabilityItem, pp) ->
                            List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))


                    .addAdvancedGuardMotion(DawnDayWeaponCategories.LIGHT_GREATSWORD, ((capabilityItem, pp) ->
                            List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))


            ;

        }
    }

        @OnlyIn(Dist.CLIENT)
        public static void onIconCreate(WeaponCategoryIconRegisterEvent icon){

            icon.registerCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD, new ItemStack(DawnDayItems.iron_light_greatsword.get()));
            icon.registerCategory(DawnDayWeaponCategories.RITUS_DAGGER, new ItemStack(DawnDayItems.BLOOD_RITUS_DAGGER.get()));
            icon.registerCategory(DawnDayWeaponCategories.CLAWS, new ItemStack(DawnDayItems.HOOKCLAWS.get()));
            icon.registerCategory(DawnDayWeaponCategories.FLORETT, new ItemStack(DawnDayItems.IRON_FLORETT.get()));
        }





    @Override
    public void onModEventBus(IEventBus eventBus) {
        eventBus.addGenericListener(GuardSkill.Builder.class,
                EventPriority.NORMAL,
                NightfallCompat::onEnhancedParry);
    }

    @Override
    public void onForgeEventBus(IEventBus eventBus) {

    }

    @Override
    public void onModEventBusClient(IEventBus eventBus) {
        eventBus.addListener(NightfallCompat::onIconCreate);
    }

    @Override
    public void onForgeEventBusClient(IEventBus eventBus) {

    }
}
