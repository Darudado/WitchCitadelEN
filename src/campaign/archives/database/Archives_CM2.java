package data.campaign.archives.database;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_CM2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Hello... traveler from the stars.", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("I know... you always thirst for the truth behind all mysteries...", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("So... you will surely come here seeking more secrets...", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Alas, perhaps knowledge from another world will not appear so simply before your eyes...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Even though you are so special... possessing freedom and power that other Perseus inhabitants do not have...", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Like... there exists a supreme being behind that virtual veil as well...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Just as... I exist behind them...", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Since... mortals have more than once called me a demon...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Then... what degree of calamity will you become...?", WthC_ColorData.LIGHT_CRIMSON, padS);

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
                "You gained 1 Story Point... A small reward for glimpsing beyond the veil~");
    }
}
