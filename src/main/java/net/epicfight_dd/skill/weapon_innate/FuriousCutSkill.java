package net.epicfight_dd.skill.weapon_innate;

import net.epicfight_dd.skill.DawnDaySkills;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

import java.util.Locale;

public class FuriousCutSkill extends SimpleWeaponInnateSkill {


    public FuriousCutSkill(Builder builder) {
        super(builder);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ResourceLocation getSkillTexture() {

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        LocalPlayerPatch localPlayerPatch = EpicFightCapabilities.getLocalPlayerPatch(player);

        if(localPlayerPatch != null &&
                !localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).isEmpty() &&
                localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).hasSkill(DawnDaySkills.WINGSTANCE) &&
                localPlayerPatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().getDataValue(SkillDataKeyZ.SPECIAL_STANCE_ACTIVATE.get()) == true)
        {
            return ResourceLocation.fromNamespaceAndPath(this.getRegistryName().getNamespace(), String.format("textures/gui/skills/%s/%s.png", this.category.toString().toLowerCase(Locale.ROOT), this.getRegistryName().getPath() + "_special"));
        }

        return super.getSkillTexture();
    }


}
