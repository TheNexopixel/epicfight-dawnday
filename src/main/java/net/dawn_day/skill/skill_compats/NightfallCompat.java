package net.dawn_day.skill.skill_compats;

//public class NightfallCompat implements ICompatModule {
//
//
//    public static void onEnhancedParry(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<GuardSkill.Builder> event) {
//        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("efn", "efn_parry"))) {
//            GuardSkill.Builder builder = event.getSkillBuilder();
//            builder
//
//                    .addGuardMotion(
//                            EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
//                            (i, p) -> Animations.LONGSWORD_GUARD_HIT
//                    ).addGuardBreakMotion(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD,
//                            (i, p) -> Animations.GREATSWORD_GUARD_BREAK)
//
//                    .addGuardMotion(
//                            EpicFightDD_WeaponCategories.EVIL_TACHI,
//                            (i, p) -> AdditionalAnimations.EVIL_ODACHI_GUARD_HIT
//                    ).addGuardBreakMotion(EpicFightDD_WeaponCategories.EVIL_TACHI,
//                            (i, p) -> AdditionalAnimations.EVIL_ODACHI_NEUTRALIZED)
//
//                    .addGuardMotion(
//                            EpicFightDD_WeaponCategories.RITUS_DAGGER,
//                            (i, p) -> DawnDayAnimations.RITUS_DAGGER_GUARD_HIT
//                    ).addGuardBreakMotion(EpicFightDD_WeaponCategories.RITUS_DAGGER,
//                            (i, p) -> DawnDayAnimations.RITUS_DAGGER_NEUTRALIZED)
//
//                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.RITUS_DAGGER, ((capabilityItem, pp) ->
//                            List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))
//
//                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, ((capabilityItem, pp) ->
//                            List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))
//
//
//                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD, ((capabilityItem, pp) ->
//                            List.of(EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT1, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT2, EFNSkillAnimations.EFN_GUARD_ACTIVE_HIT3)))
//
//
//            ;
//
//        }
//    }
//
//        @OnlyIn(Dist.CLIENT)
//        public static void onIconCreate(WeaponCategoryIconRegisterEvent icon){
//
//            icon.registerCategory(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD, new ItemStack(DawnDayItems.iron_light_greatsword.get()));
//            icon.registerCategory(EpicFightDD_WeaponCategories.EVIL_TACHI, new ItemStack(WOMItems.EVIL_TACHI.get()));
//        }
//
//
//
//
//
//    @Override
//    public void onModEventBus(IEventBus eventBus) {
//        eventBus.addGenericListener(GuardSkill.Builder.class,
//                EventPriority.NORMAL,
//                NightfallCompat::onEnhancedParry);
//    }
//
//    @Override
//    public void onForgeEventBus(IEventBus eventBus) {
//
//    }
//
//    @Override
//    public void onModEventBusClient(IEventBus eventBus) {
//        eventBus.addListener(NightfallCompat::onIconCreate);
//    }
//
//    @Override
//    public void onForgeEventBusClient(IEventBus eventBus) {
//
//    }
//}
