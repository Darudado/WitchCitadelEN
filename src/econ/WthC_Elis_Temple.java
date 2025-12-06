package data.econ;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

// Modified from light industry


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;

import java.awt.*;

public class WthC_Elis_Temple extends BaseIndustry {
    public static float IMPROVE_STABILITY_BONUS = 5.0F;

    public WthC_Elis_Temple() {
    }

    public void apply() {
        super.apply(true);
        this.demand("cube_crimson", 3);
        this.market.getStability().modifyFlat("ground_defenses_mod", IMPROVE_STABILITY_BONUS, this.getImprovementsDescForModifiers());

    }

    protected Pair<String, Integer> getStabilityAffectingDeficit() {
        return this.getMaxDeficit(new String[]{"cube_crimson"});
    }
    public boolean isAvailableToBuild() {
        return false;
    }

    public boolean showWhenUnavailable() {
        return false;
    }

    public boolean canImprove() {
        return false;
    }

    public String getUnavailableReason() {
        return !super.isAvailableToBuild() ? super.getUnavailableReason() : "Cannot be built on gas giants";
    }

    public void unapply() {
        super.unapply();
    }


    protected void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {
        Color h = Misc.getHighlightColor();
        tooltip.addPara("Colony stability: %s", 10.0f, h, new String[]{"+" + 5});

    }
}
