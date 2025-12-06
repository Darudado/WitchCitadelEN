//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.listeners.DamageDealtModifier;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.scripts.util.MagicIncompatibleHullmods;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicRender;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

// Some formatting from Polaris
public class WthC_PaleAndCrimson extends BaseHullMod {
    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static final float COST_REDUCTION1 = -8.0F;
    public static final float COST_REDUCTION2 = -4.0F;
    public static final float COST_REDUCTION3 = -2.0F;
    private static final Set<String> BLOCKED_HULLMODS = new HashSet();
    public static final float WEAPON_Bonus = 100f;
    public static final float ProjectileSpeed_Bonus = 33f;
    public static float ROFBonus = -33.0f;
    public static float FLUXBonus = -50.0f;
    public static final float SUP_Bonus = 200f;
    public static float RECOIL_BONUS = 33f;

    private final IntervalUtil GlowSS = new IntervalUtil(0.3F, 0.3F);
    private boolean runOnce = false;
    private Vector2f VEL = null;
    private final IntervalUtil TX = new IntervalUtil(0.05F, 0.05F);
    protected String id2 = "PAC1";
    public WthC_PaleAndCrimson() {
    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padL = 20f;
        float padS = 2f;

        tooltip.addSectionHeading("Introduction", Alignment.TMID, 10.0F);
        tooltip.addPara("Beyond its excessively wide weapon firing arcs and sigh-inducing maintenance difficulty, you can find too many luxurious, almost wasteful designs on this ship... In an almost apocalyptic era, deploying this ship is enough to drain the majority of the supply quota a large fleet can allocate.", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Fortunately... when it charges into battle, this elegant vessel lives up to its nobility.", WthC_ColorData.B_WHITE, pad);

        tooltip.addSectionHeading("Effects on the Ship", Alignment.TMID, 10.0F);
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXT1"), pad, Misc.getPositiveHighlightColor(), "#", "2", "4", "8");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#", "50%");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXT22"), padS, Misc.getPositiveHighlightColor(), "#", "33%");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXT3"), padS, Misc.getPositiveHighlightColor(), "#", "50%");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXT4"), padS, Misc.getPositiveHighlightColor(), "#", "100%");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXT5"), padS, Misc.getPositiveHighlightColor(), "#", "100%");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXT6"), padS, Misc.getPositiveHighlightColor(), "#", "33%");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXT7"), padS, Misc.getPositiveHighlightColor(), "#", "15%");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXT8"), padS, Misc.getPositiveHighlightColor(), "#", "50%");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXT9"), padS, Misc.getPositiveHighlightColor(), "#", "Ring Spirit Interface");

        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXTN1"), pad, Misc.getNegativeHighlightColor(), "#", "33%");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXTN2"), padS, Misc.getNegativeHighlightColor(), "#", "15%");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXTN3"), padS, Misc.getNegativeHighlightColor(), "#", "200%");

        tooltip.addSectionHeading("Limitations and Conflicts", Alignment.TMID, 10.0F);
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXTN4"), pad, Misc.getNegativeHighlightColor(), "#", "Cannot");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXTN5"), padS, Misc.getNegativeHighlightColor(), "#", "Incompatible");
        tooltip.addPara(" %s " + strings.get("PaleAndCrimson_TEXTN6"), padS, Misc.getNegativeHighlightColor(), "#", "Ring Spirit Protocol", "Pale Contract");
    }
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {

        stats.getDynamic().getMod("large_ballistic_mod").modifyFlat(id, COST_REDUCTION1);
        stats.getDynamic().getMod("large_energy_mod").modifyFlat(id, COST_REDUCTION1);
        stats.getDynamic().getMod("medium_ballistic_mod").modifyFlat(id, COST_REDUCTION2);
        stats.getDynamic().getMod("medium_energy_mod").modifyFlat(id, COST_REDUCTION2);
        stats.getDynamic().getMod("small_ballistic_mod").modifyFlat(id, COST_REDUCTION3);
        stats.getDynamic().getMod("small_energy_mod").modifyFlat(id, COST_REDUCTION3);

        stats.getWeaponTurnRateBonus().modifyPercent(id, WEAPON_Bonus);
        stats.getAutofireAimAccuracy().modifyPercent(id, WEAPON_Bonus);
        stats.getProjectileSpeedMult().modifyPercent(id, ProjectileSpeed_Bonus);
        stats.getEmpDamageTakenMult().modifyMult(id, 0.5F);
        stats.getBallisticRoFMult().modifyPercent(id, ROFBonus);
        stats.getEnergyRoFMult().modifyPercent(id, ROFBonus);
        stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FLUXBonus);
        stats.getEnergyWeaponFluxCostMod().modifyPercent(id, FLUXBonus);
        stats.getMaxRecoilMult().modifyMult(id, 1f - (0.01f * RECOIL_BONUS));
        stats.getRecoilPerShotMult().modifyMult(id, 1f - (0.01f * RECOIL_BONUS));
        stats.getRecoilDecayMult().modifyMult(id, 1f - (0.01f * RECOIL_BONUS));
        stats.getMaxCombatReadiness().modifyFlat(id2, -0.85F);

        stats.getMaxCombatReadiness().modifyFlat(id, 0.15f);
        stats.getBeamWeaponDamageMult().modifyMult(id, 0.85F);
        stats.getSuppliesPerMonth().modifyPercent(id, SUP_Bonus);
    }

    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        ship.addListener(new IEBaptismPartScatterAmpDamageDealtMod(ship));

