package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_RRA_0 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Artifact Witch's Decorative Style", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Pale Moon designed several blue and white robes matching her eye color, focusing on 'elegance, simplicity, and dignity'. Though exquisite, these robes have minimal patterns, staying understated while fitting the theme.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("But under a certain unnamed Primordial Magic user's 'special request', these clothes became more form-fitting, enough to show Pale Moon's body curves, so even simple, modest cool-colored robes might generate a special allure...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As an irreplaceable 'close friend', Lika has given Pale Moon countless gem accessories over the long years. These ornaments are always the highest quality from the Primordial Witch, showing the meaningful thoughtfulness of that usually calm witch lady.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Even though wanting to stay low-key, Pale Moon always gently selects these accessories carefully, using just the right combinations to subtly highlight her unique beauty. Of course... limited to cool-toned gems similar to blue and white, she really doesn't want to wear any warm-colored accessories that would make her more eye-catching.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As the Artifact Witch, Pale Moon naturally has her own witch hat, though except for the Primordial Witch, other Council Witches' hats bear no resemblance to the stereotypical pointed witch hat.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Pale Moon's hat is a blue and white wide-brimmed hat with silver trim. On the left side, elegant ribbons and lace form a delicate flower bed, with the Artifact Witch badge perfectly integrated into this garden atop her head; on the right back, two longer cyan ribbons slide down along the brim, blending into her clothes, creating ripples in the air like willows only when the breeze passes.", WthC_ColorData.MID_WHITE, padS);}

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
