//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.weapons.beam;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;

import java.awt.*;

//抄了一手地质协会变色
public class DarkestEffect implements BeamEffectPlugin {
    private IntervalUtil fireInterval = new IntervalUtil(1F, 1F);
    private float timer = (float)Math.random() * 3f;
    private boolean wasZero = true;

    private static Color colorSwitch(float blender) {
        Color color;
        blender %= 3f;

        if (blender < 1f) {
            color = Misc.interpolateColor(WthC_ColorData.MID_BLACK, WthC_ColorData.MID_WHITE, blender);
        } else if (blender < 2f) {
            blender -= 1f;
            color = Misc.interpolateColor(WthC_ColorData.MID_WHITE, WthC_ColorData.L_BLACK, blender);
        } else {
            blender -= 2f;
            color = Misc.interpolateColor(WthC_ColorData.L_BLACK, WthC_ColorData.MID_BLACK, blender);
        }

        color = Misc.interpolateColor(color, WthC_ColorData.MID_BLACK, 0.5f);
        return color;
    }

    public DarkestEffect() {
    }

    public void advance(float amount, CombatEngineAPI engine, BeamAPI beam) {
        timer += amount * 5f;
        Color color = colorSwitch(timer);
        beam.setFringeColor(color);
        CombatEntityAPI target = beam.getDamageTarget();
        if (target instanceof ShipAPI && beam.getBrightness() >= 1.0F) {
            float dur = beam.getDamage().getDpsDuration();
            if (!this.wasZero) {
                dur = 0.0F;
            }

            this.wasZero = beam.getDamage().getDpsDuration() <= 0.0F;
            this.fireInterval.advance(dur);

            }
        }
    }

