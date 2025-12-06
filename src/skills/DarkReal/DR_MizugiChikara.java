package data.skills.DarkReal;


import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import static com.fs.starfarer.api.impl.campaign.ids.Stats.PHASE_CLOAK_FLUX_LEVEL_FOR_MIN_SPEED_MOD;


public class DR_MizugiChikara {
    public static float PHASE_EFFICIENCY = 25f;
    public static float FLUX_THRESHOLD_INCREASE_PERCENT = 300f;
    public static float CR_PERCENT  = 20f;
    public static float FLUX_COIL_MULT  = 0.6f;

    public DR_MizugiChikara() {
    }



    public static class Level1A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getAutofireAimAccuracy().modifyPercent(id, 50f);
            stats.getCRLossPerSecondPercent().modifyMult(id, 0.75f);

            stats.getPhaseCloakUpkeepCostBonus().modifyMult(id, 1f - PHASE_EFFICIENCY / 100f);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getAutofireAimAccuracy().unmodify(id);
            stats.getCRLossPerSecondPercent().unmodify(id);

            stats.getPhaseCloakUpkeepCostBonus().unmodify(id);
            stats.getPhaseCloakCooldownBonus().unmodify(id);
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
            float padS = 2.0F;
            info.addPara(" %s weapon accuracy when auto-firing", pad, Misc.getPositiveHighlightColor(), new String[]{"+50%"});
            info.addPara(" %s CR decay rate after peak performance time", padS, Misc.getPositiveHighlightColor(), new String[]{"-25%"});
            info.addPara(" %s phase coil flux upkeep", padS, Misc.getPositiveHighlightColor(), new String[]{"-25%"});
        }
    }
    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getPeakCRDuration().modifyPercent(id, 30f);
            stats.getMaxCombatReadiness().modifyFlat(id, CR_PERCENT * 0.01f, "Swimsuit Power");
            stats.getFluxCapacity().modifyMult(id, FLUX_COIL_MULT);

            stats.getDynamic().getMod(PHASE_CLOAK_FLUX_LEVEL_FOR_MIN_SPEED_MOD).modifyPercent(id, FLUX_THRESHOLD_INCREASE_PERCENT);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getPeakCRDuration().unmodify(id);
            stats.getMaxCombatReadiness().unmodify(id);
            stats.getFluxCapacity().unmodify(id);
            stats.getDynamic().getMod(PHASE_CLOAK_FLUX_LEVEL_FOR_MIN_SPEED_MOD).unmodify(id);
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
            float padS = 2.0F;
            info.addPara(" %s ship peak performance time", pad, Misc.getPositiveHighlightColor(), new String[]{"+30%"});
            info.addPara(" %s ship maximum CR", padS, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
            info.addPara(" %s phase coil speed reduction threshold", padS, Misc.getPositiveHighlightColor(), new String[]{"+300%"});
            info.addPara(" %s final flux capacity", padS, Misc.getNegativeHighlightColor(), new String[]{"-40%"});

        }
    }
}
