package data.campaign.archives.Cube;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// Stitched together from items like the Two-Faced God
public class Data_Bb_1 extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");

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


        tooltip.addPara("Stellar Energy:", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("'The energy linking the entire starry sky' and 'the power controlling all fundamental rules' - these romantic and somewhat impractical metaphors are actually the most common explanations when referring to stellar energy. It can be said that stellar energy is the source of power for all star dust particles, and moreover, it is their manifestation of different paths to the same destination.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Although this sounds full of various idealistic and mystical implications, essentially even the concept of [ACCESS DENIED] was born from its influence.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Since all other types of stellar energy need to be transitioned from primordial stellar energy, the annihilation process for creating this energy is the most crucial link. Unlike other annihilation reactions produced by matter-antimatter collisions, the process of creating stellar energy only requires two equal amounts of star dust particles, which seems to suggest that there are no so-called anti-star dust particles in the world, or that it inherently possesses complete opposite properties.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The annihilation process can be completed simply by having two pure star dust particles collide at light speed, thus obtaining a certain amount of primordial stellar energy. Even though making a specific particle exceed light speed in a three-dimensional universe is a significant challenge, if only reaching it is required, it's not impossible. Most stellar energy existing in today's universe comes from the collapse of [ACCESS DENIED].", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("That Genesis-like [ACCESS DENIED] impact was the main reason star dust particles were scattered to every corner of the universe, and during this process, they could reach light speed. A considerable number of star dust particles collided in this state, thus giving birth to the vast primordial stellar energy that permeates through today's universe.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Most other stellar energies often originate from the de-primordial phenomenon of [ACCESS DENIED] species. In this process of finding renewable energy sources, large amounts of primordial stellar energy interact and merge with natural energy, giving rise to various unique energies that can be replicated locally. In this massive and lengthy evolution process, if some energy can extremely fortunately inherit the consciousness retention property from primordial stellar energy, it means a new member will be added to the stellar energy family.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("The greatest characteristic of primordial stellar energy is naturally its consciousness retention and quantum state information collection ability. Through this, organisms can completely attach their memories... or rather, [ACCESS DENIED] information to this energy, and can also control its flow through [ACCESS DENIED]. If willing, existing purely in the form of primordial stellar energy is not an impossibility.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Due to the [ACCESS DENIED] system, [ACCESS DENIED] organisms are actually passively engaging in primordial stellar energy interactions during normal times. On this basis, they can also more or less directly manipulate the flow of this energy, with specific effects related to the number of star dust particles contained within them.", WthC_ColorData.MID_WHITE, padS);
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
