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
public class WthC_CrimsonModification_Elf extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static final float F_DAMAGE_TAKEN_MULT = 0.75f;
    public static final float F_SPEED_BONUS = 25f;
    public static float PD_DAMAGE_BONUS = 50.0F;
    public static final float DAMAGE_TAKEN_MULT = 1.25f;
    public static final float DAMAGE_TAKEN_MULT2 = 1.1f;

    public static final float F_DAMAGE = 15f;
    public static final float F_FLUX_COST = 50f;
    public static final float DAMAGE = 0.66f;
    private static final float FIGHTER_RATE1 = 50f;
    private static final float FIGHTER_RATE2 = 100f;
    private static final float WEAPONS_ROF = -25f;
    public static float CREW_PERCENT = 100.0F;
    public static final float SUPPLY_USE_MULT = 1.5F;
    private boolean runOnce = false;
    private final IntervalUtil GlowSS = new IntervalUtil(0.3F, 0.5F);

    private static Map mag1 = new HashMap();
    static {
        mag1.put(HullSize.CRUISER, -20f);
        mag1.put(HullSize.CAPITAL_SHIP,-15f);
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
            tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Eden, the Spirit Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
            tooltip.addPara("This witch is a recluse who tames the avatars of old gods, once spent days and nights hunting beasts but now has put down her weapons and lives peacefully commanding her furry pets", WthC_ColorData.EIF_ORANGE, pad);
            tooltip.addPara("Therefore, this large-scale modification has made all the ship's fighters elite, and transferred part of the ship's weapon support to these little ones", WthC_ColorData.LIGHT_BLUE, padS);
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
        tooltip.addPara("This Crimson modification carries the complete stellar consciousness of Eden, the Spirit Witch", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("This witch is a recluse who tames the avatars of old gods, once spent days and nights hunting beasts but now has put down her weapons and lives peacefully commanding her furry pets", WthC_ColorData.EIF_ORANGE, pad);
        tooltip.addPara("Therefore, this large-scale modification has made all the ship's fighters elite, and transferred part of the ship's weapon support to these little ones", WthC_ColorData.LIGHT_BLUE, padS);
        tooltip.addPara("Most of the time even I don't want to do it myself...", WthC_ColorData.LIGHT_CRIMSON, pad);

        if(count >= 1){
            tooltip.addPara("                                               "+"...Why should one as noble as a primordial sovereign need to act personally?", WthC_ColorData.B_WHITE, padS);
        }
        tooltip.addSectionHeading("Ship Modification Details", Alignment.TMID, 10.0F);

        tooltip.addPara(" %s " + strings.get("CrimsonModification_Elf_TEXT0"), pad, Misc.getPositiveHighlightColor(), "#");
        tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXT1"), padS, Misc.getPositiveHighlightColor(), "#", "25%");
        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#", "50%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXT3"), padS, Misc.getPositiveHighlightColor(), "#", "1");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXT4"), padS, Misc.getPositiveHighlightColor(), "#", "25%");
        }
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXT2EX"), padS, WthC_ColorData.HIGH_BLUE, "#");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXT3EX"), padS, WthC_ColorData.HIGH_BLUE, "#", "15%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXT4EX"), padS, WthC_ColorData.HIGH_BLUE, "#", "50%");
        }

        tooltip.addPara(" %s " + strings.get("CrimsonModification_Elf_TEXTd0"), pad, Misc.getNegativeHighlightColor(), "#");

        if(count == 0){
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXTd1"), padS, Misc.getNegativeHighlightColor(), "#", "50%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXTd2"), padS, Misc.getNegativeHighlightColor(), "#", "25%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXTd3"), padS, Misc.getNegativeHighlightColor(), "#", "10%");
        }
        if(count >= 1) {
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXTd1"), padS, WthC_ColorData.HIGH_BLUE, "#", "100%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXTdEX2"), padS, WthC_ColorData.HIGH_BLUE, "#", "33%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXTd3"), padS, WthC_ColorData.HIGH_BLUE, "#", "25%");
            tooltip.addPara("     %s " + strings.get("CrimsonModification_Elf_TEXTd4"), padS, WthC_ColorData.HIGH_BLUE, "#");
        }



        if(count >= 1) {
            tooltip.addPara(" %s " + strings.get("CrimsonModification_EX"), pad, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addSectionHeading("Restrictions and Conflicts", Alignment.TMID, 10.0F);

        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT1"), pad, Misc.getNegativeHighlightColor(), "#","1");
        tooltip.addPara("%s " + strings.get("CrimsonModification_EX233"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT11"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("CrimsonModification_TEXT233"), padS, Misc.getNegativeHighlightColor(), "#");

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


        if(count == 0){
            stats.getDynamic().getMod("converted_hangar_mod").modifyFlat(id, 1f);
            stats.getFighterRefitTimeMult().modifyPercent(id, FIGHTER_RATE1);
            stats.getBallisticRoFMult().modifyPercent(id, WEAPONS_ROF);
            stats.getEnergyRoFMult().modifyPercent(id, WEAPONS_ROF);
            stats.getArmorDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT2);
            stats.getHullDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT2);
            stats.getShieldDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT2);
        }
        if(count >= 1) {
            stats.getFighterRefitTimeMult().modifyPercent(id, FIGHTER_RATE2);
            stats.getDamageToFrigates().modifyMult(id, DAMAGE);
            stats.getDamageToDestroyers().modifyMult(id, DAMAGE);
            stats.getDamageToCapital().modifyMult(id, DAMAGE);
            stats.getDamageToCruisers().modifyMult(id, DAMAGE);
            stats.getArmorDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT);
            stats.getHullDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT);
            stats.getShieldDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT);
            stats.getFighterWingRange().modifyMult(id, 0.0F);
        }

        boolean sMod = isSMod(stats);
        if (sMod) {
            if(!stats.getVariant().hasHullMod("witch_emblem")) {
                stats.getMinCrewMod().modifyMult(id,SUPPLY_USE_MULT);
                stats.getSuppliesPerMonth().modifyPercent(id, CREW_PERCENT);
            }
            stats.getMaxSpeed().modifyFlat(id, (Float) mag1.get(hullSize));
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
                        Misc.scaleAlpha(WthC_ColorData.EIF_ORANGE, 1f), //Image color
                        true, //Fixed relative position
                        0.5f,  //Fade in time
                        0.5f,  //Duration
                        0.4f  //Fade out time
                );
            }
        }
    }
    
    public void applyEffectsToFighterSpawnedByShip(ShipAPI fighter, ShipAPI ship, String id) {
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
        fighter.getMutableStats().getShieldDamageTakenMult().modifyMult(id, F_DAMAGE_TAKEN_MULT);
        fighter.getMutableStats().getArmorDamageTakenMult().modifyMult(id, F_DAMAGE_TAKEN_MULT);
        fighter.getMutableStats().getHullDamageTakenMult().modifyMult(id, F_DAMAGE_TAKEN_MULT);

        if(count == 0){
            fighter.getMutableStats().getDamageToFighters().modifyFlat(id, PD_DAMAGE_BONUS / 100.0F);
            fighter.getMutableStats().getDamageToMissiles().modifyFlat(id, PD_DAMAGE_BONUS / 100.0F);
            fighter.getMutableStats().getMaxSpeed().modifyPercent(id, F_SPEED_BONUS);
        }
        if(count == 1){
            float Range1 = ship.getFleetMember().getStats().getBallisticWeaponRangeBonus().getPercentMod();
            float Range2 = ship.getFleetMember().getStats().getEnergyWeaponRangeBonus().getPercentMod();
            float Range3 = ship.getFleetMember().getStats().getMissileWeaponRangeBonus().getPercentMod();
            fighter.getMutableStats().getBallisticWeaponRangeBonus().modifyPercent(id, Range1);
            fighter.getMutableStats().getEnergyWeaponRangeBonus().modifyPercent(id, Range2);
            fighter.getMutableStats().getMissileWeaponRangeBonus().modifyPercent(id, Range3);

            fighter.getMutableStats().getDamageToFrigates().modifyPercent(id, F_DAMAGE);
            fighter.getMutableStats().getDamageToDestroyers().modifyPercent(id, F_DAMAGE);
            fighter.getMutableStats().getDamageToCapital().modifyPercent(id, F_DAMAGE);
            fighter.getMutableStats().getDamageToCruisers().modifyPercent(id, F_DAMAGE);
            fighter.getMutableStats().getFluxDissipation().modifyPercent(id, F_FLUX_COST);
            if (fighter.getWing() != null && fighter.getWing().getSpec() != null && (fighter.getWing().getSpec().isRegularFighter() || fighter.getWing().getSpec().isAssault() || fighter.getWing().getSpec().isBomber() || fighter.getWing().getSpec().isInterceptor())) {
                fighter.addTag("stay_in_front_of_ship");
            }
        }


    }

    @Override
    public String getSModDescriptionParam(int index, HullSize hullSize, ShipAPI ship) {
        if (index == 0) return "100"  + "%";
        if (index == 1) return "50" + "%";
        if (index == 2) return "20/15";
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
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "crimsonmodificationelf");
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
        BLOCKED_HULLMODS.add("defensive_targeting_array");
        BLOCKED_HULLMODS.add("expanded_deck_crew");
        BLOCKED_HULLMODS.add("crimsonmodificationie");
        BLOCKED_HULLMODS.add("crimsonmodificationdust");
        BLOCKED_HULLMODS.add("crimsonmodificationcross");
        BLOCKED_HULLMODS.add("crimsonmodificationssoul");
        BLOCKED_HULLMODS.add("crimsonmodificationrra");
    }

}
