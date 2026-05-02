package net.epicfight_dd.skill.skill_compats;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.epicfight_dd.world.capabilities.item.EpicFightDD_WeaponCategories;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;

import java.util.List;


@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class evil_tachi_skillcompats {
    public static void forceGuard(SkillBuildEvent bus) {
    }

    @SubscribeEvent
    public static void onGuardSkillCreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<GuardSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight", "guard"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();
            builder.addGuardMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) -> DawnDayAnimations.EVIL_ODACHI_GUARD_HIT)
                    .addGuardBreakMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) -> DawnDayAnimations.EVIL_ODACHI_NEUTRALIZED);
        }
    }

    @SubscribeEvent
    public static void onParrySkillCreate(SkillBuildEvent.ModRegistryWorker.SkillCreateEvent<ParryingSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight", "parrying"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();
            builder.addGuardMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) -> DawnDayAnimations.EVIL_ODACHI_GUARD_HIT)
                    .addGuardBreakMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) -> DawnDayAnimations.EVIL_ODACHI_NEUTRALIZED)
                    .addAdvancedGuardMotion(EpicFightDD_WeaponCategories.EVIL_TACHI, (item, player) -> List.of(DawnDayAnimations.EVIL_ODACHI_PARRY1, DawnDayAnimations.EVIL_ODACHI_PARRY2));
        }
    }
}
