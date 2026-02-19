package net.epicfight_dd.world.capabilities.item;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.MiladyCollider;
import net.epicfight_dd.gameasset.animation.MiladyMoveset;
import net.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import net.epicfight_dd.gameasset.animation.WingStanceAnims;
import net.epicfight_dd.gameasset.dawnDaySounds;
import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;


import java.util.function.Function;


@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EpicFight_DD_WeaponCapabilityPresets {

    public static final Function<Item, CapabilityItem.Builder> MILADY = (item) -> (CapabilityItem.Builder)
            WeaponCapability.builder()
            .category(EpicFightDD_WeaponCategories.LIGHT_GREATSWORD)
            .styleProvider((pp) ->
                    {
                        if (pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == EpicFightDD_WeaponCategories.LIGHT_GREATSWORD) {
                            return CapabilityItem.Styles.TWO_HAND;
                        } else if (pp instanceof PlayerPatch<?> playerpatch && (playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().hasData(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get()) &&
                                playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().getDataValue(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get()))) {
                            return CapabilityItem.Styles.OCHS;
                        } else {
                            return CapabilityItem.Styles.ONE_HAND;
                        }

                    }

            )
            .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == EpicFightDD_WeaponCategories.LIGHT_GREATSWORD)
            .collider(MiladyCollider.LIGHT_GREATSWORD)
            //.passiveSkill(DawnDaySkills.WINGSTANCE)

            .swingSound(dawnDaySounds.Milady_light_slash.get())
            .hitSound(EpicFightSounds.BLADE_HIT.get())
            .canBePlacedOffhand(true)
            .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.FURIOUS_CUT)
            .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.GENTLE_NUDGE)
            .innateSkill(CapabilityItem.Styles.OCHS, ip -> DawnDaySkills.FURIOUS_CUT)

            .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO1,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO2,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO3,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO4,
                    MiladyMoveset.MILADY_TWOHANDED_DASH,
                    MiladyMoveset.MILADY_SPECIAL_AUTO1)

            .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                    MiladyMoveset.MILADY_SPECIAL_AUTO1,
                    MiladyMoveset.MILADY_SPECIAL_AUTO2,
                    MiladyMoveset.MILADY_SPECIAL_AUTO3,
                    MiladyMoveset.MILADY_SPECIAL_AUTO4,
                    MiladyMoveset.MILADY_SPECIAL_AUTO5,
                    MiladyMoveset.MILADY_SPECIAL_DASH,
                    MiladyMoveset.MILADY_DUAL_AIRSLASH
            )

            .newStyleCombo(CapabilityItem.Styles.OCHS,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO1,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO2,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO3,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO4,
                    MiladyMoveset.MILADY_TWOHANDED_DASH,
                    MiladyMoveset.MILADY_SPECIAL_AUTO1
            )

            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, MiladyMoveset.MILADY_TWOHANDED_IS_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, MiladyMoveset.MILADY_ONEHANDED_RUN)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, MiladyMoveset.MILADY_SPECIAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_SPECIAL_WALK)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_TWOHANDED_WALK)
            .livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.DEATH, QoLMiscAnimations.EXPRESSIVE_DEATH)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)

            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, WingStanceAnims.WING_STANCE_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, WingStanceAnims.WINGSTANCE_WALK)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD);


    public static final Function<Item, CapabilityItem.Builder> LIGHT_GREATSWORD = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.LONGSWORD)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.LONGSWORD ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .collider(MiladyCollider.LIGHT_GREATSWORD)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.PIERCING_FANG)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.GENTLE_NUDGE)
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == CapabilityItem.WeaponCategories.LONGSWORD)
                    .swingSound(dawnDaySounds.Milady_light_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            MiladyMoveset.MILADY_ONE_HANDED_AUTO_1,
                            MiladyMoveset.MILADY_ONE_HANDED_AUTO_2,
                            MiladyMoveset.MILADY_ONE_HANDED_AUTO_3,
                            MiladyMoveset.MILADY_ONE_HANDED_AUTO_4,
                            MiladyMoveset.MILADY_ONE_HANDED_AUTO_5,
                            MiladyMoveset.milady_onehanded_dash,
                            MiladyMoveset.MILADY_AIR_SLASH
                    )
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            MiladyMoveset.MILADY_DUAL_AUTO1,
                            MiladyMoveset.MILADY_DUAL_AUTO2,
                            MiladyMoveset.MILADY_DUAL_AUTO3,
                            MiladyMoveset.MILADY_DUAL_AUTO4,
                            MiladyMoveset.MILADY_DUAL_AUTO5,
                            MiladyMoveset.MILADY_DUAL_DASH,
                            MiladyMoveset.MILADY_DUAL_AIRSLASH)
                    .canBePlacedOffhand(true)

                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, MiladyMoveset.MILADY_DUAL_IS_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, MiladyMoveset.MILADY_IS_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD);


    public static final Function<Item, CapabilityItem.Builder> WAR_SICKLE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(MiladyCollider.WAR_SICKLE)
                    .swingSound(dawnDaySounds.Milady_heavy_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> EpicFightSkills.STEEL_WHIRLWIND)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            MiladyMoveset.WAR_SICKLE_AUTO1,
                            MiladyMoveset.WAR_SICKLE_AUTO2,
                            MiladyMoveset.WAR_SICKLE_AUTO3,
                            MiladyMoveset.WAR_SICKLE_AUTO4,
                            MiladyMoveset.WAR_SICKLE_AUTO5,
                            MiladyMoveset.WAR_SICKLE_AUTO6,
                            MiladyMoveset.POLE_AXE_DASH,
                            MiladyMoveset.MILADY_AIR_SLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, MiladyMoveset.WAR_SICKLE_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_TWOHANDED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);


    public static final Function<Item, CapabilityItem.Builder> BACKHAND_BLADE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.TACHI)
                    .styleProvider((pp) -> CapabilityItem.Styles.ONE_HAND)
                    .collider(MiladyCollider.BACKHAND_BLADE)
                    .swingSound(dawnDaySounds.Milady_dual_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> EpicFightSkills.SHARP_STAB)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            MiladyMoveset.BACKHAND_AUTO1,
                            MiladyMoveset.BACKHAND_AUTO2,
                            MiladyMoveset.BACKHAND_AUTO3,
                            MiladyMoveset.BACKHAND_AUTO4,
                            MiladyMoveset.HEAVY_AXE_DASH,
                            MiladyMoveset.MILADY_SPECIAL_DASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, MiladyMoveset.BACKHAND_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> HALBERD = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(MiladyCollider.HALBERD)
                    .swingSound(dawnDaySounds.Milady_heavy_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.SPEARING_STRIKE)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            MiladyMoveset.HALBERD_AUTO1,
                            MiladyMoveset.HALBERD_AUTO2,
                            MiladyMoveset.HALBERD_AUTO3,
                            MiladyMoveset.HALBERD_AUTO4,
                            MiladyMoveset.HALBERD_DASH,
                            MiladyMoveset.MILADY_AIR_SLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, MiladyMoveset.HALBEARD_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static final Function<Item, CapabilityItem.Builder> BATTLE_STAFF = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(MiladyCollider.BATTLESTAFF_FRONT)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.SKULL_RUPTURE)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            MiladyMoveset.BATTLESTAFF_AUTO1,
                            MiladyMoveset.BATTLESTAFF_AUTO2,
                            MiladyMoveset.BATTLESTAFF_AUTO3,
                            MiladyMoveset.BATTLESTAFF_AUTO4,
                            MiladyMoveset.BATTLESTAFF_DASH,
                            MiladyMoveset.MILADY_DUAL_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, MiladyMoveset.BATTLESTAFF_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);


    @SubscribeEvent // register Weapon Moveset
    public static void WeaponMovesetRegister(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "milady"), MILADY);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "war_sickle"), WAR_SICKLE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "halberd"), HALBERD);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "battlestaff"), BATTLE_STAFF);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "backhand_blade"), BACKHAND_BLADE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "light_greatsword"), LIGHT_GREATSWORD);
    }

}
