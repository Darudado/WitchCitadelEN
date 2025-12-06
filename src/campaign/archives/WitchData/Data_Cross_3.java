package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// 从双面神等道具缝合而来
public class Data_Cross_3 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Detailed Information of the Target Individual:", WthC_ColorData.CROSS_GARY_D, pad);

        tooltip.addPara("Once, in the so-called holy city of the world's foremost religion, there was a nun of utmost kindness and piety. Orphaned and alone, she moved the world with her sincerity and faith, becoming an irreplaceable spiritual anchor for the lost souls in every street and alley. In any fairy tale, a being of excellent character and inherent goodness would surely be rewarded with a happy ending, and the gods would surely bestow blessings to punish those vile individuals who defiled this goodwill from the shadows.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Unfortunately, perhaps there were no true gods in that world, and it was destined that only the vile would have the power to write such fairy tales. Thus, the poor nun was imprisoned in a grandiose hall of sin, where she would be subjected to daily defilement of both body and soul... until she accepted an invitation from the devil, or... became the devil herself.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("The Cross Witch, Rosalia Cross, is the most significant theological master in Earth's history, bar none. Even though her presence is rarely seen in Crimson City, she remains one of the few who has made immense contributions to her fellow witches. During the complex religious conflicts of humanity's long medieval period, she not only amassed incalculable wealth from humans but also successfully introduced the mystical concept of the 'Saint' to the masses. Although, after excessive religious embellishment, the term has become as illusory as those gods and buddhas...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("For mortals, directly worshipping this tangible saint might even be a more popular choice than believing in those elusive deities. Of course, Rosalia, long disillusioned with religion, is not as holy as she seems. She has always been a witch, a being who betrayed both humanity and the gods. In essence and appearance, she is closer to what religious doctrines would call a demon.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Even from an omniscient perspective, having a witch like her secretly control the faith of all human society is utterly horrifying. Moreover, every witch has been tainted by the Crimson Demon, and knowing the truth of the world, they are all completely disillusioned with religion itself. It can be said... this saint has no faith whatsoever, and her activities in the human world seem more like a sacrilegious act driven by her dark, twisted sense of humor.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Yet, interestingly, even though the Cross Witch herself harbors no goodwill, her spread of religion across the world has indeed brought real redemption and hope to many humans. After all, during humanity's dark ages, Rosalia's preaching filled the hearts of many who were otherwise, giving them the courage to unite and survive.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("With her immense mental interference abilities and psychological expertise, the Cross Witch can always achieve what human lords or religious leaders dream of—waving her hand or speaking a word to make everyone around her unconditionally devote themselves to her. Thus, in any religious war involving only humans, as long as she explicitly supports one side, the outcome is already decided. At least, no historical record shows even a trace of her defeat.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("What's even more fascinating is that Rosalia, who knows nothing about tactics, is exceptionally skilled at handling troublesome soldiers. For instance, ensuring that most soldiers die 'honorably' on the battlefield has always been one of her specialties... As a result, wars involving the Cross Witch often result in high casualty rates on both sides. More often than not, her support for one side's victory comes at the cost of several times more deaths than the opponent... But so what? The expressions on those soldiers' faces as they march to their deaths are filled with happiness... It is the glorious death they have always yearned for.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("She has always been a witch, a being who betrayed all of humanity and the so-called gods. In essence and appearance, she is closer to what religious doctrines would call a demon.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Or perhaps even the concept of evil in people's hearts originates from this true dark evangelist...", WthC_ColorData.CROSS_GARY_D, pad);
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

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Congratulations on spending 10 million for a piece of data... Keep up the good work, little genius. Here's a Story Point for your trouble~");
    }
}
