package data.campaign;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;


// Stitched together from Janus and other items
public class Agreement_Story extends BaseSpecialItemPlugin{

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


        tooltip.addPara("A description engraved on the back of the protocol card:", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("This protocol card has been directly authorized by the Crimson Lord...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Simply activate this protocol card by following the steps below to exchange for corresponding basic supplies...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("(Below are a total of 6 detailed steps)", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("The specific denomination corresponding to this protocol card: %s ", pad, WthC_ColorData.HIGH_BLUE, new String[]{"1 favor from fate..."});
        tooltip.addPara("Warning: %s ", pad, Misc.getNegativeHighlightColor(), new String[]{"You will often need to pay a higher price for this"});

        tooltip.addPara("(The final right of this product belongs to Crimson Citadel...)", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("(Improperly executed dust-based transfer procedures may result in supplies being sent far from the designated location, property damage, collision explosions, direct heavy object crushing, and other catastrophic phenomena...)", WthC_ColorData.CROSS_GARY_D, padS);

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
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You strictly followed the steps to activate a Dust-based Transfer Protocol Card... A sense of purpose fills your mind... (Gained 1 Story Point)");

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);

    }
}
