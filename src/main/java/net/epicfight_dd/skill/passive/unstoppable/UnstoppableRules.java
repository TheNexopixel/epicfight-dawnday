package net.epicfight_dd.skill.passive.unstoppable;

public final class UnstoppableRules {
    public static final int BASE_CAP = 3;
    public static final int MAX_CAP = 13;

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

    public static double regenerationRate(double healing) {
        double scale = Double.isFinite(healing) ? Math.max(0, Math.min(1, healing)) : 0;
        return 2 / (2 - scale);
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

    public static float damageMultiplier(int stacks, boolean bypassesInvulnerability) {
        return !bypassesInvulnerability && stacks >= 4 ? 0.5F : 1.0F;
    }
}
