package data.skills.DarkReal;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import java.util.Iterator;


public class DR_SuDan {
    public static final float FLUX_COIL_MULT = 1.25f;
    private static final class State {
        float num5;

        private State() {
            num5 = 0;
        }
    }
    public DR_SuDan() {
    }

    public static class DarkReal implements AdvanceableListener {

        protected MutableShipStatsAPI stats;
        protected ShipAPI ship;
        protected String id = "WitchSudan11";
        protected String id233 = "WitchSudan12";
        State sAll = new State();



        public DarkReal(ShipAPI ship) {
            this.ship = ship;
            this.stats = ship.getMutableStats();
        }

        public void advance(float amount) {

            if (ship.isAlive()){
                sAll.num5 = 0f;
                Iterator<String> i$ = ship.getVariant().getHullMods().iterator();

                String tmp;
                while(i$.hasNext()) {
                    tmp = i$.next();
                    if (Global.getSettings().getHullModSpec(tmp).hasTag("dmod")) {
                        sAll.num5 += 1;
                    }
                }
                if (sAll.num5 >= 1){
                    stats.getBallisticWeaponDamageMult().modifyPercent(id233, 2.5F);
                    stats.getEnergyWeaponDamageMult().modifyPercent(id233, 2.5F);
                    stats.getMissileWeaponDamageMult().modifyPercent(id233, 2.5F);
                }
            }

        }

    }

    public static class Level1A extends BaseSkillEffectDescription implements ShipSkillEffect, AfterShipCreationSkillEffect {

        public Level1A() {
        }

        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {

        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getMaxSpeed().modifyPercent(id, 15F);
            stats.getBallisticWeaponDamageMult().modifyPercent(id, 15F);
            stats.getEnergyWeaponDamageMult().modifyPercent(id, 15F);
            stats.getMissileWeaponDamageMult().modifyPercent(id, 15F);
            stats.getShieldDamageTakenMult().modifyMult(id, 1.15F);
            stats.getArmorDamageTakenMult().modifyMult(id, 1.15F);
            stats.getHullDamageTakenMult().modifyMult(id, 1.15F);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getMaxSpeed().unmodify(id);
            stats.getWeaponDamageTakenMult().unmodify(id);
            stats.getShieldDamageTakenMult().unmodify(id);
            stats.getArmorDamageTakenMult().unmodify(id);
            stats.getHullDamageTakenMult().unmodify(id);
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
            info.addPara(" %s ship maximum speed", pad, Misc.getPositiveHighlightColor(), new String[]{"+15%"});
            info.addPara(" %s ship weapon damage", pads, Misc.getPositiveHighlightColor(), new String[]{"+15%"});
            info.addPara(" %s damage taken by ships", pads, Misc.getPositiveHighlightColor(), new String[]{"+15%"});
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
            stats.getDynamic().getMod(Stats.INDIVIDUAL_SHIP_RECOVERY_MOD).modifyFlat(id, 1000f);
            stats.getBreakProb().modifyMult(id, 0f);
        }

        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getDynamic().getMod(Stats.INDIVIDUAL_SHIP_RECOVERY_MOD).unmodify(id);
            stats.getBreakProb().unmodify(id);
        }

        public void createCustomDescription(MutableCharacterStatsAPI stats, SkillSpecAPI skill, TooltipMakerAPI info, float width) {
            this.initElite(stats, skill);
            float pad = 5.0F;
            float pads = 2.0F;

            info.addPara("When ships are disabled they won't break apart and almost always recover after battle",Misc.getPositiveHighlightColor(), pad);
            info.addPara("Each %s D-mod on a ship increases weapon damage by %s and damage taken by %s", pads, Misc.getHighlightColor(), new String[]{"1","2.5%","2.5%"});
            
        }
    }
}
