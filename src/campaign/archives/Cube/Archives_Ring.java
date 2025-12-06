package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Ring extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Primal Spirit Ring", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Primal Spirit Ring is a multifunctional energy-storing ring, and as its name suggests, it's a masterpiece of the Primal Witch. The Primal Witch has customized different Primal Spirit Rings for each member of the Council of Witches, and these rings serve as the best proof of their identity.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Generally, a Primal Ring consists of three parts: a double-helix circular ring, a metal base resembling an eyeglass frame, and an energy-focusing gem/crystal serving as the eyeball. Based on this foundation, each Primal Spirit Ring often has significant variations in appearance, filled with the bearer's personal essence.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Functionally, they are essentially mediums for long-distance recording and transmission of spiritual information. The Ring Spirit Network, with the rings serving as sub-terminals, is their primary function. Whoever wears this ring can communicate with others wearing similar rings regardless of location, or engage in more specific information exchanges.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Thanks to the stellar energy utilization technology brought by the Crimson Lord, this so-called information transmission can incredibly enter directly into the consciousness of living beings, so when using these rings, there's a completely immersive holographic experience.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Of course, these rings also have basic functions such as storing text/video information, active energy emission, and virtual image projection, but more importantly than these relatively minor functions, each Primal Spirit Ring possesses some additional customized features.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Since the witch ladies of the Crimson City are... individuals with quite distinct personal styles, the customized functions on these rings vary dramatically, with some even capable of being used directly as weapons.", WthC_ColorData.MID_WHITE, padS);

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
