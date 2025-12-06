package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_SSoul_3 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("Extraction Process", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("As the Lord of Ten Thousand Rifts, Su is the most concrete embodiment of the Reincarnation System. She not only observes and manages everything within this system but can also directly intervene without much difficulty. For example... she can directly extract the stellar energy infused into any intelligent being during the Newborn Ritual, pulling it out of their physical body.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Due to the nature of stellar energy, for almost all intelligent beings, this energy is absolutely unified with their consciousness. When it is extracted, even though the original body does not cease physiological functions due to the loss of this energy, it plunges the being into a sensation of being torn apart, as if the world itself is burning. Depending on the strength of the target's will, this is often accompanied by complete madness or unrelenting confusion and dizziness, as if their soul has left their body. Even if the extracted energy is reinfused, it takes an extremely long time to regain consciousness from this unbearable sense of severance.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Of course, if this isn't terrifying enough, she possesses an even more extreme method—directly causing this energy to reverse flow within the target's body, triggering an internal explosion sufficient to utterly annihilate their soul in every sense...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("However, even with such power, it doesn't mean she always uses it. If she truly enjoyed arbitrarily deciding life and death based on personal whims, then the act of establishing and maintaining so many rifts over hundreds or thousands of years would have long lost all meaning.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As with any myth about her, there is always a classic proverb repeated in various forms—\"The sword above your head remains, but it has never fallen.\"", WthC_ColorData.HIGH_BLUE, pad);

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
