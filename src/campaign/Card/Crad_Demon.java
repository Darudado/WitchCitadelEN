package data.campaign.Card;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.ReputationActionResponsePlugin;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.CoreReputationPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import data.world.systems.WthC_Citadel;
import org.lazywizard.lazylib.MathUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 从幻想工造的军官酒馆事件和双面神等道具缝合而来
public class Crad_Demon extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");
    protected CampaignFleetAPI player_fleet;
    protected PersonAPI officer;
    protected OfficerDataAPI officer_data;

    int C1 = 0;
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

        tooltip.addPara(strings.get("Card2"), WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("When you pay the corresponding price, a %s %s will join your fleet as an officer...", pad, WthC_ColorData.HIGH_BLUE, new String[]{"contract-bound", "elite Crimson Demon"});
        tooltip.addPara("These %s always %s regardless of danger, making them more suitable for commanding %s ships...", padS, WthC_ColorData.HIGH_BLUE, new String[]{"wild ones", "charge into the enemy", "armor-focused"});

        tooltip.addPara("They are genuine elites... and you don't need to worry about the contract duration... it will always outlast your lifespan.", WthC_ColorData.MYSTERIOUS_PURPLE, padS);
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
        Witch.add("impact_mitigation");
        Witch.add("polarized_armor");
        Iterator var7 = Witch.iterator();

        List<String> Witch2 = new ArrayList();
        Witch2.add("ordnance_expert");
        Witch2.add("systems_expertise");
        Witch2.add("damage_control");
        Witch2.add("combat_endurance");
        Witch2.add("CrimsonContract");
        Iterator var8 = Witch2.iterator();

        while(var7.hasNext()) {
            String SENetLink = (String)var7.next();
            this.officer.getStats().setSkillLevel(SENetLink, 1.0F);
        }
        while(var8.hasNext()) {
            String SENetLink2 = (String)var8.next();
            this.officer.getStats().setSkillLevel(SENetLink2, 2.0F);
        }

        this.officer.getStats().setLevel(7);
        this.officer.setPersonality("heroic");
        this.officer.setRankId("spaceCommander");
        this.officer.setPostId("spacer");

        if(C1 == 0){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DM1"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1++;
        } else if(C1 == 1){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DMB1"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1++;
        } else if(C1 == 2){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DM2"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1++;
        } else if(C1 == 3){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DMB2"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1++;
        } else if(C1 == 4){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DM3"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1++;
        } else if(C1 == 5){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DMB3"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1++;
        } else if(C1 == 6){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DM4"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1++;
        } else if(C1 == 7){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DMB4"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1++;
        } else if(C1 == 8){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DM5"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1++;
        } else if(C1 == 9){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DMB5"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1++;
        } else{
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DM6"));
            this.officer.setName(new FullName("Crimson Demon", "Contract Wraith", FullName.Gender.FEMALE));
            this.officer.setGender(FullName.Gender.FEMALE);
            C1 = 0;
        }


        this.player_fleet.getFleetData().addOfficer(this.officer);


        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Contract confirmed... An envoy from Crimson Citadel will assist you in commanding the battle.");
    }
}
