package data.campaign.archives.Story;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_AL extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Although you're certain you delivered a killing blow to the target in the recent battle...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("But clearly... no trace of it can be found afterward.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Even in the ship's analyzer records, that vessel seems to have vanished into thin air.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("And just as you're pondering this perplexing situation, a sudden hyperspace transmission catches your attention.", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Your mission is complete, and your account has been credited accordingly...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The subsequent cleanup will no longer be your responsibility...", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("Though somewhat bewildered by this, after checking your credit account, you have to admit the numerical increase is real.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Well, it seems this job should end here.", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("And just as you're contemplating this, another brief message arrives from a different source...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Come back to the old place... you dim-witted Perseus inhabitant~", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Well, well... looks like this damned job is getting more mysterious by the minute...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("But one thing you're quite certain of is that the crimson font they used inexplicably irritates you...", WthC_ColorData.CROSS_GARY_D, padS);

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
                "You gained 1 Story Point... Come back to that planet after some time.");
    }
}
