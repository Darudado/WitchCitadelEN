package data.skills.DarkReal;


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


public class DR_Umida {
    public static float Beam_resist = 0.6f;

    public DR_Umida() {
    }

    private static final class State{
        int num;
        private State(){
            num = 0;
        }
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

    public static class Umi implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchUmida11";
        private final IntervalUtil SS = new IntervalUtil(0.1F, 0.1F);
        private boolean runOnce = false;
        State CRflag = new State();

        public Umi(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }

            if (CRflag.num < 1){
                if(ship.getCurrentCR() < 0.95f){
                    ship.setCurrentCR(ship.getCurrentCR() + 0.05f);
                }
                CRflag.num++;
            }

            if (ship.isAlive()) {
                if(ship.getHitpoints() <= ship.getMaxHitpoints() * 0.5F){
                    if (this.SS.intervalElapsed()) {
                        ship.setHitpoints((ship.getHitpoints() + (ship.getMaxHitpoints() * 0.0003F)));
                    }
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
                stats.getBeamDamageTakenMult().modifyMult(id, Beam_resist);
            }
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getBeamDamageTakenMult().unmodifyMult(id);
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
            float padS = 2.0F;
            info.addPara("Applied sunscreen, %s beam damage taken", pad, Misc.getPositiveHighlightColor(), new String[]{"-40%"});
        }
    }
    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            if (isCapitalAndOfficer2(ship)) {
                ship.addListener(new Umi(ship));
            }
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(Umi.class);
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
            float padS = 2.0F;
            info.addPara("When ship's hull points are %s %s of maximum in combat:", pad, Misc.getHighlightColor(), new String[]{"<=","50%"});
            info.addPara("    %s ship timeflow", padS, Misc.getPositiveHighlightColor(), new String[]{"+15%"});

        }
    }
}
