package data.campaign.Card;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import org.lazywizard.lazylib.MathUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 从幻想工造的军官酒馆事件和双面神等道具缝合而来
public class Crad_DemonL extends BaseSpecialItemPlugin{

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

        tooltip.addPara("I wonder if you also fancy those elegant maids around? Hehe... Don't even think about it, they belong to me~", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Of course... if you're willing, I can have some men join your fleet...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("When you pay the corresponding price, a %s %s will serve as an officer under your command...", pad, WthC_ColorData.HIGH_BLUE, new String[]{"relatively ordinary", "Crimson Demon"});
        tooltip.addPara("These %s always %s regardless of danger, making them more suitable for commanding %s ships...", padS, WthC_ColorData.HIGH_BLUE, new String[]{"wild ones", "charge into the enemy", "armor-focused"});

        tooltip.addPara("If you desire some cuter and more powerful children... continue to fulfill your contract, \"Chosen One.\"", WthC_ColorData.LIGHT_CRIMSON, padS);
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
        Witch.add("polarized_armor");
        Witch.add("impact_mitigation");
        Witch.add("damage_control");
        Iterator var7 = Witch.iterator();

        List<String> Witch2 = new ArrayList();
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

        this.officer.getStats().setLevel(5);
        this.officer.setPersonality("heroic");
        this.officer.setRankId("spaceCommander");
        this.officer.setPostId("spacer");
        float RandomNumber1 = MathUtils.getRandomNumberInRange(0F, 100F);
        if(RandomNumber1 <= 33){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DMN1"));
            this.officer.setName(new FullName("Crimson Demon", "", FullName.Gender.MALE));
            this.officer.setGender(FullName.Gender.MALE);
        } else if(RandomNumber1 < 66){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DMN2"));
            this.officer.setName(new FullName("Crimson Demon", "", FullName.Gender.MALE));
            this.officer.setGender(FullName.Gender.MALE);
        } else{
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "DMN3"));
            this.officer.setName(new FullName("Crimson Demon", "", FullName.Gender.MALE));
            this.officer.setGender(FullName.Gender.MALE);
        }


        this.player_fleet.getFleetData().addOfficer(this.officer);


        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Contract confirmed... An envoy from Crimson City will assist you in commanding the battle.");
    }
}
