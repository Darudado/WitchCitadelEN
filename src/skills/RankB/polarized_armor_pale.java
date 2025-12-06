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
public class polarized_armor_pale {



    public polarized_armor_pale() {
    }


    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            stats.getEngineDamageTakenMult().modifyMult(id66,0.5F);
            stats.getWeaponDamageTakenMult().modifyMult(id66,0.5F);
            stats.getArmorBonus().modifyPercent(id66, 10F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            stats.getEngineDamageTakenMult().unmodify(id66);
            stats.getWeaponDamageTakenMult().unmodify(id66);
            stats.getArmorBonus().unmodify(id66);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara(" %s ship armor rating ", pad, Misc.getPositiveHighlightColor(), new String[]{"+10%"});
            info.addPara(" %s weapon and engine damage taken ", pads, Misc.getPositiveHighlightColor(), new String[]{"-50%"});
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
                stats.getEmpDamageTakenMult().modifyMult(id66,0.5F);
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
                stats.getEmpDamageTakenMult().unmodify(id66);
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara(" %s ship EMP damage taken", pad, Misc.getPositiveHighlightColor(), new String[]{"-50%"});

            }
        }


    }
