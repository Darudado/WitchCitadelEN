package data.skills.RankB;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.combat.listeners.ApplyDamageResultAPI;
import com.fs.starfarer.api.combat.listeners.DamageListener;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import java.util.HashMap;
import java.util.Map;

// 原版战斗耐力修改
public class WitchRRAPale {


    public WitchRRAPale() {
    }
    private final static class Countandother {

        //这俩没用， 可以自行拓展成是否开启和冷却之类的
        boolean isActive;
        float timer;
        float damageRe;
        // 能量计数
        int energynum;

        public void setDamageRe(float damageRe) {
            this.damageRe = damageRe;
        }

        public void setEnergynum(int energynum) {
            this.energynum = energynum;
        }

        private Countandother() {
            isActive = false;
            timer = 0f;
            damageRe = 0f;
            energynum = 0;
        }
    }
    public static class RRA2 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchRRAP1";
        private static final String key = "Wthc_damagereport";

        private final IntervalUtil SS = new IntervalUtil(0.1F, 0.1F);
        private boolean runOnce = false;
        public RRA2(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public static class what_the_hel_is_it_listener implements DamageListener {
            public CombatEngineAPI engine;
            public ShipAPI ship;
            public what_the_hel_is_it_listener(CombatEngineAPI engine){
                this.engine = engine;
            }

            public void reportDamageApplied(Object source, CombatEntityAPI target, ApplyDamageResultAPI result) {
                if (engine == null) return;
                if (!(source instanceof ShipAPI)) return;
                if (!(target instanceof ShipAPI)) return;
                if (source == target) return;
                if (!((ShipAPI) target).isAlive()) return;
                if (result.isDps()) return;
                ShipAPI ship = (ShipAPI) source;
                //这里放置判断舰船是否满足受到增益的条件
                //例如如果是船插可以 if(!ship.getVariant().hasHullMod(什么什么))return;
                //如果是技能，可以在技能的apply处添加customdata，比如 ship.setCustomData(enablekey,true);
                //在unapply处添加 ship.getCustomData().remove(enablekey);
                // 然后这里判断if(!ship.getCustomData().containsKey(enablekey))return;

                if (!ship.isAlive()) return;
                if (target.getOwner() == ship.getOwner()) return;
                float damage = result.getDamageToShields()+ result.getTotalDamageToArmor() + result.getDamageToHull();
                if (engine.getCustomData().containsKey(key)) {
                    Map<ShipAPI, Countandother> currState;
                    currState = (Map<ShipAPI, Countandother>) engine.getCustomData().get(key);
                    if (currState.containsKey(ship)) {

                        //这里获得了目前舰船的计数器
                        Countandother c = currState.get(ship);
                        float all = c.damageRe+ damage;
                        //每40f增加一点
                        int count = (int)(all/40f);
                        all = all%40f;
                        c.setDamageRe(all);
                        c.setEnergynum(c.energynum+count);
                    }
                }
            }

        }
        public void advance(float amount) {
            CombatEngineAPI engine = Global.getCombatEngine();
            this.SS.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }

            if (!engine.getCustomData().containsKey(key)) {
                engine.getCustomData().put(key, new HashMap<ShipAPI, Countandother>());
            }
            if (!engine.getListenerManager().hasListenerOfClass(what_the_hel_is_it_listener.class)) {
                engine.getListenerManager().addListener(new what_the_hel_is_it_listener(engine));
            }

            if (ship.isAlive()) {
                Map<ShipAPI, Countandother> currState = (Map<ShipAPI, Countandother>) engine.getCustomData().get(key);
                if (!currState.containsKey(ship)) {
                    currState.put(ship, new Countandother());
                }
            }

            //这里是示例获得能量计数

            if (engine.getCustomData().containsKey(key)) {
                Map<ShipAPI, Countandother> currState = (Map<ShipAPI, Countandother>) engine.getCustomData().get(key);
                if (currState.containsKey(ship)) {
                    //这里获得了目前舰船的计数器
                    Countandother c = currState.get(ship);
                    //然后你可以做任何事情

                    if (c.energynum > 20){
                        if (ship.getHitpoints() < ship.getMaxHitpoints() * 0.4f){
                            if (SS.intervalElapsed()){
                                ship.setHitpoints(ship.getHitpoints() + 20F);
                                c.energynum-=20f;
                            }
                        } else {
                            if (ship.getFluxTracker().getFluxLevel() > 0.8F){
                                if (SS.intervalElapsed()){
                                    ship.getFluxTracker().setCurrFlux(ship.getCurrFlux() - 100F);
                                    c.energynum-=20F;
                                }
                            }
                        }
                    }

                    int energy = c.energynum;
                    if (ship == engine.getPlayerShip()) {
                        if (energy > 0){
                            engine.maintainStatusForPlayerShip("WitchDustPale", "graphics/icons/hullsys/GatherStarCore.png", "Shattered Blue Rose", "Current energy points: " + energy, false);
                        } else {
                            engine.maintainStatusForPlayerShip("WitchDustPale", "graphics/icons/hullsys/GatherStarCore.png", "Shattered Blue Rose", "No energy points", true);
                        }
                    }
                }
            }


            //功能待加入
        }
    }

    public static class RRA1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchRRAP2";

        public RRA1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {

            if (ship.isAlive()) {
                ship.getMutableStats().getDamageToFighters().modifyPercent(id,-15F);
                ship.getMutableStats().getDamageToMissiles().modifyPercent(id,-15F);
                ship.getMutableStats().getDamageToFrigates().modifyPercent(id,-15F);
                ship.getMutableStats().getDamageToDestroyers().modifyPercent(id,-15F);
                ship.getMutableStats().getDamageToCruisers().modifyPercent(id,-15F);
                ship.getMutableStats().getDamageToCapital().modifyPercent(id,-15F);
                ship.getMutableStats().getShieldDamageTakenMult().modifyMult(id,1.15F);
                ship.getMutableStats().getArmorDamageTakenMult().modifyMult(id,1.15F);
                ship.getMutableStats().getHullDamageTakenMult().modifyMult(id,1.15F);
            }
        }
    }


        public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level1() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new RRA1(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(RRA1.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.init(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara(" %s damage dealt by ship", pad, Misc.getNegativeHighlightColor(), new String[]{"-15%"});
                info.addPara(" %s damage taken by ship", pads, Misc.getNegativeHighlightColor(), new String[]{"+15%"});
            }
        }


        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new RRA2(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(RRA2.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("Ship gains %s energy point for every %s dealt", pad, WthC_ColorData.HIGH_BLUE, new String[]{"1", "40 damage"});
                info.addPara("    When ship's hull is %s, consumes energy points at %s per second to rapidly restore %s hull", pads, WthC_ColorData.HIGH_BLUE, new String[]{"<40%","200 points","100%"});
                info.addPara("    When ship's flux level is %s, consumes energy points at %s per second to rapidly dissipate %s flux", pads, WthC_ColorData.HIGH_BLUE, new String[]{">80%","200 points","500%"});
                info.addPara("    These two processes will not %s, and will prioritize %s", pads, WthC_ColorData.HIGH_BLUE, new String[]{"occur simultaneously","hull restoration"});
            }
        }
}
