package net.epicfight_dd.skill.skill_compats;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import reascer.wom.world.item.WOMItems;
import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.passive.SwordmasterSkill;

import java.util.List;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DefaultEpicFightSkillCompat {


    @SubscribeEvent
    public static void onGuardSkillcreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<GuardSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","guard"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();

            builder.addGuardMotion(
                    EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                    (i, p) -> Animations.LONGSWORD_GUARD_HIT
            ).addGuardBreakMotion(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                    (i, p) -> Animations.GREATSWORD_GUARD_BREAK);

            builder.addGuardMotion(
                    EpicFightDD_WeaponCategories.SICKLE,
                    (i, p) -> Animations.SWORD_GUARD_HIT
            ).addGuardBreakMotion(EpicFightDD_WeaponCategories.SICKLE,
                    (i, p) -> Animations.BIPED_COMMON_NEUTRALIZED);

            builder.addGuardMotion(
                    EpicFightDD_WeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_HIT
            ).addGuardBreakMotion(EpicFightDD_WeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_BREAK);

            builder.addGuardMotion(
                    EpicFightDD_WeaponCategories.RITUS_DAGGER,
                    (i, p) -> DawnDayAnimations.RITUS_DAGGER_GUARD_HIT
            ).addGuardBreakMotion(EpicFightDD_WeaponCategories.RITUS_DAGGER,
                    (i, p) -> DawnDayAnimations.RITUS_DAGGER_NEUTRALIZED);

            builder.addGuardMotion(
                    EpicFightDD_WeaponCategories.FLORETT,
                    (i, p) -> DawnDayAnimations.FLORETT_DUAL_GUARD_HIT
            ).addGuardBreakMotion(EpicFightDD_WeaponCategories.FLORETT,
                    (i, p) -> DawnDayAnimations.FLORETT_DUAL_NEUTRALIZED);


        }

    }

    @SubscribeEvent
    public static void onParrySkillcreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<GuardSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","parrying"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();

            builder.addGuardMotion(
                            EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> Animations.LONGSWORD_GUARD_HIT
                    ).addGuardBreakMotion(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> Animations.GREATSWORD_GUARD_BREAK)
                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> List.of(
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT1,
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            ));

            builder.addGuardMotion(
                            EpicFightDD_WeaponCategories.SICKLE,
                            (i, p) -> Animations.SWORD_GUARD_HIT
                    ).addGuardBreakMotion(EpicFightDD_WeaponCategories.SICKLE,
                            (i, p) -> Animations.BIPED_COMMON_NEUTRALIZED)
                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.SICKLE,
                            (i, p) -> List.of(
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT1,
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            ));

            builder.addGuardMotion(
                    EpicFightDD_WeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_HIT
            ).addGuardBreakMotion(EpicFightDD_WeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_BREAK)
                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.IRON_FIST,
                            (i, p) -> List.of(
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT1,
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            ));

            builder.addGuardMotion(
                            EpicFightDD_WeaponCategories.FLORETT,
                            (i, p) -> DawnDayAnimations.FLORETT_DUAL_GUARD_HIT
                    ).addGuardBreakMotion(EpicFightDD_WeaponCategories.FLORETT,
                            (i, p) -> DawnDayAnimations.FLORETT_DUAL_NEUTRALIZED)
                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.FLORETT,
                            (i, p) -> List.of(
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT1,
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            ));
        }
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","parrying"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();

            builder.addGuardMotion(
                            EpicFightDD_WeaponCategories.RITUS_DAGGER,
                            (i, p) -> DawnDayAnimations.RITUS_DAGGER_GUARD_HIT
                    ).addGuardBreakMotion(EpicFightDD_WeaponCategories.RITUS_DAGGER,
                            (i, p) -> DawnDayAnimations.RITUS_DAGGER_NEUTRALIZED)
                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.RITUS_DAGGER,
                            (i, p) -> List.of(
                                    DawnDayAnimations.RITUS_DAGGER_PARRY1,
                                    DawnDayAnimations.RITUS_DAGGER_PARRY2
                            ));
        }

    }
    @SubscribeEvent
    public static void onSwordSkillCreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<SwordmasterSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","swordmaster"))) {
                SwordmasterSkill.Builder builder = event.getSkillBuilder();
            builder.addAvailableWeaponCategory(EpicFightDD_WeaponCategories.RITUS_DAGGER)
                    .addAvailableWeaponCategory(EpicFightDD_WeaponCategories.FLORETT)
                    .addAvailableWeaponCategory(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD);
        }
    }
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onIconCreate(WeaponCategoryIconRegisterEvent icon){
        icon.registerCategory(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD, new ItemStack(DawnDayItems.iron_light_greatsword.get()));
        icon.registerCategory(EpicFightDD_WeaponCategories.RITUS_DAGGER, new ItemStack(DawnDayItems.BLOOD_RITUS_DAGGER.get()));
        icon.registerCategory(EpicFightDD_WeaponCategories.FLORETT, new ItemStack(DawnDayItems.IRON_FLORETT.get()));
        icon.registerCategory(EpicFightDD_WeaponCategories.IRON_FIST, new ItemStack(DawnDayItems.IRON_FIST.get()));

    }



}
