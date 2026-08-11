package net.epicfight_dd.skill.skill_compats;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.epicfight_dd.world.capabilities.item.DawnDayWeaponCategories;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
                    DawnDayWeaponCategories.LIGHT_GREATSWORD,
                    (i, p) -> Animations.LONGSWORD_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.LIGHT_GREATSWORD,
                    (i, p) -> Animations.GREATSWORD_GUARD_BREAK);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.CLAWS,
                    (i, p) -> DawnDayAnimations.HOOKCLAWS_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.CLAWS,
                    (i, p) -> DawnDayAnimations.HOOKCLAWS_NEUTRALIZE);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.SICKLE,
                    (i, p) -> Animations.SWORD_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.SICKLE,
                    (i, p) -> Animations.BIPED_COMMON_NEUTRALIZED);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.BAT,
                    (i, p) -> Animations.GREATSWORD_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.BAT,
                    (i, p) -> Animations.GREATSWORD_GUARD_BREAK);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_BREAK);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.RITUS_DAGGER,
                    (i, p) -> DawnDayAnimations.RITUS_DAGGER_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.RITUS_DAGGER,
                    (i, p) -> DawnDayAnimations.RITUS_DAGGER_NEUTRALIZED);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.FLORETT,
                    (i, p) -> DawnDayAnimations.FLORETT_DUAL_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.FLORETT,
                    (i, p) -> DawnDayAnimations.FLORETT_DUAL_NEUTRALIZED);


        }

    }
    @SubscribeEvent
    public static void onImpactGuardSkillcreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<GuardSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","impact_guard"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();

            builder.addGuardMotion(
                    DawnDayWeaponCategories.LIGHT_GREATSWORD,
                    (i, p) -> Animations.LONGSWORD_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.LIGHT_GREATSWORD,
                    (i, p) -> Animations.GREATSWORD_GUARD_BREAK);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.CLAWS,
                    (i, p) -> DawnDayAnimations.HOOKCLAWS_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.CLAWS,
                    (i, p) -> DawnDayAnimations.HOOKCLAWS_NEUTRALIZE);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.SICKLE,
                    (i, p) -> Animations.SWORD_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.SICKLE,
                    (i, p) -> Animations.BIPED_COMMON_NEUTRALIZED);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.BAT,
                    (i, p) -> Animations.GREATSWORD_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.BAT,
                    (i, p) -> Animations.GREATSWORD_GUARD_BREAK);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_BREAK);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.RITUS_DAGGER,
                    (i, p) -> DawnDayAnimations.RITUS_DAGGER_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.RITUS_DAGGER,
                    (i, p) -> DawnDayAnimations.RITUS_DAGGER_NEUTRALIZED);

            builder.addGuardMotion(
                    DawnDayWeaponCategories.FLORETT,
                    (i, p) -> DawnDayAnimations.FLORETT_DUAL_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.FLORETT,
                    (i, p) -> DawnDayAnimations.FLORETT_DUAL_NEUTRALIZED);


        }

    }

    @SubscribeEvent
    public static void onParrySkillcreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<GuardSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","parrying"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();

            builder.addGuardMotion(
                            DawnDayWeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> Animations.LONGSWORD_GUARD_HIT
                    ).addGuardBreakMotion(DawnDayWeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> Animations.GREATSWORD_GUARD_BREAK)
                    .addAdvancedGuardMotion(DawnDayWeaponCategories.LIGHT_GREATSWORD,
                            (i, p) -> List.of(
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT1,
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            ));

            builder.addGuardMotion(
                            DawnDayWeaponCategories.SICKLE,
                            (i, p) -> Animations.SWORD_GUARD_HIT
                    ).addGuardBreakMotion(DawnDayWeaponCategories.SICKLE,
                            (i, p) -> Animations.BIPED_COMMON_NEUTRALIZED)
                    .addAdvancedGuardMotion(DawnDayWeaponCategories.SICKLE,
                            (i, p) -> List.of(
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT1,
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            ));

            builder.addGuardMotion(
                    DawnDayWeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_HIT
            ).addGuardBreakMotion(DawnDayWeaponCategories.IRON_FIST,
                    (i, p) -> DawnDayAnimations.IRON_FIST_GUARD_BREAK)
                    .addAdvancedGuardMotion(DawnDayWeaponCategories.IRON_FIST,
                            (i, p) -> List.of(
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT1,
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            ));

            builder.addGuardMotion(
                            DawnDayWeaponCategories.FLORETT,
                            (i, p) -> DawnDayAnimations.FLORETT_DUAL_GUARD_HIT
                    ).addGuardBreakMotion(DawnDayWeaponCategories.FLORETT,
                            (i, p) -> DawnDayAnimations.FLORETT_DUAL_NEUTRALIZED)
                    .addAdvancedGuardMotion(DawnDayWeaponCategories.FLORETT,
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
                            DawnDayWeaponCategories.RITUS_DAGGER,
                            (i, p) -> DawnDayAnimations.RITUS_DAGGER_GUARD_HIT
                    ).addGuardBreakMotion(DawnDayWeaponCategories.RITUS_DAGGER,
                            (i, p) -> DawnDayAnimations.RITUS_DAGGER_NEUTRALIZED)
                    .addAdvancedGuardMotion(DawnDayWeaponCategories.RITUS_DAGGER,
                            (i, p) -> List.of(
                                    DawnDayAnimations.RITUS_DAGGER_PARRY1,
                                    DawnDayAnimations.RITUS_DAGGER_PARRY2
                            ));
            builder.addGuardMotion(
                            DawnDayWeaponCategories.CLAWS,
                            (i, p) -> DawnDayAnimations.HOOKCLAWS_GUARD_HIT
                    ).addGuardBreakMotion(DawnDayWeaponCategories.CLAWS,
                            (i, p) -> DawnDayAnimations.HOOKCLAWS_NEUTRALIZE)
                    .addAdvancedGuardMotion(DawnDayWeaponCategories.CLAWS,
                            (i, p) -> List.of(
                                    DawnDayAnimations.HOOKCLAWS_PARRY1,
                                    DawnDayAnimations.HOOKCLAWS_PARRY2
                            ));
        }

    }
    @SubscribeEvent
    public static void onSwordSkillCreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<SwordmasterSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","swordmaster"))) {
                SwordmasterSkill.Builder builder = event.getSkillBuilder();
            builder.addAvailableWeaponCategory(DawnDayWeaponCategories.RITUS_DAGGER)
                    .addAvailableWeaponCategory(DawnDayWeaponCategories.FLORETT)
                    .addAvailableWeaponCategory(DawnDayWeaponCategories.CLAWS)
                    .addAvailableWeaponCategory(DawnDayWeaponCategories.SICKLE)
                    .addAvailableWeaponCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD);
        }
    }
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onIconCreate(WeaponCategoryIconRegisterEvent icon){
        icon.registerCategory(DawnDayWeaponCategories.LIGHT_GREATSWORD, new ItemStack(DawnDayItems.iron_light_greatsword.get()));
        icon.registerCategory(DawnDayWeaponCategories.RITUS_DAGGER, new ItemStack(DawnDayItems.BLOOD_RITUS_DAGGER.get()));
        icon.registerCategory(DawnDayWeaponCategories.FLORETT, new ItemStack(DawnDayItems.IRON_FLORETT.get()));
        icon.registerCategory(DawnDayWeaponCategories.SICKLE, new ItemStack(DawnDayItems.IRON_SICKLE.get()));
        icon.registerCategory(DawnDayWeaponCategories.BAT, new ItemStack(DawnDayItems.spiked_bat.get()));
        icon.registerCategory(DawnDayWeaponCategories.CLAWS, new ItemStack(DawnDayItems.HOOKCLAWS.get()));
        icon.registerCategory(DawnDayWeaponCategories.IRON_FIST, new ItemStack(DawnDayItems.IRON_FIST.get()));

    }



}
