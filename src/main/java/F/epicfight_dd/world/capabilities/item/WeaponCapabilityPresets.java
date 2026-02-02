package F.epicfight_dd.world.capabilities.item;

import F.epicfight_dd.Epicfight_dd;
import F.epicfight_dd.gameasset.animation.MiladyMoveset;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;


@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponCapabilityPresets {

    public static final Function<Item, CapabilityItem.Builder> MILADY = (item) ->
            WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.LONGSWORD) // will use milady category later
            .styleProvider((playerpatch) -> CapabilityItem.Styles.ONE_HAND)
            .collider(ColliderPreset.LONGSWORD)
            .swingSound(EpicFightSounds.WHOOSH_SHARP.get())
            .hitSound(EpicFightSounds.BLADE_HIT.get())
            .canBePlacedOffhand(true)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, ip -> EpicFightSkills.SHARP_STAB)
            .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                    MiladyMoveset.MILADY_ONE_HANDED_AUTO_1,
                    MiladyMoveset.MILADY_ONE_HANDED_AUTO_2,
                    MiladyMoveset.MILADY_ONE_HANDED_AUTO_3,
                    MiladyMoveset.MILADY_ONE_HANDED_AUTO_4,
                    MiladyMoveset.MILADY_ONE_HANDED_AUTO_5,
                   MiladyMoveset.MILADY_AIR_SLASH,
                    MiladyMoveset.milady_onehanded_dash)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, MiladyMoveset.MILADY_IS_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, MiladyMoveset.MILADY_WALK)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);



    @SubscribeEvent // register Weapon Moveset
    public static void WeaponMovesetRegister(WeaponCapabilityPresetRegistryEvent event){
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "milady"), MILADY);
    }


}
