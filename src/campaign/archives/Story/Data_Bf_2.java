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
public class Data_Bf_2 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("You did it... something even I couldn't accomplish...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("And surely... you already know everything...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Both my true identity, and the warnings they gave you in combat...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Ashes to ashes... dust to dust...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("All mistakes will finally face their moment of correction...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Now... please accept this true... Pale Contract, my... no, my friend.", WthC_ColorData.B_WHITE, pad);

        tooltip.addPara("......", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Thank you, 'Lord'...", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("Or should I say... Pale King... Lovenia Blast.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("Indeed... has our game finally reached a beautiful ending?", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("At least that's what you think... right? 'Chosen One' from Perseus...?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Heh... very well, at least for now... enjoy your well-earned victory...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Perhaps centuries later... another unfortunate soul will appear...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("To embark on that destined contract journey under a demon's invitation...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Of course... if you're willing...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("I have one final gift I'd like to give you...", WthC_ColorData.LIGHT_CRIMSON, padS);

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


        Global.getSector().getPlayerStats().setSkillLevel("Contract", 2.0F);


        CoreReputationPlugin.CustomRepImpact impact = new CoreReputationPlugin.CustomRepImpact();
        impact.delta = 0.05f;
        CoreReputationPlugin.CustomRepImpact impact2 = new CoreReputationPlugin.CustomRepImpact();
        impact2.delta = 0.15f;
        ReputationActionResponsePlugin.ReputationAdjustmentResult result = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact, null, null, true), WthC_Citadel.getHILLYA());
        ReputationActionResponsePlugin.ReputationAdjustmentResult result2 = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact2, null, null, true), WthC_Citadel.getROMENA());

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "As a wave of comfort washes over you... you feel some indescribable changes happening to your body.");
    }
}
