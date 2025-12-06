package data.GongShi;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import org.lazywizard.console.BaseCommand;
import org.lazywizard.console.CommandUtils;
import org.lazywizard.console.Console;
import org.lazywizard.console.commands.Storage;

import java.util.LinkedHashSet;
import java.util.Set;

// Stitched together from Fantasy Workshop's officer tavern event and Janus items
public class Crad_S extends BaseSpecialItemPlugin{

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
        FleetDataAPI target;
        String targetName;
        int total = 0;

        if (args == null || args.isEmpty())
        {
            target = Storage.retrieveStorageFleetData();
            targetName = "storage (use 'storage' to retrieve)";
        }
        else if ("player".equalsIgnoreCase(args))
        {
            target = Global.getSector().getPlayerFleet().getFleetData();
            targetName = "player fleet";
        }
        else
        {
            SectorEntityToken token = CommandUtils.findTokenInLocation(args,
                    Global.getSector().getCurrentLocation());

            if (token == null)
            {
                Console.showMessage(args + " not found!");
            }

            if (token instanceof FleetMemberAPI)
            {
                target = ((FleetMemberAPI) token).getFleetData();
            }
            else
            {
                CargoAPI cargo = CommandUtils.getUsableCargo(token);
                if (cargo.getMothballedShips() == null)
                {
                    cargo.initMothballedShips(token.getFaction().getId());
                }

                target = cargo.getMothballedShips();
            }

            targetName = token.getFullName();
        }

        final Set<String> ids = new LinkedHashSet<>(Global.getSector().getAllEmptyVariantIds());
        for (FleetMemberAPI tmp : target.getMembersListCopy())
        {
            if (!tmp.isFighterWing() && tmp.getVariant().isEmptyHullVariant())
            {
                ids.remove(tmp.getVariant().getHullVariantId());
            }
        }

        for (String id : ids)
        {
            FleetMemberAPI tmp = Global.getFactory().createFleetMember(
                    FleetMemberType.SHIP, id);
            tmp.getRepairTracker().setMothballed(true);
            target.addFleetMember(tmp);
            total++;
        }




        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "The game is about the journey, the formula is just for fun.");
    }
}
