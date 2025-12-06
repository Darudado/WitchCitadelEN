package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Demon extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Crimson Demons", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A group of surviving offspring from the Dark World, ancient and strong, with physical strength that nearly defies the laws of physics but completely lacking the ability to reproduce.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("'Demons', or perhaps... 'Devils'? Surely this is an extremely fitting alias for these ladies who exude an aura of bloodshed, and it's often how they refer to themselves as well.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("But don't worry, as members of the material universe, they don't possess those supernatural abilities from religious contexts - there were just some subtle accidents in their evolution from massive beasts to sentient beings. In any case... even though their muscles appear weak yet possess immense strength, even though their eyes glow red with bloodthirst, even though they can freely move through space wearing just elegant dresses, they are still beings not much different from the homo sapiens scattered throughout this sector.", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("At least, that's what the Crimson Lord would advise you.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("And you have no ability to question this reality.", WthC_ColorData.HIGH_BLUE, padS);

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
