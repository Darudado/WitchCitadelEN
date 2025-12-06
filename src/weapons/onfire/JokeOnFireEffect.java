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
public class JokeOnFireEffect implements OnFireEffectPlugin {
    public JokeOnFireEffect() {
    }

    public void onFire(DamagingProjectileAPI proj, WeaponAPI weapon, CombatEngineAPI engine) {

        float RandomNumber1 = MathUtils.getRandomNumberInRange(0F, 100F);

        if (RandomNumber1 <= 1F) {
            DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_Thundertearcannon", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
            spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
        } else if (RandomNumber1 <= 4F){
            float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
            if (RandomNumber2 <= 33){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "gauss", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 66){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "hellbore", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else {
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "plasma", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            }
        } else if (RandomNumber1 <= 20F){
            float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
            if (RandomNumber2 <= 25){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "heavyblaster", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 50){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "miningblaster", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 75){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "mjolnir", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else {
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_magnificentfire", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            }
        } else if (RandomNumber1 <= 50F){
            float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
            if (RandomNumber2 <= 10){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "shredder", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 20){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "ionpulser", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 30){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "autopulse", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 40){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "mark9", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 50){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_stormveil", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 60){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_ForthrightMoon", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 70){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_blazingassault", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 80){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "devastator", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 90){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "autopulse", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else {
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "flak", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            }
        } else {
            float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
            if (RandomNumber2 <= 10){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_gonggonq", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 20){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "WthC_gonggonq_sup", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 30){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "chaingun", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 40){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "lightmortar", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 50){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "vulcan", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 60){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "lightmg", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 70){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "lightac", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 80){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "heavyneedler", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else if (RandomNumber2 <= 90){
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "ioncannon", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            } else {
                DamagingProjectileAPI spawnedProj = (DamagingProjectileAPI) engine.spawnProjectile(weapon.getShip(), weapon, "irpulse", proj.getLocation(), proj.getFacing(), weapon.getShip().getVelocity());
                spawnedProj.getVelocity().scale(MathUtils.getRandomNumberInRange(1.0f, 1.0f));
            }
        }
        engine.removeEntity(proj);
    }
}
