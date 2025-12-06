//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.beam;

import com.fs.starfarer.api.combat.BeamAPI;
import com.fs.starfarer.api.combat.BeamEffectPlugin;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.util.TimeoutTracker;
import java.util.List;
import org.lwjgl.util.vector.Vector2f;

//从引力子束修改
public class BreezeTacticalLaserEffect implements BeamEffectPlugin {
    public static float EFFECT_DUR = 1.0F;
    public static float DAMAGE_PERCENT_ONE = 2.5F;
    public static float DAMAGE_PERCENT_TWO = 5.0F;
    public static float DAMAGE_PERCENT_THREE = 7.5F;
    public static float DAMAGE_PERCENT_FOUR = 10.0F;
    public static float DAMAGE_PERCENT_FIVE = 12.5F;
    public static float DAMAGE_PERCENT_SIX = 15.0F;
    public static float DAMAGE_PERCENT_SEVEN = 17.5F;
    public static float DAMAGE_PERCENT_EIGHT = 20.0F;

    protected boolean wasZero = true;
    public static String DAMAGE_MOD_ID = "breezetacticallaser_dam_mod";

    public BreezeTacticalLaserEffect() {
    }

    public void advance(float amount, CombatEngineAPI engine, BeamAPI beam) {
        CombatEntityAPI target = beam.getDamageTarget();
        if (target instanceof ShipAPI && beam.getBrightness() >= 1.0F && beam.getWeapon() != null) {
            float dur = beam.getDamage().getDpsDuration();
            if (!this.wasZero) {
                dur = 0.0F;
            }

            this.wasZero = beam.getDamage().getDpsDuration() <= 0.0F;
            if (dur > 0.0F) {
                boolean hitShield = target.getShield() != null && target.getShield().isWithinArc(beam.getTo());
                if (hitShield) {
                    ShipAPI ship = (ShipAPI)target;
                    if (!ship.hasListenerOfClass(BreezeTacticalLaserDamageTakenMod.class)) {
                        ship.addListener(new BreezeTacticalLaserDamageTakenMod(ship));
                    }

                    List<BreezeTacticalLaserDamageTakenMod> listeners = ship.getListeners(BreezeTacticalLaserDamageTakenMod.class);
                    if (listeners.isEmpty()) {
                        return;
                    }

                    BreezeTacticalLaserDamageTakenMod listener = (BreezeTacticalLaserDamageTakenMod)listeners.get(0);
                    listener.notifyHit(beam.getWeapon());
                }
            }
        }

    }

    public static class BreezeTacticalLaserDamageTakenMod implements AdvanceableListener {
        protected ShipAPI ship;
        protected TimeoutTracker<WeaponAPI> recentHits = new TimeoutTracker();

        public BreezeTacticalLaserDamageTakenMod(ShipAPI ship) {
            this.ship = ship;
        }

        public void notifyHit(WeaponAPI w) {
            this.recentHits.add(w, BreezeTacticalLaserEffect.EFFECT_DUR, BreezeTacticalLaserEffect.EFFECT_DUR);
        }

        public void advance(float amount) {
            this.recentHits.advance(amount);
            int beams = this.recentHits.getItems().size();
            float bonus = 0.0F;
            if (beams == 1) {
                bonus = BreezeTacticalLaserEffect.DAMAGE_PERCENT_ONE;
            } else if (beams == 2) {
                bonus = BreezeTacticalLaserEffect.DAMAGE_PERCENT_TWO;
            } else if (beams == 3) {
                bonus = BreezeTacticalLaserEffect.DAMAGE_PERCENT_THREE;
            } else if (beams == 4) {
                bonus = BreezeTacticalLaserEffect.DAMAGE_PERCENT_FOUR;
            } else if (beams == 5) {
                bonus = BreezeTacticalLaserEffect.DAMAGE_PERCENT_FIVE;
            } else if (beams == 6) {
                bonus = BreezeTacticalLaserEffect.DAMAGE_PERCENT_SIX;
            } else if (beams == 7) {
                bonus = BreezeTacticalLaserEffect.DAMAGE_PERCENT_SEVEN;
            } else if (beams == 8) {
                bonus = BreezeTacticalLaserEffect.DAMAGE_PERCENT_EIGHT;
            }


            if (bonus > 0.0F) {
                this.ship.getMutableStats().getShieldDamageTakenMult().modifyMult(BreezeTacticalLaserEffect.DAMAGE_MOD_ID, 1.0F + bonus * 0.01F);
            } else {
                this.ship.removeListener(this);
                this.ship.getMutableStats().getShieldDamageTakenMult().unmodify(BreezeTacticalLaserEffect.DAMAGE_MOD_ID);
            }

        }

        public String modifyDamageTaken(Object param, CombatEntityAPI target, DamageAPI damage, Vector2f point, boolean shieldHit) {
            return null;
        }
    }
}
