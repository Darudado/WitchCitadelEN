package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Reincarnation extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Star Dust Transfer", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A transportation system independently developed by the Primal Witch Rika, based on the 'Spatial Transfer Theory' derived from classical physics. Commonly abbreviated as 'Star Transfer'", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("In Spatial Transfer Theory, performing a physical transfer requires five steps: 'Model Creation → Spatial Positioning → Space Folding → Wormhole Generation → Directional Retention'. Star Transfer can skip two of these steps to achieve higher efficiency.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Thanks to the consciousness residue characteristic of primordial stellar energy, in principle, a Star Transfer only requires precise spatial positioning markers and wormhole generation markers, with the energy injected during wormhole activation being replaced by stellar energy. Once the two wormholes completely overlap, the transfer and retention process can proceed, forming extremely stable wormholes capable of transmitting large objects for extended periods.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("However, while this behavior of essentially maintaining permanently active one-way wormholes greatly enhances the Star Transfer system's stability and flexibility, it also means it constantly requires enormous amounts of energy to maintain, and the computing power needed for each transfer is also astounding...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("It can be said that until now, this technology remains exclusive to the Crimson Citadel, as for the Perseus inhabitants, such a low cost-effectiveness attempt is far less practical than finding ways to restart the gates.", WthC_ColorData.MID_WHITE, padS);
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
