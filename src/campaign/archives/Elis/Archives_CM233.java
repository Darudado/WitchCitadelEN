package data.campaign.archives.Elis;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.ReputationActionResponsePlugin;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.impl.campaign.CoreReputationPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.world.systems.WthC_Citadel;


// Stitched together from items like the Two-Faced God
public class Archives_CM233 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Or perhaps... you would prefer these games (laughs)", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Of course... we all enjoy fair competition, don't we?", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("(Purchase and right-click this information to unlock numerous themed challenge mission branches)", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("(Right-clicking this item won't immediately unlock any side missions, subsequent sub-items must be used)", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("(Most of these missions come from other player submissions and have no direct connection to the main missions.)", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("(They are mostly purely for entertainment, either extremely difficult or outside game rules, but we hope you enjoy them.)", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("(Though I suspect you might end up in jail, as they say, it's about the participation.)", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("(If you're interested, you can also create such a mission yourself. Check the related information in the mod's root directory.)", WthC_ColorData.MID_WHITE, padS);
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
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_CM233_Liao", "special_items"), 1.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_CM233_ROTK", "special_items"), 1.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_CM233_Sudan", "special_items"), 1.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_CM233_SZB", "special_items"), 1.0F);
        //Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_CM233_YKM", "special_items"), 1.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_CM233_Zako", "special_items"), 1.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_CM233_ZuanShi", "special_items"), 1.0F);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_CM233_YuanXingZhe", "special_items"), 1.0F);
        CoreReputationPlugin.CustomRepImpact impact = new CoreReputationPlugin.CustomRepImpact();
        impact.delta = 0.1f;
        ReputationActionResponsePlugin.ReputationAdjustmentResult result = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact, null, null, true), WthC_Citadel.getHILLYA());

    }
}
