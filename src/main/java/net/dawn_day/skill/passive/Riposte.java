package net.dawn_day.skill.passive;

import java.util.List;


import net.dawn_day.registry.entries.DawnDaySkillDataKeys;
import net.dawn_day.registry.entries.DawnDaySounds;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import yesman.epicfight.api.event.EntityEventListener;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

public class Riposte extends PassiveSkill {

    private float damage_bonus;

    public Riposte(SkillBuilder<? extends SkillBuilder> builder) {
        super(builder);
    }

    @Override
    public float getCooldownRegenPerSecond(PlayerPatch<?> playerpatch) {
        return 1.0F;
    }

    @Override
    public void onInitiate(SkillContainer container, EntityEventListener eventListener) {
        super.onInitiate(container, eventListener);

        eventListener.registerContextAwareEvent( //NOTE: TAKE_DAMAGE_INCOME HAS TO BE CONTEXT AWARE TO PROPERLY WORK
                EpicFightEventHooks.Entity.TAKE_DAMAGE_INCOME,

                (event, context) -> {

                    if (!event.isParried()) {
                        return;
                    }

                    if (!container.isFull()) {
                        return;
                    }

                    if (container.getDataManager().getDataValue(
                            DawnDaySkillDataKeys.IRON_WILL_STACKS) > 0) {
                        return;
                    }

                    container.getDataManager().setDataSync(
                            DawnDaySkillDataKeys.IRON_WILL_STACKS,
                            1
                    );
/*
                    float reflectedDamage = event.getDamage() * 0.5F;

                    container.getDataManager().setDataSync(
                            DawnDaySkillDataKeys.IRON_WILL_DAMAGE.get(),
                            reflectedDamage
                    );

 */

                   container.getExecutor().playSound(
                            DawnDaySounds.GUARD_COUNTER.get(),
                            0.9F,
                           1.1F
                    );


                },this);

       eventListener.registerEvent(
                EpicFightEventHooks.Entity.MODIFY_ATTACK_DAMAGE,

                event -> {


                    int stacks = container.getDataManager().getDataValue(
                            DawnDaySkillDataKeys.IRON_WILL_STACKS);

                    if (stacks <= 0) {
                        return;
                    }


                    event.attachValueModifier(
                            ValueModifier.multiplier(this.damage_bonus)
                    );


                },this);


        eventListener.registerEvent(
                EpicFightEventHooks.Entity.DELIVER_DAMAGE_POST,
                event -> {
                    int stacks = container.getDataManager().getDataValue(
                            DawnDaySkillDataKeys.IRON_WILL_STACKS);

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
                            DawnDaySkillDataKeys.IRON_WILL_STACKS,
                            0
                    );

                    Skill.setSkillStackSynchronize(container, 0);
                },this);
    }



    @Override
    public boolean shouldDraw(SkillContainer container) {
        return !container.isFull()
                || container.getDataManager().getDataValue(
                DawnDaySkillDataKeys.IRON_WILL_STACKS) > 0;
    }

    @Override
    public void loadDatapackParameters(CompoundTag parameters) {
        super.loadDatapackParameters(parameters);
        this.consumption = parameters.getFloat("consumption");
        this.damage_bonus = parameters.getFloat("damage_bonus");
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public List<Object> getTooltipArgsOfScreen(List<Object> list) {
        list.add(ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(this.damage_bonus * 100.0F - 100));

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