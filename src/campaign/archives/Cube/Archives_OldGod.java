package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_OldGod extends BaseSpecialItemPlugin{

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


        tooltip.addPara("The Great Beasts", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A collective term for creatures of extremely massive size that require extraordinarily high biological sustenance to maintain normal existence.", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("According to reliable records, most of them came from a distant blue planet, now commonly known as 'Earth'. Long before the emergence of humans as the dominant species, the Great Beasts once held supremacy over every land and ocean. To complete the task of creating new life on this almost perfectly naturally habitable planet, an outsider used orbital bombardment to rain down massive amounts of stellar energy from space, exterminating over 90% of ancient life forms on the surface.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("However, due to [ACCESS DENIED], these ancient Great Beasts were once brought to the brink of extinction.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Almost simultaneously, the species known as 'humans' began to proliferate extensively on that planet, and even the surviving Great Beasts were continuously hunted by [ACCESS DENIED].", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Perhaps due to their species facing extinction, these beings based on primordial stellar energy evolved a consciousness transfer adaptation, allowing several individuals to successfully escape that cruel hunting by [ACCESS DENIED].", WthC_ColorData.MID_WHITE, pad);

        tooltip.addPara("Now, thanks to the execution of the [ACCESS DENIED] plan, most of the surviving Great Beasts have come to space along with the Crimson City, finding new homes on several artificial planets around [ACCESS DENIED].", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("However, these surviving Great Beasts seem to be somewhat lacking in intelligence; in comparison, even the pets around the [ACCESS DENIED] Witch appear more spirited...", WthC_ColorData.MID_WHITE, padS);
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
