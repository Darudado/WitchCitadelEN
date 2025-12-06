package data.GongShi;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;

// Stitched together from Janus and other items
public class Crad_Diff1 extends BaseSpecialItemPlugin{


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


        tooltip.addPara("After use, will switch the difficulty of all \"Road of the Warrior\" series levels", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Current difficulty: %s ", pad, WthC_ColorData.MYSTERIOUS_PURPLE, new String[]{"Easy"});
        tooltip.addPara("Right-click to switch to other difficulties", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("(Switching difficulty will also reset all completed levels in this series)", WthC_ColorData.B_WHITE_L, padS);
        tooltip.addPara("(Currently accepted levels can still be challenged)", WthC_ColorData.B_WHITE_L, padS);


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


        Global.getSector().getMemoryWithoutUpdate().set("$ROTK-ON-L",false);
        Global.getSector().getMemoryWithoutUpdate().set("$ROTK-ON",true);
        Global.getSector().getMemoryWithoutUpdate().set("$ROTK-ON-H",false);

        Global.getSector().getMemoryWithoutUpdate().set("$WthC_IL-hegemony",false);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_IL-tritachyon",false);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_IL-pirates",false);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_IL-persean_league",false);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_IL-remnants",false);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_IL-sindrian_diktat",false);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_IL-luddic_church",false);

        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_Diff2", "special_items"), 1.0F);

            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to switch difficulty, now all Road of the Warrior levels are set to Normal");

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);

    }
}
