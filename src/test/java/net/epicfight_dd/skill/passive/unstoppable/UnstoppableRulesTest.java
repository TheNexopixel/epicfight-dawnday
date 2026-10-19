package net.epicfight_dd.skill.passive.unstoppable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class UnstoppableRulesTest {
    @ParameterizedTest
    @CsvSource({"-2,3", "0,3", "1.99,3", "2,4", "4,5", "10,8", "19.99,12", "20,13", "1000,13"})
    void armorRaisesCapEveryTwoPoints(double armor, int cap) {
        assertEquals(cap, UnstoppableRules.cap(armor));
    }

    @Test
    void nonFiniteArmorIsBounded() {
        assertEquals(3, UnstoppableRules.cap(Double.NaN));
        assertEquals(13, UnstoppableRules.cap(Double.POSITIVE_INFINITY));
        assertEquals(3, UnstoppableRules.cap(Double.NEGATIVE_INFINITY));
    }

    @Test
    void killsGrantOneOrSixButNeverExceedArmorCap() {
        assertEquals(1, UnstoppableRules.kill(0, 13, false));
        assertEquals(6, UnstoppableRules.kill(0, 13, true));
        assertEquals(3, UnstoppableRules.kill(0, 3, true));
        assertEquals(13, UnstoppableRules.kill(12, 13, true));
        assertEquals(3, UnstoppableRules.clamp(13, 3));
    }

    @ParameterizedTest
    @CsvSource({"0,0", "1,-0.03", "3,-0.09", "6,-0.18", "7,-0.21", "12,-0.36", "13,-0.39"})
    void healingPenaltyIsThreePercentPerStack(int stacks, double penalty) {
        assertEquals(penalty, UnstoppableRules.healingPenalty(stacks), 0.000001);
    }

    @ParameterizedTest
    @CsvSource({"0,false,false", "1,true,false", "3,true,false", "4,true,false", "6,true,false",
            "7,true,true", "12,true,true", "13,true,true"})
    void thresholdBonusesAccumulate(int stacks, boolean stun, boolean stamina) {
        assertEquals(stun, UnstoppableRules.stunImmune(stacks));
        assertEquals(stamina, UnstoppableRules.uninterruptedStamina(stacks));
    }

    @ParameterizedTest
    @CsvSource({"0,10", "1,10", "3,10", "4,5", "6,5", "7,5", "12,5", "13,5"})
    void damageReductionUsesStackCountBeforeTheHit(int stacks, float damage) {
        var hit = UnstoppableRules.hit(stacks, 100, 10, false);
        assertEquals(damage, hit.damage());
        assertEquals(Math.max(0, stacks - 1), hit.stacks());
        assertFalse(hit.absorbed());
    }

    @Test
    void barrierAbsorbsOneHitAndStartsTenSecondCooldown() {
        var hit = UnstoppableRules.hit(13, 0, 100, false);
        assertTrue(hit.absorbed());
        assertEquals(0, hit.damage());
        assertEquals(13, hit.stacks());
        assertFalse(hit.uninterruptedStamina());
        assertEquals(200, hit.cooldown());
        assertFalse(UnstoppableRules.barrierReady(12, 0));
        assertFalse(UnstoppableRules.barrierReady(13, 1));
        assertTrue(UnstoppableRules.barrierReady(13, 0));
    }

    @Test
    void barrierDoesNotAbsorbAnotherHitDuringCooldown() {
        var first = UnstoppableRules.hit(13, 0, 100, false);
        int stacks = UnstoppableRules.kill(first.stacks(), 13, false);
        var second = UnstoppableRules.hit(stacks, first.cooldown(), 100, false);
        assertFalse(second.absorbed());
        assertEquals(50, second.damage());
        assertEquals(200, second.cooldown());
        assertEquals(12, second.stacks());
    }

    @Test
    void barrierReturnsAfterCooldownWithoutRequiringAnotherKill() {
        var first = UnstoppableRules.hit(13, 0, 100, false);
        assertEquals(13, first.stacks());
        assertFalse(UnstoppableRules.barrierReady(first.stacks(), first.cooldown()));
        assertTrue(UnstoppableRules.barrierReady(first.stacks(), 0));
        var next = UnstoppableRules.hit(first.stacks(), 0, 100, false);
        assertTrue(next.absorbed());
        assertEquals(13, next.stacks());
    }

    @ParameterizedTest
    @CsvSource({"0,0,0,false,false", "10,0,0,false,true", "10,10,10,false,true",
            "10,11,10,false,false", "10,20,20,false,false", "20,20,10,false,true", "10,20,10,true,true"})
    void ignoredAttacksDoNotConsumeBarrier(float damage, int invulnerableTicks, float lastDamage,
                                         boolean bypassesCooldown, boolean canHurt) {
        assertEquals(canHurt, UnstoppableRules.attackCanHurt(damage, invulnerableTicks, lastDamage, bypassesCooldown));
    }

    @ParameterizedTest
    @CsvSource({"10,0,0", "10,1,6", "1,1,1", "10,2,10", "10,3,15"})
    void barrierUsesVanillaDifficultyScalingBeforeInvulnerabilityCheck(float damage, int difficulty, float expected) {
        assertEquals(expected, UnstoppableRules.difficultyDamage(damage, difficulty));
    }

    @Test
    void nonDamagingHitsDoNotConsumeStacksOrBarrier() {
        var hit = UnstoppableRules.hit(13, 0, 0, false);
        assertEquals(13, hit.stacks());
        assertFalse(hit.absorbed());
        assertEquals(0, hit.cooldown());
    }

    @Test
    void killCommandIsNotBlockedByBarrierOrReduction() {
        var hit = UnstoppableRules.hit(13, 0, 100, true);
        assertFalse(hit.absorbed());
        assertEquals(100, hit.damage());
    }

    @Test
    void separatePlayersDoNotShareState() {
        var first = UnstoppableRules.hit(13, 0, 10, false);
        var second = UnstoppableRules.hit(13, 0, 20, false);
        assertTrue(first.absorbed());
        assertTrue(second.absorbed());
        assertEquals(1, UnstoppableRules.kill(0, 3, false));
    }
}
