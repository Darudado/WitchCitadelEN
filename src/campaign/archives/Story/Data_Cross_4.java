package data.campaign.archives.Story;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.ReputationActionResponsePlugin;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.CoreReputationPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import data.world.systems.WthC_Citadel;

// 从双面神等道具缝合而来
public class Data_Cross_4 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("They are not as splendid as they appear...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("The more beautiful something is, the more deadly it tends to be... Perseus one...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("You need not know their identities, nor doubt the reality of what you feel.", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("When the lost become entangled here... the meaning of truth is far less important than struggling to survive...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("But surely... by now, she no longer allows you to die easily...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Once again... after defeating a massive fleet that seemed to appear from nowhere near the gate, it appeared again in its usual place.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Still containing cryptic information... clearly... from that 'Lord'.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("After all... besides those mystifying Luddic Prophets, who else would want to spout such meaningless circular philosophy in this damned chaotic sector?", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Honestly... you're displeased with everything that's happened recently... especially since arriving in this bizarre black hole system. What here makes any sense? The castle floating in the void? Planets orbiting in strict patterns? Or the ships and weapons that clearly weren't made in any normal military factory?", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("......", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("You hope this 'Lord' would give you some satisfactory answers...", WthC_ColorData.HIGH_BLUE, padS);

        tooltip.addPara("Perhaps impatience is another trait of Perseus people...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Truth exists only in the whispers of all things... you will understand, someday...", WthC_ColorData.B_WHITE, pad);

        tooltip.addPara("......", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("After reading the reply, you let out a resigned cold laugh... clearly this is not a 'satisfactory' answer...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Well, seems impossible to get much useful information from them.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Perhaps all you can do is return to that strange castle to start the next 'Crimson Invitation'... at least their rewards are decent.", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("After all... confident as you are, you've never feared any challenge faced on a warship.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("As a captain who fights in space day and night, you certainly know how insignificant a single life is in this age.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Counting casualties after each encounter has long numbed you, especially since a large part was by your own hand...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("You may indeed be fortunate - even though each overload and explosion could easily take your fragile life, you've made it to today.", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("Perhaps their philosophical statements aren't entirely meaningless after all...", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("For a Perseus person, understanding the truth is indeed far less important than struggling to survive...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Perhaps this is why she chose you...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("......", WthC_ColorData.B_WHITE, pad);

        tooltip.addPara("With another mysterious short phrase, this terminal's screen went dark.", Misc.getHighlightColor(), pad);
        tooltip.addPara("You tried to repair the previous terminal after it lost function, but even your fleet's most trusted technical team was powerless to help.", Misc.getHighlightColor(), padS);
        tooltip.addPara("Looks like you can only prepare to face the next challenge.", Misc.getHighlightColor(), padS);
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
                "You gained 1 story point... Challenge the next Crimson Invitation to obtain more clues");

    }
}
