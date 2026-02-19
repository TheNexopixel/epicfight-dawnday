package net.epicfight_dd.gameasset.animation.optional.type;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionHitAnimation;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.client.animation.Layer;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class SelectiveExecutionHitAnimation extends ExecutionHitAnimation {
    private final Function<LivingEntityPatch<?>, Integer> selector;
    private final List<AssetAccessor<? extends ExecutionHitAnimation>> animationsInEachState;

    @SafeVarargs
    public SelectiveExecutionHitAnimation(
            Function<LivingEntityPatch<?>, Integer> selector,
            float transitionTime,
            AnimationManager.AnimationAccessor<? extends SelectiveExecutionHitAnimation> accessor,
            AssetAccessor<? extends Armature> armature,
            AssetAccessor<? extends ExecutionHitAnimation>... selectOptions) {

        // Call private constructor otherwise compiler will bitch
        this(selector, transitionTime, accessor, armature, List.of(selectOptions));
    }

    // Private constructor
    private SelectiveExecutionHitAnimation(
            Function<LivingEntityPatch<?>, Integer> selector,
            float transitionTime,
            AnimationManager.AnimationAccessor<? extends SelectiveExecutionHitAnimation> accessor,
            AssetAccessor<? extends Armature> armature,
            List<AssetAccessor<? extends ExecutionHitAnimation>> selectOptions) {

        super(transitionTime, accessor, armature);
        this.selector = selector;
        this.animationsInEachState = new ArrayList<>(selectOptions);
    }

    @Override
    public void begin(LivingEntityPatch<?> entitypatch) {

        int selectedIndex = this.selector.apply(entitypatch);
        selectedIndex = Math.max(0, Math.min(selectedIndex, this.animationsInEachState.size() - 1));


        entitypatch.playAnimationSynchronized(this.animationsInEachState.get(selectedIndex), 0.0F);
    }

    @Override
    public void tick(LivingEntityPatch<?> entitypatch) {
        // Empty
    }

    @Override
    public boolean isMetaAnimation() {
        return true;
    }

    @Override
    public List<AssetAccessor<? extends StaticAnimation>> getSubAnimations() {
        // Return empty list if not initialized yet (during construction)
        if (this.animationsInEachState == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(this.animationsInEachState);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Layer.Priority getPriority() {
        if (this.animationsInEachState == null || this.animationsInEachState.isEmpty()) {
            return Layer.Priority.LOWEST;
        }
        return this.animationsInEachState.get(0).get().getPriority();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Layer.LayerType getLayerType() {
        if (this.animationsInEachState == null || this.animationsInEachState.isEmpty()) {
            return Layer.LayerType.BASE_LAYER;
        }
        return this.animationsInEachState.get(0).get().getLayerType();
    }
}