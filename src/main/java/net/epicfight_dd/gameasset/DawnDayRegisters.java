package net.epicfight_dd.gameasset;

import net.epicfight_dd.EpicFightDawnDay;
import net.epicfight_dd.attribute.DawnDayAttributes;
import net.epicfight_dd.client.particle.DawnDayParticles;
import net.epicfight_dd.effect.DawnDayEffects;
import net.epicfight_dd.skill.DawnDaySkillDataKeys;
import net.epicfight_dd.world.entity.decoration.DawnDayPaintings;
import net.epicfight_dd.world.item.DawnDayCreativeTab;
import net.epicfight_dd.world.item.DawnDayItems;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Consumer;

public enum DawnDayRegisters {
    SOUNDS(DawnDaySounds.SOUNDS::register),
    ATTRIBUTES(DawnDayAttributes.ATTRIBUTES::register),
    ITEMS(DawnDayItems.ITEMS::register),
    CREATIVE_TABS(DawnDayCreativeTab.CREATIVE_MODE_TABS::register),
    SKILL_DATA_KEYS(DawnDaySkillDataKeys.DATA_KEYS::register),
    PARTICLES(DawnDayParticles.PARTICLES::register),
    EFFECTS(DawnDayEffects.EFFECTS::register),
    POTIONS(DawnDayItems.POTIONS::register),
    PAINTINGS(DawnDayPaintings.PAINTING_VARIANTS::register);

    private final Consumer<IEventBus> registrar;

    DawnDayRegisters(Consumer<IEventBus> registrar) {
        this.registrar = registrar;
    }

    public void register(IEventBus modBus) {
        this.registrar.accept(modBus);
    }

    public static void registerAll(IEventBus modBus) {
        for (DawnDayRegisters registry : DawnDayRegisters.values()) {
            EpicFightDawnDay.LOGGER.debug("Registered {}", registry);
            registry.register(modBus);
        }
    }
}