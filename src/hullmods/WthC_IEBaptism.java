package data.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.listeners.DamageDealtModifier;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.scripts.util.MagicIncompatibleHullmods;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import org.lwjgl.util.vector.Vector2f;

import java.util.*;

// Some formatting from Polaris
public class WthC_IEBaptism extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static final float BEAM_MULT = 0.85f;
    public static float ROFBonus = -15.0f;
    public static float FLUXBonus = -15.0f;

    public static final float BEAM_MULT2 = 25f;
    public static float ROFBonus2 = -30.0f;
    public static float RangeBonus = 15.0f;


    private static final Set<String> BLOCKED_HULLMODS = new HashSet();

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padS = 2f;
        if (ship != null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("This ship has undergone modifications from the Crimson Citadel, making it more aligned with the tactical style envisioned by its modifiers.", WthC_ColorData.MID_BLUE, pad);
            tooltip.addPara("However, it is clear that these seemingly flashy modifications have not undergone thorough battlefield testing. Keeping such a ship in the fleet is an act of great bravery.", WthC_ColorData.MID_BLUE, padS);
        }
        tooltip.addSectionHeading("Hull Modification Concept", Alignment.TMID, 10.0F);

        tooltip.addPara("Compared to the base version, the modifications on this ship are mainly reflected in the following aspects", WthC_ColorData.IE_WHITE, pad);
        tooltip.addPara("     %s " + strings.get("IEBapitsm_TEXT0"), pad, Misc.getPositiveHighlightColor(), "#");
        tooltip.addPara("     %s " + strings.get("IEBapitsm_TEXT1"), padS, Misc.getPositiveHighlightColor(), "#");
        tooltip.addPara("     %s " + strings.get("IEBapitsm_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#");
        tooltip.addPara("     %s " + strings.get("IEBapitsm_TEXT3"), padS, Misc.getPositiveHighlightColor(), "#");
        tooltip.addPara("     %s " + strings.get("IEBapitsm_TEXT4"), padS, Misc.getPositiveHighlightColor(), "#");

        tooltip.addPara("     %s " + strings.get("IEBapitsm_TEXTd1"), pad, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("     %s " + strings.get("IEBapitsm_TEXTd2"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("     %s " + strings.get("IEBapitsm_TEXTd3"), padS, Misc.getNegativeHighlightColor(), "#");
        if (!ship.getVariant().hasHullMod("blasstbaptism")){
            tooltip.addPara("     %s " + strings.get("IEBapitsm_TEXTd4"), padS, Misc.getNegativeHighlightColor(), "#");
        }
        if (ship.getVariant().hasHullMod("blasstbaptism")){
            tooltip.addPara("     %s " + strings.get("BlasstBapitsm_TEXTd4"), padS, WthC_ColorData.HIGH_BLUE, "#");
        }

        tooltip.addSectionHeading("Weapon Modification Concept", Alignment.TMID, 10.0F);

        tooltip.addPara("Missile hardpoints are rarely found on these ships, and other aspects have also been altered.", WthC_ColorData.IE_WHITE, pad);
        if (!ship.getVariant().hasHullMod("blasstbaptism")){
            tooltip.addPara("%s " + strings.get("IEBapitsm_TEXTn1"), pad, Misc.getHighlightColor(), "#","15%","15%");
            tooltip.addPara("%s " + strings.get("IEBapitsm_TEXTn2"), padS, Misc.getHighlightColor(), "#","50%","85%");
        }
        if (ship.getVariant().hasHullMod("blasstbaptism")){
            tooltip.addPara("%s " + strings.get("BlasstBapitsm_TEXTn1"), pad, WthC_ColorData.HIGH_BLUE, "#", "15%","30%");
            tooltip.addPara("%s " + strings.get("BlasstBapitsm_TEXTn2"), padS, WthC_ColorData.HIGH_BLUE, "#", "75%","25%");
            tooltip.addPara("%s " + strings.get("BlasstBapitsm_TEXTn4"), pad, WthC_ColorData.HIGH_BLUE, "#");
        }


        tooltip.addSectionHeading("Limitations and Conflicts", Alignment.TMID, 10.0F);

        tooltip.addPara("%s " + strings.get("IEBapitsm_TEXTn3"), pad, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("IEBapitsm_TEXTn4"), pad, Misc.getNegativeHighlightColor(), "#");


    }


    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        if (!stats.getVariant().hasHullMod("blasstbaptism")){
            stats.getBeamWeaponDamageMult().modifyMult(id, BEAM_MULT);
            stats.getBallisticRoFMult().modifyPercent(id, ROFBonus);
            stats.getEnergyRoFMult().modifyPercent(id, ROFBonus);
            stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FLUXBonus);
            stats.getEnergyWeaponFluxCostMod().modifyPercent(id, FLUXBonus);
        } else {
            stats.getBeamWeaponFluxCostMult().modifyPercent(id, BEAM_MULT2);
            stats.getBallisticRoFMult().modifyPercent(id, ROFBonus2);
            stats.getBallisticWeaponRangeBonus().modifyPercent(id, RangeBonus);
            stats.getEnergyRoFMult().modifyPercent(id, ROFBonus2);
            stats.getEnergyWeaponRangeBonus().modifyPercent(id, RangeBonus);
        }

    }
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        if (!ship.getVariant().hasHullMod("blasstbaptism")){
            ship.addListener(new WthC_IEBaptism.IEBaptismPartScatterAmpDamageDealtMod(ship));
        } else {
            ship.addListener(new WthC_IEBaptism.BABaptismPartScatterAmpDamageDealtMod(ship));
        }


        Iterator i$ = BLOCKED_HULLMODS.iterator();

        while(i$.hasNext()) {
            String tmp = (String)i$.next();
            if (ship.getVariant().getHullMods().contains(tmp)) {
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "iebaptism");
            }
        }
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

    public static class BABaptismPartScatterAmpDamageDealtMod implements DamageDealtModifier {
        protected ShipAPI ship;
        protected static final float ratio = 0.75f;
        protected static final String KEY = "Part_Scatter_Amp2";

        public BABaptismPartScatterAmpDamageDealtMod(ShipAPI ship) {
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
    }

}
