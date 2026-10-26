package net.epicfight_dd.skill.passive.unstoppable;

public final class UnstoppableRules {
    public static final int BASE_CAP = 3;
    public static final int MAX_CAP = 13;
    public static final int BARRIER_COOLDOWN = 200;
    public static final int REGENERATION_INTERVAL = 1200;

    private UnstoppableRules() {
    }

    public static int cap(double armor) {
        if (Double.isNaN(armor) || armor <= 0) {
            return BASE_CAP;
        }
        return BASE_CAP + (int) Math.min(MAX_CAP - BASE_CAP, Math.floor(armor / 2));
    }

    public static int clamp(int stacks, int cap) {
        return Math.max(0, Math.min(stacks, Math.min(MAX_CAP, Math.max(BASE_CAP, cap))));
    }

    public static int kill(int stacks, int cap, boolean player) {
        return clamp(clamp(stacks, cap) + (player ? 6 : 1), cap);
    }

    public static double healingPenalty(int stacks) {
        return -0.07592307 * clamp(stacks, MAX_CAP);
    }

    public static boolean stunImmune(int stacks) {
        return stacks > 0;
    }

    public static boolean uninterruptedStamina(int stacks) {
        return stacks >= 7;
    }

    public static boolean barrierReady(int stacks, int cooldown) {
        return stacks >= MAX_CAP && cooldown <= 0;
    }

    public static Hit hit(int stacks, int cooldown, float damage, boolean bypassesInvulnerability) {
        int current = clamp(stacks, MAX_CAP);
        if (!(damage > 0)) {
            return new Hit(current, Math.max(0, cooldown), damage, false, false);
        }
        boolean absorbed = !bypassesInvulnerability && barrierReady(current, cooldown);
        float multiplier = !bypassesInvulnerability && current >= 4 ? 0.5F : 1.0F;
        return new Hit(absorbed ? current : Math.max(0, current - 1), absorbed ? BARRIER_COOLDOWN : Math.max(0, cooldown),
                absorbed ? 0 : damage * multiplier, absorbed, !absorbed && uninterruptedStamina(current));
    }

    public record Hit(int stacks, int cooldown, float damage, boolean absorbed, boolean uninterruptedStamina) {
    }
}
