package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.Epicfight_dd;
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
    public static final Collider LIGHT_GREATSWORD = registerCollider(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID,"milady"),new MultiOBBCollider(6, 0.3D, 0.3D, 1.23D, 0D, 0.0D, -1.6D));
    public static final Collider HALBERD = registerCollider(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID,"halberd"),new MultiOBBCollider(6, 0.2D, 0.65D, 1D, 0D, 0.15D, -1.4D));
    public static final Collider HALBERD_INNATE = registerCollider(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID,"halberd_innate"),new MultiOBBCollider(1, 0.3D, 0.4D, 0.7D, 0D, 0.0D, -1.6D));
    public static final Collider WAR_SICKLE = registerCollider(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID,"war_sickle"),new MultiOBBCollider(6, 0.5D, 0.5D, 1.0D, 0D, 0.0D, -2.0D));
    public static final Collider BACKHAND_BLADE = registerCollider(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID,"backhand_blade"),new MultiOBBCollider(6, 0.4D, 0.4D, 1.1D, 0D, 0.0D, 1.3D));
    public static final Collider BATTLESTAFF_FRONT = registerCollider(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID,"battlestaff_front"),new MultiOBBCollider(6, 0.2D, 0.35D, 1.8D, 0D, 0.15D, -0.3D));
    public static final Collider BATTLESTAFF_BACK = registerCollider(ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID,"battlestaff_back"),new MultiOBBCollider(6, 0.2D, 0.35D, -0.8D, 0D, 0.15D, 1.5D));

}


