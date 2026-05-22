package net.dawn_day.skill.skill_compats;


//public class evil_tachi_skillcompats {
//    public static void forceGuard() {
//    }
//
//    public static void onGuardSkillCreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<GuardSkill.Builder> event) {
//        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight", "guard"))) {
//            GuardSkill.Builder builder = event.getSkillBuilder();
//            builder.addGuardMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) -> AdditionalAnimations.EVIL_ODACHI_GUARD_HIT)
//                    .addGuardBreakMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) -> AdditionalAnimations.EVIL_ODACHI_NEUTRALIZED);
//        }
//    }
//
//    public static void onParrySkillCreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<ParryingSkill.Builder> event) {
//        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight", "parrying"))) {
//            GuardSkill.Builder builder = event.getSkillBuilder();
//            builder.addGuardMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) -> AdditionalAnimations.EVIL_ODACHI_GUARD_HIT)
//                    .addGuardBreakMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) -> AdditionalAnimations.EVIL_ODACHI_NEUTRALIZED)
//                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) -> List.of(AdditionalAnimations.EVIL_ODACHI_PARRY1, AdditionalAnimations.EVIL_ODACHI_PARRY2));
//        }
//    }
//}
