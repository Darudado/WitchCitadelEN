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
public class DestinyTrackOnHitEffect implements OnHitEffectPlugin {
    public DestinyTrackOnHitEffect() {
    }

    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, ApplyDamageResultAPI damageResult, CombatEngineAPI engine) {
        if (target instanceof ShipAPI) {
            if (shieldHit) {
                ShipAPI ship = (ShipAPI)target;
                if ((float)Math.random() > 0.75F)  {
                    float emp = projectile.getEmpAmount();
                    float dam = projectile.getDamageAmount();
                    engine.spawnEmpArcPierceShields(projectile.getSource(), point, ship, ship, DamageType.ENERGY, dam, emp, 10000.0F, "tachyon_lance_emp_impact", 10.0F, WthC_ColorData.N_WHITE, WthC_ColorData.N_WHITE);
                }
            }
        }
    }
}
