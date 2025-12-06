package data.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicRender;

//从烈焰去世器修改
public class PACGrandCruise extends BaseShipSystemScript {

    public static float SHIELD_BONUS = 30f;
    private final IntervalUtil TX = new IntervalUtil(0.1F, 0.15F);
    private final IntervalUtil TX2 = new IntervalUtil(0.11F, 0.16F);
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
        this.TX2.advance(amount);

        if (!runOnce) {
            for (WeaponAPI w : ship.getAllWeapons()) {
                if (w.getSlot().getId().equals("Weapon")) {
                    Weapon = w;
                }
            }
            runOnce = true;
        }

        Vector2f loc = MathUtils.getRandomPointInCircle(ship.getLocation(), 500f);
        Vector2f loc2 = MathUtils.getRandomPointInCircle(ship.getLocation(), 500f);
        if(TX.intervalElapsed()){
            engine.spawnProjectile(ship, Weapon, "WthC_starsnode", loc, ship.getFacing(), null);
            Global.getSoundPlayer().playSound("resonator_fire", 1f, 0.5f, loc, ship.getVelocity());
        }
        if(TX2.intervalElapsed()){
            engine.spawnProjectile(ship, Weapon, "WthC_starsnode_elementS_wood_PAC", loc2, ship.getFacing(), null);
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
            return new StatusData("Continuously generates tracking projectiles", false);
        } else if (index == 3) {
            return new StatusData("Continuously generates hard flux", true);
        }
        return null;
    }
}
