package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Cross_0 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Cross Witch's Decorative Style", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Rosalia often needs to attend various religious occasions, so depending on the setting, she can be seen wearing all kinds of ceremonial and elaborate outfits. Perhaps because she's grown accustomed to these cumbersome and movement-restricting garments, even in casual settings she tends to choose overly ornate casual wear.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Of course, in private, these formal dresses lack their theological significance, replaced instead by numerous cross accessories that make clanking metallic sounds when walking. Moreover, in private these clothes often become more bewitching, fully displaying her body's... unique allure...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Actually, Rosalia should always have many metal cross accessories matching her name, showing her love for these heavy decorations. However, in inappropriate settings, they are always hidden inside clothes and ceremonial items; only in private do they freely appear in every possible corner of the formal attire.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("But aside from personal preference, Rosalia's carrying of these numerous metal crosses is actually a necessity, as her special physical constitution indeed needs some external assistance to maintain long-term stability.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("In the past, Rosalia would always wear a triple-tiered high hat, composed of blue, white, and black base colors, with a crown-like decoration forming the brim; at the front of the high hat, an elegant cross emblem that naturally emits a soft glow stands proudly - without doubt, that is the Cross Witch's badge.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Of course... since this hat is too heavy and overly ornate, Rosalia doesn't really like moving around with it on, so now that we're far from Earth, it's rare to see her wearing the high hat anymore.", WthC_ColorData.MID_WHITE, padS); }

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
