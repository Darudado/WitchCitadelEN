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
public class Data_Bc_2 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("The ancient shall pass... while the eternal stands unshaken...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("So when the ancient steps into eternity, the primal one shall execute the end...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Though 'Lord'... or rather, 'Lady Lovenia's' messages are increasingly concise, they're becoming more obscure...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("To the point where you're starting to doubt if there's any actual meaning in these seemingly philosophical phrases...", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("Haha, this is when the Crimson Lord makes their entrance~ I heard your call, chosen one!", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Honestly... you probably wouldn't want to summon this one even in your next life...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Couldn't they be more considerate of someone who just went through a major battle...?", WthC_ColorData.CROSS_GARY_D, padS);
        tooltip.addPara("And... wait, since when did you start believing in next lives?", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("When will you abandon this clamor...? Crimson Demon...?", WthC_ColorData.B_WHITE, pad);

        tooltip.addPara("Heh, perhaps not even after you die a few more times... Pale Ruler? Anyway, never take what this one says as philosophy...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("For instance... here she's just trying to say 'we undying ones should step aside for the young ones', right? Lovenia?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("As you wish...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Though this terminal only shows text, you can now feel the resignation behind it through the void...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Perhaps... this experience might come in handy next time you encounter those zealous Luddic believers?", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("By the way, if you really want to learn more about otherworldly knowledge, why not chat with my lovely witch lady~ Of course, I mean the one who most resembles a 'witch'...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("And if nothing goes wrong... most facilities in the Crimson City should now be open to you~", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Though... others aren't as merciful as I am...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Merciful...?", WthC_ColorData.CROSS_GARY_D, pad);

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
