package data.skills;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;

// 原版战斗耐力修改

public class Dali {




    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
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
            float pad = 10.0F;
            float pads = 2.0F;
            info.addPara("When dealing damage, damage ratio becomes a terrifying %s", pad, Misc.getPositiveHighlightColor(), new String[]{"100%"});
            info.addPara("When taking damage, disdainfully reduces damage by %s", pads, Misc.getPositiveHighlightColor(), new String[]{"0%"});
            info.addPara("Ship's time flow rate becomes a blazing %s", pads, Misc.getPositiveHighlightColor(), new String[]{"100%"});
            info.addPara("Increase %s damage dealt after combat", pads, Misc.getPositiveHighlightColor(), new String[]{"10000%"});
            info.addPara("Reduce %s damage taken after combat", pads, Misc.getPositiveHighlightColor(), new String[]{"50000%"});
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
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 10.0F;
                float pads = 2.0F;
                info.addPara("When dealing kinetic damage, deals %s additional hits at %s multiplier", pad, Misc.getPositiveHighlightColor(), new String[]{"0*999","114%"});
                info.addPara("When dealing high-explosive damage, deals %s additional hits at %s multiplier", pads, Misc.getPositiveHighlightColor(), new String[]{"0*999","514%"});
                info.addPara("When dealing energy damage, deals %s additional hits at %s multiplier", pads, Misc.getPositiveHighlightColor(), new String[]{"0*999","1919%"});
                info.addPara("When dealing fragmentation damage, deals %s additional hits at %s multiplier", pads, Misc.getPositiveHighlightColor(), new String[]{"0*999","810%"});

            }
        }


    }
