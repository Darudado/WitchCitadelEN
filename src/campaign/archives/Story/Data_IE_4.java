package data.campaign.archives.Story;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.ReputationActionResponsePlugin;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.impl.campaign.CoreReputationPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import data.world.systems.WthC_Citadel;

// 从双面神等道具缝合而来
public class Data_IE_4 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Information recorded on the terminal:", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Pure good and evil cannot define their nature... just as no simple concept can explain human nature.", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("As I expected... you excel at combat, or rather... one-sided slaughter?", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("It seems... another fool is destined to become her prophet, even if they're a visitor from another space-time...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("You admit... compared to the first two challenges, this opponent was indeed more troublesome.", Misc.getHighlightColor(), pad);
        tooltip.addPara("Their fleet doctrine was the type most Perseus captains would least want to encounter...", Misc.getHighlightColor(), padS);
        tooltip.addPara("Fortunately the fleet's overall size wasn't too large, leaving you enough energy to find this data terminal...", Misc.getHighlightColor(), padS);

        tooltip.addPara("The terminal's information remains as mystical and incomprehensible as expected.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("But gradually... you're even starting to find this new form of communication interesting?", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Uh... perhaps staying in this system too long makes even oneself become strange?", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Perhaps until today... you never questioned the source of your power.", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("'Just another stroke of luck'... 'Just a display of skill'... 'Just a difference in power'...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("But have you ever considered... perhaps you were born special...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("You always have countless chances... you always have potential beyond human limits... you always clearly know all enemy details...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("These special traits may not be of your own will, but they remain unfair...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("To all other Perseus people... especially those who died by your hand...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("......?", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("This series of messages is hard for you to accept...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("It's as if there's some mysterious supernatural power within you...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("And seems to deny your efforts and abilities as a fleet leader...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Humans are a species that always imagines their own uniqueness...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("They often imagine some invisible force protecting them until their life's end...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Unless... they truly are that special, needing no impractical dreams to rely on...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Which do you think you are...? Sorrowful chosen one...?", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("......", Misc.getHighlightColor(), pad);
        tooltip.addPara("As usual, this terminal went completely dark once again.", Misc.getHighlightColor(), padS);

        tooltip.addPara("Normally... you should return to your familiar starship career now...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("But why... can't you break free from this supposedly meaningless philosophy right now...?", WthC_ColorData.CROSS_GARY_D, padS);


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

        CoreReputationPlugin.CustomRepImpact impact = new CoreReputationPlugin.CustomRepImpact();
        impact.delta = 0.1f;
        ReputationActionResponsePlugin.ReputationAdjustmentResult result = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact, null, null, true), WthC_Citadel.getHILLYA());

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You gained 1 Story Point... Challenge the next Crimson Invitation to obtain more clues");
    }
}
