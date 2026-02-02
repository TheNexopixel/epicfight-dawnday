package F.epicfight_dd.world.capabilities.item;

import F.epicfight_dd.world.item.MiladyMoveset;
import net.minecraft.world.item.Item;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;

public class WeaponCapabilityPresets {
    public static final Function<Item, CapabilityItem.Builder> milday = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.milday) // Updated to use custom category
                .styleProvider((playerpatch) -> CapabilityItem.Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(RapierSounds.RAPIER_HIT.get())
                .canBePlacedOffhand(true)
                .newStyleCombo(CapabilityItem.Styles.OCHS, MiladyMoveset.milady_onehanded_auto1, MiladyMoveset.milady_onehanded_auto2, MiladyMoveset.milady_onehanded_auto3, MiladyMoveset.milady_onehanded_auto4,MiladyMoveset.milady_onehanded_auto5,MiladyMoveset.milady_onehanded_dash)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, MiladyMoveset.milady_onehanded_walk)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, MiladyMoveset.BIPED_WALK_RAPIER)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.CHASE, MiladyMoveset.BIPED_WALK_RAPIER)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.RUN, MiladyMoveset.BIPED_RUN_RAPIER)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.JUMP, MiladyMoveset.BIPED_HOLD_RAPIER)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.KNEEL, MiladyMoveset.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SNEAK, MiladyMoveset.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SWIM, MiladyMoveset.BIPED_HOLD_RAPIER)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, MiladyMoveset.RAPIER_GUARD);

        return builder;
    };
}
