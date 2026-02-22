package net.epicfight_dd.client.input;


import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.WingStanceAnims;
import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyMapHandler {

    @SubscribeEvent
    public static void onKeyInput(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (event.phase == TickEvent.Phase.END && mc.player != null) {
            if(DawnKeymappings.SWITCH_STANCE.consumeClick()) {
                LocalPlayerPatch localPlayerPatch = EpicFightCapabilities.getLocalPlayerPatch(mc.player);
                if(localPlayerPatch != null && localPlayerPatch.isEpicFightMode()
                  && localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().hasData(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get())){

                   if(localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().getDataValue(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get()) == false){
                       localPlayerPatch.getSkill(DawnDaySkills.WINGSTANCE).getDataManager().setDataSync(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get(),true);
                       localPlayerPatch.getOriginal().playSound(SoundEvents.AMETHYST_BLOCK_RESONATE,150f,0.85f);
                       for (int i = 1; i <= 5; i++) {
                           mc.player.level().addParticle(
                                   ParticleTypes.END_ROD,
                                   mc.player.getX(),mc.player.getY() + 0.85,mc.player.getZ(),0.23,0.020,0.25
                           );
                           mc.player.level().addParticle(
                                   ParticleTypes.END_ROD,
                                   mc.player.getX(),mc.player.getY() + 0.25,mc.player.getZ(),0.03,0.020,0.0
                           );

                       }

                      localPlayerPatch.playAnimationSynchronized(WingStanceAnims.WINGSTANCE_TRANSITION,0.0f);

                   } else {
                       localPlayerPatch.getSkill(DawnDaySkills.WINGSTANCE).getDataManager().setDataSync(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get(), false);
                       localPlayerPatch.getOriginal().playSound(SoundEvents.ANVIL_DESTROY);

                   }
                }
            }
        }
    }



}
