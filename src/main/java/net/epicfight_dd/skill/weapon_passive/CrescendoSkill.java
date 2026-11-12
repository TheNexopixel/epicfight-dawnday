package net.epicfight_dd.skill.weapon_passive;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID)
public final class CrescendoSkill extends PassiveSkill {

    private static final class Params {
        static volatile float baseDamage = 1.0F;
        static volatile float damage_increasement = 0.5F;
        static volatile float sharpnessBonus = 0.5F;
        static volatile float maxDamage = 275.0F;
        static volatile int expireTicks = 40;
        static volatile float decayFactor = 0.9F;
        static volatile float sharpnessReduction = 0.2F;
        static volatile float sweepingBonus = 0.1F;
        static volatile float minStepFactor = 0.0F;
    }

    private static final class Build {
        long lastHit;
        float damage = Params.baseDamage;
    }

    private static final Map<UUID, SkillContainer> CONTAINERS = new ConcurrentHashMap<>();
    private static final Map<UUID, Build> BUILDS = new ConcurrentHashMap<>();

    public CrescendoSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }


    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            CONTAINERS.put(player.getUUID(), container);
            BUILDS.put(player.getUUID(), new Build());
            container.getDataManager().setDataSync(SkillDataKeyZ.CRESCENDO_DAMAGE.get(), 0);
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            forget(player.getUUID());
            container.getDataManager().setDataSync(SkillDataKeyZ.CRESCENDO_DAMAGE.get(), 0);
        }
        super.onRemoved(container);
    }

    private static void forget(UUID id) {
        CONTAINERS.remove(id);
        BUILDS.remove(id);
    }

    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        forget(event.getEntity().getUUID());
    }


    @Override
    public void setParams(CompoundTag p) {
        super.setParams(p);
        Params.baseDamage = readFloat(p, "base_damage", 1.0F);
        Params.sharpnessBonus = readFloat(p, "sharpness_bonus", 0.5F);
        Params.damage_increasement = readFloat(p, "damage_increasement", 0.5F);
        Params.maxDamage = readFloat(p, "max_damage", 275.0F);
        Params.expireTicks = p.contains("expire_ticks") ? p.getInt("expire_ticks") : 60;
        Params.decayFactor = readFloat(p, "decay_factor", 0.9F);
        Params.sharpnessReduction = readFloat(p, "sharpness_reduction", 0.2F);
        Params.sweepingBonus = readFloat(p, "sweeping_bonus", 0.1F);
        Params.minStepFactor = readFloat(p, "min_step_factor", 0.0F);
    }

    private static float readFloat(CompoundTag tag, String key, float fallback) {
        return tag.contains(key) ? tag.getFloat(key) : fallback;
    }


    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) return;
        if (event.getSource().getDirectEntity() != player) return; // nur Nahkampf

        Build build = BUILDS.get(player.getUUID());
        SkillContainer container = CONTAINERS.get(player.getUUID());
        if (build == null || container == null) return;

        LivingEntity target = event.getEntity();
        if (target == player) return;

        float current = Math.max(build.damage, startDamage(player));
        event.setAmount(current);

        build.lastHit = player.level().getGameTime();
        build.damage = Math.min(Params.maxDamage, current + step(player));

        container.getDataManager().setDataSync(SkillDataKeyZ.CRESCENDO_DAMAGE.get(), tenths(build.damage));
    }

    private static float step(ServerPlayer player) {
        ItemStack weapon = player.getMainHandItem();
        int sharpness = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, weapon);
        int sweeping = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWEEPING_EDGE, weapon);

        float sharpFactor = (float) Math.pow(1.0F - Params.sharpnessReduction, sharpness);
        float sweepFactor = 1.0F + Params.sweepingBonus * sweeping;

        return Params.damage_increasement * Math.max(Params.minStepFactor, sharpFactor * sweepFactor);
    }

    private static int tenths(float damage) {
        return Math.round(damage * 10.0F);
    }



    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
        if (container.getExecutor().isLogicalClient()) return;

        ServerPlayer player = container.getServerExecutor().getOriginal();
        Build build = BUILDS.get(player.getUUID());
        if (build == null) return;

        float base = startDamage(player);
        if (build.damage <= base) return;
        if (player.level().getGameTime() - build.lastHit <= Params.expireTicks) return;

        build.damage = base + (build.damage - base) * Params.decayFactor;
        if (build.damage - base < 0.05F) build.damage = base;

        int shown = tenths(build.damage);
        boolean finished = build.damage <= base;
        int current = container.getDataManager().getDataValue(SkillDataKeyZ.CRESCENDO_DAMAGE.get());

        if (shown != current && (finished || player.tickCount % 2 == 0)) {
            container.getDataManager().setDataSync(SkillDataKeyZ.CRESCENDO_DAMAGE.get(), shown);
        }
    }


    private static float shownDamage(SkillContainer container) {
        return container.getDataManager().getDataValue(SkillDataKeyZ.CRESCENDO_DAMAGE.get()) / 10.0F;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return shownDamage(container) > startDamage(container.getExecutor().getOriginal()) + 0.01F;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics graphics,
                          float x, float y, float partialTick) {
        graphics.blit(this.getSkillTexture(), (int) x, (int) y, 24, 24, 0, 0, 1, 1, 1, 1);

        float damage = shownDamage(container);
        float ratio = Math.min(1.0F, damage / Math.max(1.0F, Params.maxDamage));

        int color;
        if (ratio < 0.5F) {
            float t = ratio / 0.5F;
            color = rgb(1.0F, 1.0F - 0.16F * t, 1.0F - 0.55F * t);
        } else {
            float t = (ratio - 0.5F) / 0.5F;
            color = rgb(1.0F, 0.84F - 0.54F * t, 0.45F - 0.25F * t);
        }

        String text = damage >= 100.0F ? String.valueOf(Math.round(damage)) : String.format("%.1f", damage);
        graphics.drawString(gui.getFont(), text,
                (int) x + (24 - gui.getFont().width(text)) / 2, (int) y + 15, color, true);
    }

    private static int rgb(float r, float g, float b) {
        return 0xFF000000 | ((int) (r * 255) << 16) | ((int) (g * 255) << 8) | (int) (b * 255);
    }
    private static float startDamage(net.minecraft.world.entity.player.Player player) {
        int sharpness = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, player.getMainHandItem());
        return Params.baseDamage + Params.sharpnessBonus * sharpness;
    }
}