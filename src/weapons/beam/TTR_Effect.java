package data.weapons.beam;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.loading.DamagingExplosionSpec;
import com.fs.starfarer.api.util.IntervalUtil;
import data.util.WthC_ColorData;
import org.dark.shaders.distortion.DistortionShader;
import org.dark.shaders.distortion.RippleDistortion;

//抄了一手北极星极化射流
public class TTR_Effect implements BeamEffectPlugin {

    private final IntervalUtil fireInterval = new IntervalUtil(0.3f, 0.35f);
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
            DamagingProjectileAPI explosion = engine.spawnDamagingExplosion(createExplosionSpec(300F, 300f),
                    beam.getSource(), beam.getRayEndPrevFrame());
            explosion.getDamage().setFluxComponent(explosion.getDamageAmount());


            // proj.setArmingTime(0.25f);
            // proj.setEmpResistance(10000);
        }
    }
    public DamagingExplosionSpec createExplosionSpec(float damage, float radius) {
        DamagingExplosionSpec spec = new DamagingExplosionSpec(
                0.15f, // duration
                radius, // radius
                radius, // coreRadius
                damage, // maxDamage
                damage / 2f, // minDamage
                CollisionClass.PROJECTILE_FF, // collisionClass
                CollisionClass.PROJECTILE_FIGHTER, // collisionClassByFighter
                1F, // particleSizeMin
                2F, // particleSizeRange
                0.5f, // particleDuration
                20, // particleCount
                WthC_ColorData.HIGH_BLUE_L, // particleColor
                WthC_ColorData.HIGH_BLUE_L // explosionColor
        );
        spec.setDamageType(DamageType.FRAGMENTATION);
        spec.setUseDetailedExplosion(true);
        spec.setSoundSetId("explosion_guardian");
        return spec;
    }
}