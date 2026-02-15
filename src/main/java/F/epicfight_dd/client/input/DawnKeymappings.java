package F.epicfight_dd.client.input;

import F.epicfight_dd.Epicfight_dd;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.client.input.CombatKeyMapping;
import yesman.epicfight.client.input.EpicFightInputCategories;


@SuppressWarnings("UnstableApiUsage")
@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DawnKeymappings {

    public static final KeyMapping SWITCH_STANCE =
            new CombatKeyMapping(
                    Component.translatable("key.epicfight_dd.switch_stance").toString(),
                    InputConstants.KEY_F,
                    EpicFightInputCategories.COMBAT
            );


    @SubscribeEvent
    public static void registerKeyZ(RegisterKeyMappingsEvent event){

        event.register(SWITCH_STANCE);

    }


}
