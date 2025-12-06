//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.onfire;

import com.fs.starfarer.api.combat.*;
import org.lazywizard.lazylib.MathUtils;

//从离子脉冲特效修改（个毛）
public class TPCRuneOnFireEffect implements OnFireEffectPlugin {
    public TPCRuneOnFireEffect() {
    }

    public void onFire(DamagingProjectileAPI proj, WeaponAPI weapon, CombatEngineAPI engine) {
        float RandomNumber1 = MathUtils.getRandomNumberInRange(0F, 100F);
        if (RandomNumber1 <= 17F) {
            DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_TPCRune_K", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
            spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            engine.removeEntity(proj);
        } else if(RandomNumber1 <= 33F){
            DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_TPCRune_H", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
            spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            engine.removeEntity(proj);
        } else if(RandomNumber1 <= 50F){
            DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_TPCRune_F", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
            spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            engine.removeEntity(proj);
        }
    }
}
