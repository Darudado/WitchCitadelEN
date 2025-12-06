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
public class Data_Dust_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Additional Information of the Target Individual:", WthC_ColorData.DUST_RED, pad);

        tooltip.addPara(strings.get("Data_2_1"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Similar to a human female... roughly 156CM in height, but both her skin and clothing give off the impression that they might burst into flames at any moment. Her eyes naturally emit a blinding red light, and her black hair always floats wildly in the air.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_2"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("A cold and socially distant woman, her demeanor carries a sense of freedom and grandeur. She prefers to be alone and often uses force to drive away those who try to approach her. She is constantly frustrated by her overwhelming destructive power, and though she dislikes unnecessary killing, she has long become numb to life. In truth, she is well-versed in etiquette and knows how to treat others, harboring a gentle side that few ever see.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_3"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Energy Manipulation III, Astronomy III, Knowledge I, War Overturning III", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_4"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("As unbelievable as it sounds, the Dust Witch is essentially a \"quasi-stellar being.\" She has spent her entire life searching for ways to further limit her own power, as even the slightest lapse in control can turn her surroundings into a hellscape of radiation.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("And so... it becomes one massacre after another. Until the last fleeing soul perishes, the reaper tirelessly swings her blades, which will never be stained with blood. Even... her swordsmanship, driven purely by overwhelming force, lacks any semblance of carelessness.", WthC_ColorData.DUST_RED, pad);
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
