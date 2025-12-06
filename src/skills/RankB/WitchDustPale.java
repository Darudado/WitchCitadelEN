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
import org.lazywizard.lazylib.combat.AIUtils;

import java.util.List;

// 原版战斗耐力修改
public class WitchDustPale {

    public static float PEAK_BONUS_PERCENT = -66f;


    public WitchDustPale() {
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
    public static class Dust1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchDustP1";
        private final IntervalUtil SS = new IntervalUtil(1.0F, 1.0F);
        private boolean runOnce = false;
        State sAll = new State();

        public Dust1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            sAll.num = 0;
            CombatEngineAPI engine = Global.getCombatEngine();
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            List<ShipAPI> ships = AIUtils.getNearbyEnemies(ship, 2000f);
            if (!ships.isEmpty()){
                for (ShipAPI ship : ships){
                    if (ship.isAlive()){
                        if (!ship.isFighter()){
                            if (ship.getHitpoints() < ship.getMaxHitpoints()){
                                int Hitpointlvl = (int) ((1-(ship.getHitpoints() / ship.getMaxHitpoints())) * 10);
                                sAll.num += Hitpointlvl;
                            }
                        }
                    }
                }
            }
            stats.getDamageToCapital().modifyPercent(id, sAll.num);
            stats.getDamageToCruisers().modifyPercent(id, sAll.num);
            stats.getDamageToDestroyers().modifyPercent(id, sAll.num);
            stats.getDamageToFrigates().modifyPercent(id, sAll.num);
            if (ship == engine.getPlayerShip()) {
                engine.maintainStatusForPlayerShip("WitchDustPale", "graphics/icons/hullsys/GatherStarCore.png", "Final Oblivion", "Increases Damage Dealt " + sAll.num + " " + "%", false); }
        }
    }



    public static class Dust2 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchDustP2";
        State sAll = new State();
        public Dust2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            if (ship.isAlive()){
                if (ship.getHitpoints() > ship.getMaxHitpoints() * 0.5F){
                    stats.getDamageToCapital().modifyPercent(id, -10F);
                    stats.getDamageToCruisers().modifyPercent(id, -10F);
                    stats.getDamageToDestroyers().modifyPercent(id, -10F);
                    stats.getDamageToFrigates().modifyPercent(id, -10F);
                } else {
                    stats.getDamageToCapital().modifyPercent(id, 20F);
                    stats.getDamageToCruisers().modifyPercent(id, 20F);
                    stats.getDamageToDestroyers().modifyPercent(id, 20F);
                    stats.getDamageToFrigates().modifyPercent(id, 20F);
                }
            }
        }
    }

    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new Dust1(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(Dust1.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara("When ship's remaining hull is %s, %s damage to ships", pad, Misc.getNegativeHighlightColor(), new String[]{">50%","-15%"});
            info.addPara("When ship's remaining hull is %s, %s damage to ships", pads, Misc.getPositiveHighlightColor(), new String[]{"<50%","+20%"});
        }
    }




        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new Dust2(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(Dust2.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("Ship gains %s based on enemy ships' %s within %s", pad, Misc.getHighlightColor(), new String[]{"increased damage","hull lost","2000 range"});
                info.addPara("    For every %s hull lost by enemies in range, ship gains %s increased damage", pads, Misc.getPositiveHighlightColor(), new String[]{"10%","1%"});
                info.addPara("    This effect has no cap and doesn't count fighters or wreckage", Misc.getPositiveHighlightColor(), pads);
            }
        }


    }
