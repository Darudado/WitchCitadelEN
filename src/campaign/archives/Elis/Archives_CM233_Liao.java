package data.campaign.archives.Elis;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;


// Stitched together from items like the Two-Faced God
public class Archives_CM233_Liao extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");
    protected CampaignFleetAPI player_fleet;

    @Override
    public String getDesignType() {
        return null;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, CargoTransferHandlerAPI transferHandler, Object stackSource) {
        float pad = 10f;
        float padS = 2f;

        tooltip.addTitle(getName());

        String design = getDesignType();

        if (design != null) {
            Misc.addDesignTypePara(tooltip, design, 10f);
        }

        if (!spec.getDesc().isEmpty()) {
            tooltip.addPara(spec.getDesc(), Misc.getTextColor(), pad);
        }


        tooltip.addPara("Mission Name: %s ", pad, WthC_ColorData.MYSTERIOUS_PURPLE, "Classical Love");

        tooltip.addPara("Creator: %s ", pad, WthC_ColorData.HIGH_BLUE, "Scythe");
        tooltip.addPara("Difficulty: %s ", padS, WthC_ColorData.HIGH_BLUE, "Cheat Level");
        tooltip.addPara("Challenge Type: %s ", padS, WthC_ColorData.HIGH_BLUE, "Late-game Multi-mod Mixed Invincible Fleet");
        tooltip.addPara("Style: %s ", padS, WthC_ColorData.HIGH_BLUE, "Vanilla + Witch's unrestricted loadouts, with high-intensity skills and overall pressure.");
        tooltip.addPara("Ship data unchanged, but uses some super OP loadouts and vanilla-restricted hullmods.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("Description: %s ", padS, WthC_ColorData.HIGH_BLUE, "After beating this, you and your fleet are probably truly invincible in the entire game.");
        tooltip.addPara("Unless you have special preferences, it's not recommended to challenge this series of missions, they're purely designed to be frustrating.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("Or maybe consider it a high-end Western-style testing platform?", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("(Right-click this item to unlock the corresponding mission)", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("(You can find them in the Bounty Board section of the Intel menu (E))", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("(Due to the large number of missions, it's not recommended to unlock multiple series at once)", WthC_ColorData.MID_WHITE, padS);

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

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Heh... let the show begin.");
        Global.getSector().getMemoryWithoutUpdate().set("$DRON",true);
    }
}
