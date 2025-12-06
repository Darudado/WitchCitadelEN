package data.skills.RankB;

import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

// 原版战斗耐力修改
public class target_analysis_pale {

    public static float MANEUVERABILITY_BONUS = 50;

    public target_analysis_pale() {
    }


    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            stats.getDamageToFrigates().modifyPercent(id66,15F);
            stats.getDamageToDestroyers().modifyPercent(id66,15F);
            stats.getDamageToCruisers().modifyPercent(id66,15F);
            stats.getDamageToCapital().modifyPercent(id66,15F);
            stats.getDamageToTargetWeaponsMult().modifyPercent(id66, 50F);
            stats.getDamageToTargetEnginesMult().modifyPercent(id66, 50F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            stats.getDamageToFrigates().unmodify(id66);
            stats.getDamageToDestroyers().unmodify(id66);
            stats.getDamageToCruisers().unmodify(id66);
            stats.getDamageToCapital().unmodify(id66);
            stats.getDamageToTargetWeaponsMult().unmodify(id66);
            stats.getDamageToTargetEnginesMult().unmodify(id66);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara(" %s damage to ships ", pad, Misc.getPositiveHighlightColor(), new String[]{"+15%"});
            info.addPara(" %s damage to engines and weapons ", pads, Misc.getPositiveHighlightColor(), new String[]{"+50%"});
        }
    }




        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
                stats.getMissileDamageTakenMult().modifyMult(id66, 0.85F);
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
                stats.getMissileDamageTakenMult().unmodify(id66);
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara(" %s ship missile damage taken ", pad, Misc.getPositiveHighlightColor(), new String[]{"-15%"});

            }
        }


    }
