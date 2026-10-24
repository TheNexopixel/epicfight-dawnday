package net.epicfight_dd.client.particle;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.network.UnstoppableVisualPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Vector3f;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, value = Dist.CLIENT)
public final class UnstoppableParticles {
    private static final int MAX_CLOUDS = 512;
    private static final int MAX_BARRIERS = 64;
    private static final DustParticleOptions DUST = new DustParticleOptions(new Vector3f(1.0F, .84F, .42F), .7F);
    private static final ArrayDeque<Cloud> CLOUDS = new ArrayDeque<>();
    private static final Map<Integer, Barrier> BARRIERS = new HashMap<>();
    private static ClientLevel level;

    private UnstoppableParticles() { }

    private static final class Cloud {
        final Player player;
        final Particle particle;
        final boolean inward;
        final double angle;
        final double radius;
        final double height;
        final int lifetime;
        int age;
        float scale = 1;

        Cloud(Player player, Particle particle, boolean inward, double angle, double radius, double height, int lifetime) {
            this.player = player; this.particle = particle; this.inward = inward;
            this.angle = angle; this.radius = radius; this.height = height; this.lifetime = lifetime;
        }

        boolean tick() {
            if (!particle.isAlive() || player.isRemoved() || !player.isAlive() || player.isInvisible() || player.level() != level || ++age >= lifetime) {
                particle.remove(); return false;
            }
            double progress = age / (double)lifetime;
            double distance = inward ? radius * (1 - progress) * (1 - progress) : .12 + radius * (1 - (1 - progress) * (1 - progress));
            double rotation = angle + Math.toRadians(player.yBodyRot) + (inward ? .45 : -.2) * progress;
            double y = player.getBbHeight() * (.58 + (height - .58) * (inward ? 1 - progress : 1));
            particle.setParticleSpeed(0, 0, 0);
            particle.setPos(player.getX() + Math.cos(rotation) * distance, player.getY() + y, player.getZ() + Math.sin(rotation) * distance);
            float nextScale = (float)(1 - progress);
            particle.scale(nextScale / scale);
            scale = nextScale;
            return true;
        }
    }

    private static final class Barrier {
        final UUID playerId;
        Particle[] particles;
        int[] created;
        int points;
        int age;
        int missing;

        Barrier(UUID playerId, int points) {
            this.playerId = playerId; this.points = points;
            this.particles = new Particle[points * 2]; this.created = new int[points * 2];
        }

        void clearParticles() {
            for (int i = 0; i < particles.length; i++) {
                if (particles[i] != null) { particles[i].remove(); particles[i] = null; }
            }
        }

        boolean tick(int entityId) {
            Entity entity = level.getEntity(entityId);
            if (entity == null) { clearParticles(); return ++missing <= 20; }
            if (!(entity instanceof Player player) || !player.getUUID().equals(playerId) || !player.isAlive() || player.isRemoved()) return false;
            missing = 0;
            if (player.isInvisible() || !nearby(player)) { clearParticles(); age++; return true; }
            int count = density(16, 8);
            if (count != points) {
                clearParticles(); points = count;
                particles = new Particle[points * 2]; created = new int[points * 2];
            }
            double radius = player.getBbWidth() * .85;
            double offset = Math.sin(age * .075) * .18;
            double yaw = Math.toRadians(player.yBodyRot);
            for (int ring = 0; ring < 2; ring++) {
                double direction = ring == 0 ? 1 : -1;
                double y = player.getY() + player.getBbHeight() * (.58 + direction * offset);
                for (int point = 0; point < points; point++) {
                    int index = ring * points + point;
                    double angle = Math.PI * 2 * (point + ring * .5) / points + yaw + direction * age * .04;
                    double x = player.getX() + Math.cos(angle) * radius, z = player.getZ() + Math.sin(angle) * radius;
                    Particle particle = particles[index];
                    if (particle != null && age - created[index] >= 10 + point % 6) { particle.remove(); particle = null; }
                    if (particle == null || !particle.isAlive()) {
                        particle = Minecraft.getInstance().particleEngine.createParticle(DUST, x, y, z, 0, 0, 0);
                        if (particle != null) { particle.setLifetime(10 + point % 6); particles[index] = particle; created[index] = age; }
                    }
                    if (particle != null) {
                        particle.setParticleSpeed(0, 0, 0);
                        particle.setPos(x, y, z);
                    }
                }
            }
            age++;
            return true;
        }
    }

