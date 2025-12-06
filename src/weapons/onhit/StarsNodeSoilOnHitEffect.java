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
public class StarsNodeSoilOnHitEffect implements OnHitEffectPlugin {
    private BaseEveryFrameCombatPlugin plugin = null;
    public StarsNodeSoilOnHitEffect() {
    }

    public void onHit(DamagingProjectileAPI projectile, final CombatEntityAPI target, Vector2f point, boolean shieldHit, ApplyDamageResultAPI damageResult, final CombatEngineAPI engine) {
        if (target != null) {
            if (target instanceof ShipAPI) {
                if (this.plugin == null) {

                    this.plugin = new BaseEveryFrameCombatPlugin() {
                        public float TIMER = 3.0F;

                        public void advance(float amount, List<InputEventAPI> events) {
                            super.advance(amount, events);
                            this.TIMER -= amount;
                            ((ShipAPI)target).getMutableStats().getMaxSpeed().modifyMult("SoilBuff", 0.2F);
                            float jitterBuff = this.TIMER / 3.0F;
                            ((ShipAPI)target).setJitterUnder(target, WthC_ColorData.IE_WHITE, 0.6F * jitterBuff, 10, 25.0F * jitterBuff);
                            ((ShipAPI)target).setCircularJitter(true);
                            if (this.TIMER <= 0.0F) {
                                ((ShipAPI)target).getMutableStats().getMaxSpeed().unmodify("SoilBuff");
                                engine.removePlugin(StarsNodeSoilOnHitEffect.this.plugin);
                            }

                        }
                    };
                    engine.addPlugin(this.plugin);
                }

            }

        }
    }
}
