package net.dawn_day.skill.weapon_innate;

import com.google.common.collect.Maps;
import net.dawn_day.gameasset.animation.DawnDayAnimations;
import net.minecraft.nbt.CompoundTag;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

import java.util.Map;
import java.util.Objects;

public class BloodDanceSkill extends WeaponInnateSkill {
    private final Map<AnimationManager.AnimationAccessor<? extends StaticAnimation>, AnimationManager.AnimationAccessor<? extends AttackAnimation>> comboAnimation = Maps.newHashMap();

    public BloodDanceSkill(WeaponInnateSkill.Builder<?> builder) {
        super(builder);
    }

    @Override
    public void executeOnServer(SkillContainer container, CompoundTag arguments) {
        AssetAccessor<? extends DynamicAnimation> animation = Objects.requireNonNull(container.getExecutor().getAnimator().getPlayerFor(null)).getAnimation();

        if (this.comboAnimation.containsKey(animation)) {
            container.getExecutor().playAnimationSynchronized(this.comboAnimation.get(animation), 0.0F);
            super.executeOnServer(container, arguments);
        }
    }

    @Override
    public boolean checkExecuteCondition(SkillContainer container) {
        EntityState playerState = container.getExecutor().getEntityState();

        return this.comboAnimation.containsKey(Objects.requireNonNull(container.getExecutor().getAnimator().getPlayerFor(null)).getAnimation()) && playerState.canUseSkill() && playerState.inaction();
    }
    @Override
    public WeaponInnateSkill registerPropertiesToAnimation() {
        this.comboAnimation.clear();

        this.comboAnimation.put(
                DawnDayAnimations.POLEBLADE_AUTO1,
                DawnDayAnimations.BLOOD_DANCE_1
        );

        this.comboAnimation.put(
                DawnDayAnimations.POLEBLADE_AUTO2,
                DawnDayAnimations.BLOOD_DANCE_2
        );

        this.comboAnimation.put(
                DawnDayAnimations.POLEBLADE_AUTO3,
                DawnDayAnimations.BLOOD_DANCE_3
        );
        this.comboAnimation.put(
                DawnDayAnimations.BLOOD_DANCE_3,
                DawnDayAnimations.BLOOD_DANCE_3B
        );

        this.comboAnimation.put(
                DawnDayAnimations.POLEBLADE_AUTO4,
                DawnDayAnimations.BLOOD_DANCE_4
        );
        this.comboAnimation.put(
                DawnDayAnimations.POLEBLADE_DASH,
                DawnDayAnimations.BLOOD_DANCE_DASH
        );
        this.comboAnimation.put(
                DawnDayAnimations.POLEBLADE_AIRSLASH,
                DawnDayAnimations.BLOOD_DANCE_AIRSLASH
        );
        this.comboAnimation.put(
                DawnDayAnimations.POLEBLADE_COUNTER,
                DawnDayAnimations.BLOOD_DANCE_1
        );
        return this;
    }


}
