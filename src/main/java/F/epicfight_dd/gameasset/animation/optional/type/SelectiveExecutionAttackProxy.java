package F.epicfight_dd.gameasset.animation.optional.type;

import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionAttackAnimation;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.client.animation.Layer;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class SelectiveExecutionAttackProxy extends ExecutionAttackAnimation {
    private final Function<LivingEntityPatch<?>, Integer> selector;
    private final List<AssetAccessor<? extends ExecutionAttackAnimation>> animationsInEachState;

    @SafeVarargs
    public SelectiveExecutionAttackProxy(
            Function<LivingEntityPatch<?>, Integer> selector,
            AnimationManager.AnimationAccessor<? extends SelectiveExecutionAttackProxy> accessor,
            AssetAccessor<? extends ExecutionAttackAnimation>... selectOptions) {

        // Init the LIST! before calling super to stop the compiler from bitching
        this(selector, accessor, List.of(selectOptions));
    }

    // Private constructor that does the actual work
    private SelectiveExecutionAttackProxy(
            Function<LivingEntityPatch<?>, Integer> selector,
            AnimationManager.AnimationAccessor<? extends SelectiveExecutionAttackProxy> accessor,
            List<AssetAccessor<? extends ExecutionAttackAnimation>> selectOptions) {

        // Call parent W a dummy phase
        super(0.15F, accessor, selectOptions.get(0).get().getArmature(),
                new ExecutionPhase(true, InteractionHand.MAIN_HAND, null, null));


        this.selector = selector;
        this.animationsInEachState = new ArrayList<>(selectOptions);
    }

    @Override
    public void begin(LivingEntityPatch<?> entitypatch) {
        // Select animation
        int selectedIndex = this.selector.apply(entitypatch);
        selectedIndex = Math.max(0, Math.min(selectedIndex, this.animationsInEachState.size() - 1));


        entitypatch.getAnimator().playAnimation(this.animationsInEachState.get(selectedIndex), 0.0F);
    }

    @Override
    public void tick(LivingEntityPatch<?> entitypatch) {

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