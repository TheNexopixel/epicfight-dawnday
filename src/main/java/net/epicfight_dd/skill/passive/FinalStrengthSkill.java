package net.epicfight_dd.skill.passive;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

// CODE MADE BY AI

import java.util.List;

public class FinalStrengthSkill extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("4a9d1c3e-7b52-4f08-b6e1-2c8f5d0a9e37");

    private static final float MAX_NORMAL_DELTA = 3.0F;

    private static final Map<UUID, Float> LAST_ENERGY = new ConcurrentHashMap<>();

    private float damageMultiplier = 1.3F;
    private float saturationDrainMultiplier = 2.0F;
    private int resistanceAmplifier = 0;

    public FinalStrengthSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    @Override
    public void setParams(CompoundTag p) {
        super.setParams(p);
        this.damageMultiplier = p.contains("damage_multiplier") ? p.getFloat("damage_multiplier") : 1.2F;
        this.saturationDrainMultiplier = p.contains("saturation_drain_multiplier") ? p.getFloat("saturation_drain_multiplier") : 2.0F;
        this.resistanceAmplifier = p.contains("resistance_amplifier") ? p.getInt("resistance_amplifier") : 0;
    }

    private static boolean hungerEmpty(Player player) {
        return player.getFoodData().getFoodLevel() <= 0;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT,
                EVENT_UUID,
                event -> {
                    if (hungerEmpty(container.getExecutor().getOriginal())) {
                        event.attachValueModifier(ValueModifier.multiplier(this.damageMultiplier));
                    }
                });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT, EVENT_UUID);

        Player player = container.getExecutor().getOriginal();
        LAST_ENERGY.remove(player.getUUID());
        if (!container.getExecutor().isLogicalClient()) {
            removeResistance(player);
        }
        super.onRemoved(container);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
        if (container.getExecutor().isLogicalClient()) return;

        ServerPlayer player = container.getServerExecutor().getOriginal();
        UUID id = player.getUUID();
        FoodData food = player.getFoodData();

        float energy = food.getSaturationLevel() * 4.0F - food.getExhaustionLevel();
        Float last = LAST_ENERGY.get(id);
        if (last != null && food.getSaturationLevel() > 0.0F) {
            float drop = last - energy;
            if (drop > 0.0F && drop <= MAX_NORMAL_DELTA) {
                food.addExhaustion(drop * (this.saturationDrainMultiplier - 1.0F));
                energy = food.getSaturationLevel() * 4.0F - food.getExhaustionLevel();
            }
        }
        LAST_ENERGY.put(id, energy);

        if (hungerEmpty(player) && player.isAlive()) {
            MobEffectInstance current = player.getEffect(MobEffects.DAMAGE_RESISTANCE);
            if (current == null || current.getAmplifier() < this.resistanceAmplifier) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,
                        MobEffectInstance.INFINITE_DURATION, this.resistanceAmplifier, false, false, true));
            }
        } else {
            removeResistance(player);
        }
    }

    private static void removeResistance(Player player) {
        MobEffectInstance current = player.getEffect(MobEffects.DAMAGE_RESISTANCE);
        if (current != null && current.isInfiniteDuration()) {
            player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public List<Object> getTooltipArgsOfScreen(List<Object> list) {
        list.add(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format((this.damageMultiplier - 1.0F) * 100.0F));
        list.add(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(this.saturationDrainMultiplier));
        return list;
    }
}