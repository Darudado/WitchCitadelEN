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

import java.util.List;

// 原版战斗耐力修改
public class WitchSSoulPale {

    public static float PEAK_BONUS_PERCENT = -66f;


    public WitchSSoulPale() {
    }


    public static class SSoul2 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchSSOULP1";
        private final IntervalUtil SS = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;

        public SSoul2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            List<ShipAPI> ships = AIUtils.getNearbyEnemies(ship, 2000f);
            if (!ships.isEmpty()) {
                for (ShipAPI ship : ships) {
                    if (ship.isAlive()) {
                        if (!ship.isFighter()) {
                            if (SS.intervalElapsed()) {
                                ship.setHitpoints((float) (ship.getHitpoints() - (ship.getMaxHitpoints() * 0.001)));
                            }
                        }
                    }
                }
            }
        }
    }

    public static class SSoul1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchSSOULP2";
        private final IntervalUtil SS = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;

        public SSoul1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            if (ship.isAlive()) {
                if (ship.getHitpoints() > ship.getMaxHitpoints() * 0.45F) {
                    if (SS.intervalElapsed()){
                        ship.setHitpoints((float) (ship.getHitpoints() - ship.getMaxHitpoints() * 0.005));
                    }
                }

                if (ship.getHitpoints() < ship.getMaxHitpoints() * 0.44F) {
                    if (SS.intervalElapsed()){
                        ship.setHitpoints((float) (ship.getHitpoints() + ship.getMaxHitpoints() * 0.005));
                    }
                }
            }
        }
    }


        public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level1() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new SSoul1(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(SSoul1.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.init(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("Ship's remaining hull always shifts toward %s of maximum value", pad, WthC_ColorData.HIGH_BLUE, new String[]{"44%"});
                info.addPara("    Shift rate is %s of maximum hull per second", pads, WthC_ColorData.HIGH_BLUE, new String[]{"0.5%"});

            }
        }


        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new SSoul2(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(SSoul2.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("Enemy ships within %s %s hull", pad, WthC_ColorData.HIGH_BLUE, new String[]{"2000 range", "slowly lose"});
                info.addPara("    Rate of %s of maximum hull per second", pads, Misc.getPositiveHighlightColor(), new String[]{"0.1%"});
            }
        }
}
