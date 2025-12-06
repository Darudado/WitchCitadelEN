package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// Some formatting from Polaris
public class WthC_FogTransformed extends BaseHullMod {


    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static final float PROFILE_MULT = 0.75f;
    public static final float REPAIR_RATE_BONUS = 33.0F;
    public static final float CR_RECOVERY_BONUS = 33.0F;
    public static final float MAINTENANCE_MULT = -20.0F;
    public static final float FLUX_RESISTANCE = -25f;


    public static final float DAMAGE = 1f;
    public static final float DAMAGE_TAKEN = 1f;
    public static final float DAMAGE_TAKEN_S = -1f;
    public static final float CR_PEAK = 1f;
    public static final float RANGE_BONUS = 1f;
    public static final float FLUX_COIL_MULT = 1f;
    public static final float FLUX_DISSIPATION_MULT = 1f;
    public static final float CASUALTY_REDUCTION = -3f;



    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        int count = 0;
        float pad = 10f;
        float padL = 20f;
        float padS = 2f;
        if (ship == null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("The Stellar Mist contained within the activated hull resonates, affecting the ship's appearance to some extent.", WthC_ColorData.DUST_PURPLE, pad);
            tooltip.addPara("Although this transformation can create confusion and intimidation on the battlefield, most mentally stable crew members still dislike this eerie mist.", WthC_ColorData.DUST_PURPLE, padS);
            tooltip.addPara("For certain reasons, please move to the fitting screen to view the specific detailed effects.", WthC_ColorData.HIGH_BLUE, padL);
        }



