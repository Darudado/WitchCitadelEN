package data.skills.DarkReal;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.combat.listeners.DamageDealtModifier;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;

// 原版战斗耐力修改

public class DR_Nikushoku {

    public static final float maxDamage = 150000f;


    //参考rat的seraphcoreskill, 重写
    public static class NikushokuSkillListener implements DamageDealtModifier, AdvanceableListener {
        protected ShipAPI ship;
        protected MutableShipStatsAPI stats;
        private float damageDealt = 0f;

        protected String modID= "Nikushoku" ;

        public NikushokuSkillListener(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {
            if(damageDealt >= maxDamage){
                this.stats.getMaxSpeed().modifyPercent(modID, 33f);
            }
            float bonuslevel = damageDealt/maxDamage;
            this.stats.getEnergyWeaponRangeBonus().modifyPercent(modID, 0.3f * bonuslevel);
            this.stats.getBallisticWeaponRangeBonus().modifyPercent(modID, 0.3f * bonuslevel);
        }

        public String modifyDamageDealt(Object param, CombatEntityAPI target, DamageAPI damage, Vector2f point, boolean shieldHit) {
            if (!(target instanceof ShipAPI)) return null;
            if (!((ShipAPI) target).isAlive()) return null;
            if (target.getOwner() == ship.getOwner()) return null;
            float currentDamage = 0f;
            if (param instanceof BeamAPI){
                currentDamage += damage.getDamage() * damage.getDpsDuration();
            }else{
                currentDamage += damage.getDamage();
            }
            damageDealt += currentDamage;
            damageDealt = MathUtils.clamp(damageDealt, 0, maxDamage);

            float recovered = currentDamage * 0.1f;

            if(this.ship.getHitpoints() <= this.ship.getMaxHitpoints() ){
                this.ship.setHitpoints(ship.getHitpoints() + recovered);

                float over = 0f;
                if (this.ship.getHitpoints() > this.ship.getMaxHitpoints()){
                    over = ship.getHitpoints() - ship.getMaxHitpoints();
                    this.ship.setHitpoints(ship.getHitpoints() - over);
                }
            }
            return null;
        }
    }

    public static boolean isFRIGATE(MutableShipStatsAPI stats) {
        if (stats.getEntity() instanceof ShipAPI) {
            ShipAPI ship = (ShipAPI)stats.getEntity();
            if (!ship.isFrigate()) {
                return false;
            } else {
                return ship.isFrigate();
            }
        } else {
            FleetMemberAPI member = stats.getFleetMember();
            if (member == null) {
                return false;
            } else if (!member.isFrigate()) {
                return false;
            } else {
                return member.isFrigate();
            }
        }
    }





    public static class Level1A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level1A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new NikushokuSkillListener(ship));
        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(NikushokuSkillListener.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
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
            float pad = 10.0F;
            float pads = 2.0F;
            info.addPara("Ships restore health equal to %s of damage dealt", pad, Misc.getPositiveHighlightColor(), new String[]{"10%"});
            info.addPara("Ballistic and energy weapon damage increases up to %s as total damage dealt approaches threshold of %s ", pads, Misc.getPositiveHighlightColor(), new String[]{"30%","150000"});
            info.addPara("When damage threshold is reached, gain additional %s maximum speed", pads, Misc.getPositiveHighlightColor(), new String[]{"33%"});}
    }




    public static class Level2A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {
        public Level2A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            float baseCost = stats.getSuppliesToRecover().getBaseValue();
            float penalty = Math.min(15f, baseCost * 0.25F);
            if (!isFRIGATE(stats)) {
                stats.getPeakCRDuration().modifyPercent(id, -33f);
                stats.getBallisticWeaponFluxCostMod().modifyPercent(id, -20f);
                stats.getEnergyWeaponFluxCostMod().modifyPercent(id, -20f);
            }else{
                stats.getDynamic().getMod("deployment_points_mod").modifyFlat(id, penalty);
                stats.getZeroFluxSpeedBoost().modifyFlat(id, 50f);
                stats.getArmorDamageTakenMult().modifyPercent(id, -30f);
                stats.getHullDamageTakenMult().modifyPercent(id, -30f);
            }
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            if (!isFRIGATE(stats)) {
                stats.getPeakCRDuration().unmodify(id);
                stats.getBallisticWeaponFluxCostMod().unmodify(id);
                stats.getEnergyWeaponFluxCostMod().unmodify(id);
            }else{
                stats.getDynamic().getMod("deployment_points_mod").unmodify(id);
                stats.getZeroFluxSpeedBoost().unmodify(id);
                stats.getArmorDamageTakenMult().unmodify(id);
                stats.getHullDamageTakenMult().unmodify(id);
            }
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
            float pad = 10.0F;
            float pads = 2.0F;
            info.addPara("All non-frigates:", Misc.getNegativeHighlightColor(), pad);
            info.addPara("   %s ship peak performance time", pads, Misc.getNegativeHighlightColor(), new String[]{"-33%"});
            info.addPara("   %s weapon flux generation", pads, Misc.getPositiveHighlightColor(), new String[]{"-20%"});

            info.addPara("All frigates:", Misc.getNegativeHighlightColor(), pad);
            info.addPara("   %s deployment points", pad, Misc.getNegativeHighlightColor(), new String[]{"+25%"});
            info.addPara("   %s zero-flux boost", pads, Misc.getPositiveHighlightColor(), new String[]{"+50"});
            info.addPara("   %s armor and hull damage taken", pads, Misc.getPositiveHighlightColor(), new String[]{"-30%"});
        }
    }


}