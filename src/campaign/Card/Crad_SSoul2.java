package data.campaign.Card;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// 从幻想工造的军官酒馆事件和双面神等道具缝合而来
public class Crad_SSoul2 extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");
    protected CampaignFleetAPI player_fleet;
    protected PersonAPI officer;
    protected OfficerDataAPI officer_data;

    @Override
    public String getDesignType() {
        return null;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, CargoTransferHandlerAPI transferHandler, Object stackSource) {
        float pad = 15f;
        float padS = 3f;
        
        tooltip.addTitle(getName());

        String design = getDesignType();
        if (design != null) {
            Misc.addDesignTypePara(tooltip, design, 10f);
        }

        if (!spec.getDesc().isEmpty()) {
            tooltip.addPara(spec.getDesc(), Misc.getTextColor(), pad);
        }

        tooltip.addPara("\"In the end... it was still too late...\"", WthC_ColorData.SOUL_GREEN, pad);

        tooltip.addPara("Under a cherry blossom tree in full bloom, you kneel, holding a dying girl in your arms... She smiles so radiantly, even as blood trickles from the corner of her mouth, and her once pale blue kimono is now soaked crimson from the life seeping out of her......", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("She... took her own life. As the one closest to her, you know the full story... Her cursed constitution, her overly gentle nature...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("You knew she would choose this path, even though you, her servant, had sworn time and again to break the curse she was born with... Even after making a pact with a demon, it seemed there was still hope...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("But in the end, it was too late... Manipulated by the curses, lies, and guidance of others, she chose to end her own life... All to prevent more innocent lives from being lost because of her...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("But have you ever realized... my sorrowful mistress, that perhaps there has never been a truly innocent soul in this world?", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Everyone has made mistakes... Everyone has told lies... Even those who are good, who carry the weight of justice, are no exception...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("So... if the silent dead can only be judged by the living who cling to life, then there will never be true fairness in this world...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("I will not let you leave like this... Even if the peace of death is so alluring, I will not allow it...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("I will ensure that the deceivers... the cursers, and even the truly kind, all have the right to be judged in the darkness!", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Yes, the past of the dead shall only be judged by the dead!", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("So... please rest quietly by my side for now...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("\"And I will make them understand that if there is nothing after death, then life itself is meaningless...\"", WthC_ColorData.SOUL_GREEN, pad);

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
        Global.getSector().getPlayerStats().addStoryPoints(1);
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_SSoul3", "special_items"), 1.0F);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "This immersive experience will surely linger in your memory for a long time... You have gained 1 story point.");
    }
}
