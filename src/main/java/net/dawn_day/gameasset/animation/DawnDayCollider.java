package net.dawn_day.gameasset.animation;

import net.dawn_day.EpicFightDawnDay;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.resources.ResourceLocation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;

public class DawnDayCollider {

    private static final BiMap<ResourceLocation, Collider> PRESETS = HashBiMap.create();

    public static Collider registerCollider(ResourceLocation rl, Collider collider) {
        if (PRESETS.containsKey(rl)) {
            throw new IllegalStateException("Collider named " + rl + " already registered.");
        }
        PRESETS.put(rl, collider);

        return collider;
    }
    public static final Collider LIGHT_GREATSWORD = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"milady"),new MultiOBBCollider(4, 0.3D, 0.3D, 1.23D, 0D, 0.0D, -1.4D));
    public static final Collider HALBERD = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"halberd"),new MultiOBBCollider(4, 0.3D, 0.50D, 1.4D, 0D, 0.0D, -1.3D));
    public static final Collider HALBERD_INNATE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"halberd_innate"),new MultiOBBCollider(1, 0.3D, 0.5D, 0.8D, 0D, 0.0D, -2.1D));
    public static final Collider WARSICKLE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"warsickle"),new MultiOBBCollider(4, 0.3D, 0.3D, 1.5D, 0D, 0.0D, -1.0D));
    public static final Collider POLEBLADE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"poleblade"),new MultiOBBCollider(6, 0.5D, 0.5D, 2.8D, 0D, 0.0D, -0.0D));
    public static final Collider BACKHAND_BLADE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"backhand_blade"),new MultiOBBCollider(6, 0.35D, 0.35D, 0.9D, 0D, 0.0D, -0.8D));
    public static final Collider BATTLESTAFF_FRONT = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"battlestaff_front"),new MultiOBBCollider(4, 0.3D, 0.4D, 1.3D, 0D, 0.0D, -1.0D));
    public static final Collider WHIRLWIND1 = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"whirlwind1"),new MultiOBBCollider(1, 2.5D, 1.0D, 2.5D, 0D, 1.5D, 0.0D));
    public static final Collider WHIRLWIND2 = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"whirlwind2"),new MultiOBBCollider(1, 4.0D, 1.2D, 4.0D, 0D, 1.5D, 0.0D));
    public static final Collider MILADY_DASH = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"milady_dash"),new MultiOBBCollider(4, 1.5D, 0.6D, 1.2D, 0D, 1.8D, -0.8D));
    public static final Collider KNIFE_DASH = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"knife_dash"),new MultiOBBCollider(4, 0.5D, 0.7D, 0.8D, 0D, 0.0D, -0.8D));
    public static final Collider BACKHAND_INNATE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"backhand_innate"),new MultiOBBCollider(1, 1.8D, 1.0D, 2.1D, 0D, 1.5D, -0.4D));
    public static final Collider KNIFE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"knife"),new MultiOBBCollider(4, 0.2D, 0.3D, 0.8D, 0D, 0.1D, -0.7D));
    public static final Collider BAT = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"bat"),new MultiOBBCollider(4, 0.4D, 0.4D, 1.13D, 0D, 0.0D, -1.23D));
    public static final Collider BAT_BACK = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"bat_back"),new MultiOBBCollider(1, 0.55D, 0.55D, 0.7D, 0D, 0.0D, 0.6D));
    public static final Collider NAILBAT_INNATE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"nailbat_innate"),new MultiOBBCollider(1, 3.5D, 1.0D, 3.5D, 0D, 0.5D, 0.0D));
    public static final Collider BAT_LONGER = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"bat_lng"),new MultiOBBCollider(4, 0.4D, 0.4D, 1.53D, 0D, 0.0D, -1.43D));
    public static final Collider GROUNDSLAM = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"groundslam"),new MultiOBBCollider(1, 4.5D, 1.0D, 4.5D, 0D, 0.5D, 0.0D));
    public static final Collider BATTLESTAFF_FULL = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"battlestaff_full"),new MultiOBBCollider(6, 0.3D, 0.4D, 2.55D, 0D, 0.0D, 0.0D));
    public static final Collider BONE_SAW_AUTO4 = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"bonesaw4"),new MultiOBBCollider(1, 1.8D, 1.0D, 2.1D, 0D, 1.5D, -1.4D));
    public static final Collider WILDSTRIKES = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"wstrikes"),new MultiOBBCollider(4, 0.3D, 0.3D, 3.03D, 0D, 0.0D, -3.3D));
    public static final Collider KNIFE_BIGGER = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"knife_bigger"),new MultiOBBCollider(4, 0.4D, 0.5D, 1.0D, 0D, 0.1D, -0.8D));
    public static final Collider IRON_FIST = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"iron_fist"),new MultiOBBCollider(5, 0.6D, 0.8D, 0.6D, 0.0D, -0.5D, -0.25D));
    public static final Collider IRON_FIST_KICK = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"iron_fist_kick"),new MultiOBBCollider(4, 0.7D, 0.5D, 0.6D, 0.4D, 0.0D, -0.55D));
    public static final Collider SICKLE_LONGER = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"sickle_long"),new MultiOBBCollider(5, 0.55D, 0.6D, 1.0D, 0D, 0.0D, -0.85D));
    public static final Collider HOOKCLAWS_SPECIAL = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"hookclaws_auto4"),new MultiOBBCollider(1, 1.2D, 1.0D, 1.5D, 0D, 1.5D, -1.2D));
    public static final Collider SICKLE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"sickle"),new MultiOBBCollider(5, 0.45D, 0.5D, 0.8D, 0D, 0.0D, -0.65D));
    public static final Collider BEAST_EYE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"beast_eye"),new MultiOBBCollider(1, 1.8D, 1.0D, 7.1D, 0D, 1.5D, 6.6D));
    public static final Collider POLEBLADE_2 = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"poleblade_2"),new MultiOBBCollider(6, 0.5D, 0.5D, 2.8D, 0D, 0.0D, -2.0D));
    public static final Collider IRON_FIST_REACH = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"iron_fist_reach"),new MultiOBBCollider(5, 0.7D, 1.0D, 0.7D, 0.0D, -0.7D, -0.25D));
    public static final Collider IUDEX_HALBERD = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MOD_ID,"iudex_halberd"),new MultiOBBCollider(4, 0.3D, 0.450D, 2.1D, 0D, 0.0D, -2.0D));

















}


