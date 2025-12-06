package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// 从双面神等道具缝合而来
public class Data_Cross_2 extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");

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


        tooltip.addPara("Additional Information of the Target Individual:", WthC_ColorData.CROSS_GARY_D, pad);

        tooltip.addPara(strings.get("Data_2_1"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("The Cross Witch stands at a height of 175CM. Her silver hair that reaches down to her waist and her slightly grayish pupils give her an innate sacred aura. Not to mention her unusually large and proud chest...", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_2"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("An enigmatic woman, she always carries an air of sacredness and untouchability. Combined with her elegant speech that naturally carries an ethereal echo and the faint glow that emanates from her entire body, she is practically the embodiment of a saint to humans. However, upon closer interaction, it becomes clear that she is not exactly what she appears to be.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_3"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Energy Manipulation II, Leadership III, Politics II, War Overturning II", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_4"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("The Cross Witch is a master of mental control. Her methods include, but are not limited to, a series of psychological hints, brainwave interference, cognitive modification, hypnosis, and consciousness manipulation. These abilities, derived from both psychology and mysticism, are collectively referred to by her as \"Proclamation of Truth.\" Both the naming and the actual effects are filled with her usual penchant for mystique.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("As a \"supernatural\" being, the Cross Witch has seamlessly integrated herself into human society. Wherever theology exists in the world, her legends can be found. She can be the most holy figure standing in the mortal world on behalf of all gods, or the most evil deity lurking behind any fool...", WthC_ColorData.CROSS_GARY_D, pad); }

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
