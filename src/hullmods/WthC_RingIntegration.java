//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_Util;

// 从其他插件简单修改

public class WthC_RingIntegration extends BaseHullMod {
    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");


    public WthC_RingIntegration() {
    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padS = 2f;
        tooltip.addSectionHeading("Effects on the Ship", Alignment.TMID, 10.0F);
        tooltip.addPara(" %s " + strings.get("RingIntegration_1"), pad, Misc.getPositiveHighlightColor(), "#", "1");
        tooltip.addPara(" %s " + strings.get("RingIntegration_2"), padS, Misc.getPositiveHighlightColor(), "#", "1");
    }
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getDynamic().getMod("max_permanent_hullmods_mod").modifyFlat(id, 1.0F);
    }

}
