package data.campaign;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.CharacterDataAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// Stitched together from Janus and other items
public class Page_Ie extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");
    public static final String HULLMOD_RIC = "ringinterferecross";
    public static final String HULLMOD_RID = "ringinterferedust";
    public static final String HULLMOD_RII = "ringinterfereie";
    public static final String HULLMOD_RIR = "ringinterfererra";
    public static final String HULLMOD_RIS = "ringinterferessoul";
    public static final String HULLMOD_RIE = "ringinterfereelf";

    @Override
    public String getDesignType() {
        return null;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, CargoTransferHandlerAPI transferHandler, Object stackSource) {
        float pad = 10f;

        tooltip.addTitle(getName());

        String design = getDesignType();
        if (design != null) {
            Misc.addDesignTypePara(tooltip, design, 10f);
        }

        if (!spec.getDesc().isEmpty()) {
            tooltip.addPara(spec.getDesc(), Misc.getTextColor(), pad);
        }


        tooltip.addPara(strings.get("page"), WthC_ColorData.IE_WHITE, pad);
    }

    @Override
    public float getTooltipWidth() {
        return super.getTooltipWidth();
    }

    @Override
    public boolean isTooltipExpandable() {
        return false;
    }

    @Override
    public boolean hasRightClickAction() {
        return true;
    }

    @Override
    public boolean shouldRemoveOnRightClickAction() {
        return true;
    }

    @Override
    public void performRightClickAction() {
        final CharacterDataAPI player = Global.getSector().getCharacterData();
        if (player.getHullMods().contains(HULLMOD_RIC)) return;
        if (player.getHullMods().contains(HULLMOD_RID)) return;
        if (player.getHullMods().contains(HULLMOD_RII)) return;
        if (player.getHullMods().contains(HULLMOD_RIR)) return;
        if (player.getHullMods().contains(HULLMOD_RIS)) return;
        if (player.getHullMods().contains(HULLMOD_RIE)) return;


        player.addHullMod(HULLMOD_RIC);
        player.addHullMod(HULLMOD_RID);
        player.addHullMod(HULLMOD_RII);
        player.addHullMod(HULLMOD_RIR);
        player.addHullMod(HULLMOD_RIS);
        player.addHullMod(HULLMOD_RIE);


        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You've obtained some general hull modification schemes, you can check them in the hullmods interface.");
    }
}
