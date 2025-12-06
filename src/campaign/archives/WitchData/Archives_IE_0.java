package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_IE_0 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Primordial Witch's Decorative Style", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Primordial Witch always wears a simple, plain black and white robe, accompanied by various magical artifacts. These colorful gem ornaments often brazenly display their elemental types, their brilliant radiance almost overshadowing everything else.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Perhaps it's because Lika noticed this discord that she chose to use simple monochrome robes to balance out the overflow of colors...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Most accessories Lika carries are made by her and the Artifact Witch, emphasizing aesthetics with slight practicality. Actually, the Primordial Witch's magic has no direct connection to these accessories, which exist more as her hobby.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("She seems to be a natural jewelry enthusiast, continuing her jewelry crafting practice from her human days in the Primordial Workshop until now, still producing more gorgeous gem creations.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("A huge pointed witch hat is the Primordial Witch's most iconic symbol, with a crown-like woven ring design at the tip of the point, and a highly conspicuous Primordial Witch badge embedded on the right front side.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Notably, this witch hat itself is a color-changing magical artifact, often displaying the opposite color of her robe for coordination. So although its appearance constantly changes, the Primordial Witch has never worn any other hat.", WthC_ColorData.MID_WHITE, padS); }

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
                "Congratulations on spending 10 million for a piece of data... Keep up the good work, little genius. Here's a Story Point for your trouble~");
    }
}
