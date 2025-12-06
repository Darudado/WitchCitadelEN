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
public class field_modulation_pale {



    public field_modulation_pale() {
    }


    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            stats.getShieldDamageTakenMult().modifyMult(id66, 0.8F);
            stats.getHardFluxDissipationFraction().modifyFlat(id66,0.2F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            stats.getShieldDamageTakenMult().unmodify(id66);
            stats.getHardFluxDissipationFraction().unmodify(id66);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara(" %s shield damage taken ", pad, Misc.getPositiveHighlightColor(), new String[]{"-20%"});
            info.addPara(" %s hard flux dissipation when shield is active ", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
        }
    }




        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id67, float level) {
                stats.getShieldDamageTakenMult().modifyMult(id67, 0.95F);
                stats.getHardFluxDissipationFraction().modifyFlat(id67,0.05F);
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id67) {
                stats.getDamageToFighters().unmodify(id67);
                stats.getDamageToMissiles().unmodify(id67);
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara(" %s shield damage taken ", pad, Misc.getPositiveHighlightColor(), new String[]{"-5%"});
                info.addPara(" %s hard flux dissipation when shield is active ", pads, Misc.getPositiveHighlightColor(), new String[]{"+5%"});

            }
        }


    }
