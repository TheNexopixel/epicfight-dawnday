package net.epicfight_dd.skill.identity;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.attribute.DawnDayAttributes;
import net.epicfight_dd.client.particle.ParticleRegistry;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

// Code generated with AI (gez. Nexo)

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID)
public final class GorgedBulwarkSkill extends PassiveSkill {

    private static final class Params {
        static volatile float hungerPerDamage = 1.0F;
        static volatile float saturatedMultiplier = 0.5F;
        static volatile float saturationCost = 0.5F;
        static volatile float maxHungerFraction = 0.5F;
        static volatile float healPerFood = 0.25F;
        static volatile float healingReduction = 0.5F; // 0.5 = 50 % weniger Heilrate (positiv angeben!)
        static volatile float absorbPerFood = 0.5F;
        static volatile float absorptionMax = 10.0F;
        static volatile int absorptionDuration = 600;
    }

    private static final float ABSORPTION_DECAY_PER_TICK = 0.05F; // 1 HP pro Sekunde
    private static final UUID HEALING_MODIFIER = UUID.fromString("3b7e2d4a-5c1f-4e8b-9a60-2f4d8c1e7b95");

    private record FoodSnapshot(int food, float saturation) {}

    private static final Set<UUID> ACTIVE = ConcurrentHashMap.newKeySet();
    private static final Map<UUID, SkillContainer> CONTAINERS = new ConcurrentHashMap<>();
    private static final Map<UUID, Float> DEBT = new ConcurrentHashMap<>();
    private static final Map<UUID, FoodSnapshot> LAST = new ConcurrentHashMap<>();
    private static final Map<UUID, Long> ABSORB_UNTIL = new ConcurrentHashMap<>();

    public static volatile boolean clientActive = false;

    public static boolean isActiveFor(Player player) {
        return player.level().isClientSide ? clientActive : ACTIVE.contains(player.getUUID());
    }

