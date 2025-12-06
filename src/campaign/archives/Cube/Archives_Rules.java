package data.campaign.archives.Cube;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// Stitched together from items like the Two-Faced God
public class Archives_Rules extends BaseSpecialItemPlugin{

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


        tooltip.addPara("The Ritual of Entry", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("'If you are truly great, why not spare us from this calamity?'", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("But perhaps he never considered that if this ethereal deity truly existed, it would also possess the power to erase what remains.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Indeed, there is absolutely no fairness in anything that happens in this universe. Higher beings can easily tear apart the reality that those of lower status have painstakingly built, as if they were the ones that weren't real.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Perhaps those foreign visitors from the Crimson City are such beings; for them, everything that happens here is merely a game. However, what differs from reality on this basis is that any interesting game actually requires a set of relatively fair rules.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("For this, they created numerous toys, just to give those forced into this deadly game a damned sense of participation.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Surely by observing that massive fortress and the giant warships accompanying it, it's not hard to realize that these uninvited guests from beyond Perseus adhere to a space doctrine entirely different from this sector. At least, ships from another sky shouldn't be as... 'classical' as what you've encountered before.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("No one knows how those original blueprints fell so casually into their hands, but the crystallized wisdom of thousands of years of human development was easily decoded and reborn under the interweaving of vibrant colors... and made infinitely more powerful. Clearly... this is inherently a mockery and desecration of technological development and social research, as if those war machines most favored by the Perseus inhabitants were merely model toys to them...", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("As if... giving a glimmer of hope first allows them to experience deeper despair afterward?", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("But ironically, all Perseus inhabitants should pray that the Crimson visitors enjoy this highly insulting game, because when model toys no longer satisfy these wealthy young ladies... they'll seek something more real, won't they?", WthC_ColorData.HIGH_BLUE, pad);
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

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "This mysterious knowledge might prove useful in future adventures... You gained 1 Story Point.");
    }

}
