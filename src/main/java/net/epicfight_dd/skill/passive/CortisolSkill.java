package net.epicfight_dd.skill.passive;

import java.util.List;
import java.util.UUID;

import net.epicfight_dd.EpicFightDawnDay;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Vector4f;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.EntityDecorations;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

// CODE MADE BY AI (gez. Nexo)

@Mod.EventBusSubscriber(modid = EpicFightDawnDay.MODID)
public class CortisolSkill extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("2f8b6d41-9a37-4c15-b0e2-7d5a3c18f946");
    private static final ResourceLocation OVERLAY_ID =
            ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID, "cortisol_overlay");
    private static final int SCALE = 100;

    private float stressPerHit = 2.0F;
    private float maxStress = 100.0F;
    private int decayDelayTicks = 300;
    private float decayPerSecond = 4.0F;
    private int syncInterval = 2;
    private float overlayAlpha = 0.25F;

    public CortisolSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    @Override
    public void setParams(CompoundTag p) {
        super.setParams(p);
        this.stressPerHit = p.contains("stress_per_hit") ? p.getFloat("stress_per_hit") : 2.0F;
        this.maxStress = p.contains("max_stress") ? p.getFloat("max_stress") : 100.0F;
        this.decayDelayTicks = p.contains("decay_delay_ticks") ? p.getInt("decay_delay_ticks") : 300;
        this.decayPerSecond = p.contains("decay_per_second") ? p.getFloat("decay_per_second") : 4.0F;
        this.syncInterval = Math.max(1, p.contains("sync_interval") ? p.getInt("sync_interval") : 2);
        this.overlayAlpha = p.contains("overlay_alpha") ? p.getFloat("overlay_alpha") : 0.25F;
    }

    private static int stress(SkillContainer container) {
        if (SkillDataKeyZ.CORTISOL_STRESS.isPresent()) {
            int stressLevel = container.getDataManager().getDataValue(SkillDataKeyZ.CORTISOL_STRESS.get());
            EpicFightDawnDay.LOGGER.debug("Stress level is: {}", stressLevel);
            return stressLevel;
        } else {
            throw new NullPointerException("Cortisol stress is null in CortisolSkill#stress");
        }
    }

    private static int idle(SkillContainer container) {
        if (SkillDataKeyZ.CORTISOL_IDLE.isPresent()) {
            int idleLevel = container.getDataManager().getDataValue(SkillDataKeyZ.CORTISOL_IDLE.get());
            EpicFightDawnDay.LOGGER.debug("Idle level is: {}", idleLevel);
            return idleLevel;
        } else {
            throw new NullPointerException("Cortisol idle is null in CortisolSkill#idle");
        }
    }

    private static float ratio(SkillContainer container) {
        return stress(container) / (float) (100 * SCALE);
    }

    private int maxScaled() {
        return Math.round(this.maxStress * SCALE);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT,
                EVENT_UUID,
                event -> event.attachValueModifier(ValueModifier.multiplier(1.0F + ratio(container))));

        if (!container.getExecutor().isLogicalClient()) {
            container.getDataManager().setData(SkillDataKeyZ.CORTISOL_STRESS.get(), 0);
            container.getDataManager().setData(SkillDataKeyZ.CORTISOL_IDLE.get(), 0);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT, EVENT_UUID);

        if (!container.getExecutor().isLogicalClient()) {
            container.getDataManager().setData(SkillDataKeyZ.CORTISOL_STRESS.get(), 0);
            container.getDataManager().setData(SkillDataKeyZ.CORTISOL_IDLE.get(), 0);
        }
        super.onRemoved(container);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
        if (container.getExecutor().isLogicalClient()) return;

        int current = stress(container);
        if (current <= 0) return;

        int idle = idle(container) + 1;
        container.getDataManager().setData(SkillDataKeyZ.CORTISOL_IDLE.get(), idle);
        if (idle < this.decayDelayTicks) return;

        int step = Math.max(1, Math.round(this.decayPerSecond * SCALE / 20.0F));
        int next = Math.max(0, current - step);

        ServerPlayer player = container.getServerExecutor().getOriginal();
        if (next == 0 || player.tickCount % this.syncInterval == 0) {
            container.getDataManager().setDataSync(SkillDataKeyZ.CORTISOL_STRESS.get(), next);
        } else {
            container.getDataManager().setData(SkillDataKeyZ.CORTISOL_STRESS.get(), next);
        }
    }

    private void handleHurt(SkillContainer container, LivingHurtEvent event) {
        int current = stress(container);
        event.setAmount(event.getAmount() * (1.0F + current / (float) (100 * SCALE)));

        int gain = Math.round(this.stressPerHit * SCALE);
        int next = Math.min(this.maxScaled(), current + gain);

        container.getDataManager().setDataSync(SkillDataKeyZ.CORTISOL_STRESS.get(), next);
        container.getDataManager().setData(SkillDataKeyZ.CORTISOL_IDLE.get(), 0);
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || event.getAmount() <= 0.0F) return;

        PlayerPatch<?> patch = EpicFightCapabilities.getEntityPatch(player, PlayerPatch.class);
        if (patch == null) return;

        patch.getSkillCapability().listSkillContainers().forEach(container -> {
            if (container.getSkill() instanceof CortisolSkill skill) {
                skill.handleHurt(container, event);
            }
        });
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void onInitiateClient(SkillContainer container) {
        super.onInitiateClient(container);

        Vector4f color = new Vector4f(0.66F, 0.06F, 0.07F, 0.0F);

        container.getExecutor().getEntityDecorations()
                .addDecorationOverlay(OVERLAY_ID, new EntityDecorations.DecorationOverlay() {
                    @Override
                    public Vector4f color(float partialTick) {
                        return color.set(0.66F, 0.06F, 0.07F, ratio(container) * CortisolSkill.this.overlayAlpha);
                    }

                    @Override
                    public boolean shouldRemove() {
                        return container.getExecutor().getSkill(CortisolSkill.this) == null;
                    }
                });
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return stress(container) > 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics graphics,
                          float x, float y, float partialTick) {
        graphics.blit(this.getSkillTexture(), (int) x, (int) y, 24, 24, 0, 0, 1, 1, 1, 1);

        float r = Math.min(1.0F, ratio(container) / Math.max(0.01F, this.maxStress / 100.0F));
        int green = (int) (255 * (1.0F - 0.7F * r));
        int color = 0xFF000000 | (255 << 16) | (green << 8) | green;

        String text = Math.round(stress(container) / (float) SCALE) + "%";
        graphics.drawString(gui.getFont(), text,
                (int) x + (24 - gui.getFont().width(text)) / 2, (int) y + 15, color, true);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public List<Object> getTooltipArgsOfScreen(List<Object> list) {
        list.add(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(this.stressPerHit));
        list.add(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(this.maxStress));
        list.add(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(this.decayDelayTicks / 20.0F));
        return list;
    }
}