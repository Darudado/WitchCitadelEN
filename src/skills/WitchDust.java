package data.skills;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.*;
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
public class WitchDust {

    public static float DAMAGE1 = 15.0F;
    public static float DAMAGE2 = 30.0F;
    public static final float DAMAGE_TAKEN_MULT1 = 1.15F;
    public static final float DAMAGE_TAKEN_MULT2 = 1.1F;
    public static float DAMAGE_TAKEN_MULTEX1 = -15F;
    public static float DAMAGE_TAKEN_MULTEX2 = -30F;
    public static float DAMAGE_TAKEN_MULTEX3 = -45F;

    public WitchDust() {
    }
    private static final class State {
         int num;
         int num2;
        private State() {
            num = 0;
            num2 = 0;
        }
    }
    public static class Dust1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchDustE1";

        public Dust1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            if (this.ship.getHitpoints() >= this.ship.getMaxHitpoints() * 0.5F) {
                this.stats.getDamageToFrigates().modifyPercent(this.id, WitchDust.DAMAGE1);
                this.stats.getDamageToDestroyers().modifyPercent(this.id, WitchDust.DAMAGE1);
                this.stats.getDamageToCapital().modifyPercent(this.id, WitchDust.DAMAGE1);
                this.stats.getDamageToCruisers().modifyPercent(this.id, WitchDust.DAMAGE1);
                this.stats.getShieldDamageTakenMult().modifyMult(this.id, DAMAGE_TAKEN_MULT1);
                this.stats.getArmorDamageTakenMult().modifyMult(this.id, DAMAGE_TAKEN_MULT1);
                this.stats.getHullDamageTakenMult().modifyMult(this.id, DAMAGE_TAKEN_MULT1);
            }

        }
    }

    public static class Dust2 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchDustE2";

        public Dust2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            if (this.ship.getHitpoints() < this.ship.getMaxHitpoints() * 0.5F) {
                this.stats.getDamageToFrigates().modifyPercent(this.id, WitchDust.DAMAGE2);
                this.stats.getDamageToDestroyers().modifyPercent(this.id, WitchDust.DAMAGE2);
                this.stats.getDamageToCapital().modifyPercent(this.id, WitchDust.DAMAGE2);
                this.stats.getDamageToCruisers().modifyPercent(this.id, WitchDust.DAMAGE2);
                this.stats.getShieldDamageTakenMult().modifyMult(this.id, DAMAGE_TAKEN_MULT2);
                this.stats.getArmorDamageTakenMult().modifyMult(this.id, DAMAGE_TAKEN_MULT2);
                this.stats.getHullDamageTakenMult().modifyMult(this.id, DAMAGE_TAKEN_MULT2);
            }

        }
    }

    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new WitchDust.Dust1(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(WitchDust.Dust1.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 10.0F;
            float pads = 2.0F;
            info.addPara("When the ship's hull integrity >= %s", pad, Misc.getHighlightColor(), new String[]{"50%"});
            info.addPara("    %s damage to ships", pads, Misc.getPositiveHighlightColor(), new String[]{"+15%"});
            info.addPara("    %s damage taken by the ship", pads, Misc.getNegativeHighlightColor(), new String[]{"+15%"});
        }
    }

    public static class DustRegen implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchDustE3";
        private final IntervalUtil DS = new IntervalUtil(0.01F, 0.03F);
        private boolean runOnce = false;
        protected String id2 = "WitchDustE4";
        State sAll = new State();
        public DustRegen(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.DS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }

            CombatEngineAPI engine = Global.getCombatEngine();

            if (ship.isAlive()) {
                if (this.ship.getFullTimeDeployed() < 1f){
                    sAll.num = 0;
                    sAll.num2 = 0;
                }
                if (this.ship.getHitpoints() < this.ship.getMaxHitpoints() * 0.25F && this.ship.getCurrentCR() > 0.25F) {
                    ++sAll.num2;
                }
                if(!(sAll.num2 == 0)){
                    if(sAll.num2 == 1){
                        engine.addFloatingText(this.ship.getLocation(), "Stellar Reformation Process", 50F, WthC_ColorData.MYSTERIOUS_PURPLE, this.ship, 5F, 5F);
                        ship.setJitter(this.ship, WthC_ColorData.MYSTERIOUS_PURPLE, 20f, 20, 20f);
                        ++sAll.num2;
                        Global.getSoundPlayer().playSound("Dust_SE", 0.85f, 0.5f, this.ship.getLocation(), this.ship.getVelocity());
                    }
                    this.stats.getFluxDissipation().modifyPercent(id2, 1000F);
                    this.stats.getHardFluxDissipationFraction().modifyFlat(id2, 0.75f);
                    if(!(this.ship.getHitpoints() >= this.ship.getMaxHitpoints() * 0.75F)){
                        if (this.DS.intervalElapsed()) {
                            this.ship.setHitpoints(this.ship.getHitpoints() + this.ship.getMaxHitpoints() * 0.01F);
                        }
                        if (this.ship.getHitpoints() >= this.ship.getMaxHitpoints() * 0.75F) {
                            sAll.num2 = 0;
                            this.ship.setCurrentCR(this.ship.getCurrentCR() - 0.25F);
                            ++sAll.num;
                        }
                    }
                }
                if(sAll.num2 == 0){
                    this.stats.getFluxDissipation().unmodify(id2);
                    this.stats.getHardFluxDissipationFraction().unmodify(id2);
                }

                }

            if ((sAll.num == 1)) {
                this.stats.getShieldDamageTakenMult().modifyPercent(this.id, DAMAGE_TAKEN_MULTEX1);
                this.stats.getArmorDamageTakenMult().modifyPercent(this.id, DAMAGE_TAKEN_MULTEX1);
                this.stats.getHullDamageTakenMult().modifyPercent(this.id, DAMAGE_TAKEN_MULTEX1);
                ship.setJitter(this.ship, WthC_ColorData.DUST_RED, 3f, 3, 3f);
                ship.setJitterShields(false);
            }
            if (sAll.num == 2) {
                this.stats.getShieldDamageTakenMult().modifyPercent(this.id, DAMAGE_TAKEN_MULTEX2);
                this.stats.getArmorDamageTakenMult().modifyPercent(this.id, DAMAGE_TAKEN_MULTEX2);
                this.stats.getHullDamageTakenMult().modifyPercent(this.id, DAMAGE_TAKEN_MULTEX2);
                ship.setJitter(this.ship, WthC_ColorData.DUST_RED, 5f, 5, 5f);
                ship.setJitterShields(false);
            }
            if (sAll.num >= 3) {
                this.stats.getShieldDamageTakenMult().modifyPercent(this.id, DAMAGE_TAKEN_MULTEX3);
                this.stats.getArmorDamageTakenMult().modifyPercent(this.id, DAMAGE_TAKEN_MULTEX3);
                this.stats.getHullDamageTakenMult().modifyPercent(this.id, DAMAGE_TAKEN_MULTEX3);
                ship.setJitter(this.ship, WthC_ColorData.DUST_RED, 7f, 7, 7f);
                ship.setJitterShields(false);
            }
            }
        }


        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new Dust2(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(Dust2.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.init(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("When the ship's hull integrity < %s", pad, Misc.getHighlightColor(), new String[]{"50%"});
                info.addPara("    %s damage to ships", pads, Misc.getPositiveHighlightColor(), new String[]{"+30%"});
                info.addPara("    %s damage taken by the ship", pads, Misc.getNegativeHighlightColor(), new String[]{"+10%"});
            }
        }

        public static class Level3 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level3() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new DustRegen(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(DustRegen.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("When the ship's hull integrity < %s and current CR > %s", pad, WthC_ColorData.HIGH_BLUE, new String[]{"25%", "25%"});
                info.addPara("   Immediately restores hull integrity to %s of its maximum value at the cost of %s CR", pads, WthC_ColorData.HIGH_BLUE, new String[]{"75%", "25%"});
                info.addPara("   During the restoration process, the ship will %s and can vent hard flux while shields are active", pads, Misc.getPositiveHighlightColor(), new String[]{"rapidly vent flux"});
                info.addPara("   Each time this effect is triggered, it enhances the ship's %s damage resistance, lasting until the end of combat", pads, Misc.getPositiveHighlightColor(), new String[]{"15%"});
            }
        }
    }
