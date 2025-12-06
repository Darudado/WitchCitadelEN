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
public class Data_SSoul_4 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("Familiar days reappear once again...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("All that was lost shall return once more...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Perhaps one day you will sincerely wish...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("That destined death would be the end of everything...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Though these challenges have grown increasingly difficult, you still achieved final victory.", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("However... unexpectedly...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("This time, before you could perform any recovery, the light emanating from this terminal has already begun to dim...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Your next opponent is a lovely child...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Just as lovely as all your previous opponents...", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Hehe... I wonder how she would introduce this little girl who has such deep connections with herself to you? How interesting...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("I must say, this is your first time seeing these terminals being relit...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("But clearly, these messages were written by another person.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("After all, their tone is notably more casual, and they've thoughtfully changed the font color to a vibrant crimson...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Who knows what Mr. 'Solitary' just went through...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("What if I told you she just turned into several fragments that cannot be reassembled?", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);

        tooltip.addPara("Hehehe... I can already guess your expression now.", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Don't worry... it's just a joke~ You'll get used to this...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Besides, even if that one actually shattered into pieces, she'd be fine. It's impressive she could think of such a way to communicate with you, how interesting.", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("......", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Now you completely understand the true face of this uninvited guest hiding behind the text", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("After all, this was the same tone when you first tried to accept these death games.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("However... perhaps even in this one's hands, the 'she' in the text still won't lie?", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Hehehe... Let this long-awaited greeting end here. I look forward to you completing the next invitation from the Crimson Lord...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Then I'll have a proper chat with you, 'Space Traveler' from Perseus?", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("What an ill-natured fellow...", WthC_ColorData.CROSS_GARY_D, padS);
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
        impact.delta = 0.1f;
        ReputationActionResponsePlugin.ReputationAdjustmentResult result = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact, null, null, true), WthC_Citadel.getHILLYA());

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You gained 1 story point... Challenge the next Crimson Invitation to get more clues");
    }
}
