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
public class Data_RRA_3 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Detailed information on the target individual:", WthC_ColorData.DEEP_BLUE, pad);

        tooltip.addPara("A grandiose carriage, its destination a palace intertwined with conspiracy and power, with a trail of desperate bandits behind. Will she accept her fate and become a consort under the escort of soldiers, or will she resist and end her life before that? Of course... please don't flatter yourself, the choice was never in your hands. Those chosen can never escape the crimson fate.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The girl once named Yue was born in a small village in eastern Asia on Earth. After an uneventful childhood, at the tender age of thirteen, she was forced onto a path to the imperial harem due to her prematurely evident beauty. This should have been a common story for that era, until she encountered an unexpected guest on the way, twisting the trajectory of her life entirely away from human society...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("After the chaotic bandit conflict subsided, the girl once named Yue was rescued by Rika, who had not yet become the Primordial Witch. Despite the cultural differences spanning several continents, the two initially couldn't even communicate verbally. Forced to leave her homeland, she was utterly despondent and went through a period of listlessness. However, perhaps due to her natural affinity, with the help of Rika, who acted as an elder sister, she gradually emerged from the shadows and adapted to life in Europe.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("As she gradually became accustomed to Western life, the girl once named Yue became the face of a jewelry shop opened by Rika. Although Rika initially wanted to keep the adorable child by her side as a personal maid, she slowly discovered the girl's astonishing artistic talent. She never imagined that the gemstones she had collected since childhood could be carved into such beautiful shapes. Thus, the idea of transforming her into the witch Youyue began to take root...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("In truth, YouYu's transformation into a witch was entirely a serendipitous process driven by the interference of the Primordial Witch, with no intention on her part. Long before the two became witches, they had been close friends for several years. Eternal life is a significant symbol of transcending human identity, but an eternity spent alone is the loneliest existence in the world.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Thus, under certain influences, the Primordial Witch Rika, destined to shed her human identity, selfishly dragged her closest confidante into the fold, even though the latter had no supernatural talents whatsoever. This is why Youyu essentially knows no so-called magic.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("The Enchanting Art Witch, YouYu, is the most delicate among all the council witches. While the others have, to varying degrees, transcended the mundane world around them, the Enchanting Art Witch is more like a human bearing the title of a witch. But in the end, the concept of a witch has never... and never needed a clear definition. Whether you achieve eternal life through some means, know a few small spells... or simply possess some unique talent, as long as you are recognized by certain individuals, you can claim this identity.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Even as a witch, YouYu's physical structure remains consistent with that of a human, with only a small portion of her bodily functions replaced by magical energy. Thus, Youyu is the most human-like among the witches. Although Youyu does not age naturally and her internal organs do not gradually fail, she has made almost no progress in other aspects. Her strength and speed remain within the limits of what a human can achieve.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("No matter how lacking she is in supernatural power, for this fragile young girl, eloquence, beauty, and an understanding of art are her own brand of witchcraft.", WthC_ColorData.DEEP_BLUE, pad);
        tooltip.addPara("But perhaps, she is merely a gentle, somewhat dazed victim... and nothing more?", WthC_ColorData.DEEP_BLUE, pad);
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
