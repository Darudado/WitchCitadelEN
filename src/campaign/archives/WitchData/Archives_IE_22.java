package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_IE_22 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Eternal Conquest Scepter", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A ceremonial scepter about one meter long, composed of tip, shaft, and end sections, more resembling an ornate artwork than a tool.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("At the tip, beneath sturdy metal wire frames lies a massive crystal displaying star charts within, with visible energy streams constantly flowing into the shaft below; the shaft section resembles a typical metal scepter, its slender structure clearly an energy capacitor; the end section has a teardrop-shaped exquisite gem, where careful observation reveals slight reddish plasma gathering within.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("This precious 'ceremonial scepter' isn't from the Primordial Witch's hands - she's more like 'temporarily safeguarding' this cool-sounding metal rod for someone, though this doesn't prevent her from regularly using the vast, cosmos-like energy within. For the Primordial Witch, who doesn't have much stellar energy residing in her body, this scepter serves as her best magical sustenance tool.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Of course... even this mysterious scepter with seemingly inexhaustible internal energy has limited generation speed, deliberately restricted to an extremely low threshold, so it still can't guarantee Lika's regrettable energy sustainability.", WthC_ColorData.MID_WHITE, padS); }

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
