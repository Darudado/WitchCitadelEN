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
public class Archives_AL3_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("#Ship's Log-B.A.1654/8/13#", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Unexpected... that black-haired lady just gifted me this magnificent warship...", WthC_ColorData.IE_WHITE_D, pad);
        tooltip.addPara("It seems her heart is as beautiful as her appearance...", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("With this... I'm sure I can recover my lost memories on that planet...", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("I can feel something calling me to that destined place...", WthC_ColorData.IE_WHITE_D, padS);

        tooltip.addPara("#Ship's Log-B.A.1654/11/26#", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("I've been on this planet for some time now... Indeed, its interior is as beautiful as it appears from the void...", WthC_ColorData.IE_WHITE_D, pad);
        tooltip.addPara("Why is it rumored to be a forbidden place that rejects all living beings?", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("After all... there are so many welcoming compatriots here willing to help me find my memories... aren't there?", WthC_ColorData.IE_WHITE_D, padS);

        tooltip.addPara("#Ship's Log-B.A.1655/3/5#", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Several more months have passed... my friends have brought more warships...", WthC_ColorData.IE_WHITE_D, pad);
        tooltip.addPara("With these space outposts, our search work will become much smoother", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("They say they've known me for a long time... Indeed, I must have been here before.", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("It seems the day of recovering all memories is just around the corner...", WthC_ColorData.IE_WHITE_D, padS);

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

        player_fleet.getCargo().addSpecial(new SpecialItemData("Archives_AL3_2", "special_items"), 1.0F);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You turned the page...");
    }
}
