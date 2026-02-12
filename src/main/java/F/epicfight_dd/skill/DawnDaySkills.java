package F.epicfight_dd.skill;

import F.epicfight_dd.Epicfight_dd;
import F.epicfight_dd.gameasset.animation.MiladyMoveset;

import java.util.Set;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.StunType;

@EventBusSubscriber(
        modid = Epicfight_dd.MODID,
        bus = Bus.MOD
)
public class DawnDaySkills {

    public static Skill GENTLE_NUDGE;


    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent build){
        SkillBuildEvent.ModRegistryWorker modRegistry = build.createRegistryWorker(Epicfight_dd.MODID);

       WeaponInnateSkill gentlenudge = modRegistry.build("gentle_nudge",SimpleWeaponInnateSkill::new,SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder()
                .setAnimations(MiladyMoveset.MILADY_KNUCKLE_INNATE)
                .setCategory(SkillCategories.WEAPON_INNATE));
               gentlenudge.newProperty()
                       .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                       .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F))
                       .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.0F))
                       .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                       .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.6F))
                       .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                       .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT
                       .create())).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE));
               GENTLE_NUDGE = gentlenudge;

    }

}
