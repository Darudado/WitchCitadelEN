package data.campaign.Card;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 从幻想工造的军官酒馆事件和双面神等道具缝合而来
public class Crad_Dust4 extends BaseSpecialItemPlugin{

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

        tooltip.addPara(strings.get("CardRe1"), pad, Misc.getNegativeHighlightColor(), "Disabled");

        tooltip.addPara(strings.get("CardRe2"), pad, WthC_ColorData.HIGH_BLUE, "Nazel-Dust");
        tooltip.addPara(strings.get("CardRe3"), padS, WthC_ColorData.HIGH_BLUE, "Dust Witch");
        tooltip.addPara(strings.get("CardRe4"), padS, WthC_ColorData.HIGH_BLUE, "This witch will become a lingering nightmare for all enemies...");
        tooltip.addPara("She excels at annihilating all approaching foes with sheer violence while charging recklessly... Even when constantly in danger, the vast energy swirling like a storm makes her dreadnought nearly indestructible.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara(strings.get("CardRe7"), WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara(strings.get("CardRe6"), WthC_ColorData.MYSTERIOUS_PURPLE, pad);
        tooltip.addPara("(Note: Every time the Wraith Link is reset, all data related to the actual officer will be initialized...)", WthC_ColorData.MID_WHITE, padS);

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

        List<String> Witch = new ArrayList();
        Witch.add("SENetLink");
        Witch.add("WitchDust");
        Witch.add("ballistic_mastery");
        Witch.add("energy_weapon_mastery");
        Witch.add("ordnance_expert");
        Iterator var7 = Witch.iterator();

        List<String> Witch2 = new ArrayList();
        Witch2.add("helmsmanship");
        Witch2.add("target_analysis");
        Witch2.add("systems_expertise");
        Iterator var8 = Witch2.iterator();

        while(var7.hasNext()) {
            String SENetLink = (String)var7.next();
            this.officer.getStats().setSkillLevel(SENetLink, 2.0F);
        }
        while(var8.hasNext()) {
            String SENetLink2 = (String)var8.next();
            this.officer.getStats().setSkillLevel(SENetLink2, 1.0F);
        }

        this.officer.getStats().setLevel(7);
        this.officer.setPersonality("heroic");
        this.officer.setGender(FullName.Gender.FEMALE);
        this.officer.setRankId("spaceCommander");
        this.officer.setPostId("spacer");
        this.officer.setId("WitchDust");
        this.officer.setPortraitSprite(Global.getSettings().getSpriteName("intel", "WN1"));
        this.officer.setName(new FullName("Nizer", "Dust", FullName.Gender.FEMALE));
        this.player_fleet.getFleetData().addOfficer(this.officer);

        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_Dust3", "special_items"), 1.0F);
        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You have reactivated this Wraith Key... Use it again to enter hibernation mode.");
    }
}
