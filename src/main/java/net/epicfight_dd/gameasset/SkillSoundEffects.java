package net.epicfight_dd.gameasset;

import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;

public final class SkillSoundEffects {
    private record Layer(SoundEvent sound, float volume, float pitch) { }

    private static final Layer[][] HEATED_STEPS = {
            {new Layer(SoundEvents.FIRECHARGE_USE, .45F, 1.8F),
                    new Layer(SoundEvents.PLAYER_ATTACK_SWEEP, .2F, 1.75F)},
            {new Layer(SoundEvents.FIRECHARGE_USE, .55F, 1.45F),
                    new Layer(SoundEvents.BLAZE_SHOOT, .3F, 1.65F)},
            {new Layer(SoundEvents.FIRECHARGE_USE, .6F, 1.15F),
                    new Layer(SoundEvents.BLAZE_SHOOT, .35F, 1.1F),
                    new Layer(SoundEvents.PLAYER_ATTACK_SWEEP, .25F, 1.15F)},
            {new Layer(SoundEvents.FIRECHARGE_USE, .65F, .9F),
                    new Layer(SoundEvents.BLAZE_SHOOT, .45F, .8F),
                    new Layer(SoundEvents.GENERIC_EXPLODE, .25F, 1.55F)},
            {new Layer(SoundEvents.FIRECHARGE_USE, .8F, .65F),
                    new Layer(SoundEvents.GENERIC_EXPLODE, .55F, .75F),
                    new Layer(SoundEvents.PLAYER_ATTACK_SWEEP, .45F, .65F)}
    };
    private static final Layer[] METER = {
            new Layer(SoundEvents.EXPERIENCE_ORB_PICKUP, .3F, 1.8F),
            new Layer(SoundEvents.FLINTANDSTEEL_USE, .18F, 1.3F)
    };
    private static final Layer[] STACK_GAIN = {
            new Layer(SoundEvents.RESPAWN_ANCHOR_CHARGE, .6F, 1.06F),
            new Layer(SoundEvents.AMETHYST_BLOCK_CHIME, .25F, 1.55F)
    };
    private static final Layer[] STACK_LOSS = {
            new Layer(SoundEvents.CONDUIT_DEACTIVATE, .55F, 1.35F),
            new Layer(SoundEvents.PLAYER_ATTACK_SWEEP, .2F, .75F)
    };
    private static final Layer[] BARRIER_STOP = {
            new Layer(SoundEvents.CONDUIT_DEACTIVATE, .9F, 1.2F),
            new Layer(SoundEvents.AMETHYST_BLOCK_BREAK, .35F, .85F)
    };

    private SkillSoundEffects() { }

    public static void heatedStep(ServerPlayer player, int tier) {
        playWorld(player, HEATED_STEPS[Math.max(0, Math.min(HEATED_STEPS.length - 1, tier))]);
    }

    public static void heatedMeter(Player player) {
        if (!player.level().isClientSide) return;
        float variation = variation();
        for (Layer layer : METER)
            player.playNotifySound(layer.sound(), player.getSoundSource(), layer.volume(), layer.pitch() * variation);
    }

    public static void unstoppableStack(ServerPlayer player, boolean gained) {
        playWorld(player, gained ? STACK_GAIN : STACK_LOSS);
    }

    public static void unstoppableBarrierStop(ServerPlayer player) {
        playWorld(player, BARRIER_STOP);
    }

    private static void playWorld(ServerPlayer player, Layer[] layers) {
        float variation = variation();
        for (Layer layer : layers)
            player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(),
                    layer.sound(), player.getSoundSource(), layer.volume(), layer.pitch() * variation);
    }

    private static float variation() {
        return (float)ThreadLocalRandom.current().nextDouble(.96, 1.04);
    }
}
