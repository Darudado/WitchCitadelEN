package data.skills.RankB;

import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShieldAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.skills.Contract;
import data.util.WthC_ColorData;

// 原版战斗耐力修改

public class SENetLink_pale {

    public static float UP = 5f;
    public static float TUP = 0.95f;
    public static float UP2 = 10f;
    public static float TUP2 = 0.9f;
    public static float CREW_PERCENT = 50.0F;
    public static final float SUPPLY_USE_MULT = 0.0F;
    public static float MAX_CR_PENALTY = 0.15F;
    public static final float PEAK_BONUS_PERCENT = 20f;
    public static final float DEGRADE_REDUCTION_PERCENT = -100f;

    public SENetLink_pale() {
    }

    public static class CT1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "ContractP1";

        public CT1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {

            if(this.ship.isAlive()){
                if (this.ship.getHitpoints() >= this.ship.getMaxHitpoints() * 0.5f) {
                    this.stats.getEnergyWeaponDamageMult().modifyPercent(id, UP);
                    this.stats.getEnergyRoFMult().modifyPercent(id, UP);
                    this.stats.getBallisticWeaponDamageMult().modifyPercent(id, UP);
                    this.stats.getBallisticRoFMult().modifyPercent(id, UP);
                    this.stats.getHullDamageTakenMult().modifyMult(id, TUP);
                    this.stats.getShieldDamageTakenMult().modifyMult(id, TUP);
                    this.stats.getArmorDamageTakenMult().modifyMult(id, TUP);
                    this.stats.getTimeMult().modifyPercent(id, UP);
                    this.stats.getMaxSpeed().modifyPercent(id,UP);

                    ShieldAPI shield = ship.getShield();
                    if (shield != null) {
                        if (this.ship.getShield().isOn()) {
                            this.stats.getFluxDissipation().modifyPercent(id, UP);
                        }
                        if (this.ship.getShield().isOff()) {
                            stats.getFluxDissipation().unmodify(id);
                        }
                    }
                }
            }
        }
    }

    public static class CT2 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "ContractP2";

        public CT2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {

            if(this.ship.isAlive()){
                if (this.ship.getHitpoints() < this.ship.getMaxHitpoints() * 0.5f) {
                    this.stats.getEnergyWeaponDamageMult().modifyPercent(id, UP2);
                    this.stats.getEnergyRoFMult().modifyPercent(id, UP2);
                    this.stats.getBallisticWeaponDamageMult().modifyPercent(id, UP2);
                    this.stats.getBallisticRoFMult().modifyPercent(id, UP2);
                    this.stats.getHullDamageTakenMult().modifyMult(id, TUP2);
                    this.stats.getShieldDamageTakenMult().modifyMult(id, TUP2);
                    this.stats.getArmorDamageTakenMult().modifyMult(id, TUP2);
                    this.stats.getTimeMult().modifyPercent(id, UP2);
                    this.stats.getMaxSpeed().modifyPercent(id, UP2);
                    ShieldAPI shield = ship.getShield();
                    if (shield != null) {
                        if (this.ship.getShield().isOn()) {
                            this.stats.getFluxDissipation().modifyPercent(id, UP2);
                        }
                        if (this.ship.getShield().isOff()) {
                            stats.getFluxDissipation().unmodify(id);
                        }
                    }
                }
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
            stats.getMinCrewMod().modifyMult(id,SUPPLY_USE_MULT);
            stats.getMaxCombatReadiness().modifyFlat(id, MAX_CR_PENALTY);
            stats.getSuppliesPerMonth().modifyPercent(id, CREW_PERCENT);
            stats.getPeakCRDuration().modifyPercent(id, PEAK_BONUS_PERCENT);
            stats.getCRLossPerSecondPercent().modifyMult(id, 1f - DEGRADE_REDUCTION_PERCENT / 100f);

            if (stats.getVariant().getHullVariantId().contains("WthC_PaleCrown")){
                stats.getMaxCombatReadiness().modifyFlat(id, 0.85F);
            }

        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5f;
            float pads = 2f;

            info.addPara(" %s ship max CR", pad, Misc.getPositiveHighlightColor(), "+15%");
            info.addPara(" %s monthly maintenance cost", pads, Misc.getNegativeHighlightColor(), "+50%");
            info.addPara(" %s ship peak performance time", pads, Misc.getPositiveHighlightColor(), "+20%");
            info.addPara(" %s CR degradation rate after peak time", pads, Misc.getNegativeHighlightColor(), "+100%");

            info.addPara("Ship no longer requires crew (still not considered automated ship)", WthC_ColorData.HIGH_BLUE, pad);
            info.addPara("This skill counts as a Wraith Link", WthC_ColorData.HIGH_BLUE, pad);

        }

        public ScopeDescription getScopeDescription() {
            return ScopeDescription.PILOTED_SHIP;
        }
    }

    public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level2() {
        }
        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new CT1(ship));
            ship.addListener(new CT2(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListener(new CT1(ship));
            ship.removeListener(new CT2(ship));
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {

        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.initElite(stats, skill);
            float pad = 5f;
            float pads = 2f;
            info.addPara(" %s ballistic and energy weapon damage", pad, WthC_ColorData.DUST_RED, new String[]{"+5%"});
            info.addPara(" %s ballistic and energy weapon rate of fire", pads, WthC_ColorData.CROSS_GARY, new String[]{"+5%"});
            info.addPara(" %s soft flux dissipation when shield is active", pads, WthC_ColorData.IE_WHITE, new String[]{"+5%"});
            info.addPara(" %s ship damage reduction", pads, WthC_ColorData.DEEP_BLUE, new String[]{"+5%"});
            info.addPara(" %s ship time flow rate", pads, WthC_ColorData.SOUL_GREEN_D, new String[]{"+5%"});
            info.addPara(" %s ship maximum speed", pads, WthC_ColorData.EIF_ORANGE, new String[]{"+5%"});

            info.addPara("When ship hull is at %s , all bonuses increase to %s ", pad, WthC_ColorData.HIGH_BLUE, new String[]{"<50%","10%"});
        }
    }
}
