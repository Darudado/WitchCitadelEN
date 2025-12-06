package data.hullmods;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.combat.ShieldAPI.ShieldType;
import data.scripts.util.MagicIncompatibleHullmods;
import data.scripts.util.MagicRender;
import data.util.WthC_Util;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import data.util.WthC_ColorData;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;


import java.util.*;

// Some formats from Polaris
public class WthC_CrimsonModification_IE extends BaseHullMod {


    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static float PIERCE_MULT = 0.5f;
    public static float SHIELD_BONUS = 15f;
    public static final float FLUX_COIL_MULT = 1.15f;
    public static final float FLUX_DISSIPATION_MULT = 1.15f;
    public static final float SHIELD_BONUS_UNFOLD = 75f;
    public static float ZERO_FLUX_LEVEL = 5.0F;
    public static final float VENT_RATE_BONUS = -25f;
    public static float MANEUVER_BONUS = -25f;
    public static float SHIELD_UPKEEP_BONUS = -50f;
    public static float CREW_PERCENT = 100.0F;
    public static final float SUPPLY_USE_MULT = 1.5F;
    public static float SMOD_SOFT_FLUX_CONVERSION2 = 0.5f;
    private final IntervalUtil TX = new IntervalUtil(0.05F, 0.05F);
    private boolean runOnce = false;
    private Vector2f VEL = null;
    private final IntervalUtil GlowSS = new IntervalUtil(0.3F, 0.5F);


    private static Map mag1 = new HashMap();
    static {
        mag1.put(HullSize.CRUISER, -20f);
        mag1.put(HullSize.CAPITAL_SHIP, -15f);
    }

