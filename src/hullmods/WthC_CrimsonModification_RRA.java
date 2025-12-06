package data.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
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
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import java.util.*;

// Some formats from Polaris
public class WthC_CrimsonModification_RRA extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static final float PEAK_BONUS_PERCENT = 75f;
    public static final float DEGRADE_REDUCTION_PERCENT = 33f;
    public static final float CASUALTY_REDUCTION = 50f;
    public static final float DAMAGE_TAKEN_MULT = 0.85f;
    public static final float DAMAGE_TAKEN_MULT2 = 0.8f;
    public static final float WEAPON_RANGE_BONUS = 0.85f;
    public static final float DAMAGE = 0.85f;
    public static final float DAMAGE2 = 0.7f;
    private static final float FIGHTER_RATE = 25f;
    public static final float HULL_BONUS = 0.75f;
    public static float CREW_PERCENT = 100.0F;
    public static final float SUPPLY_USE_MULT = 1.5F;

//    public static final float PROFILE_MULT = 0.5f;
//    public static final float HEALTH_BONUS = 150f;
//    public static final float REPAIR_BONUS = 33f;
//    private static final int BURN_LEVEL_BONUS = 1;
//    public static float DP_REDUCTION = -10.0F;

    private static Map mag1 = new HashMap();
    static {
        mag1.put(HullSize.CRUISER, 20f);
        mag1.put(HullSize.CAPITAL_SHIP, 10f);
    }
    private final IntervalUtil GlowSS = new IntervalUtil(0.3F, 0.5F);
    private boolean runOnce = false;
    private static final Set<String> BLOCKED_HULLMODS = new HashSet();

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        int count = 0;
        float pad = 10f;
        float padL = 20f;
        float padS = 2f;
        if (ship == null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Youyu, the Art Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
            tooltip.addPara("This witch is a peace-loving art master who, though not skilled in combat, can protect herself through various means", WthC_ColorData.MID_BLUE, pad);
            tooltip.addPara("Therefore, this large-scale modification strengthens the ship's cruising performance while sacrificing some combat capability, and can greatly ensure the comfort and personal safety of all crew members", WthC_ColorData.LIGHT_BLUE, padS);
            tooltip.addPara("For certain reasons, please move to the loadout screen to view detailed effects.", WthC_ColorData.HIGH_BLUE, padL);
        }
        if (ship != null) {
        if (ship.getVariant().hasHullMod("ringreverse")){
            count = 0;
        }
        if (!ship.getVariant().hasHullMod("ringreverse")){
            if(!ship.getVariant().hasHullMod("ringinterferecross")) {
                if (!ship.getVariant().hasHullMod("ringinterferedust")) {
                    if (!ship.getVariant().hasHullMod("ringinterfereie")) {
                        if (!ship.getVariant().hasHullMod("ringinterfererra")) {
                            if (!ship.getVariant().hasHullMod("ringinterferessoul")) {
                                if (!ship.getVariant().hasHullMod("ringinterfereelf")) {
                                    count = 0;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!ship.getVariant().hasHullMod("ringreverse")){
            if(count == 0){
                if(ship.getVariant().hasHullMod("ringinterferecross")) {
                    count = 1;
                }
                if (ship.getVariant().hasHullMod("ringinterferedust")) {
                    count = 1;
                }
                if (ship.getVariant().hasHullMod("ringinterfereie")) {
                    count = 1;
                }
                if (ship.getVariant().hasHullMod("ringinterfererra")) {
                    count = 1;
                }
                if (ship.getVariant().hasHullMod("ringinterferessoul")) {
                    count = 1;
                }
                if (ship.getVariant().hasHullMod("ringinterfereelf")) {
                    count = 1;
                }
            }
        }


        tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
        tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Yue, the Art Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("This witch is a peace-loving art master who, though not skilled in combat, can protect herself through various means", WthC_ColorData.MID_BLUE, pad);
        tooltip.addPara("Therefore, this large-scale modification strengthens the ship's cruising performance while sacrificing some combat capability, and can greatly ensure the comfort and personal safety of all crew members", WthC_ColorData.LIGHT_BLUE, padS);
        tooltip.addPara("Staying alive is the only thing a soldier needs to do on the battlefield", WthC_ColorData.LIGHT_CRIMSON, pad);

        if(count >= 1){
            tooltip.addPara("                                               "+"Even if... living itself is already an act of cowardice?", WthC_ColorData.B_WHITE, padS);
        }
        tooltip.addSectionHeading("Ship Modification Details", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("CrimsonModification_RRA_TEXT0"), pad, Misc.getPositiveHighlightColor(), "#");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXT00"), padS, Misc.getPositiveHighlightColor(), "#", "15%");
        }
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXTEX00"), padS, WthC_ColorData.HIGH_BLUE, "#", "15%");
        }
        tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXT1"), padS, Misc.getPositiveHighlightColor(), "#", "75%");
        tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#", "33%");
        tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXT3"), padS, Misc.getPositiveHighlightColor(), "#", "20","10");
        tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXT8"), padS, Misc.getPositiveHighlightColor(), "#", "50%");
        tooltip.addPara(" %s " + strings.get("CrimsonModification_RRA_TEXTd0"), pad, Misc.getNegativeHighlightColor(), "#");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXTd1"), padS, Misc.getNegativeHighlightColor(), "#", "15%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXTd2"), padS, Misc.getNegativeHighlightColor(), "#", "15%");
        }
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXTdEX1"), padS, WthC_ColorData.HIGH_BLUE, "#");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXTd2"), padS, WthC_ColorData.HIGH_BLUE, "#", "30%");
        }

        tooltip.addPara("     %s " + strings.get("CrimsonModification_RRA_TEXTd3"), padS, Misc.getNegativeHighlightColor(), "#", "25%");

        if(count >= 1) {
            tooltip.addPara(" %s " + strings.get("CrimsonModification_EX"), pad, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addSectionHeading("Restrictions and Conflicts", Alignment.TMID, 10.0F);

        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT000"), pad, Misc.getHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT1"), pad, Misc.getNegativeHighlightColor(), "#","1");
        tooltip.addPara("%s " + strings.get("CrimsonModification_EX233"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT11"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT22"), padS, Misc.getNegativeHighlightColor(), "#");

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
        Float numMax = 3f;
        if (this.GlowSS.intervalElapsed()) {
            for (int i = 0; i < numMax; i = i + 1) {
                Vector2f loc = MathUtils.getRandomPointInCircle(ship.getLocation(), 240f);
                MagicRender.battlespace(
                        effect2, //Image
                        loc, //Center coordinates
                        new Vector2f(0f, 0f), //Image movement speed
                        size2, //Size
                        new Vector2f(0f, 0f), //Image size change speed
                        -180f, //Angle
                        0f, //Rotation rate
                        Misc.scaleAlpha(WthC_ColorData.DEEP_BLUE, 1f), //Image color
                        true, //Fixed relative position
                        0.5f,  //Fade in time
                        0.5f,  //Duration
                        0.4f  //Fade out time
                );
            }
        }
    }
    
    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        int count = 0;
        if (stats.getVariant().hasHullMod("ringreverse")){
            count = 0;
        }
        if (!stats.getVariant().hasHullMod("ringreverse")){
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
        }

        if (!stats.getVariant().hasHullMod("ringreverse")){
            if(count == 0){
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
            }
        }

        stats.getPeakCRDuration().modifyPercent(id, PEAK_BONUS_PERCENT);
        stats.getCRLossPerSecondPercent().modifyMult(id, 1f - DEGRADE_REDUCTION_PERCENT / 100f);
        stats.getCrewLossMult().modifyMult(id, 1f - CASUALTY_REDUCTION * 0.01f);
        stats.getFighterRefitTimeMult().modifyPercent(id, FIGHTER_RATE);
        stats.getMaxSpeed().modifyFlat(id, (Float) mag1.get(hullSize));


        if(count == 0){
            stats.getDamageToFrigates().modifyMult(id, DAMAGE);
            stats.getDamageToDestroyers().modifyMult(id, DAMAGE);
            stats.getDamageToCapital().modifyMult(id, DAMAGE);
            stats.getDamageToCruisers().modifyMult(id, DAMAGE);
            stats.getArmorDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT);
            stats.getHullDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT);
            stats.getBallisticWeaponRangeBonus().modifyMult(id, WEAPON_RANGE_BONUS);
            stats.getEnergyWeaponRangeBonus().modifyMult(id, WEAPON_RANGE_BONUS);
        }
        if(count >= 1) {
            stats.getDamageToFrigates().modifyMult(id, DAMAGE2);
            stats.getDamageToDestroyers().modifyMult(id, DAMAGE2);
            stats.getDamageToCapital().modifyMult(id, DAMAGE2);
            stats.getDamageToCruisers().modifyMult(id, DAMAGE2);
            stats.getArmorDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT);
            stats.getHullDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT);
            stats.getShieldDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT);
        }


