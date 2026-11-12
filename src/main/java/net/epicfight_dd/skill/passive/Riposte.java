package net.epicfight_dd.skill.passive;

import java.util.List;
import java.util.UUID;

import net.epicfight_dd.gameasset.DawnDaySounds;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

public class Riposte extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("a5d42df5-5dd5-46ec-92fb-4b4d0cfd1c42");

    private static final float STORED_DAMAGE_RATIO = 1.0F / 3.0F;

    private float damage_bonus;

    // Code generated with AI (gez. Nexo)

    public Riposte(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }


    private static boolean isCharged(SkillContainer container) {
        return container.getDataManager().getDataValue(SkillDataKeyZ.IRON_WILL_STACKS.get()) > 0;
    }

    private static void setCharge(SkillContainer container, int stacks, float storedDamage) {
        container.getDataManager().setDataSync(SkillDataKeyZ.IRON_WILL_STACKS.get(), stacks);
        container.getDataManager().setDataSync(SkillDataKeyZ.IRON_WILL_DAMAGE.get(), storedDamage);
    }


    @Override
    public float getCooldownRegenPerSecond(PlayerPatch<?> playerpatch) {
        return 1.0F;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        PlayerEventListener listener = container.getExecutor().getEventListener();

        listener.addEventListener(PlayerEventListener.EventType.TAKE_DAMAGE_EVENT_ATTACK, EVENT_UUID, event -> {
            if (!event.isParried() || !container.isFull() || isCharged(container)) return;

            setCharge(container, 1, event.getDamage() * STORED_DAMAGE_RATIO);

            container.getExecutor().playSound(DawnDaySounds.GUARD_COUNTER.get(), 0.9F, 1.1F);
        });

        listener.addEventListener(PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT, EVENT_UUID, event -> {
            if (isCharged(container)) {
                event.attachValueModifier(ValueModifier.multiplier(this.damage_bonus));
            }
        });

        listener.addEventListener(PlayerEventListener.EventType.DEAL_DAMAGE_EVENT_DAMAGE, EVENT_UUID, event -> {
            if (!isCharged(container)) return;

            Player player = container.getExecutor().getOriginal();
            LivingEntity target = event.getTarget();
            float stored = container.getDataManager().getDataValue(SkillDataKeyZ.IRON_WILL_DAMAGE.get());

            setCharge(container, 0, 0.0F);
            Skill.setSkillStackSynchronize(container, 0);

            if (!(player.level() instanceof ServerLevel level)) return;

            level.sendParticles(ParticleTypes.CRIT,
                    target.getX(), target.getY() + target.getBbHeight() * 0.5D, target.getZ(),
                    80, 0.4D, 0.4D, 0.4D, 0.9D);

            level.playSound(null, target.getX(), target.getY(), target.getZ(),
                    SoundEvents.WITHER_BREAK_BLOCK, target.getSoundSource(), 2.0F, 1.1F);

            if (stored > 0.0F && target.isAlive()) {
                target.invulnerableTime = 0;
                target.hurt(player.damageSources().playerAttack(player), stored * this.damage_bonus);
            }
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        PlayerEventListener listener = container.getExecutor().getEventListener();
        listener.removeListener(PlayerEventListener.EventType.TAKE_DAMAGE_EVENT_ATTACK, EVENT_UUID);
        listener.removeListener(PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT, EVENT_UUID);
        listener.removeListener(PlayerEventListener.EventType.DEAL_DAMAGE_EVENT_DAMAGE, EVENT_UUID);

        if (!container.getExecutor().isLogicalClient()) {
            setCharge(container, 0, 0.0F);
        }
    }


    @Override
    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
        this.consumption = parameters.getFloat("consumption");
        this.damage_bonus = parameters.getFloat("damage_bonus");
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public List<Object> getTooltipArgsOfScreen(List<Object> list) {
        list.add(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(this.damage_bonus * 100.0F - 100.0F));
        list.add(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(STORED_DAMAGE_RATIO * 100.0F));
        return list;
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return !container.isFull() || isCharged(container);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics graphics,
                          float x, float y, float partialTick) {
        graphics.blit(this.getSkillTexture(), (int) x, (int) y, 24, 24, 0, 0, 1, 1, 1, 1);

        int seconds = (int) Math.ceil((1.0F - container.getResource(partialTick)) * this.consumption);
        graphics.drawString(gui.getFont(), String.valueOf(seconds), x + 18, y + 14, 0xFFFFFF, true);
    }
}