package data.econ;


import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;


public class WthC_OldGodEX extends BaseMarketConditionPlugin {
    // Parameter settings

    public WthC_OldGodEX() {
    }


    public void apply(String id) {

        this.market.getAccessibilityMod().modifyFlat(id, 0.5F, "Old Gods' Aid");
        this.market.getHazard().modifyFlat(id, -0.25F, "Old Gods' Aid");

    }


    public void unapply(String id) {
        super.unapply(id);
        this.market.getAccessibilityMod().unmodify(id);
        this.market.getHazard().unmodify(id);
    }


    protected void createTooltipAfterDescription(TooltipMakerAPI tooltip, boolean expanded) {
        super.createTooltipAfterDescription(tooltip, expanded);
        float Pad = 10F;

        tooltip.addPara("Accessibility: %s", Pad, Misc.getHighlightColor(), "+50" + "%");
        tooltip.addPara("Hazard rating: %s", Pad, Misc.getHighlightColor(), "-25" + "%");
    }
}