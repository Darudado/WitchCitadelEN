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
public class Data_Cross_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Basic Information of the Target Individual:", WthC_ColorData.CROSS_GARY_D, pad);

        tooltip.addPara(strings.get("Data_1"), pad, WthC_ColorData.HIGH_BLUE, "Rosalia");
        tooltip.addPara(strings.get("Data_11"), padS, WthC_ColorData.HIGH_BLUE, "Cross");
        tooltip.addPara(strings.get("Data_2"), padS, WthC_ColorData.HIGH_BLUE, "Cross Witch, Saint");
        tooltip.addPara(strings.get("Data_3"), padS, WthC_ColorData.HIGH_BLUE, "Humanoid Energy Composite, Controllable Memory");
        tooltip.addPara(strings.get("Data_4"), padS, WthC_ColorData.HIGH_BLUE, "Regenerative Immortality, Death upon Complete Energy Escape");
        tooltip.addPara(strings.get("Data_5"), padS, WthC_ColorData.HIGH_BLUE, "Earth, Sicily, Italy, A.C.626");
        tooltip.addPara(strings.get("Data_6"), padS, WthC_ColorData.HIGH_BLUE, ">1000");
        tooltip.addPara(strings.get("Data_7"), padS, WthC_ColorData.HIGH_BLUE, ">30000000");

        tooltip.addPara("Lies... illusions... conspiracies... plagues... Regardless, she is the most sinful existence among the council witches... Hidden behind the sacred radiance surrounding her are tens of millions of deaths.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("But if even after all this, no so-called deity has made her pay the slightest price... what harm is there in one more?", WthC_ColorData.CROSS_GARY_D, padS);


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
