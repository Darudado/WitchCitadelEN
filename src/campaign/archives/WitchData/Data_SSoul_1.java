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
public class Data_SSoul_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Basic information on the target individual:", WthC_ColorData.SOUL_GREEN, pad);

        tooltip.addPara(strings.get("Data_1"), pad, WthC_ColorData.HIGH_BLUE, "Su");
        tooltip.addPara(strings.get("Data_11"), padS, WthC_ColorData.HIGH_BLUE, "Spirit");
        tooltip.addPara(strings.get("Data_2"), padS, WthC_ColorData.HIGH_BLUE, "Spirit Witch, Lord of the Myriad Rifts");
        tooltip.addPara(strings.get("Data_3"), padS, WthC_ColorData.HIGH_BLUE, "Energy Entity, Uncontrollable Memories");
        tooltip.addPara(strings.get("Data_4"), padS, WthC_ColorData.HIGH_BLUE, "Transferable Immortality, Death upon Complete Erasure of Consciousness");
        tooltip.addPara(strings.get("Data_5"), padS, WthC_ColorData.HIGH_BLUE, "Earth, Heian-kyo, Japan, A.C.820");
        tooltip.addPara(strings.get("Data_6"), padS, WthC_ColorData.HIGH_BLUE, "<10");
        tooltip.addPara(strings.get("Data_7"), padS, WthC_ColorData.HIGH_BLUE, "Incalculable");

        tooltip.addPara("Perhaps statistically speaking... this council witch named Su is the one who has caused the least number of intelligent lifeform deaths.", WthC_ColorData.SOUL_GREEN, pad);
        tooltip.addPara("After all, she merely granted them the right to repeat their mistakes... and die a few more times...", WthC_ColorData.SOUL_GREEN, padS);



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
