package data.skills.RankB;

import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import org.lazywizard.lazylib.combat.CombatUtils;

import java.util.List;

// 原版战斗耐力修改
public class WitchElfPale {


    public WitchElfPale() {
    }

    private static final class State {
        int num;
        int num2;
        int num3;

        private State() {
            num = 0;
            num2 = 0;
            num3 = 0;
        }
    }

    public static class Elf2 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchElfP1";
        public Elf2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {

            float points1 = (-25 + (0.60F * (ship.getFluxLevel() * 100)));
            float TimeUp1 = Math.min(20f, points1);
            float points2 = (20 - (0.60F * (ship.getFluxLevel() * 100)));
            float TimeUp2 = Math.max(-25f, points2);
            int TimeUpInt = Math.round(ship.getFluxLevel() * 75);

            List<ShipAPI> ships = CombatUtils.getShipsWithinRange(ship.getLocation(), 1250f);
            if (!ships.isEmpty()) {
                for (ShipAPI ship : ships) {
                    if (ship.isAlive()) {
                        if (ship.isFighter()) {
                            if (ship.getOwner() == Misc.OWNER_PLAYER){
                                ship.getMutableStats().getTimeMult().modifyPercent(id,TimeUp1);
                                ship.getMutableStats().getDamageToFighters().modifyPercent(id,TimeUp1);
                                ship.getMutableStats().getDamageToMissiles().modifyPercent(id,TimeUp1);
                                ship.getMutableStats().getDamageToFrigates().modifyPercent(id,TimeUp1);
                                ship.getMutableStats().getDamageToDestroyers().modifyPercent(id,TimeUp1);
                                ship.getMutableStats().getDamageToCruisers().modifyPercent(id,TimeUp1);
                                ship.getMutableStats().getDamageToCapital().modifyPercent(id,TimeUp1);
                                ship.getMutableStats().getShieldDamageTakenMult().modifyPercent(id,-TimeUp1);
                                ship.getMutableStats().getArmorDamageTakenMult().modifyPercent(id,-TimeUp1);
                                ship.getMutableStats().getHullDamageTakenMult().modifyPercent(id,-TimeUp1);
                                ship.getMutableStats().getMaxSpeed().modifyPercent(id,TimeUp1);
                            } else {
                                ship.getMutableStats().getTimeMult().modifyPercent(id,TimeUp2);
                                ship.getMutableStats().getDamageToFighters().modifyPercent(id,TimeUp2);
                                ship.getMutableStats().getDamageToMissiles().modifyPercent(id,TimeUp2);
                                ship.getMutableStats().getDamageToFrigates().modifyPercent(id,TimeUp2);
                                ship.getMutableStats().getDamageToDestroyers().modifyPercent(id,TimeUp2);
                                ship.getMutableStats().getDamageToCruisers().modifyPercent(id,TimeUp2);
                                ship.getMutableStats().getDamageToCapital().modifyPercent(id,TimeUp2);
                                ship.getMutableStats().getShieldDamageTakenMult().modifyPercent(id,TimeUp2);
                                ship.getMutableStats().getArmorDamageTakenMult().modifyPercent(id,TimeUp2);
                                ship.getMutableStats().getHullDamageTakenMult().modifyPercent(id,TimeUp2);
                                ship.getMutableStats().getMaxSpeed().modifyPercent(id,TimeUp2);
                            }

                        }
                    }
                }
            }
        }
    }

    public static class Elf1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchElfP2";
        State sAll = new State();

        public Elf1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {

            if (ship.isAlive()) {
                if (ship.getFluxLevel() > 0.5F){
                    stats.getFluxDissipation().modifyPercent(id, 25F);
                } else {
                    stats.getFluxDissipation().modifyPercent(id, -25F);
                }
            }
        }
    }


        public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level1() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new Elf1(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(Elf1.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.init(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("When ship's current flux level is %s:", pad, WthC_ColorData.HIGH_BLUE, new String[]{"<50%"});
                info.addPara("     %s ship flux dissipation", pads, Misc.getNegativeHighlightColor(), new String[]{"-25%"});
                info.addPara("When ship's current flux level is %s:", pad, WthC_ColorData.HIGH_BLUE, new String[]{">50%"});
                info.addPara("     %s ship flux dissipation", pads, Misc.getPositiveHighlightColor(), new String[]{"+25%"});
            }
        }


        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new Elf2(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(Elf2.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("Interferes with %s within %s based on current flux level", pad, WthC_ColorData.HIGH_BLUE, new String[]{"all fighters", "1250 range"});
                info.addPara("    Changes time flow rate of all affected ships between %s", pads, WthC_ColorData.HIGH_BLUE, new String[]{"120%~75%"});
                info.addPara("    If fighters are %s:", pad, Misc.getPositiveHighlightColor(), new String[]{"friendly"});
                info.addPara("    Minimum %s when flux level is at %s", pads, Misc.getNegativeHighlightColor(), new String[]{"75%","0%"});
                info.addPara("    Maximum %s when flux level is at %s", pads, Misc.getPositiveHighlightColor(), new String[]{"120%","75%"});
                info.addPara("    If fighters are %s:", pad, Misc.getNegativeHighlightColor(), new String[]{"hostile"});
                info.addPara("    Maximum %s when flux level is at %s", pads, Misc.getNegativeHighlightColor(), new String[]{"120%","0%"});
                info.addPara("    Minimum %s when flux level is at %s", pads, Misc.getPositiveHighlightColor(), new String[]{"75%","75%"});
            }
        }
}
