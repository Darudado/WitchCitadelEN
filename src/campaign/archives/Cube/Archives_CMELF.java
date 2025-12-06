package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_CMELF extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Spirit Servants", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A collective term for all those under the ruling class in the Crimson City, not referring to any specific sentient being.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Though it sounds like a species from some fantasy work, these striking girls are actually extremely diverse from both biological and social perspectives. There are 'demons' with long black horns, 'angels' with pure white wings, and even humans commonly found throughout Perseus. Interestingly, regardless of their species, they are almost always biologically female within the system.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("However, due to the Crimson City's technology level far exceeding common sense, these Spirit Servants, nominally slaves, actually live extremely luxurious and relaxed lives. Since the space fortress itself has long achieved fully automated maintenance and operation, these elegantly dressed 'ladies' with abundant free time often have no actual daily work, like sheltered princesses seeking ways to pass time day after day in the vast expanse of space. Thanks to knowledge that seems to come from another world, they are often stronger and more intelligent than the native species of this sector; some, after learning of this, even hope to voluntarily sign this unequal contract to become part of it.", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("But just like the mysterious agnosticism that always shrouds this castle, no one truly knows the truth behind these contracts. Encounters with the Crimson Lord always seem so natural, whether chosen... or saved... or brought to destruction on a whim... All they need to do is wait for a mission to arrive and devote everything to it, as all has been predetermined since the signing of the crimson contract.", WthC_ColorData.HIGH_BLUE, pad);

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
