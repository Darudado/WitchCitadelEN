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


public class DR_WitchElf {
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
    public DR_WitchElf() {
    }


    public static class DarkReal implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchElf1";
        protected String id233 = "WitchElf2";
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

    public static class DR_Elf implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchDust3";
        protected String id2 = "WitchDust4";
        private final IntervalUtil SS = new IntervalUtil(1F, 1F);
        private boolean runOnce = false;
        State sAll = new State();
        public DR_Elf(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            if (this.ship.getFullTimeDeployed() < 1f){
                sAll.num = 0;
                sAll.num2 = 0;
                sAll.num3 = 0;
            }
            if (ship.isAlive()) {
                if(SS.intervalElapsed()){
                    sAll.num++;
                }
                if (sAll.num == 30){
                    sAll.num = 0;
                    sAll.num2++;
                }
                if (sAll.num2 == 1){
                    stats.getFluxDissipation().modifyPercent(id2, 1000F);
                    stats.getHardFluxDissipationFraction().modifyFlat(id2, 0.5F);
                    if (SS.intervalElapsed()){
                        sAll.num3++;
                    }
                }
                if (sAll.num3 == 3){
                    stats.getFluxDissipation().unmodify(id2);
                    stats.getHardFluxDissipationFraction().unmodify(id2);
                    sAll.num2 = 0;
                    sAll.num3 = 0;
                }
                stats.getDamageToFrigates().modifyPercent(id, 15F);
                stats.getDamageToDestroyers().modifyPercent(id, 15F);
                stats.getDamageToCruisers().modifyPercent(id, 15F);
                stats.getDamageToCapital().modifyPercent(id, 15F);
                stats.getShieldDamageTakenMult().modifyMult(id, 0.85F);
                stats.getArmorDamageTakenMult().modifyMult(id, 0.85F);
                stats.getHullDamageTakenMult().modifyMult(id, 0.85F);
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
            if (ship.isFrigate()){
                ship.addListener(new DR_Elf(ship));
            }
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            if (ship.isFrigate()){
                ship.removeListenerOfClass(DR_Elf.class);
            }
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
        }
    }
    public static class Level2AA extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2AA() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getTimeMult().modifyPercent(id, 50F);
            stats.getDamageToMissiles().modifyPercent(id, 100F);
            stats.getDamageToFighters().modifyPercent(id, 100F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getTimeMult().unmodify(id);
            stats.getDamageToMissiles().unmodify(id);
            stats.getDamageToFighters().unmodify(id);
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

            info.addPara("All %s in combat additionally gain %s :", pad, Misc.getHighlightColor(), new String[]{"fighters","following modifiers"});
            info.addPara("    %s damage to fighters and missiles", pads, Misc.getPositiveHighlightColor(), new String[]{"+100%"});
            info.addPara("    %s time flow", pads, Misc.getPositiveHighlightColor(), new String[]{"+50%"});

            info.addPara("All %s in combat additionally gain %s :", pad, Misc.getHighlightColor(), new String[]{"frigates","following modifiers"});
            info.addPara("   Rapidly vent flux every %s seconds", pads, Misc.getPositiveHighlightColor(), new String[]{"30"});
            info.addPara("    %s damage to ships", pads, Misc.getPositiveHighlightColor(), new String[]{"+15%"});
            info.addPara("    %s damage reduction", pads, Misc.getPositiveHighlightColor(), new String[]{"+15%"});
            
        }
    }
}
