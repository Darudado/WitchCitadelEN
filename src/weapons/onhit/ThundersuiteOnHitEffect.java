//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.onhit;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.OnHitEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.ApplyDamageResultAPI;
import data.util.WthC_ColorData ;
import org.lwjgl.util.vector.Vector2f;

//从离子脉冲特效修改
public class ThundersuiteOnHitEffect implements OnHitEffectPlugin {
    public ThundersuiteOnHitEffect() {
    }

    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, ApplyDamageResultAPI damageResult, CombatEngineAPI engine) {
        if (target instanceof ShipAPI) {
            if (shieldHit) {
                ShipAPI ship = (ShipAPI)target;
                float fluxLevel = ship.getFluxTracker().getFluxLevel();
                float empChanceBase = fluxLevel / 0.75F;
                if (empChanceBase >= 1.0F) {
                    empChanceBase = 1.0F;
                }

                if (Math.random() <= (double)(empChanceBase * 0.33F)) {
                    float emp = projectile.getEmpAmount()* 0.66F;
                    float dam = projectile.getDamageAmount()* 0.00F;
                    engine.spawnEmpArcPierceShields(projectile.getSource(), point, ship, ship, DamageType.ENERGY, dam, emp, 10000.0F, "tachyon_lance_emp_impact", 10.0F, WthC_ColorData.N_WHITE, WthC_ColorData.N_WHITE);
                }

            }
        }
    }
}
