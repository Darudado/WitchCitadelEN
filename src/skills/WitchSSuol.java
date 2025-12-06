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
public class WitchSSuol {


    public static final float DAMAGE_TAKEN_MULT1 = 0.6F;
    public static float TIME = 25f;
    public static float TIME2 = 50f;
    public static float PEAK_BONUS_PERCENT = -66f;


    public WitchSSuol() {
    }
    private static final class State {
         int num;
         int num2;
         int num3;
        private State() {
            num = 0;
            num2 = 0;
            num3 = 0;
        }
    }
    public static class SSoul1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchSSoulE1";
        private final IntervalUtil SS = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;
        State sAll = new State();

        public SSoul1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }

            CombatEngineAPI engine = Global.getCombatEngine();
            if (this.ship.getFullTimeDeployed() < 1f){
                sAll.num2 = 0;
                sAll.num3 = 0;
            }
            if(this.ship.isAlive()){
                if(!this.ship.isFrigate()){
                    if(!this.ship.isDestroyer()){
                        if (!(this.ship.getHitpoints() == this.ship.getMaxHitpoints())) {
                            if(this.ship.getHitpoints() < 2000f) {
                                if(sAll.num2 == 0){
                                    if (this.SS.intervalElapsed()) {
                                        sAll.num3++;
                                    }
                                    if(sAll.num3 == 10){
                                        sAll.num2++;
                                    }
                                }
                            }
                        }
                        if(sAll.num2 == 1){
                            engine.addFloatingText(this.ship.getLocation(), "Embrace the Moment of Death", 50F, WthC_ColorData.SOUL_GREEN_D, this.ship, 5F, 5F);
                            this.ship.setJitter(this.ship, WthC_ColorData.MYSTERIOUS_PURPLE, 20f, 20, 20f);
                            ++sAll.num2;
                        }
                        if(sAll.num2 == 2){
                            this.stats.getHullDamageTakenMult().modifyMult(this.id, DAMAGE_TAKEN_MULT1);
                            this.stats.getTimeMult().modifyPercent(this.id, TIME);
                            if (!(this.ship.getHitpoints() >= this.ship.getMaxHitpoints())) {
                                if (this.SS.intervalElapsed()) {
                                    this.ship.setHitpoints(this.ship.getHitpoints() + this.ship.getMaxHitpoints() * 0.01F);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static class SSoul2 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchSSoulE2";
        State sAll = new State();
        public SSoul2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            CombatEngineAPI engine = Global.getCombatEngine();

            if (this.ship.getFullTimeDeployed() < 1f){
                    sAll.num = 0;
            }
            if (this.ship.getPeakTimeRemaining() <= 1f) {
                if (sAll.num < 5) {
                    this.ship.getMutableStats().getPeakCRDuration().modifyFlat(id, this.ship.getFullTimeDeployed() * 0.5f);
                    this.ship.setHitpoints(this.ship.getHitpoints() * 0.67f);
                    ship.setJitter(this.ship, WthC_ColorData.SOUL_GREEN_D, 20f, 40, 20f);
                    engine.addFloatingText(this.ship.getLocation(), "Soul Sacrifice", 50F, WthC_ColorData.SOUL_GREEN_D, this.ship, 5F, 5F);
                    Global.getSoundPlayer().playSound("SkillsSESS", 1f, 1f, this.ship.getLocation(), this.ship.getVelocity());
                    ++sAll.num;
                }
                if (sAll.num == 1){
                    this.stats.getTimeMult().modifyPercent(this.id, TIME);
                }
                if (sAll.num >= 2){
                    this.stats.getTimeMult().modifyPercent(this.id, TIME2);
                }
            }

        }
    }

    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new SSoul1(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(SSoul1.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            stats.getPeakCRDuration().modifyPercent(id66, PEAK_BONUS_PERCENT);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara(" %s ship peak performance time", pad, Misc.getNegativeHighlightColor(), new String[]{"-66%"});
            info.addPara("When the ship's hull integrity is %s and %s for %s seconds (only applies to cruisers and capital ships)", pad, Misc.getHighlightColor(), new String[]{"<2000", "not full", "10"});
            info.addPara("    %s damage taken by ship hull", pads, Misc.getPositiveHighlightColor(), new String[]{"-40%"});
            info.addPara("    %s ship time flow rate", pads, Misc.getPositiveHighlightColor(), new String[]{"+25%"});
            info.addPara("    %s maximum hull integrity recovery per second", pads, Misc.getPositiveHighlightColor(), new String[]{"+1%"});
            info.addPara("   These effects will last until the end of combat and will no longer be affected by the ship's hull integrity after triggering", Misc.getHighlightColor(), pads);}

    }




        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new SSoul2(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(SSoul2.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;

                info.addPara("When the ship's peak performance time %s, consume %s of the ship's current hull integrity to restore it to %s of its maximum value", pad, WthC_ColorData.HIGH_BLUE, new String[]{"reaches zero", "33%", "50%"});
                info.addPara("    Each time this effect is triggered, the peak performance time gained next time will be reduced by %s and can only be triggered up to %s times", pads, WthC_ColorData.HIGH_BLUE, new String[]{"50%", "5"});
                info.addPara("    When this effect is triggered, the ship's time flow rate will be additionally increased by %s", pads, Misc.getPositiveHighlightColor(), new String[]{"25%"});
                info.addPara("    The time flow rate effect will last until the end of combat and can stack up to %s in a single battle", pads, Misc.getPositiveHighlightColor(), new String[]{"50%"});
                info.addPara("    When piloting a ship with a safety protocol override module, the peak performance time gained from this effect will be drastically reduced", Misc.getHighlightColor(), pads);
            }
        }


    }
