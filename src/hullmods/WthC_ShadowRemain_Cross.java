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
import java.util.Set;

//部分格式来源于北极星
public class WthC_ShadowRemain_Cross extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");

    private static final Set<String> BLOCKED_HULLMODS = new HashSet();
    protected String id123;

    private boolean runOnce = false;
    private final IntervalUtil GlowSS = new IntervalUtil(0.3F, 0.5F);

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padL = 20f;
        float padS = 2f;
        if (ship == null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("A mysterious lightweight interference protocol, with traces of consciousness information possibly originating from a certain witch", WthC_ColorData.MYSTERIOUS_PURPLE, pad);
            tooltip.addPara("Installing this protocol alone will only cause extremely subtle style changes in the ship's AI system, but it seems quite sensitive to other protocols?", WthC_ColorData.MYSTERIOUS_PURPLE, padS);
            tooltip.addPara("For certain reasons, please move to the loadout interface to view the specific detailed effects.", WthC_ColorData.HIGH_BLUE, padL);
        }
        if (ship != null) {
            int count = 0;
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
            int count2 = 0;
            if(ship.getVariant().hasHullMod("ringinterferecross")) {
                count2++;
            }
            if (ship.getVariant().hasHullMod("ringinterferedust")) {
                count2++;
            }
            if (ship.getVariant().hasHullMod("ringinterfereie")) {
                count2++;
            }
            if (ship.getVariant().hasHullMod("ringinterfererra")) {
                count2++;
            }
            if (ship.getVariant().hasHullMod("ringinterferessoul")) {
                count2++;
            }
            if (ship.getVariant().hasHullMod("ringinterfereelf")) {
                count2++;
            }

            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("A mysterious lightweight interference protocol, with traces of consciousness information possibly originating from a certain witch", WthC_ColorData.B_WHITE, pad);
            tooltip.addPara("Installing this protocol will make the ship's AI system produce quite peculiar effects in certain situations, and it seems quite sensitive to other equally mysterious modifications?", WthC_ColorData.B_WHITE, padS);

            tooltip.addSectionHeading("Shadow Interference Details", Alignment.TMID, 10.0F);

            tooltip.addPara("     %s " + strings.get("ShadowRemain_Cross_TEXT0"), pad, Misc.getHighlightColor(), "#","Different effects","Gradually rotating");
            tooltip.addPara("         %s " + strings.get("ShadowRemain_Cross_TEXT1"), padS, WthC_ColorData.HIGH_BLUE,"#","3","10");
            tooltip.addPara("         %s " + strings.get("ShadowRemain_Cross_TEXT2"), padS, WthC_ColorData.HIGH_BLUE,"#","25%","15%");
            tooltip.addPara("         %s " + strings.get("ShadowRemain_Cross_TEXT3"), padS, WthC_ColorData.HIGH_BLUE,"#","10%","5%");
            tooltip.addPara("         %s " + strings.get("ShadowRemain_Cross_TEXT4"), padS, WthC_ColorData.HIGH_BLUE,"#","25%","15%");

            if (count >= 1){
                tooltip.addPara("     %s " + strings.get("ShadowRemain_TEXT1"), pad, Misc.getHighlightColor(), "#","Crimson Modification");
                if (ship.getVariant().hasHullMod("crimsonmodificationelf")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT2"), padS, WthC_ColorData.HIGH_BLUE, "#","50%","Soul");
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT3"), padS, WthC_ColorData.HIGH_BLUE, "#","25%","Soul");
                }
                if (ship.getVariant().hasHullMod("crimsonmodificationie")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT4"), padS, WthC_ColorData.HIGH_BLUE, "#","5%","Origin");
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT5"), padS, WthC_ColorData.HIGH_BLUE, "#","10%","Origin");
                }
                if (ship.getVariant().hasHullMod("crimsonmodificationcross")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT6"), padS, WthC_ColorData.HIGH_BLUE, "#","50%","Cross");
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT7"), padS, WthC_ColorData.HIGH_BLUE, "#","50%","Cross");
                }
                if (ship.getVariant().hasHullMod("crimsonmodificationdust")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT8"), padS, WthC_ColorData.HIGH_BLUE, "#","10","Dust");
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT9"), padS, WthC_ColorData.HIGH_BLUE, "#","5%","Dust");
                }
                if (ship.getVariant().hasHullMod("crimsonmodificationrra")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT10"), padS, WthC_ColorData.HIGH_BLUE, "#","10%","Eden");
                }
                if (ship.getVariant().hasHullMod("crimsonmodificationssoul")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT11"), padS, WthC_ColorData.HIGH_BLUE, "#","15%","Spirit");
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_TEXT12"), padS, WthC_ColorData.HIGH_BLUE, "#","5%","Spirit");
                }
            }
            if (count2 >= 1){
                tooltip.addPara("     %s " + strings.get("ShadowRemain_ATEXT1"), pad, Misc.getHighlightColor(), "#","Wraith Protocol");
                if (ship.getVariant().hasHullMod("ringinterfereelf")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_ATEXT2"), padS, WthC_ColorData.HIGH_BLUE, "#","10%","Soul");
                }
                if (ship.getVariant().hasHullMod("ringinterfereie")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_ATEXT3"), padS, WthC_ColorData.HIGH_BLUE, "#","Origin");
                }
                if (ship.getVariant().hasHullMod("ringinterferecross")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_ATEXT4"), padS, WthC_ColorData.HIGH_BLUE, "#","10%","Cross");
                }
                if (ship.getVariant().hasHullMod("ringinterferedust")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_ATEXT5"), padS, WthC_ColorData.HIGH_BLUE, "#","5%","Dust");
                }
                if (ship.getVariant().hasHullMod("ringinterfererra")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_ATEXT6"), padS, WthC_ColorData.HIGH_BLUE, "#","5%","Eden");
                }
                if (ship.getVariant().hasHullMod("ringinterferessoul")) {
                    tooltip.addPara("         %s " + strings.get("ShadowRemain_ATEXT7"), padS, WthC_ColorData.HIGH_BLUE, "#","15%","Spirit");
                }
            }

        tooltip.addSectionHeading("额外说明", Alignment.TMID, 10.0F);
            tooltip.addPara("     %s " + strings.get("ShadowRemain_EXTEXT0"), pad, Misc.getHighlightColor(), "#");
            tooltip.addPara("     %s " + strings.get("ShadowRemain_EXTEXT1"), padS, Misc.getNegativeHighlightColor(), "#","1");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_EX233"), padS, Misc.getNegativeHighlightColor(), "#");
            tooltip.addPara("     %s " + strings.get("ShadowRemain_EXTEXT2"), padS, Misc.getNegativeHighlightColor(), "#");
        }
    }

    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        if (stats.getVariant().hasHullMod("ringinterfereelf")){
            stats.getFighterRefitTimeMult().modifyPercent(id, -10F);
        }
        if (stats.getVariant().hasHullMod("ringinterferecross")) {
            stats.getBallisticRoFMult().modifyPercent(id, 10F);
            stats.getEnergyRoFMult().modifyPercent(id, 10F);
        }
        if (stats.getVariant().hasHullMod("ringinterferedust")) {
            stats.getHullDamageTakenMult().modifyMult(id, 0.95F);
            stats.getArmorDamageTakenMult().modifyMult(id, 0.95F);
            stats.getShieldDamageTakenMult().modifyMult(id, 0.95F);
        }
        if (stats.getVariant().hasHullMod("ringinterfererra")) {
            stats.getDamageToFrigates().modifyPercent(id, 5F);
            stats.getDamageToDestroyers().modifyPercent(id, 5F);
            stats.getDamageToCruisers().modifyPercent(id, 5F);
            stats.getDamageToCapital().modifyPercent(id, 5F);
        }
        if (stats.getVariant().hasHullMod("ringinterferessoul")) {
            stats.getHullBonus().modifyPercent(id, 15F);
        }

        if (stats.getVariant().hasHullMod("crimsonmodificationelf")) {
            stats.getFighterRefitTimeMult().modifyPercent(id, 50F);
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationie")) {
            stats.getShieldDamageTakenMult().modifyMult(id, 0.95F);
            stats.getAcceleration().modifyPercent(id, -20F);
            stats.getDeceleration().modifyPercent(id, -10F);
            stats.getTurnAcceleration().modifyPercent(id, -20F);
            stats.getMaxTurnRate().modifyPercent(id, -10F);
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationcross")) {
            stats.getMissileRoFMult().modifyPercent(id, 50F);
            stats.getMissileAmmoRegenMult().modifyPercent(id, 50F);
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationdust")) {
            stats.getDamageToFrigates().modifyMult(id, 0.95F);
            stats.getDamageToDestroyers().modifyMult(id, 0.95F);
            stats.getDamageToCruisers().modifyMult(id, 0.95F);
            stats.getDamageToCapital().modifyMult(id, 0.95F);
            stats.getMaxSpeed().modifyFlat(id, 10F);
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationrra")) {
            stats.getDamageToFrigates().modifyPercent(id, 10F);
            stats.getDamageToDestroyers().modifyPercent(id, 10F);
            stats.getDamageToCruisers().modifyPercent(id, 10F);
            stats.getDamageToCapital().modifyPercent(id, 10F);
        }
        if (stats.getVariant().hasHullMod("crimsonmodificationssoul")) {
            stats.getArmorBonus().modifyPercent(id, 15F);
            stats.getHardFluxDissipationFraction().modifyFlat(id, 0.05F);
        }


    }
    public void applyEffectsToFighterSpawnedByShip(ShipAPI fighter, ShipAPI ship, String id) {
        if (ship.getVariant().hasHullMod("ringinterfereelf")){
            fighter.getMutableStats().getTimeMult().modifyPercent(id, 25F);
        }

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
                        Misc.scaleAlpha(WthC_ColorData.IE_WHITE, 1f), //图片颜色
                        true, //相对位置是否固定
                        0.5f,  //渐入时间
                        0.5f,  //持续时间
                        0.4f  //渐出时间
                );
            }
        }
    }

    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        for (String tmp : BLOCKED_HULLMODS) {
            if (ship.getVariant().getHullMods().contains(tmp)) {
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "shadowremaincross");
            }
        }


    }
    public boolean isApplicableToShip(ShipAPI ship) {
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
        BLOCKED_HULLMODS.add("ringinterferecross");
        BLOCKED_HULLMODS.add("crimsonmodificationcross");
        BLOCKED_HULLMODS.add("shadowremainrra");
        BLOCKED_HULLMODS.add("shadowremainssoul");
        BLOCKED_HULLMODS.add("shadowremainie");
        BLOCKED_HULLMODS.add("shadowremaindust");
        BLOCKED_HULLMODS.add("shadowremainelf");
    }
}
