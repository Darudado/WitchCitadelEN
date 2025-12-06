package data.skills.RankB;

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
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import org.lazywizard.lazylib.combat.AIUtils;
import org.lazywizard.lazylib.combat.CombatUtils;

import java.util.List;

// 原版战斗耐力修改
public class WitchCrossPale {


    public WitchCrossPale() {
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

    public static class Cross2 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchCrossP1";
        public Cross2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            CombatEngineAPI engine = Global.getCombatEngine();
            float points1 = (-25 + (0.60F * (ship.getFluxLevel() * 100)));
            float TimeUp1 = Math.min(20f, points1);
            float points2 = (20 - (0.60F * (ship.getFluxLevel() * 100)));
            float TimeUp2 = Math.max(-25f, points2);
            int TimeUpInt = Math.min(100, Math.round(ship.getFluxLevel() * 133F));
            if (ship == engine.getPlayerShip()) {
                engine.maintainStatusForPlayerShip("WitchCrossPale", "graphics/icons/hullsys/GatherStarCore.png", "Space-Time Interference", "Flux Efficiency" + TimeUpInt + " " + "%", false);
            }
            List<ShipAPI> ships = CombatUtils.getShipsWithinRange(ship.getLocation(), 1250f);
            if (!ships.isEmpty()) {
                for (ShipAPI ship : ships) {
                    if (ship.isAlive()) {
                        if (!ship.isFighter()) {
                                ship.getMutableStats().getTimeMult().modifyPercent(id,TimeUp2);
                        }
                    }
                }
            }
        }
    }

    public static class Cross1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchCrossP2";
        private final IntervalUtil SS = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;
        State sAll = new State();

        public Cross1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            if (this.ship.getFullTimeDeployed() < 1f){
                sAll.num = 0;
                sAll.num2 = 0;
            }
            if (ship.isAlive()) {
                if (ship.getShield() != null){
                    if (ship.getShield().isOn()){
                        if (SS.intervalElapsed()){
                            sAll.num = 0;
                            sAll.num2++;
                        }
                        if (sAll.num2 >= 10){
                            if (ship.getHardFluxLevel() < 0.5F){
                                if (SS.intervalElapsed()){
                                    ship.getFluxTracker().setHardFlux((float) (ship.getFluxTracker().getHardFlux() + (ship.getMaxFlux() * 0.025)));
                                }
                            }
                        }
                    } else {
                        if (SS.intervalElapsed()){
                            sAll.num2 = 0;
                            sAll.num++;
                        }
                        if (sAll.num >= 3){
                            if (ship.getFluxLevel() > 0){
                                if (SS.intervalElapsed()){
                                    ship.getFluxTracker().setHardFlux((float) (ship.getFluxTracker().getHardFlux() - (ship.getMaxFlux() * 0.05)));
                                }
                            }
                            if (ship.getFluxLevel() < 0){
                                ship.getFluxTracker().setCurrFlux(0);
                            }
                        }
                    }
                }
            }
        }
    }


        public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level1() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new Cross1(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(Cross1.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.init(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("After ship maintains %s :", pad, WthC_ColorData.HIGH_BLUE, new String[]{"shield active for 10s"});
                info.addPara("    Ship hard flux level rapidly increases to %s of maximum at %s rate", pads, WthC_ColorData.HIGH_BLUE, new String[]{"50%","5% per second"});
                info.addPara("After ship maintains %s :", pads, WthC_ColorData.HIGH_BLUE, new String[]{"shield inactive for 3s"});
                info.addPara("    Ship hard flux level rapidly decreases at %s rate", pads, WthC_ColorData.HIGH_BLUE, new String[]{"10% per second"});
                info.addPara("(These effects only work on ships with shields)",WthC_ColorData.B_WHITE_L, pads);
            }
        }


        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new Cross2(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(Cross2.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("Interferes with all ships within %s based on current flux level %s ", pad, WthC_ColorData.HIGH_BLUE, new String[]{"1250 range", "all ships"});
                info.addPara("    Changes time flow rate of all affected ships between %s ", pads, WthC_ColorData.HIGH_BLUE, new String[]{"120%~75%"});
                info.addPara("    Reaches maximum %s when flux level is at %s ", pads, Misc.getNegativeHighlightColor(), new String[]{"120%","0%"});
                info.addPara("    Reaches minimum %s when flux level is at %s ", pads, Misc.getPositiveHighlightColor(), new String[]{"75%","75%"});
            }
        }
}
