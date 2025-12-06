package data.GongShi;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;

import java.awt.*;

// Stitched together from Janus and other items
public class Crad_Skip extends BaseSpecialItemPlugin{


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


        tooltip.addPara("After use, will skip the current main story level and proceed to the next one, still needs to be accepted from the bounty board", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Although this will make you lose all post-battle rewards, it helps you directly challenge the next opponent", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Please note that any level skip is irreversible, and this item cannot skip the final level", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("(This item can be used infinitely, but is not recommended under normal circumstances)", WthC_ColorData.B_WHITE_L, padS);
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
        return false;
    }

    @Override
    public void performRightClickAction() {
        this.player_fleet = Global.getSector().getPlayerFleet();

        if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_CM-RRA")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-RRA",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-RRA2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Crimson Invitation - Cross Phantom");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_CM-Cross")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-Cross",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-Cross2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Crimson Invitation - Primordial Phantom");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_CM-IE")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-IE",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-IE2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Crimson Invitation - Soul Phantom");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_CM-SSoul")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-SSoul",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-SSoul2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Crimson Invitation - Spirit Phantom");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_CM-Elf")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-Elf",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-Elf2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Crimson Invitation - Dust Phantom");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_CM-Dust")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-Dust",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-Dust2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Angel's Expedition");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_AL-1")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_AL-1",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_AL-1EX",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Divine Warning");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_AL-2")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_AL-2",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_AL-2EX",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Martyr's Journey");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_AL-3")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_AL-3",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_AL-3EX",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Pale Contract - Art Reflection");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_BA-RRA")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-RRA",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-RRA2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Pale Contract - Cross Reflection");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_BA-Cross")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-Cross",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-Cross2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Pale Contract - Primordial Reflection");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_BA-IE")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-IE",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-IE2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Pale Contract - Soul Reflection");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_BA-SSoul")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-SSoul",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-SSoul2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Pale Contract - Spirit Reflection");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_BA-Elf")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-Elf",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-Elf2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Pale Contract - Dust Reflection");
        } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_BA-Dust")) {
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-Dust",true);
            Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-Dust2",false);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You used the invitation to skip the current level, next level is Chronicle of Crimson and Pale");
        } else {
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You have reached the final level, cannot skip!");
        }

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);

    }
}
