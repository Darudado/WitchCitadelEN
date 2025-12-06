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
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 原版战斗耐力修改
public class WitchCross {


    public static final float DAMAGE_TO_MODULES_BONUS = 100.0F;
    public static float DAMAGE_TAKEN_MULTEX1 = -15F;
    public static final float FLUX_Bonus = -20f;

    public WitchCross() {
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

    public static class Cross1 implements AdvanceableListener {
        private final IntervalUtil TF = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchCrossE1";
        State sAll = new State();

        public Cross1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.TF.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            if (this.ship.getFullTimeDeployed() < 1f){
                sAll.num = 0;
                sAll.num2 = 0;
                sAll.num3 = 0;
                sAll.num4 = 0;
            }
            if (this.ship.isAlive()) {
                if(this.ship.getHitpoints() >= this.ship.getMaxHitpoints()){
                    sAll.num2 = 0;
                }
                if(this.ship.getHitpoints() <= this.ship.getMaxHitpoints() * 0.51f){
                    ++sAll.num2;
                }

                if(sAll.num2 == 0){
                    if (this.TF.intervalElapsed()) {
                        if(this.ship.getHitpoints() >= this.ship.getMaxHitpoints() * 0.5F){
                            this.ship.setHitpoints(ship.getHitpoints() - ship.getMaxHitpoints() * 0.01F);
                        }
                    }
                  }
                if(sAll.num2 >= 1) {
                    if (this.TF.intervalElapsed()) {
                        if (this.ship.getHitpoints() < this.ship.getMaxHitpoints()) {
                            this.ship.setHitpoints(ship.getHitpoints() + ship.getMaxHitpoints() * 0.01F);
                        }
                    }
                }
                this.stats.getDamageToTargetEnginesMult().modifyPercent(id, DAMAGE_TO_MODULES_BONUS);
                this.stats.getDamageToTargetWeaponsMult().modifyPercent(id, DAMAGE_TO_MODULES_BONUS);
                this.stats.getEmpDamageTakenMult().modifyPercent(id, 50F);
            }

        }
    }
        public static class Cross2 implements AdvanceableListener {
            protected ShipAPI ship;
            protected MutableShipStatsAPI stats;
            protected String id = "WitchCrossE2";
            protected String id111 = "WitchCrossE3";
            protected String id222 = "WitchCrossE4";
            protected String id3 = "WitchCrossE5";
            State sAll = new State();

            private final IntervalUtil TF = new IntervalUtil(1.0F, 1.0F);
            private boolean runOnce = false;

            public Cross2(ShipAPI ship) {
                this.ship = ship;
                this.stats = ship.getMutableStats();
            }

