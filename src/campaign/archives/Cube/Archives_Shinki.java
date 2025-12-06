package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Shinki extends BaseSpecialItemPlugin{

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


        tooltip.addPara("The Third Principal Deity", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The 'Temple of Iris' and 'Crimson Citadel' both correspond to two principal deities from an alien mythology, yet the third principal deity has no corresponding cultural symbol in the castle.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("For the Crimson Lord, who values ceremony, such incompleteness is not her usual style... but perhaps there are deeper reasons behind this that mortals cannot know?", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("In that alien civilization's mythology, Shinki the Pale Creation God was one of three principal deities, the creator of the Ancient Capital, and the true and only mother of all universal life forms. A being capable of controlling the mass portion of matter through will, able to freely arrange and recombine any fundamental particles and waves given the presence of energy, thus possessing the so-called ability of 'creation', but unable to freely control the 'energy' portion of matter. Without external energy assistance, their abilities would be greatly limited.", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("The only form they presented was extremely similar to Supreme Deity Hillya, eternally unchanging in their treatment of everything around them, completely indulgent toward their only relative, sister Hillya, but never actively interfering with any matters in the universe.", WthC_ColorData.HIGH_BLUE, pad);

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
