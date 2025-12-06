package data.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.scripts.util.MagicIncompatibleHullmods;
import data.util.WthC_Util;
import data.util.WthC_ColorData;

import java.util.*;

// Some formats from Polaris
public class WthC_Erosion1 extends BaseHullMod {


    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static final float DAMAGE = -3f;
    public static final float DAMAGE_TAKEN = -3f;
    public static final float DAMAGE_TAKEN_S = 3f;
    public static final float CR_PEAK = -3f;
    public static final float RANGE_BONUS = -3f;
    public static final float FLUX_COIL_MULT = -3f;
    public static final float FLUX_DISSIPATION_MULT = -3f;
    public static final float CASUALTY_REDUCTION = 10f;
    public static float DEPLOYMENT_COST_MULT = 0.8F;
    private static final Set<String> BLOCKED_HULLMODS = new HashSet();

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padS = 2f;
        if (ship != null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("Actively releasing a certain amount of stellar energy mist, making the ship's interior perilous, and the increasingly difficult survival environment will affect the ship's theoretical combat power to some extent", WthC_ColorData.DUST_PURPLE, pad);
            tooltip.addPara("Although this reduction in combat potential seems foolish, the assimilation effect of stellar energy mist on the hull will also further reduce logistical pressure", WthC_ColorData.DUST_PURPLE, padS);
            tooltip.addPara("This mod is considered a D-mod, enjoying most D-mod related effects", WthC_ColorData.DUST_RED, padS);
        }
        tooltip.addSectionHeading("Specific Effects on Ship", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("Erosion1"), pad, Misc.getNegativeHighlightColor(), "#","3%");
        tooltip.addPara(" %s " + strings.get("Erosion2"), padS, Misc.getNegativeHighlightColor(), "#", "3%");
        tooltip.addPara(" %s " + strings.get("Erosion3"), padS, Misc.getNegativeHighlightColor(), "#", "3%");
        tooltip.addPara(" %s " + strings.get("Erosion4"), padS, Misc.getNegativeHighlightColor(), "#", "3%");
        tooltip.addPara(" %s " + strings.get("Erosion5"), padS, Misc.getNegativeHighlightColor(), "#", "3%");
        tooltip.addPara(" %s " + strings.get("Erosion6"), padS, Misc.getNegativeHighlightColor(), "#", "3%");
        tooltip.addPara(" %s " + strings.get("Erosion7"), padS, Misc.getNegativeHighlightColor(), "#", "10%");




        tooltip.addSectionHeading("Restrictions and Notes", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("Erosion_TEXT1"), pad, Misc.getHighlightColor(), "#");
        tooltip.addPara(" %s " + strings.get("Erosion_TEXT2"), padS, Misc.getHighlightColor(), "#");
        tooltip.addPara(" %s " + strings.get("Erosion_TEXT4"), padS, Misc.getNegativeHighlightColor(), "#");


    }

    public static void modifyCost(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getSuppliesToRecover().modifyMult(id, DEPLOYMENT_COST_MULT);
        float effect = stats.getDynamic().getValue("dmod_reduce_maintenance", 0.0F);
        if (effect > 0.0F) {
            stats.getSuppliesPerMonth().modifyMult(id, DEPLOYMENT_COST_MULT);
        }

    }

    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getDamageToFrigates().modifyPercent(id, DAMAGE);
        stats.getDamageToDestroyers().modifyPercent(id, DAMAGE);
        stats.getDamageToCapital().modifyPercent(id, DAMAGE);
        stats.getDamageToCruisers().modifyPercent(id, DAMAGE);
        stats.getShieldDamageTakenMult().modifyPercent(id, DAMAGE_TAKEN_S);
        stats.getArmorDamageTakenMult().modifyPercent(id, DAMAGE_TAKEN);
        stats.getHullDamageTakenMult().modifyPercent(id, DAMAGE_TAKEN);
        stats.getPeakCRDuration().modifyPercent(id,CR_PEAK);
        stats.getBallisticWeaponRangeBonus().modifyPercent(id, RANGE_BONUS);
        stats.getEnergyWeaponRangeBonus().modifyPercent(id, RANGE_BONUS);
        stats.getFluxCapacity().modifyPercent(id, FLUX_COIL_MULT);
        stats.getFluxDissipation().modifyPercent(id, FLUX_DISSIPATION_MULT);
        stats.getCrewLossMult().modifyPercent(id, CASUALTY_REDUCTION);
        modifyCost(hullSize, stats, id);
        }


    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {




    Iterator i$ = BLOCKED_HULLMODS.iterator();

    while(i$.hasNext()) {
        String tmp = (String)i$.next();
        if (ship.getVariant().getHullMods().contains(tmp)) {
            MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "erosion1");
        }
    }
    }
    static {
        BLOCKED_HULLMODS.add("efficiency_overhaul");
        }
}
