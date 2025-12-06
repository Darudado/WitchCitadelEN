package data.scripts.cargo;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;

import java.awt.*;


public class URA_cargo extends BaseSpecialItemPlugin {



    public special_cargodata getData() {
        return (special_cargodata) stack.getSpecialDataIfSpecial();
    }

    public CargoAPI getCargo() {
        CargoAPI globalCargo = WTHC_cargodata.getInstance().getCargo();
        if (globalCargo.isEmpty()) {
            return getData().getCargo();
        }
        return globalCargo;
    }





    @Override
    public boolean hasRightClickAction() {
        return true;
    }

    @Override
    public boolean shouldRemoveOnRightClickAction() {
        return false;
    }

    @Override
    public void performRightClickAction() {
        //fix for campaign post-combat crash
        if (Global.getSector().getCampaignUI().getCurrentInteractionDialog() != null) return;
        //script
        try {
            Global.getSector().addTransientScript(new opencargo(stack.getCargo()));
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Now...you need to focus and observe your surroundings...(Please return to the campaign map interface)");
    }


    //小作文时间
    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, CargoTransferHandlerAPI transferHandler, Object stackSource, boolean useGray) {
        float pad = 2f;
        tooltip.addTitle(getName());

        String design = getDesignType();
        if (design != null) {
            Misc.addDesignTypePara(tooltip, design, 10f);
        }

        if (!spec.getDesc().isEmpty()) {
            tooltip.addPara(spec.getDesc(), Misc.getTextColor(), 10f);
        }

        tooltip.addPara("Even though you know little about the mysterious Ringwraith Network, you can vaguely sense that it hides an incredibly vast and profound space.", WthC_ColorData.HIGH_BLUE, 10f);
        tooltip.addPara("The process of linking feels as intense as a temporary out-of-body experience, so it's best to perform this in a relatively safe location.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("(Right-click to use, then you must return to the campaign map interface for it to take effect)", WthC_ColorData.B_WHITE_L, pad);
    }

}

