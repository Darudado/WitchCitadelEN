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
public class Data_Ba_2 extends BaseSpecialItemPlugin{

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

        tooltip.addPara("And so, our true game begins...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("How about it? Perhaps such a challenge is enough to stir a hero's heart?", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("But at least now... time is on my side... and all truths will gradually appear before you...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("You have to admit, the difficulties you face now are far more terrifying than what you've encountered before...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("Of course, the increasingly valuable rewards are enough to keep you going forward.", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("But perhaps... rather than these things, you want to know what you've been facing all along?", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("Indeed... starting from here, are we~", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Though I'm abruptly interrupting your conversation, it must be difficult to let that one explain these things to you~ right, Lovenia...?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("I do not deny it...", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("That's right then, so let me explain all the academic topics to this unfortunate Perseus person~", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Though you had this vague feeling... so this crimson one could actually interfere with everything from start to finish?", WthC_ColorData.CROSS_GARY_D, padS);

        tooltip.addPara("First... as a captain who's made it this far in the space age, you must know about 'entropy', right?", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("So building on that, have you ever wondered if someone could completely control this entropy... or even completely strip it from others?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Though it sounds mystical, on that blue planet back then, there really was someone with such a gift... She could completely strip the entropy from countless living beings, correcting their nature and granting them conceptual immortality~", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("However, the stripped entropy doesn't completely disappear - it's a collection of all negative possibilities of the original, just like the one you just met~", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("These collections are collectively called 'Entropy Shadows', extremely pathological existences that gain varying degrees of power based on their original's condition.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("You don't need to understand more specific details at this point - just know that after that gift-bearer passed away, all Entropy Shadows went out of control, and the worst among them were those from the six witches...", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Precisely because they themselves were so unique and full of possibilities... dealing with these shadows isn't so simple...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("By the way, I could solve all problems with just a snap of my fingers~ but wouldn't that make things boring?", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("...Why does this one have to emphasize that...", WthC_ColorData.CROSS_GARY_D, pad);

        tooltip.addPara("In any case, if you can handle these Entropy Shadows, they'll all surely be grateful to you~", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("For instance~ you've already gained one recognition... haven't you?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Well... even if you were momentarily struck silent by that overly cute virtual form after incorporating the Wraith Key into your ship's systems...", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("But if behind those illusions are real legends... perhaps your Perseus journey ahead will become much easier because of it.", WthC_ColorData.CROSS_GARY_D, padS);
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
