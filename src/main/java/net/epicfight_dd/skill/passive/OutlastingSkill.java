package net.epicfight_dd.skill.passive;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector4f;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.EntityDecorations;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

// CODE MADE BY AI

public class OutlastingSkill extends PassiveSkill {
    private static final ResourceLocation OVERLAY_ID =
            ResourceLocation.fromNamespaceAndPath("epicfight_dd", "outlasting_overlay");

    private static final Map<UUID, Float> LAST_STAMINA = new ConcurrentHashMap<>();

    private float healthThreshold = 0.5F;
    private float consumptionMultiplier = 0.5F;
    private float overlayAlpha = 0.18F;
    private int regenWaitExtraTicks = 1;

    public OutlastingSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }
    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        if (!container.getExecutor().isLogicalClient()) {
        }
    }


    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
        if (parameters.contains("health_threshold")) this.healthThreshold = parameters.getFloat("health_threshold");
        if (parameters.contains("regen_wait_extra_ticks")) this.regenWaitExtraTicks = parameters.getInt("regen_wait_extra_ticks");
        if (parameters.contains("consumption_multiplier")) this.consumptionMultiplier = parameters.getFloat("consumption_multiplier");
        if (parameters.contains("overlay_alpha")) this.overlayAlpha = parameters.getFloat("overlay_alpha");
    }

    private boolean belowThreshold(Player player) {
        return player.isAlive()
                && player.getMaxHealth() > 0.0F
                && player.getHealth() / player.getMaxHealth() < this.healthThreshold;
    }

    @Override
    public void onRemoved(SkillContainer container) {
        Player player = container.getExecutor().getOriginal();
        LAST_STAMINA.remove(player.getUUID());
        if (!container.getExecutor().isLogicalClient()) {
        }
        super.onRemoved(container);
    }


    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
        if (container.getExecutor().isLogicalClient()) return;

        ServerPlayer player = container.getServerExecutor().getOriginal();
        PlayerPatch<?> patch = container.getExecutor();
        UUID id = player.getUUID();

        float current = patch.getStamina();

        if (counterAttackActive(patch)) {
            LAST_STAMINA.put(id, current);
            return;
        }

        if (!patch.getEntityState().inaction()) {
            int wait = patch.getStaminaRegenAwaitTicks();
            if (wait > 0) {
                patch.setStaminaRegenAwaitTicks(Math.max(0, wait - this.regenWaitExtraTicks));
            }
        }

        Float last = LAST_STAMINA.get(id);
        if (last != null && current < last && belowThreshold(player)) {
            float refund = (last - current) * (1.0F - this.consumptionMultiplier);
            current = Math.min(patch.getMaxStamina(), current + refund);
            patch.setStamina(current);
        }

        LAST_STAMINA.put(id, current);
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public void onInitiateClient(SkillContainer container) {
        super.onInitiateClient(container);

        Player player = container.getExecutor().getOriginal();
        Vector4f color = new Vector4f(0.25F, 0.8F, 0.35F, 0.0F);

        container.getExecutor().getEntityDecorations()
                .addDecorationOverlay(OVERLAY_ID, new EntityDecorations.DecorationOverlay() {
                    @Override
                    public Vector4f color(float partialTick) {
                        float alpha = belowThreshold(player) ? OutlastingSkill.this.overlayAlpha : 0.0F;
                        return color.set(0.25F, 0.8F, 0.35F, alpha);
                    }

                    @Override
                    public boolean shouldRemove() {
                        return container.getExecutor().getSkill(OutlastingSkill.this) == null;
                    }
                });

    }
    private static final Field COUNTER_ATTACK_FIELD = resolveCounterAttack();

    private static Field resolveCounterAttack() {
        try {
            return Class.forName("reascer.wom.gameasset.WOMSkills").getField("COUNTER_ATTACK");
        } catch (ReflectiveOperationException | LinkageError e) {
            return null;
        }
    }

    private static boolean counterAttackActive(PlayerPatch<?> patch) {
        if (COUNTER_ATTACK_FIELD == null) return false;
        try {
            if (!(COUNTER_ATTACK_FIELD.get(null) instanceof Skill counter)) return false;
            SkillContainer guard = patch.getSkill(counter);
            return guard != null && guard.isActivated();
        } catch (IllegalAccessException e) {
            return false;
        }
    }
}