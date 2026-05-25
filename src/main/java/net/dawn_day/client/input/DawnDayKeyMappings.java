package net.dawn_day.client.input;

import com.mojang.blaze3d.platform.InputConstants;
import net.epicfight_dd.epicfight_dd.generated.LangKeys;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import yesman.epicfight.client.input.CombatKeyMapping;
import yesman.epicfight.client.input.EpicFightInputCategories;


@SuppressWarnings("UnstableApiUsage")
public class DawnDayKeyMappings {

    public static final KeyMapping SWITCH_STANCE =
            new CombatKeyMapping(
                    LangKeys.KEY_SWITCH_STANCE,
                    InputConstants.KEY_J,
                    EpicFightInputCategories.COMBAT
            );


    public static void registerKeys(RegisterKeyMappingsEvent event){
        event.register(SWITCH_STANCE);
    }


}
