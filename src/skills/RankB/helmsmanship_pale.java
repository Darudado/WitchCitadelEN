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
public class helmsmanship_pale {

    public static float MANEUVERABILITY_BONUS = 50;

    public helmsmanship_pale() {
    }


    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            stats.getAcceleration().modifyPercent(id66, MANEUVERABILITY_BONUS);
            stats.getDeceleration().modifyPercent(id66, MANEUVERABILITY_BONUS);
            stats.getTurnAcceleration().modifyPercent(id66, MANEUVERABILITY_BONUS * 2f);
            stats.getMaxTurnRate().modifyPercent(id66, MANEUVERABILITY_BONUS);
            stats.getMaxSpeed().modifyPercent(id66, 20F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            stats.getAcceleration().unmodify(id66);
            stats.getDeceleration().unmodify(id66);
            stats.getTurnAcceleration().unmodify(id66);
            stats.getMaxTurnRate().unmodify(id66);
            stats.getMaxSpeed().unmodify(id66);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara(" %s ship maneuverability ", pad, Misc.getPositiveHighlightColor(), new String[]{"+50%"});
            info.addPara(" %s ship maximum speed ", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
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
                stats.getAllowZeroFluxAtAnyLevel().modifyFlat(id66, 5f);
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
                stats.getAllowZeroFluxAtAnyLevel().unmodify(id66);
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("Zero flux boost active when flux level is below %s ", pad, Misc.getPositiveHighlightColor(), new String[]{"5%"});

            }
        }


    }
