//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.onhit;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.listeners.ApplyDamageResultAPI;
import data.util.WthC_ColorData;
import org.lwjgl.util.vector.Vector2f;

//从离子脉冲特效修改
public class StarsNodeWoodOnHitEffect implements OnHitEffectPlugin {
    public StarsNodeWoodOnHitEffect() {
    }

    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, ApplyDamageResultAPI damageResult, CombatEngineAPI engine) {

        if (target instanceof ShipAPI) {
            ShipAPI ship = (ShipAPI)target;
            if (shieldHit) {
                if ((float)Math.random() > 0.50F) {
                    float emp = projectile.getEmpAmount()* 0.5F;
                    float dam = projectile.getDamageAmount()* 0.01F;
                    engine.spawnEmpArcPierceShields(projectile.getSource(), point, ship, ship, DamageType.ENERGY, dam, emp, 10000.0F, "tachyon_lance_emp_impact", 10.0F, WthC_ColorData.SOUL_GREEN_D, WthC_ColorData.N_WHITE);
                }
            }
            if (!shieldHit) {
                float emp = projectile.getEmpAmount()* 0.5F;
                float dam = projectile.getDamageAmount()* 0.01F;
                engine.spawnEmpArcPierceShields(projectile.getSource(), point, ship, ship, DamageType.ENERGY, dam, emp, 10000.0F, "tachyon_lance_emp_impact", 10.0F, WthC_ColorData.SOUL_GREEN_D, WthC_ColorData.N_WHITE);
            }
        }
    }
}
