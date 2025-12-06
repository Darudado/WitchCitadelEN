package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_RRA_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Artifact Witch's Badge", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Artifact Witch's badge appears as a blue rose with leaves surrounded by seven orderly colored gemstones. The silver metal base is engraved with arrays symbolizing endings and origins. Combined with the translucent gems, this badge shines more brilliantly than others under light.", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Notably, unlike other witch badges, this Artifact Witch badge was entirely designed and crafted by Primordial Witch Lika Primordial, using precious metals and rare natural gems in this friendship-filled ornament.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Its disc base is made of platinum, the rose's stem and leaves of dyed tungsten gold, and the rose's main body composed of several sapphires. The seven different-colored gems surrounding the flower correspond to the basic elemental colors in Primordial theory, symbolizing the Primordial Witch's protective wishes. Heaven knows how much the Primordial Witch invested in this badge only 5CM in diameter.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Though those elemental theme colors aren't as harmonious as people imagine, and Lika isn't an art design expert, consequently, this badge doesn't match aesthetic principles like other badges designed by Pale Moon herself.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Instead, it contains numerous passive defensive measures... even though some hypothetical functions may never be used in the Artifact Witch's lifetime.", WthC_ColorData.HIGH_BLUE, pad);
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
