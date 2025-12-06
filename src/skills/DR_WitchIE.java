package data.skills;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


public class DR_WitchIE {
    public static final float FLUX_COIL_MULT = 1.25f;
    private static final class State {
        float num5;

        private State() {
            num5 = 0;
        }
    }
    public DR_WitchIE() {
    }


    public static class DarkReal implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchIE1";
        protected String id233 = "WitchIE2";
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

    public static class DR_IE implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchIE3";
        protected String id2 = "WitchIE4";


        public DR_IE(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {


            if (ship.isAlive()) {
                if(this.ship.getHardFluxLevel() > 0.25F){
                    stats.getBallisticRoFMult().modifyPercent(id2, 25F);
                    stats.getEnergyRoFMult().modifyPercent(id2, 25F);
                } else {
                    stats.getBallisticRoFMult().unmodify(id2);
                    stats.getEnergyRoFMult().unmodify(id2);
                }
                if(this.ship.getHardFluxLevel() > 0.5F){
                    stats.getBallisticWeaponFluxCostMod().modifyPercent(id2, -50F);
                    stats.getEnergyWeaponFluxCostMod().modifyPercent(id2, -50F);
                    stats.getShieldDamageTakenMult().modifyMult(id2, 0.85F);
                } else {
                    stats.getBallisticWeaponFluxCostMod().unmodify(id2);
                    stats.getEnergyWeaponFluxCostMod().unmodify(id2);
                    stats.getShieldDamageTakenMult().unmodify(id2);
                }
                if(this.ship.getHardFluxLevel() > 0.75F){
                    stats.getHardFluxDissipationFraction().modifyFlat(id2, 0.2F);
                } else {
                    stats.getHardFluxDissipationFraction().unmodify(id2);
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
            ship.addListener(new DR_IE(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(DR_IE.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getBeamWeaponRangeBonus().modifyFlat(id, 200F);
            stats.getFluxCapacity().modifyMult(id, FLUX_COIL_MULT);
            stats.getShieldUpkeepMult().modifyMult(id, 0.0F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getBeamWeaponRangeBonus().unmodify(id);
            stats.getFluxCapacity().unmodify(id);
            stats.getShieldUpkeepMult().unmodify(id);
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
            info.addPara("    %s beam weapon range", pads, Misc.getPositiveHighlightColor(), new String[]{"+200"});
            info.addPara("    %s final flux capacity", pads, Misc.getPositiveHighlightColor(), new String[]{"+25%"});
            info.addPara("    %s shield flux generation", pads, Misc.getPositiveHighlightColor(), new String[]{"-100%"});

            info.addPara("When ship's current %s is above %s :", pad, Misc.getHighlightColor(), new String[]{"hard flux level","25%"});
            info.addPara("    %s non-missile weapon rate of fire", pads, Misc.getPositiveHighlightColor(), new String[]{"25%"});

            info.addPara("When ship's current %s is above %s :", pad, Misc.getHighlightColor(), new String[]{"hard flux level","50%"});
            info.addPara("    %s non-missile weapon flux generation", pads, Misc.getPositiveHighlightColor(), new String[]{"-50%"});
            info.addPara("    %s shield damage taken", pads, Misc.getPositiveHighlightColor(), new String[]{"-15%"});

            info.addPara("When ship's current %s is above %s :", pad, Misc.getHighlightColor(), new String[]{"hard flux level","75%"});
            info.addPara("    %s hard flux dissipation rate with shields up", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
            
        }
    }
}
