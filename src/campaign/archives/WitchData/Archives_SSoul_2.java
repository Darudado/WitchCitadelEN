package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_SSoul_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Soul Witch Emblem", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("\"The Soul Witch Emblem\" is the most unique among all the council witch emblems, as it isn't even a tangible physical entity. Due to its resemblance to a slightly larger \"Flame Spirit\" and its pure white appearance, it is referred to as the \"Primordial Soul\".", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("At least when Su still had a trace of life within her, this \"ghost flame\" was a rather unique existence. It was precisely because of this spiritual-like manifestation that the title of Soul Witch came into being. However, nowadays, the Flame Spirits are likely the most common sight surrounding the Lord of Ten Thousand Rifts. In such times, the Soul Witch Emblem doesn't seem as unique, but amidst a sea of colorful little ghost flames, this pure white and slightly larger \"Primordial Soul\" still stands out quite distinctly.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("In fact, back when Su was still the Soul Witch, much like how other Flame Spirits would carry some color, this \"Primordial Soul\" would shimmer with a dazzling pink-blue light. But at some point, this beautiful flame color quietly faded, transforming into the pristine white appearance it has today.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("So, if it's truly as the legends say, and the \"Soul Witch Emblem\" has the ability to imprison souls, then the meaning behind this \"Primordial Soul\" becomes quite intriguing.", WthC_ColorData.MID_WHITE, padS);

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
