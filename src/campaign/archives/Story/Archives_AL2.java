package data.campaign.archives.Story;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_AL2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("...what makes you so persistent?", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("But if you can face even this trial...", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("You can no longer escape...", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("You swear you've never seen such an eerie enemy in your life... not even those damned Omega.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Before you could recover from your shock, this abrupt cryptic message makes you want to cry.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("What kind of horrifying prank is this...? And who is the mysterious figure behind these messages...?", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Heh... long time no see, you timid Perseus dweller?", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("...you knew this mischievous one would come to tease... as if their life had nothing but seeking amusement.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("This time you absolutely won't respond to them... you silently vow to yourself.", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Haha... very well, but I think you definitely won't miss that grand ceremony...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("After all... what in this vast sector can truly be reborn through nirvana...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara(".........", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Anyway... I'll send out the formal invitation here after some time...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Of course... you'll need to attend in the Perseus way...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Guess we'll just have to come back and see later...", WthC_ColorData.CROSS_GARY_D, padS);
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
                "You gained 1 Story Point... Hopefully the Crimson Lord won't set up another big trap for you after some time...");
    }
}
