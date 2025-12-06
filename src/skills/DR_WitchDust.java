package data.skills;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


public class DR_WitchDust {
    private static final class State {
        int num;
        int num2;
        int num3;
        float num5;
        private State() {
            num = 0;
            num2 = 0;
            num3 = 0;
            num5 = 0;
        }
    }

    public DR_WitchDust() {
    }


    public static class DarkReal implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchDust1";
        protected String id233 = "WitchDust2";
        State sAll = new State();


        public DarkReal(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            sAll.num5 = 0f;
            for (ShipAPI ship : Global.getCombatEngine().getShips()) {
                if (ship.getOwner() == Misc.OWNER_PLAYER) {
                    if (ship.getFleetMember() != null){
                        sAll.num5 += ship.getFleetMember().getDeploymentPointsCost();
                        // 这是玩家
                    }
                }
            }
            if (sAll.num5 > 240){
                float reduction = Math.min(0.05f, (1- (((sAll.num5 - 240) / 5) / 100)));
                stats.getMaxSpeed().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getFluxDissipation().modifyPercent(id233, (((sAll.num5 - 240) / 5) / 5));
                stats.getFluxCapacity().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getEnergyWeaponRangeBonus().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getBallisticWeaponRangeBonus().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getDamageToFrigates().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getDamageToDestroyers().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getDamageToCruisers().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getDamageToCapital().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getHullDamageTakenMult().modifyMult(id233,reduction);
                stats.getArmorDamageTakenMult().modifyMult(id233,reduction);
                stats.getShieldDamageTakenMult().modifyMult(id233,reduction);
            } else {
                stats.getMaxSpeed().unmodify(id233);
                stats.getFluxDissipation().unmodify(id233);
                stats.getFluxCapacity().unmodify(id233);
                stats.getEnergyWeaponRangeBonus().unmodify(id233);
                stats.getBallisticWeaponRangeBonus().unmodify(id233);
                stats.getDamageToFrigates().unmodify(id233);
                stats.getDamageToDestroyers().unmodify(id233);
                stats.getDamageToCruisers().unmodify(id233);
                stats.getDamageToCapital().unmodify(id233);
                stats.getHullDamageTakenMult().unmodify(id233);
                stats.getArmorDamageTakenMult().unmodify(id233);
                stats.getShieldDamageTakenMult().unmodify(id233);
            }

            if (ship.isAlive()) {
                if(ship.getCurrentCR() != 1F){
                    ship.setCurrentCR(1f);
                }

            }
        }

    }

    public static class DR_Dust implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchDust3";
        protected String id2 = "WitchDust4";
        private final IntervalUtil SS = new IntervalUtil(0.01F, 0.03F);
        private boolean runOnce = false;
        State sAll = new State();

        public DR_Dust(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }

            if (ship.isAlive()) {
                CombatEngineAPI engine = Global.getCombatEngine();

                if (ship.isAlive()) {
                    stats.getDamageToFrigates().modifyPercent(id, 33F);
                    stats.getDamageToDestroyers().modifyPercent(id, 33F);
                    stats.getDamageToCruisers().modifyPercent(id, 33F);
                    stats.getDamageToCapital().modifyPercent(id, 33F);
                    if (sAll.num == 0){
                        stats.getShieldDamageTakenMult().modifyMult(id2, 1.33F);
                        stats.getArmorDamageTakenMult().modifyMult(id2, 1.33F);
                        stats.getHullDamageTakenMult().modifyMult(id2, 1.33F);
                    }
                    if (this.ship.getFullTimeDeployed() < 1f){
                        sAll.num = 0;
                        sAll.num2 = 0;
                    }
                    if (this.ship.getHitpoints() < this.ship.getMaxHitpoints() * 0.25F && sAll.num == 0) {
                        ++sAll.num2;
                        ++sAll.num;
                    }
                    if(!(sAll.num2 == 0)){
                        if(sAll.num2 == 1){
                            engine.addFloatingText(this.ship.getLocation(), "Fate Burning", 50F, WthC_ColorData.MYSTERIOUS_PURPLE, this.ship, 5F, 5F);
                            ship.setJitter(this.ship, WthC_ColorData.MYSTERIOUS_PURPLE, 20f, 20, 20f);
                            ++sAll.num2;
                        }
                        this.stats.getFluxDissipation().modifyPercent(id2, 1000F);
                        this.stats.getHardFluxDissipationFraction().modifyFlat(id2, 0.75f);
                        if(!(this.ship.getHitpoints() >= this.ship.getMaxHitpoints())){
                            if (this.SS.intervalElapsed()) {
                                this.ship.setHitpoints(this.ship.getHitpoints() + this.ship.getMaxHitpoints() * 0.01F);
                            }
                        }
                        if (this.ship.getHitpoints() >= this.ship.getMaxHitpoints()) {
                            this.stats.getFluxDissipation().unmodify(id2);
                            this.stats.getHardFluxDissipationFraction().unmodify(id2);
                            this.stats.getShieldDamageTakenMult().unmodify(id2);
                            this.stats.getArmorDamageTakenMult().unmodify(id2);
                            this.stats.getHullDamageTakenMult().unmodify(id2);
                            sAll.num2 = 0;
                        }
                    }
                }

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
            stats.getDynamic().getMod("deployment_points_mod").modifyMult(id, 0.05f);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getDynamic().getMod("deployment_points_mod").unmodifyMult(id);
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
            info.addPara("All ships will no longer be restricted by deployment points", WthC_ColorData.MYSTERIOUS_PURPLE,pad);
            info.addPara("Has significantly higher overall officer levels", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara("All ships permanently lock into maximum combat readiness after entering battle", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara("All ships have more built-in modules and cannot be salvaged", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara("When player's deployment points exceed vanilla limit in combat, all ships gain additional bonuses", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
        }
    }
    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new DR_Dust(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(DR_Dust.class);
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

            info.addPara("All ships in combat additionally gain %s :", pad, Misc.getHighlightColor(), new String[]{"ships","following modifiers"});
            info.addPara("    %s damage to ships", pads, Misc.getPositiveHighlightColor(), new String[]{"+33%"});
            info.addPara("    %s damage taken", pads, Misc.getPositiveHighlightColor(), new String[]{"+33%"});

            info.addPara("When a ship's hull integrity first drops below %s :", pad, Misc.getHighlightColor(), new String[]{"25%"});
            info.addPara("   Will rapidly restore hull integrity to %s ", pads, Misc.getPositiveHighlightColor(), new String[]{"maximum"});
            info.addPara("   During recovery, the ship will %s and can vent hard flux with shields up ", pads, Misc.getPositiveHighlightColor(), new String[]{"rapidly vent flux"});
            info.addPara("   After recovery ends, permanently %s damage taken by the ship", pads, Misc.getPositiveHighlightColor(), new String[]{"-33%"});
        }
    }
}
