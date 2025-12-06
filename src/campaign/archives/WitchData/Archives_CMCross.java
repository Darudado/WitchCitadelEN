package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_CMCross extends BaseSpecialItemPlugin{

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



        tooltip.addPara("Heh... if you truly want to understand these mysterious visitors from the Crimson Cities, why not hear the Crimson Lord's explanation directly?", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Because you know what follows will be long-winded...", WthC_ColorData.MID_WHITE, pad);

        tooltip.addPara("Rosalia is quite an interesting one... far more interesting than you, at least.", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("Though I never considered any human truly innocent, she did have a relatively tragic past. Under the influence of these cruel shadows, she ultimately chose to become an abuser, a deceiver who transcended mere harm to living beings. Haha, quite like the choices you humans would make, isn't it?", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("So in the long years that followed, she was only devoted to her own desires - physical sensations and psychological dominance became the only two things worth pursuing.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("But can you believe it? Such a purely self-serving villain from an omniscient perspective was revered as the most sacred being in your past society... even most victims willingly gave their lives for her, and indeed often got their wish.", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("I remember back then, when discussing more 'higher forms of entertainment' privately with all my witch ladies, she was always the one who brought the most ideas~", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("Of course, of course~ You Perseus people probably just can't understand that kind of pure beauty~", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("......", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("Must one's pleasure always be built upon others' pain?", WthC_ColorData.MID_WHITE, padS);
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
                "Congratulations on spending 10 million for a piece of data... Keep up the good work, little genius. Here's a Story Point for your trouble~");
    }
}
