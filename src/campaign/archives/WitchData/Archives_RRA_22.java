package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_RRA_22 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Passive Defense Measures", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Because her energy and physical strength remain similar to a normal person's, without her magical artifacts, the Artifact Witch can't even match an ordinary adult human male.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Even more than combat ability, Pale Moon's almost non-existent will to fight is a greater limiting factor. However, even though she strongly opposes using violence or carrying any lethal items, the Primordial Witch still created many protective magical accessories for this carefully cherished friend out of 'safety' concerns.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Under normal circumstances, these aesthetically pleasing magical tools play the role of unsuspicious gem accessories, like the Artifact Witch badge. However, once emergency mode is activated, regardless of Pale Moon's willingness, they automatically eliminate surrounding threats by any means.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Thus, magical artifacts are the source of almost all Pale Moon's combat power. Relying on these enchanted accessories from the Primordial Witch, Pale Moon can passively defeat some not-so-powerful opponents even against her will... or create some... very spectacular explosions when she is willing.", WthC_ColorData.MID_WHITE, padS);
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
