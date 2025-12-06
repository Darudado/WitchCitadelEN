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
public class WthC_CrimsonModification_Dust extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static final float FLUX_DISSIPATION_MULT = 1.5f;
    public static final float FLUX_COIL_MULT = 0.75f;
    public static final float FLUX_COIL_MULT2 = 0.9f;
    public static final float ROFBonus = 25f;
    public static final float PEAK_BONUS_PERCENT = 0.5f;
    public static float MANEUVER_BONUS = -50f;
    private static final float RANGE_THRESHOLD = 1200f;
    private static final float RANGE_MULT = 0.25f;

    public static float CREW_PERCENT = 100.0F;
    public static final float SUPPLY_USE_MULT = 1.5F;
    private boolean runOnce = false;
    private final IntervalUtil GlowSS = new IntervalUtil(0.3F, 0.5F);

//    public static float DAMAGE = 20f;
//    public static final float DAMAGE_TAKEN_MULT = 1.33f;


    private static Map mag1 = new HashMap();
    static {
        mag1.put(HullSize.CRUISER, 30f);
        mag1.put(HullSize.CAPITAL_SHIP, 20f);
    }
    private static Map mag2 = new HashMap();
    static {
        mag2.put(HullSize.CRUISER, 15f);
        mag2.put(HullSize.CAPITAL_SHIP, 10f);
    }
    private static Map mag22 = new HashMap();
    static {
        mag22.put(HullSize.CRUISER, 25f);
        mag22.put(HullSize.CAPITAL_SHIP, 20f);
    }
    private static Map mag3 = new HashMap();
    static {
        mag3.put(HullSize.CRUISER, 1.25f);
        mag3.put(HullSize.CAPITAL_SHIP, 1.33f);
    }
    private static Map mag32 = new HashMap();
    static {
        mag32.put(HullSize.CRUISER, 1.33f);
        mag32.put(HullSize.CAPITAL_SHIP, 1.5f);
    }
    private static Map mag4 = new HashMap();
    static {
        mag4.put(HullSize.CRUISER, 100f);
        mag4.put(HullSize.CAPITAL_SHIP, 200f);
    }

    private static final Set<String> BLOCKED_HULLMODS = new HashSet();

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        int count = 0;
        float pad = 10f;
        float padL = 20f;
        float padS = 2f;
        if (ship == null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Nezell, the Dust Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
            tooltip.addPara("This witch is the battlefield sovereign who overwhelms everything with pure power, excelling at charging through and destroying everything in her path, never concerned about her own safety", WthC_ColorData.DUST_RED, pad);
            tooltip.addPara("Therefore, this large-scale modification extremely enhances the ship's assault capabilities at the cost of almost completely abandoning defensive performance, and greatly increases ship control difficulty", WthC_ColorData.LIGHT_BLUE, padS);
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
        tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Nizer, the Dust Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("This witch is the battlefield sovereign who overwhelms everything with pure power, excelling at charging through and destroying everything in her path, never concerned about her own safety", WthC_ColorData.DUST_RED, pad);
        tooltip.addPara("Therefore, this large-scale modification extremely enhances the ship's assault capabilities at the cost of almost completely abandoning defensive performance, and greatly increases ship control difficulty", WthC_ColorData.LIGHT_BLUE, padS);
        tooltip.addPara("Now... you just need more weapons?", WthC_ColorData.LIGHT_CRIMSON, pad);

        if(count >= 1){
            tooltip.addPara("                                                       "+"People always crave more pure violence...", WthC_ColorData.B_WHITE, padS);
        }
        tooltip.addSectionHeading("Ship Modification Details", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("CrimsonModification_Dust_TEXT0"), pad, Misc.getPositiveHighlightColor(), "#");
        tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXT1"), padS, Misc.getPositiveHighlightColor(), "#", "50%");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#", "25");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXT3"), padS, Misc.getPositiveHighlightColor(), "#", "15%", "10%");
        }
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXTEX2"), padS, WthC_ColorData.HIGH_BLUE, "#");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXT3"), padS, WthC_ColorData.HIGH_BLUE, "#", "25%", "20%");
        }
        tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXT4"), padS, Misc.getPositiveHighlightColor(), "#", "30", "20");
        tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXT5"), padS, Misc.getPositiveHighlightColor(), "#", "100", "200");
        tooltip.addPara(" %s " + strings.get("CrimsonModification_Dust_TEXTd0"), pad, Misc.getNegativeHighlightColor(), "#");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXTd1"), padS, Misc.getNegativeHighlightColor(), "#", "25%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXTd4"), padS, Misc.getNegativeHighlightColor(), "#", "25%", "33%");
        }
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXTdEX1"), padS, WthC_ColorData.HIGH_BLUE, "#");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXTd4"), padS, WthC_ColorData.HIGH_BLUE, "#", "33%", "50%");
        }

        tooltip.addPara("     %s " + strings.get("CrimsonModification_Dust_TEXTd3"), padS, Misc.getNegativeHighlightColor(), "#", "50%");


        if(count >= 1) {
            tooltip.addPara(" %s " + strings.get("CrimsonModification_EX"), pad, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addSectionHeading("Restrictions and Conflicts", Alignment.TMID, 10.0F);

        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT1"), pad, Misc.getNegativeHighlightColor(), "#","1");
        tooltip.addPara("%s " + strings.get("CrimsonModification_EX233"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT11"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT222"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT3333"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_Dust_TEXTn"), padS, Misc.getNegativeHighlightColor(), "#","1200");
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

        stats.getFluxDissipation().modifyMult(id, FLUX_DISSIPATION_MULT);

        stats.getMaxSpeed().modifyFlat(id, (Float) mag1.get(hullSize));
        stats.getWeaponRangeThreshold().modifyFlat(id, RANGE_THRESHOLD);
        stats.getWeaponRangeMultPastThreshold().modifyMult(id, RANGE_MULT);
        stats.getBallisticWeaponRangeBonus().modifyFlat(id, (Float) mag3.get(hullSize));
        stats.getEnergyWeaponRangeBonus().modifyFlat(id, (Float) mag3.get(hullSize));
        stats.getDeceleration().modifyPercent(id, MANEUVER_BONUS);
        stats.getTurnAcceleration().modifyPercent(id, MANEUVER_BONUS * 2f);
        stats.getMaxTurnRate().modifyPercent(id, MANEUVER_BONUS);


        if(count == 0){
            stats.getShieldDamageTakenMult().modifyMult(id, (Float) mag3.get(hullSize));
            stats.getArmorDamageTakenMult().modifyMult(id, (Float) mag3.get(hullSize));
            stats.getHullDamageTakenMult().modifyMult(id, (Float) mag3.get(hullSize));
            stats.getDamageToFrigates().modifyPercent(id, (Float) mag2.get(hullSize));
            stats.getDamageToDestroyers().modifyPercent(id, (Float) mag2.get(hullSize));
            stats.getDamageToCapital().modifyPercent(id, (Float) mag2.get(hullSize));
            stats.getDamageToCruisers().modifyPercent(id, (Float) mag2.get(hullSize));
            stats.getBallisticRoFMult().modifyPercent(id, ROFBonus);
            stats.getEnergyRoFMult().modifyPercent(id, ROFBonus);
            stats.getFluxCapacity().modifyMult(id, FLUX_COIL_MULT);
        }
        if(count >= 1) {
            stats.getShieldDamageTakenMult().modifyMult(id, (Float) mag32.get(hullSize));
            stats.getArmorDamageTakenMult().modifyMult(id, (Float) mag32.get(hullSize));
            stats.getHullDamageTakenMult().modifyMult(id, (Float) mag32.get(hullSize));
            stats.getDamageToFrigates().modifyPercent(id, (Float) mag22.get(hullSize));
            stats.getDamageToDestroyers().modifyPercent(id, (Float) mag22.get(hullSize));
            stats.getDamageToCapital().modifyPercent(id, (Float) mag22.get(hullSize));
            stats.getDamageToCruisers().modifyPercent(id, (Float) mag22.get(hullSize));
        }

        boolean sMod = isSMod(stats);
        if (sMod) {
            if(!stats.getVariant().hasHullMod("witch_emblem")) {
                stats.getMinCrewMod().modifyMult(id,SUPPLY_USE_MULT);
                stats.getSuppliesPerMonth().modifyPercent(id, CREW_PERCENT);
            }
            stats.getPeakCRDuration().modifyMult(id, PEAK_BONUS_PERCENT);
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
                        Misc.scaleAlpha(WthC_ColorData.DUST_RED, 1f), //Image color
                        true, //Fixed relative position
                        0.5f,  //Fade in time
                        0.5f,  //Duration
                        0.4f  //Fade out time
                );
            }
        }
    }

    @Override
    public String getSModDescriptionParam(int index, HullSize hullSize, ShipAPI ship) {
        if (index == 0) return "100"  + "%";
        if (index == 1) return "50" + "%";
        if (index == 2) return "50" + "%";
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
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "crimsonmodificationdust");
            }
        }
    }

    public boolean isApplicableToShip(ShipAPI ship) {
        if (ship.getVariant().getHullSize() == HullSize.FRIGATE) return false;
        if (ship.getVariant().getHullSize() == HullSize.DESTROYER) return false;
        if(ship.getSystem() != null){
            if(ship.getSystem().getId().contentEquals("fortressshield")){
                return false;
            }
        }
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
        if (ship.getParentStation() != null) return false;
        return true;
    }

    public String getUnapplicableReason(ShipAPI ship) {
        if (ship.getVariant().getHullSize() == HullSize.FRIGATE) {
            return "Cannot be installed on frigates";
        }
        if (ship.getVariant().getHullSize() == HullSize.DESTROYER) {
            return "Cannot be installed on destroyers";
        }
        if(ship.getSystem() != null){
            if(ship.getSystem().getId().contentEquals("fortressshield")){
                return "Cannot be installed on ships with fortress shield";
            }
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
        BLOCKED_HULLMODS.add("safetyoverrides");
        BLOCKED_HULLMODS.add("auxiliarythrusters");
        BLOCKED_HULLMODS.add("hardenedshieldemitter");
        BLOCKED_HULLMODS.add("heavyarmor");
        BLOCKED_HULLMODS.add("fluxbreakers");
        BLOCKED_HULLMODS.add("crimsonmodificationie");
        BLOCKED_HULLMODS.add("crimsonmodificationrra");
        BLOCKED_HULLMODS.add("crimsonmodificationcross");
        BLOCKED_HULLMODS.add("crimsonmodificationssoul");
        BLOCKED_HULLMODS.add("crimsonmodificationelf");
    }

}
