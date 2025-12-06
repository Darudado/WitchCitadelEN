//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.onfire;

import com.fs.starfarer.api.combat.*;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;

//从离子脉冲特效修改（个毛）
public class DestinyTrackOnFireEffect implements OnFireEffectPlugin {

    public DestinyTrackOnFireEffect() {
    }


    public void onFire(DamagingProjectileAPI proj, WeaponAPI weapon, CombatEngineAPI engine) {

        float RandomNumber1 = MathUtils.getRandomNumberInRange(0F, 100F);
        Vector2f loc = MathUtils.getRandomPointInCircle(weapon.getShip().getLocation(), 360f);
        DamagingProjectileAPI spawnedProj;
        if (RandomNumber1 <= 80F) {
            spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_DestinyTrack", loc, proj.getFacing(), weapon.getShip().getVelocity());
        } else {
            spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_DestinyTrack_EX", loc, proj.getFacing(), weapon.getShip().getVelocity());
        }
        spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
        engine.removeEntity(proj);

    }
}
