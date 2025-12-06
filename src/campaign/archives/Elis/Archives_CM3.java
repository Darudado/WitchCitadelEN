package data.campaign.archives.Elis;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_CM3 extends BaseSpecialItemPlugin{

    protected CampaignFleetAPI player_fleet;

    @Override
    public String getDesignType() {
        return null;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, CargoTransferHandlerAPI transferHandler, Object stackSource) {
        float pad = 10f;
        float padEX = 20f;

        tooltip.addTitle(getName());

        String design = getDesignType();

        if (design != null) {
            Misc.addDesignTypePara(tooltip, design, 10f);
        }

        if (!spec.getDesc().isEmpty()) {
            tooltip.addPara(spec.getDesc(), Misc.getTextColor(), pad);
        }


        tooltip.addPara("Hello... Perseus inhabitant from another starry sky.", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Tell me... are you satisfied with this game that nears its end...", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Now... the past that once had no connection to this sector lies before your eyes...", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("So... who else could share with you these mysteries hidden beneath the facade of stars?", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Congratulations... you have merged with this unknown, becoming part of it...", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Or perhaps... from the moment fate's branches began to extend, you were already there...", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("This won't make any difference, will it?", WthC_ColorData.MID_WHITE, padEX);
        tooltip.addPara("But it's time to bring this to a close...", WthC_ColorData.MID_WHITE, pad);


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

        FleetMemberAPI member = Global.getFactory().createFleetMember(FleetMemberType.SHIP, "WthC_PaleCrown_始与终");
        Global.getSector().getPlayerFleet().getFleetData().addFleetMember(member);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Another legend reaches its end... and that ship awaits you at the edge of sight.");
    }
}
