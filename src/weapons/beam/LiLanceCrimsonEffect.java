package data.weapons.beam;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.loading.DamagingExplosionSpec;
import com.fs.starfarer.api.util.IntervalUtil;
import data.util.WthC_ColorData;
import org.dark.shaders.distortion.DistortionShader;
import org.dark.shaders.distortion.RippleDistortion;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lwjgl.util.vector.Vector2f;

import static com.fs.starfarer.api.impl.combat.RiftCascadeEffect.EXPLOSION_UNDERCOLOR;
import static com.fs.starfarer.api.impl.combat.RiftCascadeEffect.STANDARD_RIFT_COLOR;

//抄了一手北极星极化射流
public class LiLanceCrimsonEffect implements BeamEffectPlugin {

    private final IntervalUtil fireInterval = new IntervalUtil(0.5f, 0.75f);
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
            DamagingProjectileAPI explosion = engine.spawnDamagingExplosion(createExplosionSpec(1500F, 600f),
                    beam.getSource(), beam.getRayEndPrevFrame());
            RippleDistortion r = new RippleDistortion();
            r.setLocation(beam.getRayEndPrevFrame());
            r.setSize(1000f * 1.5f);
            r.fadeInSize(1f);
            r.fadeOutIntensity(1f);
            DistortionShader.addDistortion(r);
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
                5f, // particleSizeMin
                7f, // particleSizeRange
                1f, // particleDuration
                200, // particleCount
                WthC_ColorData.MYSTERIOUS_PURPLE, // particleColor
                WthC_ColorData.LIGHT_CRIMSON // explosionColor
        );
        spec.setDamageType(DamageType.FRAGMENTATION);
        spec.setUseDetailedExplosion(true);
        spec.setSoundSetId("explosion_guardian");
        return spec;
    }
}