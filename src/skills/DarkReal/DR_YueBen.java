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
import data.util.WthC_ColorData;


public class DR_YueBen {
    public static final float FLUX_COIL_MULT = 1.25f;
    private static final class State {
        float num5;

        private State() {
            num5 = 0;
        }
    }
    public DR_YueBen() {
    }


    public static class DarkReal implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchYB11";
        protected String id233 = "WitchYB22";
        State sAll = new State();



        public DarkReal(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            sAll.num5 = 0f;
            for (ShipAPI ship : Global.getCombatEngine().getShips()) {
                if (ship.getVariant().getHullVariantId().contains("yoshura_prime_hate")){
                        sAll.num5 += 1;
                        // 这是玩家
                }
            }
            if (sAll.num5 >= 1){
                stats.getHullDamageTakenMult().modifyMult(id233,0.9F);
                stats.getArmorDamageTakenMult().modifyMult(id233,0.9F);
                stats.getShieldDamageTakenMult().modifyMult(id233,0.9F);
                stats.getTimeMult().modifyPercent(id233, 10F);
                stats.getEnergyRoFMult().modifyPercent(id233, 10F);
                stats.getBallisticRoFMult().modifyPercent(id233, 10F);
                stats.getMissileRoFMult().modifyPercent(id233, 10F);
            } else {
                stats.getHullDamageTakenMult().unmodify(id233);
                stats.getArmorDamageTakenMult().unmodify(id233);
                stats.getShieldDamageTakenMult().unmodify(id233);
                stats.getTimeMult().unmodify(id233);
                stats.getEnergyRoFMult().unmodify(id233);
                stats.getBallisticRoFMult().unmodify(id233);
                stats.getMissileRoFMult().unmodify(id233);
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
            info.addPara("Elite effects only activate when Joshua-class Dreadnought is present", WthC_ColorData.MYSTERIOUS_PURPLE,pad);
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

            info.addPara("All ships in combat gain %s :", pad, Misc.getHighlightColor(), new String[]{"following bonuses"});
            info.addPara("    %s ship damage taken", pads, Misc.getPositiveHighlightColor(), new String[]{"-10%"});
            info.addPara("    %s ship time flow rate", pads, Misc.getPositiveHighlightColor(), new String[]{"+10%"});
            info.addPara("    %s ship weapon rate of fire", pads, Misc.getPositiveHighlightColor(), new String[]{"+10%"});
            
        }
    }
}
