package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import java.awt.Color;
import java.util.List;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.listeners.ApplyDamageResultAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.combat.MissileAPI;
import com.fs.starfarer.api.loading.DamagingExplosionSpec;
import com.fs.starfarer.api.impl.combat.CombatEntityPluginWithParticles;
import org.lazywizard.lazylib.CollisionUtils;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lazywizard.lazylib.combat.AIUtils;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lazywizard.lazylib.combat.entities.AnchoredEntity;
import org.lwjgl.util.vector.Vector2f;
import com.fs.starfarer.api.util.IntervalUtil;


// Simple modification from other plugins

public class WthC_evilintegration extends BaseHullMod {
    public static final float TIME_MULT = 1.1f;
    public static final float CLONE_TIME = 0.2f;
    public static final String KEY = "evilintegration";
    public float whatthehellisthat = 6f;

    public WthC_evilintegration() {
    }
    public static class Periodictraininterval{
        IntervalUtil interval = new IntervalUtil(CLONE_TIME, CLONE_TIME);
    }

    @Override
    public void applyEffectsToFighterSpawnedByShip(ShipAPI fighter, ShipAPI ship, String id) {
        MutableShipStatsAPI stats = fighter.getMutableStats();

        stats.getTimeMult().modifyMult(id, TIME_MULT);
    }

    @Override
    public void advanceInCombat(ShipAPI ship, float amount) {
        if (ship == null) {
            return;
        }




        String key = KEY + "_" + ship.getId();
        Periodictraininterval data = (Periodictraininterval)  Global.getCombatEngine().getCustomData().get(key);
        if (data == null) {
            data = new Periodictraininterval();
            Global.getCombatEngine().getCustomData().put(key, data);
        }
        data.interval.advance(amount);


        MutableShipStatsAPI stats = ship.getMutableStats();
        String id = "evilintegration";

        float scale = ship.getMutableStats().getVentRateMult().getModifiedValue()
                * (ship.getMutableStats().getFluxDissipation().getModifiedValue() / 50f);
        if (ship.isAlive()) {
            stats.getTimeMult().modifyMult(id, TIME_MULT);
            if (ship.getFluxTracker().isVenting()) {
                float range = (float) Math.sqrt(ship.getFluxTracker().getCurrFlux()) * (float) Math.sqrt(scale) * 4f;
                List<ShipAPI> targets = AIUtils.getNearbyEnemies(ship, range);

                if (data.interval.intervalElapsed()) {
                    if (targets.size() > 0) {
                        ShipAPI target = (ShipAPI)targets.get(MathUtils.getRandom().nextInt(targets.size()));
                        Global.getCombatEngine().spawnDamagingExplosion(evilexplosion(scale * 40f), ship, MathUtils.getRandomPointInCircle(target.getLocation(), 100f));
                    }
                }
            }
            if(!ship.getFluxTracker().isOverloadedOrVenting() && ship.getFluxTracker().getFluxLevel()>0.8f) ship.giveCommand(ShipCommand.valueOf("VENT_FLUX"),ship.getMouseTarget(), 0);

            return;
        }
        stats.getTimeMult().unmodify(id);
    }



    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getDynamic().getMod("max_permanent_hullmods_mod").modifyFlat(id, 1.0F);
    }


    public DamagingExplosionSpec evilexplosion(float damage) {
        DamagingExplosionSpec Explosion=new DamagingExplosionSpec(
                0.05f, // duration
                300f, // radius
                150f, // coreRadius
                damage, // maxDamage
                damage/2, // minDamage
                CollisionClass.PROJECTILE_NO_FF, // collisionClass
                CollisionClass.PROJECTILE_FIGHTER, // collisionClassByFighter
                0.5f, // particleSizeMin
                0.5f, // particleSizeRange
                0.05f, // particleDuration
                300, // particleCount
                new Color(60, 45, 60, 200), // particleColor
                new Color(60, 45, 60, 150)  // explosionColor
        );
        Explosion.setDamageType(DamageType.ENERGY);
        Explosion.setUseDetailedExplosion(false);
        Explosion.setSoundSetId(null);
        return Explosion;
    }

}
