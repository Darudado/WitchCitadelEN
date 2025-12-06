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
public class Data_Elf_4 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("Congratulations~ You're once again the winner of this game!", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Though I knew you would win all along~", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("But often the journey is more interesting than the destined outcome, isn't it?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("So... can you explain something this time?", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Surely anyone sane wouldn't be interested in these messages right after a death battle...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Have to say... you're starting to miss the mysterious Professor Lo...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Haha... very well, as you wish.", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("You know who I am... people usually call me the Crimson Lord, as you're familiar with.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("I'm sure you can guess the source behind these Crimson Invitations...", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("So... before questioning your patron who's given you so many resources, shouldn't you express some gratitude first? 'Little Unfortunate One'?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Honestly, no one wants their long-time sponsor to be so frivolous and twisted...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Not to mention one wealthy enough to rebuild a fleet anytime just to support their pranks...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Why don't they just conquer this sector completely?", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Heh, well~ Someone like you who's spent half their life scrambling for a living wouldn't understand...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("What a persistent enemy 'boredom' can be across those long eternal years...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Eternal? Does anyone really use that word to describe themselves?", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("Ha... that means everyone you've met has been quite modest, considering whether you stuff yourself in a cryo unit or copy your personality into a little box, both could be called eternal in a way.", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("But obviously... I'm neither a canned genius nor a projection made by some AI core, and my body temperature is higher than yours. Yet I've lived through an unimaginably long time, and will undoubtedly continue to do so.", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("So these invitations were really just pranks after all...?", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("You could see it that way~", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Though you should have known this was a game before accepting the first invitation~", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("I've always explained it to you this way~ haven't I?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("However... perhaps I wasn't the one who started all this?", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Right, Lovenia? Or should I say... the one who still likes to call themselves 'Lord'...? Hehehe~ What era do you think this is?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Anyway... before you now lies the final curtain call symbolizing the end.", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Please bring me more entertainment... after all, by now you have no reason to refuse.", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("At least not all Perseus faction leaders are this strange...", WthC_ColorData.CROSS_GARY_D, padS);

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
                "You gained 1 Story Point... Challenge the next Crimson Invitation to obtain more clues");
    }
}
