package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Elf_0 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Familiar Witch's Decorative Style", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("For the Familiar Witch who rarely meets others, there's no need to focus on clothing, and reality reflects this - she always wears an oversized robe that looks completely ill-fitting regardless of time or place. Perhaps in the past, this 'Witch's Ceremonial Robe' had more vibrant colors, but now it only shows an empty, deep black.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This oversized robe is connected to an equally plain white hood without any patterns. Though it seems practical, it's hard not to wonder if Eden can still see what's in front of her when wearing it completely. Perhaps because it's a bit too long, for a long time, the Familiar Witch even used this robe as a sleeping bag - in this case, the ill-fitting size became an advantage?", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As if the huge robe wasn't awkward enough... hanging behind the Familiar Witch is an even longer cape that always drags carelessly on the ground when walking. But unlike the now pitch-black robe, at least this equally mismatched long fabric still displays the same bright white as the hood.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("As for this cape's function, it's not hard to guess - just looking at the faintly visible metal components within, one can roughly understand why it's always carried. Of course, normal people would probably become more bewildered afterward - why are these precision components that look heavy just by sight attached to a cape that's always carelessly thrown around? At the same time, Eden's 'spirits' seem to love the soft, smooth texture of this cape; when she takes it off to rest, you can always see those fluffy little ones curled up on the snow-white surface.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Finally, under the robe, the Familiar Witch has some hard-to-notice small pieces, such as a pair of seemingly cotton slippers, white tights that barely show any skin, and a pair of long gloves that look completely out of place with the robe. Most people would probably question from the bottom of their hearts if something's wrong with this child to come up with such an unsuitable clothing combination. They might also start to wonder why she wraps her body so completely.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Actually, just as people suspect, all the clothes on the Familiar Witch are specially made like that robe. After all, she isn't a native creature of this planet, and in her tens of thousands of years of daily life, convenience has become her only pursuit. What could be more convenient than clothes that don't need changing, protect against wind and rain, and can be used as a sleeping bag...?", WthC_ColorData.MID_WHITE, padS); }

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
