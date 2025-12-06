package data.campaign;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import org.lazywizard.lazylib.MathUtils;


// Stitched together from Janus and other items
public class LanceCollect extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");

    protected CampaignFleetAPI player_fleet;

    @Override
    public String getDesignType() {
        return null;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, CargoTransferHandlerAPI transferHandler, Object stackSource) {
        float pad = 10f;
        float padS = 2f;

        tooltip.addTitle(getName());

        String design = getDesignType();

        if (design != null) {
            Misc.addDesignTypePara(tooltip, design, 10f);
        }

        if (!spec.getDesc().isEmpty()) {
            tooltip.addPara(spec.getDesc(), Misc.getTextColor(), pad);
        }

        tooltip.addPara("She loves lances... no matter what kind of lance it is~", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Of course~ I believe no Persean captain would dislike them either~", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("(Opening this seems to yield quite a few beam weapons...)", WthC_ColorData.CROSS_GARY_D, pad);


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
        this.player_fleet = Global.getSector().getPlayerFleet();

            Global.getSector().getPlayerFleet().getCargo().addWeapons("riftcascade",1);
            Global.getSector().getPlayerFleet().getCargo().addWeapons("tachyonlance",2);
            Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_Darkest",2);
            Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_NewMoon",2);
            Global.getSector().getPlayerFleet().getCargo().addWeapons("hil",2);
            Global.getSector().getPlayerFleet().getCargo().addWeapons("riftlance",2);
            Global.getSector().getPlayerFleet().getCargo().addWeapons("irautolance",4);
            Global.getSector().getPlayerFleet().getCargo().addWeapons("phasebeam",4);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "Hmm... but what exactly defines a lance anyway?");



        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);

    }
}
