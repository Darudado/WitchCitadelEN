package data.campaign.archives.Elis;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;


// Stitched together from items like the Two-Faced God
public class Archives_CM233_SZB extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Mission Name: %s ", pad, WthC_ColorData.MYSTERIOUS_PURPLE, "Dance of the Valkyrie");

        tooltip.addPara("Creator: %s ", pad, WthC_ColorData.HIGH_BLUE, "Brunhilde");
        tooltip.addPara("Difficulty: %s ", padS, WthC_ColorData.HIGH_BLUE, "Gradually Increasing");
        tooltip.addPara("Challenge Type: %s ", padS, WthC_ColorData.HIGH_BLUE, "For Yoben Enthusiasts");
        tooltip.addPara("Style: %s ", padS, WthC_ColorData.HIGH_BLUE, "Valkyrie Style, Linear, Many Missions");
        tooltip.addPara("Description: %s ", padS, WthC_ColorData.HIGH_BLUE, "I am Brunhilde, if you wish for me to join... then show me your strength.");
        tooltip.addPara("Due to the large number of missions, it can serve as a kind of single-faction stress test.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("But the problem is there are just too many missions.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("(Possibly just to showcase the weeb factor)", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("(Right-click this item to unlock the corresponding mission)", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("(You can find them in the Bounty Board section of the Intel menu (E))", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("(Due to the large number of missions, it's not recommended to unlock multiple series at once)", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("(Requires Valkyrie Organization 3.2.1 or higher to play this mission series)", WthC_ColorData.DUST_RED, pad);

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

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Heh... let the show begin.");
        Global.getSector().getMemoryWithoutUpdate().set("$DR_Valk",true);
    }
}