        if (ship != null) {

            if(ship.getVariant().hasHullMod("erosion1")) {
                count++;
            }
            if(ship.getVariant().hasHullMod("erosion2")) {
                count++;
            }
            if(ship.getVariant().hasHullMod("erosion3")) {
                count++;
            }
            if(ship.getVariant().hasHullMod("erosion4")) {
                count++;
            }
            if(ship.getVariant().hasHullMod("erosion5")) {
                count++;
            }


            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("The Stellar Mist contained within the activated hull resonates, affecting the ship's appearance to some extent.", WthC_ColorData.DUST_PURPLE, pad);
            tooltip.addPara("Although this transformation can create confusion and intimidation on the battlefield, most mentally stable crew members still dislike this eerie mist.", WthC_ColorData.DUST_PURPLE, padS);

            tooltip.addSectionHeading("Specific Effects on the Ship", Alignment.TMID, 10.0F);

            tooltip.addPara(" %s " + strings.get("FogTransformed2"), pad, Misc.getPositiveHighlightColor(), "#");

            if (!(count >= 1)) {
                tooltip.addPara("Unlocks when Mist Erosion reaches the first stage...", WthC_ColorData.MID_WHITE, pad);
            } else {
                tooltip.addPara(" %s " + strings.get("ErosionEx1"), pad, WthC_ColorData.HIGH_BLUE, "#", "25%");
            }
            if (!(count >= 2)) {
                tooltip.addPara("Unlocks when Mist Erosion reaches the second stage...", WthC_ColorData.MID_WHITE, padS);
            } else {
                tooltip.addPara(" %s " + strings.get("ErosionEx2"), padS, WthC_ColorData.HIGH_BLUE, "#", "33%");
            }
            if (!(count >= 3)) {
                tooltip.addPara("Unlocks when Mist Erosion reaches the third stage...", WthC_ColorData.MID_WHITE, padS);
            } else {
                tooltip.addPara(" %s " + strings.get("ErosionEx3"), padS, WthC_ColorData.HIGH_BLUE, "#", "20%");
            }
            if (!(count >= 4)) {
                tooltip.addPara("Unlocks when Mist Erosion reaches the fourth stage...", WthC_ColorData.MID_WHITE, padS);
            } else {
                tooltip.addPara(" %s " + strings.get("ErosionEx4-1"), padS, WthC_ColorData.HIGH_BLUE, "#", "25%");
            }
            if (!(count >= 5)) {
                tooltip.addPara("Unlocks when Mist Erosion reaches the fifth stage...", WthC_ColorData.MID_WHITE, padS);
            } else {
                tooltip.addPara(" %s " + strings.get("ErosionEx4-2"), padS, WthC_ColorData.HIGH_BLUE, "#");
            }

            tooltip.addSectionHeading("Limitations and Notes", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("FogTransformed1"), pad, Misc.getHighlightColor(), "#");
        tooltip.addPara(" %s " + strings.get("FogTransformed4"), padS, Misc.getHighlightColor(), "#");

        }
    }
    public void advanceInCombat(ShipAPI ship, float amount) {
        boolean sMod = isSMod(ship.getMutableStats());
        if (sMod) {
            if (ship.isAlive()) {
                ship.setJitterUnder(ship, WthC_ColorData.MYSTERIOUS_PURPLE, 2f, 1, 8f,8f);
                ship.setJitterShields(false);
            }
        }

    }


    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        int count = 0;
        boolean sMod = isSMod(stats);
        if(stats.getVariant().hasHullMod("erosion1")) {
            count++;
        }
        if(stats.getVariant().hasHullMod("erosion2")) {
            count++;
        }
        if(stats.getVariant().hasHullMod("erosion3")) {
            count++;
        }
        if(stats.getVariant().hasHullMod("erosion4")) {
            count++;
        }
        if(stats.getVariant().hasHullMod("erosion5")) {
            count++;
        }

        if (count >= 1){
            stats.getSensorProfile().modifyMult(id, PROFILE_MULT);
        }
        if (count >= 2){
            stats.getBaseCRRecoveryRatePercentPerDay().modifyPercent(id, CR_RECOVERY_BONUS);
            stats.getRepairRatePercentPerDay().modifyPercent(id, REPAIR_RATE_BONUS);
        }
        if (count >= 3){
            stats.getSuppliesPerMonth().modifyPercent(id, MAINTENANCE_MULT);
        }
        if (count >= 4){
            stats.getEmpDamageTakenMult().modifyPercent(id, FLUX_RESISTANCE);
        }
        if (count >= 5){
            stats.getDynamic().getMod(Stats.INDIVIDUAL_SHIP_RECOVERY_MOD).modifyFlat(id, 1000f);
            stats.getBreakProb().modifyMult(id, 0f);
        }
        if (sMod) {
            stats.getDamageToFrigates().modifyPercent(id, (DAMAGE * count));
            stats.getDamageToDestroyers().modifyPercent(id, (DAMAGE * count));
            stats.getDamageToCapital().modifyPercent(id, (DAMAGE * count));
            stats.getDamageToCruisers().modifyPercent(id, (DAMAGE * count));
            stats.getShieldDamageTakenMult().modifyPercent(id, (DAMAGE_TAKEN_S * count));
            stats.getArmorDamageTakenMult().modifyPercent(id, (DAMAGE_TAKEN * count));
            stats.getHullDamageTakenMult().modifyPercent(id, (DAMAGE_TAKEN * count));
            stats.getPeakCRDuration().modifyPercent(id,(CR_PEAK * count));
            stats.getBallisticWeaponRangeBonus().modifyPercent(id, (RANGE_BONUS * count));
            stats.getEnergyWeaponRangeBonus().modifyPercent(id, (RANGE_BONUS * count));
            stats.getFluxCapacity().modifyPercent(id, (FLUX_COIL_MULT * count));
            stats.getFluxDissipation().modifyPercent(id, (FLUX_DISSIPATION_MULT * count));
            stats.getCrewLossMult().modifyPercent(id, (CASUALTY_REDUCTION * count));
        }

        }

    @Override
    public String getSModDescriptionParam(int index, HullSize hullSize, ShipAPI ship) {
        if (index == 0) return "33"  + "%";
        return null;
    }

    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.setDHullOverlay(null);
    }

}
