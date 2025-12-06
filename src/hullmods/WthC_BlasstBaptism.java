package data.hullmods;

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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Some formats from Polaris
public class WthC_BlasstBaptism extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static float HullBonus = 50.0f;
    public static float ArmorBonus = -50.0f;
    public static float FLUX_MULT = 25f;
    public static final float DAMAGE_TAKEN_MULT = 0.85f;
    public static final float FLUX_RESISTANCE = 75f;

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padS = 2f;
        if (ship != null) {
        tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
        tooltip.addPara("This ship seems to have received... some indescribable baptism, its exposed armor and structure have become blurred, and its paint looks strangely faded as if suddenly", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("At the same time, the ship's internal structure has also undergone major changes, making it look more like something that shouldn't exist in this world", WthC_ColorData.MID_WHITE, padS);

        tooltip.addSectionHeading("Effects on Ship", Alignment.TMID, 10.0F);
        if (!ship.getVariant().hasHullMod("crimsonmodificationssoul")){
            tooltip.addPara("     %s " + strings.get("BlasstBapitsm_TEXT1"), pad, Misc.getPositiveHighlightColor(), "#","50%");
        }
        if (ship.getVariant().hasHullMod("crimsonmodificationssoul")){
            tooltip.addPara("     %s " + strings.get("BlasstBapitsm_TEXTEX1"), pad, WthC_ColorData.HIGH_BLUE, "#");
        }
        tooltip.addPara("     %s " + strings.get("BlasstBapitsm_TEXT11"), padS, Misc.getPositiveHighlightColor(), "#", "25%");
        tooltip.addPara("     %s " + strings.get("BlasstBapitsm_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#", "15%");
        tooltip.addPara("     %s " + strings.get("BlasstBapitsm_TEXT3"), padS, Misc.getPositiveHighlightColor(), "#", "75%");

        tooltip.addPara("     %s " + strings.get("BlasstBapitsm_TEXTd1"), pad, Misc.getNegativeHighlightColor(), "#","50%");
        tooltip.addPara("     %s " + strings.get("BlasstBapitsm_TEXTd2"), padS, Misc.getNegativeHighlightColor(), "#", "50%");
        tooltip.addPara("     %s " + strings.get("BlasstBapitsm_TEXTd33"), padS, Misc.getNegativeHighlightColor(), "#");
        tooltip.addPara("     %s " + strings.get("BlasstBapitsm_TEXTd3"), padS, Misc.getNegativeHighlightColor(), "#", "2");
        if (ship.getVariant().hasHullMod("crimsonmodificationssoul")){
            tooltip.addPara("A touching... reunion of old friends?", WthC_ColorData.SOUL_GREEN, pad);
        }
        }
    }


    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        if (!stats.getVariant().hasHullMod("crimsonmodificationssoul")){
            stats.getHullBonus().modifyPercent(id, HullBonus);
        }
        stats.getArmorBonus().modifyPercent(id, ArmorBonus);
        stats.getFluxDissipation().modifyPercent(id, FLUX_MULT);

        stats.getHullDamageTakenMult().modifyMult(id, DAMAGE_TAKEN_MULT);
        stats.getEmpDamageTakenMult().modifyMult(id, 1f - FLUX_RESISTANCE * 0.01f);
        stats.getCrewLossMult().modifyPercent(id, 50f);
    }
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
    }


}
