package data.skills;

import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;

// 原版战斗耐力修改

public class SENetLink {


    public static float CREW_PERCENT = 50.0F;
    public static final float SUPPLY_USE_MULT = 0.0F;
    public static float MAX_CR_PENALTY = 0.15F;
    public static final float PEAK_BONUS_PERCENT = 20f;
    public static final float DEGRADE_REDUCTION_PERCENT = -100f;

    public SENetLink() {
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

            info.addPara(" %s ship CR cap", pad, Misc.getPositiveHighlightColor(), "+15%");
            info.addPara(" %s monthly maintenance cost", pads, Misc.getNegativeHighlightColor(), "+50%");

            info.addPara("Ship no longer requires crew (still not considered automated ship)", WthC_ColorData.HIGH_BLUE, pad);
        }

        public ScopeDescription getScopeDescription() {
            return ScopeDescription.PILOTED_SHIP;
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
            stats.getPeakCRDuration().modifyPercent(id, PEAK_BONUS_PERCENT);
            stats.getCRLossPerSecondPercent().modifyMult(id, 1f - DEGRADE_REDUCTION_PERCENT / 100f);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.initElite(stats, skill);
            float pad = 5f;
            float pads = 2f;
            info.addPara(" %s ship peak time", pad, Misc.getPositiveHighlightColor(), "+20%");
            info.addPara(" %s CR decay rate after peak time", pads, Misc.getNegativeHighlightColor(), "+100%");
        }
    }
}
