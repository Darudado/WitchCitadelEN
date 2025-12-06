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
import com.fs.starfarer.api.util.Misc;
import data.skills.Overmind_Doctrine;
import data.util.WthC_ColorData;


public class DR_WeiLianChaoZai {

    public DR_WeiLianChaoZai() {
    }

    public static class DarkReal3 implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchWLCZ11";
        protected String id233 = "WitchWLCZ22";

        public DarkReal3(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            for (ShipAPI ship2 : Global.getCombatEngine().getShips()) {
                if (ship2.getOwner() == Misc.OWNER_PLAYER) {
                    ship2.getMutableStats().getSightRadiusMod().modifyMult(id, 0.5F);
                    // 这是玩家
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
            stats.getMissileAmmoBonus().modifyPercent(id, 300F);
            stats.getMaxSpeed().modifyFlat(id, 30F);
            stats.getAcceleration().modifyPercent(id, 50f);
            stats.getDeceleration().modifyPercent(id, 50f);
            stats.getTurnAcceleration().modifyFlat(id, 20f);
            stats.getTurnAcceleration().modifyPercent(id, 50f);
            stats.getMaxTurnRate().modifyFlat(id, 10f);
            stats.getMaxTurnRate().modifyPercent(id, 50f);
            stats.getFluxDissipation().modifyPercent(id, 20F);
            stats.getFluxCapacity().modifyPercent(id, 20F);
            stats.getPeakCRDuration().modifyMult(id, 2F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getMissileAmmoBonus().unmodify(id);
            stats.getMaxSpeed().unmodify(id);
            stats.getAcceleration().unmodify(id);
            stats.getDeceleration().unmodify(id);
            stats.getTurnAcceleration().unmodify(id);
            stats.getMaxTurnRate().unmodify(id);
            stats.getFluxDissipation().unmodify(id);
            stats.getFluxCapacity().unmodify(id);
            stats.getPeakCRDuration().unmodify(id);
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
            info.addPara(" %s missile weapon ammunition", pad, Misc.getPositiveHighlightColor(), new String[]{"+300%"});
            info.addPara(" %s ship maximum speed", pads, Misc.getPositiveHighlightColor(), new String[]{"+30"});
            info.addPara(" %s ship maneuverability", pads, Misc.getPositiveHighlightColor(), new String[]{"+50"});
            info.addPara(" %s ship flux capacity", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
            info.addPara(" %s ship flux dissipation", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
            info.addPara(" %s ship peak time duration", pads, Misc.getPositiveHighlightColor(), new String[]{"+100%"});
        }
    }
    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new DarkReal3(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListener(new DarkReal3(ship));
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

            info.addPara("When flagship is present:", Misc.getHighlightColor(), pad);
            info.addPara(" %s hostile ships sensor range", pads, Misc.getPositiveHighlightColor(), new String[]{"-50%"});

        }
    }
}
