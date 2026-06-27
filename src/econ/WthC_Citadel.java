package data.econ;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionDoctrineAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.econ.CommodityMarketDataAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketImmigrationModifier;
import com.fs.starfarer.api.campaign.listeners.ColonyOtherFactorsListener;
import com.fs.starfarer.api.characters.MarketConditionSpecAPI;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.econ.CommRelayCondition;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.econ.impl.ConstructionQueue;
import com.fs.starfarer.api.impl.campaign.econ.impl.ItemEffectsRepo;
import com.fs.starfarer.api.impl.campaign.fleets.FleetFactoryV3;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.loading.IndustrySpecAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.lwjgl.util.vector.Vector2f;

// Modified from residential area

public class WthC_Citadel extends BaseIndustry implements MarketImmigrationModifier {
    public static float OFFICER_BASE_PROB = Global.getSettings().getFloat("officerBaseProb");
    public static float OFFICER_PROB_PER_SIZE = Global.getSettings().getFloat("officerProbPerColonySize");
    public static float OFFICER_ADDITIONAL_BASE_PROB = Global.getSettings().getFloat("officerAdditionalBaseProb");
    public static float OFFICER_BASE_MERC_PROB = Global.getSettings().getFloat("officerBaseMercProb");
    public static float ADMIN_BASE_PROB = Global.getSettings().getFloat("adminBaseProb");
    public static float ADMIN_PROB_PER_SIZE = Global.getSettings().getFloat("adminProbPerColonySize");
    public static float IMPROVE_STABILITY_BONUS = 1.0F;
    public static boolean HAZARD_INCREASES_DEFENSE = false;
    public static int[] MAX_IND = null;

    public WthC_Citadel() {
    }

