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


public class DR_Hanabi {
    public static float MISSILE_RANGE = 150f;
    public static float MISSILE_DAMAGE = 0.2f;
    public static float MISSILE_FLUXCOST = 0.2f;
    public static float MISSILE_MANEU = 0.75f;
    public static float MISSILE_ROF = 150f;
    public static float MISSILE_HEALTH = 0.4f;
    public static float MISSILE_AMMO = 300f;
    public DR_Hanabi() {
    }



    public static class Level1A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getMissileWeaponRangeBonus().modifyPercent(id,MISSILE_RANGE);
            stats.getMissileWeaponDamageMult().modifyMult(id,MISSILE_DAMAGE);
            stats.getMissileAccelerationBonus().modifyMult(id,MISSILE_MANEU);
            stats.getMissileTurnAccelerationBonus().modifyMult(id,MISSILE_MANEU);
            stats.getMissileGuidance().modifyMult(id,MISSILE_MANEU);
            stats.getMissileMaxSpeedBonus().modifyMult(id,MISSILE_MANEU);
            stats.getMissileRoFMult().modifyPercent(id,MISSILE_ROF);
            stats.getMissileWeaponFluxCostMod().modifyMult(id, MISSILE_FLUXCOST);
            stats.getMissileAmmoRegenMult().modifyPercent(id,MISSILE_ROF);
            stats.getMissileHealthBonus().modifyMult(id,MISSILE_HEALTH);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getMissileWeaponRangeBonus().unmodify(id);
            stats.getMissileWeaponDamageMult().unmodify(id);
            stats.getMissileAccelerationBonus().unmodify(id);
            stats.getMissileTurnAccelerationBonus().unmodify(id);
            stats.getMissileGuidance().unmodify(id);
            stats.getMissileMaxSpeedBonus().unmodify(id);
            stats.getMissileRoFMult().unmodify(id);
            stats.getMissileWeaponFluxCostMod().unmodify(id);
            stats.getMissileAmmoRegenMult().unmodify(id);
            stats.getMissileHealthBonus().unmodify(id);
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
            info.addPara(" %s missile weapon rate of fire and range", pad, Misc.getPositiveHighlightColor(), new String[]{"+150%"});
            info.addPara(" %s missile maneuverability", padS, Misc.getNegativeHighlightColor(), new String[]{"-25%"});
            info.addPara(" %s missile damage and flux generation", padS, Misc.getNegativeHighlightColor(), new String[]{"-80%"});
            info.addPara(" %s missile health", padS, Misc.getNegativeHighlightColor(), new String[]{"-60%"});
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
            if (stats.getVariant().hasHullMod("crimsonmodificationcross")){
                stats.getMissileAmmoBonus().modifyPercent(id,MISSILE_AMMO);
            }

        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getMissileAmmoBonus().unmodify(id);
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

            info.addPara(" %s missile ammo capacity, only affects ships with %s installed", pad, Misc.getPositiveHighlightColor(), new String[]{"+300%","Crimson Modification - Cross"});
        }
    }
}
