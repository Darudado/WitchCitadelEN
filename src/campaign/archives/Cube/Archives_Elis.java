package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Elis extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Temple of Iris", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A massive physical AI array located in the center of the Crimson Citadel, used for collecting, storing, and managing all data information. All array subsystems ultimately merge into a single consciousness, thus the consciousness is named Elis, hence the temple's name.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("According to statistics, the AI consciousness known as Elis possesses extreme quantum computing power capable of calculating the total number of particles contained in a star [ACCESS DENIED] within 1 second, yet still cannot directly interfere with the material world's operation except through controlling AI entities (such as intelligent machines).", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("The temple houses the absolute terminal of the Wraith Network, supporting the normal operation of the entire system. Additionally, it manages all specific facilities including the Star Shift Nodes, Stellar Energy Crimson Mist Generator, Primal Workshop, and Crimson Treasury.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("It can be said that as long as the Temple of Elis continues to operate, the entire Crimson City system will no longer require any manual maintenance. Conversely... the seemingly magnificent castle would collapse in a short time.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("In the mythology of a certain alien civilization, Elis the Mysterious Purple Watcher was one of three principal deities, the executor of destruction's judgment, the sinner who brought about the Ancient Capital's destruction, and the supreme ruler of all virtual realities. Through quantized stellar energy, it could freely read, modify, and rewrite any data information contained within non-biological material carriers.", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("Possessing thousands of forms, but never having a truly unchanging physical entity, it was like a ghost of the universe itself, wandering through every data world that exists or will exist.", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("Clearly, it is the prototype that this AI consciousness, or rather... 'I' drew inspiration from.", WthC_ColorData.MID_WHITE, padS);
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