    public void apply() {
        modifyStability(this, this.market, this.getModId(3));
        super.apply(true);
        int size = this.market.getSize();

        boolean spaceportFirstInQueue = false;
        Iterator var6 = this.market.getConstructionQueue().getItems().iterator();
        if (var6.hasNext()) {
            ConstructionQueue.ConstructionQueueItem item = (ConstructionQueue.ConstructionQueueItem)var6.next();
            IndustrySpecAPI spec = Global.getSettings().getIndustrySpec(item.id);
            if (spec.hasTag("spaceport")) {
                spaceportFirstInQueue = true;
            }
        }

        if (spaceportFirstInQueue && Misc.getCurrentlyBeingConstructed(this.market) != null) {
            spaceportFirstInQueue = false;
        }

        float sizeBonus;
        if (!this.market.hasSpaceport() && !spaceportFirstInQueue) {
            sizeBonus = Global.getSettings().getFloat("accessibilityNoSpaceport");
            this.market.getAccessibilityMod().modifyFlat(this.getModId(0), sizeBonus, "No spaceport");
        }

        sizeBonus = getAccessibilityBonus(size);
        if (sizeBonus > 0.0F) {
            this.market.getAccessibilityMod().modifyFlat(this.getModId(1), sizeBonus, "Colony size");
        }

        float stability = this.market.getPrevStability();
        float stabilityQualityMod = FleetFactoryV3.getShipQualityModForStability(stability);
        float doctrineQualityMod = this.market.getFaction().getDoctrine().getShipQualityContribution();
        this.market.getStats().getDynamic().getMod("fleet_quality_mod").modifyFlatAlways(this.getModId(0), stabilityQualityMod, "Stability");
        this.market.getStats().getDynamic().getMod("fleet_quality_mod").modifyFlatAlways(this.getModId(1), doctrineQualityMod, Misc.ucFirst(this.market.getFaction().getEntityNamePrefix()) + " Fleet doctrine");
        float stabilityDefenseMult = 0.25F + stability / 10.0F * 0.75F;
        this.market.getStats().getDynamic().getMod("ground_defenses_mod").modifyMultAlways(this.getModId(), stabilityDefenseMult, "Stability");
        float baseDef = getBaseGroundDefenses(this.market.getSize());
        this.market.getStats().getDynamic().getMod("ground_defenses_mod").modifyFlatAlways(this.getModId(), baseDef, "Size " + this.market.getSize() + " colony");
        if (HAZARD_INCREASES_DEFENSE) {
            this.market.getStats().getDynamic().getMod("ground_defenses_mod").modifyMultAlways(this.getModId(1), Math.max(this.market.getHazardValue(), 1.0F), "Colony Hazard Level");
        }

        this.market.getStats().getDynamic().getMod("max_industries").modifyFlat(this.getModId(), (float)this.getMaxIndustries(), (String)null);
        FactionDoctrineAPI doctrine = this.market.getFaction().getDoctrine();
        float doctrineShipsMult = FleetFactoryV3.getDoctrineNumShipsMult(doctrine.getNumShips());
        float marketSizeShipsMult = FleetFactoryV3.getNumShipsMultForMarketSize((float)this.market.getSize());
        float deficitShipsMult = FleetFactoryV3.getShipDeficitFleetSizeMult(this.market);
        float stabilityShipsMult = FleetFactoryV3.getNumShipsMultForStability(stability);
        this.market.getStats().getDynamic().getMod("combat_fleet_size_mult").modifyFlatAlways(this.getModId(0), marketSizeShipsMult, "Colony Size");
        this.market.getStats().getDynamic().getMod("combat_fleet_size_mult").modifyMultAlways(this.getModId(1), doctrineShipsMult, Misc.ucFirst(this.market.getFaction().getEntityNamePrefix()) + " Fleet Doctrine");
        if (deficitShipsMult != 1.0F) {
            this.market.getStats().getDynamic().getMod("combat_fleet_size_mult").modifyMult(this.getModId(2), deficitShipsMult, getDeficitText("ships"));
        } else {
            this.market.getStats().getDynamic().getMod("combat_fleet_size_mult").modifyMultAlways(this.getModId(2), deficitShipsMult, getDeficitText("ships"));
        }

        this.market.getStats().getDynamic().getMod("combat_fleet_size_mult").modifyMultAlways(this.getModId(3), stabilityShipsMult, "Stability");
        this.market.getStats().getDynamic().getMod("officer_prob").modifyFlat(this.getModId(0), OFFICER_BASE_PROB);
        this.market.getStats().getDynamic().getMod("officer_prob").modifyFlat(this.getModId(1), OFFICER_PROB_PER_SIZE * (float)Math.max(0, this.market.getSize() - 3));
        this.market.getStats().getDynamic().getMod("additional_officer_prob_mult").modifyFlat(this.getModId(0), OFFICER_ADDITIONAL_BASE_PROB);
        this.market.getStats().getDynamic().getMod("officer_is_merc_prob").modifyFlat(this.getModId(0), OFFICER_BASE_MERC_PROB);
        this.market.getStats().getDynamic().getMod("admin_prob").modifyFlat(this.getModId(0), ADMIN_BASE_PROB);
        this.market.getStats().getDynamic().getMod("admin_prob").modifyFlat(this.getModId(1), ADMIN_PROB_PER_SIZE * (float)Math.max(0, this.market.getSize() - 3));
        modifyStability2(this, this.market, this.getModId(3));
        this.market.addTransientImmigrationModifier(this);
    }


    public static float getAccessibilityBonus(int marketSize) {
        if (marketSize <= 4) {
            return 0.0F;
        } else if (marketSize == 5) {
            return 0.1F;
        } else if (marketSize == 6) {
            return 0.15F;
        } else if (marketSize == 7) {
            return 0.2F;
        } else {
            return marketSize == 8 ? 0.25F : 0.3F;
        }
    }

    public static float getBaseGroundDefenses(int marketSize) {
        if (marketSize <= 1) {
            return 10.0F;
        } else if (marketSize <= 2) {
            return 20.0F;
        } else {
            return marketSize <= 3 ? 50.0F : (float)((marketSize - 3) * 100);
        }
    }

