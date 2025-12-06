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
public class Crad_Cross2 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("\"One more strong assault... and the multitude of sins will be utterly vanquished under divine radiance!\"", WthC_ColorData.CROSS_GARY, pad);
        tooltip.addPara("Though you know that even if left alone, their surrender is only a matter of time...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("\"Do not spare them, this sin must be completely purified!\"", WthC_ColorData.CROSS_GARY, pad);
        tooltip.addPara("Even though all the enemy forces are merely civilians forcibly conscripted...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("\"The Lord only welcomes those brave souls who sacrifice themselves for the truth!\"", WthC_ColorData.CROSS_GARY, pad);
        tooltip.addPara("Of course, you’ve long known that there are no so-called deities here...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("......", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("\"Well done... your mission is now complete... holy warrior...\"", WthC_ColorData.CROSS_GARY, pad);
        tooltip.addPara("After wiping the blood from the top of your scepter and shaking off the remains of our own commander that had clung to it, you look around. The battlefield is littered with corpses, and no living soul remains...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("The sticky, black-and-red blood beneath your feet has stained your once pristine boots with a hint of crimson... You realize you’ve stepped on some unfortunate soul’s dismembered limb, though you’re not sure which part it was. Of course, long accustomed to such scenes, you feel no disgust. If anything, this hellish carnage is the best testament to your exceptional skills...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("This was supposed to be a triumphant, decisive victory, but it turned into the worst possible outcome—a battlefield where tens of thousands fell in relentless combat... What kind of disastrous tactics and psychological manipulation could have created such absolute equilibrium? Perhaps this will remain an unsolved mystery for scholars studying this battle for centuries to come.", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("And for you, who holds the truth, watching them struggle to find an explanation is the most amusing part...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Incidentally, you begin to ponder... What could be more cruel than religious war? What better way to reveal both the brightest and darkest sides of human nature...?", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("After all... there’s always an endless supply of ordinary people who have chosen to follow you, ready to pay the price, isn’t there?", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("\"Then... how about a plague driven by rats...?\"", WthC_ColorData.CROSS_GARY, pad);
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
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_Cross3", "special_items"), 1.0F);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "This immersive experience will likely linger in your mind for a long time... You have gained 1 story point.");
    }
}
