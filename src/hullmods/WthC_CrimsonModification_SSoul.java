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
public class WthC_CrimsonModification_SSoul extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static float TIME = 20f;
    public static float TIME2 = 25f;
    public static float CREW_PERCENT = 100.0F;
    public static final float SUPPLY_USE_MULT = 1.5F;

    public static float DAMAGE1 = -15f;
    public static float DAMAGE2 = 20f;
    public static final float MIN_ARMOR = 0.15f;
    public static final float MAX_ARMOR = -0.05f;
    public static final float HULL = -80f;
    public static final float HULL2 = -90f;
    public static final float ARMOR = 50f;

    public static final float PEAK_BONUS_PERCENT = -50f;
    public static final float REPAIR_BONUS = 50f;
    public static final float CASUALTY_REDUCTION = 50f;
    public static final float CASUALTY_REDUCTION2 = 75f;
    public static final int HARD_FLUX_DISSIPATION_PERCENT = 10;
    private static final Set<String> BLOCKED_HULLMODS = new HashSet();
    private final IntervalUtil GlowSS = new IntervalUtil(0.3F, 0.5F);
    private boolean runOnce = false;


    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        int count = 0;
        float pad = 10f;
        float padL = 20f;
        float padS = 2f;
        if (ship == null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Su, the Soul Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
            tooltip.addPara("This witch is the arbiter who transcends life and death, with a deep understanding of the birth and passing of life, time, and space", WthC_ColorData.SOUL_GREEN, pad);
            tooltip.addPara("Therefore, this large-scale modification interferes with the crew's perception of surrounding space-time, and strips away much of the hull structure to give the crew the supreme experience of being perpetually near death", WthC_ColorData.LIGHT_BLUE, padS);
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
        tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Su, the Soul Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("This witch is the arbiter who transcends life and death, with a deep understanding of the birth and passing of life, time, and space", WthC_ColorData.SOUL_GREEN, pad);
        tooltip.addPara("Therefore, this large-scale modification interferes with the crew's perception of surrounding space-time, and strips away much of the hull structure to give the crew the supreme experience of being perpetually near death", WthC_ColorData.LIGHT_BLUE, padS);
        tooltip.addPara("As they say... one must be pushed to the brink of death to be reborn", WthC_ColorData.LIGHT_CRIMSON, pad);

        if(count >= 1){
            tooltip.addPara("                                               "+"Even if you only have one life from beginning to end?", WthC_ColorData.B_WHITE, padS);
        }
        tooltip.addSectionHeading("Ship Modification Details", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("CrimsonModification_SSoul_TEXT0"), pad, Misc.getPositiveHighlightColor(), "#");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXT1"), padS, Misc.getPositiveHighlightColor(), "#", "50%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#", "20%");
        }
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXTEX1"), padS, WthC_ColorData.HIGH_BLUE, "#");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXT2"), padS, WthC_ColorData.HIGH_BLUE, "#", "25%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXTEX3"), padS, WthC_ColorData.HIGH_BLUE, "#", "10%");
        }



        tooltip.addPara(" %s " + strings.get("CrimsonModification_SSoul_TEXTd0"), pad, Misc.getNegativeHighlightColor(), "#");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXTd1"), padS, Misc.getNegativeHighlightColor(), "#", "80%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXTd2"), padS, Misc.getNegativeHighlightColor(), "#", "50%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXTd3"), padS, Misc.getNegativeHighlightColor(), "#", "50%");
        }
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXTd1"), padS, WthC_ColorData.HIGH_BLUE, "#", "90%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXTdEX2"), padS, WthC_ColorData.HIGH_BLUE, "#","50%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_SSoul_TEXTd3"), padS, WthC_ColorData.HIGH_BLUE, "#", "75%");
        }


        if(count >= 1) {
            tooltip.addPara(" %s " + strings.get("CrimsonModification_EX"), pad, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addSectionHeading("Restrictions and Conflicts", Alignment.TMID, 10.0F);

        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT0"), pad, Misc.getHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT1"), pad, Misc.getNegativeHighlightColor(), "#","1");
        tooltip.addPara("%s " + strings.get("CrimsonModification_EX233"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT11"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT3333"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXTSSoul"), padS, Misc.getNegativeHighlightColor(), "#");
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
        for (int i = 0; i < numMax; i = i + 1){
            Vector2f loc = MathUtils.getRandomPointInCircle(ship.getLocation(), 240f);
            MagicRender.battlespace(
                    effect2, //Image
                    loc, //Center coordinates
                    new Vector2f(0f,0f), //Image movement speed
                    size2, //Size
                    new Vector2f(0f,0f), //Image size change speed
                    -180f, //Angle
                    0f, //Rotation rate
                    Misc.scaleAlpha(WthC_ColorData.SOUL_GREEN, 1f), //Image color
                    true, //Fixed relative position
                    0.5f,  //Fade in time
                    0.5f,  //Duration
                    0.4f  //Fade out time
            );
        }
        }


        /*//Magic circle effect
        SpriteAPI effect = Global.getSettings().getSprite("fx", "IE");
        Vector2f size = new Vector2f(effect.getWidth(), effect.getHeight());
        if (ship.getFullTimeDeployed() < 1f) {
                    MagicRender.objectspace(
                            effect, //Image
                            ship, //Render object
                            WthC_Util.ZERO, //Offset
                            WthC_Util.ZERO, //Offset speed
                            size, //Size
                            ship.getRenderOffset(), //Scale
                            -180f, //Angle
                            33f, //Rotation rate
                            true, //Fixed relative position
                            Misc.scaleAlpha(WthC_ColorData.SOUL_GREEN, Math.min(1f, 1f)), //Image color
                            0.5f,  //Jitter range
                            1f,  //Jitter frequency
                            0f,  //Flash range
                            0f,  //Flash frequency
                            0f,  //Flash and jitter delay
                            1.5f,  //Fade in time
                            3f,  //Duration
                            1.5f,  //Fade out time
                            true,  //Fade out after death
                            CombatEngineLayers.BELOW_SHIPS_LAYER,  //Layer
                            GL11.GL_SRC_ALPHA, GL11.GL_ONE  //Layer blend mode
                    );
        }*/
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

        if(count == 0){
            stats.getTimeMult().modifyPercent(id, TIME);
            stats.getHullBonus().modifyPercent(id, HULL);
            stats.getArmorBonus().modifyPercent(id, ARMOR);
            stats.getCombatEngineRepairTimeMult().modifyPercent(id, REPAIR_BONUS);
            stats.getCombatWeaponRepairTimeMult().modifyPercent(id, REPAIR_BONUS);
            stats.getCrewLossMult().modifyPercent(id, CASUALTY_REDUCTION);
        }
        if(count >= 1) {
            stats.getTimeMult().modifyPercent(id, TIME2);
            stats.getHullBonus().modifyPercent(id, HULL2);
            stats.getHardFluxDissipationFraction().modifyFlat(id, (float)HARD_FLUX_DISSIPATION_PERCENT * 0.01f);
            stats.getCrewLossMult().modifyPercent(id, CASUALTY_REDUCTION2);
            stats.getPeakCRDuration().modifyPercent(id, PEAK_BONUS_PERCENT);
        }

        boolean sMod = isSMod(stats);
        if (sMod) {
            if(!stats.getVariant().hasHullMod("witch_emblem")) {
                stats.getMinCrewMod().modifyMult(id,SUPPLY_USE_MULT);
                stats.getSuppliesPerMonth().modifyPercent(id, CREW_PERCENT);
            }
            stats.getShieldDamageTakenMult().modifyPercent(id, DAMAGE2);
        }
    }

    @Override
    public String getSModDescriptionParam(int index, HullSize hullSize, ShipAPI ship) {
        if (index == 0) return "100"  + "%";
        if (index == 1) return "50" + "%";
        if (index == 2) return "20" + "%";
        return null;
    }

    @Override
    public boolean isSModEffectAPenalty() {
        return true;
    }



    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {


        ship.getMutableStats().getMinArmorFraction().modifyFlat(id, MIN_ARMOR);
        ship.getMutableStats().getMaxArmorDamageReduction().modifyFlat(id,MAX_ARMOR);
        Iterator i$ = BLOCKED_HULLMODS.iterator();



        while(i$.hasNext()) {
            String tmp = (String)i$.next();
            if (ship.getVariant().getHullMods().contains(tmp)) {
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "crimsonmodificationssoul");
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
        return ship != null && ship.getShield() != null;
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
        return "This ship has no shield";
    }
        static {
        BLOCKED_HULLMODS.add("shield_shunt");
        BLOCKED_HULLMODS.add("heavyarmor");
        BLOCKED_HULLMODS.add("insulatedengine");
        BLOCKED_HULLMODS.add("reinforcedhull");
        BLOCKED_HULLMODS.add("blast_doors");
        BLOCKED_HULLMODS.add("armoredweapons");
        BLOCKED_HULLMODS.add("ablative_armor");
        BLOCKED_HULLMODS.add("crimsonmodificationdust");
        BLOCKED_HULLMODS.add("crimsonmodificationie");
        BLOCKED_HULLMODS.add("crimsonmodificationrra");
        BLOCKED_HULLMODS.add("crimsonmodificationcross");
        BLOCKED_HULLMODS.add("crimsonmodificationelf");
    }

}
