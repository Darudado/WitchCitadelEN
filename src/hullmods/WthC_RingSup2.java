//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// 从其他插件简单修改

public class WthC_RingSup2 extends BaseHullMod {
    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static float MISSILE_Bonus = 15.0f;

    public WthC_RingSup2() {
    }

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        tooltip.addSectionHeading("Introduction", Alignment.TMID, 10.0F);
        tooltip.addPara("Just a simple auxiliary plugin", WthC_ColorData.B_WHITE, pad);

    }
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        int count2 = 0;

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
        if (ship.getVariant().hasHullMod("crimsonmodificationcross")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationdust")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationie")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationrra")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationssoul")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationelf")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("shadowremaincross")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("shadowremaindust")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("shadowremainie")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("shadowremainrra")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("shadowremainssoul")) {
            count2++;
        }
        if (ship.getVariant().hasHullMod("shadowremainelf")) {
            count2++;
        }
        if (count2 > 0){
            ship.getVariant().addMod("ringfog");
        }

    }



}
