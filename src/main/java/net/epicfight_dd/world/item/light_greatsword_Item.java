package net.epicfight_dd.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import yesman.epicfight.world.item.WeaponItem;

public class light_greatsword_Item extends WeaponItem
{
    public light_greatsword_Item(Tier materialIn, int damageIn, float speed, int durability) {
        super(materialIn, 3, -2F,
                new Properties().stacksTo(1)
                        .defaultDurability(durability)

        );
    }


    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return EnchantmentHelper.getEnchantments(stack).containsKey(Enchantments.KNOCKBACK);
    }

}
