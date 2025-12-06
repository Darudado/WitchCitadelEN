package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Dust_3 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Re-Solarization", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("'Re-Solarization' is the Dust Witch's method of gaining more power on the battlefield. Though it sounds terrifying, since the Dust Witch herself is a quasi-stellar being, this so-called re-solarization is merely a process of removing limitations.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Generally, the Dust Witch has no need to use this method most of the time, as nothing on the surface can stop her, and the process of removing limitations to gain more fusion energy would cause irreversible damage to the surrounding environment, which completely contradicts her principles.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("To this day, in the long history of the Dark Dwellers, Naizel has only performed one massive re-solarization during her battle with the Pale King, causing the entire surrounding mountain range to be engulfed in massive radiation. Although this energy was collected afterward, it was also an important reason why the Dust Witch later left the surface to begin her Ethereal Journey.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Of course, performing this process in space has minimal impact on the surrounding environment, which means that in this realm inaccessible to mortals, this truly invincible witch can demonstrate her full power to a greater extent.", WthC_ColorData.MID_WHITE, padS);
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
