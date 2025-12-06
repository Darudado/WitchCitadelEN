package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Cross_3 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Divine Proclamation", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The so-called Divine Proclamation is not a specific spell or ability, but rather a collective term for all mental control techniques in the Cross Witch's possession, including but not limited to psychological suggestions, brainwave interference, cognitive modification, hypnosis, consciousness manipulation and other means. Both its naming and actual effects are full of her consistently mystifying style.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("For the Cross Witch, who naturally emits slight psychological suggestions even in daily conversation, there's little need to delve into exactly how many mental control techniques she knows. For a normal person, efforts in just one aspect are not enough to prevent her mental control influence - the Cross Witch, who has reached the pinnacle in this field, has long learned to penetrate one's mind through any of the five senses.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("However, it's worth noting that even the Cross Witch, the world's most authoritative 'brainwashing master', cannot control targets out of thin air. All these influences from Divine Proclamation are still based on her expertise in psychology and biology. If the target cannot understand her implanted information, such as a pure madman or fool, they naturally won't be controlled by her psychological methods; and if the target's thoughts aren't entirely controlled by the brain, the effectiveness of her biological control methods will also greatly decrease.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Therefore, when facing non-human monsters, even the Cross Witch cannot completely interfere with their minds, yet this is often Rosalia's only effective means of dealing with these powerful enemies. It is precisely this self-contradictory fact that makes her one of the weakest Council Witches in a broad sense.", WthC_ColorData.MID_WHITE, padS);
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
