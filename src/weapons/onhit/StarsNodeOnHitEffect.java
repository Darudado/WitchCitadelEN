//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.onhit;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.listeners.ApplyDamageResultAPI;
import com.fs.starfarer.api.impl.combat.DisintegratorEffect;
import com.fs.starfarer.api.util.Misc;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;

//从离子脉冲特效修改
public class StarsNodeOnHitEffect implements OnHitEffectPlugin {
    public static float DAMAGE = 15.0F;
    public StarsNodeOnHitEffect() {
    }

    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, ApplyDamageResultAPI damageResult, CombatEngineAPI engine) {
        if (!shieldHit && target instanceof ShipAPI) {
            dealArmorDamage(projectile, (ShipAPI)target, point);
        }
        if ((float)Math.random() > 0.50F && shieldHit && target instanceof ShipAPI) {
            float emp = projectile.getEmpAmount();
            float dam = projectile.getDamageAmount()* 0.2F;
            engine.spawnEmpArcPierceShields(projectile.getSource(), point, target, target, DamageType.ENERGY, dam, emp, 100000.0F, "tachyon_lance_emp_impact", 20.0F, new Color(25, 100, 155, 255), new Color(255, 255, 255, 255));
        }

    }
    public static void dealArmorDamage(DamagingProjectileAPI projectile, ShipAPI target, Vector2f point) {
        CombatEngineAPI engine = Global.getCombatEngine();
        ArmorGridAPI grid = target.getArmorGrid();
        int[] cell = grid.getCellAtLocation(point);
        if (cell != null) {
            int gridWidth = grid.getGrid().length;
            int gridHeight = grid.getGrid()[0].length;
            float damageTypeMult = DisintegratorEffect.getDamageTypeMult(projectile.getSource(), target);
            float damageDealt = 0.0F;

            for(int i = -2; i <= 2; ++i) {
                for(int j = -2; j <= 2; ++j) {
                    if (i != 2 && i != -2 || j != 2 && j != -2) {
                        int cx = cell[0] + i;
                        int cy = cell[1] + j;
                        if (cx >= 0 && cx < gridWidth && cy >= 0 && cy < gridHeight) {
                            float damMult = 0.033333335F;
                            if (i == 0 && j == 0) {
                                damMult = 0.06666667F;
                            } else if (i <= 1 && i >= -1 && j <= 1 && j >= -1) {
                                damMult = 0.06666667F;
                            } else {
                                damMult = 0.033333335F;
                            }

                            float armorInCell = grid.getArmorValue(cx, cy);
                            float damage = DAMAGE * damMult * damageTypeMult;
                            damage = Math.min(damage, armorInCell);
                            if (!(damage <= 0.0F)) {
                                target.getArmorGrid().setArmorValue(cx, cy, Math.max(0.0F, armorInCell - damage));
                                damageDealt += damage;
                            }
                        }
                    }
                }
            }

            if (damageDealt > 0.0F) {
                if (Misc.shouldShowDamageFloaty(projectile.getSource(), target)) {
                    engine.addFloatingDamageText(point, damageDealt, Misc.FLOATY_ARMOR_DAMAGE_COLOR, target, projectile.getSource());
                }

                target.syncWithArmorGridState();
            }

        }
    }
}
