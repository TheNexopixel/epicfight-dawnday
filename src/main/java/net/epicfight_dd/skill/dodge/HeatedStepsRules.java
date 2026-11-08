package net.epicfight_dd.skill.dodge;

public final class HeatedStepsRules {
    public static final int TAP_TICKS = 4;
    public static final int PERIOD = 40;
    public static final int RESULT_TICKS = 8;
    public static final int BURN_TICKS = 140;
    public static final int BURST_TICKS = 6;
    public static final int BURST_RING_DELAY = 3;
    private HeatedStepsRules() { }

    public static float marker(float ticks) {
        if (!Float.isFinite(ticks)) return 2;
        float phase = Math.max(0, ticks - TAP_TICKS) % PERIOD / (PERIOD / 2F);
        return 2 + (phase <= 1 ? phase : 2 - phase) * 59;
    }

    public static int tier(int ticks) {
        if (ticks < TAP_TICKS) return 0;
        int pixel = Math.round(marker(ticks));
        if (pixel >= 27 && pixel <= 36) return 4;
        if (pixel >= 22 && pixel <= 41) return 3;
        if (pixel >= 11 && pixel <= 51) return 2;
        return 1;
    }

    public static float distance(int tier) {
        return switch (tier) { case 2 -> 1.25F; case 3 -> 1.5F; case 4 -> 1.75F; default -> 1; };
    }

    public static double burstRadius(int tier) { return tier >= 4 ? 2.5 : tier >= 1 ? 2 : 1.5; }

    public static int validatedTicks(int requested, int server) {
        return requested >= 0 && Math.abs((long)requested - server) <= 4 ? requested : Math.max(0, server);
    }
}
