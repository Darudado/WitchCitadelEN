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

// 原版战斗耐力修改

public class CrimsonContract {




    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getAutofireAimAccuracy().modifyPercent(id, 50f);
            stats.getCRLossPerSecondPercent().modifyMult(id, 0.75f);
            stats.getCrewLossMult().modifyMult(id, 0.85f);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 10.0F;
            float pads = 2.0F;
            info.addPara("Increase %s weapon autofire accuracy", pad, Misc.getPositiveHighlightColor(), new String[]{"50%"});
            info.addPara("Reduce %s CR degradation rate after peak time", pads, Misc.getPositiveHighlightColor(), new String[]{"25%"});
            info.addPara("Reduce %s crew lost in combat", pads, Misc.getPositiveHighlightColor(), new String[]{"15%"});
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
                stats.getPeakCRDuration().modifyPercent(id, 15f);
                stats.getSuppliesPerMonth().modifyPercent(id, 15f);
                stats.getFuelUseMod().modifyMult(id, 0.85f);
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 10.0F;
                float pads = 2.0F;
                info.addPara("Increase %s ship peak performance time", pad, Misc.getPositiveHighlightColor(), new String[]{"15%"});
                info.addPara("Reduce %s ship fuel usage", pads, Misc.getPositiveHighlightColor(), new String[]{"20%"});
                info.addPara("Increase %s monthly supply consumption", pads, Misc.getNegativeHighlightColor(), new String[]{"15%"});

            }
        }


    }
