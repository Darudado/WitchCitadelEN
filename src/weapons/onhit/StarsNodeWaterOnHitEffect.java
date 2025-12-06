//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.onhit;


import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.listeners.ApplyDamageResultAPI;
import com.fs.starfarer.api.input.InputEventAPI;
import data.util.WthC_ColorData;
import org.lwjgl.util.vector.Vector2f;

import java.util.List;

//从FM疫病布网特效修改
public class StarsNodeWaterOnHitEffect implements OnHitEffectPlugin {
    private BaseEveryFrameCombatPlugin plugin = null;
    public StarsNodeWaterOnHitEffect() {
    }

    public void onHit(DamagingProjectileAPI projectile, final CombatEntityAPI target, Vector2f point, boolean shieldHit, ApplyDamageResultAPI damageResult, final CombatEngineAPI engine) {
        if (target != null) {
            if (target instanceof ShipAPI) {

                if (this.plugin == null) {

                    this.plugin = new BaseEveryFrameCombatPlugin() {

                        public float TIMER = 5.0F;

                        public void advance(float amount, List<InputEventAPI> events) {

                                super.advance(amount, events);
                                this.TIMER -= amount;
                                ((ShipAPI)target).getMutableStats().getFluxDissipation().modifyPercent("WaterBuff", -15F);
                                ((ShipAPI)target).getMutableStats().getShieldDamageTakenMult().modifyPercent("WaterBuff", 15F);
                                float jitterBuff = this.TIMER / 5.0F;
                                ((ShipAPI)target).setJitterUnder(target, WthC_ColorData.DEEP_BLUE, 0.6F * jitterBuff, 10, 25.0F * jitterBuff);
                                ((ShipAPI)target).setCircularJitter(true);

                                if (this.TIMER <= 0.0F) {
                                    ((ShipAPI)target).getMutableStats().getFluxDissipation().unmodify("WaterBuff");
                                    ((ShipAPI)target).getMutableStats().getShieldDamageTakenMult().unmodify("WaterBuff");
                                    engine.removePlugin(StarsNodeWaterOnHitEffect.this.plugin);
                                }
                            }

                    };
                    engine.addPlugin(this.plugin);
                }

            }

        }
    }
}