    private static final Set<String> BLOCKED_HULLMODS = new HashSet();

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        int count = 0;
        float pad = 10f;
        float padL = 20f;
        float padS = 2f;
        if (ship == null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Rika, the Primordial Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
            tooltip.addPara("This witch is an energy manipulation expert who favors static combat, excelling at standing still and achieving victory through defense that makes enemies sigh", WthC_ColorData.IE_WHITE, pad);
            tooltip.addPara("Therefore, this large-scale modification sacrifices some maneuverability to completely fortify the ship, bringing out more advantages in energy management and shield defense", WthC_ColorData.LIGHT_BLUE, padS);
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
        tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Rika, the Primordial Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("This witch is an energy manipulation expert who favors static combat, excelling at standing still and achieving victory through defense that makes enemies sigh", WthC_ColorData.IE_WHITE, pad);
        tooltip.addPara("Therefore, this large-scale modification sacrifices some maneuverability to completely fortify the ship, bringing out more advantages in energy management and shield defense", WthC_ColorData.LIGHT_BLUE, padS);
        tooltip.addPara("Even a small stone can take a life?", WthC_ColorData.LIGHT_CRIMSON, pad);

        if(count >= 1){
            tooltip.addPara("                                                       "+"So... they always find ways to block them", WthC_ColorData.B_WHITE, padS);
        }
        tooltip.addSectionHeading("Ship Modification Details", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("CrimsonModification_IE_TEXT0"), pad, Misc.getPositiveHighlightColor(), "#");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXT1"), padS, Misc.getPositiveHighlightColor(), "#", "15%");
        }
        if(count == 1){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXTEX1"), padS, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#", "15%");
        if(count == 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXT3"), padS, WthC_ColorData.HIGH_BLUE, "#", "50%");
        }
        tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXT4"), padS, Misc.getPositiveHighlightColor(), "#", "75%");
        tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXT5"), padS, Misc.getPositiveHighlightColor(), "#", "360");
        tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXT6"), padS, Misc.getPositiveHighlightColor(), "#", "5%");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXT7"), padS, Misc.getPositiveHighlightColor(), "#", "250%");
        }
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXTEX7"), padS, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addPara(" %s " + strings.get("CrimsonModification_IE_TEXTd0"), pad, Misc.getNegativeHighlightColor(), "#");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXTd1"), padS, Misc.getNegativeHighlightColor(), "#");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXTd2"), padS, Misc.getNegativeHighlightColor(), "#", "33%");
        }
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXTdEX1"), padS, WthC_ColorData.HIGH_BLUE, "#");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXTdEX2"), padS, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXTd5"), padS, Misc.getNegativeHighlightColor(), "#", "20","15");
        tooltip.addPara("     %s " + strings.get("CrimsonModification_IE_TEXTd3"), padS, Misc.getNegativeHighlightColor(), "#", "25%");

        if(count >= 1) {
            tooltip.addPara(" %s " + strings.get("CrimsonModification_EX"), pad, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addSectionHeading("Restrictions and Conflicts", Alignment.TMID, 10.0F);

        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT0"), pad, Misc.getHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT1"), pad, Misc.getNegativeHighlightColor(), "#","1");
        tooltip.addPara("%s " + strings.get("CrimsonModification_EX233"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT11"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT2"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_IE_TEXTn1"), padS, Misc.getHighlightColor(), "#");

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


        stats.getShieldDamageTakenMult().modifyMult(id, 1f - SHIELD_BONUS * 0.01f);
        stats.getShieldUnfoldRateMult().modifyPercent(id, SHIELD_BONUS_UNFOLD);
        stats.getDynamic().getStat(Stats.SHIELD_PIERCED_MULT).modifyMult(id, PIERCE_MULT);
        stats.getZeroFluxMinimumFluxLevel().modifyFlat(id, ZERO_FLUX_LEVEL * 0.01f);
        stats.getAcceleration().modifyPercent(id, MANEUVER_BONUS * 2f);
        stats.getDeceleration().modifyPercent(id, MANEUVER_BONUS);
        stats.getTurnAcceleration().modifyPercent(id, MANEUVER_BONUS * 2f);
        stats.getMaxTurnRate().modifyPercent(id, MANEUVER_BONUS);
        stats.getMaxSpeed().modifyFlat(id, (Float) mag1.get(hullSize));

        if(count == 0){
            stats.getVentRateMult().modifyPercent(id, VENT_RATE_BONUS);
            stats.getFluxCapacity().modifyMult(id, FLUX_COIL_MULT);
            stats.getFluxDissipation().modifyMult(id, FLUX_DISSIPATION_MULT);
        }
        if(count >= 1) {
            stats.getShieldSoftFluxConversion().modifyFlat(id, SMOD_SOFT_FLUX_CONVERSION2);
        }

        boolean sMod = isSMod(stats);
        if (sMod) {
            if(!stats.getVariant().hasHullMod("witch_emblem")) {
                stats.getMinCrewMod().modifyMult(id,SUPPLY_USE_MULT);
                stats.getSuppliesPerMonth().modifyPercent(id, CREW_PERCENT);
            }
            stats.getShieldUpkeepMult().modifyMult(id, 1f - SHIELD_UPKEEP_BONUS * 0.01f);
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
                        Misc.scaleAlpha(WthC_ColorData.IE_WHITE, 1f), //Image color
                        true, //Fixed relative position
                        0.5f,  //Fade in time
                        0.5f,  //Duration
                        0.4f  //Fade out time
                );
            }
        }

        //Afterimage effect
        /*this.TX.advance(amount);
        if (!this.runOnce) {
            this.runOnce = true;
        }

        VEL = ship.getVelocity().negate(VEL);
        Vector2f RENDER_OFFSET = ship.getRenderOffset();
        SpriteAPI effect = Global.getSettings().getSprite(ship.getHullSpec().getSpriteName());
        Vector2f size = new Vector2f(ship.getSpriteAPI().getWidth(), ship.getSpriteAPI().getHeight());



        if (this.TX.intervalElapsed()) {
                    MagicRender.objectspace(
                            effect, //Image
                            ship, //Render object
                            RENDER_OFFSET, //Offset
                            VEL, //Offset speed
                            size, //Size
                            ship.getRenderOffset(), //Scale
                            ship.getFacing() + 180f, //Angle
                            0f, //Rotation rate
                            false, //Fixed relative position
                            Misc.scaleAlpha(WthC_ColorData.IE_WHITE_D, 1f), //Image color
                            0f,  //Jitter range
                            0f,  //Jitter frequency
                            0f,  //Flash range
                            0f,  //Flash frequency
                            0f,  //Flash and jitter delay
                            0f,  //Fade in time
                            0.05f,  //Duration
                            0.25f,  //Fade out time
                            true,  //Fade out after death
                            CombatEngineLayers.BELOW_SHIPS_LAYER,  //Layer
                            GL11.GL_SRC_ALPHA, GL11.GL_ONE  //Layer blend mode
                    );
        }*/

        int count = 0;
        if (ship.getVariant().hasHullMod("ringreverse")) {
            count = 0;
        }

        if (!ship.getVariant().hasHullMod("ringreverse")) {
            if (!ship.getVariant().hasHullMod("ringinterferecross")) {
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

        if (!ship.getVariant().hasHullMod("ringreverse")) {
            if (count == 0) {
                if (ship.getVariant().hasHullMod("ringinterferecross")) {
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

        super.advanceInCombat(ship, amount);
        if (ship.isAlive()) {
            if(!(ship.isStationModule())){
                if (ship.getShield() != null){
                    if (!(ship.getShield().getArc() == 360)) {
                        ship.getShield().setArc(360);
                    }
                }
            }
            if (count >= 1) {
                ship.blockCommandForOneFrame(ShipCommand.VENT_FLUX);
            }
            ship.blockCommandForOneFrame(ShipCommand.TOGGLE_SHIELD_OR_PHASE_CLOAK);
            String id = "shield_always_on";
            if (ship.getSystem() != null){
                if (ship.getShield() != null){
                    if(!ship.getSystem().isActive()){
                        if (!ship.getShield().isOn()) {
                            ship.getShield().toggleOn();
                        }

                        if (ship.getFluxTracker().isOverloaded()) {
                            if (count == 0) {
                                ship.getMutableStats().getFluxDissipation().modifyMult(id, 2.5F);
                            }
                        } else {
                            ship.getMutableStats().getFluxDissipation().modifyMult(id, 1.0F);
                        }
                    }
                }
            }
        }
    }

    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        ShieldAPI shield = ship.getShield();
        if (shield != null) {
            shield.setType(ShieldType.FRONT);
        }
        Iterator i$ = BLOCKED_HULLMODS.iterator();

        while(i$.hasNext()) {
            String tmp = (String)i$.next();
            if (ship.getVariant().getHullMods().contains(tmp)) {
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "crimsonmodificationie");
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
        return ship != null && ship.getShield() != null;
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
        return "This ship has no shield";
    }


    static {
        BLOCKED_HULLMODS.add("unstable_injector");
        BLOCKED_HULLMODS.add("safetyoverrides");
        BLOCKED_HULLMODS.add("stabilizedshieldemitter");
        BLOCKED_HULLMODS.add("fluxbreakers");
        BLOCKED_HULLMODS.add("advancedshieldemitter");
        BLOCKED_HULLMODS.add("extendedshieldemitter");
        BLOCKED_HULLMODS.add("frontemitter");
        BLOCKED_HULLMODS.add("shield_shunt");
        BLOCKED_HULLMODS.add("hardenedshieldemitter");
        BLOCKED_HULLMODS.add("adaptiveshields");
        BLOCKED_HULLMODS.add("crimsonmodificationrra");
        BLOCKED_HULLMODS.add("crimsonmodificationdust");
        BLOCKED_HULLMODS.add("crimsonmodificationcross");
        BLOCKED_HULLMODS.add("crimsonmodificationssoul");
        BLOCKED_HULLMODS.add("crimsonmodificationelf");
    }

}
