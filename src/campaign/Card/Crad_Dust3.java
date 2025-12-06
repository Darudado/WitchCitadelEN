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
public class Crad_Dust3 extends BaseSpecialItemPlugin{

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

        tooltip.addPara(strings.get("CardRe2"), pad, WthC_ColorData.HIGH_BLUE, "Nazel-Dust");
        tooltip.addPara(strings.get("CardRe3"), padS, WthC_ColorData.HIGH_BLUE, "Dust Witch");
        tooltip.addPara(strings.get("CardRe4"), padS, WthC_ColorData.HIGH_BLUE, "This witch will become a lingering nightmare for all enemies...");
        tooltip.addPara("She excels at annihilating all approaching foes with sheer violence while charging recklessly... Even when constantly in danger, the vast energy swirling like a storm makes her dreadnought nearly indestructible.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara(strings.get("CardRe7"), WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara(strings.get("CardRe5"), WthC_ColorData.MYSTERIOUS_PURPLE, pad);
        tooltip.addPara("(Note: Every time the Wraith Link is reset, all data related to the actual officer will be initialized...)", WthC_ColorData.MID_WHITE, padS);
        if(Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_CAP")){
            tooltip.addPara("(Perhaps... this Wraith Key holds other secrets?)", WthC_ColorData.B_WHITE, pad);
        }
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
            if(Objects.equals(officers.getId(), "WitchDust")){
                this.player_fleet.getFleetData().removeOfficer(officers);

                for (FleetMemberAPI member2 : this.player_fleet.getFleetData().getMembersListCopy()) {
                    PersonAPI officer2 = member2.getCaptain();
                    if(Objects.equals(officer2.getId(), "WitchDust")){
                        member2.setCaptain(null);
                    }
                }

                break;
            }
        }
        if(!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_CAP")){
            Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_Dust4", "special_items"), 1.0F);
            Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "You have temporarily put this Wraith Key into hibernation... Use it again to reawaken the link.");
        } else {
            Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_Dust3EX", "special_items"), 1.0F);
            Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
            Global.getSector().getMemoryWithoutUpdate().set("$DustEX-ON",true);
            Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                    "The Wraith Key refused to hibernate... and shifted into another form...");

            List<String> Witch = new ArrayList();
            Witch.add("Pale_SENetLink");
            Witch.add("Pale_WitchDust");
            Witch.add("Pale_ballistic_mastery");
            Witch.add("Pale_energy_weapon_mastery");
            Witch.add("Pale_ordnance_expert");
            Iterator var7 = Witch.iterator();

            List<String> Witch2 = new ArrayList();
            Witch2.add("Pale_helmsmanship");
            Witch2.add("Pale_target_analysis");
            Witch2.add("Pale_systems_expertise");
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
            this.officer.setId("WitchDustEX");
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("intel", "WPN1"));
            this.officer.setName(new FullName("Nizer", "Dust", FullName.Gender.FEMALE));
            this.player_fleet.getFleetData().addOfficer(this.officer);
        }

    }
}
