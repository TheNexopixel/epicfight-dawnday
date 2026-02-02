package F.epicfight_dd.world.item;

import F.epicfight_dd.Epicfight_dd;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationRegistryEvent;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.property.AnimationProperty.*;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;


public class MiladyMoveset {

    public static AnimationAccessor<BasicAttackAnimation> milady_onehanded_auto1;
    public static AnimationAccessor<BasicAttackAnimation> milady_onehanded_auto2;
    public static AnimationAccessor<BasicAttackAnimation> milady_onehanded_auto3;
    public static AnimationAccessor<BasicAttackAnimation> milady_onehanded_auto4;
    public static AnimationAccessor<BasicAttackAnimation> milady_onehanded_auto5;
    public static AnimationAccessor<BasicAttackAnimation> milady_onehanded_dash;

    public static void build(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<HumanoidArmature> biped = Armatures.BIPED;

        @SubscribeEvent
        public static void registerAnimations (AnimationRegistryEvent event){
            event.newBuilder(Epicfight_dd.MODID, MiladyMoveset::build);


            AnimationManager.AnimationBuilder builder;
            milady_onehanded_auto1 = builder.nextAccessor("biped/combat/rapier_auto1", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.3F, 0.5F, 0.72F, 0.52F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));
            milady_onehanded_auto2 = builder.nextAccessor("biped/combat/rapier_auto2", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.3F, 0.38F, 0.48F, 0.54F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));
            milady_onehanded_auto3 = builder.nextAccessor("biped/combat/rapier_auto2", (accessor) ->
                    new BasicAttackAnimation(0.12F, 0.3F, 0.38F, 0.48F, 0.54F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                            .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));
        }
    }
}
