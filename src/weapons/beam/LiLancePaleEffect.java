package data.weapons.beam;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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

import java.awt.*;

//抄的原版速子矛
public class LiLancePaleEffect implements BeamEffectPlugin {
    private IntervalUtil fireInterval = new IntervalUtil(0.2F, 0.3F);
    private boolean wasZero = true;
    public LiLancePaleEffect() {
    }

    public void advance(float amount, CombatEngineAPI engine, BeamAPI beam) {
        CombatEntityAPI target = beam.getDamageTarget();
        if (target instanceof ShipAPI && beam.getBrightness() >= 1.0F) {
            float dur = beam.getDamage().getDpsDuration();
            if (!this.wasZero) {
                dur = 0.0F;
            }

            this.wasZero = beam.getDamage().getDpsDuration() <= 0.0F;
            this.fireInterval.advance(dur);
            Color color = Color.WHITE;
            if (this.fireInterval.intervalElapsed()) {
                boolean hitShield = target.getShield() != null && target.getShield().isWithinArc(beam.getTo());
                if (!hitShield) {
                    Vector2f point = beam.getRayEndPrevFrame();
                    float emp = beam.getDamage().getFluxComponent() * 0F;
                    float dam = beam.getDamage().getDamage() * 0.41F;
                    engine.spawnEmpArcPierceShields(beam.getSource(), point, beam.getDamageTarget(), beam.getDamageTarget(), DamageType.ENERGY, dam, emp, 100000.0F, "tachyon_lance_emp_impact", beam.getWidth() + 5.0F, color, WthC_ColorData.WHITE);
                }
            }
        }

    }
}
