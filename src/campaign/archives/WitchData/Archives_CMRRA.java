package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_CMRRA extends BaseSpecialItemPlugin{

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

        tooltip.addPara("Pale Moon, oh Pale Moon... such a kind and gentle soul always makes one worry... well, though in many ways I don't count as a person.", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Nevertheless, as I wandered through the entire three-dimensional world reviewing all the Tower Lords' graduation theses, I've never seen such an endearing existence as her. Could this also be considered an anomaly?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("If beauty could kill... well, it actually can kill, and this harmless Miss Witch is that terrifying killing machine!", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Hmm... although this sounds like a joking joke, by now you surely understand that countless sins hide behind all witches, even her.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("You humans... always fighting over things you want~ No matter how much your experience and technology advance, this nature never truly disappears.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("So... what massive catastrophe would be triggered by an eternal treasure that exists in this world, unaware of its own nature?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("Must it be unaware?", WthC_ColorData.MID_WHITE, padS);

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
