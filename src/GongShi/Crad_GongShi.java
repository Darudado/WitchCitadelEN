package data.GongShi;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;


import java.util.*;

// Stitched together from Fantasy Workshop's officer tavern event and Janus items
public class Crad_GongShi extends BaseSpecialItemPlugin{

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

        tooltip.addPara("Do you have a formula like this?", WthC_ColorData.MYSTERIOUS_PURPLE, padS);
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
        String args = null;
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_DM", "special_items"), 10.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_AL", "special_items"), 10.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Crew", "special_items"), 1000.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Supplies", "special_items"), 1000.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Fuel", "special_items"), 1000.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_G", "special_items"), 1.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_S", "special_items"), 1.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_A", "special_items"), 1.0F);
        Global.getSector().getPlayerFleet().getCargo().getCredits().add(99999999);
        Global.getSector().getPlayerFleet().getCargo().addCommodity("crew",2000f);
        Global.getSector().getPlayerStats().addStoryPoints(1000);
        Global.getSector().getPlayerStats().addXP(100000000);
        for (int i = 0; i < 15; i++)
        {
            FleetMemberAPI member = Global.getFactory().createFleetMember(FleetMemberType.SHIP, "monitor_IE");
            Global.getSector().getPlayerFleet().getFleetData().addFleetMember(member);
        }
        for (int i = 0; i < 4; i++)
        {
            FleetMemberAPI member2 = Global.getFactory().createFleetMember(FleetMemberType.SHIP, "brawler_tritachyon_IE");
            Global.getSector().getPlayerFleet().getFleetData().addFleetMember(member2);
        }
        FleetMemberAPI member3 = Global.getFactory().createFleetMember(FleetMemberType.SHIP, "atlas_Standard");
        Global.getSector().getPlayerFleet().getFleetData().addFleetMember(member3);

        final List<String> unlocked = new ArrayList<>();
        final CharacterDataAPI player = Global.getSector().getCharacterData();
        for (HullModSpecAPI spec : Global.getSettings().getAllHullModSpecs())
        {
            if (!spec.isHidden() && !spec.isAlwaysUnlocked()
                    && !player.knowsHullMod(spec.getId()))
            {
                player.addHullMod(spec.getId());
                unlocked.add(spec.getDisplayName());
            }
        }

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "The game is about the journey, the formula is just for fun.");
    }
}
