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
public class Data_SSoul_2 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("Additional information on the target individual:", WthC_ColorData.SOUL_GREEN, pad);

        tooltip.addPara(strings.get("Data_2_1"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("A translucent spirit, her body seemingly engulfed by ghostly blue flames, or rather, it is her sorrowful soul itself that burns. Though some human contours can be discerned, her appearance, while still beautiful, is difficult for ordinary people to comprehend.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_2"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("A recluse who has transcended life and death, she shows almost no emotional fluctuations, always maintaining a cold expression that seems both sorrowful and serene. As someone who has long seen through the illusions of life and death and lost all feeling for the mortal world, her spiritual realm far surpasses the physical world. She does not mind recounting past stories to any listener.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_3"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Energy Manipulation II, Knowledge II, Management III", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_4"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("As the Spirit Witch and Lord of the Myriad Rifts, she is the creator and arbiter of all afterlife worlds. Utilizing the reincarnation system directly maintained by Iris, she can observe and decide the death and subsequent rebirth of any lifeform influenced by the system across different dimensions of time and space.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("Perhaps she does not love death as much as she describes herself. As the most concrete embodiment of the reincarnation system, she not only can thoroughly observe and manage everything within the system but also directly intervene. This means she can most directly extract the stellar energy infused into any intelligent lifeform during the rebirth ritual, causing irreversible death.", WthC_ColorData.SOUL_GREEN, pad); }

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
