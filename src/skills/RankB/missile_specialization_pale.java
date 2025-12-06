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
public class missile_specialization_pale {



    public missile_specialization_pale() {
    }


    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            stats.getMissileTurnAccelerationBonus().modifyPercent(id66, 25F);
            stats.getMissileMaxTurnRateBonus().modifyPercent(id66, 25F);
            stats.getMissileAccelerationBonus().modifyPercent(id66, 25F);
            stats.getMissileMaxSpeedBonus().modifyPercent(id66, 25F);
            stats.getMissileHealthBonus().modifyPercent(id66, 50F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            stats.getMissileTurnAccelerationBonus().unmodify(id66);
            stats.getMissileMaxTurnRateBonus().unmodify(id66);
            stats.getMissileAccelerationBonus().unmodify(id66);
            stats.getMissileMaxSpeedBonus().unmodify(id66);
            stats.getMissileHealthBonus().unmodify(id66);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara(" %s missile maneuverability and maximum speed", pad, Misc.getPositiveHighlightColor(), new String[]{"+25%"});
            info.addPara(" %s missile health", pads, Misc.getPositiveHighlightColor(), new String[]{"+50%"});
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
                stats.getMissileWeaponDamageMult().modifyPercent(id66, 15F);
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
                stats.getMissileWeaponDamageMult().unmodify(id66);
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara(" %s missile damage", pad, Misc.getPositiveHighlightColor(), new String[]{"+25%"});

            }
        }


    }
