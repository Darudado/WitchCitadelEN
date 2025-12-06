package data.skills;


import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;


public class Overmind_Doctrine {
    public static float DAMAGE_TAKEN_BONUS = 0.8F;
    public static float PEAK_TIME_BONUS = 25.0F;

    public Overmind_Doctrine() {
    }

    public static boolean isCapitalAndOfficer(MutableShipStatsAPI stats) {
        if (stats.getEntity() instanceof ShipAPI) {
            ShipAPI ship = (ShipAPI)stats.getEntity();
            if (!ship.isCapital()) {
                return false;
            } else {
                return !ship.getCaptain().isDefault();
            }
        } else {
            FleetMemberAPI member = stats.getFleetMember();
            if (member == null) {
                return false;
            } else if (!member.isCapital()) {
                return false;
            } else {
                return !member.getCaptain().isDefault();
            }
        }
    }
    public static boolean isCapitalAndOfficer2(ShipAPI ship) {
        if (ship.getMutableStats().getEntity() instanceof ShipAPI) {
            if (!ship.isCapital()) {
                return false;
            } else {
                return !ship.getCaptain().isDefault();
            }
        } else {
            FleetMemberAPI member = ship.getMutableStats().getFleetMember();
            if (member == null) {
                return false;
            } else if (!member.isCapital()) {
                return false;
            } else {
                return !member.getCaptain().isDefault();
            }
        }
    }
    public static class OverMind implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchOD1";
        private final IntervalUtil SS = new IntervalUtil(0.1F, 0.1F);
        private boolean runOnce = false;

        public OverMind(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }

            if (ship.isAlive()) {
                if(ship.getHitpoints() <= ship.getMaxHitpoints() * 0.5F){
                    stats.getMaxSpeed().modifyPercent(id, 20F);
                    stats.getHullDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_BONUS);
                    stats.getArmorDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_BONUS);
                }
                if(!(ship.getHitpoints() <= ship.getMaxHitpoints() * 0.5F)){
                    stats.getMaxSpeed().unmodify(id);
                    stats.getHullDamageTakenMult().unmodify(id);
                    stats.getArmorDamageTakenMult().unmodify(id);
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
            if (isCapitalAndOfficer(stats)) {
                float baseCost = stats.getSuppliesToRecover().getBaseValue();
                float reduction = Math.min(15f, baseCost * 0.25F);

                stats.getEnergyWeaponFluxCostMod().modifyPercent(id, -20F);
                stats.getBeamWeaponFluxCostMult().modifyPercent(id, -20F);
                stats.getMissileWeaponFluxCostMod().modifyPercent(id, -20F);
                stats.getDynamic().getMod("deployment_points_mod").modifyFlat(id, reduction);
                stats.getSuppliesPerMonth().modifyPercent(id, 25F);
                stats.getPeakCRDuration().modifyPercent(id, PEAK_TIME_BONUS);
            }
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getDamageToDestroyers().unmodifyPercent(id);
            stats.getDamageToCruisers().unmodifyPercent(id);
            stats.getDamageToFrigates().unmodifyPercent(id);
            stats.getDamageToFighters().unmodifyPercent(id);
            stats.getEnergyWeaponFluxCostMod().unmodifyPercent(id);
            stats.getBeamWeaponFluxCostMult().unmodifyPercent(id);
            stats.getMissileWeaponFluxCostMod().unmodifyPercent(id);
            stats.getPeakCRDuration().unmodifyPercent(id);
            stats.getDamageToMissiles().unmodifyPercent(id);
            stats.getSuppliesPerMonth().unmodifyPercent(id);
            stats.getDynamic().getMod("deployment_points_mod").unmodifyFlat(id);
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
            info.addPara(" %s ship deployment point cost (max increase %s points)", pad, Misc.getNegativeHighlightColor(), new String[]{"+25%","15"});
            info.addPara(" %s ship monthly supply consumption", pads, Misc.getNegativeHighlightColor(), new String[]{"+25%"});
            info.addPara(" %s ship peak time", pads, Misc.getPositiveHighlightColor(), new String[]{"+25%"});
            info.addPara(" %s flux generation for all ship weapons", pads, Misc.getPositiveHighlightColor(), new String[]{"-20%"});
        }
    }
    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            if (isCapitalAndOfficer2(ship)) {
                ship.addListener(new OverMind(ship));
            }
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(OverMind.class);
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
            info.addPara("When ship's hull integrity in combat is %s of maximum %s :", pad, Misc.getHighlightColor(), new String[]{"<=","50%"});
            info.addPara("    %s ship maximum speed", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
            info.addPara("    %s armor and hull damage taken", pads, Misc.getPositiveHighlightColor(), new String[]{"-20%"});
        }
    }
}
