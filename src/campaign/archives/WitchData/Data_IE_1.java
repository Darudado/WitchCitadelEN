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
public class Data_IE_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Basic information of the target individual:", WthC_ColorData.IE_WHITE, pad);

        tooltip.addPara(strings.get("Data_1"), pad, WthC_ColorData.HIGH_BLUE, "Rika");
        tooltip.addPara(strings.get("Data_11"), padS, WthC_ColorData.HIGH_BLUE, "Primordial ");
        tooltip.addPara(strings.get("Data_2"), padS, WthC_ColorData.HIGH_BLUE, "Primordial  Witch, Chief Witch");
        tooltip.addPara(strings.get("Data_3"), padS, WthC_ColorData.HIGH_BLUE, "Humanoid Organism, Controllable Memory");
        tooltip.addPara(strings.get("Data_4"), padS, WthC_ColorData.HIGH_BLUE, "Transferable Immortality, Death upon Erasure of Backup Body or Consciousness");
        tooltip.addPara(strings.get("Data_5"), padS, WthC_ColorData.HIGH_BLUE, "Earth, London, England, A.C.574");
        tooltip.addPara(strings.get("Data_6"), padS, WthC_ColorData.HIGH_BLUE, ">15000");
        tooltip.addPara(strings.get("Data_7"), padS, WthC_ColorData.HIGH_BLUE, ">180000");

        tooltip.addPara("Even though this Council Witch named Rika has spent most of her long life in the high towers of the castle, traces of her can be found in many major historical military conflicts.", WthC_ColorData.IE_WHITE, pad);
        tooltip.addPara("On the battlefield, she always stands at the center of the main formation as a commander or chief of staff, rather than engaging on the front lines as many might imagine, even though the Primordial  Spear is such an efficient tool of slaughter.", WthC_ColorData.IE_WHITE, padS);
        tooltip.addPara("Perhaps for a wise individual encased in a flesh-and-blood body, the mind, knowledge, and strategy are far deadlier weapons...", WthC_ColorData.IE_WHITE, padS);
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
