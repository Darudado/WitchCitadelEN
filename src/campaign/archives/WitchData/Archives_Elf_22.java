package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Elf_22 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("God-Hunting Twin Spears", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Two spears of extraterrestrial origin that can be disassembled into four sections for storage and carrying. The spearheads can retract into the front cylindrical section to prevent self-injury during normal times. Some of the metal cylinders inside Eden's cape are these spears in their unassembled state. Surprisingly... for weapons from an advanced civilization, these spears' disassembly and assembly method is simply rotating the cylinder's threads, rather than some more practical and special 'high-tech' method.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The Familiar Witch herself describes these two pole weapons as 'slightly harder ordinary sticks...' While her assessment is completely accurate in various ways, she's being modest about the 'slightly harder' part. From all materials related to the 'Ancient Behemoths', we know these two spears were her commonly used weapons thousands or even tens of thousands of years ago when Eden was keen on hunting, and to this day, these two spears remain pristine, without even the slightest sign of 'rust' or 'nicks', and even scratches are hard to find anywhere on their surface.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("So while they're just 'slightly harder', this level makes one wonder what material these seemingly plain metal poles are actually made of.", WthC_ColorData.HIGH_BLUE, pad); }

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
