//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.beam;

import com.fs.starfarer.api.combat.BeamAPI;
import com.fs.starfarer.api.combat.BeamEffectPlugin;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import org.lwjgl.util.vector.Vector2f;

import java.awt.Color;

//抄了一手地质协会变色
public class LiLanceDeepBlue implements BeamEffectPlugin {
    private IntervalUtil fireInterval = new IntervalUtil(1F, 1F);
    private float timer = (float)Math.random() * 3f;
    private boolean wasZero = true;

    private static Color colorSwitch(float blender) {
        Color color;
        blender %= 3f;

        if (blender < 1f) {
            color = Misc.interpolateColor(WthC_ColorData.DEEP_BLUE, WthC_ColorData.MID_BLUE, blender);
        } else if (blender < 2f) {
            blender -= 1f;
            color = Misc.interpolateColor(WthC_ColorData.MID_BLUE, WthC_ColorData.LIGHT_BLUE, blender);
        } else {
            blender -= 2f;
            color = Misc.interpolateColor(WthC_ColorData.LIGHT_BLUE, WthC_ColorData.DEEP_BLUE, blender);
        }

        color = Misc.interpolateColor(color, WthC_ColorData.DEEP_BLUE, 0.5f);
        return color;
    }

    public LiLanceDeepBlue() {
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
                ShipAPI ship = (ShipAPI)target;
                boolean hitShield = target.getShield() != null && target.getShield().isWithinArc(beam.getTo());
                float pierceChance = ((ShipAPI)target).getHardFluxLevel() - 0.01F;
                pierceChance *= ship.getMutableStats().getDynamic().getValue("shield_pierced_mult");
                boolean piercedShield = hitShield && (float)Math.random() > pierceChance;
                if (!hitShield || piercedShield) {
                    Vector2f point = beam.getRayEndPrevFrame();
                    float emp = beam.getDamage().getFluxComponent();
                    float dam = beam.getDamage().getDamage() * 3.0F;
                    engine.spawnEmpArcPierceShields(beam.getSource(), point, beam.getDamageTarget(), beam.getDamageTarget(), DamageType.ENERGY, dam, emp, 100000.0F, "tachyon_lance_emp_impact", beam.getWidth() + 12.0F, color, WthC_ColorData.LIGHT_BLUE);
                }
            }
        }

    }
}
