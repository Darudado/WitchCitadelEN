package data.econ;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.Industry.IndustryTooltipMode;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import java.awt.Color;

// Modified from light industry

public class WthC_WorkShopIE extends BaseIndustry {
    public static float ORBITAL_WORKS_QUALITY_BONUS = 1.0F;


    public WthC_WorkShopIE() {
    }

    public void apply() {
        super.apply(true);
        float qualityBonus = ORBITAL_WORKS_QUALITY_BONUS;



        this.demand("cube_deepbule", 3);
        this.supply("ships", 2);
        this.supply("supplies", 1);
        this.supply("fuel", 1);

        Pair<String, Integer> deficit = this.getMaxDeficit(new String[]{"cube_deepbule"});


        this.applyDeficitToProduction(2, deficit, new String[]{"ships","supplies","fuel"});
        if (qualityBonus > 0.0F) {
            this.market.getStats().getDynamic().getMod("production_quality_mod").modifyFlat(this.getModId(1), qualityBonus, "Primordial Workshop");
        }


        if (!this.isFunctional()) {
            this.supply.clear();
            this.unapply();
        }

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

    public void unapply() {
        super.unapply();
        this.market.getStats().getDynamic().getMod("production_quality_mod").unmodifyFlat(this.getModId(0));
        this.market.getStats().getDynamic().getMod("production_quality_mod").unmodifyFlat(this.getModId(1));
    }


    public boolean isDemandLegal(CommodityOnMarketAPI com) {
        return true;
    }

    public boolean isSupplyLegal(CommodityOnMarketAPI com) {
        return true;
    }

}