    public GorgedBulwarkSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    // ---------- Lifecycle ----------

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        if (container.getExecutor().isLogicalClient()) {
            clientActive = true;
        } else if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            CONTAINERS.put(player.getUUID(), container);
            container.setResource(container.getMaxResource());
            ACTIVE.add(player.getUUID());
            applyHealingModifier(player);
        }
    }
    @Override
    public float getCooldownRegenPerSecond(PlayerPatch<?> playerpatch) {
        return 1.0F;
    }

    @Override
    public void onRemoved(SkillContainer container) {
        if (container.getExecutor().isLogicalClient()) {
            clientActive = false;
        } else if (container.getExecutor().getOriginal() instanceof ServerPlayer player) {
            removeHealingModifier(player);
            forget(player.getUUID());
        }
        super.onRemoved(container);
    }

    private static void forget(UUID id) {
        ACTIVE.remove(id);
        CONTAINERS.remove(id);
        DEBT.remove(id);
        LAST.remove(id);
        ABSORB_UNTIL.remove(id);
    }

    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            removeHealingModifier(player);
        }
        forget(event.getEntity().getUUID());
    }

    // ---------- Parameter ----------

    @Override
    public void setParams(CompoundTag p) {
        super.setParams(p);
        Params.hungerPerDamage = readFloat(p, "hunger_per_damage", 1.0F);
        this.consumption = p.contains("consumption") ? p.getFloat("consumption") : 15.0F;
        Params.saturatedMultiplier = readFloat(p, "saturated_multiplier", 0.5F);
        Params.saturationCost = readFloat(p, "saturation_cost", 0.5F);
        Params.maxHungerFraction = readFloat(p, "max_hunger_fraction", 0.5F);
        Params.healPerFood = readFloat(p, "heal_per_food", 0.25F);
        Params.healingReduction = readFloat(p, "healing_reduction", 0.5F);
        Params.absorbPerFood = readFloat(p, "absorb_per_food", 0.5F);
        Params.absorptionMax = readFloat(p, "absorption_max", 10.0F);
        Params.absorptionDuration = p.contains("absorption_duration") ? p.getInt("absorption_duration") : 600;
    }

    private static float readFloat(CompoundTag tag, String key, float fallback) {
        return tag.contains(key) ? tag.getFloat(key) : fallback;
    }

    // ---------- Heilrate (HEALING-Attribut) ----------

    private static void applyHealingModifier(ServerPlayer player) {
        AttributeInstance healing = player.getAttribute(DawnDayAttributes.HEALING.get());
        if (healing == null || healing.getModifier(HEALING_MODIFIER) != null) return;
        healing.addTransientModifier(new AttributeModifier(HEALING_MODIFIER, "Gorged Bulwark healing",
                -Params.healingReduction, AttributeModifier.Operation.ADDITION));
    }

    private static void removeHealingModifier(ServerPlayer player) {
        AttributeInstance healing = player.getAttribute(DawnDayAttributes.HEALING.get());
        if (healing != null) healing.removeModifier(HEALING_MODIFIER);
    }

    // ---------- Schaden -> Hunger ----------

    private static final int MAX_FOOD = 20;

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onDamage(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !ACTIVE.contains(player.getUUID())) return;

        DamageSource source = event.getSource();
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) || source.is(DamageTypes.STARVE)) return;

        float damage = event.getAmount();
        if (damage <= 0.0F) return;

        FoodData food = player.getFoodData();

        if (food.getSaturationLevel() > 0.0F) {
            food.setSaturation(Math.max(0.0F, food.getSaturationLevel() - damage * Params.saturationCost));
            damage *= Params.saturatedMultiplier;
        }

        SkillContainer skill = CONTAINERS.get(player.getUUID());
        boolean locked = skill != null && !skill.isFull();

        if (food.getFoodLevel() <= 0 || locked) {
            event.setAmount(damage);
            return;
        }

        UUID id = player.getUUID();
        float total = damage * Params.hungerPerDamage + DEBT.getOrDefault(id, 0.0F);
        int whole = (int) total;
        DEBT.put(id, total - whole);

        boolean fall = source.is(DamageTypeTags.IS_FALL);
        int cap = fall ? Integer.MAX_VALUE : Math.max(1, Math.round(MAX_FOOD * Params.maxHungerFraction));
        boolean capped = whole > cap;

        int effective = Math.min(whole, cap);
        int loss = Math.min(effective, food.getFoodLevel());
        int overflow = effective - loss;

        if (loss > 0) {
            food.setFoodLevel(food.getFoodLevel() - loss);
            hungerHitFeedback(player, loss);
            if (food.getFoodLevel() <= 0) {
                startLock(player);
            }
        }

        if (capped) {
            player.level().playSound(null, player.blockPosition(),
                    SoundEvents.RAVAGER_HURT, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        event.setAmount(overflow / Params.hungerPerDamage);
    }

    private static void hungerHitFeedback(ServerPlayer player, int loss) {
        if (!(player.level() instanceof ServerLevel level)) return;
        level.sendParticles(ParticleRegistry.HUNGER_INDICATOR.get(),
                player.getX(), player.getY() + player.getBbHeight() * 0.6D, player.getZ(),
                Math.min(Math.max(1, loss), 6), 0.3D, 0.1D, 0.3D, 0.05D);
    }

    // ---------- Gift: kein Herzschaden ----------

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !ACTIVE.contains(player.getUUID())) return;

        DamageSource source = event.getSource();
        if (source.is(DamageTypes.MAGIC)
                && source.getEntity() == null && source.getDirectEntity() == null
                && event.getAmount() == 1.0F
                && player.hasEffect(MobEffects.POISON)) {
            event.setCanceled(true);
        }
    }

    // ---------- Essen heilt, Überschuss gibt Absorption ----------

    @SubscribeEvent
    public static void onEat(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !ACTIVE.contains(player.getUUID())) return;

        FoodProperties props = event.getItem().getFoodProperties(player);
        if (props == null) return;

        FoodSnapshot last = LAST.get(player.getUUID());
        int before = last != null ? last.food() : player.getFoodData().getFoodLevel();

        int nutrition = props.getNutrition();
        int overflow = Math.max(0, before + nutrition - 20);
        int real = nutrition - overflow;

        float heal = real * Params.healPerFood;
        if (heal > 0.0F && player.getHealth() < player.getMaxHealth()) {
            player.heal(heal);
        }

        float absorb = overflow * Params.absorbPerFood;
        if (absorb > 0.0F) {
            player.setAbsorptionAmount(Math.min(Params.absorptionMax, player.getAbsorptionAmount() + absorb));
            ABSORB_UNTIL.put(player.getUUID(), player.level().getGameTime() + Params.absorptionDuration);
        }
    }

    // ---------- Effekte pro Tick ----------

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !(event.player instanceof ServerPlayer player)) return;
        UUID id = player.getUUID();
        if (!ACTIVE.contains(id)) return;

        applyHealingModifier(player); // Absicherung (Respawn, andere Skills)

        FoodData food = player.getFoodData();



        MobEffectInstance hunger = player.getEffect(MobEffects.HUNGER);
        if (hunger != null) {
            float vanilla = 0.005F * (hunger.getAmplifier() + 1);
            food.setExhaustion(Math.max(0.0F, food.getExhaustionLevel() - vanilla));

            if (shouldTick(hunger, 25) && player.getHealth() > 1.0F) {
                player.hurt(player.damageSources().starve(), 1.0F);
            }
        }

        MobEffectInstance poison = player.getEffect(MobEffects.POISON);
        if (poison != null && shouldTick(poison, 25)) {
            food.addExhaustion(4.0F);
        }

        FoodSnapshot last = LAST.get(id);
        if (last != null && player.hasEffect(MobEffects.WITHER)) {
            if (food.getFoodLevel() > last.food()) food.setFoodLevel(last.food());
            if (food.getSaturationLevel() > last.saturation()) food.setSaturation(last.saturation());
        }

        if (last != null && last.food() > 0 && food.getFoodLevel() <= 0) {
            startLock(player);
        }


        Long until = ABSORB_UNTIL.get(id);
        if (until != null) {
            float absorption = player.getAbsorptionAmount();
            if (absorption <= 0.0F) {
                ABSORB_UNTIL.remove(id);
            } else if (player.level().getGameTime() >= until) {
                player.setAbsorptionAmount(Math.max(0.0F, absorption - ABSORPTION_DECAY_PER_TICK));
            }
        }

        LAST.put(id, new FoodSnapshot(food.getFoodLevel(), food.getSaturationLevel()));
    }

    private static boolean shouldTick(MobEffectInstance effect, int base) {
        int interval = base >> effect.getAmplifier();
        return interval <= 0 || effect.getDuration() % interval == 0;
    }
    private static void startLock(ServerPlayer player) {
        SkillContainer skill = CONTAINERS.get(player.getUUID());
        if (skill == null || !skill.isFull()) return;

        Skill.setSkillConsumptionSynchronize(skill, 0.0F);
        Skill.setSkillStackSynchronize(skill, 0);

        player.level().playSound(null, player.blockPosition(),
                SoundEvents.RAVAGER_STUNNED, SoundSource.PLAYERS, 1.2F, 1.0F);
    }

    // ---------- HUD ----------

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics graphics,
                          float x, float y, float partialTick) {
        boolean locked = !container.isFull();
        boolean saturated = container.getExecutor().getOriginal().getFoodData().getSaturationLevel() > 0.0F;

        if (locked) {
            RenderSystem.setShaderColor(0.45F, 0.45F, 0.45F, 1.0F);
        } else if (saturated) {
            RenderSystem.setShaderColor(1.0F, 0.84F, 0.25F, 1.0F);
        }
        graphics.blit(this.getSkillTexture(), (int) x, (int) y, 24, 24, 0, 0, 1, 1, 1, 1);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        if (locked) {
            int seconds = (int) Math.ceil((1.0F - container.getResource(partialTick)) * this.consumption);
            String text = String.valueOf(seconds);
            graphics.drawString(gui.getFont(), text,
                    (int) x + (24 - gui.getFont().width(text)) / 2, (int) y + 15, 0xFFFFFF, true);
        }
    }

}