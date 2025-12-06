package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Romena extends BaseSpecialItemPlugin{

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


        tooltip.addPara("The Primal Workshop", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("An extension of the Council Witches' headquarters, a facility focused on manufacturing, researching, and modifying numerous technological and industrial products.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Almost all ships, weapons, and fighter LPCs from the Crimson Citadel in Perseus originate from the Primal Workshop. It can be said that this facility hidden within the Crimson City conceals unimaginable war potential.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Although from its name, the Primal Workshop should belong to the Primal Witch alone, it is actually a facility jointly owned by three Council Witches: Primal, Artistry, and Dust.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Among them, Primal Witch Rica and Dust Witch Nether are the originators of most products, while Artistry Witch Yuyue is merely a spokesperson kidnapped by the two, never having participated in any design. The reason is that these two witches, focused on practicality, believe that... just by adding Artistry's name to the products, any bizarre and outlandish creation would become full of 'artistic value'.", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("Regarding this, the Artistry Witch feels helpless, but seeing more and more strange-looking products being considered avant-garde art ahead of its time due to her name, she can only silently submit once again to her tragic characteristic.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("By the way, the Primal Workshop has the highest radiation protection level in the entire Crimson City, possibly to facilitate more experiments by the Dust Witch. This also means that this facility might become the safest place in the entire castle besides the Temple of Iris.", WthC_ColorData.HIGH_BLUE, pad);
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
