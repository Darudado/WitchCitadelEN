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
public class Crad_Elf2 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("\"It's... more comfortable than I imagined...?\"", WthC_ColorData.EIF_ORANGE, pad);

        tooltip.addPara("With a massive energy shockwave that could envelop the entire planet's surface, you know another annihilation ritual has been performed by the Supreme Deity...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("You look down at your hands, which are leaking radiation incessantly... They do seem a bit smaller than before? But in any case, perhaps you should find a pair of long gloves, as even though the radiation effect will gradually slow over time, this level of radiation is enough to cause irreversible damage to surrounding lifeforms...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Naturally... the same goes for the lower body. Perhaps even as a woman, you shouldn't wear skirts anymore... or maybe make a full-body stocking from the same material...?", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Heh... I suppose for the Supreme Deity standing before you, none of this is a big deal... At most, she might tease you with her mischievous tone, questioning why you showed such mercy to an ancient deity's descendant who should have had no connection to you...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Yes... With the complete end of my long mission, I should have returned to the void to beat up my former superior who has been stripped of their title... and then gone back to my spire to enjoy the worship my subjects owe me... Instead of becoming this unstable radioactive child?", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Why, as a demon god, did I sacrifice my noble body to save a little girl who should have been discarded after use? Isn't she, after all, the equally foolish and detestable descendant of those already trapped in dolls?", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Was it because of her existence that I was able to end this millennia-long mission of slaughter? Or did I, who just annihilated an entire species, suddenly feel a pang of pity?", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Heh... Perhaps even I am not entirely sure...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Well, once the thought arises, all is decided... I have no interest in that damned homeland of mine, so why not take this chance to enjoy what it feels like to be a little girl...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("\"And you... Lovanya, please enjoy the feeling of being the one on high...\"", WthC_ColorData.EIF_ORANGE, pad);
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
        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_Elf3", "special_items"), 1.0F);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "This immersive experience is sure to leave a lasting impression on you... You have gained 1 story point.");
    }
}
