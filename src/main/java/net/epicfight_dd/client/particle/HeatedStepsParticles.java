package net.epicfight_dd.client.particle;

import java.util.ArrayDeque;
import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.network.HeatedStepsBurstPacket;
import net.epicfight_dd.skill.dodge.HeatedStepsRules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, value = Dist.CLIENT)
public final class HeatedStepsParticles {
    private static final int MAX_BURSTS = 16;
    private static final ArrayDeque<Burst> BURSTS = new ArrayDeque<>();
    private static ClientLevel level;

    private HeatedStepsParticles() { }

    private static final class Burst {
        final Vec3 position;
        final Particle[][] particles;
        final float[] scales = {1, 1};
        final int points;
        int age;

        Burst(Vec3 position, int points) {
            this.position = position;
            this.points = points;
            this.particles = new Particle[2][points];
        }

        boolean tick() {
            if (age >= HeatedStepsRules.BURST_TICKS + HeatedStepsRules.BURST_RING_DELAY) return false;
            for (int ring = 0; ring < 2; ring++) {
                int elapsed = age - ring * HeatedStepsRules.BURST_RING_DELAY;
                if (elapsed < 0) continue;
                if (elapsed >= HeatedStepsRules.BURST_TICKS) {
                    clearRing(ring);
                    continue;
                }
                double radius = HeatedStepsRules.burstRadius(4) * (elapsed + 1) / HeatedStepsRules.BURST_TICKS;
                float scale = Math.min(1, (HeatedStepsRules.BURST_TICKS - elapsed) / 2F);
                for (int point = 0; point < points; point++) {
                    double angle = Math.PI * 2 * (point + ring * .5) / points;
                    double x = position.x + Math.cos(angle) * radius;
                    double z = position.z + Math.sin(angle) * radius;
                    Particle particle = particles[ring][point];
                    if (elapsed == 0) {
                        particle = Minecraft.getInstance().particleEngine.createParticle(
                                point % 3 == 0 ? ParticleTypes.SMALL_FLAME : ParticleTypes.FLAME,
                                x, position.y + .08, z, 0, 0, 0);
                        particles[ring][point] = particle;
                        if (particle != null) {
                            particle.setLifetime(HeatedStepsRules.BURST_TICKS + 2);
                            particle.scale(1.25F);
                        }
                    }
                    if (particle == null || !particle.isAlive()) continue;
                    particle.setParticleSpeed(0, 0, 0);
                    particle.setPos(x, position.y + .08, z);
                    particle.scale(scale / scales[ring]);
                }
                scales[ring] = scale;
            }
            age++;
            return true;
        }

        void clearRing(int ring) {
            for (int i = 0; i < points; i++) {
                if (particles[ring][i] != null) {
                    particles[ring][i].remove();
                    particles[ring][i] = null;
                }
            }
        }

        void clear() { clearRing(0); clearRing(1); }
    }

    public static void accept(HeatedStepsBurstPacket packet) {
        var minecraft = Minecraft.getInstance();
        useLevel(minecraft.level);
        Vec3 position = packet.position();
        if (level == null || !level.dimension().location().equals(packet.dimension())
                || !Double.isFinite(position.x) || !Double.isFinite(position.y) || !Double.isFinite(position.z)) return;
        var camera = minecraft.getCameraEntity();
        if (camera == null || camera.position().distanceToSqr(position) > 2304) return;
        ParticleStatus status = minecraft.options.particles().get();
        int points = status == ParticleStatus.MINIMAL ? 32 : status == ParticleStatus.DECREASED ? 48 : 64;
        while (BURSTS.size() >= MAX_BURSTS) BURSTS.removeFirst().clear();
        BURSTS.addLast(new Burst(position, points));
    }

    private static void useLevel(ClientLevel current) {
        if (level != current) {
            clear();
            level = current;
        }
    }

    private static void clear() {
        for (Burst burst : BURSTS) burst.clear();
        BURSTS.clear();
    }

    @SubscribeEvent
    public static void tick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        var minecraft = Minecraft.getInstance();
        useLevel(minecraft.level);
        if (level == null || minecraft.isPaused()) return;
        BURSTS.removeIf(burst -> {
            if (burst.tick()) return false;
            burst.clear();
            return true;
        });
    }

    @SubscribeEvent
    public static void logout(ClientPlayerNetworkEvent.LoggingOut event) { clear(); level = null; }
}
