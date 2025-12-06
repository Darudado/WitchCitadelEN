package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_ElisRRA extends BaseSpecialItemPlugin{

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



        tooltip.addPara("Countermeasure Plan for Individual Pale Moon:", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Target's life form extremely fragile, can directly terminate all life activities through physical means...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This item has 5241 detailed explanations...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Target possesses numerous passive defense measures, recommend using unconventional destructive weapons...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This item has 1572 detailed explanations...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Target's mental resilience extremely fragile, interference through mental means possible...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This item has 6421 detailed explanations...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Target has basic physiological needs, interference through biological means possible...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This item has 2074 detailed explanations...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Target has deep interpersonal relationships, rash execution of countermeasures will trigger special catastrophic consequences...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This item has 52 detailed explanations...", WthC_ColorData.MID_WHITE, padS);

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
