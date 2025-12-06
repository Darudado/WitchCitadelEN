package data.campaign.Card;

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
public class Crad_B2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("You know that this inspiration will drive one to pursue even more absolute individual power...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Even if it means paying a greater price...", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("Just as in the faintly flashing fragments of memory... a vast empire crumbled into dust due to this morbid obsession...", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("Current state of the Doctrine of Dominance: %s ", pad, Misc.getPositiveHighlightColor(), new String[]{"Accepted"});
        tooltip.addPara("(You can find detailed information about this skill in your officer skill tab...)", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("(Using it again will temporarily remove these effects...)", WthC_ColorData.MID_WHITE, padS);

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

        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_B3", "special_items"), 1.0F);
        Global.getSector().getPlayerStats().setSkillLevel("Overmind_Doctrine", 0.0F);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Should you really give up on this inspiration...?");
    }
}
