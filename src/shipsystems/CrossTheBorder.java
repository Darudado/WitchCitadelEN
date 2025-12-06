package data.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineLayers;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import org.lazywizard.lazylib.VectorUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicRender;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

//从高能聚焦修改
public class CrossTheBorder extends BaseShipSystemScript {

    /*
    计时器
    private boolean runOnce = false;
    private final IntervalUtil GlowSS = new IntervalUtil(10.1F, 10.1F);
    private final IntervalUtil GlowSS2 = new IntervalUtil(9.9F, 9.9F);
    */
    private final IntervalUtil TX = new IntervalUtil(0.1F, 0.1F);
    private boolean runOnce = false;
    private Vector2f VEL = null;
    public static final Object KEY_JITTER = new Object();


    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {

        ShipAPI ship = (ShipAPI) stats.getEntity();
        boolean player = false;
        if (ship == null) {
            return;
        }
        player = ship == Global.getCombatEngine().getPlayerShip();
        stats.getTimeMult().modifyMult(id, 3.0F);
        if (player) {
            Global.getCombatEngine().getTimeMult().modifyMult(id, 0.33F);
        } else {
            Global.getCombatEngine().getTimeMult().unmodify(id);
        }
        ship.getVelocity().set(VectorUtils.rotate(new Vector2f(100f, 50f), ship.getFacing() + 270f));

        if (effectLevel > 0.0F) {

            float maxRangeBonus = 5.0F;
            float jitterRangeBonus = effectLevel * maxRangeBonus;

            for (ShipAPI fighter : this.getFighters(ship)) {
                if (!fighter.isHulk()) {
                    MutableShipStatsAPI fStats = fighter.getMutableStats();
                    fStats.getTimeMult().modifyMult(id, 3.0F);
                    fighter.setWeaponGlow(effectLevel, Misc.setAlpha(WthC_ColorData.SOUL_GREEN_D, 255), EnumSet.allOf(WeaponAPI.WeaponType.class));
                    fighter.setJitterUnder(KEY_JITTER, WthC_ColorData.SOUL_GREEN_D, effectLevel, 5, 0.0F, jitterRangeBonus);
                    fighter.setJitter(KEY_JITTER, WthC_ColorData.SOUL_GREEN, effectLevel, 2, 0.0F, 0.0F + jitterRangeBonus);
                }
            }
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
                            Misc.scaleAlpha(WthC_ColorData.SOUL_GREEN, 1f), //图片颜色
                            0f,  //抖动范围
                            0f,  //抖动频率
                            0f,  //闪烁范围
                            0f,  //闪烁频率
                            0f,  //闪烁及抖动延迟
                            0f,  //渐入时间
                            0.05f,  //持续时间
                            1f,  //渐出时间
                            true,  //是否死亡后渐出
                            CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                            GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
                    );
        }
    }
    private List<ShipAPI> getFighters(ShipAPI carrier) {
        List<ShipAPI> result = new ArrayList();
        Iterator var4 = Global.getCombatEngine().getShips().iterator();

        while(var4.hasNext()) {
            ShipAPI ship = (ShipAPI)var4.next();
            if (ship.isFighter() && ship.getWing() != null && ship.getWing().getSourceShip() == carrier) {
                result.add(ship);
            }
        }

        return result;
    }

    public void unapply(MutableShipStatsAPI stats, String id) {
        ShipAPI ship = (ShipAPI) stats.getEntity();
        if (ship == null) {
            return;
        }
            Global.getCombatEngine().getTimeMult().unmodify(id);
            stats.getTimeMult().unmodify(id);
            for (ShipAPI fighter : this.getFighters(ship)) {
                if (!fighter.isHulk()) {
                    MutableShipStatsAPI fStats = fighter.getMutableStats();
                    fStats.getTimeMult().unmodify(id);
                }
            }
    }

    public StatusData getStatusData(int index, State state, float effectLevel) {
        if (index == 0) {
            return new StatusData("Rapid Diagonal Movement", false);
        } else if (index == 1) {
            return new StatusData("All things slow down...the soul stands firm...", false);
        }
        return null;
    }
}
