package data.weapons.beam;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.util.IntervalUtil;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lwjgl.util.vector.Vector2f;

//抄了一手北极星极化射流
public class LiLanceDeepBlueNewEffect implements BeamEffectPlugin {

    private final IntervalUtil fireInterval = new IntervalUtil(0.1f, 0.1f);
    private boolean wasZero = true;

    @Override
    public void advance(float amount, CombatEngineAPI engine, BeamAPI beam) {
        CombatEntityAPI target = beam.getDamageTarget();
        if (target == null || beam.getBrightness() < 1f) return;

        float dur = beam.getDamage().getDpsDuration();
        if (!wasZero) dur = 0f;
        wasZero = beam.getDamage().getDpsDuration() <= 0f;
        fireInterval.advance(dur);

        if (fireInterval.intervalElapsed()) {
            float angle = VectorUtils.getAngle(beam.getFrom(), beam.getRayEndPrevFrame()) + 180f;
            float differ = 30f + 45f * (float)Math.random();
            angle += Math.random() > 0.5 ? differ : -differ;

            Vector2f vel = MathUtils.getPoint(target.getVelocity(), 150f, angle);

            MissileAPI proj = (MissileAPI)engine.spawnProjectile(beam.getSource(), beam.getWeapon(), "WthC_LiLancedeepblueSup", beam.getRayEndPrevFrame(), angle, vel);
            proj.addDamagedAlready(beam.getSource());



            // proj.setArmingTime(0.25f);
            // proj.setEmpResistance(10000);
        }
    }
}