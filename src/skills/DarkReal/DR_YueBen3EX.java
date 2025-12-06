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
import data.util.WthC_ColorData;


public class DR_YueBen3EX {
    public static final float FLUX_COIL_MULT = 1.25f;
    private static final class State {
        float num5;

        private State() {
            num5 = 0;
        }
    }
    public DR_YueBen3EX() {
    }



    public static class Level1A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getDynamic().getMod("deployment_points_mod").modifyMult(id, 0.05f);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getDynamic().getMod("deployment_points_mod").unmodify(id);
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
            info.addPara("All ships will no longer be restricted by deployment points", WthC_ColorData.MYSTERIOUS_PURPLE,padS);
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
            stats.getHullBonus().modifyPercent(id, 20F);
            stats.getArmorBonus().modifyPercent(id, 10F);
            stats.getMissileAmmoRegenMult().modifyPercent(id, 30F);
            stats.getDamageToCapital().modifyPercent(id, 10F);
            stats.getDamageToCruisers().modifyPercent(id, 10F);
            stats.getDamageToDestroyers().modifyPercent(id, 10F);
            stats.getDamageToFrigates().modifyPercent(id, 10F);
            stats.getDamageToFighters().modifyPercent(id, 10F);
            stats.getDamageToMissiles().modifyPercent(id, 10F);
            stats.getOverloadTimeMod().modifyMult(id, 0.5F);

        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getHullBonus().unmodify(id);
            stats.getArmorBonus().unmodify(id);
            stats.getMissileAmmoRegenMult().unmodify(id);
            stats.getDamageToCapital().unmodify(id);
            stats.getDamageToCruisers().unmodify(id);
            stats.getDamageToDestroyers().unmodify(id);
            stats.getDamageToFrigates().unmodify(id);
            stats.getDamageToFighters().unmodify(id);
            stats.getDamageToMissiles().unmodify(id);
            stats.getOverloadTimeMod().unmodify(id);
            stats.getBallisticWeaponRangeBonus().unmodify(id);
            stats.getEnergyWeaponRangeBonus().unmodify(id);
            stats.getMissileWeaponRangeBonus().unmodify(id);
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

            info.addPara("All ships in combat gain %s :", pad, Misc.getHighlightColor(), new String[]{"following bonuses"});
            info.addPara("    %s ship base hull points", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
            info.addPara("    %s ship base armor value", pads, Misc.getPositiveHighlightColor(), new String[]{"+10%"});
            info.addPara("    %s missile weapon ammo regeneration rate", pads, Misc.getPositiveHighlightColor(), new String[]{"+30%"});
            info.addPara("    %s ship damage dealt", pads, Misc.getPositiveHighlightColor(), new String[]{"+10%"});
            info.addPara("    %s ship overload duration", pads, Misc.getPositiveHighlightColor(), new String[]{"-50%"});

        }
    }
}
