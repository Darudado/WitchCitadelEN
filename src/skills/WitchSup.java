package data.skills;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.characters.*;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEngineLayers;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.skills.RankB.*;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicRender;

import java.util.Objects;


public class WitchSup {
    private static final class State {
        float RRA1;
        float RRA2;
        float IE1;
        float IE2;
        float CROSS1;
        float CROSS2;
        float ELF1;
        float ELF2;
        float ELF3;
        float DR1;
        float DR2;
        float DR3;

        private State() {
            RRA1 = 0;
            RRA2 = 0;
            IE1 = 0;
            IE2 = 0;
            CROSS1 = 0;
            CROSS2 = 0;
            ELF1 = 0;
            ELF2 = 0;
            ELF3 = 0;
            DR1 = 0;
            DR2 = 0;
            DR3 = 0;
        }
    }
    public WitchSup() {
    }


    public static class Sup implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchSupE1";
        protected String id123 = "WitchSupE2";
        protected CampaignFleetAPI player_fleet;
        private final IntervalUtil Sec = new IntervalUtil(1.0F, 1.0F);
        private final IntervalUtil EEE = new IntervalUtil(9999.0F, 9999.0F);
        private boolean runOnce = false;
        State sAll = new State();



        public Sup(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            if (ship == null) {
                return;
            }
            this.Sec.advance(amount);
            this.EEE.advance(amount);
            if (!this.runOnce) {
                this.runOnce = true;
            }
            CombatEngineAPI engine = Global.getCombatEngine();
            if (!ship.isAlive()) return;
            if (engine == null) return;

            if (ship.getVariant().hasHullMod("shadowremainrra")){
                if (ship.isAlive()){
                    if(!(ship.isStationModule())){
                        if (ship.getFullTimeDeployed() < 1f) {
                            sAll.RRA1 = 0;
                            sAll.RRA2 = 0;
                        }
                    }

                    if (sAll.RRA1 == 0){
                        if (ship.getHitpoints() < ship.getMaxHitpoints()){
                            engine.addFloatingText(ship.getLocation(), "Rejection Protocol Triggered...", 50F, WthC_ColorData.DEEP_BLUE, ship, 5F, 5F);
                            sAll.RRA1++;
                        }
                    }
                    if (sAll.RRA1 == 1){
                        ship.getMutableStats().getHullDamageTakenMult().modifyMult(id123, 0.05F);
                        ship.getMutableStats().getArmorDamageTakenMult().modifyMult(id123, 0.05F);
                        ship.getMutableStats().getShieldDamageTakenMult().modifyMult(id123, 0.05F);
                        ship.setJitterUnder(ship, WthC_ColorData.HIGH_BLUE, 5f, 5, 5f);
                        ship.setJitterShields(false);
                        if (Sec.intervalElapsed()){
                            sAll.RRA2++;
                        }
                    } else {
                        ship.getMutableStats().getHullDamageTakenMult().unmodify(id123);
                        ship.getMutableStats().getArmorDamageTakenMult().unmodify(id123);
                        ship.getMutableStats().getShieldDamageTakenMult().unmodify(id123);
                    }
                    if (sAll.RRA2 == 6){
                        sAll.RRA1++;
                    }
                }

                if (ship == Global.getCombatEngine().getPlayerShip()) {
                    if (sAll.RRA1 == 0) {
                        Global.getCombatEngine().maintainStatusForPlayerShip("WthC_ShadowRemain_RRA", "graphics/icons/hullsys/GatherStarCore.png", "Shadow Remnant - Rui Yi", "Emergency Rejection Ready", false);
                    } else {
                        Global.getCombatEngine().maintainStatusForPlayerShip("WthC_ShadowRemain_RRA", "graphics/icons/hullsys/GatherStarCore.png", "Shadow Remnant - Rui Yi", "Emergency Rejection Consumed", true);
                    }
                }
            }

            if (ship.getVariant().hasHullMod("shadowremainie")){
                if (ship.isAlive()){
                    if(!(ship.isStationModule())){
                        if (ship.getFullTimeDeployed() < 1f) {
                            sAll.IE1 = 0;
                            sAll.IE2 = 0;
                        }
                    }

                    if (sAll.IE1 == 0){
                        if (ship.getHardFluxLevel() > 0.8F){
                            ship.setOverloadColor(WthC_ColorData.IE_WHITE);
                            ship.getFluxTracker().beginOverloadWithTotalBaseDuration(3F);
                            engine.addFloatingText(ship.getLocation(), "Retrospection Protocol Triggered...", 50F, WthC_ColorData.IE_WHITE, ship, 5F, 5F);
                            sAll.IE1++;
                        }
                    }
                    if (sAll.IE1 == 1){
                        ship.getMutableStats().getFluxDissipation().modifyPercent(id123, 500F);
                        if (Sec.intervalElapsed()){
                            sAll.IE2++;
                        }
                        if (sAll.IE2 == 4){
                            ship.getMutableStats().getFluxDissipation().unmodify(id123);
                            sAll.IE1++;
                        }
                    }
                }

                if (ship == Global.getCombatEngine().getPlayerShip()) {
                    if (sAll.IE1 == 0) {
                        Global.getCombatEngine().maintainStatusForPlayerShip("WthC_ShadowRemain_IE", "graphics/icons/hullsys/GatherStarCore.png", "Shadow Remnant - Yuanchu", "Emergency Retrospection Ready", false);
                    } else {
                        Global.getCombatEngine().maintainStatusForPlayerShip("WthC_ShadowRemain_IE", "graphics/icons/hullsys/GatherStarCore.png", "Shadow Remnant - Yuanchu", "Emergency Retrospection Consumed", true);
                    }
                }
            }

            if (ship.getVariant().hasHullMod("shadowremaincross")){
                if (ship.isAlive()){
                    if (ship.getFullTimeDeployed() < 1f) {
                        sAll.CROSS1 = 0;
                        sAll.CROSS2 = 0;
                    }
                    if (Sec.intervalElapsed()){
                        sAll.CROSS1++;
                    }
                    if (sAll.CROSS1 == 10){
                        sAll.CROSS2++;
                        sAll.CROSS1 = 0;
                    }
                    if (sAll.CROSS2 == 0){
                        ship.getMutableStats().getBallisticRoFMult().modifyPercent(id123, 25F);
                        ship.getMutableStats().getEnergyRoFMult().modifyPercent(id123, 25F);
                        ship.getMutableStats().getMissileRoFMult().modifyPercent(id123, 25F);
                        ship.getMutableStats().getDamageToFrigates().modifyPercent(id123, -15F);
                        ship.getMutableStats().getDamageToDestroyers().modifyPercent(id123, -15F);
                        ship.getMutableStats().getDamageToCruisers().modifyPercent(id123, -15F);
                        ship.getMutableStats().getDamageToCapital().modifyPercent(id123, -15F);
                    }
                    if (sAll.CROSS2 == 1){
                        ship.getMutableStats().getBallisticRoFMult().modifyPercent(id123, 10F);
                        ship.getMutableStats().getEnergyRoFMult().modifyPercent(id123, 10F);
                        ship.getMutableStats().getMissileRoFMult().modifyPercent(id123, 10F);
                        ship.getMutableStats().getDamageToFrigates().modifyPercent(id123, 5F);
                        ship.getMutableStats().getDamageToDestroyers().modifyPercent(id123, 5F);
                        ship.getMutableStats().getDamageToCruisers().modifyPercent(id123, 5F);
                        ship.getMutableStats().getDamageToCapital().modifyPercent(id123, 5F);
                    }
                    if (sAll.CROSS2 == 2){
                        ship.getMutableStats().getBallisticRoFMult().modifyPercent(id123, -25F);
                        ship.getMutableStats().getEnergyRoFMult().modifyPercent(id123, -25F);
                        ship.getMutableStats().getMissileRoFMult().modifyPercent(id123, -25F);
                        ship.getMutableStats().getDamageToFrigates().modifyPercent(id123, -25F);
                        ship.getMutableStats().getDamageToDestroyers().modifyPercent(id123, 15F);
                        ship.getMutableStats().getDamageToCruisers().modifyPercent(id123, 15F);
                        ship.getMutableStats().getDamageToCapital().modifyPercent(id123, 15F);
                    }
                    if (sAll.CROSS2 == 3){
                        sAll.CROSS2 = 0;
                    }
                }

                if (ship == Global.getCombatEngine().getPlayerShip()) {
                    if (sAll.CROSS2 == 0) {
                        Global.getCombatEngine().maintainStatusForPlayerShip("WthC_ShadowRemain_Cross", "graphics/icons/hullsys/GatherStarCore.png", "Shadow Remnant - Cross", "Increased Rate of Fire, Reduced Damage", true);
                    }
                    if (sAll.CROSS2 == 1) {
                        Global.getCombatEngine().maintainStatusForPlayerShip("WthC_ShadowRemain_Cross", "graphics/icons/hullsys/GatherStarCore.png", "Shadow Remnant - Cross", "Increased Rate of Fire and Damage", false);
                    }
                    if (sAll.CROSS2 == 2) {
                        Global.getCombatEngine().maintainStatusForPlayerShip("WthC_ShadowRemain_Cross", "graphics/icons/hullsys/GatherStarCore.png", "Shadow Remnant - Cross", "Increased Damage, Reduced Rate of Fire", true);
                    }
                }
            }

            if (ship.getVariant().hasHullMod("shadowremainelf")){
                if (ship.isAlive()){
                    if (ship.getFullTimeDeployed() < 1f) {
                        sAll.ELF1 = 0;
                        sAll.ELF2 = 0;
                        sAll.ELF3 = 0;
                    }
                    if (Sec.intervalElapsed()){
                        sAll.ELF1++;
                    }
                    if (sAll.ELF1 == 180){
                        sAll.ELF2++;
                        engine.addFloatingText(ship.getLocation(), "Reassembly Protocol Triggered...", 50F, WthC_ColorData.EIF_ORANGE, ship, 5F, 5F);
                    }
                    if (sAll.ELF2 >0){
                        if (Sec.intervalElapsed()){
                            sAll.ELF3++;
                        }
                        if (sAll.ELF3 < 5){
                            ship.getMutableStats().getDynamic().getStat("replacement_rate_increase_mult").modifyPercent(id123, 1000f);
                            ship.getMutableStats().getDynamic().getStat("replacement_rate_decrease_mult").modifyPercent(id123, -1000f);
                        } else {
                            ship.getMutableStats().getDynamic().getStat("replacement_rate_increase_mult").unmodify(id123);
                            ship.getMutableStats().getDynamic().getStat("replacement_rate_decrease_mult").unmodify(id123);
                            sAll.ELF1 = 0;
                            sAll.ELF2 = 0;
                            sAll.ELF3 = 0;
                        }
                    }
                }
                int Sec2 = (int) (180-sAll.ELF1);
                if (ship == Global.getCombatEngine().getPlayerShip()) {
                    if (sAll.ELF2 == 0) {
                        Global.getCombatEngine().maintainStatusForPlayerShip("WthC_ShadowRemain_Elf", "graphics/icons/hullsys/GatherStarCore.png", "Shadow Remnant - Elf", "Next Reassembly in " + Sec2 + " seconds", false);
                    } else {
                        Global.getCombatEngine().maintainStatusForPlayerShip("WthC_ShadowRemain_Elf", "graphics/icons/hullsys/GatherStarCore.png", "Shadow Remnant - Elf", "Reassembly in Progress...", false);
                    }

                }
            }
            if (ship.getFullTimeDeployed() < 0.01f) {
                sAll.DR1 = 0;
                sAll.DR2 = 0;
            }
            if (ship == Global.getCombatEngine().getPlayerShip()){
                SpriteAPI WaiHuan1 = Global.getSettings().getSprite("fx", "WaiHuan1");
                SpriteAPI WaiHuan11 = Global.getSettings().getSprite("fx", "WaiHuan1");
                SpriteAPI WaiHuan111 = Global.getSettings().getSprite("fx", "WaiHuan1");
                SpriteAPI WaiHuan2 = Global.getSettings().getSprite("fx", "WaiHuan2");
                SpriteAPI ZhongHuan2 = Global.getSettings().getSprite("fx", "ZhongHuan2");
                SpriteAPI NeiHuan2 = Global.getSettings().getSprite("fx", "NeiHuan2");
                Vector2f size = new Vector2f(4000,4000);
                Vector2f sizeM = new Vector2f(2500,2500);
                Vector2f sizeS = new Vector2f(1000,1000);
                if (sAll.DR1 == 0){
                    if(Global.getSector().getMemoryWithoutUpdate().getBoolean("$SSoulEX-ON")){
                        ship.addListener(new WitchSSoulPale.SSoul1(ship));
                        ship.addListener(new WitchSSoulPale.SSoul2(ship));
                        MagicRender.objectspace(
                                WaiHuan2, //图片
                                ship, //渲染对象
                                WthC_Util.ZERO, //偏移量
                                WthC_Util.ZERO, //偏移速度
                                size, //尺寸
                                ship.getRenderOffset(), //缩放
                                -180f, //角度
                                -15f, //旋转速率
                                true, //相对位置是否固定
                                Misc.scaleAlpha(WthC_ColorData.SOUL_GREEN_D, 0.8F), //图片颜色
                                0f,  //抖动范围
                                0f,  //抖动频率
                                0f,  //闪烁范围
                                0f,  //闪烁频率
                                0f,  //闪烁及抖动延迟
                                3f,  //渐入时间
                                9999f,  //持续时间
                                3f,  //渐出时间
                                true,  //是否死亡后渐出
                                CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                                GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
                        );
                    } else {
                        ship.removeListener(new WitchSSoulPale.SSoul1(ship));
                        ship.removeListener(new WitchSSoulPale.SSoul2(ship));
                    }
                    if(Global.getSector().getMemoryWithoutUpdate().getBoolean("$DustEX-ON")){
                        ship.addListener(new WitchDustPale.Dust1(ship));
                        ship.addListener(new WitchDustPale.Dust2(ship));
                        MagicRender.objectspace(
                                WaiHuan1, //图片
                                ship, //渲染对象
                                WthC_Util.ZERO, //偏移量
                                WthC_Util.ZERO, //偏移速度
                                size, //尺寸
                                ship.getRenderOffset(), //缩放
                                -180f, //角度
                                15f, //旋转速率
                                true, //相对位置是否固定
                                Misc.scaleAlpha(WthC_ColorData.DUST_RED, 0.8F), //图片颜色
                                0f,  //抖动范围
                                0f,  //抖动频率
                                0f,  //闪烁范围
                                0f,  //闪烁频率
                                0f,  //闪烁及抖动延迟
                                3f,  //渐入时间
                                9999f,  //持续时间
                                3f,  //渐出时间
                                true,  //是否死亡后渐出
                                CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                                GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
                        );
                    } else {
                        ship.removeListener(new WitchDustPale.Dust1(ship));
                        ship.removeListener(new WitchDustPale.Dust2(ship));
                    }

                    if(Global.getSector().getMemoryWithoutUpdate().getBoolean("$ElfEX-ON")){
                        ship.addListener(new WitchElfPale.Elf1(ship));
                        ship.addListener(new WitchElfPale.Elf2(ship));
                        MagicRender.objectspace(
                                WaiHuan11, //图片
                                ship, //渲染对象
                                WthC_Util.ZERO, //偏移量
                                WthC_Util.ZERO, //偏移速度
                                sizeM, //尺寸
                                ship.getRenderOffset(), //缩放
                                -180f, //角度
                                18f, //旋转速率
                                true, //相对位置是否固定
                                Misc.scaleAlpha(WthC_ColorData.EIF_ORANGE, 0.6F), //图片颜色
                                0f,  //抖动范围
                                0f,  //抖动频率
                                0f,  //闪烁范围
                                0f,  //闪烁频率
                                0f,  //闪烁及抖动延迟
                                3f,  //渐入时间
                                9999f,  //持续时间
                                3f,  //渐出时间
                                true,  //是否死亡后渐出
                                CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                                GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
                        );
                    } else {
                        ship.removeListener(new WitchElfPale.Elf1(ship));
                        ship.removeListener(new WitchElfPale.Elf2(ship));
                    }

                    if(Global.getSector().getMemoryWithoutUpdate().getBoolean("$CrossEX-ON")){
                        ship.addListener(new WitchCrossPale.Cross1(ship));
                        ship.addListener(new WitchCrossPale.Cross2(ship));
                        MagicRender.objectspace(
                                ZhongHuan2, //图片
                                ship, //渲染对象
                                WthC_Util.ZERO, //偏移量
                                WthC_Util.ZERO, //偏移速度
                                sizeM, //尺寸
                                ship.getRenderOffset(), //缩放
                                -180f, //角度
                                -18f, //旋转速率
                                true, //相对位置是否固定
                                Misc.scaleAlpha(WthC_ColorData.CROSS_GARY_D, 0.6F), //图片颜色
                                0f,  //抖动范围
                                0f,  //抖动频率
                                0f,  //闪烁范围
                                0f,  //闪烁频率
                                0f,  //闪烁及抖动延迟
                                3f,  //渐入时间
                                9999f,  //持续时间
                                3f,  //渐出时间
                                true,  //是否死亡后渐出
                                CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                                GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
                        );
                    } else {
                        ship.removeListener(new WitchCrossPale.Cross1(ship));
                        ship.removeListener(new WitchCrossPale.Cross1(ship));
                    }

                    if(Global.getSector().getMemoryWithoutUpdate().getBoolean("$IEEX-ON")){
                        ship.addListener(new WitchIEPale.IE1(ship));
                        ship.addListener(new WitchIEPale.IE2(ship));
                        MagicRender.objectspace(
                                WaiHuan111, //图片
                                ship, //渲染对象
                                WthC_Util.ZERO, //偏移量
                                WthC_Util.ZERO, //偏移速度
                                sizeS, //尺寸
                                ship.getRenderOffset(), //缩放
                                -180f, //角度
                                21f, //旋转速率
                                true, //相对位置是否固定
                                Misc.scaleAlpha(WthC_ColorData.IE_WHITE, 0.4F), //图片颜色
                                0f,  //抖动范围
                                0f,  //抖动频率
                                0f,  //闪烁范围
                                0f,  //闪烁频率
                                0f,  //闪烁及抖动延迟
                                3f,  //渐入时间
                                9999f,  //持续时间
                                3f,  //渐出时间
                                true,  //是否死亡后渐出
                                CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                                GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
                        );
                    } else {
                        ship.removeListener(new WitchIEPale.IE1(ship));
                        ship.removeListener(new WitchIEPale.IE2(ship));
                    }

                    if(Global.getSector().getMemoryWithoutUpdate().getBoolean("$RRAEX-ON")){
                        ship.addListener(new WitchRRAPale.RRA1(ship));
                        ship.addListener(new WitchRRAPale.RRA2(ship));
                        MagicRender.objectspace(
                                NeiHuan2, //图片
                                ship, //渲染对象
                                WthC_Util.ZERO, //偏移量
                                WthC_Util.ZERO, //偏移速度
                                sizeS, //尺寸
                                ship.getRenderOffset(), //缩放
                                -180f, //角度
                                -21f, //旋转速率
                                true, //相对位置是否固定
                                Misc.scaleAlpha(WthC_ColorData.DEEP_BLUE, 0.4F), //图片颜色
                                0f,  //抖动范围
                                0f,  //抖动频率
                                0f,  //闪烁范围
                                0f,  //闪烁频率
                                0f,  //闪烁及抖动延迟
                                3f,  //渐入时间
                                9999f,  //持续时间
                                3f,  //渐出时间
                                true,  //是否死亡后渐出
                                CombatEngineLayers.BELOW_SHIPS_LAYER,  //图层
                                GL11.GL_SRC_ALPHA, GL11.GL_ONE  //图层混合方式
                        );
                    } else {
                        ship.removeListener(new WitchRRAPale.RRA1(ship));
                        ship.removeListener(new WitchRRAPale.RRA2(ship));
                    }
                    sAll.DR1++;
                }

            }
        }

    }



    public static class Level1A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new Sup(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(Sup.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
        }
    }

}