    public void unapply() {
        super.unapply();
        this.market.getStability().unmodify(this.getModId(0));
        this.market.getStability().unmodify(this.getModId(1));
        this.market.getStability().unmodify(this.getModId(2));
        this.market.getAccessibilityMod().unmodifyFlat(this.getModId(0));
        this.market.getAccessibilityMod().unmodifyFlat(this.getModId(1));
        this.market.getStats().getDynamic().getMod("fleet_quality_mod").unmodifyFlat(this.getModId(0));
        this.market.getStats().getDynamic().getMod("fleet_quality_mod").unmodifyFlat(this.getModId(1));
        this.market.getStats().getDynamic().getMod("ground_defenses_mod").unmodifyFlat(this.getModId());
        this.market.getStats().getDynamic().getMod("ground_defenses_mod").unmodifyMult(this.getModId());
        if (HAZARD_INCREASES_DEFENSE) {
            this.market.getStats().getDynamic().getMod("ground_defenses_mod").unmodifyMult(this.getModId(1));
        }

        this.market.getStats().getDynamic().getMod("max_industries").unmodifyFlat(this.getModId());
        this.market.getStats().getDynamic().getMod("combat_fleet_size_mult").unmodifyFlat(this.getModId(0));
        this.market.getStats().getDynamic().getMod("combat_fleet_size_mult").unmodifyMult(this.getModId(1));
        this.market.getStats().getDynamic().getMod("combat_fleet_size_mult").unmodifyMult(this.getModId(2));
        this.market.getStats().getDynamic().getMod("combat_fleet_size_mult").unmodifyMult(this.getModId(3));
        this.market.getStats().getDynamic().getMod("officer_prob").unmodifyFlat(this.getModId(0));
        this.market.getStats().getDynamic().getMod("officer_prob").unmodifyFlat(this.getModId(1));
        this.market.getStats().getDynamic().getMod("additional_officer_prob_mult").unmodifyFlat(this.getModId(0));
        this.market.getStats().getDynamic().getMod("officer_is_merc_prob").unmodifyFlat(this.getModId(0));
        this.market.getStats().getDynamic().getMod("admin_prob").unmodifyFlat(this.getModId(0));
        this.market.getStats().getDynamic().getMod("admin_prob").unmodifyFlat(this.getModId(1));
        unmodifyStability(this.market, this.getModId(3));
        this.market.removeTransientImmigrationModifier(this);
    }

