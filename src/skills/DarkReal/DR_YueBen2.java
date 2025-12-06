package data.skills.DarkReal;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


public class DR_YueBen2 {
    public static final float FLUX_COIL_MULT = 1.25f;
    private static final class State {
        float num5;

        private State() {
            num5 = 0;
        }
    }
    public DR_YueBen2() {
    }


    public static class DarkReal implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchYB33";
        protected String id233 = "WitchYB44";
        State sAll = new State();
        private final IntervalUtil SS = new IntervalUtil(0.1F, 0.1F);
        private boolean runOnce = false;


        public DarkReal(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }

            if (ship.isAlive()){
                    if (ship.getSystem().isActive()){
                        stats.getShieldDamageTakenMult().modifyMult(id233, 0.84F);
                        stats.getMaxSpeed().modifyFlat(id233, 16.6F);
                        stats.getFluxDissipation().modifyPercent(id233, 33F);
                        stats.getWeaponDamageTakenMult().modifyPercent(id233, 16.6F);
                        stats.getDeceleration().modifyPercent(id233, 66F);
                        stats.getTurnAcceleration().modifyPercent(id233, 66F * 2f);
                        stats.getMaxTurnRate().modifyPercent(id233, 66F);
                        if(!(ship.getHitpoints() >= ship.getMaxHitpoints())){
                            if (this.SS.intervalElapsed()) {
                                ship.setHitpoints((ship.getHitpoints() + (ship.getMaxHitpoints() * 0.0002F)));
                            }
                        }
                    } else {
                        stats.getShieldDamageTakenMult().unmodify(id233);
                        stats.getMaxSpeed().unmodify(id233);
                        stats.getFluxDissipation().unmodify(id233);
                        stats.getWeaponDamageTakenMult().unmodify(id233);
                        stats.getDeceleration().unmodify(id233);
                        stats.getTurnAcceleration().unmodify(id233);
                        stats.getMaxTurnRate().unmodify(id233);
                    }

                stats.getSystemCooldownBonus().modifyMult(id233, 0.7F);
                stats.getHullDamageTakenMult().modifyMult(id233,0.85F);
                stats.getArmorDamageTakenMult().modifyMult(id233,0.85F);
                stats.getShieldDamageTakenMult().modifyMult(id233,0.85F);
                stats.getOverloadTimeMod().modifyMult(id233, 0.6F);
            }

        }

    }

    public static class Level1A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new DarkReal(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(DarkReal.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {

        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {

        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
        }
    }
    public static class Level1B extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1B() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            info.addPara("Elite effects only activate when piloting Joshua-class Dreadnought", WthC_ColorData.MYSTERIOUS_PURPLE,pad);
        }
    }
    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getDynamic().getMod("deployment_points_mod").modifyMult(id, 0.0f);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getDynamic().getMod("deployment_points_mod").unmodifyMult(id);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
        }
    }
    public static class Level2B extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2B() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {

        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {

        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.initElite(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;

            info.addPara("Ships in combat gain %s :", pad, Misc.getHighlightColor(), new String[]{"following bonuses"});
            info.addPara("    %s all effects when Valiant Defense is active", pads, Misc.getPositiveHighlightColor(), new String[]{"+66%"});
            info.addPara("    %s Valiant Defense cooldown time", pads, Misc.getPositiveHighlightColor(), new String[]{"-30%"});
            info.addPara("    %s ship damage taken", pads, Misc.getPositiveHighlightColor(), new String[]{"-15%"});
            info.addPara("    %s ship overload duration", pads, Misc.getPositiveHighlightColor(), new String[]{"-40%"});
            info.addPara("    %s ship deployment requirements", pads, Misc.getPositiveHighlightColor(), new String[]{"-100%"});

            
        }
    }
}
