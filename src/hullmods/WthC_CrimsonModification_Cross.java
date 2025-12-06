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
public class WthC_CrimsonModification_Cross extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static float ROFBonus = 66f;
    public static float ROFBonus2 = 75f;
    public static final float FLUX_Bonus = -66f;
    public static final float FLUX_Bonus2 = -75f;
    public static float CREW_PERCENT = 100.0F;
    public static float REGEN_BONUS = 100f;
    public static float AMMO_BONUS = 50f;
    public static float AMMO_BONUS2 = 150f;
    public static final float SUPPLY_USE_MULT = 1.5F;

    public static float DAMAGE = -66f;
    public static float DAMAGE2 = -75f;
    public static float RECOIL_BONUS = -33f;
    public static float WEAPONS_BONUS = -50f;
    public static final float DAMAGE_TO_MODULES_BONUS = 250.0F;
    public static final float SPEED_BONUS = -50f;

    private static Map mag1 = new HashMap();
    static {
        mag1.put(HullSize.CRUISER, 200f);
        mag1.put(HullSize.CAPITAL_SHIP, 300f);
    }
    private static Map mag2 = new HashMap();
    static {
        mag2.put(HullSize.CRUISER, 300f);
        mag2.put(HullSize.CAPITAL_SHIP, 400f);
    }
    private boolean runOnce = false;
    private final IntervalUtil GlowSS = new IntervalUtil(0.3F, 0.5F);

