package net.epicfight_dd.client.input;


import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.gameasset.animation.WingStanceAnims;
import net.epicfight_dd.network.ChangeLivingModifierPacket;
import net.epicfight_dd.network.DDNetworkHandler;
import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyMapHandler {

    @SubscribeEvent
    public static void onKeyInput(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (event.phase == TickEvent.Phase.END && mc.player != null) {
            if (DawnKeymappings.SWITCH_STANCE.consumeClick()) {
                LocalPlayerPatch localPlayerPatch = EpicFightCapabilities.getLocalPlayerPatch(mc.player);
                if (localPlayerPatch != null && localPlayerPatch.isEpicFightMode()
                        && localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().hasData(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get())) {

                    AnimationPlayer animationPlayer = localPlayerPatch.getAnimator().getPlayerFor(null);

                    ItemStack offhand = localPlayerPatch.getValidItemInHand(InteractionHand.OFF_HAND);
                    boolean isHoldingMiladyOffhand = offhand == DawnDayItems.milady.get().getDefaultInstance();


                    if (    animationPlayer != null
                            && !(animationPlayer.getAnimation().checkType(AttackAnimation.class))
                            && !isHoldingMiladyOffhand) {

                        if (localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().getDataValue(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get()) == false) {
                            localPlayerPatch.getSkill(DawnDaySkills.WINGSTANCE).getDataManager().setDataSync(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get(), true);

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

                            ChangeLivingModifierPacket modifierPacket = new ChangeLivingModifierPacket(false);
                            DDNetworkHandler.INSTANCE.sendToServer(modifierPacket);


                        } else {
                            localPlayerPatch.getSkill(DawnDaySkills.WINGSTANCE).getDataManager().setDataSync(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get(), false);
                            localPlayerPatch.getOriginal().playSound(SoundEvents.BEACON_DEACTIVATE, 70f, 1.0f);

                            ChangeLivingModifierPacket modifierPacket = new ChangeLivingModifierPacket(true);
                            DDNetworkHandler.INSTANCE.sendToServer(modifierPacket);


                        }
                    }
                }
            }
        }
    }


}
