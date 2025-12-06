package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Dust_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Dust Witch's Badge", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Dust Witch's badge is always proudly displayed on her chest, forming a bold yet harmonious beauty with other Star Magic School-styled decorations. Among the curtain formed by the black dress, this supernova-like dazzling badge is naturally the 'eternal protagonist of the void'.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Naizel is also the only Council Witch who wears her witch badge on her chest, but perhaps this is how a 'badge' should be worn. The other Council Witches' ways of using these symbols are always so full of personal characteristics.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("The Dust Witch's badge appears like a microscopic-styled ring galaxy, and thanks to the fusion reaction phenomenon on Naizel's body, this beautiful badge naturally flows like a real nebula. If broken down, this badge actually consists of only two parts: the base and the core.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The base is made of a special material with controllable optical reflection properties. When combined with the core, it can freely control whether to refract light forward, providing flashlight and laser pointer functions. The core's main body is just an energy capacitor in the shape of a neutron star concept, with an added miniature quantum transmission device that can directly connect to the reactor on the 'Dust', making it an external miniature fusion reaction output device.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Besides serving as a decoration, flashlight, laser pointer, and high-intensity ray emitter, the Dust Witch's badge has a very romantic primary function. Since its core is directly connected to the 'Dust', it can directly preview the star charts from that warship that far exceed human cognition. Combined with the light-reflecting base and hologram-producing core, Naizel can create a holographic star projection anywhere, as if bringing that universe directly before one's eyes.", WthC_ColorData.HIGH_BLUE, pad);}

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
