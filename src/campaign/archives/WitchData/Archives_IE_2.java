package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_IE_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Primordial Witch's Badge", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Primordial Witch's badge is a symbolic ornament crafted by the Artifact Witch, appearing as a circular badge with a golden base, engraved with a crescent moon within a sun... surrounded by the five elements.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Since the crescent part is made of Spirit Steel containing abundant stellar energy, it always flickers with intermittent radiation, and the entire badge's pattern naturally corresponds to the elemental aspects of the Primordial Magic system and their most primitive operating principles.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As an ornament for the hat, this badge appears somewhat miniature, and to reflect the sun element, there's an additional circle of metal lines resembling radiant patterns outside the circular base.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("These metal threads always rotate around the disc in a rather mystical way, adding quite a bit of 'magical' atmosphere to this badge.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Apart from the small amount of stellar energy surrounding it for slight illumination, this is just an extremely ordinary ornament, fundamentally different from other witch badges that carry various additional functions.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This is mainly because Primordial Witch Lika, being the most knowledgeable about energy usage in the entire Witch Council, doesn't need to add easily achievable functions to this symbolic ornament.", WthC_ColorData.MID_WHITE, padS);}

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
