package data.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineLayers;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.scripts.util.MagicRender;
import java.awt.*;

import data.util.WthC_ColorData;
import data.util.WthC_Util;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;



//从烈焰去世器修改
//特效来源于幻想工造
public class UntilElapse extends BaseShipSystemScript {
    private boolean runOnce = false;
    private final IntervalUtil SS = new IntervalUtil(0.1F, 0.1F);
    private boolean RENDER = false;
    private Vector2f RENDER_OFFSET = null;
    private float TIMER = 0;
    private Vector2f VEL = null;

    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        ShipAPI ship = (ShipAPI) stats.getEntity();
        if (ship == null) {
            return;
        }
        float amount = Global.getCombatEngine().getElapsedInLastFrame();
        this.SS.advance(amount);
        if (!this.runOnce) {
            this.runOnce = true;
        }
        float UpLvl = 100 - (((ship.getMaxHitpoints() - ship.getHitpoints()) / ship.getMaxHitpoints()) * 100);
        if (state == State.OUT) {
            stats.getMaxSpeed().unmodify(id);
        } else {
            stats.getMaxSpeed().modifyFlat(id, 500f * effectLevel);
            stats.getAcceleration().modifyFlat(id, 1000f * effectLevel);
            stats.getTurnAcceleration().modifyFlat(id, 20f * effectLevel);
            stats.getTurnAcceleration().modifyPercent(id, 100f * effectLevel);
            stats.getMaxTurnRate().modifyFlat(id, 20f);
            stats.getMaxTurnRate().modifyPercent(id, 100f);
            stats.getBallisticRoFMult().modifyPercent(id, UpLvl);
            stats.getEnergyRoFMult().modifyPercent(id, UpLvl);
            stats.getEnergyAmmoRegenMult().modifyPercent(id, UpLvl);
            stats.getBallisticAmmoRegenMult().modifyPercent(id, UpLvl);
            stats.getEnergyWeaponFluxCostMod().modifyMult(id, 1 - (UpLvl / 100));
            stats.getBallisticWeaponFluxCostMod().modifyMult(id, 1 - (UpLvl / 100));
            if (SS.intervalElapsed()){
                ship.setHitpoints(ship.getHitpoints() - (ship.getMaxHitpoints() * 0.001F));
            }
        }

        TIMER = TIMER + 1;
        if (RENDER_OFFSET == null) {
            RENDER_OFFSET = ship.getRenderOffset();
            VEL = ship.getVelocity().negate(VEL);
        }
        if (TIMER >= 10 && RENDER) {
            RENDER_OFFSET = ship.getRenderOffset();
            VEL = ship.getVelocity().negate(VEL);
            TIMER = 0;
            RENDER = !RENDER;
        }

        if (effectLevel > 0 && !RENDER) {
            Vector2f size = new Vector2f(ship.getSpriteAPI().getWidth(), ship.getSpriteAPI().getHeight());

            SpriteAPI effect = Global.getSettings().getSprite("fx", "IE");

            MagicRender.objectspace(
                    effect, //图片
                    ship, //渲染对象
                    WthC_Util.ZERO, //偏移量
                    WthC_Util.ZERO, //偏移速度
                    size, //尺寸
                    ship.getRenderOffset(), //缩放
                    ship.getFacing() + 180f, //角度
                    60f, //旋转速率
                    true, //相对位置是否固定
                    Misc.scaleAlpha(WthC_ColorData.DUST_RED, 1f), //图片颜色
                    3f,  //抖动范围
                    1.5f,  //抖动频率
                    1f,  //闪烁范围
                    3f,  //闪烁频率
                    0f,  //闪烁及抖动延迟
                    0.1f,  //渐入时间
                    0.1f,  //持续时间
                    0.3f,  //渐出时间
                    true,  //是否死亡后渐出
                    CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                    GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
            );


            }
            RENDER = !RENDER;
        }


    public void unapply(MutableShipStatsAPI stats, String id) {
        stats.getMaxSpeed().unmodify(id);
        stats.getAcceleration().unmodify(id);
        stats.getTurnAcceleration().unmodify(id);
        stats.getMaxTurnRate().unmodify(id);
        stats.getBallisticRoFMult().unmodify(id);
        stats.getEnergyRoFMult().unmodify(id);
        stats.getEnergyAmmoRegenMult().unmodify(id);
        stats.getBallisticAmmoRegenMult().unmodify(id);
        stats.getEnergyWeaponFluxCostMod().unmodify(id);
        stats.getBallisticWeaponFluxCostMod().unmodify(id);
    }

    public StatusData getStatusData(int index, State state, float effectLevel) {

        if (index == 0) {
            return new StatusData("Massively increases engine output", false);
        } else if (index == 1) {
            return new StatusData("Massively increases maneuverability", false);
        } else if (index == 2) {
            return new StatusData("Improves weapon performance", false);
        } else if (index == 3) {
            return new StatusData("Rapidly reduces hull integrity", true);
        }
        return null;
    }
}
