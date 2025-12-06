package data.campaign.archives.Story;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.ReputationActionResponsePlugin;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.impl.campaign.CoreReputationPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import data.world.systems.WthC_Citadel;

// 从双面神等道具缝合而来
public class Data_Be_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Information recorded on the terminal:", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("When the final beast falls with the collapsing earth, the divine offspring shall also be freed from their supposed destiny...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Yet even when the hunt is done, the hunters never fade...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("It is in these past days... that lie buried the reason I must return...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Though you want to question how she manages to spout these lines every time...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("But these extreme emotional swings have surely numbed anyone by now...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("To be fair... isn't she actually the cruelest one to you? That pale one?", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Does being cute warrant unconditional forgiveness? Can our values really be twisted so casually?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("......", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("......", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Well, isn't anyone going to object...?", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("......", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("......", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("......", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Well... anyway, our game is about to reach its final curtain...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("I can assure you... this will be a grand finale...", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("You may face many difficulties... you may completely doubt yourself...", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("But remember... you have countless chances to try again... chosen one...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Perhaps...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("This time you will shatter that eternally burning star with your own hands...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("The most brilliant of all mistakes I have made...", WthC_ColorData.B_WHITE, padS);

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

        Global.getSector().getPlayerStats().addStoryPoints(1);

        CoreReputationPlugin.CustomRepImpact impact = new CoreReputationPlugin.CustomRepImpact();
        impact.delta = 0.05f;
        CoreReputationPlugin.CustomRepImpact impact2 = new CoreReputationPlugin.CustomRepImpact();
        impact2.delta = 0.15f;
        ReputationActionResponsePlugin.ReputationAdjustmentResult result = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact, null, null, true), WthC_Citadel.getHILLYA());
        ReputationActionResponsePlugin.ReputationAdjustmentResult result2 = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact2, null, null, true), WthC_Citadel.getROMENA());

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You gained 1 Story Point... Challenge the next Pale Contract to obtain more clues");
    }
}
