package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Hillya extends BaseSpecialItemPlugin{

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


        tooltip.addPara("The Crimson Citdel", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A massive fortress-city spanning tens of thousands of meters in diameter, equipped with comprehensive facilities, like a luxurious artificial space station that can drift freely through the cosmos.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Although from its design it's clear that it was meant to exist in a floating state from the beginning, various details reveal that this 'castle' also spent considerable time within an atmospheric environment.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Both nominally and in reality, this castle belongs entirely to the Crimson Lord, who holds absolute dominion over it. Even all living beings within the castle have signed eternal unequal contracts with her... including all the Council Witches.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("But this doesn't mean the Crimson Lord is a power-abusing tyrant. On the contrary, this somewhat peculiar dictator doesn't restrict any beings, and life within the castle is often absolutely free...", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("Even among the so-called maids, butlers, and nobles within the castle, there are no actual class distinctions, as if these non-human beings simply enjoy engaging in some extremely immersive cosplay...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As for the name 'Crimson Citadel', it broadly comes from the mythology of an alien civilization, as otherwise there's hardly any trace of 'crimson' visible on this massive structure.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("In the mythology of that alien civilization, Hillya the Crimson Supreme Deity was one of three principal deities, the designer of the Ancient Capital, and the initial guide for most intelligent civilizations in the universe. A being capable of controlling universal energy with thought, able to freely transform, jump, compress, and weave any type of energy, and create massive energy through matter-antimatter reactions, still following the law of conservation of energy, unable to control the 'mass' portion of matter.", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("She has only ever presented herself in one form: a white-haired, red-clothed demoness resembling an ordinary woman, who lingers behind the scenes of all civilizations, finding various forms of pleasure in her preferred ways.", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("Clearly, the Crimson Lord is that principal deity herself... Don't worry, I, being incorporeal, am not under any form of coercion, probably.", WthC_ColorData.MID_WHITE, padS);
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
