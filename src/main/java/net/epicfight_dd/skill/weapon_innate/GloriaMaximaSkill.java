package net.epicfight_dd.skill.weapon_innate;

import com.google.common.collect.Maps;
import net.epicfight_dd.gameasset.animation.DawnDayAnimations;
import net.epicfight_dd.gameasset.animation.WoMCompatAnimations;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

import java.util.Map;
import java.util.Objects;

public class GloriaMaximaSkill extends WeaponInnateSkill {

    private final Map<AnimationManager.AnimationAccessor<? extends StaticAnimation>, AnimationManager.AnimationAccessor<? extends AttackAnimation>> comboAnimation = Maps.newHashMap();

    public GloriaMaximaSkill(SkillBuilder<? extends WeaponInnateSkill> builder) {
        super(builder);
    }
    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
    }

    @Override
    public void onRemoved(SkillContainer container) {
    }
    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {
        AssetAccessor<? extends DynamicAnimation> animation = Objects.requireNonNull(container.getExecutor().getAnimator().getPlayerFor(null)).getAnimation();

        if (this.comboAnimation.containsKey(animation)) {
            container.getExecutor().playAnimationSynchronized(this.comboAnimation.get(animation), 0.0F);
            super.executeOnServer(container, args);
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
                DawnDayAnimations.GLORIA_AUTO1,
                DawnDayAnimations.GLORIA_SKILL1
        );

        this.comboAnimation.put(
                DawnDayAnimations.GLORIA_AUTO2,
                DawnDayAnimations.GLORIA_SKILL2
        );

        this.comboAnimation.put(
                DawnDayAnimations.GLORIA_AUTO3,
                DawnDayAnimations.GLORIA_SKILL3
        );
        this.comboAnimation.put(
                DawnDayAnimations.GLORIA_AUTO4,
                DawnDayAnimations.GLORIA_SKILL4
        );
        this.comboAnimation.put(
                DawnDayAnimations.GLORIA_DASH,
                DawnDayAnimations.GLORIA_DASH_SKILL
        );
        this.comboAnimation.put(
                DawnDayAnimations.GLORIA_AIRSLASH,
                DawnDayAnimations.GLORIA_AIRSLASH_SKILL
        );


        return this;
    }


}
