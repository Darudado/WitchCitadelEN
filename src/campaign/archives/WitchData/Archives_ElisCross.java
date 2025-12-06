package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_ElisCross extends BaseSpecialItemPlugin{

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



        tooltip.addPara("Countermeasure Plan for Individual Rosalia Cross:", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Target's life form is fragile, but can directly terminate all life activities through physical means...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This item has 1052 detailed explanations...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Target lacks passive defense measures, recommend using mass destruction weapons for saturation strikes...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This item has 725 detailed explanations...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Target possesses extremely strong mental interference abilities, recommend using non-intelligent entities to engage...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This item has 5421 detailed explanations...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Target possesses extremely strong mental resilience, not recommended to use any non-physical interference methods...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This item has 3251 detailed explanations...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Target possesses an extremely broad social network, rash execution of countermeasures will trigger civilization-level catastrophic consequences...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This item has 251 detailed explanations...", WthC_ColorData.MID_WHITE, padS);
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

        Global.getSector().getPlayerStats().addStoryPoints(1);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Congratulations on spending 10 million for a piece of data... Keep up the good work, little genius. Here's a Story Point for your trouble~");
    }
}
