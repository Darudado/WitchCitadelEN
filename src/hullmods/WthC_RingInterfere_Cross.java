package data.hullmods;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.scripts.util.MagicIncompatibleHullmods;
import data.scripts.util.MagicRender;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//部分格式来源于北极星
public class WthC_RingInterfere_Cross extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static float PEAK_BONUS_PERCENT = 60f;
    public static float DEGRADE_REDUCTION_PERCENT = 25f;

    public static final float ROFBonus = 15f;
    public static final float SPEED_BONUS = -33f;
    public static final float FLUX_Bonus = -15f;
    public static float REGEN_BONUS = 20f;

    private static final Set<String> BLOCKED_HULLMODS = new HashSet();
    private static final Set<String> BLOCKED_HULLMODS2 = new HashSet();
    private boolean runOnce = false;
    private final IntervalUtil GlowSS = new IntervalUtil(0.3F, 0.5F);

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        int count = 0;
        float pad = 10f;
        float padL = 20f;
        float padS = 2f;
        if (ship == null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("This Wraith Interference Protocol contains partial stellar consciousness information derived from the Cross Witch", WthC_ColorData.IE_WHITE, pad);
            tooltip.addPara("Installing this protocol will make the ship's AI system more frantically increase projectile output... even if it reduces some propulsion power", WthC_ColorData.IE_WHITE, padS);
            tooltip.addPara("For certain reasons, please move to the loadout interface to view the specific detailed effects.", WthC_ColorData.HIGH_BLUE, padL);
        }
        if (ship != null) {
        if (ship.getVariant().hasHullMod("crimsonmodificationcross")) {
            count++;
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationdust")) {
            count++;
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationie")) {
            count++;
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationrra")) {
            count++;
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationssoul")) {
            count++;
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationelf")) {
            count++;
        }
        if (ship.getVariant().hasHullMod("starssynchronous")) {
            count++;
        }
        if (!ship.getVariant().hasHullMod("crimsonmodificationssoul")) {
            if (!ship.getVariant().hasHullMod("crimsonmodificationdust")) {
                if (!ship.getVariant().hasHullMod("crimsonmodificationie")) {
                    if (!ship.getVariant().hasHullMod("crimsonmodificationrra")) {
                        if (!ship.getVariant().hasHullMod("crimsonmodificationcross")) {
                            if (!ship.getVariant().hasHullMod("crimsonmodificationelf")) {
                                if (!ship.getVariant().hasHullMod("starssynchronous")) {
                                    count = 0;
                                }
                            }
                        }
                    }
                }
            }
        }



            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("This Wraith Interference Protocol contains partial stellar consciousness information derived from the Cross Witch", WthC_ColorData.IE_WHITE, pad);
            tooltip.addPara("Installing this protocol will make the ship's AI system more frantically increase projectile output... even if it reduces some propulsion power", WthC_ColorData.IE_WHITE, padS);
            tooltip.addPara("Mortals are neither omniscient nor omnipotent...", WthC_ColorData.CROSS_GARY, pad);
            if(count >= 1) {
                tooltip.addPara("                                          "+"Yet even true deities are not what they seem", WthC_ColorData.DUST_RED, padS);
            }

            tooltip.addSectionHeading("Subsystem Override Details", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("RingInterfere_1"), pad, Misc.getHighlightColor(), "#");
        if (!ship.getVariant().hasHullMod("ringreverse")){
                tooltip.addPara("     %s " + strings.get("RingInterfere_2"), padS, Misc.getPositiveHighlightColor(), "#", "60");
        }
        if (ship.getVariant().hasHullMod("ringreverse")){
            tooltip.addPara("     %s " + strings.get("RingInterfere_2"), padS, WthC_ColorData.HIGH_BLUE, "#", "120");
        }
        tooltip.addPara("     %s " + strings.get("RingInterfere_3"), padS, Misc.getNegativeHighlightColor(), "#", "33%");
        tooltip.addPara(" %s " + strings.get("RingInterfere_4"), pad, Misc.getHighlightColor(), "#");
        tooltip.addPara("     %s " + strings.get("RingInterfere_Cross_TEXT1"), padS, Misc.getPositiveHighlightColor(), "#", "15%");
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("RingInterfere_Cross_TEXTEX1"), padS, WthC_ColorData.HIGH_BLUE, "#", "25%");
        }
        tooltip.addPara("     %s " + strings.get("RingInterfere_Cross_TEXT2"), padS, Misc.getNegativeHighlightColor(), "#", "33%");

        if(count >= 1) {
            tooltip.addPara(" %s " + strings.get("RingInterfere_EX"), pad, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addSectionHeading("Limitations and Conflicts", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("RingInterfere_X"), pad, Misc.getNegativeHighlightColor(), "#","1");
        tooltip.addPara(" %s " + strings.get("CrimsonModification_EX233"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara(" %s " + strings.get("RingInterfere_X2"), padS, Misc.getNegativeHighlightColor(), "#");
        }
    }

    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        int count = 0;
        if (stats.getVariant().hasHullMod("crimsonmodificationcross")) {
            count++;
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationdust")) {
            count++;
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationie")) {
            count++;
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationrra")) {
            count++;
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationssoul")) {
            count++;
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationelf")) {
            count++;
        }
        if (stats.getVariant().hasHullMod("starssynchronous")) {
            count++;
        }
        if (!stats.getVariant().hasHullMod("crimsonmodificationssoul")) {
            if (!stats.getVariant().hasHullMod("crimsonmodificationdust")) {
                if (!stats.getVariant().hasHullMod("crimsonmodificationie")) {
                    if (!stats.getVariant().hasHullMod("crimsonmodificationrra")) {
                        if (!stats.getVariant().hasHullMod("crimsonmodificationcross")) {
                            if (!stats.getVariant().hasHullMod("crimsonmodificationeif")) {
                                if (!stats.getVariant().hasHullMod("starssynchronous")) {
                                    count = 0;
                                }
                            }
                        }
                    }
                }
            }
        }

        stats.getPeakCRDuration().modifyFlat(id, PEAK_BONUS_PERCENT);
        stats.getBallisticRoFMult().modifyPercent(id, ROFBonus);
        stats.getEnergyRoFMult().modifyPercent(id, ROFBonus);
        if(count >= 1) {
            stats.getBallisticAmmoRegenMult().modifyPercent(id, REGEN_BONUS);
            stats.getEnergyAmmoRegenMult().modifyPercent(id, REGEN_BONUS);
        }
        stats.getProjectileSpeedMult().modifyPercent(id, SPEED_BONUS);


        boolean sMod = isSMod(stats);
        if (sMod) {
            stats.getEnergyWeaponFluxCostMod().modifyPercent(id, FLUX_Bonus);
            stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FLUX_Bonus);
        }
        if (!sMod) {
            stats.getCRLossPerSecondPercent().modifyMult(id, 1f + DEGRADE_REDUCTION_PERCENT / 100f);
        }
    }

    @Override
    public String getSModDescriptionParam(int index, HullSize hullSize, ShipAPI ship) {
        if (index == 0) return "15%";
        return null;
    }

    public void advanceInCombat(ShipAPI ship, float amount) {
        if (ship == null) {
            return;
        }
        this.GlowSS.advance(amount);
        if (!this.runOnce) {
            this.runOnce = true;
        }
        CombatEngineAPI engine = Global.getCombatEngine();
        if (!ship.isAlive()) return;
        if (engine == null) return;


        SpriteAPI effect2 = Global.getSettings().getSprite("fx", "glow1");
        Vector2f size2 = new Vector2f((effect2.getWidth() * 0.5F), (effect2.getHeight() * 0.5F));
        Float numMax = 1f;
        if (this.GlowSS.intervalElapsed()) {
            for (int i = 0; i < numMax; i = i + 1) {
                Vector2f loc = MathUtils.getRandomPointInCircle(ship.getLocation(), 240f);
                MagicRender.battlespace(
                        effect2, //图片
                        loc, //中心坐标
                        new Vector2f(0f, 0f), //贴图的移动速度
                        size2, //尺寸
                        new Vector2f(0f, 0f), //贴图的长宽变化速度
                        -180f, //角度
                        0f, //旋转速率
                        Misc.scaleAlpha(WthC_ColorData.CROSS_GARY, 1f), //图片颜色
                        true, //相对位置是否固定
                        0.5f,  //渐入时间
                        0.5f,  //持续时间
                        0.4f  //渐出时间
                );
            }
        }
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
        if (ship.getVariant().hasHullMod("ringintegration")){
            count2--;
        }



        Iterator i$2 = BLOCKED_HULLMODS2.iterator();
        Iterator i$ = BLOCKED_HULLMODS.iterator();


        while (i$.hasNext()) {
            String tmp = (String)i$.next();
            if (ship.getVariant().getHullMods().contains(tmp)) {
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "ringinterferecross");
            }
        }
            while (i$2.hasNext()) {
                String tmp2 = (String)i$2.next();
                if(count2 >= 2){
                    if (ship.getVariant().getHullMods().contains(tmp2)) {
                        MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp2, "ringinterferecross");
                    }
                }
            }

    }
    public boolean isApplicableToShip(ShipAPI ship) {
        if (ship.getParentStation() != null) return false;
        if(!ship.getVariant().hasHullMod("ringba")){
            if (!ship.getVariant().hasHullMod("ringfog")){
                if (!ship.getVariant().hasHullMod("ringie")){
                    if (!ship.getVariant().hasHullMod("iebaptism")){
                        if (!ship.getVariant().hasHullMod("starssynchronous")){
                            if (!ship.getVariant().hasHullMod("paleandcrimson")){
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
        if (ship.getParentStation() != null){
            return "Cannot be installed on modules";
        }
        if(!ship.getVariant().hasHullMod("ringba")){
            if (!ship.getVariant().hasHullMod("ringfog")){
                if (!ship.getVariant().hasHullMod("ringie")){
                    if (!ship.getVariant().hasHullMod("iebaptism")){
                        if (!ship.getVariant().hasHullMod("starssynchronous")){
                            if (!ship.getVariant().hasHullMod("paleandcrimson")){
                                return "Ship has no wraith interface";
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
        static {

        BLOCKED_HULLMODS.add("hardened_subsystems");
        BLOCKED_HULLMODS.add("crimsonmodificationcross");

            BLOCKED_HULLMODS2.add("ringinterferessoul");
            BLOCKED_HULLMODS2.add("ringinterfereie");
            BLOCKED_HULLMODS2.add("ringinterfererra");
            BLOCKED_HULLMODS2.add("ringinterferedust");
            BLOCKED_HULLMODS2.add("ringinterfereelf");
    }

}
