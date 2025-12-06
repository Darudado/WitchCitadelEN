package data.campaign;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.CharacterDataAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// Stitched together from Janus and other items
public class Mace_Ec extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");
    public static final String DRIVE_RESONANCE = "DriveResonance";
    public static final String RESONANCE_REPAIR = "ResonanceRepair";
    public static final String RINGSPIRIT = "RingSpirit";
    public static final String DENSE_FOG_TWINE = "DenseFogTwine";


    @Override
    public String getDesignType() {
        return null;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, CargoTransferHandlerAPI transferHandler, Object stackSource) {
        float pad = 10f;

        tooltip.addTitle(getName());

        String design = getDesignType();
        if (design != null) {
            Misc.addDesignTypePara(tooltip, design, 10f);
        }

        if (!spec.getDesc().isEmpty()) {
            tooltip.addPara(spec.getDesc(), Misc.getTextColor(), pad);
        }

        tooltip.addPara(strings.get("mace"), WthC_ColorData.IE_WHITE, pad);
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
        final CharacterDataAPI player = Global.getSector().getCharacterData();
        if (player.getAbilities().contains(DRIVE_RESONANCE)) return;
        if (player.getAbilities().contains(RESONANCE_REPAIR)) return;
        if (player.getAbilities().contains(RINGSPIRIT)) return;
        if (player.getAbilities().contains(DENSE_FOG_TWINE)) return;

        player.addAbility(DRIVE_RESONANCE);
        player.addAbility(RESONANCE_REPAIR);
        player.addAbility(RINGSPIRIT);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You've learned some techniques for using the scepter, you can check them in your abilities.");
    }
}
