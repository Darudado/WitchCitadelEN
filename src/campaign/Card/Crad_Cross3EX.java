package data.campaign.Card;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

// 从幻想工造的军官酒馆事件和双面神等道具缝合而来
public class Crad_Cross3EX extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");
    protected CampaignFleetAPI player_fleet;
    protected PersonAPI officer;
    protected OfficerDataAPI officer_data;


    @Override
    public String getDesignType() {
        return null;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, CargoTransferHandlerAPI transferHandler, Object stackSource) {
        float pad = 10f;
        float padS = 5f;

        tooltip.addTitle(getName());

        String design = getDesignType();
        if (design != null) {
            Misc.addDesignTypePara(tooltip, design, 10f);
        }

        if (!spec.getDesc().isEmpty()) {
            tooltip.addPara(spec.getDesc(), Misc.getTextColor(), pad);
        }

        tooltip.addPara(strings.get("CardRe1"), pad, Misc.getPositiveHighlightColor(), "Connected");

        tooltip.addPara(strings.get("CardRe2"), pad, WthC_ColorData.HIGH_BLUE, "Rosalia Cross");
        tooltip.addPara(strings.get("CardRe3"), padS, WthC_ColorData.HIGH_BLUE, "Cross Witch");
        tooltip.addPara(strings.get("CardRe4"), padS, WthC_ColorData.HIGH_BLUE, "This witch is a mysterious deceiver to both allies and enemies...");
        tooltip.addPara("She excels at paralyzing or outright destroying enemies with ease, and even when piloting the most cumbersome ships, she can intermittently unleash terrifying bursts of mobility.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara(strings.get("CardRe7"), WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara(strings.get("CardRe5"), WthC_ColorData.MYSTERIOUS_PURPLE, pad);
        tooltip.addPara("(Note: Every time the Ring Spirit link is reset, all officer-related data will be initialized...)", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Now... you are one step closer to their legendary form...", WthC_ColorData.B_WHITE, pad);
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
        this.officer = Global.getFactory().createPerson();
        this.officer_data = Global.getFactory().createOfficerData(this.officer);

        for(OfficerDataAPI member : this.player_fleet.getFleetData().getOfficersCopy()) {
            PersonAPI officers = member.getPerson();
            if(Objects.equals(officers.getId(), "WitchCrossEX")){
                this.player_fleet.getFleetData().removeOfficer(officers);

                for (FleetMemberAPI member2 : this.player_fleet.getFleetData().getMembersListCopy()) {
                    PersonAPI officer2 = member2.getCaptain();
                    if(Objects.equals(officer2.getId(), "WitchCrossEX")){
                        member2.setCaptain(null);
                    }
                }

                break;
            }
        }
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_Cross4", "special_items"), 1.0F);
        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getMemoryWithoutUpdate().set("$CrossEX-ON",false);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You have temporarily put this Ring Spirit Key into a dormant state... Use it again to reawaken the link.");
    }
}
