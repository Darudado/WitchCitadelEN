package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_IE_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Primordial Spirit Ring: Glimpse", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Primordial Witch's spirit ring is named Glimpse, with silver-gray band and base, set with a faintly radiating colorless transparent magic crystal in the center. As the first Primordial Ring ever created, it was designed and crafted entirely by Lika herself, appearing rather plain without many fancy elements.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This design later became the base version for mass-produced Primordial Spirit Rings, though those samples widely sold in the Dark World market all appear more... ornate.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Due to the need for extensive research, the Glimpse on the Primordial Witch's hand has customized functions including text scanning and translation, instant memory access, elemental property programming simulation, energy infusion, and fusion simulation.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Additionally, as the first ever Primordial Ring, Glimpse is called the 'Master Ring' and has the unique function of managing other rings' permissions.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Furthermore, due to certain special requirements, Glimpse has enhanced information interaction capabilities beyond the basic functions, allowing constant monitoring of another Primordial Spirit Ring owner's every move...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Fortunately the other party is unaware of this, otherwise such an almost pathological protective desire would surely be extremely uncomfortable.", WthC_ColorData.MID_WHITE, padS);
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
                "Congratulations on spending 10 million for a piece of data... Keep up the good work, little genius. Here's a Story Point for your trouble~");
    }
}
