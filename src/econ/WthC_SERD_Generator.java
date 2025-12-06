package data.econ;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketImmigrationModifier;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;

import java.awt.Color;

// Modified from planetary shield

public class WthC_SERD_Generator extends BaseIndustry implements MarketImmigrationModifier {
    public static float DEFENSE_BONUS = 50000F;


    public WthC_SERD_Generator() {
    }

    public void apply() {
        super.apply(false);
        this.demand("cube_densefog", 3);
        int size = 5;
        this.applyIncomeAndUpkeep((float)size);
        float bonus = DEFENSE_BONUS;
        this.market.getStats().getDynamic().getMod("ground_defenses_mod").modifyFlat(this.getModId(), bonus, this.getNameForModifier());
        if (this.isFunctional()) {
            applyVisuals(this.market.getPlanetEntity());
        } else {
            this.unapply();
        }

    }

    public static void applyVisuals(PlanetAPI planet) {
        if (planet != null) {
            planet.applySpecChanges();
        }
    }

    public static void unapplyVisuals(PlanetAPI planet) {
        if (planet != null) {
            planet.applySpecChanges();
        }
    }

    public void unapply() {
        super.unapply();
        unapplyVisuals(this.market.getPlanetEntity());
        this.market.getStats().getDynamic().getMod("ground_defenses_mod").unmodifyFlat(this.getModId());
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


    protected boolean hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode) {
        return mode != IndustryTooltipMode.NORMAL || this.isFunctional();
    }

    protected void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {
        if (mode != IndustryTooltipMode.NORMAL || this.isFunctional()) {
            float bonus = DEFENSE_BONUS;
            this.addGroundDefensesImpactSection(tooltip, bonus, (String[])null);
        }
        Color h = Misc.getHighlightColor();
        tooltip.addPara("Population growth rate: %s", 10.0f, h, new String[]{"-" + 5});

    }
    public float getPopulationGrowthBonus() {
        boolean megaport = "megaport".equals(this.getId());
        float bonus = -5.0F;
        if (megaport) {
            bonus = (float)this.market.getSize();
        }

        return bonus;
    }
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        float bonus = this.getPopulationGrowthBonus();
        incoming.getWeight().modifyFlat(this.getModId(), bonus, this.getNameForModifier());
    }
    protected Pair<String, Integer> getStabilityAffectingDeficit() {
        return this.getMaxDeficit(new String[]{"cube_densefog"});
    }


}
