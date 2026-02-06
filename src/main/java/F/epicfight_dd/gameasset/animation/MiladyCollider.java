package F.epicfight_dd.gameasset.animation;

import F.epicfight_dd.Epicfight_dd;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.resources.ResourceLocation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;

public class MiladyCollider {

    private static final BiMap<ResourceLocation, Collider> PRESETS = HashBiMap.create();

    public static Collider registerCollider(ResourceLocation rl, Collider collider) {
        if (PRESETS.containsKey(rl)) {
            throw new IllegalStateException("Collider named " + rl + " already registered.");
        }
        PRESETS.put(rl, collider);

        return collider;
    }
        public static final Collider LIGHT_GREATSWORD = registerCollider(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID,"milady"),new MultiOBBCollider(3, 0.3D, 0.3D, 1.23D, 0D, 0.0D, -1.6D));
    public static final Collider WAR_SICKLE = registerCollider(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID,"war_sickle"),new MultiOBBCollider(3, 0.5D, 0.5D, 1.0D, 0D, 0.0D, -2.0D));

}


