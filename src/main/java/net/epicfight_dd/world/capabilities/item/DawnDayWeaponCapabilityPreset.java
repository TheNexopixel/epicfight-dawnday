package net.epicfight_dd.world.capabilities.item;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.*;
import net.epicfight_dd.gameasset.DawnDaySounds;
import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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


@SuppressWarnings({"removal", "deprecation"})
@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DawnDayWeaponCapabilityPreset {


    public static final Function<Item, CapabilityItem.Builder> MILADY = (item) -> (CapabilityItem.Builder)
            WeaponCapability.builder()
            .category(DawnDayWeaponCategories.LIGHT_GREATSWORD)
            .styleProvider((pp) ->
                    {
                        if (pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == DawnDayWeaponCategories.LIGHT_GREATSWORD) {
                            return CapabilityItem.Styles.TWO_HAND;
                        } else if (pp instanceof PlayerPatch<?> playerpatch && (playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().hasData(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get()) &&
                                playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().getDataValue(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get()))) {
                            return CapabilityItem.Styles.OCHS;
                        } else {
                            return CapabilityItem.Styles.ONE_HAND;
                        }

                    }

            )
            .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == DawnDayWeaponCategories.LIGHT_GREATSWORD)
            .collider(DawnDayCollider.LIGHT_GREATSWORD)
                   .passiveSkill(DawnDaySkills.WINGSTANCE)

            .swingSound(DawnDaySounds.Milady_light_slash.get())
            .hitSound(EpicFightSounds.BLADE_HIT.get())
            .canBePlacedOffhand(true)
            .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.PIERCING_STRIKE)
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
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
            .livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.DEATH, QoLMiscAnimations.EXPRESSIVE_DEATH)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)

            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, WingStanceAnims.WING_STANCE_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, WingStanceAnims.WINGSTANCE_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.RUN, WingStanceAnims.WINGSTANCE_RUN)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, Animations.SWORD_GUARD);

