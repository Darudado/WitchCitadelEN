package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_SSoul_22 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Rifts", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A collective term for all afterlife worlds, nominally owned by the \"Lord of Ten Thousand Rifts,\" the Soul Witch Su.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Although the names of these afterlife worlds often sound mysterious and are frequently associated with numerous objective idealistic entities, in reality, they are merely planets connected by stellar dust junction points.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Driven by the Soul Witch's obsession, all stellar energy consciousnesses that have departed their original bodies will wander in these afterlife worlds for several years, with the specific duration closely tied to the judgment they previously received. Since most so-called \"necromorphs\" exist as energy consciousnesses, the planets chosen for rifts are often not habitable, as long as they possess a certain degree of adaptability.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("However, it is often thought-provoking: if no memories are retained after reincarnation and one's birth does not fully determine their fate, is this system symbolizing reincarnation truly fair and meaningful?", WthC_ColorData.MID_WHITE, padS);
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
