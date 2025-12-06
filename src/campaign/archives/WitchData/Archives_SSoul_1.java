package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_SSoul_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Primordial Spirit Ring: This Realm", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The spirit ring belonging to the Soul Witch is named \"This Realm\". Unlike a typical single-finger ring, it uses two rings as supports to hold up a larger hexagram circle. This also means that Su always needs to keep two fingers together to wear it, and the back of her right hand is often covered by this circle, which is unusually large for a ring.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The hexagram circle on This Realm has a diameter of 8CM, and at each point and intersection of the hexagram, there is a transparent, flawless round diamond embedded. Clearly, only the Primordial Witch could have found and polished these gems at the time. Not to mention, the functions it corresponds to are something even the Primordial Witch could not achieve on her own.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("The main function of this circle is to observe the specific conditions of various rifts and each designated necromorph. When in use, Su only needs to inject stellar and psychic energy into each diamond according to the corresponding pattern, causing them to change color. The ring can then read data from the \"Reincarnation System\" and project the corresponding images above the circle.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The specific images that the circle can project include, but are not limited to, \"all visual records from the necromorph's past life\", \"the birth, death, and all reincarnation records of the necromorph\", \"the past appearance and specific information of the necromorph\", \"the current overview of the corresponding rift\", \"the history and information of the corresponding rift\", and \"specific images of a certain location within the corresponding rift\".", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Therefore, This Realm is like a terminal for reading information within the Reincarnation System. It allows Su to clearly understand the specific circumstances of each necromorph she encounters, symbolizing the unparalleled authority of the Supreme Arbiter.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Theoretically, the circle can support reading up to 333,333,333,333 (333.3 billion) different types of information. Clearly, it will take some years before there are that many necromorphs and rifts in existence...", WthC_ColorData.MID_WHITE, padS);
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