@SuppressWarnings("deprecation")
    public static final Function<Item, CapabilityItem.Builder> LIGHT_GREATSWORD = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.LONGSWORD)
                    .styleProvider((pp) ->
                            {
                                if (pp.getHoldingItemCapability(InteractionHand.OFF_HAND)
                                        .getWeaponCategory() == CapabilityItem.WeaponCategories.LONGSWORD) {
                                    return CapabilityItem.Styles.TWO_HAND;
                                } else if (pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.SWORD ||
                                        pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == DawnDayWeaponCategories.SICKLE){
                                    return CapabilityItem.Styles.OCHS;
                                } else {
                                    return CapabilityItem.Styles.ONE_HAND;
                                }

                            }

                    )
                    .collider(DawnDayCollider.LIGHT_GREATSWORD)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.PIERCING_STRIKE)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.GENTLE_NUDGE)
                    .innateSkill(CapabilityItem.Styles.OCHS, ip -> DawnDaySkills.GENTLE_NUDGE)
                    .weaponCombinationPredicator((entityPatch) ->
                            EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == CapabilityItem.WeaponCategories.LONGSWORD ||
                                    EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == CapabilityItem.WeaponCategories.SWORD
                                    ||
                                    EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == DawnDayWeaponCategories.SICKLE
                    )

                    .swingSound(DawnDaySounds.Milady_light_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.MILADY_TWOHANDED_AUTO1,
                            DawnDayAnimations.MILADY_TWOHANDED_AUTO2,
                            DawnDayAnimations.MILADY_TWOHANDED_AUTO3,
                            DawnDayAnimations.MILADY_TWOHANDED_AUTO4,
                            DawnDayAnimations.MILADY_DASH_NEW,
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

                    .newStyleCombo(CapabilityItem.Styles.OCHS,
                            DawnDayAnimations.LIGHTGREATSWORD_SWORD_ATT1,
                            DawnDayAnimations.LIGHTGREATSWORD_SWORD_ATT2,
                            DawnDayAnimations.LIGHTGREATSWORD_SWORD_ATT3,
                            DawnDayAnimations.LIGHTGREATSWORD_SWORD_ATT4,
                            DawnDayAnimations.MILADY_SPECIAL_AUTO1,
                            DawnDayAnimations.LIGHTGREATSWORD_SWORD_AIRSLASH)
                    .canBePlacedOffhand(true)

                    .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, DawnDayAnimations.LIGHTGREATSWORD_SWORD_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, DawnDayAnimations.LIGHTGREATSWORD_SWORD_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.RUN, DawnDayAnimations.LIGHTGREATSWORD_SWORD_RUN)

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
                    .collider(DawnDayCollider.WARSICKLE)
                    .swingSound(DawnDaySounds.Milady_heavy_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.CHAMPIONS_MIGHT)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.WARSICKLE_ATT1,
                            DawnDayAnimations.WARSICKLE_ATT2,
                            DawnDayAnimations.WARSICKLE_ATT3,
                            DawnDayAnimations.SICKLE_ONEHAND_DASH,
                            DawnDayAnimations.BAT_DASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.WARSICKLE_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> POLEBLADE = (item) ->
            WeaponCapability.builder()
                    .category(DawnDayWeaponCategories.POLEBLADE)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.POLEBLADE)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.BLOOD_DANCE)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.POLEBLADE_AUTO1,
                            DawnDayAnimations.POLEBLADE_AUTO2,
                            DawnDayAnimations.POLEBLADE_AUTO3,
                            DawnDayAnimations.POLEBLADE_AUTO4,
                            DawnDayAnimations.POLEBLADE_DASH,
                            DawnDayAnimations.POLEBLADE_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.POLEBLADE_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.POLEBLADE_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, DawnDayAnimations.POLEBLADE_GUARD);


    public static final Function<Item, CapabilityItem.Builder> BACKHAND_BLADE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.DAGGER)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BACKHAND_BLADE)
                    .swingSound(DawnDaySounds.Milady_light_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                  //  .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.SPINNING_SHADOW)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.BACKHAND_BLADE_AUTO1,
                            DawnDayAnimations.BACKHAND_BLADE_AUTO2,
                            DawnDayAnimations.BACKHAND_BLADE_AUTO3,
                            DawnDayAnimations.BACKHAND_BLADE_AUTO4,
                            DawnDayAnimations.BACKHAND_DASH,
                            DawnDayAnimations.KNIFE_DUAL_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.BACKHAND_BLADE_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.BACKHAND_BLADE_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, DawnDayAnimations.BACKHAND_BLADE_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> HALBERD = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.HALBERD)
                    .swingSound(DawnDaySounds.Milady_heavy_slash.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.SPEARING_STRIKE)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.HALBERD_AUTO1,
                            DawnDayAnimations.HALBERD_AUTO2,
                            DawnDayAnimations.HALBERD_AUTO3,
                            DawnDayAnimations.HALBERD_AUTO4,
                            DawnDayAnimations.IUDEX_HALBERD_DASH,
                            DawnDayAnimations.IUDEX_HALBERD_AUTO1
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.HALBERD_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.IUDEX_HALBERD_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, DawnDayAnimations.IUDEX_HALBERD_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static final Function<Item, CapabilityItem.Builder> BATTLE_STAFF_NETHERITE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BATTLESTAFF_FULL)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())

                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.SKULL_RUPTURE)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.BATTLESTAFF_AUTO1,
                            DawnDayAnimations.BATTLESTAFF_AUTO2,
                            DawnDayAnimations.BATTLESTAFF_AUTO3,
                            DawnDayAnimations.BATTLESTAFF_AUTO4,
                            DawnDayAnimations.POLEBLADE_DASH,
                            DawnDayAnimations.POLEBLADE_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.BATTLESTAFF_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.POLEBLADE_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static final Function<Item, CapabilityItem.Builder> BATTLE_STAFF = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BATTLESTAFF_FULL)
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
                            DawnDayAnimations.POLEBLADE_DASH,
                            DawnDayAnimations.POLEBLADE_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.BATTLESTAFF_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.POLEBLADE_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static final Function<Item, CapabilityItem.Builder> BATTLE_STAFF_AMETHIST = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BATTLESTAFF_FULL)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLUNT.get())
                    .hitSound(SoundEvents.AMETHYST_BLOCK_HIT)

                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.GROUNDSLAM)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.BATTLESTAFF_AUTO1,
                            DawnDayAnimations.BATTLESTAFF_AUTO2,
                            DawnDayAnimations.BATTLESTAFF_AUTO3,
                            DawnDayAnimations.BATTLESTAFF_AUTO4,
                            DawnDayAnimations.POLEBLADE_DASH,
                            DawnDayAnimations.POLEBLADE_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.BATTLESTAFF_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.POLEBLADE_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);



    public static final Function<Item, CapabilityItem.Builder> BATTLE_STAFF_WOOD = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BATTLESTAFF_FULL)
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
                            DawnDayAnimations.POLEBLADE_DASH,
                            DawnDayAnimations.BATTLESTAFF_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.BATTLESTAFF_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static final Function<Item, CapabilityItem.Builder> HERB_SICKLE = (item) ->
            WeaponCapability.builder()
                    .category(DawnDayWeaponCategories.SICKLE)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == DawnDayWeaponCategories.SICKLE ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == DawnDayWeaponCategories.SICKLE)
                    .collider(DawnDayCollider.SICKLE)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.QUICK_STEP)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.WHIRLWIND)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.SICKLE_ONEHAND_AUTO1,
                            DawnDayAnimations.SICKLE_ONEHAND_AUTO2,
                            DawnDayAnimations.SICKLE_ONEHAND_AUTO3,
                            DawnDayAnimations.SICKLE_ONEHAND_AUTO4,
                            DawnDayAnimations.SICKLE_ONEHAND_DASH,
                            DawnDayAnimations.SICKLE_ONEHAND_AIRSLASH)

                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.SICKLE_DUAL_AUTO1,
                            DawnDayAnimations.SICKLE_DUAL_AUTO2,
                            DawnDayAnimations.SICKLE_DUAL_AUTO3,
                            DawnDayAnimations.SICKLE_DUAL_DASH,
                            DawnDayAnimations.KNIFE_DUAL_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.SICKLE_ONEHAND_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.SICKLE_ONEHAND_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, DawnDayAnimations.SICKLE_ONEHAND_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD)

                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.SICKLE_ONEHAND_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, DawnDayAnimations.SICKLE_ONEHAND_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.SICKLE_DUAL_IDLE);

    public static final Function<Item, CapabilityItem.Builder> STEEL_AXE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
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
                            DawnDayAnimations.IUDEX_HALBERD_AUTO3,
                            DawnDayAnimations.BAT_DASH)


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
                    .swingSound(DawnDaySounds.milady_light_sweep.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.QUICK_RUSH)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.WHIRLWIND)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.KNIFE_ONEHAND_AUTO1,
                            DawnDayAnimations.KNIFE_ONEHAND_AUTO2,
                            DawnDayAnimations.KNIFE_ONEHAND_AUTO3,
                            DawnDayAnimations.KNIFE_ONEHAND_AUTO4,
                            DawnDayAnimations.SICKLE_ONEHAND_DASH,
                            DawnDayAnimations.SICKLE_ONEHAND_AIRSLASH)

                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.SICKLE_DUAL_AUTO1,
                            DawnDayAnimations.SICKLE_DUAL_AUTO2,
                            DawnDayAnimations.SICKLE_DUAL_AUTO3,
                            DawnDayAnimations.KNIFE_DUAL_DASH,
                            DawnDayAnimations.KNIFE_DUAL_AIRSLASH
                    )
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.KNIFE_ONEHAND_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.SICKLE_DUAL_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.SICKLE_ONEHAND_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.VITR_WALK)
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
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.WILD_STRIKES)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.SABER_AUTO1,
                            DawnDayAnimations.SABER_AUTO2,
                            DawnDayAnimations.SABER_AUTO3,
                            DawnDayAnimations.SABER_AUTO4,
                            DawnDayAnimations.SABER_AUTO5,
                            DawnDayAnimations.SABER_AUTO6,
                            DawnDayAnimations.SABER_DASH,
                            DawnDayAnimations.SABER_AIRSLASH)


                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.SABER_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> MESSER = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SWORD)
                    .styleProvider((pp) -> CapabilityItem.Styles.ONE_HAND)
                    .collider(DawnDayCollider.KNIFE)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.SWORD ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == CapabilityItem.WeaponCategories.SWORD)
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.WILD_STRIKES)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.WHIRLWIND)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.MESSER_ONEHANDED_AUTO1,
                            DawnDayAnimations.MESSER_ONEHANDED_AUTO2,
                            DawnDayAnimations.MESSER_ONEHANDED_AUTO3,
                            DawnDayAnimations.SICKLE_ONEHAND_DASH,
                            DawnDayAnimations.SABER_AIRSLASH)

                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.MESSER_DUAL_AUTO1,
                            DawnDayAnimations.MESSER_DUAL_AUTO2,
                            DawnDayAnimations.MESSER_DUAL_AUTO3,
                            DawnDayAnimations.KNIFE_DUAL_DASH,
                            DawnDayAnimations.KNIFE_DUAL_AIRSLASH)

                    .newStyleCombo(CapabilityItem.Styles.OCHS,
                            DawnDayAnimations.MILADY_ONE_HANDED_AUTO_1,
                            DawnDayAnimations.MILADY_ONE_HANDED_AUTO_2,
                            DawnDayAnimations.KNIFE_DUAL_DASH,
                            DawnDayAnimations.KNIFE_DUAL_AIRSLASH)


                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.MESSER_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, Animations.BIPED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.MESSER_DUAL_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD);

    public static final Function<Item, CapabilityItem.Builder> BAT = (item) ->
            WeaponCapability.builder()
                    .category(DawnDayWeaponCategories.BAT)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BAT)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == DawnDayWeaponCategories.BAT ? CapabilityItem.Styles.OCHS : CapabilityItem.Styles.TWO_HAND)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == DawnDayWeaponCategories.BAT)
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitParticle(EpicFightParticles.HIT_BLUNT.get())
                    .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.OCHS, ip -> DawnDaySkills.HEAD_KNOCKER)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.BRUTAL_DASH)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.BAT_AUTO1,
                            DawnDayAnimations.BAT_AUTO2,
                            DawnDayAnimations.BAT_AUTO3,
                            DawnDayAnimations.BAT_AUTO4,
                            DawnDayAnimations.BAT_DASH,
                            DawnDayAnimations.BAT_AIRSLASH)

                    .newStyleCombo(CapabilityItem.Styles.OCHS,
                            DawnDayAnimations.NAILBAT_DUAL_AUTO1,
                            DawnDayAnimations.NAILBAT_DUAL_AUTO2,
                            DawnDayAnimations.NAILBAT_DUAL_AUTO3,
                            DawnDayAnimations.NAILBAT_DUAL_DASH,
                            DawnDayAnimations.NAILBAT_DUAL_AIRSLASH)

                    .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, DawnDayAnimations.NAILBAT_DUAL_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, DawnDayAnimations.NAILBAT_DUAL_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.RUN, DawnDayAnimations.NAILBAT_DUAL_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)

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
                            DawnDayAnimations.SAW_AUTO1,
                            DawnDayAnimations.SAW_AUTO2,
                            DawnDayAnimations.SAW_AUTO3,
                            DawnDayAnimations.SAW_AUTO4,
                            DawnDayAnimations.BAT_DASH,
                            DawnDayAnimations.BAT_AIRSLASH)


                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.SAW_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> BLOOD_RITUS_DAGGER = (item) ->
            WeaponCapability.builder()
                    .category(DawnDayWeaponCategories.RITUS_DAGGER)
                    .styleProvider((pp) -> CapabilityItem.Styles.ONE_HAND)
                    .collider(DawnDayCollider.KNIFE)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == DawnDayWeaponCategories.RITUS_DAGGER ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == DawnDayWeaponCategories.RITUS_DAGGER)
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.SEPUKKU)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.WHIRLWIND)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.BLOOD_RITUS_DAGGER_AUTO1,
                            DawnDayAnimations.BLOOD_RITUS_DAGGER_AUTO2,
                            DawnDayAnimations.BLOOD_RITUS_DAGGER_AUTO3,
                            DawnDayAnimations.BLOOD_RITUS_DAGGER_AUTO4,
                            DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO2,
                            DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO1)

                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO1,
                            DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO2,
                            DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO3,
                            DawnDayAnimations.RITUS_DAGGER_DUAL_DASH,
                            DawnDayAnimations.RITUS_DAGGER_DUAL_AIRSLASH)


                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.BLOOD_RITUS_DAGGER_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.BLOOD_RITUS_DAGGER_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.RITUS_DAGGER_DUAL_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.RITUS_DAGGER_DUAL_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD);

    public static final Function<Item, CapabilityItem.Builder> NIGHT_RITUS_DAGGER = (item) ->
            WeaponCapability.builder()
                    .category(DawnDayWeaponCategories.RITUS_DAGGER)
                    .styleProvider((pp) -> CapabilityItem.Styles.ONE_HAND)
                    .collider(DawnDayCollider.KNIFE)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == DawnDayWeaponCategories.RITUS_DAGGER ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == DawnDayWeaponCategories.RITUS_DAGGER)
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.SEPUKKU)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.WHIRLWIND)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO1,
                            DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO2,
                            DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO3,
                            DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO4,
                            DawnDayAnimations.NIGHT_RITUS_DAGGER_DASH,
                            DawnDayAnimations.BLOOD_RITUS_DAGGER_AUTO3)

                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO1,
                            DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO2,
                            DawnDayAnimations.RITUS_DAGGER_DUAL_AUTO3,
                            DawnDayAnimations.RITUS_DAGGER_DUAL_DASH,
                            DawnDayAnimations.RITUS_DAGGER_DUAL_AIRSLASH)



                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.NIGHT_RITUS_DAGGER_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, DawnDayAnimations.RITUS_DAGGER_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.RITUS_DAGGER_DUAL_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.RITUS_DAGGER_DUAL_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD);

    public static final Function<Item, CapabilityItem.Builder> ECLIPSE = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.GREATSWORD)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.BAT_LONGER)
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.GENTLE_NUDGE)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.ECLIPSE_ATTACK1,
                            DawnDayAnimations.ECLIPSE_ATTACK2,
                            DawnDayAnimations.IUDEX_HALBERD_AUTO3,
                            DawnDayAnimations.IUDEX_HALBERD_AUTO4,
                            DawnDayAnimations.IUDEX_HALBERD_AUTO2,
                            DawnDayAnimations.IUDEX_HALBERD_AUTO1)


                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.ECLIPSE_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> IRON_FIST = (item) ->
            WeaponCapability.builder()
                    .category(DawnDayWeaponCategories.IRON_FIST)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == DawnDayWeaponCategories.IRON_FIST ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == DawnDayWeaponCategories.IRON_FIST)
                    .collider(DawnDayCollider.IRON_FIST)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLUNT.get())
                    .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.POWERFUL_KICK)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.POWERFUL_KICK)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.IRON_FIST_AUTO1,
                            DawnDayAnimations.IRON_FIST_AUTO2,
                            DawnDayAnimations.IRON_FIST_AUTO3,
                            DawnDayAnimations.IRON_FIST_AUTO4,
                            DawnDayAnimations.IRON_FIST_AUTO5,
                            DawnDayAnimations.IRON_FIST_DASH,
                            DawnDayAnimations.IRON_FIST_AIRSLASH)

                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.IRON_FIST_AUTO1,
                            DawnDayAnimations.IRON_FIST_AUTO2,
                            DawnDayAnimations.IRON_FIST_AUTO3,
                            DawnDayAnimations.IRON_FIST_AUTO4,
                            DawnDayAnimations.IRON_FIST_AUTO5,
                            DawnDayAnimations.IRON_FIST_DASH,
                            DawnDayAnimations.IRON_FIST_AIRSLASH)


                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.IRON_FIST_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.IRON_FIST_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.IRON_FIST_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, DawnDayAnimations.IRON_FIST_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, DawnDayAnimations.IRON_FIST_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.IRON_FIST_IDLE);

    public static final Function<Item, CapabilityItem.Builder> VITREUS = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.TACHI)
                    .styleProvider((pp) -> CapabilityItem.Styles.ONE_HAND)
                    .collider(DawnDayCollider.BAT_LONGER)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.INCISURA_VITREA)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.VITR_AUTO1,
                            DawnDayAnimations.VITR_AUTO2,
                            DawnDayAnimations.VITR_AUTO3,
                            DawnDayAnimations.VITR_AUTO4,
                            DawnDayAnimations.VITR_DASH,
                            DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO1)


                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.VITR_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.VITR_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR);

    public static final Function<Item, CapabilityItem.Builder> IUDEX_HALBERD = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(DawnDayCollider.IUDEX_HALBERD)
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.CHAMPIONS_MIGHT)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.IUDEX_HALBERD_AUTO1,
                            DawnDayAnimations.IUDEX_HALBERD_AUTO2,
                            DawnDayAnimations.IUDEX_HALBERD_AUTO3,
                            DawnDayAnimations.IUDEX_HALBERD_AUTO4,
                            DawnDayAnimations.IUDEX_HALBERD_DASH,
                            DawnDayAnimations.IUDEX_HALBERD_AIRSLASH)


                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.IUDEX_HALBERD_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.IUDEX_HALBERD_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, DawnDayAnimations.IUDEX_HALBERD_RUN);

    public static final Function<Item, CapabilityItem.Builder> FLORETT = (item) ->
            WeaponCapability.builder()
                    .category(DawnDayWeaponCategories.FLORETT)
                    .styleProvider((pp) -> CapabilityItem.Styles.ONE_HAND)
                    .styleProvider((pp) -> pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory()
                            == DawnDayWeaponCategories.FLORETT ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == DawnDayWeaponCategories.FLORETT)

                    .collider(DawnDayCollider.BAT_LONGER)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.WILD_STRIKES_DUAL)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.PIERCING_STRIKE)
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.FLORETT_AUTO1,
                            DawnDayAnimations.FLORETT_AUTO2,
                            DawnDayAnimations.FLORETT_AUTO3,
                            DawnDayAnimations.FLORETT_AUTO4,
                            DawnDayAnimations.VITR_DASH,
                            DawnDayAnimations.NIGHT_RITUS_DAGGER_AUTO1)

                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.FLORETT_DUAL_AUTO1,
                            DawnDayAnimations.FLORETT_DUAL_AUTO2,
                            DawnDayAnimations.FLORETT_DUAL_AUTO3,
                            DawnDayAnimations.FLORETT_DUAL_DASH,
                            DawnDayAnimations.FLORETT_DUAL_AIRSLASH)


                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.FLORETT_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.FLORETT_DUAL_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, DawnDayAnimations.FLORETT_DUAL_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.MILADY_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.FLORETT_DUAL_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR);

    public static final Function<Item, CapabilityItem.Builder> HOOKCLAWS = (item) ->
            WeaponCapability.builder()
                    .category(DawnDayWeaponCategories.CLAWS)
                    .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
                    .styleProvider((pp) -> pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory()
                            == DawnDayWeaponCategories.CLAWS ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == DawnDayWeaponCategories.CLAWS)
                    .collider(DawnDayCollider.IRON_FIST)
                    .swingSound(DawnDaySounds.milady_light_sweep.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> DawnDaySkills.BEAST_EYE)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> DawnDaySkills.QUICK_STEP)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            DawnDayAnimations.HOOKCLAWS_AUTO1,
                            DawnDayAnimations.HOOKCLAWS_AUTO2,
                            DawnDayAnimations.HOOKCLAWS_AUTO3,
                            DawnDayAnimations.HOOKCLAWS_AUTO4,
                            DawnDayAnimations.HOOKCLAWS_DASH,
                            DawnDayAnimations.HOOKCLAWS_AIRSLASH)

                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            DawnDayAnimations.HOOKCLAWS_ONEHAND_AUTO1,
                            DawnDayAnimations.HOOKCLAWS_ONEHAND_AUTO2,
                            DawnDayAnimations.HOOKCLAWS_ONEHAND_DASH,
                            DawnDayAnimations.HOOKCLAWS_ONEHAND_AIRSLASH
                            )

                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, DawnDayAnimations.HOOKCLAWS_ONEHAND_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, DawnDayAnimations.HOOKCLAWS_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, DawnDayAnimations.HOOKCLAWS_GUARD)

                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DawnDayAnimations.HOOKCLAWS_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, DawnDayAnimations.HOOKCLAWS_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, DawnDayAnimations.HOOKCLAWS_WALK);


    @SubscribeEvent // register Weapon Moveset
    public static void WeaponMovesetRegister(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "milady"), MILADY);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "vitreus"), VITREUS);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "saber"), SABER);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "hookclaws"), HOOKCLAWS);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "pole_blade"), POLEBLADE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "florett"), FLORETT);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "blood_ritus_dagger"), BLOOD_RITUS_DAGGER);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "night_ritus_dagger"), NIGHT_RITUS_DAGGER);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "messer"), MESSER);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "iron_fist"), IRON_FIST);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "eclipse"), ECLIPSE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "bonecutting_saw"), BONECUTTING_SAW);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "nailbat"), BAT);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "iudex_halberd"), IUDEX_HALBERD);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "knife"), KNIFE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "steelaxe"), STEEL_AXE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "herb_sickle"), HERB_SICKLE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "war_sickle"), WAR_SICKLE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "halberd"), HALBERD);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "battlestaff"), BATTLE_STAFF);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "battlestaff_amethist"), BATTLE_STAFF_AMETHIST);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "battlestaff_netherite"), BATTLE_STAFF_NETHERITE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "battlestaff_wood"), BATTLE_STAFF_WOOD);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "backhand_blade"), BACKHAND_BLADE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "light_greatsword"), LIGHT_GREATSWORD);
    }




}
