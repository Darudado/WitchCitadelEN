package data.campaign.Card;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.ReputationActionResponsePlugin;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.CoreReputationPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import data.world.systems.WthC_Citadel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 从幻想工造的军官酒馆事件和双面神等道具缝合而来
public class Crad_IE2 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("\"What is it like to witness your own death?\"", WthC_ColorData.IE_WHITE, pad);
        tooltip.addPara("For ordinary people, such an experience, which they can never personally undergo, will forever remain a false proposition in this world...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("But clearly, you are not one of those ordinary mortals... At least in some aspects, you are worlds apart.", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("As you gaze at the image of a corpse tied to a chair, you ponder...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("The deceased is a woman, appearing to be around twenty years old, with long white hair nearly reaching her waist and a height of about 160 cm. These are the details you can ascertain for now, as other aspects are less obvious...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Her clothes are tattered and torn, drenched in a mix of blood and a murky yellow-white fluid. Wounds and bruises are the most widespread features on her skin. Her face, bearing numerous sharp instrument marks, is no longer recognizable as human. A single eye, still stubbornly lodged in its socket, conveys a hollow emptiness from the deceased...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Such a horrifying scene would undoubtedly be unbearable for those who have never witnessed death... especially when the deceased bears an uncanny resemblance to oneself...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("It is clear that the deceased endured prolonged and intense humiliation before her life was ultimately ended by a heavy blow directly to the eye... Of course, this conclusion may not stem from any latent detective skills you possess, but rather from memories that have yet to fully reach your brain...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("And for you, how many times has this been? Has that heart, not born of a womb, grown numb to it all...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Regardless, the preparations for the Stellar Shift have been completed. In the next moment, everything surrounding the pitiful deceased will be reduced to ashes. Along with the blinding light, the perpetrators, the assailants, and the other hollow victims will vanish...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("As the light fades, the only thing left within a radius of several miles is the corpse still bound to the chair... You step forward and retrieve the wide-brimmed pointed hat, still pristine, from the decaying, foul remains...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("\"At least... only it remains eternally intact...\"", WthC_ColorData.IE_WHITE, pad);

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
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_IE3", "special_items"), 1.0F);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "This immersive experience will likely linger in your memory for a long time... You have gained 1 story point.");
    }
}
