package data.econ;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketImmigrationModifier;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.intel.events.ht.HyperspaceTopographyEventIntel;
import com.fs.starfarer.api.impl.campaign.intel.events.ht.HyperspaceTopographyEventIntel.Stage;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import java.awt.Color;

// Modified from spaceport
public class WthC_StarDustNode extends BaseIndustry implements MarketImmigrationModifier {
    public static float OFFICER_PROB_MOD = 0.1F;
    public static float OFFICER_PROB_MOD_MEGA = 0.2F;
    public static float UPKEEP_MULT_PER_DEFICIT = 0.1F;
    public static float BASE_ACCESSIBILITY = 0.6F;
    public static float MEGAPORT_ACCESSIBILITY = 0.9F;
    public static float ALPHA_CORE_ACCESSIBILITY = 0.2F;
    public static float IMPROVE_ACCESSIBILITY = 0.2F;

    public WthC_StarDustNode() {
    }

    public void apply() {
        super.apply(true);
        boolean megaport = "megaport".equals(this.getId());

        this.demand("cube_deepbule", 2);
        this.demand("cube_crimson", 2);
        this.demand("cube_densefog", 2);

        String desc = this.getNameForModifier();
        Pair<String, Integer> deficit = this.getUpkeepAffectingDeficit();
        float a;
        if ((Integer)deficit.two > 0) {
            a = this.getUpkeepPenalty(deficit);
            this.getUpkeep().modifyMult("deficit", 1.0F + a, getDeficitText((String)deficit.one));
        } else {
            this.getUpkeep().unmodifyMult("deficit");
        }

        this.market.setHasSpaceport(true);
        a = BASE_ACCESSIBILITY;
        if (megaport) {
            a = MEGAPORT_ACCESSIBILITY;
        }

        if (a > 0.0F) {
            this.market.getAccessibilityMod().modifyFlat(this.getModId(0), a, desc);
        }

        float officerProb = OFFICER_PROB_MOD;
        if (megaport) {
            officerProb = OFFICER_PROB_MOD_MEGA;
        }

        this.market.getStats().getDynamic().getMod("officer_prob").modifyFlat(this.getModId(0), officerProb);
        if (!this.isFunctional()) {
            this.supply.clear();
            this.unapply();
            this.market.setHasSpaceport(true);
        }

    }

    public void unapply() {
        super.unapply();
        this.market.setHasSpaceport(false);
        this.market.getAccessibilityMod().unmodifyFlat(this.getModId(0));
        this.market.getAccessibilityMod().unmodifyFlat(this.getModId(1));
        this.market.getAccessibilityMod().unmodifyFlat(this.getModId(2));
        this.market.getStats().getDynamic().getMod("officer_prob").unmodifyFlat(this.getModId(0));
    }

    protected float getUpkeepPenalty(Pair<String, Integer> deficit) {
        float loss = (float)(Integer)deficit.two * UPKEEP_MULT_PER_DEFICIT;
        if (loss < 0.0F) {
            loss = 0.0F;
        }

        return loss;
    }
    protected Pair<String, Integer> getUpkeepAffectingDeficit() {
        return this.getMaxDeficit(new String[]{"cube_deepbule", "cube_crimson", "cube_densefog"});
    }

