package net.epicfight_dd.gameasset.animation;

import net.epicfight_dd.EpicFightDawnDay;
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
    public static final Collider LIGHT_GREATSWORD = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"milady"),new MultiOBBCollider(4, 0.3D, 0.3D, 1.23D, 0D, 0.0D, -1.4D));
    public static final Collider HORUS_FANG = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"horus_fang"),
            new MultiOBBCollider(4, 0.3D, 0.50D, 1.4D,
                                            0.0D, -0.3D, -0.9D));

    public static final Collider HORUS_FANG_HUGE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"horus_fang_huge"),
            new MultiOBBCollider(3, 0.5D, 0.90D, 2.0D,
                    0.0D, -0.5D, -1.2D));
    public static final Collider GLORIA_ULTIMATE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"gloria_ult"),
            new MultiOBBCollider(1, 10.5D, 4.0D, 10.5D, 0D, 1.5D, 0.0D));

    public static final Collider GLORIA = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"gloria"),new MultiOBBCollider(5, 0.6D, 0.8D, 0.6D, 0.0D, -0.5D, -0.25D));
    public static final Collider TORNADO = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"tornado"),
            new MultiOBBCollider(1, 3.0D, 2.2D, 8.0D, 0D, 1.5D, -2.0D));
    public static final Collider RUSTY_AXE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"rusty_axe"),
            new MultiOBBCollider(4, 0.6D, 0.6D, 1.4D, 0D, 0.0D, -1.2D));


    public static final Collider GRIMM = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"grimm"),new MultiOBBCollider(4, 0.5D, 0.50D, 1.0D, 0D, 0.0D, -1.0D));
    public static final Collider SCYTHE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"scythe"),new MultiOBBCollider(4, 0.2D, 1.20D, 1.3D, 0D, 0.0D, -2.2D));
    public static final Collider HALBERD = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"halberd"),new MultiOBBCollider(4, 0.3D, 0.50D, 1.4D, 0D, 0.0D, -1.9D));
    public static final Collider NIGHTINGALE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"nightingale"),new MultiOBBCollider(4, 0.3D, 0.50D, 1.4D, 0D, 0.0D, -1.3D));
    public static final Collider HALBERD_INNATE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"halberd_innate"),new MultiOBBCollider(1, 0.3D, 0.5D, 0.8D, 0D, 0.0D, -2.1D));
    public static final Collider WARSICKLE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"warsickle"),new MultiOBBCollider(4, 0.3D, 0.3D, 1.5D, 0D, 0.0D, -1.0D));
    public static final Collider POLEBLADE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"poleblade"),new MultiOBBCollider(6, 0.5D, 0.5D, 2.8D, 0D, 0.0D, -0.0D));
    public static final Collider BACKHAND_BLADE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"backhand_blade"),new MultiOBBCollider(6, 0.35D, 0.35D, 0.9D, 0D, 0.0D, -0.8D));
    public static final Collider BATTLESTAFF_FRONT = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"battlestaff_front"),new MultiOBBCollider(4, 0.3D, 0.4D, 1.3D, 0D, 0.0D, -1.0D));
    public static final Collider WHIRLWIND1 = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"whirlwind1"),new MultiOBBCollider(1, 2.5D, 1.0D, 2.5D, 0D, 1.5D, 0.0D));
    public static final Collider WHIRLWIND2 = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"whirlwind2"),new MultiOBBCollider(1, 4.0D, 2.2D, 4.0D, 0D, 1.5D, 0.0D));
    public static final Collider MILADY_DASH = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"milady_dash"),new MultiOBBCollider(4, 1.5D, 0.6D, 1.2D, 0D, 1.8D, -0.8D));
    public static final Collider KNIFE_DASH = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"knife_dash"),new MultiOBBCollider(4, 0.5D, 0.7D, 0.8D, 0D, 0.0D, -0.8D));
    public static final Collider BACKHAND_INNATE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"backhand_innate"),new MultiOBBCollider(1, 1.8D, 1.0D, 2.1D, 0D, 1.5D, -0.4D));
    public static final Collider KNIFE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"knife"),new MultiOBBCollider(4, 0.2D, 0.3D, 0.8D, 0D, 0.1D, -0.7D));
    public static final Collider BAT = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"bat"),new MultiOBBCollider(4, 0.4D, 0.4D, 1.13D, 0D, 0.0D, -1.23D));
    public static final Collider BAT_BACK = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"bat_back"),new MultiOBBCollider(1, 0.55D, 0.55D, 0.7D, 0D, 0.0D, 0.6D));
    public static final Collider FLORETT = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"florett"),new MultiOBBCollider(4, 0.4D, 0.4D, 1.53D, 0D, 0.0D, -1.43D));
    public static final Collider VITREUS = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"vitreus"),new MultiOBBCollider(4, 0.4D, 0.4D, 1.53D, 0D, 0.0D, -1.43D));
    public static final Collider NAILBAT_INNATE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"nailbat_innate"),new MultiOBBCollider(1, 3.5D, 1.0D, 3.5D, 0D, 0.5D, 0.0D));
    public static final Collider BAT_LONGER = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"bat_lng"),new MultiOBBCollider(4, 0.4D, 0.4D, 1.53D, 0D, 0.0D, -1.43D));
    public static final Collider GROUNDSLAM = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"groundslam"),new MultiOBBCollider(1, 4.5D, 1.0D, 4.5D, 0D, 0.5D, 0.0D));
    public static final Collider BATTLESTAFF_FULL = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"battlestaff_full"),new MultiOBBCollider(6, 0.3D, 0.4D, 2.55D, 0D, 0.0D, 0.0D));
    public static final Collider BONE_SAW_AUTO4 = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"bonesaw4"),new MultiOBBCollider(1, 1.8D, 1.0D, 2.1D, 0D, 1.5D, -1.4D));
    public static final Collider WILDSTRIKES = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"wstrikes"),new MultiOBBCollider(4, 0.3D, 0.3D, 3.03D, 0D, 0.0D, -3.3D));
    public static final Collider KNIFE_BIGGER = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"knife_bigger"),new MultiOBBCollider(4, 0.4D, 0.5D, 1.0D, 0D, 0.1D, -0.8D));
    public static final Collider IRON_FIST = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"iron_fist"),new MultiOBBCollider(5, 0.6D, 0.8D, 0.6D, 0.0D, -0.5D, -0.25D));
    public static final Collider IRON_FIST_KICK = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"iron_fist_kick")
            ,new MultiOBBCollider(4, 0.9D, 0.9D, 0.9D, 0.0D, 0.2D, -0.55D));

    public static final Collider SICKLE_LONGER = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"sickle_long"),new MultiOBBCollider(5, 0.55D, 0.6D, 1.0D, 0D, 0.0D, -0.85D));
    public static final Collider HOOKCLAWS_SPECIAL = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"hookclaws_auto4"),new MultiOBBCollider(1, 1.2D, 1.0D, 1.5D, 0D, 1.5D, -1.2D));
    public static final Collider SICKLE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"sickle"),new MultiOBBCollider(5, 0.45D, 0.5D, 0.8D, 0D, 0.0D, -0.65D));
    public static final Collider BEAST_EYE = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"beast_eye"),new MultiOBBCollider(1, 1.8D, 1.0D, 7.1D, 0D, 1.5D, 6.6D));
    public static final Collider POLEBLADE_2 = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"poleblade_2"),new MultiOBBCollider(6, 0.5D, 0.5D, 2.8D, 0D, 0.0D, -2.0D));
    public static final Collider IRON_FIST_REACH = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"iron_fist_reach"),new MultiOBBCollider(5, 0.7D, 1.0D, 0.7D, 0.0D, -0.7D, -0.25D));
    public static final Collider IUDEX_HALBERD = registerCollider(ResourceLocation.fromNamespaceAndPath(EpicFightDawnDay.MODID,"iudex_halberd"),new MultiOBBCollider(4, 0.3D, 0.450D, 2.1D, 0D, 0.0D, -2.0D));





















}


