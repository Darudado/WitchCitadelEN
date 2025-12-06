package data.econ;

import java.util.HashSet;
import java.util.Set;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.campaign.econ.MarketImmigrationModifier;
import com.fs.starfarer.api.impl.campaign.econ.ResourceDepositsCondition;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Planets;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD.RaidDangerLevel;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Pair;


public class TitanZoo extends BaseIndustry {


    public void apply() {
        super.apply(true);

        market.removeCondition("WthC_Titan");
        market.addCondition("WthC_TitanEX");

        if (!isFunctional()) {
            supply.clear();
        }
    }


    public void unapply() {
        super.unapply();

        market.removeCondition("WthC_TitanEX");
        market.addCondition("WthC_Titan");

    }


    public boolean isAvailableToBuild() {
        if (!super.isAvailableToBuild()) return false;

        if (Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_AL-Zoo")){
            for (MarketConditionAPI mc : market.getConditions()) {
                if (mc.getId().equals("WthC_Titan")) {
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
            if (!mc.getId().equals("WthC_Titan")) {
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
        return "Requires substantial funding to convert the Titan's negative effects into positive ones";
    }


    public void createTooltip(IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded) {
        super.createTooltip(mode, tooltip, expanded);
    }





}