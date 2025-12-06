package data.skills;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.*;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShieldAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;

// 原版战斗耐力修改
public class WitchIE {

    public static final float FLUX_COIL_MULTON = 1.1f;
    public static final float FLUX_COIL_MULTOFF = 0.85f;
    public static final float AMMO_BONUS = -50f;
    public static float SHIELD_UPKEEP_BONUS = 50f;

    public WitchIE() {
    }
    private static final class State {
         int num;
         int num2;
         int num3;
         int num4;
        private State() {
            num = 0;
            num2 = 0;
            num3 = 0;
            num4 = 0;
        }
    }
    public static class IE1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchIEE1";
        protected String id2 = "WitchIEE2";
        protected String id3 = "WitchIEE3";



        public IE1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }


        public void advance(float amount) {
            ShieldAPI shield = ship.getShield();
            stats.getMissileRoFMult().modifyPercent(id, AMMO_BONUS);
            stats.getShieldUpkeepMult().modifyMult(id, 1f - SHIELD_UPKEEP_BONUS * 0.01f);
            if(this.ship.isAlive()){
                if (shield != null) {
                    if (this.ship.getShield().isOn()) {
                        stats.getFluxDissipation().modifyMult(id2, FLUX_COIL_MULTON);
                        stats.getFluxDissipation().unmodify(id3);
                    }
                    if (this.ship.getShield().isOff()) {
                        stats.getFluxDissipation().modifyMult(id3, FLUX_COIL_MULTOFF);
                        stats.getFluxDissipation().unmodify(id2);
                    }
                }
            }

        }

    }

    public static class IE2 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchIEE4";
        protected String id111 = "WitchIEE5";
        protected String id222 = "WitchIEE6";
        protected String id333 = "WitchIEE7";
        private final IntervalUtil IES = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;
        State sAll = new State();

        public IE2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }


        public void advance(float amount) {
            this.IES.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            CombatEngineAPI engine = Global.getCombatEngine();
            if(this.ship.isAlive()){
                if(this.ship.getHardFluxLevel() <= 0.33){

                    if(!(sAll.num == 1)){
                        if (this.IES.intervalElapsed()) {
                            ++sAll.num2;
                        }
                    }
                    if(sAll.num2 == 5){
                        sAll.num = 1;
                        sAll.num2 =0;
                        engine.addFloatingText(this.ship.getLocation(), "Form of Primordial Fire", 50F, WthC_ColorData.IE_WHITE, this.ship, 5F, 5F);
                        Global.getSoundPlayer().playSound("SkillsSEIE1", 1f, 1f, this.ship.getLocation(), this.ship.getVelocity());
                        ship.setJitter(this.ship, WthC_ColorData.DUST_RED, 20f, 20, 20f);
                        ship.setJitterShields(false);
                        stats.getMaxSpeed().unmodify(id222);
                        stats.getEnergyWeaponFluxCostMod().unmodify(id222);
                        stats.getBallisticWeaponFluxCostMod().unmodify(id222);
                        stats.getBallisticWeaponDamageMult().unmodify(id222);
                        stats.getEnergyRoFMult().unmodify(id222);
                        stats.getBallisticRoFMult().unmodify(id222);
                        stats.getEnergyWeaponRangeBonus().unmodify(id333);
                        stats.getBallisticWeaponRangeBonus().unmodify(id333);
                        stats.getHardFluxDissipationFraction().unmodify(id333);
                        stats.getHullDamageTakenMult().unmodify(id333);
                        stats.getArmorDamageTakenMult().unmodify(id333);
                        stats.getEnergyRoFMult().unmodify(id333);
                        stats.getBallisticRoFMult().unmodify(id333);
                    }
                    if(sAll.num == 1){
                        if(sAll.num3 > 0){
                            if (this.IES.intervalElapsed()) {
                                --sAll.num3;
                            }
                        }
                        if(sAll.num4 > 0){
                            if (this.IES.intervalElapsed()) {
                                --sAll.num4;
                            }
                        }
                        stats.getEnergyWeaponRangeBonus().modifyPercent(id111, 15f);
                        stats.getBallisticWeaponRangeBonus().modifyPercent(id111, 15f);
                        stats.getEnergyWeaponDamageMult().modifyPercent(id111, 15f);
                        stats.getShieldDamageTakenMult().modifyMult(id111, 1.15f);
                        stats.getEnergyRoFMult().modifyPercent(id111, 15f);
                    }

                }
                if(this.ship.getHardFluxLevel() > 0.33 && this.ship.getHardFluxLevel() < 0.66){
                    if(!(sAll.num == 2)){
                        if (this.IES.intervalElapsed()) {
                            ++sAll.num3;
                        }
                    }
                    if(sAll.num3 == 5) {
                        sAll.num = 2;
                        sAll.num3 = 0;
                        engine.addFloatingText(this.ship.getLocation(), "Form of Wood, Water, and Metal", 50F, WthC_ColorData.DEEP_BLUE, this.ship, 5F, 5F);Global.getSoundPlayer().playSound("SkillsSEIE2", 1f, 1f, this.ship.getLocation(), this.ship.getVelocity());
                        ship.setJitter(this.ship, WthC_ColorData.SOUL_GREEN_D, 20f, 20, 20f);
                        ship.setJitterShields(false);
                        stats.getEnergyWeaponRangeBonus().unmodify(id111);
                        stats.getBallisticWeaponRangeBonus().unmodify(id111);
                        stats.getEnergyWeaponDamageMult().unmodify(id111);
                        stats.getShieldDamageTakenMult().unmodify(id111);
                        stats.getEnergyRoFMult().unmodify(id111);
                        stats.getEnergyWeaponRangeBonus().unmodify(id333);
                        stats.getBallisticWeaponRangeBonus().unmodify(id333);
                        stats.getHardFluxDissipationFraction().unmodify(id333);
                        stats.getHullDamageTakenMult().unmodify(id333);
                        stats.getArmorDamageTakenMult().unmodify(id333);
                        stats.getEnergyRoFMult().unmodify(id333);
                        stats.getBallisticRoFMult().unmodify(id333);
                    }
                    if(sAll.num == 2){
                        if(sAll.num2 > 0){
                            if (this.IES.intervalElapsed()) {
                                --sAll.num2;
                            }
                        }
                        if(sAll.num4 > 0){
                            if (this.IES.intervalElapsed()) {
                                --sAll.num4;
                            }
                        }
                        stats.getMaxSpeed().modifyPercent(id222, 25f);
                        stats.getEnergyWeaponFluxCostMod().modifyPercent(id222, -25f);
                        stats.getBallisticWeaponFluxCostMod().modifyPercent(id222, -25f);
                        stats.getBallisticWeaponDamageMult().modifyPercent(id222, 15f);
                        stats.getEnergyRoFMult().modifyPercent(id222, -25f);
                        stats.getBallisticRoFMult().modifyPercent(id222, -25f);
                    }

                }
                if(this.ship.getHardFluxLevel() >= 0.66){
                    if(!(sAll.num == 3)){
                        if (this.IES.intervalElapsed()) {
                            ++sAll.num4;
                        }
                    }
                    if(sAll.num4 == 5) {
                        sAll.num = 3;
                        sAll.num4 = 0;
                        engine.addFloatingText(this.ship.getLocation(), "Form of Final Earth", 50F, WthC_ColorData.B_WHITE, this.ship, 5F, 5F);
                        Global.getSoundPlayer().playSound("SkillsSEIE3", 1f, 1f, this.ship.getLocation(), this.ship.getVelocity());
                        ship.setJitter(this.ship, WthC_ColorData.EIF_ORANGE, 20f, 20, 20f);
                        ship.setJitterShields(false);
                        stats.getMaxSpeed().unmodify(id222);
                        stats.getEnergyWeaponFluxCostMod().unmodify(id222);
                        stats.getBallisticWeaponFluxCostMod().unmodify(id222);
                        stats.getBallisticWeaponDamageMult().unmodify(id222);
                        stats.getEnergyRoFMult().unmodify(id222);
                        stats.getBallisticRoFMult().unmodify(id222);
                        stats.getEnergyWeaponRangeBonus().unmodify(id111);
                        stats.getBallisticWeaponRangeBonus().unmodify(id111);
                        stats.getEnergyWeaponDamageMult().unmodify(id111);
                        stats.getShieldDamageTakenMult().unmodify(id111);
                        stats.getEnergyRoFMult().unmodify(id111);
                    }

                    if(sAll.num == 3){
                        if(sAll.num2 > 0){
                            if (this.IES.intervalElapsed()) {
                                --sAll.num2;
                            }
                        }
                        if(sAll.num3 > 0){
                            if (this.IES.intervalElapsed()) {
                                --sAll.num3;
                            }
                        }
                        stats.getEnergyWeaponRangeBonus().modifyPercent(id333, -50f);
                        stats.getBallisticWeaponRangeBonus().modifyPercent(id333, -50f);
                        stats.getEnergyRoFMult().modifyPercent(id333, -50f);
                        stats.getBallisticRoFMult().modifyPercent(id333, -50f);
                        stats.getHardFluxDissipationFraction().modifyFlat(id333, 0.10f);
                        stats.getHullDamageTakenMult().modifyMult(id333, 0.75f);
                        stats.getArmorDamageTakenMult().modifyMult(id333, 0.75f);
                    }
                }
            }

        }

    }

    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new WitchIE.IE1(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(WitchIE.IE1.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {

        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5f;
            float pads = 2f;

            info.addPara(" %s flux generated while maintaining shields", pad, Misc.getPositiveHighlightColor(), "-50%");
            info.addPara(" %s ship's final flux dissipation rate when shields are active", pads, Misc.getPositiveHighlightColor(), "+10%");
            info.addPara(" %s ship's final flux dissipation rate when shields are inactive", pads, Misc.getNegativeHighlightColor(), "-15%");
            info.addPara(" %s rate of fire for all missile weapons", pads, Misc.getNegativeHighlightColor(), "-50%");
        }

        public LevelBasedEffect.ScopeDescription getScopeDescription() {
            return ScopeDescription.PILOTED_SHIP;
        }
    }

    public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level2() {
        }
        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new WitchIE.IE2(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(WitchIE.IE2.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.initElite(stats, skill);
            float pad = 5f;
            float pads = 2f;
            info.addPara("When the ship's current hard flux level is %s and remains so for %s, it will transition to %s", pad, WthC_ColorData.HIGH_BLUE, "<33%", "5 seconds", "Form of Primordial Fire");
            info.addPara("    %s range of ballistic and energy weapons", pads, Misc.getPositiveHighlightColor(), "+15%");
            info.addPara("    %s rate of fire and damage of energy weapons", pads, Misc.getPositiveHighlightColor(), "+15%");
            info.addPara("    %s damage taken by shields", pads, Misc.getNegativeHighlightColor(), "+15%");
            info.addPara("When the ship's current hard flux level is %s and %s, and remains so for %s, it will transition to %s", pad, WthC_ColorData.HIGH_BLUE, ">=33%", "<66%", "5 seconds", "Form of Wood, Water, and Metal");
            info.addPara("    %s ship's maximum speed", pads, Misc.getPositiveHighlightColor(), "+25%");
            info.addPara("    %s damage of ballistic weapons", pads, Misc.getPositiveHighlightColor(), "+15%");
            info.addPara("    %s rate of fire and flux generation of ballistic and energy weapons", pads, Misc.getHighlightColor(), "-25%");
            info.addPara("When the ship's current hard flux level is %s and remains so for %s, it will transition to %s", pad, WthC_ColorData.HIGH_BLUE, ">=66%", "5 seconds", "Form of Final Earth");
            info.addPara("    %s range and rate of fire of ballistic and energy weapons", pads, Misc.getNegativeHighlightColor(), "-50%");
            info.addPara("    %s hard flux dissipation rate when shields are active", pads, Misc.getPositiveHighlightColor(), "+10%");
            info.addPara("    %s damage taken by armor and hull", pads, Misc.getPositiveHighlightColor(), "-25%");
        }
    }
}