//    public static final float DAMAGE_TAKEN_MULT = 1.33f;

    private static final Set<String> BLOCKED_HULLMODS = new HashSet();

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        int count = 0;
        float pad = 10f;
        float padL = 20f;
        float padS = 2f;
        if (ship == null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Rosalia, the Cross Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
            tooltip.addPara("This witch is a preacher skilled in deception and illusions, excelling at spreading incredible fear and blasphemous personal will with grand gestures", WthC_ColorData.CROSS_GARY, pad);
            tooltip.addPara("Therefore, this large-scale modification greatly enhances firepower projection at the cost of weapon's effective damage, shifting focus from destruction to paralysis", WthC_ColorData.LIGHT_BLUE, padS);
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
        tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Rosalia, the Cross Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("This witch is a preacher skilled in deception and illusions, excelling at spreading incredible fear and blasphemous personal will with grand gestures", WthC_ColorData.CROSS_GARY, pad);
        tooltip.addPara("Therefore, this large-scale modification greatly enhances firepower projection at the cost of weapon's effective damage, shifting focus from destruction to paralysis", WthC_ColorData.LIGHT_BLUE, padS);
        tooltip.addPara("Tormenting the mind is more entertaining than destroying the body, isn't it?", WthC_ColorData.LIGHT_CRIMSON, pad);


        if(count >= 1){
            tooltip.addPara("                                                       "+"You never tire of this sort of thing...", WthC_ColorData.B_WHITE, padS);
        }
        tooltip.addSectionHeading("Ship Modification Details", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("CrimsonModification_Cross_TEXT0"), pad, Misc.getPositiveHighlightColor(), "#");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXT1"), padS, Misc.getPositiveHighlightColor(), "#", "66%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#", "66%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXT33"), padS, Misc.getPositiveHighlightColor(), "#", "200/300");
        }
        if(count == 1){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXT1"), padS, WthC_ColorData.HIGH_BLUE, "#","75%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXT2"), padS, WthC_ColorData.HIGH_BLUE, "#","75%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXT33"), padS, WthC_ColorData.HIGH_BLUE, "#", "300/400");
        }
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXT3"), padS, Misc.getPositiveHighlightColor(), "#", "50%");
        }
        if(count == 1){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXT3"), padS, WthC_ColorData.HIGH_BLUE, "#","150%");
        }
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXT4"), padS, Misc.getPositiveHighlightColor(), "#", "100%");
        }
        if(count == 1){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXTEX4"), padS, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXT6"), padS, Misc.getPositiveHighlightColor(), "#", "250%");
        tooltip.addPara(" %s " + strings.get("CrimsonModification_Cross_TEXTd0"), pad, Misc.getNegativeHighlightColor(), "#");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXTd1"), padS, Misc.getNegativeHighlightColor(), "#", "66%");
        }
        if(count == 1){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXTd1"), padS, WthC_ColorData.HIGH_BLUE, "#", "75%");
        }

        tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXTd2"), padS, Misc.getNegativeHighlightColor(), "#", "50%");
        tooltip.addPara("     %s " + strings.get("CrimsonModification_Cross_TEXTd3"), padS, Misc.getNegativeHighlightColor(), "#", "33%");

        if(count == 1) {
            tooltip.addPara(" %s " + strings.get("CrimsonModification_EX"), pad, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addSectionHeading("Restrictions and Conflicts", Alignment.TMID, 10.0F);
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT1"), pad, Misc.getNegativeHighlightColor(), "#","1");
        tooltip.addPara("%s " + strings.get("CrimsonModification_EX233"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT11"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXTCross"), padS, Misc.getNegativeHighlightColor(), "#");
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


        stats.getDamageToTargetEnginesMult().modifyPercent(id, DAMAGE_TO_MODULES_BONUS);
        stats.getDamageToTargetWeaponsMult().modifyPercent(id, DAMAGE_TO_MODULES_BONUS);
        stats.getProjectileSpeedMult().modifyPercent(id, SPEED_BONUS);

        if(count == 0){
            stats.getBallisticAmmoRegenMult().modifyPercent(id, AMMO_BONUS);
            stats.getEnergyAmmoRegenMult().modifyPercent(id, AMMO_BONUS);
            stats.getBallisticAmmoBonus().modifyPercent(id, REGEN_BONUS);
            stats.getEnergyAmmoBonus().modifyPercent(id, REGEN_BONUS);
            stats.getDamageToFrigates().modifyPercent(id,  DAMAGE);
            stats.getDamageToDestroyers().modifyPercent(id,  DAMAGE);
            stats.getDamageToCapital().modifyPercent(id,  DAMAGE);
            stats.getDamageToCruisers().modifyPercent(id,  DAMAGE);
            stats.getBallisticRoFMult().modifyPercent(id, ROFBonus);
            stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FLUX_Bonus);
            stats.getEnergyRoFMult().modifyPercent(id, ROFBonus);
            stats.getEnergyWeaponFluxCostMod().modifyPercent(id, FLUX_Bonus);
            stats.getBallisticWeaponRangeBonus().modifyFlat(id, (Float) mag1.get(hullSize));
            stats.getEnergyWeaponRangeBonus().modifyFlat(id, (Float) mag1.get(hullSize));
        }
        if(count >= 1){
            stats.getBallisticAmmoRegenMult().modifyPercent(id, AMMO_BONUS2);
            stats.getEnergyAmmoRegenMult().modifyPercent(id, AMMO_BONUS2);
            stats.getDamageToFrigates().modifyPercent(id,  DAMAGE2);
            stats.getDamageToDestroyers().modifyPercent(id,  DAMAGE2);
            stats.getDamageToCapital().modifyPercent(id,  DAMAGE2);
            stats.getDamageToCruisers().modifyPercent(id,  DAMAGE2);
            stats.getBallisticRoFMult().modifyPercent(id, ROFBonus2);
            stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FLUX_Bonus2);
            stats.getBallisticWeaponRangeBonus().modifyFlat(id, (Float) mag2.get(hullSize));
            stats.getEnergyRoFMult().modifyPercent(id, ROFBonus2);
            stats.getEnergyWeaponFluxCostMod().modifyPercent(id, FLUX_Bonus2);
            stats.getEnergyWeaponRangeBonus().modifyFlat(id, (Float) mag2.get(hullSize));
        }


        stats.getMaxRecoilMult().modifyMult(id, 1f - (0.01f * RECOIL_BONUS));
        stats.getRecoilPerShotMult().modifyMult(id, 1f - (0.01f * RECOIL_BONUS));
        stats.getRecoilDecayMult().modifyMult(id, 1f - (0.01f * RECOIL_BONUS));


        boolean sMod = isSMod(stats);
        if (sMod) {
            if(!stats.getVariant().hasHullMod("witch_emblem")) {
                stats.getMinCrewMod().modifyMult(id,SUPPLY_USE_MULT);
                stats.getSuppliesPerMonth().modifyPercent(id, CREW_PERCENT);
            }
            stats.getWeaponHealthBonus().modifyPercent(id, WEAPONS_BONUS);
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
                        Misc.scaleAlpha(WthC_ColorData.CROSS_GARY, 1f), //Image color
                        true, //Fixed relative position
                        0.5f,  //Fade in time
                        0.5f,  //Duration
                        0.4f  //Fade out time
                );
            }
        }
    }

    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        int count = 0;
        if (ship.getVariant().hasHullMod("ringreverse")){
            count = 0;
        }
        if (!ship.getVariant().hasHullMod("ringreverse")){
                if(!ship.getVariant().hasHullMod("ringinterferecross")) {
                    if (!ship.getVariant().hasHullMod("ringinterferedust")) {
                        if (!ship.getVariant().hasHullMod("ringinterfereie")) {
                            if (!ship.getVariant().hasHullMod("ringinterfererra")) {
                                if (!ship.getVariant().hasHullMod("ringinterferessoul")) {
                                    count = 0;
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
            }
        }


        Iterator i$ = BLOCKED_HULLMODS.iterator();



        while(i$.hasNext()) {
            String tmp = (String)i$.next();
            if (ship.getVariant().getHullMods().contains(tmp)) {
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "crimsonmodificationcross");
            }
        }
    }

    public boolean isApplicableToShip(ShipAPI ship) {
        if (ship.getVariant().getHullSize() == HullSize.FRIGATE) return false;
        if (ship.getVariant().getHullSize() == HullSize.DESTROYER) return false;
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
        BLOCKED_HULLMODS.add("magazines");
        BLOCKED_HULLMODS.add("armoredweapons");
        BLOCKED_HULLMODS.add("crimsonmodificationdust");
        BLOCKED_HULLMODS.add("crimsonmodificationie");
        BLOCKED_HULLMODS.add("crimsonmodificationrra");
        BLOCKED_HULLMODS.add("crimsonmodificationssoul");
        BLOCKED_HULLMODS.add("crimsonmodificationelf");
    }

}
