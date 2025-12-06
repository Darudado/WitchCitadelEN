//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.scripts.util.MagicIncompatibleHullmods;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// 从其他插件简单修改

public class WthC_RingIe extends BaseHullMod {
    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static float ROFBonus1 = -5.0f;
    public static float FLUXBonus1 = -5.0f;
    public static float ROFBonus2 = -10.0f;
    public static float FLUXBonus2 = -10.0f;
    public static float ROFBonus3 = -15.0f;
    public static float FLUXBonus3 = -15.0f;

    public WthC_RingIe() {
    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padS = 2f;
        if (ship == null){
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 10.0F);
            tooltip.addPara("The Ring Spirit Interface, produced by the Yuanchu Workshop, serves as the foundation of this series. Its creation has allowed these foreign visitors to truly engage with the space machinery originating from the Persean Sector.", WthC_ColorData.IE_WHITE, pad);
            tooltip.addPara("Due to the designer's philosophy of warfare, equipping this interface enables ships to better adapt to prolonged frontal artillery engagements.", WthC_ColorData.IE_WHITE, pad);
            tooltip.addPara("For certain reasons, please move to the fitting screen to view the specific detailed effects.", WthC_ColorData.HIGH_BLUE, pad);
        } else {
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            int c1 = 0;
            int c2 = 0;
            int c3 = 0;

            if (ship.getVariant().hasHullMod("crimsonmodificationcross")) {
                count1++;
            }
            if (ship.getVariant().hasHullMod("crimsonmodificationdust")) {
                count1++;
            }
            if (ship.getVariant().hasHullMod("crimsonmodificationie")) {
                count1++;
            }
            if (ship.getVariant().hasHullMod("crimsonmodificationrra")) {
                count1++;
            }
            if (ship.getVariant().hasHullMod("crimsonmodificationssoul")) {
                count1++;
            }
            if (ship.getVariant().hasHullMod("crimsonmodificationelf")) {
                count1++;
            }

            if (ship.getVariant().hasHullMod("ringinterfereie")){
                count2++;
            }
            if (ship.getVariant().hasHullMod("ringinterferecross")){
                count2++;
            }
            if (ship.getVariant().hasHullMod("ringinterfererra")){
                count2++;
            }
            if (ship.getVariant().hasHullMod("ringinterferedust")){
                count2++;
            }
            if (ship.getVariant().hasHullMod("ringinterferessoul")){
                count2++;
            }
            if (ship.getVariant().hasHullMod("ringinterfereelf")){
                count2++;
            }

            if (ship.getVariant().hasHullMod("shadowremaincross")) {
                count3++;
            }
            if (ship.getVariant().hasHullMod("shadowremaindust")) {
                count3++;
            }
            if (ship.getVariant().hasHullMod("shadowremainie")) {
                count3++;
            }
            if (ship.getVariant().hasHullMod("shadowremainrra")) {
                count3++;
            }
            if (ship.getVariant().hasHullMod("shadowremainssoul")) {
                count3++;
            }
            if (ship.getVariant().hasHullMod("shadowremainelf")) {
                count3++;
            }
            if (count1 >= 1){
                c1 = 1;
            }
            if (count2 >= 1){
                c2 = 1;
            }
            if (count3 >= 1){
                c3 = 1;
            }
            int cX = c1 + c2 + c3;

            tooltip.addSectionHeading("Introduction", Alignment.TMID, 10.0F);
            tooltip.addPara("The Ring Spirit Interface, produced by the Yuanchu Workshop, serves as the foundation of this series. Its creation has allowed these foreign visitors to truly engage with the space machinery originating from the Persean Sector.", WthC_ColorData.IE_WHITE, pad);
            tooltip.addPara("Due to the designer's philosophy of warfare, equipping this interface enables ships to better adapt to prolonged frontal artillery engagements.", WthC_ColorData.IE_WHITE, pad);
            tooltip.addSectionHeading("Effects on the Ship", Alignment.TMID, 10.0F);
            tooltip.addPara(" %s " + strings.get("RingIe_1"), pad, Misc.getHighlightColor(), "#", "Crimson Modification", "Ring Spirit Protocol", "Shadow Remnant");
            if (cX == 0) {
                tooltip.addPara(" %s " + strings.get("IEBapitsm_TEXTn1"), padS, Misc.getHighlightColor(), "#", "5%", "5%");
            } else if (cX == 1) {
                tooltip.addPara(" %s " + strings.get("IEBapitsm_TEXTn1"), padS, WthC_ColorData.HIGH_BLUE, "#", "10%", "10%");
            } else if (cX == 2) {
                tooltip.addPara(" %s " + strings.get("IEBapitsm_TEXTn1"), padS, WthC_ColorData.HIGH_BLUE, "#", "15%", "15%");
            } else {
                tooltip.addPara(" %s " + strings.get("IEBapitsm_TEXTn1"), padS, WthC_ColorData.HIGH_BLUE, "#", "15%", "15%");
                tooltip.addPara(" %s " + strings.get("Ring_IE"), padS, WthC_ColorData.HIGH_BLUE, "#", "10%", "5%");
            }
            tooltip.addSectionHeading("Interface Occupancy", Alignment.TMID, 10.0F);
            tooltip.addPara(" %s " + strings.get("Ring_0"), pad, WthC_ColorData.HIGH_BLUE, "#", "Specific Effects", "Gradual Improvement");
            if (c1 == 0) {
                tooltip.addPara(" %s " + strings.get("Ring_1"), pad, Misc.getNegativeHighlightColor(), "#", "Not Performed");
            } else {
                tooltip.addPara(" %s " + strings.get("Ring_1"), pad, WthC_ColorData.HIGH_BLUE, "#", "Performed");
            }
            if (c2 == 0) {
                tooltip.addPara(" %s " + strings.get("Ring_2"), padS, Misc.getNegativeHighlightColor(), "#", "Not Equipped");
            } else {
                tooltip.addPara(" %s " + strings.get("Ring_2"), padS, WthC_ColorData.HIGH_BLUE, "#", "Equipped");
            }
            if (c3 == 0) {
                tooltip.addPara(" %s " + strings.get("Ring_3"), padS, Misc.getNegativeHighlightColor(), "#", "Not Interfered");
            } else {
                tooltip.addPara(" %s " + strings.get("Ring_3"), padS, WthC_ColorData.HIGH_BLUE, "#", "Interfered");
            }
            tooltip.addSectionHeading("Limitations and Conflicts", Alignment.TMID, 10.0F);
            tooltip.addPara(" %s " + strings.get("RingIe_2"), pad, Misc.getNegativeHighlightColor(), "#", "1 Type");
            tooltip.addPara(" %s " + strings.get("RingIe_3"), padS, Misc.getNegativeHighlightColor(), "#", "Integrated");
            tooltip.addPara(" %s " + strings.get("RingIe_4"), padS, Misc.getNegativeHighlightColor(), "#", "Normal Removal");
        }

    }
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;

        if (stats.getVariant().hasHullMod("crimsonmodificationcross")) {
            count1++;
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationdust")) {
            count1++;
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationie")) {
            count1++;
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationrra")) {
            count1++;
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationssoul")) {
            count1++;
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationelf")) {
            count1++;
        }

        if (stats.getVariant().hasHullMod("ringinterfereie")){
            count2++;
        }
        if (stats.getVariant().hasHullMod("ringinterferecross")){
            count2++;
        }
        if (stats.getVariant().hasHullMod("ringinterfererra")){
            count2++;
        }
        if (stats.getVariant().hasHullMod("ringinterferedust")){
            count2++;
        }
        if (stats.getVariant().hasHullMod("ringinterferessoul")){
            count2++;
        }
        if (stats.getVariant().hasHullMod("ringinterfereelf")){
            count2++;
        }

        if (stats.getVariant().hasHullMod("shadowremaincross")) {
            count3++;
        }
        if (stats.getVariant().hasHullMod("shadowremaindust")) {
            count3++;
        }
        if (stats.getVariant().hasHullMod("shadowremainie")) {
            count3++;
        }
        if (stats.getVariant().hasHullMod("shadowremainrra")) {
            count3++;
        }
        if (stats.getVariant().hasHullMod("shadowremainssoul")) {
            count3++;
        }
        if (stats.getVariant().hasHullMod("shadowremainelf")) {
            count3++;
        }
        if (count1 >= 1){
            c1 = 1;
        }
        if (count2 >= 1){
            c2 = 1;
        }
        if (count3 >= 1){
            c3 = 1;
        }
        int cX = c1 + c2 + c3;

        if (cX == 0){
            stats.getBallisticRoFMult().modifyPercent(id, ROFBonus1);
            stats.getEnergyRoFMult().modifyPercent(id, ROFBonus1);
            stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FLUXBonus1);
            stats.getEnergyWeaponFluxCostMod().modifyPercent(id, FLUXBonus1);
        } else if (cX == 1) {
            stats.getBallisticRoFMult().modifyPercent(id, ROFBonus2);
            stats.getEnergyRoFMult().modifyPercent(id, ROFBonus2);
            stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FLUXBonus2);
            stats.getEnergyWeaponFluxCostMod().modifyPercent(id, FLUXBonus2);
        } else if (cX == 2) {
            stats.getBallisticRoFMult().modifyPercent(id, ROFBonus3);
            stats.getEnergyRoFMult().modifyPercent(id, ROFBonus3);
            stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FLUXBonus3);
            stats.getEnergyWeaponFluxCostMod().modifyPercent(id, FLUXBonus3);
        } else {
            stats.getBallisticRoFMult().modifyPercent(id, ROFBonus3);
            stats.getEnergyRoFMult().modifyPercent(id, ROFBonus3);
            stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FLUXBonus3);
            stats.getEnergyWeaponFluxCostMod().modifyPercent(id, FLUXBonus3);
            stats.getProjectileDamageTakenMult().modifyMult(id, 1.05F);
            stats.getBeamDamageTakenMult().modifyMult(id, 0.9F);
            stats.getMissileDamageTakenMult().modifyMult(id, 1.05F);
        }

    }
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        if (!ship.getVariant().hasHullMod("ringsup")){
                ship.getVariant().addMod("ringsup");
        }
        ship.getVariant().removeMod("ringsup2");
        ship.getVariant().removeMod("ringsup3");
    }





    public boolean isApplicableToShip(ShipAPI ship) {
        if (ship.getVariant().hasHullMod("ringba")) return false;
        if (ship.getVariant().hasHullMod("ringfog")) return false;
        if (ship.getVariant().hasHullMod("iebaptism")) return false;
        if (ship.getVariant().hasHullMod("starssynchronous")) return false;
        if (ship.getVariant().hasHullMod("paleandcrimson")) return false;
        return true;
    }


    public String getUnapplicableReason(ShipAPI ship) {
        if (ship.getVariant().hasHullMod("ringba")) {
            return "Ship already has a Ring Spirit interface";
        }
        if (ship.getVariant().hasHullMod("ringfog")) {
            return "Ship already has a Ring Spirit interface";
        }
        if (ship.getVariant().hasHullMod("iebaptism")){
            return "Ship already has a Ring Spirit interface";
        }
        if (ship.getVariant().hasHullMod("starssynchronous")){
            return "Ship already has a Ring Spirit interface";
        }
        if (ship.getVariant().hasHullMod("paleandcrimson")){
            return "Ship already has a Ring Spirit interface";
        }
        return null;
    }

}
