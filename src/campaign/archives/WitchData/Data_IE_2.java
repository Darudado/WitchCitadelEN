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
public class Data_IE_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Additional information about the target individual:", WthC_ColorData.IE_WHITE, pad);

        tooltip.addPara(strings.get("Data_2_1"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Approximately 160cm tall, with long silver-gray hair and a striking appearance. Her primary non-human feature is her heterochromatic eyes, which change color in real-time based on the type of elemental energy within her body.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara(strings.get("Data_2_2"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Calm and focused, she appears as a silent and somewhat lifeless woman. Combined with her appearance, she resembles a cold literary girl... or perhaps a complete homebody. A few individuals close to her believe she prefers women, and she herself does not seem to deny this.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara(strings.get("Data_2_3"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Energy Manipulation II, Elemental Studies III, Crafting II, Knowledge II, Military II", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara(strings.get("Data_2_4"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("As the most representative elemental mage, with enough energy, she can accomplish almost anything. These abilities include but are not limited to teleportation, telekinesis, physical form alteration, force field manipulation, energy jets, and anti-gravity. However, it is clear that the energy she has at her disposal is always very limited, meaning that this original elemental witch is not as godlike as the legends suggest.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("The Witch is particularly skilled at using subtle interactive magic to achieve earth-shattering effects. However, bound by her rigid and meticulous personality, she does not excessively use magic to alter the rules of the world.", WthC_ColorData.IE_WHITE, pad);
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
