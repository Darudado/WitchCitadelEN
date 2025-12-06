package data.econ;


import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;


public class WthC_DarkWorld extends BaseMarketConditionPlugin {
    // Parameter settings
    private static final float STABILITY = -2F;


    public WthC_DarkWorld() {
    }


    public void apply(String id) {

        this.market.getStability().modifyFlat(id, STABILITY, "Dark World Remnants");

    }


    public void unapply(String id) {
        super.unapply(id);
        this.market.getStability().unmodify(id);
        this.market.getHazard().unmodify(id);
    }


    protected void createTooltipAfterDescription(TooltipMakerAPI tooltip, boolean expanded) {
        super.createTooltipAfterDescription(tooltip, expanded);
        float Pad = 10F;

        tooltip.addPara("Stability: %s", Pad, Misc.getHighlightColor(), "-2");
    }
}