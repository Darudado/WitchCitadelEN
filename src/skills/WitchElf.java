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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 原版战斗耐力修改
public class WitchElf {

    public static float DAMAGE1 = 0.85F;
    public static float DAMAGE2 = 1.15F;


    public WitchElf() {
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

    public static class Elf1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchElfE1";
        protected String id2 = "WitchElfE2";
        private final IntervalUtil SS = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;
        State sAll = new State();
        public Elf1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();

        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }

            CombatEngineAPI engine = Global.getCombatEngine();
            if (this.ship.isAlive()) {
                if (this.ship.isFrigate()) {
                    this.stats.getTimeMult().modifyPercent(id, 25f);
                    if (sAll.num2 < 5) {
                        this.stats.getShieldDamageTakenMult().modifyMult(id2, DAMAGE2);
                        this.stats.getArmorDamageTakenMult().modifyMult(id2, DAMAGE2);
                        this.stats.getHullDamageTakenMult().modifyMult(id2, DAMAGE2);
                    }
                }
                if (!this.ship.isFrigate()) {
                    this.stats.getShieldDamageTakenMult().modifyMult(id, DAMAGE1);
                    this.stats.getArmorDamageTakenMult().modifyMult(id, DAMAGE1);
                    this.stats.getHullDamageTakenMult().modifyMult(id, DAMAGE1);
                    if (sAll.num2 < 5) {
                        this.stats.getTimeMult().modifyPercent(id2, -25f);
                    }
                }
                if (sAll.num2 == 5) {
                    if (this.SS.intervalElapsed()) {
                        ++sAll.num4;
                    }
                }
                if (sAll.num4 == 5) {
                    engine.addFloatingText(this.ship.getLocation(), "Reorganization Process", 50F, WthC_ColorData.EIF_ORANGE, this.ship, 5F, 5F);
                    ship.setJitter(this.ship, WthC_ColorData.EIF_ORANGE, 20f, 20, 20f);
                    ship.setJitterShields(false);
                    ++sAll.num4;
                    ++sAll.num2;
                    this.stats.getShieldDamageTakenMult().unmodify(id2);
                    this.stats.getArmorDamageTakenMult().unmodify(id2);
                    this.stats.getHullDamageTakenMult().unmodify(id2);
                    this.stats.getTimeMult().unmodify(id2);
                }
            }


        }
    }


    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new Elf1(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(Elf1.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara(" %s time flow rate for all fighter craft", pad, Misc.getPositiveHighlightColor(), new String[]{"+50%"});

            info.addPara("When piloting a frigate", Misc.getHighlightColor(), pad);
            info.addPara("    %s ship time flow rate", pads, Misc.getPositiveHighlightColor(), new String[]{"+25%"});
            info.addPara("    %s damage taken by the ship", pads, Misc.getNegativeHighlightColor(), new String[]{"+15%"});

            info.addPara("When piloting a non-frigate", Misc.getHighlightColor(), pad);
            info.addPara("    %s damage taken by the ship", pads, Misc.getPositiveHighlightColor(), new String[]{"-15%"});
            info.addPara("    %s ship time flow rate", pads, Misc.getNegativeHighlightColor(), new String[]{"-25%"});


        }
    }

    public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level2() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getTimeMult().modifyPercent(id, 50f);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getTimeMult().unmodify(id);
        }

    }

    public static class Elf2 implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchElfE3";
        private final IntervalUtil ES = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;
        protected String id3 = "WitchElfE4";
        State sAll = new State();

        public Elf2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.ES.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            CombatEngineAPI engine = Global.getCombatEngine();


            if (ship.isAlive()) {
                if (this.ship.getFullTimeDeployed() < 1f) {
                    sAll.num = 0;
                    sAll.num2 = 0;
                    sAll.num3 = 0;
                    sAll.num4 = 0;
                }

                if (sAll.num2 < 11) {
                    if (this.ES.intervalElapsed()) {
                        ++sAll.num;
                    }
                    if (sAll.num == 30) {
                        sAll.num = 0;
                        engine.addFloatingText(this.ship.getLocation(), "Stardust Reorganization", 50F, WthC_ColorData.EIF_ORANGE, this.ship, 5F, 5F);
                        Global.getSoundPlayer().playSound("SkillsSEIE3", 1f, 1f, this.ship.getLocation(), this.ship.getVelocity());
                        ship.setJitter(this.ship, WthC_ColorData.EIF_ORANGE, 20f, 20, 20f);
                        ship.setJitterShields(false);
                        ++sAll.num3;
                        ++sAll.num2;
                        if(ship.isFrigate()){
                            this.ship.getMutableStats().getPeakCRDuration().modifyFlat(id, 15f);
                        }
                    }
                }
                if (sAll.num3 > 0 && sAll.num3 < 6) {
                    if (this.ES.intervalElapsed()) {
                        ++sAll.num3;
                    }
                    if (ship.isFrigate()) {
                        this.stats.getFluxDissipation().modifyPercent(id3, 500f);
                        this.stats.getHardFluxDissipationFraction().modifyFlat(id3, 0.99f);
                    }
                    if (!ship.isFrigate()) {
                        this.stats.getHullDamageTakenMult().modifyMult(id3,0.2f);
                        this.stats.getShieldDamageTakenMult().modifyMult(id3,0.2f);
                        this.stats.getArmorDamageTakenMult().modifyMult(id3,0.2f);
                        this.stats.getDynamic().getStat("replacement_rate_increase_mult").modifyPercent(id3, 1000f);
                        this.stats.getDynamic().getStat("replacement_rate_decrease_mult").modifyPercent(id3, -1000f);
                        this.stats.getFighterRefitTimeMult().modifyMult(id3, 0.01f);
                    }
                }
                if (sAll.num3 == 6) {
                    sAll.num3 = 0;
                }
                if (sAll.num3 == 0) {
                    this.stats.getDynamic().getStat("replacement_rate_increase_mult").unmodify(id3);
                    this.stats.getDynamic().getStat("replacement_rate_decrease_mult").unmodify(id3);
                    this.stats.getFluxDissipation().unmodify(id3);
                    this.stats.getHardFluxDissipationFraction().unmodify(id3);
                    this.stats.getFighterRefitTimeMult().unmodify(id3);
                    this.stats.getHullDamageTakenMult().unmodify(id3);
                    this.stats.getShieldDamageTakenMult().unmodify(id3);
                    this.stats.getArmorDamageTakenMult().unmodify(id3);
                }

            }
        }

    }


        public static class Level3 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level3() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new Elf2(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(Elf2.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("Every %s seconds during combat, the ship will trigger %s", pad, WthC_ColorData.HIGH_BLUE, new String[]{"30", "Stardust Reorganization"});
                info.addPara("   If piloting a %s, it will reverse peak time by %s and vent flux at %s efficiency within %s", pads, Misc.getPositiveHighlightColor(), new String[]{"frigate", "15 seconds", "5x", "5 seconds"});
                info.addPara("   If piloting a %s, it will block %s of incoming damage and %s rearmament efficiency within %s", pads, Misc.getPositiveHighlightColor(), new String[]{"non-frigate", "most", "rapidly restore", "5 seconds"});
                info.addPara("   This effect can trigger up to %s times per battle. Upon the %s trigger, all %s from this skill will be removed", pads, WthC_ColorData.HIGH_BLUE, new String[]{"10", "5th", "base penalties"});
            }
        }

    }

