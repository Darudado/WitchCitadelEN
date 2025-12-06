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
import com.fs.starfarer.api.util.Misc;

import static com.fs.starfarer.api.impl.campaign.ids.Stats.PHASE_CLOAK_FLUX_LEVEL_FOR_MIN_SPEED_MOD;


public class DR_ToshiUe {
    public static float TIME_BONUS = 15.0F;
    public static float DAMAGE_BONUS = 50.0F;
    public static float PD_BONUS = 15.0F;

    public DR_ToshiUe() {
    }

    public static class Toshi implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchToshi11";

        public Toshi(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {

            if (ship.isAlive()) {
                if(ship.getHitpoints() <= ship.getMaxHitpoints() * 0.5F){
                    stats.getTimeMult().modifyPercent(id, TIME_BONUS);
                }
                if(!(ship.getHitpoints() <= ship.getMaxHitpoints() * 0.5F)){
                    stats.getTimeMult().unmodify(id);
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
            stats.getDamageToFighters().modifyPercent(id, DAMAGE_BONUS);
            stats.getDamageToMissiles().modifyPercent(id, DAMAGE_BONUS);

            stats.getNonBeamPDWeaponRangeBonus().modifyPercent(id, PD_BONUS);
            stats.getBeamPDWeaponRangeBonus().modifyPercent(id, PD_BONUS);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getDamageToMissiles().unmodifyPercent(id);
            stats.getDamageToFighters().unmodifyPercent(id);

            stats.getNonBeamPDWeaponRangeBonus().unmodifyPercent(id);
            stats.getBeamPDWeaponRangeBonus().unmodifyPercent(id);
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
            info.addPara(" %s damage dealt to %s", pad, Misc.getPositiveHighlightColor(), new String[]{"+50%", "fighters and missiles"});
            info.addPara(" %s point defense weapon range", padS, Misc.getPositiveHighlightColor(), new String[]{"+15%"});
        }
    }
    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new Toshi(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(Toshi.class);
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
