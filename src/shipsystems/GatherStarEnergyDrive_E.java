package data.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipEngineControllerAPI.ShipEngineAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;

//从机动推进器修改
public class GatherStarEnergyDrive_E extends BaseShipSystemScript {

    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
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
            stats.getBeamWeaponRangeBonus().modifyFlat(id, 200f);
            stats.getFluxDissipation().modifyPercent(id, (-25f));
            stats.getTimeMult().modifyPercent(id, 100f);
        }

        if (stats.getEntity() instanceof ShipAPI && false) {
            ShipAPI ship = (ShipAPI) stats.getEntity();
            String key = ship.getId() + "_" + id;
            Object test = Global.getCombatEngine().getCustomData().get(key);
            if (state == State.IN) {
                if (test == null && effectLevel > 0.2f) {
                    Global.getCombatEngine().getCustomData().put(key, new Object());
                    ship.getEngineController().getExtendLengthFraction().advance(1f);
                    for (ShipEngineAPI engine : ship.getEngineController().getShipEngines()) {
                        if (engine.isSystemActivated()) {
                            ship.getEngineController().setFlameLevel(engine.getEngineSlot(), 1f);
                        }
                    }
                }
            } else {
                Global.getCombatEngine().getCustomData().remove(key);
            }
        }
    }
    public void unapply(MutableShipStatsAPI stats, String id) {
        stats.getMaxSpeed().unmodify(id);
        stats.getMaxTurnRate().unmodify(id);
        stats.getTurnAcceleration().unmodify(id);
        stats.getAcceleration().unmodify(id);
        stats.getDeceleration().unmodify(id);
        stats.getBeamWeaponRangeBonus().unmodify(id);
        stats.getFluxDissipation().unmodify(id);
        stats.getTimeMult().unmodify(id);
    }

    public StatusData getStatusData(int index, State state, float effectLevel) {
        if (index == 0) {
            return new StatusData("Increased Maneuverability", false);
        } else if (index == 1) {
            return new StatusData("+75 Top Speed", false);
        } else if (index == 2) {
            return new StatusData("+200 Beam Range", false);
        } else if (index == 3) {
            return new StatusData("-25% Flux Dissipation", true);

        }
        return null;
    }
}
