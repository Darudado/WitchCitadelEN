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
public class Data_Dust_4 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("Looks like you've succeeded again~ As expected of one chosen by me...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Well... I admit I knew the outcome already, just wanted to play along with you.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("After all, you possess potential more special than anyone else in this sector... don't you?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("In comparison, these fragile phantoms are so insignificant... perhaps it's time to let you meet the real witches.", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("It seems this is finally over.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Or rather, even though they always deliver all rewards as promised, you have little desire to continue these antics with the Crimson Lord...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Unlike that mysterious one who called themselves 'Lord'... this one is truly a master of twisted humor.", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Haha... I understand your feelings, every human before me was the same, even they were no exception at first.", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Surely only after you embrace eternity yourself... will you gradually understand all this seeming nonsense.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("After all... even the oldest stars in human imagination must seem young to me.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("They remain as pure as ever, lying quietly in their void-woven cradle, humming that dirge from birth to end.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Perhaps witnessing and recording their complete lives is my only purpose - those stars in their final stage before rest... someone must place their coffin lid, declaring the end of a cycle...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Suddenly feels heavy...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Seems they at least have some literary flair, though essentially just as cryptic as the previous one...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Of course, true immortality isn't as painful as those dystopian stories describe, it's just that you always need to find ways to spend that endless time...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("That's the only reason why I always lure outlanders to seek the void castle, after all, the life experiences of 'unfortunate ones' are always quite interesting in any sector...", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Though... even if this invitation is just a game for you and me, don't forget, others have witnessed your activities...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Go check the archives on that ship... you've actually had access to it for a while now.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("The past mistakes she forged await your confrontation... and they await the day to truly acknowledge an outsider...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("So... this isn't completely over?", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("Rather... it's only just beginning...", WthC_ColorData.B_WHITE, pad);

        tooltip.addPara("......!", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("It seems this final terminal has also lost function.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("But you know well who that last message was from.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Your instincts also tell you this won't end so simply...", WthC_ColorData.CROSS_GARY_D, padS);
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

        Global.getSector().getPlayerStats().addStoryPoints(5);

        CoreReputationPlugin.CustomRepImpact impact = new CoreReputationPlugin.CustomRepImpact();
        impact.delta = 0.1f;
        ReputationActionResponsePlugin.ReputationAdjustmentResult result = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact, null, null, true), WthC_Citadel.getHILLYA());

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You gained 5 Story Points...");
    }
}
