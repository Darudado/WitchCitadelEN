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
public class Data_RRA_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Basic information of the target individual:", WthC_ColorData.DEEP_BLUE, pad);

        tooltip.addPara(strings.get("Data_1"), pad, WthC_ColorData.HIGH_BLUE, "Youyu");
        tooltip.addPara(strings.get("Data_11"), padS, WthC_ColorData.HIGH_BLUE, "Wuxin");
        tooltip.addPara(strings.get("Data_2"), padS, WthC_ColorData.HIGH_BLUE, "Rosalia Witch, World Artisan");
        tooltip.addPara(strings.get("Data_3"), padS, WthC_ColorData.HIGH_BLUE, "Humanoid Organism, Controllable Memory");
        tooltip.addPara(strings.get("Data_4"), padS, WthC_ColorData.HIGH_BLUE, "Conceptual Immortality, Can Be Killed Physically");
        tooltip.addPara(strings.get("Data_5"), padS, WthC_ColorData.HIGH_BLUE, "Earth, Sui-Yang Yuhang, A.C.577");
        tooltip.addPara(strings.get("Data_6"), padS, WthC_ColorData.HIGH_BLUE, "0");
        tooltip.addPara(strings.get("Data_7"), padS, WthC_ColorData.HIGH_BLUE, ">50000");

        tooltip.addPara("This Council Witch named Youyu never resorts to violence to solve problems, and in her long life, she has never personally caused the death of any intelligent being.", WthC_ColorData.DEEP_BLUE, pad);
        tooltip.addPara("Unfortunately, even though she is unarmed, her enchanting presence can still lead to fatal consequences against her will.", WthC_ColorData.DEEP_BLUE, padS);
        tooltip.addPara("Countless fools have paid with their lives in their pursuit of this \"Enchantress,\" yet she remains oblivious to it... or perhaps these memories have been deliberately forgotten due to external factors.", WthC_ColorData.DEEP_BLUE, padS);
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
