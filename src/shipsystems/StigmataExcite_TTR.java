package data.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineLayers;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.skills.WitchCross;
import data.util.WthC_ColorData;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicRender;

//从高能聚焦修改
public class StigmataExcite_TTR extends BaseShipSystemScript {

    /*
    计时器
    private boolean runOnce = false;
    private final IntervalUtil GlowSS = new IntervalUtil(10.1F, 10.1F);
    private final IntervalUtil GlowSS2 = new IntervalUtil(9.9F, 9.9F);
    */
    private final IntervalUtil TX = new IntervalUtil(0.05F, 0.05F);
    private boolean runOnce = false;
    private Vector2f VEL = null;


    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {


        stats.getBallisticRoFMult().modifyPercent(id, 200f);
        stats.getEnergyRoFMult().modifyPercent(id, 200f);

        stats.getEnergyWeaponRangeBonus().modifyPercent(id, 50f);
        stats.getBallisticWeaponRangeBonus().modifyPercent(id, 50f);

        stats.getBallisticWeaponDamageMult().modifyPercent(id, -50f);
        stats.getEnergyWeaponDamageMult().modifyPercent(id, -50f);

        stats.getDamageToFighters().modifyPercent(id, 200F);
        stats.getDamageToMissiles().modifyPercent(id, 200F);

        stats.getMaxSpeed().modifyFlat(id, 150f);
        stats.getAcceleration().modifyPercent(id, 100f * effectLevel);
        stats.getDeceleration().modifyPercent(id, 100f * effectLevel);
        stats.getTurnAcceleration().modifyFlat(id, 20f * effectLevel);
        stats.getTurnAcceleration().modifyPercent(id, 100f * effectLevel);
        stats.getMaxTurnRate().modifyFlat(id, 25f);
        stats.getMaxTurnRate().modifyPercent(id, 50f);


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
                    Misc.scaleAlpha(WthC_ColorData.CROSS_GARY, 1f), //图片颜色
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

        stats.getBallisticRoFMult().unmodify(id);
        stats.getEnergyRoFMult().unmodify(id);

        stats.getEnergyWeaponRangeBonus().unmodify(id);
        stats.getBallisticWeaponRangeBonus().unmodify(id);

        stats.getBallisticWeaponDamageMult().unmodify(id);
        stats.getEnergyWeaponDamageMult().unmodify(id);

        stats.getDamageToFighters().unmodify(id);
        stats.getDamageToMissiles().unmodify(id);

        stats.getMaxSpeed().unmodify(id);
        stats.getAcceleration().unmodify(id);
        stats.getDeceleration().unmodify(id);
        stats.getTurnAcceleration().unmodify(id);
        stats.getTurnAcceleration().unmodify(id);
        stats.getMaxTurnRate().unmodify(id);
        stats.getMaxTurnRate().unmodify(id);

    }

    public StatusData getStatusData(int index, State state, float effectLevel) {
        if (index == 0) {
            return new StatusData("Flamboyant maneuvers", false);
        } else if (index == 1) {
            return new StatusData("Massively increases non-missile weapon rate of fire", false);
        } else if (index == 2){
            return new StatusData("Massively increases damage to fighters and missiles", false);
        } else if (index == 3){
            return new StatusData("+50% non-missile weapon range", true);
        } else if (index == 5){
            return new StatusData("-50% non-missile weapon damage", true);
        }
        return null;
    }
}
