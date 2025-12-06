package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_CMIE extends BaseSpecialItemPlugin{

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



        tooltip.addPara("Heh... if you truly want to understand these mysterious visitors from the Crimson Cities, why not hear the Crimson Lord's explanation directly?", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Because you know what follows will be long-winded...", WthC_ColorData.MID_WHITE, pad);

        tooltip.addPara("Hmm... my witch lady, the most witch-like among all my witch ladies.", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("After all, isn't the combination of wide-brimmed pointed hat and robes so deeply loved?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Don't deny it~ I know you like to secretly browse those treasured artistic images in quiet corners, and some of them feature characters with this design?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Ha... Surely you know it's extremely foolish to ask an omniscient being how they know everything.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("So rather than that... tell me, do you like adult artistic images of witch-type characters?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Perhaps even one as noble as myself has some exclusive, never-before-seen collections...?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("Maybe...?", WthC_ColorData.MID_WHITE, padS);

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
