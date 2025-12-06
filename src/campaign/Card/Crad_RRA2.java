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
public class Crad_RRA2 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("\"Why... would you pay such a price for me?\"", WthC_ColorData.DEEP_BLUE, pad);

        tooltip.addPara("As the elegantly written words in the letter in your hands line by line come into view, tears have already begun to flow uncontrollably... His emotions are so sincere... so touching... so all-consuming... so unwavering...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("It's just a pity that, from the moment he fell in love with a sinful witch, perhaps an untimely death was already destined...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("He is not the first to be so infatuated, nor will he be the last...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Since you became accustomed to traveling, emotions like jealousy, longing, admiration, and hatred have always accompanied you...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("As the saying goes, once you have experienced it yourself, you know that possessing such beauty is an innate curse... It only invites endless calamity, offering nothing more than a small psychological comfort...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Without her... without them, how much suffering would you have endured because of this sinful appearance?", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("But because of her... because of them... how many innocent lives have had to pay the price?", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("You know... you must know, that you have felt this sorrow more than once... But why can't you recall any of the details?", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Still... at least...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("\"At least this time, I swear to remember him forever...\"", WthC_ColorData.DEEP_BLUE, padS);

        tooltip.addPara("Having made this silent vow in your heart, you wipe away your tears, carefully refold the letter, and place it back into a delicate little box.", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("After all... you still have to go see her, the friend who has protected you for countless years... Even if she doesn't seem... as towering as others might imagine.", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("But you believe she will teach you how to be strong... She will...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("......", WthC_ColorData.B_WHITE, pad);

        tooltip.addPara("...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("\"Are you sure you want to interfere with the currently selected memories?\"", WthC_ColorData.MYSTERIOUS_PURPLE, pad);
        tooltip.addPara("\"Yes...\"", WthC_ColorData.IE_WHITE, pad);

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
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_RRA3", "special_items"), 1.0F);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "This immersive experience will likely linger in your memory for a long time... You have gained 1 story point.");
    }
}
