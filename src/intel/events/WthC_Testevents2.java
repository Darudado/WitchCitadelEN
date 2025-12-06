package data.intel.events;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.TextPanelAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.FullName.Gender;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.DebugFlags;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.BarEventManager;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.BaseBarEventWithPerson;
import com.fs.starfarer.api.impl.campaign.rulecmd.AddRemoveCommodity;
import data.util.I18nUtil;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// 从幻想工造军官酒馆事件修改
// 已不再生效
public class WthC_Testevents2 extends BaseBarEventWithPerson {

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("event", "WthC_");
    protected CampaignFleetAPI player_fleet;
    protected PersonAPI officer;
    protected OfficerDataAPI officer_data;
    protected WthC_Util I18nSection;
    public static String NeizerKey = "$WthC_TestCompleted";

    public WthC_Testevents2() {
    }

    public boolean shouldShowAtMarket(MarketAPI market) {
        if (!super.shouldShowAtMarket(market)) {
            return false;
        } else if (!market.getFactionId().equals("Crimson_citadel")) {
            return false;
        } else {
            return Global.getSector().getPlayerStats().getLevel() >= 0 || DebugFlags.BAR_DEBUG;
        }
    }

    protected void regen(MarketAPI market) {
        if (this.market != market) {
            super.regen(market);
            this.person.setPortraitSprite(Global.getSettings().getSpriteName("intel", "WN1"));
            this.person.setName(new FullName("???", "???", Gender.FEMALE));
        }
    }

    public void addPromptAndOption(InteractionDialogAPI dialog, Map<String, MemoryAPI> memoryMap) {
        super.addPromptAndOption(dialog, memoryMap);
        this.regen(dialog.getInteractionTarget().getMarket());
        TextPanelAPI text = dialog.getTextPanel();
        text.addPara(strings.get("Test_Event_1"));
        Color R = WthC_ColorData.DUST_RED;
        dialog.getOptionPanel().addOption(strings.get("Test_Event_1"), this, R, (String)null);
    }

    public void init(InteractionDialogAPI dialog, Map<String, MemoryAPI> memoryMap) {
        super.init(dialog, memoryMap);
        this.player_fleet = Global.getSector().getPlayerFleet();
        this.done = false;
        this.optionSelected((String)null, WthC_Testevents2.OptionId.FINAL);
    }

    public void optionSelected(String optionText, Object optionData) {
        if (optionData instanceof OptionId) {
            OptionId option = (OptionId)optionData;
            OptionPanelAPI options = this.dialog.getOptionPanel();
            TextPanelAPI text = this.dialog.getTextPanel();
            options.clearOptions();
            switch (option) {
                case FINAL:
                    BarEventManager.getInstance().notifyWasInteractedWith(this);
                    this.person.setName(new FullName("奈泽尔", "尘埃", Gender.FEMALE));
                    text.addPara(strings.get("Test_Event_2"));
                    text.addPara(strings.get("Test_Event_3"));
                    text.addPara(strings.get("Test_Event_4"));
                    options.addOption(I18nUtil.getString("event", "WthC_Test_Event_5"), WthC_Testevents2.OptionId.LEAVE);
                    List<String> Rika = new ArrayList();
                    Rika.add("SENetLink");
                    Rika.add("WitchDust");
                    Rika.add("helmsmanship");
                    Rika.add("target_analysis");
                    Rika.add("ballistic_mastery");
                    Rika.add("systems_expertise");
                    Rika.add("energy_weapon_mastery");
                    Rika.add("ordnance_expert");
                    if (!Global.getSector().getMemoryWithoutUpdate().contains(NeizerKey)) {
                        Global.getSector().getMemoryWithoutUpdate().set(NeizerKey, true);
                    }

                    this.officer = Global.getFactory().createPerson();
                    this.officer_data = Global.getFactory().createOfficerData(this.officer);
                    Iterator var7 = Rika.iterator();

                    while(var7.hasNext()) {
                        String SENetLink = (String)var7.next();
                        this.officer.getStats().setSkillLevel(SENetLink, 3.0F);
                    }


                    this.officer.getStats().setLevel(7);
                    this.officer.setPersonality("aggressive");
                    this.officer.setName(this.person.getName());
                    this.officer.setPortraitSprite(this.person.getPortraitSprite());
                    this.officer.setGender(this.person.getGender());
                    this.officer.setRankId("spaceCommander");
                    this.officer.setPostId("hacker");
                    this.player_fleet.getFleetData().addOfficer(this.officer);
                    AddRemoveCommodity.addOfficerGainText(this.officer, text);
                    break;
                case LEAVE:
                    this.noContinue = true;
                    this.done = true;
            }

        }
    }

    protected String getPersonFaction() {
        return "Crimson_citadel";
    }

    protected String getPersonRank() {
        return Ranks.SPACE_SAILOR;
    }

    protected String getPersonPost() {
        return Ranks.CITIZEN;
    }

    protected String getPersonPortrait() {
        return null;
    }

    protected Gender getPersonGender() {
        return Gender.ANY;
    }

    public static enum OptionId {

        FINAL,
        LEAVE;

        private OptionId() {
        }
    }
}
