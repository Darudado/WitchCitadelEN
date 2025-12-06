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
public class Page_Elf extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");

    public static final String HULLMOD_SMI = "shadowremainie";
    public static final String HULLMOD_SMR = "shadowremainrra";
    public static final String HULLMOD_SMD = "shadowremaindust";
    public static final String HULLMOD_SME = "shadowremainelf";
    public static final String HULLMOD_SMS = "shadowremainssoul";
    public static final String HULLMOD_SMC = "shadowremaincross";


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


        tooltip.addPara(strings.get("page"), WthC_ColorData.EIF_ORANGE, pad);
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

        if (player.getHullMods().contains(HULLMOD_SMI)) return;
        if (player.getHullMods().contains(HULLMOD_SMR)) return;
        if (player.getHullMods().contains(HULLMOD_SME)) return;
        if (player.getHullMods().contains(HULLMOD_SMD)) return;
        if (player.getHullMods().contains(HULLMOD_SMS)) return;
        if (player.getHullMods().contains(HULLMOD_SMC)) return;

        player.addHullMod(HULLMOD_SMI);
        player.addHullMod(HULLMOD_SMR);
        player.addHullMod(HULLMOD_SME);
        player.addHullMod(HULLMOD_SMD);
        player.addHullMod(HULLMOD_SMS);
        player.addHullMod(HULLMOD_SMC);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You've obtained some general hull modification schemes, you can check them in the hullmods interface.");
    }
}
