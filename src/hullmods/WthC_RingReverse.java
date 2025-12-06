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

public class WthC_RingReverse extends BaseHullMod {
    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");

    public static float PEAK_BONUS_PERCENT = 60f;

    public WthC_RingReverse() {
    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padS = 2f;
        tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
        tooltip.addPara("Although the extra consciousness synchronization effect of the wraith protocol was born as a core factor at the beginning of the Crimson Modification Plan design, this forced binding change always makes some captains who are not familiar with new things feel troubled.", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Perhaps feeling helpless about this, the Origin Witch, who is perhaps the only one in the Crimson City with extensive unique personal insights into space naval warfare, designed this reverse protocol to make the choice between Crimson Modification and Wraith Protocol more flexible.", WthC_ColorData.IE_WHITE, padS);
        tooltip.addSectionHeading("Effects of Reverse Synchronization", Alignment.TMID, 10.0F);
        tooltip.addPara(" %s " + strings.get("RingReverse_TEXT1"), pad, Misc.getHighlightColor(), "#");
        tooltip.addPara(" %s " + strings.get("RingReverse_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#", "60");
        tooltip.addSectionHeading("Restrictions and Conflicts", Alignment.TMID, 10.0F);
        tooltip.addPara(" %s " + strings.get("RingReverse_TEXT3"), pad, Misc.getNegativeHighlightColor(), "#","1","1");
    }
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getPeakCRDuration().modifyFlat(id, PEAK_BONUS_PERCENT);
    }


    public boolean isApplicableToShip(ShipAPI ship) {

        if(!ship.getVariant().hasHullMod("ringinterferecross")){
            if (!ship.getVariant().hasHullMod("ringinterferedust")){
                if (!ship.getVariant().hasHullMod("ringinterfereie")){
                    if (!ship.getVariant().hasHullMod("ringinterfererra")){
                        if (!ship.getVariant().hasHullMod("ringinterferessoul")){
                            if (!ship.getVariant().hasHullMod("ringinterfereelf")) {
                                return false;
                            }
                       }
                    }
                }
            }
        }
        if (!ship.getVariant().hasHullMod("crimsonmodificationssoul")) {
            if (!ship.getVariant().hasHullMod("crimsonmodificationdust")) {
                if (!ship.getVariant().hasHullMod("crimsonmodificationrra")) {
                    if (!ship.getVariant().hasHullMod("crimsonmodificationcross")) {
                        if (!ship.getVariant().hasHullMod("crimsonmodificationie")) {
                            if (!ship.getVariant().hasHullMod("crimsonmodificationelf")) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public String getUnapplicableReason(ShipAPI ship) {
        if(!ship.getVariant().hasHullMod("ringinterferecross")){
            if (!ship.getVariant().hasHullMod("ringinterferedust")){
                if (!ship.getVariant().hasHullMod("ringinterfereie")){
                    if (!ship.getVariant().hasHullMod("ringinterfererra")){
                        if (!ship.getVariant().hasHullMod("ringinterferessoul")){
                            if (!ship.getVariant().hasHullMod("ringinterfereelf")) {
                                return "Ship has no wraith protocol installed";
                            }
                        }
                    }
                }
            }
        }

        if (!ship.getVariant().hasHullMod("crimsonmodificationssoul")) {
            if (!ship.getVariant().hasHullMod("crimsonmodificationdust")) {
                if (!ship.getVariant().hasHullMod("crimsonmodificationrra")) {
                    if (!ship.getVariant().hasHullMod("crimsonmodificationcross")) {
                        if (!ship.getVariant().hasHullMod("crimsonmodificationie")) {
                            if (!ship.getVariant().hasHullMod("crimsonmodificationelf")) {
                                return "Ship has no Crimson Modification applied";
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

}
