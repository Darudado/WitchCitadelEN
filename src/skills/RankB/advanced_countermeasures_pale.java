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
public class advanced_countermeasures_pale {



    public advanced_countermeasures_pale() {
    }


    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            stats.getKineticArmorDamageTakenMult().modifyMult(id66, 0.5F);
            stats.getHighExplosiveShieldDamageTakenMult().modifyMult(id66,0.66F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            stats.getKineticArmorDamageTakenMult().unmodify(id66);
            stats.getHighExplosiveShieldDamageTakenMult().unmodify(id66);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara(" %s armor kinetic damage taken", pad, Misc.getPositiveHighlightColor(), new String[]{"-50%"});
            info.addPara(" %s shield high explosive damage taken", pads, Misc.getPositiveHighlightColor(), new String[]{"-33%"});
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
                stats.getDamageToFighters().modifyPercent(id66,66F);
                stats.getDamageToMissiles().modifyPercent(id66,66F);
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
                stats.getDamageToFighters().unmodify(id66);
                stats.getDamageToMissiles().unmodify(id66);
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara(" %s damage dealt to fighters and missiles", pad, Misc.getPositiveHighlightColor(), new String[]{"+66%"});

            }
        }


    }
