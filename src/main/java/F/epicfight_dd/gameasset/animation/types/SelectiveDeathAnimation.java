package F.epicfight_dd.gameasset.animation.types;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.client.animation.Layer;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.List;
import java.util.function.Function;

public class SelectiveDeathAnimation extends StaticAnimation {
    private final Function<LivingEntityPatch<?>, Integer> selector;
    private final List<AssetAccessor<? extends StaticAnimation>> animationsInEachState;

    @SafeVarargs
    public SelectiveDeathAnimation(Function<LivingEntityPatch<?>, Integer> selector,
                                   AnimationManager.AnimationAccessor<? extends SelectiveDeathAnimation> accessor,
                                   AssetAccessor<? extends StaticAnimation>... selectOptions) {
        super(0.15F, false, accessor, null);
        this.selector = selector;
        this.animationsInEachState = List.of(selectOptions);
    }

    @Override
    public void begin(LivingEntityPatch<?> entitypatch) {
        super.begin(entitypatch);

        // Determine which animation at the start
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
        return this.animationsInEachState;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Layer.Priority getPriority() {
        return this.animationsInEachState.get(0).get().getPriority();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Layer.LayerType getLayerType() {
        return this.animationsInEachState.get(0).get().getLayerType();
    }

}
