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
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// 从其他插件简单修改

public class WthC_RingFog extends BaseHullMod {
    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static float MISSILE_Bonus1 = 15.0f;
    public static float MISSILE_Bonus2 = 20.0f;
    public static float MISSILE_Bonus3 = 25.0f;

    public WthC_RingFog() {
    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padS = 2f;
        if (ship == null){
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 10.0F);
            tooltip.addPara("The Ring Spirit interface, interfered with by the Dust Witch, is a later product in this series. This modification often aligns more closely with the tactical style of the Persean Sector.", WthC_ColorData.DUST_PURPLE, pad);
            tooltip.addPara("Due to the designer's philosophy of warfare, equipping this interface enhances the ship's missile projection capabilities.", WthC_ColorData.DUST_PURPLE, pad);
            tooltip.addPara("For certain reasons, please move to the fitting screen to view specific detailed effects.", WthC_ColorData.HIGH_BLUE, pad);
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
            tooltip.addPara("The Ring Spirit interface, interfered with by the Dust Witch, is a later product in this series. This modification often aligns more closely with the tactical style of the Persean Sector.", WthC_ColorData.DUST_PURPLE, pad);
            tooltip.addPara("Due to the designer's philosophy of warfare, equipping this interface enhances the ship's missile projection capabilities.", WthC_ColorData.DUST_PURPLE, pad);
            tooltip.addPara("For certain reasons, please move to the fitting screen to view specific detailed effects.", WthC_ColorData.HIGH_BLUE, pad);
            tooltip.addPara(" %s " + strings.get("RingIe_1"), pad, Misc.getHighlightColor(), "#", "Crimson Modification", "Ring Spirit Protocol", "Shadow Remnant");

            if (cX == 0){
                tooltip.addPara(" %s " + strings.get("StarSsynchronousEX1"), padS, Misc.getHighlightColor(), "#", "15%");
            } else if (cX == 1){
                tooltip.addPara(" %s " + strings.get("StarSsynchronousEX1"), padS, WthC_ColorData.HIGH_BLUE, "#", "20%");
            } else if (cX == 2){
                tooltip.addPara(" %s " + strings.get("StarSsynchronousEX1"), padS, WthC_ColorData.HIGH_BLUE, "#", "25%");
            } else {
                tooltip.addPara(" %s " + strings.get("StarSsynchronousEX1"), padS, WthC_ColorData.HIGH_BLUE, "#", "25%");
                tooltip.addPara(" %s " + strings.get("Ring_FOG"), padS, WthC_ColorData.HIGH_BLUE, "#", "10%","5%");
            }
            tooltip.addSectionHeading("Interface Usage Status", Alignment.TMID, 10.0F);
            tooltip.addPara(" %s " + strings.get("Ring_0"), pad, WthC_ColorData.HIGH_BLUE, "#", "Specific Effects", "Gradually Increase");
            if (c1 == 0){
                tooltip.addPara(" %s " + strings.get("Ring_1"), pad, Misc.getNegativeHighlightColor(), "#", "Not Performed");
            } else {
                tooltip.addPara(" %s " + strings.get("Ring_1"), pad, WthC_ColorData.HIGH_BLUE, "#", "Performed");
            }
            if (c2 == 0){
                tooltip.addPara(" %s " + strings.get("Ring_2"), padS, Misc.getNegativeHighlightColor(), "#", "Not Mounted");
            } else {
                tooltip.addPara(" %s " + strings.get("Ring_2"), padS, WthC_ColorData.HIGH_BLUE, "#", "Mounted");
            }
            if (c3 == 0){
                tooltip.addPara(" %s " + strings.get("Ring_3"), padS, Misc.getNegativeHighlightColor(), "#", "Not Interfered");
            } else {
                tooltip.addPara(" %s " + strings.get("Ring_3"), padS, WthC_ColorData.HIGH_BLUE, "#", "Interfered");
            }
            tooltip.addSectionHeading("Limitations and Conflicts", Alignment.TMID, 10.0F);
            tooltip.addPara(" %s " + strings.get("RingIe_2"), pad, Misc.getNegativeHighlightColor(), "#", "1");
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
            stats.getMissileAmmoRegenMult().modifyPercent(id, MISSILE_Bonus1);
            stats.getMissileRoFMult().modifyPercent(id, MISSILE_Bonus1);
        } else if (cX == 1) {
            stats.getMissileAmmoRegenMult().modifyPercent(id, MISSILE_Bonus2);
            stats.getMissileRoFMult().modifyPercent(id, MISSILE_Bonus2);
        } else if (cX == 2) {
            stats.getMissileAmmoRegenMult().modifyPercent(id, MISSILE_Bonus3);
            stats.getMissileRoFMult().modifyPercent(id, MISSILE_Bonus3);
        } else {
            stats.getMissileAmmoRegenMult().modifyPercent(id, MISSILE_Bonus3);
            stats.getMissileRoFMult().modifyPercent(id, MISSILE_Bonus3);
            stats.getProjectileDamageTakenMult().modifyMult(id, 1.05F);
            stats.getBeamDamageTakenMult().modifyMult(id, 1.05F);
            stats.getMissileDamageTakenMult().modifyMult(id, 0.9F);
        }


    }
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        if (!ship.getVariant().hasHullMod("ringsup2")){
            ship.getVariant().addMod("ringsup2");
        }
        ship.getVariant().removeMod("ringsup");
        ship.getVariant().removeMod("ringsup3");
    }
    public boolean isApplicableToShip(ShipAPI ship) {
        if (ship.getVariant().hasHullMod("ringba")) return false;
        if (ship.getVariant().hasHullMod("ringie")) return false;
        if (ship.getVariant().hasHullMod("iebaptism")) return false;
        if (ship.getVariant().hasHullMod("starssynchronous")) return false;
        if (ship.getVariant().hasHullMod("paleandcrimson")) return false;
        return true;
    }


    public String getUnapplicableReason(ShipAPI ship) {
        if (ship.getVariant().hasHullMod("ringba")) {
            return "The ship already has a Ring Spirit interface.";
        }
        if (ship.getVariant().hasHullMod("ringie")) {
            return "The ship already has a Ring Spirit interface.";
        }
        if (ship.getVariant().hasHullMod("iebaptism")) {
            return "The ship already has a Ring Spirit interface.";
        }
        if (ship.getVariant().hasHullMod("starssynchronous")) {
            return "The ship already has a Ring Spirit interface.";
        }
        if (ship.getVariant().hasHullMod("paleandcrimson")) {
            return "The ship already has a Ring Spirit interface.";
        }
        return null;
    }

}