        for (String tmp : BLOCKED_HULLMODS) {
            if (ship.getVariant().getHullMods().contains(tmp)) {
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "paleandcrimson");
            }
        }
    }
    public void advanceInCombat(ShipAPI ship, float amount) {
        if (ship == null) {
            return;
        }
        this.GlowSS.advance(amount);
        if (!this.runOnce) {
            this.runOnce = true;
        }
        CombatEngineAPI engine = Global.getCombatEngine();
        if (!ship.isAlive()) return;
        if (engine == null) return;

        //残影特效
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
                            Misc.scaleAlpha(WthC_ColorData.LIGHT_CRIMSON, 1f), //图片颜色
                            0f,  //抖动范围
                            0f,  //抖动频率
                            0f,  //闪烁范围
                            0f,  //闪烁频率
                            0f,  //闪烁及抖动延迟
                            0f,  //渐入时间
                            0.05f,  //持续时间
                            0.25f,  //渐出时间
                            true,  //是否死亡后渐出
                            CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                            GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
                    );
        }
    }
    public boolean affectsOPCosts() {
        return true;
    }

    public static class IEBaptismPartScatterAmpDamageDealtMod implements DamageDealtModifier {
        protected ShipAPI ship;
        protected static final float ratio = 0.5f;
        protected static final String KEY = "Part_Scatter_Amp1";

        public IEBaptismPartScatterAmpDamageDealtMod(ShipAPI ship) {
            this.ship = ship;
        }

        public String modifyDamageDealt(Object param,
                                        CombatEntityAPI target, DamageAPI damage,
                                        Vector2f point, boolean shieldHit) {

            if (!(param instanceof DamagingProjectileAPI) && param instanceof BeamAPI) {
                BeamAPI beam = (BeamAPI) param;
                damage.getModifier().unmodify(KEY);
                if (shieldHit) {
                    float amount = damage.getDamage();
                    if (damage.isDps())
                        amount = amount * 0.1f;
                    damage.getModifier().modifyMult(KEY, (1f - ratio));
                    Global.getCombatEngine().applyDamage(target, point, amount * ratio, damage.getType(), 0f, false,
                            false, beam.getSource(), false);
                    return KEY;
                }
            }
            return null;
        }
    }

    static {
        BLOCKED_HULLMODS.add("high_scatter_amp");
        BLOCKED_HULLMODS.add("advancedoptics");
        BLOCKED_HULLMODS.add("turretgyros");
    }

}
