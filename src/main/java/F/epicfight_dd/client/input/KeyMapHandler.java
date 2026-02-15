package F.epicfight_dd.client.input;


import F.epicfight_dd.Epicfight_dd;
import F.epicfight_dd.skill.DawnDaySkills;
import F.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.Minecraft;
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
                  && localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).hasSkill(DawnDaySkills.WINGSTANCE)
                ) {
                    localPlayerPatch.getSkill(DawnDaySkills.WINGSTANCE).getDataManager().setDataSync(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get(),true);
                }
            }
        }
    }



}
