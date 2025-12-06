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
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import org.lazywizard.lazylib.VectorUtils;

import java.util.ArrayList;
import java.util.List;


// 原版战斗耐力修改
public class WitchIEPale {


    public WitchIEPale() {
    }

    public static class IE2 implements AdvanceableListener {
        private List<DamagingProjectileAPI> projs =new ArrayList<>();
        // private final IntervalUtil interval = new IntervalUtil(0.05f,0.05f);
        private ShipAPI thisship;
        // private boolean fired =false;

        private final String  key = "Wthc_whatever";
        //   private final String  key2 = "fuckoff_ballistic_as_beam";

        //效果水平和范围
        private final float effective_range = 500f;
        private final float effect =0.5f;

        public IE2(ShipAPI ship){
            thisship = ship;
        }
        public void updateprojlist(){
            projs.clear();
            for (DamagingProjectileAPI proj : Global.getCombatEngine().getProjectiles())
            {
                if(proj.getOwner()+thisship.getOwner()!=1)return;
                float distance = Misc.getDistance(proj.getLocation(),thisship.getLocation());
                if (distance <= effective_range)
                {
                    projs.add(proj);
                }
                if(!proj.getCustomData().containsKey(key)){
                    proj.setCustomData(key,proj.getVelocity().length());

                }
            /*
            if(proj.getSpawnType() == ProjectileSpawnType.BALLISTIC_AS_BEAM||
                    proj.getSpawnType() == ProjectileSpawnType.PLASMA){
                if(!proj.getCustomData().containsKey(key2)){
                    proj.setCustomData(key2,proj.getLocation());

                }else {
                    proj.getCustomData().put(key2,proj.getLocation());
                }
            }

             */
            }
        }

        @Override
        public void advance(float amount) {
            if(Global.getCombatEngine().isPaused())return;
            if(thisship.isHulk()||!thisship.isAlive()){
                        if(!projs.isEmpty()){
                            List<DamagingProjectileAPI> thiscopy = new ArrayList<>(projs);
                            for(DamagingProjectileAPI proj: thiscopy) {

                                if (proj != null && proj.getCustomData().containsKey(key)) {
                                    VectorUtils.resize(proj.getVelocity(),
                                            (float) proj.getCustomData().get(key));

                                }

                            }
                        }
                        thisship.removeListenerOfClass(IE2.class);
                        return;
            }
            //interval.advance(amount);
            boolean shouldupdate =false;
            // if(interval.intervalElapsed()){
            //      shouldupdate = true;
            //  }
            //  if(!fired){
            //      fired =true;
            //      shouldupdate =true;
            //  }
            //  if(shouldupdate){
            updateprojlist();
            //  }
            if (thisship.getShield() != null) {
                if (thisship.getShield().isOn()) {
                    if (!projs.isEmpty()) {
                        List<DamagingProjectileAPI> thiscopy = new ArrayList<>(projs);
                        for (DamagingProjectileAPI proj : thiscopy) {
                            if (proj == null || !Global.getCombatEngine().isEntityInPlay(proj)) {

                                if (proj != null && proj.getCustomData().containsKey(key)) {
                                    VectorUtils.resize(proj.getVelocity(),
                                            (float) proj.getCustomData().get(key), proj.getVelocity());
                                    proj.getCustomData().remove(key);
                                }
                                //   Global.getLogger(this.getClass()).info("removed");
                                projs.remove(proj);
                                continue;
                            }
                            if (Misc.getDistance(proj.getLocation(), thisship.getLocation()) > effective_range + 20f) {
                                if (proj.getCustomData().containsKey(key)) {
                                    VectorUtils.resize(proj.getVelocity(),
                                            (float) proj.getCustomData().get(key), proj.getVelocity());
                                    proj.getCustomData().remove(key);

                                }
                                projs.remove(proj);
                                continue;
                            }

                            float originalspeed = (float) proj.getCustomData().get(key);
                /*
                if(proj.getSpawnType() == ProjectileSpawnType.BALLISTIC_AS_BEAM||
                        proj.getSpawnType() == ProjectileSpawnType.PLASMA){
                    if(proj.getCustomData().containsKey(key2)){
                        Vector2f lastframeloc = (Vector2f) proj.getCustomData().get(key2);
                        Vector2f v = Misc.getUnitVectorAtDegreeAngle(proj.getFacing());
                        v.scale(proj.getMoveSpeed()*amount*effect);
                        Vector2f.add(lastframeloc,v,proj.getLocation());
                        v.normalise();
                        v.scale(proj.getProjectileSpec().getLength()*effect*0.001F);
                        Vector2f.add(proj.getLocation(),v,proj.getTailEnd());
                        proj.getCustomData().put(key2,proj.getLocation());
                    }
                }

                 */
                            if (proj.getVelocity().length() != 0) {
                                proj.getVelocity().normalise();
                                proj.getVelocity().scale(originalspeed * effect);
                            }

                            ///  Global.getLogger(I_WILL_CRUSH_YOU.class).info("resize:"+ proj.getVelocity());
                        }
                    }
                }
            }
        }
    }

    public static class IE1 implements AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        protected String id = "WitchIEP2";

        public IE1(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {

            if (ship.isAlive()) {
                stats.getMaxSpeed().modifyPercent(id, -33F);
                ship.getMutableStats().getDamageToFighters().modifyPercent(id,10F);
                ship.getMutableStats().getDamageToMissiles().modifyPercent(id,10F);
                ship.getMutableStats().getDamageToFrigates().modifyPercent(id,10F);
                ship.getMutableStats().getDamageToDestroyers().modifyPercent(id,10F);
                ship.getMutableStats().getDamageToCruisers().modifyPercent(id,10F);
                ship.getMutableStats().getDamageToCapital().modifyPercent(id,10F);
                ship.getMutableStats().getShieldDamageTakenMult().modifyMult(id,0.9F);
                ship.getMutableStats().getArmorDamageTakenMult().modifyMult(id,0.9F);
                ship.getMutableStats().getHullDamageTakenMult().modifyMult(id,0.9F);
            }
        }
    }


        public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level1() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new IE1(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(IE1.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.init(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara(" %s ship maximum speed", pad, Misc.getNegativeHighlightColor(), new String[]{"-33%"});
                info.addPara(" %s damage dealt by ship", pads, Misc.getPositiveHighlightColor(), new String[]{"+10%"});
                info.addPara(" %s damage taken by ship", pads, Misc.getPositiveHighlightColor(), new String[]{"-10%"});
            }
        }


        public static class Level2 extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
            public Level2() {
            }

            public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.addListener(new IE2(ship));
            }

            public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
                ship.removeListenerOfClass(IE2.class);
            }

            public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66, float level) {
            }

            public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id66) {
            }

            public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
                this.initElite(stats, skill);
                float pad = 5.0F;
                float pads = 2.0F;
                info.addPara("When ship's shields are %s:", pad, WthC_ColorData.HIGH_BLUE, new String[]{"active"});
                info.addPara("    Most projectiles and missiles within %s are slowed by %s", pads, WthC_ColorData.HIGH_BLUE, new String[]{"500 range","50%"});
            }
        }
}
