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
public class Data_SSoul_3 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Detailed information on the target individual:", WthC_ColorData.SOUL_GREEN, pad);

        tooltip.addPara("Witches are a species with an extremely broad definition. They can be bizarre entities far beyond the scope of humans, or near-mortal children of immortality, and even... ordinary people skilled in deception can claim this title. So... is it really unacceptable for a long-deceased being to reappear in the world under this identity?", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("The Spirit Witch Su is perhaps the most unique among all council witches. She has undergone three transformations: from human to witch, and then to a necromorph. She is also the only one who has truly passed from this world. Compared to the brief years she spent as a human and a witch, almost all records about her officially begin after her death.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Many mystics may struggle to understand why a being who has spent the majority of her existence as the Lord of the Myriad Rifts still remains listed among the Six Witches under the title of Spirit Witch. Yet, perhaps she is the one truly worth remembering in the shadows.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("It is because of her arrival that all witches chosen by the Crimson Lord have gathered; it is because of her obsession that all intelligent beings possess the right to an endless cycle of death and rebirth; and it is because of her sacrifice that the death of any mortal finally holds the meaning it was meant to have.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Whether you believe you will go to heaven or hell after death, at least you will already be there before you even turn back. When you begin to lament the passing of your life, you should be grateful that the Lord of the Myriad Rifts has carved out this resting place for you, which would not have existed otherwise. Here, you will face judgment. Whether in sorrow or joy, and whether you wish to enter the reincarnation cycle after death, you must pay the price for everything you did in life.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("After redeeming most of your past sins, whether fortunate or unfortunate, you will be reborn as a newborn, beginning a new life that you may either dream of or fear. And this time, how will you live? Will you strive to be a good person? Or will you embrace sin to the end?", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Sadly, the choice is never in your hands, and the experiences and memories of your past life will not offer you any guidance. The Lord of the Myriad Rifts does not symbolize so-called justice. She does not care who you are; she only seeks to ensure that you reach the place you deserve before you forget everything.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("You may feel this is unfair, or perhaps you wish to embrace the void of nothingness after death. But you must understand that this is the responsibility all life must bear. This untouchable deity of the underworld is merely fulfilling the duties of a non-existent master.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("So, rest easy and forget everything. Even if you can never determine whether she is right, at least she is absolutely fair.", WthC_ColorData.SOUL_GREEN, pad);
        tooltip.addPara("She will forever impartially watch over the lives she never cares about, ensuring that after death, they will once again journey to that promised land...", WthC_ColorData.SOUL_GREEN, pad);
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
