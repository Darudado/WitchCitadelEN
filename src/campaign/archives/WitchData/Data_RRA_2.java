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
public class Data_RRA_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Additional information on the target individual:", WthC_ColorData.DEEP_BLUE, pad);

        tooltip.addPara(strings.get("Data_2_1"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Jet-black long hair, blue eyes, and an exceptionally striking appearance, possessing a beauty that is universally understood.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara(strings.get("Data_2_2"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("A gentle and kind woman, cheerful and sociable, able to engage in pleasant conversations with most people; she loves traveling and scenery, often embarking on spontaneous trips upon hearing of interesting places; she prefers resolving issues through dialogue and has no inclination toward engaging in combat.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara(strings.get("Data_2_3"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Energy Manipulation I, Art III, Crafting I, Travel I", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara(strings.get("Data_2_4"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Due to her magical and physical capabilities being similar to those of an ordinary human, the Enchanting Art Witch has always been a woman of delicate strength, especially since her energy manipulation remains at a... performance level. Thus, compared to other witches with vast powers, Youyue's uniqueness lies in her transcendent beauty.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("Although aesthetics may be the most subjective and free concept in the universe, even setting aside her innate beauty, most of the artworks created by Youyue are indeed considered treasures of the world. Some seek to obtain her works, while others desire her herself. Regardless of their intentions, the phrase 'capable of toppling cities and kingdoms' is most fitting when applied to the Enchanting Art Witch.", WthC_ColorData.DEEP_BLUE, pad);}

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
