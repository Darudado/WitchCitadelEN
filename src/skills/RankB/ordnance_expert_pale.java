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
public class ordnance_expert_pale {



    public ordnance_expert_pale() {
    }


    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            stats.getEnergyRoFMult().modifyPercent(id66, 25F);
            stats.getBallisticRoFMult().modifyPercent(id66, 25F);
            stats.getWeaponHealthBonus().modifyPercent(id66,50F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            stats.getEnergyRoFMult().unmodify(id66);
            stats.getBallisticRoFMult().unmodify(id66);
            stats.getWeaponHealthBonus().unmodify(id66);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara(" %s ballistic and energy weapon rate of fire", pad, Misc.getPositiveHighlightColor(), new String[]{"+25%"});
            info.addPara(" %s all weapon durability", pads, Misc.getPositiveHighlightColor(), new String[]{"+50%"});
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
                stats.getEnergyWeaponDamageMult().modifyPercent(id66, 15F);
                stats.getBallisticWeaponDamageMult().modifyPercent(id66, 15F);
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
                stats.getMissileWeaponDamageMult().unmodify(id66);
                stats.getEnergyWeaponDamageMult().unmodify(id66);
                stats.getBallisticWeaponDamageMult().unmodify(id66);
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara(" %s all weapon damage", pad, Misc.getPositiveHighlightColor(), new String[]{"+15%"});

            }
        }


    }
