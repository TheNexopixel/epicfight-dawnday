package F.epicfight_dd.world.capabilities.item;

import F.epicfight_dd.Epicfight_dd;
import F.epicfight_dd.gameasset.animation.MiladyMoveset;
import F.epicfight_dd.gameasset.dawnDaySounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;


@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class EpicFight_DD_WeaponCapabilityPresets {

    public static final Function<Item, CapabilityItem.Builder> MILADY = (item) ->
            WeaponCapability.builder()
            .category(EpicFightDD_WeaponCategories.MILADY)
            .styleProvider((pp) -> CapabilityItem.Styles.TWO_HAND)
            .collider(ColliderPreset.GREATSWORD)
            .swingSound(dawnDaySounds.Milady_slash.get())
            .hitSound(EpicFightSounds.BLADE_HIT.get())
            .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, ip -> EpicFightSkills.SHARP_STAB)
            .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO1,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO2,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO3,
                    MiladyMoveset.MILADY_TWOHANDED_AUTO4,
                    MiladyMoveset.MILADY_TWOHANDED_DASH,
                   MiladyMoveset.MILADY_ONE_HANDED_AUTO_1
                   )
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, MiladyMoveset.MILADY_TWOHANDED_IS_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_TWOHANDED_WALK)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> DIAMOND_LIGHT_GREATSWORD = (item) ->
            WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.LONGSWORD)
                    .styleProvider((pp) ->
                            pp.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.LONGSWORD ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .collider(ColliderPreset.SPEAR)
                    .weaponCombinationPredicator((entityPatch) -> EpicFightCapabilities.getItemStackCapability(entityPatch.getOriginal().getOffhandItem()).getWeaponCategory() == CapabilityItem.WeaponCategories.LONGSWORD)
                    .swingSound(dawnDaySounds.Milady_slash.get())
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
                            MiladyMoveset.MILADY_DUAL_DASH,
                            MiladyMoveset.MILADY_DUAL_AIRSLASH)
                    .canBePlacedOffhand(true)
                                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, MiladyMoveset.MILADY_DUAL_IS_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, MiladyMoveset.MILADY_IS_IDLE)
                                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_WALK)
                                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_WALK)
                                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
                                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);

    @SubscribeEvent // register Weapon Moveset
    public static void WeaponMovesetRegister(WeaponCapabilityPresetRegistryEvent event){
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "milady"), MILADY);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "diamond_light_greatsword"), DIAMOND_LIGHT_GREATSWORD);
    }
}
