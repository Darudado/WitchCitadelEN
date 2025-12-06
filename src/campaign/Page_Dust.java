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
public class Page_Dust extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");
    public static final String HULLMOD_1 = "erosion1";
    public static final String HULLMOD_2 = "erosion2";
    public static final String HULLMOD_3 = "erosion3";
    public static final String HULLMOD_4 = "erosion4";
    public static final String HULLMOD_5 = "erosion5";
    public static final String HULLMOD_6 = "fogtransformed";


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

        tooltip.addPara(strings.get("page"), WthC_ColorData.DUST_PURPLE, pad);
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
        if (player.getHullMods().contains(HULLMOD_1)) return;
        if (player.getHullMods().contains(HULLMOD_2)) return;
        if (player.getHullMods().contains(HULLMOD_3)) return;
        if (player.getHullMods().contains(HULLMOD_4)) return;
        if (player.getHullMods().contains(HULLMOD_5)) return;
        if (player.getHullMods().contains(HULLMOD_6)) return;

        player.addHullMod(HULLMOD_1);
        player.addHullMod(HULLMOD_2);
        player.addHullMod(HULLMOD_3);
        player.addHullMod(HULLMOD_4);
        player.addHullMod(HULLMOD_5);
        player.addHullMod(HULLMOD_6);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You've obtained some general hull modification schemes, you can check them in the hullmods interface.");
    }
}
