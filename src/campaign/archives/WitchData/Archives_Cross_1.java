package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Cross_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Primordial Spirit Ring: Radiance", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Cross Witch's ring is named 'Radiance', a particularly eye-catching Primordial Spirit Ring where the band, base, and gem all display an extremely pure milky white color. Small dark runes float on the outer surface of the band, while an asymmetrically designed arc cross emblem on the outer eye socket gives Radiance its unique character. Of course, the most distinctive feature is that dazzling gem that perfectly embodies its name of Radiance.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("According to multiple biological experiments conducted by Rosalia, describing the gem's flash as 'blinding' is still somewhat conservative. When the ring's light output reaches medium-high levels, it can cause prolonged blindness in nearby humans, and at maximum power, it can directly use intense light to completely destroy the target's visual system and cause permanent blindness without issue.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Regarding custom functions, unlike the Primordial Witch who needs long-term research and the Artifact Witch who needs numerous auxiliary spells, Rosalia doesn't need to deal with much precise work, and most functions needed in daily life can be achieved through herself, so Radiance's custom functions have a unique twisted humor compared to other Primordial Spirit Rings.", WthC_ColorData.HIGH_BLUE, pad);

        tooltip.addPara("First, Radiance has the function of real-time observation and recording of nearby lifeforms' status, allowing Rosalia to observe the number and specific parameters of surrounding creatures. With this function, she can even know exactly how many insects are lurking in the shadows nearby.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("With Lika's help, Rosalia used her experience to program a 'psychological analysis' and 'emotional analysis' system into the ring. Through self-learning and information interpretation, the ring passively provides the Cross Witch with analysis of human psychological activities and emotional changes before her. In the vast majority of cases, the information provided by this system is extremely accurate. Rosalia even proposed to Lika to upgrade this function to a basic feature and include it in all Primordial Spirit Rings, but unsurprisingly, it was strongly opposed by others in the council and came to nothing.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Secondly, Radiance's flash can include additional psychological suggestions from Rosalia, achieving an effect similar to instantaneous wide-area hypnosis. Although this function theoretically only affects non-intelligent creatures and ordinary humans, unique beings like the Artifact Witch, who are close to ordinary humans, have also experienced being hypnotized by the ring.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Therefore, shortly after, Pale Moon's Primordial Spirit Ring 'Deep Color' was additionally equipped with anti-mind control components to prevent further enchantment from the Cross Witch.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Finally, Radiance links in real-time with the Cross Witch badge, simultaneously recording Rosalia's surroundings from two perspectives in video form, and she can permanently save certain segments whenever she wishes to share with other Primordial Spirit Ring owners.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("According to the Crimson Lord who shares 'similar interests' with Rosalia, this witch lady has an astonishing number of... 'fascinating segment' collections, often filled with 'wild yet subtle beauty of human nature' and 'charm that reminds creatures of their instincts'. For the usually unserious Crimson Lord to make such an evaluation, it's indeed hard not to question how chaotic Rosalia's private life must be...", WthC_ColorData.MID_WHITE, padS);
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
