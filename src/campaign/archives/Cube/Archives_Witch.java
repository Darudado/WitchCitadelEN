package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Witch extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Witch", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("'Witch' is a rather broad term for non-human species, applying to almost any female who has achieved immortality through means considered supernatural by human society.", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Therefore, for easier distinction, witches have refined this concept. Those who resemble humans in appearance or have transformed from humans, non-innate magical beings, are called humanoid acquired witches. The vast majority of Council Witches belong to this category.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("All witches indicate their school of magic, for example, the Primal Witch and Dust Witch are the most iconic figures of the [REDACTED] Magic School and [REDACTED] Magic School respectively. Among them are also some whose schools' authenticity is questionable, such as the Cross Witch's claimed [REDACTED] Magic School.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("However, even so, many witches still completely transcend this definition, but since this species definition, like something out of fantasy, is quite flexible, presumably no one else would question it.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("For example, the Soul Witch is strictly speaking a [REDACTED], while defining the Spirit Witch and Dust Witch as biological entities is even more challenging, but this doesn't affect their self-identification with this status...", WthC_ColorData.MID_WHITE, padS);
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
                "This mysterious knowledge might prove useful in future adventures... You gained 1 Story Point.");
    }
}
