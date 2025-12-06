package data.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.util.IntervalUtil;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;


//从弩张弹射修改
public class AbandonJourney_E extends BaseShipSystemScript {
    private final IntervalUtil TX = new IntervalUtil(1F, 1.5F);
    private boolean runOnce = false;
    private WeaponAPI Weapon;


    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        float amount = Global.getCombatEngine().getElapsedInLastFrame();
        CombatEngineAPI engine = Global.getCombatEngine();
        if (engine.isPaused()) {
            return;
        }

        ShipAPI ship = (ShipAPI) stats.getEntity();
        if (ship == null) {
            return;
        }

        if (!ship.isAlive()) {
            return;
        }
        this.TX.advance(amount);

        if (state == State.OUT) {
            stats.getMaxSpeed().unmodify(id); // to slow down ship to its regular top speed while powering drive down
            stats.getMaxTurnRate().unmodify(id);
        } else {
            stats.getMaxSpeed().modifyFlat(id, 75f);
            stats.getAcceleration().modifyPercent(id, 100f * effectLevel);
            stats.getDeceleration().modifyPercent(id, 100f * effectLevel);
            stats.getTurnAcceleration().modifyFlat(id, 20f * effectLevel);
            stats.getTurnAcceleration().modifyPercent(id, 100f * effectLevel);
            stats.getMaxTurnRate().modifyFlat(id, 10f);
            stats.getMaxTurnRate().modifyPercent(id, 50f);
            stats.getTimeMult().modifyPercent(id, 100f);
        }

        if (!runOnce) {
            for (WeaponAPI w : ship.getAllWeapons()) {
                if (w.getSlot().getId().equals("Weapon")) {
                    Weapon = w;
                }
            }
            runOnce = true;
        }

        Vector2f loc = MathUtils.getRandomPointInCircle(ship.getLocation(), 360f);
        if(TX.intervalElapsed()){
            engine.spawnProjectile(ship, Weapon, "WthC_starsnode_element_soil", loc, ship.getFacing(), null);
            Global.getSoundPlayer().playSound("resonator_fire", 0.5F, 1, loc, ship.getVelocity());
        }

    }
    public void unapply(MutableShipStatsAPI stats, String id) {
        stats.getMaxSpeed().unmodify(id);
        stats.getMaxTurnRate().unmodify(id);
        stats.getTurnAcceleration().unmodify(id);
        stats.getAcceleration().unmodify(id);
        stats.getDeceleration().unmodify(id);
        stats.getTimeMult().unmodify(id);
    }

    public StatusData getStatusData(int index, State state, float effectLevel) {
        if (index == 0) {
            return new StatusData("Improves Maneuverability", false);
        } else if (index == 1) {
            return new StatusData("+75 Top Speed", false);
        } else if (index == 2) {
            return new StatusData("Continuously Generates Denial Spheres", false);
        }
        return null;
    }
}
