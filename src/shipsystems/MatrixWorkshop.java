package data.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineLayers;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipCommand;
import com.fs.starfarer.api.combat.ShipEngineControllerAPI.ShipEngineAPI;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicRender;

//从高能聚焦修改
public class MatrixWorkshop extends BaseShipSystemScript {

    /*
    计时器
    private boolean runOnce = false;
    private final IntervalUtil GlowSS = new IntervalUtil(10.1F, 10.1F);
    private final IntervalUtil GlowSS2 = new IntervalUtil(9.9F, 9.9F);
    */

    int num = 0;

    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {

            stats.getMaxSpeed().modifyMult(id, 0.0f);
            stats.getBeamWeaponRangeBonus().modifyFlat(id, 400f);
            stats.getBeamWeaponDamageMult().modifyPercent(id, 25.0f);
            stats.getHullDamageTakenMult().modifyMult(id, 0.33f);
            stats.getArmorDamageTakenMult().modifyMult(id,0.33f);
            stats.getCombatWeaponRepairTimeMult().modifyMult(id, 0.05f);
            stats.getCombatEngineRepairTimeMult().modifyMult(id, 0.05f);



        ShipAPI ship = (ShipAPI) stats.getEntity();
        SpriteAPI effect = Global.getSettings().getSprite("fx", "IE");
        SpriteAPI glow3 = Global.getSettings().getSprite("fx", "glow3");
        SpriteAPI glow4 = Global.getSettings().getSprite("fx", "glow4");
        Vector2f size = new Vector2f(effect.getWidth(), effect.getHeight());
        Vector2f glow3size = new Vector2f((glow3.getWidth()* 2F), (glow3.getHeight()* 2F));
        Vector2f glow4size = new Vector2f(glow3.getWidth(), glow3.getHeight());

        if (ship == null) {
            return;
        }
        ship.blockCommandForOneFrame(ShipCommand.VENT_FLUX);
        /*
        计时器
        if (GlowSS.intervalElapsed()) {
            num = 0;
        }
        float amount = Global.getCombatEngine().getElapsedInLastFrame();
        this.GlowSS.advance(amount);
        if (!this.runOnce) {
            this.runOnce = true;
        }
        */
        if(num == 0){
            MagicRender.objectspace(
                    effect, //图片
                    ship, //渲染对象
                    WthC_Util.ZERO, //偏移量
                    WthC_Util.ZERO, //偏移速度
                    size, //尺寸
                    ship.getRenderOffset(), //缩放
                    -180f, //角度
                    33f, //旋转速率
                    true, //相对位置是否固定
                    Misc.scaleAlpha(WthC_ColorData.IE_WHITE_L, Math.min(1f, 1f)), //图片颜色
                    1f,  //抖动范围
                    2f,  //抖动频率
                    0f,  //闪烁范围
                    0f,  //闪烁频率
                    0f,  //闪烁及抖动延迟
                    3f,  //渐入时间
                    17f,  //持续时间
                    3f,  //渐出时间
                    true,  //是否死亡后渐出
                    CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                    GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
            );
            MagicRender.objectspace(
                    glow3, //图片
                    ship, //渲染对象
                    WthC_Util.ZERO, //偏移量
                    WthC_Util.ZERO, //偏移速度
                    glow3size, //尺寸
                    WthC_Util.ZERO, //缩放
                    -180f, //角度
                    33f, //旋转速率
                    true, //相对位置是否固定
                    Misc.scaleAlpha(WthC_ColorData.IE_WHITE, Math.min(1f, 1f)), //图片颜色
                    0.5f,  //抖动范围
                    2f,  //抖动频率
                    0f,  //闪烁范围
                    0f,  //闪烁频率
                    0f,  //闪烁及抖动延迟
                    3f,  //渐入时间
                    17f,  //持续时间
                    3f,  //渐出时间
                    true,  //是否死亡后渐出
                    CombatEngineLayers.ABOVE_SHIPS_LAYER,  //图层
                    GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
            );
            MagicRender.objectspace(
                    glow4, //图片
                    ship, //渲染对象
                    WthC_Util.ZERO, //偏移量
                    WthC_Util.ZERO, //偏移速度
                    glow4size, //尺寸
                    WthC_Util.ZERO, //缩放
                    -180f, //角度
                    0f, //旋转速率
                    true, //相对位置是否固定
                    Misc.scaleAlpha(WthC_ColorData.IE_WHITE, Math.min(1f, 1f)), //图片颜色
                    2f,  //抖动范围
                    3f,  //抖动频率
                    0f,  //闪烁范围
                    0f,  //闪烁频率
                    0f,  //闪烁及抖动延迟
                    3f,  //渐入时间
                    17f,  //持续时间
                    3f,  //渐出时间
                    true,  //是否死亡后渐出
                    CombatEngineLayers.ABOVE_SHIPS_LAYER,  //图层
                    GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
            );
            num++;
        }
    }

    public void unapply(MutableShipStatsAPI stats, String id) {
        num = 0;
        stats.getMaxSpeed().unmodify(id);
        stats.getBeamWeaponRangeBonus().unmodify(id);
        stats.getBeamWeaponDamageMult().unmodify(id);
        stats.getHullDamageTakenMult().unmodify(id);
        stats.getArmorDamageTakenMult().unmodify(id);
        stats.getCombatWeaponRepairTimeMult().unmodify(id);
        stats.getCombatEngineRepairTimeMult().unmodify(id);
    }

    public StatusData getStatusData(int index, State state, float effectLevel) {
        if (index == 0) {
            return new StatusData("Significantly increase beam weapon range", false);
        } else if (index == 1) {
            return new StatusData("Drastically reduce hull damage taken", false);
        } else if (index == 2){
            return new StatusData("Rapidly repair weapons and engines", false);
        } else if (index == 3){
            return new StatusData("+25% beam weapon damage", false);
        } else if (index == 4){
            return new StatusData("Unable to move", true);
        }
        return null;
    }
}
