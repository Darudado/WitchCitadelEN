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
public class Archives_AL3_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("#Ship's Log-B.A.1655/3/12#", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Something unexpected happened...", WthC_ColorData.IE_WHITE_D, pad);
        tooltip.addPara("An unknown fleet opened fire on us... probably trying to steal this warship.", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("My friends are all dead... silently buried in the void...", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("But somehow... amidst the explosions... I remembered something...", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("I think... I know where I need to go... fortunately this noble gift has emergency jump capability...", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("Fortunately this noble gift has emergency jump capability...", WthC_ColorData.IE_WHITE_D, padS);

        tooltip.addPara("#Ship's Log-B.A.1655/3/15#", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Finally... I've arrived at the promised land from my memories...", WthC_ColorData.IE_WHITE_D, pad);
        tooltip.addPara("But why is it so broken... and why are tears flowing from my eyes?", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("Even when... tens of thousands of friends perished days ago... I didn't feel a hint of sadness... did I?", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("But... surely this pain won't last much longer...", WthC_ColorData.IE_WHITE_D, padS);
        tooltip.addPara("After all... the King who has been calling me, now... is right before my eyes.", WthC_ColorData.IE_WHITE_D, padS);

        tooltip.addPara("#Ship's Log-B.A.1655/3/16#", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("The screen shows only one image...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Appears to be a photo of a tombstone...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Flische the Winged Commander", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("Here lies the King's eternally loyal servant", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("B.A.655/3/16", WthC_ColorData.HIGH_BLUE, padS);

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

        player_fleet.getCargo().addSpecial(new SpecialItemData("Archives_AL3_3", "special_items"), 1.0F);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You turned the page...");
    }
}
