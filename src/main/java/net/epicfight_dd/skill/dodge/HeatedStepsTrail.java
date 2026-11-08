package net.epicfight_dd.skill.dodge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.network.HeatedStepsBurstPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.damagesource.StunType;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID)
public final class HeatedStepsTrail {
    private static final int MAX_POINTS = 128;
    private static final double SPACING = .35;
    private record Point(Vec3 position, long until, HeatedStepsSkill.TrailParameters parameters) { }
    private static final class Burst {
        final Vec3 position;
        final long start;
        final int tier;
        final HashSet<UUID> first = new HashSet<>();
        final HashSet<UUID> second = new HashSet<>();
        Burst(Vec3 position, long start, int tier) { this.position = position; this.start = start; this.tier = tier; }
    }
    private static final class State {
        final ServerPlayer owner;
        final ServerLevel level;
        final List<Point> points = new ArrayList<>();
        final List<Burst> bursts = new ArrayList<>();
        final DamageSource damage;
        SkillContainer container;
        HeatedStepsSkill.TrailParameters parameters;
        Vec3 previous;
        boolean tracing;
        State(ServerPlayer owner) {
            this.owner = owner;
            this.level = owner.serverLevel();
            this.damage = new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(DamageTypes.IN_FIRE), owner);
        }
    }
    private static final Map<UUID, State> STATES = new HashMap<>();

    private HeatedStepsTrail() { }

    public static void begin(SkillContainer container, HeatedStepsSkill.TrailParameters parameters, int tier) {
        ServerPlayer owner = container.getServerExecutor().getOriginal();
        State state = STATES.get(owner.getUUID());
        if (state == null || state.owner != owner || state.level != owner.serverLevel()) {
            state = new State(owner);
            STATES.put(owner.getUUID(), state);
        }
        state.container = container;
        if (!owner.isInWaterOrBubble()) {
            if (state.bursts.size() >= 4) state.bursts.remove(0);
            Burst burst = new Burst(owner.position().add(0, .05, 0), state.level.getGameTime(), tier);
            state.bursts.add(burst);
            if (tier >= 4) HeatedStepsBurstPacket.send(owner, burst.position);
        }
        state.parameters = parameters;
        state.previous = owner.position();
        state.tracing = tier >= 2;
        if (state.tracing) point(state, state.previous);
    }

    public static void follow(SkillContainer container) {
        if (!(container.getExecutor().getOriginal() instanceof ServerPlayer owner)) return;
        State state = STATES.get(owner.getUUID());
        if (state == null || state.container != container || !state.tracing) return;
        var animation = container.getExecutor().getAnimator().getPlayerFor(null).getAnimation().get().getRealAnimation();
        if (animation != Animations.BIPED_ROLL_FORWARD && animation != Animations.BIPED_ROLL_BACKWARD) {
            state.tracing = false;
            return;
        }
        Vec3 current = owner.position();
        double distance = current.distanceTo(state.previous);
        if (distance > 4 || !Double.isFinite(distance)) {
            state.previous = current;
            return;
        }
        if (distance < SPACING) return;
        int count = Math.min(12, (int)Math.ceil(distance / SPACING));
        for (int i = 1; i <= count; i++) point(state, state.previous.lerp(current, i / (double)count));
        state.previous = current;
    }

    private static void point(State state, Vec3 from) {
        if (!valid(state) || !state.level.hasChunkAt(BlockPos.containing(from)) || state.owner.isInWaterOrBubble()) return;
        var hit = state.level.clip(new ClipContext(from.add(0, .5, 0), from.add(0, -1, 0), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, state.owner));
        if (hit.getType() != HitResult.Type.BLOCK || !state.level.getFluidState(BlockPos.containing(hit.getLocation().add(0, .05, 0))).isEmpty()) return;
        Vec3 position = hit.getLocation().add(0, .03, 0);
        Point previous = state.points.isEmpty() ? null : state.points.get(state.points.size() - 1);
        if (previous != null && previous.position.distanceToSqr(position) < SPACING * SPACING * .5) return;
        if (state.points.size() >= MAX_POINTS) state.points.remove(0);
        state.points.add(new Point(position, state.level.getGameTime() + state.parameters.duration(), state.parameters));
    }

    private static boolean valid(State state) {
        return state.owner.isAlive() && !state.owner.isRemoved() && !state.owner.isSpectator() && state.owner.serverLevel() == state.level;
    }

    private static boolean enemy(ServerPlayer owner, LivingEntity target) {
        if (target == owner || !target.isAlive() || !target.isAttackable() || target instanceof ArmorStand || target.fireImmune()
                || owner.isAlliedTo(target) || target.isAlliedTo(owner)) return false;
        if (target instanceof Player player && (player.isCreative() || player.isSpectator() || !owner.server.isPvpAllowed() || !owner.canHarmPlayer(player))) return false;
        if (target instanceof OwnableEntity pet) {
            if (owner.getUUID().equals(pet.getOwnerUUID())) return false;
            if (pet.getOwner() != null && owner.isAlliedTo(pet.getOwner())) return false;
        }
        return true;
    }

    @SubscribeEvent
    public static void tick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        for (State state : List.copyOf(STATES.values())) {
            if (!valid(state)) { STATES.remove(state.owner.getUUID(), state); continue; }
            long now = state.level.getGameTime();
            state.points.removeIf(point -> point.until <= now);
            state.bursts.removeIf(burst -> now - burst.start >= HeatedStepsRules.BURST_TICKS
                    + (burst.tier >= 4 ? HeatedStepsRules.BURST_RING_DELAY : 0));
            if (!state.tracing && state.points.isEmpty() && state.bursts.isEmpty()) { STATES.remove(state.owner.getUUID(), state); continue; }
            for (Burst burst : List.copyOf(state.bursts)) {
                ring(state, burst, now, 0);
                if (burst.tier >= 4) ring(state, burst, now, 1);
            }
            var struck = new HashSet<UUID>();
            for (Point point : List.copyOf(state.points)) {
                if (STATES.get(state.owner.getUUID()) != state || !valid(state)) break;
                if (!state.level.hasChunkAt(BlockPos.containing(point.position))) continue;
                if (now % 4 == 0) {
                    double spread = point.parameters.radius() * .35;
                    state.level.sendParticles(ParticleTypes.FLAME, point.position.x, point.position.y + .08, point.position.z, 2, spread, .07, spread, .015);
                    if (now % 8 == 0) state.level.sendParticles(ParticleTypes.SMOKE, point.position.x, point.position.y + .1, point.position.z, 1, spread, .05, spread, .008);
                }
                if (now % point.parameters.interval() != 0) continue;
                double radius = point.parameters.radius();
                AABB bounds = new AABB(point.position, point.position).inflate(radius, 0, radius);
                bounds = new AABB(bounds.minX, bounds.minY - .2, bounds.minZ, bounds.maxX, bounds.maxY + 1.5, bounds.maxZ);
                for (LivingEntity target : state.level.getEntitiesOfClass(LivingEntity.class, bounds)) {
                    if (!enemy(state.owner, target) || struck.contains(target.getUUID())) continue;
                    var box = target.getBoundingBox();
                    double dx = Math.max(box.minX - point.position.x, Math.max(0, point.position.x - box.maxX));
                    double dz = Math.max(box.minZ - point.position.z, Math.max(0, point.position.z - box.maxZ));
                    if (dx * dx + dz * dz > radius * radius || state.level.clip(new ClipContext(point.position.add(0, .12, 0),
                            box.getCenter(), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, state.owner)).getType() != HitResult.Type.MISS) continue;
                    struck.add(target.getUUID());
                    if (target.hurt(state.damage, point.parameters.damage()) && point.parameters.burnTicks() > 0
                            && state.owner.getRandom().nextFloat() < point.parameters.burnChance())
                        target.setSecondsOnFire(Math.max(1, (point.parameters.burnTicks() + 19) / 20));
                }
            }
        }
    }

    private static void ring(State state, Burst burst, long now, int ring) {
        int age = (int)(now - burst.start) - ring * HeatedStepsRules.BURST_RING_DELAY;
        if (age < 0 || age >= HeatedStepsRules.BURST_TICKS || !valid(state) || STATES.get(state.owner.getUUID()) != state) return;
        double maximum = HeatedStepsRules.burstRadius(burst.tier);
        double radius = maximum * (age + 1) / HeatedStepsRules.BURST_TICKS;
        double previous = maximum * age / HeatedStepsRules.BURST_TICKS;
        Vec3 position = burst.position;
        if (!state.level.hasChunkAt(BlockPos.containing(position))) return;
        if (burst.tier < 4 && (age % 2 == 0 || age == HeatedStepsRules.BURST_TICKS - 1)) {
            for (int i = 0; i < 16; i++) {
                double angle = Math.PI * 2 * i / 16;
                double x = position.x + Math.cos(angle) * radius;
                double z = position.z + Math.sin(angle) * radius;
                state.level.sendParticles(i % 2 == 0 ? ParticleTypes.FLAME : ParticleTypes.SMALL_FLAME,
                        x, position.y + .08, z, 1, .02, .04, .02, .012);
                if (i % 4 == 0) state.level.sendParticles(ParticleTypes.SMOKE, x, position.y + .1, z, 1, .02, .03, .02, .008);
            }
        }
        var hit = ring == 0 ? burst.first : burst.second;
        for (LivingEntity target : state.level.getEntitiesOfClass(LivingEntity.class,
                new AABB(position, position).inflate(radius, 1.5, radius))) {
            if (!valid(state) || STATES.get(state.owner.getUUID()) != state) break;
            if (!enemy(state.owner, target) || hit.contains(target.getUUID())) continue;
            AABB box = target.getBoundingBox();
            double dx = Math.max(box.minX - position.x, Math.max(0, position.x - box.maxX));
            double dz = Math.max(box.minZ - position.z, Math.max(0, position.z - box.maxZ));
            double farX = Math.max(Math.abs(box.minX - position.x), Math.abs(box.maxX - position.x));
            double farZ = Math.max(Math.abs(box.minZ - position.z), Math.abs(box.maxZ - position.z));
            if (dx * dx + dz * dz > radius * radius || farX * farX + farZ * farZ < previous * previous
                    || state.level.clip(new ClipContext(position.add(0, .12, 0), box.getCenter(),
                    ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, state.owner)).getType() != HitResult.Type.MISS) continue;
            hit.add(target.getUUID());
            target.setSecondsOnFire(HeatedStepsRules.BURN_TICKS / 20);
            if (ring == 0 && burst.tier >= 4 && target.hurt(state.damage, 4)) {
                LivingEntityPatch<?> patch = EpicFightCapabilities.getEntityPatch(target, LivingEntityPatch.class);
                if (patch != null && target.isAlive()) patch.applyStun(StunType.KNOCKDOWN, 1);
            }
        }
    }

    public static void clear(ServerPlayer player) { STATES.remove(player.getUUID()); }
    @SubscribeEvent public static void logout(PlayerEvent.PlayerLoggedOutEvent event) { if (event.getEntity() instanceof ServerPlayer player) clear(player); }
    @SubscribeEvent public static void dimension(PlayerEvent.PlayerChangedDimensionEvent event) { if (event.getEntity() instanceof ServerPlayer player) clear(player); }
    @SubscribeEvent(priority = EventPriority.LOWEST) public static void death(LivingDeathEvent event) { if (!event.isCanceled() && event.getEntity() instanceof ServerPlayer player) clear(player); }
    @SubscribeEvent public static void stopped(ServerStoppedEvent event) { STATES.clear(); }
}
