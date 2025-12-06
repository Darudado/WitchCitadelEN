package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Dust_22 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Void Entropy Twin Blades", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Two weapons resembling sword hilts in their dormant state, always fastened to the Dust Witch's dress sash. The connection points between the 'hilts' and 'guards' are decorated with emblems of a crescent moon and a shattered sun respectively. As almost the only weapons the Dust Witch can use, they are both swords that destroy the mortal realm and pens that write elegies. At least, unlike their user, they don't have a past that needs to be let go.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Though the Void Entropy Twin Blades appear as swords without blades normally, when activated, their 'blades' come into view. Their hilts are actually special energy transformer mediums, extremely stable in physical form but essentially existing as energy polymers, which is why Naizel can keep them at her waist without fear of radiation damage.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Obviously, these two 'swords' are just a pair of energy emitters with focusing functions. But undoubtedly, these energy-made blades are far more destructive than any metal-forged swords in the world. Under these sharp blades composed of extreme heat, presumably nothing can escape the fate of being cut in half.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The Dust Witch rarely wields these two blades in human fashion. They usually float in mid-air, controlled freely by Naizel, and because of this, many sword techniques impossible by human standards can be easily mastered by her.", WthC_ColorData.MID_WHITE, padS);
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
