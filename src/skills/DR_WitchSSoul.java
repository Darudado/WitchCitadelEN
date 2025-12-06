package data.skills;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DR_WitchSSoul {
    private static final class State {
        int num;
        int num2;
        int num3;
        int num4;
        float num5;

        private State() {
            num = 0;
            num2 = 0;
            num3 = 0;
            num4 = 0;
        }
    }

    public DR_WitchSSoul() {
    }


    public static class DarkReal implements AdvanceableListener {
        State sAll = new State();
        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchSSoul1";
        protected String id233 = "WitchSSoul2";


        public DarkReal(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            sAll.num5 = 0f;
            for (ShipAPI ship : Global.getCombatEngine().getShips()) {
                if (ship.getOwner() == Misc.OWNER_PLAYER) {
                    if (ship.getFleetMember() != null){
                        sAll.num5 += ship.getFleetMember().getDeploymentPointsCost();
                        // 这是玩家
                    }
                }
            }
            if (sAll.num5 > 240){
                float reduction = Math.min(0.05f, (1- (((sAll.num5 - 240) / 5) / 100)));
                stats.getMaxSpeed().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getFluxDissipation().modifyPercent(id233, (((sAll.num5 - 240) / 5) / 5));
                stats.getFluxCapacity().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getEnergyWeaponRangeBonus().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getBallisticWeaponRangeBonus().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getDamageToFrigates().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getDamageToDestroyers().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getDamageToCruisers().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getDamageToCapital().modifyPercent(id233, ((sAll.num5 - 240) / 5));
                stats.getHullDamageTakenMult().modifyMult(id233,reduction);
                stats.getArmorDamageTakenMult().modifyMult(id233,reduction);
                stats.getShieldDamageTakenMult().modifyMult(id233,reduction);
            } else {
                stats.getMaxSpeed().unmodify(id233);
                stats.getFluxDissipation().unmodify(id233);
                stats.getFluxCapacity().unmodify(id233);
                stats.getEnergyWeaponRangeBonus().unmodify(id233);
                stats.getBallisticWeaponRangeBonus().unmodify(id233);
                stats.getDamageToFrigates().unmodify(id233);
                stats.getDamageToDestroyers().unmodify(id233);
                stats.getDamageToCruisers().unmodify(id233);
                stats.getDamageToCapital().unmodify(id233);
                stats.getHullDamageTakenMult().unmodify(id233);
                stats.getArmorDamageTakenMult().unmodify(id233);
                stats.getShieldDamageTakenMult().unmodify(id233);
            }

            if (ship.isAlive()) {
                if(ship.getCurrentCR() != 1F){
                    ship.setCurrentCR(1f);
                }

            }
        }

    }

    public static class DR_SSoul implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchSSoul3";
        protected String id2 = "WitchSSoul4";
        private final IntervalUtil SS = new IntervalUtil(1F, 1F);

        private boolean runOnce = false;
        private WeaponAPI Weapon;
        State sAll = new State();
        CombatEngineAPI engine = Global.getCombatEngine();
        public DR_SSoul(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            this.SS.advance(amount);
            if (!runOnce) {
                for (WeaponAPI w : ship.getAllWeapons()) {
                    if (w.getSlot().getId().equals("Weapon")) {
                        Weapon = w;
                    }
                }
                runOnce = true;
            }

            if (this.ship.getFullTimeDeployed() < 1f){
                sAll.num = 0;
                sAll.num2 = 0;
                sAll.num3 = 0;
                sAll.num4 = 0;
            }
            if (ship.isAlive()) {
                if(SS.intervalElapsed()){
                    sAll.num++;
                }
                if (sAll.num == 90){
                    sAll.num2++;
                    sAll.num = 0;
                }
                if (sAll.num2 == 1){
                    engine.addFloatingText(this.ship.getLocation(), "Myriad Sync: Phase One",50F, WthC_ColorData.SOUL_GREEN, this.ship, 5F,5F);
                    sAll.num3++;
                    sAll.num2++;
                }
                if (sAll.num2 == 3){
                    engine.addFloatingText(this.ship.getLocation(), "Myriad Sync: Phase Two",50F, WthC_ColorData.SOUL_GREEN, this.ship, 5F,5F);
                    sAll.num3++;
                    sAll.num2++;
                }
                if (sAll.num2 == 5){
                    engine.addFloatingText(this.ship.getLocation(), "Myriad Sync: Phase Three",50F, WthC_ColorData.SOUL_GREEN, this.ship, 5F,5F);
                    sAll.num3++;
                    sAll.num2++;
                }
                if (sAll.num3 == 1){
                    stats.getTimeMult().modifyPercent(id2,25F);
                } else if (sAll.num3 == 2) {
                    stats.getTimeMult().modifyPercent(id2,50F);
                } else if (sAll.num3 == 3) {
                    stats.getTimeMult().modifyPercent(id2,75F);
                }
            }
            if (ship.getFluxTracker().isOverloaded()){
                Vector2f loc = MathUtils.getRandomPointInCircle(ship.getLocation(), 360f);
                Float numMax = 1f;
                if (sAll.num4 < 3){
                    for (int i = 0; i < numMax; i = i + 1) {
                        engine.spawnProjectile(ship, Weapon, "WthC_starsnode", loc, ship.getFacing(), null);
                        engine.spawnProjectile(ship, Weapon, "WthC_starsnode_elementS_soil", loc, ship.getFacing(), null);
                        engine.spawnProjectile(ship, Weapon, "WthC_starsnode_elementS_water", loc, ship.getFacing(), null);
                        engine.spawnProjectile(ship, Weapon, "WthC_starsnode_elementS_gold", loc, ship.getFacing(), null);
                        sAll.num4++;
                    }
                }
            } else {
                sAll.num4 = 0;
            }
        }

    }

    public static class Level1A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new DarkReal(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(DarkReal.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getDynamic().getMod("deployment_points_mod").modifyMult(id, 0.05f);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getDynamic().getMod("deployment_points_mod").unmodifyMult(id);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
        }
    }
    public static class Level1B extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1B() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.init(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;
            info.addPara("All ships will no longer be restricted by deployment points", WthC_ColorData.MYSTERIOUS_PURPLE,pad);
            info.addPara("Has significantly higher overall officer levels", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara("All ships permanently lock into maximum combat readiness after entering battle", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara("All ships have more built-in hullmods and cannot be salvaged", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara("When player's deployment points exceed vanilla limit in combat, all ships gain additional bonuses", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
        }
    }
    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new DR_SSoul(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(DR_SSoul.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getTimeMult().modifyPercent(id,25F);

        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {

        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
        }
    }
    public static class Level2B extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2B() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {

        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {

        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.initElite(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;

            info.addPara("All ships in combat additionally gain %s :", pad, Misc.getHighlightColor(), new String[]{"following modifiers"});
            info.addPara("    %s ship time flow", pads, Misc.getPositiveHighlightColor(), new String[]{"+25%"});

            info.addPara("Every %s seconds in combat:", pad, Misc.getHighlightColor(), new String[]{"90"});
            info.addPara("   Additional %s ship time flow, up to %s ", pads, Misc.getPositiveHighlightColor(), new String[]{"+25%","100%"});

            info.addPara("Whenever the ship %s in combat:", pad, Misc.getHighlightColor(), new String[]{"overloads"});
            info.addPara("   Generates %s of elemental energy barrage", pads, Misc.getPositiveHighlightColor(), new String[]{"a wave"});
            
        }
    }
}
