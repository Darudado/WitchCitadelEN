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
public class Archives_CM233_ZuanShi extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Mission Name: %s ", pad, WthC_ColorData.MYSTERIOUS_PURPLE, "Vanilla Fantasy");

        tooltip.addPara("Creator: %s ", pad, WthC_ColorData.HIGH_BLUE, "Trash Diamond");
        tooltip.addPara("Difficulty: %s ", padS, WthC_ColorData.HIGH_BLUE, "Cheat Level");
        tooltip.addPara("Challenge Type: %s ", padS, WthC_ColorData.HIGH_BLUE, "Stacking Game Enthusiasts");
        tooltip.addPara("Style: %s ", padS, WthC_ColorData.HIGH_BLUE, "Just a Star Rage of Numbers");
        tooltip.addPara("Description: %s ", padS, WthC_ColorData.HIGH_BLUE, "Seems to be a regular fleet from Perseus, but somehow it ended up here.");
        tooltip.addPara("The fleet composition is complete, but appears to be twisted by an unknown force, with no signs of life throughout the fleet. Surprisingly, the fleet remains combat-ready at all times. Is this reality, or illusion? Either way, some poor soul needs to clean up this mess.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("Eh? Can't even beat this, stacking game players? What weaklings~", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("(Right-click this item to unlock the corresponding mission)", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("(You can find them in the Bounty Board section of the Intel menu (E))", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("(Due to the large number of missions, it's not recommended to unlock multiple series at once)", WthC_ColorData.MID_WHITE, padS);


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
        Global.getSector().getMemoryWithoutUpdate().set("$DR_Diamond",true);
    }
}