            public void advance(float amount) {
                this.TF.advance(amount);
                if (!this.runOnce) {
                    this.runOnce = true;
                }
                CombatEngineAPI engine = Global.getCombatEngine();
                if (this.ship.getHitpoints() > this.ship.getMaxHitpoints() * 0.75f) {
                        this.stats.getEnergyWeaponRangeBonus().modifyPercent(id111, -20f);
                        this.stats.getEnergyWeaponDamageMult().modifyPercent(id111, 20f);
                        this.stats.getBallisticWeaponRangeBonus().modifyPercent(id111, -20f);
                        this.stats.getBallisticWeaponDamageMult().modifyPercent(id111, 20f);
                        this.stats.getShieldDamageTakenMult().modifyPercent(id111, DAMAGE_TAKEN_MULTEX1);
                        this.stats.getArmorDamageTakenMult().modifyPercent(id111, DAMAGE_TAKEN_MULTEX1);
                        this.stats.getHullDamageTakenMult().modifyPercent(id111, DAMAGE_TAKEN_MULTEX1);
                    if (sAll.num == 0) {
                        engine.addFloatingText(this.ship.getLocation(), "Switched to Assault Mode", 50F, WthC_ColorData.CROSS_GARY_D, this.ship, 5F, 5F);
                        Global.getSoundPlayer().playSound("SkillsSEIE3", 1f, 1f, this.ship.getLocation(), this.ship.getVelocity());
                        ++sAll.num;
                        ++sAll.num3;
                        this.stats.getEnergyWeaponRangeBonus().unmodify(id222);
                        this.stats.getMaxSpeed().unmodify(id222);
                        this.stats.getBallisticWeaponRangeBonus().unmodify(id222);
                        this.stats.getBallisticWeaponFluxCostMod().unmodify(id222);
                        this.stats.getEnergyWeaponFluxCostMod().unmodify(id222);
                    }
                }
                if (this.ship.getHitpoints() < this.ship.getMaxHitpoints() * 0.75f) {
                    this.stats.getEnergyWeaponRangeBonus().modifyPercent(id222, 20f);
                    this.stats.getMaxSpeed().modifyPercent(id222, 33f);
                    this.stats.getBallisticWeaponRangeBonus().modifyPercent(id222, 20f);
                    this.stats.getBallisticWeaponFluxCostMod().modifyPercent(id222, FLUX_Bonus);
                    this.stats.getEnergyWeaponFluxCostMod().modifyPercent(id222, FLUX_Bonus);
                    if (sAll.num == 1) {
                        engine.addFloatingText(this.ship.getLocation(), "Switched to Disruption Mode", 50F, WthC_ColorData.CROSS_GARY_D, this.ship, 5F, 5F);
                        Global.getSoundPlayer().playSound("SkillsSEIE3", 1f, 1f, this.ship.getLocation(), this.ship.getVelocity());
                        --sAll.num;
                        ++sAll.num3;
                        this.stats.getEnergyWeaponRangeBonus().unmodify(id111);
                        this.stats.getEnergyWeaponDamageMult().unmodify(id111);
                        this.stats.getBallisticWeaponRangeBonus().unmodify(id111);
                        this.stats.getBallisticWeaponDamageMult().unmodify(id111);
                        this.stats.getShieldDamageTakenMult().unmodify(id111);
                        this.stats.getArmorDamageTakenMult().unmodify(id111);
                        this.stats.getHullDamageTakenMult().unmodify(id111);
                    }
                }
                if (sAll.num3 == 1) {
                    this.stats.getTimeMult().modifyMult(id3, 3.0f);
                    if (this.TF.intervalElapsed()) {
                        ++sAll.num4;
                    }
                    if (sAll.num4 == 10) {
                        ++sAll.num3;
                    }
                }
                if (sAll.num3 == 2) {
                    this.stats.getTimeMult().unmodify(id3);
                    sAll.num3 = 0;
                    sAll.num4 = 0;
                }

            }
        }

        public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level1() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new Cross1(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(Cross1.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.init(stats, skill);
                float pad = 10.0F;
                float pads = 2.0F;
                info.addPara("When the ship's hull integrity is %s, it will enter %s, and when it is %s, it will enter %s", pad, WthC_ColorData.HIGH_BLUE, new String[]{">75%","Assault Mode","<75%","Disruption Mode"});
                info.addPara("Each time the ship performs %s, it will gain %s", pads, WthC_ColorData.HIGH_BLUE, new String[]{"mode switching","a brief significant time flow bonus"});
                info.addPara("When the ship is in %s", pad, Misc.getHighlightColor(), new String[]{"Assault Mode"});
                info.addPara("    %s damage from non-missile weapons", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
                info.addPara("    %s damage taken by the ship", pads, Misc.getPositiveHighlightColor(), new String[]{"-15%"});
                info.addPara("    %s range of non-missile weapons", pads, Misc.getNegativeHighlightColor(), new String[]{"-20%"});
            }
        }


        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                    ship.addListener(new Cross2(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                    ship.removeListenerOfClass(Cross2.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 10.0F;
                float pads = 2.0F;
                info.addPara("When the ship's hull integrity is %s, it will enter %s, and when it is %s, it will enter %s", pad, WthC_ColorData.HIGH_BLUE, new String[]{">75%","Assault Mode","<75%","Disruption Mode"});
                info.addPara("Each time the ship performs %s, it will gain %s", pads, WthC_ColorData.HIGH_BLUE, new String[]{"mode switching","a brief significant time flow bonus"});
                info.addPara("When the ship is in %s", pad, Misc.getHighlightColor(), new String[]{"Assault Mode"});
                info.addPara("    %s damage from non-missile weapons", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
                info.addPara("    %s damage taken by the ship", pads, Misc.getPositiveHighlightColor(), new String[]{"-15%"});
                info.addPara("    %s range of non-missile weapons", pads, Misc.getNegativeHighlightColor(), new String[]{"-20%"});

                info.addPara("When the ship is in %s", pad, Misc.getHighlightColor(), new String[]{"Disruption Mode"});
                info.addPara("    %s ship base speed", pads, Misc.getPositiveHighlightColor(), new String[]{"+33%"});
                info.addPara("    %s range of non-missile weapons", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
                info.addPara("    %s flux generated by weapons", pads, Misc.getPositiveHighlightColor(), new String[]{"-20%"});
            }
        }


    }