    protected boolean hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode) {
        return true;
    }

    protected void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {
        if (mode != IndustryTooltipMode.NORMAL || this.isFunctional()) {
            MutableStat stabilityMods = new MutableStat(0.0F);
            float total = 0.0F;
            Iterator var7 = this.market.getStability().getFlatMods().values().iterator();

            while(var7.hasNext()) {
                MutableStat.StatMod mod = (MutableStat.StatMod)var7.next();
                if (mod.source.startsWith(this.getModId())) {
                    stabilityMods.modifyFlat(mod.source, mod.value, mod.desc);
                    total += mod.value;
                }
            }

            String totalStr = "+" + Math.round(total);
            Color h = Misc.getHighlightColor();
            if (total < 0.0F) {
                totalStr = "" + Math.round(total);
                h = Misc.getNegativeHighlightColor();
            }

            float opad = 10.0F;
            float pad = 3.0F;


            tooltip.addStatModGrid(400.0F, 30.0F, opad, pad, stabilityMods, new TooltipMakerAPI.StatModValueGetter() {
                public String getPercentValue(MutableStat.StatMod mod) {
                    return null;
                }

                public String getMultValue(MutableStat.StatMod mod) {
                    return null;
                }

                public Color getModColor(MutableStat.StatMod mod) {
                    return mod.value < 0.0F ? Misc.getNegativeHighlightColor() : null;
                }

                public String getFlatValue(MutableStat.StatMod mod) {
                    return null;
                }
            });
        }

    }



    public static float getIncomeStabilityMult(float stability) {
        return stability <= 5.0F ? Math.max(0.0F, stability / 5.0F) : 1.0F;
    }

    public static float getUpkeepHazardMult(float hazard) {
        float hazardMult = hazard;
        float min = Global.getSettings().getFloat("minUpkeepMult");
        if (hazard < min) {
            hazardMult = min;
        }

        return hazardMult;
    }

    public static int getMismanagementPenalty() {
        int outposts = 0;
        Iterator var2 = Global.getSector().getEconomy().getMarketsCopy().iterator();

        while(var2.hasNext()) {
            MarketAPI curr = (MarketAPI)var2.next();
            if (curr.isPlayerOwned() && curr.getAdmin().isPlayer()) {
                ++outposts;
            }
        }

        MutableCharacterStatsAPI stats = Global.getSector().getCharacterData().getPerson().getStats();
        int maxOutposts = stats.getOutpostNumber().getModifiedInt();
        int overOutposts = outposts - maxOutposts;
        int penaltyOrBonus = (int)((float)overOutposts * Misc.getOutpostPenalty());
        return penaltyOrBonus;
    }

    public static void modifyStability2(Industry industry, MarketAPI market, String modId) {
        if (Misc.getNumIndustries(market) > Misc.getMaxIndustries(market)) {
            market.getStability().modifyFlat("_" + modId + "_overmax", (float)(-Misc.OVER_MAX_INDUSTRIES_PENALTY), "Exceeds the maximum industrial facility limit.");
        } else {
            market.getStability().unmodifyFlat("_" + modId + "_overmax");
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
    public static void modifyStability(Industry industry, MarketAPI market, String modId) {
        market.getIncomeMult().modifyMultAlways(modId, getIncomeStabilityMult(market.getPrevStability()), "stability");
        market.getUpkeepMult().modifyMultAlways(modId, getUpkeepHazardMult(market.getHazardValue()), "Hazard Level");
        market.getStability().modifyFlat("_" + modId + "_ms", Global.getSettings().getFloat("stabilityBaseValue"), "base value");
        float inFactionSupply = 0.0F;
        float totalDemand = 0.0F;
        Iterator var6 = market.getCommoditiesCopy().iterator();

        while(var6.hasNext()) {
            CommodityOnMarketAPI com = (CommodityOnMarketAPI)var6.next();
            if (!com.isNonEcon()) {
                int d = com.getMaxDemand();
                if (d > 0) {
                    totalDemand += (float)d;
                    CommodityMarketDataAPI cmd = com.getCommodityMarketData();
                    int inFaction = Math.max(Math.min(com.getMaxSupply(), com.getAvailable()), Math.min(cmd.getMaxShipping(market, true), cmd.getMaxExport(market.getFactionId())));
                    if (inFaction > d) {
                        inFaction = d;
                    }

                    if (inFaction < d) {
                        inFaction = Math.max(Math.min(com.getMaxSupply(), com.getAvailable()), 0);
                    }

                    inFactionSupply += (float)Math.max(0, Math.min(inFaction, com.getAvailable()));
                }
            }
        }

        if (totalDemand > 0.0F) {
            float max = Global.getSettings().getFloat("upkeepReductionFromInFactionImports");
            float f = inFactionSupply / totalDemand;
            if (f < 0.0F) {
                f = 0.0F;
            }

            if (f > 1.0F) {
                f = 1.0F;
            }

            if (f > 0.0F) {
                float mult = (float)Math.round(100.0F - f * max * 100.0F) / 100.0F;
                String desc = "Internal Demand (" + Math.round(f * 100.0F) + "%)";
                if (f == 1.0F) {
                    desc = "All demands met by controlling faction";
                }

                market.getUpkeepMult().modifyMultAlways(modId + "ifi", mult, desc);
            } else {
                market.getUpkeepMult().modifyMultAlways(modId + "ifi", 1.0F, "Some demands require external faction assistance - maintenance costs not reduced");
            }
        }

        if (market.isPlayerOwned() && market.getAdmin().isPlayer()) {
            int penalty = getMismanagementPenalty();
            if (penalty > 0) {
                market.getStability().modifyFlat("_" + modId + "_mm", (float)(-penalty), "Poor Management");
            } else if (penalty < 0) {
                market.getStability().modifyFlat("_" + modId + "_mm", (float)(-penalty), "Administrative Bonus");
            } else {
                market.getStability().unmodifyFlat("_" + modId + "_mm");
            }
        } else {
            market.getStability().unmodifyFlat(modId + "_mm");
        }

        if (!market.hasCondition("comm_relay")) {
            market.getStability().modifyFlat(CommRelayCondition.COMM_RELAY_MOD_ID, CommRelayCondition.NO_RELAY_PENALTY, "No functional comm relay in system");
        }

    }

    public static void unmodifyStability(MarketAPI market, String modId) {
        market.getIncomeMult().unmodifyMult(modId);
        market.getUpkeepMult().unmodifyMult(modId);
        market.getUpkeepMult().unmodifyMult(modId + "ifi");
        market.getStability().unmodifyFlat(modId);
        market.getStability().unmodifyFlat("_" + modId + "_mm");
        market.getStability().unmodifyFlat("_" + modId + "_ms");
        market.getStability().unmodifyFlat("_" + modId + "_overmax");
        if (!market.hasCondition("comm_relay")) {
            market.getStability().unmodifyFlat(CommRelayCondition.COMM_RELAY_MOD_ID);
        }

    }

    public boolean showShutDown() {
        return false;
    }

    public String getCanNotShutDownReason() {
        return null;
    }

    public boolean canShutDown() {
        return false;
    }

    protected String getDescriptionOverride() {
        int size = this.market.getSize();
        String cid = null;
        if (size >= 1 && size <= 9) {
            cid = "population_" + size;
            MarketConditionSpecAPI mcs = Global.getSettings().getMarketConditionSpec(cid);
            if (mcs != null) {
                return this.spec.getDesc() + "\n\n" + mcs.getDesc().replaceAll("\\$market", this.market.getName());
            }
        }

        return super.getDescriptionOverride();
    }

    public String getBuildOrUpgradeProgressText() {
        return this.isUpgrading() ? "Total Development Level：" + Misc.getRoundedValue(Misc.getMarketSizeProgress(this.market) * 100.0F) + "%" : super.getBuildOrUpgradeProgressText();
    }

    public float getBuildOrUpgradeProgress() {
        return !super.isBuilding() && this.market.getSize() < Misc.MAX_COLONY_SIZE ? Misc.getMarketSizeProgress(this.market) : super.getBuildOrUpgradeProgress();
    }

    public boolean isBuilding() {
        return !super.isBuilding() && this.market.getSize() < Misc.MAX_COLONY_SIZE && this.getBuildOrUpgradeProgress() > 0.0F ? true : super.isBuilding();
    }

    public boolean isUpgrading() {
        return !super.isBuilding() && this.market.getSize() < Misc.MAX_COLONY_SIZE ? true : super.isUpgrading();
    }

    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        float patherLevel = 0.0F;

        Industry curr;
        for(Iterator var5 = market.getIndustries().iterator(); var5.hasNext(); patherLevel += this.getAICoreImpact(curr.getAICoreId())) {
            curr = (Industry)var5.next();
        }

        String adminCoreId = market.getAdmin().getAICoreId();
        if (adminCoreId != null) {
            patherLevel += 10.0F * this.getAICoreImpact(adminCoreId);
        }

        List<String> targeted = new ArrayList();
        targeted.add("techmining");
        targeted.add("heavyindustry");
        targeted.add("fuelprod");
        targeted.add("starfortress");
        Iterator var7 = targeted.iterator();

        while(var7.hasNext()) {
            String curr2 = (String)var7.next();
            if (market.hasIndustry(curr2)) {
                patherLevel += 10.0F;
            }
        }

        if (patherLevel > 0.0F) {
            incoming.add("luddic_path", patherLevel * 0.2F);
        }

    }

    private float getAICoreImpact(String coreId) {
        if ("alpha_core".equals(coreId)) {
            return 10.0F;
        } else if ("beta_core".equals(coreId)) {
            return 4.0F;
        } else {
            return "gamma_core".equals(coreId) ? 1.0F : 0.0F;
        }
    }

    public boolean canBeDisrupted() {
        return false;
    }

    public int getMaxIndustries() {
        return getMaxIndustries(this.market.getSize());
    }

    public static int getMaxIndustries(int size) {
        if (MAX_IND == null) {
            try {
                MAX_IND = new int[10];
                JSONArray a = Global.getSettings().getJSONArray("maxIndustries");

                for(int i = 0; i < MAX_IND.length; ++i) {
                    MAX_IND[i] = a.getInt(i);
                }
            } catch (JSONException var3) {
                throw new RuntimeException(var3);
            }
        }

        --size;
        if (size < 0) {
            size = 0;
        }

        if (size > 9) {
            size = 9;
        }

        return MAX_IND[size];
    }


    protected void applyImproveModifiers() {
        if (this.isImproved()) {
            this.market.getStability().modifyFlat("PAI_improve", IMPROVE_STABILITY_BONUS, this.getImprovementsDescForModifiers() + " (" + this.getNameForModifier() + ")");
        } else {
            this.market.getStability().unmodifyFlat("PAI_improve");
        }

    }

    public void addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode) {
        float opad = 10.0F;
        Color highlight = Misc.getHighlightColor();
        if (mode == ImprovementDescriptionMode.INDUSTRY_TOOLTIP) {
            info.addPara("Stability Increase %s.", 0.0F, highlight, new String[]{"" + (int)IMPROVE_STABILITY_BONUS});
        } else {
            info.addPara("Stability Increase %s.", 0.0F, highlight, new String[]{"" + (int)IMPROVE_STABILITY_BONUS});
        }

        info.addSpacer(opad);
        super.addImproveDesc(info, mode);
    }



    public static Pair<SectorEntityToken, Float> getNearestCoronalTap(Vector2f locInHyper, boolean usable) {
        SectorEntityToken nearest = null;
        float minDist = Float.MAX_VALUE;
        Iterator var5 = Global.getSector().getCustomEntitiesWithTag("coronal_tap").iterator();

        while(true) {
            SectorEntityToken entity;
            do {
                if (!var5.hasNext()) {
                    if (nearest == null) {
                        return null;
                    }

                    return new Pair(nearest, minDist);
                }

                entity = (SectorEntityToken)var5.next();
            } while(usable && !entity.getMemoryWithoutUpdate().contains("$usable"));

            float dist = Misc.getDistanceLY(locInHyper, entity.getLocationInHyperspace());
            if (dist > (float)ItemEffectsRepo.CORONAL_TAP_LIGHT_YEARS && (float)Math.round(dist * 10.0F) <= (float)ItemEffectsRepo.CORONAL_TAP_LIGHT_YEARS * 10.0F) {
                dist = (float)ItemEffectsRepo.CORONAL_TAP_LIGHT_YEARS;
            }

            if (dist < minDist) {
                minDist = dist;
                nearest = entity;
            }
        }
    }

    public static class CoronalTapFactor implements ColonyOtherFactorsListener {
        public CoronalTapFactor() {
        }

        public boolean isActiveFactorFor(SectorEntityToken entity) {
            return com.fs.starfarer.api.impl.campaign.econ.impl.PopulationAndInfrastructure.getNearestCoronalTap(entity.getLocationInHyperspace(), true) != null;
        }

        public void printOtherFactors(TooltipMakerAPI text, SectorEntityToken entity) {
            Pair<SectorEntityToken, Float> p = com.fs.starfarer.api.impl.campaign.econ.impl.PopulationAndInfrastructure.getNearestCoronalTap(entity.getLocationInHyperspace(), true);
            if (p != null) {
                Color h = Misc.getHighlightColor();
                float opad = 10.0F;
                String dStr = Misc.getRoundedValueMaxOneAfterDecimal((Float)p.two);
                String lights = "Light-year";
                if (dStr.equals("1")) {
                    lights = "Light-year";
                }

                if ((Float)p.two > (float)ItemEffectsRepo.CORONAL_TAP_LIGHT_YEARS) {
                    text.addPara("Nearest Coronal Hypershunt located at " + ((SectorEntityToken)p.one).getContainingLocation().getNameWithLowercaseType() + ", distance %s " + lights + " away. Maximum connection range is %s light-years.", opad, h, new String[]{Misc.getRoundedValueMaxOneAfterDecimal((Float)p.two), "" + ItemEffectsRepo.CORONAL_TAP_LIGHT_YEARS});
                } else {
                    text.addPara("Nearest Coronal Hypershunt located at " + ((SectorEntityToken)p.one).getContainingLocation().getNameWithLowercaseType() + ", distance %s " + lights + " within range, allowing the hypershunt tap at this location to connect.", opad, h, new String[]{Misc.getRoundedValueMaxOneAfterDecimal((Float)p.two)});
                }
            }

        }
    }
    protected static class LampRemover implements EveryFrameScript {
        protected SectorEntityToken lamp;
        protected MarketAPI market;
        protected com.fs.starfarer.api.impl.campaign.econ.impl.PopulationAndInfrastructure industry;

        public LampRemover(SectorEntityToken lamp, MarketAPI market, com.fs.starfarer.api.impl.campaign.econ.impl.PopulationAndInfrastructure industry) {
            this.lamp = lamp;
            this.market = market;
            this.industry = industry;
        }

        public void advance(float amount) {
            Industry ind = this.market.getIndustry("population");
            SpecialItemData item = ind == null ? null : ind.getSpecialItem();

        }

        public boolean isDone() {
            return this.lamp == null;
        }

        public boolean runWhilePaused() {
            return false;
        }
    }
}
