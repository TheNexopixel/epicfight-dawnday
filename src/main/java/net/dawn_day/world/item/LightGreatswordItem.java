package net.dawn_day.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.world.item.TieredWeaponItem;
import yesman.epicfight.world.item.WeaponItem;

public class LightGreatswordItem extends TieredWeaponItem
{
    public LightGreatswordItem(Tier materialIn, int durability) {
        super(materialIn, new Properties().stacksTo(1).attributes(createAttributes(materialIn)).durability(durability));
    }

    public static ItemAttributeModifiers createAttributes(Tier tier)
    {
        return TieredWeaponItem.createAttributes(tier, 3.0F, -2.4F, 0.0F);
    }


    @Override
    public boolean canDisableShield(ItemStack stack, @NotNull ItemStack shield, LivingEntity entity, @NotNull LivingEntity attacker) {
        return stack.getEnchantmentLevel(entity.level().holderOrThrow(Enchantments.KNOCKBACK)) > 0;
    }

}
