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
import java.awt.Color;
import org.lwjgl.util.vector.Vector2f;

//从离子脉冲特效修改
public class UnderCurrentOnHitEffect implements OnHitEffectPlugin {
    public UnderCurrentOnHitEffect() {
    }

    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, ApplyDamageResultAPI damageResult, CombatEngineAPI engine) {
        if ((float)Math.random() > 0.80F && !shieldHit && target instanceof ShipAPI) {
            float emp = projectile.getEmpAmount();
            float dam = projectile.getDamageAmount()* 3.00F;
            engine.spawnEmpArc(projectile.getSource(), point, target, target, DamageType.HIGH_EXPLOSIVE, dam, emp, 100000.0F, "tachyon_lance_emp_impact", 20.0F, new Color(25, 100, 155, 255), new Color(255, 255, 255, 255));
        }
        if ((float)Math.random() > 0.90F && shieldHit && target instanceof ShipAPI) {
            float emp = projectile.getEmpAmount();
            float dam = projectile.getDamageAmount()* 3.00F;
            engine.spawnEmpArc(projectile.getSource(), point, target, target, DamageType.HIGH_EXPLOSIVE, dam, emp, 100000.0F, "tachyon_lance_emp_impact", 20.0F, new Color(25, 100, 155, 255), new Color(255, 255, 255, 255));
        }

    }
}
