package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Yihui extends BaseSpecialItemPlugin{

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


        tooltip.addPara("The Council of Witches", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Council of Witches is a small club organization within the Crimson Citadel, with core members consisting only of the six founding witches and several other investors and associates. The organization itself has very few servants or other public officials.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Primal Witch Rica Primal is the nominal leader of this organization, but essentially there is no organizational discipline within the council, nor are there any actual agendas. Therefore, in the end, she cannot command the other core council members, and this club-style council itself doesn't require any organized group activities.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As the Crimson Citadel left Earth for space, all Council Witches departed their homeland with this [REDACTED], presumably having no attachment to humanity's original cradle as they had already transcended their original species.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Of course... from another perspective, due to the Ring [REDACTED] Network's information transmission speed beyond human technological imagination and the gradual perfection of [REDACTED] technology, 'physical location' has long ceased to be a noteworthy issue for the Council Witches.", WthC_ColorData.MID_WHITE, padS);
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
