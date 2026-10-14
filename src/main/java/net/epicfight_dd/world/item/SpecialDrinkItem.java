package net.epicfight_dd.world.item;

import net.epicfight_dd.gameasset.animation.QoLMiscAnimations;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlot;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpecialDrinkItem extends PotionItem {

    private final List<MobEffectInstance> effects;

    public SpecialDrinkItem(Properties properties, MobEffectInstance... effects) {
        super(properties);
        this.effects = List.of(effects);

    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 32;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entityLiving) {
        Player player = entityLiving instanceof Player ? (Player) entityLiving : null;

        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
        }

        if (!level.isClientSide) {
            for (MobEffectInstance effect : effects) {
                if (effect.getEffect().isInstantenous()) {
                    effect.getEffect().applyInstantenousEffect(player, player, entityLiving, effect.getAmplifier(), 1.0F);
                } else {
                    // copy() is important — instances are shared, don't consume the original
                    entityLiving.addEffect(new MobEffectInstance(effect));
                }
            }
        }

        LivingEntityPatch<?> patch = EpicFightCapabilities.getEntityPatch(entityLiving, LivingEntityPatch.class);
        if (patch != null) {
            patch.playAnimation(QoLMiscAnimations.WIPE_MOUTH, 0.0f);
            if (patch instanceof PlayerPatch<?> playerPatch && !playerPatch.isLogicalClient()) {
                skillSlotSet.forEach(skillSlot -> {
                    SkillContainer container = playerPatch.getSkill(skillSlot);
                   if (container != null &&
                            container.getSkill() != null &&
                            container.getSkill().getResourceType() == (Skill.Resource.COOLDOWN)

                    ) {
                        
                        float cooldown = container.getResource();
                        float max = container.getMaxResource();
                        
                        if (cooldown < max) {
                            container.setResource(cooldown * 2f);
                        }
                    }
                });
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        entityLiving.gameEvent(GameEvent.DRINK);
        return stack.isEmpty() ? ItemStack.EMPTY : stack;
    }

    /// A HashSet for other addon devs to append into during modConstructEvent
    public static final Set<SkillSlot> skillSlotSet = new HashSet<>(Arrays.asList(SkillSlots.values()));

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        PotionUtils.addPotionTooltip(this.effects, tooltip, 1.0F);
    }


}
