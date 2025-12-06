package data.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.scripts.util.MagicIncompatibleHullmods;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Some formats from Polaris
public class WthC_Emblem extends BaseHullMod {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("HullMod", "WthC_");
    public static final float WEAPON_Bonus = 50f;
    public static final float SUP_Bonus = 100f;

    private static final Set<String> BLOCKED_HULLMODS = new HashSet();

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        float padS = 2f;
        if (ship != null) {
        tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
        tooltip.addPara("Without exception... these witch flagships usually undergo extensive permanent modifications to better suit their unique tactical styles. Meanwhile, the addition of more predictive auxiliary systems provides more possibilities for upcoming operations...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("But why, despite having technology and abilities beyond human imagination, are they still willing to remain detached from the world?", WthC_ColorData.IE_WHITE, pad);
        }
        tooltip.addSectionHeading("Effects on Ship", Alignment.TMID, 10.0F);

        tooltip.addPara("%s " + strings.get("Emblem_TEXT1"), pad, Misc.getPositiveHighlightColor(), "#");
        tooltip.addPara("%s " + strings.get("Emblem_TEXT2"), padS, Misc.getPositiveHighlightColor(), "#","+50%");
        tooltip.addPara("%s " + strings.get("Emblem_TEXT3"), padS, Misc.getPositiveHighlightColor(), "#","+50%");
        tooltip.addPara("%s " + strings.get("Emblem_TEXT4"), padS, Misc.getPositiveHighlightColor(), "#","+5%");
        tooltip.addPara("%s " + strings.get("Emblem_TEXT5"), padS, Misc.getPositiveHighlightColor(), "#","+5%");
        tooltip.addPara("%s " + strings.get("Emblem_TEXTD1"), padS, Misc.getNegativeHighlightColor(), "#","+100%");

        tooltip.addSectionHeading("Restrictions and Special Effects", Alignment.TMID, 10.0F);

        tooltip.addPara("%s " + strings.get("Emblem_TEXTEX"), pad, Misc.getNegativeHighlightColor(), "#");


    }


    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getDamageToFrigates().modifyPercent(id, 5f);
        stats.getDamageToDestroyers().modifyPercent(id, 5f);
        stats.getWeaponTurnRateBonus().modifyPercent(id, WEAPON_Bonus);
        stats.getAutofireAimAccuracy().modifyPercent(id, WEAPON_Bonus);
        stats.getSuppliesPerMonth().modifyPercent(id, SUP_Bonus);
        stats.getMaxCombatReadiness().modifyFlat(id, 0.05f);

    }

    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        int count = 0;
        for (String d : ship.getVariant().getHullMods()) {
            if (Global.getSettings().getHullModSpec(d).hasTag(Tags.HULLMOD_DMOD)) {
                if (ship.getHullSpec().getBuiltInMods().contains(d)) continue;
                count++;
            }
        }

        ship.getMutableStats().getMaxCombatReadiness().modifyFlat(id, -0.15f);

        Iterator i$ = BLOCKED_HULLMODS.iterator();

        while(i$.hasNext()) {
            String tmp = (String)i$.next();
            if (ship.getVariant().getHullMods().contains(tmp)) {
                MagicIncompatibleHullmods.removeHullmodWithWarning(ship.getVariant(), tmp, "witch_emblem");
            }
        }
    }
        static {
        BLOCKED_HULLMODS.add("turretgyros");
    }

}
