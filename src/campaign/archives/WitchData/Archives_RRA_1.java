package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_RRA_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Primordial Spirit Ring: Deep Color", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Artifact Witch's Primordial Spirit Ring is named Deep Color, inheriting Glimpse's three-part principle of band, base, and gem, but with a platinum band bearing thorn patterns, an eye socket base incorporating leaf elements, and a magic crystal emitting dark blue light, making this ring no longer plain like its prototype, but possessing a unique and harmonious beauty.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Pale Moon's Primordial Spirit Ring naturally comes from the Primordial Witch's hands, but starting with the Artifact Witch's ring, all subsequent spirit rings' appearance designs were completed by Pale Moon, so these rings tend to be more unique and beautiful than Lika's 'Glimpse'.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("For custom functions, Deep Color emphasizes monitoring/protection features, with additional neural links allowing the Primordial Witch to immediately know any subtle changes in Pale Moon's body. The gem has two special formations built in, enabling Deep Color to deploy a magical barrier that completely envelops the body (requiring the wearer to temporarily float), protecting Pale Moon from external influences as much as possible until energy is depleted to the teleportation threshold. When the ring's remaining energy drops to this threshold, it performs an emergency directional teleport, allowing Pale Moon to quickly return safely to the Council Hall in the Crimson City.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Of course, with just a little thought, one should understand this function was almost forcibly installed by the Primordial Witch for Deep Color. Although it greatly ensures Pale Moon's safety, one can still feel Lika's almost pathological protective desire over her best friend.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Additionally, Deep Color has some custom functions proposed by Pale Moon herself, such as photography, color simulation, 3D model preview, distant projection, map viewing, short-range radar, and other functions aiding artistic creation and travel. For this artist lady who loves traveling everywhere, perhaps these are what Deep Color should really have.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Although as human civilization gradually entered the modern era, increasingly advanced personal terminals gradually replaced these functions, back in the distant and long Middle Ages, these now extremely common applications could only be described as magical.", WthC_ColorData.MID_WHITE, padS); }

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
