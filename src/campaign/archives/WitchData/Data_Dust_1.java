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
public class Data_Dust_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Basic Information of the Target Individual:", WthC_ColorData.DUST_RED, pad);

        tooltip.addPara(strings.get("Data_1"), pad, WthC_ColorData.HIGH_BLUE, "Nizer");
        tooltip.addPara(strings.get("Data_11"), padS, WthC_ColorData.HIGH_BLUE, "Dust");
        tooltip.addPara(strings.get("Data_2"), padS, WthC_ColorData.HIGH_BLUE, "Dust Witch, Star Shatterer");
        tooltip.addPara(strings.get("Data_3"), padS, WthC_ColorData.HIGH_BLUE, "Quasi-Stellar Entity, Uncontrollable Memory");
        tooltip.addPara(strings.get("Data_4"), padS, WthC_ColorData.HIGH_BLUE, "Absolute Immortality, Nearly Impossible to Kill Before the Heat Death of the Universe");
        tooltip.addPara(strings.get("Data_5"), padS, WthC_ColorData.HIGH_BLUE, "Earth, Damascus, Arabia, A.C.723");
        tooltip.addPara(strings.get("Data_6"), padS, WthC_ColorData.HIGH_BLUE, ">1000000");
        tooltip.addPara(strings.get("Data_7"), padS, WthC_ColorData.HIGH_BLUE, ">30000");

        tooltip.addPara("Swords, guns, vehicles, warships—so many weapons have appeared throughout the long evolutionary history of human civilization. Yet, they all share one common trait: they pose no threat to a star...", WthC_ColorData.DUST_RED, pad);
        tooltip.addPara("So, it's clear that when a humanoid star descends upon the battlefield, there's only one way for people to respond—die.", WthC_ColorData.DUST_RED, padS);
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