    public static void accept(UnstoppableVisualPacket packet) {
        ClientLevel current = Minecraft.getInstance().level;
        if (current == null || packet.stacks() < 0 || packet.stacks() > 13 || packet.kind() < 0 || packet.kind() > 3) return;
        useLevel(current);
        if (packet.kind() == UnstoppableVisualPacket.BARRIER_STOP) {
            var old = BARRIERS.get(packet.entityId());
            if (old != null && old.playerId.equals(packet.playerId())) { old.clearParticles(); BARRIERS.remove(packet.entityId()); }
            return;
        }
        if (packet.kind() == UnstoppableVisualPacket.BARRIER_START) {
            var old = BARRIERS.get(packet.entityId());
            if (old != null && old.playerId.equals(packet.playerId())) return;
            if (old != null) old.clearParticles();
            if (old != null || BARRIERS.size() < MAX_BARRIERS)
                BARRIERS.put(packet.entityId(), new Barrier(packet.playerId(), density(16, 8)));
            return;
        }
        Entity entity = level.getEntity(packet.entityId());
        if (!(entity instanceof Player player) || !player.getUUID().equals(packet.playerId())
                || !player.isAlive() || player.isInvisible() || !nearby(player)) return;
        boolean inward = packet.kind() == UnstoppableVisualPacket.GAIN;
        int count = density(8 + packet.stacks() * 2, 4);
        int lifetime = inward ? 16 : 12;
        double phase = level.random.nextDouble() * Math.PI * 2;
        for (int i = 0; i < count; i++) {
            double angle = phase + Math.PI * 2 * (i + level.random.nextDouble() * .4) / count;
            double radius = .65 + level.random.nextDouble() * .35;
            double height = .35 + level.random.nextDouble() * .45;
            double distance = inward ? radius : .12;
            double rotation = angle + Math.toRadians(player.yBodyRot);
            var particle = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.CLOUD,
                    player.getX() + Math.cos(rotation) * distance, player.getY() + player.getBbHeight() * height,
                    player.getZ() + Math.sin(rotation) * distance, 0, 0, 0);
            if (particle == null) continue;
            particle.setParticleSpeed(0, 0, 0);
            particle.setLifetime(lifetime);
            particle.scale(.65F);
            particle.setColor(.92F, .95F, 1);
            while (CLOUDS.size() >= MAX_CLOUDS) CLOUDS.removeFirst().particle.remove();
            CLOUDS.addLast(new Cloud(player, particle, inward, angle, radius, height, lifetime));
        }
    }

    private static int density(int count, int minimum) {
        ParticleStatus status = Minecraft.getInstance().options.particles().get();
        return Math.max(minimum, status == ParticleStatus.MINIMAL ? count / 4 : status == ParticleStatus.DECREASED ? count / 2 : count);
    }

    private static boolean nearby(Entity entity) {
        Entity camera = Minecraft.getInstance().getCameraEntity();
        return camera != null && entity.distanceToSqr(camera) <= 1600;
    }

    private static void useLevel(ClientLevel current) {
        if (level != current) { clear(); level = current; }
    }

    private static void clear() {
        for (var cloud : CLOUDS) cloud.particle.remove();
        CLOUDS.clear();
        for (var barrier : BARRIERS.values()) barrier.clearParticles();
        BARRIERS.clear();
    }

    @SubscribeEvent
    public static void tick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        var minecraft = Minecraft.getInstance();
        useLevel(minecraft.level);
        if (level == null || minecraft.isPaused()) return;
        CLOUDS.removeIf(cloud -> !cloud.tick());
        var iterator = BARRIERS.entrySet().iterator();
        while (iterator.hasNext()) {
            var entry = iterator.next();
            if (!entry.getValue().tick(entry.getKey())) { entry.getValue().clearParticles(); iterator.remove(); }
        }
    }

    @SubscribeEvent
    public static void logout(ClientPlayerNetworkEvent.LoggingOut event) { clear(); level = null; }
}
