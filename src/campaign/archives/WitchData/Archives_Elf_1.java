package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Elf_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Primordial Spirit Ring: Living Spirit", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Familiar Witch's Primordial Spirit Ring is named Living Spirit, a 'golden gemstone ring' with an emerald set in a rose gold band and base. Of course, it only appears this way - all Primordial Spirit Rings use Primordial Steel as their metal base, which is crucial for their actual functionality.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Like its name, 'Living Spirit' unabashedly displays natural elements... the ring's band is extremely rugged, carved with tree root-like patterns, while its base is encircled by a long leaf pattern and a corresponding wing pattern. This subtle harmony between flora and fauna reaches its most primal unity in the emerald gem's constantly repeating dripping and ripple effects. This sense of harmony and closeness between animals, plants, and nature elevates Living Spirit's artistry to new heights, though admittedly, this living-like artistic conception appears somewhat abstract on a ring.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Functionally, 'Living Spirit' only adds the ability to understand corresponding object information when used with the badge, beyond the basic Primordial Spirit Ring functions. Initially, the Primordial Witch was surprised by this complete lack of interest in customizing functions, and Eden's response was the laziness-inducing 'I don't do much anyway...' It's hard to imagine what mindset allows this petite witch lady to spend thousands of years so boringly and lazily...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Worth mentioning is that since Eden likes to pass time reading, she often takes books from Rika's collection, but this lazy girl reads very improperly, rolling around on the ground, sometimes even directly covering her head with heavy books to block sunlight, and frequently getting pages dirty or damaged with pet saliva. The book-loving Primordial Witch was quite helpless against this wasteful behavior and added the same book preview function in the Spirit Ring network as 'Glimpse'. However, for some reason, Eden just loves to cuddle with physical books completely disproportionate to her body size, forcing the resigned Lika to start her 'Book Digitization Project' to prevent these precious books from becoming unreadable after the originals are damaged.", WthC_ColorData.MID_WHITE, padS);}

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
