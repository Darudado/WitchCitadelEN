package data.campaign.archives.Story;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_AL3_3 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("The shock leaves you silent for a long while.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("You can't help but recall the previous battle... what kind of being were you fighting against?", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Just thinking about it sends chills down your spine...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("And why... did she want you to understand all this?", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Do not grieve...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("We shall remember the sacrifice of every subject...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Just as We always have...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("And what surprises, even terrifies you is...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("A video transmission suddenly arrives, and in the frame appears the... golden-haired angel you glimpsed earlier?", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("A thousand years have passed... yet Our loyalty still dreams of the King's return...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Even with soul scattered... this ancient wish drives the wandering spirit's rebirth...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Alas... this place no longer belongs to Us...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("And We... have never lingered here in remembrance...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Whatever the case... it seems you'll need some special means to sleep for a while...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Even as a prank... this was quite shocking enough...", WthC_ColorData.CROSS_GARY_D, padS);
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

    public void performRightClickAction() {
        this.player_fleet = Global.getSector().getPlayerFleet();

        Global.getSector().getPlayerStats().addStoryPoints(1);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You gained 10 Story Points... This scene will surely haunt you forever");
    }
}
