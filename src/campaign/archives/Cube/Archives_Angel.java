package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Angel extends BaseSpecialItemPlugin{

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


        tooltip.addPara("The Winged Ones", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A group of humanoid beings originating from another ancient civilization, ancient and precise, capable of manipulating surrounding magnetic fields to achieve effects similar to 'psychokinesis'. From a biological perspective, they are extremely similar to ordinary humans, or rather, human structure is extremely similar to theirs... a miraculous coincidence?", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("'Angels', thanks to the still considerable influence of the Lutheran Church in the space age, some Perseus residents are not unfamiliar with this religious concept originating from humanity's cradle period. Coincidentally, for those white-winged women in the Crimson City, this beautiful nickname will become their most elegant calling card when entering human society.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("However, as beings isolated from the world, perhaps they will never have a day to open their eyes to see the world, and when people cast aside their too-ancient stereotypes, what these 'Winged Ones' possess may not be any divine aura, but rather the desolation and helplessness of a setting sun.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("As members of an ancient civilization, they were meant to serve the great Creation God and maintain that eternal prosperity. The Ancient Capital was the cradle of all angels, forever supreme... forever sacred... forever standing...", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("While the winged ones bound to the Crimson are exiles one after another, forced to become spirits who have lost their past, never again finding the path back to their homeland.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("Even though the mother of creation remains in the distance...", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("Even though the supreme deity protects their being...", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("The lost children are destined never to return...", WthC_ColorData.MID_WHITE, padS);
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
