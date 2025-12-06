package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_RRA_3 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Art from the Blue Rose", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Though 'good vs bad art' has always been a pseudo-proposition that can only be judged subjectively, perhaps like the Artifact Witch's constitution, her creations carry a unique 'enchanting magic'.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Even if an identical replica is made and placed beside the original for others to choose, anyone unaware of the truth would select the one made by Pale Moon. This seems inexplicable by normal logic, but regardless, there's no interference beyond natural phenomena involved...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("However, while the Artifact Witch is accomplished in painting, architecture, design, and crafting, she's extremely poor with music, struggling even with the simplest etudes... though she can still sing in tune with accompaniment.", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Perhaps this somewhat dull artist doesn't possess the vast array of techniques people imagine, but simply has abundant inspiration to express?", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Or perhaps she just passively became this so-called world's art designer under some other beings' interference...", WthC_ColorData.MID_WHITE, padS);}

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
