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
public class Data_Ba_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Star Dust Particles:", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A type of unnatural special fundamental particle that, in this dimension, can only be obtained through a reverse annihilation process and re-precipitation of primordial stellar energy. These fundamental particles can use star dust force to supersede other particles' original fundamental force associations, instead arranging and recombining around another set of logic.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("These particles are both the origin of all stellar energy interference organisms and magic, and are closely connected to all life forms born after the Big Bang.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As a fundamental particle, the star dust particle's position within the atom is absolutely central, always being encased by protons and neutrons, forming a multi-layered and patterned nucleus. On this foundation, other atomic rules remain unchanged, still attracting other fundamental particles from nature to arrange around it into complete atoms, thus further giving matter other characteristics.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("And if an atom contains star dust particles, this special atom composed entirely around star dust particles is also called a pan-stellar atom. The biggest difference between these special atoms and ordinary atoms is that they possess consciousness resonance characteristics independent of natural fundamental forces, also known as the [REDACTED] system.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Under the influence of [REDACTED], multiple pan-stellar atoms produce a mysterious resonance phenomenon, continuously emitting coordinated and complex magnetic field fluctuations to other atoms, as if possessing autonomous thinking ability.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The true nature of this resonance is actually the process of pan-stellar atoms analyzing and communicating with their surrounding environmental information, that is, the cycle of continuously emitting, receiving, and storing primordial stellar energy. Since primordial stellar energy has the ability to carry quantum state information, pan-stellar atoms can completely record the magnetic field and wave information collected by the primordial stellar energy during their exchange with surrounding counterparts.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Through this back-and-forth process, complete environmental information appears in one stellar energy network after another composed of numerous pan-stellar atoms. Based on this information, large numbers of atoms will arrange into a [REDACTED] collective, and a new life form is thus born, with the information contained in the primordial stellar energy networks becoming their initial memories and consciousness.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Life forms born through stellar energy interactions between pan-stellar atoms are called origin species, and due to the different environments of various planets in the universe, these origin species have different forms and mechanisms.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("However, since primordial stellar energy is a broadly non-renewable resource, they must find another renewable substitute before the stellar energy is completely depleted. Under natural selection, often a large portion of these origin species, born in the cradle of life and existing only in mythology, will vanish with the depletion of primordial stellar energy.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Generally by this stage, the primordial stellar energy that each species relies on for survival has long been replaced by other forms of energy, and the pan-stellar atoms, deprived of stellar energy nourishment, will temporarily abandon the [REDACTED] system as if completing their self-mission and enter a dormant state, becoming like any other atom in nature.", WthC_ColorData.MID_WHITE, padS);

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
