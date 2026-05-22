package net.dawn_day.client.input;


import net.dawn_day.EpicFightDawnDay;
import net.dawn_day.gameasset.animation.WingStanceAnims;
import net.dawn_day.network.CPLivingModifierPacket;
import net.dawn_day.network.DDNetworkHandler;
import net.dawn_day.skill.DawnDaySkills;
import net.dawn_day.skill.SkillDataKeys;
import net.dawn_day.world.item.DawnDayItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

@EventBusSubscriber(modid = EpicFightDawnDay.MOD_ID, value = Dist.CLIENT)
public class KeyMapHandler {

    @SubscribeEvent
    public static void onKeyInput(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            if (DawnDayKeyMappings.SWITCH_STANCE.consumeClick()) {
                LocalPlayerPatch localPlayerPatch = EpicFightCapabilities.getLocalPlayerPatch(mc.player);
                if (localPlayerPatch != null && localPlayerPatch.isEpicFightMode()
                        && localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().hasData(SkillDataKeys.SPECIAL_STANCE_ACTIVATE)) {

                    AnimationPlayer animationPlayer = localPlayerPatch.getAnimator().getPlayerFor(null);

                    ItemStack offhand = localPlayerPatch.getValidItemInHand(InteractionHand.OFF_HAND);
                    boolean isHoldingMiladyOffhand = offhand == DawnDayItems.MILADY.get().getDefaultInstance();


                    if (    animationPlayer != null
                            && !(animationPlayer.getAnimation().checkType(AttackAnimation.class))
                            && !isHoldingMiladyOffhand) {

                        if (localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().getDataValue(SkillDataKeys.SPECIAL_STANCE_ACTIVATE) == false) {
                            localPlayerPatch.getSkill(DawnDaySkills.WINGSTANCE.get()).getDataManager().setDataSync(SkillDataKeys.SPECIAL_STANCE_ACTIVATE, true);

                            localPlayerPatch.getOriginal().playSound(SoundEvents.AMETHYST_BLOCK_RESONATE, 150f, 0.85f);

                            localPlayerPatch.getOriginal().playSound(SoundEvents.BEACON_ACTIVATE, 150f, 0.25f);


                            RandomSource rand =  mc.player.getRandom();

                            for (int i = 0; i < 20; i++) {

                                double angle = (2 * Math.PI / 5) * i;
                                double radius = 0.3;

                                double offsetX = Math.cos(angle) * radius;
                                double offsetZ = Math.sin(angle) * radius;


                                double velX = offsetX * 0.05;
                                double velY = 0.09 + rand.nextFloat() * 0.2;
                                double velZ = offsetZ * 0.05;

                                mc.player.level().addParticle(
                                        ParticleTypes.END_ROD,
                                        mc.player.getX() + offsetX,
                                        mc.player.getY() + 0.1,
                                        mc.player.getZ() + offsetZ,
                                        velX, velY, velZ
                                );
                            }

                            localPlayerPatch.playAnimationSynchronized(WingStanceAnims.WINGSTANCE_TRANSITION, 0.0f);

                            CPLivingModifierPacket modifierPacket = new CPLivingModifierPacket(false);
                            DDNetworkHandler.sendToServer(modifierPacket);


                        } else {
                            localPlayerPatch.getSkill(DawnDaySkills.WINGSTANCE.get()).getDataManager().setDataSync(SkillDataKeys.SPECIAL_STANCE_ACTIVATE, false);
                            localPlayerPatch.getOriginal().playSound(SoundEvents.BEACON_DEACTIVATE, 70f, 1.0f);

                            CPLivingModifierPacket modifierPacket = new CPLivingModifierPacket(true);
                            DDNetworkHandler.sendToServer(modifierPacket);


                        }
                    }
                }
            }
        }
    }


}
