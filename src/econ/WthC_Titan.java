package data.econ;


import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;


public class WthC_Titan extends BaseMarketConditionPlugin {
    // Parameter settings
    private static final float STABILITY = -1F;
    private static final float HAZARD = 0.75F;

    public WthC_Titan() {
    }


    public void apply(String id) {

        this.market.getStability().modifyFlat(id, STABILITY, "Titans Pervasive");
        this.market.getHazard().modifyFlat(id, HAZARD, "Titans Pervasive");

    }


    public void unapply(String id) {
        super.unapply(id);
        this.market.getStability().unmodify(id);
        this.market.getHazard().unmodify(id);
    }


    protected void createTooltipAfterDescription(TooltipMakerAPI tooltip, boolean expanded) {
        super.createTooltipAfterDescription(tooltip, expanded);
        float Pad = 10F;

        tooltip.addPara("Hazard rating: %s", Pad, Misc.getHighlightColor(), "+75" + "%");
        tooltip.addPara("Stability: %s", Pad, Misc.getHighlightColor(), "-1");
    }
}