//        stats.getEngineHealthBonus().modifyPercent(id, HEALTH_BONUS);
//        stats.getSensorProfile().modifyMult(id, PROFILE_MULT);
//        stats.getCombatEngineRepairTimeMult().modifyMult(id, 1f - REPAIR_BONUS * 0.01f);
//        stats.getCombatWeaponRepairTimeMult().modifyMult(id, 1f - REPAIR_BONUS * 0.01f);
//        stats.getMaxBurnLevel().modifyFlat(id, BURN_LEVEL_BONUS);
//        stats.getSuppliesToRecover().modifyPercent(id, DP_REDUCTION);

        boolean sMod = isSMod(stats);
        if (sMod) {
            if(!stats.getVariant().hasHullMod("witch_emblem")) {
                stats.getMinCrewMod().modifyMult(id,SUPPLY_USE_MULT);
                stats.getSuppliesPerMonth().modifyPercent(id, CREW_PERCENT);
            }
            stats.getHullBonus().modifyMult(id, HULL_BONUS);
        }
    }
    @Override
    public String getSModDescriptionParam(int index, HullSize hullSize, ShipAPI ship) {
        if (index == 0) return "100"  + "%";
        if (index == 1) return "50" + "%";
        if (index == 2) return "25" + "%";
        return null;
    }

    @Override
    public boolean isSModEffectAPenalty() {
        return true;
    }
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        Iterator i$ = BLOCKED_HULLMODS.iterator();

        while(i$.hasNext()) {
            String tmp = (String)i$.next();
            if (ship.getVariant().getHullMods().contains(tmp)) {
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "crimsonmodificationrra");
            }
        }
    }

    public boolean isApplicableToShip(ShipAPI ship) {
        if (ship.getVariant().getHullSize() == HullSize.FRIGATE) return false;
        if (ship.getVariant().getHullSize() == HullSize.DESTROYER) return false;
        if (Misc.isAutomated(ship)) return false;
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
        if (ship.getVariant().getHullSize() == HullSize.FRIGATE) {
            return "Cannot be installed on frigates";
        }
        if (ship.getVariant().getHullSize() == HullSize.DESTROYER) {
            return "Cannot be installed on destroyers";
        }
        if (Misc.isAutomated(ship)){
            return "Cannot be installed on automated ships";
        }
        if (ship.getParentStation() != null){
            return "Cannot be installed on modules";
        }
        if(!ship.getVariant().hasHullMod("ringba")){
            if (!ship.getVariant().hasHullMod("ringfog")){
                if (!ship.getVariant().hasHullMod("ringie")){
                    if (!ship.getVariant().hasHullMod("iebaptism")){
                        if (!ship.getVariant().hasHullMod("starssynchronous")){
                            if (!ship.getVariant().hasHullMod("paleandcrimson")){
                                return "Ship does not have any Ring Spirit interface";
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
        static {
        BLOCKED_HULLMODS.add("unstable_injector");
        BLOCKED_HULLMODS.add("hardened_subsystems");
        BLOCKED_HULLMODS.add("crimsonmodificationie");
        BLOCKED_HULLMODS.add("crimsonmodificationdust");
        BLOCKED_HULLMODS.add("crimsonmodificationcross");
        BLOCKED_HULLMODS.add("crimsonmodificationssoul");
        BLOCKED_HULLMODS.add("crimsonmodificationelf");
    }

}
