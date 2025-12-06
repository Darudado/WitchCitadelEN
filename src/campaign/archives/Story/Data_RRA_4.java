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
public class Data_RRA_4 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("It seems... you are her newest spokesperson... unfortunate one.", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Who could know that once you accepted this initial invitation, you could no longer escape from a fate intertwined with another world?", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("But since you are destined to meet them in the future, at least I can offer you some warnings to help you survive...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Of course... you can choose to ignore this stranger's visit, and face alone your fate that will drive straight to its end.", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("After briefly previewing the information recorded in this broken terminal, you're somewhat puzzled...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Obviously, as a mature Perseus person, the recent battle is more worth reflecting on than this so-called destiny.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Originally, you thought this was just another ordinary system bounty, but who could have imagined it would be so mysterious...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Those so-called phantoms surrounded the gate as if artificially summoned, and those modified ships and weapons were all novel creations never seen before...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Even... when cleaning up the battlefield, there was no trace of life or death - weren't these ships supposed to need many crew members?", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Just as your mind was wandering, a text box appearing at the end of the paragraph caught your attention, seemingly suggesting you could input text in this terminal? Though you're not even sure if this thing can connect to the network...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Well, might as well try... for example, ask about their identity.", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("......", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Due to certain... rules, I cannot tell you much, but... you will know someday.", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Surprisingly, the reply came almost instantly after the question was asked...", Misc.getHighlightColor(), pad);

        tooltip.addPara("Looks like this broken terminal really does have network functionality? At least... something can communicate with you through it.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("And... this self-reference as 'I'... isn't it too outdated? Didn't expect Perseus to still have such eccentric people...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("......", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Heh, looks like it's time...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Someday... you will fall into her claws... this is the chosen one's destiny.", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("As this cryptic message slowly appeared... the screen of this broken terminal went completely dark, seemingly having lost all functionality.", Misc.getHighlightColor(), pad);

        tooltip.addPara("Uh... looks like this 'Lord' is quite mysterious?", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Though you have enough reason to suspect this is a high-level cyber prank, looking at the credits that actually entered your account and the large amount of ship wreckage around... isn't this cost a bit too high?", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("And... seems like there aren't any proper buttons on this thing, can it really be restarted?", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Should you find a researcher to try to fix this thing? Or... does this so-called Crimson Invitation have a follow-up?", WthC_ColorData.CROSS_GARY_D, padS);

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
                "You gained 1 story point... Challenge the next Crimson Invitation to get more clues");
    }
}
