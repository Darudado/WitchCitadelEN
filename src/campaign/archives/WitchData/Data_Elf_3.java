package data.campaign.archives.WitchData;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

// 从双面神等道具缝合而来
public class Data_Elf_3 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Detailed introduction of the target individual:", WthC_ColorData.IE_WHITE, pad);

        tooltip.addPara("The Spirit Witch Eden Ludwig Faust might be the most enigmatic presence among all the Council Witches. Unlike the Cross Witch and the Rosalia Witch, who are revered by the world, or the Primordial  Witch and the Soul Witch, who subtly changed the world, she doesn't even represent an extreme combat force like the Dust Witch...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Even though her name is always mentioned alongside these more common witches, does anyone truly understand this recluse?", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("But perhaps for that distant blue planet, it was this unassuming little girl who, with her own hands, opened up a new world capable of nurturing human civilization...", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("This is a piece of buried history, or perhaps, no creature could fully preserve this era... Humans can never determine the true face of the world before the birth of civilization. Who was it that ended the ancient reign of the behemoths, allowing these intelligent species to slowly take over the world?", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Of course... the Eden from those mythical legends and the present her can no longer be connected. Thousands of years ago, after a cataclysmic ritual swept across the planet's surface, she became what she is today. Due to the instability caused by that forced reconstruction, Eden must cover her entire body with clothes, gloves, and socks to prevent the surrounding environment from being affected by stellar radiation.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("As for the specific details of that ritual, they have long been blurred, but it seems Eden wanted to save a dying ancient god's offspring, and the pitiful child who was saved at that time later became the infamous... \"Pale King\" of the dark world.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("In any case, the tall and fearsome \"Hunter\" who once terrified the ancient world has long disappeared, replaced by a lazy and adorable \"little witch\" always accompanied by various pets.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("You can always catch a glimpse of her in some corners related to Crimson City... There are rumors of a little girl asking the spirit creatures for candy in the castle, or gossip about the Crimson Lord having an illegitimate daughter...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Perhaps for a butcher who personally exterminated an entire species, redemption is a dream that will never come.", WthC_ColorData.EIF_ORANGE, pad);
        tooltip.addPara("But that doesn't mean... she can't redeem others...", WthC_ColorData.EIF_ORANGE, pad);
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
