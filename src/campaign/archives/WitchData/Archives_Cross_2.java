package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Cross_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Cross Witch's Badge", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Cross Witch's badge appears as an intricate bordered cross mounted on a blue square base. Like other witch badges, it's a masterpiece of the Artifact Witch. Due to the requirement to be designed together with the hat, its base isn't the common circular or diamond shape of other badges, but rather a blue translucent rectangular prism that fits perfectly into the high hat. The cross portion is made of two high-strength metals rarely used in decorative items - the silver-white part is a sacred silver-platinum-iridium alloy, while the black part is an equally high-strength tungsten-cobalt alloy.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Although the Cross Witch's badge appears relatively simple in design elements, for Rosalia, this prominent cross itself is her most precise symbol. Of course, in that era, only Lika Primordial could provide such high-precision alloys to the designer.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Initially, this witch badge had no function beyond decoration, until later when Rosalia infused large amounts of her energy into the metals forming the cross and constructed a controllable mental interference formation within it, transforming this badge into a magical tool with brainwashing and self-illuminating capabilities.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Interestingly, upon learning that her artwork had become a brainwashing tool, the usually gentle and kind Pale Moon was quite upset about it, until she was gradually persuaded by Rosalia through various means, after which the two reconciled.", WthC_ColorData.MID_WHITE, padS);
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
