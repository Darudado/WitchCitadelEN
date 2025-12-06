package data.skills.DarkReal;


import com.fs.starfarer.api.Global;
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
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


public class DR_OceansideRemodeling {

    public DR_OceansideRemodeling() {
    }
    public static boolean isBB(MutableShipStatsAPI stats) {
        if (stats.getEntity() instanceof ShipAPI) {
            ShipAPI ship = (ShipAPI)stats.getEntity();
            if (!ship.isCapital()) {
                return false;
            } else {
                return ship.isCapital();
            }
        } else {
            FleetMemberAPI member = stats.getFleetMember();
            if (member == null) {
                return false;
            } else if (!member.isCapital()) {
                return false;
            } else {
                return member.isCapital();
            }
        }
    }
    public static boolean isCL(MutableShipStatsAPI stats) {
        if (stats.getEntity() instanceof ShipAPI) {
            ShipAPI ship = (ShipAPI)stats.getEntity();
            if (!ship.isCruiser()) {
                return false;
            } else {
                return ship.isCruiser();
            }
        } else {
            FleetMemberAPI member = stats.getFleetMember();
            if (member == null) {
                return false;
            } else if (!member.isCruiser()) {
                return false;
            } else {
                return member.isCruiser();
            }
        }
    }

    public static class DarkReal implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchOR1";
        protected String id233 = "WitchOR2";



        public DarkReal(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            stats.getEccmChance().modifyFlat(id233, 1F);
            stats.getMissileAmmoRegenMult().modifyPercent(id233, 30F);
            stats.getMissileHealthBonus().modifyPercent(id233, 25F);
            stats.getMissileMaxSpeedBonus().modifyPercent(id233, 25F);
            stats.getMissileAccelerationBonus().modifyPercent(id233, 25F);
            stats.getMissileMaxTurnRateBonus().modifyPercent(id233, 25F);
            stats.getMissileTurnAccelerationBonus().modifyPercent(id233, 25F);
            stats.getCrewLossMult().modifyPercent(id233, 500F);
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
            float pads = 2.0F;
            info.addPara("All ships are immune to electronic warfare debuffs", WthC_ColorData.MYSTERIOUS_PURPLE,pad);
            info.addPara("All ships have more built-in modules and cannot be salvaged", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara(" %s missile weapon reload speed for all ships", pads, Misc.getPositiveHighlightColor(), new String[]{"+50%"});
            info.addPara(" %s missile speed, acceleration, and health for all ships", pads, Misc.getPositiveHighlightColor(), new String[]{"+25%"});
            info.addPara(" %s crew casualties in combat", pads, Misc.getNegativeHighlightColor(), new String[]{"+500%"});


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
            if (isBB(stats)) {
                stats.getDynamic().getMod("deployment_points_mod").modifyMult(id, 0.5f);
            } else if (isCL(stats)){
                stats.getDynamic().getMod("deployment_points_mod").modifyMult(id, 0.5f);
            } else {
                stats.getDynamic().getMod("deployment_points_mod").modifyMult(id, 0.05f);
            }

        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getDynamic().getMod("deployment_points_mod").unmodifyFlat(id);
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

            info.addPara("All frigates and destroyers:", Misc.getHighlightColor(), pad);
            info.addPara("   No longer restricted by deployment points",  Misc.getPositiveHighlightColor(), pads);
            info.addPara("All cruisers and capital ships:", Misc.getHighlightColor(), pad);
            info.addPara("    %s deployment requirements", pads, Misc.getPositiveHighlightColor(), new String[]{"-50%"});

        }
    }
}
