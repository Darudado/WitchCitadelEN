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
public class Data_Bd_2 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("Destruction... is as important as creation, the two are destined to coexist, since there is a beginning there must be a final end.", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Those who try to reverse natural laws will ultimately pay the price...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("No matter how long it takes to repay this blasphemy...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Alright... alright...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Honestly, you're used to ignoring these dialogues now, but couldn't they show even a little sympathy for someone who keeps struggling back from death's door?", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("At least since long ago, you've already regretted accepting that initial Crimson invitation.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Not to mention... you're sure an even more unbearable one is about to appear...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("So you still haven't forgiven her after all this time... Pale Ruler?", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("She only chased you across multiple void realms for a few thousand years? Isn't that a form of love?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Perfect entrance...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Only in this regard do they never disappoint... It's hard to imagine what kind of vast divine power it takes to develop such an unrestrained personality...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("I suppose you don't know that history either... Perseus person?", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("If you want to know what happened between them, why not have a chat with that equally mysterious witch lady...", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("If you ask me... they could have been good friends, after all they're so similar in many ways... is it really just about appearances...?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("That's why I created that virtual image~ I hope you'll be satisfied with it~", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("I only have bad feelings about this...", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("I concur...", WthC_ColorData.B_WHITE, pad);

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
