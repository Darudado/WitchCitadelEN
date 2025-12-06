package data.econ;


import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;


public class WthC_Xijie extends BaseMarketConditionPlugin {
    // Parameter settings
    private static final float STABILITY = -3F;
    private static final float HAZARD = 2.0F;

    public WthC_Xijie() {
    }


    public void apply(String id) {

        this.market.getStability().modifyFlat(id, STABILITY, "Void Realm");
        this.market.getHazard().modifyFlat(id, HAZARD, "Void Realm");

    }


    public void unapply(String id) {
        super.unapply(id);
        this.market.getStability().unmodify(id);
        this.market.getHazard().unmodify(id);
    }


    protected void createTooltipAfterDescription(TooltipMakerAPI tooltip, boolean expanded) {
        super.createTooltipAfterDescription(tooltip, expanded);
        float Pad = 10F;

        tooltip.addPara("Hazard rating: %s", Pad, Misc.getHighlightColor(), "+200" + "%");
        tooltip.addPara("Stability: %s", Pad, Misc.getHighlightColor(), "-3");
    }
}