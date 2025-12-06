package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_CMSSoul extends BaseSpecialItemPlugin{

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



        tooltip.addPara("Heh... If you want to truly understand these mysterious visitors from the Crimson City, why not listen directly to the Crimson Lord's analysis?", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Because you know there will be endless rambling...", WthC_ColorData.MID_WHITE, pad);

        tooltip.addPara("Tell me, kimono, ghosts, master-servant relationships, underworld... what comes to mind with these keywords?", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Haha... I don't expect you barbaric Perseans to understand the art created by your ancestors when they first entered the information age~", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("But I must say... even in this chaotic space age, these combined scenes still stir a hint of romance in your human hearts... don't they?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("So what I really want to ask is, are you satisfied with those virtual images of the witch ladies?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Or do you prefer something even younger...?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Ugh... if that's really the case, please stay away from me.", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("Already too tired to argue with her...", WthC_ColorData.MID_WHITE, padS);

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
