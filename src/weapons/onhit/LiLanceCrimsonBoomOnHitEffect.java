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
import org.dark.shaders.distortion.DistortionShader;
import org.dark.shaders.distortion.RippleDistortion;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;

//从离子脉冲特效修改
public class LiLanceCrimsonBoomOnHitEffect implements OnHitEffectPlugin {
    public static float DAMAGE = 15.0F;
    public LiLanceCrimsonBoomOnHitEffect() {
    }

    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, ApplyDamageResultAPI damageResult, CombatEngineAPI engine) {
        RippleDistortion r = new RippleDistortion();
        r.setLocation(projectile.getLocation());
        r.setSize(450f * 1.5f);
        r.fadeInSize(1.75f);
        r.fadeOutIntensity(1.75f);
        DistortionShader.addDistortion(r);
    }

}
