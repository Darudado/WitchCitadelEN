package data.skills.DarkReal;


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


public class DR_YueBen3 {
    public static final float FLUX_COIL_MULT = 1.25f;
    private static final class State {
        float num5;

        private State() {
            num5 = 0;
        }
    }
    public DR_YueBen3() {
    }

    public static class DarkReal implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchYB77";
        protected String id233 = "WitchYB88";
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
                if (ship.getVariant().hasHullMod("valk_bryncore")){
                    stats.getMissileWeaponRangeBonus().modifyFlat(id233, 100F);
                    stats.getEnergyWeaponRangeBonus().modifyFlat(id233, 100F);
                    stats.getBallisticWeaponRangeBonus().modifyFlat(id233, 100F);
                    stats.getFluxCapacity().modifyPercent(id233, 10F);
                    stats.getShieldDamageTakenMult().modifyMult(id233, 0.9F);
                    if(ship.getShield() != null){
                        if (!(ship.getShield().getArc() == 360)) {
                            ship.getShield().setArc(360);
                        }
                    }
                }
                if (ship.getVariant().hasHullMod("valk_microbryncore")){
                    stats.getMaxSpeed().modifyFlat(id233, 5F);
                    stats.getFighterRefitTimeMult().modifyPercent(id233, 20F);
                    stats.getFluxDissipation().modifyPercent(id233, 15F);
                }

            }

        }

    }

    public static class Level1A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1A() {
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
            float pads = 2.0F;
            info.addPara("Ships with Brunhilde Rotary Core gain %s :", pad, Misc.getHighlightColor(), new String[]{"following bonuses"});
            info.addPara("    %s all weapon range", pads, Misc.getPositiveHighlightColor(), new String[]{"+100"});
            info.addPara("    %s ship flux capacity", pads, Misc.getPositiveHighlightColor(), new String[]{"+10%"});
            info.addPara("    Shield angle locked at %s ", pads, Misc.getPositiveHighlightColor(), new String[]{"360 degrees"});
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
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
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
            info.addPara("Ships with Small Rotary Core gain %s :", pad, Misc.getHighlightColor(), new String[]{"following bonuses"});
            info.addPara("    %s ship maximum speed", pads, Misc.getPositiveHighlightColor(), new String[]{"+5"});
            info.addPara("    %s fighter refit rate", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
            info.addPara("    %s ship flux dissipation", pads, Misc.getPositiveHighlightColor(), new String[]{"+15%"});
        }
    }
}
