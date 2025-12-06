package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// 从双面神等道具缝合而来
public class Data_Dust_3 extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");

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


        tooltip.addPara("Detailed Information of the Target Individual:", WthC_ColorData.DUST_RED, pad);

        tooltip.addPara("The embodiment of falling stars... the reaper who scorches the earth... the demon who stirs crimson tornadoes... or the one destined to bring an end to this world... The legends about the Dust Witch are always fantastical, yet what the world often doesn't know is that even this witch, who has merged with the stars, was once an ordinary human. She was once a brilliant scholar, a princess adored like the stars embracing the moon, and a rising star in the field of astronomy.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The Dust Witch once bore a more elegant surname, Stardust; as the true first princess of an empire, she was burdened with great expectations from the royal family from a young age, and the girl who later became renowned never failed to meet those expectations. 'The Princess Who Embraced the Stars'—this was the highest praise the world bestowed upon the young astronomer... At that time, almost everyone believed that this star-blessed envoy would lead the empire, and even the entire human world, toward a brighter future. But perhaps no one could have imagined that just a few years later, she would witness the fall of a family, a school of thought, and even an empire... under the name of the Princess Who Shattered the Stars.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Even if, as the legends say, the current Dust Witch is a wild figure who excels at solving everything with overwhelming violence, she is also the only scholar among the remaining council witches besides the Primordial Witch. Unlike Rika, who was inspired by external factors from a young age, Nizer's wisdom comes entirely from her own understanding, making her a true genius. Before reaching adulthood, she had already achieved remarkable accomplishments in fields such as astronomy, mysticism, politics, and management. Not to mention, she was the most promising Star Envoy in the entire empire, radiating an aura that suggested she could conquer the world even as a mere human.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("But sadly, the reality born from her shattered dreams is what she has become today... After the Star Synchronization Ceremony, which brought about all the tragedies, she became one with the stars, and the Princess Who Embraced the Stars vanished completely from that planet. She could no longer skillfully weave energy... Anyone attempting to approach her would turn to ash, and even when she looked up at the starry sky she once loved, all she felt was a sense of desolate self-reflection.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Of course, while there are many similarities, the Dust Witch's body is not entirely identical to a star. This eternally burning, beautiful form is more like a fleeting illusion... a reflection of the true fragments from the distant cosmos. Nizer is the only witch whose body is purely composed of energy, with fusion energy making up over 99% of it, while the rest consists of primordial stellar energy that serves a defensive function. This is the primary reason why her surroundings are so dangerous.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Fortunately, her millennia-long exploration of her own power has not been in vain. With the help of many energy-restraining tools, as long as one does not come within a meter of her, there is no need to worry about致命 gamma radiation. The Dust Witch has also regained a significant degree of energy-weaving technology, allowing her to alter her clothing to a certain extent or use this ability to develop further technological advancements that interest her...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("To this day, the Dust Witch still loves stargazing and related astronomical research. In the quiet of the night, one can often see eerie flashes emanating from the tower where she resides. Without a doubt, it is likely her using the emblem on her chest to observe the stars she can no longer touch. Even though the starry sky no longer holds the dreams and aspirations of the past, she continues to indulge in the hobby she has had since childhood, alongside the somber wish to one day burn out alongside those very stars.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Nizer also never turns away anyone interested in astronomy, and she is incredibly patient, meticulously explaining any points of confusion to those around her. Combined with her vast knowledge, she is undoubtedly an authoritative mentor in this field.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("No matter how you choose to address her, within the fortress shrouded in mist and energy, resides a mortal god capable of incinerating the entire world...", WthC_ColorData.DUST_RED, pad);
        tooltip.addPara("And she still awaits the day when all things return to silence...", WthC_ColorData.DUST_RED, pad);
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
