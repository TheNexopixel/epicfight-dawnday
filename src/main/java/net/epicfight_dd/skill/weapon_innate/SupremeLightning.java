package net.epicfight_dd.skill.weapon_innate;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.AttackAnimation.Phase;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class SupremeLightning extends SimpleWeaponInnateSkill {
    public SupremeLightning(Builder builder) {
        super(builder);
    }

    @Override
    public List<Component> getTooltipOnItem(ItemStack itemStack, CapabilityItem itemCap, PlayerPatch<?> playerpatch) {
        List<Component> list = super.getTooltipOnItem(itemStack, itemCap, playerpatch);
        this.generateTooltipforPhase(list, itemStack, itemCap, playerpatch, this.properties.get(0), "Thunder:");

        return list;
    }

    @Override
    public WeaponInnateSkill registerPropertiesToAnimation() {
        AttackAnimation anim = this.attackAnimation.get();

        for (Phase phase : anim.phases) {
            phase.addProperties(this.properties.get(0).entrySet());
        }

        return this;
    }
}