    protected boolean hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode) {
        return mode != IndustryTooltipMode.NORMAL || this.isFunctional();
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
    protected void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {
        if (mode != IndustryTooltipMode.NORMAL || this.isFunctional()) {
            MutableStat fake = new MutableStat(0.0F);
            boolean megaport = "megaport".equals(this.getId());
            String desc = this.getNameForModifier();
            float a = BASE_ACCESSIBILITY;
            if (megaport) {
                a = MEGAPORT_ACCESSIBILITY;
            }

            if (a > 0.0F) {
                fake.modifyFlat(this.getModId(0), a, desc);
            }

            String totalStr = "+" + Math.round(a * 100.0F) + "%";
            Color h = Misc.getHighlightColor();
            if (a < 0.0F) {
                h = Misc.getNegativeHighlightColor();
                totalStr = Math.round(a * 100.0F) + "%";
            }

            float opad = 10.0F;
            float pad = 3.0F;
            if (a >= 0.0F) {
                tooltip.addPara("Accessibility bonus: %s", opad, h, new String[]{totalStr});
            } else {
                tooltip.addPara("Accessibility penalty: %s", opad, h, new String[]{totalStr});
            }

            float bonus = this.getPopulationGrowthBonus();
            tooltip.addPara("Population growth: %s", opad, h, new String[]{"+" + (int)bonus});
            HyperspaceTopographyEventIntel intel = HyperspaceTopographyEventIntel.get();
            if (intel != null && intel.isStageActive(Stage.SLIPSTREAM_DETECTION)) {
                h = Misc.getHighlightColor();
                tooltip.addSectionHeading("Hyperspace topography", Alignment.MID, opad);
                if (!this.isFunctional()) {
                    tooltip.addPara("Slipstream detection requires functional Spaceport", Misc.getNegativeHighlightColor(), opad);
                } else {
                    int range = Math.round(this.market.getStats().getDynamic().getMod("slipstream_reveal_range_ly_mod").computeEffective(0.0F));
                    tooltip.addPara("Slipstream detection range: %s light-years", opad, h, new String[]{"" + range});
                    tooltip.addStatModGrid(tooltip.getWidthSoFar(), 50.0F, opad, pad, this.market.getStats().getDynamic().getMod("slipstream_reveal_range_ly_mod"));
                }
            }
        }

    }

    public float getPopulationGrowthBonus() {
        boolean megaport = "megaport".equals(this.getId());
        float bonus = 2.0F;
        if (megaport) {
            bonus = (float)this.market.getSize();
        }

        return bonus;
    }

    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        float bonus = this.getPopulationGrowthBonus();
        incoming.getWeight().modifyFlat(this.getModId(), bonus, this.getNameForModifier());
    }

    protected void applyAlphaCoreModifiers() {
        this.market.getAccessibilityMod().modifyFlat(this.getModId(2), ALPHA_CORE_ACCESSIBILITY, "Alpha Core (" + this.getNameForModifier() + ")");
    }

    protected void applyNoAICoreModifiers() {
        this.market.getAccessibilityMod().unmodifyFlat(this.getModId(2));
    }

    protected void applyAlphaCoreSupplyAndDemandModifiers() {
        this.demandReduction.modifyFlat(this.getModId(0), (float)DEMAND_REDUCTION, "Alpha Core");
    }

    protected void addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode) {
        float opad = 10.0F;
        Color highlight = Misc.getHighlightColor();
        String pre = "Currently assigned Alpha-level AI core.";
        if (mode == AICoreDescriptionMode.MANAGE_CORE_DIALOG_LIST || mode == AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            pre = "Alpha-level AI core.";
        }

        float a = ALPHA_CORE_ACCESSIBILITY;
        String aStr = Math.round(a * 100.0F) + "%";
        if (mode == AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            CommoditySpecAPI coreSpec = Global.getSettings().getCommoditySpec(this.aiCoreId);
            TooltipMakerAPI text = tooltip.beginImageWithText(coreSpec.getIconName(), 48.0F);
            text.addPara(pre + "Maintenance cost reduced by %s. Demand reduced by %s units. Accessibility increased by %s.", 0.0F, highlight, new String[]{(int)((1.0F - UPKEEP_MULT) * 100.0F) + "%", "" + DEMAND_REDUCTION, aStr});
            tooltip.addImageWithText(opad);
        } else {
            tooltip.addPara(pre + "Maintenance cost reduced by %s. Demand reduced by %s units. Accessibility increased by %s.", opad, highlight, new String[]{(int)((1.0F - UPKEEP_MULT) * 100.0F) + "%", "" + DEMAND_REDUCTION, aStr});
        }
    }


    protected void applyImproveModifiers() {
        if (this.isImproved()) {
            this.market.getAccessibilityMod().modifyFlat("spaceport_improve", IMPROVE_ACCESSIBILITY, this.getImprovementsDescForModifiers() + " (" + this.getNameForModifier() + ")");
        } else {
            this.market.getAccessibilityMod().unmodifyFlat("spaceport_improve");
        }

    }

    public void addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode) {
        float opad = 10.0F;
        Color highlight = Misc.getHighlightColor();
        float a = IMPROVE_ACCESSIBILITY;
        String aStr = Math.round(a * 100.0F) + "%";
        if (mode == ImprovementDescriptionMode.INDUSTRY_TOOLTIP) {
            info.addPara("Accessibility increased by %s.", 0.0F, highlight, new String[]{aStr});
        } else {
            info.addPara("Accessibility increased by %s.", 0.0F, highlight, new String[]{aStr});
        }

        info.addSpacer(opad);
        super.addImproveDesc(info, mode);
    }
}
