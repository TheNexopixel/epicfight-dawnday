package net.epicfight_dd.skill.skill_compats;

import com.hm.efn.gameasset.animations.EFNSkillAnimations;
import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import reascer.wom.world.item.WOMItems;
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
                            EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> Animations.LONGSWORD_GUARD_HIT
                    ).addGuardBreakMotion(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> Animations.GREATSWORD_GUARD_BREAK)

                    .addGuardMotion(
                            EpicFightDD_WeaponCategories.EVIL_TACHI,
                            (i, p) -> DawnDayAnimations.EVIL_ODACHI_GUARD_HIT
                    ).addGuardBreakMotion(EpicFightDD_WeaponCategories.EVIL_TACHI,
                            (i, p) -> DawnDayAnimations.EVIL_ODACHI_NEUTRALIZED)

                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, ((capabilityItem, pp) ->
                            List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))


                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD, ((capabilityItem, pp) ->
                            List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))


            ;

        }
    }

        @OnlyIn(Dist.CLIENT)
        public static void onIconCreate(WeaponCategoryIconRegisterEvent icon){

            icon.registerCategory(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD, new ItemStack(DawnDayItems.iron_light_greatsword.get()));
            icon.registerCategory(EpicFightDD_WeaponCategories.EVIL_TACHI, new ItemStack(WOMItems.EVIL_TACHI.get()));
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
