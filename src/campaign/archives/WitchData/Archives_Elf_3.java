package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Elf_3 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Stardust Reconstruction", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Making Eden's physical form collapse and lose bodily functions temporarily isn't particularly difficult, as her body, like humans', is a product of blood and flesh fusion, not especially hard to destroy.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("But truly killing her is almost impossible in the universe, due to her body's special nature. Since the Familiar Witch's body is essentially composed of 'field-like matter', when its form's stability is destroyed, it rapidly recombines in a way humans can hardly imagine. During this process, missing particles are replenished through a kind of 'anti-annihilation' by absorbing energy from nature, and these 'repair materials' can even be anything found in the surroundings.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("At the same time, her clothes seem to share the same properties as her body, able to restore to their perfect original state through this almost unlimited 'energy creation system'.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Even on the question of how to kill Eden, the ancient behemoths, whose mobility far exceeded modern humans in ancient times, had researched for thousands of years during the long death game, and a 'most likely theory' emerged. Some sages believed that Eden wasn't actually 'eternal'. Although in their war they had countless times destroyed her body to dust-like fragments, they never succeeded in 'instantaneously and completely annihilating all parts of her body'. If this could be achieved, there might be a possibility of killing Eden. However, obviously, this was never successfully tested - the behemoths' complete disappearance from that planet is the best proof.", WthC_ColorData.MID_WHITE, pad);

        tooltip.addPara("As for Eden's 'rebirth' process, many might find it quite bizarre and bloody, but in reality, since this process is extremely fast and because 'Stardust Reconstruction' temporarily disrupts spacetime stability and emits massive light, effective observation is almost impossible. Often it's just a flash, and the next second, the previously shattered little girl appears whole before your eyes.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Worth mentioning is that the energy absorption during 'Stardust Reconstruction' is indiscriminate, so during this process, it's best to stay far away from her, lest you too be swept away.", WthC_ColorData.MID_WHITE, pad); }

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
