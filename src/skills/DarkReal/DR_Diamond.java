package data.skills.DarkReal;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


public class DR_Diamond {
    private static final class State {
        int num;
        int num2;
        int num3;
        float num5;

        private State() {
            num = 0;
            num2 = 0;
            num3 = 0;
            num5 = 0;

        }
    }

    public DR_Diamond() {
    }


    public static class DarkReal implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "DR_Diamond1";
        protected String id233 = "DR_Diamond2";
        State sAll = new State();


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
            stats.getDynamic().getMod("deployment_points_mod").modifyMult(id, 0.75f);
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
            info.addPara("-25% all ships deployment points", WthC_ColorData.MYSTERIOUS_PURPLE,pad);
            info.addPara("Possesses officer quality far beyond normal standards", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara("All ships permanently lock to maximum combat readiness after entering battle", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara("All ships have more modules and cannot be salvaged", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
            info.addPara("All ships gain additional bonuses when player deployment points exceed vanilla limit in combat", WthC_ColorData.MYSTERIOUS_PURPLE,pads);
        }
    }
    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getDynamic().getStat(Stats.EXPLOSION_DAMAGE_MULT).modifyMult(id, 2.5F);
            stats.getDynamic().getStat(Stats.EXPLOSION_RADIUS_MULT).modifyMult(id, 2.5F);
            stats.getDamageToFrigates().modifyPercent(id, 20F);
            stats.getDamageToDestroyers().modifyPercent(id, 20F);
            stats.getDamageToCruisers().modifyPercent(id, 20F);
            stats.getDamageToCapital().modifyPercent(id, 20F);
            stats.getDamageToMissiles().modifyPercent(id, 20F);
            stats.getDamageToFighters().modifyPercent(id, 20F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getDynamic().getStat(Stats.EXPLOSION_DAMAGE_MULT).unmodify(id);
            stats.getDynamic().getStat(Stats.EXPLOSION_RADIUS_MULT).unmodify(id);
            stats.getDamageToFrigates().unmodify(id);
            stats.getDamageToDestroyers().unmodify(id);
            stats.getDamageToCruisers().unmodify(id);
            stats.getDamageToCapital().unmodify(id);
            stats.getDamageToMissiles().unmodify(id);
            stats.getDamageToFighters().unmodify(id);
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

            info.addPara("All ships in combat gain %s :", pad, Misc.getHighlightColor(), new String[]{"following bonuses"});
            info.addPara("    %s ship damage dealt", pads, Misc.getPositiveHighlightColor(), new String[]{"+20%"});
            info.addPara("    %s ship explosion radius", pads, Misc.getPositiveHighlightColor(), new String[]{"+250%"});
            info.addPara("    %s ship explosion damage", pads, Misc.getPositiveHighlightColor(), new String[]{"+250%"});

        }
    }
}
