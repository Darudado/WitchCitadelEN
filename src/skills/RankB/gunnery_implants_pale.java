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
public class gunnery_implants_pale {



    public gunnery_implants_pale() {
    }


    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            stats.getWeaponTurnRateBonus().modifyPercent(id66, 50F);
            stats.getAutofireAimAccuracy().modifyPercent(id66, 100F);
            stats.getMaxRecoilMult().modifyMult(id66, 0.5F);
            stats.getRecoilPerShotMult().modifyMult(id66, 0.5F);
            stats.getRecoilDecayMult().modifyMult(id66, 0.5F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            stats.getWeaponTurnRateBonus().unmodify(id66);
            stats.getAutofireAimAccuracy().unmodify(id66);
            stats.getMaxRecoilMult().unmodify(id66);
            stats.getRecoilPerShotMult().unmodify(id66);
            stats.getRecoilDecayMult().unmodify(id66);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara(" %s weapon turn rate ", pad, Misc.getPositiveHighlightColor(), new String[]{"+50%"});
            info.addPara(" %s autofire accuracy ", pads, Misc.getPositiveHighlightColor(), new String[]{"+100%"});
            info.addPara(" %s weapon recoil ", pads, Misc.getPositiveHighlightColor(), new String[]{"-50%"});
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
                stats.getEnergyWeaponRangeBonus().modifyPercent(id66, 20F);
                stats.getBallisticWeaponRangeBonus().modifyPercent(id66, 20F);
                stats.getMissileWeaponRangeBonus().modifyPercent(id66, 20F);
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
                stats.getEnergyWeaponRangeBonus().unmodify(id66);
                stats.getBallisticWeaponRangeBonus().unmodify(id66);
                stats.getMissileWeaponRangeBonus().unmodify(id66);
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara(" %s all weapon range ", pad, Misc.getPositiveHighlightColor(), new String[]{"+20%"});

            }
        }


    }
