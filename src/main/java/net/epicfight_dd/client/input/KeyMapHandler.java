package net.epicfight_dd.client.input;


import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.WingStanceAnims;
import net.epicfight_dd.network.ChangeLivingModifierPacket;
import net.epicfight_dd.network.DDNetworkHandler;
import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.types.AttackAnimation;
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

                    AnimationPlayer animationPlayer = localPlayerPatch.getAnimator().getPlayerFor(null);

                    if (animationPlayer != null && !(animationPlayer.getAnimation().checkType(AttackAnimation.class))){

                        if(localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().getDataValue(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get()) == false){
                            localPlayerPatch.getSkill(DawnDaySkills.WINGSTANCE).getDataManager().setDataSync(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get(),true);

                            localPlayerPatch.getOriginal().playSound(SoundEvents.AMETHYST_BLOCK_RESONATE,150f,0.85f);

                            localPlayerPatch.getOriginal().playSound(SoundEvents.BEACON_ACTIVATE,150f,0.25f);


                            for (int i = 1; i <= 5; i++) {
                                mc.player.level().addParticle(
                                        ParticleTypes.END_ROD,
                                        mc.player.getX() - Math.min(0.25, mc.player.getRandomX(0.01)),mc.player.getY(), mc.player.getZ() + mc.player.getRandomZ(-0.01f), Math.min(mc.player.getRandom().nextFloat(), 0.6f), Math.min(mc.player.getRandom().nextFloat(), 0.2f) , Math.min(mc.player.getRandom().nextFloat(), 1.6f)
                                );
                                mc.player.level().addParticle(
                                        ParticleTypes.END_ROD,
                                        mc.player.getX() + Math.min(0.25, mc.player.getRandomX(0.01)),mc.player.getY(), mc.player.getZ() + mc.player.getRandomZ(0.01f),0.03, 0.020, 0.0
                                );

                            }

                           localPlayerPatch.playAnimationSynchronized(WingStanceAnims.WINGSTANCE_TRANSITION,0.0f);

                            ChangeLivingModifierPacket modifierPacket = new ChangeLivingModifierPacket(false);
                            DDNetworkHandler.INSTANCE.sendToServer(modifierPacket);


                        } else {
                            localPlayerPatch.getSkill(DawnDaySkills.WINGSTANCE).getDataManager().setDataSync(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get(), false);
                            localPlayerPatch.getOriginal().playSound(SoundEvents.BEACON_DEACTIVATE, 70f,1.0f);

                            ChangeLivingModifierPacket modifierPacket = new ChangeLivingModifierPacket(true);
                            DDNetworkHandler.INSTANCE.sendToServer(modifierPacket);


                        }
                    }
                }
            }
        }
    }



}
