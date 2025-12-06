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
public class Page_Cm extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");
    public static final String HULLMOD_IE = "crimsonmodificationie";
    public static final String HULLMOD_RRA = "crimsonmodificationrra";
    public static final String HULLMOD_DUST = "crimsonmodificationdust";
    public static final String HULLMOD_RR = "ringreverse";


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


        tooltip.addPara(strings.get("page"), WthC_ColorData.LIGHT_CRIMSON, pad);
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
        if (player.getHullMods().contains(HULLMOD_IE)) return;
        if (player.getHullMods().contains(HULLMOD_RRA)) return;
        if (player.getHullMods().contains(HULLMOD_DUST)) return;
        if (player.getHullMods().contains(HULLMOD_RR)) return;

        player.addHullMod(HULLMOD_IE);
        player.addHullMod(HULLMOD_RRA);
        player.addHullMod(HULLMOD_DUST);
        player.addHullMod(HULLMOD_RR);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You've obtained some general hull modification schemes, you can check them in the hullmods interface.");
    }
}
