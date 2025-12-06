package data.econ;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.ui.TooltipMakerAPI;


public class OldGodZoo extends BaseIndustry {


    public void apply() {
        super.apply(true);

        market.removeCondition("WthC_OldGod");
        market.addCondition("WthC_OldGodEX");

        if (!isFunctional()) {
            supply.clear();
        }
    }


    public void unapply() {
        super.unapply();

        market.removeCondition("WthC_OldGodEX");
        market.addCondition("WthC_OldGod");

    }


    public boolean isAvailableToBuild() {
        if (!super.isAvailableToBuild()) return false;

        if (Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_AL-Zoo")){
            for (MarketConditionAPI mc : market.getConditions()) {
                if (mc.getId().equals("WthC_OldGod")) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean showWhenUnavailable() {

        if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_AL-Zoo")){
            return false;
        }
        for (MarketConditionAPI mc : market.getConditions()) {
            if (!mc.getId().equals("WthC_OldGod")) {
                return false;
            }
        }

        return super.showWhenUnavailable();
    }

    public boolean canImprove() {
        return false;
    }


    public String getUnavailableReason() {
        if (!super.isAvailableToBuild()) return super.getUnavailableReason();
        return "Requires substantial funding to convert the Old God's negative effects into positive ones";
    }


    public void createTooltip(IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded) {
        super.createTooltip(mode, tooltip, expanded);
    }





}