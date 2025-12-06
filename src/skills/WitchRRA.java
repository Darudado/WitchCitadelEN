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

// 原版战斗耐力修改
public class WitchRRA {


    public static final float WEAPON_RANGE_BONUS = 25f;
    public static final float WEAPON_ROF_BONUS = -25f;
    public static final float DAMAGE1 = -80.0F;
    public static final float DAMAGE_TAKEN_MULTEX1 = 0.2F;
    private static final float FIGHTER_RATE = 80f;
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
    public WitchRRA() {
    }

    public static class RRA2 implements AdvanceableListener {
        private final IntervalUtil TS = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchRRAE1";
        protected String id3 = "WitchRRAE2";
        State sAll = new State();

        public RRA2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }


        public void advance(float amount) {
            this.TS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            CombatEngineAPI engine = Global.getCombatEngine();
            if (this.ship.getFullTimeDeployed() < 1f){
                sAll.num = 0;
                sAll.num4 = 0;
            }
            if (this.ship.getHitpoints() > this.ship.getMaxHitpoints() * 0.8F) {
                this.stats.getMaxSpeed().modifyFlat(id, -10f);
                this.stats.getBallisticWeaponDamageMult().modifyPercent(id,15.0f);
                this.stats.getEnergyWeaponDamageMult().modifyPercent(id,15.0f);

            }
            if (this.ship.getHitpoints() <= this.ship.getMaxHitpoints() * 0.5F) {
                ++sAll.num;
                ++sAll.num4;
            }
            if(sAll.num4 != 0){
                this.stats.getMaxSpeed().modifyPercent(id, 300.0f);
                this.stats.getDamageToFrigates().modifyPercent(id, DAMAGE1);
                this.stats.getDamageToDestroyers().modifyPercent(id, DAMAGE1);
                this.stats.getDamageToCapital().modifyPercent(id, DAMAGE1);
                this.stats.getDamageToCruisers().modifyPercent(id, DAMAGE1);
                this.stats.getCombatEngineRepairTimeMult().modifyMult(id, 0.15f);
                this.stats.getFighterRefitTimeMult().modifyPercent(id, FIGHTER_RATE);
                this.stats.getPeakCRDuration().modifyFlat(id, -9999f);
            }
            if(sAll.num == 1){
                engine.addFloatingText(this.ship.getLocation(), "Switching to emergency protocol", 50F, WthC_ColorData.DEEP_BLUE, this.ship, 5F, 5F);
                ++sAll.num;
                ++sAll.num3;
            }
            if(sAll.num3 == 1){
                this.stats.getShieldDamageTakenMult().modifyMult(this.id3, DAMAGE_TAKEN_MULTEX1);
                this.stats.getArmorDamageTakenMult().modifyMult(this.id3, DAMAGE_TAKEN_MULTEX1);
                this.stats.getHullDamageTakenMult().modifyMult(this.id3, DAMAGE_TAKEN_MULTEX1);
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

    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getBallisticWeaponRangeBonus().modifyPercent(id, WEAPON_RANGE_BONUS);
            stats.getEnergyWeaponRangeBonus().modifyPercent(id, WEAPON_RANGE_BONUS);
            stats.getBallisticRoFMult().modifyPercent(id, WEAPON_ROF_BONUS);
            stats.getEnergyRoFMult().modifyPercent(id, WEAPON_ROF_BONUS);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5f;
            float pads = 2f;
            info.addPara(" %s final range of non-missile weapons", pad, Misc.getPositiveHighlightColor(), "+25%");
            info.addPara(" %s rate of fire for non-missile weapons", pads, Misc.getNegativeHighlightColor(), "-25%");

        }

        public ScopeDescription getScopeDescription() {
            return ScopeDescription.PILOTED_SHIP;
        }
    }

    public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level2() {
        }
        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new WitchRRA.RRA2(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(WitchRRA.RRA2.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.initElite(stats, skill);
            float pad = 5f;
            float pads = 2f;
            info.addPara("When ship's hull integrity > %s", pad, Misc.getHighlightColor(), "80%");
            info.addPara("   "+" %s damage dealt by non-missile weapons", pads, Misc.getPositiveHighlightColor(), "+15%");
            info.addPara("   "+" %s base ship speed", pads, Misc.getNegativeHighlightColor(), "-10");
            info.addPara("When ship's hull integrity <= %s, it will enter %s", pad, WthC_ColorData.HIGH_BLUE, "50%", "emergency mode");
            info.addPara("Upon entering %s, for the first %s seconds, the ship will %s and immediately lose all peak performance time", pads, WthC_ColorData.HIGH_BLUE, "emergency mode", "5", "negate most incoming damage");
            info.addPara("When the ship is in %s:", pad, Misc.getHighlightColor(), "emergency mode");
            info.addPara("   "+" %s overall ship speed", pads, Misc.getPositiveHighlightColor(), "+300%");
            info.addPara("   "+" %s engine repair speed", pads, Misc.getPositiveHighlightColor(), "greatly increased");
            info.addPara("   "+" %s damage dealt to the ship", pads, Misc.getNegativeHighlightColor(), "-80%");
            info.addPara("   "+" %s fighter rearmament time", pads, Misc.getNegativeHighlightColor(), "+80%");
        }
    }
}
