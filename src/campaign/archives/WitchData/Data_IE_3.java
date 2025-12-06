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
public class Data_IE_3 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Detailed introduction of the target individual:", WthC_ColorData.IE_WHITE, pad);

        tooltip.addPara("Even if she was not born in a glamorous manor, it does not mean that a dreamy girl cannot possess a noble heart. She was once so naive and innocent, clutching a tattered tome of mysticism and a few transparent stones she found by the river, attempting to use her half-baked knowledge to make a grand impact on the world around her. Perhaps it was this sweet naivety that won the favor of beings from beyond... With the first olive branch extended by a demon to humanity, the first witch was born...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("As if deliberately concealed, the origin story of the Yuanchushi Witch is always so vague... Even the Temple of Iris only indicates that she was born in 6th-century Britain. But the island was so barbaric at the time that a little girl with missing parents could barely survive, let alone a 16-year-old Rika who could already use so-called magic to traverse two continents...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("The Yuanchushi Witch, Rika Yuanchushi, is the nominal leader of all Council Witches and the one who most resembles what people imagine a witch to be. She often retreats to the core workshop within Crimson City, staying there for decades or even centuries at a time. It is for this reason that the headquarters of the Witch Council was established within Crimson City, and the witches have naturally come to regard this massive fortress as their new home.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Under the brilliance of the witches, many have forgotten that Rika is also an authority in craftsmanship and manufacturing. Or rather, it is difficult for people to connect these achievements with a witch who is already so enigmatic. In truth, compared to the obscure and talent-dependent Elemental School of mysticism, Rika's contributions to more accessible fields like handicrafts and alchemy have far greater significance for human society.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As a master of the Elemental Magic School, Rika single-handedly pioneered and perfected the Yuanchushi system based on eight elements. However, as she was not enthusiastic about promoting her teachings, few mystics worldwide have been able to fully comprehend it. Until her departure from Earth, she never took on a single disciple. Perhaps, from the very beginning, the Yuanchushi Witch never cared whether her research would be carried on.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Thus, even today, when someone mentions elemental magic, they are more likely referring to the more famous schools, such as the Seven Luminaries, Five Elements, or Four Foundations. Nevertheless, almost all mystics still regard Rika as one of the founding figures of the Elemental Magic School.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Despite her numerous achievements, the Yuanchushi Witch is not an omnipotent being, even if she often demonstrates immense talent in precise situations. For example, she lacks artistic sensibility and is not meticulous in her daily affairs. Without the help of servants, her ever-thinking mind would struggle to manage even her basic needs.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Additionally, since her initial supernatural powers came from the Crimson Lord, Rika was never able to escape the grasp of her master. Although this capricious and unrestrained deity from beyond rarely interferes with others' freedom, when she is in the mood, even the Yuanchushi Witch must fulfill certain obligations as a woman...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As the most famous witch in Earth's history, her existence serves as a stark warning to the world: behind this title lies not a magical girl, but women who have made pacts with demons.", WthC_ColorData.IE_WHITE, pad);
        tooltip.addPara("Without exception, they are indifferent to mortal lives and to the universe that seems to have nothing to do with them.", WthC_ColorData.IE_WHITE, pad);
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
