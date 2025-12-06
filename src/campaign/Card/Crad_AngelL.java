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
public class Crad_AngelL extends BaseSpecialItemPlugin{

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

        tooltip.addPara("Do you also fancy those elegant maids around you? Hehe... don't even think about it, they belong to me~", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Of course... if you're willing, I can arrange for some men to join your fleet...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("After paying the corresponding price, a %s %s will serve as an officer under your command...", pad, WthC_ColorData.HIGH_BLUE, new String[]{"relatively ordinary", "winged one"});
        tooltip.addPara("These %s are %s but %s, making them more suitable for commanding %s ships...", padS, WthC_ColorData.HIGH_BLUE, new String[]{"shrewd individuals", "highly intelligent", "relatively fragile", "shield-focused and long-range"});

        tooltip.addPara("If you desire more adorable and powerful children... please continue to fulfill your contract... \"Chosen One.\"", WthC_ColorData.LIGHT_CRIMSON, padS);
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
        Witch.add("ordnance_expert");
        Witch.add("target_analysis");
        Witch.add("missile_specialization");
        Iterator var7 = Witch.iterator();

        List<String> Witch2 = new ArrayList();
        Witch2.add("field_modulation");
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
        this.officer.setPersonality("precise");
        this.officer.setRankId("spaceCommander");
        this.officer.setPostId("spacer");
        float RandomNumber1 = MathUtils.getRandomNumberInRange(0F, 100F);
        if(RandomNumber1 <= 50){
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "ALN1"));
            this.officer.setName(new FullName("Winged One", "", FullName.Gender.MALE));
            this.officer.setGender(FullName.Gender.MALE);
        } else{
            this.officer.setPortraitSprite(Global.getSettings().getSpriteName("officers", "ALN2"));
            this.officer.setName(new FullName("Winged One", "", FullName.Gender.MALE));
            this.officer.setGender(FullName.Gender.MALE);
        }


        this.player_fleet.getFleetData().addOfficer(this.officer);


        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Contract confirmed... An envoy from Crimson City will assist you in commanding the battle.");
    }
}
