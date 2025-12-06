package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Elf_2 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Familiar Witch's Badge", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("As a formal member of the Witch Council, the Familiar Witch naturally has her own witch badge, a mirror-like metal ring that Eden always hangs on her small belt with several ribbons. This symbolic badge's appearance remains as distinctive as the others. Since there were no common requirements like 'connecting it to something' in its initial design, the Artifact Witch was bolder in design, making the finished product a size larger than some badges worn as headpieces.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The overall design concept of the 'Familiar Witch Badge' is 'a lens surrounded by several small rings', with the 'small rings' composed of freely interchangeable metal particles, forming patterns by adhering to the mirror base that can provide vectored gravity. Eden always wants to observe the state of her 'fluffy pets' around her, and the number of her pets constantly changes, so a self-sensing and morphing lens emerged.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Due to the numerous different-sized metal particles on the black base behind the lens, this magical lens can easily handle even when dozens of 'fluffies' surround Eden. These metal particles also have limited color-changing capabilities; though somewhat abstract, one can still roughly make out each 'fluffy's' characteristic markings within these small rings. As for the lens part, it's actually an information collector bound to the Primordial Spirit Ring 'Living Spirit'. When the Familiar Witch points the lens part at an object she wants to understand, 'Living Spirit' will compare and analyze it in the vast Illyis Archives and provide Eden with most of the information she wants to know through the Spirit Ring network.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("However, obviously, these precise designs have some drawbacks. Although this badge is beautiful, versatile, interesting, artistic, and practical, its size far larger than Eden's palm and weight of 3KG would deter normal little girls - probably only a witch would be willing to keep such a trinket constantly at her waist.", WthC_ColorData.MID_WHITE, padS);}

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
