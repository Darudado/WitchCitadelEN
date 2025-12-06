//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.beam;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.TimeoutTracker;
import data.util.WthC_ColorData;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.util.List;

//抄了一手地质协会变色
public class NewMoonEffect implements BeamEffectPlugin {
    private IntervalUtil fireInterval = new IntervalUtil(1F, 1F);
    private float timer = (float)Math.random() * 3f;
    private boolean wasZero = true;

    public static float EFFECT_DUR = 1.0F;
    public static float DAMAGE_PERCENT_ONE = 2.5F;
    public static float DAMAGE_PERCENT_TWO = 5.0F;
    public static float DAMAGE_PERCENT_THREE = 7.5F;
    public static float DAMAGE_PERCENT_FOUR = 10.0F;
    public static float DAMAGE_PERCENT_FIVE = 12.5F;
    public static float DAMAGE_PERCENT_SIX = 15.0F;
    public static String DAMAGE_MOD_ID = "newmoon_dam_mod";

    private static Color colorSwitch(float blender) {
        Color color;
        blender %= 3f;

        if (blender < 1f) {
            color = Misc.interpolateColor(WthC_ColorData.MID_BLACK, WthC_ColorData.MID_WHITE, blender);
        } else if (blender < 2f) {
            blender -= 1f;
            color = Misc.interpolateColor(WthC_ColorData.MID_WHITE, WthC_ColorData.L_BLACK, blender);
        } else {
            blender -= 2f;
            color = Misc.interpolateColor(WthC_ColorData.L_BLACK, WthC_ColorData.MID_BLACK, blender);
        }

        color = Misc.interpolateColor(color, WthC_ColorData.MID_BLACK, 0.5f);
        return color;
    }

    public NewMoonEffect() {
    }

    public void advance(float amount, CombatEngineAPI engine, BeamAPI beam) {
        timer += amount * 5f;
        Color color = colorSwitch(timer);
        beam.setFringeColor(color);
        CombatEntityAPI target = beam.getDamageTarget();
        if (target instanceof ShipAPI && beam.getBrightness() >= 1.0F) {
            float dur = beam.getDamage().getDpsDuration();
            if (!this.wasZero) {
                dur = 0.0F;
            }

            this.wasZero = beam.getDamage().getDpsDuration() <= 0.0F;
            this.fireInterval.advance(dur);
            if (this.fireInterval.intervalElapsed()) {
                boolean hitShield = target.getShield() != null && target.getShield().isWithinArc(beam.getTo());
                if (!hitShield) {
                    Vector2f point = beam.getRayEndPrevFrame();
                    float emp = beam.getDamage().getFluxComponent() * 1.0F;
                    float dam = 0.1F;
                    engine.spawnEmpArcPierceShields(beam.getSource(), point, beam.getDamageTarget(), beam.getDamageTarget(), DamageType.ENERGY, dam, emp, 100000.0F, "tachyon_lance_emp_impact", beam.getWidth() + 9.0F, beam.getFringeColor(), beam.getCoreColor());
                }
            }
            }
        if (target instanceof ShipAPI && beam.getBrightness() >= 1.0F && beam.getWeapon() != null) {
            float dur = beam.getDamage().getDpsDuration();
            if (!this.wasZero) {
                dur = 0.0F;
            }

            if (dur > 0.0F) {
                boolean hitShield = target.getShield() != null && target.getShield().isWithinArc(beam.getTo());
                if (hitShield) {
                    ShipAPI ship = (ShipAPI)target;
                    if (!ship.hasListenerOfClass(NewMoonDamageTakenMod.class)) {
                        ship.addListener(new NewMoonDamageTakenMod(ship));
                    }

                    List<NewMoonEffect.NewMoonDamageTakenMod> listeners = ship.getListeners(NewMoonDamageTakenMod.class);
                    if (listeners.isEmpty()) {
                        return;
                    }

                    NewMoonEffect.NewMoonDamageTakenMod listener = (NewMoonDamageTakenMod)listeners.get(0);
                    listener.notifyHit(beam.getWeapon());
                }
            }
        }


        }

    public static class NewMoonDamageTakenMod implements AdvanceableListener {
        protected ShipAPI ship;
        protected TimeoutTracker<WeaponAPI> recentHits = new TimeoutTracker();

        public NewMoonDamageTakenMod(ShipAPI ship) {
            this.ship = ship;
        }

        public void notifyHit(WeaponAPI w) {
            this.recentHits.add(w, EFFECT_DUR, EFFECT_DUR);
        }

        public void advance(float amount) {
            this.recentHits.advance(amount);
            int beams = this.recentHits.getItems().size();
            float bonus = 0.0F;
            if (beams == 1) {
                bonus = DAMAGE_PERCENT_ONE;
            } else if (beams == 2) {
                bonus = DAMAGE_PERCENT_TWO;
            } else if (beams == 3) {
                bonus = DAMAGE_PERCENT_THREE;
            } else if (beams == 4) {
                bonus = DAMAGE_PERCENT_FOUR;
            } else if (beams == 5) {
                bonus = DAMAGE_PERCENT_FIVE;
            } else if (beams == 6) {
                bonus = DAMAGE_PERCENT_SIX;
            }


            if (bonus > 0.0F) {
                this.ship.getMutableStats().getArmorDamageTakenMult().modifyMult(DAMAGE_MOD_ID, 1.0F + bonus * 0.01F);
                this.ship.getMutableStats().getHullDamageTakenMult().modifyMult(DAMAGE_MOD_ID, 1.0F + bonus * 0.01F);
            } else {
                this.ship.removeListener(this);
                this.ship.getMutableStats().getArmorDamageTakenMult().unmodify(DAMAGE_MOD_ID);
                this.ship.getMutableStats().getHullDamageTakenMult().unmodify(DAMAGE_MOD_ID);
            }

        }

        public String modifyDamageTaken(Object param, CombatEntityAPI target, DamageAPI damage, Vector2f point, boolean shieldHit) {
            return null;
        }
    }
    }

