package net.epicfight_dd.client.sound;

import java.util.concurrent.ThreadLocalRandom;
import net.epicfight_dd.effect.DawnDayEffects;
import net.epicfight_dd.network.UnstoppableVisualPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.EntityBoundSoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;

public final class UnstoppableBarrierSound extends EntityBoundSoundInstance {
    private final Player player;

    private UnstoppableBarrierSound(Player player) {
        super(SoundEvents.RESPAWN_ANCHOR_AMBIENT, player.getSoundSource(), .3F,
                (float) ThreadLocalRandom.current().nextDouble(.96, 1.04), player,
                ThreadLocalRandom.current().nextLong());
        this.player = player;
    }

    public static void play(UnstoppableVisualPacket packet) {
        var minecraft = Minecraft.getInstance();
        if (minecraft.level == null) return;
        var entity = minecraft.level.getEntity(packet.entityId());
        if (entity instanceof Player player && player.getUUID().equals(packet.playerId())
                && player.isAlive() && player.hasEffect(DawnDayEffects.UNSTOPPABLE_BARRIER.get()))
            minecraft.getSoundManager().play(new UnstoppableBarrierSound(player));
    }

    @Override
    public void tick() {
        if (Minecraft.getInstance().level != player.level() || !player.isAlive()
                || !player.hasEffect(DawnDayEffects.UNSTOPPABLE_BARRIER.get())) {
            stop();
            return;
        }
        super.tick();
    }
}
