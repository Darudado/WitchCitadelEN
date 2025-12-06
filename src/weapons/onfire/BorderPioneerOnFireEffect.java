//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.onfire;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.OnFireEffectPlugin;
import com.fs.starfarer.api.combat.WeaponAPI;
import org.lazywizard.lazylib.MathUtils;

//从离子脉冲特效修改（个毛）
public class BorderPioneerOnFireEffect implements OnFireEffectPlugin {

    public BorderPioneerOnFireEffect() {
    }

    public void onFire(DamagingProjectileAPI proj, WeaponAPI weapon, CombatEngineAPI engine) {
            DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_BorderPioneer_Sup", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
            spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
    }
}
