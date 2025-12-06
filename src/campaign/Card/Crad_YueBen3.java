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
import java.util.List;
import java.util.Objects;

// 从幻想工造的军官酒馆事件和双面神等道具缝合而来
public class Crad_YueBen3 extends BaseSpecialItemPlugin{

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

        tooltip.addPara(strings.get("CardRe1"), pad, Misc.getNegativeHighlightColor(), "Connected");

        tooltip.addPara(strings.get("CardRe2"), pad, WthC_ColorData.HIGH_BLUE, "Brynhild");
        tooltip.addPara(strings.get("CardRe3"), padS, WthC_ColorData.HIGH_BLUE, "Valkyrie Chief");
        tooltip.addPara(strings.get("CardRe4"), padS, WthC_ColorData.HIGH_BLUE, "This warrior has an upright and unyielding personality, making her a formidable foe.");
        tooltip.addPara("This character excels in frontline combat, so try to position her at the forefront.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara(strings.get("CardRe7"), WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara(strings.get("CardRe5"), WthC_ColorData.MYSTERIOUS_PURPLE, pad);
        tooltip.addPara("(Note: Whenever the Ring Spirit Link is reset, all officer-related data will be initialized...)", WthC_ColorData.MID_WHITE, padS);
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
        Witch.add("DR_YueBenJunGuan");
        Witch.add("target_analysis");
        Witch.add("missile_specialization");
        Witch.add("ordnance_expert");
        Witch.add("systems_expertise");
        Witch.add("ballistic_mastery");
        Witch.add("field_modulation");

        for (String SENetLink : Witch) {
            this.officer.getStats().setSkillLevel(SENetLink, 2.0F);
        }


        this.officer.getStats().setLevel(7);
        this.officer.setPersonality("aggressive");
        this.officer.setGender(FullName.Gender.FEMALE);
        this.officer.setRankId("spaceCommander");
        this.officer.setPostId("spacer");
        this.officer.setId("WitchBrunhilde");
        this.officer.setPortraitSprite(Global.getSettings().getSpriteName("characters", "novtry"));
        this.officer.setName(new FullName("Brynhild", "", FullName.Gender.FEMALE));
        this.player_fleet.getFleetData().addOfficer(this.officer);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_YueBen2", "special_items"), 1.0F);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You have temporarily put this Ring Spirit Key into hibernation... Use it again to reawaken the link.");
    }
}
