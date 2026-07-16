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

public class IronWillSkill extends PassiveSkill {
    private static final UUID EVENT_UUID =
            UUID.fromString("a5d42df5-5dd5-46ec-92fb-4b4d0cfd1c42");

    public IronWillSkill(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    private float damage_bonus;
    @Override
    public float getCooldownRegenPerSecond(PlayerPatch<?> playerpatch) {
        return 1.0F;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.TAKE_DAMAGE_EVENT_ATTACK,
                EVENT_UUID,
                event -> {
                    if (!event.isParried()) {
                        return;
                    }

                    if (!container.isFull()) {
                        return;
                    }

                    if (container.getDataManager().getDataValue(
                            SkillDataKeyZ.IRON_WILL_STACKS.get()) > 0) {
                        return;
                    }

                    container.getDataManager().setDataSync(
                            SkillDataKeyZ.IRON_WILL_STACKS.get(),
                            1
                    );

                    container.getExecutor().playSound(
                            DawnDaySounds.GUARD_COUNTER.get(),
                            0.9F,
                            1.1F
                    );
                });

        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT,
                EVENT_UUID,
                event -> {


                    int stacks = container.getDataManager().getDataValue(
                            SkillDataKeyZ.IRON_WILL_STACKS.get());

                    if (stacks <= 0) {
                        return;
                    }


                    event.attachValueModifier(
                            ValueModifier.multiplier(this.damage_bonus)
                    );


                });


        container.getExecutor().getEventListener().addEventListener(
                PlayerEventListener.EventType.DEAL_DAMAGE_EVENT_DAMAGE,
                EVENT_UUID,
                event -> {
                    int stacks = container.getDataManager().getDataValue(
                            SkillDataKeyZ.IRON_WILL_STACKS.get());

                    if (stacks <= 0) {
                        return;
                    }

                    ServerLevel level =
                            (ServerLevel)container.getExecutor().getOriginal().level();

                    LivingEntity target = event.getTarget();

                    level.sendParticles(
                            ParticleTypes.CRIT,
                            target.getX(),
                            target.getY() + target.getBbHeight() * 0.5,
                            target.getZ(),
                            80,
                            0.4,
                            0.4,
                            0.4,
                            0.9
                    );

                    target.level().playSound(
                            null,
                            target.getX(),
                            target.getY(),
                            target.getZ(),
                            SoundEvents.WITHER_BREAK_BLOCK,
                            target.getSoundSource(),
                            2.0F,
                            1.1F
                    );

                    container.getDataManager().setDataSync(
                            SkillDataKeyZ.IRON_WILL_STACKS.get(),
                            0
                    );

                    Skill.setSkillStackSynchronize(container, 0);
                });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);

        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.TAKE_DAMAGE_EVENT_ATTACK,
                EVENT_UUID);

        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT,
                EVENT_UUID);

        container.getExecutor().getEventListener().removeListener(
                PlayerEventListener.EventType.DEAL_DAMAGE_EVENT_DAMAGE,
                EVENT_UUID);
    }
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return !container.isFull()
                || container.getDataManager().getDataValue(
                SkillDataKeyZ.IRON_WILL_STACKS.get()) > 0;
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
        list.add(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(this.damage_bonus * 100.0F - 100));

        return list;
    }
    @Override
    public void drawOnGui(
            BattleModeGui gui,
            SkillContainer container,
            GuiGraphics guiGraphics,
            float x,
            float y,
            float partialTick
    ) {
        guiGraphics.blit(
                this.getSkillTexture(),
                (int)x,
                (int)y,
                24,
                24,
                0,
                0,
                1,
                1,
                1,
                1
        );


        float resource = container.getResource(partialTick);

        int seconds =
                (int)Math.ceil(
                        (1.0F - resource) * consumption
                );

        guiGraphics.drawString(
                gui.getFont(),
                String.valueOf(seconds),
                x + 18,
                y + 14,
                16777215,
                true
        );
    }
}