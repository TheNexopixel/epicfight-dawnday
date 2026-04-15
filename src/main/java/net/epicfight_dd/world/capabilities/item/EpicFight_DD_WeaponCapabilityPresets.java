package net.epicfight_dd.world.capabilities.item;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.*;
import net.epicfight_dd.gameasset.dawnDaySounds;
import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import reascer.wom.gameasset.WOMSkills;
import reascer.wom.particle.WOMParticles;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.particle.HitParticleType;
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
            .collider(DawnDayCollider.LIGHT_GREATSWORD)
                   .passiveSkill(DawnDaySkills.WINGSTANCE)

            .swingSound(dawnDaySounds.Milady_light_slash.get())
            .hitSound(EpicFightSounds.BLADE_HIT.get())
            .canBePlacedOffhand(true)
            .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.PIERCING_FANG)
            .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.GENTLE_NUDGE)
            .innateSkill(CapabilityItem.Styles.OCHS, ip -> DawnDaySkills.FURIOUS_CUT)

            .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                    DawnDayAnimations.MILADY_ONE_HANDED_AUTO_1,
                    DawnDayAnimations.MILADY_ONE_HANDED_AUTO_2,
                    DawnDayAnimations.MILADY_ONE_HANDED_AUTO_3,
                    DawnDayAnimations.MILADY_ONE_HANDED_AUTO_4,
                    DawnDayAnimations.MILADY_ONE_HANDED_AUTO_5,
                    DawnDayAnimations.MILADY_DASH_NEW,
                    DawnDayAnimations.MILADY_AIR_SLASH)

            .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                    DawnDayAnimations.MILADY_SPECIAL_AUTO1,
                    DawnDayAnimations.MILADY_SPECIAL_AUTO2,
                    DawnDayAnimations.MILADY_SPECIAL_AUTO3,
                    DawnDayAnimations.MILADY_SPECIAL_AUTO4,
                    DawnDayAnimations.MILADY_SPECIAL_AUTO5,
                    DawnDayAnimations.MILADY_SPECIAL_DASH,
                    DawnDayAnimations.MILADY_DUAL_AIRSLASH
            )

            .newStyleCombo(CapabilityItem.Styles.OCHS,
                    WingStanceAnims.WINGSTANCE_ATTACK_1,
                    WingStanceAnims.WINGSTANCE_ATTACK_2,
                    WingStanceAnims.WINGSTANCE_ATTACK_3,
                    WingStanceAnims.WINGSTANCE_ATTACK_5,
                    WingStanceAnims.WINGSTANCE_DASH,
                    WingStanceAnims.WINGSTANCE_AIRSLASH
            )

            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.MILADY_IS_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.MILADY_SPECIAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_SPECIAL_WALK)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.DEATH, QoLMiscAnimations.EXPRESSIVE_DEATH)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)

            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, WingStanceAnims.WING_STANCE_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, WingStanceAnims.WINGSTANCE_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.RUN, WingStanceAnims.WINGSTANCE_RUN)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, Animations.SWORD_GUARD);


    public static final Function<Item, CapabilityItem.Builder> LIGHT_GREATSWORD = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.LONGSWORD)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.LONGSWORD ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .collider(DawnDayCollider.LIGHT_GREATSWORD)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.FURIOUS_CUT)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.GENTLE_NUDGE)
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == CapabilityItem.WeaponCategories.LONGSWORD)
                    .swingSound(dawnDaySounds.Milady_light_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.MILADY_TWOHANDED_AUTO1,
                            DawnDayAnimations.MILADY_TWOHANDED_AUTO2,
                            DawnDayAnimations.MILADY_TWOHANDED_AUTO3,
                            DawnDayAnimations.MILADY_TWOHANDED_AUTO4,
                            DawnDayAnimations.MILADY_TWOHANDED_DASH,
                            DawnDayAnimations.MILADY_AIR_SLASH
                    )
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.MILADY_DUAL_AUTO1,
                            DawnDayAnimations.MILADY_DUAL_AUTO2,
                            DawnDayAnimations.MILADY_DUAL_AUTO3,
                            DawnDayAnimations.MILADY_DUAL_AUTO4,
                            DawnDayAnimations.MILADY_DUAL_AUTO5,
                            DawnDayAnimations.MILADY_DUAL_DASH,
                            DawnDayAnimations.MILADY_DUAL_AIRSLASH)
                    .canBePlacedOffhand(true)

                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.MILADY_DUAL_IS_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.MILADY_TWOHANDED_IS_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_TWOHANDED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD);


    public static final Function<Item, CapabilityItem.Builder> WAR_SICKLE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.WAR_SICKLE)
                    .swingSound(dawnDaySounds.Milady_heavy_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> EpicFightSkills.STEEL_WHIRLWIND)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.WAR_SICKLE_AUTO1,
                            DawnDayAnimations.WAR_SICKLE_AUTO2,
                            DawnDayAnimations.WAR_SICKLE_AUTO3,
                            DawnDayAnimations.WAR_SICKLE_AUTO4,
                            DawnDayAnimations.WAR_SICKLE_AUTO5,
                            DawnDayAnimations.WAR_SICKLE_AUTO6,
                            DawnDayAnimations.POLE_AXE_DASH,
                            DawnDayAnimations.MILADY_AIR_SLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.WAR_SICKLE_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_TWOHANDED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);


    public static final Function<Item, CapabilityItem.Builder> BACKHAND_BLADE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.DAGGER)
                    .styleProvider((pp) -> CapabilityItem.Styles.ONE_HAND)
                    .collider(DawnDayCollider.BACKHAND_BLADE)
                    .swingSound(dawnDaySounds.Milady_light_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.SPINNING_SHADOW)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.BACKHAND_AUTO1,
                            DawnDayAnimations.BACKHAND_AUTO2,
                            DawnDayAnimations.BACKHAND_AUTO3,
                            DawnDayAnimations.BACKHAND_AUTO4,
                            DawnDayAnimations.BACKHAND_DASH,
                            DawnDayAnimations.KNIFE_DUAL_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.BACKHAND_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, Animations.BIPED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> HALBERD = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.HALBERD)
                    .swingSound(dawnDaySounds.Milady_heavy_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.SPEARING_STRIKE)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.HALBERD_AUTO1,
                            DawnDayAnimations.HALBERD_AUTO2,
                            DawnDayAnimations.HALBERD_AUTO3,
                            DawnDayAnimations.HALBERD_AUTO4,
                            DawnDayAnimations.HALBERD_DASH,
                            DawnDayAnimations.MILADY_AIR_SLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.HALBEARD_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static final Function<Item, CapabilityItem.Builder> BATTLE_STAFF = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BATTLESTAFF_FRONT)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLUNT.get())
                    .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.SKULL_RUPTURE)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.BATTLESTAFF_AUTO1,
                            DawnDayAnimations.BATTLESTAFF_AUTO2,
                            DawnDayAnimations.BATTLESTAFF_AUTO3,
                            DawnDayAnimations.BATTLESTAFF_AUTO4,
                            DawnDayAnimations.BATTLESTAFF_AUTO5,
                            DawnDayAnimations.BATTLESTAFF_DASH,
                            DawnDayAnimations.BATTLESTAFF_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.BATTLESTAFF_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static final Function<Item, CapabilityItem.Builder> BATTLE_STAFF_WOOD = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BATTLESTAFF_FRONT)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLUNT.get())
                    .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.GROUNDSLAM)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.BATTLESTAFF_AUTO1,
                            DawnDayAnimations.BATTLESTAFF_AUTO2,
                            DawnDayAnimations.BATTLESTAFF_AUTO3,
                            DawnDayAnimations.BATTLESTAFF_AUTO4,
                            DawnDayAnimations.BATTLESTAFF_AUTO5,
                            DawnDayAnimations.BATTLESTAFF_DASH,
                            DawnDayAnimations.BATTLESTAFF_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.BATTLESTAFF_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static final Function<Item, CapabilityItem.Builder> HERB_SICKLE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.AXE)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.AXE ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == CapabilityItem.WeaponCategories.AXE)
                    .collider(ColliderPreset.SWORD)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.QUICK_RUSH)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.WHIRLWIND)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.SICKLE_AUTO1,
                            DawnDayAnimations.SICKLE_AUTO2,
                            DawnDayAnimations.SICKLE_AUTO3,
                            DawnDayAnimations.SICKLE_AUTO4,
                            DawnDayAnimations.SICKLE_DASH,
                            DawnDayAnimations.SICKLE_AIRSLASH)

                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.SICKLE_DUAL_AUTO1,
                            DawnDayAnimations.SICKLE_DUAL_AUTO2,
                            DawnDayAnimations.SICKLE_DUAL_AUTO3,
                            DawnDayAnimations.MILADY_DUAL_DASH,
                            DawnDayAnimations.SICKLE_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.SICKLE_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.SICKLE_DUAL_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, Animations.BIPED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD);

    public static final Function<Item, CapabilityItem.Builder> STEEL_AXE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.AXE)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(ColliderPreset.LONGSWORD)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.SPEARING_STRIKE)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.STEELAXE_AUTO1,
                            DawnDayAnimations.STEELAXE_AUTO2,
                            DawnDayAnimations.STEELAXE_AUTO3,
                            DawnDayAnimations.STEELAXE_AUTO4,
                            DawnDayAnimations.HALBERD_DASH,
                            DawnDayAnimations.POLE_AXE_AUTO3)


                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.STEELAXE_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static final Function<Item, CapabilityItem.Builder> KNIFE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.DAGGER)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.DAGGER ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == CapabilityItem.WeaponCategories.DAGGER)
                    .collider(DawnDayCollider.KNIFE)
                    .swingSound(dawnDaySounds.milady_light_sweep.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.IMPAILING_THRUST)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.WHIRLWIND)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.KNIFE_ONEHANDED_AUTO1,
                            DawnDayAnimations.KNIFE_ONEHANDED_AUTO2,
                            DawnDayAnimations.KNIFE_ONEHANDED_AUTO3,
                            DawnDayAnimations.KNIFE_ONEHANDED_AUTO4,
                            DawnDayAnimations.SICKLE_DASH,
                            DawnDayAnimations.SICKLE_AIRSLASH)

                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.KNIFE_DUAL_AUTO1,
                            DawnDayAnimations.KNIFE_DUAL_AUTO2,
                            DawnDayAnimations.SICKLE_DUAL_AUTO3,
                            DawnDayAnimations.KNIFE_DUAL_DASH,
                            DawnDayAnimations.KNIFE_DUAL_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.KNIFE_ONEHANDED_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.KNIFE_DUAL_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, Animations.BIPED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> SABER = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.TACHI)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.LIGHT_GREATSWORD)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.WINGSTANCE)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.SABER_AUTO1,
                            DawnDayAnimations.SABER_AUTO2,
                            DawnDayAnimations.STEELAXE_AUTO3,
                            DawnDayAnimations.STEELAXE_AUTO4,
                            DawnDayAnimations.HALBERD_DASH,
                            DawnDayAnimations.POLE_AXE_AUTO3)


                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.SABER_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static HitParticleType randomSlashHitParticleTYPE(){
        int randomInt = (int)(Math.random() * (double)4.0F);
        HitParticleType RandomHitParticle;
        switch (randomInt) {
            case 0 -> RandomHitParticle = WOMParticles.SHARPCUT_SLASH.get();
            case 1 -> RandomHitParticle = WOMParticles.SHARPCUT_LEFT_SLASH.get();
            case 2 -> RandomHitParticle = WOMParticles.SHARPCUT_RIGHT_SLASH.get();
            case 3 -> RandomHitParticle = WOMParticles.SHARPCUT_ANGLED_UP_RIGHT_SLASH.get();
            default -> RandomHitParticle = WOMParticles.SHARPCUT_ANGLED_DOWN_LEFT_SLASH.get();
        }
        return RandomHitParticle;
    }


    public static final Function<Item, CapabilityItem.Builder> EVIL_TACHI = (item) ->
            WeaponCapability.builder()
                    .category(EpicFightDD_WeaponCategories.EVIL_TACHI)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.EVIL_TACHI)
                    .hitParticle(randomSlashHitParticleTYPE())
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .passiveSkill(WOMSkills.EVIL_TACHI_PASSIVE)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.EVIL_BEAAAAMMMM)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.EVIL_ODACHI_AUTO1,
                            DawnDayAnimations.EVIL_ODACHI_AUTO2,
                            DawnDayAnimations.EVIL_ODACHI_AUTO3,
                            DawnDayAnimations.EVIL_ODACHI_AUTO4,
                            DawnDayAnimations.EVIL_ODACHI_DASH,
                            DawnDayAnimations.EVIL_ODACHI_AIRSLASH)


                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.EVIL_ODACHI_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.EVIL_ODACHI_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, DawnDayAnimations.EVIL_ODACHI_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.DEATH, QoLMiscAnimations.WITHERING_DEMISE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.JUMP, DawnDayAnimations.EVIL_ODACHI_JUMP)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, DawnDayAnimations.EVIL_ODACHI_GUARD);

    public static final Function<Item, CapabilityItem.Builder> BAT = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.GREATSWORD)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BAT)
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitParticle(EpicFightParticles.HIT_BLUNT.get())
                    .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.BRUTAL_DASH)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.BAT_AUTO1,
                            DawnDayAnimations.BAT_AUTO2,
                            DawnDayAnimations.BAT_AUTO3,
                            DawnDayAnimations.BAT_DASH,
                            DawnDayAnimations.BAT_AIRSLASH)


                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.BAT_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> BONECUTTING_SAW = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.GREATSWORD)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BAT_LONGER)
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.BRUTAL_DASH)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.BAT_AUTO1,
                            DawnDayAnimations.BAT_AUTO2,
                            DawnDayAnimations.BAT_AUTO3,
                            DawnDayAnimations.BAT_DASH,
                            DawnDayAnimations.BAT_AIRSLASH)


                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.BAT_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD);


    @SubscribeEvent // register Weapon Moveset
    public static void WeaponMovesetRegister(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "milady"), MILADY);
        if (ModList.get().isLoaded("wom")) {
            event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "evil_tachi"), EVIL_TACHI);
        }
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "saber"), SABER);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "bonecutting_saw"), BONECUTTING_SAW);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "nailbat"), BAT);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "knife"), KNIFE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "steelaxe"), STEEL_AXE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "herb_sickle"), HERB_SICKLE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "war_sickle"), WAR_SICKLE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "halberd"), HALBERD);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "battlestaff"), BATTLE_STAFF);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "battlestaff_wood"), BATTLE_STAFF_WOOD);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "backhand_blade"), BACKHAND_BLADE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "light_greatsword"), LIGHT_GREATSWORD);
    }




}
