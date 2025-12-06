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

//从烈焰去世器修改
public class GrandCruise extends BaseShipSystemScript {

    public static float SHIELD_BONUS = 30f;
    private final IntervalUtil TX = new IntervalUtil(0.05F, 0.1F);
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
            engine.spawnProjectile(ship, Weapon, "WthC_starsnode", loc, ship.getFacing(), null);
            Global.getSoundPlayer().playSound("resonator_fire", 1f, 0.5f, loc, ship.getVelocity());
        }

        if (state == State.OUT) {
            stats.getMaxSpeed().unmodify(id);
        } else {
            stats.getMaxSpeed().modifyFlat(id, 150f * effectLevel);
            stats.getAcceleration().modifyFlat(id, 150f * effectLevel);
            stats.getShieldDamageTakenMult().modifyMult(id, 1f - SHIELD_BONUS * 0.01f);
        }
    }
    public void unapply(MutableShipStatsAPI stats, String id) {
        stats.getMaxSpeed().unmodify(id);
        stats.getMaxTurnRate().unmodify(id);
        stats.getTurnAcceleration().unmodify(id);
        stats.getAcceleration().unmodify(id);
        stats.getDeceleration().unmodify(id);
        stats.getShieldDamageTakenMult().unmodify(id);
    }

    public StatusData getStatusData(int index, State state, float effectLevel) {
        if (index == 0) {
            return new StatusData("Increase engine output", false);
        } else if (index == 1) {
            return new StatusData("-30% shield damage taken", false);
        } else if (index == 2) {
            return new StatusData("Continuously generates homing projectiles", false);
        } else if (index == 3) {
            return new StatusData("Continuously generates hard flux", true);
        }
        return null;
    }
}
