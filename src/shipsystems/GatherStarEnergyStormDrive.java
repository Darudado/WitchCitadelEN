package data.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineLayers;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicRender;

//从烈焰去世器修改
public class GatherStarEnergyStormDrive extends BaseShipSystemScript {
    private final IntervalUtil TX = new IntervalUtil(0.05F, 0.05F);
    private boolean runOnce = false;
    private Vector2f VEL = null;
    public static float BONUS = 30f;

    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        if (state == State.OUT) {
            stats.getMaxSpeed().unmodify(id);
        } else {
            stats.getMaxSpeed().modifyFlat(id, 200f * effectLevel);
            stats.getAcceleration().modifyFlat(id, 200f * effectLevel);
            stats.getArmorDamageTakenMult().modifyMult(id, 1f - BONUS * 0.01f);
        }

        ShipAPI ship = (ShipAPI) stats.getEntity();
        if (ship == null) {
            return;
        }
        //残影特效
        float amount = Global.getCombatEngine().getElapsedInLastFrame();
        this.TX.advance(amount);
        if (!this.runOnce) {
            this.runOnce = true;
        }

        VEL = ship.getVelocity().negate(VEL);
        Vector2f RENDER_OFFSET = ship.getRenderOffset();
        SpriteAPI effect = Global.getSettings().getSprite(ship.getHullSpec().getSpriteName());
        Vector2f size = new Vector2f(ship.getSpriteAPI().getWidth(), ship.getSpriteAPI().getHeight());



        if (this.TX.intervalElapsed()) {
            MagicRender.objectspace(
                    effect, //图片
                    ship, //渲染对象
                    RENDER_OFFSET, //偏移量
                    VEL, //偏移速度
                    size, //尺寸
                    ship.getRenderOffset(), //缩放
                    ship.getFacing() + 180f, //角度
                    0f, //旋转速率
                    false, //相对位置是否固定
                    Misc.scaleAlpha(WthC_ColorData.DEEP_BLUE, 1f), //图片颜色
                    0f,  //抖动范围
                    0f,  //抖动频率
                    0f,  //闪烁范围
                    0f,  //闪烁频率
                    0f,  //闪烁及抖动延迟
                    0f,  //渐入时间
                    0.05f,  //持续时间
                    0.55f,  //渐出时间
                    true,  //是否死亡后渐出
                    CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                    GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
            );
        }

    }
    public void unapply(MutableShipStatsAPI stats, String id) {
        stats.getMaxSpeed().unmodify(id);
        stats.getMaxTurnRate().unmodify(id);
        stats.getTurnAcceleration().unmodify(id);
        stats.getAcceleration().unmodify(id);
        stats.getDeceleration().unmodify(id);
        stats.getArmorDamageTakenMult().unmodify(id);
    }

    public StatusData getStatusData(int index, State state, float effectLevel) {
        if (index == 0) {
            return new StatusData("Increase engine output", false);
        } else if (index == 1) {
            return new StatusData("-30% armor damage taken", false);
        } else if (index == 2) {
            return new StatusData("Continuously generates hard flux", true);
        }
        return null;
    }
}
