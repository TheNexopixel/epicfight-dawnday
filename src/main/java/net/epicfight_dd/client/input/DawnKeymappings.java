package net.epicfight_dd.client.input;

import net.epicfight_dd.EpicFightDawnDay;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.client.input.CombatKeyMapping;
import yesman.epicfight.client.input.EpicFightInputCategories;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("UnstableApiUsage")
@Mod.EventBusSubscriber(modid = EpicFightDawnDay.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DawnKeymappings {

    private static final List<KeyMapping> REGISTERED_KEYMAPPINGS = new ArrayList<>();

    private static CombatKeyMapping registerCombat(String description, int keyCode, String category) {
        CombatKeyMapping keyMapping = new CombatKeyMapping(description, keyCode, category);
        REGISTERED_KEYMAPPINGS.add(keyMapping);
        return keyMapping;
    }

    public static final KeyMapping SWITCH_STANCE = registerCombat("WingStance Mode", InputConstants.KEY_Y, EpicFightInputCategories.COMBAT);


    @SubscribeEvent
    public static void registerKeyZ(RegisterKeyMappingsEvent event){
        for (KeyMapping keyMapping : REGISTERED_KEYMAPPINGS) {
            event.register(keyMapping);
        }
    }


}
