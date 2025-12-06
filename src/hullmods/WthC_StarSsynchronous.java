package data.hullmods;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.scripts.util.MagicIncompatibleHullmods;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//部分格式来源于北极星
public class WthC_StarSsynchronous extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static final float MISSILE_Bonus = 50f;

    private static final Set<String> BLOCKED_HULLMODS = new HashSet();

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padS = 2f;
        if (ship != null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("This is a peculiar baptism specifically for small and medium ships, causing these battlefield-flexible vessels to undergo massive changes both in appearance and internally.", WthC_ColorData.LIGHT_CRIMSON, pad);
            tooltip.addPara("With the help of the wraith network, these ships often sacrifice large amounts of crew and cargo space to connect to numerous elemental nodes, thereby gaining various strategically valuable unique characteristics.", WthC_ColorData.DUST_RED, padS);
        }
        tooltip.addSectionHeading("Effects on Ship", Alignment.TMID, 10.0F);

        tooltip.addPara("%s " + strings.get("StarSsynchronous1"), pad, Misc.getPositiveHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("IEBapitsm_TEXT0"), padS, Misc.getPositiveHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("StarSsynchronous2"), padS, Misc.getPositiveHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("StarSsynchronous3"), padS, Misc.getHighlightColor(), "#","1");
        tooltip.addPara("%s " + strings.get("StarSsynchronous4"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("StarSsynchronous5"), padS, Misc.getNegativeHighlightColor(), "#");

        tooltip.addSectionHeading("Restrictions and Special Effects", Alignment.TMID, 10.0F);

        tooltip.addPara("%s " + strings.get("StarSsynchronousEX1"), pad, Misc.getPositiveHighlightColor(), "#","50%");
        tooltip.addPara("%s " + strings.get("StarSsynchronousEX2"), padS, Misc.getNegativeHighlightColor(), "#","500%");
        tooltip.addPara("%s " + strings.get("StarSsynchronousEX3"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("IEBapitsm_TEXTn4"), pad, Misc.getNegativeHighlightColor(), "#");
    }


    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        int count = 0;
        if(!stats.getVariant().hasHullMod("ringinterferecross")) {
            if (!stats.getVariant().hasHullMod("ringinterferedust")) {
                if (!stats.getVariant().hasHullMod("ringinterfereie")) {
                    if (!stats.getVariant().hasHullMod("ringinterfererra")) {
                        if (!stats.getVariant().hasHullMod("ringinterferessoul")) {
                            if (!stats.getVariant().hasHullMod("ringinterfereelf")) {
                                count = 0;
                            }
                        }
                    }
                }
            }
        }


        if(stats.getVariant().hasHullMod("ringinterferecross")) {
            count = 1;
        }
        if (stats.getVariant().hasHullMod("ringinterferedust")) {
            count = 1;
        }
        if (stats.getVariant().hasHullMod("ringinterfereie")) {
            count = 1;
        }
        if (stats.getVariant().hasHullMod("ringinterfererra")) {
            count = 1;
        }
        if (stats.getVariant().hasHullMod("ringinterferessoul")) {
            count = 1;
        }
        if (stats.getVariant().hasHullMod("ringinterfereelf")) {
            count = 1;
        }

        stats.getMissileAmmoRegenMult().modifyPercent(id, MISSILE_Bonus);
        stats.getMissileRoFMult().modifyPercent(id, MISSILE_Bonus);

        if(count < 1){
            stats.getCRLossPerSecondPercent().modifyMult(id, 5f);
        }
    }

    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        Iterator i$ = BLOCKED_HULLMODS.iterator();

        while(i$.hasNext()) {
            String tmp = (String)i$.next();
            if (ship.getVariant().getHullMods().contains(tmp)) {
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "starssynchronous");
            }
        }
    }
        static {
        BLOCKED_HULLMODS.add("missleracks");
        BLOCKED_HULLMODS.add("missile_autoloader");
    }

}
