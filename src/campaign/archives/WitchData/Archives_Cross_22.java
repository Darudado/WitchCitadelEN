package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Cross_22 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Divine Cross Staff", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("A ceremonial staff about ninety centimeters long, it is both a symbol of Cross Witch Rosalia's theological status and her most effective weapon. This staff resembles a papal staff, but was redesigned by the Artifact Witch. Since Pale Moon didn't understand many theological elements, she could only follow Rosalia's descriptions for the patterns around the shaft, resulting in a somewhat bold and casual design.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("At the top is a common crown-like design, but unlike ordinary staffs, this crown section doesn't use many jewels for decoration. Instead, it uses metal lines to create a sturdy shell formed by two shield-shaped patterns and two sword-shaped patterns. Within the hard crown shell at the staff's tip, the ever-present cross element is enclosed, like a treasure guarded by swords and shields.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As a ceremonial staff, the Divine Cross Staff mainly serves as a medium for symbolism and ritual. According to Rosalia, 'Divine' represents a strong will that is solid, mysterious, difficult to seize yet still observable, just as this staff aims to express: 'The truth of the cross is protected by swords and shields, yet those with faith can still glimpse its pure and unreserved essence, an essence that is grand and three-dimensional, its radiance far stronger than one-sided swords and shields.'", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("However, as expected of a magical tool in a witch's hands, the 'Divine Cross' staff is certainly not just a ceremonial staff. As Rosalia's main weapon for hundreds of years, this so-called 'ceremonial staff' is essentially a multi-functional weapon that combines melee combat, defense, and magical amplification.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("The saying 'its radiance far stronger than one-sided swords and shields' actually has a more concrete manifestation. Thanks to alien materials provided by the Crimson Lord, the white crystal sphere at the staff's end has a light-caching function. After absorbing light for a while, Rosalia can use it as a high-intensity flashlight.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The two sword-pattern plates and two shield-pattern plates forming the 'crown' at the staff's tip can be detached from the base using stellar energy, exposing the cross completely as an excellent medium for energy projection. The two shield-pattern plates usually rotate slowly around the cross, receiving Rosalia's stellar energy to form a shield against frontal impacts, while the two sword-pattern plates extend with stellar energy, transforming into dagger-like spikes that rotate rapidly in front of the cross and shield, serving as melee weapons in emergencies. This staff has accompanied Rosalia through countless ages, witnessing numerous significant occasions and countless battles as her favorite handheld plaything.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("However, according to some unreliable sources, the staff's end crystal can not only absorb light and heat but also has the strange function of 'extending soft tentacles'. The Illyis Archives currently makes no comment on the authenticity of this information or the practicality of such a function...", WthC_ColorData.HIGH_BLUE, pad);}

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
