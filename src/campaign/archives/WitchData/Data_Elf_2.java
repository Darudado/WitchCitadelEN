package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// 从双面神等道具缝合而来
public class Data_Elf_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Additional Information of the Target Individual:", WthC_ColorData.EIF_ORANGE, pad);

        tooltip.addPara(strings.get("Data_2_1"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("A very petite female, standing only 140CM tall. Her short brown hair and green eyes make her look like a lazy, adorable human girl, exuding a special kind of feeling. She is always surrounded by a few strange creatures.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_2"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Usually extremely lazy and gentle, she always appears listless, spending her days playing with her pets as if she were a little girl. She enjoys sleeping and reading, rarely speaks, and doesn't seem bothered by disturbances from others.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_3"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Energy Manipulation III, Polearm Weapons I, Beast Taming II, War Overturning II", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara(strings.get("Data_2_4"), WthC_ColorData.N_WHITE, pad);
        tooltip.addPara("Since the Spirit Witch Eden Ludwig Faust has long retreated from the historical stage, the only ability that can be glimpsed from her is the \"Stellar Recomposition\" that classifies her as absolutely eternal. Simply put, no matter how severely her body is damaged, she can always restore it to its original state in the next second.", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("As a complete recluse, the Spirit Witch is like a floating cloud—observable but impossible to truly understand. Even as a member of the council witches, her name often appears alongside the others, yet this does nothing to diminish the atmosphere surrounding her...", WthC_ColorData.EIF_ORANGE, pad);
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
