package net.epicfight_dd.compat;

import com.hm.efn.EFN;
import net.epicfight_dd.skill.skill_compats.CombatEvoCompat;
import net.epicfight_dd.skill.skill_compats.NightfallCompat;
import net.epicfight_dd.skill.skill_compats.WoMCompat;
import net.shelmarow.combat_evolution.CombatEvolution;
import reascer.wom.main.WeaponsOfMinecraft;
import yesman.epicfight.compat.ICompatModule;

public enum DawnDayCompats {
    WOM(WoMCompat.class, WeaponsOfMinecraft.MODID),
    NIGHT_FALL(NightfallCompat.class, EFN.MODID),
    COMBAT_EVO(CombatEvoCompat.class, CombatEvolution.MOD_ID);

    private final Class<? extends ICompatModule> moduleCls;
    private final String modId;

    DawnDayCompats(Class<? extends ICompatModule> moduleCls, String modId) {
        this.moduleCls = moduleCls;
        this.modId = modId;
    }

    public Class<? extends ICompatModule> getModuleClass() {
        return this.moduleCls;
    }

    public String getModId() {
        return this.modId;
    }
}