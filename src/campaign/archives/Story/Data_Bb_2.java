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
public class Data_Bb_2 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("Hardships abound... the road ahead is long...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Past mistakes created countless eternities, but with the ruler's passing, all has turned to dust...", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("Floating in endless void, waiting for a hero to proclaim the end of obsession...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("By now, you're well used to these overly mysterious philosophical proclamations.", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Perhaps for this 'Lord', this is just their normal way of speaking...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Heh... still the same as ever, just like thousands of years ago?", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("It seems even you struggle against such an opponent. Wondering why there's such a huge difference between shadows of the same witch?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("This requires explaining the gap between 'Phantoms' and 'Entropy Shadows'... Of course, don't ask why I know everything, it's my innate privilege~", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Essentially, both Phantoms and Entropy Shadows originate from the same phenomenon called the 'Pale Contract', or rather, they're both manifestations of entropy reversal.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("The difference is that Entropy Shadows are manifestations born from the convergence of countless possibilities, while Phantoms are just one of those possibilities. In other words, what you fought before was merely a part of an Entropy Shadow, but now you face everything born from its entirety~!", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("So... why are you telling me all this...?", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Haha... isn't this the truth you've been seeking?", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("But rest assured... to make this game more interesting, even I, who holds nothing back, won't reveal everything at this moment~", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Let us meet again when this contract progresses further~ chosen one.", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Not sure what's reassuring about that...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Even you feel overwhelmed facing these increasingly difficult death games... Looks like you'll just have to prepare as best you can for the next challenge.", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("By the way, please don't trust Rosalia's words too much, she's... a child with some peculiar tastes~", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Is there anyone better at that than you...?", WthC_ColorData.HIGH_BLUE, pad);


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
        impact.delta = 0.05f;
        CoreReputationPlugin.CustomRepImpact impact2 = new CoreReputationPlugin.CustomRepImpact();
        impact2.delta = 0.15f;
        ReputationActionResponsePlugin.ReputationAdjustmentResult result = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact, null, null, true), WthC_Citadel.getHILLYA());
        ReputationActionResponsePlugin.ReputationAdjustmentResult result2 = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact2, null, null, true), WthC_Citadel.getROMENA());

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You gained 1 Story Point... Challenge the next Pale Contract to obtain more clues");
    }
}
