package data.econ;


import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;


public class WthC_TitanEX extends BaseMarketConditionPlugin {
    // Parameter settings


    public WthC_TitanEX() {
    }


    public void apply(String id) {

        this.market.getStats().getDynamic().getMod("ground_defenses_mod").modifyFlat(id, 3000F, "Titans' Friend");
        this.market.getStability().modifyFlat(id, 2F, "Titans' Friend");

    }


    public void unapply(String id) {
        super.unapply(id);
        this.market.getStats().getDynamic().getMod("ground_defenses_mod").unmodify(id);
        this.market.getStability().unmodify(id);
    }


    protected void createTooltipAfterDescription(TooltipMakerAPI tooltip, boolean expanded) {
        super.createTooltipAfterDescription(tooltip, expanded);
        float Pad = 10F;

        tooltip.addPara("Ground defense capability: %s", Pad, Misc.getHighlightColor(), "+3000");
        tooltip.addPara("Stability: %s", Pad, Misc.getHighlightColor(), "+2");
    }
}