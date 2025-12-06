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
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


public class DR_WitchRRA {
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

    public DR_WitchRRA() {
    }


    public static class DarkReal implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchRRA1";
        protected String id233 = "WitchRRA2";
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

    public static class DR_RRA implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchRRA3";
        protected String id2 = "WitchRRA4";
        protected String id3 = "WitchRRA5";
        private final IntervalUtil SS = new IntervalUtil(0.1F, 0.1F);
        private final IntervalUtil TS = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;
        State sAll = new State();

        public DR_RRA(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            this.TS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            CombatEngineAPI engine = Global.getCombatEngine();
            if (this.ship.getFullTimeDeployed() < 1f){
                sAll.num = 0;
            }

            if (ship.isAlive()) {
                if(ship.getHitpoints() < ship.getMaxHitpoints()){
                    if (this.SS.intervalElapsed()) {
                        ship.setHitpoints((ship.getHitpoints() + (ship.getMaxHitpoints() * 0.0005F)));
                    }
                }

                if (ship.getHitpoints() >= ship.getMaxHitpoints() * 0.75F){
                    stats.getBallisticWeaponRangeBonus().modifyPercent(id2, 25F);
                    stats.getEnergyWeaponRangeBonus().modifyPercent(id2, 25F);
                } else {
                    stats.getBallisticWeaponRangeBonus().unmodify(id2);
                    stats.getEnergyWeaponRangeBonus().unmodify(id2);
                }

                if (ship.getHitpoints() <= ship.getMaxHitpoints() * 0.5F){
                    stats.getHullDamageTakenMult().modifyMult(id2, 0.8F);
                    stats.getArmorDamageTakenMult().modifyMult(id2, 0.8F);
                } else {
                    stats.getHullDamageTakenMult().unmodify(id2);
                    stats.getArmorDamageTakenMult().unmodify(id2);
                }

                if (this.ship.getHitpoints() <= this.ship.getMaxHitpoints() * 0.5F) {
                    ++sAll.num;
                }
                if(sAll.num == 1){
                    engine.addFloatingText(this.ship.getLocation(), "Damage Block Triggered",50F, WthC_ColorData.DEEP_BLUE, this.ship, 5F,5F);
                    ++sAll.num;
                    ++sAll.num3;
                }
                if(sAll.num3 == 1){
                    this.stats.getShieldDamageTakenMult().modifyMult(this.id3, 0.2F);
                    this.stats.getArmorDamageTakenMult().modifyMult(this.id3, 0.2F);
                    this.stats.getHullDamageTakenMult().modifyMult(this.id3, 0.2F);
                    if (this.TS.intervalElapsed()) {
                        ++sAll.num2;
                    }
                    if(sAll.num2 == 5){
                        ++sAll.num3;
                    }
                }
                if(sAll.num3 == 2){
                    this.stats.getShieldDamageTakenMult().unmodify(this.id3);
                    this.stats.getArmorDamageTakenMult().unmodify(this.id3);
                    this.stats.getHullDamageTakenMult().unmodify(this.id3);
                    sAll.num2 = 0;
                    sAll.num3 = 0;
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
            info.addPara("All ships have more built-in hullmods and cannot be salvaged", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara("When player's deployment points exceed vanilla limit in combat, all ships gain additional bonuses", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
        }
    }
    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new DR_RRA(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(DR_RRA.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getDamageToFighters().modifyPercent(id, 50f);
            stats.getDamageToMissiles().modifyPercent(id, 50f);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getDamageToFighters().unmodify(id);
            stats.getDamageToMissiles().unmodify(id);
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

            info.addPara("All ships in combat additionally gain %s :", pad, Misc.getHighlightColor(), new String[]{"following modifiers"});
            info.addPara("    %s damage to fighters and missiles", pads, Misc.getPositiveHighlightColor(), new String[]{"+50%"});

            info.addPara("When ship's hull integrity in combat is %s of maximum %s :", pad, Misc.getHighlightColor(), new String[]{"<","100%"});
            info.addPara("   Regenerates hull at %s per second", pads, Misc.getPositiveHighlightColor(), new String[]{"0.5%"});

            info.addPara("When ship's hull integrity in combat is %s of maximum %s :", pad, Misc.getHighlightColor(), new String[]{">=","75%"});
            info.addPara("    %s non-missile weapon range", pads, Misc.getPositiveHighlightColor(), new String[]{"+25%"});

            info.addPara("When ship's hull integrity in combat is %s of maximum %s :", pad, Misc.getHighlightColor(), new String[]{"<=","50%"});
            info.addPara("    %s armor and hull damage reduction", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});

            info.addPara("When ship's hull integrity first drops below %s in combat:", pad, Misc.getHighlightColor(), new String[]{"50%"});
            info.addPara("   Gains massive damage reduction for %s seconds", pads, Misc.getPositiveHighlightColor(), new String[]{"5"});
        }
    }
}
