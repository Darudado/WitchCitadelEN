package data.skills.DarkReal;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


public class DR_Dalei {
    private static final class State {
        int num;
        int num2;
        int num3;
        float num5;

        private State() {
            num = 0;
            num2 = 0;
            num3 = 0;
            num5 = 0;

        }
    }

    public DR_Dalei() {
    }





    public static class Level1A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1A() {
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
            float pads = 2.0F;
            info.addPara("All ships have more modules and cannot be salvaged", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
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
            stats.getMissileMaxSpeedBonus().modifyPercent(id, 150F);
            stats.getMissileWeaponRangeBonus().modifyPercent(id,150F);
            stats.getMissileRoFMult().modifyPercent(id, 100F);
            stats.getMissileAmmoRegenMult().modifyPercent(id, 50F);
            stats.getMissileAmmoBonus().modifyPercent(id, 100F);
            stats.getMissileWeaponDamageMult().modifyPercent(id, 50F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getMissileMaxSpeedBonus().unmodify(id);
            stats.getMissileWeaponRangeBonus().unmodify(id);
            stats.getMissileRoFMult().unmodify(id);
            stats.getMissileAmmoRegenMult().unmodify(id);
            stats.getMissileAmmoBonus().unmodify(id);
            stats.getMissileWeaponDamageMult().unmodify(id);
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
            info.addPara("    %s missile speed", pads, Misc.getPositiveHighlightColor(), new String[]{"+150%"});
            info.addPara("    %s missile weapon range", pads, Misc.getPositiveHighlightColor(), new String[]{"+150%"});
            info.addPara("    %s missile reload rate", pads, Misc.getPositiveHighlightColor(), new String[]{"+50%"});
            info.addPara("    %s missile weapon rate of fire", pads, Misc.getPositiveHighlightColor(), new String[]{"+100%"});
            info.addPara("    %s missile weapon ammo", pads, Misc.getPositiveHighlightColor(), new String[]{"+100%"});
            info.addPara("    %s missile weapon damage", pads, Misc.getPositiveHighlightColor(), new String[]{"+50%"});

        }
    }
}
