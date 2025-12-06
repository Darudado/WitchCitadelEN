//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_Util;

// Some formatting from Polaris
public class WthC_HeavyEquipmentIntegration extends BaseHullMod {
    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static final float COST_REDUCTION = -8.0F;

    public WthC_HeavyEquipmentIntegration() {
    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        tooltip.addSectionHeading("Effects on the Ship", Alignment.TMID, 10.0F);
        tooltip.addPara(" %s " + strings.get("HeavyEquipmentIntegration_TEXT1"), pad, Misc.getPositiveHighlightColor(), "#", "8");
    }
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getDynamic().getMod("large_ballistic_mod").modifyFlat(id, COST_REDUCTION);
        stats.getDynamic().getMod("large_energy_mod").modifyFlat(id, COST_REDUCTION);
    }

    public boolean affectsOPCosts() {
        return true;
    }
}
